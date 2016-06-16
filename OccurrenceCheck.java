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
	private ArrayList<String> builtIn = new ArrayList<String>();
	private ArrayList<String> variable = new ArrayList<String>();
	private boolean currentLeft = true;
	private boolean currentInTuple = true;
	
	public OccurrenceCheck()
	{
		createbuiltIn();
	}
	
	@Override
    public void caseStart(Start node)
    {
        inStart(node);
        node.getPDefs().apply(this);
        node.getEOF().apply(this);
		check();
        outStart(node);
    }
//***************************************************************************************	
//Left Side

	@Override
    public void caseANewDefExpression(ANewDefExpression node)
    {
        inANewDefExpression(node);
		
		currentLeft = true;
		
        if(node.getFuncId() != null)
        {
            node.getFuncId().apply(this);
			String str = node.getFuncId().toString().replaceAll(" ","");
			if(!isFunc(str))
			{
				left.add(str);
			}
        }
        if(node.getEq() != null)
        {
            node.getEq().apply(this);
        }
		
		currentLeft = false;
		
        if(node.getProc1() != null)
        {
            node.getProc1().apply(this);
        }
        outANewDefExpression(node);
    }
	
	@Override
	public void caseATypedef(ATypedef node)
    {
        inATypedef(node);
        if(node.getId() != null)
        {
            node.getId().apply(this);
			String str = node.getId().toString().replaceAll(" ","");
			if(!left.contains(str))
			{
				left.add(str);
			}
			else
			{
				throw new RuntimeException("Redefinition of Identifier "+str+".");
			}
        }
        if(node.getEq() != null)
        {
            node.getEq().apply(this);
        }
        if(node.getClause() != null)
        {
            node.getClause().apply(this);
        }
        {
            List<PTypedefRek> copy = new ArrayList<PTypedefRek>(node.getTypedefRek());
            for(PTypedefRek e : copy)
            {
                e.apply(this);
            }
        }
        outATypedef(node);
    }
	
	@Override
    public void caseAClause(AClause node)
    {
        inAClause(node);
        if(node.getClauseName() != null)
        {
            node.getClauseName().apply(this);
			String str = node.getClauseName().toString().replaceAll(" ","");
			if(!left.contains(str))
			{
				left.add(str);
			}
			else
			{
				throw new RuntimeException("Redefinition of Identifier "+str+".");
			}
        }
        if(node.getDotted() != null)
        {
            node.getDotted().apply(this);
        }
        outAClause(node);
    }
	
    @Override
    public void caseANtype(ANtype node)
    {
        inANtype(node);
        if(node.getNType() != null)
        {
            node.getNType().apply(this);
        }
        if(node.getId() != null)
        {
            node.getId().apply(this);
			String str = node.getId().toString().replaceAll(" ","");
			if(!left.contains(str))
			{
				left.add(str);
			}
			else
			{
				throw new RuntimeException("Redefinition of Identifier "+str+".");
			}
        }
        if(node.getEq() != null)
        {
            node.getEq().apply(this);
        }
        if(node.getTypeExp() != null)
        {
            node.getTypeExp().apply(this);
        }
        outANtype(node);
    }
	
    public void caseAChan(AChan node)
    {
        inAChan(node);
        if(node.getChannel() != null)
        {
            node.getChannel().apply(this);
        }
        if(node.getId() != null)
        {
            node.getId().apply(this);
			String str = node.getId().toString().replaceAll(" ","");
			if(!left.contains(str))
			{
				left.add(str);
			}
			else
			{
				throw new RuntimeException("Redefinition of Identifier "+str+".");
			}
        }
        if(node.getChanRek() != null)
        {
            node.getChanRek().apply(this);
        }
        outAChan(node);
    }

    @Override
    public void caseARekChanRek(ARekChanRek node)
    {
        inARekChanRek(node);
        if(node.getComma() != null)
        {
            node.getComma().apply(this);
        }
        if(node.getId() != null)
        {
            node.getId().apply(this);
			String str = node.getId().toString().replaceAll(" ","");
			if(!left.contains(str))
			{
				left.add(str);
			}
			else
			{
				throw new RuntimeException("Redefinition of Identifier "+str+".");
			}
        }
        if(node.getChanRek() != null)
        {
            node.getChanRek().apply(this);
        }
        outARekChanRek(node);
    }
	
	@Override
    public void caseATransDef(ATransDef node)
    {
        inATransDef(node);
        if(node.getTransparent() != null)
        {
            node.getTransparent().apply(this);
        }
        if(node.getId() != null)
        {
            node.getId().apply(this);
			String str = node.getId().toString().replaceAll(" ","");
			if(!left.contains(str))
			{
				left.add(str);
			}
			else
			{
				throw new RuntimeException("Redefinition of Identifier "+str+".");
			}
        }
        outATransDef(node);
    }

    @Override
    public void caseAExtDef(AExtDef node)
    {
        inAExtDef(node);
        if(node.getExternal() != null)
        {
            node.getExternal().apply(this);
        }
        if(node.getId() != null)
        {
            node.getId().apply(this);
			String str = node.getId().toString().replaceAll(" ","");
			if(!left.contains(str))
			{
				left.add(str);
			}
			else
			{
				throw new RuntimeException("Redefinition of Identifier "+str+".");
			}
        }
        outAExtDef(node);
    }
	
    @Override
    public void caseATupleTuple(ATupleTuple node)
    {
        inATupleTuple(node);
		
		currentInTuple = true;
		
        if(node.getParL() != null)
        {
            node.getParL().apply(this);
        }
        if(node.getInnerTuple() != null)
        {
            node.getInnerTuple().apply(this);
        }
        if(node.getParR() != null)
        {
            node.getParR().apply(this);
        }
		
		currentInTuple = false;
		
        outATupleTuple(node);
    }
