import java.io.*;
import java.*;
import java.util.*;
import java.lang.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.charset.Charset;
import java.nio.file.Files;
import CSPMparser.parser.*;
import CSPMparser.lexer.*;
import CSPMparser.node.*;


public class CSPMparser
{

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


public int parseFilesInFolder(File folder, Boolean help)
{
	int i = 0;
	for (File fileEntry : folder.listFiles()) 
	{
		
		if (fileEntry.isDirectory())
		{
			i += parseFilesInFolder(fileEntry,help);
		} 
		else 
		{
			String ext = getExtension(fileEntry.toString());				
			if(ext.equals("csp"))
			{
				if(help)
				System.out.println("Analysiere Syntax für "+fileEntry.getName()+":");
				else
				System.out.println("Analysiere Syntax für "+fileEntry.getName()+"...");
				try 
				{
					StreamEdit se = new StreamEdit(fileEntry.toString(),help);
					String newstream = se.editTokens();
					StringReader sr = new StringReader(newstream);
					BufferedReader br = new BufferedReader(sr); 
					Lexer l = new Lexer(new PushbackReader(br,20000));
					Parser p = new Parser(l);
					Start tree = p.parse();
					if(help)
					System.out.println("\nParsing für "+fileEntry.getName()+" erfolgreich.\n");
					else
					System.out.println("Parsing für "+fileEntry.getName()+" erfolgreich.\n");
					i++;
				} 	
				catch (Exception e) 
				{
					throw new RuntimeException("\n"+e.getMessage());
				}
			}
		}
	}
	return i;
}

		
public void parseFile(String s, Boolean help)
{
	System.out.println("Analysiere Syntax:");
	try 
	{							
		StreamEdit se = new StreamEdit(s, help);
		String newstream = se.editTokens();
		StringReader sr = new StringReader(newstream);
		BufferedReader br = new BufferedReader(sr); 
		Lexer l = new Lexer(new PushbackReader(br,16384));
		Parser p = new Parser(l);
		Start tree = p.parse();
		System.out.println("\nIhr CSP_M-Code konnte erfolgreich geparst werden.");

	} 	
	catch (Exception e) 
	{
		throw new RuntimeException("\n"+e.getMessage());
	}		
}

public static void main(String arguments[]) 
{		
	CSPMparser cspm = new CSPMparser();
	if(arguments.length == 3)
	{
		if((arguments[0].toString().equals("-parse") 
			&& arguments[1].toString().equals("-h"))
			||(arguments[1].toString().equals("-parse") 
			&& arguments[0].toString().equals("-h")))
		{
			cspm.parseFile(arguments[2],true);
		}
	}
	else if(arguments.length == 2)
	{	
		if(arguments[0].toString().equals("-parse") 
			&& !(arguments[1].toString().equals("-h")) )
		{
			cspm.parseFile(arguments[1],false);
		}
		if((arguments[0].toString().equals("-parseAll") 
			&& arguments[1].toString().equals("-h"))
			||(arguments[1].toString().equals("-parseAll") 
			&& arguments[0].toString().equals("-h")))
		{
			Boolean help = true;
			File folder = new File(cspm.getPath());
			int k = cspm.parseFilesInFolder(folder,true);
			if(k == 1)
			{
				System.out.println("\nDie CSPM-Datei wurde erfolgreich geparst!");
			}
			else if(k==2)
			{
				System.out.println("\nDie beiden CSPM-Dateien wurden erfolgreich geparst!");
			}
			else
			{
				System.out.println("\nAlle "+k+" CSPM-Dateien wurden erfolgreich geparst!");
			}
		}
	}
	else if((arguments.length == 1) && (arguments[0].toString().equals("-parseAll")))
	{
		File folder = new File(cspm.getPath());
		int k = cspm.parseFilesInFolder(folder,false);
		if(k == 1)
		{
			System.out.println("\nDie CSPM-Datei wurde erfolgreich geparst!");
		}
		else if(k==2)
		{
			System.out.println("\nDie beiden CSPM-Dateien wurden erfolgreich geparst!");
		}
		else
		{
			System.out.println("\nAlle "+k+" CSPM-Dateien wurden erfolgreich geparst!");
		}
	}
	else
	{
		System.out.println("Eingabe ungueltig!");
		System.exit(1);
	}	
}

}