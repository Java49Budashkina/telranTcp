package telran.net;


import java.util.*;
import telran.view.*;

public class CalculatorTcpClient {

	public static void main(String[] args) throws Exception {
		TcpHandler client = new TcpHandler("localhost", 5014);
		
		InputOutput io = new ConsoleInputOutput();
		 Menu menu = new Menu("TCPcalculator", Item.of("send request", io1 -> {
			 HashSet<String> requests = new HashSet<>(Arrays.asList("+", "-", "*", "/"));
			 String oper = io1.readString("Enter arithmetic operator  " + requests, "", requests);
			 String operand1 = io1.readString("Enter the first operand");
			 String operand2 = io1.readString("Enter the second operand");
			 try {
			 String response = client.send(oper, String.format("%s#%s",  operand1, operand2));
			 io1.writeLine(response);
			} catch (Exception e) {
				throw new RuntimeException(e.toString());
			}
		 }), Item.ofExit());
		 menu.perform(io);
		client.close();
		
		
		
		
		
		
	}

}
