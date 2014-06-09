import java.awt.Color;
import java.awt.Font;
import java.util.LinkedList;
import java.util.ListIterator;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;
import javax.swing.text.Highlighter;
/**
Represents the GUI of a DisplayArea which manages the viewing area for the user 
and shows the input and output of the user
*/

public class DisplayArea 
{
	private LinkedList<String> lines;
	private JTextArea textArea ;
	private JScrollPane scrollPane;
	private String str;
	private int lineNumber;
	
	final Highlighter hilit;
	final Highlighter.HighlightPainter painter;
	final static Color  HILIT_COLOR = Color.LIGHT_GRAY;
	
	/**
	Constructs a DisplayArea.
	Initializes lines,textArea,scrollPane,str,lineNumber,hilit and painter.
	Sets the highlighter for textArea.
	Puts textArea within scrollPane.
	 */
	public DisplayArea()
	{
		
		lines = new LinkedList<String>();
		textArea = new JTextArea();
		scrollPane = new JScrollPane(textArea);
		str = "";
		lineNumber=0;
		
		hilit = new DefaultHighlighter();
		painter = new DefaultHighlighter.DefaultHighlightPainter(HILIT_COLOR);
		textArea.setHighlighter(hilit);
	}
	
	public String getCurrentLine()
	{
		return lines.get(lineNumber);
	}
	public int getLineNumber()
	{
		return lineNumber;
	}
	public String getString()
	{
		return str;
	}
	public void addInput(String temp)
	{
		lines.addLast(str);
		lineNumber++;
		str ="";
	}
	public void addOutput(String temp)
	{
		lines.addLast(temp);
		lineNumber++;
		textArea.setText(textArea.getText()+"\n \t\t\t\t"+temp+"\n");
	}
	/**
	Builds str and updates textArea to show the user that what they have clicked
	 */
	public void build (String temp)
	{
		str+=temp;
		textArea.setText(textArea.getText()+temp);
	}
	public void clear()
	{
		str="";
		lines = new LinkedList<String>();
		textArea.setText("");
		lineNumber=0;
	}
	public void backspace()
	{
		str = str.substring(0,str.length()-1);
		String temp = textArea.getText();
		temp = temp.substring(0, temp.length()-1);
		textArea.setText(temp);
	}
	public JScrollPane getDisplay()
	{
		return scrollPane;
	}
	/**
	Highlights the next entry up
	Updates lineNumber
	 */
	public void up()
	{	
		try
		{
			lineNumber--;
			String temp = lines.get(lineNumber);
			String text = textArea.getText();
			int index = text.indexOf(temp);
			int end = index + temp.length();
			hilit.removeAllHighlights();
			hilit.addHighlight(index,end, painter);
			textArea.setCaretPosition(end);
		}
		catch(IndexOutOfBoundsException e)
		{
			lineNumber = lines.size();
			up();
		}
		catch(BadLocationException e)
		{
			e.printStackTrace();
		}
	}
	/**
	Highlights the next entry down.
	Updates lineNumber.
	 */
	public void down()
	{
		try
		{
			lineNumber++;
			String temp = lines.get(lineNumber);
			String text = textArea.getText();
			int index = text.indexOf(temp);
			int end = index + temp.length();
			hilit.removeAllHighlights();
			hilit.addHighlight(index,end, painter);
			textArea.setCaretPosition(end);
		}
		catch(IndexOutOfBoundsException e)
		{
			lineNumber =-1;
			down();
		}
		catch(BadLocationException e)
		{
			e.printStackTrace();
		}
	}
	/**
	Gets the highlighted entry and adds it to str
	 */
	public void insert()
	{
		this.build(getCurrentLine());
	}
}
