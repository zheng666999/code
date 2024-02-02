package customTomcat;

import org.apache.catalina.Lifecycle;
import org.apache.catalina.LifecycleEvent;
import org.apache.catalina.LifecycleListener;

public class SimpleContextlifecycleListener implements LifecycleListener{

	@Override
	public void lifecycleEvent(LifecycleEvent event) {
		// TODO Auto-generated method stub
		Lifecycle lifecycle = event.getLifecycle();
		System.out.println("SimpleContxtLifecycleListener's event "+event.getType().toString());
		
		if(Lifecycle.START_EVENT.equals(event.getType())) {
			System.out.println("Starting context");
		}
		else if(Lifecycle.STOP_EVENT.equals(event.getType())) {
			System.out.println("Stopping context");
		}
	}

}
