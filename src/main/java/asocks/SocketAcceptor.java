package asocks;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * socket接收器
 */
public class SocketAcceptor {
	
	
	/**
	 * 端口绑定
	 */
	public void bind() {
		
	}
	
	/**
	 * socket接收
	 */
	public void accept(ServerSocket serverSocket) {
		try {
			Socket socket = serverSocket.accept();
			//找到处理器单独处理该socket
			
		} catch (IOException e) {
			System.out.println("server accept error ,"+e);
		}
	}

}
