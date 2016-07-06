
import java.io.IOException;
import java.util.Locale;
import CSPMparser.analysis.*;
import CSPMparser.node.*;
import java.util.*;
import java.io.*;
import de.prob.prolog.output.IPrologTermOutput;
import de.prob.prolog.output.StructuredPrologOutput;
import de.prob.prolog.term.PrologTerm;

public class PrologGenerator extends DepthFirstAdapter
{
	private final IPrologTermOutput p;
	private final String currentStateID;
	private int currentInParams;
	private HashMap<String,ArrayList<SymInfo>> symbols; //Identifier,Counter
	private ArrayList<String> currentParams;
	
	public PrologGenerator(final IPrologTermOutput pto,final String currentStateID) 
	{
		//super();
		this.p = pto;
		this.currentStateID = currentStateID;
		this.currentInParams = 0;
		symbols = new HashMap<String,ArrayList<SymInfo>>();
		currentParams = new ArrayList<String>();
	}

	protected void applyPrologGenerator(StructuredPrologOutput pto,String stateID, Start ast) 
	{
		final PrologGenerator prologGenerator = new PrologGenerator(pto,stateID);
		ast.apply(prologGenerator);
	}
	
	@Override
	public void defaultIn(final Node node) 
	{
	//	StringBuffer sb = new StringBuffer(node.getClass().getSimpleName());
	//	sb.setLength(sb.length() - 3);
	//  sb.deleteCharAt(0);
	//	String term = sb.toString().toLowerCase(Locale.ENGLISH);
	//	p.openTerm(term);
	//	p.openTerm("test");
	}
    @Override
	public void defaultOut(final Node node) 
	{
	//	p.closeTerm();
	}
	
	@Override
	public void inStart(final Start node) 
	{
		// Do not call default in Method
	}

	@Override
	public void outStart(final Start node) 
	{
		// Do not call default out Method
	}
	
	
	
	
	
	@Override
    public void caseADefsStart(ADefsStart node)
    {
        inADefsStart(node);
        {
			int count = 0;
            List<PDef> copy = new ArrayList<PDef>(node.getDef());
            for(PDef e : copy)
            {
                e.apply(this);
				p.fullstop();
            }
        }

		for (String key : symbols.keySet()) 
		{

			for(int i = 0;i<symbols.get(key).size();i++)
			{
				Node n = symbols.get(key).get(i).getNode();
				String s = "";
				p.openTerm("symbol");
				if(key.startsWith("_"))
				{
					s = key.substring(1);
				}
				else
				{
					s = key;
				}
				if(i == 0)
				{
					p.printAtom(s);
					p.printAtom(s);
				}
				else
				{
					p.printAtom(s+(i+1));
					p.printAtom(s);
				}				
		
				printSrcLoc(n);
				p.printAtom(symbols.get(key).get(i).getSymbolInfo());
				p.closeTerm();
				p.fullstop();
			}

		}
		
        outADefsStart(node);
    }
	
//***************************************************************************************************************************************************
//Expressions Left Side	
	@Override
    public void caseAExpressionDef(AExpressionDef node)
    {
        inAExpressionDef(node);
        if(node.getExp() != null)
        {
            node.getExp().apply(this);
        }
		printSrcLoc(node);
		p.closeTerm();
		currentParams.clear();
        outAExpressionDef(node);
    }
	
	@Override
    public void caseAFunctionExp(AFunctionExp node)
    {
        inAFunctionExp(node);
		p.openTerm("agent");
        if(node.getId() != null)
        {
            node.getId().apply(this);
			String id = node.getId().toString().replace(" ","");

			ArrayList<SymInfo> temp = new ArrayList<SymInfo>();
			SymInfo si = new SymInfo(node,"Function or Process");
			temp.add(si);
			symbols.put(id,temp);
				
			p.openTerm(node.getId().toString().replace(" ",""));
        }
        if(node.getParameters() != null)
        {
			currentInParams += 1;
            node.getParameters().apply(this);
			currentInParams -= 1;
			p.closeTerm();
        }
        if(node.getProc1() != null)
        {
            node.getProc1().apply(this);
        }
        outAFunctionExp(node);
    }
	
