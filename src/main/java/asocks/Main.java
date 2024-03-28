package asocks;

import java.io.IOException;
import java.net.Socket;

public class Main {
	
	public static void main(String[] args) {
		CommonQueue<Socket> queue = new CommonQueue<Socket>();
		SocketAcceptor acceptor = new SocketAcceptor(queue);
		SocketPoller poller = new SocketPoller(queue);
		try {
			Thread pollThread = new Thread(poller);
			pollThread.start();
			// 这里可以考虑启动线程
			acceptor.init();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("启动失败"+CommonUtils.getErrorMsg(e));
		}
		
//		try {
//			Thread.currentThread().join();
//		} catch (InterruptedException e) {
//			System.out.println("error"+CommonUtils.getErrorMsg(e));
//		}

	}

}
