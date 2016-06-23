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

// Renaming Analysis and Unbound Identifier Check

public class OccurrenceCheck extends DepthFirstAdapter
{
	private HashMap<Integer,ArrayList<String>> left = new HashMap<Integer,ArrayList<String>>();
				//letWithinDepth,     id
	private HashMap<Integer,ArrayList<String>> right = new HashMap<Integer,ArrayList<String>>();
					//letWithinDepth,     id
	private ArrayList<String> builtIn = new ArrayList<String>();
	private ArrayList<String> variable = new ArrayList<String>();
	private ArrayList<String> currentLambdaParams = new ArrayList<String>();
	private ArrayList<String> currentParams = new ArrayList<String>();
	private ArrayList<String> pendingId = new ArrayList<String>();
	private ArrayList<String> statementVar = new ArrayList<String>();
	private HashMap<Integer,ArrayList<String>> letWithinArgs = new HashMap<Integer,ArrayList<String>>();
	private int letWithinDepth = 0;
	
	private int currentInStatement = 0;
	private boolean currentLeft = true;
	private boolean currentInTuple = false;
	private boolean currentInParameters = false;
	private boolean currentInLambdaLeft = false;
	private boolean currentInInput = false;
	private boolean currentInSubtype = false;
	private int currentInGenerator = 0;
	private int currentInPredicate = 0;
	
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
		check2();
        outStart(node);
    }
	
