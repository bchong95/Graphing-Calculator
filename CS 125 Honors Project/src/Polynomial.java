import java.util.ArrayList;


public class Polynomial 
{
	public double evaluate (String polynomial, String variable, double value)
	{
		System.out.println("making terms....");
		ArrayList<Term> terms = makeTerms(polynomial,variable);
		System.out.println("done");
		return evaluate (terms,value);
	}
	
	public double evaluate (ArrayList<Term> terms,double value)
	{
		double output = 0;
		for (Term t: terms)
		{
			output += t.getCoefficient()*Math.pow(value, t.getExponent());
		}
		return output;
	}

	public ArrayList<Term> makeTerms (String input,String variable)
	{
		Parse parse = new Parse();
		ArrayList<Term> terms =new ArrayList<Term>();
		ArrayList<String> polynomial = parse.fixArray(input,variable);
		//cleanse of impurities like multiplication signs
//		int i=0;
//		while(i<polynomial.size())
//		{
//			if(polynomial.get(i).equals("*"))
//			{
//				polynomial.remove(i);
//			}
//			else ++i;
//		}
		System.out.print("Infix Notation: ");
		for(String s:polynomial) System.out.print(s+",");
		System.out.print("\n");
		for (int j=0; j<polynomial.size();j++)
		{
			double coefficient,exponent;
			String str = polynomial.get(j);
			if (str.equals(variable))
			{
				try
				{
					coefficient = Double.parseDouble(polynomial.get(j-1));
				}
				catch (Exception e)
				{
					coefficient = 1.0;
				}
				try
				{
					String temp = polynomial.get(j+1);
					if (parse.isOperator(temp)) temp = polynomial.get(j+2);
					/*else if(parse.isOperand(temp)||parse.isConstant(temp)) */exponent =Double.parseDouble(temp);
				}
				catch (Exception e)
				{
					exponent = 1.0;
				}
				terms.add(new Term(coefficient,exponent));
			}
			if (parse.isOperand(str)||parse.isConstant(str))
			{
				coefficient = 0;
				try
				{
					String before = polynomial.get(j-1);
					
				}
				catch (Exception e)
				{
					String after = polynomial.get(j+1);
					if(after.equals("+")&&!after.equals(variable))
					{
						coefficient = Double.parseDouble(str);
					}
				}
				
				try
				{
					String after = polynomial.get(j+1);
					
				}
				catch (Exception e)
				{
					String before = polynomial.get(j-1);
					if(before.equals("+")&&!before.equals(variable))
					{
						coefficient = Double.parseDouble(str);
					}
				}
				try
				{
					String before = polynomial.get(j-1);
					String after = polynomial.get(j+1);
					if(before.equals("+")&&!before.equals(variable)&&after.equals("+")&&!after.equals(variable))
					{
						coefficient = Double.parseDouble(str);
					}
				}
				catch (Exception e)
				{
					
				}
				
				if(coefficient !=0) terms.add(new Term(coefficient,0));	
			}
		}
		System.out.print("polynomial form: ");
		for(Term t :terms)
		{
			System.out.print(t.getCoefficient()+variable+"^"+t.getExponent()+" + ");
		}
		System.out.print("\n");
		return terms;
	}

	public ArrayList<Term> sort(ArrayList<Term> input)
	{
		ArrayList<Term> terms = new ArrayList<Term>();
		double [] exponents = sortAndRemoveRedundencies(input);
		for (Double expo : exponents)
		{
			for (Term t: input)
			{
				if (t.getExponent()==expo)
				{
					terms.add(t);
				}
			}
		}
		return terms;
	}
	public double[] sortAndRemoveRedundencies(ArrayList<Term> input)
	{
		double [] exponents = makeExponentArray(input);
		SelectionSort selection = new SelectionSort();
		selection.sort(exponents);
		return removeDuplicates(exponents);
	}
	public double[] removeDuplicates(double[] arr){
	    //dest array index
	    int destination = 0;
	    //source array index
	    int source = 0;
	    double currentValue = arr[0];
	    double[] whitelist = new double[arr.length];
	    whitelist[destination] = currentValue;
	    while(source < arr.length){

	        if(currentValue == arr[source]){
	            source++;
	        } else {
	            currentValue = arr[source];
	            destination++;
	            source++;
	            whitelist[destination] = currentValue;
	        }
	    }
	    double[] returnList = new double[++destination];
	    for(int i = 0; i < destination; i++){
	        returnList[i] = whitelist[i];
	    }
	    return returnList;
	}
	public double[] makeExponentArray(ArrayList<Term> input)
	{
		double [] exponents = new double[input.size()];
		for (int i=0; i<input.size();i++)
		{
			exponents[i] = input.get(i).getExponent();
		}
		return exponents;
	}
}
