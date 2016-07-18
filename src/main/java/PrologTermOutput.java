
import java.io.*;
import java.util.Arrays;


public class PrologTermOutput
{
	private StringWriter out;

	// comma_needed states if the next term can be printed directly (false) or
	// if a separating comma is needed first
	private boolean comma_needed = false;

	private final boolean use_indention;
	private int indent_level = 0;
	private int ignore_indention_level = 0;

	private int termCount = 0;
	private int listCount = 0;

	// flag to enable printing of terms without arguments as atoms.
	// if set, the last printed object was a functor, and if anything is printed
	// before closing the term, an opening parenthesis should be printed.
	private boolean lazy_parenthesis = false;

	
	public PrologTermOutput()
	{
		out = new StringWriter();
		use_indention = false;
	}
	

	public void openTerm(final String functor)
	{
		openTerm(functor, false);
		
	}


	public void openTerm(final String functor,final boolean ignoreIndention)
	{
		termCount++;
		printAtom(functor);
		lazy_parenthesis = true;
		comma_needed = false;
		indent_level += 2;
		if (ignore_indention_level > 0)
		{
			ignore_indention_level++;
		} else if (ignoreIndention)
		{
			ignore_indention_level = 1;
		}
	}

	private void printIndention()
	{
		if (use_indention && ignore_indention_level == 0)
		{
			out.write('\n');
			for (int i = 0; i < indent_level; i++)
			{
				out.write(' ');
			}			
		}
	}

	public void closeTerm()
	{
		termCount--;
		if (termCount < 0)
		throw new IllegalStateException("Tried to close a term that has not been opened.");
		if (lazy_parenthesis)
		{
			lazy_parenthesis = false;
		} else {
			out.write(')');
		}
		comma_needed = true;
		indent_level -= 2;
		if (ignore_indention_level > 0)
		{
			ignore_indention_level--;
		}

	}


	public void printAtom(final String content)
	{
			printCommaIfNeeded();
			out.write('\''); //@Robin
			out.write(content);
			out.write('\''); //@Robin			
			comma_needed = true;	
	}

	
	public void printAtomOrNumber(final String content)
	{
		try 
		{
			printNumber(Integer.parseInt(content));
		} 
		catch (NumberFormatException e)
		{
			printAtom(content);
		}
		
	}

	public void printString(final String content)
	{
		printCommaIfNeeded();
		out.write('"');
		out.write(content);
		out.write('"');
		comma_needed = true;

	}

	public void printNumber(final long number)
	{
		
		printCommaIfNeeded();
		String temp = String.valueOf(number);
		out.write(temp);
		comma_needed = true;

	}

	public void openList()
	{
		listCount++;
		printCommaIfNeeded();
		out.write('[');
		comma_needed = false;
		indent_level += 1;
	}


	public void closeList()
	{

		listCount--;
		if (listCount < 0)
		throw new IllegalStateException("Tried to close a list that has not been opened.");
		out.write(']');
		comma_needed = true;
		indent_level -= 1;

	}

	public void appendTerm(String s)
	{
		out.write(s);
	}

	public void emptyList()
	{
		printCommaIfNeeded();
		out.write("[]");
		comma_needed = true;
	}


	public void printVariable(final String var)
	{
		printCommaIfNeeded();
		out.write(var);
		comma_needed = true;
	}

	private void checkVariable(final String var)
	{
		boolean ok = var.length() > 0;
		if(ok)
		{
			char c = var.charAt(0);
			ok = c == '_' || Character.isUpperCase(c);
			for (int i = 1; ok && i < var.length(); i++)
			{
				c = var.charAt(i);
				ok &= c == '_' || Character.isLetterOrDigit(c);
			}
		}
		if(!ok)
		throw new IllegalArgumentException("Invalid name for Prolog variable '" + var + "'");
	}


	private void printCommaIfNeeded()
	{
		if (lazy_parenthesis)
		{
			out.write('(');
			lazy_parenthesis = false;
		}
		if (comma_needed)
		{
			out.write(',');
			printIndention();
		}
	}

	public void fullstop() 
	{

		if (listCount != 0)
		{
			throw new IllegalStateException("Number of openList and closeList do not match. openList Counter is "+ listCount);
		}
		if (termCount != 0)
		{
			throw new IllegalStateException("Number of openTerm and closeTerm do not match. openTerm Counter is "+ termCount);
		}
		out.write(".\n");
		comma_needed = false;
	}
	
	public void fullstopEnd() 
	{
		out.write(".");
		comma_needed = false;
	}


	public StringWriter getStringWriter()
	{
		try
		{
			out.close();
		}
		catch(Exception e)
		{
			throw new RuntimeException("Could not close StringWriter.");
		}
		return out;
	}
	
	public void setStringWriter(StringWriter sw)
	{
		out = sw;
	}	 	
	
}
