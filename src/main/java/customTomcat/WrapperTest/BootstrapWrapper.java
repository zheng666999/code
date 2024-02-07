package customTomcat.WrapperTest;

import java.util.concurrent.ExecutionException;

import org.apache.catalina.Connector;
import org.apache.catalina.Context;
import org.apache.catalina.Lifecycle;
import org.apache.catalina.LifecycleListener;
import org.apache.catalina.Loader;
import org.apache.catalina.Wrapper;
import org.apache.catalina.connector.http.HttpConnector;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.core.StandardWrapper;
import org.apache.catalina.loader.WebappLoader;

public class BootstrapWrapper {

	public static void main(String[] args) {
		
		System.out.println("user.dir : "+System.getProperty("user.dir"));
		System.setProperty("catalina.base", System.getProperty("user.dir"));
		
		Connector connector = new HttpConnector();
		
		Wrapper wrapper1 = new StandardWrapper();
		wrapper1.setName("Primitive");
		wrapper1.setServletClass("PrimitiveServlet");
		
		Wrapper wrapper2 = new StandardWrapper();
		wrapper2.setName("Modern");
		wrapper2.setServletClass("ModernServlet");
		
		Context context = new StandardContext();
		context.setPath("myApp");
		context.setDocBase("myApp");
		LifecycleListener listener = new SimpleContextConfig();
		((Lifecycle)context).addLifecycleListener(listener);
		
		context.addChild(wrapper1);
		context.addChild(wrapper2);
		
		Loader loader = new WebappLoader();
		context.setLoader(loader);
		
		context.addServletMapping("/Primitive", "Primitive");
		context.addServletMapping("/Modern", "Modern");
		
		connector.setContainer(context);
		
		try {
			connector.initialize();
			((Lifecycle)connector).start();
			((Lifecycle)context).start();
			System.in.read();
			((Lifecycle)context).stop();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}
