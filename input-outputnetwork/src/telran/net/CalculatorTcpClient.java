package telran.net;


import java.util.*;
import telran.view.*;

public class CalculatorTcpClient {

	public static void main(String[] args) throws Exception {
		try(TcpHandler client = new TcpHandler("localhost", 5014)) {		
		InputOutput io = new ConsoleInputOutput();
		 Menu menu = new Menu("TCPcalculator", Item.of("send request", io1 -> {
			 HashSet<String> requests = new HashSet<>(Arrays.asList("+", "-", "*", "/"));
			 String oper = io1.readString("Enter arithmetic operator  " + requests, "", requests);
			 Double[] op = new Double[2];
			 
			 op[0] = io1.readDouble("Enter the first operand", "");
			 op[1] = io1.readDouble("Enter the second operand", "");
			
			 try {
			 String response = client.send(oper, op);
			 io1.writeLine(response);
			} catch (Exception e) {
				throw new RuntimeException(e.toString());
			}
		 }), Item.ofExit());
		 menu.perform(io);
		}
		
		
	}

}
