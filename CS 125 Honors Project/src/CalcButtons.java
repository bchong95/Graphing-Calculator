import java.awt.Component;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
Represents the GUI of CalcButtons which manages the buttons of a calculator.
CalcButtons modify the view and tell the controller what they want the model to do.
*/
public class CalcButtons extends JPanel implements ActionListener
{
	private JButton add, subtract, multiply, divide, expo,rad, lp, rp, plusminus; 
	private JButton e,pi;
	private JButton sin, tan, cos, arcsin, arccos, arctan ;
	private JButton b1, b2, b3, b4, b5, b6, b7, b8, b9, b0, bpoint, bequal,clear,up,down,insert,backspace;
	private JButton action;
	private ArrayList<JButton> buttons;
	private JPanel panel;
	private DisplayArea display;
	private Evaluator myEvaluator;
	/**
	Constructs a CalcButtons which takes in an Evaluator and a String.
	The String is used to determine whether CalcButtons should represent a 4-Function or Scientific Calculator.
	The Evaluator is used so that CalcButtons can indirectly access Calculators evaluate method.
	*/
	public CalcButtons(Evaluator eval, String str) 
	{
		display = AllView.getDisplay();
		buttons = new ArrayList<JButton>();
		myEvaluator = eval;
		panel = new JPanel();
		/**
		Creates a 7x5 panel full of GridButtons that represents a Scientific Calculator
		*/
		if(str.equals("Scientific"))
		{
			panel.setLayout(new GridLayout(7,5));
			//row 1
			buttons.add(arcsin = new JButton("arcsin"));
			buttons.add(arccos = new JButton("arccos"));
			buttons.add(arctan = new JButton("arctan"));
			buttons.add(backspace = new JButton("backspace"));
			buttons.add(clear = new JButton("clear"));
			//row 2
			buttons.add(sin = new JButton("sin"));
			buttons.add(cos = new JButton("cos"));
			buttons.add(tan = new JButton("tan"));
			buttons.add(new JButton(""));
			buttons.add(up = new JButton("up"));
			//row 3
			buttons.add(e = new JButton("e"));
			buttons.add(pi = new JButton("pi"));
			buttons.add(expo = new JButton("^"));
			buttons.add(rad = new JButton("sqrt"));
			buttons.add(down = new JButton("down"));
			//row 4
			buttons.add(b7 = new JButton("7"));
			buttons.add(b8 = new JButton("8"));
			buttons.add(b9 = new JButton("9"));	
			buttons.add(lp = new JButton("("));
			buttons.add(rp = new JButton(")"));
			//row 5
			buttons.add(b4 = new JButton("4"));
			buttons.add(b5 = new JButton("5"));
			buttons.add(b6 = new JButton("6"));
			buttons.add(multiply = new JButton("*"));
			buttons.add(divide = new JButton("/"));
			//row 6
			buttons.add(b1 = new JButton("1"));
			buttons.add(b2 = new JButton("2"));
			buttons.add(b3 = new JButton("3"));
			buttons.add(add = new JButton("+"));
			buttons.add(subtract = new JButton("-"));
			//row 7
			buttons.add(b0 = new JButton("0"));
			buttons.add(bpoint = new JButton("."));
			buttons.add( new JButton(" "));
			buttons.add(bequal = new JButton("="));
			buttons.add(insert = new JButton("insert"));
		}
		/**
		Creates a 4x4 panel full of GridButtons that represents a 4-Function Calculator
		*/
		else if (str.equals("4-Function"))
		{
			panel.setLayout(new GridLayout(4,4));
			//row 1
			buttons.add(b7 = new JButton("7"));
			buttons.add(b8 = new JButton("8"));
			buttons.add(b9 = new JButton("9"));	
			buttons.add(divide = new JButton("/"));
			//row 2
			buttons.add(b4 = new JButton("4"));
			buttons.add(b5 = new JButton("5"));
			buttons.add(b6 = new JButton("6"));
			buttons.add(multiply = new JButton("*"));
			//row 3
			buttons.add(b1 = new JButton("1"));
			buttons.add(b2 = new JButton("2"));
			buttons.add(b3 = new JButton("3"));
			buttons.add(subtract = new JButton("-"));
			//row 4
			buttons.add(b0 = new JButton("0"));
			buttons.add(bpoint = new JButton("."));
			buttons.add(bequal = new JButton("="));
			buttons.add(add = new JButton("+"));
		}
		/**
		Creates a 1x1 panel full of GridButtons that represents a Table Making Calculator
		*/
		else if (str.equals("Table"))
		{
			panel.setLayout(new GridLayout(1,1));
			//row 1
			buttons.add(action = new JButton("Make Table"));
		}
		/**
		Creates a 1x1 panel full of GridButtons that represents a Derivative Calculator
		*/
		else if (str.equals("Derivative"))
		{
			panel.setLayout(new GridLayout(1,1));
			//row 1
			buttons.add(action = new JButton("Take Derivative"));
		}
		/**
		Creates a 1x1 panel full of GridButtons that represents a Integral Calculator
		*/
		else //if (str.equals("Integral"))
		{
			panel.setLayout(new GridLayout(1,1));
			//row 1
			buttons.add(action = new JButton("Take Integral"));
		}
		
		for (int x=0;x<buttons.size();x++)
		{
			buttons.get(x).addActionListener(this);
			panel.add(buttons.get(x));
		}
	}
	/**
	Allows AllView to access CalcButtons display
	*/
	public JPanel getDisplay()
	{
		return panel;
	}
	/**
	Determines what CalcButton is being clicked by the user 
	then determines whether to update DisplayArea's JTextArea 
	or to call on Evaluator's evaluate method.
	Plays a sound.
	*/
	public void actionPerformed(ActionEvent event) 
	{
		Object source = event.getSource();
		if(source instanceof JButton)
		{
			Sound.playFX();
		}
		if(source  == bequal)
		{
			String temp = display.getString();
			display.addInput(temp);
			String result = myEvaluator.eval(temp);
			display.addOutput(result);
		}
		else if(source == insert)
		{
			display.insert();
		}
		else if(source == backspace)
		{
			display.backspace();
		}
		else if(source == clear)
		{
			display.clear();
		}
		else if (source == up)
		{
			display.up();
		}
		else if (source == down)
		{
			display.down();
		}
		else if (source == action)
		{
			if (((JButton) source).getText().equals("Make Table"))
			{
				
				String inputFunction = AllView.getFunction().getText();
				String [] inputBounds =AllView.getBound().getText().split(",");
				String interval = AllView.getInterval().getText();
				Graphing graph = new Graphing();
				Point[] points = graph.plot(Integer.parseInt(inputBounds[0]), Integer.parseInt(inputBounds[1]), Integer.parseInt(interval), inputFunction);
				String output ="";
				output+="Generating Table For "+inputFunction+"...";
				output+="\nx\ty\n";
				for (Point p: points)
				{
					output+=p.getX()+"\t"+p.getY()+"\n";
				}
				display.build(output);
				
			}
			else if (((JButton) source).getText().equals("Take Derivative"))
			{
				String inputFunction = AllView.getFunction().getText();
				String variable = AllView.getBound().getText();
				String potentialPoint = AllView.getInterval().getText();
				String point = potentialPoint.equals("(OPTIONAL) Enter Your Point Of Differentiation")?"no point":potentialPoint;
				System.out.println("point is: "+point);
				Derivative deri = new Derivative();
				String fPrime = deri.differentiate(inputFunction, variable);
				String output="The Derivative of "+inputFunction+" = "+fPrime+"\n";
				display.build(output);	
				//double value = Double.MAX_VALUE;
				if (!point.equals("no point"))
				{
					double pointValue = Double.parseDouble(point);
					Polynomial poly = new Polynomial();
					double value = poly.evaluate(fPrime, variable,pointValue);
					System.out.println("value is: "+value);
					output="\n And f'("+point+") is = "+value+"\n\n";
					display.build(output);	
				}
				//display.build(output);	
			}
			else //if (((JButton) source).getText().equals("Take Integral"))
			{
				String inputFunction = AllView.getFunction().getText();
				String bound = AllView.getBound().getText();
				String variable = AllView.getInterval().getText();
				String [] inputBounds =null;
				if(!bound.equals("(OPTIONAL) Enter Your Bounds Here in the format: a,b)"))
				{
					inputBounds =AllView.getBound().getText().split(",");
				}
				Integral inte = new Integral();
				String fInt = inte.integrate(inputFunction, variable);
				String output="The Integral of "+inputFunction+" = "+fInt+"\n";
				display.build(output);	
				if (inputBounds!=null)
				{
					Polynomial poly = new Polynomial();
					double value = inte.integrate(inputFunction,variable,Double.parseDouble(inputBounds[0]),Double.parseDouble(inputBounds[1]));
					//System.out.println("value is: "+value);
					output="\n And the definite integral from "+inputBounds[0]+" to "+inputBounds[1]+" is "+ value+"\n\n";
					display.build(output);	
				}
			}
		}
		else //if(source instanceof JButton)
		{
			String temp = ((JButton) source).getText();
			display.build(temp);	
		}
	}
}
