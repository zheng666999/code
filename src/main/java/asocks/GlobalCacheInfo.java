package asocks;

import java.net.Socket;
import java.util.HashMap;
import java.util.concurrent.locks.ReentrantLock;


public class GlobalCacheInfo {
	
	private HashMap<String, Socket> socketCacheHashMap ;
	
	private ReentrantLock reentrantLock = new ReentrantLock();
	//ReentrantReadWriteLock
	
	
	public GlobalCacheInfo() {
		socketCacheHashMap = new HashMap<String, Socket>();
	}
	
	public Socket getCacheSocket(String host,String port) {
		reentrantLock.lock();
		try {
			return socketCacheHashMap.get(host+"_"+port);
		}finally {
			reentrantLock.unlock();
		}
	}
	
	/**
	 * 更新失效socket
	 */
	public void updateCahceSocket(String host,String port,Socket socket) {
		reentrantLock.lock();
		try {
			socketCacheHashMap.put(host+"_"+port, socket);
		}finally {
			reentrantLock.unlock();
		}
		
	}

}
