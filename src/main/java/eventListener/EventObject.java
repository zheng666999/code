package eventListener;

import org.apache.naming.java.javaURLContextFactory;

public class EventObject extends java.util.EventObject{
	

	public EventObject(String source) {
		super(source);
		// TODO Auto-generated constructor stub
	}
	
	public void doEvent() {
		System.out.println("通知一个事件源source:"+this.getSource());
	}

}
