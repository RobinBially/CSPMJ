import java.io.*;
import java.*;
import java.util.*;
import java.lang.*;
import java.lang.Character;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.charset.Charset;
import java.nio.file.Files;
import CSPMparser.analysis.*;
import CSPMparser.node.*;


public class Typechecker extends DepthFirstAdapter
{

    @Override
    public void caseStart(Start node)
    {
        inStart(node);	
        node.getPDefs().apply(this);
        node.getEOF().apply(this);
        outStart(node);
    }


    @Override
    public void caseALtlAssertion(ALtlAssertion node)
	{
        inALtlAssertion(node);
        if(node.getAssert() != null)
        {
            node.getAssert().apply(this);
        }
        if(node.getProc1() != null)
        {
            node.getProc1().apply(this);
        }
        if(node.getPipeEquals() != null)
        {
            node.getPipeEquals().apply(this);
        }
        if(node.getLtl() != null)
        {
            node.getLtl().apply(this);
        }
        if(node.getDdot() != null)
        {
            node.getDdot().apply(this);
        }
        if(node.getAllInQuotes() != null)
        {
			LTLparser ltl = new LTLparser(node.getAllInQuotes().getText());	
			ltl.checkLTL();
            node.getAllInQuotes().apply(this);
        }
        outALtlAssertion(node);
    }
	

    @Override
    public void caseACtlAssertion(ACtlAssertion node)
    {
        inACtlAssertion(node);
        if(node.getAssert() != null)
        {
            node.getAssert().apply(this);
        }
        if(node.getProc1() != null)
        {
            node.getProc1().apply(this);
        }
        if(node.getPipeEquals() != null)
        {
            node.getPipeEquals().apply(this);
        }
        if(node.getCtl() != null)
        {
            node.getCtl().apply(this);
        }
        if(node.getDdot() != null)
        {
            node.getDdot().apply(this);
        }
        if(node.getAllInQuotes() != null)
        {
			CTLparser ctl = new CTLparser(node.getAllInQuotes().getText());	
			ctl.checkCTL();
            node.getAllInQuotes().apply(this);
        }
        outACtlAssertion(node);
    }
}
