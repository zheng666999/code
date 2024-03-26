package asocks.processor;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;

public class SocksProcessor implements Processor{

	
	
	@Override
	public void process(Socket socket) {
		
		InetAddress inetAddress = socket.getLocalAddress();
		
		String localHost = inetAddress.getHostName();
		
		Integer port = socket.getLocalPort();
		
		
	}
	
	
	public void deal(Socket socket) {
		
		
		
		ArrayList<Byte> arrayList = new ArrayList<Byte>();
		try {
			InputStream inputStream = socket.getInputStream();
			
			byte[] buffer = new byte[1];
			while (-1 == inputStream.read(buffer)) {
				arrayList.add(buffer[0]);
			}
			Byte[] totalByte = new Byte[arrayList.size()];
			arrayList.toArray(totalByte);
			String inputString = Arrays.toString(totalByte);
			
			
		} catch (IOException e) {
			System.out.println("socket get inputStream error : "+e);
		}
	}
	
	
}
