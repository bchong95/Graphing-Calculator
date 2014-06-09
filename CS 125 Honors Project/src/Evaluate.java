import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.Stack;

public class Evaluate {
	private Parse parse = new Parse();
	public String eval(String str) throws ArithmeticException {
		try {
			Stack<String> stk = new Stack<String>();
			ArrayList<String> string = parse.toPostFix(str);
			for (int x = 0; x < string.size(); x++) 
			{
				if (parse.isOperand(string.get(x))) {stk.push(string.get(x));}
				else if (parse.isOperator(string.get(x))) 
				{
					double temp = Double.parseDouble(stk.pop());
					double top = Double.parseDouble(stk.pop());
					String retVal = new String();
					if (string.get(x).equals("+")) {retVal = "" + (top + temp);}
					else if (string.get(x).equals("-")) {retVal = "" + (top - temp);}
					else if (string.get(x).equals("*")) {retVal = "" + (top * temp);}
					else if (string.get(x).equals("/")) 
					{
						if (temp == 0) {throw new ArithmeticException("ERROR: You can not divide by zero -_-");}
						retVal = "" + (top / temp);
					} 
					else if (string.get(x).equals("^")) {retVal = "" + Math.pow(top, temp);}
					stk.push(retVal);
				} 
				else if (string.get(x).equals("sqrt")) 
				{
					double top = Double.parseDouble(stk.pop());
					if (top < 0) {throw new ArithmeticException("ERROR: You can not take the square root of a negative number -_-");}
					else 
					{
						String retVal = "" + Math.pow(top, .5);
						stk.push(retVal);
					}
				} 
				else if (parse.isTrig(string.get(x)))// isTrig(str.get(x))
				{
					double temp = Double.parseDouble(stk.pop());
					double top = Math.toRadians(temp);
					String retVal = new String();

					if (string.get(x).equals("sin")) {retVal = "" + Math.sin(top);} 
					else if (string.get(x).equals("cos")) {retVal = "" + Math.cos(top);} 
					else if (string.get(x).equals("tan")) {retVal = "" + Math.tan(top);} 
					else if (string.get(x).equals("arcsin")) 
					{
						if (temp > 1 || temp < -1) {throw new ArithmeticException("ERROR: The Domain of arcsin is -1<=x<=1");} 
						else {retVal = "" + Math.asin(top);}
					} 
					else if (string.get(x).equals("arccos")) 
					{
						if (temp > 1 || temp < -1) {throw new ArithmeticException("ERROR: The Domain of arccos is -1<=x<=1");} 
						else {retVal = "" + Math.acos(top);}
					}
					else if (string.get(x).equals("arctan")) {retVal = "" + Math.atan(top);}
					stk.push(retVal);
				}
			}
			Double temp = Double.parseDouble(stk.pop());
			String result = "" + temp;
			if (result.equals("Infinity")) 
			{
				//Controller.troll();
				return "ITS OVER 9000!!!!";
			}
			return result;
		} 
		catch (EmptyStackException e) 
		{
			System.out.println("whoops");
			throw new ArithmeticException("Syntax Error");
		}
		// http://www.youtube.com/watch?v=uh7fD8WiT28
	}
	public double round(double d, int c) // d=number to round; c = number of decimal places.
	{
		int temp = (int) ((d * Math.pow(10, c)));
		return (((double) temp) / Math.pow(10, c));
	}
}
