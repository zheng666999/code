package asocks.task;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

import asocks.CommonUtils;
import asocks.CustomException;
import asocks.ErrorConstant;
import asocks.GlobalCacheInfo;

/**
 * socks5 处理线程
 * 生命周期就是一次socket的生命周期，如果本地关闭了，那么就认为结束
 *
 * 测试连接需要缓存：防止多客户端访问同一个网站的情况，
 * 		不能使用传统意义的连接池概念，连接池适用于数据库这样目标地址固定的，代理场景并不是一个池子合适的场景：可以向netty学习下经验。
 * 		暂定计划，先不使用池化的概念，只使用连接缓存，如果缓存存在就直接获取（目标服务不会很多，当让可以另外启动一个事件处理来清除失效连接、超时连接等）
 * 	这里属于线程内部，缓存不能放置在这里，必须在外面一个公共的地方
 */
public class Socks5Processor implements ProcessTask{

	private GlobalCacheInfo globalCacheInfo  ; //包含testSocket引用
	private Socket localSocket;
	
	public Socks5Processor(GlobalCacheInfo globalCacheInfo,Socket localSocket) {
		this.globalCacheInfo = globalCacheInfo;
		this.localSocket = localSocket;
	}
	@Override
	public void run() {
		try {
			this.process();
		}catch (Exception e) {
			System.out.println("error:"+CommonUtils.getErrorMsg(e));
		}finally {
			globalCacheInfo = null;
			try {
				localSocket.close();
			} catch (IOException e) {
				System.out.println("error:"+CommonUtils.getErrorMsg(e));
			}
		}
	}
	
	//顺序处理,协商、连接、认证
	public void process() throws IOException  {
		
		handleAuthenticationRequest();
		
		Socket testSocket = handleConnectionRequest();
		
		handleTransferRequest(testSocket);
	}
	
	private void handleAuthenticationRequest() throws IOException {
		byte[] readDataFromLocal = CommonUtils.readFromSocket(localSocket);

		if(readDataFromLocal == null || readDataFromLocal.length == 0) {
			throw new CustomException(ErrorConstant.READ_DATA_ERROR_STRING);
		}
		
		//判断协议、是否是协商、建立请求、正常数据流,用长度判断是不合理的
		/**request 格式
		 * ver  nmethods methods
		 * 后面两个参数是否需要gua
		 */
		byte ver = readDataFromLocal[0];
		
		/** reply格式
		 * ver   method
		 * 1       1
		 */
		if(ver != 0x05) {
			CommonUtils.writeToSocket(localSocket, new byte[] {0x05,-1});
		}
		
		byte NMETHODS = readDataFromLocal[1];
		Integer nmethodsInteger =  NMETHODS&0xFF;
		for(int i = 0 ; i<nmethodsInteger ; i++) {
			switch (readDataFromLocal[2+i]&0xFF) {
				case 0x00:
					System.out.println("method is "+"NO AUTHENTICATION REQUIRED");
					break;
				case 0x01:
					System.out.println("method is "+"GSSAPI");
					break;
				case 0x02:
					System.out.println("method is "+"USERNAME/PASSWORD");
					break;
				case 0x03:
					System.out.println("method is "+"to X'7F' IANA ASSIGNED");
					break;
				case 0x80:
					System.out.println("method is "+"to X'FE' RESERVED FOR PRIVATE METHODS");
					break;
				case 0xFF:
					System.out.println("method is "+"NO ACCEPTABLE METHODS");
					break;
				default:
					//default is end
					CommonUtils.writeToSocket(localSocket, new byte[] {0x05,-1});
					break;
			}
		}
		CommonUtils.writeToSocket(localSocket, new byte[] {0x05,0x00});
	}
	
	private Socket handleConnectionRequest() throws  IOException {
		byte[] readDataFromLocal = CommonUtils.readFromSocket(localSocket);
		int length = readDataFromLocal.length;
		/**request格式
		 * ver   cmd   rsv  atyp  dst.addr  dst.port
		 *  1     1     1     1     4/16       2
		 */
		byte cmd = readDataFromLocal[1];
		byte rsv = readDataFromLocal[2];
		byte atyp = readDataFromLocal[3];
		byte[] dstAddr = Arrays.copyOfRange(readDataFromLocal, 4, length-2);//readDataFromLocal[4] ... readDataFromLocal[length-2]
		byte[] dstPort = Arrays.copyOfRange(readDataFromLocal, length-2, length);//readDataFromLocal[length-2] ... readDataFromLocal[length-1]
		if(atyp == 0x01) {
			// 4 bytes , check ipv4
		}
		if(atyp == 0x04) {
			//16 bytes , check ipv6
		}
		
		String address = new String(dstAddr,StandardCharsets.UTF_8); 
		String port = new String(dstPort,StandardCharsets.UTF_8); 
		
		byte[] response ;
		Socket testSocket;
		//todo 建立连接,可以考虑是否在这里处理
		try {
		     testSocket = getTestSocket(address,port);
		}catch (Exception e) {
			response = new byte[] {0x05,0x01,0x00,1,0,0,0,0,0,0};
			CommonUtils.writeToSocket(localSocket, response);
			throw new CustomException(CommonUtils.getErrorMsg(e));
		}
		
		//建立请求 
		/**reply格式
		 * ver   rep   rsv  atyp  bind.addr bind.port
		 * 
		 */
		//后面两个暂时不管
		response = new byte[] {0x05,0x00,0x00,atyp,0,0,0,0,0,0};
		CommonUtils.writeToSocket(localSocket, response);
		
		return testSocket;
		
	}
	
	//转发数据
	//可以考虑启动线程
	private void handleTransferRequest(Socket dstSocket) throws IOException {	
		byte[] readDataFromLocal = CommonUtils.readFromSocket(localSocket);
		CommonUtils.writeToSocket(dstSocket, readDataFromLocal);
		byte[] remoteData =  CommonUtils.readFromSocket(dstSocket);
		CommonUtils.writeToSocket(localSocket, remoteData);
	}
	
	private Socket getTestSocket(String address,String port) throws  IOException {
		Socket testSocket = globalCacheInfo.getCacheSocket(address , port);
		
		if(testSocket == null || !testSocket.isConnected()) {
			testSocket = CommonUtils.connect(address, Integer.valueOf(port));
			globalCacheInfo.updateCahceSocket(address , port, testSocket);
		}
		return testSocket;
	}
	
}
