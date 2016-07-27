@SuppressWarnings("serial")
public class TriangleSubstitutionException extends RuntimeException 
{
	public TriangleSubstitutionException(final String msg) 
	{
		super("'"+msg+"',0,0");
	}
}
