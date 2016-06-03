import java.io.*;
import java.*;
import java.util.*;
import java.lang.*;
import java.util.regex.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.charset.Charset;
import java.nio.file.Files;

public class StreamEdit
{
	public String s;
	public Boolean help;
	public StreamEdit(String ts, Boolean h)
	{
		s = ts;
		help = h;
	}
	
	
	public String editTokens()
	{
		String newstream = deleteNewlines();
		//newstream = replaceSequentialPars(newstream);
		printNewStream(newstream);
		return newstream;
	}
		

/*	public String replaceSequentialPars(String edit)
	{		
		String teststring = "<5<(<3><<4>><5>),(<5>)>";
	
		teststring = teststring.replaceAll(",\u0020*<",",~<~");
		teststring = teststring.replaceAll(">\u0020*,","~>~,");
		teststring = teststring.replaceAll("=\u0020*<","= ~<~");
		teststring = teststring.replaceAll(">\u0020*(\r|\n)+","~>~\n");
		teststring = teststring.replaceAll(">\u0020*\\)","~>~\\)");
		teststring = teststring.replaceAll("\\(\u0020*<","\\(~<~");
		teststring = teststring.replaceAll("<(^>|^<)*~>~","~<~(^>|^<)*~>~");
		teststring = teststring.replaceAll("<\\u0020*(^>|^<)\\u0020*<","");
	    teststring = teststring.replaceAll("<\\u0020*(^>|^<)\\u0020*<","");
		System.out.println(teststring);
		return teststring;
		
	}*/
	
	
	public void printNewStream(String tokenstream)
	{
		if(help)
		{
			char[] ca = tokenstream.toCharArray();
			int i = 1;
			int j = 0;
			System.out.print("Line "+i+": ");
			while(j< ca.length)
			{
				
				if(ca[j] != '\r' && ca[j] != '\n')
				{				
					System.out.print(ca[j]);
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
	}
	
	
	public String deleteNewlines()
	{
		String tokenstream = getStringFromFile(s);		
		String newStr = tokenstream;
		
		
		//Löschen von Kommentaren
		newStr = deleteComments(newStr);
				
		/*Rekursives Löschen von whitespaces 
		*zwischen/vor/hinter newlines bis keine Änderung mehr auftritt*
		*Außerdem Löschen von doppelten Vorkommen von newlines*/
		Boolean replaceable = tokenstream.contains("\t");
		Boolean replaceable2 = false;
		Boolean replaceable3 = false;
	
		while(replaceable)
		{
			newStr = newStr.replace("\t", "");
			replaceable = newStr.contains("\t");
		}
			
		//Löschen von überflüssigem whitespace vor und hinter newlines
		replaceable = containsLeftNewline(newStr);
		while(replaceable)
		{	
			newStr = newStr.replace("\r\n ", "\r\n");	
			newStr = newStr.replace("\r ", "\r");	
			newStr = newStr.replace("\n ", "\n");				
			replaceable = containsLeftNewline(newStr);
		}
		
		replaceable2 = containsRightNewline(newStr); 
		while(replaceable2) 						
		{
			newStr = newStr.replace(" \r\n", "\r\n");
			newStr = newStr.replace(" \r", "\r");
			newStr = newStr.replace(" \n", "\n");
			replaceable2 = containsRightNewline(newStr);
		}
				
		//Löschen von doppelten Newline-Zeichen
		replaceable3 = containsDoubleNewline(newStr);
		while(replaceable3)
		{
			newStr = newStr.replace("\r\n\r\n","\r\n");
			newStr = newStr.replace("\r\r","\r");
			newStr = newStr.replace("\n\n","\n");
			replaceable3 = containsDoubleNewline(newStr);
		}
		tokenstream = newStr;
				
		//Löschen von newlines vor Operatoren und Zeichen
		ArrayList<String> op_before = new ArrayList<String>();
		op_before.add(")");
		op_before.add(">");
		op_before.add("|)");
		op_before.add("]");
		op_before.add("}");
		op_before.add("]]");
		
		for(int i = 0; i<op_before.size();i++)
		{
			String crlf = "\r\n"+op_before.get(i);
			String cr = "\r"+op_before.get(i);
			String lf = "\n"+op_before.get(i);
			
			tokenstream = tokenstream.replace(crlf," "+op_before.get(i));
			tokenstream = tokenstream.replace(cr," "+op_before.get(i));
			tokenstream = tokenstream.replace(lf," "+op_before.get(i));
		}
				
		//Löschen von newlines hinter Operatoren und Zeichen
		ArrayList<String> op_behind = new ArrayList<String>();
		op_behind.add("(");
		op_behind.add("(|");
		op_behind.add("<");
		op_behind.add("{");
		op_behind.add("[[");
		op_behind.add("let");
		op_behind.add("within");
		op_behind.add(":");
		op_behind.add(",");
		op_behind.add("=");
		op_behind.add("@");
	
		for(int i = 0; i<op_behind.size();i++)
		{
			String crlf = op_behind.get(i)+"\r\n";
			String cr = op_behind.get(i)+"\r";
			String lf = op_behind.get(i)+"\n";
			
			tokenstream = tokenstream.replace(crlf,op_behind.get(i)+" ");
			tokenstream = tokenstream.replace(cr,op_behind.get(i)+" ");
			tokenstream = tokenstream.replace(lf,op_behind.get(i)+" ");
		}

		
		//Löschen von newlines hinter und vor allen unären Operatoren
		ArrayList<String> binop = new ArrayList<String>();	
		binop.add("then");
		binop.add("else");
		binop.add("|");
		binop.add("||");
		binop.add("|]");
		binop.add("[|");
		binop.add("[");
		binop.add("<=");
		binop.add(">=");
		binop.add("==");
		binop.add("!=");
	//	binop.add("and");
	//	binop.add("or");
		binop.add("+");
		binop.add("*");
		binop.add("/");
		binop.add("%");
		binop.add("->");
		binop.add("&");
		binop.add("|||");
		binop.add("[]");
		binop.add("|~|");
		binop.add("/\\");
		binop.add("[>");
		binop.add("^");
		binop.add(";");
		binop.add("\\");
		binop.add(".");
		binop.add("<-");
		binop.add("@@");
		
		
		for(int i = 0; i<binop.size();i++)
		{
			String crlf_before = "\r\n"+binop.get(i);
			String cr_before = "\r"+binop.get(i);
			String lf_before = "\n"+binop.get(i);
			
			String crlf_behind = binop.get(i)+"\r\n";
			String cr_behind = binop.get(i)+"\r";
			String lf_behind = binop.get(i)+"\n";

			tokenstream = tokenstream.replace(crlf_before," "+binop.get(i));
			tokenstream = tokenstream.replace(cr_before," "+binop.get(i));
			tokenstream = tokenstream.replace(lf_before," "+binop.get(i));
			tokenstream = tokenstream.replace(crlf_behind,binop.get(i)+" ");
			tokenstream = tokenstream.replace(cr_behind,binop.get(i)+" ");
			tokenstream = tokenstream.replace(lf_behind,binop.get(i)+" ");
		}

		return tokenstream;		
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
	
	
	public String getStringFromFile(String fp)
	{
		try 
		{	
			byte[] encoded = Files.readAllBytes(Paths.get(fp));
			String file_content = new String(encoded, Charset.defaultCharset());
			return file_content;
		}
		catch(Exception e) 		
		{
			throw new RuntimeException("\n"+e.getMessage());
		}	
		
	}
	
	public Boolean containsLeftNewline(String s)
	{
			if(s.contains("\r\n "))
			{return true;}
			else if(s.contains("\r "))
			{return true;}
			else if(s.contains("\n "))
			{return true;}
			else {return false;}
	}
	
	public Boolean containsRightNewline(String s)
	{
			if(s.contains(" \r\n"))
			{return true;}
			else if(s.contains(" \r"))
			{return true;}
			else if(s.contains(" \n"))
			{return true;}
			else {return false;}
	}
	
	public Boolean containsDoubleNewline(String s)
	{
			if(s.contains("\r\n\r\n"))
			{return true;}
			else if(s.contains("\r\r"))
			{return true;}
			else if(s.contains("\n\n"))
			{return true;}
			else {return false;}
	}
	
	public static void main(String[] args)
	{
		
	}
}