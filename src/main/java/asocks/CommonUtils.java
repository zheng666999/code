package asocks;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Arrays;

/**
 * 通用工具函数
 */
public class CommonUtils {
	
	public static Socket connect(String host , int port) throws IOException {
		Socket socket = new Socket(host, port);
		if(!socket.isConnected()) {
			socket.connect(new InetSocketAddress(host, port));
		}
		return socket;
	}

	public static byte[] readFromSocket(Socket socket) throws IOException {
		InputStream inputStream = socket.getInputStream();
		byte[] buffer = new byte[100];
		byte[] once = new byte[1];
		int max = 0;
		while (-1 != inputStream.read(once)) {
			if(max >= buffer.length) {
				//注意里面的Object的clone方法
				buffer = Arrays.copyOf(buffer, max*2);
			}
			buffer[max] = once[0];
			max++;
		}
		byte[] result = Arrays.copyOf(buffer, max);
		return result;
	}
	
	public static void writeToSocket(Socket socket,byte[] data) throws IOException {
		OutputStream outputStream = socket.getOutputStream();
		outputStream.write(data);
		outputStream.flush();	
	}
	
	public static String getErrorMsg(Exception e) {
		String errorMsgString = "" ;
		for(StackTraceElement stackTraceElement : e.getStackTrace()) {
			errorMsgString = errorMsgString + "\r\n"+stackTraceElement.toString();
		}
		return e + "\r\n"+errorMsgString;
	}
}
