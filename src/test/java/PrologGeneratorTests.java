import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.Ignore;

//Notepad Regex for replacing src-locations:    'src_span'\(\d+,\d+,\d+,\d+,\d+,\d+\)  ->   'no_loc_info_available'

public class PrologGeneratorTests 
{	
	@Test
	public void NonSpecialBuiltins() throws Exception
	{
		check(		"H = Events"
				,
					"'bindval'('H','Events','no_loc_info_available')."
					+"\n'symbol'('Events','Events','no_loc_info_available','BuiltIn primitive')."
					+"\n'symbol'('H','H','no_loc_info_available','Ident (Groundrep.)')."
			 );
	}
	@Test
	public void IfThenElse() throws Exception
	{
		check("m = if true then 1 else 1"
		,
			"'bindval'('m','ifte'('true','int'(1),'int'(1),'no_loc_info_available','no_loc_info_available','no_loc_info_available'),'no_loc_info_available')."
			+"\n'symbol'('m','m','no_loc_info_available','Ident (Groundrep.)')."
		);
	}
	@Test
	public void ParamNumeration() throws Exception
	{
		check(
				"B(x,y) = SKIP"
				+"\nB(x,y) = 5"
				+"\nF(x,y) = (1,2)"
				+"\nG(a) = F(a)"
				+"\ny(_) = 1"			
				,
				"'agent'('B'(_x,_y),'skip'('no_loc_info_available'),'no_loc_info_available')."
				+"\n'agent'('B'(_x2,_y2),'int'(5),'no_loc_info_available')."
				+"\n'agent'('F'(_x3,_y3),'tupleExp'(['int'(1),'int'(2)]),'no_loc_info_available')."
				+"\n'agent'('G'(_a),'agent_call'('no_loc_info_available','F',[_a]),'no_loc_info_available')."
				+"\n'agent'('y4'(_),'int'(1),'no_loc_info_available')."
				+"\n'symbol'('a','a','no_loc_info_available','Ident (Prolog Variable)')."
				+"\n'symbol'('B','B','no_loc_info_available','Function or Process')."
				+"\n'symbol'('F','F','no_loc_info_available','Function or Process')."
				+"\n'symbol'('G','G','no_loc_info_available','Function or Process')."
				+"\n'symbol'('x','x','no_loc_info_available','Ident (Prolog Variable)')."
				+"\n'symbol'('x2','x','no_loc_info_available','Ident (Prolog Variable)')."
				+"\n'symbol'('x3','x','no_loc_info_available','Ident (Prolog Variable)')."
				+"\n'symbol'('y','y','no_loc_info_available','Ident (Prolog Variable)')."
				+"\n'symbol'('y2','y','no_loc_info_available','Ident (Prolog Variable)')."
				+"\n'symbol'('y3','y','no_loc_info_available','Ident (Prolog Variable)')."
				+"\n'symbol'('y4','y','no_loc_info_available','Function or Process')."
				+"\n'symbol'('SKIP','SKIP','no_loc_info_available','BuiltIn primitive')."
			);
	}
	
	@Test
	public void Chars() throws Exception
	{
		check(
					"E = 'a'"
					,
					"'bindval'('E','char'('a'),'no_loc_info_available')."
					+"\n'symbol'('E','E','no_loc_info_available','Ident (Groundrep.)')."
					);
	}
	
	@Test
	public void Maps() throws Exception
	{
		check(
					"C = (| |)\nD = (|1=>2|)"
					,
					"'bindval'('C','emptyMap','no_loc_info_available')."
					+"\n'bindval'('D','mapExp'(['int'(1),'int'(2)]),'no_loc_info_available')."
					+"\n'symbol'('C','C','no_loc_info_available','Ident (Groundrep.)')."
					+"\n'symbol'('D','D','no_loc_info_available','Ident (Groundrep.)')."
					);
	}
	
