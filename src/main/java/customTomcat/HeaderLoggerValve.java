package customTomcat;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import org.apache.catalina.Contained;
import org.apache.catalina.Container;
import org.apache.catalina.Request;
import org.apache.catalina.Response;
import org.apache.catalina.Valve;
import org.apache.catalina.ValveContext;

/**
 * 自定义valve打印请求头
 */
public class HeaderLoggerValve implements Valve , Contained{

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
		// TODO Auto-generated method stub
		context.invokeNext(request, response);
		System.out.println("header logger valve");
		ServletRequest sRequest = request.getRequest();
		if(sRequest instanceof HttpServletRequest) {
			HttpServletRequest hRequest = (HttpServletRequest)sRequest;
			Enumeration headerNames = hRequest.getHeaderNames();
			while(headerNames.hasMoreElements()) {
				String headerName = headerNames.nextElement().toString();
				String headerValue = hRequest.getHeader(headerName);
				System.out.println(headerName+":"+headerValue);
			}
		}else {
			System.out.println("not an http request");
			System.out.println("-----------------------------------------------");
		}
	}

}
