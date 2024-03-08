package eventListener;

import java.util.Vector;

public class EventSource {
	
	//事件监听器存储链表
	private Vector<EventListener> listenerListeners = new Vector<EventListener>();
	
	//添加事件监听器
	public void addListener(EventListener eventListener) {
		listenerListeners.add(eventListener);
	}
	
	//移除事件监听器
	public void removeListener(EventListener eventListener) {
		listenerListeners.remove(eventListener);
	}
	
	public void notifyListenerEvents(EventObject event) {
	//收到事件，找到对应的处理器处理
		for(EventListener eventListener : listenerListeners) {
			eventListener.handleEvent(event);
		}
	}

}