	@Test
	public void TypeDefs() throws Exception
	{
		check(	
				"datatype dt = cn|cn2.Bool"
				+"\nsubtype st = cn"
				+"\nnametype nt = Bool.Int"
				+"\nchannel c1,c2,c3: Bool.Int"
				,
				"'dataTypeDef'('dt',['constructor'('cn'),'constructorC'('cn2','dotTupleType'(['boolType']))])."
				+"\n'subTypeDef'('st',['constructor'('cn')])."
				+"\n'nameType'('nt','type'('dotTupleType'(['boolType','intType'])))."
				+"\n'channel'('c1','type'('dotTupleType'(['boolType','intType'])))."
				+"\n'channel'('c2','type'('dotTupleType'(['boolType','intType'])))."
				+"\n'channel'('c3','type'('dotTupleType'(['boolType','intType'])))."
				+"\n'symbol'('dt','dt','no_loc_info_available','Datatype')."
				+"\n'symbol'('c3','c3','no_loc_info_available','Channel')."
				+"\n'symbol'('st','st','no_loc_info_available','Datatype')."
				+"\n'symbol'('Bool','Bool','no_loc_info_available','BuiltIn primitive')."
				+"\n'symbol'('cn2','cn2','no_loc_info_available','Constructor of Datatype')."
				+"\n'symbol'('nt','nt','no_loc_info_available','Nametype')."
				+"\n'symbol'('cn','cn','no_loc_info_available','Constructor of Datatype')."
				+"\n'symbol'('cn2','cn','no_loc_info_available','Constructor of Datatype')."
				+"\n'symbol'('Int','Int','no_loc_info_available','BuiltIn primitive')."
				+"\n'symbol'('c1','c1','no_loc_info_available','Channel')."
				+"\n'symbol'('c2','c2','no_loc_info_available','Channel')."
			);
	}
	
	@Test
	public void MultipleAgentDefs() throws Exception
	{
		check(
				"a = 1"
				+"\nA = 1"
				+"\nI(a) = A"
				+"\nI(a) = a"
				+"\nI(A) = a"
				+"\nI(A) = A"
				,
				"'bindval'('a','int'(1),'no_loc_info_available')."
				+"\n'bindval'('A','int'(1),'no_loc_info_available')."
				+"\n'agent'('I'(_a2),'val_of'('A','no_loc_info_available'),'no_loc_info_available')."
				+"\n'agent'('I'(_a3),_a3,'no_loc_info_available')."
				+"\n'agent'('I'(_A2),'val_of'('a','no_loc_info_available'),'no_loc_info_available')."
				+"\n'agent'('I'(_A3),_A3,'no_loc_info_available')."
				+"\n'symbol'('a','a','no_loc_info_available','Ident (Groundrep.)')."
				+"\n'symbol'('a2','a','no_loc_info_available','Ident (Prolog Variable)')."
				+"\n'symbol'('a3','a','no_loc_info_available','Ident (Prolog Variable)')."
				+"\n'symbol'('A','A','no_loc_info_available','Ident (Groundrep.)')."
				+"\n'symbol'('A2','A','no_loc_info_available','Ident (Prolog Variable)')."
				+"\n'symbol'('A3','A','no_loc_info_available','Ident (Prolog Variable)')."
				+"\n'symbol'('I','I','no_loc_info_available','Function or Process')."							
			);
	}
	@Test
	public void redefineBuiltins() throws Exception
	{
		check("STOP = 1\nSKIP = 1"
		,
			"'bindval'('STOP','int'(1),'no_loc_info_available').\n'bindval'('SKIP','int'(1),'no_loc_info_available')."
			+"\n'symbol'('STOP','STOP','no_loc_info_available','Ident (Groundrep.)')."
			+"\n'symbol'('SKIP','SKIP','no_loc_info_available','Ident (Groundrep.)')."
		);
	}
	@Test
	public void callSingleBuiltin() throws Exception
	{
		check("P=STOP",
		
				 "'bindval'('P','stop'('no_loc_info_available'),'no_loc_info_available')."
				+"\n'symbol'('P','P','no_loc_info_available','Ident (Groundrep.)')."
				+"\n'symbol'('STOP','STOP','no_loc_info_available','BuiltIn primitive').");
	}
	
