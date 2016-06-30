import java.io.*;
import java.*;
import java.util.*;
import java.lang.*;
import java.util.regex.*;
import java.lang.Character;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

import de.prob.prolog.output.StructuredPrologOutput;
import de.prob.prolog.term.PrologTerm;
import CSPMparser.parser.*;
import CSPMparser.lexer.*;
import CSPMparser.node.*;

public class CSPMparser
{
public String newstream;
public String currentFile;
public int workcount;

public String getExtension(String filename)
{
	if (filename == null) 
	{
		return null;
	}
	int extensionPos = filename.lastIndexOf('.');
	int lastUnixPos = filename.lastIndexOf('/');
	int lastWindowsPos = filename.lastIndexOf('\\');
	int lastSeparator = Math.max(lastUnixPos, lastWindowsPos);
	int index = lastSeparator > extensionPos ? -1 : extensionPos;
	if (index == -1) 
	{
		return "";
	} 
	else 
	{
		return filename.substring(index + 1);
	}
}

public String getPath()
{
	
	File temp = new File("temp.temp");
	String absolutePath = temp.getAbsolutePath();				
	String filePath = absolutePath.
	substring(0,absolutePath.lastIndexOf(File.separator));						
	return filePath;
}


public int parseFilesInFolder(File folder, Boolean show)
{
	int i = 0;
	for (File fileEntry : folder.listFiles()) 
	{		
		if (fileEntry.isDirectory())
		{
			i += parseFilesInFolder(fileEntry,show);
		} 
		else if(getExtension(fileEntry.toString()).equals("csp"))
		{
			i++;
			if(show)
			System.out.println("\n\nParsing '"+fileEntry.getName()+"':");
			else
			System.out.println("\n\nParsing '"+fileEntry.getName()+"'...");
			workcount = 0;
			try 
			{
				newstream = getStringFromFile(fileEntry.toString());
				newstream = deleteComments(newstream);
				newstream = includeFile(newstream);
				newstream = deleteComments(newstream);
				
				workcount++; //now 1
				 
				TriangleBruteForce tbf = new TriangleBruteForce(newstream);
				newstream = tbf.findTriangles();
				
				if(show)
				{
					printNewStream(newstream);
				}
				
				workcount++; //now 2

				StringReader sr = new StringReader(newstream);
				BufferedReader br = new BufferedReader(sr); 
				Lexer l = new LexHelper(new PushbackReader(br,100000));
				Parser p = new Parser(l);
				Start tree = p.parse();	
				
//				workcount++; //now 3
//
//				StatementPatternCheck spc = new StatementPatternCheck();
//				tree.apply(spc);
//				
//				workcount++; //now 4
//				TreeLogicChecker tlc = new TreeLogicChecker();
//				tree.apply(tlc);
//
//				OccurrenceCheck oc = new OccurrenceCheck();
//				tree.apply(oc);
//				System.out.println("No unbound Identifiers were found.");				
//				workcount++; //now 5
		//		Typechecker ts = new Typechecker();
		//		tree.apply(ts);
			} 	
			catch (Exception e) 
			{
				if(workcount == 0)
				{
					throw new RuntimeException(e.getMessage());
				}
				if(workcount == 1)//Error in StreamEdit
				{
					throw new RuntimeException("\nError in bracket transformation: "+e.getMessage());
				}
				else if(workcount == 2) //Parsing Error
				{
					throw new RuntimeException("\nParsing Error: "+e.getMessage());
				}
				else if(workcount == 3) //Error in statement pattern checking
				{
					throw new RuntimeException("\nError in pattern checking: "+e.getMessage());
				}
				else if(workcount == 4) //Error in occurs checking
				{
					throw new RuntimeException("\nError in Identifier Analysis: "+e.getMessage());
				}
				else if(workcount == 5)
				{
					throw new RuntimeException("\nTypechecking Error: "+e.getMessage());
				}
			}
		}
	}
	return i;
}

		
public void parseFile(String s, Boolean show)
{
	System.out.println("Parsing '"+s+"' :");
	workcount = 0;
	try 
	{			
		newstream = getStringFromFile(s);
		newstream = deleteComments(newstream);
		newstream = includeFile(newstream);
		newstream = deleteComments(newstream);
		
		workcount++; //now 1
		
		TriangleBruteForce tbf = new TriangleBruteForce(newstream);
		newstream = tbf.findTriangles();
		
		if(show)
		{
			printNewStream(newstream);
		}
		
		workcount++; //now 2
		
		StringReader sr = new StringReader(newstream);
		BufferedReader br = new BufferedReader(sr); 
		Lexer l = new LexHelper(new PushbackReader(br,100000));
		Parser p = new Parser(l);
		Start tree = p.parse();	

		workcount++; //now 3
		
//		StatementPatternCheck spc = new StatementPatternCheck();
//		tree.apply(spc);
		
		workcount++; //now 4

		
		TreeLogicChecker tlc = new TreeLogicChecker();
		tree.apply(tlc);
		System.out.println("\nYour CSPM-File has been successfully parsed.\n"
							+"Checking Identifier occurrences...");
//		OccurrenceCheck oc = new OccurrenceCheck();
//		tree.apply(oc);
//		System.out.println("No unbound Identifiers were found.");
		
		workcount++; //now 5
		
//		Typechecker ts = new Typechecker();
//		tree.apply(ts);
//		tree.apply(ts);
//		System.out.println("Typechecking successful!");
		StructuredPrologOutput pto = new StructuredPrologOutput();
		PrologGenerator pout = new PrologGenerator(pto, "root");
		tree.apply(pout);
		pto.fullstop(); // needed to end a sentence (this should be removed later)
		System.out.println("Get tree in prolog form:");
		for (Iterator<PrologTerm> iterator = pto.getSentences().iterator(); iterator.hasNext();) {
			System.out.println(iterator.next().toString());			
		}
//		System.out.println("get tree in prolog form: " + pto.getSentences().iterator().next().toString());
	} 	
	catch (Exception e) 
	{
		if(workcount == 0)
		{
			throw new RuntimeException(e.getMessage());
		}
		if(workcount == 1)//Error in StreamEdit
		{
			throw new RuntimeException("\nError in bracket transformation: "+e.getMessage());
		}
		else if(workcount == 2) //Parsing Error
		{
			throw new RuntimeException("\nParsing Error: "+e.getMessage());
		}
		else if(workcount == 3) //Error in statement pattern checking
		{
			throw new RuntimeException("\nError in pattern checking: "+e.getMessage());
		}
		else if(workcount == 4) //Error in occurs checking
		{
			throw new RuntimeException("\nError in Identifier Analysis: "+e.getMessage());
		}
		else if(workcount == 5)
		{
			throw new RuntimeException("\nTypechecking Error: "+e.getMessage());
		}
		else {
			throw new RuntimeException("\nUnknown Error: "+e.getMessage());
		}
	}		
}


public String getStringFromFile(String fp)
{
	try 
	{	
		byte[] encoded = Files.readAllBytes(Paths.get(fp));
		String file_content = new String(encoded, StandardCharsets.UTF_8);
		return file_content;
	}
	catch(Exception e) 		
	{
		throw new RuntimeException("\nYour File was not found!");
	}	
	
}

public void printNewStream(String stream)
{
	char[] ca = stream.toCharArray();
	int i = 1;
	int j = 0;
	System.out.print("Line "+i+": ");
	while(j< ca.length)
	{
		
		if(ca[j] != '\r' && ca[j] != '\n')
		{	
			try
			{
				PrintStream print = new PrintStream(System.out, true, "UTF-8");
				print.print(ca[j]);
			}
			catch(Exception e){}
			j++;
		}
		else if (j+1<ca.length && ca[j] == '\r' && ca[j+1] == '\n')
		{
			j=j+2; // Überspringe LF im CR LF
			i++;
			System.out.print("\nLine "+i+": ");
		}
		else if (j<ca.length && ca[j] == '\n')
		{
			j++; // Überspringe LF
			i++;
			System.out.print("\nLine "+i+": ");
		}
		else if (j<ca.length && ca[j] == '\r')
		{
			j++; // Überspringe CR
			i++;
			System.out.print("\nLine "+i+": ");
		}
	}	
	
}

//Deletes content in range l-r in Chararray
public char[] delRange(int l, int r, char[] q)
{
	for(int i = l; i<=r;i++)
	{
		q[i] = ' ';
	}
	return q;
}

	
public String deleteComments(String ts)
{
		String newTS = ts;	
		char[] c = ts.toCharArray();	
		int i = 0;
		
		while(i<c.length)
		{
			if(c[i] == '{' && c[i+1] == '-')
			{
				int v = 0;
				int count_crlf = 0;
				int count_cr = 0;
				int count_lf = 0;
				boolean b = true;
				while(b)
				{

					if(c[i+v] == '-' && c[i+v+1] == '}')
					{
						c = delRange(i,i+v+1,c);
						b = false;
						i=i+v+1;
					}
					else
					{
						v++;
					}
				}
			}
			else if(c[i] == '-' && c[i+1] == '-')
			{
				int w = 0;
				boolean b = true;
				while(b)
				{
					if(c[i+w] == '\r' && c[i+w+1] == '\n')
					{
						c = delRange(i,i+w,c); //nicht i+w+1, da \n erhalten bleiben muss
						b = false;
						i = i+w+1;
					}
					else if(c[i+w] == '\r')
					{
						c = delRange(i,i+w-1,c);
						b = false;
						i = i+w;
					}
					else if(c[i+w] == '\n')
					{
						c = delRange(i,i+w-1,c);
						b = false;
						i = i+w;
					}
					else
					{
						w++;
						if((i+w) == c.length-1)
						{
							c = delRange(i,i+w,c);
							b = false;
							i = i+w;
						}
					}
				}
			}
		i++;
		}	
		newTS = String.valueOf(c);
		return newTS; 
}

public String includeFile(String incl)
{
	Pattern pattern = Pattern.compile("include \"(.*)\"");
	Matcher matcher = pattern.matcher(incl);
	ArrayList<String> al = new ArrayList<String>();
	StringBuffer sb = new StringBuffer();
	String path = "";
	while(matcher.find())
	{	
		path = matcher.group(1);
		
		File f = new File(path);
		if(f.exists() && !f.isDirectory()) 
		{ 
			System.out.println(path+"\nhas been included successfully.");
		}
		else
		{
			throw new RuntimeException("File "+path+" was not found.");
		}		
		String str = getStringFromFile(path);	
		matcher.appendReplacement(sb,str);	
	}
	matcher.appendTail(sb);
	return sb.toString();
}


public static void main(String arguments[]) 
{		
	CSPMparser cspm = new CSPMparser();
	if(arguments.length == 3)
	{
		if((arguments[0].toString().equals("-parse") 
			&& arguments[1].toString().equals("-show"))
			||(arguments[1].toString().equals("-parse") 
			&& arguments[0].toString().equals("-show")))
		{
			cspm.parseFile(arguments[2],true);
		}
	}
	else if(arguments.length == 2)
	{	
		if(arguments[0].toString().equals("-parse") 
			&& !(arguments[1].toString().equals("-show")) )
		{
			cspm.parseFile(arguments[1],false);
		}
		if((arguments[0].toString().equals("-parseAll") 
			&& arguments[1].toString().equals("-show"))
			||(arguments[1].toString().equals("-parseAll") 
			&& arguments[0].toString().equals("-show")))
		{
			Boolean help = true;
			File folder = new File(cspm.getPath());
			int k = cspm.parseFilesInFolder(folder,true);
			if(k == 1)
			{
				System.out.println("\nYour CSPM-File has been parsed successfully!");
			}
			else if(k==2)
			{
				System.out.println("\nBoth CSPM-Files have been parsed successfully!");
			}
			else
			{
				System.out.println("\n"+k+" CSPM-Files have been parsed successfully!");
			}
		}
	}
	else if((arguments.length == 1) && (arguments[0].toString().equals("-parseAll")))
	{
		File folder = new File(cspm.getPath());
		int k = cspm.parseFilesInFolder(folder,false);
		if(k == 1)
		{
			System.out.println("\nYour CSPM-File has been parsed successfully!");
		}
		else if(k==2)
		{
			System.out.println("\nBoth CSPM-Files have been parsed successfully!");
		}
		else
		{
			System.out.println("\n"+k+" CSPM-Files have been parsed successfully!");
		}
	}
	else
	{
		System.out.println("Incorrect input!");
		System.exit(1);
	}	
}
}