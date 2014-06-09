import java.util.ArrayList;


public class Graphing 
{
	private Point[] points;
	
	public Point[] plot (int xMin,int xMax, int delX, String polynomial)
	{
		Polynomial poly = new Polynomial();
		ArrayList<Term> terms = poly.sort(poly.makeTerms(polynomial,"x"));
		points =new Point[(int)((xMax-xMin)/delX)];
		for (int i=0;i<points.length;i++)
		{
			double x = xMin+(i*delX);
			double y = 0;
			for(Term t : terms)
			{
				y+= (t.getCoefficient()*Math.pow(x, t.getExponent()));
			}
			points[i] = new Point(x,y);
		}
		return points;
	}
}
