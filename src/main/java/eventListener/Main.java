package eventListener;


/**
 * 
 */
public class Main {

	public static void main(String[] args) {
		
		
		EventSource eventSource = new EventSource();
		
		eventSource.addListener(new EventListener() {
			@Override
			public void handleEvent(EventObject event) {
				event.doEvent();
				if(event.getSource().equals("closeWindows")) {
					System.out.println("doCLose");
				}
			}

		});
		
		eventSource.addListener(new EventListener() {
			@Override
			public void handleEvent(EventObject event) {
				System.out.println("gogogo");
				
			}
		});
		
		/**
		 * 这里面的设计有问题，通知函数里面没有判断这个事件对象被什么接收，就会导致所有的监听器都会执行
		 * 以这个场景为例，虽然不是都打印了场景标识日志，但是第一个Listener的doEvent方法确实被调用了
		 */
		eventSource.notifyListenerEvents(new EventObject("openWindows"));
		
		
	}
	
}
