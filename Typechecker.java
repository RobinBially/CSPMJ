import java.io.*;
import java.*;
import java.util.*;
import java.lang.*;
import java.lang.String.*;
import java.lang.Character;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.charset.Charset;
import java.nio.file.Files;
import CSPMparser.analysis.*;
import CSPMparser.node.*;


public class Typechecker extends DepthFirstAdapter
{
	private Map<String,ArrayList<String>> channels = new HashMap<String,ArrayList<String>>();
	private Map<String,ArrayList<String>> datanodeMap = new HashMap<String,ArrayList<String>>();
	private ArrayList<String> value = new ArrayList<String>();
	private HashMap nodeMap = new HashMap();
	private HashMap types = new HashMap();
	private int symbolIndex = 0;
	
	
	private String currentDatatype;
	private ArrayList<ArrayList<String>> arguments = new ArrayList<ArrayList<String>>();
	private int argDepth = -1;
	private ArrayList<ArrayList<String>> innerseq = new ArrayList<ArrayList<String>>();
	private int seqDepth = -1;
	private String currentTuple = "";
	private int tupleDepth = -1;

    @Override
    public void caseATypedef(ATypedef node)
    {
        inATypedef(node);
        if(node.getId() != null)
        {
			//Reset
		//	value = new ArrayList<String>();
			//until here!
		//	currentDatatype = node.getId().toString();				
		//	datanodeMap.put(node.getId().toString(),value);
            node.getId().apply(this);
        }
        if(node.getEq() != null)
        {
            node.getEq().apply(this);
        }
        if(node.getClause() != null)
        {
		//	datanodeMap.get(currentDatatype).add(symbolIndex,node.getClause().toString());
		//	symbolIndex = 0;
            node.getClause().apply(this);
        }
        {
            List<PTypedefRek> copy = new ArrayList<PTypedefRek>(node.getTypedefRek());
            for(PTypedefRek e : copy)
            {
                e.apply(this);
            }
        }
	//	System.out.println(currentDatatype+"    "+symbolIndex+"      "+node.getClause().toString());
        outATypedef(node);
    }

	
	@Override
    public void caseAStypeTypes(AStypeTypes node)
    {
        inAStypeTypes(node);
        if(node.getSType() != null)
        {
            node.getSType().apply(this);
        }
        if(node.getTypedef() != null)
        {
            node.getTypedef().apply(this);
        }
        outAStypeTypes(node);
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
    public void caseATypedefRek(ATypedefRek node)
    {
        inATypedefRek(node);
        if(node.getPipe() != null)
        {
            node.getPipe().apply(this);
        }
        if(node.getClause() != null)
        {
		//	datanodeMap.get(currentDatatype).add(symbolIndex,node.getClause().toString());
		//	symbolIndex++;
            node.getClause().apply(this);
		//	System.out.println(currentDatatype+" "+symbolIndex+"  "+node.getClause().toString());
        }
        outATypedefRek(node);
    }

	
//***************************************************************************************
//Process Expressions
	
	@Override
    public void caseADoublePatternProc1(ADoublePatternProc1 node)
    {
        inADoublePatternProc1(node);
        if(node.getProc1() != null)
        {
            node.getProc1().apply(this);
			if(!(nodeMap.get(node.getProc1()).toString().equals("proc")))
			{
				throw new RuntimeException("TypeError, expecting 'proc' at: "
										+node.toString());
			}
        }
        if(node.getDoubleat() != null)
        {
            node.getDoubleat().apply(this);
        }
        if(node.getProc2() != null)
        {
            node.getProc2().apply(this);
			if(!(nodeMap.get(node.getProc2()).toString().equals("proc")))
			{
				throw new RuntimeException("TypeError, expecting 'proc' at: "
										+node.toString());
			}
        }
		nodeMap.put(node, "proc");
		nodeMap.remove(node.getProc1());
		nodeMap.remove(node.getProc2());
        outADoublePatternProc1(node);
    }



    @Override
    public void caseAP2Proc1(AP2Proc1 node)
    {
        inAP2Proc1(node);
        if(node.getProc2() != null)
        {
            node.getProc2().apply(this);
			nodeMap.put(node, nodeMap.get(node.getProc2()));
			nodeMap.remove(node.getProc2());
        }
        outAP2Proc1(node);
    }


    @Override
    public void caseAHideProc2(AHideProc2 node)
    {
        inAHideProc2(node);
        if(node.getProc2() != null)
        {
            node.getProc2().apply(this);
			if(!(nodeMap.get(node.getProc2()).toString().equals("proc")))
			{
				throw new RuntimeException("TypeError, expecting 'proc' at: "
										+node.toString());
			}
        }
        if(node.getBSlash() != null)
        {
            node.getBSlash().apply(this);
        }
        if(node.getProc3() != null)
        {
            node.getProc3().apply(this);
			if(!(nodeMap.get(node.getProc3()).toString().equals("proc")))
			{
				throw new RuntimeException("TypeError, expecting 'proc' at: "
										+node.toString());
			}
        }
		nodeMap.put(node, "proc");
		nodeMap.remove(node.getProc2());
		nodeMap.remove(node.getProc3());
        outAHideProc2(node);
    }


    @Override
    public void caseAP3Proc2(AP3Proc2 node)
    {
        inAP3Proc2(node);
        if(node.getProc3() != null)
        {
            node.getProc3().apply(this);
			nodeMap.put(node, nodeMap.get(node.getProc3()));
			nodeMap.remove(node.getProc3());
        }
        outAP3Proc2(node);
    }


    @Override
    public void caseAIleaveProc3(AIleaveProc3 node)
    {
        inAIleaveProc3(node);
        if(node.getProc3() != null)
        {
            node.getProc3().apply(this);
			if(!(nodeMap.get(node.getProc3()).toString().equals("proc")))
			{
				throw new RuntimeException("TypeError, expecting 'proc' at: "
										+node.toString());
			}
        }
        if(node.getILeaving() != null)
        {
            node.getILeaving().apply(this);
        }
        if(node.getProc4() != null)
        {
            node.getProc4().apply(this);
			if(!(nodeMap.get(node.getProc4()).toString().equals("proc")))
			{
				throw new RuntimeException("TypeError, expecting 'proc' at: "
										+node.toString());
			}
        }
		nodeMap.put(node, "proc");
		nodeMap.remove(node.getProc4());
		nodeMap.remove(node.getProc3());
        outAIleaveProc3(node);
    }


    @Override
    public void caseAP4Proc3(AP4Proc3 node)
    {
        inAP4Proc3(node);
        if(node.getProc4() != null)
        {
            node.getProc4().apply(this);
			nodeMap.put(node, nodeMap.get(node.getProc4()));
			nodeMap.remove(node.getProc4());
        }
        outAP4Proc3(node);
    }


    @Override
    public void caseAExceptProc4(AExceptProc4 node)
    {
        inAExceptProc4(node);
        if(node.getProc4() != null)
        {
            node.getProc4().apply(this);
			if(!(nodeMap.get(node.getProc4()).toString().equals("proc")))
			{
				throw new RuntimeException("TypeError, expecting 'proc' at: "
										+node.toString());
			}
        }
        if(node.getBracketL() != null)
        {
            node.getBracketL().apply(this);
        }
        if(node.getL() != null)
        {
            node.getL().apply(this);
        }
        if(node.getEvent() != null)
        {
            node.getEvent().apply(this);
			if(!(nodeMap.get(node.getEvent()).toString().equals("event")))
			{
				throw new RuntimeException("TypeError, expecting 'event' at: "
										+node.toString());
			}
        }
        if(node.getR() != null)
        {
            node.getR().apply(this);
        }
        if(node.getTriaR() != null)
        {
            node.getTriaR().apply(this);
        }
        if(node.getProc5() != null)
        {
            node.getProc5().apply(this);
			if(!(nodeMap.get(node.getProc5()).toString().equals("proc")))
			{
				throw new RuntimeException("TypeError, expecting 'proc' at: "
										+node.toString());
			}
        }
		nodeMap.put(node, "proc");
		nodeMap.remove(node.getProc4());
		nodeMap.remove(node.getProc5());
		nodeMap.remove(node.getEvent());
        outAExceptProc4(node);
    }


    @Override
    public void caseAGenParProc4(AGenParProc4 node)
    {
        inAGenParProc4(node);
        if(node.getProc4() != null)
        {
            node.getProc4().apply(this);
			if(!(nodeMap.get(node.getProc4()).toString().equals("proc")))
			{
				throw new RuntimeException("TypeError, expecting 'proc' at: "
										+node.toString());
			}
        }
        if(node.getBracketL() != null)
        {
            node.getBracketL().apply(this);
        }
        if(node.getLp() != null)
        {
            node.getLp().apply(this);
        }
        if(node.getEvent() != null)
        {
            node.getEvent().apply(this);
			if(!(nodeMap.get(node.getEvent()).toString().equals("event")))
			{
				throw new RuntimeException("TypeError, expecting 'event' at: "
										+node.toString());
			}
        }
        if(node.getRp() != null)
        {
            node.getRp().apply(this);
        }
        if(node.getBracketR() != null)
        {
            node.getBracketR().apply(this);
        }
        if(node.getProc5() != null)
        {
            node.getProc5().apply(this);
			if(!(nodeMap.get(node.getProc5()).toString().equals("proc")))
			{
				throw new RuntimeException("TypeError, expecting 'proc' at: "
										+node.toString());
			}
        }
		nodeMap.put(node, "proc");
		nodeMap.remove(node.getProc4());
		nodeMap.remove(node.getProc5());
		nodeMap.remove(node.getEvent());
        outAGenParProc4(node);
    }


    @Override
    public void caseAAlphParProc4(AAlphParProc4 node)
    {
        inAAlphParProc4(node);
        if(node.getProc4() != null)
        {
            node.getProc4().apply(this);
			if(!(nodeMap.get(node.getProc4()).toString().equals("proc")))
			{
				throw new RuntimeException("TypeError, expecting 'proc' at: "
										+node.toString());
			}
        }
        if(node.getBracketL() != null)
        {
            node.getBracketL().apply(this);
        }
        if(node.getL() != null)
        {
            node.getL().apply(this);
			if(!(nodeMap.get(node.getL()).toString().equals("event")))
			{
				throw new RuntimeException("TypeError, expecting 'event' at: "
										+node.toString());
			}
        }
        if(node.getDpipe() != null)
        {
            node.getDpipe().apply(this);
        }
        if(node.getR() != null)
        {
            node.getR().apply(this);
			if(!(nodeMap.get(node.getR()).toString().equals("event")))
			{
				throw new RuntimeException("TypeError, expecting 'event' at: "
										+node.toString());
			}
        }
        if(node.getBracketR() != null)
        {
            node.getBracketR().apply(this);
        }
        if(node.getProc5() != null)
        {
            node.getProc5().apply(this);
			if(!(nodeMap.get(node.getProc5()).toString().equals("proc")))
			{
				throw new RuntimeException("TypeError, expecting 'proc' at: "
										+node.toString());
			}
        }
		nodeMap.put(node, "proc");
		nodeMap.remove(node.getProc4());
		nodeMap.remove(node.getProc5());
		nodeMap.remove(node.getL());
		nodeMap.remove(node.getR());
        outAAlphParProc4(node);
    }


    @Override
    public void caseALinkedParProc4(ALinkedParProc4 node)
    {
        inALinkedParProc4(node);
        if(node.getProc4() != null)
        {
            node.getProc4().apply(this);
			if(!(nodeMap.get(node.getProc4()).toString().equals("proc")))
			{
				throw new RuntimeException("TypeError, expecting 'proc' at: "
										+node.toString());
			}
        }
        if(node.getBracketL() != null)
        {
            node.getBracketL().apply(this);
        }
        if(node.getLinkComp() != null)
        {
            node.getLinkComp().apply(this);
        }
        if(node.getBracketR() != null)
        {
            node.getBracketR().apply(this);
        }
        if(node.getProc5() != null)
        {
            node.getProc5().apply(this);
			if(!(nodeMap.get(node.getProc5()).toString().equals("proc")))
			{
				throw new RuntimeException("TypeError, expecting 'proc' at: "
										+node.toString());
			}
        }
		nodeMap.put(node, "proc");
		nodeMap.remove(node.getProc4());
		nodeMap.remove(node.getProc5());
		nodeMap.remove(node.getLinkComp());
        outALinkedParProc4(node);
    }


    @Override
    public void caseAP5Proc4(AP5Proc4 node)
    {
        inAP5Proc4(node);
        if(node.getProc5() != null)
        {
            node.getProc5().apply(this);
			nodeMap.put(node, nodeMap.get(node.getProc5()));
			nodeMap.remove(node.getProc5());
        }
        outAP5Proc4(node);
    }


    @Override
    public void caseAIntChoiceProc5(AIntChoiceProc5 node)
    {
        inAIntChoiceProc5(node);
        if(node.getProc5() != null)
        {
            node.getProc5().apply(this);
			if(!(nodeMap.get(node.getProc5()).toString().equals("proc")))
			{
				throw new RuntimeException("TypeError, expecting 'proc' at: "
										+node.toString());
			}
        }
        if(node.getIChoice() != null)
        {
            node.getIChoice().apply(this);
        }
        if(node.getProc6() != null)
        {
            node.getProc6().apply(this);
			if(!(nodeMap.get(node.getProc6()).toString().equals("proc")))
			{
				throw new RuntimeException("TypeError, expecting 'proc' at: "
										+node.toString());
			}
        }
		nodeMap.put(node, "proc");
		nodeMap.remove(node.getProc5());
		nodeMap.remove(node.getProc6());
        outAIntChoiceProc5(node);
    }


    @Override
    public void caseAP6Proc5(AP6Proc5 node)
    {
        inAP6Proc5(node);
        if(node.getProc6() != null)
        {
            node.getProc6().apply(this);
			nodeMap.put(node, nodeMap.get(node.getProc6()));
			nodeMap.remove(node.getProc6());
        }
        outAP6Proc5(node);
    }


    @Override
    public void caseAExtChoiceProc6(AExtChoiceProc6 node)
    {
        inAExtChoiceProc6(node);
        if(node.getProc6() != null)
        {
            node.getProc6().apply(this);
			if(!(nodeMap.get(node.getProc6()).toString().equals("proc")))
			{
				throw new RuntimeException("TypeError, expecting 'proc' at: "
										+node.toString());
			}
        }
        if(node.getEChoice() != null)
        {
            node.getEChoice().apply(this);
        }
        if(node.getProc7() != null)
        {
            node.getProc7().apply(this);
			if(!(nodeMap.get(node.getProc7()).toString().equals("proc")))
			{
				throw new RuntimeException("TypeError, expecting 'proc' at: "
										+node.toString());
			}
        }
		nodeMap.put(node, "proc");
		nodeMap.remove(node.getProc6());
		nodeMap.remove(node.getProc7());
        outAExtChoiceProc6(node);
    }


    @Override
    public void caseASyncExtProc6(ASyncExtProc6 node)
    {
        inASyncExtProc6(node);
        if(node.getProc6() != null)
        {
            node.getProc6().apply(this);
			if(!(nodeMap.get(node.getProc6()).toString().equals("proc")))
			{
				throw new RuntimeException("TypeError, expecting 'proc' at: "
										+node.toString());
			}
        }
        if(node.getSyncParL() != null)
        {
            node.getSyncParL().apply(this);
        }
        if(node.getEvent() != null)
        {
            node.getEvent().apply(this);
			if(!(nodeMap.get(node.getEvent()).toString().equals("event")))
			{
				throw new RuntimeException("TypeError, expecting 'event' at: "
										+node.toString());
			}
        }
        if(node.getSyncParR() != null)
        {
            node.getSyncParR().apply(this);
        }
        if(node.getProc7() != null)
        {
            node.getProc7().apply(this);
			if(!(nodeMap.get(node.getProc7()).toString().equals("proc")))
			{
				throw new RuntimeException("TypeError, expecting 'proc' at: "
										+node.toString());
			}
        }
		nodeMap.put(node, "proc");
		nodeMap.remove(node.getProc6());
		nodeMap.remove(node.getProc7());
		nodeMap.remove(node.getEvent());
        outASyncExtProc6(node);
    }


    @Override
    public void caseAP7Proc6(AP7Proc6 node)
    {
        inAP7Proc6(node);
        if(node.getProc7() != null)
        {
            node.getProc7().apply(this);
			nodeMap.put(node, nodeMap.get(node.getProc7()));
			nodeMap.remove(node.getProc7());
        }
        outAP7Proc6(node);
    }


    @Override
    public void caseAInterruptProc7(AInterruptProc7 node)
    {
        inAInterruptProc7(node);
        if(node.getProc7() != null)
        {
            node.getProc7().apply(this);
			if(!(nodeMap.get(node.getProc7()).toString().equals("proc")))
			{
				throw new RuntimeException("TypeError, expecting 'proc' at: "
										+node.toString());
			}

        }
        if(node.getInterrupt() != null)
        {
            node.getInterrupt().apply(this);
        }
        if(node.getProc8() != null)
        {
            node.getProc8().apply(this);
			if(!(nodeMap.get(node.getProc8()).toString().equals("proc")))
			{
				throw new RuntimeException("TypeError, expecting 'proc' at: "
										+node.toString());
			}

        }
		nodeMap.put(node, "proc");
		nodeMap.remove(node.getProc8());
		nodeMap.remove(node.getProc7());
        outAInterruptProc7(node);
    }


    @Override
    public void caseASyncInterruptProc7(ASyncInterruptProc7 node)
    {
        inASyncInterruptProc7(node);
        if(node.getProc7() != null)
        {
            node.getProc7().apply(this);
			if(!(nodeMap.get(node.getProc7()).toString().equals("proc")))
			{
				throw new RuntimeException("TypeError, expecting 'proc' at: "
										+node.toString());
			}
        }
        if(node.getSyncIntL() != null)
        {
            node.getSyncIntL().apply(this);
        }
        if(node.getEvent() != null)
        {
            node.getEvent().apply(this);
			if(!(nodeMap.get(node.getEvent()).toString().equals("event")))
			{
				throw new RuntimeException("TypeError, expecting 'event' at: "
										+node.toString());
			}
        }
        if(node.getSyncIntR() != null)
        {
            node.getSyncIntR().apply(this);
        }
        if(node.getProc8() != null)
        {
            node.getProc8().apply(this);
			if(!(nodeMap.get(node.getProc7()).toString().equals("proc")))
			{
				throw new RuntimeException("TypeError, expecting 'proc' at: "
										+node.toString());
			}

        }
		nodeMap.put(node, "proc");
		nodeMap.remove(node.getProc8());
		nodeMap.remove(node.getProc7());
		nodeMap.remove(node.getEvent());
        outASyncInterruptProc7(node);
    }


    @Override
    public void caseAP8Proc7(AP8Proc7 node)
    {
        inAP8Proc7(node);
        if(node.getProc8() != null)
        {
            node.getProc8().apply(this);
			nodeMap.put(node, nodeMap.get(node.getProc8()));
			nodeMap.remove(node.getProc8());
        }
        outAP8Proc7(node);
    }


    @Override
    public void caseASlidingChoiceProc8(ASlidingChoiceProc8 node)
    {
        inASlidingChoiceProc8(node);
        if(node.getProc8() != null)
        {
            node.getProc8().apply(this);
			if(!(nodeMap.get(node.getProc8()).toString().equals("proc")))
			{
				throw new RuntimeException("TypeError, expecting 'proc' at: "
										+node.toString());
			}

        }
        if(node.getTimeout() != null)
        {
            node.getTimeout().apply(this);
        }
        if(node.getProc9() != null)
        {
            node.getProc9().apply(this);
			if(!(nodeMap.get(node.getProc9()).toString().equals("proc")))
			{
				throw new RuntimeException("TypeError, expecting 'proc' at: "
										+node.toString());
			}
        }
		nodeMap.put(node, "proc");
		nodeMap.remove(node.getProc8());
		nodeMap.remove(node.getProc9());
        outASlidingChoiceProc8(node);
    }


    @Override
    public void caseAP9Proc8(AP9Proc8 node)
    {
        inAP9Proc8(node);
        if(node.getProc9() != null)
        {
            node.getProc9().apply(this);
        }
		nodeMap.put(node, nodeMap.get(node.getProc9()));
		nodeMap.remove(node.getProc9());
        outAP9Proc8(node);
    }


    @Override
    public void caseASeqCompProc9(ASeqCompProc9 node)
    {
        inASeqCompProc9(node);
        if(node.getProc9() != null)
        {
            node.getProc9().apply(this);
			if(!(nodeMap.get(node.getProc9()).toString().equals("proc")))
			{
				throw new RuntimeException("TypeError, expecting 'proc' at: "
										+node.toString());
			}
        }
        if(node.getSemicolon() != null)
        {
            node.getSemicolon().apply(this);
        }
        if(node.getProc10() != null)
        {
            node.getProc10().apply(this);
			if(!(nodeMap.get(node.getProc10()).toString().equals("proc")))
			{
				throw new RuntimeException("TypeError, expecting 'proc' at: "
										+node.toString());
			}
        }
		nodeMap.put(node, "proc");
		nodeMap.remove(node.getProc9());
		nodeMap.remove(node.getProc10());
        outASeqCompProc9(node);
    }


    @Override
    public void caseAP10Proc9(AP10Proc9 node)
    {
        inAP10Proc9(node);
        if(node.getProc10() != null)
        {
            node.getProc10().apply(this);
			nodeMap.put(node, nodeMap.get(node.getProc10()));
			nodeMap.remove(node.getProc10());
        }
        outAP10Proc9(node);
    }
	
    @Override
    public void caseAGuardExpProc10(AGuardExpProc10 node)
    {
        inAGuardExpProc10(node);
        if(node.getDotOp() != null)
        {
            node.getDotOp().apply(this);
			if(!(nodeMap.get(node.getDotOp()).toString().equals("bool")))
			{
				throw new RuntimeException("TypeError, expecting 'bool' at: "
											+node.toString());
			}
        }
        if(node.getGuard() != null)
        {
            node.getGuard().apply(this);
        }
        if(node.getProc10() != null)
        {
            node.getProc10().apply(this);
			if(!(nodeMap.get(node.getProc10()).toString().equals("proc")))
			{
				throw new RuntimeException("TypeError, expecting 'proc' at: "
											+node.toString());
			}
        }
		nodeMap.put(node,"proc");
		nodeMap.remove(node.getProc10());
		nodeMap.remove(node.getDotOp());
        outAGuardExpProc10(node);
    }

    @Override
    public void caseAPrefixProc10(APrefixProc10 node)
    {
        inAPrefixProc10(node);
        if(node.getEvent() != null)
        {
            node.getEvent().apply(this);
			if(!(nodeMap.get(node.getEvent()).toString().equals("event")))
			{
				throw new RuntimeException("TypeError, expecting 'event' at: "
											+node.toString());
			}
        }
        if(node.getPrefix() != null)
        {
            node.getPrefix().apply(this);
        }
        if(node.getProc10() != null)
        {
            node.getProc10().apply(this);
			if(!(nodeMap.get(node.getProc10()).toString().equals("proc")))
			{
				throw new RuntimeException("TypeError, expecting 'proc' at: "
											+node.toString());
			}
        }
		nodeMap.put(node,"proc");
		nodeMap.remove(node.getProc10());
		nodeMap.remove(node.getEvent());
        outAPrefixProc10(node);
    }


    @Override
    public void caseALambdaTermProc10(ALambdaTermProc10 node)
    {
        inALambdaTermProc10(node);
        if(node.getBSlash() != null)
        {
            node.getBSlash().apply(this);
        }
        if(node.getInnerTuple() != null)
        {
            node.getInnerTuple().apply(this);
        }
        if(node.getAt() != null)
        {
            node.getAt().apply(this);
        }
        if(node.getProc10() != null)
        {
            node.getProc10().apply(this);
        }
        outALambdaTermProc10(node);
    }

    @Override
    public void caseALetWithinProc10(ALetWithinProc10 node)
    {
        inALetWithinProc10(node);
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
        if(node.getNl() != null)
        {
            node.getNl().apply(this);
        }
        if(node.getProc10() != null)
        {
            node.getProc10().apply(this);
			if(!(nodeMap.get(node.getProc10()).toString().equals("proc")))
			{
				throw new RuntimeException("TypeError, expecting 'proc' at:"
										+node.toString());
			}
        }
		nodeMap.put(node,"proc");
		nodeMap.remove(node.getProc10());
        outALetWithinProc10(node);
    }


    @Override
    public void caseAIfElseProc10(AIfElseProc10 node)
    {
        inAIfElseProc10(node);
        if(node.getIf() != null)
        {
            node.getIf().apply(this);
        }
        if(node.getBoolExp() != null)
        {
            node.getBoolExp().apply(this);
			if(!(nodeMap.get(node.getBoolExp()).toString().equals("bool")))
			{
				throw new RuntimeException("TypeError, expecting 'bool' at: "
										+node.toString());
			}
        }
        if(node.getA() != null)
        {
            node.getA().apply(this);
        }
		if(node.getThen() != null)
        {
            node.getThen().apply(this);
        }
        if(node.getB() != null)
        {
            node.getB().apply(this);
        }
        if(node.getProc1() != null)
        {
            node.getProc1().apply(this);
			if(!(nodeMap.get(node.getProc1()).toString().equals("proc")))
			{
				throw new RuntimeException("TypeError, expecting 'proc' at:"
										+node.toString());
			}
        }
        if(node.getC() != null)
        {
            node.getC().apply(this);
        }
        if(node.getElse() != null)
        {
            node.getElse().apply(this);
        }
        if(node.getD() != null)
        {
            node.getD().apply(this);
        }
        if(node.getProc10() != null)
        {
            node.getProc10().apply(this);
			if(!(nodeMap.get(node.getProc10()).toString().equals("proc")))
			{
				throw new RuntimeException("TypeError, expecting 'proc' at:"
										+node.toString());
			}
        }
		nodeMap.put(node,"proc");
		nodeMap.remove(node.getProc1());
		nodeMap.remove(node.getBoolExp());
		nodeMap.remove(node.getProc10());
        outAIfElseProc10(node);
    }

    @Override
    public void caseARepProc10(ARepProc10 node)
    {
        inARepProc10(node);
        if(node.getRep() != null)
        {
            node.getRep().apply(this);
        }
        if(node.getProc10() != null)
        {
            node.getProc10().apply(this);
			if(!(nodeMap.get(node.getProc10()).toString().equals("proc")))
			{
				throw new RuntimeException("TypeError, expecting 'proc' at:"
										+node.toString());
			}
        }
		nodeMap.put(node,"proc");
		nodeMap.remove(node.getProc10());
        outARepProc10(node);
    }


    @Override
    public void caseAP11Proc10(AP11Proc10 node)
    {
        inAP11Proc10(node);
        if(node.getProc11() != null)
        {
            node.getProc11().apply(this);
			nodeMap.put(node, nodeMap.get(node.getProc11()));
			nodeMap.remove(node.getProc11());
        }
        outAP11Proc10(node);
    }


    @Override
    public void caseARenamingProc11(ARenamingProc11 node)
    {
        inARenamingProc11(node);
        if(node.getProc11() != null)
        {
            node.getProc11().apply(this);
			if(!(nodeMap.get(node.getProc11()).toString().equals("proc")))
			{
				throw new RuntimeException("TypeError, expecting 'proc' at:"
										+node.toString());
			}
        }
        if(node.getDbracketL() != null)
        {
            node.getDbracketL().apply(this);
        }
        if(node.getRenameComp() != null)
        {
            node.getRenameComp().apply(this);
        }
        if(node.getDbracketR() != null)
        {
            node.getDbracketR().apply(this);
        }
		nodeMap.put(node, "proc");
		nodeMap.remove(node.getProc11());
        outARenamingProc11(node);
    }


    @Override
    public void caseAERenamingProc11(AERenamingProc11 node)
    {
        inAERenamingProc11(node);
        if(node.getProc11() != null)
        {
            node.getProc11().apply(this);
			if(!(nodeMap.get(node.getProc11()).toString().equals("proc")))
			{
				throw new RuntimeException("TypeError, expecting 'proc' at:"
										+node.toString());
			}
        }
        if(node.getDbracketL() != null)
        {
            node.getDbracketL().apply(this);
        }
        if(node.getDbracketR() != null)
        {
            node.getDbracketR().apply(this);
        }
		nodeMap.put(node, "proc");
		nodeMap.remove(node.getProc11());
        outAERenamingProc11(node);
    }
	
	@Override
    public void caseAEventProc11(AEventProc11 node)
    {
        inAEventProc11(node);
        if(node.getEvent() != null)
        {
            node.getEvent().apply(this);
			nodeMap.put(node, nodeMap.get(node.getEvent()));
			nodeMap.remove(node.getEvent());
        }
        outAEventProc11(node);
    }
	

//***************************************************************************************
//Events
    @Override
    public void caseAEventEvent(AEventEvent node)
    {
        inAEventEvent(node);
		int i = 0;
		int j = 0;
        if(node.getDotOp() != null)
        {
            node.getDotOp().apply(this);
        }
        {
            List<PField1> copy = new ArrayList<PField1>(node.getField1());
            for(PField1 e : copy)
            {
				i++;
                e.apply(this);
            }
        }
        {
            List<PField2> copy = new ArrayList<PField2>(node.getField2());
            for(PField2 e : copy)
            {
				j++;
                e.apply(this);
            }
        }
		if(i == 0 && j == 0)
		{
			nodeMap.put(node, nodeMap.get(node.getDotOp()));
			nodeMap.remove(node.getDotOp());
		}
        outAEventEvent(node);
    }

    @Override
    public void caseANondetRestField1(ANondetRestField1 node)
    {
        inANondetRestField1(node);
        if(node.getDollar() != null)
        {
            node.getDollar().apply(this);
        }
        if(node.getDotRest() != null)
        {
            node.getDotRest().apply(this);
        }
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
        if(node.getDotRest() != null)
        {
            node.getDotRest().apply(this);
        }
        outAInputRestField2(node);
    }

    @Override
    public void caseAOutputField2(AOutputField2 node)
    {
        inAOutputField2(node);
        if(node.getExclMark() != null)
        {
            node.getExclMark().apply(this);
        }
        if(node.getDotOp() != null)
        {
            node.getDotOp().apply(this);
        }
        outAOutputField2(node);
    }

    @Override
    public void caseADotRestDotRest(ADotRestDotRest node)
    {
        inADotRestDotRest(node);
        if(node.getBoolExp() != null)
        {
            node.getBoolExp().apply(this);
        }
        if(node.getDot() != null)
        {
            node.getDot().apply(this);
        }
        if(node.getDotRest() != null)
        {
            node.getDotRest().apply(this);
        }
        outADotRestDotRest(node);
    }

    @Override
    public void caseARestDotRest(ARestDotRest node)
    {
        inARestDotRest(node);
        if(node.getRestricted() != null)
        {
            node.getRestricted().apply(this);
			nodeMap.put(node, nodeMap.get(node.getRestricted()));
			nodeMap.remove(node.getRestricted());
        }
        outARestDotRest(node);
    }

    @Override
    public void caseABoolExpDotRest(ABoolExpDotRest node)
    {
        inABoolExpDotRest(node);
        if(node.getBoolExp() != null)
        {
            node.getBoolExp().apply(this);
			nodeMap.put(node, nodeMap.get(node.getBoolExp()));
			nodeMap.remove(node.getBoolExp());
        }
        outABoolExpDotRest(node);
    }

    @Override
    public void caseARestricted(ARestricted node)
    {
        inARestricted(node);
        if(node.getBoolExp() != null)
        {
            node.getBoolExp().apply(this);
        }
        if(node.getDdot() != null)
        {
            node.getDdot().apply(this);
        }
        if(node.getAtom() != null)
        {
            node.getAtom().apply(this);
        }
        outARestricted(node);
    }

    @Override
    public void caseADotDotOp(ADotDotOp node)
    {
        inADotDotOp(node);
        if(node.getDotOp() != null)
        {
            node.getDotOp().apply(this);
        }
        if(node.getDot() != null)
        {
            node.getDot().apply(this);
        }
        if(node.getBoolExp() != null)
        {
            node.getBoolExp().apply(this);
        }
        outADotDotOp(node);
    }

    @Override
    public void caseASsDotOp(ASsDotOp node)
    {
        inASsDotOp(node);
        if(node.getBoolExp() != null)
        {
            node.getBoolExp().apply(this);
			nodeMap.put(node, nodeMap.get(node.getBoolExp()));
			nodeMap.remove(node.getBoolExp());
        }
        outASsDotOp(node);
    }

//***************************************************************************************
//Boolean Expressions	
    @Override
    public void caseAOrBoolExp(AOrBoolExp node)
    {
        inAOrBoolExp(node);
		String a = "";
		String b = "";
        if(node.getBoolExp() != null)
        {
            node.getBoolExp().apply(this);
			a = nodeMap.get(node.getBoolExp()).toString();
			if(!a.equals("bool"))
			{
				throw new RuntimeException("TypeError, expecting: 'bool' or 'bool'"+
												"at: "+node.toString());
			}
        }
        if(node.getOr() != null)
        {
            node.getOr().apply(this);
        }
        if(node.getBoolExp2() != null)
        {
            node.getBoolExp2().apply(this);
			b = nodeMap.get(node.getBoolExp2()).toString();
			if(!b.equals("bool"))
			{
				throw new RuntimeException("TypeError, expecting: 'bool' or 'bool'"+
												"at: "+node.toString());
			}
        }
		nodeMap.put(node,"bool");
		nodeMap.remove(node.getBoolExp());
		nodeMap.remove(node.getBoolExp2());
        outAOrBoolExp(node);
    }


    @Override
    public void caseABoolExp2BoolExp(ABoolExp2BoolExp node)
    {
        inABoolExp2BoolExp(node);
        if(node.getBoolExp2() != null)
        {
            node.getBoolExp2().apply(this);
			nodeMap.put(node,nodeMap.get(node.getBoolExp2()));
			nodeMap.remove(node.getBoolExp2());
        }
        outABoolExp2BoolExp(node);
    }


    @Override
    public void caseAAndBoolExp2(AAndBoolExp2 node)
    {
        inAAndBoolExp2(node);
		String a = "";
		String b = "";
        if(node.getBoolExp2() != null)
        {
            node.getBoolExp2().apply(this);
			a = nodeMap.get(node.getBoolExp2()).toString();
			if(!(a.equals("bool")))
			{
				throw new RuntimeException("TypeError, expecting: 'bool' and 'bool'"+
												"at: "+node.toString());
			}
        }
        if(node.getAnd() != null)
        {
            node.getAnd().apply(this);
        }
        if(node.getBoolExp3() != null)
        {
            node.getBoolExp3().apply(this);
			b = nodeMap.get(node.getBoolExp3()).toString();
			if(!(b.equals("bool")))
			{
				throw new RuntimeException("TypeError, expecting: 'bool' and 'bool'"+
												"at: "+node.toString());
			}
        }
		nodeMap.put(node,"bool");
		nodeMap.remove(node.getBoolExp2());
		nodeMap.remove(node.getBoolExp3());
        outAAndBoolExp2(node);
    }

    @Override
    public void caseABoolExp3BoolExp2(ABoolExp3BoolExp2 node)
    {
        inABoolExp3BoolExp2(node);
        if(node.getBoolExp3() != null)
        {
            node.getBoolExp3().apply(this);
			nodeMap.put(node,nodeMap.get(node.getBoolExp3()));
			nodeMap.remove(node.getBoolExp3());
        }
        outABoolExp3BoolExp2(node);
    }

	@Override
    public void caseANotBoolExp3(ANotBoolExp3 node)
    {
        inANotBoolExp3(node);
        if(node.getNot() != null)
        {
            node.getNot().apply(this);
        }
        if(node.getBoolExp3() != null)
        {
            node.getBoolExp3().apply(this);
			String a = nodeMap.get(node.getBoolExp3()).toString();
			if(!(a.equals("bool")))
			{
				throw new RuntimeException("TypeError, expecting: not 'bool'"+
												"at: "+node.toString());
			}
        }
		nodeMap.put(node,"bool");
		nodeMap.remove(node.getBoolExp3());
        outANotBoolExp3(node);
    }

    @Override
    public void caseABoolExp4BoolExp3(ABoolExp4BoolExp3 node)
    {
        inABoolExp4BoolExp3(node);
        if(node.getBoolExp4() != null)
        {
            node.getBoolExp4().apply(this);
			nodeMap.put(node,nodeMap.get(node.getBoolExp4()));
			nodeMap.remove(node.getBoolExp4());
        }
        outABoolExp4BoolExp3(node);
    }

    @Override
    public void caseAEqualBoolExp4(AEqualBoolExp4 node)
    {
        inAEqualBoolExp4(node);
        if(node.getBoolExp4() != null)
        {
            node.getBoolExp4().apply(this);
			if(nodeMap.get(node.getBoolExp4()).toString().equals("proc"))
			{
				throw new RuntimeException("TypeError: Everything but 'proc' supported.");
			}
        }
        if(node.getEqual() != null)
        {
            node.getEqual().apply(this);
        }
        if(node.getValExp() != null)
        {
            node.getValExp().apply(this);
			if(nodeMap.get(node.getValExp()).toString().equals("proc"))
			{
				throw new RuntimeException("TypeError: Everything but 'proc' supported.");
			}
        }
		String a = nodeMap.get(node.getValExp()).toString();
		String b = nodeMap.get(node.getBoolExp4()).toString();
		if(!(b.equals(a)))
		{
			throw new RuntimeException("Different Types at: "+node.toString());
		}
		nodeMap.put(node, "bool");
		nodeMap.remove(node.getValExp());
		nodeMap.remove(node.getBoolExp4());
        outAEqualBoolExp4(node);
    }
	
    @Override
    public void caseAOrderingLgeBoolExp4(AOrderingLgeBoolExp4 node)
    {
        inAOrderingLgeBoolExp4(node);
		String help = "";
        if(node.getBoolExp4() != null)
        {
            node.getBoolExp4().apply(this);
			help = nodeMap.get(node.getBoolExp4()).toString();
			if(!(help.startsWith("(") || help.startsWith("<") || help.startsWith("{")
				|| help.equals("int") || help.equals("char")))
			{
				throw new RuntimeException("TypeError, expecting:"+
				"(|a=>b|), <a>, {a}, int, char, at: "+node.toString());
			}
        }
        if(node.getLge() != null)
        {
            node.getLge().apply(this);
        }
        if(node.getValExp() != null)
        {
            node.getValExp().apply(this);
			help = nodeMap.get(node.getValExp()).toString();
			if(!(help.startsWith("(") || help.startsWith("<") || help.startsWith("{")
				|| help.equals("int") || help.equals("char")))
			{
				throw new RuntimeException("TypeError, expecting:"+
				"(|a=>b|), <a>, {a}, int, char, at: "+node.toString());
			}
        }
		String a = nodeMap.get(node.getValExp()).toString();
		String b = nodeMap.get(node.getBoolExp4()).toString();
		if(!(b.equals(a)))
		{
			throw new RuntimeException("Different Types at: "+node.toString());
		}
		nodeMap.put(node,"bool");
		nodeMap.remove(node.getValExp());
		nodeMap.remove(node.getBoolExp4());
        outAOrderingLgeBoolExp4(node);
    }
	
    @Override
    public void caseAOrderingLessBoolExp4(AOrderingLessBoolExp4 node)
    {
        inAOrderingLessBoolExp4(node);
		String help = "";
        if(node.getBoolExp4() != null)
        {
            node.getBoolExp4().apply(this);
			help = nodeMap.get(node.getBoolExp4()).toString();			
			if(!(help.startsWith("(") || help.startsWith("<") || help.startsWith("{")
				|| help.equals("int") || help.equals("char")))
			{
				throw new RuntimeException("TypeError, expecting:"+
				"(|a=>b|), <a>, {a}, int, char, at: "+node.toString());
			}
        }
        if(node.getTriaL() != null)
        {
            node.getTriaL().apply(this);
        }
        if(node.getValExp() != null)
        {
            node.getValExp().apply(this);
			help = nodeMap.get(node.getValExp()).toString();			
			if(!(help.startsWith("(") || help.startsWith("<") || help.startsWith("{")
				|| help.equals("int") || help.equals("char")))
			{
				throw new RuntimeException("TypeError, expecting:"+
				"(|a=>b|), <a>, {a}, int, char, at: "+node.toString());
			}
        }
		String a = nodeMap.get(node.getValExp()).toString();
		String b = nodeMap.get(node.getBoolExp4()).toString();
		if(!(b.equals(a)))
		{
			throw new RuntimeException("Different Types at: "+node.toString());
		}
		nodeMap.put(node,"bool");
		nodeMap.remove(node.getValExp());
		nodeMap.remove(node.getBoolExp4());
        outAOrderingLessBoolExp4(node);
    }


    @Override
    public void caseAOrderingGreaterBoolExp4(AOrderingGreaterBoolExp4 node)
    {
        inAOrderingGreaterBoolExp4(node);
		String help = "";
        if(node.getBoolExp4() != null)
        {
            node.getBoolExp4().apply(this);	
			help = nodeMap.get(node.getBoolExp4()).toString();			
			if(!(help.startsWith("(") || help.startsWith("<") || help.startsWith("{")
				|| help.equals("int") || help.equals("char")))
			{
				throw new RuntimeException("TypeError, expecting:"+
				"(|a=>b|), <a>, {a}, int, char, at: "+node.toString());
			}
        }
        if(node.getTriaR() != null)
        {
            node.getTriaR().apply(this);
        }
        if(node.getValExp() != null)
        {
            node.getValExp().apply(this);
			help = nodeMap.get(node.getValExp()).toString();
			if(!(help.startsWith("(") || help.startsWith("<") || help.startsWith("{")
				|| help.equals("int") || help.equals("char")))
			{
				throw new RuntimeException("TypeError, expecting:"+
				"(|a=>b|), <a>, {a}, int, char, at: "+node.toString());
			}
        }
		String a = nodeMap.get(node.getValExp()).toString();
		String b = nodeMap.get(node.getBoolExp4()).toString();
		if(!(b.equals(a)))
		{
			throw new RuntimeException("Different Types at: "+node.toString());
		}
		nodeMap.put(node,"bool");
		nodeMap.remove(node.getValExp());
		nodeMap.remove(node.getBoolExp4());
        outAOrderingGreaterBoolExp4(node);
    }


    @Override
    public void caseAValExpBoolExp4(AValExpBoolExp4 node)
    {
        inAValExpBoolExp4(node);
        if(node.getValExp() != null)
        {
            node.getValExp().apply(this);
			nodeMap.put(node, nodeMap.get(node.getValExp()));
			nodeMap.remove(node.getValExp());
        }
        outAValExpBoolExp4(node);
    }
	
    @Override
    public void caseAAdditionValExp(AAdditionValExp node)
    {
        inAAdditionValExp(node);
        if(node.getValExp() != null)
        {
            node.getValExp().apply(this);
			if(!(nodeMap.get(node.getValExp()).toString().equals("int")))
			{
				throw new RuntimeException("TypeError, expecting: 'int' at: "+node.toString());
			}
        }
        if(node.getPlus() != null)
        {
            node.getPlus().apply(this);
        }
        if(node.getValExp1() != null)
        {
            node.getValExp1().apply(this);
			if(!(nodeMap.get(node.getValExp1()).toString().equals("int")))
			{
				throw new RuntimeException("TypeError, expecting: 'int' at: "+node.toString());
			}
        }
		nodeMap.put(node,"int");
		nodeMap.remove(node.getValExp());
		nodeMap.remove(node.getValExp1());
        outAAdditionValExp(node);
    }


    @Override
    public void caseASubtractionValExp(ASubtractionValExp node)
    {
        inASubtractionValExp(node);
        if(node.getValExp() != null)
        {
            node.getValExp().apply(this);
			if(!nodeMap.get(node.getValExp()).toString().equals("int"))
			{
				throw new RuntimeException("TypeError, expecting: 'int' at: "+node.toString());
			}
        }
        if(node.getMinus() != null)
        {
            node.getMinus().apply(this);
        }
        if(node.getValExp1() != null)
        {
            node.getValExp1().apply(this);
			if(!nodeMap.get(node.getValExp1()).toString().equals("int"))
			{
				throw new RuntimeException("TypeError, expecting: 'int' at: "+node.toString());
			}
        }
		nodeMap.put(node,"int");
		nodeMap.remove(node.getValExp());
		nodeMap.remove(node.getValExp1());
        outASubtractionValExp(node);
    }


    @Override
    public void caseAValExp1ValExp(AValExp1ValExp node)
    {
        inAValExp1ValExp(node);
        if(node.getValExp1() != null)
        {
            node.getValExp1().apply(this);
			nodeMap.put(node, nodeMap.get(node.getValExp1()));
			nodeMap.remove(node.getValExp1());
        }
        outAValExp1ValExp(node);
    }


    @Override
    public void caseAMultiplicationValExp1(AMultiplicationValExp1 node)
    {
        inAMultiplicationValExp1(node);
        if(node.getValExp1() != null)
        {
            node.getValExp1().apply(this);
			if(!nodeMap.get(node.getValExp1()).toString().equals("int"))
			{
				throw new RuntimeException("TypeError, expecting: 'int' at: "+node.toString());
			}
        }
        if(node.getMulDivMod() != null)
        {
            node.getMulDivMod().apply(this);
        }
        if(node.getValExp2() != null)
        {
            node.getValExp2().apply(this);		
			if(!nodeMap.get(node.getValExp1()).toString().equals("int"))
			{
				throw new RuntimeException("TypeError, expecting: 'int' at: "+node.toString());
			}
        }
		nodeMap.put(node,"int");
		nodeMap.remove(node.getValExp2());
		nodeMap.remove(node.getValExp1());
        outAMultiplicationValExp1(node);
    }
	
    @Override
    public void caseAValExp2ValExp1(AValExp2ValExp1 node)
    {
        inAValExp2ValExp1(node);
        if(node.getValExp2() != null)
        {
            node.getValExp2().apply(this);
			nodeMap.put(node,nodeMap.get(node.getValExp2()));
			nodeMap.remove(node.getValExp2());
        }
        outAValExp2ValExp1(node);
    }


    @Override
    public void caseAUnMinusValExp2(AUnMinusValExp2 node)
    {
        inAUnMinusValExp2(node);
        if(node.getMinus() != null)
        {
            node.getMinus().apply(this);
        }
        if(node.getValExp2() != null)
        {
            node.getValExp2().apply(this);
			if(nodeMap.get(node.getValExp2()).equals("int"))
			{
				nodeMap.put(node, "int");
				nodeMap.remove(node.getValExp2());
			}
			else
			{
				throw new RuntimeException("TypeError, expecting: 'int' at: "+node.toString());
			}
        }
        outAUnMinusValExp2(node);
    }


    @Override
    public void caseASequence0ValExp2(ASequence0ValExp2 node)
    {
        inASequence0ValExp2(node);
        if(node.getSequence0() != null)
        {
            node.getSequence0().apply(this);
			nodeMap.put(node, nodeMap.get(node.getSequence0()));
			nodeMap.remove(node.getSequence0());
        }
        outASequence0ValExp2(node);
    }
	
    @Override
    public void caseALenSequence0(ALenSequence0 node)
    {
        inALenSequence0(node);
        if(node.getHash() != null)
        {
            node.getHash().apply(this);
        }
        if(node.getSequence0() != null)
        {
            node.getSequence0().apply(this);
			if(nodeMap.get(node.getSequence0()).toString().startsWith("<"))
			{
				nodeMap.put(node, "int");
				nodeMap.remove(node.getSequence0());
			}
			else
			{
				throw new RuntimeException("TypeError, expecting: <a> at: "+node.toString());
			}
        }
        outALenSequence0(node);
    }


    @Override
    public void caseASequence1Sequence0(ASequence1Sequence0 node)
    {
        inASequence1Sequence0(node);
        if(node.getSequence1() != null)
        {
            node.getSequence1().apply(this);
			nodeMap.put(node, nodeMap.get(node.getSequence1()));
			nodeMap.remove(node.getSequence1());
        }
        outASequence1Sequence0(node);
    }


    @Override
    public void caseACatSequence1(ACatSequence1 node)
    {
        inACatSequence1(node);
		String a = "";
		String b = "";
        if(node.getSequence1() != null)
        {
            node.getSequence1().apply(this);
			a = nodeMap.get(node.getAtom()).toString();
			if(!(a.startsWith("<")))
			{
				throw new RuntimeException("TypeError, expecting: <a> at: "+node.toString());
			}		
        }
        if(node.getCat() != null)
        {
            node.getCat().apply(this);
        }
        if(node.getAtom() != null)
        {
            node.getAtom().apply(this);
			b = nodeMap.get(node.getSequence1()).toString();
			if(!(b.startsWith("<")))
			{
				throw new RuntimeException("TypeError, expecting: <a> at: "+node.toString());
			}

        }
		if(a.equals(b))
		{
			nodeMap.put(node, nodeMap.get(node.getAtom()));
			nodeMap.remove(node.getSequence1());
			nodeMap.remove(node.getAtom());
		}
		else
		{
			throw new RuntimeException("Different Types at: "+node.toString());
		}
        outACatSequence1(node);
    }

	@Override
    public void caseAAtomSequence1(AAtomSequence1 node)
    {
        inAAtomSequence1(node);
        if(node.getAtom() != null)
        {
            node.getAtom().apply(this);
			nodeMap.put(node,nodeMap.get(node.getAtom()));
			nodeMap.remove(node.getAtom());
        }
        outAAtomSequence1(node);
    }


//***************************************************************************************
//Atoms	

	@Override
    public void caseATrueFalseAtom(ATrueFalseAtom node)
    {
        inATrueFalseAtom(node);
        if(node.getTrueFalse() != null)
        {
            node.getTrueFalse().apply(this);
			nodeMap.put(node,"bool");
        }
        outATrueFalseAtom(node);
    }
	
	@Override
    public void caseANumAtom(ANumAtom node)
    {
        inANumAtom(node);
        if(node.getNumber() != null)
        {
            node.getNumber().apply(this);
			nodeMap.put(node,"int");
        }
        outANumAtom(node);
    }
	
	@Override
    public void caseAFidAtom(AFidAtom node)
    {
        inAFidAtom(node);
        if(node.getFuncId() != null)
        {
            node.getFuncId().apply(this);
			nodeMap.put(node,nodeMap.get(node.getFuncId()));
			nodeMap.remove(node.getFuncId());			
        }
        outAFidAtom(node);
    }
	
	@Override
    public void caseATupleAtom(ATupleAtom node)
    {
        inATupleAtom(node);
        if(node.getTuple() != null)
        {
            node.getTuple().apply(this);
			nodeMap.put(node, currentTuple);
			if(tupleDepth<0)
			{
				System.out.println(currentTuple);
				currentTuple = "";
			}
        }
        if(node.getLambda() != null)
        {
            node.getLambda().apply(this);
        }
        outATupleAtom(node);
    }
	
    @Override
    public void caseASetAtom(ASetAtom node)
    {
        inASetAtom(node);
        if(node.getSet() != null)
        {
            node.getSet().apply(this);
			nodeMap.put(node, nodeMap.get(node.getSet()));
			nodeMap.remove(node.getSet());
        }
        outASetAtom(node);
    }
	
	@Override
    public void caseASequenceAtom(ASequenceAtom node)
    {
        inASequenceAtom(node);
        if(node.getSequence() != null)
        {
            node.getSequence().apply(this);
			nodeMap.put(node,nodeMap.get(node.getSequence()));
			nodeMap.remove(node.getSequence());
        }
        outASequenceAtom(node);
    }
	
//***************************************************************************************
//Function and ID	
	@Override
    public void caseAFuncId(AFuncId node)
    {
        inAFuncId(node);
		int count = 0;
        if(node.getId() != null)
        {
            node.getId().apply(this);
        }
        {
            List<PTuple> copy = new ArrayList<PTuple>(node.getTuple());
            for(PTuple e : copy)
            {
				count++;
                e.apply(this);
            }
        }
		if(count == 0)
		{
			nodeMap.put(node, nodeMap.get(node.getId()));
			nodeMap.remove(node.getId());
		}
        outAFuncId(node);
    }
	
	@Override
    public void caseAIdId(AIdId node)
    {
        inAIdId(node);
        if(node.getIdentifier() != null)
        {
            node.getIdentifier().apply(this);
			if(types.containsKey(node.getIdentifier().getText()))
			{
				nodeMap.put(node,types.get(node.getIdentifier().getText()));
			}
			else
			{
				nodeMap.put(node,"id");			
			}
        }
        outAIdId(node);
    }

    @Override
    public void caseALtlId(ALtlId node)
    {
        inALtlId(node);
        if(node.getLtl() != null)
        {
            node.getLtl().apply(this);
			if(types.containsKey(node.getLtl().getText()))
			{
				nodeMap.put(node,types.get(node.getLtl().getText()));
			}
			else
			{
				nodeMap.put(node,"id");			
			}
        }
        outALtlId(node);
    }

    @Override
    public void caseACtlId(ACtlId node)
    {
        inACtlId(node);
        if(node.getCtl() != null)
        {
            node.getCtl().apply(this);
			if(types.containsKey(node.getCtl().getText()))
			{
				nodeMap.put(node,types.get(node.getCtl().getText()));
			}
			else
			{
				nodeMap.put(node,"id");			
			}
        }
        outACtlId(node);
    }
	

//***************************************************************************************
//Sets

    @Override
    public void caseAEmptySetSet(AEmptySetSet node)
    {
        inAEmptySetSet(node);
        if(node.getBraceL() != null)
        {
            node.getBraceL().apply(this);
        }
        if(node.getBraceR() != null)
        {
            node.getBraceR().apply(this);
        }
		nodeMap.put(node, "{}");
        outAEmptySetSet(node);
    }

    @Override
    public void caseASetSet(ASetSet node)
    {
        inASetSet(node);
        if(node.getBraceL() != null)
        {
            node.getBraceL().apply(this);
        }
        if(node.getArguments() != null)
        {
            node.getArguments().apply(this); //Baustelle
        }
        if(node.getBraceR() != null)
        {
            node.getBraceR().apply(this);
        }
		nodeMap.put(node,"{"+arguments.get(argDepth+1).get(0)+"}");
		System.out.println(nodeMap.get(node));
		if(argDepth<0)
		{
			arguments.clear();
		}
        outASetSet(node);
    }

    @Override
    public void caseAClosedRangeSet(AClosedRangeSet node)
    {
        inAClosedRangeSet(node);
        if(node.getBraceL() != null)
        {
            node.getBraceL().apply(this);
        }
        if(node.getL() != null)
        {
            node.getL().apply(this);
			if(!(nodeMap.get(node.getL()).toString().equals("int")))
			{
				throw new RuntimeException("TypeError, expecting 'int' at: "
										+node.toString()); 
			}
        }
        if(node.getDotdot() != null)
        {
            node.getDotdot().apply(this);
        }
        if(node.getR() != null)
        {
            node.getR().apply(this);
			if(!(nodeMap.get(node.getR()).toString().equals("int")))
			{
				throw new RuntimeException("TypeError, expecting 'int' at: "
										+node.toString()); 
			}
        }
        if(node.getBraceR() != null)
        {
            node.getBraceR().apply(this);
        }
		nodeMap.put(node,"{int}");
		nodeMap.remove(node.getL());
		nodeMap.remove(node.getR());
        outAClosedRangeSet(node);
    }

    @Override
    public void caseAOpenRangeSet(AOpenRangeSet node)
    {
        inAOpenRangeSet(node);
        if(node.getBraceL() != null)
        {
            node.getBraceL().apply(this);
        }
        if(node.getValExp() != null)
        {
            node.getValExp().apply(this);
			if(!(nodeMap.get(node.getValExp()).toString().equals("int")))
			{
				throw new RuntimeException("TypeError, expecting 'int' at: "
										+node.toString()); 
			}
        }
        if(node.getDotdot() != null)
        {
            node.getDotdot().apply(this);
        }
        if(node.getBraceR() != null)
        {
            node.getBraceR().apply(this);
        }
		nodeMap.put(node,"{int}");
		nodeMap.remove(node.getValExp());
        outAOpenRangeSet(node);
    }
	
    @Override
    public void caseASetComprehensionSet(ASetComprehensionSet node)
    {
        inASetComprehensionSet(node);
        if(node.getBraceL() != null)
        {
            node.getBraceL().apply(this);
        }
        if(node.getArguments() != null)
        {
            node.getArguments().apply(this); //Baustelle	
        }
        if(node.getPipe() != null)
        {
            node.getPipe().apply(this);
        }
        if(node.getStmtsSet() != null)
        {
            node.getStmtsSet().apply(this);
        }
        if(node.getBraceR() != null)
        {
            node.getBraceR().apply(this);
        }
		nodeMap.put(node,"{"+arguments.get(argDepth+1).get(0)+"}");
		if(argDepth<0)
		{
			arguments.clear();
		}
        outASetComprehensionSet(node);
    }

    @Override
    public void caseARangedComprehensionSet(ARangedComprehensionSet node)
    {
        inARangedComprehensionSet(node);
        if(node.getBraceL() != null)
        {
            node.getBraceL().apply(this);
        }
        if(node.getL() != null)
        {
            node.getL().apply(this);
			if(!(nodeMap.get(node.getL()).toString().equals("int")))
			{
				throw new RuntimeException("TypeError, expecting: 'int' at: "
										+node.toString()); 
			}
        }
        if(node.getDotdot() != null)
        {
            node.getDotdot().apply(this);
        }
        if(node.getR() != null)
        {
            node.getR().apply(this);
			if(!(nodeMap.get(node.getR()).toString().equals("int")))
			{
				throw new RuntimeException("TypeError, expecting 'int' at: "
										+node.toString()); 
			}
        }
        if(node.getPipe() != null)
        {
            node.getPipe().apply(this);
        }
        if(node.getStmtsSet() != null)
        {
            node.getStmtsSet().apply(this);
        }
        if(node.getBraceR() != null)
        {
            node.getBraceR().apply(this);
        }
		nodeMap.put(node,"{int}");
		nodeMap.remove(node.getR());
		nodeMap.remove(node.getL());
        outARangedComprehensionSet(node);
    }

    @Override
    public void caseAInfiniteComprehensionSet(AInfiniteComprehensionSet node)
    {
        inAInfiniteComprehensionSet(node);
        if(node.getBraceL() != null)
        {
            node.getBraceL().apply(this);
        }
        if(node.getValExp() != null)
        {
            node.getValExp().apply(this);
			if(!(nodeMap.get(node.getValExp()).toString().equals("int")))
			{
				throw new RuntimeException("TypeError, expecting 'int' at: "
										+node.toString()); 
			}
        }
        if(node.getDotdot() != null)
        {
            node.getDotdot().apply(this);
        }
        if(node.getPipe() != null)
        {
            node.getPipe().apply(this);
        }
        if(node.getStmtsSet() != null)
        {
            node.getStmtsSet().apply(this);
        }
        if(node.getBraceR() != null)
        {
            node.getBraceR().apply(this);
        }
		nodeMap.put(node,"{int}");
		nodeMap.remove(node.getValExp());
        outAInfiniteComprehensionSet(node);
    }

    @Override
    public void caseAFuncSetSet(AFuncSetSet node)
    {
        inAFuncSetSet(node);
        if(node.getFuncRetSet() != null)
        {
            node.getFuncRetSet().apply(this);
			nodeMap.put(node, nodeMap.get(node.getFuncRetSet()));
			nodeMap.remove(node.getFuncRetSet());
        }
        outAFuncSetSet(node);
    }

    @Override
    public void caseAEnumSetSet(AEnumSetSet node)
    {
        inAEnumSetSet(node);
        if(node.getEnumSet() != null)
        {
            node.getEnumSet().apply(this);
			nodeMap.put(node, nodeMap.get(node.getEnumSet()));
			nodeMap.remove(node.getEnumSet());
        }
        outAEnumSetSet(node);
    }


    @Override
    public void caseASetCompSet(ASetCompSet node)
    {
        inASetCompSet(node);
        if(node.getSetComp() != null)
        {
            node.getSetComp().apply(this);
			nodeMap.put(node, nodeMap.get(node.getSetComp()));
			nodeMap.remove(node.getSetComp());
        }
        outASetCompSet(node);
	}

//***************************************************************************************
//Sequence
    @Override
    public void caseAEmptySeqSequence(AEmptySeqSequence node)
    {
        inAEmptySeqSequence(node);
        if(node.getTriaL() != null)
        {
            node.getTriaL().apply(this);
        }
        if(node.getTriaR() != null)
        {
            node.getTriaR().apply(this);
        }
		nodeMap.put(node,"<>");
        outAEmptySeqSequence(node);
    }

    @Override
    public void caseAExplSeqSequence(AExplSeqSequence node)
    {
        inAExplSeqSequence(node);
        if(node.getTriaL() != null)
        {
            node.getTriaL().apply(this);
        }
        if(node.getInnerSequence() != null)
        {
            node.getInnerSequence().apply(this);
			nodeMap.put(node,"<"+innerseq.get(seqDepth+1).get(0)+">");
			System.out.println(nodeMap.get(node));
			if(seqDepth<0)
			{
				innerseq.clear();
		    }
        }
        if(node.getTriaR() != null)
        {
            node.getTriaR().apply(this);
        }
        outAExplSeqSequence(node);
    }

    @Override
    public void caseAListCompSequence(AListCompSequence node)
    {
        inAListCompSequence(node);
        if(node.getTriaL() != null)
        {
            node.getTriaL().apply(this);
        }
        if(node.getInnerSequence() != null)
        {
            node.getInnerSequence().apply(this);
			nodeMap.put(node,innerseq.get(argDepth+1).get(0));
			if(argDepth<0)
			{
				arguments.clear();
			}
        }
        if(node.getPipe() != null)
        {
            node.getPipe().apply(this);
        }
        if(node.getStmtsSeq() != null)
        {
            node.getStmtsSeq().apply(this);
        }
        if(node.getTriaR() != null)
        {
            node.getTriaR().apply(this);
        }
        outAListCompSequence(node);
    }

    @Override
    public void caseACrSeqSequence(ACrSeqSequence node)
    {
        inACrSeqSequence(node);
        if(node.getTriaL() != null)
        {
            node.getTriaL().apply(this);
        }
        if(node.getL() != null)
        {
            node.getL().apply(this);
			if(!(nodeMap.get(node.getL()).toString().equals("int")))
			{
				throw new RuntimeException("TypeError, expecting 'int' at: "
									+node.toString());
			}
        }
        if(node.getDotdot() != null)
        {
            node.getDotdot().apply(this);
        }
        if(node.getR() != null)
        {
            node.getR().apply(this);
			if(!(nodeMap.get(node.getR()).toString().equals("int")))
			{
				throw new RuntimeException("TypeError, expecting 'int' at: "
									+node.toString());
			}
        }
        if(node.getTriaR() != null)
        {
            node.getTriaR().apply(this);
        }
		nodeMap.put(node,"<int>");
		nodeMap.remove(node.getR());
		nodeMap.remove(node.getL());
        outACrSeqSequence(node);
    }

    @Override
    public void caseARanCompSequence(ARanCompSequence node)
    {
        inARanCompSequence(node);
        if(node.getTriaL() != null)
        {
            node.getTriaL().apply(this);
        }
        if(node.getL() != null)
        {
            node.getL().apply(this);
			if(!(nodeMap.get(node.getL()).toString().equals("int")))
			{
				throw new RuntimeException("TypeError, expecting 'int' at: "
									+node.toString());
			}
        }
        if(node.getDotdot() != null)
        {
            node.getDotdot().apply(this);
        }
        if(node.getR() != null)
        {
            node.getR().apply(this);
			if(!(nodeMap.get(node.getR()).toString().equals("int")))
			{
				throw new RuntimeException("TypeError, expecting 'int' at: "
									+node.toString());
			}
        }
        if(node.getPipe() != null)
        {
            node.getPipe().apply(this);
        }
        if(node.getStmtsSeq() != null)
        {
            node.getStmtsSeq().apply(this);
        }
        if(node.getTriaR() != null)
        {
            node.getTriaR().apply(this);
        }
		nodeMap.put(node,"<int>");
		nodeMap.remove(node.getR());
		nodeMap.remove(node.getL());
        outARanCompSequence(node);
    }

    @Override
    public void caseAOrSeqSequence(AOrSeqSequence node)
    {
        inAOrSeqSequence(node);
        if(node.getTriaL() != null)
        {
            node.getTriaL().apply(this);
        }
        if(node.getValExp() != null)
        {
            node.getValExp().apply(this);
			if(!(nodeMap.get(node.getValExp()).toString().equals("int")))
			{
				throw new RuntimeException("TypeError, expecting 'int' at: "
									+node.toString());
			}
        }
        if(node.getDotdot() != null)
        {
            node.getDotdot().apply(this);
        }
        if(node.getTriaR() != null)
        {
            node.getTriaR().apply(this);
        }
		nodeMap.put(node,"<int>");
		nodeMap.remove(node.getValExp());
        outAOrSeqSequence(node);
    }

    @Override
    public void caseAInfComprSequence(AInfComprSequence node)
    {
        inAInfComprSequence(node);
        if(node.getTriaL() != null)
        {
            node.getTriaL().apply(this);
        }
        if(node.getValExp() != null)
        {
            node.getValExp().apply(this);
			if(!(nodeMap.get(node.getValExp()).toString().equals("int")))
			{
				throw new RuntimeException("TypeError, expecting 'int' at: "
									+node.toString());
			}
        }
        if(node.getDotdot() != null)
        {
            node.getDotdot().apply(this);
        }
        if(node.getPipe() != null)
        {
            node.getPipe().apply(this);
        }
        if(node.getStmtsSeq() != null)
        {
            node.getStmtsSeq().apply(this);
        }
        if(node.getTriaR() != null)
        {
            node.getTriaR().apply(this);
        }
		nodeMap.put(node,"<int>");
		nodeMap.remove(node.getValExp());
        outAInfComprSequence(node);
    }

    @Override
    public void caseAEnumSeqSequence(AEnumSeqSequence node)
    {
        inAEnumSeqSequence(node);
        if(node.getEnumSeq() != null)
        {
            node.getEnumSeq().apply(this); //Baustelle
        }
        outAEnumSeqSequence(node);
    }

    @Override
    public void caseASeqCompSequence(ASeqCompSequence node)
    {
        inASeqCompSequence(node);
        if(node.getSeqComp() != null)
        {
            node.getSeqComp().apply(this);
			nodeMap.put(node,"<int>");
			nodeMap.remove(node.getSeqComp());
        }
        outASeqCompSequence(node);
    }

    @Override
    public void caseAGsStmtsSeq(AGsStmtsSeq node)
    {
        inAGsStmtsSeq(node);
        if(node.getGenStmtSeq() != null)
        {
            node.getGenStmtSeq().apply(this);
        }
        outAGsStmtsSeq(node);
    }

    @Override
    public void caseAPsStmtsSeq(APsStmtsSeq node)
    {
        inAPsStmtsSeq(node);
        if(node.getPredStmtSeq() != null)
        {
            node.getPredStmtSeq().apply(this);
        }
        outAPsStmtsSeq(node);
    }

    @Override
    public void caseAGenStmtSeq(AGenStmtSeq node)
    {
        inAGenStmtSeq(node);
        if(node.getL() != null)
        {
            node.getL().apply(this);
        }
        if(node.getArrowL() != null)
        {
            node.getArrowL().apply(this);
        }
        if(node.getR() != null)
        {
            node.getR().apply(this);
        }
        {
            List<PStmtRekSeq> copy = new ArrayList<PStmtRekSeq>(node.getStmtRekSeq());
            for(PStmtRekSeq e : copy)
            {
                e.apply(this);
            }
        }
        outAGenStmtSeq(node);
    }

    @Override
    public void caseAGsStmtRekSeq(AGsStmtRekSeq node)
    {
        inAGsStmtRekSeq(node);
        if(node.getComma() != null)
        {
            node.getComma().apply(this);
        }
        if(node.getL() != null)
        {
            node.getL().apply(this);
        }
        if(node.getArrowL() != null)
        {
            node.getArrowL().apply(this);
        }
        if(node.getR() != null)
        {
            node.getR().apply(this);
        }
        outAGsStmtRekSeq(node);
    }
	
    @Override
    public void caseAPsStmtRekSeq(APsStmtRekSeq node)
    {
        inAPsStmtRekSeq(node);
        if(node.getComma() != null)
        {
            node.getComma().apply(this);
        }
        if(node.getPredStmtSeq() != null)
        {
            node.getPredStmtSeq().apply(this);
        }
        outAPsStmtRekSeq(node);
    }

    @Override
    public void caseAPredStmtSeq(APredStmtSeq node)
    {
        inAPredStmtSeq(node);
        if(node.getValExp() != null)
        {
            node.getValExp().apply(this);
        }
        outAPredStmtSeq(node);
    }
	
    @Override
    public void caseAEnumSeq(AEnumSeq node)
    {
        inAEnumSeq(node);
        if(node.getTriaL() != null)
        {
            node.getTriaL().apply(this);
        }
        if(node.getL() != null)
        {
            node.getL().apply(this);
        }
        if(node.getArguments() != null)
        {
            node.getArguments().apply(this);
        }
        if(node.getR() != null)
        {
            node.getR().apply(this);
        }
        if(node.getTriaR() != null)
        {
            node.getTriaR().apply(this);
        }
        outAEnumSeq(node);
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
        if(node.getArguments() != null)
        {
            node.getArguments().apply(this);
			nodeMap.put(node,arguments.get(argDepth+1).get(0));
			if(argDepth<0)
			{
				arguments.clear();
			}
        }
        if(node.getM() != null)
        {
            node.getM().apply(this);
        }
        if(node.getStmtsSet() != null)
        {
            node.getStmtsSet().apply(this);
        }
        if(node.getR() != null)
        {
            node.getR().apply(this);
        }
        if(node.getTriaR() != null)
        {
            node.getTriaR().apply(this);
        }
        outASeqComp(node);
    }

//***************************************************************************************
//Inner Sequence
    @Override
    public void caseAInnerSequence(AInnerSequence node)
    {
        inAInnerSequence(node);
        if(node.getInnerSequenceRek() != null)
        {
			seqDepth++;
			innerseq.add(seqDepth,new ArrayList<String>());
            node.getInnerSequenceRek().apply(this);
			seqDepth = seqDepth-1;
        }
        outAInnerSequence(node);
    }

    @Override
    public void caseAStartInnerSequenceRek(AStartInnerSequenceRek node)
    {
        inAStartInnerSequenceRek(node);
        if(node.getValExp() != null)
        {
            node.getValExp().apply(this);
			String a = nodeMap.get(node.getValExp()).toString();
			innerseq.get(seqDepth).add(a);
        }
        if(node.getComma() != null)
        {
            node.getComma().apply(this);
        }
        if(node.getInnerSequenceRek() != null)
        {
            node.getInnerSequenceRek().apply(this);
        }
		nodeMap.remove(node.getValExp());
        outAStartInnerSequenceRek(node);
    }

    @Override
    public void caseAEndInnerSequenceRek(AEndInnerSequenceRek node)
    {
        inAEndInnerSequenceRek(node);
        if(node.getValExp() != null)
        {
            node.getValExp().apply(this);
			String a = nodeMap.get(node.getValExp()).toString();
			innerseq.get(seqDepth).add(a);
        }
		for(int i = 1; i<innerseq.get(seqDepth).size();i++)
		{
			if(!(innerseq.get(seqDepth).get(0).equals(innerseq.get(seqDepth).get(i))))
			{
				throw new RuntimeException("TypeError, expecting "
							+innerseq.get(seqDepth).get(0)+" at:"+node.toString());
			}
		}
		nodeMap.remove(node.getValExp());
        outAEndInnerSequenceRek(node);
    }

//***************************************************************************************
//Tuple
    @Override
    public void caseATupleTuple(ATupleTuple node)
    {
        inATupleTuple(node);
        if(node.getParL() != null)
        {
            node.getParL().apply(this);
        }
        if(node.getInnerTuple() != null)
        {
			tupleDepth++;
			currentTuple += "(";
            node.getInnerTuple().apply(this);
			currentTuple += ")";
			tupleDepth = tupleDepth-1;
        }
        if(node.getParR() != null)
        {
            node.getParR().apply(this);
        }
        outATupleTuple(node);
    }

    @Override
    public void caseAInnerStartInnerTuple(AInnerStartInnerTuple node)
    {
        inAInnerStartInnerTuple(node);
        if(node.getProc1() != null)
        {
            node.getProc1().apply(this);
			String a = nodeMap.get(node.getProc1()).toString();
			currentTuple += a+",";
        }
        if(node.getComma() != null)
        {
            node.getComma().apply(this);
        }
        if(node.getInnerTuple() != null)
        {
            node.getInnerTuple().apply(this);
        }
		nodeMap.remove(node.getProc1());
        outAInnerStartInnerTuple(node);
    }
	
	@Override
    public void caseAInnerEndInnerTuple(AInnerEndInnerTuple node)
    {
        inAInnerEndInnerTuple(node);
        if(node.getProc1() != null)
        {
            node.getProc1().apply(this);
			String a = nodeMap.get(node.getProc1()).toString();
			currentTuple += a;
        }
		nodeMap.remove(node.getProc1());
        outAInnerEndInnerTuple(node);
    }

//***************************************************************************************
//Arguments
    @Override
    public void caseAArguments(AArguments node)
    {
        inAArguments(node);
		argDepth++;
		arguments.add(argDepth, new ArrayList<String>());
        if(node.getArgumentsRek() != null)
        {
            node.getArgumentsRek().apply(this);
        }
		argDepth = argDepth-1;
        outAArguments(node);
    }

    @Override
    public void caseAArgStartArgumentsRek(AArgStartArgumentsRek node)
    {
        inAArgStartArgumentsRek(node);
		if(node.getProc1() != null)
        {
            node.getProc1().apply(this);
			String a = nodeMap.get(node.getProc1()).toString();
			arguments.get(argDepth).add(a);
        }
        if(node.getComma() != null)
        {
            node.getComma().apply(this);
        }
		if(node.getArgumentsRek() != null)
        {
            node.getArgumentsRek().apply(this);
        }
		nodeMap.remove(node.getProc1());
        outAArgStartArgumentsRek(node);
    }

    @Override
    public void caseAArgEndArgumentsRek(AArgEndArgumentsRek node)
    {
        inAArgEndArgumentsRek(node);
        if(node.getProc1() != null)
        {
            node.getProc1().apply(this);
			String a = nodeMap.get(node.getProc1()).toString();
			arguments.get(argDepth).add(a);
        }

		for(int i = 1; i<arguments.get(argDepth).size();i++)
		{
			if(!(arguments.get(argDepth).get(0).equals(arguments.get(argDepth).get(i))))
			{
				throw new RuntimeException("TypeError, expecting "
							+arguments.get(argDepth).get(0)+" at:"+node.toString());
			}
		}
		nodeMap.remove(node.getProc1());
        outAArgEndArgumentsRek(node);
    }
}