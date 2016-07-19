import java.io.IOException;
import java.util.Locale;
import CSPMparser.analysis.*;
import CSPMparser.node.*;
import java.util.*;
import java.io.*;



public class PrologGenerator extends DepthFirstAdapter
{
	private boolean renamingActivated; //print symbol-numbers or not (needed for cspmf comparison)
	private boolean assertionNegated;
	private boolean expectingPattern;
	private boolean inSubtypeDef;
	private final PrologTermOutput p;
	private int currentInParams;
	private ArrayList<SymInfo> symbols; //Identifier,Counter
	private int groundrep;
	private boolean currentInChannel;
	private HashMap<Integer,HashMap<String,String>> generatorArgs;
	private int currentInPredicate;	
	private BlockTree tree;	
	private boolean printSrcLoc;
	private boolean patternRequired;	
	private ArrayList<CommentInfo> commentList;
	
	public PrologGenerator(final PrologTermOutput pto,ArrayList<SymInfo> symbols, boolean printSrcLoc, boolean rna, ArrayList<CommentInfo> commentList) 
	{
		assertionNegated = false;
		currentInPredicate = 0;
		tree = new BlockTree();
		generatorArgs = new HashMap<Integer,HashMap<String,String>>();	
		expectingPattern = false;
		inSubtypeDef = false;
		currentInChannel = false;
		p = pto;
		currentInParams = 0;
		groundrep = 0;
		this.symbols = symbols;
		this.printSrcLoc = printSrcLoc;
		renamingActivated = rna;
		this.commentList = commentList;
		patternRequired = false;
	}
	
//***************************************************************************************************************************************************
//Definitions

	@Override
    public void caseADefsStart(ADefsStart node)
    {
        inADefsStart(node);
        {
            List<PDef> copy = new ArrayList<PDef>(node.getDef());
            for(PDef e : copy)
            {
                e.apply(this);
				if(!currentInChannel)
				{
					p.fullstop();
				}
				currentInChannel = false;
            }
        }
		if(renamingActivated)
		{
			//print pragmas
			for(CommentInfo cinfo : commentList)
			{
				if(!cinfo.formula.equals(""))
				{
					String tempFormula = cinfo.formula;
					tempFormula = tempFormula.replaceAll("\n","\\\\n");
					tempFormula = tempFormula.replaceAll("\r","\\\\r");
					String tempCom = cinfo.pragmaComment;
					tempCom = tempCom.replaceAll("\n","\\\\n");
					tempCom = tempCom.replaceAll("\r","\\\\r");
					
					p.appendTerm("'pragma'(");
					if(cinfo.isLTL)
					p.appendTerm("'assert_ltl \""+tempFormula+"\"");
					else
					p.appendTerm("'assert_ctl \""+tempFormula+"\"");	
					if(!tempCom.equals(""))
					p.appendTerm(" \""+tempCom+"\"')");
					else
					p.appendTerm("')");
					p.fullstop();
				}
			}
			//print comments		
			for(CommentInfo cinfo : commentList)
			{
				if(cinfo.isMultilineComment)
				{
					String comment = cinfo.comment;
					p.openTerm("comment");
					if(cinfo.formula.equals(""))
					p.openTerm("blockComment");
					else
					p.openTerm("pragmaComment");
					comment = comment.replaceAll("\n","\\\\n");
					comment = comment.replaceAll("\r","\\\\r");
					p.printAtom(comment);
					p.closeTerm();
					if(printSrcLoc)
					{
						p.openTerm("src_position");
						p.printNumber(cinfo.startLine);
						p.printNumber(cinfo.startColumn);
						p.printNumber(cinfo.offset);
						p.printNumber(cinfo.len);
						p.closeTerm();
					}
					else
					{
						p.printAtom("no_loc_info_available");
					}
					p.closeTerm();
				}
				else
				{
					p.openTerm("comment");
					p.openTerm("lineComment");
					p.printAtom(cinfo.comment);
					if(printSrcLoc)
					{
						p.openTerm("src_position");
						p.printNumber(cinfo.startLine);
						p.printNumber(cinfo.startColumn);
						p.printNumber(cinfo.offset);
						p.printNumber(cinfo.len);
						p.closeTerm();
					}
					else
					{
						p.printAtom("no_loc_info_available");
					}
					p.closeTerm();
					p.closeTerm();				
				}
				p.fullstop();
			}
			//print Symbols	
			for(int i = 0;i<symbols.size();i++)
			{
				p.openTerm("symbol");
				//print symbol reference name
				String ref = symbols.get(i).symbolReference;
				if(ref.startsWith("_"))
					p.printAtom(ref.substring(1));
				else
					p.printAtom(ref);
				//print symbol name
				p.printAtom(symbols.get(i).symbolName);
				//print location of symbol
				printSrcLoc(symbols.get(i).node);
				//print type of symbol
				p.printAtom(symbols.get(i).symbolInfo);
				p.closeTerm();
				p.fullstop();
			}
		}	
        outADefsStart(node);
    }
	
//***************************************************************************************************************************************************
//Types

    @Override
    public void caseADtypeTypes(ADtypeTypes node)
    {
        inADtypeTypes(node);
        if(node.getTypedef() != null)
        {
			p.openTerm("dataTypeDef");
            node.getTypedef().apply(this);
			p.closeTerm();
        }
        outADtypeTypes(node);
    }

    @Override
    public void caseAStypeTypes(AStypeTypes node)
    {
        inAStypeTypes(node);
        if(node.getTypedef() != null)
        {
			p.openTerm("subTypeDef");
			inSubtypeDef = true;
            node.getTypedef().apply(this);
			inSubtypeDef = false;
			p.closeTerm();

        }
        outAStypeTypes(node);
    }

    @Override
    public void caseANtypeTypes(ANtypeTypes node)
    {
        inANtypeTypes(node);
		p.openTerm("nameType");
        if(node.getId() != null)
        {
            node.getId().apply(this);	
			if(renamingActivated)
			p.printAtom(getSymbol(node.getId()));
			else
			p.printAtom(getNameFromReference(getSymbol(node.getId())));	
        }
        if(node.getTypeExp() != null)
        {
			p.openTerm("type");
            node.getTypeExp().apply(this);
			p.closeTerm();
        }
		p.closeTerm();
        outANtypeTypes(node);
    }

    @Override
    public void caseATypedefTypes(ATypedefTypes node)
    {
        inATypedefTypes(node);
        if(node.getId() != null)
        {
            node.getId().apply(this);
			if(renamingActivated)
			p.printAtom(getSymbol(node.getId()));
			else
			p.printAtom(getNameFromReference(getSymbol(node.getId())));	
        }
		p.openList();
        {
            List<PTypes> copy = new ArrayList<PTypes>(node.getTypedefList());
            for(PTypes e : copy)
            {
                e.apply(this);
				p.closeTerm();
            }		
        }
		p.closeList();
        outATypedefTypes(node);
    }

    @Override
    public void caseAClauseTypes(AClauseTypes node)
    {
        inAClauseTypes(node);
		if(node.getDotted() == null)
		{
			p.openTerm("constructor");
		}
		else
		{
			p.openTerm("constructorC");
		}
		
        if(node.getClauseName() != null)
        {
            node.getClauseName().apply(this);
			if(!inSubtypeDef)//In datatype, it defines a new constructor. In subtype, it looks for the reference of a existing constructor
				if(renamingActivated)
				p.printAtom(getSymbol(node.getClauseName()));
				else
				p.printAtom(getNameFromReference(getSymbol(node.getClauseName())));					
			else
				printSymbol(node.getClauseName().toString().replace(" ",""),node.getClauseName());
        }
        if(node.getDotted() != null)
        {
            node.getDotted().apply(this);
        }
        outAClauseTypes(node);
    }
	

//***************************************************************************************************************************************************
//Channel

	@Override
    public void caseAChannelDef(AChannelDef node)
    {
		currentInChannel = true;
		ArrayList<Node> nodeList = new ArrayList<Node>();
        inAChannelDef(node);
        {
            List<PId> copy = new ArrayList<PId>(node.getChanList());
            for(PId e : copy)
            {
                e.apply(this);
				nodeList.add(e);
            }
        }
        if(node.getChanType() != null)
        {
			for(int i = 0;i<nodeList.size();i++)
			{
				p.openTerm("channel");
				
				if(renamingActivated)
				p.printAtom(getSymbol(nodeList.get(i)));
				else
				p.printAtom(getNameFromReference(getSymbol(nodeList.get(i))));	
			
				p.openTerm("type");
				node.getChanType().apply(this);
				p.closeTerm();
				p.closeTerm();
				p.fullstop();
			}
        }
		else
		{
			
			for(int i = 0;i<nodeList.size();i++)
			{
				p.openTerm("channel");
				if(renamingActivated)
				p.printAtom(getSymbol(nodeList.get(i)));
				else
				p.printAtom(getNameFromReference(getSymbol(nodeList.get(i))));	
				p.openTerm("type");
				p.printAtom("dotUnitType");
				p.closeTerm();
				p.closeTerm();
				p.fullstop();
			}
		}
        outAChannelDef(node);
    }
	
//***************************************************************************************************************************************************
//Type Eypressions

    @Override
    public void caseADottedTypeExp(ADottedTypeExp node)
    {
        inADottedTypeExp(node);
        {
			p.openTerm("dotTupleType");
			p.openList();
            List<PTypeExp> copy = new ArrayList<PTypeExp>(node.getTypeExp0());
            for(PTypeExp e : copy)
            {
                e.apply(this);
            }
			p.closeList();
			p.closeTerm();
        }
        outADottedTypeExp(node);
    }

