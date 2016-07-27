import CSPMparser.node.*;

@SuppressWarnings("serial")
public class UnboundIdentifierException extends RuntimeException  
{
	private String errorMessage = "";
	public UnboundIdentifierException(final String id, final Node node) 
	{
		super();
		setText("'Unbound Identifier "+id+"'"+getErrorLoc(node));
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
