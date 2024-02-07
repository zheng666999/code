package customTomcat;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;

import org.apache.catalina.Contained;
import org.apache.catalina.Container;
import org.apache.catalina.Request;
import org.apache.catalina.Response;
import org.apache.catalina.Valve;
import org.apache.catalina.ValveContext;

/**
 * 自定义日志valve
 */
public class ClientIPLoggerValve implements Valve , Contained{
	protected Container container;
	
	@Override
	public Container getContainer() {
		// TODO Auto-generated method stub
		return container;
	}

	@Override
	public void setContainer(Container container) {
		// TODO Auto-generated method stub
		this.container = container;
	}

	@Override
	public String getInfo() {
		// TODO Auto-generated method stub
		String infoString = this.getClass().getName();
		return infoString;
	}

	@Override
	public void invoke(Request request, Response response, ValveContext context) throws IOException, ServletException {
		// invokeNext
		context.invokeNext(request, response);
		System.out.println("client ip logger valve");
		ServletRequest sRequest = request.getRequest();
		System.out.println(sRequest.getRemoteAddr());
		System.out.println("--------------------------------------------------------");
		
	}
}
