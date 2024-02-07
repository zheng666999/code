package customTomcatHostTest;

import java.io.CharConversionException;

import org.apache.catalina.Connector;
import org.apache.catalina.Context;
import org.apache.catalina.Host;
import org.apache.catalina.Lifecycle;
import org.apache.catalina.LifecycleListener;
import org.apache.catalina.Loader;
import org.apache.catalina.Wrapper;
import org.apache.catalina.connector.http.HttpConnector;
import org.apache.catalina.core.StandardHost;
import org.apache.catalina.core.StandardWrapper;
import org.apache.catalina.loader.WebappLoader;

import customTomcat.SimpleContext;
import customTomcat.WrapperTest.SimpleContextConfig;

public class BootstrapHostTest {

    public static void main(String[] args) {
    	
    	System.setProperty("catalina.base", System.getProperty("user.dir"));
    	
    	Connector connector = new HttpConnector();
    	
    	Wrapper wrapper1 = new StandardWrapper();
    	wrapper1.setName("Primitive");
    	wrapper1.setServletClass("PrimitiveServlet");
    	
    	Wrapper wrapper2 = new StandardWrapper();
    	wrapper2.setName("Modern");
    	wrapper2.setServletClass("ModernServlet");
    	
    	Context context = new SimpleContext();
    	context.setPath("/app1");
    	context.setDocBase("app1");
    	context.addChild(wrapper1);
    	context.addChild(wrapper2);
    	
    	LifecycleListener listener = new SimpleContextConfig();
    	
    	((Lifecycle)context).addLifecycleListener(listener);
    	
    	Host host = new StandardHost();
    	
    	host.addChild(context);
    	host.setName("localHost");
    	host.setAppBase("webApps");
    	Loader loader = new WebappLoader();
    	loader.setContainer(context);
    	context.setLoader(loader);
    	context.addServletMapping("/Primitive","Privitive");
    	context.addServletMapping("/Modern", "Modern");
    	connector.setContainer(host);
    	try {
    		connector.initialize();
    		((Lifecycle)connector).start();
    		((Lifecycle)host).start();
    		System.in.read();
    		((Lifecycle)host).stop();
    	}catch (Exception e) {
    		//System.out.println(e.getMessage());
    		String msg = "";
    		for(StackTraceElement element : e.getStackTrace()) {
    			msg = msg + element.toString();
    		}
    		System.out.println(msg);
		}	
    }
}
