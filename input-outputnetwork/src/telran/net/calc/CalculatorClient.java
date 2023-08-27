package telran.net.calc;


	import java.net.*;
	import java.util.*;
	import java.io.*;
	import telran.view.*;
	public class CalculatorClient  {
	static final String HOST="localhost";
	static final int PORT=5012;
		public static void main(String[] args) throws Exception{
			try(Socket socket = new Socket(HOST, PORT);
					PrintStream writer = new PrintStream(socket.getOutputStream());
					BufferedReader reader =
							new BufferedReader(new InputStreamReader(socket.getInputStream()))){
				 InputOutput io = new ConsoleInputOutput();
				 Menu menu = new Menu("TCPcalculator", Item.of("send request", io1 -> {
					 HashSet<String> requests = new HashSet<>(Arrays.asList("+", "-", "*", "/"));
					 String oper = io1.readString("Enter arithmetic operator  " + requests, HOST, requests);
					 String operand1 = io1.readString("Enter the first operand");
					 String operand2 = io1.readString("Enter the second operand");
					 writer.println(String.format("%s#%s#%s", oper, operand1, operand2));
					 try {
						String response = reader.readLine();
						io1.writeLine(response);
					} catch (IOException e) {
						throw new RuntimeException(e.toString());
					}
				 }), Item.ofExit());
				 menu.perform(io);
			}

		}

	}
