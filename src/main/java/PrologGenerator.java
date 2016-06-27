/** 
 * (c) 2009-2014 Lehrstuhl fuer Softwaretechnik und Programmiersprachen, 
 * Heinrich Heine Universitaet Duesseldorf
 * This software is licenced under EPL 1.0 (http://www.eclipse.org/org/documents/epl-v10.html) 
 * */


import java.io.IOException;
import java.util.Locale;

import CSPMparser.analysis.DepthFirstAdapter;
import CSPMparser.node.AExpressionDef;
import CSPMparser.node.AIdId;
import CSPMparser.node.AManyLinesDefs;
import CSPMparser.node.ANewDefExpression;
import CSPMparser.node.ASkipId;
import CSPMparser.node.AStopId;
import CSPMparser.node.PExpression;
import CSPMparser.node.Start;
import CSPMparser.node.Token;
import CSPMparser.node.Node;
import de.prob.prolog.output.IPrologTermOutput;
import de.prob.prolog.output.StructuredPrologOutput;
import de.prob.prolog.term.PrologTerm;

public class PrologGenerator extends DepthFirstAdapter {
	private final IPrologTermOutput p;
	private final String currentStateID;
	
	public PrologGenerator(final IPrologTermOutput pto,
			final String currentStateID) {
		//super();
		this.p = pto;
		this.currentStateID = currentStateID;
	}

	protected void applyPrologGenerator(StructuredPrologOutput pto,
			String stateID, Start ast) {
		final PrologGenerator prologGenerator = new PrologGenerator(pto,
				stateID);
		ast.apply(prologGenerator);
	}
	
	@Override
	public void defaultIn(final Node node) {
		StringBuffer sb = new StringBuffer(node.getClass().getSimpleName());
		//sb.setLength(sb.length() - 3);
		sb.deleteCharAt(0);
		String term = sb.toString().toLowerCase(Locale.ENGLISH);
		p.openTerm(term);
	}
    @Override
	public void defaultOut(final Node node) {
		p.closeTerm();
	}
	
	@Override
	public void inStart(final Start node) {
		// Do not call default in Method
	}

	@Override
	public void outStart(final Start node) {
		// Do not call default out Method
	}
	
//	@Override
//	public void caseAManyLinesDefs(final AManyLinesDefs node) {
//		//Robin: please adapt the sablecc grammar such that we get a list of single definitions and not just a node with 'single definitions' as childs
//		//p.fullstop();
//	};
//	
	@Override
	public void caseAIdId(final AIdId node) {
		p.printAtom(node.getIdentifier().getText());
	}
	
	@Override
	public void caseASkipId(final ASkipId node) {
		p.openTerm("skip");
		printSrcLoc(node);
		p.closeTerm();
	}
    @Override
	public void caseAStopId(final AStopId node) {
		p.openTerm("stop");
		printSrcLoc(node);
		p.closeTerm();
	}
    
    
   public void printSrcLoc(Node node) {
	   /* Data type of src_loc in cspmf 
	    * src_loc {
   fixedStartLine   = getStartLine s
  ,fixedStartCol    = getStartCol s
  ,fixedEndLine     = getEndLine e
  ,fixedEndCol      = getEndCol e
  ,fixedLen         = getEndOffset e - getStartOffset s
  ,fixedStartOffset = getStartOffset s
      }
	  */
		p.openTerm("src_loc");
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
