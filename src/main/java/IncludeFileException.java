import CSPMparser.node.*;

@SuppressWarnings("serial")
public class IncludeFileException extends RuntimeException 
{
	private String errorMessage = "";
	private int line;
	private int column;
	
	public IncludeFileException(final String path, int line, int column) 
	{
		super();
		this.line = line;
		this.column = column;
		setText("'Your File "+path+" was not found.'");
	}
	
	public void setText(String text)
	{
		errorMessage = text;
	}
	
	public String getText()
	{
		return errorMessage;
	}
	
	public int getLine()
	{
		return line;
	}
	
	public int getColumn()
	{
		return column;
	}
}
