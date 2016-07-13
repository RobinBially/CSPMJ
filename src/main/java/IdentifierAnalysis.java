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

public class IdentifierAnalysis extends DepthFirstAdapter
{
	private HashMap<Integer,ArrayList<String>> left = new HashMap<Integer,ArrayList<String>>();
				//letWithinDepth,     id
	private HashMap<Integer,ArrayList<String>> right = new HashMap<Integer,ArrayList<String>>();
					//letWithinDepth,     id
	private ArrayList<String> builtIn = new ArrayList<String>();
	private ArrayList<String> currentLambdaParams = new ArrayList<String>();
	private ArrayList<String> currentParams = new ArrayList<String>();
	private ArrayList<String> currentInput = new ArrayList<String>();
	private ArrayList<String> pendingId = new ArrayList<String>();
	private ArrayList<String> statementVar = new ArrayList<String>();
	private HashMap<Integer,ArrayList<String>> letWithinArgs = new HashMap<Integer,ArrayList<String>>();
	private int letWithinDepth = 0;
	private String previousDef = "";
	
	private int currentInComprArgs = 0;
	private boolean currentLeft = true;
	private boolean currentInTuple = false;
	private boolean currentInParameters = false;
	private boolean currentInLambdaLeft = false;
	private boolean currentInInput = false;
	private boolean currentInSubtype = false;
	private int currentInGenerator = 0;
	private int currentInPredicate = 0;
	
	public IdentifierAnalysis()
	{
		createbuiltIn();
	}
	
	@Override
    public void caseStart(Start node)
    {
        inStart(node);
        node.getPStart().apply(this);
        node.getEOF().apply(this);
		check2();
        outStart(node);
    }
	
//***************************************************************************************
//Typedefinitions
    @Override
    public void caseATypedefTypes(ATypedefTypes node)
    {
        inATypedefTypes(node);
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
				throw new RuntimeException("Redefinition of Identifier "+str+" "+getSrcLoc(node.getId())+".");
			}
        }
        {
            List<PTypes> copy = new ArrayList<PTypes>(node.getTypedefList());
            for(PTypes e : copy)
            {
                e.apply(this);
            }
        }
        outATypedefTypes(node);
    }	
	
    @Override
    public void caseAClauseTypes(AClauseTypes node)
    {
        inAClauseTypes(node);
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
				throw new RuntimeException("Redefinition of Identifier "+str+" "+getSrcLoc(node.getClauseName())+".");
			}
        }
        if(node.getDotted() != null)
        {
            node.getDotted().apply(this);
        }
        outAClauseTypes(node);
    }
	
    @Override
    public void caseANtypeTypes(ANtypeTypes node)
    {
        inANtypeTypes(node);
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
				throw new RuntimeException("Redefinition of Identifier "+str+" "+getSrcLoc(node.getId())+".");
			}
        }
        if(node.getTypeExp() != null)
        {
            node.getTypeExp().apply(this);
        }
        outANtypeTypes(node);
    }
	
	@Override
    public void caseAStypeTypes(AStypeTypes node)
    {
        inAStypeTypes(node);
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
    @Override
    public void caseAChannelDef(AChannelDef node)
    {
        inAChannelDef(node);
        {
            List<PId> copy = new ArrayList<PId>(node.getChanList());
            for(PId e : copy)
            {
                e.apply(this);
				String str = e.toString().replaceAll(" ","");
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
					throw new RuntimeException("Redefinition of Identifier "+str+" "+getSrcLoc(e)+".");
				}
            }
        }
        if(node.getChanType() != null)
        {
            node.getChanType().apply(this);
        }
        outAChannelDef(node);
    }	

//***************************************************************************************
//Type Expressions
    @Override
    public void caseAIdTypeExp(AIdTypeExp node)
    {
        inAIdTypeExp(node);
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
        if(node.getLambda() != null)
        {
            node.getLambda().apply(this);
        }
        outAIdTypeExp(node);
    }

//***************************************************************************************	
//Expression Definitions 

    @Override
    public void caseAFunctionExp(AFunctionExp node)
    {
        inAFunctionExp(node);
		currentParams.clear();
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
				throw new RuntimeException("Redefinition of Identifier: "+str+" "+getSrcLoc(node.getId())+".");
			}
			if(left.get(letWithinDepth).contains(str+"()") && !(str+"()").equals(previousDef))
			{
				throw new RuntimeException("Redefinition of Identifier: "+str+" "+getSrcLoc(node.getId())+".");
			} 			
			else if(!left.get(letWithinDepth).contains(str+"()"))
			{
				ArrayList<String> temp = left.get(letWithinDepth);
				temp.add(str+"()");
				left.put(letWithinDepth,temp);
			}								//Functions can occur multiple times
			currentInParameters = false;
        }
		
		currentLeft = false;
		
        if(node.getProc1() != null)
        {
            node.getProc1().apply(this);
        }
		previousDef = str+"()";
        outAFunctionExp(node);
    }
	
    @Override
    public void caseAPatternExp(APatternExp node)
    {
        inAPatternExp(node);
		currentParams.clear();
		currentInput.clear();
		pendingId = new ArrayList<String>();
		
		currentLeft = true;		
        if(node.getPattern1() != null)
        {
           node.getPattern1().apply(this);
        }
		currentLeft = false;

        if(node.getProc1() != null)
        {
           node.getProc1().apply(this);
        }
        outAPatternExp(node);
    }
