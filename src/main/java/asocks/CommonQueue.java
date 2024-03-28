package asocks;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class CommonQueue<T> {

	private final Object placeHolder = new Object();
	private final Lock lock = new ReentrantLock();
	private LinkedList<T> queue;
	private HashMap<T, Object> cache;
	
	public CommonQueue() {
		this.queue = new LinkedList();
		this.cache = new HashMap();
	}
	
	public void enQueue(T e) {
		try {
			lock.lock();
			queue.addLast(e);
			cache.put(e, placeHolder);
		}finally {
			lock.unlock();
		}
	}
	
	public synchronized T poll() {
		try {
			lock.lock();
			T res = queue.poll();
			cache.remove(res);
			return res;
		}finally {
			lock.unlock();
		}
	}
	
	public int size() {
		return cache.size();
	}
	
	public void clear() {
		try {
			lock.lock();
			queue.clear();
			cache.clear();
		}finally {
			lock.unlock();
		}
	}
}
