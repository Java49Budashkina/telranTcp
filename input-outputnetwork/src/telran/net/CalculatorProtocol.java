package telran.net;
import java.io.*;
public class CalculatorProtocol implements ApplProtocol {

	@Override
	public Response getResponse(Request request) {
		ResponseCode code = ResponseCode.WRONG_DATA;
		String res = "Wrong request structure, usage: <request operator>#<operand1>#<operand2>";
		String [] tokens = request.requestData().toString().split("#");
		if(tokens.length == 2) {
			code = ResponseCode.OK;
			res = switch(request.requestType()) {
			case "+" -> Double.toString( Double.valueOf( tokens[0]) + Double.valueOf(tokens[1])) ;
			case "-" -> Double.toString( Double.valueOf( tokens[0]) - Double.valueOf(tokens[1])) ;
			case "*" -> Double.toString( Double.valueOf( tokens[0]) * Double.valueOf(tokens[1])) ;
			case "/" -> Double.toString( Double.valueOf( tokens[0]) / Double.valueOf(tokens[1])) ;
			default -> { code = ResponseCode.WRONG_TYPE;
					yield "wrong request type"; }
			};
		}
				
		return  new Response(code, res );
	}

}
