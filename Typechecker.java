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
//	private Map<String,ArrayList<String>> channels = new HashMap<String,ArrayList<String>>();
//	private Map<String,ArrayList<String>> datanodeMap = new HashMap<String,ArrayList<String>>();
	private ArrayList<String> value = new ArrayList<String>();
	private HashMap nodeMap = new HashMap();
	private HashMap types = new HashMap();
	private int symbolIndex = 0;
	private String currentDatatype;

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



	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	@Override
    public void caseADoublePatternProc1(ADoublePatternProc1 node)
    {
        inADoublePatternProc1(node);
        if(node.getProc1() != null)
        {
            node.getProc1().apply(this);
        }
        if(node.getDoubleat() != null)
        {
            node.getDoubleat().apply(this);
        }
        if(node.getProc2() != null)
        {
            node.getProc2().apply(this);
        }
        outADoublePatternProc1(node);
    }



    @Override
    public void caseAP2Proc1(AP2Proc1 node)
    {
        inAP2Proc1(node);
        if(node.getProc2() != null)
        {
            node.getProc2().apply(this);
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
        }
        if(node.getBSlash() != null)
        {
            node.getBSlash().apply(this);
        }
        if(node.getProc3() != null)
        {
            node.getProc3().apply(this);
        }
        outAHideProc2(node);
    }


    @Override
    public void caseAP3Proc2(AP3Proc2 node)
    {
        inAP3Proc2(node);
        if(node.getProc3() != null)
        {
            node.getProc3().apply(this);
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
        }
        if(node.getILeaving() != null)
        {
            node.getILeaving().apply(this);
        }
        if(node.getProc4() != null)
        {
            node.getProc4().apply(this);
        }
        outAIleaveProc3(node);
    }


    @Override
    public void caseAP4Proc3(AP4Proc3 node)
    {
        inAP4Proc3(node);
        if(node.getProc4() != null)
        {
            node.getProc4().apply(this);
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
        }
        outAExceptProc4(node);
    }


    @Override
    public void caseAGenParProc4(AGenParProc4 node)
    {
        inAGenParProc4(node);
        if(node.getProc4() != null)
        {
            node.getProc4().apply(this);
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
        }
        outAGenParProc4(node);
    }


    @Override
    public void caseAAlphParProc4(AAlphParProc4 node)
    {
        inAAlphParProc4(node);
        if(node.getProc4() != null)
        {
            node.getProc4().apply(this);
        }
        if(node.getBracketL() != null)
        {
            node.getBracketL().apply(this);
        }
        if(node.getL() != null)
        {
            node.getL().apply(this);
        }
        if(node.getDpipe() != null)
        {
            node.getDpipe().apply(this);
        }
        if(node.getR() != null)
        {
            node.getR().apply(this);
        }
        if(node.getBracketR() != null)
        {
            node.getBracketR().apply(this);
        }
        if(node.getProc5() != null)
        {
            node.getProc5().apply(this);
        }
        outAAlphParProc4(node);
    }


    @Override
    public void caseALinkedParProc4(ALinkedParProc4 node)
    {
        inALinkedParProc4(node);
        if(node.getProc4() != null)
        {
            node.getProc4().apply(this);
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
        }
        outALinkedParProc4(node);
    }


    @Override
    public void caseAP5Proc4(AP5Proc4 node)
    {
        inAP5Proc4(node);
        if(node.getProc5() != null)
        {
            node.getProc5().apply(this);
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
        }
        if(node.getIChoice() != null)
        {
            node.getIChoice().apply(this);
        }
        if(node.getProc6() != null)
        {
            node.getProc6().apply(this);
        }
        outAIntChoiceProc5(node);
    }


    @Override
    public void caseAP6Proc5(AP6Proc5 node)
    {
        inAP6Proc5(node);
        if(node.getProc6() != null)
        {
            node.getProc6().apply(this);
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
        }
        if(node.getEChoice() != null)
        {
            node.getEChoice().apply(this);
        }
        if(node.getProc7() != null)
        {
            node.getProc7().apply(this);
        }
        outAExtChoiceProc6(node);
    }


    @Override
    public void caseASyncExtProc6(ASyncExtProc6 node)
    {
        inASyncExtProc6(node);
        if(node.getProc6() != null)
        {
            node.getProc6().apply(this);
        }
        if(node.getSyncParL() != null)
        {
            node.getSyncParL().apply(this);
        }
        if(node.getEvent() != null)
        {
            node.getEvent().apply(this);
        }
        if(node.getSyncParR() != null)
        {
            node.getSyncParR().apply(this);
        }
        if(node.getProc7() != null)
        {
            node.getProc7().apply(this);
        }
        outASyncExtProc6(node);
    }


    @Override
    public void caseAP7Proc6(AP7Proc6 node)
    {
        inAP7Proc6(node);
        if(node.getProc7() != null)
        {
            node.getProc7().apply(this);
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
        }
        if(node.getInterrupt() != null)
        {
            node.getInterrupt().apply(this);
        }
        if(node.getProc8() != null)
        {
            node.getProc8().apply(this);
        }
        outAInterruptProc7(node);
    }


    @Override
    public void caseASyncInterruptProc7(ASyncInterruptProc7 node)
    {
        inASyncInterruptProc7(node);
        if(node.getProc7() != null)
        {
            node.getProc7().apply(this);
        }
        if(node.getSyncIntL() != null)
        {
            node.getSyncIntL().apply(this);
        }
        if(node.getEvent() != null)
        {
            node.getEvent().apply(this);
        }
        if(node.getSyncIntR() != null)
        {
            node.getSyncIntR().apply(this);
        }
        if(node.getProc8() != null)
        {
            node.getProc8().apply(this);
        }
        outASyncInterruptProc7(node);
    }


    @Override
    public void caseAP8Proc7(AP8Proc7 node)
    {
        inAP8Proc7(node);
        if(node.getProc8() != null)
        {
            node.getProc8().apply(this);
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
        }
        if(node.getTimeout() != null)
        {
            node.getTimeout().apply(this);
        }
        if(node.getProc9() != null)
        {
            node.getProc9().apply(this);
        }
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
        outAP9Proc8(node);
    }


    @Override
    public void caseASeqCompProc9(ASeqCompProc9 node)
    {
        inASeqCompProc9(node);
        if(node.getProc9() != null)
        {
            node.getProc9().apply(this);
        }
        if(node.getSemicolon() != null)
        {
            node.getSemicolon().apply(this);
        }
        if(node.getProc10() != null)
        {
            node.getProc10().apply(this);
        }
        outASeqCompProc9(node);
    }


    @Override
    public void caseAP10Proc9(AP10Proc9 node)
    {
        inAP10Proc9(node);
        if(node.getProc10() != null)
        {
            node.getProc10().apply(this);
        }
        outAP10Proc9(node);
    }


    @Override
    public void caseAGuardExpProc10(AGuardExpProc10 node)
    {
        inAGuardExpProc10(node);
        if(node.getEvent() != null)
        {
            node.getEvent().apply(this);
        }
        if(node.getGuard() != null)
        {
            node.getGuard().apply(this);
        }
        if(node.getProc10() != null)
        {
            node.getProc10().apply(this);
        }
        outAGuardExpProc10(node);
    }


    @Override
    public void caseAPrefixProc10(APrefixProc10 node)
    {
        inAPrefixProc10(node);
        if(node.getEvent() != null)
        {
            node.getEvent().apply(this);
        }
        if(node.getPrefix() != null)
        {
            node.getPrefix().apply(this);
        }
        if(node.getProc10() != null)
        {
            node.getProc10().apply(this);
        }
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
        if(node.getArguments() != null)
        {
            node.getArguments().apply(this);
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
        }
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
        }
        if(node.getA() != null)
        {
            node.getA().apply(this);
        }
        if(node.getB() != null)
        {
            node.getB().apply(this);
        }
        if(node.getC() != null)
        {
            node.getC().apply(this);
        }
        if(node.getProc1() != null)
        {
            node.getProc1().apply(this);
        }
        if(node.getD() != null)
        {
            node.getD().apply(this);
        }
        if(node.getElse() != null)
        {
            node.getElse().apply(this);
        }
        if(node.getE() != null)
        {
            node.getE().apply(this);
        }
        if(node.getProc10() != null)
        {
            node.getProc10().apply(this);
        }
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
        }
        outARepProc10(node);
    }


    @Override
    public void caseAP11Proc10(AP11Proc10 node)
    {
        inAP11Proc10(node);
        if(node.getProc11() != null)
        {
            node.getProc11().apply(this);
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
        outARenamingProc11(node);
    }


    @Override
    public void caseAERenamingProc11(AERenamingProc11 node)
    {
        inAERenamingProc11(node);
        if(node.getProc11() != null)
        {
            node.getProc11().apply(this);
        }
        if(node.getDbracketL() != null)
        {
            node.getDbracketL().apply(this);
        }
        if(node.getDbracketR() != null)
        {
            node.getDbracketR().apply(this);
        }
        outAERenamingProc11(node);
    }


    @Override
    public void caseAEventProc11(AEventProc11 node)
    {
        inAEventProc11(node);
        if(node.getEvent() != null)
        {
            node.getEvent().apply(this);
        }
        outAEventProc11(node);
    }


    @Override
    public void caseADollarEvent(ADollarEvent node)
    {
        inADollarEvent(node);
        if(node.getEvent() != null)
        {
            node.getEvent().apply(this);
        }
        if(node.getDollar() != null)
        {
            node.getDollar().apply(this);
        }
        if(node.getEvent2() != null)
        {
            node.getEvent2().apply(this);
        }
        outADollarEvent(node);
    }


    @Override
    public void caseAEvent2Event(AEvent2Event node)
    {
        inAEvent2Event(node);
        if(node.getEvent2() != null)
        {
            node.getEvent2().apply(this);
        }
        outAEvent2Event(node);
    }


    @Override
    public void caseAQmEvent2(AQmEvent2 node)
    {
        inAQmEvent2(node);
        if(node.getEvent2() != null)
        {
            node.getEvent2().apply(this);
        }
        if(node.getQMark() != null)
        {
            node.getQMark().apply(this);
        }
        if(node.getDotOp() != null)
        {
            node.getDotOp().apply(this);
        }
        outAQmEvent2(node);
    }


    @Override
    public void caseAExEvent2(AExEvent2 node)
    {
        inAExEvent2(node);
        if(node.getEvent2() != null)
        {
            node.getEvent2().apply(this);
        }
        if(node.getExclMark() != null)
        {
            node.getExclMark().apply(this);
        }
        if(node.getDotOp() != null)
        {
            node.getDotOp().apply(this);
        }
        outAExEvent2(node);
    }


    @Override
    public void caseADotOpEvent2(ADotOpEvent2 node)
    {
        inADotOpEvent2(node);
        if(node.getDotOp() != null)
        {
            node.getDotOp().apply(this);
        }
        outADotOpEvent2(node);
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
        if(node.getColon() != null)
        {
            node.getColon().apply(this);
        }
        outADotDotOp(node);
    }


    @Override
    public void caseASsDotOp(ASsDotOp node)
    {
        inASsDotOp(node);
        if(node.getColon() != null)
        {
            node.getColon().apply(this);
        }
        outASsDotOp(node);
    }


    @Override
    public void caseAColonColon(AColonColon node)
    {
        inAColonColon(node);
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
        outAColonColon(node);
    }


    @Override
    public void caseABoolExpColon(ABoolExpColon node)
    {
        inABoolExpColon(node);
        if(node.getBoolExp() != null)
        {
            node.getBoolExp().apply(this);
        }
        outABoolExpColon(node);
    }


    @Override
    public void caseAOrBoolExp(AOrBoolExp node)
    {
        inAOrBoolExp(node);
        if(node.getBoolExp() != null)
        {
            node.getBoolExp().apply(this);
        }
        if(node.getOr() != null)
        {
            node.getOr().apply(this);
        }
        if(node.getBoolExp2() != null)
        {
            node.getBoolExp2().apply(this);
        }
        outAOrBoolExp(node);
    }


    @Override
    public void caseABoolExp2BoolExp(ABoolExp2BoolExp node)
    {
        inABoolExp2BoolExp(node);
        if(node.getBoolExp2() != null)
        {
            node.getBoolExp2().apply(this);
        }
        outABoolExp2BoolExp(node);
    }


    @Override
    public void caseAAndBoolExp2(AAndBoolExp2 node)
    {
        inAAndBoolExp2(node);
        if(node.getBoolExp2() != null)
        {
            node.getBoolExp2().apply(this);
        }
        if(node.getAnd() != null)
        {
            node.getAnd().apply(this);
        }
        if(node.getBoolExp3() != null)
        {
            node.getBoolExp3().apply(this);
        }
        outAAndBoolExp2(node);
    }


    @Override
    public void caseABoolExp3BoolExp2(ABoolExp3BoolExp2 node)
    {
        inABoolExp3BoolExp2(node);
        if(node.getBoolExp3() != null)
        {
            node.getBoolExp3().apply(this);
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
        }
        outANotBoolExp3(node);
    }


    @Override
    public void caseABoolExp4BoolExp3(ABoolExp4BoolExp3 node)
    {
        inABoolExp4BoolExp3(node);
        if(node.getBoolExp4() != null)
        {
            node.getBoolExp4().apply(this);
        }
        outABoolExp4BoolExp3(node);
    }


	
//***************************************************************************************
//***************************************************************************************
//***************************************************************************************	
	
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
				"(|a=>b|), <a>, {a}, int, char");
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
				"(|a=>b|), <a>, {a}, int, char");
			}
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
				"(|a=>b|), <a>, {a}, int, char");
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
				"(|a=>b|), <a>, {a}, int, char ");
			}
        }
		if(!(nodeMap.get(node.getValExp()).toString()
			.equals(nodeMap.get(node.getBoolExp4()).toString())))
		{
			throw new RuntimeException("Different types!");
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
				"(|a=>b|), <a>, {a}, int, char ");
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
				"(|a=>b|), <a>, {a}, int, char ");
			}
        }
		if(!(nodeMap.get(node.getValExp()).toString()
			.equals(nodeMap.get(node.getBoolExp4()).toString())))
		{
			throw new RuntimeException("Different types!");
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
				throw new RuntimeException("TypeError, expecting: 'int'");
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
				throw new RuntimeException("TypeError, expecting: 'int'");
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
				throw new RuntimeException("TypeError, expecting: 'int'");
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
				throw new RuntimeException("TypeError, expecting: 'int'");
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
				throw new RuntimeException("TypeError, expecting: 'int'");
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
				throw new RuntimeException("TypeError, expecting: 'int'");
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
				throw new RuntimeException("TypeError, expecting: 'int'");
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
				throw new RuntimeException("TypeError, expecting: '<a>'");
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
        if(node.getSequence1() != null)
        {
            node.getSequence1().apply(this);
        }
        if(node.getCat() != null)
        {
            node.getCat().apply(this);
        }
        if(node.getPar() != null)
        {
            node.getPar().apply(this);
			if(nodeMap.get(node.getPar()).equals(nodeMap.get(node.getSequence1())))
			{
				nodeMap.put(node, nodeMap.get(node.getPar()));
				nodeMap.remove(node.getSequence1());
				nodeMap.remove(node.getPar());
			}
			else
			{
				throw new RuntimeException("TypeError, expecting: <a>^<a>");
			}
        }
        outACatSequence1(node);
    }


    @Override
    public void caseAParSequence1(AParSequence1 node)
    {
        inAParSequence1(node);
        if(node.getPar() != null)
        {
            node.getPar().apply(this);
			nodeMap.put(node,nodeMap.get(node.getPar()));
			nodeMap.remove(node.getPar());
        }
        outAParSequence1(node);
    }

    @Override
    public void caseAParPar(AParPar node)
    {
        inAParPar(node);
        if(node.getParL() != null)
        {
            node.getParL().apply(this);
        }
        if(node.getProc1() != null)
        {
            node.getProc1().apply(this);
			nodeMap.put(node,nodeMap.get(node.getProc1()));
			nodeMap.remove(node.getProc1());
        }
        if(node.getParR() != null)
        {
            node.getParR().apply(this);
        }
		if(node.getFuncIdRek() != null)
        {
            node.getFuncIdRek().apply(this);
        }
        outAParPar(node);
    }

    @Override
    public void caseAAtomPar(AAtomPar node)
    {
        inAAtomPar(node);
        if(node.getAtom() != null)
        {
            node.getAtom().apply(this);
			nodeMap.put(node,nodeMap.get(node.getAtom()));
			nodeMap.remove(node.getAtom());
        }
        outAAtomPar(node);
    }

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
    public void caseAFuncId(AFuncId node)
    {
        inAFuncId(node);
		int count = 0;
        if(node.getId() != null)
        {
            node.getId().apply(this);
        }
        {
            List<PFuncIdRek> copy = new ArrayList<PFuncIdRek>(node.getFuncIdRek());
			
            for(PFuncIdRek e : copy)
            {
				count++;
                e.apply(this);
            }
        }
		if(count == 0)
		{
			nodeMap.put(node, nodeMap.get(node.getId()));
			nodeMap.remove(nodeMap.get(node.getId()));
		}
        outAFuncId(node);
    }
	
	@Override
    public void caseAFuncIdRek(AFuncIdRek node)
    {
        inAFuncIdRek(node);
        if(node.getParL() != null)
        {
            node.getParL().apply(this);
        }
        if(node.getArguments() != null)
        {
            node.getArguments().apply(this);
        }
        if(node.getParR() != null)
        {
            node.getParR().apply(this);
        }
        outAFuncIdRek(node);
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
	
}