//***************************************************************************************
//Right Side

	@Override
    public void caseAFidAtom(AFidAtom node)
    {
        inAFidAtom(node);
        if(node.getFuncId() != null)
        {
            node.getFuncId().apply(this);
			
			if(currentLeft && currentInTuple) // f(x,y) save temp. variables
			{
				if(isFunc())
				{
					throw new
				}
			}
			else
			{	
				String str = node.getFuncId().toString().replaceAll(" ","");
				if(isFunc(str))
				{
					str = getName(str);
					if(!right.contains(str))
					{
						right.add(str);
					}
				}
				else if(!right.contains(str))
				{
					right.add(str);
				}
			}
			
        }
        outAFidAtom(node);
    }
	
	@Override
    public void caseASetNameTypeExp2(ASetNameTypeExp2 node)
    {
        inASetNameTypeExp2(node);
        if(node.getFuncId() != null)
        {
            node.getFuncId().apply(this);
			String str = node.getFuncId().toString().replaceAll(" ","");
			if(isFunc(str))
			{
				str = getName(str);
				if(!right.contains(str))
				{
					right.add(str);
				}
			}
			else if(!right.contains(str))
			{
				right.add(str);
			}
        }
        outASetNameTypeExp2(node);
    }
//***************************************************************************************	
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
	
	public void createbuiltIn()
	{
		builtIn.add("STOP");
		builtIn.add("SKIP");
		builtIn.add("CHAOS");
		builtIn.add("DIV");
		builtIn.add("WAIT");
		builtIn.add("RUN");
		builtIn.add("member");
		builtIn.add("empty");
		builtIn.add("null");
		builtIn.add("Bool");
		builtIn.add("bool");
		builtIn.add("diff");
		builtIn.add("card");
		builtIn.add("Events");
		builtIn.add("head");
		builtIn.add("tail");
		builtIn.add("concat");
		builtIn.add("union");
		builtIn.add("Union");
		builtIn.add("inter");
		builtIn.add("Inter");
		builtIn.add("set");
		builtIn.add("Set");
		builtIn.add("seq");
		builtIn.add("Seq");	
		builtIn.add("length");
		builtIn.add("Proc");
		builtIn.add("Char");
		builtIn.add("Int");
		builtIn.add("error");
		builtIn.add("show");
		builtIn.add("True");
		builtIn.add("False");
	}
	
	public void check()
	{
		System.out.println("Left: "+left);
		System.out.println("Right: "+right);

		for(int i = 0;i<right.size();i++)
		{
			
			if(!left.contains(right.get(i)) && !builtIn.contains(right.get(i)))
			{
				throw new RuntimeException("Unbound Identifier: "+right.get(i));		
			}
		}
	}
	
}