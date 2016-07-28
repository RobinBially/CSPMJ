import CSPMparser.node.*;

@SuppressWarnings("serial")
public class TreeLogicException extends RuntimeException 
{
	private String errorMessage = "";
	private int line = 0;
	private int column = 0;
	
	public TreeLogicException(final String formula,final String type) 
	{
		super();
		setText("'Your "+type+"-Formula \""+formula+"\" is incorrect'");
	}
	
	public void addText(String t)
	{
		errorMessage += t;
	}
	
	public void setText(String text)
	{
		errorMessage = text;
	}
	
	public String getText()
	{
		return errorMessage;
	}
	
}
