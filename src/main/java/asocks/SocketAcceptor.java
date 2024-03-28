package asocks;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

import asocks.task.Socks5Processor;

/**
 * socket接收器
 */
public class SocketAcceptor {
	
	private ServerSocket serverSocket;
	private CommonQueue<Socket> queue = null;  

	public SocketAcceptor(CommonQueue<Socket> queue) {
		this.queue = queue;
	}
	
	public void init() throws IOException {
		bind();
		accept();
	}
	
	/**
	 * 端口绑定
	 */
	public void bind() throws IOException {
		serverSocket = new ServerSocket();
		InetSocketAddress inetSocketAddress = new InetSocketAddress(InetAddress.getLocalHost(), 9889);
		serverSocket.bind(inetSocketAddress);
	}
	
	/**
	 * socket接收
	 */
	public void accept() {
		try {
			Socket socket = serverSocket.accept();
			//找到处理器单独处理该socket
			
		} catch (IOException e) {
			System.out.println("server accept error ,"+e);
		}
	}

}
