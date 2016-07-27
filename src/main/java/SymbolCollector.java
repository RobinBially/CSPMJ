import java.io.IOException;
import java.util.Locale;
import CSPMparser.analysis.*;
import CSPMparser.node.*;
import java.util.*;
import java.io.*;

public class SymbolCollector extends DepthFirstAdapter
{	
	private boolean patternRequired;
	private int expectingPrologVar;
	private boolean inSubtypeDef;
	private int groundrep;
	private int inComprGenerator;
	private ArrayList<String> renamingArgs;
	private HashMap<Integer,String> lastFunctionDef; //Save last function definition (String) in scope (Integer) for verticalRenamingAnalysis
	private ScopeTree tree;
	
	ArrayList<SymInfo> symbols;

	public SymbolCollector()
	{
		lastFunctionDef = new HashMap<Integer,String>();
		inSubtypeDef = false;	
		patternRequired = false;
		inComprGenerator = 0;
		expectingPrologVar = 0;
		groundrep = 0;
		renamingArgs = new ArrayList<String>();
		tree = new ScopeTree();
		symbols = new ArrayList<SymInfo>();

	}
//***************************************************************************************************************************************************
//Datatype, Subtype, Nametype

    @Override
    public void caseANtypeTypes(ANtypeTypes node)
    {
        inANtypeTypes(node);
        if(node.getId() != null)
        {
            node.getId().apply(this);
			String str = node.getId().toString().replace(" ","");
			lastFunctionDef.put(tree.getCurrentScopeNumber(),str);
			addSymbol(str, "Nametype", node.getId());
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
        if(node.getTypedef() != null)
        {
			inSubtypeDef = true;
            node.getTypedef().apply(this);
			inSubtypeDef = false;
        }
        outAStypeTypes(node);
    }
	
    @Override
    public void caseATypedefTypes(ATypedefTypes node) // datatype X = Y  this node defines X
    {
        inATypedefTypes(node);
        if(node.getId() != null)
        {
            node.getId().apply(this);
			String str = node.getId().toString().replace(" ","");
			lastFunctionDef.put(tree.getCurrentScopeNumber(),str);
			addSymbol(str, "Datatype", node.getId());
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
    public void caseAClauseTypes(AClauseTypes node) // datatype X = Y  this node defines Y
    {
        inAClauseTypes(node);	
        if(node.getClauseName() != null)
        {
            node.getClauseName().apply(this);
			if(!inSubtypeDef)
			{
				String str = node.getClauseName().toString().replace(" ","");
				addSymbol(str, "Constructor of Datatype", node.getClauseName());
			}
        }
        if(node.getDotted() != null)
        {
            node.getDotted().apply(this);
        }
        outAClauseTypes(node);
    }

//***************************************************************************************************************************************************
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
				String str = e.toString().replace(" ","");
				lastFunctionDef.put(tree.getCurrentScopeNumber(),str);
				addSymbol(str, "Channel", e);
            }
        }
        if(node.getChanType() != null)
        {
            node.getChanType().apply(this);
        }
        outAChannelDef(node);
    }
//***************************************************************************************************************************************************
//Comprehensions

    @Override
    public void caseALinkCompLinkComp(ALinkCompLinkComp node)
    {
        inALinkCompLinkComp(node);
		tree.newLeaf();

		if(node.getStmts() != null)
		{
			inComprGenerator += 1;
			node.getStmts().apply(this);
			inComprGenerator -= 1;
		}
        {
            List<PExp> copy = new ArrayList<PExp>(node.getLcList());
            for(PExp e : copy)
            {
                e.apply(this);
            }
        }
		tree.returnToParent();
        outALinkCompLinkComp(node);
    }

    @Override
    public void caseARenameCompRenameComp(ARenameCompRenameComp node)
    {
		inARenameCompRenameComp(node);
		if(node.getProc10() != null)
        {
            node.getProc10().apply(this);
        }
		tree.newLeaf();
		if(node.getStmts() != null)
        {
			inComprGenerator += 1;
            node.getStmts().apply(this);
			inComprGenerator -= 1;
        }
        {
            List<PExp> copy = new ArrayList<PExp>(node.getRcList());
            for(PExp e : copy)
            {
                e.apply(this);
            }
        }
		tree.returnToParent();
        outARenameCompRenameComp(node);
    }
    @Override
    public void caseAComprSeqExp(AComprSeqExp node)
    {
        inAComprSeqExp(node);
		tree.newLeaf();

        if(node.getExpressions() != null)
        {
            node.getExpressions().apply(this);
        }
        if(node.getStmts() != null)
        {
			inComprGenerator += 1;
            node.getStmts().apply(this);
			inComprGenerator -= 1;
        }
		tree.returnToParent();
        outAComprSeqExp(node);
    }

