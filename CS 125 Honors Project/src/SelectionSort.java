
public class SelectionSort 
{
	public void sort(double[] data) {
		sort (data,0,data.length-1);
		//System.out.println("data: "+toString(data));
	}
	public static void sort(double[] data, int lo, int hi) {
		if (lo>=hi) return;
		//System.out.println("data: "+toString(data));
		swap (data,lo,findMax(data,lo,hi));
		sort(data,lo+1,hi);
	}
	public static void swap(double[] data, int i, int j) {
		/*data[i] = data[i]+data[j];
		data[j] = data[i]-data[j];
		data[i] = data[i]-data[j];*/
		double temp = data[i];
		data[i] = data[j];
		data[j] = temp;
	}
	public static int findMax(double[] data, int lo, int hi) {
		double max = Integer.MIN_VALUE;
		int maxIndex = lo;
		for (int i=lo;i<=hi;i++)
		{
			if (data[i]>=max)
			{
				max = data[i];
				//System.out.println("min: "+min);
				maxIndex =i;
			}
		}
		return maxIndex;
	}
}