	@Test
	public void callBuiltinsMultipleTimes() throws Exception 
	{
		check( 		"P = STOP"
					+"\nQ = STOP"
					+"\nV = SKIP"
					+"\nW = SKIP"
				,
					"'bindval'('P','stop'('no_loc_info_available'),'no_loc_info_available')."
					+"\n'bindval'('Q','stop'('no_loc_info_available'),'no_loc_info_available')."
					+"\n'bindval'('V','skip'('no_loc_info_available'),'no_loc_info_available')."
					+"\n'bindval'('W','skip'('no_loc_info_available'),'no_loc_info_available')."
					+"\n'symbol'('P','P','no_loc_info_available','Ident (Groundrep.)')."
					+"\n'symbol'('Q','Q','no_loc_info_available','Ident (Groundrep.)')."
					+"\n'symbol'('STOP','STOP','no_loc_info_available','BuiltIn primitive')."
					+"\n'symbol'('V','V','no_loc_info_available','Ident (Groundrep.)')."
					+"\n'symbol'('W','W','no_loc_info_available','Ident (Groundrep.)')."
					+"\n'symbol'('SKIP','SKIP','no_loc_info_available','BuiltIn primitive')."
				);
	}
	
	@Test
	public void expressionsNewlinesBetween() throws Exception
	{
		check("b= 2 \n a = true \n       and     \n b==1", 
		"'bindval'('b','int'(2),'no_loc_info_available')."
		+"\n'bindval'('a','bool_and'('true','=='('val_of'('b','no_loc_info_available'),'int'(1))),'no_loc_info_available')."
		+"\n'symbol'('a','a','no_loc_info_available','Ident (Groundrep.)')."
		+"\n'symbol'('b','b','no_loc_info_available','Ident (Groundrep.)').");	
	}
	
	@Test
	public void LetWithinTest() throws Exception
	{
		check("channel a \n R(b) = let \n b = STOP \n within a-> b \n Q = a-> a -> SKIP",				
		"'channel'('a','type'('dotUnitType'))."
		+"\n'agent'('R'(_b),'let'(['bindval'('b2','stop'('no_loc_info_available'),'no_loc_info_available')],'prefix'('no_loc_info_available',[],'a','val_of'('b2','no_loc_info_available'),'no_loc_info_available')),'no_loc_info_available')."
		+"\n'bindval'('Q','prefix'('no_loc_info_available',[],'a','prefix'('no_loc_info_available',[],'a','skip'('no_loc_info_available'),'no_loc_info_available'),'no_loc_info_available'),'no_loc_info_available')."
		+"\n'symbol'('a','a','no_loc_info_available','Channel')."
		+"\n'symbol'('Q','Q','no_loc_info_available','Ident (Groundrep.)')."
		+"\n'symbol'('R','R','no_loc_info_available','Function or Process')."
		+"\n'symbol'('b','b','no_loc_info_available','Ident (Prolog Variable)')."
		+"\n'symbol'('b2','b','no_loc_info_available','Ident (Groundrep.)')."
		+"\n'symbol'('STOP','STOP','no_loc_info_available','BuiltIn primitive')."
		+"\n'symbol'('SKIP','SKIP','no_loc_info_available','BuiltIn primitive')."
		);
	}
	
	@Test
	public void FunctionCalls() throws Exception
	{
		check("A = 1.A(1)",
		"'bindval'('A','dotTuple'(['int'(1),'agent_call'('no_loc_info_available','val_of'('A','no_loc_info_available'),['int'(1)])]),'no_loc_info_available')."
		+"\n'symbol'('A','A','no_loc_info_available','Ident (Groundrep.)')."
		);
	}
	
	@Test
	public void Functional() throws Exception
	{
		check("1 = ((1)(1))(1)",
		"'bindval'('int'(1),'agent_call'('no_loc_info_available','agent_call'('no_loc_info_available','int'(1),['int'(1)]),['int'(1)]),'no_loc_info_available')."
		);
	}
	
	@Test
	public void Patterns() throws Exception
	{
		check("\"stringPattern\" = 1\n'c' = 1\n1  = 1\n(_,\"tuplePattern\") = 1"
		,		
		"'bindval'('stringPat'(\"stringPattern\"),'int'(1),'no_loc_info_available')."
		+"\n'bindval'('charPat'('c'),'int'(1),'no_loc_info_available')."
		+"\n'bindval'('int'(1),'int'(1),'no_loc_info_available')."
		+"\n'bindval'('tuplePat'([_,'stringPat'(\"tuplePattern\")]),'int'(1),'no_loc_info_available')."				
		);
	}
	
