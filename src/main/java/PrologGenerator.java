import java.io.IOException;
import java.util.Locale;
import CSPMparser.analysis.*;
import CSPMparser.node.*;
import java.util.*;
import java.io.*;



public class PrologGenerator extends DepthFirstAdapter
{
	private boolean expectingPattern;
	private boolean currentInInput;
	private boolean currentInNondetInput;
	private final PrologTermOutput p;
	private int currentInParams;
	private HashMap<String,ArrayList<SymInfo>> symbols; //Identifier,Counter
	private int groundrep;
	private HashMap<String,String> currentParams;
	private HashMap<String,String> currentLambdaParams;
	
	private HashMap<Integer,HashMap<String,String>> letWithinArgs;
	private HashMap<Integer,HashMap<String,String>> generatorArgs;
	private int currentInPredicate;
	
	private int currentInLambdaLeft;
	private int currentInLambdaRight;
	private boolean currentInChannel;
	private int leftFromPrefix;
	
	private int structCount; //renumber structs
	private int currentStructNum; //Saves a reference to the current struct
	private TreeMap<Integer,Integer> struct;
	
	private boolean printSrcLoc;
	private boolean patternRequired;
	
	private ArrayList<CommentInfo> commentList;
	
	public PrologGenerator(final PrologTermOutput pto,HashMap<String,ArrayList<SymInfo>> symbols, boolean printSrcLoc, ArrayList<CommentInfo> commentList) 
	{
		currentInPredicate = 0;

		structCount = 0;
		currentStructNum = 0;
		struct = new TreeMap<Integer,Integer>();
		
		letWithinArgs = new HashMap<Integer,HashMap<String,String>>();
		generatorArgs = new HashMap<Integer,HashMap<String,String>>();
		
		expectingPattern = false;
		currentInInput = false;
		currentInNondetInput = false;
		leftFromPrefix = 0;
		currentInChannel = false;
		p = pto;
		currentInParams = 0;
		groundrep = 0;
		this.symbols = symbols;
		currentParams = new HashMap<String,String>();
		currentLambdaParams = new HashMap<String,String>();
		currentInLambdaLeft = 0;
		currentInLambdaRight = 0;
		this.printSrcLoc = printSrcLoc;
		this.commentList = commentList;
		patternRequired = false;
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
            node.getTypedef().apply(this);
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
			p.printAtom(node.getId().toString().replace(" ",""));
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
			p.printAtom(node.getId().toString().replace(" ",""));
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
			p.printAtom(node.getClauseName().toString().replace(" ",""));
        }
        if(node.getDotted() != null)
        {
            node.getDotted().apply(this);
        }
        outAClauseTypes(node);
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
		//print comments		
		for(CommentInfo cinfo : commentList)
		{
			if(cinfo.isMultilineComment)
			{
				String comment = cinfo.comment;
				p.openTerm("comment");
				p.openTerm("blockComment");
				comment = comment.replaceAll("\n","\\\\n");
				comment = comment.replaceAll("\r","\\\\r");
				p.printAtom(comment);
				p.openTerm("src_position");
				p.printNumber(cinfo.startLine);
				p.printNumber(cinfo.startColumn);
				p.printNumber(cinfo.offset);
				p.printNumber(cinfo.len);
				p.closeTerm();
				p.closeTerm();
				p.closeTerm();
			}
			else
			{
				p.openTerm("comment");
				p.openTerm("lineComment");
				p.printAtom(cinfo.comment);
				p.openTerm("src_position");
				p.printNumber(cinfo.startLine);
				p.printNumber(cinfo.startColumn);
				p.printNumber(cinfo.offset);
				p.printNumber(cinfo.len);
				p.closeTerm();
				p.closeTerm();
				p.closeTerm();				
			}
			p.fullstop();
		}
		
