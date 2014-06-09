import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
Represents the Controller of my project.
Communicates between Evaluate and my Views.
	My Views are AllView, DisplayArea and CalcButtons
*/
public class Controller implements Evaluator
{
	private Evaluate myModel;
	private JFrame myView;

	public Controller()
	{
		myModel = new Evaluate();
		myView = new AllView(this,"Scientific");
		myView.setVisible(true);
	}
	public Controller(String str)
	{
		myModel = new Evaluate();
		myView = new AllView(this,str);
		myView.setVisible(true);
	}
	public String eval(String str)
	{
		try
		{
			return myModel.eval(str);
		}
		catch (ArithmeticException e)
		{
			String errorMessage = e.getMessage();
			error(errorMessage);
			return "ERROR!!!!";
		}
	}
	public static void troll()
	{
		Sound.play9000();
	}
	public void error(String str)
	{
		Sound.error();
		JOptionPane.showMessageDialog(myView, str);	
	}
	/**
	Asks the user what type of Evaluate they want
	*/
	private static String ask()
	{
		String [] choices = {"4-Function","Scientific","Table","Derivative","Integral"};
		String input = (String) JOptionPane.showInputDialog(null,
				"Choose one", "Which Calculator?",
				JOptionPane.INFORMATION_MESSAGE, null,
				choices, choices[0]);
		return input;
	}
	public static void main (String args[])
	{
		String str = ask();
		new Controller(str);
	}
}
