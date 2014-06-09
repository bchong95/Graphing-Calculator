import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.*;

import javax.swing.AbstractButton;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.applet.Applet;
import java.util.ArrayList;
/**
	Represents the GUI of a Scientific Calculator by bring the DisplayArea and CalcButtons together in one JFrame
 */
public class AllView extends JFrame //implements ActionListener
{
	private static DisplayArea display;
	private CalcButtons buttons;
	private JPanel inputPanel;
	private static JTextField function,bounds,interval;
	public static JTextField getFunction() {
		return function;
	}
	public static JTextField getBound() {
		return bounds;
	}
	public static JTextField getInterval() {
		return interval;
	}
	private CalcButtons action;
	//private JPanel outputPanel;
	/**
	Constructs a AllView by constructing a DisplayArea and a CalcButtons and putting them both into a 2x1 JFrame
	*/
	public AllView (Evaluator eval, String str)
	{
		super(str+" Calculator");
		if (str.equals("Scientific")||str.equals("4-Function"))
		{
			display = new DisplayArea ();
			buttons = new CalcButtons(eval,str);
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setSize(480,800);
			setLayout(new GridLayout(2,1));
			add(display.getDisplay());
			add(buttons.getDisplay());
			this.setResizable(false);
		}
		else
		{
			inputPanel = new JPanel();
			display = new DisplayArea ();
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setSize(480,800);
			setLayout(new GridLayout(2,1));
			if (str.equals("Table"))
			{
				inputPanel.setLayout(new GridLayout(4,1)); // Function, bounds, interval, action
				function = new JTextField("Enter Your Function Here");
				bounds = new JTextField("Enter Your Bounds Here in the format: a,b");
				interval = new JTextField("Enter Your X-Interval Here ");
				action = new CalcButtons(eval,str);
			}
			else if (str.equals("Derivative"))
			{
				inputPanel.setLayout(new GridLayout(4,1)); // Function, bounds, interval, action
				function = new JTextField("Enter Your Function Here");
				bounds = new JTextField("Enter Your Variable");
				interval = new JTextField("(OPTIONAL) Enter Your Point Of Differentiation");
				action = new CalcButtons(eval,str);
			}
			else // if (str.equals("Integral"))
			{
				inputPanel.setLayout(new GridLayout(4,1)); // Function, variable, bounds
				function = new JTextField("Enter Your Function Here");
				bounds = new JTextField("(OPTIONAL) Enter Your Bounds Here in the format: a,b");
				interval = new JTextField("Enter Your Variable");
				action = new CalcButtons(eval,str);
			}
			inputPanel.add(function);
			inputPanel.add(bounds);
			inputPanel.add(interval);
			inputPanel.add(action.getDisplay());
			add(display.getDisplay());
			add(inputPanel);
			this.setResizable(false);
		}
	}
	/**
	Allows CalcButtons to access a DisplayArea
	*/
	public static DisplayArea getDisplay()
	{
		return display;
	}
}
