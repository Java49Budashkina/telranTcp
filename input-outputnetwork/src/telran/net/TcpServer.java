package telran.net;

import java.io.IOException;
import java.net.*;

public class TcpServer implements Runnable {
private int port;
private ApplProtocol protocol;
private  ServerSocket srvSocket;

public TcpServer(int port, ApplProtocol prot) throws IOException {
	this.port = port;
	protocol = prot;
	srvSocket = new ServerSocket(port);
}

	@Override
	public void run() {
		System.out.println("Server is listening to port " + port);
		
		try {
			while(true){
				Socket socket = srvSocket.accept();
				TcpClientServer clientSrv = new TcpClientServer(socket, protocol);
				Thread thread = new Thread(clientSrv);
				thread.start();
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
		

}
