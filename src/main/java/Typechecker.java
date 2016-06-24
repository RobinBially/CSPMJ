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

	private HashMap<Integer,HashMap<String,String>> types = 
										new HashMap<Integer,HashMap<String,String>>();
	private int letWithinDepth = 0;

	private ArrayList<String> dtypes = new ArrayList<String>();//Speichert Name aller Datentypen

	private boolean currentInTypeExpNode; //z.b. datatype Type = A.Bool | B.Type.{1..3}
									//Nur im TypeExp Knoten, darf Type vom Typ Type sein !!!!
	private String currentTypeExp;
	private String currentDatatype;
	private String currentClauseName;
	private HashMap nodeMap = new HashMap();
	private ArrayList<ArrayList<String>> arguments = new ArrayList<ArrayList<String>>();
	private int argDepth = -1;
	private ArrayList<ArrayList<String>> innerseq = new ArrayList<ArrayList<String>>();
	private int seqDepth = -1;
	
//***************************************************************************************	
//Channels

    @Override
    public void caseAChan(AChan node)
    {
        inAChan(node);
		String a = node.getId().toString().replaceAll(" ","");
        if(node.getChannel() != null)
        {
            node.getChannel().apply(this);
        }
        if(node.getId() != null)
        {
            node.getId().apply(this);
        }
        if(node.getChanRek() != null)
        {
            node.getChanRek().apply(this);
        }
		
	
		HashMap<String,String> tempMap = types.get(0);
		tempMap.put(a,nodeMap.get(node.getChanRek()).toString()+"=>Event");
		if(types.get(0).get(a) != null)
		{
			types.get(0).remove(a);
		}
		types.put(0,tempMap);
		
		nodeMap.remove(node.getChanRek());
		nodeMap.remove(node.getId());
	//	System.out.println(types);
        outAChan(node);
    }	
	
	@Override
    public void caseARekChanRek(ARekChanRek node)
    {
        inARekChanRek(node);
		String a = node.getId().toString().replaceAll(" ","");
        if(node.getComma() != null)
        {
            node.getComma().apply(this);
        }
        if(node.getId() != null)
        {
            node.getId().apply(this);
        }
        if(node.getChanRek() != null)
        {
            node.getChanRek().apply(this);
        }
		//channel a,b,c: this pushes types of b and c into types
	
		HashMap<String,String> tempMap = types.get(0);
		tempMap.put(a,nodeMap.get(node.getChanRek()).toString()+"=>Event");
		if(types.get(0).get(a) != null)
		{
			types.get(0).remove(a);
		}
		types.put(0,tempMap);
				
		nodeMap.put(node,nodeMap.get(node.getChanRek()));
		nodeMap.remove(node.getId());
		nodeMap.remove(node.getChanRek());
        outARekChanRek(node);
    }
	
	@Override
    public void caseAEndChanRek(AEndChanRek node)
    {
        inAEndChanRek(node);
        if(node.getDdot() != null)
        {
            node.getDdot().apply(this);
        }
        if(node.getTypeExp() != null)
        {
			currentInTypeExpNode = true;
            node.getTypeExp().apply(this);
			currentInTypeExpNode = false;
			nodeMap.put(node,nodeMap.get(node.getTypeExp()));
        }
		nodeMap.remove(node.getTypeExp());
        outAEndChanRek(node);
    }

	
