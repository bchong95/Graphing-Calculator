import java.util.ArrayList;


public class Integral 
{
	public String integrate (String polynomial,String variable)
	{
		ArrayList<Term> fInt = makeIntegralTerms(polynomial,variable);
		String integral ="";
		for(Term coEx :fInt)
		{
			integral+=""+coEx.getCoefficient()+variable+"^"+coEx.getExponent()+" + ";
		}
		return integral.substring(0, integral.length()-2)+"+ C";
	}
	private ArrayList<Term> makeIntegralTerms(String polynomial,String variable)
	{
		Polynomial poly = new Polynomial();
		//SelectionSort selection = new SelectionSort();
		ArrayList<Term> coExpo = poly.sort(poly.makeTerms(polynomial,variable));
		ArrayList<Term> fInt = new ArrayList<Term>();
		double [] exponents = poly.sortAndRemoveRedundencies(coExpo);
		for (Double ex : exponents)
		{
			double e = ex.doubleValue();
			double coefficient=0;
			for (Term coEx :coExpo)
			{
				if(coEx.getExponent()==e)
				{
					coefficient+=(coEx.getCoefficient());
				}
			}
			double exponent = e+1;
			fInt.add(new Term(coefficient/(e+1),exponent));
		}
		return fInt;	
	}
	public double integrate (String polynomial,String variable,double a, double b)
	{
		ArrayList<Term> fInt = makeIntegralTerms (polynomial,variable);
		Polynomial poly = new Polynomial();
		double fOfB = poly.evaluate(fInt, b);
		double fOfA = poly.evaluate(fInt, a);
		return fOfB-fOfA;
		
	}
}
