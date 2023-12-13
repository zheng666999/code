package customTomcat;

import java.io.IOException;

import javax.servlet.ServletException;

import org.apache.catalina.Pipeline;
import org.apache.catalina.Request;
import org.apache.catalina.Response;
import org.apache.catalina.Valve;
import org.apache.catalina.Wrapper;

/**
 * pipeLine的实现类
 */
public class SimplePipeLine implements Pipeline{

	public SimplePipeLine(Wrapper wrapper) {
		
	}
	@Override
	public Valve getBasic() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setBasic(Valve valve) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addValve(Valve valve) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Valve[] getValves() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void invoke(Request request, Response response) throws IOException, ServletException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeValve(Valve valve) {
		// TODO Auto-generated method stub
		
	}

}
