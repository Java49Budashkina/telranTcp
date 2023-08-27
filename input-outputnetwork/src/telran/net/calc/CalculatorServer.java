package telran.net.calc;

import java.net.*;
import java.io.*;
public class CalculatorServer {
static final int PORT=5012;
	public static void main(String[] args) throws Exception{
		try (ServerSocket serverSocket = new ServerSocket(PORT)) {
			System.out.println("Server is listening to port " + PORT);
			System.out.println("press quit");
			BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
			
			while(true) {
				Socket socket = serverSocket.accept();
				clientRun(socket);
				if(input.readLine().equalsIgnoreCase("quit"))
					break;
			}
			System.out.println("buy");
		}
		
	}
		
	private static void clientRun(Socket socket) {
		try(
			BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			PrintStream writer = new PrintStream(socket.getOutputStream())) {
			while(true) {
				System.out.println("cicle");
				String line = reader.readLine();
				if(line == null) {
						System.out.println("client closed normally connection");
							break;
					}
					String response = getResponse(line);
					writer.println(response);
				}
				
		} catch (Exception e) {
				System.out.println("client closed abnormally connection");
			}
			
		}
	
	private static String getResponse(String line) {
		// <request operator>#<operand1>#<operand2>
				
		String response = "Wrong request structure, usage: <request operator>#<operand1>#<operand2>";
		String [] tokens = line.split("#");
		if(tokens.length == 3) {
			response = switch(tokens[0]) {
			case "+" -> Double.toString( Double.valueOf( tokens[1]) + Double.valueOf(tokens[2])) ;
			case "-" -> Double.toString( Double.valueOf( tokens[1]) - Double.valueOf(tokens[2])) ;
			case "*" -> Double.toString( Double.valueOf( tokens[1]) * Double.valueOf(tokens[2])) ;
			case "/" -> Double.toString( Double.valueOf( tokens[1]) / Double.valueOf(tokens[2])) ;
			default -> "wrong request type";
			};
		}
		
		return response;
	}

}
