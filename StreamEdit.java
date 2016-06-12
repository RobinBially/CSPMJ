import java.io.*;
import java.*;
import java.util.*;
import java.lang.*;
import java.util.regex.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

public class StreamEdit
{
	public String s;

	public StreamEdit(String tokenstream)
	{
		s = tokenstream;
	}
		
	public String editTokens()
	{
		String newstream = deleteNewlines();
		return newstream;
	}

	public String deleteNewlines()
	{
		String ts = getStringFromFile(s);		
	
		//Delete comments
		ts = deleteComments(ts);
		//Delete Tabs	
		ts = ts.replaceAll("\t","");

		//Löschen von überflüssigem whitespace vor und hinter newlines
		String old = "";
		while(true)
		{	
			old = ts;
			ts = ts.replace("\r\n ", "\r\n");	
			ts = ts.replace("\r ", "\r");	
			ts = ts.replace("\n ", "\n");				
			if(old.equals(ts))
			{ break; }
		}
		

		while(true) 						
		{
			old = ts;
			ts = ts.replace(" \r\n", "\r\n");
			ts = ts.replace(" \r", "\r");
			ts = ts.replace(" \n", "\n");
			if(old.equals(ts))
			{ break; }	
		}
				
		//Löschen von doppelten Newline-Zeichen
		while(true)
		{
			old = ts;
			ts = ts.replaceAll("\n\r","\r\n");
			ts = ts.replaceAll("\r\n\r\n","\r\n");
			ts = ts.replaceAll("\r\r","\r");
			ts = ts.replaceAll("\n\n","\n");
			
			if(old.equals(ts))
			{ break; }	
		}

				
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
			
			ts = ts.replace(crlf,""+op_before.get(i));
			ts = ts.replace(cr,""+op_before.get(i));
			ts = ts.replace(lf,""+op_before.get(i));
		}
				
		//Löschen von newlines hinter Operatoren und Zeichen
		ArrayList<String> op_behind = new ArrayList<String>();
		op_behind.add("(");
		op_behind.add("(|");
		op_behind.add("<");
		op_behind.add("{");
		op_behind.add("[[");
		op_behind.add(":");
		op_behind.add(",");
		op_behind.add("=");
		op_behind.add("@");
	
		for(int i = 0; i<op_behind.size();i++)
		{
			String crlf = op_behind.get(i)+"\r\n";
			String cr = op_behind.get(i)+"\r";
			String lf = op_behind.get(i)+"\n";
			
			ts = ts.replace(crlf,op_behind.get(i)+"");
			ts = ts.replace(cr,op_behind.get(i)+"");
			ts = ts.replace(lf,op_behind.get(i)+"");
		}
		
		//Löschen von newlines hinter und vor allen unären Operatoren
		ArrayList<String> binop = new ArrayList<String>();	
		binop.add("|");
		binop.add("||");
		binop.add("|]");
		binop.add("[|");
		binop.add("[");
		binop.add("<=");
		binop.add(">=");
		binop.add("==");
		binop.add("!=");
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

			ts = ts.replace(crlf_before,""+binop.get(i));
			ts = ts.replace(cr_before,""+binop.get(i));
			ts = ts.replace(lf_before,""+binop.get(i));
			ts = ts.replace(crlf_behind,binop.get(i)+"");
			ts = ts.replace(cr_behind,binop.get(i)+"");
			ts = ts.replace(lf_behind,binop.get(i)+"");
		}

		return ts;		
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
			throw new RuntimeException("\n"+e.getMessage());
		}	
		
	}
}