	@Test
	public void ProcOperators() throws Exception
	{
		check(	"J = 1 \\ 2"
				+"\nK = 1 ||| 2"
				+"\nL = 2 [|3|> 4"
				+"\nM = 2 [|3|] 4"
				+"\nN = 2 [3||4] 5"
				+"\nO = 2 [3<->4] 5"
				+"\nP = 2 [3<->4,6<->7] 5"
				+"\nQ = 2 |~| 3"
				+"\nR = 2 [] 3"
				+"\nS = 2 [+ 3 +] 4"
				+"\nT = 2 /\\ 3"
				+"\nU = 2 /+ 1 +\\ 3"
				+"\nV = 2 [> 3"
				+"\nW = 2 ; 3"
				+"\nX = 2 & 3"
				+"\nY = 1 -> 5"
				+"\nn = [] true @ 1"
				+"\no = |~| 1:2 @ 1"
				+"\np = ||| 1:2,true @ 2"
				+"\nq = || 1 @ [true] 1"
				+"\nr = ; 1 @ 2"
				+"\ns = [|true|] 1 @ 2"
				+"\nt = [1<->2] true @ true"
				+"\nu = [+1+] true @ true"
				,
				"'bindval'('J','\\'('int'(1),'int'(2),'src_span_operator'('no_loc_info_available','no_loc_info_available')),'no_loc_info_available')."
				+"\n'bindval'('K','|||'('int'(1),'int'(2),'src_span_operator'('no_loc_info_available','no_loc_info_available')),'no_loc_info_available')."
				+"\n'bindval'('L','exception'('int'(2),'int'(3),'int'(4),'no_loc_info_available'),'no_loc_info_available')."
				+"\n'bindval'('M','sharing'('int'(2),'int'(3),'int'(4),'no_loc_info_available'),'no_loc_info_available')."
				+"\n'bindval'('N','aParallel'('int'(2),'int'(3),'int'(4),'int'(5),'no_loc_info_available'),'no_loc_info_available')."
				+"\n'bindval'('O','lParallel'('linkList'(['link'('int'(3),'int'(4))]),'int'(2),'int'(5),'no_loc_info_available'),'no_loc_info_available')."
				+"\n'bindval'('P','lParallel'('linkList'(['link'('int'(3),'int'(4)),'link'('int'(6),'int'(7))]),'int'(2),'int'(5),'no_loc_info_available'),'no_loc_info_available')."
				+"\n'bindval'('Q','|~|'('int'(2),'int'(3),'src_span_operator'('no_loc_info_available','no_loc_info_available')),'no_loc_info_available')."
				+"\n'bindval'('R','[]'('int'(2),'int'(3),'src_span_operator'('no_loc_info_available','no_loc_info_available')),'no_loc_info_available')."
				+"\n'bindval'('S','syncExtChoice'('int'(2),'int'(3),'int'(4),'no_loc_info_available'),'no_loc_info_available')."
				+"\n'bindval'('T','/\\'('int'(2),'int'(3),'src_span_operator'('no_loc_info_available','no_loc_info_available')),'no_loc_info_available')."
				+"\n'bindval'('U','syncInterrupt'('int'(2),'int'(1),'int'(3),'no_loc_info_available'),'no_loc_info_available')."
				+"\n'bindval'('V','[>'('int'(2),'int'(3),'src_span_operator'('no_loc_info_available','no_loc_info_available')),'no_loc_info_available')."
				+"\n'bindval'('W',';'('int'(2),'int'(3),'src_span_operator'('no_loc_info_available','no_loc_info_available')),'no_loc_info_available')."
				+"\n'bindval'('X','&'('int'(2),'int'(3)),'no_loc_info_available')."
				+"\n'bindval'('Y','prefix'('no_loc_info_available',[],'int'(1),'int'(5),'no_loc_info_available'),'no_loc_info_available')."
				+"\n'bindval'('n','repChoice'(['comprehensionGuard'('true')],'int'(1),'no_loc_info_available'),'no_loc_info_available')."
				+"\n'bindval'('o','repInternalChoice'(['comprehensionGenerator'('int'(1),'int'(2))],'int'(1),'no_loc_info_available'),'no_loc_info_available')."
				+"\n'bindval'('p','repInterleave'(['comprehensionGenerator'('int'(1),'int'(2)),'comprehensionGuard'('true')],'int'(2),'no_loc_info_available'),'no_loc_info_available')."
				+"\n'bindval'('q','procRepAParallel'(['comprehensionGuard'('int'(1))],'pair'('true','int'(1)),'no_loc_info_available'),'no_loc_info_available')."
				+"\n'bindval'('r','repSequence'(['comprehensionGuard'('int'(1))],'int'(2),'no_loc_info_available'),'no_loc_info_available')."
				+"\n'bindval'('s','procRepSharing'('true',['comprehensionGuard'('int'(1))],'int'(2),'no_loc_info_available'),'no_loc_info_available')."
				+"\n'bindval'('t','procRepLinkParallel'('linkList'(['link'('int'(1),'int'(2))]),['comprehensionGuard'('true')],'true','no_loc_info_available'),'no_loc_info_available')."
				+"\n'bindval'('u','procRepSyncParallel'('int'(1),['comprehensionGuard'('true')],'true','no_loc_info_available'),'no_loc_info_available')."
				+"\n'symbol'('J','J','no_loc_info_available','Ident (Groundrep.)')."
				+"\n'symbol'('K','K','no_loc_info_available','Ident (Groundrep.)')."
				+"\n'symbol'('L','L','no_loc_info_available','Ident (Groundrep.)')."
				+"\n'symbol'('M','M','no_loc_info_available','Ident (Groundrep.)')."
				+"\n'symbol'('N','N','no_loc_info_available','Ident (Groundrep.)')."
				+"\n'symbol'('n','n','no_loc_info_available','Ident (Groundrep.)')."
				+"\n'symbol'('O','O','no_loc_info_available','Ident (Groundrep.)')."
				+"\n'symbol'('o','o','no_loc_info_available','Ident (Groundrep.)')."
				+"\n'symbol'('P','P','no_loc_info_available','Ident (Groundrep.)')."
				+"\n'symbol'('p','p','no_loc_info_available','Ident (Groundrep.)')."
				+"\n'symbol'('Q','Q','no_loc_info_available','Ident (Groundrep.)')."
				+"\n'symbol'('q','q','no_loc_info_available','Ident (Groundrep.)')."
				+"\n'symbol'('R','R','no_loc_info_available','Ident (Groundrep.)')."
				+"\n'symbol'('r','r','no_loc_info_available','Ident (Groundrep.)')."
				+"\n'symbol'('S','S','no_loc_info_available','Ident (Groundrep.)')."
				+"\n'symbol'('s','s','no_loc_info_available','Ident (Groundrep.)')."
				+"\n'symbol'('T','T','no_loc_info_available','Ident (Groundrep.)')."
				+"\n'symbol'('t','t','no_loc_info_available','Ident (Groundrep.)')."
				+"\n'symbol'('U','U','no_loc_info_available','Ident (Groundrep.)')."
				+"\n'symbol'('u','u','no_loc_info_available','Ident (Groundrep.)')."
				+"\n'symbol'('V','V','no_loc_info_available','Ident (Groundrep.)')."
				+"\n'symbol'('W','W','no_loc_info_available','Ident (Groundrep.)')."
				+"\n'symbol'('X','X','no_loc_info_available','Ident (Groundrep.)')."
				+"\n'symbol'('Y','Y','no_loc_info_available','Ident (Groundrep.)')."
				);
	}
	