    @Override
    public void caseAParTypeExp(AParTypeExp node)
    {
        inAParTypeExp(node);
        {
			p.openTerm("typeTuple");
			p.openList();
            List<PTypeExp> copy = new ArrayList<PTypeExp>(node.getTypeExpList());
            for(PTypeExp e : copy)
            {
                e.apply(this);
            }
			p.closeList();
			p.closeTerm();
        }
        outAParTypeExp(node);
    }
	
    @Override
    public void caseASetTypeExp(ASetTypeExp node)
    {
        inASetTypeExp(node);
        if(node.getSet() != null)
        {
            node.getSet().apply(this);
        }
        outASetTypeExp(node);
    }

    @Override
    public void caseAIdTypeExp(AIdTypeExp node)
    {
        inAIdTypeExp(node);
		List<PArguments> copy = new ArrayList<PArguments>(node.getArguments());
		String str = node.getId().toString().replace(" ","");
		
        if(copy.size()>1)
        {
			p.openTerm("agent_call_curry");
        }
        else if(copy.size()>0)
        {
			p.openTerm("agent_call");
			printSrcLoc(node.getId());
        }
		
        if(node.getId() != null)
        {
            node.getId().apply(this);
        }
		
		printSymbol(str,node.getId());
		
        {
            if(copy.size()>1) //f(1)(1) has size 2
			{
				p.openList();
				for(PArguments e : copy)
				{
					e.apply(this);
				}
				p.closeList();
			}
			else if(copy.size() == 1)
			{
				for(PArguments e : copy)
				{
					e.apply(this);

				}		
			}
			
			if(copy.size()>0)
			p.closeTerm();
        }
		
        outAIdTypeExp(node);
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
	//	currentParams.clear();
        outAExpressionDef(node);
    }
	
