import java.io.*;
import java.*;
import java.util.*;
import java.lang.*;
import java.lang.Character;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.charset.Charset;
import java.nio.file.Files;
import ltl_ctl.ltl.ltlparser.parser.*;
import ltl_ctl.ltl.ltlparser.lexer.*;
import ltl_ctl.ltl.ltlparser.node.*;


public class LTLparser
{
	private String ltlstr;

	public LTLparser(String s)
	{				
		ltlstr = s;
	}
	
	public void checkLTL() throws TreeLogicException
	{
		try
		{	
			char[] k = ltlstr.toCharArray();
			String temp = "";
			for(int i = 1;i<k.length-1;i++)
			{
				temp += k[i];
			}
			ltlstr = temp;		
			StringReader sr = new StringReader(ltlstr);
			BufferedReader br = new BufferedReader(sr); 
			Lexer l = new Lexer(new PushbackReader(br,100000));
			Parser p = new Parser(l);
			Start tree = p.parse();
		}
		catch(Exception e)
		{
			throw new TreeLogicException(ltlstr,"LTL");
		}
	}

}