	@Test
	public void letWithinDifficult() throws Exception
	{
		check(
		"k = let\nI(a) = 1\nI(a) = let\nI(a) = a\nwithin 8\nI(a) = 3\nwithin 9"
		+"\nl(x) =  let\nu = 1\nb(u) = let\nc = u\nwithin 1\nwithin x"
		,
		"'bindval'('k','let'(['agent'('I'(_a),'int'(1),'no_loc_info_available'),'agent'('I'(_a2),'let'(['agent'('I2'(_a3),_a3,'no_loc_info_available')],'int'(8)),'no_loc_info_available'),'agent'('I'(_a4),'int'(3),'no_loc_info_available')],'int'(9)),'no_loc_info_available')."
		+"\n'agent'('l'(_x),'let'(['bindval'('u','int'(1),'no_loc_info_available'),'agent'('b'(_u2),'let'(['bindval'('c',_u2,'no_loc_info_available')],'int'(1)),'no_loc_info_available')],_x),'no_loc_info_available')."
		+"\n'symbol'('a','a','no_loc_info_available','Ident (Prolog Variable)')."
		+"\n'symbol'('a2','a','no_loc_info_available','Ident (Prolog Variable)')."
		+"\n'symbol'('a3','a','no_loc_info_available','Ident (Prolog Variable)')."
		+"\n'symbol'('a4','a','no_loc_info_available','Ident (Prolog Variable)')."
		+"\n'symbol'('b','b','no_loc_info_available','Function or Process')."
		+"\n'symbol'('c','c','no_loc_info_available','Ident (Groundrep.)')."
		+"\n'symbol'('u','u','no_loc_info_available','Ident (Groundrep.)')."
		+"\n'symbol'('u2','u','no_loc_info_available','Ident (Prolog Variable)')."
		+"\n'symbol'('x','x','no_loc_info_available','Ident (Prolog Variable)')."
		+"\n'symbol'('I','I','no_loc_info_available','Function or Process')."
		+"\n'symbol'('I2','I','no_loc_info_available','Function or Process')."
		+"\n'symbol'('k','k','no_loc_info_available','Ident (Groundrep.)')."
		+"\n'symbol'('l','l','no_loc_info_available','Function or Process')."
		);
	}
	
