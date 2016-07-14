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

import CSPMparser.parser.*;
import CSPMparser.lexer.*;
import CSPMparser.node.*;

public class CSPMparser
{
	private String newstream;
	private String currentFile;
	private int exceptionCounter;
	private ArrayList<CommentInfo> commentList;
	private HashMap<Integer,Character> commentMap;

	public CSPMparser()
	{
		exceptionCounter = 0;
		commentMap = new HashMap<Integer,Character>();
		commentList = new ArrayList<CommentInfo>();
	}

	public int getExceptionCounter()
	{
		return exceptionCounter;
	}

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
		int fileCounter = 0;
		for (File fileEntry : folder.listFiles()) 
		{		
			if (fileEntry.isDirectory())
			{
				fileCounter += parseFilesInFolder(fileEntry,show);
			} 
			else if(getExtension(fileEntry.toString()).equals("csp"))
			{
				fileCounter++;
				if(show)
				System.out.println("\n\nParsing '"+fileEntry.getName()+"':");
				else
				System.out.println("\n\nParsing '"+fileEntry.getName()+"'...");

				try 
				{
					newstream = getStringFromFile(fileEntry.toString());
					newstream = saveComments(newstream);
					newstream = includeFile(newstream);
					newstream = saveComments(newstream);
					
					TriangleBruteForce tbf = new TriangleBruteForce(newstream);
					newstream = tbf.findTriangles();

					StringReader sr = new StringReader(newstream);
					BufferedReader br = new BufferedReader(sr); 
					Lexer l = new LexHelper(new PushbackReader(br,100000));
					Parser p = new Parser(l);
					Start tree = p.parse();						

					StatementPatternCheck spc = new StatementPatternCheck();
					tree.apply(spc);
					
					TreeLogicChecker tlc = new TreeLogicChecker();
					tree.apply(tlc);
					System.out.println("Checking unbound and renamed identifiers.");
					IdentifierAnalysis ia = new IdentifierAnalysis();
					tree.apply(ia);
					System.out.println("No unbound identifiers were found.\nYour File has been parsed successfully.");	
					
					PrologTermOutput pto = new PrologTermOutput();
					SymbolCollector sc = new SymbolCollector();
					tree.apply(sc);
					HashMap<String,ArrayList<SymInfo>> symbols = sc.getSymbols();
					
					PrologGenerator pout = new PrologGenerator(pto,symbols,true,commentList);
					tree.apply(pout);

					System.out.println("Generating Prolog-File.");
					createPrologFile(pto,fileEntry.toString(),null,"");
					
					//Typechecker ts = new Typechecker();
					//tree.apply(ts);
				} 	
				catch (Exception e) 
				{
					exceptionCounter++;
					System.out.println("An Exception was thrown!");
					createPrologFile(null,fileEntry.toString(),e,"");
				}
			}
			commentList.clear();
		}
		return fileCounter;
	}

	
	public String parseString(String s) throws CSPMparserException 
	{
		String ret = "";
		
		newstream = saveComments(s);
		newstream = includeFile(newstream);
		newstream = saveComments(newstream);
		TriangleBruteForce tbf = new TriangleBruteForce(newstream);
		newstream = tbf.findTriangles();
		StringReader sr = new StringReader(newstream);
		BufferedReader br = new BufferedReader(sr); 
		Lexer l = new LexHelper(new PushbackReader(br,100000));
		Parser p = new Parser(l);
		try {
			Start tree = p.parse();
			
			StatementPatternCheck spc = new StatementPatternCheck();
			tree.apply(spc);
						
			TreeLogicChecker tlc = new TreeLogicChecker();
			tree.apply(tlc);
			System.out.println("\nYour CSPM-File has been successfully parsed.\n"
			+"Checking Identifier occurrences...");
			IdentifierAnalysis ia = new IdentifierAnalysis();
			tree.apply(ia);
			
			PrologTermOutput pto = new PrologTermOutput();
			SymbolCollector sc = new SymbolCollector();
			tree.apply(sc);
			HashMap<String,ArrayList<SymInfo>> symbols = sc.getSymbols();
			PrologGenerator pout = new PrologGenerator(pto,symbols,false,commentList);
			tree.apply(pout);
			
			ret = pto.getStringWriter().toString();
			while(ret.endsWith("\n") || ret.endsWith("\r"))
			{
				ret = ret.substring(0,ret.length()-1);
			}		
			
		} catch (ParserException e) {
			e.printStackTrace();
			throw new CSPMparserException(e.getToken(),e.getLocalizedMessage());
		} catch (LexerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ret;
	}


	public void parseFile(String inputFile, String outputFile)
	{
		System.out.println("Parsing '"+inputFile+"'...");
		try 
		{			
			newstream = getStringFromFile(inputFile);
			newstream = saveComments(newstream);
			newstream = includeFile(newstream);
			newstream = saveComments(newstream);
			
			TriangleBruteForce tbf = new TriangleBruteForce(newstream);
			newstream = tbf.findTriangles();
			
			StringReader sr = new StringReader(newstream);
			BufferedReader br = new BufferedReader(sr); 
			Lexer l = new LexHelper(new PushbackReader(br,100000));
			Parser p = new Parser(l);
			Start tree = p.parse();	
			
			StatementPatternCheck spc = new StatementPatternCheck();
			tree.apply(spc);
	
			TreeLogicChecker tlc = new TreeLogicChecker();
			tree.apply(tlc);
			
			System.out.println("Checking unbound and renamed identifiers.");

			IdentifierAnalysis ia = new IdentifierAnalysis();
			tree.apply(ia);
			
			System.out.println("No unbound identifiers were found.\nYour File has been parsed successfully.");	
			
			PrologTermOutput pto = new PrologTermOutput();
			SymbolCollector sc = new SymbolCollector();
			tree.apply(sc);
			HashMap<String,ArrayList<SymInfo>> symbols = sc.getSymbols();
			PrologGenerator pout = new PrologGenerator(pto,symbols,true,commentList);
			tree.apply(pout);

			System.out.println("Generating Prolog-File.");
			createPrologFile(pto,inputFile,null,outputFile);


		} 	
		catch (Exception e) 
		{
			System.out.println("An Exception was thrown!");
			createPrologFile(null,inputFile,e,outputFile);
		}		
	}

	public void createPrologFile(PrologTermOutput pto,String filename, Exception e, String outputFile)
	{
		try
		{
			PrintWriter writer;
			if(outputFile.equals(""))
			{
				writer = new PrintWriter(filename+".pl", "UTF-8");
			}
			else
			{
				String name = outputFile.substring(12,outputFile.length());
				writer = new PrintWriter(name+".pl", "UTF-8");
			}
			if(e == null)
			{
				writer.println(":- dynamic parserVersionNum/1, parserVersionStr/1, parseResult/5."
				+"\n:- dynamic module/4."
				+"\n'parserVersionStr'('CSPMJ V0.5')."
				+"\n'parseResult'('ok','',0,0,0)."
				+"\n:- dynamic channel/2, bindval/3, agent/3."
				+"\n:- dynamic agent_curry/3, symbol/4."
				+"\n:- dynamic dataTypeDef/2, subTypeDef/2, nameType/2."
				+"\n:- dynamic cspTransparent/1."
				+"\n:- dynamic cspPrint/1."
				+"\n:- dynamic pragma/1."
				+"\n:- dynamic comment/2."
				+"\n:- dynamic assertBool/1, assertRef/5, assertTauPrio/6."
				+"\n:- dynamic assertModelCheckExt/4, assertModelCheck/3."
				+"\n:- dynamic assertLtl/4, assertCtl/4."
				+"\n'parserVersionNum'([0,5])."
				+"\n'parserVersionStr'('CSPMJ V0.5').");
				File file = new File(filename+".pl");	
				String str = pto.getStringWriter().toString();

				while(str.endsWith("\n") || str.endsWith("\r"))
				{
					str = str.substring(0,str.length()-1);
				}
				writer.print(str);
				
				
				writer.close();
			}
			else
			{
				writer.print(":- dynamic parserVersionNum/1, parserVersionStr/1, parseResult/5."
				+"\n:- dynamic module/4."
				+"\n'parserVersionStr'('0.6.1.1')."
				+"\n'parseResult'('parseError','"+e.getMessage()+"',0,0,0).");
				writer.close();
			}

		}
		catch(Exception ex)
		{
			throw new RuntimeException(ex.getMessage());
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
	
	public int getLineFromPos(int pos, char[] c)
	{
		int lineCount = 1;
		for(int i = 0; i<pos;i++)
		{
			if(c[i] == '\r' && c[i] == '\n')
			{
				lineCount++;
				i++;
			}
			else if(c[i] == '\r')
			{
				lineCount++;
			}
			else if(c[i] == '\n')
			{
				lineCount++;
			}
		}
		return lineCount;
	}
	
	public int getColumnFromPos(int pos, char[] c)
	{
		int columnCount = 1;
		for(int i = 0; i<pos;i++)
		{
			if(c[i] == '\r' && c[i] == '\n')
			{
				columnCount = 1;
				i++;
			}
			else if(c[i] == '\r')
			{
				columnCount = 1;
			}
			else if(c[i] == '\n')
			{
				columnCount = 1;
			}
			else
			{
				columnCount ++;
			}
		}
		return columnCount;	
	}
	//Deletes content in range l-r in Chararray and saves it in commentMap
	public char[] saveRange(int l, int r, char[] q, boolean isMultiline)
	{		
		String temp = "";
		for(int i = l; i<=r;i++)
		{
			temp += q[i];
			q[i] = ' ';
		}
		
		CommentInfo cInfo = new CommentInfo(getLineFromPos(l,q),getColumnFromPos(l,q),l,r-l,isMultiline,temp);
		commentList.add(cInfo);
		return q;
	}

	
	public String saveComments(String ts)
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
						c = saveRange(i,i+v+1,c,true);
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
						c = saveRange(i,i+w-1,c,false); //nicht i+w oder i+w+1, da newline erhalten bleiben muss
						b = false;
						i = i+w+1;
					}
					else if(c[i+w] == '\r')
					{
						c = saveRange(i,i+w-1,c,false);
						b = false;
						i = i+w;
					}
					else if(c[i+w] == '\n')
					{
						c = saveRange(i,i+w-1,c,false);
						b = false;
						i = i+w;
					}
					else
					{
						w++;
						if((i+w) == c.length-1)
						{
							c = saveRange(i,i+w,c,false);
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
		if(arguments.length == 3 && arguments[0].equals("-parse"))
		{	if(arguments[2].startsWith("--prologOut="))
			{
				cspm.parseFile(arguments[1],arguments[2]);
			}
			else
			{
				System.out.println("Incorrect input!");
				System.exit(1);
			}
		}
		else if(arguments.length == 2 && arguments[0].equals("-parse"))
		{	
				cspm.parseFile(arguments[1],"");
		}
		else if((arguments.length == 1) && (arguments[0].equals("-parseAll")))
		{
			File folder = new File(cspm.getPath());
			int fileCounter = cspm.parseFilesInFolder(folder,false);

			if(fileCounter-cspm.getExceptionCounter() == 0)
			{
				System.out.println("\nNo CSPM-File has been parsed successfully!");
			}
			else
			{
				System.out.println("\n"+(fileCounter-cspm.getExceptionCounter())+" of "+fileCounter+" CSPM-Files have been parsed successfully!");
			}
		}
		else
		{
			System.out.println("Incorrect input!");
			System.exit(1);
		}	
	}
}