//***************************************************************************************
//Datatypes, Subtypes, Nametypes	
    @Override
    public void caseATypedef(ATypedef node)
    {
        inATypedef(node);
        if(node.getId() != null)
        {
            node.getId().apply(this);
			currentDatatype = node.getId().toString().replaceAll(" ","");
			dtypes.add(currentDatatype); // datatype X = ... saves X
        }
        if(node.getEq() != null)
        {
            node.getEq().apply(this);
        }
        if(node.getClause() != null)
        {
            node.getClause().apply(this);
			if(nodeMap.containsKey(node.getClause()))
			{
				String a = nodeMap.get(node.getClause()).toString();
				nodeMap.remove(node.getClause());
				HashMap<String,String> tempMap = types.get(0);
				tempMap.put(currentClauseName,a+"=>"+currentDatatype);
				types.put(0,tempMap);
														//z.B. ...= Y.Bool      
														//Y:: Bool=>X, wobei Y ccn
			}
			else
			{
				HashMap<String,String> tempMap = types.get(0);
				tempMap.put(currentClauseName,currentDatatype);
				types.put(0,tempMap);
			}
			
        }
        {
            List<PTypedefRek> copy = new ArrayList<PTypedefRek>(node.getTypedefRek());
            for(PTypedefRek e : copy)
            {
                e.apply(this);
				if(nodeMap.containsKey(e))
				{	
					String a = nodeMap.get(e).toString();
					nodeMap.remove(e);
					HashMap<String,String> tempMap = types.get(0);
					tempMap.put(currentClauseName,a+"=>"+currentDatatype);
					types.put(0,tempMap);
				}
				else
				{
					HashMap<String,String> tempMap = types.get(0);
					tempMap.put(currentClauseName,currentDatatype);
					types.put(0,tempMap);
				}
            }
        }
//		System.out.println(types);
		currentDatatype = "";
        outATypedef(node);
    }
	
	@Override
    public void caseAClause(AClause node)
    {
        inAClause(node);
        if(node.getClauseName() != null)
        {
            node.getClauseName().apply(this);
			currentClauseName = node.getClauseName().toString();
			currentClauseName = currentClauseName.replaceAll(" ","");
        }
        if(node.getDotted() != null)
        {
            node.getDotted().apply(this);
			nodeMap.put(node, nodeMap.get(node.getDotted()));
			nodeMap.remove(node.getDotted());
        }
        outAClause(node);
    }
	
	@Override
    public void caseADotted(ADotted node)
    {
        inADotted(node);
        if(node.getDot() != null)
        {
            node.getDot().apply(this);
        }
        if(node.getTypeExp() != null)
        {
			currentInTypeExpNode = true;
            node.getTypeExp().apply(this);
			currentInTypeExpNode = false;
			nodeMap.put(node, nodeMap.get(node.getTypeExp()));
        }
		nodeMap.remove(node.getTypeExp());
        outADotted(node);
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
            node.getClause().apply(this);
			if(nodeMap.containsKey(node.getClause()))
			{
				nodeMap.put(node,nodeMap.get(node.getClause()));
			}
        }
		nodeMap.remove(node.getClause());
        outATypedefRek(node);
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
//***************************************************************************************
//Type Expressions

    @Override
    public void caseADottedTypeExp(ADottedTypeExp node)
    {
        inADottedTypeExp(node);
		String upperType = "";
		String lowerType = "";
        if(node.getTypeExp() != null)
        {
            node.getTypeExp().apply(this);
			upperType = nodeMap.get(node.getTypeExp()).toString();
			
        }
        if(node.getDot() != null)
        {
            node.getDot().apply(this);
        }
        if(node.getTypeExp1() != null)
        {
            node.getTypeExp1().apply(this);
			lowerType = nodeMap.get(node.getTypeExp1()).toString();

        }
		nodeMap.put(node, upperType+"=>"+lowerType);
		nodeMap.remove(node.getTypeExp());
		nodeMap.remove(node.getTypeExp1());
        outADottedTypeExp(node);
    }	
	
    @Override
    public void caseAParTypeExp(AParTypeExp node)
    {
        inAParTypeExp(node);
        if(node.getTypeExp1() != null)
        {
            node.getTypeExp1().apply(this);
			nodeMap.put(node, nodeMap.get(node.getTypeExp1()));
			nodeMap.remove(node.getTypeExp1());
        }
        outAParTypeExp(node);
    }

    @Override
    public void caseAParTypeExp1(AParTypeExp1 node)
    {
		String upper = "";
		int copySize = 0;
		ArrayList<String> lower = new ArrayList<String>();
        inAParTypeExp1(node);
        if(node.getParL() != null)
        {
            node.getParL().apply(this);
        }
        if(node.getTypeExp() != null)
        {
            node.getTypeExp().apply(this);
			upper = nodeMap.get(node.getTypeExp()).toString();
			nodeMap.remove(node.getTypeExp());
			
        }
        {
            List<PTypeExps> copy = new ArrayList<PTypeExps>(node.getTypeExps());
            for(PTypeExps e : copy)
            {
				copySize++;
                e.apply(this);
				lower.add(nodeMap.get(e).toString());
				nodeMap.remove(e);
            }
        }
        if(node.getParR() != null)
        {
            node.getParR().apply(this);
        }
		
		String full = "";
		if(copySize>0)
		{
			full = "("+upper;
			for(int i = 0;i<lower.size();i++)
			{
				full += ","+lower.get(i);
			}
			full += ")";
		}
		else
		{
			full = upper;
		}
		
		nodeMap.put(node,full);
        outAParTypeExp1(node);
    }

    @Override
    public void caseASetTypeTypeExp1(ASetTypeTypeExp1 node)
    {
        inASetTypeTypeExp1(node);
        if(node.getTypeExp2() != null)
        {
            node.getTypeExp2().apply(this);
			nodeMap.put(node, nodeMap.get(node.getTypeExp2()));
			nodeMap.remove(node.getTypeExp2());
        }
        outASetTypeTypeExp1(node);
    }
	
	@Override
    public void caseASetTypeExp2(ASetTypeExp2 node)
    {
        inASetTypeExp2(node);
        if(node.getSet() != null)
        {
            node.getSet().apply(this);
			String a = "";
			Pattern pattern = Pattern.compile("\\{(.*)\\}");
			Matcher matcher = pattern.matcher(nodeMap.get(node.getSet()).toString());
			while(matcher.find())
			{
				a = matcher.group(1);
			}
			nodeMap.put(node, a);
			nodeMap.remove(node.getSet());
        }
        outASetTypeExp2(node);
    }	
	
	@Override
    public void caseASetNameTypeExp2(ASetNameTypeExp2 node)
    {
        inASetNameTypeExp2(node);
        if(node.getId() != null)
        {
            node.getId().apply(this);
			String a = nodeMap.get(node.getId()).toString().replaceAll(" ","");
			for(int j = 0; j<dtypes.size();j++)
			{
				if(a.equals(dtypes.get(j)))
				{
					nodeMap.put(node,a);
				}
			}
			if(a.matches("\\{.*\\}"))
			{
				Pattern pattern = Pattern.compile("\\{(.*)\\}");
				Matcher matcher = pattern.matcher(a);
				String t = "";
				while(matcher.find())
				{
					t = matcher.group(1);
				}
				nodeMap.put(node,t);
			}
				
		}
        if(node.getTuple() != null)
        {
            node.getTuple().apply(this);
        }
		nodeMap.remove(node.getId());
		outASetNameTypeExp2(node);
    }
