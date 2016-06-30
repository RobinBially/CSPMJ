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
	private Tree tree = new Tree(null);
	
	private int letWithinDepth = 0;	
	private int currentLetWithinCount = 0;


	private ArrayList<String> dtypes = new ArrayList<String>();//Speichert Name aller Datentypen

	private boolean currentInTypeExpNode; //z.b. datatype Type = A.Bool | B.Type.{1..3}
									//Nur im TypeExp Knoten, darf Type vom Typ Type sein !!!!
	private String currentTypeExp;
	private String currentDatatype;
	private String currentClauseName;
	private HashMap nodeMap = new HashMap();
	private ArrayList<ArrayList<String>> arguments = new ArrayList<ArrayList<String>>();
	private int argDepth = -1;
	
	
//***************************************************************************************
//Start
//    @Override
//    public void caseStart(Start node)
//    {
//        inStart(node);
//        node.getPDefs().apply(this);
//        node.getEOF().apply(this);
//		tree.setHeadAsCurrent();
//		System.out.println(tree.getCurrent().getTypes());
//        outStart(node);
//    }
//	
////***************************************************************************************	
////Channels
//
//    @Override
//    public void caseAChan(AChan node)
//    {
//        inAChan(node);
//		String a = node.getId().toString().replaceAll(" ","");
//        if(node.getChannel() != null)
//        {
//            node.getChannel().apply(this);
//        }
//        if(node.getId() != null)
//        {
//            node.getId().apply(this);
//        }
//        if(node.getChanRek() != null)
//        {
//            node.getChanRek().apply(this);
//        }
//		
//		if(tree.getTypes().get(a) != null)
//		{
//			tree.getTypes().remove(a);
//		}
//		tree.getTypes().put(a, nodeMap.get(node.getChanRek()).toString()+"=>Event");
//			
//		nodeMap.remove(node.getChanRek());
//		nodeMap.remove(node.getId());
//
//        outAChan(node);
//    }	
//	
//	@Override
//    public void caseARekChanRek(ARekChanRek node)
//    {
//        inARekChanRek(node);
//		String a = node.getId().toString().replaceAll(" ","");
//        if(node.getComma() != null)
//        {
//            node.getComma().apply(this);
//        }
//        if(node.getId() != null)
//        {
//            node.getId().apply(this);
//        }
//        if(node.getChanRek() != null)
//        {
//            node.getChanRek().apply(this);
//        }
//		//channel a,b,c: this pushes types of b and c into types
//		
//		if(tree.getTypes().get(a) != null)
//		{
//			tree.getTypes().remove(a);
//		}
//		tree.getTypes().put(a, nodeMap.get(node.getChanRek()).toString()+"=>Event");
//				
//		nodeMap.put(node,nodeMap.get(node.getChanRek()));
//		nodeMap.remove(node.getId());
//		nodeMap.remove(node.getChanRek());
//        outARekChanRek(node);
//    }
//	
//	@Override
//    public void caseAEndChanRek(AEndChanRek node)
//    {
//        inAEndChanRek(node);
//        if(node.getDdot() != null)
//        {
//            node.getDdot().apply(this);
//        }
//        if(node.getTypeExp() != null)
//        {
//			currentInTypeExpNode = true;
//            node.getTypeExp().apply(this);
//			currentInTypeExpNode = false;
//			nodeMap.put(node,nodeMap.get(node.getTypeExp()));
//        }
//		nodeMap.remove(node.getTypeExp());
//        outAEndChanRek(node);
//    }
//
//	
////***************************************************************************************
////Datatypes, Subtypes, Nametypes	
//    @Override
//    public void caseATypedef(ATypedef node)
//    {
//        inATypedef(node);
//        if(node.getId() != null)
//        {
//            node.getId().apply(this);
//			currentDatatype = node.getId().toString().replaceAll(" ","");
//			dtypes.add(currentDatatype); // datatype X = ... saves X
//        }
//        if(node.getEq() != null)
//        {
//            node.getEq().apply(this);
//        }
//        if(node.getClause() != null)
//        {
//            node.getClause().apply(this);
//			if(nodeMap.containsKey(node.getClause()))
//			{
//				String a = nodeMap.get(node.getClause()).toString();
//				nodeMap.remove(node.getClause());						
//				tree.getTypes().put(currentClauseName,a+"=>"+currentDatatype);
//														//z.B. ...= Y.Bool      
//														//Y:: Bool=>X, wobei Y ccn
//			}
//			else
//			{
//				tree.getTypes().put(currentClauseName,currentDatatype);
//			}
//			
//        }
//        {
//            List<PTypedefRek> copy = new ArrayList<PTypedefRek>(node.getTypedefRek());
//            for(PTypedefRek e : copy)
//            {
//                e.apply(this);
//				if(nodeMap.containsKey(e))
//				{	
//					String a = nodeMap.get(e).toString();
//					nodeMap.remove(e);
//					tree.getTypes().put(currentClauseName,a+"=>"+currentDatatype);
//				}
//				else
//				{
//					tree.getTypes().put(currentClauseName,currentDatatype);
//				}
//            }
//        }
////		System.out.println(types);
//		currentDatatype = "";
//        outATypedef(node);
//    }
//	
//	@Override
//    public void caseAClause(AClause node)
//    {
//        inAClause(node);
//        if(node.getClauseName() != null)
//        {
//            node.getClauseName().apply(this);
//			currentClauseName = node.getClauseName().toString();
//			currentClauseName = currentClauseName.replaceAll(" ","");
//        }
//        if(node.getDotted() != null)
//        {
//            node.getDotted().apply(this);
//			nodeMap.put(node, nodeMap.get(node.getDotted()));
//			nodeMap.remove(node.getDotted());
//        }
//        outAClause(node);
//    }
//	
//	@Override
//    public void caseADotted(ADotted node)
//    {
//        inADotted(node);
//        if(node.getDot() != null)
//        {
//            node.getDot().apply(this);
//        }
//        if(node.getTypeExp() != null)
//        {
//			currentInTypeExpNode = true;
//            node.getTypeExp().apply(this);
//			currentInTypeExpNode = false;
//			nodeMap.put(node, nodeMap.get(node.getTypeExp()));
//        }
//		nodeMap.remove(node.getTypeExp());
//        outADotted(node);
//    }
//	
//    @Override
//    public void caseATypedefRek(ATypedefRek node)
//    {
//        inATypedefRek(node);
//        if(node.getPipe() != null)
//        {
//            node.getPipe().apply(this);
//        }
//        if(node.getClause() != null)
//        {
//            node.getClause().apply(this);
//			if(nodeMap.containsKey(node.getClause()))
//			{
//				nodeMap.put(node,nodeMap.get(node.getClause()));
//			}
//        }
//		nodeMap.remove(node.getClause());
//        outATypedefRek(node);
//    }	
//	
//	@Override
//    public void caseAStypeTypes(AStypeTypes node)
//    {
//        inAStypeTypes(node);
//        if(node.getSType() != null)
//        {
//            node.getSType().apply(this);
//        }
//        if(node.getTypedef() != null)
//        {
//            node.getTypedef().apply(this);
//        }
//        outAStypeTypes(node);
//    }	
//	
//	@Override
//    public void caseANtype(ANtype node)
//    {
//        inANtype(node);
//        if(node.getNType() != null)
//        {
//            node.getNType().apply(this);
//        }
//        if(node.getId() != null)
//        {
//            node.getId().apply(this);
//        }
//        if(node.getEq() != null)
//        {
//            node.getEq().apply(this);
//        }
//        if(node.getTypeExp() != null)
//        {
//            node.getTypeExp().apply(this);
//        }
//        outANtype(node);
//    }
////***************************************************************************************
////Type Expressions
//
//    @Override
//    public void caseADottedTypeExp(ADottedTypeExp node)
//    {
//        inADottedTypeExp(node);
//		String upperType = "";
//		String lowerType = "";
//        if(node.getTypeExp() != null)
//        {
//            node.getTypeExp().apply(this);
//			upperType = nodeMap.get(node.getTypeExp()).toString();
//			
//        }
//        if(node.getDot() != null)
//        {
//            node.getDot().apply(this);
//        }
//        if(node.getTypeExp1() != null)
//        {
//            node.getTypeExp1().apply(this);
//			lowerType = nodeMap.get(node.getTypeExp1()).toString();
//
//        }
//		nodeMap.put(node, upperType+"=>"+lowerType);
//		nodeMap.remove(node.getTypeExp());
//		nodeMap.remove(node.getTypeExp1());
//        outADottedTypeExp(node);
//    }	
//	
//    @Override
//    public void caseAParTypeExp(AParTypeExp node)
//    {
//        inAParTypeExp(node);
//        if(node.getTypeExp1() != null)
//        {
//            node.getTypeExp1().apply(this);
//			nodeMap.put(node, nodeMap.get(node.getTypeExp1()));
//			nodeMap.remove(node.getTypeExp1());
//        }
//        outAParTypeExp(node);
//    }
//
//    @Override
//    public void caseAParTypeExp1(AParTypeExp1 node)
//    {
//		String upper = "";
//		int copySize = 0;
//		ArrayList<String> lower = new ArrayList<String>();
//        inAParTypeExp1(node);
//        if(node.getParL() != null)
//        {
//            node.getParL().apply(this);
//        }
//        if(node.getTypeExp() != null)
//        {
//            node.getTypeExp().apply(this);
//			upper = nodeMap.get(node.getTypeExp()).toString();
//			nodeMap.remove(node.getTypeExp());
//			
//        }
//        {
//            List<PTypeExps> copy = new ArrayList<PTypeExps>(node.getTypeExps());
//            for(PTypeExps e : copy)
//            {
//				copySize++;
//                e.apply(this);
//				lower.add(nodeMap.get(e).toString());
//				nodeMap.remove(e);
//            }
//        }
//        if(node.getParR() != null)
//        {
//            node.getParR().apply(this);
//        }
//		
//		String full = "";
//		if(copySize>0)
//		{
//			full = "("+upper;
//			for(int i = 0;i<lower.size();i++)
//			{
//				full += ","+lower.get(i);
//			}
//			full += ")";
//		}
//		else
//		{
//			full = upper;
//		}
//		
//		nodeMap.put(node,full);
//        outAParTypeExp1(node);
//    }
//
//    @Override
//    public void caseASetTypeTypeExp1(ASetTypeTypeExp1 node)
//    {
//        inASetTypeTypeExp1(node);
//        if(node.getTypeExp2() != null)
//        {
//            node.getTypeExp2().apply(this);
//			nodeMap.put(node, nodeMap.get(node.getTypeExp2()));
//			nodeMap.remove(node.getTypeExp2());
//        }
//        outASetTypeTypeExp1(node);
//    }
//	
//	@Override
//    public void caseASetTypeExp2(ASetTypeExp2 node)
//    {
//        inASetTypeExp2(node);
//        if(node.getSet() != null)
//        {
//            node.getSet().apply(this);
//			String a = "";
//			Pattern pattern = Pattern.compile("\\{(.*)\\}");
//			Matcher matcher = pattern.matcher(nodeMap.get(node.getSet()).toString());
//			while(matcher.find())
//			{
//				a = matcher.group(1);
//			}
//			nodeMap.put(node, a);
//			nodeMap.remove(node.getSet());
//        }
//        outASetTypeExp2(node);
//    }	
//	
//	@Override
//    public void caseASetNameTypeExp2(ASetNameTypeExp2 node)
//    {
//        inASetNameTypeExp2(node);
//        if(node.getId() != null)
//        {
//            node.getId().apply(this);
//			String a = nodeMap.get(node.getId()).toString().replaceAll(" ","");
//			for(int j = 0; j<dtypes.size();j++)
//			{
//				if(a.equals(dtypes.get(j)))
//				{
//					nodeMap.put(node,a);
//				}
//			}
//			if(a.matches("\\{.*\\}"))
//			{
//				Pattern pattern = Pattern.compile("\\{(.*)\\}");
//				Matcher matcher = pattern.matcher(a);
//				String t = "";
//				while(matcher.find())
//				{
//					t = matcher.group(1);
//				}
//				nodeMap.put(node,t);
//			}
//				
//		}
//        if(node.getTuple() != null)
//        {
//            node.getTuple().apply(this);
//        }
//		nodeMap.remove(node.getId());
//		outASetNameTypeExp2(node);
//    }
//	
//    @Override
//    public void caseATypeExps(ATypeExps node)
//    {
//        inATypeExps(node);
//        if(node.getComma() != null)
//        {
//            node.getComma().apply(this);
//        }
//        if(node.getTypeExp() != null)
//        {
//            node.getTypeExp().apply(this);
//			nodeMap.put(node,nodeMap.get(node.getTypeExp()));
//			nodeMap.remove(node.getTypeExp());
//        }
//        outATypeExps(node);
//    }
////***************************************************************************************
////Process Expressions
//
//    @Override
//    public void caseAHideProc1(AHideProc1 node)
//    {
//        inAHideProc1(node);
//		String a = nodeMap.get(node.getProc1()).toString();
//		String b = nodeMap.get(node.getProc2()).toString();
//        if(node.getProc1() != null)
//        {
//            node.getProc1().apply(this);
//			if(!a.equals("Proc") && !a.equals("_"))
//			{
//				throw new RuntimeException(
//				"TypeError in Typechecker at caseAHideProc1 (Line "
//				+Thread.currentThread().getStackTrace()[1].getLineNumber()+")");
//			}
//        }
//        if(node.getBSlash() != null)
//        {
//            node.getBSlash().apply(this);
//        }
//        if(node.getProc2() != null)
//        {
//            node.getProc2().apply(this);
//			if(!b.equals("Proc") && !b.equals("_"))
//			{
//				throw new RuntimeException(
//				"TypeError in Typechecker at caseAHideProc1 (Line "
//				+Thread.currentThread().getStackTrace()[1].getLineNumber()+")");
//			}
//        }
//		nodeMap.remove(node.getProc1());
//		nodeMap.remove(node.getProc2());
//        outAHideProc1(node);
//    }
//
//    @Override
//    public void caseAP2Proc1(AP2Proc1 node)
//    {
//        inAP2Proc1(node);
//        if(node.getProc2() != null)
//        {
//            node.getProc2().apply(this);
//			nodeMap.put(node,nodeMap.get(node.getProc2()));
//        }
//		nodeMap.remove(node.getProc2());
//        outAP2Proc1(node);
//    }
//
//    @Override
//    public void caseAIleaveProc2(AIleaveProc2 node)
//    {
//        inAIleaveProc2(node);
//		String a = nodeMap.get(node.getProc2()).toString();
//		String b = nodeMap.get(node.getProc3()).toString();
//        if(node.getProc2() != null)
//        {
//            node.getProc2().apply(this);
//			if(!a.equals("Proc") && !a.equals("_"))
//			{
//				throw new RuntimeException(
//				"TypeError in Typechecker at caseAIleaveProc2 (Line "
//				+Thread.currentThread().getStackTrace()[1].getLineNumber()+")");
//			}
//        }
//        if(node.getILeaving() != null)
//        {
//            node.getILeaving().apply(this);
//        }
//        if(node.getProc3() != null)
//        {
//            node.getProc3().apply(this);
//			if(!b.equals("Proc") && !b.equals("_"))
//			{
//				throw new RuntimeException(
//				"TypeError in Typechecker at caseAIleaveProc2 (Line "
//				+Thread.currentThread().getStackTrace()[1].getLineNumber()+")");
//			}
//        }
//		nodeMap.remove(node.getProc2());
//		nodeMap.remove(node.getProc3());
//        outAIleaveProc2(node);
//    }
//
//    @Override
//    public void caseAP3Proc2(AP3Proc2 node)
//    {
//        inAP3Proc2(node);
//        if(node.getProc3() != null)
//        {
//            node.getProc3().apply(this);
//			nodeMap.put(node,nodeMap.get(node.getProc3()));
//        }
//		nodeMap.remove(node.getProc3());
//        outAP3Proc2(node);
//    }
//
//    @Override
//    public void caseAExceptProc3(AExceptProc3 node)
//    {
//        inAExceptProc3(node);		
//		String a = nodeMap.get(node.getProc3()).toString();
//		String b = nodeMap.get(node.getProc4()).toString();
//		String c = nodeMap.get(node.getEvent()).toString();
//        if(node.getProc3() != null)
//        {
//            node.getProc3().apply(this);
//			if(!a.equals("Proc") && !a.equals("_"))
//			{
//				throw new RuntimeException(
//				"TypeError in Typechecker at caseAExceptProc3 (Line "
//				+Thread.currentThread().getStackTrace()[1].getLineNumber()+")");
//			}
//        }
//        if(node.getBracketPipeL() != null)
//        {
//            node.getBracketPipeL().apply(this);
//        }
//        if(node.getEvent() != null)
//        {
//            node.getEvent().apply(this);
//			if(!c.equals("Event") && !c.equals("_"))
//			{
//				throw new RuntimeException(
//				"TypeError in Typechecker at caseAExceptProc3 (Line "
//				+Thread.currentThread().getStackTrace()[1].getLineNumber()+")");
//			}
//        }
//        if(node.getPipe() != null)
//        {
//            node.getPipe().apply(this);
//        }
//        if(node.getTriaR() != null)
//        {
//            node.getTriaR().apply(this);
//        }
//        if(node.getProc4() != null)
//        {
//            node.getProc4().apply(this);
//			if(!b.equals("Proc") && !b.equals("_"))
//			{
//				throw new RuntimeException(
//				"TypeError in Typechecker at caseAExceptProc3 (Line "
//				+Thread.currentThread().getStackTrace()[1].getLineNumber()+")");
//			}
//        }
//		nodeMap.remove(node.getProc3());
//		nodeMap.remove(node.getProc4());
//		nodeMap.remove(node.getEvent());
//        outAExceptProc3(node);
//    }
//
//    @Override
//    public void caseAGenParProc3(AGenParProc3 node)
//    {
//        inAGenParProc3(node);
//		String a = nodeMap.get(node.getProc3()).toString();
//		String b = nodeMap.get(node.getEvent()).toString();
//		String c = nodeMap.get(node.getProc4()).toString();
//        if(node.getProc3() != null)
//        {
//            node.getProc3().apply(this);
//			if(!a.equals("Proc") && !a.equals("_"))
//			{
//				throw new RuntimeException(
//				"TypeError in Typechecker at caseAGenParProc3 (Line "
//				+Thread.currentThread().getStackTrace()[1].getLineNumber()+")");
//			}
//        }
//        if(node.getBracketPipeL() != null)
//        {
//            node.getBracketPipeL().apply(this);
//        }
//        if(node.getEvent() != null)
//        {
//            node.getEvent().apply(this);
//			if(!b.equals("Event") && !b.equals("_"))
//			{
//				throw new RuntimeException(
//				"TypeError in Typechecker at caseAGenParProc3 (Line "
//				+Thread.currentThread().getStackTrace()[1].getLineNumber()+")");
//			}
//        }
//        if(node.getBracketPipeR() != null)
//        {
//            node.getBracketPipeR().apply(this);
//        }
//        if(node.getProc4() != null)
//        {
//            node.getProc4().apply(this);
//			if(!c.equals("Proc") && !c.equals("_"))
//			{
//				throw new RuntimeException(
//				"TypeError in Typechecker at caseAGenParProc3 (Line "
//				+Thread.currentThread().getStackTrace()[1].getLineNumber()+")");
//			}
//        }
//		nodeMap.remove(node.getProc3());
//		nodeMap.remove(node.getProc4());
//		nodeMap.remove(node.getEvent());
//        outAGenParProc3(node);
//    }
//
//    @Override
//    public void caseAAlphParProc3(AAlphParProc3 node)
//    {
//        inAAlphParProc3(node);
//		String a = nodeMap.get(node.getProc3()).toString();
//		String b = nodeMap.get(node.getEventl()).toString();
//		String c = nodeMap.get(node.getEventr()).toString();
//		String d = nodeMap.get(node.getProc4()).toString();
//        if(node.getProc3() != null)
//        {
//            node.getProc3().apply(this);
//			if(!a.equals("Proc") && !a.equals("_"))
//			{
//				throw new RuntimeException(
//				"TypeError in Typechecker at caseAAlphParProc3 (Line "
//				+Thread.currentThread().getStackTrace()[1].getLineNumber()+")");
//			}
//        }
//        if(node.getBracketL() != null)
//        {
//            node.getBracketL().apply(this);
//        }
//        if(node.getEventl() != null)
//        {
//            node.getEventl().apply(this);
//			if(!b.equals("Event") && !b.equals("_"))
//			{
//				throw new RuntimeException(
//				"TypeError in Typechecker at caseAAlphParProc3 (Line "
//				+Thread.currentThread().getStackTrace()[1].getLineNumber()+")");
//			}
//        }
//        if(node.getDpipe() != null)
//        {
//            node.getDpipe().apply(this);
//        }
//        if(node.getEventr() != null)
//        {
//            node.getEventr().apply(this);
//			if(!c.equals("Event") && !c.equals("_"))
//			{
//				throw new RuntimeException(
//				"TypeError in Typechecker at caseAAlphParProc3 (Line "
//				+Thread.currentThread().getStackTrace()[1].getLineNumber()+")");
//			}
//        }
//        if(node.getBracketR() != null)
//        {
//            node.getBracketR().apply(this);
//        }
//        if(node.getProc4() != null)
//        {
//            node.getProc4().apply(this);
//			if(!d.equals("Proc") && !d.equals("_"))
//			{
//				throw new RuntimeException(
//				"TypeError in Typechecker at caseAAlphParProc3 (Line "
//				+Thread.currentThread().getStackTrace()[1].getLineNumber()+")");
//			}
//        }
//		nodeMap.remove(node.getProc3());
//		nodeMap.remove(node.getEventl());
//		nodeMap.remove(node.getEventr());
//		nodeMap.remove(node.getProc4());
//        outAAlphParProc3(node);
//    }
//
//    @Override
//    public void caseALinkedParProc3(ALinkedParProc3 node)
//    {
//        inALinkedParProc3(node);
//		String a = nodeMap.get(node.getProc3()).toString();
//		String b = nodeMap.get(node.getProc4()).toString();
//        if(node.getProc3() != null)
//        {
//            node.getProc3().apply(this);
//			if(!a.equals("Proc") && !a.equals("_"))
//			{
//				throw new RuntimeException(
//				"TypeError in Typechecker at caseALinkedParProc3 (Line "
//				+Thread.currentThread().getStackTrace()[1].getLineNumber()+")");
//			}
//        }
//        if(node.getBracketL() != null)
//        {
//            node.getBracketL().apply(this);
//        }
//        if(node.getLinkComp() != null)
//        {
//            node.getLinkComp().apply(this);
//        }
//        if(node.getBracketR() != null)
//        {
//            node.getBracketR().apply(this);
//        }
//        if(node.getProc4() != null)
//        {
//            node.getProc4().apply(this);
//			if(!b.equals("Proc") && !b.equals("_"))
//			{
//				throw new RuntimeException(
//				"TypeError in Typechecker at caseALinkedParProc3 (Line "
//				+Thread.currentThread().getStackTrace()[1].getLineNumber()+")");
//			}
//        }
//		nodeMap.remove(node.getProc3());
//		nodeMap.remove(node.getProc4());
//        outALinkedParProc3(node);
//    }
//
//
//    @Override
//    public void caseAP4Proc3(AP4Proc3 node)
//    {
//        inAP4Proc3(node);
//        if(node.getProc4() != null)
//        {
//            node.getProc4().apply(this);
//			nodeMap.put(node,nodeMap.get(node.getProc4()));
//        }
//		nodeMap.remove(node.getProc4());
//        outAP4Proc3(node);
//    }
//
//    @Override
//    public void caseAIntChoiceProc4(AIntChoiceProc4 node)
//    {
//        inAIntChoiceProc4(node);
//		String a = nodeMap.get(node.getProc4()).toString();
//		String b = nodeMap.get(node.getProc5()).toString();
//        if(node.getProc4() != null)
//        {
//            node.getProc4().apply(this);
//			if(!a.equals("Proc") && !a.equals("_"))
//			{
//				throw new RuntimeException(
//				"TypeError in Typechecker at caseAIntChoiceProc4 (Line "
//				+Thread.currentThread().getStackTrace()[1].getLineNumber()+")");
//			}
//        }
//        if(node.getIChoice() != null)
//        {
//            node.getIChoice().apply(this);
//        }
//        if(node.getProc5() != null)
//        {
//            node.getProc5().apply(this);
//			if(!b.equals("Proc") && !b.equals("_"))
//			{
//				throw new RuntimeException(
//				"TypeError in Typechecker at caseAIntChoiceProc4 (Line "
//				+Thread.currentThread().getStackTrace()[1].getLineNumber()+")");
//			}
//        }
//		nodeMap.remove(node.getProc4());
//		nodeMap.remove(node.getProc5());
//        outAIntChoiceProc4(node);
//    }
//
//    @Override
//    public void caseAP5Proc4(AP5Proc4 node)
//    {
//        inAP5Proc4(node);
//        if(node.getProc5() != null)
//        {
//            node.getProc5().apply(this);
//			nodeMap.put(node,nodeMap.get(node.getProc5()));
//        }
//		nodeMap.remove(node.getProc5());
//        outAP5Proc4(node);
//    }
//
//    @Override
//    public void caseAExtChoiceProc5(AExtChoiceProc5 node)
//    {
//        inAExtChoiceProc5(node);
//		String a = nodeMap.get(node.getProc5()).toString();
//		String b = nodeMap.get(node.getProc6()).toString();
//        if(node.getProc5() != null)
//        {
//            node.getProc5().apply(this);
//			if(!b.equals("Proc") && !b.equals("_"))
//			{
//				throw new RuntimeException(
//				"TypeError in Typechecker at caseAExtChoiceProc5 (Line "
//				+Thread.currentThread().getStackTrace()[1].getLineNumber()+")");
//			}
//        }
//        if(node.getEChoice() != null)
//        {
//            node.getEChoice().apply(this);
//        }
//        if(node.getProc6() != null)
//        {
//            node.getProc6().apply(this);
//			if(!b.equals("Proc") && !b.equals("_"))
//			{
//				throw new RuntimeException(
//				"TypeError in Typechecker at caseAExtChoiceProc5 (Line "
//				+Thread.currentThread().getStackTrace()[1].getLineNumber()+")");
//			}
//        }
//		nodeMap.remove(node.getProc5());
//		nodeMap.remove(node.getProc6());
//        outAExtChoiceProc5(node);
//    }
//
//    @Override
//    public void caseASyncExtProc5(ASyncExtProc5 node)
//    {
//        inASyncExtProc5(node);
//		String a = nodeMap.get(node.getProc5()).toString();
//		String b = nodeMap.get(node.getEvent()).toString();
//		String c = nodeMap.get(node.getProc6()).toString();
//        if(node.getProc5() != null)
//        {
//            node.getProc5().apply(this);
//			if(!a.equals("Proc") && !a.equals("_"))
//			{
//				throw new RuntimeException(
//				"TypeError in Typechecker at caseASyncExtProc5 (Line "
//				+Thread.currentThread().getStackTrace()[1].getLineNumber()+")");
//			}
//        }
//        if(node.getSyncParL() != null)
//        {
//            node.getSyncParL().apply(this);
//        }
//        if(node.getEvent() != null)
//        {
//            node.getEvent().apply(this);
//			if(!b.equals("Event") && !b.equals("_"))
//			{
//				throw new RuntimeException(
//				"TypeError in Typechecker at caseASyncExtProc5 (Line "
//				+Thread.currentThread().getStackTrace()[1].getLineNumber()+")");
//			}
//        }
//        if(node.getSyncParR() != null)
//        {
//            node.getSyncParR().apply(this);
//        }
//        if(node.getProc6() != null)
//        {
//            node.getProc6().apply(this);
//			if(!c.equals("Proc") && !c.equals("_"))
//			{
//				throw new RuntimeException(
//				"TypeError in Typechecker at caseASyncExtProc5 (Line "
//				+Thread.currentThread().getStackTrace()[1].getLineNumber()+")");
//			}
//        }
//		nodeMap.remove(node.getProc5());
//		nodeMap.remove(node.getEvent());
//		nodeMap.remove(node.getProc6());
//        outASyncExtProc5(node);
//    }
//
//    @Override
//    public void caseAP6Proc5(AP6Proc5 node)
//    {
//        inAP6Proc5(node);
//        if(node.getProc6() != null)
//        {
//            node.getProc6().apply(this);
//			nodeMap.put(node,nodeMap.get(node.getProc6()));
//        }
//		nodeMap.remove(node.getProc6());
//        outAP6Proc5(node);
//    }
//
//    @Override
//    public void caseAInterruptProc6(AInterruptProc6 node)
//    {
//        inAInterruptProc6(node);
//		String a = nodeMap.get(node.getProc6()).toString();
//		String b = nodeMap.get(node.getProc7()).toString();
//        if(node.getProc6() != null)
//        {
//            node.getProc6().apply(this);
//			if(!a.equals("Proc") && !a.equals("_"))
//			{
//				throw new RuntimeException(
//				"TypeError in Typechecker at caseAInterruptProc6 (Line "
//				+Thread.currentThread().getStackTrace()[1].getLineNumber()+")");
//			}
//        }
//        if(node.getInterrupt() != null)
//        {
//            node.getInterrupt().apply(this);
//        }
//        if(node.getProc7() != null)
//        {
//            node.getProc7().apply(this);
//			if(!b.equals("Proc") && !b.equals("_"))
//			{
//				throw new RuntimeException(
//				"TypeError in Typechecker at caseAInterruptProc6 (Line "
//				+Thread.currentThread().getStackTrace()[1].getLineNumber()+")");
//			}
//        }
//		nodeMap.remove(node.getProc6());
//		nodeMap.remove(node.getProc7());
//        outAInterruptProc6(node);
//    }
//
//    @Override
//    public void caseASyncInterruptProc6(ASyncInterruptProc6 node)
//    {
//        inASyncInterruptProc6(node);
//		String a = nodeMap.get(node.getProc6()).toString();
//		String b = nodeMap.get(node.getEvent()).toString();
//		String c = nodeMap.get(node.getProc7()).toString();
//        if(node.getProc6() != null)
//        {
//            node.getProc6().apply(this);
//			if(!a.equals("Proc") && !a.equals("_"))
//			{
//				throw new RuntimeException(
//				"TypeError in Typechecker at caseASyncInterruptProc6 (Line "
//				+Thread.currentThread().getStackTrace()[1].getLineNumber()+")");
//			}
//        }
//        if(node.getSyncIntL() != null)
//        {
//            node.getSyncIntL().apply(this);
//        }
//        if(node.getEvent() != null)
//        {
//            node.getEvent().apply(this);
//			if(!b.equals("Event") && !b.equals("_"))
//			{
//				throw new RuntimeException(
//				"TypeError in Typechecker at caseASyncInterruptProc6 (Line "
//				+Thread.currentThread().getStackTrace()[1].getLineNumber()+")");
//			}
//        }
//        if(node.getSyncIntR() != null)
//        {
//            node.getSyncIntR().apply(this);
//        }
//        if(node.getProc7() != null)
//        {
//            node.getProc7().apply(this);
//			if(!c.equals("Proc") && !c.equals("_"))
//			{
//				throw new RuntimeException(
//				"TypeError in Typechecker at caseASyncInterruptProc6 (Line "
//				+Thread.currentThread().getStackTrace()[1].getLineNumber()+")");
//			}
//        }
//		nodeMap.remove(node.getProc6());
//		nodeMap.remove(node.getEvent());
//		nodeMap.remove(node.getProc7());
//        outASyncInterruptProc6(node);
//    }
//
//    @Override
//    public void caseAP7Proc6(AP7Proc6 node)
//    {
//        inAP7Proc6(node);
//        if(node.getProc7() != null)
//        {	
//			node.getProc7().apply(this);
//			nodeMap.put(node,nodeMap.get(node.getProc7()));
//        }
//		nodeMap.remove(node.getProc7());
//        outAP7Proc6(node);
//    }
//
//    @Override
//    public void caseASlidingChoiceProc7(ASlidingChoiceProc7 node)
//    {
//        inASlidingChoiceProc7(node);
//		String a = nodeMap.get(node.getProc7()).toString();
//		String b = nodeMap.get(node.getProc8()).toString();
//        if(node.getProc7() != null)
//        {
//            node.getProc7().apply(this);
//			if(!a.equals("Proc") && !a.equals("_"))
//			{
//				throw new RuntimeException(
//				"TypeError in Typechecker at caseASlidingChoiceProc7 (Line "
//				+Thread.currentThread().getStackTrace()[1].getLineNumber()+")");
//			}
//        }
//        if(node.getTimeout() != null)
//        {
//            node.getTimeout().apply(this);
//        }
//        if(node.getProc8() != null)
//        {
//            node.getProc8().apply(this);
//			if(!b.equals("Proc") && !b.equals("_"))
//			{
//				throw new RuntimeException(
//				"TypeError in Typechecker at caseASlidingChoiceProc7 (Line "
//				+Thread.currentThread().getStackTrace()[1].getLineNumber()+")");
//			}
//        }
//		nodeMap.remove(node.getProc7());
//		nodeMap.remove(node.getProc8());
//        outASlidingChoiceProc7(node);
//    }
//
//    @Override
//    public void caseAP8Proc7(AP8Proc7 node)
//    {
//        inAP8Proc7(node);
//        if(node.getProc8() != null)
//        {
//            node.getProc8().apply(this);
//			nodeMap.put(node,nodeMap.get(node.getProc8()));
//        }
//		nodeMap.remove(node.getProc8());
//        outAP8Proc7(node);
//    }
//
//    @Override
//    public void caseASeqCompProc8(ASeqCompProc8 node)
//    {
//        inASeqCompProc8(node);
//		String a = nodeMap.get(node.getProc8()).toString();
//		String b = nodeMap.get(node.getProc9()).toString();
//        if(node.getProc8() != null)
//        {
//            node.getProc8().apply(this);
//			if(!b.equals("Proc") && !b.equals("_"))
//			{
//				throw new RuntimeException(
//				"TypeError in Typechecker at caseASeqCompProc8 (Line "
//				+Thread.currentThread().getStackTrace()[1].getLineNumber()+")");
//			}
//        }
//        if(node.getSemicolon() != null)
//        {
//            node.getSemicolon().apply(this);
//        }
//        if(node.getProc9() != null)
//        {
//            node.getProc9().apply(this);
//			if(!b.equals("Proc") && !b.equals("_"))
//			{
//				throw new RuntimeException(
//				"TypeError in Typechecker at caseASeqCompProc8 (Line "
//				+Thread.currentThread().getStackTrace()[1].getLineNumber()+")");
//			}
//        }
//		nodeMap.remove(node.getProc8());
//		nodeMap.remove(node.getProc9());
//        outASeqCompProc8(node);
//    }
//
//    @Override
//    public void caseAP9Proc8(AP9Proc8 node)
//    {
//        inAP9Proc8(node);
//        if(node.getProc9() != null)
//        {
//            node.getProc9().apply(this);
//        	nodeMap.put(node,nodeMap.get(node.getProc9()));
//        }
//		nodeMap.remove(node.getProc9());        
//		outAP9Proc8(node);
//    }
//
//    @Override
//    public void caseAGuardExpProc9(AGuardExpProc9 node)
//    {
//        inAGuardExpProc9(node);
//		String a = nodeMap.get(node.getDotOp()).toString();
//		String b = nodeMap.get(node.getProc9()).toString();
//        if(node.getDotOp() != null)
//        {
//            node.getDotOp().apply(this);
//			if(!a.equals("Bool") && !a.equals("_"))
//			{
//				throw new RuntimeException(
//				"TypeError in Typechecker at caseAGuardExpProc9 (Line "
//				+Thread.currentThread().getStackTrace()[1].getLineNumber()+")");
//			}
//        }
//        if(node.getGuard() != null)
//        {
//            node.getGuard().apply(this);
//        }
//        if(node.getProc9() != null)
//        {
//            node.getProc9().apply(this);
//			if(!b.equals("Proc") && !b.equals("_"))
//			{
//				throw new RuntimeException(
//				"TypeError in Typechecker at caseAGuardExpProc9 (Line "
//				+Thread.currentThread().getStackTrace()[1].getLineNumber()+")");
//			}
//        }
//		nodeMap.remove(node.getDotOp());
//		nodeMap.remove(node.getProc9());
//        outAGuardExpProc9(node);
//    }
//
//    @Override
//    public void caseAPrefixProc9(APrefixProc9 node)
//    {
//        inAPrefixProc9(node);
//		String a = nodeMap.get(node.getEvent()).toString();
//		String b = nodeMap.get(node.getProc9()).toString();
//        if(node.getEvent() != null)
//        {
//            node.getEvent().apply(this);
//			if(!a.equals("Event") && !a.equals("_"))
//			{
//				throw new RuntimeException(
//				"TypeError in Typechecker at caseAPrefixProc9 (Line "
//				+Thread.currentThread().getStackTrace()[1].getLineNumber()+")");
//			}
//        }
//        if(node.getPrefix() != null)
//        {
//            node.getPrefix().apply(this);
//        }
//        if(node.getProc9() != null)
//        {
//            node.getProc9().apply(this);
//			if(!b.equals("Proc") && !b.equals("_"))
//			{
//				throw new RuntimeException(
//				"TypeError in Typechecker at caseAPrefixProc9 (Line "
//				+Thread.currentThread().getStackTrace()[1].getLineNumber()+")");
//			}
//        }
//		nodeMap.remove(node.getEvent());
//		nodeMap.remove(node.getProc9());
//        outAPrefixProc9(node);
//    }
//
//    @Override
//    public void caseALambdaTermProc9(ALambdaTermProc9 node)
//    {
//        inALambdaTermProc9(node);
//		String a = nodeMap.get(node.getProc9()).toString();
//        if(node.getBSlash() != null)
//        {
//            node.getBSlash().apply(this);
//        }
//        if(node.getPatternList() != null)
//        {
//            node.getPatternList().apply(this);
//        }
//        if(node.getAt() != null)
//        {
//            node.getAt().apply(this);
//        }
//        if(node.getProc9() != null)
//        {
//            node.getProc9().apply(this);
//			if(!a.equals("Proc") && !a.equals("_"))
//			{
//				throw new RuntimeException(
//				"TypeError in Typechecker at caseALambdaTermProc9 (Line "
//				+Thread.currentThread().getStackTrace()[1].getLineNumber()+")");
//			}
//        }
//		nodeMap.remove(node.getProc9());
//        outALambdaTermProc9(node);
//    }
//
//    @Override
//    public void caseALetWithinProc9(ALetWithinProc9 node)
//    {
//        inALetWithinProc9(node);
//		String a = nodeMap.get(node.getProc9()).toString();
//		tree.getCurrent().addLeaf();
//		
//        if(node.getLet() != null)
//        {
//            node.getLet().apply(this);
//        }
//        if(node.getDefs() != null)
//        {
//            node.getDefs().apply(this);
//        }
//        if(node.getWithin() != null)
//        {
//            node.getWithin().apply(this);
//        }
//        if(node.getProc9() != null)
//        {
//            node.getProc9().apply(this);
//			if(!a.equals("Proc") && !a.equals("_"))
//			{
//				throw new RuntimeException(
//				"TypeError in Typechecker at caseALetWithinProc9 (Line "
//				+Thread.currentThread().getStackTrace()[1].getLineNumber()+")");
//			}
//        }
//		tree.getCurrent().setParentAsCurrent();
//		nodeMap.remove(node.getProc9());
//        outALetWithinProc9(node);
//    }
//
//    @Override
//    public void caseAIfElseProc9(AIfElseProc9 node)
//    {
//        inAIfElseProc9(node);
//		
//		String a = nodeMap.get(node.getBoolExp()).toString();
//		String b = nodeMap.get(node.getProc1()).toString();
//		String c = nodeMap.get(node.getProc9()).toString();
//		
//        if(node.getIf() != null)
//        {
//            node.getIf().apply(this);
//        }
//        if(node.getBoolExp() != null)
//        {
//            node.getBoolExp().apply(this);
//			if(!a.equals("Bool") && !a.equals("_"))
//			{
//				throw new RuntimeException(
//				"TypeError in Typechecker at caseAIfElseProc9 (Line "
//				+Thread.currentThread().getStackTrace()[1].getLineNumber()+")");
//			}
//        }
//        if(node.getThen() != null)
//        {
//            node.getThen().apply(this);
//        }
//        if(node.getProc1() != null)
//        {
//            node.getProc1().apply(this);
//			if(!b.equals("Proc") && !b.equals("_"))
//			{
//				throw new RuntimeException(
//				"TypeError in Typechecker at caseAIfElseProc9 (Line "
//				+Thread.currentThread().getStackTrace()[1].getLineNumber()+")");
//			}
//        }
//        if(node.getElse() != null)
//        {
//            node.getElse().apply(this);
//        }
//        if(node.getProc9() != null)
//        {
//            node.getProc9().apply(this);
//			if(!c.equals("Proc") && !c.equals("_"))
//			{
//				throw new RuntimeException(
//				"TypeError in Typechecker at caseAIfElseProc9 (Line "
//				+Thread.currentThread().getStackTrace()[1].getLineNumber()+")");
//			}
//        }
//		nodeMap.remove(node.getBoolExp());
//		nodeMap.remove(node.getProc1());
//		nodeMap.remove(node.getProc9());
//        outAIfElseProc9(node);
//    }
//
//    @Override
//    public void caseARepProc9(ARepProc9 node)
//    {
//        inARepProc9(node);
//		String a = nodeMap.get(node.getProc9()).toString();
//        if(node.getRep() != null)
//        {
//            node.getRep().apply(this);
//        }
//        if(node.getProc9() != null)
//        {
//            node.getProc9().apply(this);
//			if(!a.equals("Proc") && !a.equals("_"))
//			{
//				throw new RuntimeException(
//				"TypeError in Typechecker at caseARepProc9 (Line "
//				+Thread.currentThread().getStackTrace()[1].getLineNumber()+")");
//			}
//        }
//		nodeMap.remove(node.getProc9());
//        outARepProc9(node);
//    }
//
//    @Override
//    public void caseAP10Proc9(AP10Proc9 node)
//    {
//        inAP10Proc9(node);
//        if(node.getProc10() != null)
//        {
//            node.getProc10().apply(this);
//			nodeMap.put(node,nodeMap.get(node.getProc10()));
//        }
//		nodeMap.remove(node.getProc10());
//        outAP10Proc9(node);
//    }
//
//    @Override
//    public void caseARenamingProc10(ARenamingProc10 node)
//    {
//        inARenamingProc10(node);
//		String a = nodeMap.get(node.getProc10()).toString();
//        if(node.getProc10() != null)
//        {
//            node.getProc10().apply(this);
//			if(!a.equals("Proc") && !a.equals("_"))
//			{
//				throw new RuntimeException(
//				"TypeError in Typechecker at caseARenamingProc10 (Line "
//				+Thread.currentThread().getStackTrace()[1].getLineNumber()+")");
//			}
//        }
//        if(node.getDbracketL() != null)
//        {
//            node.getDbracketL().apply(this);
//        }
//        if(node.getRenameComp() != null)
//        {
//            node.getRenameComp().apply(this);
//        }
//        if(node.getDbracketR() != null)
//        {
//            node.getDbracketR().apply(this);
//        }
//		nodeMap.remove(node.getProc10());
//        outARenamingProc10(node);
//    }
//
//    @Override
//    public void caseAERenamingProc10(AERenamingProc10 node)
//    {
//        inAERenamingProc10(node);
//		String a = nodeMap.get(node.getProc10()).toString();
//        if(node.getProc10() != null)
//        {
//            node.getProc10().apply(this);
//			if(!a.equals("Proc") && !a.equals("_"))
//			{
//				throw new RuntimeException(
//				"TypeError in Typechecker at caseAERenamingProc10 (Line "
//				+Thread.currentThread().getStackTrace()[1].getLineNumber()+")");
//			}
//        }
//        if(node.getDbracketL() != null)
//        {
//            node.getDbracketL().apply(this);
//        }
//        if(node.getDbracketR() != null)
//        {
//            node.getDbracketR().apply(this);
//        }
//		nodeMap.remove(node.getProc10());
//        outAERenamingProc10(node);
//    }
//
//    @Override
//    public void caseAEventProc10(AEventProc10 node)
//    {
//        inAEventProc10(node);
//        if(node.getEvent() != null)
//        {
//            node.getEvent().apply(this);
//			nodeMap.put(node,nodeMap.get(node.getEvent()));
//        }
//		nodeMap.remove(node.getEvent());
//        outAEventProc10(node);
//    }
//	
////***************************************************************************************
////Non-Proc-Expressions
//
//    @Override
//    public void caseAEventEvent(AEventEvent node)
//    {
//        inAEventEvent(node);
//        if(node.getDpattern() != null)
//        {
//            node.getDpattern().apply(this);
//        }
//        {
//            List<PField1> copy = new ArrayList<PField1>(node.getField1());
//            for(PField1 e : copy)
//            {
//                e.apply(this);
//            }
//        }
//        {
//            List<PField2> copy = new ArrayList<PField2>(node.getField2());
//            for(PField2 e : copy)
//            {
//                e.apply(this);
//            }
//        }
//        outAEventEvent(node);
//    }
//
//    @Override
//    public void caseANondetRestField1(ANondetRestField1 node)
//    {
//        inANondetRestField1(node);
//        if(node.getDollar() != null)
//        {
//            node.getDollar().apply(this);
//        }
//        if(node.getPattern1() != null)
//        {
//            node.getPattern1().apply(this);
//        }
//        outANondetRestField1(node);
//    }
//
//    @Override
//    public void caseAInputRestField2(AInputRestField2 node)
//    {
//        inAInputRestField2(node);
//        if(node.getQMark() != null)
//        {
//            node.getQMark().apply(this);
//        }
//        if(node.getPattern1() != null)
//        {
//            node.getPattern1().apply(this);
//        }
//        outAInputRestField2(node);
//    }
//
//    @Override
//    public void caseAOutputField2(AOutputField2 node)
//    {
//        inAOutputField2(node);
//        if(node.getExclMark() != null)
//        {
//            node.getExclMark().apply(this);
//        }
//        if(node.getDotOp() != null)
//        {
//            node.getDotOp().apply(this);
//        }
//        outAOutputField2(node);
//    }
//
//    @Override
//    public void caseADpatternDpattern(ADpatternDpattern node)
//    {
//        inADpatternDpattern(node);
//        if(node.getDpattern() != null)
//        {
//            node.getDpattern().apply(this);
//        }
//        if(node.getDoubleat() != null)
//        {
//            node.getDoubleat().apply(this);
//        }
//        if(node.getDotOp() != null)
//        {
//            node.getDotOp().apply(this);
//        }
//        outADpatternDpattern(node);
//    }
//
//    @Override
//    public void caseADotopDpattern(ADotopDpattern node)
//    {
//        inADotopDpattern(node);
//        if(node.getDotOp() != null)
//        {
//            node.getDotOp().apply(this);
//        }
//        outADotopDpattern(node);
//    }
//
//    @Override
//    public void caseADotDotOp(ADotDotOp node)
//    {
//        inADotDotOp(node);
//        if(node.getDotOp() != null)
//        {
//            node.getDotOp().apply(this);
//        }
//        if(node.getDot() != null)
//        {
//            node.getDot().apply(this);
//        }
//        if(node.getBoolExp() != null)
//        {
//            node.getBoolExp().apply(this);
//        }
//        outADotDotOp(node);
//    }
//
//    @Override
//    public void caseASsDotOp(ASsDotOp node)
//    {
//        inASsDotOp(node);
//        if(node.getBoolExp() != null)
//        {
//            node.getBoolExp().apply(this);
//        }
//        outASsDotOp(node);
//    }
////***************************************************************************************
////Boolean Expressions
//    @Override
//    public void caseAOrBoolExp(AOrBoolExp node)
//    {
//        inAOrBoolExp(node);
//		String a = "";
//		String b = "";
//        if(node.getBoolExp() != null)
//        {
//            node.getBoolExp().apply(this);
//			a = nodeMap.get(node.getBoolExp()).toString();
//			if(!a.equals("Bool"))
//			{
//				throw new RuntimeException("Incorrect types in Line "
//							+node.getOr().getLine());
//			}
//        }
//        if(node.getOr() != null)
//        {
//            node.getOr().apply(this);
//        }
//        if(node.getBoolExp2() != null)
//        {
//            node.getBoolExp2().apply(this);
//			b = nodeMap.get(node.getBoolExp2()).toString();
//			if(!b.equals("Bool"))
//			{
//				throw new RuntimeException("Incorrect types in Line "
//							+node.getOr().getLine());
//			}
//        }
//		nodeMap.put(node,"Bool");
//		nodeMap.remove(node.getBoolExp());
//		nodeMap.remove(node.getBoolExp2());
//        outAOrBoolExp(node);
//    }
//
//
//    @Override
//    public void caseABoolExp2BoolExp(ABoolExp2BoolExp node)
//    {
//        inABoolExp2BoolExp(node);
//        if(node.getBoolExp2() != null)
//        {
//            node.getBoolExp2().apply(this);
//			nodeMap.put(node,nodeMap.get(node.getBoolExp2()));
//			nodeMap.remove(node.getBoolExp2());
//        }
//        outABoolExp2BoolExp(node);
//    }
//
//
//    @Override
//    public void caseAAndBoolExp2(AAndBoolExp2 node)
//    {
//        inAAndBoolExp2(node);
//		String a = "";
//		String b = "";
//        if(node.getBoolExp2() != null)
//        {
//            node.getBoolExp2().apply(this);
//			a = nodeMap.get(node.getBoolExp2()).toString();
//			if(!(a.equals("Bool")))
//			{
//				throw new RuntimeException("Incorrect types in Line "
//							+node.getAnd().getLine()+", Column "
//							+node.getAnd().getPos());
//			}
//        }
//        if(node.getAnd() != null)
//        {
//            node.getAnd().apply(this);
//        }
//        if(node.getBoolExp3() != null)
//        {
//            node.getBoolExp3().apply(this);
//			b = nodeMap.get(node.getBoolExp3()).toString();
//			if(!(b.equals("Bool")))
//			{
//				throw new RuntimeException("Incorrect types in Line "
//							+node.getAnd().getLine()+", Column "
//							+node.getAnd().getPos());
//			}
//        }
//		nodeMap.put(node,"Bool");
//		nodeMap.remove(node.getBoolExp2());
//		nodeMap.remove(node.getBoolExp3());
//        outAAndBoolExp2(node);
//    }
//
//    @Override
//    public void caseABoolExp3BoolExp2(ABoolExp3BoolExp2 node)
//    {
//        inABoolExp3BoolExp2(node);
//        if(node.getBoolExp3() != null)
//        {
//            node.getBoolExp3().apply(this);
//			nodeMap.put(node,nodeMap.get(node.getBoolExp3()));
//			nodeMap.remove(node.getBoolExp3());
//        }
//        outABoolExp3BoolExp2(node);
//    }
//
//	@Override
//    public void caseANotBoolExp3(ANotBoolExp3 node)
//    {
//        inANotBoolExp3(node);
//        if(node.getNot() != null)
//        {
//            node.getNot().apply(this);
//        }
//        if(node.getBoolExp3() != null)
//        {
//            node.getBoolExp3().apply(this);
//			String a = nodeMap.get(node.getBoolExp3()).toString();
//			if(!(a.equals("Bool")))
//			{
//				throw new RuntimeException("Incorrect types in Line "
//							+node.getNot().getLine());
//			}
//        }
//		nodeMap.put(node,"Bool");
//		nodeMap.remove(node.getBoolExp3());
//        outANotBoolExp3(node);
//    }
//
//    @Override
//    public void caseABoolExp4BoolExp3(ABoolExp4BoolExp3 node)
//    {
//        inABoolExp4BoolExp3(node);
//        if(node.getBoolExp4() != null)
//        {
//            node.getBoolExp4().apply(this);
//			nodeMap.put(node,nodeMap.get(node.getBoolExp4()));
//			nodeMap.remove(node.getBoolExp4());
//        }
//        outABoolExp4BoolExp3(node);
//    }
//
//    @Override
//    public void caseAEqualBoolExp4(AEqualBoolExp4 node)
//    {
//        inAEqualBoolExp4(node);
//        if(node.getBoolExp4() != null)
//        {
//            node.getBoolExp4().apply(this);
//			if(nodeMap.get(node.getBoolExp4()).toString().equals("proc"))
//			{
//				throw new RuntimeException("Incorrect types in Line "
//							+node.getEqual().getLine());
//			}
//        }
//        if(node.getEqual() != null)
//        {
//            node.getEqual().apply(this);
//        }
//        if(node.getValExp() != null)
//        {
//            node.getValExp().apply(this);
//			if(nodeMap.get(node.getValExp()).toString().equals("proc"))
//			{
//				throw new RuntimeException("TypeError: Everything but 'proc' supported.");
//			}
//        }
//		String a = nodeMap.get(node.getValExp()).toString();
//		String b = nodeMap.get(node.getBoolExp4()).toString();
//		if(!(b.equals(a)))
//		{
//			throw new RuntimeException("Incorrect types in Line "
//							+node.getEqual().getLine());
//		}
//		nodeMap.put(node, "Bool");
//		nodeMap.remove(node.getValExp());
//		nodeMap.remove(node.getBoolExp4());
//        outAEqualBoolExp4(node);
//    }
//	
//    @Override
//    public void caseAOrderingLgeBoolExp4(AOrderingLgeBoolExp4 node)
//    {
//        inAOrderingLgeBoolExp4(node);
//		String help = "";
//        if(node.getBoolExp4() != null)
//        {
//            node.getBoolExp4().apply(this);
//			help = nodeMap.get(node.getBoolExp4()).toString();
//			if(!(help.startsWith("(") || help.startsWith("<") || help.startsWith("{")
//				|| help.equals("Int") || help.equals("Char")))
//			{
//				throw new RuntimeException("Incorrect types in Line "
//							+node.getLge().getLine());
//			}
//        }
//        if(node.getLge() != null)
//        {
//            node.getLge().apply(this);
//        }
//        if(node.getValExp() != null)
//        {
//            node.getValExp().apply(this);
//			help = nodeMap.get(node.getValExp()).toString();
//			if(!(help.startsWith("(") || help.startsWith("<") || help.startsWith("{")
//				|| help.equals("Int") || help.equals("Char")))
//			{
//				throw new RuntimeException("Incorrect types in Line "
//							+node.getLge().getLine());
//			}
//        }
//		String a = nodeMap.get(node.getValExp()).toString();
//		String b = nodeMap.get(node.getBoolExp4()).toString();
//		if(!(b.equals(a)))
//		{
//			throw new RuntimeException("Incorrect types in Line "
//							+node.getLge().getLine());
//		}
//		nodeMap.put(node,"Bool");
//		nodeMap.remove(node.getValExp());
//		nodeMap.remove(node.getBoolExp4());
//        outAOrderingLgeBoolExp4(node);
//    }
//	
//    @Override
//    public void caseAOrderingLessBoolExp4(AOrderingLessBoolExp4 node)
//    {
//        inAOrderingLessBoolExp4(node);
//		String help = "";
//        if(node.getBoolExp4() != null)
//        {
//            node.getBoolExp4().apply(this);
//			help = nodeMap.get(node.getBoolExp4()).toString();	
//		//	System.out.println(help);
//			if(!(help.startsWith("(") || help.startsWith("<") || help.startsWith("{")
//				|| help.equals("Int") || help.equals("Char")))
//			{
//				throw new RuntimeException("Incorrect types in Line "
//							+node.getLess().getLine());
//			}
//        }
//        if(node.getLess() != null)
//        {
//            node.getLess().apply(this);
//        }
//        if(node.getValExp() != null)
//        {
//            node.getValExp().apply(this);
//			help = nodeMap.get(node.getValExp()).toString();			
//			if(!(help.startsWith("(") || help.startsWith("<") || help.startsWith("{")
//				|| help.equals("Int") || help.equals("Char")))
//			{
//				throw new RuntimeException("Incorrect types in Line "
//							+node.getLess().getLine());
//			}
//        }
//		String a = nodeMap.get(node.getValExp()).toString();
//		String b = nodeMap.get(node.getBoolExp4()).toString();
//		if(!(b.equals(a)))
//		{
//			throw new RuntimeException("Incorrect types in Line "
//							+node.getLess().getLine());
//		}
//		nodeMap.put(node,"Bool");
//		nodeMap.remove(node.getValExp());
//		nodeMap.remove(node.getBoolExp4());
//        outAOrderingLessBoolExp4(node);
//    }
//
//
//    @Override
//    public void caseAOrderingGreaterBoolExp4(AOrderingGreaterBoolExp4 node)
//    {
//        inAOrderingGreaterBoolExp4(node);
//		String help = "";
//        if(node.getBoolExp4() != null)
//        {
//            node.getBoolExp4().apply(this);	
//			help = nodeMap.get(node.getBoolExp4()).toString();			
//			if(!(help.startsWith("(") || help.startsWith("<") || help.startsWith("{")
//				|| help.equals("Int") || help.equals("Char")))
//			{
//				throw new RuntimeException("Incorrect types in Line "
//							+node.getGreater().getLine());
//			}
//        }
//        if(node.getGreater() != null)
//        {
//            node.getGreater().apply(this);
//        }
//        if(node.getValExp() != null)
//        {
//            node.getValExp().apply(this);
//			help = nodeMap.get(node.getValExp()).toString();
//			if(!(help.startsWith("(") || help.startsWith("<") || help.startsWith("{")
//				|| help.equals("Int") || help.equals("Char")))
//			{
//				throw new RuntimeException("TypeError, expecting:"+
//				"(|a=>b|), <a>, {a}, Int, Char, at: "+node.toString());
//			}
//        }
//		String a = nodeMap.get(node.getValExp()).toString();
//		String b = nodeMap.get(node.getBoolExp4()).toString();
//		if(!(b.equals(a)))
//		{
//			throw new RuntimeException("Incorrect types in Line "
//							+node.getGreater().getLine());
//		}
//		nodeMap.put(node,"Bool");
//		nodeMap.remove(node.getValExp());
//		nodeMap.remove(node.getBoolExp4());
//        outAOrderingGreaterBoolExp4(node);
//    }
//
//
//    @Override
//    public void caseAValExpBoolExp4(AValExpBoolExp4 node)
//    {
//        inAValExpBoolExp4(node);
//        if(node.getValExp() != null)
//        {
//            node.getValExp().apply(this);
//			nodeMap.put(node, nodeMap.get(node.getValExp()));
//			nodeMap.remove(node.getValExp());
//        }
//        outAValExpBoolExp4(node);
//    }
//	
//    @Override
//    public void caseAAdditionValExp(AAdditionValExp node)
//    {
//        inAAdditionValExp(node);
//        if(node.getValExp() != null)
//        {
//            node.getValExp().apply(this);
//			if(!(nodeMap.get(node.getValExp()).toString().equals("Int")))
//			{
//				throw new RuntimeException("Incorrect types in Line "
//							+node.getPlus().getLine());
//			}
//        }
//        if(node.getPlus() != null)
//        {
//            node.getPlus().apply(this);
//        }
//        if(node.getValExp1() != null)
//        {
//            node.getValExp1().apply(this);
//			if(!(nodeMap.get(node.getValExp1()).toString().equals("Int")))
//			{
//				throw new RuntimeException("Incorrect types in Line "
//							+node.getPlus().getLine());
//			}
//        }
//		nodeMap.put(node,"Int");
//		nodeMap.remove(node.getValExp());
//		nodeMap.remove(node.getValExp1());
//        outAAdditionValExp(node);
//    }
//
//
//    @Override
//    public void caseASubtractionValExp(ASubtractionValExp node)
//    {
//        inASubtractionValExp(node);
//        if(node.getValExp() != null)
//        {
//            node.getValExp().apply(this);
//			if(!nodeMap.get(node.getValExp()).toString().equals("Int"))
//			{
//				throw new RuntimeException("TypeError, expecting: 'Int' at: "+node.toString());
//			}
//        }
//        if(node.getMinus() != null)
//        {
//            node.getMinus().apply(this);
//        }
//        if(node.getValExp1() != null)
//        {
//            node.getValExp1().apply(this);
//			if(!nodeMap.get(node.getValExp1()).toString().equals("Int"))
//			{
//				throw new RuntimeException("Incorrect types in Line "
//							+node.getMinus().getLine());
//			}
//        }
//		nodeMap.put(node,"Int");
//		nodeMap.remove(node.getValExp());
//		nodeMap.remove(node.getValExp1());
//        outASubtractionValExp(node);
//    }
//
//
//    @Override
//    public void caseAValExp1ValExp(AValExp1ValExp node)
//    {
//        inAValExp1ValExp(node);
//        if(node.getValExp1() != null)
//        {
//            node.getValExp1().apply(this);
//			nodeMap.put(node, nodeMap.get(node.getValExp1()));
//			nodeMap.remove(node.getValExp1());
//        }
//        outAValExp1ValExp(node);
//    }
//
//
//    @Override
//    public void caseAMultiplicationValExp1(AMultiplicationValExp1 node)
//    {
//        inAMultiplicationValExp1(node);
//        if(node.getValExp1() != null)
//        {
//            node.getValExp1().apply(this);
//			if(!nodeMap.get(node.getValExp1()).toString().equals("Int"))
//			{
//				throw new RuntimeException("Incorrect types in Line "
//							+node.getMulDivMod().getLine());
//			}
//        }
//        if(node.getMulDivMod() != null)
//        {
//            node.getMulDivMod().apply(this);
//        }
//        if(node.getValExp2() != null)
//        {
//            node.getValExp2().apply(this);		
//			if(!nodeMap.get(node.getValExp1()).toString().equals("Int"))
//			{
//				throw new RuntimeException("Incorrect types in Line "
//							+node.getMulDivMod().getLine());
//			}
//        }
//		nodeMap.put(node,"Int");
//		nodeMap.remove(node.getValExp2());
//		nodeMap.remove(node.getValExp1());
//        outAMultiplicationValExp1(node);
//    }
//	
//    @Override
//    public void caseAValExp2ValExp1(AValExp2ValExp1 node)
//    {
//        inAValExp2ValExp1(node);
//        if(node.getValExp2() != null)
//        {
//            node.getValExp2().apply(this);
//			nodeMap.put(node,nodeMap.get(node.getValExp2()));
//			nodeMap.remove(node.getValExp2());
//        }
//        outAValExp2ValExp1(node);
//    }
//
//
//    @Override
//    public void caseAUnMinusValExp2(AUnMinusValExp2 node)
//    {
//        inAUnMinusValExp2(node);
//        if(node.getMinus() != null)
//        {
//            node.getMinus().apply(this);
//        }
//        if(node.getValExp2() != null)
//        {
//            node.getValExp2().apply(this);
//			if(nodeMap.get(node.getValExp2()).equals("Int"))
//			{
//				nodeMap.put(node, "Int");
//				nodeMap.remove(node.getValExp2());
//			}
//			else
//			{
//				throw new RuntimeException("Incorrect types in Line "
//							+node.getMinus().getLine());
//			}
//        }
//        outAUnMinusValExp2(node);
//    }
//
//
//    @Override
//    public void caseASequence0ValExp2(ASequence0ValExp2 node)
//    {
//        inASequence0ValExp2(node);
//        if(node.getSequence0() != null)
//        {
//            node.getSequence0().apply(this);
//			nodeMap.put(node, nodeMap.get(node.getSequence0()));
//			nodeMap.remove(node.getSequence0());
//        }
//        outASequence0ValExp2(node);
//    }
//	
//    @Override
//    public void caseALenSequence0(ALenSequence0 node)
//    {
//        inALenSequence0(node);
//        if(node.getHash() != null)
//        {
//            node.getHash().apply(this);
//        }
//        if(node.getSequence0() != null)
//        {
//            node.getSequence0().apply(this);
//			if(nodeMap.get(node.getSequence0()).toString().startsWith("<"))
//			{
//				nodeMap.put(node, "Int");
//				nodeMap.remove(node.getSequence0());
//			}
//			else
//			{
//				throw new RuntimeException("Incorrect types in Line "
//							+node.getHash().getLine());
//			}
//        }
//        outALenSequence0(node);
//    }
//
//
//    @Override
//    public void caseASequence1Sequence0(ASequence1Sequence0 node)
//    {
//        inASequence1Sequence0(node);
//        if(node.getSequence1() != null)
//        {
//            node.getSequence1().apply(this);
//			nodeMap.put(node, nodeMap.get(node.getSequence1()));
//			nodeMap.remove(node.getSequence1());
//        }
//        outASequence1Sequence0(node);
//    }
//
//
//    @Override
//    public void caseACatSequence1(ACatSequence1 node)
//    {
//        inACatSequence1(node);
//		String a = "";
//		String b = "";
//        if(node.getSequence1() != null)
//        {
//            node.getSequence1().apply(this);
//			a = nodeMap.get(node.getAtom()).toString();
//			if(!(a.startsWith("<")))
//			{
//				throw new RuntimeException("Incorrect types in Line "
//							+node.getCat().getLine());
//			}		
//        }
//        if(node.getCat() != null)
//        {
//            node.getCat().apply(this);
//        }
//        if(node.getAtom() != null)
//        {
//            node.getAtom().apply(this);
//			b = nodeMap.get(node.getSequence1()).toString();
//			if(!(b.startsWith("<")))
//			{
//				throw new RuntimeException("Incorrect types in Line "
//							+node.getCat().getLine());
//			}
//
//        }
//		if(a.equals(b))
//		{
//			nodeMap.put(node, nodeMap.get(node.getAtom()));
//			nodeMap.remove(node.getSequence1());
//			nodeMap.remove(node.getAtom());
//		}
//		else
//		{
//				throw new RuntimeException("Incorrect types in Line "
//							+node.getCat().getLine());
//				
//		}
//        outACatSequence1(node);
//    }
//
//	@Override
//    public void caseAAtomSequence1(AAtomSequence1 node)
//    {
//        inAAtomSequence1(node);
//        if(node.getAtom() != null)
//        {
//            node.getAtom().apply(this);
//			nodeMap.put(node,nodeMap.get(node.getAtom()));
//			nodeMap.remove(node.getAtom());
//        }
//        outAAtomSequence1(node);
//    }
//
//
//
////***************************************************************************************
////Sets
//
//    @Override
//    public void caseAEmptySetSet(AEmptySetSet node)
//    {
//        inAEmptySetSet(node);
//        if(node.getBraceL() != null)
//        {
//            node.getBraceL().apply(this);
//        }
//        if(node.getBraceR() != null)
//        {
//            node.getBraceR().apply(this);
//        }
//		nodeMap.put(node, "{}");
//        outAEmptySetSet(node);
//    }
//
//    @Override
//    public void caseASetSet(ASetSet node)
//    {
//        inASetSet(node);
//        if(node.getBraceL() != null)
//        {
//            node.getBraceL().apply(this);
//        }
//        if(node.getArguments() != null)
//        {
//            node.getArguments().apply(this); //Baustelle
//        }
//        if(node.getBraceR() != null)
//        {
//            node.getBraceR().apply(this);
//        }
//		nodeMap.put(node,"{"+arguments.get(argDepth+1).get(0)+"}");
//		if(argDepth<0)
//		{
//			arguments.clear();
//		}
//        outASetSet(node);
//    }
//
//    @Override
//    public void caseAClosedRangeSet(AClosedRangeSet node)
//    {
//        inAClosedRangeSet(node);
//        if(node.getBraceL() != null)
//        {
//            node.getBraceL().apply(this);
//        }
//        if(node.getL() != null)
//        {
//            node.getL().apply(this);
//			if(!(nodeMap.get(node.getL()).toString().equals("Int")))
//			{
//				throw new RuntimeException("Incorrect types in Line "
//							+node.getDotdot().getLine()+", Column "
//							+node.getDotdot().getPos());
//			}
//        }
//        if(node.getDotdot() != null)
//        {
//            node.getDotdot().apply(this);
//        }
//        if(node.getR() != null)
//        {
//            node.getR().apply(this);
//			if(!(nodeMap.get(node.getR()).toString().equals("Int")))
//			{
//				throw new RuntimeException("Incorrect types in Line "
//							+node.getBraceR().getLine()+", Column "
//							+node.getBraceR().getPos());
//			}
//        }
//        if(node.getBraceR() != null)
//        {
//            node.getBraceR().apply(this);
//        }
//		nodeMap.put(node,"{Int}");
//		nodeMap.remove(node.getL());
//		nodeMap.remove(node.getR());
//        outAClosedRangeSet(node);
//    }
//
//    @Override
//    public void caseAOpenRangeSet(AOpenRangeSet node)
//    {
//        inAOpenRangeSet(node);
//        if(node.getBraceL() != null)
//        {
//            node.getBraceL().apply(this);
//        }
//        if(node.getValExp() != null)
//        {
//            node.getValExp().apply(this);
//			if(!(nodeMap.get(node.getValExp()).toString().equals("Int")))
//			{
//				throw new RuntimeException("Incorrect types in Line "
//							+node.getDotdot().getLine()+", Column "
//							+node.getDotdot().getPos());
//			}
//        }
//        if(node.getDotdot() != null)
//        {
//            node.getDotdot().apply(this);
//        }
//        if(node.getBraceR() != null)
//        {
//            node.getBraceR().apply(this);
//        }
//		nodeMap.put(node,"{Int}");
//		nodeMap.remove(node.getValExp());
//        outAOpenRangeSet(node);
//    }
//	
//    @Override
//    public void caseASetComprehensionSet(ASetComprehensionSet node)
//    {
//        inASetComprehensionSet(node);
//        if(node.getBraceL() != null)
//        {
//            node.getBraceL().apply(this);
//        }
//        if(node.getArguments() != null)
//        {
//            node.getArguments().apply(this); //Baustelle	
//        }
//        if(node.getPipe() != null)
//        {
//            node.getPipe().apply(this);
//        }
//        if(node.getStmts() != null)
//        {
//            node.getStmts().apply(this);
//        }
//        if(node.getBraceR() != null)
//        {
//            node.getBraceR().apply(this);
//        }
//		nodeMap.put(node,"{"+arguments.get(argDepth+1).get(0)+"}");
//		if(argDepth<0)
//		{
//			arguments.clear();
//		}
//        outASetComprehensionSet(node);
//    }
//
//    @Override
//    public void caseARangedComprehensionSet(ARangedComprehensionSet node)
//    {
//        inARangedComprehensionSet(node);
//        if(node.getBraceL() != null)
//        {
//            node.getBraceL().apply(this);
//        }
//        if(node.getL() != null)
//        {
//            node.getL().apply(this);
//			if(!(nodeMap.get(node.getL()).toString().equals("Int")))
//			{
//				throw new RuntimeException("Incorrect types in Line "
//							+node.getDotdot().getLine()+", Column "
//							+node.getDotdot().getPos());
//			}
//        }
//        if(node.getDotdot() != null)
//        {
//            node.getDotdot().apply(this);
//        }
//        if(node.getR() != null)
//        {
//            node.getR().apply(this);
//			if(!(nodeMap.get(node.getR()).toString().equals("Int")))
//			{
//				throw new RuntimeException("Incorrect types in Line "
//							+node.getPipe().getLine()+", Column "
//							+node.getPipe().getPos());
//			}
//        }
//        if(node.getPipe() != null)
//        {
//            node.getPipe().apply(this);
//        }
//        if(node.getStmts() != null)
//        {
//            node.getStmts().apply(this);
//        }
//        if(node.getBraceR() != null)
//        {
//            node.getBraceR().apply(this);
//        }
//		nodeMap.put(node,"{Int}");
//		nodeMap.remove(node.getR());
//		nodeMap.remove(node.getL());
//        outARangedComprehensionSet(node);
//    }
//
//    @Override
//    public void caseAInfiniteComprehensionSet(AInfiniteComprehensionSet node)
//    {
//        inAInfiniteComprehensionSet(node);
//        if(node.getBraceL() != null)
//        {
//            node.getBraceL().apply(this);
//        }
//        if(node.getValExp() != null)
//        {
//            node.getValExp().apply(this);
//			if(!(nodeMap.get(node.getValExp()).toString().equals("Int")))
//			{
//				throw new RuntimeException("Incorrect types in Line "
//							+node.getDotdot().getLine()+", Column "
//							+node.getDotdot().getPos());
//			}
//        }
//        if(node.getDotdot() != null)
//        {
//            node.getDotdot().apply(this);
//        }
//        if(node.getPipe() != null)
//        {
//            node.getPipe().apply(this);
//        }
//        if(node.getStmts() != null)
//        {
//            node.getStmts().apply(this);
//        }
//        if(node.getBraceR() != null)
//        {
//            node.getBraceR().apply(this);
//        }
//		nodeMap.put(node,"{Int}");
//		nodeMap.remove(node.getValExp());
//        outAInfiniteComprehensionSet(node);
//    }
//
//    @Override
//    public void caseAEnumSetSet(AEnumSetSet node)
//    {
//        inAEnumSetSet(node);
//        if(node.getEnumSet() != null)
//        {
//            node.getEnumSet().apply(this);
//			nodeMap.put(node, nodeMap.get(node.getEnumSet()));
//			nodeMap.remove(node.getEnumSet());
//        }
//        outAEnumSetSet(node);
//    }
//
//    @Override
//    public void caseASetCompSet(ASetCompSet node)
//    {
//        inASetCompSet(node);
//        if(node.getSetComp() != null)
//        {
//            node.getSetComp().apply(this);
//			nodeMap.put(node, nodeMap.get(node.getSetComp()));
//			nodeMap.remove(node.getSetComp());
//        }
//        outASetCompSet(node);
//	}
//	
////***************************************************************************************
////Sequence
//    @Override
//    public void caseAEmptySeqSequence(AEmptySeqSequence node)
//    {
//        inAEmptySeqSequence(node);
//        if(node.getSeqOpen() != null)
//        {
//            node.getSeqOpen().apply(this);
//        }
//        if(node.getSeqClose() != null)
//        {
//            node.getSeqClose().apply(this);
//        }
//		nodeMap.put(node,"<>");
//        outAEmptySeqSequence(node);
//    }
//
//    @Override
//    public void caseAExplSeqSequence(AExplSeqSequence node)
//    {
//        inAExplSeqSequence(node);
//        if(node.getSeqOpen() != null)
//        {
//            node.getSeqOpen().apply(this);
//        }
//        if(node.getArguments() != null)
//        {
//            node.getArguments().apply(this);
//			nodeMap.put(node,"<"+arguments.get(argDepth+1).get(0)+">");
//			if(argDepth<0)
//			{
//				arguments.clear();
//		    }
//        }
//        if(node.getSeqClose() != null)
//        {
//            node.getSeqClose().apply(this);
//        }
//        outAExplSeqSequence(node);
//    }
//
//    @Override
//    public void caseAListCompSequence(AListCompSequence node)
//    {
//        inAListCompSequence(node);
//        if(node.getSeqOpen() != null)
//        {
//            node.getSeqOpen().apply(this);
//        }
//        if(node.getArguments() != null)
//        {
//            node.getArguments().apply(this);
//			nodeMap.put(node,arguments.get(argDepth+1).get(0));
//			if(argDepth<0)
//			{
//				arguments.clear();
//			}
//        }
//        if(node.getPipe() != null)
//        {
//            node.getPipe().apply(this);
//        }
//        if(node.getStmts() != null)
//        {
//            node.getStmts().apply(this);
//        }
//        if(node.getSeqClose() != null)
//        {
//            node.getSeqClose().apply(this);
//        }
//        outAListCompSequence(node);
//    }
//
//    @Override
//    public void caseACrSeqSequence(ACrSeqSequence node)
//    {
//        inACrSeqSequence(node);
//        if(node.getSeqOpen() != null)
//        {
//            node.getSeqOpen().apply(this);
//        }
//        if(node.getL() != null)
//        {
//            node.getL().apply(this);
//			if(!(nodeMap.get(node.getL()).toString().equals("Int")))
//			{
//				throw new RuntimeException("Incorrect types in Line "
//							+node.getDotdot().getLine()+", Column "
//							+node.getDotdot().getPos());
//			}
//        }
//        if(node.getDotdot() != null)
//        {
//            node.getDotdot().apply(this);
//        }
//        if(node.getR() != null)
//        {
//            node.getR().apply(this);
//			if(!(nodeMap.get(node.getR()).toString().equals("Int")))
//			{
//				throw new RuntimeException("Incorrect types in Line "
//							+node.getSeqClose().getLine()+", Column "
//							+node.getSeqClose().getPos());
//			}
//        }
//        if(node.getSeqClose() != null)
//        {
//            node.getSeqClose().apply(this);
//        }
//		nodeMap.put(node,"<Int>");
//		nodeMap.remove(node.getR());
//		nodeMap.remove(node.getL());
//        outACrSeqSequence(node);
//    }
//
//    @Override
//    public void caseARanCompSequence(ARanCompSequence node)
//    {
//        inARanCompSequence(node);
//        if(node.getSeqOpen() != null)
//        {
//            node.getSeqOpen().apply(this);
//        }
//        if(node.getL() != null)
//        {
//            node.getL().apply(this);
//			if(!(nodeMap.get(node.getL()).toString().equals("Int")))
//			{
//				throw new RuntimeException("Incorrect types in Line "
//							+node.getDotdot().getLine()+", Column "
//							+node.getDotdot().getPos());
//			}
//        }
//        if(node.getDotdot() != null)
//        {
//            node.getDotdot().apply(this);
//        }
//        if(node.getR() != null)
//        {
//            node.getR().apply(this);
//			if(!(nodeMap.get(node.getR()).toString().equals("Int")))
//			{
//				throw new RuntimeException("Incorrect types in Line "
//							+node.getPipe().getLine()+", Column "
//							+node.getPipe().getPos());
//			}
//        }
//        if(node.getPipe() != null)
//        {
//            node.getPipe().apply(this);
//        }
//        if(node.getStmts() != null)
//        {
//            node.getStmts().apply(this);
//        }
//        if(node.getSeqClose() != null)
//        {
//            node.getSeqClose().apply(this);
//        }
//		nodeMap.put(node,"<Int>");
//		nodeMap.remove(node.getR());
//		nodeMap.remove(node.getL());
//        outARanCompSequence(node);
//    }
//
//    @Override
//    public void caseAOrSeqSequence(AOrSeqSequence node)
//    {
//        inAOrSeqSequence(node);
//        if(node.getSeqOpen() != null)
//        {
//            node.getSeqOpen().apply(this);
//        }
//        if(node.getValExp() != null)
//        {
//            node.getValExp().apply(this);
//			if(!(nodeMap.get(node.getValExp()).toString().equals("Int")))
//			{
//				throw new RuntimeException("Incorrect types in Line "
//							+node.getDotdot().getLine()+", Column "
//							+node.getDotdot().getPos());
//			}
//        }
//        if(node.getDotdot() != null)
//        {
//            node.getDotdot().apply(this);
//        }
//        if(node.getSeqClose() != null)
//        {
//            node.getSeqClose().apply(this);
//        }
//		nodeMap.put(node,"<Int>");
//		nodeMap.remove(node.getValExp());
//        outAOrSeqSequence(node);
//    }
//
//    @Override
//    public void caseAInfComprSequence(AInfComprSequence node)
//    {
//        inAInfComprSequence(node);
//        if(node.getSeqOpen() != null)
//        {
//            node.getSeqOpen().apply(this);
//        }
//        if(node.getValExp() != null)
//        {
//            node.getValExp().apply(this);
//			if(!(nodeMap.get(node.getValExp()).toString().equals("Int")))
//			{
//				throw new RuntimeException("Incorrect types in Line "
//							+node.getDotdot().getLine()+", Column "
//							+node.getDotdot().getPos());
//			}
//        }
//        if(node.getDotdot() != null)
//        {
//            node.getDotdot().apply(this);
//        }
//        if(node.getPipe() != null)
//        {
//            node.getPipe().apply(this);
//        }
//        if(node.getStmts() != null)
//        {
//            node.getStmts().apply(this);
//        }
//        if(node.getSeqClose() != null)
//        {
//            node.getSeqClose().apply(this);
//        }
//		nodeMap.put(node,"<Int>");
//		nodeMap.remove(node.getValExp());
//        outAInfComprSequence(node);
//    }
//
//    @Override
//    public void caseAEnumSeqSequence(AEnumSeqSequence node)
//    {
//        inAEnumSeqSequence(node);
//        if(node.getEnumSeq() != null)
//        {
//            node.getEnumSeq().apply(this); //Baustelle
//        }
//        outAEnumSeqSequence(node);
//    }
//
//    @Override
//    public void caseASeqCompSequence(ASeqCompSequence node)
//    {
//        inASeqCompSequence(node);
//        if(node.getSeqComp() != null)
//        {
//            node.getSeqComp().apply(this);
//			nodeMap.put(node,"<Int>");
//			nodeMap.remove(node.getSeqComp());
//        }
//        outASeqCompSequence(node);
//    }
//
//	
//    @Override
//    public void caseAEnumSeq(AEnumSeq node)
//    {
//        inAEnumSeq(node);
//        if(node.getTriaL() != null)
//        {
//            node.getTriaL().apply(this);
//        }
//        if(node.getL() != null)
//        {
//            node.getL().apply(this);
//        }
//        if(node.getArguments() != null)
//        {
//            node.getArguments().apply(this);
//        }
//        if(node.getR() != null)
//        {
//            node.getR().apply(this);
//        }
//        if(node.getTriaR() != null)
//        {
//            node.getTriaR().apply(this);
//        }
//        outAEnumSeq(node);
//    }
//
//    @Override
//    public void caseASeqComp(ASeqComp node)
//    {
//        inASeqComp(node);
//        if(node.getTriaL() != null)
//        {
//            node.getTriaL().apply(this);
//        }
//        if(node.getL() != null)
//        {
//            node.getL().apply(this);
//        }
//        if(node.getArguments() != null)
//        {
//            node.getArguments().apply(this);
//			nodeMap.put(node,arguments.get(argDepth+1).get(0));
//			if(argDepth<0)
//			{
//				arguments.clear();
//			}
//        }
//        if(node.getM() != null)
//        {
//            node.getM().apply(this);
//        }
//        if(node.getStmts() != null)
//        {
//            node.getStmts().apply(this);
//        }
//        if(node.getR() != null)
//        {
//            node.getR().apply(this);
//        }
//        if(node.getTriaR() != null)
//        {
//            node.getTriaR().apply(this);
//        }
//        outASeqComp(node);
//    }
//
////***************************************************************************************	
////Atoms
//	@Override
//    public void caseAIdAtom(AIdAtom node)
//    {
//        inAIdAtom(node);
//		String i = node.getId().toString().replace(" ","");
//		String t = null;
//
//        if(node.getId() != null)
//        {
//            node.getId().apply(this);
//        }
//        if(node.getTuple() != null)
//        {
//			t = node.getTuple().toString().replace(" ","");
//            node.getTuple().apply(this);
//        }
//		if(tree.getCurrent().getValidTypes().get(i) != null && t == null)
//		{ //already in types? -> put in NodeMap
//			nodeMap.put(node,tree.getCurrent().getValidTypes().get(i));
//		}			
//		else if(i.equals("STOP") && t == null)
//		{
//			nodeMap.put(node,"Proc");
//		}
//		else if(i.equals("SKIP") && t == null)
//		{
//			nodeMap.put(node,"Proc");
//		}
//		else if(i.equals("DIV") && t == null)
//		{
//			nodeMap.put(node,"Proc");
//		}
//		else if(i.equals("Bool") && t == null)
//		{
//			nodeMap.put(node,"{Bool}");
//		}
//		else if(i.equals("Char") && t == null)
//		{
//			nodeMap.put(node,"{Char}");
//		}
//		else if(i.equals("Events") && t == null)
//		{
//			nodeMap.put(node,"{Event}");
//		}
//		else if(i.equals("Proc") && t == null)
//		{
//			nodeMap.put(node,"Proc");
//		}
//		else if(i.equals("Int") && t == null)
//		{
//			nodeMap.put(node,"{Int}");
//		}
//		else if(i.equals("True") && t == null)
//		{
//			nodeMap.put(node,"Bool");
//		}
//		else if(i.equals("False") && t == null)
//		{
//			nodeMap.put(node,"Bool");
//		}
//		else if(i.equals("CHAOS") && t != null && getTupleLength(t) == 1)
//		{
//			String a = nodeMap.get(node.getTuple()).toString();
//			if(a.matches("[(]\\{Event\\}[)]"))
//			{
//				nodeMap.put(node,"Proc");
//			}
//		}
//		else if(i.equals("WAIT") && t != null && getTupleLength(t) == 1)
//		{
//			String a = nodeMap.get(node.getTuple()).toString();
//			if(a.matches("[(]Int[)]"))
//			{
//				nodeMap.put(node,"Proc");
//			}
//		}
//		else if(i.equals("RUN") && t != null && getTupleLength(t) == 1)
//		{
//			String a = nodeMap.get(node.getTuple()).toString();
//			if(a.matches("[(]\\{Event\\}[)]"))
//			{
//				nodeMap.put(node,"Proc");
//			}
//		}
//		else if(i.equals("member") && t != null && getTupleLength(t) == 2)
//		{
//			Pattern pattern = Pattern.compile("[(](.*),\\{(.*)\\}[)]");
//			Matcher matcher = pattern.matcher(nodeMap.get(node.getTuple()).toString());
//			String a = "";
//			String b = "";
//			while(matcher.find())
//			{
//				a = matcher.group(1);
//				b = matcher.group(2);
//			}
//			if(a.equals(b))
//			{
//				nodeMap.put(node,"Bool");
//			}
//		}
//		else if(i.equals("empty") && t != null && getTupleLength(t) == 1)
//		{
//			String a = nodeMap.get(node.getTuple()).toString();
//			if(a.matches("[(]\\{(.*)\\}[)]"))
//			{
//				nodeMap.put(node,"Bool");
//			}
//		}
//		else if(i.equals("null") && t != null && getTupleLength(t) == 1)
//		{
//			String a = nodeMap.get(node.getTuple()).toString();
//			if(a.matches("[(]<(.*)>[)]"))
//			{
//				nodeMap.put(node,"Bool");
//			}	
//		}
//		else if(i.equals("union") && t != null && getTupleLength(t) == 2)
//		{		
//			Pattern pattern = Pattern.compile("[(]\\{(.*)\\},\\{(.*)\\}[)]");
//			Matcher matcher = pattern.matcher(nodeMap.get(node.getTuple()).toString());
//			
//			String a = "";
//			String b = "";
//			while(matcher.find())
//			{
//				a = matcher.group(1);
//				b = matcher.group(2);
//			}
//			if(a.equals(b))
//			{
//				nodeMap.put(node,"{"+b+"}");
//			}	
//		}
//		else if(i.equals("inter") && t != null && getTupleLength(t) == 2)
//		{		
//			Pattern pattern = Pattern.compile("[(]\\{(.*)\\},\\{(.*)\\}[)]");
//			Matcher matcher = pattern.matcher(nodeMap.get(node.getTuple()).toString());
//			
//			String a = "";
//			String b = "";
//			while(matcher.find())
//			{
//				a = matcher.group(1);
//				b = matcher.group(2);
//			}
//			if(a.equals(b))
//			{
//				nodeMap.put(node,"{"+b+"}");
//			}	
//		}
//		else if(i.equals("diff") && t != null && getTupleLength(t) == 2)
//		{		
//			Pattern pattern = Pattern.compile("[(]\\{(.*)\\},\\{(.*)\\}[)]");
//			Matcher matcher = pattern.matcher(nodeMap.get(node.getTuple()).toString());
//			
//			String a = "";
//			String b = "";
//			while(matcher.find())
//			{
//				a = matcher.group(1);
//				b = matcher.group(2);
//			}
//			if(a.equals(b))
//			{
//				nodeMap.put(node,"{"+b+"}");
//			}	
//		}
//		else if(i.equals("Union") && t != null && getTupleLength(t) == 1)
//		{
//			Pattern pattern = Pattern.compile("[(]\\{\\{(.*)\\}\\}[)]");
//			Matcher matcher = pattern.matcher(nodeMap.get(node.getTuple()).toString());
//			String a = "";
//			while(matcher.find())
//			{
//				a = matcher.group(1);
//			}
//			nodeMap.put(node,"{"+a+"}");
//		}
//		else if(i.equals("Inter") && t != null && getTupleLength(t) == 1)
//		{
//			Pattern pattern = Pattern.compile("[(]\\{\\{(.*)\\}\\}[)]");
//			Matcher matcher = pattern.matcher(nodeMap.get(node.getTuple()).toString());
//			String a = "";
//			while(matcher.find())
//			{
//				a = matcher.group(1);
//			}
//			nodeMap.put(node,"{"+a+"}");
//		}
//		else if(i.equals("card") && t != null && getTupleLength(t) == 1)
//		{
//			String a = nodeMap.get(node.getTuple()).toString();
//			if(a.matches("\\(\\{(.*)\\}\\)"))
//			{
//				nodeMap.put(node,"Int");	
//			}
//		}
//		else if(i.equals("Set") && t != null && getTupleLength(t) == 1)
//		{
//			Pattern pattern = Pattern.compile("[(]\\{(.*)\\}[)]");
//			Matcher matcher = pattern.matcher(nodeMap.get(node.getTuple()).toString());
//			String a = "";
//			while(matcher.find())
//			{
//				a = matcher.group(1);
//			}
//			nodeMap.put(node,"{{"+a+"}}");	
//		}
//		else if(i.equals("Seq") && t != null && getTupleLength(t) == 1)
//		{
//			Pattern pattern = Pattern.compile("[(]\\{(.*)\\}[)]");
//			Matcher matcher = pattern.matcher(nodeMap.get(node.getTuple()).toString());
//			String a = "";
//			while(matcher.find())
//			{
//				a = matcher.group(1);
//			}
//			nodeMap.put(node,"{<"+a+">}");	
//		}
//		else if(i.equals("head") && t != null && getTupleLength(t) == 1)
//		{
//			Pattern pattern = Pattern.compile("[(]<(.*)>[)]");
//			Matcher matcher = pattern.matcher(nodeMap.get(node.getTuple()).toString());
//			String a = "";
//			while(matcher.find())
//			{
//				a = matcher.group(1);
//			}
//			nodeMap.put(node,a);
//		}
//		else if(i.equals("tail") && t != null && getTupleLength(t) == 1)
//		{
//			Pattern pattern = Pattern.compile("[(]<(.*)>[)]");
//			Matcher matcher = pattern.matcher(nodeMap.get(node.getTuple()).toString());
//			String a = "";
//			while(matcher.find())
//			{
//				a = matcher.group(1);
//			}
//			nodeMap.put(node,"<"+a+">");
//		}
//		else if(i.equals("concat") && t != null && getTupleLength(t) == 1)
//		{
//			Pattern pattern = Pattern.compile("[(]<<(.*)>>[)]");
//			Matcher matcher = pattern.matcher(nodeMap.get(node.getTuple()).toString());
//			String a = "";
//			while(matcher.find())
//			{
//				a = matcher.group(1);
//			}
//			nodeMap.put(node,"<"+a+">");
//		}
//		else if(i.equals("set") && t != null && getTupleLength(t) == 1)
//		{
//			Pattern pattern = Pattern.compile("[(]<(.*)>[)]");
//			Matcher matcher = pattern.matcher(nodeMap.get(node.getTuple()).toString());
//			String a = "";
//			while(matcher.find())
//			{
//				a = matcher.group(1);
//			}
//			nodeMap.put(node,"{"+a+"}");
//		}
//		else if(i.equals("seq") && t != null && getTupleLength(t) == 1)
//		{
//			Pattern pattern = Pattern.compile("[(]\\{(.*)\\}[)]");
//			Matcher matcher = pattern.matcher(nodeMap.get(node.getTuple()).toString());
//			String a = "";
//			while(matcher.find())
//			{
//				a = matcher.group(1);
//			}
//			nodeMap.put(node,"<"+a+">");
//		}
//		else if(i.equals("length") && t != null && getTupleLength(t) == 1)
//		{
//			String a = nodeMap.get(node.getTuple()).toString();
//			if(a.matches("\\(<(.*)>\\)"))
//			{
//				nodeMap.put(node,"Int");	
//			}
//		}
//		else if(i.equals("error") && t != null && getTupleLength(t) == 1)
//		{
//			String a = nodeMap.get(node.getTuple()).toString();
//			if(a.matches("\\(<Char>\\)"))
//			{
//				nodeMap.put(node,"<Char>");	
//			}
//		}
//		else if(i.equals("show") && t != null && getTupleLength(t) == 1)
//		{
//			String a = nodeMap.get(node.getTuple()).toString();
//			if(a.matches("\\((.*)\\)"))
//			{
//				nodeMap.put(node,"<Char>");	
//			}	
//		}					
//        outAIdAtom(node);
//    }
//	
//	@Override
//    public void caseANumAtom(ANumAtom node)
//    {
//        inANumAtom(node);
//        if(node.getNumber() != null)
//        {
//            node.getNumber().apply(this);
//			nodeMap.put(node,"Int");
//        }
//        outANumAtom(node);
//    }
//	
//	@Override
//    public void caseATupleAtom(ATupleAtom node)
//    {
//        inATupleAtom(node);
//        if(node.getTuple() != null)
//        {
//            node.getTuple().apply(this);
//			String a = nodeMap.get(node.getTuple()).toString();
//			if(getTupleLength(a) == 1)
//			{
//				char[] c = a.toCharArray();
//				String temp = new String(c);
//				if(c[0] == '('){c[0] = ' ';}
//				if(c[c.length-1] == ')'){c[c.length-1] = ' ';}
//				temp = new String(c);
//				temp = temp.replaceAll(" ","");
//				nodeMap.put(node, temp);
//			}
//			else
//			{
//				nodeMap.put(node, nodeMap.get(node.getTuple()));
//			}
//        }
//        if(node.getLambda() != null)
//        {
//            node.getLambda().apply(this);
//        }
//		nodeMap.remove(node.getTuple());
//        outATupleAtom(node);
//    }
//	
//    @Override
//    public void caseASetAtom(ASetAtom node)
//    {
//        inASetAtom(node);
//        if(node.getSet() != null)
//        {
//            node.getSet().apply(this);
//			nodeMap.put(node, nodeMap.get(node.getSet()));
//			nodeMap.remove(node.getSet());
//        }
//        outASetAtom(node);
//    }
//	
//	@Override
//    public void caseASequenceAtom(ASequenceAtom node)
//    {
//        inASequenceAtom(node);
//        if(node.getSequence() != null)
//        {
//            node.getSequence().apply(this);
//			nodeMap.put(node,nodeMap.get(node.getSequence()));
//			nodeMap.remove(node.getSequence());
//        }
//        outASequenceAtom(node);
//    }
//	
//   @Override
//    public void caseAStringAtom(AStringAtom node)
//    {
//        inAStringAtom(node);
//        if(node.getString() != null)
//        {
//            node.getString().apply(this);
//			nodeMap.put(node,"<Char>");
//        }
//        outAStringAtom(node);
//    }
//	
//    @Override
//    public void caseACharAtom(ACharAtom node)
//    {
//        inACharAtom(node);
//        if(node.getChar() != null)
//        {
//            node.getChar().apply(this);
//			nodeMap.put(node,"Char");
//        }
//        outACharAtom(node);
//    }
//
//	    @Override
//    public void caseATrue1Atom(ATrue1Atom node)
//    {
//        inATrue1Atom(node);
//        if(node.getTrue1() != null)
//        {
//            node.getTrue1().apply(this);
//			nodeMap.put(node,"Bool");
//        }
//        outATrue1Atom(node);
//    }
//
//    @Override
//    public void caseAFalse1Atom(AFalse1Atom node)
//    {
//        inAFalse1Atom(node);
//        if(node.getFalse1() != null)
//        {
//            node.getFalse1().apply(this);
//			nodeMap.put(node,"Bool");
//        }
//        outAFalse1Atom(node);
//    }
//	
////***************************************************************************************
////Arguments
//    @Override
//    public void caseAArguments(AArguments node)
//    {
//        inAArguments(node);
//		argDepth++;
//		arguments.add(argDepth, new ArrayList<String>());
//        if(node.getArgumentsRek() != null)
//        {
//            node.getArgumentsRek().apply(this);
//        }
//		argDepth = argDepth-1;
//        outAArguments(node);
//    }
//
//    @Override
//    public void caseAArgStartArgumentsRek(AArgStartArgumentsRek node)
//    {
//        inAArgStartArgumentsRek(node);
//		if(node.getProc1() != null)
//        {
//            node.getProc1().apply(this);
//			String a = nodeMap.get(node.getProc1()).toString();
//			arguments.get(argDepth).add(a);
//        }
//        if(node.getComma() != null)
//        {
//            node.getComma().apply(this);
//        }
//		if(node.getArgumentsRek() != null)
//        {
//            node.getArgumentsRek().apply(this);
//        }
//		nodeMap.remove(node.getProc1());
//        outAArgStartArgumentsRek(node);
//    }
//
//    @Override
//    public void caseAArgEndArgumentsRek(AArgEndArgumentsRek node)
//    {
//        inAArgEndArgumentsRek(node);
//        if(node.getProc1() != null)
//        {
//            node.getProc1().apply(this);
//			String a = nodeMap.get(node.getProc1()).toString();
//			arguments.get(argDepth).add(a);
//        }
//
//		for(int i = 1; i<arguments.get(argDepth).size();i++)
//		{
//			if(!(arguments.get(argDepth).get(0).equals(arguments.get(argDepth).get(i))))
//			{
//				throw new RuntimeException("TypeError, expecting "
//							+arguments.get(argDepth).get(0)+" at:"+node.toString());
//			}
//		}
//		nodeMap.remove(node.getProc1());
//        outAArgEndArgumentsRek(node);
//    }
//	
////***************************************************************************************
////Tuple
//    @Override
//    public void caseATupleTuple(ATupleTuple node)
//    {
//        inATupleTuple(node);
//        if(node.getParL() != null)
//        {
//            node.getParL().apply(this);
//        }
//        if(node.getArguments() != null)
//        {
//            node.getArguments().apply(this);
//        }
//        if(node.getParR() != null)
//        {
//            node.getParR().apply(this);
//        }
//		nodeMap.put(node,"("+nodeMap.get(node.getArguments())+")");
//		nodeMap.remove(node.getArguments());
//        outATupleTuple(node);
//    }
	
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
	
