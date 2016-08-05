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

/*	Triangle substitutions:
	\u00AB = «	seq opening
	\u00BB = »	seq closing
	\u00A3 = £	greater
	\u20AC = €  smaller
*/

public class TriangleBracketSubstitution
{
private ArrayList<String> instructions;
private String stream;

private String[] notEndOfInstruction = {"then","else","and","or","not","let","within",
",","..",":","=","@","#","\\","||","|-","|","@@","|||","|~|","[]","/\\","[>","(",">=","<=","\u00AB","\u20AC",
"<","[+","+]","/+","+\\","[[",":[","]:","[|","|]","[","{","&",".",";","->","^","==","!=","[T=","[F=","[FD=",
"[R=","[T]","[F]","[FD]","*","/","%","/","+"};

private String[] notBeginningOfInstruction = {"then","else","and","or","not","..",":","||","|-","|","@@","|||",
"|~|","[]","/\\","[>",")",">=","<=","\u00BB","\u00A3",">","[+","+]","/+","+\\","[[",":[","]:","[|","|]","[",
"]","&",".",";","->","^","==","!=","[T=","[F=","[FD=","[R=","[T]","[F]","[FD]","*","/","%","/","+"};

private String[] priorToOpeningSequence = {"<=","=>","->","<-","if","then","else","==","!=","#","|","@","^",
"\\","$","!","?",":",".","\r\n","\r","\n","=","(","[","{",",","\u00AB","sof"};

private String[] nextFromClosingSequence = {"=>","<=","<-","->","then","else","==","!=","^","\\","|","@","$",
"!","?","=",":",".","\r\n","\r","\n","]",")","}",",","\u00BB","eof"};

private String[] nextFromGreater = {"{","(","'","word"};
private	String[] priorToSmaller = {"}",")","'","word"};
private	String priorToClosingSequence = "\u00AB";

	public TriangleBracketSubstitution(String s)
	{
		stream = s;
		//instructions = new ArrayList<String>();
		//splitIntoInstructions(stream);
	}
	
	public String findTriangles()
	{
		char[] c = stream.toCharArray();
		String old = "";
		while(!old.equals(new String(c)))
		{	
			old = new String(c);
			int i = 0;
			
			while(i<c.length)
			{
				
				if(c[i] == '<')
				{
					if((i<c.length-1) && (c[i+1] == '=' || c[i+1] == '-'))
					{
						if((i<c.length-2) && (c[i+2] == '>'))
							i += 2;
						else
							i += 1;
					}
					else if((i<c.length-1) && (c[i+1] == '|'))
					{
						i+= 1;
					}
				//*****************************************Start Substitution
					else if(isOpeningSequence(c,i))
					{
						c[i] = '\u00AB';
					}
					else if(isSmaller(c,i))
					{
						c[i] = '\u20AC';
					}
				}
				//******************************************End Substitution		
				else if((i<c.length-1)
						&& (c[i] == '=' || c[i] == '-' ||c[i] == '[' || c[i] == '|') && (c[i+1] == '>'))
				{
					i+=1;
				}
				//************************************Start Substitution
				else if(c[i] == '>')
				{
					
					if((i<c.length-1) && (c[i+1] == '='))
					{
						i+=1;
					}
					else if(isClosingSequence(c,i))
					{
						c[i] = '\u00BB';
					}
					else if(isGreater(c,i))
					{
						c[i] = '\u00A3';
					}
				}
				//************************************End Substitution
				i++;
			}
		}			
		String t = new String(c);
		//String t = prepareBruteForce(c);
		return t;
	}
	
	public boolean isOpeningSequence(char[] c, int i) 
	{
		for(int k = 0;k<priorToOpeningSequence.length;k++)
		{
			if(previousIs(c,priorToOpeningSequence[k],i))
				return true;
		}
		return false;
	}
	
	public boolean isClosingSequence(char[] c, int i)
	{	
		for(int k = 0;k<nextFromClosingSequence.length;k++)
		{
			if(nextIs(c,nextFromClosingSequence[k],i))
			{
				return true;
			}
		}			
		if(previousIs(c,priorToClosingSequence,i))
			return true;
		else
			return false;
	}	
	
	public boolean isGreater(char[] c, int i)
	{
		for(int k = 0;k<nextFromGreater.length;k++)
		{
			if(nextIs(c,nextFromGreater[k],i))
				return true;
		}
		return false;
	}
	
	public boolean isSmaller(char[] c, int i)
	{
		for(int k = 0;k<priorToSmaller.length;k++)
		{
			if(previousIs(c,priorToSmaller[k],i))
				return true;
		}
		return false;
	}	
	