	@Override
    public void caseAFunctionExp(AFunctionExp node)
    {
        inAFunctionExp(node);
		List<PParameters> copy = new ArrayList<PParameters>(node.getParameters());
		if(copy.size()>1)
		p.openTerm("agent_curry");
		else
		p.openTerm("agent");
        if(node.getId() != null)
        {
            node.getId().apply(this);
			String str = node.getId().toString().replace(" ","");
			if(getSymbol(node.getId()).equals(""))//Redefinition of Function!
			{				
				if(renamingActivated)
				p.openTerm(findInSymbols(str));
				else
				p.openTerm(str);	
			}
			else
			{
				if(renamingActivated)
				p.openTerm(getSymbol(node.getId()));
				else
				p.openTerm(getNameFromReference(getSymbol(node.getId())));				
			}				
        }
		tree.newLeaf();
        {
			if(copy.size()>1)
			{
				for(PParameters e : copy)
				{
					p.openList();
					currentInParams += 1;
					e.apply(this);
					currentInParams -= 1;
					p.closeList();
				}
			}
			else
			{
				for(PParameters e : copy)
				{
					currentInParams += 1;
					e.apply(this);
					currentInParams -= 1;
				}				
			}
			p.closeTerm();
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
		p.openTerm("bindval");
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
//***************************************************************************************************************************************************
//Patterns
	
    @Override
    public void caseAVarPattern(AVarPattern node)
    {
        inAVarPattern(node);
		String str = node.getId().toString().replaceAll(" ","");
		
        if(node.getId() != null)
        {
            node.getId().apply(this);
        }
		
		if(getSymbol(node.getId()).startsWith("_"))
		{
			if(renamingActivated)
			p.printVariable(getSymbol(node.getId()));
			else
			p.printVariable("_"+getNameFromReference(getSymbol(node.getId())));
		}
		else
		{
			if(renamingActivated)
			p.printAtom(getSymbol(node.getId()));
			else
			p.printAtom(getNameFromReference(getSymbol(node.getId())));
		}
		
			//Add to blocks
			tree.addSymbol(str,getSymbol(node.getId()));				
		
        outAVarPattern(node);
    }
	
    @Override
    public void caseADoublePattern(ADoublePattern node)
    {
        inADoublePattern(node);
        {
            List<PPattern> copy = new ArrayList<PPattern>(node.getDoubleList());
			if(copy.size()>1)
			{
				p.openTerm("alsoPattern");
				p.openList();
			}
            for(PPattern e : copy)
            {
                e.apply(this);
            }
			if(copy.size()>1)
			{
				p.closeList();
				p.closeTerm();
			}
        }
        outADoublePattern(node);
    }

    @Override
    public void caseADotPattern(ADotPattern node)
    {
        inADotPattern(node);
        {
            List<PPattern> copy = new ArrayList<PPattern>(node.getDotList());
			if(copy.size()>1)
			{
				p.openTerm("dotpat");
				p.openList();
			}
            for(PPattern e : copy)
            {
                e.apply(this);
            }
			if(copy.size()>1)
			{ 
				p.closeList();
				p.closeTerm();
			}

        }
        outADotPattern(node);
    }
   

    @Override
    public void caseAConcatPattern(AConcatPattern node)
    {
        inAConcatPattern(node);
        {
            List<PPattern> copy = new ArrayList<PPattern>(node.getConcatList());
			if(copy.size()>1)
			{
				p.openTerm("appendPattern");
				p.openList();
			}
            for(PPattern e : copy)
            {
                e.apply(this);
            }
			if(copy.size()>1)
			{
				p.closeList();
				p.closeTerm();
			}
        }
        outAConcatPattern(node);
    }

    @Override
    public void caseAEmptySetPattern(AEmptySetPattern node)
    {
        inAEmptySetPattern(node);
		p.printAtom("emptySet");
        outAEmptySetPattern(node);
    }

    @Override
    public void caseASetPattern(ASetPattern node)
    {
        inASetPattern(node);
		p.openTerm("singleSetPat");
		p.openList();
        if(node.getPattern1() != null)
        {
            node.getPattern1().apply(this);
        }
		p.closeList();
		p.closeTerm();
        outASetPattern(node);
    }
	
    @Override
    public void caseAEmptyListPattern(AEmptyListPattern node)
    {
        inAEmptyListPattern(node);
		p.openTerm("listPat");
		p.openList();
		p.closeList();
		p.closeTerm();
        outAEmptyListPattern(node);
    }

    @Override
    public void caseAListPattern(AListPattern node)
    {
        inAListPattern(node);
        {
			p.openTerm("listPat");
			p.openList();
            List<PPattern> copy = new ArrayList<PPattern>(node.getPatternList());
            for(PPattern e : copy)
            {
                e.apply(this);
            }
			p.closeList();
			p.closeTerm();
        }
        outAListPattern(node);
    }

    @Override
    public void caseATuplePattern(ATuplePattern node)
    {
        inATuplePattern(node);
		p.openTerm("tuplePat");
		p.openList();
        if(node.getPattern1() != null)
        {
            node.getPattern1().apply(this);
        }
        {
            List<PPattern> copy = new ArrayList<PPattern>(node.getPatternList());
            for(PPattern e : copy)
            {
                e.apply(this);
            }
        }
		p.closeList();
		p.closeTerm();
        outATuplePattern(node);
    }

    @Override
    public void caseAWildcardPattern(AWildcardPattern node)
    {
        inAWildcardPattern(node);
		p.printVariable("_");
        if(node.getWildcard() != null)
        {
            node.getWildcard().apply(this);
        }
        outAWildcardPattern(node);
    }

    @Override
    public void caseAStringPattern(AStringPattern node)
    {
        inAStringPattern(node);
		p.openTerm("stringPat");
        if(node.getString() != null)
        {
            node.getString().apply(this);
			String temp = node.getString().getText();
			temp = temp.replaceAll("\"","");
			p.printString(temp);
        }
		p.closeTerm();
        outAStringPattern(node);
    }

    @Override
    public void caseACharPattern(ACharPattern node)
    {
        inACharPattern(node);
		p.openTerm("charPat");
        if(node.getChar() != null)
        {
            node.getChar().apply(this);
			String c = node.getChar().getText();
			c = c.replaceAll("\'","");
			p.printAtom(c);
        }
		p.closeTerm();
        outACharPattern(node);
    }

    @Override
    public void caseANumberPattern(ANumberPattern node)
    {
        inANumberPattern(node);
        if(node.getNumber() != null)
        {
			p.openTerm("int");
            node.getNumber().apply(this);
			int i = Integer.valueOf(node.getNumber().getText());
			p.printNumber(i);
			p.closeTerm();
        }
        outANumberPattern(node);
    }

    @Override
    public void caseATruePattern(ATruePattern node)
    {
        inATruePattern(node);
        if(node.getTrue() != null)
        {
            node.getTrue().apply(this);
        }
        outATruePattern(node);
    }

    @Override
    public void caseAFalsePattern(AFalsePattern node)
    {
        inAFalsePattern(node);
        if(node.getFalse() != null)
        {
            node.getFalse().apply(this);
        }
        outAFalsePattern(node);
    }	
//***************************************************************************************************************************************************	
//Expressions Right Side

    @Override
    public void caseAHideExp(AHideExp node)
    {
        inAHideExp(node);
		p.openTerm("\\");
        if(node.getProc1() != null)
        {
            node.getProc1().apply(this);
        }
        if(node.getBSlash() != null)
        {
            node.getBSlash().apply(this);
        }
        if(node.getProc2() != null)
        {
            node.getProc2().apply(this);
        }
		p.openTerm("src_span_operator");
		p.printAtom("no_loc_info_available");
		printSrcLoc(node.getBSlash());
		p.closeTerm();
		p.closeTerm();
        outAHideExp(node);
    }

    @Override
    public void caseAIleaveExp(AIleaveExp node)
    {
        inAIleaveExp(node);
		p.openTerm("|||");
        if(node.getProc2() != null)
        {
            node.getProc2().apply(this);
        }
        if(node.getILeaving() != null)
        {
            node.getILeaving().apply(this);
        }
        if(node.getProc3() != null)
        {
            node.getProc3().apply(this);
        }
		p.openTerm("src_span_operator");
		p.printAtom("no_loc_info_available");
		printSrcLoc(node.getILeaving());
		p.closeTerm();
		p.closeTerm();
        outAIleaveExp(node);
    }

    @Override
    public void caseAExceptExp(AExceptExp node)
    {
        inAExceptExp(node);
		p.openTerm("exception");
        if(node.getDotOp() != null)
        {
            node.getDotOp().apply(this);
        }
        if(node.getProc3() != null)
        {
            node.getProc3().apply(this);
        }
        if(node.getProc4() != null)
        {
            node.getProc4().apply(this);
        }
		printSrcLoc(node.getOpStart(),node.getOpEnd());
		p.closeTerm();
        outAExceptExp(node);
    }

    @Override
    public void caseAGenParExp(AGenParExp node)
    {
        inAGenParExp(node);
		p.openTerm("sharing");
        if(node.getDotOp() != null)
        {
            node.getDotOp().apply(this);
        }
        if(node.getProc3() != null)
        {
            node.getProc3().apply(this);
        }
        if(node.getProc4() != null)
        {
            node.getProc4().apply(this);
        }
		printSrcLoc(node.getOpStart(),node.getOpEnd());
		p.closeTerm();
        outAGenParExp(node);
    }

    @Override
    public void caseAAlphParExp(AAlphParExp node)
    {
        inAAlphParExp(node);
		p.openTerm("aParallel");
        if(node.getDotOpl() != null)
        {
            node.getDotOpl().apply(this);
        }
        if(node.getProc3() != null)
        {
            node.getProc3().apply(this);
        }
        if(node.getDotOpr() != null)
        {
            node.getDotOpr().apply(this);
        }
        if(node.getProc4() != null)
        {
            node.getProc4().apply(this);
        }
		printSrcLoc(node.getOpStart(),node.getOpEnd());
		p.closeTerm();
        outAAlphParExp(node);
    }

    @Override
    public void caseALinkedParExp(ALinkedParExp node)
    {
        inALinkedParExp(node);
		p.openTerm("lParallel");
        if(node.getLinkComp() != null)
        {
            node.getLinkComp().apply(this);
        }
        if(node.getProc3() != null)
        {
            node.getProc3().apply(this);
        }
        if(node.getProc4() != null)
        {
            node.getProc4().apply(this);
        }
		printSrcLoc(node.getOpStart(),node.getOpEnd());
		p.closeTerm();
        outALinkedParExp(node);
    }

    @Override
    public void caseAIntChoiceExp(AIntChoiceExp node)
    {
        inAIntChoiceExp(node);
		p.openTerm("|~|");
        if(node.getProc4() != null)
        {
            node.getProc4().apply(this);
        }
        if(node.getIChoice() != null)
        {
            node.getIChoice().apply(this);
        }
        if(node.getProc5() != null)
        {
            node.getProc5().apply(this);
        }
		
		p.openTerm("src_span_operator");
		p.printAtom("no_loc_info_available");
		printSrcLoc(node.getIChoice());
		p.closeTerm();
		p.closeTerm();
        outAIntChoiceExp(node);
    }

    @Override
    public void caseAExtChoiceExp(AExtChoiceExp node)
    {
        inAExtChoiceExp(node);
		p.openTerm("[]");
        if(node.getProc5() != null)
        {
            node.getProc5().apply(this);
        }
		if(node.getEChoice() != null)
        {
            node.getEChoice().apply(this);
        }
        if(node.getProc6() != null)
        {
            node.getProc6().apply(this);
        }
		p.openTerm("src_span_operator");
		p.printAtom("no_loc_info_available");
		printSrcLoc(node.getEChoice());
		p.closeTerm();
		p.closeTerm();
        outAExtChoiceExp(node);
    }
	
    @Override
    public void caseASyncExtExp(ASyncExtExp node)
    {
        inASyncExtExp(node);
		p.openTerm("syncExtChoice");
        if(node.getProc5() != null)
        {
            node.getProc5().apply(this);
        }
        if(node.getDotOp() != null)
        {
            node.getDotOp().apply(this);
        }
        if(node.getProc6() != null)
        {
            node.getProc6().apply(this);
        }
		printSrcLoc(node.getOpStart(),node.getOpEnd());
		p.closeTerm();
        outASyncExtExp(node);
    }

    @Override
    public void caseAInterruptExp(AInterruptExp node)
    {
        inAInterruptExp(node);
		p.openTerm("/\\");
        if(node.getProc6() != null)
        {
            node.getProc6().apply(this);
        }
        if(node.getInterrupt() != null)
        {
            node.getInterrupt().apply(this);
        }
        if(node.getProc7() != null)
        {
            node.getProc7().apply(this);
        }
		p.openTerm("src_span_operator");
		p.printAtom("no_loc_info_available");
		printSrcLoc(node.getInterrupt());
		p.closeTerm();
		p.closeTerm();
        outAInterruptExp(node);
    }

    @Override
    public void caseASyncInterruptExp(ASyncInterruptExp node)
    {
        inASyncInterruptExp(node);
		p.openTerm("syncInterrupt");
        if(node.getProc6() != null)
        {
            node.getProc6().apply(this);
        }
        if(node.getDotOp() != null)
        {
            node.getDotOp().apply(this);
        }
        if(node.getProc7() != null)
        {
            node.getProc7().apply(this);
        }
		printSrcLoc(node.getOpStart(),node.getOpEnd());
		p.closeTerm();
        outASyncInterruptExp(node);
    }

    @Override
    public void caseASlidingChoiceExp(ASlidingChoiceExp node)
    {
        inASlidingChoiceExp(node);
		p.openTerm("[>");
        if(node.getProc7() != null)
        {
            node.getProc7().apply(this);
        }
        if(node.getTimeout() != null)
        {
            node.getTimeout().apply(this);
        }
        if(node.getProc8() != null)
        {
            node.getProc8().apply(this);
        }
		p.openTerm("src_span_operator");
		p.printAtom("no_loc_info_available");
		printSrcLoc(node.getTimeout());
		p.closeTerm();
		p.closeTerm();
        outASlidingChoiceExp(node);
    }

    @Override
    public void caseASeqCompositionExp(ASeqCompositionExp node)
    {
        inASeqCompositionExp(node);
		p.openTerm(";");
        if(node.getProc8() != null)
        {
            node.getProc8().apply(this);
        }
        if(node.getSemicolon() != null)
        {
            node.getSemicolon().apply(this);
        }
        if(node.getProc9() != null)
        {
            node.getProc9().apply(this);
        }
		p.openTerm("src_span_operator");
		p.printAtom("no_loc_info_available");
		printSrcLoc(node.getSemicolon());
		p.closeTerm();
		p.closeTerm();
        outASeqCompositionExp(node);
    }

    @Override
    public void caseAGuardExp(AGuardExp node)
    {
        inAGuardExp(node);
		p.openTerm("&");
        if(node.getDotOp() != null)
        {
            node.getDotOp().apply(this);
        }
        if(node.getProc9() != null)
        {
            node.getProc9().apply(this);
        }
		p.closeTerm();
        outAGuardExp(node);
    }

    @Override
    public void caseAPrefixExp(APrefixExp node)
    {
        inAPrefixExp(node);
		p.openTerm("prefix");
        if(node.getEvent() != null)
        {
            node.getEvent().apply(this);
        }
        if(node.getPrefix() != null)
        {
            node.getPrefix().apply(this);
        }
        if(node.getProc9() != null)
        {
            node.getProc9().apply(this);
        }
		printSrcLoc(node.getPrefix());
		p.closeTerm();
        outAPrefixExp(node);
    }

    @Override
    public void caseALambdaExp(ALambdaExp node)
    {
        inALambdaExp(node);
		tree.newLeaf();
        {
            List<PPattern> copy = new ArrayList<PPattern>(node.getPatternList());
			p.openTerm("lambda");
			p.openList();
            for(PPattern e : copy)
            {
                e.apply(this);
            }
			p.closeList();
        }
        if(node.getProc9() != null)
        {
            node.getProc9().apply(this);
        }
		p.closeTerm();
		tree.returnToParent();
        outALambdaExp(node);
    }

    @Override
    public void caseALetWithinExp(ALetWithinExp node)
    {
        inALetWithinExp(node);
		tree.newLeaf();		
        {
			p.openTerm("let");
			p.openList();
            List<PDef> copy = new ArrayList<PDef>(node.getDefs());
            for(PDef e : copy)
            {
                e.apply(this);
            }
			p.closeList();
        }		
        if(node.getProc9() != null)
        {
            node.getProc9().apply(this);
        }
		tree.returnToParent();
		p.closeTerm();
        outALetWithinExp(node);
    }

    @Override
    public void caseAIfElseExp(AIfElseExp node)
    {
        inAIfElseExp(node);
		p.openTerm("ifte");
        if(node.getIf() != null)
        {
            node.getIf().apply(this);
        }
        if(node.getBoolExp() != null)
        {
            node.getBoolExp().apply(this);
        }
        if(node.getThen() != null)
        {
            node.getThen().apply(this);
        }
        if(node.getProc1() != null)
        {
            node.getProc1().apply(this);
        }
        if(node.getElse() != null)
        {
            node.getElse().apply(this);
        }
        if(node.getProc9() != null)
        {
            node.getProc9().apply(this);
        }
		printSrcLoc(node.getIf());
		printSrcLoc(node.getThen());
		printSrcLoc(node.getElse());
		p.closeTerm();
        outAIfElseExp(node);
    }
//***************************************************************************************************************************************************
//Replicated Operators
		
    @Override
    public void caseAExtChoiceRepExp(AExtChoiceRepExp node)
    {
        inAExtChoiceRepExp(node);
		p.openTerm("repChoice");
        if(node.getStmts() != null)
        {
            node.getStmts().apply(this);
        }
		if(node.getProc9() != null)
        {
            node.getProc9().apply(this);
        }
		printSrcLoc(node.getStmts());
		p.closeTerm();
        outAExtChoiceRepExp(node);
    }

    @Override
    public void caseAIntChoiceRepExp(AIntChoiceRepExp node)
    {
        inAIntChoiceRepExp(node);
		p.openTerm("repInternalChoice");
        if(node.getStmts() != null)
        {
            node.getStmts().apply(this);
        }
		if(node.getProc9() != null)
        {
            node.getProc9().apply(this);
        }
		printSrcLoc(node.getStmts());
		p.closeTerm();
        outAIntChoiceRepExp(node);
    }

    @Override
    public void caseAILeaveRepExp(AILeaveRepExp node)
    {
        inAILeaveRepExp(node);
		p.openTerm("repInterleave");
        if(node.getStmts() != null)
        {
            node.getStmts().apply(this);
        }
		if(node.getProc9() != null)
        {
            node.getProc9().apply(this);
        }
		printSrcLoc(node.getStmts());
		p.closeTerm();
        outAILeaveRepExp(node);
    }

    @Override
    public void caseASeqCompositRepExp(ASeqCompositRepExp node)
    {
        inASeqCompositRepExp(node);
		p.openTerm("repSequence");
        if(node.getStmts() != null)
        {
            node.getStmts().apply(this);
        }
		if(node.getProc9() != null)
        {
            node.getProc9().apply(this);
        }
		printSrcLoc(node.getStmts());
		p.closeTerm();
        outASeqCompositRepExp(node);
    }

    @Override
    public void caseAAlphParRepExp(AAlphParRepExp node)
    {
        inAAlphParRepExp(node);
		p.openTerm("procRepAParallel");
        if(node.getStmts() != null)
        {
            node.getStmts().apply(this);
        }
		p.openTerm("pair");
        if(node.getDotOp() != null)
        {
            node.getDotOp().apply(this);
        }
		if(node.getProc9() != null)
        {
            node.getProc9().apply(this);
        }
		p.closeTerm();
		printSrcLoc(node.getStmts());
		p.closeTerm();
        outAAlphParRepExp(node);
    }
	
    @Override
    public void caseASharingRepExp(ASharingRepExp node)
    {
        inASharingRepExp(node);
		p.openTerm("procRepSharing");
        if(node.getValExp() != null)
        {
            node.getValExp().apply(this);
        }
        if(node.getStmts() != null)
        {
            node.getStmts().apply(this);
        }
		if(node.getProc9() != null)
        {
            node.getProc9().apply(this);
        }
		printSrcLoc(node.getStmts());
		p.closeTerm();
        outASharingRepExp(node);
    }

    @Override
    public void caseALinkedParRepExp(ALinkedParRepExp node)
    {
        inALinkedParRepExp(node);
		p.openTerm("procRepLinkParallel");
        if(node.getLinkComp() != null)
        {
            node.getLinkComp().apply(this);
        }
        if(node.getStmts() != null)
        {
            node.getStmts().apply(this);
        }
		if(node.getProc9() != null)
        {
            node.getProc9().apply(this);
        }
		printSrcLoc(node.getStmts());
		p.closeTerm();
        outALinkedParRepExp(node);
    }

    @Override
    public void caseASyncParRepExp(ASyncParRepExp node)
    {
        inASyncParRepExp(node);
		p.openTerm("procRepSyncParallel");
        if(node.getDotOp() != null)
        {
            node.getDotOp().apply(this);
        }
        if(node.getStmts() != null)
        {
            node.getStmts().apply(this);
        }
		if(node.getProc9() != null)
        {
            node.getProc9().apply(this);
        }
		printSrcLoc(node.getStmts());
		p.closeTerm();
        outASyncParRepExp(node);
    }
	
//End of replicated operators
//***************************************************************************************************************************************************
  
	@Override
    public void caseARenamingExp(ARenamingExp node)
    {
        inARenamingExp(node);
        if(node.getRenameComp() != null)
        {
            node.getRenameComp().apply(this);
        }
        outARenamingExp(node);
    }

    @Override
    public void caseAERenamingExp(AERenamingExp node)
    {
        inAERenamingExp(node);
		p.openTerm("procRenaming");
		p.openList();
		p.closeList();
        if(node.getProc10() != null)
        {
            node.getProc10().apply(this);
        }
		printSrcLoc(node.getOpStart(),node.getOpEnd());
		p.closeTerm();
        outAERenamingExp(node);
    }

    @Override
    public void caseAEventExp(AEventExp node)
    {
        inAEventExp(node);
        
		List<PPattern> copy1 = new ArrayList<PPattern>(node.getF1List());
		List<PPattern> copy2 = new ArrayList<PPattern>(node.getF2List());
		
		if(copy1.size()>0 || copy2.size()>0)
		{
			printSrcLoc(node.getDpattern());
			p.openList();
			for(PPattern e : copy1)
			{
				e.apply(this);
			}	
			
			for(PPattern e : copy2)
			{
				e.apply(this);
			}
			p.closeList();
		}
		else
		{
			printSrcLoc(node.getDpattern());
			p.openList();
			p.closeList();
		}
        
        if(node.getDpattern() != null)
        {
            node.getDpattern().apply(this);
        }
        outAEventExp(node);
    }
	
    @Override
    public void caseANondetInputPattern(ANondetInputPattern node)
    {
        inANondetInputPattern(node);
		if(node.getRestriction() == null)
		{
			p.openTerm("nondetIn");
		}
		else
		{
			p.openTerm("nondetInGuard");
		}
        if(node.getPattern1() != null)
        {
            node.getPattern1().apply(this);
        }
        if(node.getRestriction() != null)
        {
            node.getRestriction().apply(this);
        }
		
		p.closeTerm();
        outANondetInputPattern(node);
    }
	
    @Override
    public void caseAInputPattern(AInputPattern node)
    {
        inAInputPattern(node);
		
		if(node.getRestriction() == null)
		{
			p.openTerm("in");
		}
		else
		{
			p.openTerm("inGuard");
		}
        if(node.getPattern1() != null)
        {
            node.getPattern1().apply(this);
        }
        if(node.getRestriction() != null)
        {
            node.getRestriction().apply(this);
        }

		p.closeTerm();
        outAInputPattern(node);
    }

    @Override
    public void caseAOutputPattern(AOutputPattern node)
    {
        inAOutputPattern(node);
		p.openTerm("out");
        if(node.getDotOp() != null)
        {
            node.getDotOp().apply(this);
        }
		p.closeTerm();
        outAOutputPattern(node);
    }
	
    @Override
    public void caseADpatternExp(ADpatternExp node)
    {
        inADpatternExp(node);
        {
            List<PExp> copy = new ArrayList<PExp>(node.getDoubleList2());
			if(copy.size()>1)
			{
				p.openTerm("alsoPattern");
				p.openList();
			}
            for(PExp e : copy)
            {
                e.apply(this);
            }
			if(copy.size()>1)
			{
				p.closeList();
				p.closeTerm();
			}
        }
        outADpatternExp(node);
    }

    @Override
    public void caseADotOpExp(ADotOpExp node)
    {
        inADotOpExp(node);
        {
            List<PExp> copy = new ArrayList<PExp>(node.getDotOpList());
			if(copy.size()>1)
			{
				if(patternRequired)
				p.openTerm("dotpat");
				else
				p.openTerm("dotTuple");
			
				p.openList();
			}
            for(PExp e : copy)
            {
                e.apply(this);
            }
			if(copy.size()>1)
			{
				p.closeList();
				p.closeTerm();
			}
        }
        outADotOpExp(node);
    }

    @Override
    public void caseAOrExp(AOrExp node)
    {
        inAOrExp(node);
		p.openTerm("bool_or");
        if(node.getBoolExp() != null)
        {
            node.getBoolExp().apply(this);
        }
        if(node.getBoolExp2() != null)
        {
            node.getBoolExp2().apply(this);
        }
		p.closeTerm();
        outAOrExp(node);
    }

    @Override
    public void caseAAndExp(AAndExp node)
    {
        inAAndExp(node);
		p.openTerm("bool_and");
        if(node.getBoolExp2() != null)
        {
            node.getBoolExp2().apply(this);
        }
        if(node.getBoolExp3() != null)
        {
            node.getBoolExp3().apply(this);
        }
		p.closeTerm();
        outAAndExp(node);
    }

    @Override
    public void caseANotExp(ANotExp node)
    {
        inANotExp(node);
		p.openTerm("bool_not");
        if(node.getBoolExp3() != null)
        {
            node.getBoolExp3().apply(this);
        }
		p.closeTerm();
        outANotExp(node);
    }

    @Override
    public void caseAEqualExp(AEqualExp node)
    {
        inAEqualExp(node);
		p.openTerm(node.getEqual().getText());
        if(node.getBoolExp4() != null)
        {
            node.getBoolExp4().apply(this);
        }
        if(node.getEqual() != null)
        {
            node.getEqual().apply(this);
        }
        if(node.getValExp() != null)
        {
            node.getValExp().apply(this);
        }
		p.closeTerm();
        outAEqualExp(node);
    }
	
    @Override
    public void caseAOrderingLgeExp(AOrderingLgeExp node)
    {
        inAOrderingLgeExp(node);
		p.openTerm(node.getLge().getText());
        if(node.getBoolExp4() != null)
        {
            node.getBoolExp4().apply(this);
        }
        if(node.getLge() != null)
        {
            node.getLge().apply(this);
        }
        if(node.getValExp() != null)
        {
            node.getValExp().apply(this);
        }
		p.closeTerm();
        outAOrderingLgeExp(node);
    }

    @Override
    public void caseAOrderingLessExp(AOrderingLessExp node)
    {
        inAOrderingLessExp(node);
		p.openTerm("<");
        if(node.getBoolExp4() != null)
        {
            node.getBoolExp4().apply(this);
        }
        if(node.getValExp() != null)
        {
            node.getValExp().apply(this);
        }
		p.closeTerm();
        outAOrderingLessExp(node);
    }

    @Override
    public void caseAOrderingGreaterExp(AOrderingGreaterExp node)
    {
        inAOrderingGreaterExp(node);
		p.openTerm(">");
        if(node.getBoolExp4() != null)
        {
            node.getBoolExp4().apply(this);
        }
        if(node.getValExp() != null)
        {
            node.getValExp().apply(this);
        }
		p.closeTerm();
        outAOrderingGreaterExp(node);
    }

    @Override
    public void caseAAdditionExp(AAdditionExp node)
    {
        inAAdditionExp(node);
		p.openTerm("+");
        if(node.getValExp() != null)
        {
            node.getValExp().apply(this);
        }
        if(node.getValExp1() != null)
        {
            node.getValExp1().apply(this);
        }
		p.closeTerm();
        outAAdditionExp(node);
    }

    @Override
    public void caseASubtractionExp(ASubtractionExp node)
    {
        inASubtractionExp(node);
		p.openTerm("-");
        if(node.getValExp() != null)
        {
            node.getValExp().apply(this);
        }
        if(node.getValExp1() != null)
        {
            node.getValExp1().apply(this);
        }
		p.closeTerm();
        outASubtractionExp(node);
    }

    @Override
    public void caseAMultiplicationExp(AMultiplicationExp node)
    {
        inAMultiplicationExp(node);
		p.openTerm(node.getMulDivMod().getText());
        if(node.getValExp1() != null)
        {
            node.getValExp1().apply(this);
        }
        if(node.getMulDivMod() != null)
        {
            node.getMulDivMod().apply(this);
        }
        if(node.getValExp2() != null)
        {
            node.getValExp2().apply(this);
        }
		p.closeTerm();
        outAMultiplicationExp(node);
    }
	
    @Override
    public void caseAUnMinusExp(AUnMinusExp node)
    {
        inAUnMinusExp(node);		
		String str = node.getValExp2().toString().replaceAll(" ","");	
		if(str.matches("\\d+"))
		 {
			p.openTerm("int");
			int x = Integer.parseInt(str);
			x = -x;
			p.printNumber(x);
			p.closeTerm();
		}
		else
		{
			p.openTerm("negate");
			if(node.getValExp2() != null)
			{
				node.getValExp2().apply(this);
			}
			p.closeTerm();
		}

        outAUnMinusExp(node);
    }

    @Override
    public void caseALengthExp(ALengthExp node)
    {
        inALengthExp(node);
		p.openTerm("#");
        if(node.getHash() != null)
        {
            node.getHash().apply(this);
        }
        if(node.getLength() != null)
        {
            node.getLength().apply(this);
        }
		p.closeTerm();
        outALengthExp(node);
    }
	
    @Override
    public void caseAConcatExp(AConcatExp node)
    {
        inAConcatExp(node);
		if(patternRequired)
		{	
			p.openTerm("appendPattern");
			p.openList();		
		}
		else
		{
			p.openTerm("^");
		}
        if(node.getAtom() != null)
        {
            node.getAtom().apply(this);
        }
        if(node.getCat() != null)
        {
            node.getCat().apply(this);
        }
        {
            List<PExp> copy = new ArrayList<PExp>(node.getConcat());
            for(PExp e : copy)
            {
                e.apply(this);
            }
        }
		if(patternRequired)
		{
			p.closeList();		
		}
		p.closeTerm();
        outAConcatExp(node);
    }	

	
//***************************************************************************************************************************************************
//Sequencees

    @Override
    public void caseAEmptySeqExp(AEmptySeqExp node)
    {
        inAEmptySeqExp(node);
		if(patternRequired)
		{
			p.openTerm("listPat");
			p.openList();
			p.closeList();
			p.closeTerm();
		}
		else
		{
			p.openTerm("listExp");
			p.openTerm("rangeEnum");
			p.openList();
			if(node.getSeqOpen() != null)
			{
				node.getSeqOpen().apply(this);
			}
			if(node.getSeqClose() != null)
			{
				node.getSeqClose().apply(this); // Do not delete these !!!
			}
			p.closeList();
			p.closeTerm();
			p.closeTerm();
		}
        outAEmptySeqExp(node);
    }

    @Override
    public void caseAExplSeqExp(AExplSeqExp node)
    {
        inAExplSeqExp(node);
		if(patternRequired)
		{
			p.openTerm("listPat");
			if(node.getExpressions() != null)
			{
				node.getExpressions().apply(this);
			}
			p.closeTerm();
		}
		else
		{
			p.openTerm("listExp");
			p.openTerm("rangeEnum");
			if(node.getSeqOpen() != null)
			{
				node.getSeqOpen().apply(this);
			}
			if(node.getExpressions() != null)
			{
				node.getExpressions().apply(this);
			}
			if(node.getSeqClose() != null)
			{
				node.getSeqClose().apply(this);
			}
			p.closeTerm();
			p.closeTerm();
		}
        outAExplSeqExp(node);
    }

    @Override
    public void caseARangedSeqExp(ARangedSeqExp node)
    {
        inARangedSeqExp(node);
		p.openTerm("listExp");
		p.openTerm("rangeClosed");
        if(node.getSeqOpen() != null)
        {
            node.getSeqOpen().apply(this);
        }
        if(node.getLval() != null)
        {
            node.getLval().apply(this);
        }
        if(node.getRval() != null)
        {
            node.getRval().apply(this);
        }
        if(node.getSeqClose() != null)
        {
            node.getSeqClose().apply(this);
        }
		p.closeTerm();
		p.closeTerm();
        outARangedSeqExp(node);
    }

    @Override
    public void caseAInfiniteSeqExp(AInfiniteSeqExp node)
    {
        inAInfiniteSeqExp(node);
		p.openTerm("listExp");
		p.openTerm("rangeOpen");
        if(node.getSeqOpen() != null)
        {
            node.getSeqOpen().apply(this);
        }
        if(node.getValExp() != null)
        {
            node.getValExp().apply(this);
        }
        if(node.getSeqClose() != null)
        {
            node.getSeqClose().apply(this);
        }
		p.closeTerm();
		p.closeTerm();
        outAInfiniteSeqExp(node);
    }

//***************************************************************************************************************************************************
//Sets

    @Override
    public void caseAEmptySetExp(AEmptySetExp node)
    {
        inAEmptySetExp(node);
		if(patternRequired)
		{
			p.printAtom("emptySet");
		}
		else
		{
			p.openTerm("setExp");
			p.openTerm("rangeEnum");
			p.openList();
			if(node.getBraceL() != null)
			{
				node.getBraceL().apply(this);
			}
			if(node.getBraceR() != null)
			{
				node.getBraceR().apply(this); // do not delete these braces!!!
			}
			p.closeList();
			p.closeTerm();
			p.closeTerm();
		}
        outAEmptySetExp(node);
    }

    @Override
    public void caseASetExp(ASetExp node)
    {
        inASetExp(node);
		if(patternRequired)
		{
			p.openTerm("singleSetPat");
			if(node.getExpressions() != null)
			{
				node.getExpressions().apply(this);
			}
			p.closeTerm();
		}
		else
		{
			p.openTerm("setExp");
			p.openTerm("rangeEnum");
			if(node.getBraceL() != null)
			{
				node.getBraceL().apply(this);
			}
			if(node.getExpressions() != null)
			{
				node.getExpressions().apply(this);
			}
			if(node.getBraceR() != null)
			{
				node.getBraceR().apply(this);
			}
			p.closeTerm();
			p.closeTerm();
		}
        outASetExp(node);
    }

    @Override
    public void caseARangedSetExp(ARangedSetExp node)
    {
        inARangedSetExp(node);
		p.openTerm("setExp");
		p.openTerm("rangeClosed");
        if(node.getBraceL() != null)
        {
            node.getBraceL().apply(this);
        }
        if(node.getLval() != null)
        {
            node.getLval().apply(this);
        }
        if(node.getRval() != null)
        {
            node.getRval().apply(this);
        }
        if(node.getBraceR() != null)
        {
            node.getBraceR().apply(this);
        }
		p.closeTerm();
		p.closeTerm();
        outARangedSetExp(node);
    }

    @Override
    public void caseAInfiniteSetExp(AInfiniteSetExp node)
    {
        inAInfiniteSetExp(node);
		p.openTerm("setExp");
		p.openTerm("rangeOpen");
        if(node.getBraceL() != null)
        {
            node.getBraceL().apply(this);
        }
        if(node.getValExp() != null)
        {
            node.getValExp().apply(this);
        }
        if(node.getBraceR() != null)
        {
            node.getBraceR().apply(this);
        }
		p.closeTerm();
		p.closeTerm();
        outAInfiniteSetExp(node);
    }

    @Override
    public void caseAEnumeratedSetExp(AEnumeratedSetExp node)
    {
        inAEnumeratedSetExp(node);
		p.openTerm("closure");
        if(node.getBraceL() != null)
        {
            node.getBraceL().apply(this);
        }
		if(node.getExpressions() != null)
		{
			node.getExpressions().apply(this);
		}
        if(node.getBraceR() != null)
        {
            node.getBraceR().apply(this);
        }
		p.closeTerm();
        outAEnumeratedSetExp(node);
    }


//***************************************************************************************************************************************************
//Comprehensions

    @Override
    public void caseALinkCompLinkComp(ALinkCompLinkComp node)
    {
        inALinkCompLinkComp(node);
		tree.newLeaf();
        {
			if(node.getStmts() == null)
			{
				p.openTerm("linkList");
			}
			else
			{
				p.openTerm("linkListComp");
				node.getStmts().apply(this);
			}
			p.openList();
            List<PExp> copy = new ArrayList<PExp>(node.getLcList());
			int count = 0;
            for(PExp e : copy)
            {
				if(count == 0)
				{
					p.openTerm("link");
				}
                e.apply(this);
				if(count == 1)
				{
					p.closeTerm();
				}
				count += 1;
				count %= 2;
            }
			p.closeList();
			p.closeTerm();
        }
		tree.returnToParent();
        outALinkCompLinkComp(node);
    }

    @Override
    public void caseARenameCompRenameComp(ARenameCompRenameComp node)
    {
        inARenameCompRenameComp(node);
		if(node.getStmts() != null)
			p.openTerm("procRenamingComp"); 
		else
			p.openTerm("procRenaming");	
		
		
		if(node.getProc10() != null && node.getStmts() != null) //Comprehension -> Different order!!!
		{
			node.getProc10().apply(this);
		}	
		tree.newLeaf();
		if(node.getStmts() != null)
		{
			node.getStmts().apply(this);
		}
		{
			int count = 0;
			List<PExp> copy = new ArrayList<PExp>(node.getRcList());
			p.openList();
			for(PExp e : copy)
			{
				if(count == 0)
				{
					p.openTerm("rename");
				}
				e.apply(this);
				if(count == 1)
				{
					p.closeTerm();
				}
				count += 1;
				count %= 2;
			}
			p.closeList();
		}
		tree.returnToParent();				
		if(node.getProc10() != null && node.getStmts() == null) //No Comprehension -> Different order!!!
		{
			node.getProc10().apply(this);
			printSrcLoc(node.getOpStart(),node.getOpEnd());
		}			
		p.closeTerm();
        outARenameCompRenameComp(node);
    }
	
//Sequence Comprehensions

    @Override
    public void caseAComprSeqExp(AComprSeqExp node)
    {
        inAComprSeqExp(node);
		
		tree.newLeaf();

		p.openTerm("listExp");
		p.openTerm("rangeEnum");		
        if(node.getSeqOpen() != null)
        {
            node.getSeqOpen().apply(this);
        }
		if(node.getExpressions() != null)
		{
			node.getExpressions().apply(this);
		}
		p.closeTerm();
        if(node.getStmts() != null)
        {
            node.getStmts().apply(this);
        }
        if(node.getSeqClose() != null)
        {
            node.getSeqClose().apply(this);
        }
		p.closeTerm();
		tree.returnToParent();
        outAComprSeqExp(node);
    }

    @Override
    public void caseARangedComprSeqExp(ARangedComprSeqExp node)
    {
        inARangedComprSeqExp(node);
		
		tree.newLeaf();

		p.openTerm("listExp");
		p.openTerm("rangeClosed");		
        if(node.getSeqOpen() != null)
        {
            node.getSeqOpen().apply(this);
        }
        if(node.getLval() != null)
        {
            node.getLval().apply(this);
        }
        if(node.getRval() != null)
        {
            node.getRval().apply(this);
        }
		p.closeTerm();
        if(node.getStmts() != null)
        {
            node.getStmts().apply(this);
        }
        if(node.getSeqClose() != null)
        {
            node.getSeqClose().apply(this);
        }
		p.closeTerm();
		tree.returnToParent();
        outARangedComprSeqExp(node);
    }

    @Override
    public void caseAInfiniteComprSeqExp(AInfiniteComprSeqExp node)
    {
        inAInfiniteComprSeqExp(node);
		
		tree.newLeaf();

		p.openTerm("listExp");
		p.openTerm("rangeOpen");
        if(node.getSeqOpen() != null)
        {
            node.getSeqOpen().apply(this);
        }
        if(node.getValExp() != null)
        {
            node.getValExp().apply(this);
        }
		p.closeTerm();
        if(node.getStmts() != null)
        {
            node.getStmts().apply(this);
        }
        if(node.getSeqClose() != null)
        {
            node.getSeqClose().apply(this);
        }
		p.closeTerm();
		tree.returnToParent();
        outAInfiniteComprSeqExp(node);
    }
	
//Set Comprehensions

    @Override
    public void caseAComprSetExp(AComprSetExp node)
    {
        inAComprSetExp(node);
		
		tree.newLeaf();
		
		p.openTerm("setExp");
		p.openTerm("rangeEnum");
        if(node.getBraceL() != null)
        {
            node.getBraceL().apply(this);
        }
		if(node.getExpressions() != null)
		{
			node.getExpressions().apply(this);
		}
		p.closeTerm();
        if(node.getStmts() != null)
        {
            node.getStmts().apply(this);
        }
        if(node.getBraceR() != null)
        {
            node.getBraceR().apply(this);
        }
		p.closeTerm();
		tree.returnToParent();
        outAComprSetExp(node);
    }

    @Override
    public void caseARangedComprSetExp(ARangedComprSetExp node)
    {
        inARangedComprSetExp(node);
		
		tree.newLeaf();

		p.openTerm("setExp");
		p.openTerm("rangeClosed");

        if(node.getBraceL() != null)
        {
            node.getBraceL().apply(this);
        }
        if(node.getLval() != null)
        {
            node.getLval().apply(this);
        }
        if(node.getRval() != null)
        {
            node.getRval().apply(this);
        }
		p.closeTerm();
        if(node.getStmts() != null)
        {
            node.getStmts().apply(this);
        }
        if(node.getBraceR() != null)
        {
            node.getBraceR().apply(this);
        }
		p.closeTerm();
		tree.returnToParent();
        outARangedComprSetExp(node);
    }

    @Override
    public void caseAInfiniteComprSetExp(AInfiniteComprSetExp node)
    {
        inAInfiniteComprSetExp(node);
		
		tree.newLeaf();

		p.openTerm("setExp");
		p.openTerm("rangeOpen");
        if(node.getBraceL() != null)
        {
            node.getBraceL().apply(this);
        }
        if(node.getValExp() != null)
        {
            node.getValExp().apply(this);
        }
		p.closeTerm();
        if(node.getStmts() != null)
        {
            node.getStmts().apply(this);
        }
        if(node.getBraceR() != null)
        {
            node.getBraceR().apply(this);
        }
		p.closeTerm();
		tree.returnToParent();
        outAInfiniteComprSetExp(node);
    }
	
	@Override
    public void caseAEnumeratedComprSetExp(AEnumeratedComprSetExp node)
    {
        inAEnumeratedComprSetExp(node);
		
		tree.newLeaf();
		
		p.openTerm("closureComp");
        if(node.getBraceL() != null)
        {
            node.getBraceL().apply(this);
        }
        if(node.getStmts() != null)
        {
            node.getStmts().apply(this);
        }
		if(node.getExpressions() != null)
		{
			node.getExpressions().apply(this);
		}
        if(node.getBraceR() != null)
        {
            node.getBraceR().apply(this);
        }
		p.closeTerm();
		tree.returnToParent();
        outAEnumeratedComprSetExp(node);
    }
//***************************************************************************************************************************************************
//Maps

    @Override
    public void caseAMapExp(AMapExp node)
    {
        inAMapExp(node);
        if(node.getParL() != null)
        {
            node.getParL().apply(this);
        }
		p.openTerm("mapExp");
		p.openList();
		
		List<PExp> copy = new ArrayList<PExp>(node.getMapList());
		for(PExp e : copy)
		{
			tree.newLeaf();
			e.apply(this);
			tree.returnToParent();
		}
		
		p.closeList();
		p.closeTerm();
        if(node.getParR() != null)
        {
            node.getParR().apply(this);
        }
        outAMapExp(node);
    }

    @Override
    public void caseAEmptyMapExp(AEmptyMapExp node)
    {
        inAEmptyMapExp(node);
        if(node.getEmptyMap() != null)
        {
            node.getEmptyMap().apply(this);
			p.printAtom("emptyMap");
        }
        outAEmptyMapExp(node);
    }
//***************************************************************************************************************************************************
//Tuples and Parenthesis

    @Override
    public void caseATupleExp(ATupleExp node)
    {
        inATupleExp(node);
		List<PArguments> copy = new ArrayList<PArguments>(node.getArguments());
        if(copy.size()>1)
        {
			p.openTerm("agent_call_curry");
        }
        else if(copy.size()>0)
        {
			p.openTerm("agent_call");
			printSrcLoc(node.getTuple());
        }

        if(node.getTuple() != null)
        {
			if(patternRequired)
			{
				p.openTerm("tuplePat");
				p.openList();
				node.getTuple().apply(this);
				p.closeList();
				p.closeTerm();
			}
			else
			{
				p.openTerm("tupleExp");
				node.getTuple().apply(this);
				p.closeTerm();
			}
        }
        {
            if(copy.size()>1) //f(1)(1) has size 2
			{
				p.openList();
				for(PArguments e : copy)
				{
					e.apply(this);
				}
				p.closeList();
			}
			else if(copy.size() == 1)
			{
				for(PArguments e : copy)
				{
					e.apply(this);
				}		
			}
			
			if(copy.size()>0)
			p.closeTerm();
		}
        outATupleExp(node);
    }
	
    @Override
    public void caseATupleTuple(ATupleTuple node)
    {
        inATupleTuple(node);
        if(node.getParL() != null)
        {
            node.getParL().apply(this);
        }
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
			p.openList();
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
			p.closeList();
		}
        if(node.getParR() != null)
        {
            node.getParR().apply(this);
        }
        outATupleTuple(node);
    }

    @Override
    public void caseAParenthesisExp(AParenthesisExp node)
    {
        inAParenthesisExp(node);
		List<PArguments> copy = new ArrayList<PArguments>(node.getArguments());
        if(copy.size()>1)
        {
			p.openTerm("agent_call_curry");
        }
        else if(copy.size()>0)
        {
			p.openTerm("agent_call");
			printSrcLoc(node.getParL(),node.getParR());
        }

        if(node.getParL() != null)
        {
            node.getParL().apply(this);
        }
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
        if(node.getParR() != null)
        {
            node.getParR().apply(this);
        }
        {
            if(copy.size()>1) //f(1)(1) has size 2
			{
				p.openList();
				for(PArguments e : copy)
				{
					e.apply(this);
				}
				p.closeList();
			}
			else if(copy.size() == 1)
			{
				for(PArguments e : copy)
				{
					e.apply(this);
				}		
			}
			
			if(copy.size()>0)
			p.closeTerm();
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
			p.openList();
            for(PExp e : copy)
            {
				tree.newLeaf();
                e.apply(this);
				tree.returnToParent();
            }
			p.closeList();
        }
        outAExpressionListExpressions(node);
    }	
//***************************************************************************************************************************************************
//Atoms       
 
    @Override
    public void caseAStringExp(AStringExp node)
    {
        inAStringExp(node);
        if(node.getString() != null)
        {
            node.getString().apply(this);
			p.openTerm("string");
			p.printString(node.getString().toString().replace("\"","").replace(" ",""));
			p.closeTerm();
        }
        outAStringExp(node);
    }

    @Override
    public void caseACharExp(ACharExp node)
    {
        inACharExp(node);
        if(node.getChar() != null)
        {
            node.getChar().apply(this);
			p.openTerm("char");
			p.printAtom(node.getChar().toString().replace("'","").replace(" ",""));
			p.closeTerm();
        }
        outACharExp(node);
    }

    @Override
    public void caseAWildcardExp(AWildcardExp node)
    {
        inAWildcardExp(node);
		p.printVariable("_");
        if(node.getWildcard() != null)
        {
            node.getWildcard().apply(this);
        }
        outAWildcardExp(node);
    }

    @Override
    public void caseANumberExp(ANumberExp node)
    {
        inANumberExp(node);
        if(node.getNumber() != null)
        {
            node.getNumber().apply(this);
			p.openTerm("int");
			int i = Integer.valueOf(node.getNumber().getText());
			p.printNumber(i);
			p.closeTerm();
        }
        outANumberExp(node);
    }

    @Override
    public void caseATrueExp(ATrueExp node)
    {
        inATrueExp(node);
        if(node.getTrue() != null)
        {
            node.getTrue().apply(this);
			p.printAtom("true");
        }
        outATrueExp(node);
    }
	
    @Override
    public void caseAFalseExp(AFalseExp node)
    {
        inAFalseExp(node);
        if(node.getFalse() != null)
        {
            node.getFalse().apply(this);
			p.printAtom("false");
        }
        outAFalseExp(node);
    }

	@Override
    public void caseAIdExp(AIdExp node)
    {
        inAIdExp(node);
		List<PArguments> copy = new ArrayList<PArguments>(node.getArguments());
		String str = node.getId().toString().replace(" ","");
        if(copy.size()>1)
        {
			p.openTerm("agent_call_curry");
        }
        else if(copy.size()>0)
        {
			p.openTerm("agent_call");
			printSrcLoc(node.getId());
        }
        if(node.getId() != null)
        {
            node.getId().apply(this);
        }
		if(patternRequired)
		{	
			//Add to blocks and print
			if(renamingActivated)
			p.printVariable(getSymbol(node.getId()));
			else
			p.printVariable("_"+getNameFromReference(getSymbol(node.getId())));
		
			tree.addSymbol(str,getSymbol(node.getId()));
		}
		else
		{
			printSymbol(str,node.getId());
			//Search and print!
		}	
        {
            if(copy.size()>1) //f(1)(1) has size 2
			{
				p.openList();
				for(PArguments e : copy)
				{
					e.apply(this);
				}
				p.closeList();
			}
			else if(copy.size() == 1)
			{
				for(PArguments e : copy)
				{
					e.apply(this);
				}		
			}
			
			if(copy.size()>0)
			p.closeTerm();
		}
        outAIdExp(node);
    }
//***************************************************************************************************************************************************
//Statements

    @Override
    public void caseAStmtListStmts(AStmtListStmts node)
    {
        inAStmtListStmts(node);
        {
			p.openList();
            List<PStmts> copy = new ArrayList<PStmts>(node.getStmtsList());
            for(PStmts e : copy)
            {
                e.apply(this);
            }
			p.closeList();
        }
        outAStmtListStmts(node);
    }

    @Override
    public void caseAGeneratorStmts(AGeneratorStmts node)
    {
        inAGeneratorStmts(node);
		p.openTerm("comprehensionGenerator");
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
		p.closeTerm();
        outAGeneratorStmts(node);
    }

    @Override
    public void caseAPredicateStmts(APredicateStmts node)
    {
        inAPredicateStmts(node);
		p.openTerm("comprehensionGuard");
		currentInPredicate += 1;
        if(node.getProc1() != null)
        {
            node.getProc1().apply(this);
        }
		currentInPredicate -= 1;
		p.closeTerm();
        outAPredicateStmts(node);
    }
//***************************************************************************************************************************************************
//Assertions
	
    @Override
    public void caseAModAssertion(AModAssertion node) //assert Proc [T= Proc
    {
        inAModAssertion(node);

		p.openTerm("assertRef");
		if(node.getAssert().getText().equals("assert"))
		p.printAtom("False");
		else
		p.printAtom("True");
	
        if(node.getLproc() != null)
        {
            node.getLproc().apply(this);
        }
		p.printAtom("Trace");
        if(node.getRproc() != null)
        {
            node.getRproc().apply(this);
        }
		printSrcLoc(node);
		p.closeTerm();
        outAModAssertion(node);
    }

    @Override
    public void caseAFmAssertion(AFmAssertion node) //assert Proc [F= Proc
    {
        inAFmAssertion(node);
		p.openTerm("assertRef");
		if(node.getAssert().getText().equals("assert"))
		p.printAtom("False");
		else
		p.printAtom("True");
        if(node.getLproc() != null)
        {
            node.getLproc().apply(this);
        }
		p.printAtom("Failure");
        if(node.getRproc() != null)
        {
            node.getRproc().apply(this);
        }
		printSrcLoc(node);
		p.closeTerm();
        outAFmAssertion(node);
    }

    @Override
    public void caseAFdAssertion(AFdAssertion node) //assert Proc [FD= Proc
    {
        inAFdAssertion(node);
        if(node.getAssert() != null)
        {
            node.getAssert().apply(this);
        }
		p.openTerm("assertRef");
		if(node.getAssert().getText().equals("assert"))
		p.printAtom("False");
		else
		p.printAtom("True");
        if(node.getLproc() != null)
        {
            node.getLproc().apply(this);
        }
		p.printAtom("FailureDivergence");
        if(node.getRproc() != null)
        {
            node.getRproc().apply(this);
        }
		printSrcLoc(node);
		p.closeTerm();
        outAFdAssertion(node);
    }
	
    @Override
    public void caseARefAssertion(ARefAssertion node)
    {
        inARefAssertion(node);
		p.openTerm("assertRef");
		if(node.getAssert().getText().equals("assert"))
		p.printAtom("False");
		else
		p.printAtom("True");
        if(node.getLproc() != null)
        {
            node.getLproc().apply(this);
        }
		p.printAtom("RefusalTesting");
        if(node.getRproc() != null)
        {
            node.getRproc().apply(this);
        }
		printSrcLoc(node);
		p.closeTerm();
        outARefAssertion(node);
    }
	
    @Override
    public void caseATauprioAssertion(ATauprioAssertion node)
    {
        inATauprioAssertion(node);
		p.openTerm("assertTauPrio");
		if(node.getAssert().getText().equals("assert"))
		p.printAtom("False");
		else
		p.printAtom("True");
        if(node.getLproc() != null)
        {
            node.getLproc().apply(this);
        }
        if(node.getAsOp() != null)
        {
            node.getAsOp().apply(this);
			String op = node.getAsOp().toString();
			if(op.indexOf("FD") >= 0)
			p.printAtom("TauFailureDivergence");
			else if(op.indexOf("F") >= 0)
			p.printAtom("TauFailure");
			else if(op.indexOf("T") >= 0)
			p.printAtom("TauTrace");
			else if(op.indexOf("R") >= 0)
			p.printAtom("TauRefusalTesting");
        }
        if(node.getMproc() != null)
        {
            node.getMproc().apply(this);
        }
        if(node.getRproc() != null)
        {
            node.getRproc().apply(this);
        }
		printSrcLoc(node);
		p.closeTerm();
        outATauprioAssertion(node);
    }
	
    @Override
    public void caseALtlAssertion(ALtlAssertion node)
    {
        inALtlAssertion(node);
		p.openTerm("assertLtl");
		if(node.getAssert().getText().equals("assert"))
		p.printAtom("False");
		else
		p.printAtom("True");
        if(node.getProc1() != null)
        {
            node.getProc1().apply(this);
        }
        if(node.getString() != null)
        {
            node.getString().apply(this);
			p.printAtom(node.getString().getText().replaceAll("\"",""));
        }
		printSrcLoc(node);
		p.closeTerm();
        outALtlAssertion(node);
    }

    @Override
    public void caseACtlAssertion(ACtlAssertion node)
    {
        inACtlAssertion(node);
		p.openTerm("assertCtl");
		if(node.getAssert().getText().equals("assert"))
		p.printAtom("False");
		else
		p.printAtom("True");	
        if(node.getProc1() != null)
        {
            node.getProc1().apply(this);
        }
        if(node.getString() != null)
        {
            node.getString().apply(this);
			p.printAtom(node.getString().getText().replaceAll("\"",""));
        }
		printSrcLoc(node);
		p.closeTerm();
        outACtlAssertion(node);
    }

    @Override
    public void caseADeadlockAssertion(ADeadlockAssertion node)
    {
        inADeadlockAssertion(node);
		if(node.getModel() == null)
			p.openTerm("assertModelCheck");
		else
			p.openTerm("assertModelCheckExt");	
		if(node.getAssert().getText().equals("assert"))
			p.printAtom("False");
		else
			p.printAtom("True");	
        if(node.getAssert() != null)
        {
            node.getAssert().apply(this);
        }
        if(node.getProc1() != null)
        {
            node.getProc1().apply(this);
        }
		p.printAtom("DeadlockFree");
        if(node.getModel() != null)
        {
            node.getModel().apply(this);
			String s = node.getModel().getText().replace(" ","");
			p.printAtom(s.substring(1,s.length()-1));
        }
		p.closeTerm();
        outADeadlockAssertion(node);
    }

    @Override
    public void caseADivergenceAssertion(ADivergenceAssertion node)
    {
        inADivergenceAssertion(node);
		if(node.getModel() == null)
			p.openTerm("assertModelCheck");
		else
			p.openTerm("assertModelCheckExt");	
		if(node.getAssert().getText().equals("assert"))
			p.printAtom("False");
		else
			p.printAtom("True");	
        if(node.getAssert() != null)
        {
            node.getAssert().apply(this);
        }
        if(node.getProc1() != null)
        {
            node.getProc1().apply(this);
        }
		p.printAtom("LivelockFree");
        if(node.getModel() != null)
        {
            node.getModel().apply(this);
			String s = node.getModel().getText().replace(" ","");
			p.printAtom(s.substring(1,s.length()-1));
        }
		p.closeTerm();
        outADivergenceAssertion(node);
    }

    @Override
    public void caseADeterministicAssertion(ADeterministicAssertion node)
    {
        inADeterministicAssertion(node);
		if(node.getModel() == null)
			p.openTerm("assertModelCheck");
		else
			p.openTerm("assertModelCheckExt");	
		if(node.getAssert().getText().equals("assert"))
			p.printAtom("False");
		else
			p.printAtom("True");	
        if(node.getAssert() != null)
        {
            node.getAssert().apply(this);
        }
        if(node.getProc1() != null)
        {
            node.getProc1().apply(this);
        }
		p.printAtom("Deterministic");
        if(node.getModel() != null)
        {
            node.getModel().apply(this);
			String s = node.getModel().getText().replace(" ","");
			p.printAtom(s.substring(1,s.length()-1));
        }
		p.closeTerm();
        outADeterministicAssertion(node);
    }

    @Override
    public void caseAHastraceAssertion(AHastraceAssertion node)
    {
        inAHastraceAssertion(node);
		if(node.getModel() == null)
			p.openTerm("assertHasTrace");
		else
			p.openTerm("assertHasTraceExt");	
		if(node.getAssert().getText().equals("assert"))
			p.printAtom("False");
		else
			p.printAtom("True");	
        if(node.getAssert() != null)
        {
            node.getAssert().apply(this);
        }
        if(node.getLproc() != null)
        {
            node.getLproc().apply(this);
        }
        if(node.getRproc() != null)
        {
            node.getRproc().apply(this);
        }
        if(node.getModel() != null)
        {
            node.getModel().apply(this);
			String s = node.getModel().getText().replace(" ","");
			p.printAtom(s.substring(1,s.length()-1));
        }
		p.closeTerm();
        outAHastraceAssertion(node);
    }
//***************************************************************************************************************************************************
//Transparent

    @Override
    public void caseATransparentDef(ATransparentDef node)
    {
        inATransparentDef(node);
        {
			p.openTerm("cspTransparent");
            List<PId> copy = new ArrayList<PId>(node.getIdList());
			p.openList();
            for(PId e : copy)
            {
                e.apply(this);
				if(renamingActivated)
				p.printAtom(getSymbol(e));
				else
				p.printAtom(getNameFromReference(getSymbol(e)));
            }
			p.closeList();
			p.closeTerm();
        }
        outATransparentDef(node);
    }
//***************************************************************************************************************************************************
//Print expressions

    @Override
    public void caseAPrintExpDef(APrintExpDef node)
    {
        inAPrintExpDef(node);
		p.openTerm("cspPrint");
        if(node.getProc1() != null)
        {
            node.getProc1().apply(this);
        }
		p.closeTerm();
        outAPrintExpDef(node);
    }	
//***************************************************************************************************************************************************
//Helpers	

	public String getSymbol(Node n)
	{
		for(int i = 0; i<symbols.size();i++)
		{
			if(symbols.get(i).node == n)
			{
				return symbols.get(i).symbolReference;
			}
		}
		return "";
	}	

	public void printSymbol(String str, Node n)
	{
		String reference = "";				
		//System.out.println("a search"+tree.tree+" "+tree.getCurrentBlockNumber()+"\n"+tree.blockStructure);		
		int saveCurrentBlockNumber = tree.getCurrentBlockNumber();	
		while(true)
		{
			if(tree.isDefined(str))
			{
					reference = tree.getSymbol(str);
					break;
			}
			else if(!findInSymbols(str).equals(""))
			{
					reference = findInSymbols(str);
					break;
			}
			else
			{			
				if(!tree.returnToParent())
				break;			
			}
		}
			
		if(getInfoFromReference(reference).equals("Ident (Groundrep.)"))
		{
			if(!renamingActivated)
			{
				reference = getNameFromReference(reference);		
			}	
			p.openTerm("val_of");
			p.printAtom(reference);
			printSrcLoc(getNodeFromReference(reference));
			p.closeTerm();
		}
		else if(reference.startsWith("_"))
		{
			if(!renamingActivated)
			{
				reference = "_"+getNameFromReference(reference);
			}
			p.printVariable(reference);
		}
		else if (reference.equals("") && isBuiltin(str))
		{
			p.printAtom(str);
			SymInfo si = new SymInfo(n,"BuiltIn primitive",tree.getCurrentBlockNumber(),str,str);
			symbols.add(si);
		}
		else if (!reference.equals(""))
		{
			if(!renamingActivated)
			{
				reference = getNameFromReference(reference);
			}
			p.printAtom(reference);
		}
		
		tree.setCurrentBlockNumber(saveCurrentBlockNumber);
	}
	
	public String findInSymbols(String s)
	{
		for(int i=symbols.size()-1; i>=0 ; i=i-1)
		{
			if(symbols.get(i).symbolName.equals(s)
			&& symbols.get(i).blockNumber == tree.getCurrentBlockNumber())
			return symbols.get(i).symbolReference;
		}
		return "";
	}
	
	public String getInfoFromReference(String str)
	{
		for(int i = 0; i< symbols.size();i++)
		{
			if(symbols.get(i).symbolReference.equals(str))
			return symbols.get(i).symbolInfo;
		}		
		return "";
	}
	
	public Node getNodeFromReference(String str)
	{
		for(int i = 0; i< symbols.size();i++)
		{
			if(symbols.get(i).symbolReference.equals(str))
			return symbols.get(i).node;
		}	
		return null;
	}

	public String getNameFromReference(String ref)
	{
		for(int i = 0; i< symbols.size();i++)
		{
			if(symbols.get(i).symbolReference.equals(ref))
			return symbols.get(i).symbolName;
		}	
		return null;
	}
	
	public boolean isNumeric(String str)  
	{  
	  try  
	  {  
		int d = Integer.parseInt(str);  
	  }  
	  catch(NumberFormatException nfe)  
	  {  
		return false;  
	  }  
	  return true;  
	}
	
    public boolean isBuiltin(String s)
	{
		String[] builtin = {"Bool","Char","Events","Int","Proc","False","True","card","diff","empty",
		"inter","Inter","member","seq","Seq","Set","union","Union","concat","elem","head","length",
		"null","set","tail","emptyMap","mapDelete","mapFromList","mapLookup","mapMember","mapToList",
		"mapUpdate","mapUpdateMultiple","Map","Inter","member","seq","error","show","seq","CHAOS","DIV",
		"RUN","WAIT","STOP","SKIP","extensions","productions"};
		
		if(Arrays.asList(builtin).contains(s))
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
    	if (this.printSrcLoc) 
		{
    		p.openTerm("src_span");
    		// src_loc(startline,startcolumn,endline,endcolumn,offset???,length)
    		p.printNumber(node.getStartPos().getLine());
    		p.printNumber(node.getStartPos().getPos());
    		p.printNumber(node.getEndPos().getLine());
    		p.printNumber(node.getEndPos().getPos());
    		// TODO: do we need this?? the offset (start line (file), start column (file)) to (start line (node), start position (node))
			p.printNumber(node.getStartPos().getPos()-1);
    		p.printNumber(node.getEndPos().getPos()-node.getStartPos().getPos());

    		p.closeTerm();
    	} 
		else 
		{
    		p.printAtom("no_loc_info_available");
    	}
    }
	
	
    public void printSrcLoc(Node startNode, Node endNode) 
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
    	if (this.printSrcLoc) 
		{
    		p.openTerm("src_span");
    		// src_loc(startline,startcolumn,endline,endcolumn,offset???,length)
    		p.printNumber(startNode.getStartPos().getLine());
    		p.printNumber(startNode.getStartPos().getPos());
    		p.printNumber(endNode.getEndPos().getLine());
    		p.printNumber(endNode.getEndPos().getPos());
    		// TODO: do we need this?? the offset (start line (file), start column (file)) to (start line (node), start position (node))
			p.printNumber(startNode.getStartPos().getPos()-1);
    		p.printNumber(endNode.getEndPos().getPos()-startNode.getStartPos().getPos());
    		p.closeTerm();
    	} 
		else 
		{
    		p.printAtom("no_loc_info_available");
    	}
    }
}
