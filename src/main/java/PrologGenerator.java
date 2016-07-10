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
	private ArrayList<String> currentParams;
	private ArrayList<String> currentLambdaParams;
	private HashMap<Integer,ArrayList<String>> letWithinArgs;
	private int currentInLambdaLeft;
	private int currentInLambdaRight;
	private boolean currentInChannel;
	private int leftFromPrefix;
	
	private int letWithinCount; //renumber let-within blocks
	private int currentLetWithinNum; //Saves a reference to the current let-within block
	private HashMap<Integer,Integer> letWithinStruct;
	
	public PrologGenerator(final PrologTermOutput pto,HashMap<String,ArrayList<SymInfo>> symbols) 
	{
		letWithinCount = 0;
		currentLetWithinNum = 0;
		letWithinStruct = new HashMap<Integer,Integer>();
		letWithinArgs = new HashMap<Integer,ArrayList<String>>();
		expectingPattern = false;
		currentInInput = false;
		currentInNondetInput = false;
		leftFromPrefix = 0;
		currentInChannel = false;
		p = pto;
		currentInParams = 0;
		groundrep = 0;
		this.symbols = symbols;
		currentParams = new ArrayList<String>();
		currentLambdaParams = new ArrayList<String>();
		currentInLambdaLeft = 0;
		currentInLambdaRight = 0;
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
//Comments
    @Override
    public void caseAMultilineCommentDef(AMultilineCommentDef node)
    {
        inAMultilineCommentDef(node);
        if(node.getMultilineComment() != null)
        {
            node.getMultilineComment().apply(this);
			p.openTerm("comment");
			p.openTerm("blockComment");
			String comment = node.getMultilineComment().getText();
			comment = comment.replaceAll("\n","\\\\n");
			comment = comment.replaceAll("\r","\\\\r");
			p.printAtom(comment);
			printSrcLoc(node.getMultilineComment());
			p.closeTerm();
			p.closeTerm();
        }
        outAMultilineCommentDef(node);
    }
	
    @Override
    public void caseALineCommentDef(ALineCommentDef node)
    {
        inALineCommentDef(node);
        if(node.getLineComment() != null)
        {
            node.getLineComment().apply(this);
			p.openTerm("comment");
			p.openTerm("lineComment");
			p.printAtom(node.getLineComment().getText());
			printSrcLoc(node.getLineComment());
			p.closeTerm();
			p.closeTerm();
        
        }
        outALineCommentDef(node);
    }
	
    @Override
    public void caseAShortCommentSeperatorDef(AShortCommentSeperatorDef node)
    {
        inAShortCommentSeperatorDef(node);
        if(node.getLineComment() != null)
        {
            node.getLineComment().apply(this);
			p.fullstop();
			p.openTerm("comment");
			p.openTerm("lineComment");
			p.printAtom(node.getLineComment().getText());
			printSrcLoc(node.getLineComment());
			p.closeTerm();
			p.closeTerm();
        }
        outAShortCommentSeperatorDef(node);
    }	
//***************************************************************************************************************************************************
//Definitions

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
				if(!currentInChannel)
				{
					p.fullstop();
				}
				currentInChannel = false;
            }
        }

		for (String key : symbols.keySet()) 
		{

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
		p.openTerm("setExp");
        if(node.getSet() != null)
        {
            node.getSet().apply(this);
        }
		p.closeTerm();
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
        if(node.getTuple() != null)
        {
            node.getTuple().apply(this);
        }
		else if(!isBuiltin(str))
		{
			p.openTerm("val_of");
			p.printAtom(str);
			printSrcLoc(node.getId());
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
					&& (symbols.get(str).get(k).getLetWithinCount() == currentLetWithinNum))
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
						currentLambdaParams.add(str);
					}
					else
					{
						p.printVariable("_"+str+(k+1));
						currentLambdaParams.add(str+(k+1));
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
				if(k == 0 && currentParams.contains(str))
				{
					currentParams.remove(str);
				}
				else if(currentParams.contains(str+k))
				{
					currentParams.remove(str+k);
				}
				if(symbols.get(str).get(k).getNode() == node.getId())
				{
					if(k == 0)
					{
						p.printVariable("_"+str);
						currentParams.add(str);
					}
					else
					{
						p.printVariable("_"+str+(k+1));
						currentParams.add(str+(k+1));
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
    public void caseAParPattern(AParPattern node)
    {
        inAParPattern(node);
        if(node.getPattern1() != null)
        {
            node.getPattern1().apply(this);
        }
        outAParPattern(node);
    }

    @Override
    public void caseATuplePattern(ATuplePattern node)
    {
        inATuplePattern(node);
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
        outATuplePattern(node);
    }

    @Override
    public void caseAWildcardPattern(AWildcardPattern node)
    {
        inAWildcardPattern(node);
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
        if(node.getString() != null)
        {
            node.getString().apply(this);
        }
        outAStringPattern(node);
    }

    @Override
    public void caseACharPattern(ACharPattern node)
    {
        inACharPattern(node);
        if(node.getChar() != null)
        {
            node.getChar().apply(this);
        }
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
        if(node.getTrue2() != null)
        {
            node.getTrue2().apply(this);
        }
        outATruePattern(node);
    }

    @Override
    public void caseAFalsePattern(AFalsePattern node)
    {
        inAFalsePattern(node);
        if(node.getFalse2() != null)
        {
            node.getFalse2().apply(this);
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
		currentLambdaParams.clear();
		p.closeTerm();
        outALambdaExp(node);
    }

    @Override
    public void caseALetWithinExp(ALetWithinExp node)
    {
        inALetWithinExp(node);
		letWithinCount++;
		letWithinStruct.put(letWithinCount,currentLetWithinNum);		
		currentLetWithinNum = letWithinCount;
		
		letWithinArgs.put(letWithinCount,currentParams);
		
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
		currentLetWithinNum = letWithinStruct.get(letWithinCount);
        if(node.getProc9() != null)
        {
            node.getProc9().apply(this);
        }
		p.closeTerm();
        outALetWithinExp(node);
    }

    @Override
    public void caseAIfElseExp(AIfElseExp node)
    {
        inAIfElseExp(node);
        if(node.getBoolExp() != null)
        {
            node.getBoolExp().apply(this);
        }
        if(node.getProc1() != null)
        {
            node.getProc1().apply(this);
        }
        if(node.getProc9() != null)
        {
            node.getProc9().apply(this);
        }
        outAIfElseExp(node);
    }

    @Override
    public void caseAReplicatedExp(AReplicatedExp node)
    {
        inAReplicatedExp(node);
        if(node.getRep() != null)
        {
            node.getRep().apply(this);
        }
        if(node.getProc9() != null)
        {
            node.getProc9().apply(this);
        }
        outAReplicatedExp(node);
    }

    @Override
    public void caseAExtChoiceRepExp(AExtChoiceRepExp node)
    {
        inAExtChoiceRepExp(node);
        if(node.getStmts() != null)
        {
            node.getStmts().apply(this);
        }
        outAExtChoiceRepExp(node);
    }

    @Override
    public void caseAIntChoiceRepExp(AIntChoiceRepExp node)
    {
        inAIntChoiceRepExp(node);
        if(node.getStmts() != null)
        {
            node.getStmts().apply(this);
        }
        outAIntChoiceRepExp(node);
    }

    @Override
    public void caseAILeaveRepExp(AILeaveRepExp node)
    {
        inAILeaveRepExp(node);
        if(node.getStmts() != null)
        {
            node.getStmts().apply(this);
        }
        outAILeaveRepExp(node);
    }

    @Override
    public void caseASeqCompositRepExp(ASeqCompositRepExp node)
    {
        inASeqCompositRepExp(node);
        if(node.getStmts() != null)
        {
            node.getStmts().apply(this);
        }
        outASeqCompositRepExp(node);
    }

    @Override
    public void caseAAlphParRepExp(AAlphParRepExp node)
    {
        inAAlphParRepExp(node);
        if(node.getStmts() != null)
        {
            node.getStmts().apply(this);
        }
        if(node.getEvent() != null)
        {
            node.getEvent().apply(this);
        }
        outAAlphParRepExp(node);
    }
	
    @Override
    public void caseASharingRepExp(ASharingRepExp node)
    {
        inASharingRepExp(node);
        if(node.getValExp() != null)
        {
            node.getValExp().apply(this);
        }
        if(node.getStmts() != null)
        {
            node.getStmts().apply(this);
        }
        outASharingRepExp(node);
    }

    @Override
    public void caseALinkedParRepExp(ALinkedParRepExp node)
    {
        inALinkedParRepExp(node);
        if(node.getLinkComp() != null)
        {
            node.getLinkComp().apply(this);
        }
        if(node.getStmts() != null)
        {
            node.getStmts().apply(this);
        }
        outALinkedParRepExp(node);
    }

    @Override
    public void caseASyncParRepExp(ASyncParRepExp node)
    {
        inASyncParRepExp(node);
        if(node.getEvent() != null)
        {
            node.getEvent().apply(this);
        }
        if(node.getStmts() != null)
        {
            node.getStmts().apply(this);
        }
        outASyncParRepExp(node);
    }

    @Override
    public void caseARenamingExp(ARenamingExp node)
    {
        inARenamingExp(node);
        if(node.getProc10() != null)
        {
            node.getProc10().apply(this);
        }
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
        if(node.getBoolExp() != null)
        {
            node.getBoolExp().apply(this);
        }
        if(node.getBoolExp2() != null)
        {
            node.getBoolExp2().apply(this);
        }
        outAOrExp(node);
    }

    @Override
    public void caseAAndExp(AAndExp node)
    {
        inAAndExp(node);
        if(node.getBoolExp2() != null)
        {
            node.getBoolExp2().apply(this);
        }
        if(node.getBoolExp3() != null)
        {
            node.getBoolExp3().apply(this);
        }
        outAAndExp(node);
    }

    @Override
    public void caseANotExp(ANotExp node)
    {
        inANotExp(node);
        if(node.getBoolExp3() != null)
        {
            node.getBoolExp3().apply(this);
        }
        outANotExp(node);
    }

    @Override
    public void caseAEqualExp(AEqualExp node)
    {
        inAEqualExp(node);
        if(node.getBoolExp4() != null)
        {
            node.getBoolExp4().apply(this);
        }
        if(node.getValExp() != null)
        {
            node.getValExp().apply(this);
        }
        outAEqualExp(node);
    }
	
    @Override
    public void caseAOrderingLgeExp(AOrderingLgeExp node)
    {
        inAOrderingLgeExp(node);
        if(node.getBoolExp4() != null)
        {
            node.getBoolExp4().apply(this);
        }
        if(node.getValExp() != null)
        {
            node.getValExp().apply(this);
        }
        outAOrderingLgeExp(node);
    }

    @Override
    public void caseAOrderingLessExp(AOrderingLessExp node)
    {
        inAOrderingLessExp(node);
        if(node.getBoolExp4() != null)
        {
            node.getBoolExp4().apply(this);
        }
        if(node.getValExp() != null)
        {
            node.getValExp().apply(this);
        }
        outAOrderingLessExp(node);
    }

    @Override
    public void caseAOrderingGreaterExp(AOrderingGreaterExp node)
    {
        inAOrderingGreaterExp(node);
        if(node.getBoolExp4() != null)
        {
            node.getBoolExp4().apply(this);
        }
        if(node.getValExp() != null)
        {
            node.getValExp().apply(this);
        }
        outAOrderingGreaterExp(node);
    }

    @Override
    public void caseAAdditionExp(AAdditionExp node)
    {
        inAAdditionExp(node);
        if(node.getValExp() != null)
        {
            node.getValExp().apply(this);
        }
        if(node.getValExp1() != null)
        {
            node.getValExp1().apply(this);
        }
        outAAdditionExp(node);
    }

    @Override
    public void caseASubtractionExp(ASubtractionExp node)
    {
        inASubtractionExp(node);
        if(node.getValExp() != null)
        {
            node.getValExp().apply(this);
        }
        if(node.getValExp1() != null)
        {
            node.getValExp1().apply(this);
        }
        outASubtractionExp(node);
    }

    @Override
    public void caseAMultiplicationExp(AMultiplicationExp node)
    {
        inAMultiplicationExp(node);
        if(node.getValExp1() != null)
        {
            node.getValExp1().apply(this);
        }
        if(node.getValExp2() != null)
        {
            node.getValExp2().apply(this);
        }
        outAMultiplicationExp(node);
    }
	
    @Override
    public void caseAUnMinusExp(AUnMinusExp node)
    {
        inAUnMinusExp(node);
        if(node.getValExp2() != null)
        {
            node.getValExp2().apply(this);
        }
        outAUnMinusExp(node);
    }

    @Override
    public void caseALengthExp(ALengthExp node)
    {
        inALengthExp(node);
        if(node.getLength() != null)
        {
            node.getLength().apply(this);
        }
        outALengthExp(node);
    }
	
   @Override
    public void caseAConcatExp(AConcatExp node)
    {
        inAConcatExp(node);
        {
            List<PExp> copy = new ArrayList<PExp>(node.getConcatList2());
			if(copy.size()>1 && expectingPattern)
			{
				p.openTerm("appendPattern");
				p.openList();
			}
			else if(copy.size()>1)
			{
				p.openTerm("^");
			}
            for(PExp e : copy)
            {
                e.apply(this);
            }
			if(copy.size()>1 && expectingPattern)
			{
				p.closeList();
				p.closeTerm();
			}
			else if(copy.size()>1)
			{
				p.closeTerm();
			}
        }
        outAConcatExp(node);
    }

    @Override
    public void caseAEmptySeqExp(AEmptySeqExp node)
    {
        inAEmptySeqExp(node);
        outAEmptySeqExp(node);
    }

    @Override
    public void caseAExplSeqExp(AExplSeqExp node)
    {
        inAExplSeqExp(node);
        if(node.getArguments() != null)
        {
            node.getArguments().apply(this);
        }
        outAExplSeqExp(node);
    }

    @Override
    public void caseARangedSeqExp(ARangedSeqExp node)
    {
        inARangedSeqExp(node);
        if(node.getLval() != null)
        {
            node.getLval().apply(this);
        }
        if(node.getRval() != null)
        {
            node.getRval().apply(this);
        }
        outARangedSeqExp(node);
    }

    @Override
    public void caseAInfiniteSeqExp(AInfiniteSeqExp node)
    {
        inAInfiniteSeqExp(node);
        if(node.getValExp() != null)
        {
            node.getValExp().apply(this);
        }
        outAInfiniteSeqExp(node);
    }
	
    @Override
    public void caseAComprSeqExp(AComprSeqExp node)
    {
        inAComprSeqExp(node);
        if(node.getArguments() != null)
        {
            node.getArguments().apply(this);
        }
        if(node.getStmts() != null)
        {
            node.getStmts().apply(this);
        }
        outAComprSeqExp(node);
    }

    @Override
    public void caseARangedComprSeqExp(ARangedComprSeqExp node)
    {
        inARangedComprSeqExp(node);
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
            node.getStmts().apply(this);
        }
        outARangedComprSeqExp(node);
    }

    @Override
    public void caseAInfiniteComprSeqExp(AInfiniteComprSeqExp node)
    {
        inAInfiniteComprSeqExp(node);
        if(node.getValExp() != null)
        {
            node.getValExp().apply(this);
        }
        if(node.getStmts() != null)
        {
            node.getStmts().apply(this);
        }
        outAInfiniteComprSeqExp(node);
    }

    @Override
    public void caseAEnumeratedSeqExp(AEnumeratedSeqExp node)
    {
        inAEnumeratedSeqExp(node);
        if(node.getArguments() != null)
        {
            node.getArguments().apply(this);
        }
        outAEnumeratedSeqExp(node);
    }

    @Override
    public void caseAEnumeratedComprSeqExp(AEnumeratedComprSeqExp node)
    {
        inAEnumeratedComprSeqExp(node);
        if(node.getArguments() != null)
        {
            node.getArguments().apply(this);
        }
        if(node.getStmts() != null)
        {
            node.getStmts().apply(this);
        }
        outAEnumeratedComprSeqExp(node);
    }

    @Override
    public void caseAEmptySetExp(AEmptySetExp node)
    {
        inAEmptySetExp(node);
        outAEmptySetExp(node);
    }

    @Override
    public void caseASetExp(ASetExp node)
    {
        inASetExp(node);
        if(node.getArguments() != null)
        {
            node.getArguments().apply(this);
        }
        outASetExp(node);
    }

    @Override
    public void caseARangedSetExp(ARangedSetExp node)
    {
        inARangedSetExp(node);
        if(node.getLval() != null)
        {
            node.getLval().apply(this);
        }
        if(node.getRval() != null)
        {
            node.getRval().apply(this);
        }
        outARangedSetExp(node);
    }

    @Override
    public void caseAInfiniteSetExp(AInfiniteSetExp node)
    {
        inAInfiniteSetExp(node);
        if(node.getValExp() != null)
        {
            node.getValExp().apply(this);
        }
        outAInfiniteSetExp(node);
    }

    @Override
    public void caseAComprSetExp(AComprSetExp node)
    {
        inAComprSetExp(node);
        if(node.getArguments() != null)
        {
            node.getArguments().apply(this);
        }
        if(node.getStmts() != null)
        {
            node.getStmts().apply(this);
        }
        outAComprSetExp(node);
    }

    @Override
    public void caseARangedComprSetExp(ARangedComprSetExp node)
    {
        inARangedComprSetExp(node);
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
            node.getStmts().apply(this);
        }
        outARangedComprSetExp(node);
    }

    @Override
    public void caseAInfiniteComprSetExp(AInfiniteComprSetExp node)
    {
        inAInfiniteComprSetExp(node);
        if(node.getValExp() != null)
        {
            node.getValExp().apply(this);
        }
        if(node.getStmts() != null)
        {
            node.getStmts().apply(this);
        }
        outAInfiniteComprSetExp(node);
    }

    @Override
    public void caseAEnumeratedSetExp(AEnumeratedSetExp node)
    {
        inAEnumeratedSetExp(node);
        if(node.getArguments() != null)
        {
            node.getArguments().apply(this);
        }
        outAEnumeratedSetExp(node);
    }

    @Override
    public void caseAEnumeratedComprSetExp(AEnumeratedComprSetExp node)
    {
        inAEnumeratedComprSetExp(node);
        if(node.getArguments() != null)
        {
            node.getArguments().apply(this);
        }
        if(node.getStmts() != null)
        {
            node.getStmts().apply(this);
        }
        outAEnumeratedComprSetExp(node);
    }

    @Override
    public void caseAMapExp(AMapExp node)
    {
        inAMapExp(node);
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

    @Override
    public void caseATupleExp(ATupleExp node)
    {
        inATupleExp(node);
        if(node.getTuple() != null)
        {
			p.openTerm("tupleExp");
			p.openList();
            node.getTuple().apply(this);			
			p.closeList();
			p.closeTerm();
        }
        if(node.getLambda() != null)
        {
            node.getLambda().apply(this);
        }
        outATupleExp(node);
    }

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
    public void caseATrue1Exp(ATrue1Exp node)
    {
        inATrue1Exp(node);
        if(node.getTrue1() != null)
        {
            node.getTrue1().apply(this);
			p.printAtom("true");
        }
        outATrue1Exp(node);
    }

    @Override
    public void caseATrue2Exp(ATrue2Exp node)
    {
        inATrue2Exp(node);
        if(node.getTrue2() != null)
        {
            node.getTrue2().apply(this);
        }
        outATrue2Exp(node);
    }
	
    @Override
    public void caseAFalse1Exp(AFalse1Exp node)
    {
        inAFalse1Exp(node);
        if(node.getFalse1() != null)
        {
            node.getFalse1().apply(this);
			p.printAtom("false");
        }
        outAFalse1Exp(node);
    }

    @Override
    public void caseAFalse2Exp(AFalse2Exp node)
    {
        inAFalse2Exp(node);
        if(node.getFalse2() != null)
        {
            node.getFalse2().apply(this);
        }
        outAFalse2Exp(node);
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
			p.openTerm("agent_call");
			printSrcLoc(node);
			boolean found = false;
			if(currentInLambdaRight>0)
			{
				for(int u = 1; u<=symbols.size();u++)
				{
					if((u == 1) && currentLambdaParams.contains(str))
					{
						found = true;
						p.printVariable("_"+str);
						break;
					}
					else if(currentLambdaParams.contains(str+u))
					{
						found = true;
						p.printVariable("_"+str+u);
						break;
					}
				}
			}
			if(!found) 
			{
				for(int u = 1; u<=symbols.size();u++)
				{
					if((u == 1) && currentParams.contains(str))
					{
						found = true;
						p.printVariable("_"+str);
						break;
					}
					else if(currentParams.contains(str+u))
					{
						found = true;
						p.printVariable("_"+str+u);
						break;
					}
				}
			}
			if(!found && currentLetWithinNum > 0)
			{
				int dimCounter = currentLetWithinNum;
				
				while(dimCounter>0 && !found)
				{
					for(int a = 0;a<symbols.get(str).size();a++)
					{
						
						for(int i = 0; i<letWithinArgs.get(dimCounter).size();i++)
						{
							if((i == 0) && letWithinArgs.get(dimCounter).get(i) == str)
							{
								found = true;
								p.printAtom(str);
								break;
							}
							if((i>0) && letWithinArgs.get(dimCounter).get(i) == str+(i+1))
							{
								found = true;	
								p.printAtom(str+(i+1));
								break; //Symbol was found in letWithinArgs of this dimension
							}
						}
						
						
						if((symbols.get(str).get(a).getSymbolInfo().equals("Function or Process") 
							&& symbols.get(str).get(a).getLetWithinCount() == dimCounter)	
							|| (symbols.get(str).get(a).getSymbolInfo().equals("Ident (Groundrep.)")
							&& symbols.get(str).get(a).getLetWithinCount() == dimCounter))
							{
								found = true;
								if(a == 0)
								{
									p.printAtom(str);
								}
								else
								{
									p.printAtom(str+(a+1));
								}
								break; //symbol was found in current dimension
							}
					}
					dimCounter = letWithinStruct.get(dimCounter); //Not found, search in predecessor
				}
			}
			if(!isBuiltin(str) && !found)
			{
				p.printAtom(str);
			}
			p.openList();
            node.getTuple().apply(this);
			p.closeList();
			p.closeTerm();
        }
		else
		{
			boolean found = false;
			if(currentInLambdaRight>0)
			{
				for(int u = 1; u<=symbols.size();u++)
				{
					if((u == 1) && currentLambdaParams.contains(str))
					{
						found = true;
						p.printVariable("_"+str);
						break;
					}
					else if(currentLambdaParams.contains(str+u))
					{
						found = true;
						p.printVariable("_"+str+u);
						break;
					}
				}
			}
			if(!found) 
			{
				for(int u = 1; u<=symbols.size();u++)
				{
					if((u == 1) && currentParams.contains(str))
					{
						found = true;
						p.printVariable("_"+str);
						break;
					}
					else if(currentParams.contains(str+u))
					{
						found = true;
						p.printVariable("_"+str+u);
						break;
					}
				}
			}
			if(!found && currentLetWithinNum > 0)
			{
				int dimCounter = currentLetWithinNum;
				
				while(dimCounter>0 && !found)
				{
					for(int a = 0;a<symbols.get(str).size();a++)
					{
						
						for(int i = 0; i<letWithinArgs.get(dimCounter).size();i++)
						{
							if((i == 0) && letWithinArgs.get(dimCounter).get(i) == str)
							{
								found = true;
								p.printAtom(str);
								break;
							}
							if((i>0) && letWithinArgs.get(dimCounter).get(i) == str+(i+1))
							{
								found = true;	
								p.printAtom(str+(i+1));
								break; //Symbol was found in letWithinArgs of this dimension
							}
						}
						
						
						if((symbols.get(str).get(a).getSymbolInfo().equals("Function or Process") 
							&& symbols.get(str).get(a).getLetWithinCount() == dimCounter)	
							|| (symbols.get(str).get(a).getSymbolInfo().equals("Ident (Groundrep.)")
							&& symbols.get(str).get(a).getLetWithinCount() == dimCounter))
							{
								found = true;
								if(a == 0)
								{
									p.printAtom(str);
								}
								else
								{
									p.printAtom(str+(a+1));
								}
								break; //symbol was found in current dimension
							}
					}
					dimCounter = letWithinStruct.get(dimCounter); //Not found, search in predecessor
				}
			}
			if(!isBuiltin(str) && !found)
			{
				ArrayList<SymInfo> al = symbols.get(str);
				int i = 0;
				for(int j = 0; j< al.size();j++)
				{
					if(al.get(j).getSymbolInfo().equals("Ident (Groundrep.)"))
					{
						i = j;
					}
				}
			
				p.openTerm("val_of");
				if(i>0)
				{
					p.printAtom(str+(i+1));
				}
				else
				{
					p.printAtom(str);
				}
				printSrcLoc(node.getId());
				p.closeTerm();
				
			}
		}
		
        outAIdExp(node);
    }

//***************************************************************************************************************************************************
//Linked & Renamed + Comprehensions

    @Override
    public void caseALinkCompLinkComp(ALinkCompLinkComp node)
    {
        inALinkCompLinkComp(node);
        {
			if(node.getStmts() == null)
			{
				p.openTerm("linkList");
			}
			else
			{
				p.openTerm("linkListComp");
				p.openList();
				p.openTerm("comprehensionGenerator");
				node.getStmts().apply(this);
				p.closeTerm();
				p.closeList();
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
        outALinkCompLinkComp(node);
    }

    @Override
    public void caseARenameCompRenameComp(ARenameCompRenameComp node)
    {
        inARenameCompRenameComp(node);
        {
            List<PExp> copy = new ArrayList<PExp>(node.getRcList());
            for(PExp e : copy)
            {
                e.apply(this);
            }
        }
        if(node.getStmts() != null)
        {
            node.getStmts().apply(this);
        }
        outARenameCompRenameComp(node);
    }


//***************************************************************************************************************************************************
//Builtins
	
    @Override
    public void caseALtlBuiltin(ALtlBuiltin node)
    {
        inALtlBuiltin(node);
        if(node.getLtl() != null)
        {
            p.printAtom("LTL");
        }
        outALtlBuiltin(node);
    }

    @Override
    public void caseACtlBuiltin(ACtlBuiltin node)
    {
        inACtlBuiltin(node);
        if(node.getCtl() != null)
        {
			p.printAtom("CTL");
        }
        outACtlBuiltin(node);
    }

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

    @Override
    public void caseAChaosBuiltin(AChaosBuiltin node)
    {
        inAChaosBuiltin(node);
        if(node.getChaos() != null)
        {
			
        }
        outAChaosBuiltin(node);
    }

    @Override
    public void caseADivBuiltin(ADivBuiltin node)
    {
        inADivBuiltin(node);
        if(node.getDiv() != null)
        {
            p.openTerm("div");
			printSrcLoc(node);
			p.closeTerm();
        }
        outADivBuiltin(node);
    }

    @Override
    public void caseABoolConstBuiltin(ABoolConstBuiltin node)
    {
        inABoolConstBuiltin(node);
        if(node.getBoolConst() != null)
        {
            p.printAtom("boolType");
        }
        outABoolConstBuiltin(node);
    }

    @Override
    public void caseAEventsBuiltin(AEventsBuiltin node)
    {
        inAEventsBuiltin(node);
        if(node.getEvents() != null)
        {
            p.openTerm("events");
			printSrcLoc(node);
			p.closeTerm();
        }
        outAEventsBuiltin(node);
    }

    @Override
    public void caseAProcBuiltin(AProcBuiltin node)
    {
        inAProcBuiltin(node);
        if(node.getProc() != null)
        {
            p.openTerm("proc");
			printSrcLoc(node);
			p.closeTerm();
        }
        outAProcBuiltin(node);
    }

    @Override
    public void caseACharConstBuiltin(ACharConstBuiltin node)
    {
        inACharConstBuiltin(node);
        if(node.getCharConst() != null)
        {
			p.printAtom("charType");
        }
        outACharConstBuiltin(node);
    }

    @Override
    public void caseAIntConstBuiltin(AIntConstBuiltin node)
    {
        inAIntConstBuiltin(node);
        if(node.getIntConst() != null)
        {
            p.printAtom("intType");
        }
        outAIntConstBuiltin(node);
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
