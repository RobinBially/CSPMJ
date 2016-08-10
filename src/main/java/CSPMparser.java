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
	private OsCheck.OSType ostype;
	private String versionNum;
	private String versionString;
	private String cspCode;
	private String currentFile;
	private int exceptionCounter;
	private ArrayList<CommentInfo> commentList;
	private String newline;


	public CSPMparser()
	{
		setVersion("0 70 160810");
		exceptionCounter = 0;
		commentList = new ArrayList<CommentInfo>();
		ostype = OsCheck.getOperatingSystemType();
		if(ostype == OsCheck.OSType.Windows) //Set the platform specific newline character
			newline = "\r\n";
		else
			newline = "\n";
	}
	
	public CSPMparser(String inputFile, String outputFile) // This is for PerfocemanceTests
	{
		setVersion("0 00 000000");
		exceptionCounter = 0;
		commentList = new ArrayList<CommentInfo>();
		newline = "\r\n";
		parseFile(inputFile, outputFile);
	}	
	
	public CSPMparser(String newline) //This is for PrologGeneratorTests
	{
		setVersion("");
		exceptionCounter = 0;
		commentList = new ArrayList<CommentInfo>();
		this.newline = newline;
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


	public int parseFilesInFolder(File folder)
	{
		int fileCounter = 0;
		for (File fileEntry : folder.listFiles()) 
		{		
			if (fileEntry.isDirectory())
			{
				fileCounter += parseFilesInFolder(fileEntry);
			} 
			else if(getExtension(fileEntry.toString()).equals("csp"))
			{
				fileCounter++;			
				try 
				{
					String s = getStringFromFile(fileEntry.toString());
					parsingRoutine(s,true,true,true,fileEntry.toString(),""); 
				} 	
				catch(Exception e) 
				{
					System.out.println(fileEntry.toString()+" has not been parsed successfully!");
					exceptionCounter++;
				}
			}
			commentList.clear();
		}
		return fileCounter;
	}

	
	public String parseString(String s, boolean renamingActivated) throws CSPMparserException 
	{
		String ret = "";
		try
		{
			ret = parsingRoutine(s,false,false,renamingActivated,"","");
			while(ret.endsWith("\n") || ret.endsWith("\r"))
			{
				ret = ret.substring(0,ret.length()-1);
			}
		}
		catch(CSPMparserException cspme) 
		{
			throw cspme;
		}
		catch(Exception e) 
		{}	
		return ret;
	}


	public void parseFile(String inputFile, String outputFile)
	{
		try 
		{			
			String s = getStringFromFile(inputFile); //throws fnf
			parsingRoutine(s,true,true,true,inputFile,outputFile);
		} 	
		catch(FileNotFoundException fnf) 
		{
			if(outputFile.equals(""))
			outputFile = inputFile;
			else
			outputFile = outputFile.substring(12,outputFile.length()); // --prologOut= must disappear	
			try
			{
				PrintWriter pw = new PrintWriter(outputFile+".pl", "UTF-8");
				pw.print(":- dynamic parserVersionNum/1, parserVersionStr/1, parseResult/5."
				+newline+":- dynamic module/4."
				+newline+"'parserVersionStr'('"+versionString+"')."
				+newline+"'parseResult'('fileNotFoundException','"+outputFile+": openFile: does not exist (No such file or directory)',0,0).");
				pw.close();
			}
			catch(Exception pwe){}		
		}
		catch(Exception e){}
	}

	
	public String parsingRoutine(String cspCode, boolean createPrologFile, boolean printSrc, boolean renamingActivated,
	String inputFile, String outputFile) throws CSPMparserException,RenamingException,UnboundIdentifierException,NoPatternException,
	TriangleSubstitutionException,IncludeFileException,LexerException,IOException,TreeLogicException
	{
		if(outputFile.equals(""))
		outputFile = inputFile;
		else if(outputFile.startsWith("--prologOut="))
		outputFile = outputFile.substring(12,outputFile.length()); // --prologOut= must disappear


		try 
		{	
			String old;
			
			do //include files and save comments until there are no more changes
			{
				old = cspCode;
				cspCode = saveComments(cspCode);
				cspCode = includeFile(cspCode);
			}			
			while(!cspCode.equals(old));

			TriangleBracketSubstitution tbs = new TriangleBracketSubstitution(cspCode);
			if(!cspCode.equals(""))
			cspCode = tbs.findTriangles();

			StringReader sr = new StringReader(cspCode);
			BufferedReader br = new BufferedReader(sr); 
			
			Lexer l = new LexHelper(new PushbackReader(br,100000));
			
			Parser p = new Parser(l);
			Start tree = p.parse();	
			
			StatementPatternCheck spc = new StatementPatternCheck();
			tree.apply(spc);
			
			TreeLogicChecker tlc = new TreeLogicChecker();
			tree.apply(tlc);
			
			PrologTermOutput pto = new PrologTermOutput(newline);
			SymbolCollector sc = new SymbolCollector();
			tree.apply(sc);
			ArrayList<SymInfo> symbols = sc.getSymbols();
			PrologGenerator pout = new PrologGenerator(pto,symbols,printSrc,renamingActivated,commentList,newline);
			tree.apply(pout);
			
			commentList.clear(); //Reset comment list for next file if -parseAll was called
			
			if(createPrologFile)
			createPrologFile(pto,outputFile);
			else
			return pto.getStringWriter().toString();


		} 
		catch (ParserException e) 
		{
			System.out.println(e.getMessage());
			System.out.println("A ParserException was thrown.");
			if(createPrologFile)
			{
				try
				{
					CSPMparserException cmpe = new CSPMparserException(e.getToken(),e.getLocalizedMessage());
					PrintWriter pw = new PrintWriter(outputFile+".pl", "UTF-8");
					pw.print(":- dynamic parserVersionNum/1, parserVersionStr/1, parseResult/5."
					+newline+":- dynamic module/4."
					+newline+"'parserVersionStr'('"+versionString+"')."
					+newline+"'parseResult'('parseError',"+cmpe.getMessage()+","+cmpe.getTokenLine()+","+cmpe.getTokenColumn()+").");
					pw.close();
				}
				catch(Exception pwe){}
			}
			throw new CSPMparserException(e.getToken(),e.getLocalizedMessage());
		} 			
		catch(LexerException le)
		{
			System.out.println("A LexerException was thrown.");
			if(createPrologFile)
			{
				try
				{
					PrintWriter pw = new PrintWriter(outputFile+".pl", "UTF-8");
					pw.print(":- dynamic parserVersionNum/1, parserVersionStr/1, parseResult/5."
					+newline+":- dynamic module/4."
					+newline+"'parserVersionStr'('"+versionString+"')."
					+newline+"'parseResult'('lexingError',"+le.getMessage()+",0,0).");
					pw.close();					
				}
				catch(Exception pwe){}
			}	
			throw le;
		}
		catch(RenamingException re)
		{
			System.out.println("A RenamingException was thrown.");
			if(createPrologFile)
			{
				try
				{
					System.out.println("test "+outputFile);
					PrintWriter pw = new PrintWriter(outputFile+".pl", "UTF-8");				
					pw.print(":- dynamic parserVersionNum/1, parserVersionStr/1, parseResult/5."
					+newline+":- dynamic module/4."
					+newline+"'parserVersionStr'('"+versionString+"')."
					+newline+"'parseResult'('renamingError',"+re.getText()+").");
					pw.close();
				}
				catch(Exception pwe){}
			}
			throw re;
		}
		catch(NoPatternException npe)
		{
			System.out.println("A NoPatternException was thrown.");
			if(createPrologFile)
			{
				try
				{
					PrintWriter pw = new PrintWriter(outputFile+".pl", "UTF-8");
					pw.print(":- dynamic parserVersionNum/1, parserVersionStr/1, parseResult/5."
					+newline+":- dynamic module/4."
					+newline+"'parserVersionStr'('"+versionString+"')."
					+newline+"'parseResult'('noPatternError',"+npe.getText()+").");
					pw.close();
				}
				catch(Exception pwe){}
			}
			throw npe;
		}
		catch(UnboundIdentifierException uie)
		{
			System.out.println("An UnboundIdentifierException was thrown.");
			if(createPrologFile)
			{
				try
				{
					PrintWriter pw = new PrintWriter(outputFile+".pl", "UTF-8");
					pw.print(":- dynamic parserVersionNum/1, parserVersionStr/1, parseResult/5."
					+newline+":- dynamic module/4."
					+newline+"'parserVersionStr'('"+versionString+"')."
					+newline+"'parseResult'("+uie.getText()+").");
					pw.close();
				}
				catch(Exception pwe){}
			}
			throw uie;
		}
		catch(TriangleSubstitutionException tse)
		{
			System.out.println("A TriangleSubstitutionException was thrown.");
			if(createPrologFile)
			{
				try
				{
					PrintWriter pw = new PrintWriter(outputFile+".pl", "UTF-8");
					pw.print(":- dynamic parserVersionNum/1, parserVersionStr/1, parseResult/5."
					+newline+":- dynamic module/4."
					+newline+"'parserVersionStr'('"+versionString+"')."
					+newline+"'parseResult'('substitutionError',"+tse.getMessage()+").");
					pw.close();
				}
				catch(Exception pwe){}
			}
			throw tse;
		}	
		catch(IncludeFileException ife)
		{
			System.out.println("A File was not found.");
			if(createPrologFile)
			{
				try
				{
					PrintWriter pw = new PrintWriter(outputFile+".pl", "UTF-8");
					pw.print(":- dynamic parserVersionNum/1, parserVersionStr/1, parseResult/5."
					+newline+":- dynamic module/4."
					+newline+"'parserVersionStr'('"+versionString+"')."
					+newline+"'parseResult'('includeError',"+ife.getText()+","+ife.getLine()+","+ife.getColumn()+").");
					pw.close();
				}
				catch(Exception pwe){}
			}		
			throw ife;
		}
		catch(TreeLogicException tle)
		{
			System.out.println("Your CTL/LTL-Formula was wrong.");
			if(createPrologFile)
			{
				try
				{
					PrintWriter pw = new PrintWriter(outputFile+".pl", "UTF-8");
					pw.print(":- dynamic parserVersionNum/1, parserVersionStr/1, parseResult/5."
					+newline+":- dynamic module/4."
					+newline+"'parserVersionStr'('"+versionString+"')."
					+newline+"'parseResult'('treeLogicError',"+tle.getText()+").");
					pw.close();
				}
				catch(Exception pwe){}
			}		
			throw tle;
		}
		catch(IOException io)// tree = p.parse(); throws this
		{
			System.out.println("An IOException was thrown.");
			if(createPrologFile)
			{
				try
				{
					PrintWriter pw = new PrintWriter(outputFile+".pl", "UTF-8");
					pw.print(":- dynamic parserVersionNum/1, parserVersionStr/1, parseResult/5."
					+newline+":- dynamic module/4."
					+newline+"'parserVersionStr'('"+versionString+"')."
					+newline+"'parseResult'('ioException','"+io.getMessage()+"',0,0).");
					pw.close();
				}
				catch(Exception pwe){}
			}		
			throw io;
		}
		return "";
	}
	
	public void createPrologFile(PrologTermOutput pto, String outputFile)
	{
		try
		{
			PrintWriter writer;
			writer = new PrintWriter(outputFile+".pl", "UTF-8");
			
			writer.println(":- dynamic parserVersionNum/1, parserVersionStr/1, parseResult/5."
			+newline+":- dynamic module/4."
			+newline+"'parserVersionStr'('"+versionString+"')."
			+newline+"'parseResult'('ok','',0,0)."
			+newline+":- dynamic channel/2, bindval/3, agent/3."
			+newline+":- dynamic agent_curry/3, symbol/4."
			+newline+":- dynamic dataTypeDef/2, subTypeDef/2, nameType/2."
			+newline+":- dynamic cspTransparent/1."
			+newline+":- dynamic cspPrint/1."
			+newline+":- dynamic pragma/1."
			+newline+":- dynamic comment/2."
			+newline+":- dynamic assertBool/1, assertRef/5, assertTauPrio/6."
			+newline+":- dynamic assertModelCheckExt/4, assertModelCheck/3."
			+newline+":- dynamic assertHasTrace/3, assertHasTraceExt/4"
			+newline+":- dynamic assertLtl/4, assertCtl/4."
			+newline+"'parserVersionNum'(["+versionNum+"])."
			+newline+"'parserVersionStr'('"+versionString+"').");	
			
			String str = pto.getStringWriter().toString();

			while(str.endsWith("\n") || str.endsWith("\r"))
			{
				str = str.substring(0,str.length()-1);
			}
			writer.print(str);			
			writer.close();			
		}
		catch(Exception ex)
		{
			throw new RuntimeException(ex.getMessage());
		}
	}

	public String getStringFromFile(String fp) throws FileNotFoundException
	{	
		try
		{
			byte[] encoded = Files.readAllBytes(Paths.get(fp));
			String file_content = new String(encoded, StandardCharsets.UTF_8);
			return file_content;
		}
		catch(IOException io)
		{
			throw new FileNotFoundException();
		}
	}
	
	public int getLineFromPos(int pos, char[] c)
	{
		int lineCount = 1;
		int i = 0;
		while(i<pos)
		{
			if((c[i] == '\r') && (c[i+1] == '\n'))
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
			i++;
		}
		return lineCount;
	}
	
	public int getColumnFromPos(int pos, char[] c)
	{
		int columnCount = 1;
		int i = 0;
		while(i<pos)
		{
			if(c[i] == '\r' && c[i+1] == '\n')
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
				columnCount++;
			}
			i++;
		}
		return columnCount;	
	}
	//Deletes content in range l-r in Chararray and saves it in commentList
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
	
	public String includeFile(String incl) throws IncludeFileException
	{
		Pattern pattern = Pattern.compile("include \"(.*)\"");
		Matcher matcher = pattern.matcher(incl);
		ArrayList<String> al = new ArrayList<String>();
		StringBuffer sb = new StringBuffer();
		String path = "";
		String str = "";
		while(matcher.find())
		{	
			path = matcher.group(1);
			try
			{
				str = getStringFromFile(path);
			}
			catch(FileNotFoundException fnf)
			{
				char[] c = incl.toCharArray(); 
				throw new IncludeFileException(path, getLineFromPos(matcher.start(),c), getColumnFromPos(matcher.start(),c));
			}
			matcher.appendReplacement(sb,matcher.quoteReplacement(str));
		}
		matcher.appendTail(sb);
		return sb.toString();
	}
	
	public void setVersion(String s)
	{
		String[] subnum = s.split(" ");
		versionNum = "";
		versionString = "CSPMJ V";
		for(int i = 0;i<subnum.length;i++)
		{
			if(i<subnum.length-1)
			versionNum += subnum[i]+",";
			else
			versionNum += subnum[i];
		}
		for(int j = 0;j<subnum.length;j++)
		{
			if(j<subnum.length-1)
			versionString += subnum[j]+".";
			else
			versionString += subnum[j];
		}
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
			int fileCounter = cspm.parseFilesInFolder(folder);

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