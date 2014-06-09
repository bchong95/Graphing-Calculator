import java.util.ArrayList;
import java.util.Stack;
public class Parse {
	private String[] operators = { "+", "-", "/", "*", "^", "(", ")" };
	private String[] trig = { "sin", "tan", "cos", "arcsin", "arctan", "arccos" };
	private String[] constants = { "e","pi","h","phi","c"};
	private String[] constantValues = { "2.71","3.14",".000000000000000000000000000000000663","1.61803398874","29979245800"};
	
	public ArrayList<String> toPostFix(String input) {
		Stack<String> stk = new Stack<String>();
		System.out.println("Fixing the string...");ArrayList<String> str = fixArray(input); System.out.println("Fixed the string");
		System.out.print("Infix Notation: ");
		for(String s:str) System.out.print(s+",");
		System.out.print("\n");
		ArrayList<String> postfix = new ArrayList<String>();
		for (int x=0;x<str.size();x++) 
		{ 
		  String T = str.get(x); // T is the incoming operator
		  if (T .equals("(")) 
		  { 
			  stk.push(T);
		  } 
		  else if (isOperand(T)) 
		  { 
		    postfix.add(T);
		  } 
		  else if (isOperator(T)||isTrig(T)) 
		  { 
		    if ((stk.isEmpty()) || 
		        (stk.peek().equals("(") || 
		        (compare(stk.peek(),T)==-1)))
		    { 
		      stk.push(T);
		    } 
		    else // clear operator stack and push new token onto it 
		    { 
		      do 
		      { 
		        //add the operator at the top of the stack to the RPN string;
		    	 postfix.add(stk.pop());
		        //pop the stack; 
		      } 
		      while ((!stk.isEmpty()) &&
		             (!stk.peek().equals("(")) &&
		             (compare(T,stk.peek())==-1));
		      stk.push(T);
		    }
		  }   
		  else if (T.equals(")"))  // clear operator stack back to the '(' 
		  { 
		    while (!stk.peek().equals("(")) 
		    { 
		      //add the operator at the top of the stack to the RPN string;
		      //pop the stack;
		    	postfix.add(stk.pop());
		    } 
		  } 
		  else 
		  { 
		    System.out.println("REPORT ERROR!!!!!");
		  } 
		}

		// no more tokens left in expression - clear stack into output
		while (!stk.isEmpty()) 
		{ 
		   //add the operator at the top of the stack to the RPN string;
		   //pop the stack; 
			postfix.add(stk.pop());
		}
		//Remove all the parenthesis
		int i=0;
		while(i<postfix.size())
		{
			if(postfix.get(i).equals("(")||postfix.get(i).equals(")"))
			{
				postfix.remove(i);
			}
			else ++i;
		}
		System.out.println("Converting string to postfix notation");
		System.out.print("Postfix Notation: ");
		for(String s:postfix) System.out.print(s+",");
		System.out.print("\n");
		return postfix;
		// http://www.youtube.com/watch?v=rA0x7b4YiMI
	}
	/*
	 * Builds an Array that takes a String and breaks it up into SubStrings in
	 * the way people would read a mathematical expression
	 */
	
	public ArrayList<String> fixArray(String input,String variable) 
	{
		String[] temp = new String[operators.length+1];
		for(int i=0; i<operators.length;i++)
		{
			temp[i] = operators[i];
		}
		temp[operators.length]=variable;
		operators = temp;
		return fixArray(input);
	}
	public ArrayList<String> fixArray(String input) {
		ArrayList<String> output = new ArrayList<String>();
		System.out.println("making progress: "+input);
		while (input.length()!=0)
		{
			if (firstIsOperator(input))
			{
				output.add(input.substring(0,1));
				input = input.substring(1);
				System.out.println("making progress: "+input);
			}
			else if (firstIsConstant(input))
			{
				int index = getIndex(input,constants);
				String constant = constants[index];
				String constantValue = constantValues[index];
				//System.out.println("the constant is : "+constantValue);
				output.add(constantValue);
				input = input.substring(constant.length());
				System.out.println("making progress: "+input);
			}
			else if (firstIsTrig(input))
			{
				String trigFunction = trig[getIndex(input,trig)];
				output.add(input.substring(0,trigFunction.length()));
				input = input.substring(trigFunction.length());
				System.out.println("making progress: "+input);
			}
			else // if (firstIsOperand(input)
			{
				StringBuilder sb = new StringBuilder();
				while(input.length()!=0&&firstIsOperand(input))
				{
					sb.append(input.substring(0,1));
					input = input.substring(1);
					System.out.println("making progress: "+input);
				}
				
				output.add(sb.toString());
			}
			//System.out.println("making progress");
		}
		return output;
	}
	

	
	/* Returns the priority of operator 1 to operator 2
	 * Greater than =1; Equal to = 0; Less than =-1;
	*/
	private int compare(String op1, String op2) 
	{
		int priortyOp1 = getPriority(op1);
		int priortyOp2 = getPriority(op2);
		int diff =priortyOp1-priortyOp2;
		if (diff>0) return 1;
		if (diff<0) return -1;
		return 0;
	}
	 //Assigns a relatively arbitarary
	private int getPriority(String operator)
	{
		String multiplicativeOps = "*/";
		String additiveOps = "+-";
		String expoOps = "^()";
		if(expoOps.indexOf(operator)!=-1) return 3;
		if(multiplicativeOps.indexOf(operator)!=-1) return 2;
		if(additiveOps.indexOf(operator)!=-1) return 1;
		return 0;
	}

	private boolean firstIsOperator(String input)
	{
		boolean bool = false;
		for (String s:operators)
		{
			if (input.indexOf(s)==0) bool =true;
		}
		return bool;
	}
	public boolean isOperator (String input) {return firstIsOperator (input);}
	private boolean firstIsOperand(String input)
	{
		if(input.substring(0,1).equals(".")) return true;
		try {
			double d = Double.parseDouble(input.substring(0,1));
		} catch (NumberFormatException nfe) {
			return false;
		}
		return true;
	}
	public boolean isOperand (String input) {return firstIsOperand (input);}
	private boolean firstIsTrig(String input)
	{
		boolean bool = false;
		for (String s:trig)
		{
			if (input.indexOf(s)==0) bool =true;
		}
		return bool;
	}
	public boolean isTrig (String input) {return firstIsTrig (input);}
	private boolean firstIsConstant(String input)
	{
		boolean bool = false;
		for (String s:constants)
		{
			if (input.indexOf(s)==0) bool =true;
		}
		return bool;
	}
	public boolean isConstant (String input) {return firstIsConstant (input);}
	private int getIndex(String str, String[] arr)
	{
		for (int x=0;x<arr.length;x++)
		{
			if (str.indexOf(arr[x])==0) return x;
		}
		return -1;
	}
}
