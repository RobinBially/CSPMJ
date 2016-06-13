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
				throw new RuntimeException("Incorrect types in Line "
							+node.getDoubleat().getLine());
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
				throw new RuntimeException("Incorrect types in Line "
							+node.getDoubleat().getLine());
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
				throw new RuntimeException("Incorrect types in Line "
							+node.getBSlash().getLine());
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
				throw new RuntimeException("Incorrect types in Line "
							+node.getBSlash().getLine());
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
				throw new RuntimeException("Incorrect types in Line "
							+node.getILeaving().getLine());
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
				throw new RuntimeException("Incorrect types in Line "
							+node.getILeaving().getLine());
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
				throw new RuntimeException("Incorrect types in Line "
							+node.getBracketPipeL().getLine());
			}
        }
        if(node.getBracketPipeL() != null)
        {
            node.getBracketPipeL().apply(this);
        }
        if(node.getEvent() != null)
        {
            node.getEvent().apply(this);
			if(!(nodeMap.get(node.getEvent()).toString().equals("event")))
			{
				throw new RuntimeException("Incorrect types in Line "
							+node.getBracketPipeL().getLine());
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
				throw new RuntimeException("Incorrect types in Line "
							+node.getTriaR().getLine());
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
				throw new RuntimeException("Incorrect types in Line "
							+node.getBracketPipeL().getLine());
			}
        }
        if(node.getBracketPipeL() != null)
        {
            node.getBracketPipeL().apply(this);
        }
        if(node.getEvent() != null)
        {
            node.getEvent().apply(this);
			if(!(nodeMap.get(node.getEvent()).toString().equals("event")))
			{
				throw new RuntimeException("Incorrect types in Line "
							+node.getBracketPipeL().getLine());
			}
        }
        if(node.getBracketPipeR() != null)
        {
            node.getBracketPipeR().apply(this);
        }
        if(node.getProc5() != null)
        {
            node.getProc5().apply(this);
			if(!(nodeMap.get(node.getProc5()).toString().equals("proc")))
			{
				throw new RuntimeException("Incorrect types in Line "
							+node.getBracketPipeR().getLine());
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
				throw new RuntimeException("Incorrect types in Line "
							+node.getBracketL().getLine());
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
				throw new RuntimeException("Incorrect types in Line "
							+node.getBracketL().getLine());
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
				throw new RuntimeException("Incorrect types in Line "
							+node.getDpipe().getLine());
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
				throw new RuntimeException("Incorrect types in Line "
							+node.getBracketR().getLine());
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
				throw new RuntimeException("Incorrect types in Line "
							+node.getBracketL().getLine());
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
				throw new RuntimeException("Incorrect types in Line "
							+node.getBracketR().getLine());
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
				throw new RuntimeException("Incorrect types in Line "
							+node.getIChoice().getLine());
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
				throw new RuntimeException("Incorrect types in Line "
							+node.getIChoice().getLine());
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
				throw new RuntimeException("Incorrect types in Line "
							+node.getEChoice().getLine());
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
				throw new RuntimeException("Incorrect types in Line "
							+node.getEChoice().getLine());
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
				throw new RuntimeException("Incorrect types in Line "
							+node.getSyncParL().getLine());
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
				throw new RuntimeException("Incorrect types in Line "
							+node.getSyncParL().getLine());
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
				throw new RuntimeException("Incorrect types in Line "
							+node.getSyncParR().getLine());
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
				throw new RuntimeException("Incorrect types in Line "
							+node.getInterrupt().getLine());
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
				throw new RuntimeException("Incorrect types in Line "
							+node.getInterrupt().getLine());
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
				throw new RuntimeException("Incorrect types in Line "
							+node.getSyncIntL().getLine());
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
				throw new RuntimeException("Incorrect types in Line "
							+node.getSyncIntL().getLine());
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
				throw new RuntimeException("Incorrect types in Line "
							+node.getSyncIntR().getLine());
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
				throw new RuntimeException("Incorrect types in Line "
							+node.getTimeout().getLine());
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
				throw new RuntimeException("Incorrect types in Line "
							+node.getTimeout().getLine());
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
				throw new RuntimeException("Incorrect types in Line "
							+node.getSemicolon().getLine());
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
				throw new RuntimeException("Incorrect types in Line "
							+node.getSemicolon().getLine());
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
				throw new RuntimeException("Incorrect types in Line "
							+node.getGuard().getLine());
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
				throw new RuntimeException("Incorrect types in Line "
							+node.getGuard().getLine());
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
				throw new RuntimeException("Incorrect types in Line "
							+node.getPrefix().getLine());
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
				throw new RuntimeException("Incorrect types in Line "
							+node.getPrefix().getLine());
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
        if(node.getProc10() != null)
        {
            node.getProc10().apply(this);
			if(!(nodeMap.get(node.getProc10()).toString().equals("proc")))
			{
				throw new RuntimeException("Incorrect types in Line "
							+node.getWithin().getLine());
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
				throw new RuntimeException("Incorrect types in Line "
							+node.getIf().getLine());
			}
        }
		if(node.getThen() != null)
        {
            node.getThen().apply(this);
        }
        if(node.getProc1() != null)
        {
            node.getProc1().apply(this);
			if(!(nodeMap.get(node.getProc1()).toString().equals("proc")))
			{
				throw new RuntimeException("Incorrect types in Line "
							+node.getThen().getLine());
			}
        }
        if(node.getElse() != null)
        {
            node.getElse().apply(this);
        }
        if(node.getProc10() != null)
        {
            node.getProc10().apply(this);
			if(!(nodeMap.get(node.getProc10()).toString().equals("proc")))
			{
				throw new RuntimeException("Incorrect types in Line "
							+node.getElse().getLine());
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
				throw new RuntimeException("Incorrect types in Line "
							+node.getDbracketL().getLine());
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
				throw new RuntimeException("Incorrect types in Line "
							+node.getDbracketL().getLine());
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
				throw new RuntimeException("Incorrect types in Line "
							+node.getOr().getLine());
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
				throw new RuntimeException("Incorrect types in Line "
							+node.getOr().getLine());
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
				throw new RuntimeException("Incorrect types in Line "
							+node.getAnd().getLine()+", Column "
							+node.getAnd().getPos());
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
				throw new RuntimeException("Incorrect types in Line "
							+node.getAnd().getLine()+", Column "
							+node.getAnd().getPos());
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
				throw new RuntimeException("Incorrect types in Line "
							+node.getNot().getLine());
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
				throw new RuntimeException("Incorrect types in Line "
							+node.getEqual().getLine());
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
			throw new RuntimeException("Incorrect types in Line "
							+node.getEqual().getLine());
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
				throw new RuntimeException("Incorrect types in Line "
							+node.getLge().getLine());
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
				throw new RuntimeException("Incorrect types in Line "
							+node.getLge().getLine());
			}
        }
		String a = nodeMap.get(node.getValExp()).toString();
		String b = nodeMap.get(node.getBoolExp4()).toString();
		if(!(b.equals(a)))
		{
			throw new RuntimeException("Incorrect types in Line "
							+node.getLge().getLine());
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
		//	System.out.println(help);
			if(!(help.startsWith("(") || help.startsWith("<") || help.startsWith("{")
				|| help.equals("int") || help.equals("char")))
			{
				throw new RuntimeException("Incorrect types in Line "
							+node.getLess().getLine());
			}
        }
        if(node.getLess() != null)
        {
            node.getLess().apply(this);
        }
        if(node.getValExp() != null)
        {
            node.getValExp().apply(this);
			help = nodeMap.get(node.getValExp()).toString();			
			if(!(help.startsWith("(") || help.startsWith("<") || help.startsWith("{")
				|| help.equals("int") || help.equals("char")))
			{
				throw new RuntimeException("Incorrect types in Line "
							+node.getLess().getLine());
			}
        }
		String a = nodeMap.get(node.getValExp()).toString();
		String b = nodeMap.get(node.getBoolExp4()).toString();
		if(!(b.equals(a)))
		{
			throw new RuntimeException("Incorrect types in Line "
							+node.getLess().getLine());
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
				throw new RuntimeException("Incorrect types in Line "
							+node.getGreater().getLine());
			}
        }
        if(node.getGreater() != null)
        {
            node.getGreater().apply(this);
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
			throw new RuntimeException("Incorrect types in Line "
							+node.getGreater().getLine());
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
				throw new RuntimeException("Incorrect types in Line "
							+node.getPlus().getLine());
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
				throw new RuntimeException("Incorrect types in Line "
							+node.getPlus().getLine());
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
				throw new RuntimeException("Incorrect types in Line "
							+node.getMinus().getLine());
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
				throw new RuntimeException("Incorrect types in Line "
							+node.getMulDivMod().getLine());
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
				throw new RuntimeException("Incorrect types in Line "
							+node.getMulDivMod().getLine());
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
				throw new RuntimeException("Incorrect types in Line "
							+node.getMinus().getLine());
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
				throw new RuntimeException("Incorrect types in Line "
							+node.getHash().getLine());
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
				throw new RuntimeException("Incorrect types in Line "
							+node.getCat().getLine());
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
				throw new RuntimeException("Incorrect types in Line "
							+node.getCat().getLine());
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
				throw new RuntimeException("Incorrect types in Line "
							+node.getCat().getLine());
				
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
			String a = nodeMap.get(node.getTuple()).toString();
			if(getTupleLength(a) == 1)
			{
				char[] c = a.toCharArray();
				String temp = new String(c);
				if(c[0] == '('){c[0] = ' ';}
				if(c[c.length-1] == ')'){c[c.length-1] = ' ';}
				temp = new String(c);
				temp = temp.replaceAll(" ","");
				nodeMap.put(node, temp);
			}
			else
			{
				nodeMap.put(node, nodeMap.get(node.getTuple()));
			}
        }
        if(node.getLambda() != null)
        {
            node.getLambda().apply(this);
        }
		nodeMap.remove(node.getTuple());
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
	
   @Override
    public void caseAStringAtom(AStringAtom node)
    {
        inAStringAtom(node);
        if(node.getString() != null)
        {
            node.getString().apply(this);
			nodeMap.put(node,"<char>");
        }
        outAStringAtom(node);
    }
		
//***************************************************************************************
//Function and ID	
    @Override
    public void caseAIdFuncId(AIdFuncId node)
    {
        inAIdFuncId(node);
        if(node.getIdentifier() != null)
        {
            node.getIdentifier().apply(this);
        }
        {
            List<PTuple> copy = new ArrayList<PTuple>(node.getTuple());
            for(PTuple e : copy)
            {
                e.apply(this);
            }
        }
        outAIdFuncId(node);
    }

    @Override
    public void caseALtlFuncId(ALtlFuncId node)
    {
        inALtlFuncId(node);
        if(node.getLtl() != null)
        {
            node.getLtl().apply(this);
        }
        {
            List<PTuple> copy = new ArrayList<PTuple>(node.getTuple());
            for(PTuple e : copy)
            {
                e.apply(this);
            }
        }
        outALtlFuncId(node);
    }

    @Override
    public void caseACtlFuncId(ACtlFuncId node)
    {
        inACtlFuncId(node);
        if(node.getCtl() != null)
        {
            node.getCtl().apply(this);
        }
        {
            List<PTuple> copy = new ArrayList<PTuple>(node.getTuple());
            for(PTuple e : copy)
            {
                e.apply(this);
            }
        }
        outACtlFuncId(node);
    }

    @Override
    public void caseAStopFuncId(AStopFuncId node)
    {
        inAStopFuncId(node);
        if(node.getStop() != null)
        {
            node.getStop().apply(this);
        }
        {
            List<PTuple> copy = new ArrayList<PTuple>(node.getTuple());
            for(PTuple e : copy)
            {
                e.apply(this);
            }
        }
        outAStopFuncId(node);
    }

    @Override
    public void caseASkipFuncId(ASkipFuncId node)
    {
        inASkipFuncId(node);
        if(node.getSkip() != null)
        {
            node.getSkip().apply(this);
        }
        {
            List<PTuple> copy = new ArrayList<PTuple>(node.getTuple());
            for(PTuple e : copy)
            {
                e.apply(this);
            }
        }
        outASkipFuncId(node);
    }

    @Override
    public void caseAChaosFuncId(AChaosFuncId node)
    {
        inAChaosFuncId(node);
        if(node.getChaos() != null)
        {
            node.getChaos().apply(this);
        }
        {
            List<PTuple> copy = new ArrayList<PTuple>(node.getTuple());
            for(PTuple e : copy)
            {
                e.apply(this);
            }
        }
        outAChaosFuncId(node);
    }

    @Override
    public void caseADivFuncId(ADivFuncId node)
    {
        inADivFuncId(node);
        if(node.getDiv() != null)
        {
            node.getDiv().apply(this);
        }
        {
            List<PTuple> copy = new ArrayList<PTuple>(node.getTuple());
            for(PTuple e : copy)
            {
                e.apply(this);
            }
        }
        outADivFuncId(node);
    }

    @Override
    public void caseAWaitFuncId(AWaitFuncId node)
    {
        inAWaitFuncId(node);
        if(node.getWait() != null)
        {
            node.getWait().apply(this);
        }
        {
            List<PTuple> copy = new ArrayList<PTuple>(node.getTuple());
            for(PTuple e : copy)
            {
                e.apply(this);
            }
        }
        outAWaitFuncId(node);
    }

    @Override
    public void caseARunFuncId(ARunFuncId node)
    {
        inARunFuncId(node);
        if(node.getRun() != null)
        {
            node.getRun().apply(this);
        }
        {
            List<PTuple> copy = new ArrayList<PTuple>(node.getTuple());
            for(PTuple e : copy)
            {
                e.apply(this);
            }
        }
        outARunFuncId(node);
    }
	
    @Override
    public void caseAMemberFuncId(AMemberFuncId node)
    {
        inAMemberFuncId(node);
        if(node.getMember() != null)
        {
            node.getMember().apply(this);
        }
        {
            List<PTuple> copy = new ArrayList<PTuple>(node.getTuple());
            for(PTuple e : copy)
            {
                e.apply(this);
            }
        }
        outAMemberFuncId(node);
    }

    @Override
    public void caseAEmptyFuncId(AEmptyFuncId node)
    {
        inAEmptyFuncId(node);
        if(node.getEmpty() != null)
        {
            node.getEmpty().apply(this);
        }
        {
            List<PTuple> copy = new ArrayList<PTuple>(node.getTuple());
            for(PTuple e : copy)
            {
                e.apply(this);
            }
        }
        outAEmptyFuncId(node);
    }

    @Override
    public void caseANullFuncId(ANullFuncId node)
    {
        inANullFuncId(node);
        if(node.getNull() != null)
        {
            node.getNull().apply(this);
        }
        {
            List<PTuple> copy = new ArrayList<PTuple>(node.getTuple());
            for(PTuple e : copy)
            {
                e.apply(this);
            }
        }
        outANullFuncId(node);
    }
	
    @Override
    public void caseABoolConstFuncId(ABoolConstFuncId node)
    {
        inABoolConstFuncId(node);
        if(node.getBoolConst() != null)
        {
            node.getBoolConst().apply(this);
        }
        {
            List<PTuple> copy = new ArrayList<PTuple>(node.getTuple());
            for(PTuple e : copy)
            {
                e.apply(this);
            }
        }
        outABoolConstFuncId(node);
    }

    @Override
    public void caseABoolFuncFuncId(ABoolFuncFuncId node)
    {
        inABoolFuncFuncId(node);
        if(node.getBoolFunc() != null)
        {
            node.getBoolFunc().apply(this);
        }
        {
            List<PTuple> copy = new ArrayList<PTuple>(node.getTuple());
            for(PTuple e : copy)
            {
                e.apply(this);
            }
        }
        outABoolFuncFuncId(node);
    }

    @Override
    public void caseAUnion1FuncId(AUnion1FuncId node)
    {
        inAUnion1FuncId(node);
        if(node.getUnion1() != null)
        {
            node.getUnion1().apply(this);
        }
        {
            List<PTuple> copy = new ArrayList<PTuple>(node.getTuple());
            for(PTuple e : copy)
            {
                e.apply(this);
            }
        }
        outAUnion1FuncId(node);
    }

    @Override
    public void caseAInter1FuncId(AInter1FuncId node)
    {
        inAInter1FuncId(node);
        if(node.getInter1() != null)
        {
            node.getInter1().apply(this);
        }
        {
            List<PTuple> copy = new ArrayList<PTuple>(node.getTuple());
            for(PTuple e : copy)
            {
                e.apply(this);
            }
        }
        outAInter1FuncId(node);
    }
	
    @Override
    public void caseADiffFuncId(ADiffFuncId node)
    {
        inADiffFuncId(node);
        if(node.getDiff() != null)
        {
            node.getDiff().apply(this);
        }
        {
            List<PTuple> copy = new ArrayList<PTuple>(node.getTuple());
            for(PTuple e : copy)
            {
                e.apply(this);
		
				if(copy.size() == 1
					&& getTupleLength(nodeMap.get(e).toString()) == 2)
				{			
					Pattern pattern = Pattern.compile("[(]\\{(.*)\\},\\{(.*)\\}[)]");
					Matcher matcher = pattern.matcher(nodeMap.get(e).toString());
					String a = "";
					String b = "";
					while(matcher.find())
					{
						a = matcher.group(1);
						b = matcher.group(2);
					}
					if(a.equals(b))
					{
						nodeMap.put(node,"{"+b+"}");
					}	
					//System.out.println(nodeMap.get(node));
				}
				else
				{

				}
				nodeMap.remove(e);
            }
        }
        outADiffFuncId(node);
    }

    @Override
    public void caseAUnion2FuncId(AUnion2FuncId node)
    {
        inAUnion2FuncId(node);
        if(node.getUnion2() != null)
        {
            node.getUnion2().apply(this);
        }
        {
            List<PTuple> copy = new ArrayList<PTuple>(node.getTuple());
            for(PTuple e : copy)
            {
                e.apply(this);
				if(copy.size() == 1 
					&& getTupleLength(nodeMap.get(e).toString()) == 1)
				{			
					Pattern pattern = Pattern.compile("[(]\\{\\{(.*)\\}\\}[)]");
					Matcher matcher = pattern.matcher(nodeMap.get(e).toString());
					String a = "";
					while(matcher.find())
					{
						a = matcher.group(1);
					}
					nodeMap.put(node,"{"+a+"}");					
					//System.out.println(nodeMap.get(node));
				}
				else
				{
				}
				nodeMap.remove(e);
            }
        }
        outAUnion2FuncId(node);
    }

    @Override
    public void caseAInter2FuncId(AInter2FuncId node)
    {
        inAInter2FuncId(node);
        if(node.getInter2() != null)
        {
            node.getInter2().apply(this);
        }
        {
            List<PTuple> copy = new ArrayList<PTuple>(node.getTuple());
            for(PTuple e : copy)
            {
                e.apply(this);
				if(copy.size() == 1 
					&& getTupleLength(nodeMap.get(e).toString()) == 1)
				{			
					Pattern pattern = Pattern.compile("[(]\\{\\{(.*)\\}\\}[)]");
					Matcher matcher = pattern.matcher(nodeMap.get(e).toString());
					String a = "";
					while(matcher.find())
					{
						a = matcher.group(1);
					}
					nodeMap.put(node,"{"+a+"}");					
					//System.out.println(nodeMap.get(node));
				}
				else
				{
				}
				nodeMap.remove(e);
            }
        }
        outAInter2FuncId(node);
    }

    @Override
    public void caseACardFuncId(ACardFuncId node)
    {
        inACardFuncId(node);
        if(node.getCard() != null)
        {
            node.getCard().apply(this);
        }
        {
            List<PTuple> copy = new ArrayList<PTuple>(node.getTuple());
            for(PTuple e : copy)
            {
                e.apply(this);
				if(copy.size() == 1 
					&& getTupleLength(nodeMap.get(e).toString()) == 1)
				{			
					String a = nodeMap.get(e).toString();
					if(a.matches("\\(\\{(.*)\\}\\)"))
					{
						nodeMap.put(node,"Int");	
					}					
					//System.out.println(nodeMap.get(node));
				}
				else
				{
				}
				nodeMap.remove(e);
            }
        }
        outACardFuncId(node);
    }

    @Override
    public void caseAEventsFuncId(AEventsFuncId node)
    {
        inAEventsFuncId(node);
        if(node.getEvents() != null)
        {
            node.getEvents().apply(this);
        }
        {
            List<PTuple> copy = new ArrayList<PTuple>(node.getTuple());
            for(PTuple e : copy)
            {
                e.apply(this);
            }
        }
        outAEventsFuncId(node);
    }

    @Override
    public void caseASet2FuncId(ASet2FuncId node)
    {
        inASet2FuncId(node);
        if(node.getSet2() != null)
        {
            node.getSet2().apply(this);
        }
        {
            List<PTuple> copy = new ArrayList<PTuple>(node.getTuple());
            for(PTuple e : copy)
            {
                e.apply(this);
				String a = nodeMap.get(e).toString();
				if(copy.size() == 1
						&& a.startsWith("({")
						&& getTupleLength(a) == 1)
				{
					String s = "{{";
					char[] c = a.toCharArray();
					for(int i = 2;i<c.length-2;i++)
					{
						s += c[i]; 
					}
					s += "}}";
				
					nodeMap.put(node,s);
				}
				else
				{

				}
				nodeMap.remove(e);
            }
        }
        outASet2FuncId(node);
    }

    @Override
    public void caseAHeadFuncId(AHeadFuncId node)
    {
        inAHeadFuncId(node);
        if(node.getHead() != null)
        {
            node.getHead().apply(this);
        }
        {
            List<PTuple> copy = new ArrayList<PTuple>(node.getTuple());
            for(PTuple e : copy)
            {
                e.apply(this);
				String a = nodeMap.get(e).toString();
				if(copy.size() == 1
						&& a.startsWith("(<")
						&& getTupleLength(a) == 1)
				{
					String s = "";
					char[] c = a.toCharArray();
					for(int i = 2;i<c.length-2;i++)
					{
						s += c[i]; 
					}
					nodeMap.put(node,s);
				}				
				else
				{

				}
				nodeMap.remove(e);

            }
        }
        outAHeadFuncId(node);
    }

    @Override
    public void caseATailFuncId(ATailFuncId node)
    {
        inATailFuncId(node);
        if(node.getTail() != null)
        {
            node.getTail().apply(this);
        }
        {
            List<PTuple> copy = new ArrayList<PTuple>(node.getTuple());
            for(PTuple e : copy)
            {
                e.apply(this);
				String a = nodeMap.get(e).toString();
				if(copy.size() == 1
						&& a.startsWith("(<")
						&& getTupleLength(a) == 1)
				{
					String s = "<";
					char[] c = a.toCharArray();
					for(int i = 2;i<c.length-2;i++)
					{
						s += c[i]; 
					}
					s += ">";
					nodeMap.put(node,s);
				}
				else
				{

				}
				nodeMap.remove(e);
            }
        }
        outATailFuncId(node);
    }

    @Override
    public void caseAConcatFuncId(AConcatFuncId node)
    {
        inAConcatFuncId(node);
        if(node.getConcat() != null)
        {
            node.getConcat().apply(this);
        }
        {
            List<PTuple> copy = new ArrayList<PTuple>(node.getTuple());
            for(PTuple e : copy)
            {
                e.apply(this);
				String a = nodeMap.get(e).toString();
				if(copy.size() == 1
						&& a.startsWith("(<<")
						&& getTupleLength(a) == 1)
				{
					String s = "<";
					char[] c = a.toCharArray();
					for(int i = 3;i<c.length-3;i++)
					{
						s += c[i]; 
					}
					s += ">";
					nodeMap.put(node,s);
				}
				else
				{

				}
				nodeMap.remove(e);
            }
        }
        outAConcatFuncId(node);
    }

    @Override
    public void caseASet1FuncId(ASet1FuncId node)
    {
        inASet1FuncId(node);
        if(node.getSet1() != null)
        {
            node.getSet1().apply(this);
        }
        {
            List<PTuple> copy = new ArrayList<PTuple>(node.getTuple());
            for(PTuple e : copy)
            {
                e.apply(this);
				String a = nodeMap.get(e).toString();
				if(copy.size() == 1
						&& a.startsWith("(<")
						&& getTupleLength(a) == 1)
				{
					String s = "{";
					char[] c = a.toCharArray();
					for(int i = 2;i<c.length-2;i++)
					{
						s += c[i]; 
					}
					s += "}";
					nodeMap.put(node,s);
				}
				else
				{

				}
				nodeMap.remove(e);
            }
        }
        outASet1FuncId(node);
    }

    @Override
    public void caseASeq1FuncId(ASeq1FuncId node)
    {
        inASeq1FuncId(node);
        if(node.getSeq1() != null)
        {
            node.getSeq1().apply(this);
        }
        {
            List<PTuple> copy = new ArrayList<PTuple>(node.getTuple());
            for(PTuple e : copy)
            {
                e.apply(this);
				String a = nodeMap.get(e).toString();
			//	System.out.println("Tuplelänge: "+getTupleLength(a));
				if(copy.size() == 1
						&& a.startsWith("({")
						&& getTupleLength(a) == 1)
				{
					String s = "<";
					char[] c = a.toCharArray();
					for(int i = 2;i<c.length-2;i++)
					{
						s += c[i]; 
					}
					s += ">";
					nodeMap.put(node,s);
				}
				else
				{

				}
				nodeMap.remove(e);  
            }
        }
        outASeq1FuncId(node);
    }

    @Override
    public void caseALengthFuncId(ALengthFuncId node)
    {
        inALengthFuncId(node);
        if(node.getLength() != null)
        {
            node.getLength().apply(this);
        }
        {
            List<PTuple> copy = new ArrayList<PTuple>(node.getTuple());
			int count = 0;

            for(PTuple e : copy)
            {
                e.apply(this);
				String a = nodeMap.get(e).toString();
				if(copy.size() == 1
						&& getTupleLength(a) == 1
						&& a.startsWith("(<"))
				{
					nodeMap.put(node,"int");
				}
				else
				{

				}
				nodeMap.remove(e);
            }

        }
        outALengthFuncId(node);
    }

    @Override
    public void caseAProcFuncId(AProcFuncId node)
    {
        inAProcFuncId(node);
        if(node.getProc() != null)
        {
            node.getProc().apply(this);
        }
        {
            List<PTuple> copy = new ArrayList<PTuple>(node.getTuple());
            for(PTuple e : copy)
            {
                e.apply(this);
            }
        }
        outAProcFuncId(node);
    }

    @Override
    public void caseACharConstFuncId(ACharConstFuncId node)
    {
        inACharConstFuncId(node);
        if(node.getCharConst() != null)
        {
            node.getCharConst().apply(this);
        }
        {
            List<PTuple> copy = new ArrayList<PTuple>(node.getTuple());
            for(PTuple e : copy)
            {
                e.apply(this);
            }
        }
        outACharConstFuncId(node);
    }

    @Override
    public void caseAErrorFuncId(AErrorFuncId node)
    {
        inAErrorFuncId(node);
        if(node.getError() != null)
        {
            node.getError().apply(this);
        }
        {
            List<PTuple> copy = new ArrayList<PTuple>(node.getTuple());
            for(PTuple e : copy)
            {
                e.apply(this);
				String a = nodeMap.get(e).toString();
				if(copy.size() == 1
						&& getTupleLength(a) == 1
						&& a.equals("(<char>)"))
				{
					nodeMap.put(node,"<char>"); //Unsauber definiert (<char>) -> a
				}
				nodeMap.remove(e);
            }
        }
        outAErrorFuncId(node);
    }

    @Override
    public void caseAShowFuncId(AShowFuncId node)
    {
        inAShowFuncId(node);
        if(node.getShow() != null)
        {
            node.getShow().apply(this);
        }
        {
            List<PTuple> copy = new ArrayList<PTuple>(node.getTuple());
            for(PTuple e : copy)
            {
                e.apply(this);
				String a = nodeMap.get(e).toString();
				if(copy.size() == 1 && getTupleLength(a) == 1)
				{
					nodeMap.put(node,"<char>");
				}
				nodeMap.remove(e);
            }
        }
        outAShowFuncId(node);
    }

    @Override
    public void caseAIntConstFuncId(AIntConstFuncId node)
    {
        inAIntConstFuncId(node);
        if(node.getIntConst() != null)
        {
            node.getIntConst().apply(this);
        }
        {
            List<PTuple> copy = new ArrayList<PTuple>(node.getTuple());
            for(PTuple e : copy)
            {
                e.apply(this);
            }
        }
        outAIntConstFuncId(node);
    }
	
    @Override
    public void caseATrueConstFuncId(ATrueConstFuncId node)
    {
        inATrueConstFuncId(node);
        if(node.getTrueConst() != null)
        {
            node.getTrueConst().apply(this);
        }
        {
            List<PTuple> copy = new ArrayList<PTuple>(node.getTuple());
            for(PTuple e : copy)
            {
                e.apply(this);
            }
        }
        outATrueConstFuncId(node);
    }

    @Override
    public void caseAFalseConstFuncId(AFalseConstFuncId node)
    {
        inAFalseConstFuncId(node);
        if(node.getFalseConst() != null)
        {
            node.getFalseConst().apply(this);
        }
        {
            List<PTuple> copy = new ArrayList<PTuple>(node.getTuple());
            for(PTuple e : copy)
            {
                e.apply(this);
            }
        }
        outAFalseConstFuncId(node);
    }
	
    @Override
    public void caseASeq2FuncId(ASeq2FuncId node)
    {
        inASeq2FuncId(node);
        if(node.getSeq2() != null)
        {
            node.getSeq2().apply(this);
        }
        {
            List<PTuple> copy = new ArrayList<PTuple>(node.getTuple());
            for(PTuple e : copy)
            {
                e.apply(this);
				String a = nodeMap.get(e).toString();
				if(copy.size() == 1
						&& a.startsWith("({"))
				{
					String s = "{<";
					char[] c = a.toCharArray();
					for(int i = 2;i<c.length-2;i++)
					{
						s += c[i]; 
					}
					s += ">}";
				//	System.out.println(s);
					nodeMap.put(node,s);
				}
				else
				{

				}
				nodeMap.remove(e);
            }
        }
        outASeq2FuncId(node);
    }
		
    @Override
    public void caseAIdentifierFuncId(AIdentifierFuncId node)
    {
        inAIdentifierFuncId(node);
        if(node.getId() != null)
        {
            node.getId().apply(this);
			nodeMap.put(node,nodeMap.get(node.getId()));
        }
		nodeMap.remove(node.getId());
        outAIdentifierFuncId(node);
    }

//Identifiers/Constants
    @Override
    public void caseAIdId(AIdId node)
    {
        inAIdId(node);
        if(node.getIdentifier() != null)
        {
            node.getIdentifier().apply(this);
			nodeMap.put(node,"id");
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
			nodeMap.put(node,"id");
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
			nodeMap.put(node,"id");
        }
        outACtlId(node);
    }

    @Override
    public void caseAStopId(AStopId node)
    {
        inAStopId(node);
        if(node.getStop() != null)
        {
            node.getStop().apply(this);
			nodeMap.put(node,"proc");
        }
        outAStopId(node);
    }
	
    @Override
    public void caseASkipId(ASkipId node)
    {
        inASkipId(node);
        if(node.getSkip() != null)
        {
            node.getSkip().apply(this);
			nodeMap.put(node,"proc");
        }
        outASkipId(node);
    }

    @Override
    public void caseAChaosId(AChaosId node)
    {
        inAChaosId(node);
        if(node.getChaos() != null)
        {
            node.getChaos().apply(this);
			nodeMap.put(node,"id");
        }
        outAChaosId(node);
    }

    @Override
    public void caseADivId(ADivId node)
    {
        inADivId(node);
        if(node.getDiv() != null)
        {
            node.getDiv().apply(this);
			nodeMap.put(node,"proc");
        }
        outADivId(node);
    }

    @Override
    public void caseAWaitId(AWaitId node)
    {
        inAWaitId(node);
        if(node.getWait() != null)
        {
            node.getWait().apply(this);
			nodeMap.put(node,"id");
        }
        outAWaitId(node);
    }

    @Override
    public void caseARunId(ARunId node)
    {
        inARunId(node);
        if(node.getRun() != null)
        {
            node.getRun().apply(this);
			nodeMap.put(node,"id");
        }
        outARunId(node);
    }

    @Override
    public void caseAMemberId(AMemberId node)
    {
        inAMemberId(node);
        if(node.getMember() != null)
        {
            node.getMember().apply(this);
			nodeMap.put(node,"id");
        }
        outAMemberId(node);
    }

    @Override
    public void caseAEmptyId(AEmptyId node)
    {
        inAEmptyId(node);
        if(node.getEmpty() != null)
        {
            node.getEmpty().apply(this);
			nodeMap.put(node,"id");
        }
        outAEmptyId(node);
    }

    @Override
    public void caseANullId(ANullId node)
    {
        inANullId(node);
        if(node.getNull() != null)
        {
            node.getNull().apply(this);
			nodeMap.put(node,"id");
        }
        outANullId(node);
    }

    @Override
    public void caseABoolConstId(ABoolConstId node)
    {
        inABoolConstId(node);
        if(node.getBoolConst() != null)
        {
            node.getBoolConst().apply(this);
			nodeMap.put(node,"{bool}");
        }
        outABoolConstId(node);
    }

    @Override
    public void caseABoolFuncId(ABoolFuncId node)
    {
        inABoolFuncId(node);
        if(node.getBoolFunc() != null)
        {
            node.getBoolFunc().apply(this);
			nodeMap.put(node,"id");
        }
        outABoolFuncId(node);
    }
	
    @Override
    public void caseAUnion1Id(AUnion1Id node)
    {
        inAUnion1Id(node);
        if(node.getUnion1() != null)
        {
            node.getUnion1().apply(this);
			nodeMap.put(node,"id");
        }
        outAUnion1Id(node);
    }

    @Override
    public void caseAInter1Id(AInter1Id node)
    {
        inAInter1Id(node);
        if(node.getInter1() != null)
        {
            node.getInter1().apply(this);
			nodeMap.put(node,"id");
        }
        outAInter1Id(node);
    }

    @Override
    public void caseADiffId(ADiffId node)
    {
        inADiffId(node);
        if(node.getDiff() != null)
        {
            node.getDiff().apply(this);
			nodeMap.put(node,"id");
        }
        outADiffId(node);
    }

    @Override
    public void caseAUnion2Id(AUnion2Id node)
    {
        inAUnion2Id(node);
        if(node.getUnion2() != null)
        {
            node.getUnion2().apply(this);
			nodeMap.put(node,"id");
        }
        outAUnion2Id(node);
    }

    @Override
    public void caseAInter2Id(AInter2Id node)
    {
        inAInter2Id(node);
        if(node.getInter2() != null)
        {
            node.getInter2().apply(this);
			nodeMap.put(node,"id");
        }
        outAInter2Id(node);
    }

    @Override
    public void caseACardId(ACardId node)
    {
        inACardId(node);
        if(node.getCard() != null)
        {
            node.getCard().apply(this);
			nodeMap.put(node,"id");
        }
        outACardId(node);
    }

    @Override
    public void caseAEventsId(AEventsId node)
    {
        inAEventsId(node);
        if(node.getEvents() != null)
        {
            node.getEvents().apply(this);
			nodeMap.put(node,"{event}");
        }
        outAEventsId(node);
    }

    @Override
    public void caseASet2Id(ASet2Id node)
    {
        inASet2Id(node);
        if(node.getSet2() != null)
        {
            node.getSet2().apply(this);
			nodeMap.put(node,"id");
        }
        outASet2Id(node);
    }

    @Override
    public void caseAHeadId(AHeadId node)
    {
        inAHeadId(node);
        if(node.getHead() != null)
        {
            node.getHead().apply(this);
			nodeMap.put(node,"id");
        }
        outAHeadId(node);
    }

    @Override
    public void caseATailId(ATailId node)
    {
        inATailId(node);
        if(node.getTail() != null)
        {
            node.getTail().apply(this);
			nodeMap.put(node,"id");
        }
        outATailId(node);
    }

    @Override
    public void caseAConcatId(AConcatId node)
    {
        inAConcatId(node);
        if(node.getConcat() != null)
        {
            node.getConcat().apply(this);
			nodeMap.put(node,"id");
        }
        outAConcatId(node);
    }

    @Override
    public void caseASet1Id(ASet1Id node)
    {
        inASet1Id(node);
        if(node.getSet1() != null)
        {
            node.getSet1().apply(this);
			nodeMap.put(node,"id");
        }
        outASet1Id(node);
    }

    @Override
    public void caseASeq1Id(ASeq1Id node)
    {
        inASeq1Id(node);
        if(node.getSeq1() != null)
        {
            node.getSeq1().apply(this);
			nodeMap.put(node,"id");
        }
        outASeq1Id(node);
    }

    @Override
    public void caseALengthId(ALengthId node)
    {
        inALengthId(node);
        if(node.getLength() != null)
        {
            node.getLength().apply(this);
			nodeMap.put(node,"id");
        }
        outALengthId(node);
    }

    @Override
    public void caseAProcId(AProcId node)
    {
        inAProcId(node);
        if(node.getProc() != null)
        {
            node.getProc().apply(this);
			nodeMap.put(node,"proc");
        }
        outAProcId(node);
    }

    @Override
    public void caseACharConstId(ACharConstId node)
    {
        inACharConstId(node);
        if(node.getCharConst() != null)
        {
            node.getCharConst().apply(this);
			nodeMap.put(node,"{char}");
        }
        outACharConstId(node);
    }

    @Override
    public void caseAErrorId(AErrorId node)
    {
        inAErrorId(node);
        if(node.getError() != null)
        {
            node.getError().apply(this);
			nodeMap.put(node,"id");
        }
        outAErrorId(node);
    }

    @Override
    public void caseAShowId(AShowId node)
    {
        inAShowId(node);
        if(node.getShow() != null)
        {
            node.getShow().apply(this);
			nodeMap.put(node,"id");
        }
        outAShowId(node);
    }

    @Override
    public void caseAIntConstId(AIntConstId node)
    {
        inAIntConstId(node);
        if(node.getIntConst() != null)
        {
            node.getIntConst().apply(this);
			nodeMap.put(node,"{int}");
        }
        outAIntConstId(node);
    }
	
	@Override
    public void caseATrueConstId(ATrueConstId node)
    {
        inATrueConstId(node);
        if(node.getTrueConst() != null)
        {
            node.getTrueConst().apply(this);
			nodeMap.put(node,"bool");
			
        }
        outATrueConstId(node);
    }

    @Override
    public void caseAFalseConstId(AFalseConstId node)
    {
        inAFalseConstId(node);
        if(node.getFalseConst() != null)
        {
            node.getFalseConst().apply(this);
			nodeMap.put(node,"bool");
        }
        outAFalseConstId(node);
    }
	
   @Override
    public void caseASeq2Id(ASeq2Id node)
    {
        inASeq2Id(node);
        if(node.getSeq2() != null)
        {
            node.getSeq2().apply(this);
			nodeMap.put(node,"id");
        }
        outASeq2Id(node);
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
				throw new RuntimeException("Incorrect types in Line "
							+node.getDotdot().getLine()+", Column "
							+node.getDotdot().getPos());
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
				throw new RuntimeException("Incorrect types in Line "
							+node.getBraceR().getLine()+", Column "
							+node.getBraceR().getPos());
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
				throw new RuntimeException("Incorrect types in Line "
							+node.getDotdot().getLine()+", Column "
							+node.getDotdot().getPos());
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
				throw new RuntimeException("Incorrect types in Line "
							+node.getDotdot().getLine()+", Column "
							+node.getDotdot().getPos());
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
				throw new RuntimeException("Incorrect types in Line "
							+node.getPipe().getLine()+", Column "
							+node.getPipe().getPos());
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
				throw new RuntimeException("Incorrect types in Line "
							+node.getDotdot().getLine()+", Column "
							+node.getDotdot().getPos());
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
        if(node.getSeqOpen() != null)
        {
            node.getSeqOpen().apply(this);
        }
        if(node.getSeqClose() != null)
        {
            node.getSeqClose().apply(this);
        }
		nodeMap.put(node,"<>");
        outAEmptySeqSequence(node);
    }

    @Override
    public void caseAExplSeqSequence(AExplSeqSequence node)
    {
        inAExplSeqSequence(node);
        if(node.getSeqOpen() != null)
        {
            node.getSeqOpen().apply(this);
        }
        if(node.getInnerSequence() != null)
        {
            node.getInnerSequence().apply(this);
			nodeMap.put(node,"<"+innerseq.get(seqDepth+1).get(0)+">");
		//	System.out.println(nodeMap.get(node));
			if(seqDepth<0)
			{
				innerseq.clear();
		    }
        }
        if(node.getSeqClose() != null)
        {
            node.getSeqClose().apply(this);
        }
        outAExplSeqSequence(node);
    }

    @Override
    public void caseAListCompSequence(AListCompSequence node)
    {
        inAListCompSequence(node);
        if(node.getSeqOpen() != null)
        {
            node.getSeqOpen().apply(this);
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
        if(node.getSeqClose() != null)
        {
            node.getSeqClose().apply(this);
        }
        outAListCompSequence(node);
    }

    @Override
    public void caseACrSeqSequence(ACrSeqSequence node)
    {
        inACrSeqSequence(node);
        if(node.getSeqOpen() != null)
        {
            node.getSeqOpen().apply(this);
        }
        if(node.getL() != null)
        {
            node.getL().apply(this);
			if(!(nodeMap.get(node.getL()).toString().equals("int")))
			{
				throw new RuntimeException("Incorrect types in Line "
							+node.getDotdot().getLine()+", Column "
							+node.getDotdot().getPos());
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
				throw new RuntimeException("Incorrect types in Line "
							+node.getSeqClose().getLine()+", Column "
							+node.getSeqClose().getPos());
			}
        }
        if(node.getSeqClose() != null)
        {
            node.getSeqClose().apply(this);
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
        if(node.getSeqOpen() != null)
        {
            node.getSeqOpen().apply(this);
        }
        if(node.getL() != null)
        {
            node.getL().apply(this);
			if(!(nodeMap.get(node.getL()).toString().equals("int")))
			{
				throw new RuntimeException("Incorrect types in Line "
							+node.getDotdot().getLine()+", Column "
							+node.getDotdot().getPos());
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
				throw new RuntimeException("Incorrect types in Line "
							+node.getPipe().getLine()+", Column "
							+node.getPipe().getPos());
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
        if(node.getSeqClose() != null)
        {
            node.getSeqClose().apply(this);
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
        if(node.getSeqOpen() != null)
        {
            node.getSeqOpen().apply(this);
        }
        if(node.getValExp() != null)
        {
            node.getValExp().apply(this);
			if(!(nodeMap.get(node.getValExp()).toString().equals("int")))
			{
				throw new RuntimeException("Incorrect types in Line "
							+node.getDotdot().getLine()+", Column "
							+node.getDotdot().getPos());
			}
        }
        if(node.getDotdot() != null)
        {
            node.getDotdot().apply(this);
        }
        if(node.getSeqClose() != null)
        {
            node.getSeqClose().apply(this);
        }
		nodeMap.put(node,"<int>");
		nodeMap.remove(node.getValExp());
        outAOrSeqSequence(node);
    }

    @Override
    public void caseAInfComprSequence(AInfComprSequence node)
    {
        inAInfComprSequence(node);
        if(node.getSeqOpen() != null)
        {
            node.getSeqOpen().apply(this);
        }
        if(node.getValExp() != null)
        {
            node.getValExp().apply(this);
			if(!(nodeMap.get(node.getValExp()).toString().equals("int")))
			{
				throw new RuntimeException("Incorrect types in Line "
							+node.getDotdot().getLine()+", Column "
							+node.getDotdot().getPos());
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
        if(node.getSeqClose() != null)
        {
            node.getSeqClose().apply(this);
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
        if(node.getProc1() != null)
        {
            node.getProc1().apply(this);
			String a = nodeMap.get(node.getProc1()).toString();
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
		nodeMap.remove(node.getProc1());
        outAStartInnerSequenceRek(node);
    }

    @Override
    public void caseAEndInnerSequenceRek(AEndInnerSequenceRek node)
    {
        inAEndInnerSequenceRek(node);
        if(node.getProc1() != null)
        {
            node.getProc1().apply(this);
			String a = nodeMap.get(node.getProc1()).toString();
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
		nodeMap.remove(node.getProc1());
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
            node.getInnerTuple().apply(this);
        }
        if(node.getParR() != null)
        {
            node.getParR().apply(this);
        }
		nodeMap.put(node,"("+nodeMap.get(node.getInnerTuple())+")");
		nodeMap.remove(node.getInnerTuple());
        outATupleTuple(node);
    }

    @Override
    public void caseAInnerStartInnerTuple(AInnerStartInnerTuple node)
    {
        inAInnerStartInnerTuple(node);
        if(node.getProc1() != null)
        {
            node.getProc1().apply(this);
        }
        if(node.getComma() != null)
        {
            node.getComma().apply(this);
        }
        if(node.getInnerTuple() != null)
        {
            node.getInnerTuple().apply(this);
        }
		nodeMap.put(node, nodeMap.get(node.getProc1()).toString()
					 +","+nodeMap.get(node.getInnerTuple()).toString());
		nodeMap.remove(node.getProc1());
		nodeMap.remove(node.getInnerTuple());
        outAInnerStartInnerTuple(node);
    }
	
	@Override
    public void caseAInnerEndInnerTuple(AInnerEndInnerTuple node)
    {
        inAInnerEndInnerTuple(node);
        if(node.getProc1() != null)
        {
            node.getProc1().apply(this);
			nodeMap.put(node, nodeMap.get(node.getProc1()));
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

//***************************************************************************************
//((1,2,(3),4),2,{True},(1,2,(3),4)) has length 4

	public int getTupleLength(String a)
	{
		char[] c = a.toCharArray();
		String s = " ";
		if(c[0] == '(' && c[c.length-1] == ')')	
		{
			for(int i = 1;i<c.length-1;i++)
			{
				s+= c[i];
			}
			
			c = s.toCharArray();
			s = " ";
			int tupleDepth = 0;
			for(int k = 0;k<c.length;k++)
			{
				if(c[k] == '(')
				{
					tupleDepth++;
				}
				else if(c[k] == ')')
				{
					tupleDepth = tupleDepth-1;
				}
				
				if(tupleDepth == 0)
				{
					s += c[k];
				}
			}
				
			c = s.toCharArray();
			int count = 0;
			for(int j = 0;j<c.length;j++)
			{
				if(c[j] == ',')
				{
					count++;
				}
			}
			return count+1;
		}
		else
		{
			return 0;
		}

	}
}