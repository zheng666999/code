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

	private GlobalCacheInfo globalCacheInfo  ;
	
	public Socks5Processor(GlobalCacheInfo globalCacheInfo) {
		this.globalCacheInfo = globalCacheInfo;
	}
	@Override
	public void run() {
		
		
	}
	
	public void process(Socket localSocket)  {
		
		InetAddress inetAddress = localSocket.getLocalAddress();
		
		//本地socket的ip、port信息
		String localHost = inetAddress.getHostName();
		
		Integer port = localSocket.getLocalPort();
		
		//从socket读取数据流
		byte[] readDataFromLocal = CommonUtils.readFromSocket(localSocket);
		
		if(readDataFromLocal == null || readDataFromLocal.length == 0) {
			throw new CustomException(ErrorConstant.READ_DATA_ERROR_STRING);
		}
		
		//判断协议、是否是协商、建立请求、正常数据流
		/**request 格式
		 * ver  nmethods methods
		 */
		byte ver = readDataFromLocal[0];
		
		if(ver != 0x05) {
			throw new CustomException(ErrorConstant.SOCKS5_VERSION_ERROR_STRING);
		}
		
		if(readDataFromLocal.length == 4) {
			//authentication
			handleAuthenticationRequest(readDataFromLocal,localSocket);
		}else if(readDataFromLocal.length <= 22) {
			//connect
			handleConnectionRequest(readDataFromLocal,localSocket);
		}else {
			//transfer data
			handleTransferRequest(readDataFromLocal,localSocket);
		}
		
		
	}
	
	private void handleAuthenticationRequest(byte[] readDataFromLocal , Socket socket) throws IOException {
		/** reply格式
		 * ver   method
		 * 1       1
		 */
		byte[] response = new byte[2];
		response[0] = 0x05;
		response[1] = 0x00;
		CommonUtils.writeToSocket(socket, response);
	}
	
	private void handleConnectionRequest(byte[] readDataFromLocal, Socket socket) throws  IOException {
		int length = readDataFromLocal.length;
		/**request格式
		 * ver   cmd   rsv  atyp  dst.addr  dst.port
		 *  1     1     1     1     4-16       2
		 */
		byte cmd = readDataFromLocal[1];
		byte rsv = readDataFromLocal[2];
		byte atyp = readDataFromLocal[3];
		byte[] dstAddr = Arrays.copyOfRange(readDataFromLocal, 4, length-2);;//readDataFromLocal[4] ... readDataFromLocal[length-2]
		byte[] dstPort = Arrays.copyOfRange(readDataFromLocal, length-2, length);;//readDataFromLocal[length-2] ... readDataFromLocal[length-1]
		if(atyp == 0x01) {
			// 4 bytes , check ipv4
		}
		if(atyp == 0x04) {
			//16 bytes , check ipv6
		}
		
		String address = new String(dstAddr,StandardCharsets.UTF_8); 
		String port = new String(dstPort,StandardCharsets.UTF_8); 
		
		//todo 建立连接
		Socket testSocket = getTestSocket(address,port);
		
		//建立请求 
		/**reply格式
		 * ver   rep   rsv  atyp  bind.addr bind.port
		 * 
		 */
		byte[] response = new byte[1];
		//todo 返回本地
		CommonUtils.writeToSocket(socket, response);
		
	}
	
	private void handleTransferRequest(byte[] readDataFromLocal, Socket socket) {
		
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
