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
		stream = stream.replaceAll(">=","\u00A1\u00A2");
		stream = stream.replaceAll("<=>","\u00A2\u00A4\u00A2");
		stream = stream.replaceAll("<=","\u00A4\u00A2");
		stream = stream.replaceAll("=>","\u00A4\u00A1");
		stream = stream.replaceAll("<->","\u00A6\u00A5\u00A6");
		stream = stream.replaceAll("->","\u00A5\u00A6");
		stream = stream.replaceAll("<-","\u00A6\u00A5");
		stream = stream.replaceAll("\\[>","\u00A7\u00A8");
		stream = stream.replaceAll("<\\|","\u00B1\u00B2");
		stream = stream.replaceAll("\\|>","\u00B2\u00B1");

		while(replaceable)
		{			
			//Equality
			stream = stream.replaceAll(">\u0020*[=][=]","\u00BB==");
			stream = stream.replaceAll("[=][=]\u0020*<","==\u00AB");
			stream = stream.replaceAll("[!][=]\u0020*<","!=\u00AB");
			stream = stream.replaceAll(">\u0020*[!][=]","\u00BB!=");
			
			//Sequence Opening
			stream = stream.replaceAll("\\^\u0020*<","^\u00AB");
			stream = stream.replaceAll("[:]\u0020*<",":\u00AB");
			stream = stream.replaceAll("\\.\u0020*<",".\u00AB");
			stream = stream.replaceAll("\r\n\u0020*<","\r\n\u00BB");
			stream = stream.replaceAll("\r\u0020*<","\n\u00BB");
			stream = stream.replaceAll("\n\u0020*<","\r\u00BB");
			stream = stream.replaceAll("[=]\u0020*<","=\u00AB");
			stream = stream.replaceAll("[(]\u0020*<","(\u00AB");
			stream = stream.replaceAll("[{]\u0020*<","{\u00AB");
			stream = stream.replaceAll("[,]\u0020*<",",\u00AB");
			stream = stream.replaceAll("\u00AB\u0020*<","\u00AB\u00AB");

			//Smaller
			stream = stream.replaceAll("(\\d|\\w)\u0020*<","$1\u20AC");
			stream = stream.replaceAll("}\u0020*<","}\u20AC");
			
			//Greater
			stream = stream.replaceAll(">\u0020*(\\d|\\w)","\u00A3$1");
			stream = stream.replaceAll(">\u0020*[{]","\u00A3{");
			
			//Sequence Closing
			stream = stream.replaceAll(">\u0020*\\^","\u00BB^");
			stream = stream.replaceAll(">\u0020+[=]","\u00BB=");
			stream = stream.replaceAll(">\u0020*[:]","\u00BB:");
			stream = stream.replaceAll(">\u0020*\\.","\u00BB.");
			stream = stream.replaceAll(">\u0020*\r\n","\u00BB\r\n");
			stream = stream.replaceAll(">\u0020*\n","\u00BB\n");
			stream = stream.replaceAll(">\u0020*\r","\u00BB\r");
			stream = stream.replaceAll(">\u0020*[)]","\u00BB)");
			stream = stream.replaceAll(">\u0020*[}]","\u00BB}");
			stream = stream.replaceAll(">\u0020*[,]","\u00BB,");
			stream = stream.replaceAll(">\u0020*\u00BB","\u00BB\u00BB");
			
			//Empty Sequence
			stream = stream.replaceAll("<\u0020*>","\u00AB\u00BB");
			
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
		stream = stream.replaceAll("\u00A1\u00A2",">=");
		stream = stream.replaceAll("\u00A2\u00A4\u00A2","<=>");
		stream = stream.replaceAll("\u00A4\u00A2","<=");
		stream = stream.replaceAll("\u00A4\u00A1","=>");
		stream = stream.replaceAll("\u00A6\u00A5\u00A6","<->");
		stream = stream.replaceAll("\u00A5\u00A6","->");
		stream = stream.replaceAll("\u00A6\u00A5","<-");
		stream = stream.replaceAll("\u00A7\u00A8","[>");
		stream = stream.replaceAll("\u00B1\u00B2","<|");
		stream = stream.replaceAll("\u00B2\u00B1","|>");	
		
		ArrayList<Integer> al = new ArrayList<Integer>();
		char[] c = stream.toCharArray();
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
			
		try{
			PrintStream out2 = new PrintStream(System.out, true, "UTF-8");
			out2.println(stream);
		}
		catch(Exception e){}
		if(al.size()>0)
		{		
			System.out.println("ACHTUNG BRUTE-FORCE!!!");
			String g = bruteForce(stream, al ,0);
			try{
				PrintStream out3 = new PrintStream(System.out, true, "UTF-8");
				out3.println("Nachher: "+g);
			}
			catch(Exception e){}
			
			return g;
		}
		else
		{
			return stream;
		}
		
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
					
			StringReader sr = new StringReader(upper);
			BufferedReader br = new BufferedReader(sr); 
			Lexer l = new Lexer(new PushbackReader(br,100000));
			Parser p = new Parser(l);
			Start tree = p.parse();
			Typechecker ts = new Typechecker();
			tree.apply(ts);
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
										
			StringReader sr = new StringReader(lower);
			BufferedReader br = new BufferedReader(sr); 
			Lexer l = new Lexer(new PushbackReader(br,100000));
			Parser p = new Parser(l);
			Start tree = p.parse();
			Typechecker ts = new Typechecker();
			tree.apply(ts);
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