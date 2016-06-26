//This checks, if x is a pattern in the following expressions by illegalizing some rules:
//{1,2,3|x<-{5},true}

import java.util.*;
import java.io.*;
import CSPMparser.analysis.*;
import CSPMparser.node.*;

public class StatementPatternCheck extends DepthFirstAdapter
{
	private boolean patternRequired = false;

    @Override
    public void caseAHideProc1(AHideProc1 node)
    {
		inAHideProc1(node);
		if(patternRequired)
		{
			throw new RuntimeException("Expecting pattern (spc L. 19).");
		}
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
        outAHideProc1(node);
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
    public void caseAIleaveProc2(AIleaveProc2 node)
    {
        inAIleaveProc2(node);
		if(patternRequired)
		{
			throw new RuntimeException("Expecting pattern (spc L. 53).");
		}
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
        outAIleaveProc2(node);
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
    public void caseAExceptProc3(AExceptProc3 node)
    {
        inAExceptProc3(node);
		if(patternRequired)
		{
			throw new RuntimeException("Expecting pattern  (spc L. 87).");
		}
        if(node.getProc3() != null)
        {
            node.getProc3().apply(this);
        }
        if(node.getBracketPipeL() != null)
        {
            node.getBracketPipeL().apply(this);
        }
        if(node.getEvent() != null)
        {
            node.getEvent().apply(this);
        }
        if(node.getPipe() != null)
        {
            node.getPipe().apply(this);
        }
        if(node.getTriaR() != null)
        {
            node.getTriaR().apply(this);
        }
        if(node.getProc4() != null)
        {
            node.getProc4().apply(this);
        }
        outAExceptProc3(node);
    }

    @Override
    public void caseAGenParProc3(AGenParProc3 node)
    {
        inAGenParProc3(node);
		if(patternRequired)
		{
			throw new RuntimeException("Expecting pattern (spc L. 122).");
		}
        if(node.getProc3() != null)
        {
            node.getProc3().apply(this);
        }
        if(node.getBracketPipeL() != null)
        {
            node.getBracketPipeL().apply(this);
        }
        if(node.getEvent() != null)
        {
            node.getEvent().apply(this);
        }
        if(node.getBracketPipeR() != null)
        {
            node.getBracketPipeR().apply(this);
        }
        if(node.getProc4() != null)
        {
            node.getProc4().apply(this);
        }
        outAGenParProc3(node);
    }

    @Override
    public void caseAAlphParProc3(AAlphParProc3 node)
    {
        inAAlphParProc3(node);
		if(patternRequired)
		{
			throw new RuntimeException("Expecting pattern (spc L. 153).");
		}
        if(node.getProc3() != null)
        {
            node.getProc3().apply(this);
        }
        if(node.getBracketL() != null)
        {
            node.getBracketL().apply(this);
        }
        if(node.getEventl() != null)
        {
            node.getEventl().apply(this);
        }
        if(node.getDpipe() != null)
        {
            node.getDpipe().apply(this);
        }
        if(node.getEventr() != null)
        {
            node.getEventr().apply(this);
        }
        if(node.getBracketR() != null)
        {
            node.getBracketR().apply(this);
        }
        if(node.getProc4() != null)
        {
            node.getProc4().apply(this);
        }
        outAAlphParProc3(node);
    }

    @Override
    public void caseALinkedParProc3(ALinkedParProc3 node)
    {
        inALinkedParProc3(node);
		if(patternRequired)
		{
			throw new RuntimeException("Expecting pattern (spc L. 192).");
		}
        if(node.getProc3() != null)
        {
            node.getProc3().apply(this);
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
        if(node.getProc4() != null)
        {
            node.getProc4().apply(this);
        }
        outALinkedParProc3(node);
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
    public void caseAIntChoiceProc4(AIntChoiceProc4 node)
    {
        inAIntChoiceProc4(node);
		if(patternRequired)
		{
			throw new RuntimeException("Expecting pattern (spc L. 234).");
		}
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
        outAIntChoiceProc4(node);
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
    public void caseAExtChoiceProc5(AExtChoiceProc5 node)
    {
        inAExtChoiceProc5(node);
		if(patternRequired)
		{
			throw new RuntimeException("Expecting pattern (spc L. 268).");
		}
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
        outAExtChoiceProc5(node);
    }

    @Override
    public void caseASyncExtProc5(ASyncExtProc5 node)
    {
        inASyncExtProc5(node);
		if(patternRequired)
		{
			throw new RuntimeException("Expecting pattern (spc L. 291).");
		}
        if(node.getProc5() != null)
        {
            node.getProc5().apply(this);
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
        if(node.getProc6() != null)
        {
            node.getProc6().apply(this);
        }
        outASyncExtProc5(node);
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
    public void caseAInterruptProc6(AInterruptProc6 node)
    {
        inAInterruptProc6(node);
		if(patternRequired)
		{
			throw new RuntimeException("Expecting pattern (spc L. 333).");
		}
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
        outAInterruptProc6(node);
    }

    @Override
    public void caseASyncInterruptProc6(ASyncInterruptProc6 node)
    {
        inASyncInterruptProc6(node);
		if(patternRequired)
		{
			throw new RuntimeException("Expecting pattern (spc L. 356).");
		}
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
        if(node.getSyncIntR() != null)
        {
            node.getSyncIntR().apply(this);
        }
        if(node.getProc7() != null)
        {
            node.getProc7().apply(this);
        }
        outASyncInterruptProc6(node);
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
    public void caseASlidingChoiceProc7(ASlidingChoiceProc7 node)
    {
        inASlidingChoiceProc7(node);
		if(patternRequired)
		{
			throw new RuntimeException("Expecting pattern (spc L. 398).");
		}
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
        outASlidingChoiceProc7(node);
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
    public void caseASeqCompProc8(ASeqCompProc8 node)
    {
        inASeqCompProc8(node);
		if(patternRequired)
		{
			throw new RuntimeException("Expecting pattern (spc L. 432).");
		}
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
        outASeqCompProc8(node);
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
    public void caseAGuardExpProc9(AGuardExpProc9 node)
    {
        inAGuardExpProc9(node);
		if(patternRequired)
		{
			throw new RuntimeException("Expecting pattern (spc L. 466).");
		}
        if(node.getDotOp() != null)
        {
            node.getDotOp().apply(this);
        }
        if(node.getGuard() != null)
        {
            node.getGuard().apply(this);
        }
        if(node.getProc9() != null)
        {
            node.getProc9().apply(this);
        }
        outAGuardExpProc9(node);
    }

    @Override
    public void caseAPrefixProc9(APrefixProc9 node)
    {
        inAPrefixProc9(node);
		if(patternRequired)
		{
			throw new RuntimeException("Expecting pattern (spc L. 489).");
		}
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
        outAPrefixProc9(node);
    }

    @Override
    public void caseALambdaTermProc9(ALambdaTermProc9 node)
    {
        inALambdaTermProc9(node);
		if(patternRequired)
		{
			throw new RuntimeException("Expecting pattern (spc L. 512).");
		}
        if(node.getBSlash() != null)
        {
            node.getBSlash().apply(this);
        }
        if(node.getPatternList() != null)
        {
            node.getPatternList().apply(this);
        }
        if(node.getAt() != null)
        {
            node.getAt().apply(this);
        }
        if(node.getProc9() != null)
        {
            node.getProc9().apply(this);
        }
        outALambdaTermProc9(node);
    }

    @Override
    public void caseALetWithinProc9(ALetWithinProc9 node)
    {
        inALetWithinProc9(node);
		if(patternRequired)
		{
			throw new RuntimeException("Expecting pattern (spc L. 539).");
		}
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
        if(node.getProc9() != null)
        {
            node.getProc9().apply(this);
        }
        outALetWithinProc9(node);
    }

    @Override
    public void caseAIfElseProc9(AIfElseProc9 node)
    {
        inAIfElseProc9(node);
		if(patternRequired)
		{
			throw new RuntimeException("Expecting pattern (spc L. 566).");
		}
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
        outAIfElseProc9(node);
    }

    @Override
    public void caseARepProc9(ARepProc9 node)
    {
        inARepProc9(node);
		if(patternRequired)
		{
			throw new RuntimeException("Expecting pattern (spc L. 601).");
		}
        if(node.getRep() != null)
        {
            node.getRep().apply(this);
        }
        if(node.getProc9() != null)
        {
            node.getProc9().apply(this);
        }
        outARepProc9(node);
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
    public void caseARenamingProc10(ARenamingProc10 node)
    {
        inARenamingProc10(node);
		if(patternRequired)
		{
			throw new RuntimeException("Expecting pattern (spc L. 631).");
		}
        if(node.getProc10() != null)
        {
            node.getProc10().apply(this);
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
        outARenamingProc10(node);
    }

    @Override
    public void caseAERenamingProc10(AERenamingProc10 node)
    {
        inAERenamingProc10(node);
		if(patternRequired)
		{
			throw new RuntimeException("Expecting pattern (spc L. 658).");
		}
        if(node.getProc10() != null)
        {
            node.getProc10().apply(this);
        }
        if(node.getDbracketL() != null)
        {
            node.getDbracketL().apply(this);
        }
        if(node.getDbracketR() != null)
        {
            node.getDbracketR().apply(this);
        }
        outAERenamingProc10(node);
    }

    @Override
    public void caseAEventProc10(AEventProc10 node)
    {
        inAEventProc10(node);
        if(node.getEvent() != null)
        {
            node.getEvent().apply(this);
        }
        outAEventProc10(node);
    }
	
	@Override
    public void caseAEventEvent(AEventEvent node)
    {
        inAEventEvent(node);
        if(node.getDpattern() != null)
        {
            node.getDpattern().apply(this);
        }
        {
            List<PField1> copy = new ArrayList<PField1>(node.getField1());
			if(patternRequired && copy.size() > 0)
			{
				throw new RuntimeException("Fields are not allowed for patterns.");
			}
            for(PField1 e : copy)
            {e.apply(this);}
        }
        {
            List<PField2> copy = new ArrayList<PField2>(node.getField2());
			if(patternRequired && copy.size() > 0)
			{
				throw new RuntimeException("Fields are not allowed for patterns.");
			}
            for(PField2 e : copy)
            {e.apply(this);}
        }
        outAEventEvent(node);
    }

    @Override
    public void caseADpatternDpattern(ADpatternDpattern node)
    {
        inADpatternDpattern(node);
		if(!patternRequired)
		{
			throw new RuntimeException("Double Pattern not allowed for Expressions.");
		}
        if(node.getDpattern() != null)
        {
            node.getDpattern().apply(this);
        }
        if(node.getDoubleat() != null)
        {
            node.getDoubleat().apply(this);
        }
        if(node.getDotOp() != null)
        {
            node.getDotOp().apply(this);
        }
        outADpatternDpattern(node);
    }

    @Override
    public void caseADotopDpattern(ADotopDpattern node)
    {
        inADotopDpattern(node);
        if(node.getDotOp() != null)
        {
            node.getDotOp().apply(this);
        }
        outADotopDpattern(node);
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
        }
        outASsDotOp(node);
    }

    @Override
    public void caseAOrBoolExp(AOrBoolExp node)
    {
        inAOrBoolExp(node);
		if(patternRequired)
		{
			throw new RuntimeException("Expecting pattern (spc L. 785).");
		}
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
		if(patternRequired)
		{
			throw new RuntimeException("Expecting pattern (spc L. 819).");
		}
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
		if(patternRequired)
		{
			throw new RuntimeException("Expecting pattern (spc L. 853).");
		}
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

    @Override
    public void caseAEqualBoolExp4(AEqualBoolExp4 node)
    {
        inAEqualBoolExp4(node);
		if(patternRequired)
		{
			throw new RuntimeException("Expecting pattern (spc L. 883).");
		}
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
        outAEqualBoolExp4(node);
    }

    @Override
    public void caseAOrderingLgeBoolExp4(AOrderingLgeBoolExp4 node)
    {
        inAOrderingLgeBoolExp4(node);
		if(patternRequired)
		{
			throw new RuntimeException("Expecting pattern (spc L. 906).");
		}
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
        outAOrderingLgeBoolExp4(node);
    }

    @Override
    public void caseAOrderingLessBoolExp4(AOrderingLessBoolExp4 node)
    {
        inAOrderingLessBoolExp4(node);
		if(patternRequired)
		{
			throw new RuntimeException("Expecting pattern (spc L. 929).");
		}
        if(node.getBoolExp4() != null)
        {
            node.getBoolExp4().apply(this);
        }
        if(node.getLess() != null)
        {
            node.getLess().apply(this);
        }
        if(node.getValExp() != null)
        {
            node.getValExp().apply(this);
        }
        outAOrderingLessBoolExp4(node);
    }

    @Override
    public void caseAOrderingGreaterBoolExp4(AOrderingGreaterBoolExp4 node)
    {
        inAOrderingGreaterBoolExp4(node);
		if(patternRequired)
		{
			throw new RuntimeException("Expecting pattern (spc L. 952).");
		}
        if(node.getBoolExp4() != null)
        {
            node.getBoolExp4().apply(this);
        }
        if(node.getGreater() != null)
        {
            node.getGreater().apply(this);
        }
        if(node.getValExp() != null)
        {
            node.getValExp().apply(this);
        }
        outAOrderingGreaterBoolExp4(node);
    }

    @Override
    public void caseAValExpBoolExp4(AValExpBoolExp4 node)
    {
        inAValExpBoolExp4(node);
        if(node.getValExp() != null)
        {
            node.getValExp().apply(this);
        }
        outAValExpBoolExp4(node);
    }

    @Override
    public void caseAAdditionValExp(AAdditionValExp node)
    {
        inAAdditionValExp(node);
		if(patternRequired)
		{
			throw new RuntimeException("Expecting pattern (spc L. 986).");
		}
        if(node.getValExp() != null)
        {
            node.getValExp().apply(this);
        }
        if(node.getPlus() != null)
        {
            node.getPlus().apply(this);
        }
        if(node.getValExp1() != null)
        {
            node.getValExp1().apply(this);
        }
        outAAdditionValExp(node);
    }

    @Override
    public void caseASubtractionValExp(ASubtractionValExp node)
    {
        inASubtractionValExp(node);
		if(patternRequired)
		{
			throw new RuntimeException("Expecting pattern (spc L. 1009).");
		}
        if(node.getValExp() != null)
        {
            node.getValExp().apply(this);
        }
        if(node.getMinus() != null)
        {
            node.getMinus().apply(this);
        }
        if(node.getValExp1() != null)
        {
            node.getValExp1().apply(this);
        }
        outASubtractionValExp(node);
    }

    @Override
    public void caseAValExp1ValExp(AValExp1ValExp node)
    {
        inAValExp1ValExp(node);
        if(node.getValExp1() != null)
        {
            node.getValExp1().apply(this);
        }
        outAValExp1ValExp(node);
    }

    @Override
    public void caseAMultiplicationValExp1(AMultiplicationValExp1 node)
    {
        inAMultiplicationValExp1(node);
		if(patternRequired)
		{
			throw new RuntimeException("Expecting pattern (spc L. 1043).");
		}
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
        outAMultiplicationValExp1(node);
    }

    @Override
    public void caseAValExp2ValExp1(AValExp2ValExp1 node)
    {
        inAValExp2ValExp1(node);
        if(node.getValExp2() != null)
        {
            node.getValExp2().apply(this);
        }
        outAValExp2ValExp1(node);
    }

    @Override
    public void caseAUnMinusValExp2(AUnMinusValExp2 node)
    {
        inAUnMinusValExp2(node);
		if(patternRequired)
		{
			throw new RuntimeException("Expecting pattern (spc L. 1077).");
		}
        if(node.getMinus() != null)
        {
            node.getMinus().apply(this);
        }
        if(node.getValExp2() != null)
        {
            node.getValExp2().apply(this);
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
        }
        outASequence0ValExp2(node);
    }

    @Override
    public void caseALenSequence0(ALenSequence0 node)
    {
        inALenSequence0(node);
		if(patternRequired)
		{
			throw new RuntimeException("Expecting pattern  (spc L. 1107).");
		}
        if(node.getHash() != null)
        {
            node.getHash().apply(this);
        }
        if(node.getSequence0() != null)
        {
            node.getSequence0().apply(this);
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
        if(node.getAtom() != null)
        {
            node.getAtom().apply(this);
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
        }
        outAAtomSequence1(node);
    }

    @Override
    public void caseANumAtom(ANumAtom node)
    {
        inANumAtom(node);
        if(node.getNumber() != null)
        {
            node.getNumber().apply(this);
        }
        outANumAtom(node);
    }

    @Override
    public void caseASequenceAtom(ASequenceAtom node)
    {
        inASequenceAtom(node);
        if(node.getSequence() != null)
        {
            node.getSequence().apply(this);
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
        }
        outAStringAtom(node);
    }

    @Override
    public void caseACharAtom(ACharAtom node)
    {
        inACharAtom(node);
        if(node.getChar() != null)
        {
            node.getChar().apply(this);
        }
        outACharAtom(node);
    }

    @Override
    public void caseASetAtom(ASetAtom node)
    {
        inASetAtom(node);
        if(node.getSet() != null)
        {
            node.getSet().apply(this);
        }
        outASetAtom(node);
    }

    @Override
    public void caseAWildcardAtom(AWildcardAtom node)
    {
        inAWildcardAtom(node);
		if(!patternRequired)
		{
			throw new RuntimeException("Wildcard not allowed for Expressions.");
		}
        if(node.getWildcard() != null)
        {
            node.getWildcard().apply(this);
        }
        outAWildcardAtom(node);
    }

    @Override
    public void caseAMapAtom(AMapAtom node)
    {
        inAMapAtom(node);
		if(patternRequired)
		{
			throw new RuntimeException("Expecting pattern (spc L. 1237).");
		}
        if(node.getMap() != null)
        {
            node.getMap().apply(this);
        }
        outAMapAtom(node);
    }

    @Override
    public void caseATrue1Atom(ATrue1Atom node)
    {
        inATrue1Atom(node);
		if(patternRequired)
		{
			throw new RuntimeException("Expecting pattern (spc L. 1252).");
		}
        if(node.getTrue1() != null)
        {
            node.getTrue1().apply(this);
        }
        outATrue1Atom(node);
    }

    @Override
    public void caseAFalse1Atom(AFalse1Atom node)
    {
        inAFalse1Atom(node);
		if(patternRequired)
		{
			throw new RuntimeException("Expecting pattern (spc L. 1267).");
		}
        if(node.getFalse1() != null)
        {
            node.getFalse1().apply(this);
        }
        outAFalse1Atom(node);
    }

    @Override
    public void caseATrue2Atom(ATrue2Atom node)
    {
        inATrue2Atom(node);
		if(!patternRequired)
		{
			throw new RuntimeException("Literal Pattern 'True' not allowed for Expressions.");
		}
        if(node.getTrue2() != null)
        {
            node.getTrue2().apply(this);
        }
        outATrue2Atom(node);
    }

    @Override
    public void caseAFalse2Atom(AFalse2Atom node)
    {
        inAFalse2Atom(node);
		if(!patternRequired)
		{
			throw new RuntimeException("Literal Pattern 'False' not allowed for Expressions.");
		}
        if(node.getFalse2() != null)
        {
            node.getFalse2().apply(this);
        }
        outAFalse2Atom(node);
    }

    @Override
    public void caseATupleAtom(ATupleAtom node)
    {
        inATupleAtom(node);
        if(node.getTuple() != null)
        {
            node.getTuple().apply(this);
        }
        if(node.getLambda() != null)
        {
			if(patternRequired)
			{
				throw new RuntimeException("Lambda args not allowed for patterns.");
			}
            node.getLambda().apply(this);
        }
        outATupleAtom(node);
    }

    @Override
    public void caseAIdAtom(AIdAtom node)
    {
        inAIdAtom(node);
        if(node.getId() != null)
        {
            node.getId().apply(this);
        }
        if(node.getTuple() != null)
        {
			if(patternRequired)
			{
				throw new RuntimeException("Var Patterns don't allow parameters.");
			}
            node.getTuple().apply(this);
        }
        outAIdAtom(node);
    }
//***************************************************************************************
//Sequences

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
        if(node.getArguments() != null)
        {
            node.getArguments().apply(this);
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
		if(patternRequired)
		{
			throw new RuntimeException("Expecting pattern (spc L. 1382).");
		}
        if(node.getSeqOpen() != null)
        {
            node.getSeqOpen().apply(this);
        }
        if(node.getArguments() != null)
        {
            node.getArguments().apply(this);
        }
        if(node.getPipe() != null)
        {
            node.getPipe().apply(this);
        }
        if(node.getStmts() != null)
        {
            node.getStmts().apply(this);
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
		if(patternRequired)
		{
			throw new RuntimeException("Expecting pattern (spc L. 1413).");
		}
        if(node.getSeqOpen() != null)
        {
            node.getSeqOpen().apply(this);
        }
        if(node.getL() != null)
        {
            node.getL().apply(this);
        }
        if(node.getDotdot() != null)
        {
            node.getDotdot().apply(this);
        }
        if(node.getR() != null)
        {
            node.getR().apply(this);
        }
        if(node.getSeqClose() != null)
        {
            node.getSeqClose().apply(this);
        }
        outACrSeqSequence(node);
    }
	
    @Override
    public void caseARanCompSequence(ARanCompSequence node)
    {
        inARanCompSequence(node);
		if(patternRequired)
		{
			throw new RuntimeException("Expecting pattern (spc L. 1444).");
		}
        if(node.getSeqOpen() != null)
        {
            node.getSeqOpen().apply(this);
        }
        if(node.getL() != null)
        {
            node.getL().apply(this);
        }
        if(node.getDotdot() != null)
        {
            node.getDotdot().apply(this);
        }
        if(node.getR() != null)
        {
            node.getR().apply(this);
        }
        if(node.getPipe() != null)
        {
            node.getPipe().apply(this);
        }
        if(node.getStmts() != null)
        {
            node.getStmts().apply(this);
        }
        if(node.getSeqClose() != null)
        {
            node.getSeqClose().apply(this);
        }
        outARanCompSequence(node);
    }

    @Override
    public void caseAOrSeqSequence(AOrSeqSequence node)
    {
        inAOrSeqSequence(node);
		if(patternRequired)
		{
			throw new RuntimeException("Expecting pattern (spc L. 1483).");
		}
        if(node.getSeqOpen() != null)
        {
            node.getSeqOpen().apply(this);
        }
        if(node.getValExp() != null)
        {
            node.getValExp().apply(this);
        }
        if(node.getDotdot() != null)
        {
            node.getDotdot().apply(this);
        }
        if(node.getSeqClose() != null)
        {
            node.getSeqClose().apply(this);
        }
        outAOrSeqSequence(node);
    }

    @Override
    public void caseAInfComprSequence(AInfComprSequence node)
    {
        inAInfComprSequence(node);
		if(patternRequired)
		{
			throw new RuntimeException("Expecting pattern (spc L. 1510).");
		}
        if(node.getSeqOpen() != null)
        {
            node.getSeqOpen().apply(this);
        }
        if(node.getValExp() != null)
        {
            node.getValExp().apply(this);
        }
        if(node.getDotdot() != null)
        {
            node.getDotdot().apply(this);
        }
        if(node.getPipe() != null)
        {
            node.getPipe().apply(this);
        }
        if(node.getStmts() != null)
        {
            node.getStmts().apply(this);
        }
        if(node.getSeqClose() != null)
        {
            node.getSeqClose().apply(this);
        }
        outAInfComprSequence(node);
    }

    @Override
    public void caseAEnumSeqSequence(AEnumSeqSequence node)
    {
        inAEnumSeqSequence(node);
		if(patternRequired)
		{
			throw new RuntimeException("Expecting pattern (spc L. 1545).");
		}
        if(node.getEnumSeq() != null)
        {
            node.getEnumSeq().apply(this);
        }
        outAEnumSeqSequence(node);
    }

    @Override
    public void caseASeqCompSequence(ASeqCompSequence node)
    {
        inASeqCompSequence(node);
		if(patternRequired)
		{
			throw new RuntimeException("Expecting pattern (spc L. 1560).");
		}
        if(node.getSeqComp() != null)
        {
            node.getSeqComp().apply(this);
        }
        outASeqCompSequence(node);
    }

//***************************************************************************************
//Sets  

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
			if(patternRequired 
					&& checkNumberOfArgs(node.getArguments().toString())>1)
			{
				throw new RuntimeException("Expecting Set Pattern.");
			}			
            node.getArguments().apply(this);
        }
        if(node.getBraceR() != null)
        {
            node.getBraceR().apply(this);
        }
        outASetSet(node);
    }

    @Override
    public void caseAClosedRangeSet(AClosedRangeSet node)
    {
        inAClosedRangeSet(node);
		if(patternRequired)
		{
			throw new RuntimeException("Expecting pattern (spc L. 1602).");
		}
        if(node.getBraceL() != null)
        {
            node.getBraceL().apply(this);
        }
        if(node.getL() != null)
        {
            node.getL().apply(this);
        }
        if(node.getDotdot() != null)
        {
            node.getDotdot().apply(this);
        }
        if(node.getR() != null)
        {
            node.getR().apply(this);
        }
        if(node.getBraceR() != null)
        {
            node.getBraceR().apply(this);
        }
        outAClosedRangeSet(node);
    }

    @Override
    public void caseAOpenRangeSet(AOpenRangeSet node)
    {
        inAOpenRangeSet(node);
		if(patternRequired)
		{
			throw new RuntimeException("Expecting pattern (spc L. 1633).");
		}
        if(node.getBraceL() != null)
        {
            node.getBraceL().apply(this);
        }
        if(node.getValExp() != null)
        {
            node.getValExp().apply(this);
        }
        if(node.getDotdot() != null)
        {
            node.getDotdot().apply(this);
        }
        if(node.getBraceR() != null)
        {
            node.getBraceR().apply(this);
        }
        outAOpenRangeSet(node);
    }

    @Override
    public void caseASetComprehensionSet(ASetComprehensionSet node)
    {
        inASetComprehensionSet(node);
		if(patternRequired)
		{
			throw new RuntimeException("Expecting pattern (spc L. 1660).");
		}
        if(node.getBraceL() != null)
        {
            node.getBraceL().apply(this);
        }
        if(node.getArguments() != null)
        {
            node.getArguments().apply(this);
        }
        if(node.getPipe() != null)
        {
            node.getPipe().apply(this);
        }
        if(node.getStmts() != null)
        {
            node.getStmts().apply(this);
        }
        if(node.getBraceR() != null)
        {
            node.getBraceR().apply(this);
        }
        outASetComprehensionSet(node);
    }

    @Override
    public void caseARangedComprehensionSet(ARangedComprehensionSet node)
    {
        inARangedComprehensionSet(node);
		if(patternRequired)
		{
			throw new RuntimeException("Expecting pattern (spc L. 1691).");
		}
        if(node.getBraceL() != null)
        {
            node.getBraceL().apply(this);
        }
        if(node.getL() != null)
        {
            node.getL().apply(this);
        }
        if(node.getDotdot() != null)
        {
            node.getDotdot().apply(this);
        }
        if(node.getR() != null)
        {
            node.getR().apply(this);
        }
        if(node.getPipe() != null)
        {
            node.getPipe().apply(this);
        }
        if(node.getStmts() != null)
        {
            node.getStmts().apply(this);
        }
        if(node.getBraceR() != null)
        {
            node.getBraceR().apply(this);
        }
        outARangedComprehensionSet(node);
    }

    @Override
    public void caseAInfiniteComprehensionSet(AInfiniteComprehensionSet node)
    {
        inAInfiniteComprehensionSet(node);
		if(patternRequired)
		{
			throw new RuntimeException("Expecting pattern (spc L. 1730).");
		}
        if(node.getBraceL() != null)
        {
            node.getBraceL().apply(this);
        }
        if(node.getValExp() != null)
        {
            node.getValExp().apply(this);
        }
        if(node.getDotdot() != null)
        {
            node.getDotdot().apply(this);
        }
        if(node.getPipe() != null)
        {
            node.getPipe().apply(this);
        }
        if(node.getStmts() != null)
        {
            node.getStmts().apply(this);
        }
        if(node.getBraceR() != null)
        {
            node.getBraceR().apply(this);
        }
        outAInfiniteComprehensionSet(node);
    }

    @Override
    public void caseAEnumSetSet(AEnumSetSet node)
    {
        inAEnumSetSet(node);
		if(patternRequired)
		{
			throw new RuntimeException("Expecting pattern (spc L. 1765).");
		}
        if(node.getEnumSet() != null)
        {
            node.getEnumSet().apply(this);
        }
        outAEnumSetSet(node);
    }

    @Override
    public void caseASetCompSet(ASetCompSet node)
    {
        inASetCompSet(node);
		if(patternRequired)
		{
			throw new RuntimeException("Expecting pattern (spc L. 1780).");
		}
        if(node.getSetComp() != null)
        {
            node.getSetComp().apply(this);
        }
        outASetCompSet(node);
    }

//***************************************************************************************
//Statements

    @Override
    public void caseAGenerator(AGenerator node)
    {
        inAGenerator(node);
        if(node.getDpattern() != null)
        {
			patternRequired = true;
            node.getDpattern().apply(this);
			patternRequired = false;
        }
        if(node.getGeneratorOp() != null)
        {
            node.getGeneratorOp().apply(this);
        }
        if(node.getProc1() != null)
        {
            node.getProc1().apply(this);
        }
        outAGenerator(node);
    }
	
	public int checkNumberOfArgs(String s)
	{
		char[] args = s.toCharArray();
		int depth = 0;
		int argCount = 1;
		for(int i = 0; i<args.length;i++)
		{
			if(args[i] == '(' || args[i] == '{' || args[i] == '\u00AB')
			{
				depth++;
			}
			else if(args[i] == ')' || args[i] == '}' || args[i] == '\u00BB')
			{
				depth -= 1;
			}
			else if(args[i] == ',' && depth == 0)
			{
				argCount++;
			}
		}
		return argCount;
	}
}
