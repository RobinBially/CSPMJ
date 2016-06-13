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

/*
\u00AB = «	seq opening
\u00BB = »	seq closing
\u00A3 = £	greater
\u20AC = €  smaller
*/
public class TriangleBruteForce
{
private String stream;	
	
	public TriangleBruteForce(String s)
	{
		stream = s;
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
		stream = stream.replaceAll("<=","\u00A4\u00A2"); //Könnten ersetzt werden 
		stream = stream.replaceAll("=>","\u00A4\u00A1"); //Siehe smaller greater erste Regel
		stream = stream.replaceAll("<->","\u00A6\u00A5\u00A6");
		stream = stream.replaceAll("->","\u00A5\u00A6");
		stream = stream.replaceAll("<-","\u00A6\u00A5");
		stream = stream.replaceAll("\\[>","\u00A7\u00A8");
		stream = stream.replaceAll("<\\|","\u00B1\u00B2");
		stream = stream.replaceAll("\\|>","\u00B2\u00B1");

		while(replaceable)
		{	
			//Words
			stream = stream.replaceAll("(\u0020*)if(\u0020*)<","$1if$2\u00AB");
			stream = stream.replaceAll("(\u0020*)then(\u0020*)<","$1then$2\u00AB");
			stream = stream.replaceAll("(\u0020*)else(\u0020*)<","$1else$2\u00AB");
			stream = stream.replaceAll(">(\u0020*)then(\u0020*)","\u00BB$1then$2");
			stream = stream.replaceAll(">(\u0020*)else(\u0020*)","\u00BB$1else$2");
				
			//Equality
			stream = stream.replaceAll(">(\u0020*)[=][=]","\u00BB$1==");
			stream = stream.replaceAll("[=][=](\u0020*)<","==$1\u00AB");
			stream = stream.replaceAll("[!][=](\u0020*)<","!=$1\u00AB");
			stream = stream.replaceAll(">(\u0020*)[!][=]","\u00BB$1!=");
			
			//Sequence Opening
			stream = stream.replaceAll("\\^(\u0020*)<","^$1\u00AB");
			stream = stream.replaceAll("[:](\u0020*)<",":$1\u00AB");
			stream = stream.replaceAll("\\.(\u0020*)<",".$1\u00AB");
			stream = stream.replaceAll("\r\n(\u0020)*<","\r\n$1\u00BB");
			stream = stream.replaceAll("\r(\u0020*)<","\n$1\u00BB");
			stream = stream.replaceAll("\n(\u0020*)<","\r$1\u00BB");
			stream = stream.replaceAll("[=](\u0020*)<","=$1\u00AB");
			stream = stream.replaceAll("[(](\u0020*)<","($1\u00AB");
			stream = stream.replaceAll("[{](\u0020*)<","{$1\u00AB");
			stream = stream.replaceAll("[,](\u0020*)<",",$1\u00AB");
			stream = stream.replaceAll("\u00AB(\u0020*)<","\u00AB$1\u00AB");

			//Smaller
			stream = stream.replaceAll("(\\d|\\w|\\_)(\u0020)*<","$1$2\u20AC");
			stream = stream.replaceAll("}(\u0020*)<","}$1\u20AC");
			
			//Greater
			stream = stream.replaceAll(">(\u0020*)(\\d|\\w|\\_)","\u00A3$1$2");
			stream = stream.replaceAll(">(\u0020*)[{]","\u00A3$1{");
			
			//Sequence Closing
			stream = stream.replaceAll(">(\u0020*)\\^","\u00BB$1^");
			stream = stream.replaceAll(">(\u0020+)[=]","\u00BB$1=");
			stream = stream.replaceAll(">(\u0020*)[:]","\u00BB$1:");
			stream = stream.replaceAll(">(\u0020*)\\.","\u00BB$1.");
			stream = stream.replaceAll(">(\u0020*)\r\n","\u00BB$1\r\n");
			stream = stream.replaceAll(">(\u0020*)\n","\u00BB$1\n");
			stream = stream.replaceAll(">(\u0020*)\r","\u00BB$1\r");
			stream = stream.replaceAll(">(\u0020*)[)]","\u00BB$1)");
			stream = stream.replaceAll(">(\u0020*)[}]","\u00BB$1}");
			stream = stream.replaceAll(">(\u0020*)[,]","\u00BB$1,");
			stream = stream.replaceAll(">(\u0020*)\u00BB","\u00BB$1\u00BB");
			
			//Empty Sequence
			stream = stream.replaceAll("<(\u0020*)>","\u00AB\u00BB");
			
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
		stream = stream.replaceAll("\u00A4\u00A2","<=");
		stream = stream.replaceAll("\u00A4\u00A1","=>");
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
	
		//Zeilenweise Bestimmung von Klammern durch rohe Gewalt
		
		String afterBruteForce = "";
		for(String subString : strArr)
		{
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
			//	if(addToString.equals(""))
			//	{
			//		throw new RuntimeException("TypeError in Bruteforce Typechecking");
			//	}
				afterBruteForce += addToString+"\r\n";	
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
}