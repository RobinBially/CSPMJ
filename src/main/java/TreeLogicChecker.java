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


public class TreeLogicChecker extends DepthFirstAdapter 
{
	
    @Override
    public void caseALtlAssertion(ALtlAssertion node)
    {
        inALtlAssertion(node);
        if(node.getProc1() != null)
        {
            node.getProc1().apply(this);
        }
		if(node.getString() != null)
        {
            node.getString().apply(this);
			//System.out.println("CHECKING LTL FORMULA");
			LTLparser ltl = new LTLparser(node.getString().getText());	
			try
			{
				ltl.checkLTL();
			}
			catch(TreeLogicException tle)
			{
				tle.addText(getErrorLoc(node.getString()));
				throw tle;
			}
        }
        outALtlAssertion(node);
    }


    @Override
    public void caseACtlAssertion(ACtlAssertion node)
    {
        inACtlAssertion(node);
        if(node.getProc1() != null)
        {
            node.getProc1().apply(this);
        }
		if(node.getString() != null)
        {
            node.getString().apply(this);
			//System.out.println("CHECKING CTL FORMULA");
			CTLparser ctl = new CTLparser(node.getString().getText());
			try
			{
				ctl.checkCTL();
			}
			catch(TreeLogicException tle)
			{
				tle.addText(getErrorLoc(node.getString()));
				throw tle;
			}
        }		
        outACtlAssertion(node);
    }
	
	public String getErrorLoc(final Node node)
	{
		String s = ","+node.getStartPos().getLine()+","+node.getStartPos().getPos();
		return s;
	}
}