		int symbolCounter = 0;
		//print Symbols
		for (String key : symbols.keySet()) 
		{
			symbolCounter++;
			for(int i = 0;i<symbols.get(key).size();i++)
			{
				Node n = symbols.get(key).get(i).getNode();
				p.openTerm("symbol");

				if(i == 0)
				{
					p.printAtom(key);
					p.printAtom(key);
				}
				else
				{
					p.printAtom(key+(i+1));
					p.printAtom(key);
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
//Channel

	@Override
    public void caseAChannelDef(AChannelDef node)
    {
		currentInChannel = true;
		ArrayList<String> strList = new ArrayList<String>();
        inAChannelDef(node);
        {
            List<PId> copy = new ArrayList<PId>(node.getChanList());
            for(PId e : copy)
            {
                e.apply(this);
				String str = e.toString().replace(" ","");
				strList.add(str);
            }
        }
        if(node.getChanType() != null)
        {
			for(int i = 0;i<strList.size();i++)
			{
				p.openTerm("channel");
				p.printAtom(strList.get(i));
				p.openTerm("type");
				node.getChanType().apply(this);
				p.closeTerm();
				p.closeTerm();
				p.fullstop();
			}
        }
		else
		{
			
			for(int i = 0;i<strList.size();i++)
			{
				p.openTerm("channel");
				p.printAtom(strList.get(i));
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
		String str = node.getId().toString().replace(" ","");
        if(node.getId() != null)
        {
            node.getId().apply(this);
        }
        if(node.getLambda() != null)
        {
			p.openTerm("agent_call");
			printSrcLoc(node.getId());
        }

		for(int i = 0;i<symbols.get(str).size();i++)
		{
			if(symbols.get(str).get(i).getSymbolInfo().equals("Ident (Groundrep.)"))
			{
				p.openTerm("val_of");
				p.printAtom(str);
				printSrcLoc(node.getId());
				p.closeTerm();
			}
			else if(symbols.get(str).get(i).getSymbolInfo().equals("BuiltIn primitive"))
			{
				printBuiltin(str,node.getId());
			}
			else
			{
				p.printAtom(str);
			}
		}
		
        if(node.getLambda() != null)
        {
            node.getLambda().apply(this);
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
			String str = node.getId().toString().replace(" ","");
			for(int k = 0;k<symbols.get(str).size();k++)
			{
				if(symbols.get(str).get(k).getSymbolInfo().equals("Function or Process")
					&& (symbols.get(str).get(k).getStructCount() == currentStructNum))
				{	
					if(k>0)
					{
						p.openTerm(str+(k+1));
					}
					else
					{
						p.openTerm(str);
					}
					break;		
				}
			}
			
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
		
		if(groundrep>0)
		{
			for(int k = 0;k<symbols.get(str).size();k++)
			{
				if(symbols.get(str).get(k).getNode() == node.getId())
				{
					if(k == 0)
					{
						p.printAtom(str);
					}
					else
					{
						p.printAtom(str+(k+1));
					}
					break;
				}
			}
		}
		else if(currentInLambdaLeft>0)
		{
			for(int k = 0;k<symbols.get(str).size();k++)
			{
				if(symbols.get(str).get(k).getNode() == node.getId())
				{
					if(k == 0)
					{
						p.printVariable("_"+str);
						currentLambdaParams.put(str,str);
					}
					else
					{
						p.printVariable("_"+str+(k+1));
						currentLambdaParams.put(str,str+(k+1));
					}
					break;
				}
			}			
		}
		else if(currentInParams>0)
		{
			for(int k = 0;k<symbols.get(str).size();k++)
			{
				//Input and nondetInput replace current parameters
				if(k == 0 && currentParams.containsKey(str))
				{
					currentParams.remove(str);
				}
				else if(currentParams.containsKey(str+(k+1)))
				{
					currentParams.remove(str+(k+1));
				}
				if(symbols.get(str).get(k).getNode() == node.getId())
				{
					if(k == 0)
					{
						currentParams.put(str,str);
						p.printVariable("_"+str);
					}
					else
					{
						currentParams.put(str,str+(k+1));
						p.printVariable("_"+str+(k+1));
					}
					break;
				}
			}
		}
		
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
        outAEmptySetPattern(node);
    }

    @Override
    public void caseASetPattern(ASetPattern node)
    {
        inASetPattern(node);
        if(node.getPattern1() != null)
        {
            node.getPattern1().apply(this);
        }
        outASetPattern(node);
    }
	
    @Override
    public void caseAEmptyListPattern(AEmptyListPattern node)
    {
        inAEmptyListPattern(node);
        outAEmptyListPattern(node);
    }

    @Override
    public void caseAListPattern(AListPattern node)
    {
        inAListPattern(node);
        {
            List<PPattern> copy = new ArrayList<PPattern>(node.getPatternList());
            for(PPattern e : copy)
            {
                e.apply(this);
            }
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
        if(node.getProc3() != null)
        {
            node.getProc3().apply(this);
        }
        if(node.getOpStart() != null)
        {
            node.getOpStart().apply(this);
        }
        if(node.getEvent() != null)
        {
            node.getEvent().apply(this);
        }
        if(node.getProc4() != null)
        {
            node.getProc4().apply(this);
        }
		printSrcLoc(node.getOpStart());
		p.closeTerm();
        outAExceptExp(node);
    }

    @Override
    public void caseAGenParExp(AGenParExp node)
    {
        inAGenParExp(node);
		p.openTerm("sharing");
        if(node.getProc3() != null)
        {
            node.getProc3().apply(this);
        }
        if(node.getOpStart() != null)
        {
            node.getOpStart().apply(this);
        }
        if(node.getEvent() != null)
        {
            node.getEvent().apply(this);
        }
        if(node.getProc4() != null)
        {
            node.getProc4().apply(this);
        }
		printSrcLoc(node.getOpStart());
		p.closeTerm();
        outAGenParExp(node);
    }

    @Override
    public void caseAAlphParExp(AAlphParExp node)
    {
        inAAlphParExp(node);
		p.openTerm("aParallel");
        if(node.getProc3() != null)
        {
            node.getProc3().apply(this);
        }
        if(node.getOpStart() != null)
        {
            node.getOpStart().apply(this);
        }
        if(node.getEventl() != null)
        {
            node.getEventl().apply(this);
        }
        if(node.getEventr() != null)
        {
            node.getEventr().apply(this);
        }
        if(node.getProc4() != null)
        {
            node.getProc4().apply(this);
        }
		printSrcLoc(node.getOpStart());
		p.closeTerm();
        outAAlphParExp(node);
    }

    @Override
    public void caseALinkedParExp(ALinkedParExp node)
    {
        inALinkedParExp(node);
		p.openTerm("lParallel");

        if(node.getOpStart() != null)
        {
            node.getOpStart().apply(this);
        }
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
		printSrcLoc(node.getOpStart());
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
        if(node.getOpStart() != null)
        {
            node.getOpStart().apply(this);
        }
        if(node.getEvent() != null)
        {
            node.getEvent().apply(this);
        }
        if(node.getProc6() != null)
        {
            node.getProc6().apply(this);
        }
		printSrcLoc(node.getOpStart());
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
        if(node.getSyncIntL() != null)
        {
            node.getSyncIntL().apply(this);
        }
        if(node.getEvent() != null)
        {
            node.getEvent().apply(this);
        }
        if(node.getProc7() != null)
        {
            node.getProc7().apply(this);
        }
		printSrcLoc(node.getSyncIntL());
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
		leftFromPrefix += 1;
        if(node.getEvent() != null)
        {
            node.getEvent().apply(this);
        }
		leftFromPrefix -= 1;
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
        {
            List<PPattern> copy = new ArrayList<PPattern>(node.getPatternList());
			p.openTerm("lambda");
			p.openList();
			currentInLambdaLeft +=1;
			currentInParams +=1;
            for(PPattern e : copy)
            {
                e.apply(this);
            }
			currentInParams -=1;
			currentInLambdaLeft -=1;
			p.closeList();
        }
        if(node.getProc9() != null)
        {
			currentInLambdaRight +=1;
            node.getProc9().apply(this);
			//System.out.println(currentLambdaParams+"\n"+currentParams);
			currentInLambdaRight -=1;
        }
		currentLambdaParams = new HashMap<String,String>(); //reset
		p.closeTerm();
        outALambdaExp(node);
    }

    @Override
    public void caseALetWithinExp(ALetWithinExp node)
    {
        inALetWithinExp(node);
		structCount++;
		struct.put(structCount,currentStructNum);		
		currentStructNum = structCount;

		letWithinArgs.put(structCount,new HashMap<String,String>(currentParams));		
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
		currentStructNum = struct.get(currentStructNum);
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
        if(node.getEvent() != null)
        {
            node.getEvent().apply(this);
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
        if(node.getEvent() != null)
        {
            node.getEvent().apply(this);
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
        if(node.getProc10() != null)
        {
            node.getProc10().apply(this);
        }
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
		else if(leftFromPrefix > 0)
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
		p.openTerm("listExp");
		p.openTerm("rangeEnum");
		p.openList();
        if(node.getSeqOpen() != null)
        {
            node.getSeqOpen().apply(this);
        }
        if(node.getSeqClose() != null)
        {
            node.getSeqClose().apply(this);
        }
		p.closeList();
		p.closeTerm();
		p.closeTerm();
        outAEmptySeqExp(node);
    }

    @Override
    public void caseAExplSeqExp(AExplSeqExp node)
    {
        inAExplSeqExp(node);
		p.openTerm("listExp");
		p.openTerm("rangeEnum");
		p.openList();
        if(node.getSeqOpen() != null)
        {
            node.getSeqOpen().apply(this);
        }
        if(node.getArguments() != null)
        {
            node.getArguments().apply(this);
        }
        if(node.getSeqClose() != null)
        {
            node.getSeqClose().apply(this);
        }
		p.closeList();
		p.closeTerm();
		p.closeTerm();
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
		p.openTerm("setExp");
		p.openTerm("rangeEnum");
		p.openList();
        if(node.getBraceL() != null)
        {
            node.getBraceL().apply(this);
        }
        if(node.getBraceR() != null)
        {
            node.getBraceR().apply(this);
        }
		p.closeList();
		p.closeTerm();
		p.closeTerm();
        outAEmptySetExp(node);
    }

    @Override
    public void caseASetExp(ASetExp node)
    {
        inASetExp(node);
		p.openTerm("setExp");
		p.openTerm("rangeEnum");
		p.openList();
        if(node.getBraceL() != null)
        {
            node.getBraceL().apply(this);
        }
        if(node.getArguments() != null)
        {
            node.getArguments().apply(this);
        }
        if(node.getBraceR() != null)
        {
            node.getBraceR().apply(this);
        }
		p.closeList();
		p.closeTerm();
		p.closeTerm();
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
		p.openList();
        if(node.getBraceL() != null)
        {
            node.getBraceL().apply(this);
        }
        if(node.getArguments() != null)
        {
            node.getArguments().apply(this);
        }
        if(node.getBraceR() != null)
        {
            node.getBraceR().apply(this);
        }
		p.closeList();
		p.closeTerm();
        outAEnumeratedSetExp(node);
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
		currentStructNum = struct.get(currentStructNum);
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
	
        if(node.getProc10() != null)
        {
            node.getProc10().apply(this);
        }
		
		structCount++;
		struct.put(structCount,currentStructNum);
		currentStructNum = structCount;
        {
			int count = 0;
            List<PExp> copy = new ArrayList<PExp>(node.getRcList());
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
        }
        if(node.getStmts() != null)
        {
            node.getStmts().apply(this);
        }
		p.closeTerm();
		currentStructNum = struct.get(currentStructNum);
        outARenameCompRenameComp(node);
    }
	
//Sequence Comprehensions

    @Override
    public void caseAComprSeqExp(AComprSeqExp node)
    {
        inAComprSeqExp(node);
		
		structCount++;
		struct.put(structCount,currentStructNum);
		currentStructNum = structCount;

		p.openTerm("listExp");
		p.openTerm("rangeEnum");
		p.openList();		
        if(node.getSeqOpen() != null)
        {
            node.getSeqOpen().apply(this);
        }
        if(node.getArguments() != null)
        {
            node.getArguments().apply(this);
        }
		p.closeList();
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
		currentStructNum = struct.get(currentStructNum);
        outAInfiniteComprSeqExp(node);
    }
	
//Set Comprehensions

    @Override
    public void caseAComprSetExp(AComprSetExp node)
    {
        inAComprSetExp(node);
		
		structCount++;
		struct.put(structCount,currentStructNum);
		currentStructNum = structCount;
		
		p.openTerm("setExp");
		p.openTerm("rangeEnum");
		p.openList();
        if(node.getBraceL() != null)
        {
            node.getBraceL().apply(this);
        }
        if(node.getArguments() != null)
        {
            node.getArguments().apply(this);
        }
		p.closeList();
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
		
		p.openTerm("closureComp");
        if(node.getBraceL() != null)
        {
            node.getBraceL().apply(this);
        }
        if(node.getStmts() != null)
        {
            node.getStmts().apply(this);
        }
		p.openList();
        if(node.getArguments() != null)
        {
            node.getArguments().apply(this);
        }
		p.closeList();
        if(node.getBraceR() != null)
        {
            node.getBraceR().apply(this);
        }
		p.closeTerm();
		currentStructNum = struct.get(currentStructNum);
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
        if(node.getLbool() != null)
        {
            node.getLbool().apply(this);
        }
        if(node.getRbool() != null)
        {
            node.getRbool().apply(this);
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
		if(node.getLambda() != null)
		{
			p.openTerm("agent_call");
			printSrcLoc(node.getTuple());		
		}
        if(node.getTuple() != null)
        {
			p.openTerm("tupleExp");
            node.getTuple().apply(this);
			p.closeTerm();
        }
        if(node.getLambda() != null)
        {
			p.openList();
            node.getLambda().apply(this);
			p.closeList();
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

        }
		else
		{
			p.openList();
			if(node.getProc1() != null)
			{
				node.getProc1().apply(this);
			}
			if(node.getComma() != null)
			{
				node.getComma().apply(this);
			}
			{
				List<PExp> copy = new ArrayList<PExp>(node.getArgumentsList());
				for(PExp e : copy)
				{
					e.apply(this);
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
		if(node.getLambda() != null)
		{
			p.openTerm("agent_call");
			printSrcLoc(node.getParL());		
		}
        if(node.getParL() != null)
        {
            node.getParL().apply(this);
        }
        if(node.getProc1() != null)
        {
            node.getProc1().apply(this);
        }
        if(node.getParR() != null)
        {
            node.getParR().apply(this);
        }
        if(node.getLambda() != null)
        {
            node.getLambda().apply(this);
			p.closeTerm();
        }
        outAParenthesisExp(node);
    }
	
    @Override
    public void caseALambdaLambda(ALambdaLambda node)
    {
        inALambdaLambda(node);
        {
			p.openList();
            List<PExp> copy = new ArrayList<PExp>(node.getArgumentsList());
            for(PExp e : copy)
            {
                e.apply(this);
            }
			p.closeList();
        }
        outALambdaLambda(node);
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
		String str = node.getId().toString().replace(" ","");
        if(node.getId() != null)
        {
            node.getId().apply(this);
        }
		if(patternRequired)
		{	
			for(int i = 0; i<symbols.get(str).size();i++)
			{
				if(node.getId() == symbols.get(str).get(i).getNode())
				{
					if(i == 0)
					{
						HashMap<String,String> map = new HashMap<String,String>();
						if(generatorArgs.get(currentStructNum) != null)
						map = generatorArgs.get(currentStructNum);
						map.put(str,str);
						generatorArgs.put(currentStructNum,map);
						
						p.printVariable("_"+str);
						break;
					}
					else
					{
						HashMap<String,String> map = new HashMap<String,String>();
						if(generatorArgs.get(currentStructNum) != null)
						map = generatorArgs.get(currentStructNum);
						map.put(str,str+(i+1));
						generatorArgs.put(currentStructNum,map);
						
						p.printVariable("_"+str+(i+1));
						break;
					}
				}
			}
			
		}
		else
		{
			if(node.getLambda() != null)
			{
				p.openTerm("agent_call");
				printSrcLoc(node);		
			}
							
			int dimCounter = currentStructNum;	
			boolean found = false;
			while(!found && symbols.get(str) != null)
			{		
		
					if(currentInPredicate>0 
					&& generatorArgs.get(dimCounter) != null
					&& generatorArgs.get(dimCounter).get(str) != null)
					{
						p.printVariable("_"+generatorArgs.get(dimCounter).get(str));
						found = true;
						break;
					}
					
				for(int a = 0;a<symbols.get(str).size();a++)
				{	
					//In Comprehension?
					if(symbols.get(str).get(a).getStructCount() == dimCounter
						&& symbols.get(str).get(a).isComprehensionArg())
					{	
					
						if(a == 0)
						p.printVariable("_"+str);
						else
						p.printVariable("_"+str+(a+1));
						
						found = true;
						break;
					}
					
				}
				
				if(found)
				break;
					
				//Search in symbol map	
				for(int a = 0;a<symbols.get(str).size();a++)
				{
					//In Lambda?					
					if(currentInLambdaRight>0 && (currentLambdaParams.get(str) != null))
					{
						p.printVariable("_"+currentLambdaParams.get(str));
						found = true;
						break;
					}
					//Current Function Parameters?
					else if(currentParams.get(str) != null)
					{
						p.printVariable("_"+currentParams.get(str));
						found = true;
						break;
					}		
					//In Identifier list of this dimension?
					else if(symbols.get(str).get(a).getSymbolInfo().equals("Ident (Groundrep.)")
					&& symbols.get(str).get(a).getStructCount() == dimCounter)
					{
						if(a == 0)
						{
							p.openTerm("val_of");
							p.printAtom(str);
							printSrcLoc(node.getId());
							p.closeTerm();
						}
						else
						{
							p.openTerm("val_of");
							p.printAtom(str+(a+1));
							printSrcLoc(node.getId());
							p.closeTerm();
						}
						found = true;
						break;						
					}
					//In functions channels or datatypes of this dimension?
					else if((symbols.get(str).get(a).getSymbolInfo().equals("Function or Process") 					
					|| symbols.get(str).get(a).getSymbolInfo().equals("Channel")					
					|| symbols.get(str).get(a).getSymbolInfo().equals("Constructor of Datatype")				
					|| symbols.get(str).get(a).getSymbolInfo().equals("Datatype")				
					|| symbols.get(str).get(a).getSymbolInfo().equals("Nametype"))
					&& symbols.get(str).get(a).getStructCount() == dimCounter)
					{					
						if(a == 0)
							p.printAtom(str);
						else
							p.printAtom(str+(a+1));
						
						found = true;
						break;
					}
					else if(symbols.get(str).get(a).getSymbolInfo().equals("BuiltIn primitive")
							&& symbols.get(str).get(a).getStructCount() == dimCounter)
					{
						printBuiltin(str,node.getId());
					}
				}
				//In let-within-args?
				if(!found
				&& letWithinArgs.get(dimCounter) != null 
				&& (letWithinArgs.get(dimCounter).get(str) != null))
				{
					p.printVariable("_"+letWithinArgs.get(dimCounter).get(str)); //key is var name and value string is enumerated key
									//In let-within-args?
					found = true;
					break;
				}
				if(dimCounter == 0)
				{
					break;
				}
				else
				{
					dimCounter = struct.get(dimCounter); //Not found, search in predecessor
				}			
			}

			if(node.getLambda() != null)
			{
				node.getLambda().apply(this);
				p.closeTerm();
			}
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
        if(node.getBoolExp() != null)
        {
            node.getBoolExp().apply(this);
        }
		currentInPredicate -= 1;
		p.closeTerm();
        outAPredicateStmts(node);
    }

//***************************************************************************************************************************************************
//Helpers	
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
	
	public void printBuiltin(String str, Node node)
	{

		if(str.equals("STOP"))
		{
			p.openTerm("stop");
			printSrcLoc(node);
			p.closeTerm();
		}
		else if(str.equals("SKIP"))
		{
			p.openTerm("skip");
			printSrcLoc(node);
			p.closeTerm();
		}
		else if(str.equals("Bool"))
		{
			p.printAtom("boolType");
		}
		else if(str.equals("Int"))
		{
			p.printAtom("intType");
		}
		else if(str.equals("CHAOS"))
		{
			p.openTerm("CHAOS");
			printSrcLoc(node);
			p.closeTerm();
		}
		else
		{
			p.printAtom(str);
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
    		p.printNumber(node.getEndPos().getPos()-node.getStartPos().getPos());
    		p.printNumber(node.getEndPos().getPos()-node.getStartPos().getPos());
    		p.closeTerm();
    	} 
		else 
		{
    		p.printAtom("no_loc_info_available");
    	}
    }
}