//***************************************************************************************
//Resolve type chain to correct type
	public String reduce(String r)
	{
		String str = "";
		char[] ca = r.toCharArray();
		int depth = 0;
		for(int i = 0;i<ca.length;i++)
		{
			if(ca[i] == '(' || ca[i] == '<' || ca[i] == '{')
			{
				depth++;
			}
			else if(ca[i] == ')' || ca[i] == '>' || ca[i] == '}')
			{
				depth -= 1;
			}
			
			if(depth == 0 && ca[i] == '=' && ca[i+1] == '>')
			{
				ca[i] = '~';
				ca[i+1] = '\u00BB';
			}
			else if(depth == 0 && ca[i] == '.')
			{
				ca[i] = '#';
			}
		}
		str = new String(ca);
		
		String[] temp1 = str.split("#");
		ArrayList<ArrayList<String>> outer = new ArrayList<ArrayList<String>>();
		ArrayList<String> inner = new ArrayList<String>();
		for(int j = 0; j<temp1.length;j++)
		{
			String[] temp2 = temp1[j].split("~\u00BB");
			inner = new ArrayList<String>(Arrays.asList(temp2));
			outer.add(inner);
		}
		
		for(int k = 0;k<outer.size();k++)
		{
			outer.set(k, reverse(outer.get(k)));
		}
		
		int u = outer.size()-1;
		
		//Reduktion
		while(u>=0)
		{
			if(outer.get(u).size()>1)
			{
				String q = outer.get(u).get(outer.get(u).size()-1);
							//Das letzte Element der aktuellen Liste outer.get(u)
				String w = outer.get(u+1).get(0);
				if(q.equals(w))
				{
					outer.get(u).remove(outer.get(u).size()-1);
					outer.get(u+1).remove(0);
					if(outer.get(u+1).isEmpty())
					{
						outer.remove(u+1);
					}
					u = outer.size()-1;
					}
				else
				{

				}
			}
			else
			{
				u = u-1;
			}
		}
		//Sub-Listen wieder umdrehen und zusammensetzen
		
		for(int h = 0;h<outer.size();h++)
		{
			outer.set(h,reverse(outer.get(h)));	
		}
		String done = "";	
		for(int n = 0;n<outer.size();n++)
		{
			if(n > 0)
			{
				done += ".";
			}
			for(int m = 0;m<outer.get(n).size();m++)
			{
				if(m == 0)
				{
					done += outer.get(n).get(m);
				}
				else
				{
					done = done+"=>"+outer.get(n).get(m);
				}
			}
		}
		return done;
	}


	public ArrayList<String> reverse(ArrayList<String> list) 
	{
		if(list.size() > 1) 
		{                   
			String value = list.remove(0);
			reverse(list);
			list.add(value);
		}
		return list;
	}	
	
}