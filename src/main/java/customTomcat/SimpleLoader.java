package customTomcat;

import java.beans.PropertyChangeListener;
import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;
import java.net.URLStreamHandler;

import org.apache.catalina.Container;
import org.apache.catalina.DefaultContext;
import org.apache.catalina.Lifecycle;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.LifecycleListener;
import org.apache.catalina.Loader;

/**
 * 
 */
public class SimpleLoader implements Loader , Lifecycle{
	public static final String WEB_ROOT =
			System.getProperty("user.dir") + File.separator + "webroot";
	
	ClassLoader classLoader = null;
	Container container = null;
	public SimpleLoader() {
		try {
			URL[] urls = new URL[1];
			URLStreamHandler streamHandler = null;
			File classPathFile = new File(WEB_ROOT);
			String repositoryString = 
					(new URL("file",null,classPathFile.getCanonicalPath()+File.separator)).toString();
			urls[0] = new URL(null,repositoryString,streamHandler);
			classLoader = new URLClassLoader(urls);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	@Override
	public ClassLoader getClassLoader() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Container getContainer() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void setContainer(Container container) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public DefaultContext getDefaultContext() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void setDefaultContext(DefaultContext defaultContext) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public boolean getDelegate() {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public void setDelegate(boolean delegate) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public String getInfo() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public boolean getReloadable() {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public void setReloadable(boolean reloadable) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void addPropertyChangeListener(PropertyChangeListener listener) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void addRepository(String repository) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public String[] findRepositories() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public boolean modified() {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public void removePropertyChangeListener(PropertyChangeListener listener) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void addLifecycleListener(LifecycleListener listener) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public LifecycleListener[] findLifecycleListeners() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void removeLifecycleListener(LifecycleListener listener) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void start() throws LifecycleException {
		// TODO Auto-generated method stub
		System.out.println("Starting SimpleLoader");
	}
	@Override
	public void stop() throws LifecycleException {
		// TODO Auto-generated method stub
		System.out.println("Stopping SimpleLoader");
	}
}
