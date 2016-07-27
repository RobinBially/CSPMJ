import CSPMparser.node.*;

@SuppressWarnings("serial")
public class NoPatternException extends RuntimeException 
{
	private String errorMessage = "";
	public NoPatternException(final String msg, final Node node) 
	{
		super();
		setText("'"+msg+"'"+getErrorLoc(node));
	}
	
	public String getErrorLoc(final Node node)
	{
		String s = ","+node.getStartPos().getLine()+","+node.getStartPos().getPos();
		return s;
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