	@Test
	public void Lambda() throws Exception
	{
		check("h = \\a,b@3\ni(a) = \\b@a+b",
				"'bindval'('h','lambda'([_a,_b],'int'(3)),'no_loc_info_available')."
				+"\n'agent'('i'(_a2),'lambda'([_b2],'+'(_a2,_b2)),'no_loc_info_available')."
				+"\n'symbol'('a','a','no_loc_info_available','Ident (Prolog Variable)')."
				+"\n'symbol'('a2','a','no_loc_info_available','Ident (Prolog Variable)')."
				+"\n'symbol'('b','b','no_loc_info_available','Ident (Prolog Variable)')."
				+"\n'symbol'('b2','b','no_loc_info_available','Ident (Prolog Variable)')."
				+"\n'symbol'('h','h','no_loc_info_available','Ident (Groundrep.)')."
				+"\n'symbol'('i','i','no_loc_info_available','Function or Process')."			  
			);
	}
	
	@Test
	public void InputOutput() throws Exception
	{
		check(  "Z = 1?1.2 -> 5"
				+"\na(d) = 1?1.2:1.2 -> 5"
				+"\na(d) = 1"
				+"\nb = 1$1.2 -> 5"
				+"\nc = 1$1.2:1.2 -> 5"
				+"\nd = 1!1.2 -> 5"
				+"\ne = 1?1@@2 -> 5"
				+"\nf = 1?1^2 -> 5"
				+"\ng = 1^2"
				+"\nj(y) = 1?y -> y"
				,
				"'bindval'('Z','prefix'('no_loc_info_available',['in'('dotpat'(['int'(1),'int'(2)]))],'int'(1),'int'(5),'no_loc_info_available'),'no_loc_info_available')."
				+"\n'agent'('a'(_d),'prefix'('no_loc_info_available',['inGuard'('dotpat'(['int'(1),'int'(2)]),'dotTuple'(['int'(1),'int'(2)]))],'int'(1),'int'(5),'no_loc_info_available'),'no_loc_info_available')."
				+"\n'agent'('a'(_d2),'int'(1),'no_loc_info_available')."
				+"\n'bindval'('b','prefix'('no_loc_info_available',['nondetIn'('dotpat'(['int'(1),'int'(2)]))],'int'(1),'int'(5),'no_loc_info_available'),'no_loc_info_available')."
				+"\n'bindval'('c','prefix'('no_loc_info_available',['nondetInGuard'('dotpat'(['int'(1),'int'(2)]),'dotTuple'(['int'(1),'int'(2)]))],'int'(1),'int'(5),'no_loc_info_available'),'no_loc_info_available')."
				+"\n'bindval'('d3','prefix'('no_loc_info_available',['out'('dotTuple'(['int'(1),'int'(2)]))],'int'(1),'int'(5),'no_loc_info_available'),'no_loc_info_available')."
				+"\n'bindval'('e','prefix'('no_loc_info_available',['in'('alsoPattern'(['int'(1),'int'(2)]))],'int'(1),'int'(5),'no_loc_info_available'),'no_loc_info_available')."
				+"\n'bindval'('f','prefix'('no_loc_info_available',['in'('appendPattern'(['int'(1),'int'(2)]))],'int'(1),'int'(5),'no_loc_info_available'),'no_loc_info_available')."
				+"\n'bindval'('g','^'('int'(1),'int'(2)),'no_loc_info_available')."
				+"\n'agent'('j'(_y),'prefix'('no_loc_info_available',['in'(_y2)],'int'(1),_y2,'no_loc_info_available'),'no_loc_info_available')."
				+"\n'symbol'('a','a','no_loc_info_available','Function or Process')."
				+"\n'symbol'('b','b','no_loc_info_available','Ident (Groundrep.)')."
				+"\n'symbol'('c','c','no_loc_info_available','Ident (Groundrep.)')."
				+"\n'symbol'('d','d','no_loc_info_available','Ident (Prolog Variable)')."
				+"\n'symbol'('d2','d','no_loc_info_available','Ident (Prolog Variable)')."
				+"\n'symbol'('d3','d','no_loc_info_available','Ident (Groundrep.)')."
				+"\n'symbol'('e','e','no_loc_info_available','Ident (Groundrep.)')."
				+"\n'symbol'('f','f','no_loc_info_available','Ident (Groundrep.)')."
				+"\n'symbol'('g','g','no_loc_info_available','Ident (Groundrep.)')."
				+"\n'symbol'('y','y','no_loc_info_available','Ident (Prolog Variable)')."
				+"\n'symbol'('y2','y','no_loc_info_available','Ident (Prolog Variable)')."
				+"\n'symbol'('Z','Z','no_loc_info_available','Ident (Groundrep.)')."
				+"\n'symbol'('j','j','no_loc_info_available','Function or Process')."
			);
	}
	
