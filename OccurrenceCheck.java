import java.io.*;
import java.*;
import java.util.*;
import java.util.regex.*;
import java.lang.*;
import java.lang.String.*;
import java.lang.Character;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.charset.Charset;
import java.nio.file.Files;
import CSPMparser.analysis.*;
import CSPMparser.node.*;


public class OccurrenceCheck extends DepthFirstAdapter
{
	private ArrayList<String> left = new ArrayList<String>();
	private ArrayList<String> right = new ArrayList<String>();
	
	public OccurrenceCheck()
	{
		
	}
	//Links vom Gleichzeichen
	@Override
    public void caseANewDefExpression(ANewDefExpression node)
    {
        inANewDefExpression(node);
        if(node.getFuncId() != null)
        {
            node.getFuncId().apply(this);
			String str = node.getFuncId().toString().replaceAll(" ","");
			if(!isFunc(str))
			{
				left.add(str);
			}
			System.out.println(left);
        }
        if(node.getEq() != null)
        {
            node.getEq().apply(this);
        }
        if(node.getProc1() != null)
        {
            node.getProc1().apply(this);
        }
        outANewDefExpression(node);
    }
	
	//Rechte Seite
	@Override
    public void caseAFidAtom(AFidAtom node)
    {
        inAFidAtom(node);
        if(node.getFuncId() != null)
        {
            node.getFuncId().apply(this);
			String str = node.getFuncId().toString().replaceAll(" ","");
			if(isBuiltIn)
			if(isFunc(str))
			{
				right.add(getName(str));
			}
			else
			{
				right.add(str);
			}
			System.out.println(right);
        }
        outAFidAtom(node);
    }
	
	public boolean isFunc(String s)
	{
		if(s.contains("("))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public String getName(String name)
	{
		Pattern pattern = Pattern.compile("([a-zA-Z]+[a-zA-Z0-9_]*[']*)\\(.*\\)");
		Matcher matcher = pattern.matcher(name);
		String output = "";
		while(matcher.find())
		{
			output = matcher.group(1);
		}
		return output;
	}
	
	public boolean isBuiltIn(String s)
	{
			
	}
}