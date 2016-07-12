public class CommentInfo
{
	public int startLine;
	public int startColumn;
	public int offset;
	public int len;
	public boolean isMultilineComment;
	public String comment;
	
	public CommentInfo(int sl, int sc, int o, int l, boolean ml, String c)
	{
			startLine = sl;
			startColumn = sc;
			offset = o;
			len = l;
			comment = c;
			isMultilineComment = ml;
	}

}