	@Test
	public void ExpressionOperators() throws Exception
	{
		check(
				"v = -(1^2)^2"
				+"\nw = 0-1+2*3/4%-5"
				+"\nx = #1"
				+"\nz = true or false and not true"
		,
			"'bindval'('v','negate'('^'('^'('int'(1),'int'(2)),'int'(2))),'no_loc_info_available')."
			+"\n'bindval'('w','+'('-'('int'(0),'int'(1)),'%'('/'('*'('int'(2),'int'(3)),'int'(4)),'int'(-5))),'no_loc_info_available')."
			+"\n'bindval'('x','#'('int'(1)),'no_loc_info_available')."
			+"\n'bindval'('z','bool_or'('true','bool_and'('false','bool_not'('true'))),'no_loc_info_available')."
			+"\n'symbol'('v','v','no_loc_info_available','Ident (Groundrep.)')."
			+"\n'symbol'('w','w','no_loc_info_available','Ident (Groundrep.)')."
			+"\n'symbol'('x','x','no_loc_info_available','Ident (Groundrep.)')."
			+"\n'symbol'('z','z','no_loc_info_available','Ident (Groundrep.)')."
		);
	}
	
	private void check(final String input, final String expected) 
	{
		String actual;
		try 
		{
			actual = parseString(input);
		} 
		catch (CSPMparserException e) 
		{
			actual = "";
		}
		System.out.println(expected+"\n"+actual);
		assertEquals(expected, actual);
	}
	
	private void checkParserError(final String input) 
	{
		boolean exception_occurred = false;
		try 
		{ 
			parseString(input);
		} 
		catch (CSPMparserException e) 
		{
			exception_occurred = true;
		}
		assertTrue(exception_occurred);		
	}

	private String parseString(final String input) throws CSPMparserException 
	{
		CSPMparser parser = new CSPMparser();
		return parser.parseString(input);
	}


}
