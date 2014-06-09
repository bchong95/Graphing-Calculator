public class Term
{
	private double coefficient;
	private double exponent;
	
	public Term(double coefficient, double exponent)
	{
		this.coefficient = coefficient;
		this.exponent = exponent;
	}
	public double getCoefficient() {
		return coefficient;
	}
	public void setCoefficient(double coefficient) {
		this.coefficient = coefficient;
	}
	public double getExponent() {
		return exponent;
	}
	public void setExponent(double exponent) {
		this.exponent = exponent;
	}
	
	
}