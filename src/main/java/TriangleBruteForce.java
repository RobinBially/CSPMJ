import java.io.*;
import java.*;
import java.util.*;
import java.lang.*;
import java.util.regex.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.charset.StandardCharsets;
import java.nio.charset.Charset;
import java.nio.file.Files;
import CSPMparser.parser.*;
import CSPMparser.lexer.*;
import CSPMparser.node.*;

public class TriangleBruteForce
{
private String stream;	
private String white;
private String white2;
	
	public TriangleBruteForce(String s)
	{
		stream = s;
		white = defineWhitespace();
	}
	
	public String findTriangles()
	{	
		
		boolean replaceable = true;
		String old = stream;
		char[] edges = stream.toCharArray();

		if(edges[0] == '<')
		{
			edges[0] = '\u00AB';
		}
		if(edges[edges.length-1] == '>')
		{
			edges[edges.length-1] = '\u00BB';
		}
		stream = new String(edges);
				
		//Save triangles that must not be replaced!

		stream = stream.replaceAll("<=>","\u00A2\u00A4\u00A2");
		stream = stream.replaceAll("<=","\u00A2\u00A4"); //Koennten ersetzt werden 
		stream = stream.replaceAll("=>","\u00A4\u00A2"); //Siehe smaller greater erste Regel
		stream = stream.replaceAll("<->","\u00A6\u00A5\u00A6");
		stream = stream.replaceAll("->","\u00A5\u00A6");
		stream = stream.replaceAll("<-","\u00A6\u00A5");
		stream = stream.replaceAll("\\[>","\u00A7\u00A8");
		stream = stream.replaceAll("<\\|","\u00B1\u00B2");
		stream = stream.replaceAll("\\|>","\u00B2\u00B1");

		while(replaceable)
		{	
			//Operators
					
			stream = stream.replaceAll("\u00A4\u00A2"+white+"<","\u00A4\u00A2$1\u00AB");		
			stream = stream.replaceAll(">"+white+"\u00A4\u00A2","\u00BB$1\u00A4\u00A2");
			stream = stream.replaceAll("\u00A2\u00A4"+white+"<","\u00A2\u00A4$1\u00AB");		
			stream = stream.replaceAll(">"+white+"\u00A2\u00A4","\u00BB$1\u00A2\u00A4");			
			stream = stream.replaceAll("\u00A5\u00A6"+white+"<","\u00A5\u00A6$1\u00AB");		
			stream = stream.replaceAll(">"+white+"\u00A5\u00A6","\u00BB$1\u00A5\u00A6");			
			stream = stream.replaceAll("\u00A6\u00A5"+white+"<","\u00A6\u00A5$1\u00AB");		
			stream = stream.replaceAll(">"+white+"\u00A6\u00A5","\u00BB$1\u00A6\u00A5");		

			
			//Words
			stream = stream.replaceAll(""+white+"if"+white+"<","$1if$2\u00AB");
			stream = stream.replaceAll(""+white+"then"+white+"<","$1then$2\u00AB");
			stream = stream.replaceAll(""+white+"else"+white+"<","$1else$2\u00AB");
			stream = stream.replaceAll(">"+white+"then"+white+"","\u00BB$1then$2");
			stream = stream.replaceAll(">"+white+"else"+white+"","\u00BB$1else$2");
				
			//Equality
			stream = stream.replaceAll(">"+white+"[=][=]","\u00BB$1==");
			stream = stream.replaceAll("[=][=]"+white+"<","==$1\u00AB");
			stream = stream.replaceAll("[!][=]"+white+"<","!=$1\u00AB");
			stream = stream.replaceAll(">"+white+"[!][=]","\u00BB$1!=");
			
			//Sequence Opening
			stream = stream.replaceAll("#"+white+"<","#$1\u00AB");
			stream = stream.replaceAll("\\|"+white+"<","|$1\u00AB");
			stream = stream.replaceAll("@"+white+"<","@$1\u00AB");			
			stream = stream.replaceAll("\\^"+white+"<","^$1\u00AB");
			stream = stream.replaceAll("\\\\"+white+"<","\\\\$1\u00AB");						
			stream = stream.replaceAll("\\$"+white+"<","\\$$1\u00AB");
			stream = stream.replaceAll("\\!"+white+"<","!$1\u00AB");
			stream = stream.replaceAll("\\?"+white+"<","?$1\u00AB");						
			stream = stream.replaceAll("[:]"+white+"<",":$1\u00AB");
			stream = stream.replaceAll("\\."+white+"<",".$1\u00AB");
			stream = stream.replaceAll("\r\n"+white+"<","\r\n$1\u00BB");
			stream = stream.replaceAll("\r"+white+"<","\n$1\u00BB");
			stream = stream.replaceAll("\n"+white+"<","\r$1\u00BB");
			stream = stream.replaceAll("[=]"+white+"<","=$1\u00AB");
			stream = stream.replaceAll("[(]"+white+"<","($1\u00AB");
			stream = stream.replaceAll("\\["+white+"<","[$1\u00AB");
			stream = stream.replaceAll("[{]"+white+"<","{$1\u00AB");
			stream = stream.replaceAll("[,]"+white+"<",",$1\u00AB");
			stream = stream.replaceAll("\u00AB"+white+"<","\u00AB$1\u00AB");

			//Smaller
			stream = stream.replaceAll("(\\d|\\w|\\_)"+white+"<","$1$2\u20AC");
			stream = stream.replaceAll("}"+white+"<","}$1\u20AC");
			
			//Greater
			stream = stream.replaceAll(">"+white+"(\\d|\\w|\\_)","\u00A3$1$2");
			stream = stream.replaceAll(">"+white+"[{]","\u00A3$1{");
			
			//Sequence Closing
			stream = stream.replaceAll(">"+white+"\\^","\u00BB$1^");
			stream = stream.replaceAll(">"+white+"\\\\","\u00BB$1\\\\");
			stream = stream.replaceAll(">"+white+"\\|","\u00BB$1|");
			stream = stream.replaceAll(">"+white+"@","\u00BB$1@");			
			stream = stream.replaceAll(">"+white+"\\$","\u00BB$1\\$");
			stream = stream.replaceAll(">"+white+"\\!","\u00BB$1!");
			stream = stream.replaceAll(">"+white+"\\?","\u00BB$1?");					
			stream = stream.replaceAll(">"+white2+"[=]","\u00BB$1=");
			stream = stream.replaceAll(">"+white+"[:]","\u00BB$1:");
			stream = stream.replaceAll(">"+white+"\\.","\u00BB$1.");
			stream = stream.replaceAll(">"+white+"\r\n","\u00BB$1\r\n");
			stream = stream.replaceAll(">"+white+"\n","\u00BB$1\n");
			stream = stream.replaceAll(">"+white+"\r","\u00BB$1\r");
			stream = stream.replaceAll(">"+white+"\\]","\u00BB$1]");
			stream = stream.replaceAll(">"+white+"[)]","\u00BB$1)");
			stream = stream.replaceAll(">"+white+"[}]","\u00BB$1}");
			stream = stream.replaceAll(">"+white+"[,]","\u00BB$1,");
			stream = stream.replaceAll(">"+white+"\u00BB","\u00BB$1\u00BB");
			
			//Empty Sequence
			stream = stream.replaceAll("<"+white+">","\u00AB\u00BB");
			
			if(stream.equals(old))
			{
				replaceable = false;
			}
			else
			{
				old = stream;
			}
		}
		
		//Reiclude saved tokens
		stream = stream.replaceAll("\u00A2\u00A4\u00A2","<=>");
		stream = stream.replaceAll("\u00A2\u00A4","<=");
		stream = stream.replaceAll("\u00A4\u00A2","=>");
		stream = stream.replaceAll("\u00A6\u00A5\u00A6","<->");
		stream = stream.replaceAll("\u00A5\u00A6","->");
		stream = stream.replaceAll("\u00A6\u00A5","<-");
		stream = stream.replaceAll("\u00A7\u00A8","[>");
		stream = stream.replaceAll("\u00B1\u00B2","<|");
		stream = stream.replaceAll("\u00B2\u00B1","|>");

				
		//Spalte stream auf.
		char[] streamChar = stream.toCharArray();
		ArrayList<String> strArr = new ArrayList<String>();
		String temp = "";
		int o = 0;

		while(o<streamChar.length)
		{
			if(o<streamChar.length-1 && streamChar[o] == '\r' 
									 && streamChar[o+1] == '\n')
			{
				strArr.add(temp);
				o += 2;
				temp = "";
			}
			else if(streamChar[o] == '\r')
			{
				strArr.add(temp);
				o += 1;
				temp = "";
			}
			else if(streamChar[o] == '\n')
			{
				strArr.add(temp);
				o += 1;
				temp = "";
			}
			else if(o < streamChar.length-1)
			{
				temp += streamChar[o];
				o++;
			}
			else
			{
				temp += streamChar[o];
				strArr.add(temp);
				o++;
			}
		}
	
		//Betrachte Zeile x. Falls Bruteforce notwendig, versuche x zu parsen.
		//Falls nicht moeglich, betrachte String aus x und x+1 und so weiter.
		
		String afterBruteForce = "";
		
		String subString = "";
		boolean lastSuccesful = true;
		for(int q = 0; q< strArr.size();q++)
		{
			if(lastSuccesful)
			{
				subString = strArr.get(q);			
			}
			else
			{
				subString += "\r\n"+strArr.get(q);
			}
			
			ArrayList<Integer> al = new ArrayList<Integer>();
			char[] c = subString.toCharArray();
	
			for(int i = 1;i<c.length-1;i++)
			{
				if(!((c[i] == '>' && c[i-1] == '=')
					|| c[i] == '<' && c[i+1] == '='
					|| c[i] == '>' && c[i+1] == '='
					|| c[i] == '>'&& c[i-1] == '|'
					|| c[i] == '>'&& c[i-1] == '['
					|| c[i] == '<'&& c[i+1] == '|'
					|| c[i] == '<'&& c[i+1] == '-'
					|| c[i] == '>'&& c[i-1] == '-')
					&& (c[i] == '<' || c[i] == '>'))
				{
					al.add(i);
				}
			}
			
			if(al.size()>0)
			{
				System.out.println("ACHTUNG BRUTE-FORCE!!!");
				String addToString = bruteForce(subString, al ,0);
				if(addToString.equals(""))
				{
					lastSuccesful = false;
				}
				else
				{
					lastSuccesful = true;
					afterBruteForce += addToString+"\r\n";
				}
				try
				{
					PrintStream p = new PrintStream(System.out, true, "UTF-8");
					p.print(addToString);
				}
				catch(Exception e){}
					
			}
			else
			{
				afterBruteForce += subString+"\r\n";
			}	
			
		}
			
		char[] end = afterBruteForce.toCharArray();
		afterBruteForce = "";
		
		for(int p = 0;p<end.length;p++)
		{
			if((p == end.length-2 && end[p] == '\r' && end[p+1] == '\n')
				||(p == end.length-1 && end[p] == '\r')
				||(p == end.length-1 && end[p] == '\n'))
			{
				break;
			}
			else
			{
				afterBruteForce += end[p];
			}
		}		
		return afterBruteForce;

	}