    @Override
    public void caseARangedComprSeqExp(ARangedComprSeqExp node)
    {
        inARangedComprSeqExp(node);
		tree.newLeaf();

        if(node.getLval() != null)
        {
            node.getLval().apply(this);
        }
        if(node.getRval() != null)
        {
            node.getRval().apply(this);
        }
        if(node.getStmts() != null)
        {
			inComprGenerator += 1;
            node.getStmts().apply(this);
			inComprGenerator -= 1;
        }
		tree.returnToParent();
        outARangedComprSeqExp(node);
    }

    @Override
    public void caseAInfiniteComprSeqExp(AInfiniteComprSeqExp node)
    {
        inAInfiniteComprSeqExp(node);
		tree.newLeaf();

        if(node.getValExp() != null)
        {
            node.getValExp().apply(this);
        }
        if(node.getStmts() != null)
        {
			inComprGenerator += 1;
            node.getStmts().apply(this);
			inComprGenerator -= 1;
        }
		tree.returnToParent();
        outAInfiniteComprSeqExp(node);
    }

    @Override
    public void caseAComprSetExp(AComprSetExp node)
    {
        inAComprSetExp(node);
		tree.newLeaf();

        if(node.getExpressions() != null)
        {
            node.getExpressions().apply(this);
        }
        if(node.getStmts() != null)
        {
			inComprGenerator += 1;
            node.getStmts().apply(this);
			inComprGenerator -= 1;
        }
		tree.returnToParent();
        outAComprSetExp(node);
    }

    @Override
    public void caseARangedComprSetExp(ARangedComprSetExp node)
    {
        inARangedComprSetExp(node);
		tree.newLeaf();

        if(node.getLval() != null)
        {
            node.getLval().apply(this);
        }
        if(node.getRval() != null)
        {
            node.getRval().apply(this);
        }
        if(node.getStmts() != null)
        {
			inComprGenerator += 1;
            node.getStmts().apply(this);
			inComprGenerator -= 1;
        }
		tree.returnToParent();
        outARangedComprSetExp(node);
    }

    @Override
    public void caseAInfiniteComprSetExp(AInfiniteComprSetExp node)
    {
        inAInfiniteComprSetExp(node);
		tree.newLeaf();

        if(node.getValExp() != null)
        {
            node.getValExp().apply(this);
        }
        if(node.getStmts() != null)
        {
			inComprGenerator += 1;
            node.getStmts().apply(this);
			inComprGenerator -= 1;
        }
		tree.returnToParent();
        outAInfiniteComprSetExp(node);
    }
	
	@Override
    public void caseAEnumeratedComprSetExp(AEnumeratedComprSetExp node)
    {
        inAEnumeratedComprSetExp(node);
		tree.newLeaf();

        if(node.getStmts() != null)
        {
			inComprGenerator += 1;
            node.getStmts().apply(this);
			inComprGenerator -= 1;
        }
        if(node.getExpressions() != null)
        {
            node.getExpressions().apply(this);
        }
		tree.returnToParent();
        outAEnumeratedComprSetExp(node);
    }
//***************************************************************************************************************************************************
//Patterns	
	
    @Override
    public void caseAVarPattern(AVarPattern node)
    {
        inAVarPattern(node);
		String str = node.getId().toString().replaceAll(" ","");		
		if(groundrep>0)
		{
			lastFunctionDef.put(tree.getCurrentScopeNumber(),str);
			addSymbol(str, "Ident (Groundrep.)", node.getId());
		}
		else if(expectingPrologVar>0)
		{	
			horizontalRenamingAnalysis(str,node.getId());
			renamingArgs.add(str);
			addSymbol(str, "Ident (Prolog Variable)", node.getId());
		}
		
        if(node.getId() != null)
        {
            node.getId().apply(this);
        }		
        outAVarPattern(node);
    }
//***************************************************************************************************************************************************
//Agent or Bindval

