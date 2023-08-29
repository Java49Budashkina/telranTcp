package telran.net;
import java.net.*;
import java.io.*;

public class TcpClientServer implements Runnable {
Socket socket;
ObjectInputStream input;
ObjectOutputStream output;
ApplProtocol protocol;
public TcpClientServer(Socket soc, ApplProtocol prot) throws IOException {
	socket = soc;
	protocol = prot;
	input = new ObjectInputStream(socket.getInputStream());
	output = new ObjectOutputStream(socket.getOutputStream());
}
	@Override
	public void run() {
		try {
			while (true) {
				Request request = (Request) input.readObject();
				Response response = protocol.getResponse(request);
				output.writeObject(response);
			}
			
		}catch(EOFException e) {
			System.out.println("client close normally connection");
			
		}catch(Exception e) {
			System.out.println("client close annormally connection" +
		e.getMessage());
			
		}

	}

}
