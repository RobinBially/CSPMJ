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
	private HashMap<String,ArrayList<SymInfo>> symbols; //Identifier,Counter
	private int groundrep;
	private int inComprGenerator;
	private int structCount; //renumber stucts
	private int currentStructNum; //Saves a reference to the current struct
	private HashMap<Integer,Integer> struct;
				// counter ,predecessor
	public SymbolCollector()
	{
		patternRequired = false;
		inComprGenerator = 0;
		structCount = 0;
		currentStructNum = 0;
		struct = new HashMap<Integer,Integer>();		
		currentInParams = 0;
		groundrep = 0;
		symbols = new HashMap<String,ArrayList<SymInfo>>();
		//currentParams = new ArrayList<String>();
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
			if(symbols.containsKey(str) && isBuiltin(str))
			{
				for(int i = 0;i<symbols.get(str).size();i++)
				{
					if(symbols.get(str).get(i).getSymbolInfo().equals("BuiltIn primitive"))
					{
						SymInfo si = new SymInfo(node.getId(),"Nametype",0);
						symbols.get(str).set(i,si);
						break;
					}
				}
			}
			else if(symbols.containsKey(str))
			{
				ArrayList<SymInfo> temp = symbols.get(str);
				SymInfo si = new SymInfo(node.getId(),"Nametype",0);
				temp.add(si);
				symbols.put(str,temp);
			}
			else
			{
				ArrayList<SymInfo> temp = new ArrayList<SymInfo>();
				SymInfo si = new SymInfo(node.getId(),"Nametype",0);
				temp.add(si);
				symbols.put(str,temp);
			}
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
			if(symbols.containsKey(str) && isBuiltin(str))
			{
				for(int i = 0;i<symbols.get(str).size();i++)
				{
					if(symbols.get(str).get(i).getSymbolInfo().equals("BuiltIn primitive"))
					{
						SymInfo si = new SymInfo(node.getId(),"Datatype",0);
						symbols.get(str).set(i,si);
						break;
					}					
				}
			}
			else if(symbols.containsKey(str))
			{
				ArrayList<SymInfo> temp = symbols.get(str);
				SymInfo si = new SymInfo(node.getId(),"Datatype",0);
				temp.add(si);
				symbols.put(str,temp);
			}
			else
			{
				ArrayList<SymInfo> temp = new ArrayList<SymInfo>();
				SymInfo si = new SymInfo(node.getId(),"Datatype",0);
				temp.add(si);
				symbols.put(str,temp);
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
			String str = node.getClauseName().toString().replace(" ","");
			if(symbols.containsKey(str) && isBuiltin(str))
			{
				for(int i = 0;i<symbols.get(str).size();i++)
				{
					if(symbols.get(str).get(i).getSymbolInfo().equals("BuiltIn primitive"))
					{
						SymInfo si = new SymInfo(node.getClauseName(),"Constructor of Datatype",0);
						symbols.get(str).set(i,si);
						break;
					}
				}
			}
			else if(symbols.containsKey(str))
			{
				ArrayList<SymInfo> temp = symbols.get(str);
				SymInfo si = new SymInfo(node.getClauseName(),"Constructor of Datatype",0);
				temp.add(si);
				symbols.put(str,temp);
			}
			else
			{
				ArrayList<SymInfo> temp = new ArrayList<SymInfo>();
				SymInfo si = new SymInfo(node.getClauseName(),"Constructor of Datatype",0);
				temp.add(si);
				symbols.put(str,temp);
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
				if(symbols.containsKey(str) && isBuiltin(str))
				{
					for(int i = 0;i<symbols.get(str).size();i++)
					{
						if(symbols.get(str).get(i).getSymbolInfo().equals("BuiltIn primitive"))
						{
							SymInfo si = new SymInfo(e,"Channel",0);
							symbols.get(str).set(i,si);
							break;
						}
					}
				}
				else if(symbols.containsKey(str))
				{
					ArrayList<SymInfo> temp = symbols.get(str);
					SymInfo si = new SymInfo(e,"Channel",0);
					temp.add(si);
					symbols.put(str,temp);
				}
				else
				{
					ArrayList<SymInfo> temp = new ArrayList<SymInfo>();
					SymInfo si = new SymInfo(e,"Channel",0);
					temp.add(si);
					symbols.put(str,temp);
				}
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
		structCount++;
		struct.put(structCount,currentStructNum);
		currentStructNum = structCount;

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
		currentStructNum = struct.get(currentStructNum);
        outALinkCompLinkComp(node);
    }

    @Override
    public void caseARenameCompRenameComp(ARenameCompRenameComp node)
    {
		inARenameCompRenameComp(node);
		structCount++;
		struct.put(structCount,currentStructNum);
		currentStructNum = structCount;

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
		currentStructNum = struct.get(currentStructNum);
        outARenameCompRenameComp(node);
    }
    @Override
    public void caseAComprSeqExp(AComprSeqExp node)
    {
        inAComprSeqExp(node);
		structCount++;
		struct.put(structCount,currentStructNum);
		currentStructNum = structCount;

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
		currentStructNum = struct.get(currentStructNum);
        outAComprSeqExp(node);
    }

    @Override
    public void caseARangedComprSeqExp(ARangedComprSeqExp node)
    {
        inARangedComprSeqExp(node);
		structCount++;
		struct.put(structCount,currentStructNum);
		currentStructNum = structCount;

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
		currentStructNum = struct.get(currentStructNum);
        outARangedComprSeqExp(node);
    }

    @Override
    public void caseAInfiniteComprSeqExp(AInfiniteComprSeqExp node)
    {
        inAInfiniteComprSeqExp(node);
		structCount++;
		struct.put(structCount,currentStructNum);
		currentStructNum = structCount;

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
		currentStructNum = struct.get(currentStructNum);
        outAInfiniteComprSeqExp(node);
    }

    @Override
    public void caseAComprSetExp(AComprSetExp node)
    {
        inAComprSetExp(node);
		structCount++;
		struct.put(structCount,currentStructNum);
		currentStructNum = structCount;

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
		currentStructNum = struct.get(currentStructNum);
        outAComprSetExp(node);
    }

    @Override
    public void caseARangedComprSetExp(ARangedComprSetExp node)
    {
        inARangedComprSetExp(node);
		structCount++;
		struct.put(structCount,currentStructNum);
		currentStructNum = structCount;

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
		currentStructNum = struct.get(currentStructNum);
        outARangedComprSetExp(node);
    }

    @Override
    public void caseAInfiniteComprSetExp(AInfiniteComprSetExp node)
    {
        inAInfiniteComprSetExp(node);
		structCount++;
		struct.put(structCount,currentStructNum);
		currentStructNum = structCount;

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
		currentStructNum = struct.get(currentStructNum);
        outAInfiniteComprSetExp(node);
    }
	
	@Override
    public void caseAEnumeratedComprSetExp(AEnumeratedComprSetExp node)
    {
        inAEnumeratedComprSetExp(node);
		structCount++;
		struct.put(structCount,currentStructNum);
		currentStructNum = structCount;

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
		currentStructNum = struct.get(currentStructNum);
        outAEnumeratedComprSetExp(node);
    }
//***************************************************************************************************************************************************
//Patterns	
	
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
        if(node.getProc1() != null)
        {
            node.getProc1().apply(this);
        }
        outAPatternExp(node);
    }
	
    @Override
    public void caseAVarPattern(AVarPattern node)
    {
        inAVarPattern(node);
		String str = node.getId().toString().replaceAll(" ","");		
		
		if(groundrep>0)
		{
				if(symbols.containsKey(str) && isBuiltin(str))
				{
					for(int i = 0;i<symbols.get(str).size();i++)
					{
						if(symbols.get(str).get(i).getSymbolInfo().equals("BuiltIn primitive"))
						{
							SymInfo si = new SymInfo(node.getId(),"Ident (Groundrep.)",currentStructNum);
							symbols.get(str).set(i,si);
							break;
						}
					}
				}
				else if(symbols.containsKey(str))
				{
					ArrayList<SymInfo> temp = symbols.get(str);
					SymInfo si = new SymInfo(node.getId(),"Ident (Groundrep.)",currentStructNum);
					temp.add(si);
					symbols.put(str,temp);
				}
				else
				{
					ArrayList<SymInfo> temp = new ArrayList<SymInfo>();
					SymInfo si = new SymInfo(node.getId(),"Ident (Groundrep.)",currentStructNum);
					temp.add(si);
					symbols.put(str,temp);
				}
		}
		else if(currentInParams>0)
		{		
			//currentParams.add(str);
		
			if(symbols.containsKey(str))
			{
				ArrayList<SymInfo> temp = symbols.get(str);
				SymInfo si = new SymInfo(node.getId(),"Ident (Prolog Variable)",currentStructNum);
				temp.add(si);
				symbols.put(str,temp);
			}
			else
			{
				ArrayList<SymInfo> temp = new ArrayList<SymInfo>();
				SymInfo si = new SymInfo(node.getId(),"Ident (Prolog Variable)",currentStructNum);
				temp.add(si);
				symbols.put(str,temp);
			}
		}
		
        if(node.getId() != null)
        {
            node.getId().apply(this);
        }		
        outAVarPattern(node);
    }
//***************************************************************************************************************************************************
//Expressions

	@Override
    public void caseAFunctionExp(AFunctionExp node)
    {
        inAFunctionExp(node);
        if(node.getId() != null)
        {
            node.getId().apply(this);			
			String str = node.getId().toString().replace(" ","");
			boolean found = false;
			if(symbols.get(str) != null)
			{
				for(int k = 0;k<symbols.get(str).size();k++)
				{
					if(symbols.get(str).get(k).getSymbolInfo().equals("Function or Process"))
					{
						int v = symbols.get(str).get(k).getStructCount();
						if(v == currentStructNum)
						{
							found = true;
							//keep in symbols, do not replace !!!
							break;
						}
					}
					
				}
			}
			if(!found)
			{
				if(symbols.get(str) == null)
				{
					ArrayList<SymInfo> temp = new ArrayList<SymInfo>();
					SymInfo si = new SymInfo(node.getId(),"Function or Process",currentStructNum);
					temp.add(si);
					symbols.put(str,temp);
				}
				else
				{

					for(int i = 0;i<symbols.get(str).size();i++)
					{
						if(symbols.get(str).get(i).getSymbolInfo().equals("BuiltIn primitive"))
						{
							SymInfo si = new SymInfo(node.getId(),"Function or Process",currentStructNum);
							symbols.get(str).set(i,si);
							found = true;
							break;
						}
					}			
					if(!found)
					{
						ArrayList<SymInfo> temp = symbols.get(str);
						SymInfo si = new SymInfo(node.getId(),"Function or Process",currentStructNum);
						temp.add(si);
						symbols.put(str,temp);
					}
				}
			}
        }
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
        outAFunctionExp(node);
    }	
	
    @Override
    public void caseALambdaExp(ALambdaExp node)
    {
        inALambdaExp(node);
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
        outALambdaExp(node);
    }
	
    @Override
    public void caseALetWithinExp(ALetWithinExp node)
    {
        inALetWithinExp(node);
		structCount++;
		struct.put(structCount,currentStructNum);
		currentStructNum = structCount;
        {
            List<PDef> copy = new ArrayList<PDef>(node.getDefs());
            for(PDef e : copy)
            {
                e.apply(this);
            }
        }
		currentStructNum = struct.get(currentStructNum);
        if(node.getProc9() != null)
        {
            node.getProc9().apply(this);
        }
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
			if(symbols.get(str) != null)
			{
				ArrayList<SymInfo> temp = symbols.get(str);
				SymInfo si = new SymInfo(node.getId(),"Ident (Prolog Variable)",currentStructNum);
				if(inComprGenerator>0)
				{
					si.setComprehensionArg(true);
				}
				temp.add(si);
				symbols.put(str,temp);
			}
			else
			{
				ArrayList<SymInfo> temp = new ArrayList<SymInfo>();
				SymInfo si = new SymInfo(node.getId(),"Ident (Prolog Variable)",currentStructNum);
				if(inComprGenerator>0)
				{
					si.setComprehensionArg(true);
				}
				temp.add(si);
				symbols.put(str,temp);
			}
			
		}
		else if(isBuiltin(str))
		{
			if(symbols.get(str)!= null)
			{
				boolean found = false;
				for(int i = 0;i<symbols.get(str).size();i++)
				{
					if(symbols.get(str).get(i).getSymbolInfo().equals("BuiltIn primitive"))
					{
						found = true;
						break;
					}
				}
				if(!found)
				{
					ArrayList<SymInfo> temp = symbols.get(str);
					SymInfo si = new SymInfo(node.getId(),"BuiltIn primitive",0);
					temp.add(si);
					symbols.put(str,temp);
				}
			}
			else
			{
				ArrayList<SymInfo> temp = new ArrayList<SymInfo>();
				SymInfo si = new SymInfo(node.getId(),"BuiltIn primitive",0);
				temp.add(si);
				symbols.put(str,temp);
			}
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
			if(symbols.get(str)!= null)
			{
				boolean found = false;
				for(int i = 0;i<symbols.get(str).size();i++)
				{
					if(symbols.get(str).get(i).getSymbolInfo().equals("BuiltIn primitive"))
					{
						found = true;
					}
				}
				if(!found)
				{
					ArrayList<SymInfo> temp = symbols.get(str);
					SymInfo si = new SymInfo(node.getId(),"BuiltIn primitive",0);
					temp.add(si);
					symbols.put(str,temp);
				}
			}
			else
			{
				ArrayList<SymInfo> temp = new ArrayList<SymInfo>();
				SymInfo si = new SymInfo(node.getId(),"BuiltIn primitive",0);
				temp.add(si);
				symbols.put(str,temp);
			}
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
	
//***************************************************************************************************************************************************
	public HashMap<String,ArrayList<SymInfo>> getSymbols()
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