//***************************************************************************************	
//Atoms
	@Override
    public void caseAIdAtom(AIdAtom node)
    {
        inAIdAtom(node);
		String i = node.getId().toString().replace(" ","");
		String t = null;
        if(node.getId() != null)
        {
            node.getId().apply(this);
        }
        if(node.getTuple() != null)
        {
			t = node.getTuple().toString().replace(" ","");
            node.getTuple().apply(this);
        }
		
		if(i.equals("STOP") && t == null)
		{
			nodeMap.put(node,"Proc");
		}
		else if(i.equals("SKIP") && t == null)
		{
			nodeMap.put(node,"Proc");
		}
		else if(i.equals("DIV") && t == null)
		{
			nodeMap.put(node,"Proc");
		}
		else if(i.equals("Bool") && t == null)
		{
			nodeMap.put(node,"{Bool}");
		}
		else if(i.equals("Char") && t == null)
		{
			nodeMap.put(node,"{Char}");
		}
		else if(i.equals("Events") && t == null)
		{
			nodeMap.put(node,"{Event}");
		}
		else if(i.equals("Proc") && t == null)
		{
			nodeMap.put(node,"Proc");
		}
		else if(i.equals("Int") && t == null)
		{
			nodeMap.put(node,"{Int}");
		}
		else if(i.equals("True") && t == null)
		{
			nodeMap.put(node,"Bool");
		}
		else if(i.equals("False") && t == null)
		{
			nodeMap.put(node,"Bool");
		}
		else if(i.equals("CHAOS") && t != null && getTupleLength(t) == 1)
		{
			String a = nodeMap.get(node.getTuple()).toString();
			if(a.matches("[(]\\{Event\\}[)]"))
			{
				nodeMap.put(node,"Proc");
			}
		}
		else if(i.equals("WAIT") && t != null && getTupleLength(t) == 1)
		{
			String a = nodeMap.get(node.getTuple()).toString();
			if(a.matches("[(]Int[)]"))
			{
				nodeMap.put(node,"Proc");
			}
		}
		else if(i.equals("RUN") && t != null && getTupleLength(t) == 1)
		{
			String a = nodeMap.get(node.getTuple()).toString();
			if(a.matches("[(]\\{Event\\}[)]"))
			{
				nodeMap.put(node,"Proc");
			}
		}
		else if(i.equals("member") && t != null && getTupleLength(t) == 2)
		{
			Pattern pattern = Pattern.compile("[(](.*),\\{(.*)\\}[)]");
			Matcher matcher = pattern.matcher(nodeMap.get(node.getTuple()).toString());
			String a = "";
			String b = "";
			while(matcher.find())
			{
				a = matcher.group(1);
				b = matcher.group(2);
			}
			if(a.equals(b))
			{
				nodeMap.put(node,"Bool");
			}
		}
		else if(i.equals("empty") && t != null && getTupleLength(t) == 1)
		{
			String a = nodeMap.get(node.getTuple()).toString();
			if(a.matches("[(]\\{(.*)\\}[)]"))
			{
				nodeMap.put(node,"Bool");
			}
		}
		else if(i.equals("null") && t != null && getTupleLength(t) == 1)
		{
			String a = nodeMap.get(node.getTuple()).toString();
			if(a.matches("[(]<(.*)>[)]"))
			{
				nodeMap.put(node,"Bool");
			}	
		}
		else if(i.equals("union") && t != null && getTupleLength(t) == 2)
		{		
			Pattern pattern = Pattern.compile("[(]\\{(.*)\\},\\{(.*)\\}[)]");
			Matcher matcher = pattern.matcher(nodeMap.get(node.getTuple()).toString());
			
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
		}
		else if(i.equals("inter") && t != null && getTupleLength(t) == 2)
		{		
			Pattern pattern = Pattern.compile("[(]\\{(.*)\\},\\{(.*)\\}[)]");
			Matcher matcher = pattern.matcher(nodeMap.get(node.getTuple()).toString());
			
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
		}
		else if(i.equals("diff") && t != null && getTupleLength(t) == 2)
		{		
			Pattern pattern = Pattern.compile("[(]\\{(.*)\\},\\{(.*)\\}[)]");
			Matcher matcher = pattern.matcher(nodeMap.get(node.getTuple()).toString());
			
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
		}
		else if(i.equals("Union") && t != null && getTupleLength(t) == 1)
		{
			Pattern pattern = Pattern.compile("[(]\\{\\{(.*)\\}\\}[)]");
			Matcher matcher = pattern.matcher(nodeMap.get(node.getTuple()).toString());
			String a = "";
			while(matcher.find())
			{
				a = matcher.group(1);
			}
			nodeMap.put(node,"{"+a+"}");
		}
		else if(i.equals("Inter") && t != null && getTupleLength(t) == 1)
		{
			Pattern pattern = Pattern.compile("[(]\\{\\{(.*)\\}\\}[)]");
			Matcher matcher = pattern.matcher(nodeMap.get(node.getTuple()).toString());
			String a = "";
			while(matcher.find())
			{
				a = matcher.group(1);
			}
			nodeMap.put(node,"{"+a+"}");
		}
		else if(i.equals("card") && t != null && getTupleLength(t) == 1)
		{
			String a = nodeMap.get(node.getTuple()).toString();
			if(a.matches("\\(\\{(.*)\\}\\)"))
			{
				nodeMap.put(node,"Int");	
			}
		}
		else if(i.equals("Set") && t != null && getTupleLength(t) == 1)
		{
			Pattern pattern = Pattern.compile("[(]\\{(.*)\\}[)]");
			Matcher matcher = pattern.matcher(nodeMap.get(node.getTuple()).toString());
			String a = "";
			while(matcher.find())
			{
				a = matcher.group(1);
			}
			nodeMap.put(node,"{{"+a+"}}");	
		}
		else if(i.equals("Seq") && t != null && getTupleLength(t) == 1)
		{
			Pattern pattern = Pattern.compile("[(]\\{(.*)\\}[)]");
			Matcher matcher = pattern.matcher(nodeMap.get(node.getTuple()).toString());
			String a = "";
			while(matcher.find())
			{
				a = matcher.group(1);
			}
			nodeMap.put(node,"{<"+a+">}");	
		}
		else if(i.equals("head") && t != null && getTupleLength(t) == 1)
		{
			Pattern pattern = Pattern.compile("[(]<(.*)>[)]");
			Matcher matcher = pattern.matcher(nodeMap.get(node.getTuple()).toString());
			String a = "";
			while(matcher.find())
			{
				a = matcher.group(1);
			}
			nodeMap.put(node,a);
		}
		else if(i.equals("tail") && t != null && getTupleLength(t) == 1)
		{
			Pattern pattern = Pattern.compile("[(]<(.*)>[)]");
			Matcher matcher = pattern.matcher(nodeMap.get(node.getTuple()).toString());
			String a = "";
			while(matcher.find())
			{
				a = matcher.group(1);
			}
			nodeMap.put(node,"<"+a+">");
		}
		else if(i.equals("concat") && t != null && getTupleLength(t) == 1)
		{
			Pattern pattern = Pattern.compile("[(]<<(.*)>>[)]");
			Matcher matcher = pattern.matcher(nodeMap.get(node.getTuple()).toString());
			String a = "";
			while(matcher.find())
			{
				a = matcher.group(1);
			}
			nodeMap.put(node,"<"+a+">");
		}
		else if(i.equals("set") && t != null && getTupleLength(t) == 1)
		{
			Pattern pattern = Pattern.compile("[(]<(.*)>[)]");
			Matcher matcher = pattern.matcher(nodeMap.get(node.getTuple()).toString());
			String a = "";
			while(matcher.find())
			{
				a = matcher.group(1);
			}
			nodeMap.put(node,"{"+a+"}");
		}
		else if(i.equals("seq") && t != null && getTupleLength(t) == 1)
		{
			Pattern pattern = Pattern.compile("[(]\\{(.*)\\}[)]");
			Matcher matcher = pattern.matcher(nodeMap.get(node.getTuple()).toString());
			String a = "";
			while(matcher.find())
			{
				a = matcher.group(1);
			}
			nodeMap.put(node,"<"+a+">");
		}
		else if(i.equals("length") && t != null && getTupleLength(t) == 1)
		{
			String a = nodeMap.get(node.getTuple()).toString();
			if(a.matches("\\(<(.*)>\\)"))
			{
				nodeMap.put(node,"Int");	
			}
		}
		else if(i.equals("error") && t != null && getTupleLength(t) == 1)
		{
			String a = nodeMap.get(node.getTuple()).toString();
			if(a.matches("\\(<Char>\\)"))
			{
				nodeMap.put(node,"<Char>");	
			}
		}
		else if(i.equals("show") && t != null && getTupleLength(t) == 1)
		{
			String a = nodeMap.get(node.getTuple()).toString();
			if(a.matches("\\((.*)\\)"))
			{
				nodeMap.put(node,"<Char>");	
			}	
		}					
        outAIdAtom(node);
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