	@Override
    public void caseAFunctionExp(AFunctionExp node)
    {
        inAFunctionExp(node);
        if(node.getId() != null)
        {
            node.getId().apply(this);			
			String str = node.getId().toString().replace(" ","");
			addSymbol(str,"Function or Process", node.getId());
			lastFunctionDef.put(tree.getCurrentScopeNumber(),str+"()");
        }
		
		tree.newLeaf();	
        {
            List<PParameters> copy = new ArrayList<PParameters>(node.getParameters());
            for(PParameters e : copy)
            {
				expectingPrologVar += 1;
                e.apply(this);
				expectingPrologVar -= 1;
            }
			renamingArgs.clear();
        }	
        if(node.getProc1() != null)
        {
            node.getProc1().apply(this);
        }
		tree.returnToParent();
        outAFunctionExp(node);
    }	
	
    @Override
    public void caseAPatternExp(APatternExp node)
    {
        inAPatternExp(node);
        if(node.getPattern1() != null)
        {
			groundrep +=1;
            node.getPattern1().apply(this);
			groundrep -=1;
        }
		
		tree.newLeaf();
		
        if(node.getProc1() != null)
        {
            node.getProc1().apply(this);
        }
		
		tree.returnToParent();
        outAPatternExp(node);
    }
	
//*******************************************************************************************************************************
//Expressions

    @Override
    public void caseAPrefixExp(APrefixExp node)
    {
        inAPrefixExp(node);
        if(node.getEvent() != null)
        {
            node.getEvent().apply(this);
			renamingArgs.clear();
        }
        if(node.getPrefix() != null)
        {
            node.getPrefix().apply(this);
        }
        if(node.getProc9() != null)
        {
            node.getProc9().apply(this);
        }
        outAPrefixExp(node);
    }
	
    @Override
    public void caseALambdaExp(ALambdaExp node)
    {	
        inALambdaExp(node);
		tree.newLeaf();
        {
			List<PPattern> copy = new ArrayList<PPattern>(node.getPatternList());
			expectingPrologVar +=1;
            for(PPattern e : copy)
            {
                e.apply(this);
            }
			expectingPrologVar -=1;
			renamingArgs.clear();
        }
        if(node.getProc9() != null)
        {
            node.getProc9().apply(this);
        }
		tree.returnToParent();
        outALambdaExp(node);
    }
	
    @Override
    public void caseALetWithinExp(ALetWithinExp node)
    {
        inALetWithinExp(node);
		tree.newLeaf();
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
		tree.returnToParent();
        outALetWithinExp(node);
    }
	
    @Override
    public void caseANondetInputPattern(ANondetInputPattern node)
    {
        inANondetInputPattern(node);
		expectingPrologVar +=1;
        if(node.getPattern1() != null)
        {
            node.getPattern1().apply(this);
        }
		expectingPrologVar -=1;
        if(node.getRestriction() != null)
        {
            node.getRestriction().apply(this);
        }
        outANondetInputPattern(node);
    }
	
    @Override
    public void caseAInputPattern(AInputPattern node)
    {
        inAInputPattern(node);
		expectingPrologVar +=1;
        if(node.getPattern1() != null)
        {
            node.getPattern1().apply(this);
        }
		expectingPrologVar -=1;
        if(node.getRestriction() != null)
        {
            node.getRestriction().apply(this);
        }
        outAInputPattern(node);
    }
	
	@Override
    public void caseAIdExp(AIdExp node)
    {
        inAIdExp(node);
		String str = node.getId().toString().replace(" ","");
		
        if(node.getId() != null)
        {
            node.getId().apply(this);
        }	
		if(patternRequired)
		{
			horizontalRenamingAnalysis(str,node.getId());
			renamingArgs.add(str);
			addSymbol(str, "Ident (Prolog Variable)", node.getId());			
		}
        {
            List<PArguments> copy = new ArrayList<PArguments>(node.getArguments());
            for(PArguments e : copy)
            {
                e.apply(this);
            }
        }
        outAIdExp(node);
    }
	
