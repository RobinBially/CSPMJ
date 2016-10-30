import java.io.*;
import java.*;
import java.util.*;
import java.util.regex.*;
import java.lang.*;
import java.lang.String.*;
import java.lang.Character;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.charset.Charset;
import java.nio.file.Files;

public class CommentInfo
{
	public boolean isLTL;
	public String formula;
	public String pragmaComment;
	public int startLine;
	public int startColumn;
	public int offset;
	public int len;
	public boolean isMultilineComment;
	public String comment;

	public CommentInfo(int sl, int sc, int o, int l, boolean ml, String c)
	{
			isLTL = false;
			startLine = sl;
			startColumn = sc;
			offset = o;
			len = l;
			comment = escapeChars(c);
			isMultilineComment = ml;
			formula = "";
			pragmaComment = "";
			analyse(c);
	}

	public boolean analyse(String s)
	{
			Pattern pattern = Pattern.compile("\\{[-]#\\s*assert_ltl\\s*\"([\\s\\S]*)\"\\s*#[-]\\}");
			Matcher matcher = pattern.matcher(s);
			while(matcher.find())
			{
				formula = matcher.group(1);
				isLTL = true;
			}

			pattern = Pattern.compile("\\{[-]#\\s*assert_ltl\\s*\"([\\s\\S]*)\"\\s*\"([\\s\\S]*)\"\\s*#[-]\\}");
			matcher = pattern.matcher(s);
			while(matcher.find())
			{
				formula = matcher.group(1);
				pragmaComment = matcher.group(2);
				isLTL = true;
			}

			pattern = Pattern.compile("\\{[-]#\\s*assert_ctl\\s*\"([\\s\\S]*)\"\\s*#[-]\\}");
			matcher = pattern.matcher(s);
			while(matcher.find())
			{
				formula = matcher.group(1);
			}

			pattern = Pattern.compile("\\{[-]#\\s*assert_ctl\\s*\"([\\s\\S]*)\"\\s*\"([\\s\\S]*)\"\\s*#[-]\\}");
			matcher = pattern.matcher(s);
			while(matcher.find())
			{
				formula = matcher.group(1);
				pragmaComment = matcher.group(2);
			}

			pragmaComment = escapeChars(pragmaComment);

			formula = escapeChars(formula);
			return false;
	}

	public static String escapeChars(String input) {
     String escapeNewLines = input.replaceAll("\\\\(n|t|r)","\\$1");
		 String escapeDoubleBackSlash = escapeNewLines.replace("\\","\\\\");
		 String escapeApostrophes = escapeDoubleBackSlash.replace("'","\\'");
		 return escapeApostrophes;
	}

}
