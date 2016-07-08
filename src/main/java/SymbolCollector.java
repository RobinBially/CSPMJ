import java.io.IOException;
import java.util.Locale;
import CSPMparser.analysis.*;
import CSPMparser.node.*;
import java.util.*;
import java.io.*;




public class SymbolCollector extends DepthFirstAdapter
{

	private int currentInParams;
	private HashMap<String,ArrayList<SymInfo>> symbols; //Identifier,Counter
	private int groundrep;
	private ArrayList<String> currentParams;	
	
	public SymbolCollector()
	{
		currentInParams = 0;
		groundrep = 0;
		symbols = new HashMap<String,ArrayList<SymInfo>>();
		currentParams = new ArrayList<String>();
	}
//***************************************************************************************************************************************************
//Datatypes and Patterns	
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
				ArrayList<SymInfo> temp = new ArrayList<SymInfo>();
				SymInfo si = new SymInfo(e,"Channel");
				temp.add(si);
				symbols.put(str,temp);
            }
        }
        if(node.getChanType() != null)
        {
            node.getChanType().apply(this);
        }
        outAChannelDef(node);
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
				ArrayList<SymInfo> temp = new ArrayList<SymInfo>();
				SymInfo si = new SymInfo(node.getId(),"Ident (Groundrep.)");
				temp.add(si);
				symbols.put(str,temp);
		}
		else if(currentInParams>0)
		{		
			currentParams.add(str);
		
			if(symbols.containsKey("_"+str))
			{
				ArrayList<SymInfo> temp = symbols.get("_"+str);
				SymInfo si = new SymInfo(node.getId(),"Ident (Prolog Variable)");
				temp.add(si);
				symbols.put("_"+str,temp);
			}
			else
			{
				ArrayList<SymInfo> temp = new ArrayList<SymInfo>();
				SymInfo si = new SymInfo(node.getId(),"Ident (Prolog Variable)");
				temp.add(si);
				symbols.put("_"+str,temp);
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
			
			String id = node.getId().toString().replace(" ","");
			ArrayList<SymInfo> temp = new ArrayList<SymInfo>();
			SymInfo si = new SymInfo(node.getId(),"Function or Process");
			temp.add(si);
			symbols.put(id,temp);
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
    public void caseAIdExp(AIdExp node)
    {
        inAIdExp(node);
		String str = node.getId().toString().replace(" ","");

        if(node.getId() != null)
        {
            node.getId().apply(this);
        }
		//System.out.println(currentParams);
		
        if(node.getTuple() != null)
        {
        }

		
        outAIdExp(node);
    }
//***************************************************************************************************************************************************
	public HashMap<String,ArrayList<SymInfo>> getSymbols()
	{
		return symbols;
	}
}