    @Override
    public void caseAPatternExp(APatternExp node)
    {
        inAPatternExp(node);
		p.openTerm("bindval");
        if(node.getPattern1() != null)
        {
            node.getPattern1().apply(this);
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
		
		if(currentInParams>0)
		{		
			currentParams.add(str);
		
			if(symbols.containsKey("_"+str))
			{
				ArrayList<SymInfo> temp = symbols.get("_"+str);
				SymInfo si = new SymInfo(node,"Ident (Prolog Variable)");
				temp.add(si);
				symbols.put("_"+str,temp);
			}
			else
			{
				ArrayList<SymInfo> temp = new ArrayList<SymInfo>();
				SymInfo si = new SymInfo(node,"Ident (Prolog Variable)");
				temp.add(si);
				symbols.put("_"+str,temp);
			}
		}
		
        if(node.getId() != null)
        {
            node.getId().apply(this);
        }
		

		if(currentInParams<1)
		{
			p.printAtom(str);
		}
		else
		{
			int j = symbols.get("_"+str).size();
			if(j == 1)
			{
				p.printVariable("_"+str);
			}
			else
			{
				p.printVariable("_"+str+j);
			}
		}
		
        outAVarPattern(node);
    }	
//***************************************************************************************************************************************************	
//Expressions	
	
	
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
		
		if(currentParams.contains(str))
		{
			int i = symbols.get("_"+str).size();
			p.printVariable("_"+str+i);
		}
		else if(!isBuiltin(str))
		{
			p.printAtom(str);
		}
		
        if(node.getTuple() != null)
        {
            node.getTuple().apply(this);
        }
        outAIdExp(node);
    }
	
	
	
	
	
	
	
	
	
	
//	@Override
//	public void caseTIdentifier(final TIdentifier node) 
//	{
//		p.printAtom(node.getText());
//	}
	
	@Override
	public void caseASkipBuiltin(final ASkipBuiltin node) 
	{
		p.openTerm("skip");
		printSrcLoc(node);
		p.closeTerm();
	}
	@Override
	public void caseAStopBuiltin(final AStopBuiltin node) 
	{
		p.openTerm("stop");
		printSrcLoc(node);
		p.closeTerm();
	}
	
//***************************************************************************************************************************************************
//Helpers	
    public boolean isBuiltin(String s)
	{
		ArrayList<String> builtin = new ArrayList<String>();

		builtin.add("STOP");
		builtin.add("SKIP");
		builtin.add("CHAOS");
		builtin.add("DIV");
		builtin.add("Events");
		builtin.add("Proc");
		builtin.add("Char"); 
		builtin.add("Bool");
		builtin.add("Int");
		builtin.add("true");
		builtin.add("false");
		builtin.add("True");
		builtin.add("False");	
		
		if(builtin.contains(s))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
    public void printSrcLoc(Node node) 
    {
		   /* Data type of src_loc in cspmf 
			* src_loc 
			{
			   fixedStartLine   = getStartLine s
			  ,fixedStartCol    = getStartCol s
			  ,fixedEndLine     = getEndLine e
			  ,fixedEndCol      = getEndCol e
			  ,fixedLen         = getEndOffset e - getStartOffset s
			  ,fixedStartOffset = getStartOffset s
			}
		  */
		p.openTerm("src_span");
        // src_loc(startline,startcolumn,endline,endcolumn,offset???,length)
		p.printNumber(node.getStartPos().getLine());
		p.printNumber(node.getStartPos().getPos());
		p.printNumber(node.getEndPos().getLine());
		p.printNumber(node.getEndPos().getPos());
		// TODO: do we need this?? the offset (start line (file), start column (file)) to (start line (node), start position (node))
		p.printNumber(node.getEndPos().getPos()-node.getStartPos().getPos());
		p.printNumber(node.getEndPos().getPos()-node.getStartPos().getPos());
		p.closeTerm();
    }
}