//***************************************************************************************
//Typedefinitions
	
	@Override
	public void caseATypedef(ATypedef node)
    {
        inATypedef(node);
        if(node.getId() != null)
        {
            node.getId().apply(this);
			String str = node.getId().toString().replaceAll(" ","");
			if(left.get(letWithinDepth) == null)
			{
				left.put(letWithinDepth,new ArrayList<String>());
			}
			if(!left.get(letWithinDepth).contains(str))
			{
				ArrayList<String> temp = left.get(letWithinDepth);
				temp.add(str);
				left.put(letWithinDepth,temp);
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
			if(left.get(letWithinDepth) == null)
			{
				left.put(letWithinDepth,new ArrayList<String>());
			}
			if(right.get(letWithinDepth) == null)
			{
				right.put(letWithinDepth,new ArrayList<String>());
			}
			if(currentInSubtype && !right.get(letWithinDepth).contains(str))
			{
				ArrayList<String> temp = right.get(letWithinDepth);
				temp.add(str);
				right.put(letWithinDepth,temp);
			}
			else if(!left.get(letWithinDepth).contains(str))
			{
				ArrayList<String> temp = left.get(letWithinDepth);
				temp.add(str);
				left.put(letWithinDepth,temp);
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
			if(left.get(letWithinDepth) == null)
			{
				left.put(letWithinDepth, new ArrayList<String>());
			}
			if(!left.get(letWithinDepth).contains(str))
			{
				ArrayList<String> temp = left.get(letWithinDepth);
				temp.add(str);
				left.put(letWithinDepth,temp);
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
	
    @Override
    public void caseAStypeTypes(AStypeTypes node)
    {
        inAStypeTypes(node);
        if(node.getSType() != null)
        {
            node.getSType().apply(this);
        }
		currentInSubtype = true;
        if(node.getTypedef() != null)
        {
            node.getTypedef().apply(this);
        }
		currentInSubtype = false;
        outAStypeTypes(node);
    }
//***************************************************************************************
//Channels
	
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
			if(left.get(letWithinDepth) == null)
			{
				left.put(letWithinDepth,new ArrayList<String>());
			}
			if(!left.get(letWithinDepth).contains(str))
			{
				ArrayList<String> temp = left.get(letWithinDepth);
				temp.add(str);
				left.put(letWithinDepth,temp);
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
			if(left.get(letWithinDepth) == null)
			{
				left.put(letWithinDepth,new ArrayList<String>());
			}
			if(!left.get(letWithinDepth).contains(str))
			{
				ArrayList<String> temp = left.get(letWithinDepth);
				temp.add(str);
				left.put(letWithinDepth,temp);
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
//***************************************************************************************
//Type Expressions

	@Override
    public void caseASetNameTypeExp2(ASetNameTypeExp2 node)
    {
        inASetNameTypeExp2(node);
        if(node.getId() != null)
        {
            node.getId().apply(this);
			String str = node.getId().toString().replaceAll(" ","");
			if(right.get(letWithinDepth) == null)
			{
				right.put(letWithinDepth,new ArrayList<String>());
			}	
			if(!right.get(letWithinDepth).contains(str))
			{
				ArrayList<String> temp = right.get(letWithinDepth);
				temp.add(str);
				right.put(letWithinDepth,temp);
			}
        }
        if(node.getTuple() != null)
        {
            node.getTuple().apply(this);
        }
        outASetNameTypeExp2(node);
    }
	
//***************************************************************************************	
//Left Side	
	@Override
    public void caseANewDefExpression(ANewDefExpression node)
    {
        inANewDefExpression(node);
		currentParams = new ArrayList<String>();
		pendingId = new ArrayList<String>();
		currentLeft = true;
		
        if(node.getId() != null)
        {
            node.getId().apply(this);		
        }
		String str = node.getId().toString().replaceAll(" ","");
        if(node.getParameters() != null)
        {
			currentInParameters = true;
            node.getParameters().apply(this);
			if(left.get(letWithinDepth) == null)
			{
				left.put(letWithinDepth,new ArrayList<String>());
			}
			if(left.get(letWithinDepth).contains(str))
			{
				throw new RuntimeException("Redefinition of Identifier: "+str+".");
			} 	
			else if(!left.get(letWithinDepth).contains(str+"()"))
			{
				ArrayList<String> temp = left.get(letWithinDepth);
				temp.add(str+"()");
				left.put(letWithinDepth,temp);
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
//***************************************************************************************
//Patterns
    @Override
    public void caseAPatternExpression(APatternExpression node)
    {
        inAPatternExpression(node);
		currentParams = new ArrayList<String>();
		pendingId = new ArrayList<String>();
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
			if(letWithinDepth>0 && letWithinArgs.get(letWithinDepth).contains(str))
			{}	
			else if(currentInParameters)
			{
				currentParams.add(str);
			}
			else if(currentLeft) //Single Identifier without params
			{	
				if(left.get(letWithinDepth) == null)
				{
					left.put(letWithinDepth,new ArrayList<String>());
				}
				if((left.get(letWithinDepth).contains(str) 
					|| left.get(letWithinDepth).contains(str+"()")))
				{							
					throw new RuntimeException("Redefinition of Identifier "+str+".");						
				}
				else
				{		
					ArrayList<String> temp = left.get(letWithinDepth);
					temp.add(str);
					left.put(letWithinDepth,temp);
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
				if(right.get(letWithinDepth) == null)
				{
					right.put(letWithinDepth,new ArrayList<String>());
				}	
				if(!right.get(letWithinDepth).contains(str) && !currentParams.contains(str))
				{
					ArrayList<String> temp = right.get(letWithinDepth);
					temp.add(str);
					right.put(letWithinDepth,temp);
				}
			}
        }
        outAVarPatternAtom(node);
    }
//***************************************************************************************
//Comprehensions	
    @Override
    public void caseAListCompSequence(AListCompSequence node)
    {
        inAListCompSequence(node);
        if(node.getSeqOpen() != null)
        {
            node.getSeqOpen().apply(this);
        }
		currentInStatement += 1;
        if(node.getArguments() != null)
        {
            node.getArguments().apply(this);
        }
		currentInStatement -= 1;
        if(node.getPipe() != null)
        {
            node.getPipe().apply(this);
        }
        if(node.getStmts() != null)
        {
            node.getStmts().apply(this);
        }
        if(node.getSeqClose() != null)
        {
            node.getSeqClose().apply(this);
        }
		//Check pendingId		
		if(!pendingId.isEmpty())
		{
			int i = 0;
			if(right.get(letWithinDepth) == null)
			{
				right.put(letWithinDepth,new ArrayList<String>());
			}	
			while(i<pendingId.size())
			{
				if(!statementVar.contains(pendingId.get(i))
					&& !currentParams.contains(pendingId.get(i)))
				{
					ArrayList<String> temp = right.get(letWithinDepth);
					temp.add(pendingId.get(i));
					right.put(letWithinDepth,temp);				
					pendingId.remove(i);
					i = 0;
				}
				else
				{
					pendingId.remove(i);
					i++;
				}
			}
		}	
		statementVar = new ArrayList<String>();		
        outAListCompSequence(node);
    }
	
	@Override
    public void caseASeqComp(ASeqComp node)
    {
        inASeqComp(node);
        if(node.getTriaL() != null)
        {
            node.getTriaL().apply(this);
        }
        if(node.getL() != null)
        {
            node.getL().apply(this);
        }
		currentInStatement += 1;
        if(node.getArguments() != null)
        {
            node.getArguments().apply(this);
        }
		currentInStatement -= 1;
        if(node.getM() != null)
        {
            node.getM().apply(this);
        }
        if(node.getStmts() != null)
        {
            node.getStmts().apply(this);
        }
        if(node.getR() != null)
        {
            node.getR().apply(this);
        }
        if(node.getTriaR() != null)
        {
            node.getTriaR().apply(this);
        }
		if(!pendingId.isEmpty())
		{
			int i = 0;
			if(right.get(letWithinDepth) == null)
			{
				right.put(letWithinDepth,new ArrayList<String>());
			}	
			while(i<pendingId.size())
			{
				if(!statementVar.contains(pendingId.get(i))
					&& !currentParams.contains(pendingId.get(i)))
				{
					ArrayList<String> temp = right.get(letWithinDepth);
					temp.add(pendingId.get(i));
					right.put(letWithinDepth,temp);	
					pendingId.remove(i);
					i = 0;
				}
				else
				{
					pendingId.remove(i);
					i++;
				}
			}
		}
		statementVar = new ArrayList<String>();
        outASeqComp(node);
    }
	
	
	@Override
    public void caseASetComprehensionSet(ASetComprehensionSet node)
    {
        inASetComprehensionSet(node);
        if(node.getBraceL() != null)
        {
            node.getBraceL().apply(this);
        }
		currentInStatement += 1;
        if(node.getArguments() != null)
        {
            node.getArguments().apply(this);
        }
		currentInStatement -= 1;
        if(node.getPipe() != null)
        {
            node.getPipe().apply(this);
        }
        if(node.getStmts() != null)
        {
            node.getStmts().apply(this);
        }
        if(node.getBraceR() != null)
        {
            node.getBraceR().apply(this);
        }
		//Check pendingId		
		if(!pendingId.isEmpty())
		{
			int i = 0;
			if(right.get(letWithinDepth) == null)
			{
				right.put(letWithinDepth,new ArrayList<String>());
			}	
			while(i<pendingId.size())
			{
				if(!statementVar.contains(pendingId.get(i))
					&& !currentParams.contains(pendingId.get(i)))
				{
					ArrayList<String> temp = right.get(letWithinDepth);
					temp.add(pendingId.get(i));
					right.put(letWithinDepth,temp);	
					pendingId.remove(i);
					i = 0;
				}
				else
				{
					pendingId.remove(i);
					i++;
				}
			}
		}	
		statementVar = new ArrayList<String>();
        outASetComprehensionSet(node);
    }
	
    @Override
    public void caseASetComp(ASetComp node)
    {
        inASetComp(node);
        if(node.getBraceL() != null)
        {
            node.getBraceL().apply(this);
        }
        if(node.getL() != null)
        {
            node.getL().apply(this);
        }
		currentInStatement +=1;
        if(node.getArguments() != null)
        {
            node.getArguments().apply(this);
        }
		currentInStatement -=1;
        if(node.getM() != null)
        {
            node.getM().apply(this);
        }
        if(node.getStmts() != null)
        {
            node.getStmts().apply(this);
        }
        if(node.getR() != null)
        {
            node.getR().apply(this);
        }
        if(node.getBraceR() != null)
        {
            node.getBraceR().apply(this);
        }
		if(!pendingId.isEmpty())
		{
			int i = 0;
			if(right.get(letWithinDepth) == null)
			{
				right.put(letWithinDepth,new ArrayList<String>());
			}	
			while(i<pendingId.size())
			{
				if(!statementVar.contains(pendingId.get(i))
					&& !currentParams.contains(pendingId.get(i)))
				{
					ArrayList<String> temp = right.get(letWithinDepth);
					temp.add(pendingId.get(i));
					right.put(letWithinDepth,temp);	
					pendingId.remove(i);
					i = 0;
				}
				else
				{
					pendingId.remove(i);
					i++;
				}
			}
		}
		statementVar = new ArrayList<String>();
        outASetComp(node);
    }
		
    @Override
    public void caseALinkComp(ALinkComp node)
    {
        inALinkComp(node);
		currentInStatement += 1;
        if(node.getLe() != null)
        {
            node.getLe().apply(this);
        }
        if(node.getDArr() != null)
        {
            node.getDArr().apply(this);
        }
        if(node.getRe() != null)
        {
            node.getRe().apply(this);
        }
        {
            List<PLinkCompRek> copy = new ArrayList<PLinkCompRek>(node.getLinkCompRek());
            for(PLinkCompRek e : copy)
            {
                e.apply(this);
            }
        }
		currentInStatement -= 1;
        if(node.getAddStmts() != null)
        {
            node.getAddStmts().apply(this);
        }
	//	System.out.println("sV "+statementVar);
	//	System.out.println("pid "+pendingId);
		if(!pendingId.isEmpty())
		{
			int i = 0;
			if(right.get(letWithinDepth) == null)
			{
				right.put(letWithinDepth,new ArrayList<String>());
			}	
			while(i<pendingId.size())
			{
				if(!statementVar.contains(pendingId.get(i))
					&& !currentParams.contains(pendingId.get(i)))
				{
					ArrayList<String> temp = right.get(letWithinDepth);
					temp.add(pendingId.get(i));
					right.put(letWithinDepth,temp);	
					pendingId.remove(i);
					i = 0;
				}
				else
				{
					pendingId.remove(i);
					i++;
				}
			}
		}
		statementVar = new ArrayList<String>();
        outALinkComp(node);
    }
	
	@Override
    public void caseARenameComp(ARenameComp node)
    {
        inARenameComp(node);
		currentInStatement += 1;
        if(node.getLe() != null)
        {
            node.getLe().apply(this);
        }
        if(node.getArrowL() != null)
        {
            node.getArrowL().apply(this);
        }
        if(node.getRe() != null)
        {
            node.getRe().apply(this);
        }
        {
            List<PRenameCompRek> copy = new ArrayList<PRenameCompRek>(node.getRenameCompRek());
            for(PRenameCompRek e : copy)
            {
                e.apply(this);
            }
        }
		currentInStatement -= 1;
        if(node.getAddStmts() != null)
        {
            node.getAddStmts().apply(this);
        }
		if(!pendingId.isEmpty())
		{
			int i = 0;
			if(right.get(letWithinDepth) == null)
			{
				right.put(letWithinDepth,new ArrayList<String>());
			}	
			while(i<pendingId.size())
			{
				if(!statementVar.contains(pendingId.get(i))
					&& !currentParams.contains(pendingId.get(i)))
				{
					ArrayList<String> temp = right.get(letWithinDepth);
					temp.add(pendingId.get(i));
					right.put(letWithinDepth,temp);	
					pendingId.remove(i);
					i = 0;
				}
				else
				{
					pendingId.remove(i);
					i++;
				}
			}
		}
		statementVar = new ArrayList<String>();
        outARenameComp(node);
    }

//***************************************************************************************
//Generator Statements	
	@Override
    public void caseAGenerator(AGenerator node)
    {
        inAGenerator(node);
		currentInGenerator += 1;
        if(node.getDpattern() != null)
        {
            node.getDpattern().apply(this);
        }
		currentInGenerator -= 1;
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
    public void caseAGenStmtsRek(AGenStmtsRek node)
    {
        inAGenStmtsRek(node);
        if(node.getComma() != null)
        {
            node.getComma().apply(this);
        }
		currentInGenerator += 1;
        if(node.getGenerator() != null)
        {
            node.getGenerator().apply(this);
        }
		currentInGenerator -= 1;
        outAGenStmtsRek(node);
    }
//***************************************************************************************	
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
			if(left.get(letWithinDepth) == null)
			{
				left.put(letWithinDepth,new ArrayList<String>());
			}
			if(!left.get(letWithinDepth).contains(str))
			{
				ArrayList<String> temp = left.get(letWithinDepth);
				temp.add(str);
				left.put(letWithinDepth,temp);
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
			if(left.get(letWithinDepth) == null)
			{
				left.put(letWithinDepth,new ArrayList<String>());
			}
			if(!left.get(letWithinDepth).contains(str))
			{
				ArrayList<String> temp = left.get(letWithinDepth);
				temp.add(str);
				left.put(letWithinDepth,temp);
			}
			else
			{
				throw new RuntimeException("Redefinition of Identifier "+str+".");
			}
        }
        outAExtDef(node);
    }
	
	@Override
    public void caseALetWithinProc9(ALetWithinProc9 node)
    {
        inALetWithinProc9(node);
		letWithinDepth++;
		ArrayList<String> newlist = new ArrayList<String>();
		for(int i = 1;i<letWithinDepth;i++)
		{	
				ArrayList<String> sublist = letWithinArgs.get(i);	
				for(int j = 0;j<sublist.size();j++)
				{
					if(!newlist.contains(sublist.get(j)))
					{
						newlist.add(sublist.get(j));
					}
				}		
		}
		for(int k = 0;k<currentParams.size();k++)
		{
			if(!newlist.contains(currentParams.get(k)))
			{
				newlist.add(currentParams.get(k));
			}
		}
		letWithinArgs.put(letWithinDepth,newlist);	
	//	System.out.println("letWithinArgs: "+letWithinArgs);
        if(node.getLet() != null)
        {
            node.getLet().apply(this);
        }	
        if(node.getDefs() != null)
        {
            node.getDefs().apply(this);
        }
        if(node.getWithin() != null)
        {
            node.getWithin().apply(this);
        }				
        if(node.getProc9() != null)
        {
            node.getProc9().apply(this);
        }	
		check(letWithinDepth);
		left.remove(letWithinDepth);
		right.remove(letWithinDepth);
		letWithinDepth -= 1;		
        outALetWithinProc9(node);
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
			if(left.get(letWithinDepth) == null)
			{
				left.put(letWithinDepth,new ArrayList<String>());
			}	
			if(right.get(letWithinDepth) == null)
			{
				right.put(letWithinDepth,new ArrayList<String>());
			}			
			if(letWithinDepth>0 && letWithinArgs.get(letWithinDepth).contains(str))
			{		
			}
			else if(currentInStatement>0 
					&& !left.get(letWithinDepth).contains(str))
			{
				if(!pendingId.contains(str))
				pendingId.add(str); //for statements {x|x<-{1,2,3}},x is pending id
			}
			else if(currentInGenerator>0 
					&& !currentParams.contains(str)
					&& !statementVar.contains(str))
			{
				statementVar.add(str);
			}
			else if(!builtIn.contains(str)
				 && !right.get(letWithinDepth).contains(str) 
			   	 && !currentParams.contains(str)
				 && !currentLambdaParams.contains(str)
				 && !statementVar.contains(str))
			{
				ArrayList<String> temp = right.get(letWithinDepth);
				temp.add(str);
				right.put(letWithinDepth,temp);
			}
        }
        if(node.getTuple() != null)
        {
            node.getTuple().apply(this);
        }
        outAIdAtom(node);
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
		builtIn.add("elem");
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
		builtIn.add("true");
		builtIn.add("false");
	}
 
 
	public void check2()//check at the end of AST
	{
	//	System.out.println("left: "+left);
	//	System.out.println("right: "+right);
		if(left.get(0) != null && right.get(0) != null)
		{
			for(int i = 0;i<right.get(0).size();i++)
			{
				if(!left.get(0).contains(right.get(0).get(i))
				&& !left.get(0).contains(right.get(0).get(i)+"()")
				&& !builtIn.contains(right.get(0).get(i)))
				{
					throw new RuntimeException("Unbound Identifier: "+right.get(0).get(i));
				}
			}
		}
	}
	
	public void check(int depth) //check until left >= depth after within
	{
	//	System.out.println("left: "+left);
	//	System.out.println("right: "+right);
		ArrayList<String> l = new ArrayList<String>(); //0-depth
		ArrayList<String> r = right.get(depth); //depth only
		
		for(int i = 0; i<left.size();i++)
		{
			for(int j = 0; j<left.get(i).size();j++)
			{
				if(!l.contains(left.get(i).get(j)))
				{
					l.add(left.get(i).get(j));
				}
			}
		}
	//	System.out.println("l: "+l);
	//	System.out.println("r: "+r);
		//Check right in left, if not, put element 1 depth higher in right
		ArrayList<String> temp = new ArrayList<String>();
		if(depth>0)
		{
			if(right.get(depth-1) != null)
			{
				temp = right.get(depth-1);	
			}
			for(int k = 0;k<r.size();k++)
			{
				if(!l.contains(r.get(k)) 
					&& !l.contains(r.get(k)+"()")
					&& !builtIn.contains(r.get(k)))
				{
					temp.add(r.get(k));
				}
			}
			right.put(depth-1,temp);
		}
	}
	
}