	public boolean nextIs(char[] c, String s, int i) //next token from index i in array c is s
	{
		char[] o = s.toCharArray();

		int j;
		if((s.indexOf("\r") == -1) && (s.indexOf("\n") == -1))
			j = jumpWhitespaceForward(c,i+1);
		else 
			j = i+1;
		
		int u = 0;	
		String buffer = "";
		
		if(s.equals("eof")) // Last char in file
		{
			if(j == c.length-1)
				return true;
			else
				return false;
		}
		else if(s.equals("word"))
		{		
			while(j<c.length && c[j] != ' ' && c[j] != '\t' && c[j] != '\r' && c[j] != '\n'
						&& Pattern.matches("\\w",String.valueOf(c[j])))
			{
				buffer += c[j];
				j++;
				u++;
			}
			if(Pattern.matches("\\w+",buffer))
				return true;
			else
				return false;
		}
		else if(s.equals("then") || s.equals("else")) //e.g. "> then "
		{
			while(u<o.length && j<c.length)
			{			
				if(c[j] == o[u])
				{
					u++;
					j++;
				}
				else
				{
					return false;
				}
			}
			if(c[j] == ' ' || c[j] == '\t' ||c[j] == '\r' || c[j] == '\n' ) //after then has to be whitespace
				return true;
			else 
				return false;
		}
		else
		{
			while(u<o.length && j<c.length)
			{
				if(c[j] == o[u])
				{
					j++;
					u++;
				}
				else
				{
					return false;
				}
			}
			if(u == o.length)
				return true;	
			else 
				return false;
		}
	}
	
	public boolean previousIs(char[] c,String s, int i) //previous token from index i is s
	{
		char[] o = s.toCharArray();
		int j;
		if((s.indexOf("\r") == -1) && (s.indexOf("\n") == -1))
			j = jumpWhitespaceBack(c,i-1);
		else 
			j = i-1;
		
		int u = o.length-1;	
		String buffer = "";
		
		if(s.equals("sof")) //First char in file
		{
			if(j == 0)
				return true;
			else 
				return false;
		}
		else if(s.equals("word"))
		{	
			while(j>=0 && c[j] != ' ' && c[j] != '\t' && c[j] != '\r' && c[j] != '\n' 
					&& Pattern.matches("\\w",String.valueOf(c[j])))
			{
				buffer += c[j];
				j-=1;
				u-=1;
			}
			if(Pattern.matches("\\w+",buffer))
				return true;
			else
				return false;
		}
		else if(s.equals("if") || s.equals("then") || s.equals("else")) //e.g. " if < "
		{
			
			while(u>=0 && j>=0)
			{
				if(c[j] == o[u])
				{
					u-=1;
					j-=1;
				}
				else
				{
					return false;
				}
			}
			if(c[j] == ' ' ||c[j] == '\t' ||c[j] == '\r' ||c[j] == '\n' ) //before if has to be whitespace
			{
				return true;
			}
			else 
			{
				return false;
			}
		}
		else
		{
			while(u>=0 && j>=0)
			{	
				if(c[j] == o[u])
				{
					j-=1;
					u-=1;
				}
				else
				{	
					return false;
				}
			}
			if(u == -1)			
				return true;		
			else 			
				return false;								
		}
	}
	
	public int jumpWhitespaceForward(char[] c, int i) // return first index from i without whitespace
	{
		while(i<c.length)
		{
			if(c[i] == ' ' || c[i] == '\t' || c[i] == '\r' || c[i] == '\n')
			i++;
			else
			return i;
		}
		return i-1;
	}
	
	public int jumpWhitespaceBack(char[] c, int i) // return first index prior to i without whitespace
	{
		while(i>=0)
		{
			if(c[i] == ' ' || c[i] == '\t' || c[i] == '\r' || c[i] == '\n')
			i-= 1;
			else
			return i;
		}
		return i+1;
	}	
	
	public void splitIntoInstructions(String s)
	{
		char[] c = s.toCharArray();
		String buffer = "";
		int count = 0;
		while(count<c.length)
		{
			buffer += c[count];
			if((c[count] == '\r' || c[count] == '\n') && isEndOfInstruction(c,count))
			{
				while(c[count] == '\r'||c[count] == '\n'||c[count] == '\t'||c[count] == ' ')
				{
					buffer += c[count];
					count++;
				}
				instructions.add(buffer);
				buffer = "";
				count -= 1;
			}
			if(count == c.length-1)
			{
				instructions.add(buffer);
			}
			count++;
		}
	}
	
	public boolean isEndOfInstruction(char[] c, int i)
	{
		String buffer = "";
		int helpIndex = i-6;
		while(helpIndex<i)
		{
			buffer += c[i];
			helpIndex ++;
		}
		for(int j = 0; j<notEndOfInstruction.length;j++)
		{
			if(buffer.indexOf(notEndOfInstruction[j]) > -1)
				return false;
		}
		helpIndex = i;
		while(c[helpIndex] == '\r'||c[helpIndex] == '\n'||c[helpIndex] == '\t'||c[helpIndex] == ' ')
		{
			helpIndex++;
		}
		for(int k = 0; k<notBeginningOfInstruction.length;k++)
		{
			if(buffer.indexOf(notBeginningOfInstruction[k]) > -1)
				return false;
		}		
		return true;		
	}
	
	public String prepareBruteForce(char[] streamChar)
	{
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
				//System.out.println("ACHTUNG BRUTE-FORCE!!!");
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
	
			StringReader sr = new StringReader(upper);
			BufferedReader br = new BufferedReader(sr); 
			Lexer l = new Lexer(new PushbackReader(br,100000));
			Parser p = new Parser(l);
			Start tree = p.parse();
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