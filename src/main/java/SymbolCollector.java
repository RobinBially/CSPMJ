import java.io.IOException;
import java.util.Locale;
import CSPMparser.analysis.*;
import CSPMparser.node.*;
import java.util.*;
import java.io.*;




public class SymbolCollector extends DepthFirstAdapter
{	
	private boolean patternRequired;
	private int currentInParams;

	private int groundrep;
	private int inComprGenerator;			
	
	private BlockTree tree;
	
	ArrayList<SymInfo> symbols;

	public SymbolCollector()
	{
			
		patternRequired = false;
		inComprGenerator = 0;
		currentInParams = 0;
		groundrep = 0;
		
		tree = new BlockTree();
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
			addSymbol(str, "Nametype", node.getId());
        }
        if(node.getTypeExp() != null)
        {
            node.getTypeExp().apply(this);
        }

        outANtypeTypes(node);
    }

    @Override
    public void caseATypedefTypes(ATypedefTypes node)
    {
        inATypedefTypes(node);
        if(node.getId() != null)
        {
            node.getId().apply(this);
			String str = node.getId().toString().replace(" ","");
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
    public void caseAClauseTypes(AClauseTypes node)
    {
        inAClauseTypes(node);	
        if(node.getClauseName() != null)
        {
            node.getClauseName().apply(this);
			String str = node.getClauseName().toString().replace(" ","");
			addSymbol(str, "Constructor of Datatype", node.getClauseName());
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

        if(node.getStmts() != null)
        {
			inComprGenerator += 1;
            node.getStmts().apply(this);
			inComprGenerator -= 1;
        }
        if(node.getArguments() != null)
        {
            node.getArguments().apply(this);
        }
		tree.returnToParent();
        outAComprSeqExp(node);
    }

    @Override
    public void caseARangedComprSeqExp(ARangedComprSeqExp node)
    {
        inARangedComprSeqExp(node);
		tree.newLeaf();

        if(node.getStmts() != null)
        {
			inComprGenerator += 1;
            node.getStmts().apply(this);
			inComprGenerator -= 1;
        }
        if(node.getLval() != null)
        {
            node.getLval().apply(this);
        }
        if(node.getRval() != null)
        {
            node.getRval().apply(this);
        }
		tree.returnToParent();
        outARangedComprSeqExp(node);
    }

    @Override
    public void caseAInfiniteComprSeqExp(AInfiniteComprSeqExp node)
    {
        inAInfiniteComprSeqExp(node);
		tree.newLeaf();

        if(node.getStmts() != null)
        {
			inComprGenerator += 1;
            node.getStmts().apply(this);
			inComprGenerator -= 1;
        }
        if(node.getValExp() != null)
        {
            node.getValExp().apply(this);
        }
		tree.returnToParent();
        outAInfiniteComprSeqExp(node);
    }

    @Override
    public void caseAComprSetExp(AComprSetExp node)
    {
        inAComprSetExp(node);
		tree.newLeaf();

        if(node.getStmts() != null)
        {
			inComprGenerator += 1;
            node.getStmts().apply(this);
			inComprGenerator -= 1;
        }
        if(node.getArguments() != null)
        {
            node.getArguments().apply(this);
        }
		tree.returnToParent();
        outAComprSetExp(node);
    }

    @Override
    public void caseARangedComprSetExp(ARangedComprSetExp node)
    {
        inARangedComprSetExp(node);
		tree.newLeaf();

        if(node.getStmts() != null)
        {
			inComprGenerator += 1;
            node.getStmts().apply(this);
			inComprGenerator -= 1;
        }
        if(node.getLval() != null)
        {
            node.getLval().apply(this);
        }
        if(node.getRval() != null)
        {
            node.getRval().apply(this);
        }
		tree.returnToParent();
        outARangedComprSetExp(node);
    }

    @Override
    public void caseAInfiniteComprSetExp(AInfiniteComprSetExp node)
    {
        inAInfiniteComprSetExp(node);
		tree.newLeaf();

        if(node.getStmts() != null)
        {
			inComprGenerator += 1;
            node.getStmts().apply(this);
			inComprGenerator -= 1;
        }
        if(node.getValExp() != null)
        {
            node.getValExp().apply(this);
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
        if(node.getArguments() != null)
        {
            node.getArguments().apply(this);
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
			addSymbol(str, "Ident (Groundrep.)", node.getId());
		}
		else if(currentInParams>0)
		{		
			//currentParams.add(str);
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
			addSymbol(str, "Function or Process", node.getId());
        }
		
		tree.newLeaf();		
        if(node.getParameters() != null)
        {
			currentInParams += 1;
            node.getParameters().apply(this);
			currentInParams -= 1;
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
    public void caseALambdaExp(ALambdaExp node)
    {	
        inALambdaExp(node);
		tree.newLeaf();
        {
			List<PPattern> copy = new ArrayList<PPattern>(node.getPatternList());
			currentInParams +=1;
            for(PPattern e : copy)
            {
                e.apply(this);
            }
			currentInParams -=1;
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
		currentInParams +=1;
        if(node.getPattern1() != null)
        {
            node.getPattern1().apply(this);
        }
		currentInParams -=1;
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
		currentInParams +=1;
        if(node.getPattern1() != null)
        {
            node.getPattern1().apply(this);
        }
		currentInParams -=1;
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
			addSymbol(str, "Ident (Prolog Variable)", node.getId());			
		}
		else if(isBuiltin(str))
		{
			addSymbol(str, "BuiltIn primitive", node.getId());
		}
        if(node.getLambda() != null)
        {			
            node.getLambda().apply(this);
        }
        outAIdExp(node);
    }
	
    @Override
    public void caseAIdTypeExp(AIdTypeExp node)
    {
        inAIdTypeExp(node);
		String str = node.getId().toString().replace(" ","");
        if(node.getId() != null)
        {
            node.getId().apply(this);
        }
		
		if(isBuiltin(str))
		{
			addSymbol(str, "BuiltIn primitive", node.getId());
		}	
	
			
        if(node.getLambda() != null)
        {			
            node.getLambda().apply(this);
        }
        outAIdTypeExp(node);
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
        if(node.getLambda() != null)
        {
            node.getLambda().apply(this);
        }
        outATupleExp(node);
    }
	
    @Override
    public void caseATupleTuple(ATupleTuple node)
    {
        inATupleTuple(node);
		
		tree.newLeaf();		
		if(node.getProc1() != null)
		{
			node.getProc1().apply(this);
		}		
		tree.returnToParent();
		
		{
			List<PExp> copy = new ArrayList<PExp>(node.getArgumentsList());
			for(PExp e : copy)
			{
				tree.newLeaf();
				e.apply(this);
				tree.returnToParent();
			}
		}
        outATupleTuple(node);
    }
	
    @Override
    public void caseAParenthesisExp(AParenthesisExp node)
    {
        inAParenthesisExp(node);
		tree.newLeaf();
        if(node.getProc1() != null)
        {
            node.getProc1().apply(this);
        }
		tree.returnToParent();

        if(node.getLambda() != null)
        {
            node.getLambda().apply(this);
        }
        outAParenthesisExp(node);
    }
	
    @Override
    public void caseALambdaLambda(ALambdaLambda node)
    {
        inALambdaLambda(node);
        {
            List<PExp> copy = new ArrayList<PExp>(node.getArgumentsList());
            for(PExp e : copy)
            {
				tree.newLeaf();
                e.apply(this);
				tree.returnToParent();
            }
        }
        outALambdaLambda(node);
    }
	
//***************************************************************************************************************************************************
//Helpers

	public void addSymbol(String name, String info, Node node)
	{
		boolean set = false;
		if(info.equals("Function of Process"))
		{
			for(int i = 0;i< symbols.size();i++)
			{
				if(symbols.get(i).symbolName.equals(name)
					&& (symbols.get(i).blockNumber == tree.getCurrentBlockNumber())
					&& (symbols.get(i).symbolInfo.equals("Function or Process")))
				{
						// do nothing
						set = true;
						break;
				}
			}			
		}
		if(isBuiltin(name) && !info.equals("Ident (Prolog Variable)") && !set)
		{	
			for(int i = 0;i< symbols.size();i++)
			{
				if(symbols.get(i).symbolName.equals(name)
					&& (symbols.get(i).blockNumber == tree.getCurrentBlockNumber())
					&& (symbols.get(i).symbolInfo.equals("BuiltIn primitive")))
				{
					if(i == 0)
					{
						SymInfo si = new SymInfo(node,info,tree.getCurrentBlockNumber(),name,name);
						symbols.set(i,si);		
						set = true;
					}
					else
					{
						SymInfo si = new SymInfo(node,info,tree.getCurrentBlockNumber(),name,name+(i+1));
						symbols.set(i,si);		
						set = true;
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
				if(countSymbol == 0)
				{
					SymInfo si = new SymInfo(node,"BuiltIn primitive",tree.getCurrentBlockNumber(),name,name);
					symbols.add(si);
					set = true;
				}
				else
				{
					SymInfo si = new SymInfo(node,"BuiltIn primitive",tree.getCurrentBlockNumber(),name,name+(countSymbol+1));
					symbols.add(si);
					set = true;
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
					SymInfo si = new SymInfo(node,info,tree.getCurrentBlockNumber(),name,"_"+name);
					symbols.add(si);
				}
				else
				{
					SymInfo si = new SymInfo(node,info,tree.getCurrentBlockNumber(),name,"_"+name+(countSymbol+1));
					symbols.add(si);
				}
			}
			else
			{
				if(countSymbol == 0)
				{
					SymInfo si = new SymInfo(node,info,tree.getCurrentBlockNumber(),name,name);
					symbols.add(si);
				}
				else
				{
					SymInfo si = new SymInfo(node,info,tree.getCurrentBlockNumber(),name,name+(countSymbol+1));
					symbols.add(si);			
				}
			}
		}
		
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