	public String bruteForce(String a, ArrayList<Integer> arr, int current)
	{	
		String upper = "";
		String lower = "";
		
		//Current Tria to greater/less
		try
		{	
			char[] c = a.toCharArray();
			if(c[arr.get(current)] == '<')
			{
				c[arr.get(current)] = '\u20AC'; //LESS
			}
			else
			{
				c[arr.get(current)] = '\u00A3'; //GREATER
			}
			upper = String.valueOf(c);
													
/* 			try{
				PrintStream out2 = new PrintStream(System.out, true, "UTF-8");
				out2.println(upper);
			}
			catch(Exception e){}
*/					
			StringReader sr = new StringReader(upper);
			BufferedReader br = new BufferedReader(sr); 
			Lexer l = new Lexer(new PushbackReader(br,100000));
			Parser p = new Parser(l);
			Start tree = p.parse();
//			Typechecker ts = new Typechecker();
//			tree.apply(ts);
		}
		catch(Exception e)
		{
			if(current < arr.size()-1)
			{
				upper = bruteForce(upper, arr, current+1);
			}
			else
			{
				upper = "";
			}
		}
		
		//Change tria at current to SEQ-Pars
		try
		{	
			char[] c = a.toCharArray();
			if(c[arr.get(current)] == '<')
			{
				c[arr.get(current)] = '\u00AB'; //SEQ Opening
			}
			else
			{
				c[arr.get(current)] = '\u00BB'; //SEQ CLOSING
			}
			lower = String.valueOf(c);		
/* 
			try{
				PrintStream out2 = new PrintStream(System.out, true, "UTF-8");
				out2.println(lower);
			}
			catch(Exception e){} 
*/		
			StringReader sr = new StringReader(lower);
			BufferedReader br = new BufferedReader(sr); 
			Lexer l = new Lexer(new PushbackReader(br,100000));
			Parser p = new Parser(l);
			Start tree = p.parse();
//			Typechecker ts = new Typechecker();
//			tree.apply(ts);
		}
		catch(Exception e)
		{
			if(current < arr.size()-1)
			{
				lower = bruteForce(lower, arr, current+1);
			}
			else
			{
				lower = "";
			}
		}	
		return upper+lower;	
	}
	
	
	public String defineWhitespace()
	{
		
		String whitespace_chars =  ""       /* dummy empty string for homogeneity */
                        + "\\u0009" // CHARACTER TABULATION
                        + "\\u000B" // LINE TABULATION
                        + "\\u000C" // FORM FEED (FF)
                        + "\\u0020" // SPACE
                        + "\\u0085" // NEXT LINE (NEL) 
                        + "\\u00A0" // NO-BREAK SPACE
                        + "\\u1680" // OGHAM SPACE MARK
                        + "\\u180E" // MONGOLIAN VOWEL SEPARATOR
                        + "\\u2000" // EN QUAD 
                        + "\\u2001" // EM QUAD 
                        + "\\u2002" // EN SPACE
                        + "\\u2003" // EM SPACE
                        + "\\u2004" // THREE-PER-EM SPACE
                        + "\\u2005" // FOUR-PER-EM SPACE
                        + "\\u2006" // SIX-PER-EM SPACE
                        + "\\u2007" // FIGURE SPACE
                        + "\\u2008" // PUNCTUATION SPACE
                        + "\\u2009" // THIN SPACE
                        + "\\u200A" // HAIR SPACE
                        + "\\u2028" // LINE SEPARATOR
                        + "\\u2029" // PARAGRAPH SEPARATOR
                        + "\\u202F" // NARROW NO-BREAK SPACE
                        + "\\u205F" // MEDIUM MATHEMATICAL SPACE
                        + "\\u3000" // IDEOGRAPHIC SPACE
                        ;   
		String whitespace_charclass = "(["  + whitespace_chars + "]*)"; 
		white2 = "(["  + whitespace_chars + "]+)";
		return whitespace_charclass;
	}
	
}