package asocks;

import java.net.Socket;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import asocks.task.PollerTask;
import asocks.task.Socks5Processor;

/**
 * socket 拉取
 */
public class SocketPoller implements PollerTask , ComponentLifeCycle{
	
	private volatile boolean isRunning = true;
	private CommonQueue<Socket> queue = null;  // 服务器接收的socket都会在这里排队
	private final Object lockObject = new Object();
	private final Object signalObject = new Object();
	private GlobalCacheInfo globalCacheInfo  ;
	private ThreadPoolExecutor executor;
	
	
	
	public SocketPoller(CommonQueue<Socket> queue) {
		this.queue = queue;
		globalCacheInfo = new GlobalCacheInfo();
		executor = new ThreadPoolExecutor(4, 8, 600, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>(600));
	}


	@Override
	public void run() {
		
		//这里就不使用quartz的复杂启动设置，默认就是运行
		while(isRunning) {
			//不对lockObj加锁，太重，这里不会修改，只会读取
			try {
				if(queue.size()==0) {
					synchronized (this) {
						signalObject.wait();
					}
				}
			
				Socket oneObj = queue.poll();
				if(oneObj == null) {
					continue;
				}
				//todo 找到对应的处理器，首先第一步必须要解析出来输入流数据
				//socks5处理
				Socks5Processor processor = new Socks5Processor(globalCacheInfo,oneObj);
				executor.submit(processor);
			} catch (Exception e) {
				System.out.println("poll data error , "+e);
			}
		}
		clear();
	}
	
	/**
	 * 这个对象需要被调用者处理
	 */
	public synchronized void notifyThisObj() {
		signalObject.notifyAll();
	}


	@Override
	public  void start() {
		System.out.println("current running status : "+getComponentStatus().getDesc());
	}

	@Override
	public void stop() {
		synchronized(lockObject) {
			isRunning = false;
		}
	}
	
	private synchronized void  clear() {
		queue = null;
		globalCacheInfo = null;
		executor.close();
		executor = null;
	}


	@Override
	public NTStatus getComponentStatus() {
		return isRunning ? NTStatus.RUN : NTStatus.STOP;
	}
	
	
}
