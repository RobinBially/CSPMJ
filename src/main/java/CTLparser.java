import java.io.*;
import java.*;
import java.util.*;
import java.lang.*;
import java.lang.Character;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.charset.Charset;
import java.nio.file.Files;
import ltl_ctl.ctl.ctlparser.parser.*;
import ltl_ctl.ctl.ctlparser.lexer.*;
import ltl_ctl.ctl.ctlparser.node.*;


public class CTLparser
{
	private String ctlstr;
	
	public CTLparser(String r)
	{
		ctlstr = r;
	}
	
	
	public void checkCTL()
	{
		try
		{	
			char[] m = ctlstr.toCharArray();
			String temp = "";
			for(int i = 1;i<m.length-1;i++)
			{
				temp += m[i];
			}
			ctlstr = temp;	
			StringReader sr = new StringReader(ctlstr);
			BufferedReader br = new BufferedReader(sr); 
			Lexer l = new Lexer(new PushbackReader(br,100000));
			Parser p = new Parser(l);
			Start tree = p.parse();
		}
		catch(Exception e)
		{
			throw new RuntimeException(e.getMessage()
								+"\nYour CTL Formula is wrong:\n"+ctlstr);
		}
	}
	
}