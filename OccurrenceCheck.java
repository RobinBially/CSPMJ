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
	private ArrayList<String> currentLambdaParams = new ArrayList<String>();
	private ArrayList<String> currentParams = new ArrayList<String>();
	private boolean currentLeft = true;
	private boolean currentInTuple = false;
	private boolean currentInParameters = false;
	private boolean currentInLambdaLeft = false;
	private boolean currentInInput = false;
	private boolean currentInStatement = false;
	
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
		currentParams = new ArrayList<String>();
		currentLeft = true;
        if(node.getId() != null)
        {
            node.getId().apply(this);		
        }
		String str = node.getId().toString().replaceAll(" ","");
	//	System.out.println(left);
        if(node.getParameters() != null)
        {
			currentInParameters = true;
            node.getParameters().apply(this);
			if(left.contains(str))
			{
				throw new RuntimeException("Redefinition of Identifier: "+str+".");
			} 	
			else if(!left.contains(str+"()"))
			{
				left.add(str+"()");
			}								//Functions can occur multiple times
			currentInParameters = false;
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
    public void caseAPatternExpression(APatternExpression node)
    {
        inAPatternExpression(node);
		currentParams = new ArrayList<String>();
		currentLeft = true;

        if(node.getPattern1() != null)
        {
            node.getPattern1().apply(this);
        }
		currentLeft = false;
        if(node.getEq() != null)
        {
            node.getEq().apply(this);
        }
        if(node.getProc1() != null)
        {
            node.getProc1().apply(this);
        }
        outAPatternExpression(node);
    }
	
    @Override
    public void caseARestrictedPattern3(ARestrictedPattern3 node)
    {
        inARestrictedPattern3(node);
		if(currentLeft || currentInLambdaLeft)
		{
			throw new RuntimeException("':'- Operator is illegal here!");
		}
        if(node.getPattern4() != null)
        {
            node.getPattern4().apply(this);
        }
        if(node.getDdot() != null)
        {
            node.getDdot().apply(this);
        }
        if(node.getBoolExp() != null)
        {
            node.getBoolExp().apply(this);
        }
        outARestrictedPattern3(node);
    }
	
    @Override
    public void caseAVarPatternAtom(AVarPatternAtom node)
    {
        inAVarPatternAtom(node);
        if(node.getId() != null)
        {
            node.getId().apply(this);
			String str = node.getId().toString().replaceAll(" ","");
		//	System.out.println(left);
			if(currentInParameters)
			{
				currentParams.add(str);
			}
			else if(currentLeft) //Single Identifier without params
			{				
				if(left.contains(str) || left.contains(str+"()"))
				{							
					throw new RuntimeException("Redefinition of Identifier "+str+".");						
				}
				else
				{
					left.add(str);
				}
			}
			else if(currentInLambdaLeft)
			{
				currentLambdaParams.add(str);
			}
			else if(currentInInput)
			{//c?x 
				currentParams.add(str);
			}
			else
			{
				if(!right.contains(str) && !currentParams.contains(str))
				{
					right.add(str);
				}
			}
        }
        outAVarPatternAtom(node);
    }
	
	@Override
    public void caseAGenerator(AGenerator node)
    {
        inAGenerator(node);
		currentInStatement = true;
        if(node.getDpattern() != null)
        {
            node.getDpattern().apply(this);
        }
		currentInStatement = false;
        if(node.getGeneratorOp() != null)
        {
            node.getGeneratorOp().apply(this);
        }
        if(node.getProc1() != null)
        {
            node.getProc1().apply(this);
        }
        outAGenerator(node);
    }
	
    @Override
    public void caseANondetRestField1(ANondetRestField1 node)
    {
        inANondetRestField1(node);
        if(node.getDollar() != null)
        {
            node.getDollar().apply(this);
        }
		currentInInput = true;
        if(node.getPattern1() != null)
        {
            node.getPattern1().apply(this);
        }
		currentInInput = false;
        outANondetRestField1(node);
    }
	
	@Override
    public void caseAInputRestField2(AInputRestField2 node)
    {
        inAInputRestField2(node);
        if(node.getQMark() != null)
        {
            node.getQMark().apply(this);
        }
		currentInInput = true;
        if(node.getPattern1() != null)
        {
            node.getPattern1().apply(this);
        }
		currentInInput = false;
        outAInputRestField2(node);

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
    public void caseALambdaTermProc9(ALambdaTermProc9 node)
    {
        inALambdaTermProc9(node);
        if(node.getBSlash() != null)
        {
            node.getBSlash().apply(this);
        }
        if(node.getPatternList() != null)
        {
			currentInLambdaLeft = true;
            node.getPatternList().apply(this);
			currentInLambdaLeft = false;
        }
        if(node.getAt() != null)
        {
            node.getAt().apply(this);
        }
        if(node.getProc9() != null)
        {
            node.getProc9().apply(this);
        }
		currentLambdaParams = new ArrayList<String>();
        outALambdaTermProc9(node);
    }
	
	@Override
    public void caseAIdAtom(AIdAtom node)
    {
        inAIdAtom(node);
        if(node.getId() != null)
        {
            node.getId().apply(this);
			String str = node.getId().toString().replaceAll(" ","");
		//	System.out.println(currentParams);
		//	System.out.println(currentLambdaParams);
			if(currentInStatement && !currentParams.contains(str))
			{
				currentParams.add(str);
			}
			else if(!right.contains(str) 
				&& !currentParams.contains(str)
				&& !currentLambdaParams.contains(str))
			{
				right.add(str);
			}
        }
        if(node.getTuple() != null)
        {
            node.getTuple().apply(this);
        }
        outAIdAtom(node);
    }
	
    @Override
    public void caseASetNameTypeExp2(ASetNameTypeExp2 node)
    {
        inASetNameTypeExp2(node);
        if(node.getId() != null)
        {
            node.getId().apply(this);
			String str = node.getId().toString().replaceAll(" ","");
			if(!right.contains(str))
			{
				right.add(str);
			}
        }
        if(node.getTuple() != null)
        {
            node.getTuple().apply(this);
        }
        outASetNameTypeExp2(node);
    }
//***************************************************************************************	
	
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
	//	System.out.println("Left: "+left);
	//	System.out.println("Right: "+right);

		for(int i = 0;i<right.size();i++)
		{
			
			if(!left.contains(right.get(i)) 
				&& !left.contains(right.get(i)+"()")
				&& !builtIn.contains(right.get(i)))
			{
				throw new RuntimeException("Unbound Identifier: "+right.get(i));		
			}
		}
	}
	
}