//***************************************************************************************
//Patterns
	
    @Override
    public void caseAVarPattern(AVarPattern node)
    {
        inAVarPattern(node);
        if(node.getId() != null)
        {
           node.getId().apply(this);
			String str = node.getId().toString().replaceAll(" ","");
			if(letWithinDepth>0 && letWithinArgs.get(letWithinDepth).contains(str))
			{}	
			else if(currentInParameters)
			{
				if(currentParams.contains(str))
				{
					throw new RuntimeException("Redefinition of Identifier: "+str+" "+getSrcLoc(node.getId())+".");
				}
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
					throw new RuntimeException("Redefinition of Identifier "+str+" "+getSrcLoc(node.getId())+".");						
				}
				else
				{		
					ArrayList<String> temp = left.get(letWithinDepth);
					temp.add(str);
					left.put(letWithinDepth,temp);
				}
				previousDef = str;
			}
			else if(currentInLambdaLeft)
			{
				if(currentLambdaParams.contains(str))
				{
					throw new RuntimeException("Redefinition of Identifier: "+str+" "+getSrcLoc(node.getId())+".");
				}
				currentLambdaParams.add(str);
			}
			else if(currentInInput)
			{//c?x 
				if(currentInput.contains(str))
				{
					throw new RuntimeException("Redefinition of Identifier: "+str+" "+getSrcLoc(node.getId())+".");
				}
				currentInput.add(str);
			}
			else
			{	
				if(right.get(letWithinDepth) == null)
				{
					right.put(letWithinDepth,new ArrayList<String>());
				}	
				if(!right.get(letWithinDepth).contains(str) && !currentParams.contains(str) && !currentInput.contains(str))
				{
					ArrayList<String> temp = right.get(letWithinDepth);
					temp.add(str);
					right.put(letWithinDepth,temp);
				}
			}
       
        }
        outAVarPattern(node);
    }	

