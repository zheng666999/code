package customTomcat;

import java.io.IOException;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.Contained;
import org.apache.catalina.Container;
import org.apache.catalina.Request;
import org.apache.catalina.Response;
import org.apache.catalina.Valve;
import org.apache.catalina.ValveContext;

public class SimpleWrapperValve implements Valve,Contained{

	Container container;
	@Override
	public String getInfo() {
		// TODO Auto-generated method stub
		String infoString = this.getClass().getName();
		return infoString;
	}

	@Override
	public void invoke(Request request, Response response, ValveContext context) throws IOException, ServletException {
		SimpleWrapper wrapper = (SimpleWrapper)getContainer();
		ServletRequest sRequest = request.getRequest();
		ServletResponse sResponse = response.getResponse();
		Servlet servlet = null;
		HttpServletRequest hRequest = null;
		HttpServletResponse hResponse = null;
		if(sRequest instanceof HttpServletRequest)
			hRequest = (HttpServletRequest)sRequest;
		if(sResponse instanceof HttpServletResponse)
			hResponse = (HttpServletResponse)sResponse;
		try {
			servlet = wrapper.allocate();
			if(hResponse!=null && hRequest!=null) {
				servlet.service(hRequest, hResponse);
			}else {
				servlet.service(sRequest, sResponse);
			}
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}
	}

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

}