    @Override
    public void caseAIdTypeExp(AIdTypeExp node) //This is not the definition side !!!
    {
        inAIdTypeExp(node);
		String str = node.getId().toString().replace(" ","");
        if(node.getId() != null)
        {
            node.getId().apply(this);
        }			
        {
            List<PArguments> copy = new ArrayList<PArguments>(node.getArguments());
            for(PArguments e : copy)
            {
                e.apply(this);
            }
        }
        outAIdTypeExp(node);
    }
	
    @Override
    public void caseAMapExp(AMapExp node)
    {
        inAMapExp(node);
        if(node.getParL() != null)
        {
            node.getParL().apply(this);
        }
		List<PExp> copy = new ArrayList<PExp>(node.getMapList());
		for(PExp e : copy)
		{
			tree.newLeaf();
			e.apply(this);
			tree.returnToParent();
		}
        if(node.getParR() != null)
        {
            node.getParR().apply(this);
        }
        outAMapExp(node);
    }	
	
//***************************************************************************************************************************************************
//Statements

    @Override
    public void caseAGeneratorStmts(AGeneratorStmts node)
    {
        inAGeneratorStmts(node);
		patternRequired = true;
        if(node.getDpattern() != null)
        {
            node.getDpattern().apply(this);
        }
		patternRequired = false;
		renamingArgs.clear();
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
//*******************************************************************************************************************************
//Tuple and Parenthesis

    @Override
    public void caseATupleExp(ATupleExp node)
    {
        inATupleExp(node);
        if(node.getTuple() != null)
        {
            node.getTuple().apply(this);
        }
        {
            List<PArguments> copy = new ArrayList<PArguments>(node.getArguments());
            for(PArguments e : copy)
            {
                e.apply(this);
            }
        }
        outATupleExp(node);
    }
	
    @Override
    public void caseATupleTuple(ATupleTuple node)
    {
        inATupleTuple(node);		
		if(patternRequired)
		{
			if(node.getProc1() != null)
			{
				node.getProc1().apply(this);
			}
			{
				List<PExp> copy = new ArrayList<PExp>(node.getExpressionList());
				for(PExp e : copy)
				{
					e.apply(this);
				}
			}
		}
		else
        {
			if(node.getProc1() != null)
			{
				tree.newLeaf();
				node.getProc1().apply(this);
				tree.returnToParent();
			}
			{
				List<PExp> copy = new ArrayList<PExp>(node.getExpressionList());
				for(PExp e : copy)
				{
					tree.newLeaf();
					e.apply(this);
					tree.returnToParent();
				}
			}
		}
        outATupleTuple(node);
    }
	
    @Override
    public void caseAParenthesisExp(AParenthesisExp node)
    {
        inAParenthesisExp(node);
        if(node.getProc1() != null)
        {
			if(patternRequired)
			{
				node.getProc1().apply(this);
			}
			else
			{
				tree.newLeaf();
				node.getProc1().apply(this);
				tree.returnToParent();
			}
        }
        {
            List<PArguments> copy = new ArrayList<PArguments>(node.getArguments());
            for(PArguments e : copy)
            {
                e.apply(this);
            }
        }
        outAParenthesisExp(node);
    }
	
    @Override
    public void caseAArgListArguments(AArgListArguments node)
    {
        inAArgListArguments(node);
        if(node.getExpressions() != null)
        {
            node.getExpressions().apply(this);
        }
        outAArgListArguments(node);
    }
	
    @Override
    public void caseAExpressionListExpressions(AExpressionListExpressions node)
    {
        inAExpressionListExpressions(node);
        {
            List<PExp> copy = new ArrayList<PExp>(node.getExpressionList());
            for(PExp e : copy)
            {
				tree.newLeaf();
                e.apply(this);
				tree.returnToParent();
            }
        }
        outAExpressionListExpressions(node);
    }	
//***************************************************************************************************************************************************
//Transparent

    @Override
    public void caseATransparentDef(ATransparentDef node)
    {
        inATransparentDef(node);
        {
            List<PId> copy = new ArrayList<PId>(node.getIdList());
			String str;
            for(PId e : copy)
            {
                e.apply(this);
				str = e.toString().replace(" ","");
				addSymbol(str,"Transparent function",e);
            }
        }
        outATransparentDef(node);
    }
	
//***************************************************************************************************************************************************
//Helpers

	public void addSymbol(String name, String info, Node node)
	{
		verticalRenamingAnalysis(name,node);
		boolean set = false;
		if(info.equals("Function or Process"))
		{
			for(int i = 0;i< symbols.size();i++)
			{
				if(symbols.get(i).symbolName.equals(name)
					&& (symbols.get(i).scopeNumber == tree.getCurrentScopeNumber())
					&& (symbols.get(i).symbolInfo.equals("Function or Process")))
				{
						// do nothing
						set = true;
						break;
				}
			}			
		}
		if(!set)
		{
			int countSymbol = 0;
			for(int i = 0;i< symbols.size();i++)
			{
				if(symbols.get(i).symbolName.equals(name))
				{
					countSymbol++;
				}
			}			
			if(info.equals("Ident (Prolog Variable)"))
			{
				if(countSymbol == 0)
				{
					SymInfo si = new SymInfo(node,info,tree.getCurrentScopeNumber(),name,"_"+name);
					symbols.add(si);
				}
				else
				{
					SymInfo si = new SymInfo(node,info,tree.getCurrentScopeNumber(),name,"_"+name+(countSymbol+1));
					symbols.add(si);
				}

			}
			else
			{
				if(countSymbol == 0)
				{
					SymInfo si = new SymInfo(node,info,tree.getCurrentScopeNumber(),name,name);
					symbols.add(si);
				}
				else
				{
					SymInfo si = new SymInfo(node,info,tree.getCurrentScopeNumber(),name,name+(countSymbol+1));
					symbols.add(si);			
				}
			}
		}
		
	}
	
	public void verticalRenamingAnalysis(String s, Node n)
	{
		for(int i=0;i<symbols.size();i++)
		{
			  if(   symbols.get(i).symbolName.equals(s)
					&&(tree.getCurrentScopeNumber() == symbols.get(i).scopeNumber)
					&&
					(
						symbols.get(i).symbolInfo.equals("Ident (Groundrep.)")
						||
						(	symbols.get(i).symbolInfo.equals("Function or Process")
							&& !lastFunctionDef.get(tree.getCurrentScopeNumber()).equals(s+"()")
							&& !lastFunctionDef.get(tree.getCurrentScopeNumber()).equals("")
						)					
					)
				)		
				{			
					throw new RenamingException(s,n);	
				}
		}
	}
	
	public void horizontalRenamingAnalysis(String s,Node n)
	{

		if(renamingArgs.contains(s))
		{
			throw new RenamingException(s,n);	
		}
		
	}
	
	public String getErrorLoc(Node node)
	{
		String s = ","+node.getStartPos().getLine()+","+node.getStartPos().getPos();
		return s;
	}
		
	public ArrayList<SymInfo> getSymbols()
	{
		return symbols;
	}
	
    public boolean isBuiltin(String s)
	{
		ArrayList<String> builtin = new ArrayList<String>();
		builtin.add("Bool");
		builtin.add("Char");
		builtin.add("Events");
		builtin.add("Int");
		builtin.add("Proc");
		builtin.add("False");
		builtin.add("True");
		builtin.add("card");
		builtin.add("diff");
		builtin.add("empty");
		builtin.add("inter");
		builtin.add("Inter");
		builtin.add("member");
		builtin.add("seq");
		builtin.add("Seq");
		builtin.add("Set");
		builtin.add("union");
		builtin.add("Union");
		builtin.add("concat");
		builtin.add("elem");
		builtin.add("head");
		builtin.add("length");
		builtin.add("null");
		builtin.add("set");
		builtin.add("tail");
		builtin.add("emptyMap");
		builtin.add("mapDelete");
		builtin.add("mapFromList");
		builtin.add("mapLookup");
		builtin.add("mapMember");
		builtin.add("mapToList");
		builtin.add("mapUpdate");
		builtin.add("mapUpdateMultiple");
		builtin.add("Map");
		builtin.add("Inter");
		builtin.add("member");
		builtin.add("seq");
		builtin.add("error");
		builtin.add("show");
		builtin.add("seq");
		builtin.add("CHAOS");
		builtin.add("DIV");
		builtin.add("RUN");
		builtin.add("WAIT");
		builtin.add("STOP");
		builtin.add("SKIP");
		builtin.add("extensions");
		builtin.add("productions");
	
		if(builtin.contains(s))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
}