//***************************************************************************************
//Comprehensions
    @Override
    public void caseAComprSeqExp(AComprSeqExp node)
    {
        inAComprSeqExp(node);
		currentInComprArgs += 1;
        if(node.getArguments() != null)
        {
            node.getArguments().apply(this);
        }
		currentInComprArgs -= 1;
        if(node.getStmts() != null)
        {
            node.getStmts().apply(this);
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
		//end
        outAComprSeqExp(node);
    }
	
    @Override
    public void caseAEnumeratedComprSeqExp(AEnumeratedComprSeqExp node)
    {
        inAEnumeratedComprSeqExp(node);
		currentInComprArgs += 1;
        if(node.getArguments() != null)
        {
            node.getArguments().apply(this);
        }
		currentInComprArgs -= 1;
        if(node.getStmts() != null)
        {
            node.getStmts().apply(this);
        }
		//Check pending Id
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
		//end
        outAEnumeratedComprSeqExp(node);
    }
	
    @Override
    public void caseAComprSetExp(AComprSetExp node)
    {
        inAComprSetExp(node);
		currentInComprArgs += 1;
        if(node.getArguments() != null)
        {
            node.getArguments().apply(this);
        }
		currentInComprArgs -= 1;
        if(node.getStmts() != null)
        {
            node.getStmts().apply(this);
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
		//end
        outAComprSetExp(node);
    }
	
    @Override
    public void caseAEnumeratedComprSetExp(AEnumeratedComprSetExp node)
    {
        inAEnumeratedComprSetExp(node);
		currentInComprArgs += 1;
        if(node.getArguments() != null)
        {
            node.getArguments().apply(this);
        }
		currentInComprArgs -= 1;
        if(node.getStmts() != null)
        {
            node.getStmts().apply(this);
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
		//end
        outAEnumeratedComprSetExp(node);
    }	
	
    @Override
    public void caseALinkCompLinkComp(ALinkCompLinkComp node)
    {
        inALinkCompLinkComp(node);
        {
			currentInComprArgs += 1;
            List<PExp> copy = new ArrayList<PExp>(node.getLcList());
            for(PExp e : copy)
            {
                e.apply(this);
            }
			currentInComprArgs -= 1;
        }
        if(node.getStmts() != null)
        {
            node.getStmts().apply(this);
        }
		//Check pending Id
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
		//end
        outALinkCompLinkComp(node);
    }	
	
    @Override
    public void caseARenameCompRenameComp(ARenameCompRenameComp node)
    {
        inARenameCompRenameComp(node);
        {
			currentInComprArgs += 1;
            List<PExp> copy = new ArrayList<PExp>(node.getRcList());
            for(PExp e : copy)
            {
                e.apply(this);
            }
			currentInComprArgs -= 1;
        }
        if(node.getStmts() != null)
        {
            node.getStmts().apply(this);
        }
		//Check pending Id
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
		//end
        outARenameCompRenameComp(node);
    }
	

//***************************************************************************************
//Generator Statements	

    @Override
    public void caseAGeneratorStmts(AGeneratorStmts node)
    {
        inAGeneratorStmts(node);
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
        outAGeneratorStmts(node);
    }

//***************************************************************************************
//Input fields	
    @Override
    public void caseANondetInputPattern(ANondetInputPattern node)
    {
        inANondetInputPattern(node);
		currentInInput = true;
        if(node.getPattern1() != null)
        {
            node.getPattern1().apply(this);
        }
		currentInInput = false;
		currentParams.addAll(currentInput);
		currentInput.clear();
        outANondetInputPattern(node);
    }
	
    @Override
    public void caseAInputPattern(AInputPattern node)
    {
        inAInputPattern(node);
		currentInInput = true;
        if(node.getPattern1() != null)
        {
            node.getPattern1().apply(this);
        }
		currentInInput = false;
		currentParams.addAll(currentInput);
		currentInput.clear();
        outAInputPattern(node);
    }

	@Override
    public void caseATransparentDef(ATransparentDef node)
    {
        inATransparentDef(node);
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
				throw new RuntimeException("Redefinition of Identifier "+str+" "+getSrcLoc(node.getId())+".");
			}
        }
        outATransparentDef(node);
    }
	
    @Override
    public void caseAExternalDef(AExternalDef node)
    {
        inAExternalDef(node);
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
				throw new RuntimeException("Redefinition of Identifier "+str+" "+getSrcLoc(node.getId())+".");
			}
        }
        outAExternalDef(node);
    }

    @Override
    public void caseALetWithinExp(ALetWithinExp node)
    {
        inALetWithinExp(node);
		
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
        {
            List<PDef> copy = new ArrayList<PDef>(node.getDefs());
            for(PDef e : copy)
            {
                e.apply(this);
            }
        }

        if(node.getProc9() != null)
        {
            node.getProc9().apply(this);
        }
		
		if(right.get(letWithinDepth) != null)
		{
			check(letWithinDepth);
		}
		left.remove(letWithinDepth);
		right.remove(letWithinDepth);
		letWithinDepth -= 1;
	
        outALetWithinExp(node);
    }
	

    @Override
    public void caseALambdaExp(ALambdaExp node)
    {
        inALambdaExp(node);
        {
			currentInLambdaLeft = true;
            List<PPattern> copy = new ArrayList<PPattern>(node.getPatternList());
            for(PPattern e : copy)
            {
                e.apply(this);
            }
			currentInLambdaLeft = false;
        }
        if(node.getProc9() != null)
        {
            node.getProc9().apply(this);
        }
		currentLambdaParams = new ArrayList<String>();
        outALambdaExp(node);
    }
	
    @Override
    public void caseAIdExp(AIdExp node)
    {
        inAIdExp(node);
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
			else if(currentInComprArgs>0 
					&& !left.get(letWithinDepth).contains(str))
			{
				if(!pendingId.contains(str))
				pendingId.add(str); //for statements {x|x<-{1,2,3}},x is pending id
			}
			else if(currentInGenerator>0 
					&& !currentParams.contains(str)
					&& !currentInput.contains(str)
					&& !statementVar.contains(str))
			{
				statementVar.add(str);
			}
			else if(!builtIn.contains(str)
				 && !right.get(letWithinDepth).contains(str) 
			   	 && !currentParams.contains(str)
				 && !currentInput.contains(str)
				 && !currentLambdaParams.contains(str)
				 && !statementVar.contains(str))
			{
				ArrayList<String> temp = right.get(letWithinDepth);
				temp.add(str);
				right.put(letWithinDepth,temp);
			}
        }
        if(node.getLambda() != null)
        {
            node.getLambda().apply(this);
        }
        outAIdExp(node);
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
					throw new RuntimeException("Unbound Identifier: "+right.get(0).get(i)+"." );
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
	
	
//***************************************************************************************************************************************************
//SrcLoc-Getter	
	
	
	public String getSrcLoc(Node node) 
    {
		String s = "(";
		s+= "Line "+node.getStartPos().getLine();
		s+= " Column "+node.getStartPos().getPos()+")";

		return s;
    }
}