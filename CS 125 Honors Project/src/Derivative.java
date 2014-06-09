import java.util.ArrayList;
import java.util.Map;
import java.util.Stack;
public class Derivative 
{
	public String differentiate (String input, String variable)
	{
		Polynomial poly = new Polynomial();
		//SelectionSort selection = new SelectionSort();
		ArrayList<Term> coExpo = poly.sort(poly.makeTerms(input,variable));
		ArrayList<Term> fPrime = new ArrayList<Term>();
		double [] exponents = poly.sortAndRemoveRedundencies(coExpo);
		String derivative ="";
		for (Double ex : exponents)
		{
			double e = ex.doubleValue();
			double coefficient=0;
			for (Term coEx :coExpo)
			{
				if(coEx.getExponent()==e)
				{
					coefficient+=(coEx.getCoefficient()*e);
				}
			}
			double exponent = e-1;
			if (exponent != -1) fPrime.add(new Term(coefficient,exponent));
		}
		for(Term coEx :fPrime)
		{
			derivative+=""+coEx.getCoefficient()+variable+"^"+coEx.getExponent()+" + ";
		}
		return derivative.substring(0, derivative.length()-3);
	}
}

		

