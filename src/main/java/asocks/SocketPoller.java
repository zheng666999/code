package asocks;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.BlockingQueue;

import asocks.task.PollerTask;

/**
 * socket 拉取
 */
public class SocketPoller implements PollerTask , ComponentLifeCycle{
	
	private volatile boolean isRunning = true;
	private BlockingQueue<Socket> queue = null; 
	private final Object lockObject = new Object();
	
	public SocketPoller(BlockingQueue<Socket> queue) {
		this.queue = queue;
	}
	
	public void poll() {
		
	}

	@Override
	public void run() {
		
		//这里就不使用quartz的复杂启动设置，默认就是运行
		while(isRunning) {
			//不对lockObj加锁，太重，这里不会修改，只会读取
				try {
					Socket oneObj = queue.take();
					//todo 找到对应的处理器，首先第一步必须要解析出来输入流数据
					//socks5处理
				} catch (Exception e) {
					System.out.println("poll data error , "+e);
				}
		}
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


	@Override
	public NTStatus getComponentStatus() {
		return isRunning ? NTStatus.RUN : NTStatus.STOP;
	}
	

}
