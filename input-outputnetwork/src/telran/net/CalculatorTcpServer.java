package telran.net;

import java.io.IOException;

public class CalculatorTcpServer {
	static final int PORT=5014;
	public static void main(String[] args) throws IOException {
		
		TcpServer srv = new TcpServer(PORT, new CalculatorProtocol());
		srv.run();

	}

}
