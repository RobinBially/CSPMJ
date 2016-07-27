import static org.junit.Assert.*;
import java.io.*;
import java.*;
import java.util.*;
import java.lang.*;
import java.util.regex.*;
import java.lang.Character;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import org.junit.Test;
import org.junit.Ignore;

//Notepad Regex for replacing src-locations:    'src_span'\(\d+,\d+,\d+,\d+,\d+,\d+\)  ->   'no_loc_info_available'

public class PrologGeneratorTests 
{

	@Test
	public void CompareLambdaToCspmf() throws Exception
	{
		System.out.println("\r\n\r\nCompareLambdaToCspmf\r\n\r\n");
		String test = 	"h = (\\a,b@3)(1)(2)\r\ni(a) = \\b@a+b\r\na = 1";
		try
		{
		checkCSPMF(test,cspmfCompileToProlog(test));
		}catch(Exception e){throw e;}					
	}

	@Test
	public void CompareLetWithinDifficultToCspmf() throws Exception
	{
		System.out.println("\r\n\r\nCompareLetWithinDifficultToCspmf\r\n\r\n");
		String test = 				
						"k = let\r\nI(a) = 1\r\nI(a) = let\r\nI(a) = a\r\nwithin 8\r\nI(a) = 3\r\nwithin 9"
						+"\r\nl(x) =  let\r\nu = 1\r\nb(u) = let\r\nc = u\r\nwithin 1\r\nwithin x";
		try
		{
		checkCSPMF(test,cspmfCompileToProlog(test));
		}catch(Exception e){throw e;}					
	}

	@Test
	public void CompareFunctionalToCspmf() throws Exception
	{
		System.out.println("\r\n\r\nCompareFunctionalToCspmf\r\n\r\n");
		String test = 	"A(x) = (1?x->1)(x)+x"
						+"\r\nA(x) = (1?x->1,x)"
						+"\r\n1 = ((1)(1))(1)";
		try
		{
		checkCSPMF(test,cspmfCompileToProlog(test));
		}catch(Exception e){throw e;}					
	}

	@Test
	public void CompareFunctionCallsToCspmf() throws Exception
	{
		System.out.println("\r\n\r\nCompareFunctionCallsToCspmf\r\n\r\n");
		String test = 	"A = 1.A(1)";
		try
		{
		checkCSPMF(test,cspmfCompileToProlog(test));
		}catch(Exception e){throw e;}					
	}

	@Test
	public void CompareLetWithinToCspmf() throws Exception
	{
		System.out.println("\r\n\r\nCompareLetWithinToCspmf\r\n\r\n");
		String test = 	
						"channel a"
						+"\r\n R(b) = let" 
									+"\r\n b = a" 
							  +"\r\n within a-> b "
						+"\r\n Q = a-> a -> a";
		try
		{
		checkCSPMF(test,cspmfCompileToProlog(test));
		}catch(Exception e){throw e;}					
	}

	@Test
	public void CompareCallSingleBuiltinToCspmf() throws Exception
	{
		System.out.println("\r\n\r\nCompareCallSingleBuiltinToCspmf\r\n\r\n");
		String test = 	"member = 1";
		try
		{
		checkCSPMF(test,cspmfCompileToProlog(test));
		}catch(Exception e){throw e;}					
	}


	@Test
	public void CompareRedefineBuiltinsToCspmf() throws Exception
	{
		System.out.println("\r\n\r\nCompareRedefineBuiltinsToCspmf\r\n\r\n");
		String test = 	"member = 1\r\n1 = member";
		try
		{
		checkCSPMF(test,cspmfCompileToProlog(test));
		}catch(Exception e){throw e;}					
	}
	
	@Test
	public void CompareMultipleAgentDefsToCspmf() throws Exception
	{
		System.out.println("\r\n\r\nCompareMultipleAgentDefsToCspmf\r\n\r\n");
		String test = 	"a = 1"
						+"\r\nA = 1"
						+"\r\nI(a) = A"
						+"\r\nI(a) = a"
						+"\r\nI(A) = a"
						+"\r\nI(A) = A";
		try
		{
		checkCSPMF(test,cspmfCompileToProlog(test));
		}catch(Exception e){throw e;}					
	}
	
	@Test
	public void CompareTypeDefsToCspmf() throws Exception	//Bool and Int constant handling different in cspmf
	{
		System.out.println("\r\n\r\nCompareTypeDefsToCspmf\r\n\r\n");
		String test = 	
						"datatype dt = cn|cn2.{1}"
						+"\r\nsubtype st = cn"
						+"\r\nnametype nt = {1}.{1}"
						+"\r\nchannel c1,c2,c3: {1}.{1}";
		try
		{
		checkCSPMF(test,cspmfCompileToProlog(test));
		}catch(Exception e){throw e;}					
	}
	
	@Test
	public void CompareParamNumerationToCspmf() throws Exception
	{
		System.out.println("\r\n\r\nCompareParamNumerationToCspmf\r\n\r\n");
		String test = 	"B(x,y) = x+y"
						+"\r\nB(x,y) = 5"
						+"\r\nF(x,y) = (1,2)"
						+"\r\nG(a) = F(a)"
						+"\r\ny(_) = 1"	;
		try
		{
		checkCSPMF(test,cspmfCompileToProlog(test));
		}catch(Exception e){throw e;}					
	}
	
	@Test
	public void CompareIfThenElseToCspmf() throws Exception
	{
		System.out.println("\r\n\r\nCompareIfThenElseToCspmf\r\n\r\n");
		String test = "m = if true then 1 else 1";
		try
		{
		checkCSPMF(test,cspmfCompileToProlog(test));
		}catch(Exception e){throw e;}					
	}
	
	@Test
	public void CompareSetComprehensionsToCspmf() throws Exception
	{
		System.out.println("\r\n\r\nCompareSetComprehensionsToCspmf\r\n\r\n");
		String test = 	"y = 1"
						+"\r\nA = {x|x<-1,x,y}"
						+"\r\nB = {x..y|x<-1,x,y}"
						+"\r\nC = {x..|x<-1,x,y}"
						+"\r\nD = {|x|x<-1,x,y|}";
		try
		{
		checkCSPMF(test,cspmfCompileToProlog(test));
		}catch(Exception e){throw e;}					
	}
	
	@Test
	public void CompareListComprehensionsToCspmf() throws Exception
	{
		System.out.println("\r\n\r\nCompareListComprehensionsToCspmf\r\n\r\n");
		String test = 		"y = 1"
							+"\r\nA = <x|x<-1,x,y>"
							+"\r\nB = <x..y|x<-1,x,y>"
							+"\r\nC = <x..|x<-1,x,y>";
		try
		{
		checkCSPMF(test,cspmfCompileToProlog(test));
		}catch(Exception e){throw e;}					
	}
	
	@Test
	public void CompareDifficultComprehensionsToCspmf() throws Exception
	{
		System.out.println("\r\n\r\nCompareDifficultComprehensionsToCspmf\r\n\r\n");
		String test = 		"A = {1|x@@y^a.z<-1}"
							+"\r\nB = {x|x<-1,{1|x},x<-2}"
							+"\r\nC = {1|x<-1,x,x<-2,x}";
		try
		{
		checkCSPMF(test,cspmfCompileToProlog(test));
		}catch(Exception e){throw e;}					
	}

	@Test
	public void CompareInputOutputToCspmf() throws Exception
	{
		System.out.println("\r\n\r\nCompareInputOutputToCspmf\r\n\r\n");
		String test = 	"Z = 1?1.2 -> 5"
						+"\r\na(d) = 1?1.2:1.2 -> 5"
						+"\r\na(d) = 1"
					//	+"\r\nb = 1$1.2 -> 5" 		Restricted Input is not supported by cspmf
					//	+"\r\nc = 1$1.2:1.2 -> 5" 	Restricted Input is not supported by cspmf
						+"\r\nd = 1!1.2 -> 5"
						+"\r\ne = 1?1@@2 -> 5"
						+"\r\nf = 1?1^2 -> 5"
						+"\r\ng = 1^2"
						+"\r\nj(y) = 1?y -> y";
		try
		{
		checkCSPMF(test,cspmfCompileToProlog(test));
		}catch(Exception e){throw e;}					
	}
	
	@Test
	public void CompareRenamingAndRenamingComprehensionsToCspmf() throws Exception
	{
		System.out.println("\r\n\r\nCompareRenamingAndRenamingComprehensionsToCspmf\r\n\r\n");
		String test =		"d = 1"
							+"\r\na = 1"
							+"\r\nA(a) = a[[a <- d,a<-d]]"
							+"\r\nb = 1"
							+"\r\nB = b[[b<-d,b<-d|b<-1]]"
							+"\r\nc = 1"
							+"\r\nC(c) = c[[c<-d,c<-d|c<-1]]";
		try
		{
		checkCSPMF(test,cspmfCompileToProlog(test));
		}catch(Exception e){throw e;}					
	}
	
	@Test
	public void CompareRepWithStatementsToCspmf() throws Exception
	{
		System.out.println("\r\n\r\nCompareRepWithStatementsToCspmf\r\n\r\n");
		String test = "A  = [] x:2,x,x:3,x@x";
		try
		{
		checkCSPMF(test,cspmfCompileToProlog(test));
		}catch(Exception e){throw e;}					
	}
	
	@Test
	public void CompareEasyNumerationToCspmf() throws Exception
	{
		System.out.println("\r\n\r\nCompareEasyNumerationToCspmf\r\n\r\n");
		String test = 			"a = 1"
								+"\r\nx(a) = 1"
								+"\r\nb(a) = 1"
								+"\r\nc = 1?d -> d"
								+"\r\nchannel e: c";
		try
		{
		checkCSPMF(test,cspmfCompileToProlog(test));
		}catch(Exception e){throw e;}					
	}
	
	@Test
	public void CompareLinkedListComprehensionToCspmf() throws Exception
	{
		System.out.println("\r\n\r\nCompareLinkedListComprehensionToCspmf\r\n\r\n");
		String test = "B = 1 [2<->3,4<->5|x<-6] 7";
		try
		{
		checkCSPMF(test,cspmfCompileToProlog(test));
		}catch(Exception e){throw e;}					
	}
	
	@Test
	public void CompareComplexBuiltinCallInLetWithinToCspmf() throws Exception
	{
		System.out.println("\r\n\r\nCompareComplexBuiltinCallInLetWithinToCspmf\r\n\r\n");
		String test = 	"B  = {member|x<-1}"
						+"\r\nC = let D = member"
						+"\r\nmember = 1"
						+"\r\nwithin 1";
		try
		{
		checkCSPMF(test,cspmfCompileToProlog(test));
		}catch(Exception e){throw e;}					
	}
	
	@Test
	public void CompareTransparentFunctionsToCspmf() throws Exception
	{
		System.out.println("\r\n\r\nTransparentFunctionsToCspmf\r\n\r\n");
		String test = 	"transparent f,member"
						+"\r\nA = f";
		try
		{
		checkCSPMF(test,cspmfCompileToProlog(test));
		}catch(Exception e){throw e;}					
	}
		
	@Test
	public void ComparePrintToCspmf() throws Exception
	{
		System.out.println("\r\n\r\nComparePrintToCspmf\r\n\r\n");
		String test = "print 1->2";
		try
		{
		checkCSPMF(test,cspmfCompileToProlog(test));
		}catch(Exception e){throw e;}					
	}
	
	@Test
	public void CompareProcessOperatorsToCspmf() throws Exception
	{
		System.out.println("\r\n\r\nCompareProcessOperatorsToCspmf\r\n\r\n");
		String test = 	
					//	"J = 1 \\ 2"		cspmf does not escape
						"\r\nK = 1 ||| 2"
						+"\r\nL = 2 [|3|> 4"
						+"\r\nM = 2 [|3|] 4"
						+"\r\nN = 2 [3||4] 5"
						+"\r\nO = 2 [3<->4] 5"
						+"\r\nP = 2 [3<->4,6<->7] 5"
						+"\r\nQ = 2 |~| 3"
						+"\r\nR = 2 [] 3"
					//	+"\r\nS = 2 [+ 3 +] 4" not supported by cspmf
					//	+"\r\nT = 2 /\\ 3"	 cspmf does not escape
					//	+"\r\nU = 2 /+ 1 +\\ 3" not supported by cspmf
						+"\r\nV = 2 [> 3"
						+"\r\nW = 2 ; 3"
						+"\r\nX = 2 & 3"
						+"\r\nY = 1 -> 5"
						+"\r\nn = [] true @ 1"
						+"\r\no = |~| 1:2 @ 1"
						+"\r\np = ||| 1:2,true @ 2"
						+"\r\nq = || 1 @ [true] 1"
						+"\r\nr = ; 1 @ 2"
						+"\r\ns = [|true|] 1 @ 2"
						+"\r\nt = [1<->2] true @ true";
					//	+"\r\nu = [+1+] true @ true"; not supported by cspmf
		try
		{
		checkCSPMF(test,cspmfCompileToProlog(test));
		}catch(Exception e){throw e;}					
	}
	
	@Test
	public void CompareExpressionOperatorsToCspmf() throws Exception
	{
		System.out.println("\r\n\r\nCompareExpressionOperatorsToCspmf\r\n\r\n");
		String test = 
						"v = -(1^2)^2"
						+"\r\nw = 0-1+2*3/4%-5"
						+"\r\nx = #1"
						+"\r\nz = true or false and not true"
						+"\r\nu = -1+2";
		try
		{
		checkCSPMF(test,cspmfCompileToProlog(test));
		}catch(Exception e){throw e;}					
	}	
	
	@Test
	public void CompareAssertionsToCspmf() throws Exception
	{
		System.out.println("\r\n\r\nCompareAssertionsToCspmf\r\n\r\n");
		String test = 
					"assert 1 [T= 1"
					+"\r\nassert 1 [F= 1"
					+"\r\nassert 1 [FD= 1"
					+"\r\nassert not 1->2 [R= 2"
					+"\r\nassert 1 |= LTL: \"true\""
					+"\r\nassert 1 |= CTL: \"true\""
					+"\r\nassert 1 :[deadlock free]"
					+"\r\nassert 1 :[deadlock free [F]]"
					+"\r\nassert 1 :[deadlock free [FD]]"
					+"\r\nassert 1 :[deadlock free [T]]"
					+"\r\nassert 1 :[divergence free]"
					+"\r\nassert 1 :[divergence free [F]]"
					+"\r\nassert 1 :[divergence free [FD]]"
					+"\r\nassert 1 :[divergence free [T]]"
					+"\r\nassert 1 :[livelock free]"
					+"\r\nassert 1 :[livelock free [F]]"
					+"\r\nassert 1 :[livelock free [FD]]"
					+"\r\nassert 1 :[livelock free [T]]"
					+"\r\nassert 1 :[deterministic]"
					+"\r\nassert 1 :[deterministic [T]]"
					+"\r\nassert 1 :[deterministic [F]]"
					+"\r\nassert 1 :[deterministic [FD]]";
		try
		{
		checkCSPMF(test,cspmfCompileToProlog(test));
		}catch(Exception e){throw e;}					
	}	
	
	
	@Test
	public void CompareComplexComprehensionsToCspmf() throws Exception
	{
		System.out.println("\r\n\r\nCompareComplexComprehensionsToCspmf\r\n\r\n");
		String test = 
					"A(x) = {x+{x|x<-1}|x<-1,x<-2,x} + x"
					+"\r\nE(x) = {let x = 1"
					+"\r\n1 = {x|1}"
					+"\r\nwithin 2 |x<-1,let 1 = x within 2}"
					+"\r\nF = {1|x<-1,x,x<-2,x}";
		try
		{
		checkCSPMF(test,cspmfCompileToProlog(test));
		}catch(Exception e){throw e;}					
	}
	
	@Test
	public void ComparePatternsInComprehensionsToCspmf() throws Exception
	{
		System.out.println("\r\n\r\nComparePatternsInComprehensionsToCspmf\r\n\r\n");
		String test = 
				"{} = 2"
				+"\r\n{1} = 1"
				+"\r\n_ = 3"
				+"\r\n<> = 3"
				+"\r\n<1,2> = 5"
				+"\r\n1 = {1|_<-2}"
				+"\r\n1 = {1|{}<-2}"
				+"\r\n1 = {1|{1}<-2}"
				+"\r\n1 = {1|<><-2}"
				+"\r\n1 = {1|<1,2,3><-2}"
				+"\r\n1 = {1|(x,y)<-2,x}"
				+"\r\n1 = {1|(x)<-2,x}"
				+"\r\n1 = {1|(a.b^c@@(d),e)<-2,a,b,c,d,e}";
		try
		{
		checkCSPMF(test,cspmfCompileToProlog(test));
		}catch(Exception e){throw e;}					
	}
	
	
	@Test
	public void ComparePrecedenceToCspmf() throws Exception
	{
		System.out.println("\r\n\r\nComparePrecedenceToCspmf\r\n\r\n");
		String test = "A = 2 -> [] 1 @  1"
						+"\r\nB = [] 1 @  1 -> 2"
						+"\r\nC = \\1@1 -> 1"
						+"\r\nD = 1 -> \\1@1"
						+"\r\nE = let 1=1 within 1->1"
						+"\r\nF = 1 -> let 1=1 within 1"
						+"\r\nG = let 1=1 within \\1@1"
						+"\r\nH = \\1@let 1=1 within 1"
						+"\r\nI = 1?1.2.3:{1}.1 -> 1";
		try
		{
		checkCSPMF(test,cspmfCompileToProlog(test));
		}catch(Exception e){throw e;}					
	}
	
	@Test
	public void CompareCurryToCspmf() throws Exception 
	{
		System.out.println("\r\n\r\nCompareCurryToCspmf\r\n\r\n");
		String test = 	"nocurry(1) = nocurry(2)"
						+"\r\nnocurry(1,2) = nocurry(2,1)"
						+"\r\ncurry(1)(2) = 1"
						+"\r\n1 = curry(1)(2)"
						+"\r\n1 = (1)(2)(3)"
						+"\r\n1 = (1,2)(3,4)(5,6)"
						+"\r\nchannel c: {1}.curry(1)(2)";
		try
		{
		checkCSPMF(test,cspmfCompileToProlog(test));
		}catch(Exception e){throw e;}
	}
	
	@Test
	public void TauPrio() throws Exception //
	{
	  check(
				"assert 1 [T= 2 :[tau priority 1]"
				+"\r\nassert 1 [FD= 2 :[tau priority 1]"
				+"\r\nassert 1 [F= 2 :[tau priority 1]"
				+"\r\nassert 1 [R= 2 :[tau priority 1]"
			,
				"'assertTauPrio'('False','int'(1),'TauTrace','int'(2),'int'(1),'no_loc_info_available')."
				+"\r\n'assertTauPrio'('False','int'(1),'TauFailureDivergence','int'(2),'int'(1),'no_loc_info_available')."
				+"\r\n'assertTauPrio'('False','int'(1),'TauFailure','int'(2),'int'(1),'no_loc_info_available')."
				+"\r\n'assertTauPrio'('False','int'(1),'TauRefusalTesting','int'(2),'int'(1),'no_loc_info_available')."
			);
	}
	
	@Test
	public void Curry() throws Exception //
	{
		check(
					"nocurry(1) = nocurry(2)"
					+"\r\nnocurry(1,2) = nocurry(2,1)"
					+"\r\ncurry(1)(2) = 1"
					+"\r\n1 = curry(1)(2)"
					+"\r\n1 = (1)(2)(3)"
					+"\r\n1 = (1,2)(3,4)(5,6)"
					+"\r\nchannel c: {1}.curry(1)(2)"

				,
					"'agent'('nocurry'('int'(1)),'agent_call'('no_loc_info_available','nocurry',['int'(2)]),'no_loc_info_available')."
					+"\r\n'agent'('nocurry'('int'(1),'int'(2)),'agent_call'('no_loc_info_available','nocurry',['int'(2),'int'(1)]),'no_loc_info_available')."
					+"\r\n'agent_curry'('curry'(['int'(1)],['int'(2)]),'int'(1),'no_loc_info_available')."
					+"\r\n'bindval'('int'(1),'agent_call_curry'('curry',[['int'(1)],['int'(2)]]),'no_loc_info_available')."
					+"\r\n'bindval'('int'(1),'agent_call_curry'('int'(1),[['int'(2)],['int'(3)]]),'no_loc_info_available')."
					+"\r\n'bindval'('int'(1),'agent_call_curry'('tupleExp'(['int'(1),'int'(2)]),[['int'(3),'int'(4)],['int'(5),'int'(6)]]),'no_loc_info_available')."
					+"\r\n'channel'('c','type'('dotTupleType'(['setExp'('rangeEnum'(['int'(1)])),'agent_call_curry'('curry',[['int'(1)],['int'(2)]])])))."
					+"\r\n'symbol'('nocurry','nocurry','no_loc_info_available','Function or Process')."
					+"\r\n'symbol'('curry','curry','no_loc_info_available','Function or Process')."
					+"\r\n'symbol'('c','c','no_loc_info_available','Channel')."
			 );
	}
	
	@Test
	public void CommentsAndPragmas() throws Exception //
	{
			check( 
						"{-" 
						+"\r\nThis is a"
						+"\r\nmultiline comment"
						+"\r\n-}"
						+"\r\n{-# assert_ltl \"formula\" #-}"
						+"\r\n{-# assert_ltl \"formula\" \"comment\r\nwith newline\" #-}"
						+"\r\n{-# assert_ctl \"formula\" #-}"
						+"\r\n{-# assert_ctl \"formula\" \"comment\" #-}"
						+"\r\n-- This is a line comment"
					,
						"'pragma'('assert_ltl \"formula\"')."
						+"\r\n'pragma'('assert_ltl \"formula\" \"comment\\r\\nwith newline\"')."
						+"\r\n'pragma'('assert_ctl \"formula\"')."
						+"\r\n'pragma'('assert_ctl \"formula\" \"comment\"')."
						+"\r\n'comment'('blockComment'('{-\\r\\nThis is a\\r\\nmultiline comment\\r\\n-}'),'no_loc_info_available')."
						+"\r\n'comment'('pragmaComment'('{-# assert_ltl \"formula\" #-}'),'no_loc_info_available')."
						+"\r\n'comment'('pragmaComment'('{-# assert_ltl \"formula\" \"comment\\r\\nwith newline\" #-}'),'no_loc_info_available')."
						+"\r\n'comment'('pragmaComment'('{-# assert_ctl \"formula\" #-}'),'no_loc_info_available')."
						+"\r\n'comment'('pragmaComment'('{-# assert_ctl \"formula\" \"comment\" #-}'),'no_loc_info_available')."
						+"\r\n'comment'('lineComment'('-- This is a line comment','no_loc_info_available'))."
					);
	}
	
	@Test
	public void PatternsInComprehensions() throws Exception  //
	{
		check(
				"{} = 2"
				+"\r\n{1} = 1"
				+"\r\n_ = 3"
				+"\r\n<> = 3"
				+"\r\n<1,2> = 5"
				+"\r\n1 = {1|_<-2}"
				+"\r\n1 = {1|{}<-2}"
				+"\r\n1 = {1|{1}<-2}"
				+"\r\n1 = {1|<><-2}"
				+"\r\n1 = {1|<1,2,3><-2}"
				+"\r\n1 = {1|(x,y)<-2,x}"
				+"\r\n1 = {1|(x)<-2,x}"
				+"\r\n1 = {1|(a.b^c@@(d),e)<-2,a,b,c,d,e}"
			 ,
				"'bindval'('emptySet','int'(2),'no_loc_info_available')."
				+"\r\n'bindval'('singleSetPat'(['int'(1)]),'int'(1),'no_loc_info_available')."
				+"\r\n'bindval'(_,'int'(3),'no_loc_info_available')."
				+"\r\n'bindval'('listPat'([]),'int'(3),'no_loc_info_available')."
				+"\r\n'bindval'('listPat'(['int'(1),'int'(2)]),'int'(5),'no_loc_info_available')."
				+"\r\n'bindval'('int'(1),'setExp'('rangeEnum'(['int'(1)]),['comprehensionGenerator'(_,'int'(2))]),'no_loc_info_available')."
				+"\r\n'bindval'('int'(1),'setExp'('rangeEnum'(['int'(1)]),['comprehensionGenerator'('emptySet','int'(2))]),'no_loc_info_available')."
				+"\r\n'bindval'('int'(1),'setExp'('rangeEnum'(['int'(1)]),['comprehensionGenerator'('singleSetPat'(['int'(1)]),'int'(2))]),'no_loc_info_available')."
				+"\r\n'bindval'('int'(1),'setExp'('rangeEnum'(['int'(1)]),['comprehensionGenerator'('listPat'([]),'int'(2))]),'no_loc_info_available')."
				+"\r\n'bindval'('int'(1),'setExp'('rangeEnum'(['int'(1)]),['comprehensionGenerator'('listPat'(['int'(1),'int'(2),'int'(3)]),'int'(2))]),'no_loc_info_available')."
				+"\r\n'bindval'('int'(1),'setExp'('rangeEnum'(['int'(1)]),['comprehensionGenerator'('tuplePat'([_x,_y]),'int'(2)),'comprehensionGuard'(_x)]),'no_loc_info_available')."
				+"\r\n'bindval'('int'(1),'setExp'('rangeEnum'(['int'(1)]),['comprehensionGenerator'(_x2,'int'(2)),'comprehensionGuard'(_x2)]),'no_loc_info_available')."
				+"\r\n'bindval'('int'(1),'setExp'('rangeEnum'(['int'(1)]),['comprehensionGenerator'('tuplePat'(['alsoPattern'(['dotpat'([_a,'appendPattern'([_b,_c])]),_d]),_e]),'int'(2)),'comprehensionGuard'(_a),'comprehensionGuard'(_b),'comprehensionGuard'(_c),'comprehensionGuard'(_d),'comprehensionGuard'(_e)]),'no_loc_info_available')."
				+"\r\n'symbol'('x','x','no_loc_info_available','Ident (Prolog Variable)')."
				+"\r\n'symbol'('y','y','no_loc_info_available','Ident (Prolog Variable)')."
				+"\r\n'symbol'('x2','x','no_loc_info_available','Ident (Prolog Variable)')."
				+"\r\n'symbol'('a','a','no_loc_info_available','Ident (Prolog Variable)')."
				+"\r\n'symbol'('b','b','no_loc_info_available','Ident (Prolog Variable)')."
				+"\r\n'symbol'('c','c','no_loc_info_available','Ident (Prolog Variable)')."
				+"\r\n'symbol'('d','d','no_loc_info_available','Ident (Prolog Variable)')."
				+"\r\n'symbol'('e','e','no_loc_info_available','Ident (Prolog Variable)')."
			 );
	}
	
	@Test
	public void PrintExpressions() throws Exception //
	{
		check(
					"print 1->2"
			 ,
					"'cspPrint'('prefix'('no_loc_info_available',[],'int'(1),'int'(2),'no_loc_info_available'))."
			 );
	}	
	
	@Test
	public void TransparentFunctions() throws Exception //
	{
		check(
					"transparent f,member"
					+"\r\nA = f"
			 ,
					"'cspTransparent'(['f','member'])."
					+"\r\n'bindval'('A','f','no_loc_info_available')."
					+"\r\n'symbol'('f','f','no_loc_info_available','Transparent function')."
					+"\r\n'symbol'('member','member','no_loc_info_available','Transparent function')."
					+"\r\n'symbol'('A','A','no_loc_info_available','Ident (Groundrep.)')."
			 );		
	}
	
	@Test
	public void Assertions() throws Exception //
	{
		check(		"assert 1 [T= 1"
					+"\r\nassert 1 [F= 1"
					+"\r\nassert 1 [FD= 1"
					+"\r\nassert not 1->2 [R= 2"
					+"\r\nassert 1 |= LTL: \"true\""
					+"\r\nassert 1 |= CTL: \"true\""
					+"\r\nassert 1 :[deadlock free]"
					+"\r\nassert 1 :[deadlock free [F]]"
					+"\r\nassert 1 :[deadlock free [FD]]"
					+"\r\nassert 1 :[deadlock free [T]]"
					+"\r\nassert 1 :[divergence free]"
					+"\r\nassert 1 :[divergence free [F]]"
					+"\r\nassert 1 :[divergence free [FD]]"
					+"\r\nassert 1 :[divergence free [T]]"
					+"\r\nassert 1 :[livelock free]"
					+"\r\nassert 1 :[livelock free [F]]"
					+"\r\nassert 1 :[livelock free [FD]]"
					+"\r\nassert 1 :[livelock free [T]]"
					+"\r\nassert 1 :[deterministic]"
					+"\r\nassert 1 :[deterministic [T]]"
					+"\r\nassert 1 :[deterministic [F]]"
					+"\r\nassert 1 :[deterministic [FD]]"
					+"\r\nassert 1 :[has trace]: 2"
					+"\r\nassert 1 :[has trace [F]]: 2"
					+"\r\nassert 1 :[has trace [FD]]: 2"
					+"\r\nassert 1 :[has trace [T]]: 2"
				,
					"'assertRef'('False','int'(1),'Trace','int'(1),'no_loc_info_available')."
					+"\r\n'assertRef'('False','int'(1),'Failure','int'(1),'no_loc_info_available')."
					+"\r\n'assertRef'('False','int'(1),'FailureDivergence','int'(1),'no_loc_info_available')."
					+"\r\n'assertRef'('True','prefix'('no_loc_info_available',[],'int'(1),'int'(2),'no_loc_info_available'),'RefusalTesting','int'(2),'no_loc_info_available')."
					+"\r\n'assertLtl'('False','int'(1),'true','no_loc_info_available')."
					+"\r\n'assertCtl'('False','int'(1),'true','no_loc_info_available')."
					+"\r\n'assertModelCheck'('False','int'(1),'DeadlockFree')."
					+"\r\n'assertModelCheckExt'('False','int'(1),'DeadlockFree','F')."
					+"\r\n'assertModelCheckExt'('False','int'(1),'DeadlockFree','FD')."
					+"\r\n'assertModelCheckExt'('False','int'(1),'DeadlockFree','T')."
					+"\r\n'assertModelCheck'('False','int'(1),'LivelockFree')."
					+"\r\n'assertModelCheckExt'('False','int'(1),'LivelockFree','F')."
					+"\r\n'assertModelCheckExt'('False','int'(1),'LivelockFree','FD')."
					+"\r\n'assertModelCheckExt'('False','int'(1),'LivelockFree','T')."
					+"\r\n'assertModelCheck'('False','int'(1),'LivelockFree')."
					+"\r\n'assertModelCheckExt'('False','int'(1),'LivelockFree','F')."
					+"\r\n'assertModelCheckExt'('False','int'(1),'LivelockFree','FD')."
					+"\r\n'assertModelCheckExt'('False','int'(1),'LivelockFree','T')."
					+"\r\n'assertModelCheck'('False','int'(1),'Deterministic')."
					+"\r\n'assertModelCheckExt'('False','int'(1),'Deterministic','T')."
					+"\r\n'assertModelCheckExt'('False','int'(1),'Deterministic','F')."
					+"\r\n'assertModelCheckExt'('False','int'(1),'Deterministic','FD')."
					+"\r\n'assertHasTrace'('False','int'(1),'int'(2))."
					+"\r\n'assertHasTraceExt'('False','int'(1),'int'(2),'F')."
					+"\r\n'assertHasTraceExt'('False','int'(1),'int'(2),'FD')."
					+"\r\n'assertHasTraceExt'('False','int'(1),'int'(2),'T')."
				);
	}
	
	@Test
	public void ComplexBuiltinCallInLetWithin() //
	{
	   check(
				"B  = {member|x<-1}"
				+"\r\nC = let D = member"
				+"\r\nmember = 1"
				+"\r\nwithin 1"
			 ,
				"'bindval'('B','setExp'('rangeEnum'(['member']),['comprehensionGenerator'(_x,'int'(1))]),'no_loc_info_available')."
				+"\r\n'bindval'('C','let'(['bindval'('D','val_of'('member','no_loc_info_available'),'no_loc_info_available'),'bindval'('member','int'(1),'no_loc_info_available')],'int'(1)),'no_loc_info_available')."
				+"\r\n'symbol'('B','B','no_loc_info_available','Ident (Groundrep.)')."
				+"\r\n'symbol'('x','x','no_loc_info_available','Ident (Prolog Variable)')."
				+"\r\n'symbol'('C','C','no_loc_info_available','Ident (Groundrep.)')."
				+"\r\n'symbol'('D','D','no_loc_info_available','Ident (Groundrep.)')."
				+"\r\n'symbol'('member','member','no_loc_info_available','Ident (Groundrep.)')."
				+"\r\n'symbol'('member','member','no_loc_info_available','BuiltIn primitive')."	 
			 );		
	}
	
	@Test
	public void ComplexComprehensionAndLetWithin() //
	{
		check(
					"A(x) = {x+{x|x<-1}|x<-1,x<-2,x} + x"
					+"\r\nE(x) = {let x = 1"
					+"\r\n1 = {x|1}"
					+"\r\nwithin 2 |x<-1,let 1 = x within 2}"
					+"\r\nF = {1|x<-1,x,x<-2,x}"
			 ,
					"'agent'('A'(_x),'+'('setExp'('rangeEnum'(['+'(_x4,'setExp'('rangeEnum'([_x2]),['comprehensionGenerator'(_x2,'int'(1))]))]),['comprehensionGenerator'(_x3,'int'(1)),'comprehensionGenerator'(_x4,'int'(2)),'comprehensionGuard'(_x4)]),_x),'no_loc_info_available')."
					+"\r\n'agent'('E'(_x5),'setExp'('rangeEnum'(['let'(['bindval'('x6','int'(1),'no_loc_info_available'),'bindval'('int'(1),'setExp'('rangeEnum'(['val_of'('x6','no_loc_info_available')]),['comprehensionGuard'('int'(1))]),'no_loc_info_available')],'int'(2))]),['comprehensionGenerator'(_x7,'int'(1)),'comprehensionGuard'('let'(['bindval'('int'(1),_x7,'no_loc_info_available')],'int'(2)))]),'no_loc_info_available')."
					+"\r\n'bindval'('F','setExp'('rangeEnum'(['int'(1)]),['comprehensionGenerator'(_x8,'int'(1)),'comprehensionGuard'(_x8),'comprehensionGenerator'(_x9,'int'(2)),'comprehensionGuard'(_x9)]),'no_loc_info_available')."
					+"\r\n'symbol'('A','A','no_loc_info_available','Function or Process')."
					+"\r\n'symbol'('x','x','no_loc_info_available','Ident (Prolog Variable)')."
					+"\r\n'symbol'('x2','x','no_loc_info_available','Ident (Prolog Variable)')."
					+"\r\n'symbol'('x3','x','no_loc_info_available','Ident (Prolog Variable)')."
					+"\r\n'symbol'('x4','x','no_loc_info_available','Ident (Prolog Variable)')."
					+"\r\n'symbol'('E','E','no_loc_info_available','Function or Process')."
					+"\r\n'symbol'('x5','x','no_loc_info_available','Ident (Prolog Variable)')."
					+"\r\n'symbol'('x6','x','no_loc_info_available','Ident (Groundrep.)')."
					+"\r\n'symbol'('x7','x','no_loc_info_available','Ident (Prolog Variable)')."
					+"\r\n'symbol'('F','F','no_loc_info_available','Ident (Groundrep.)')."
					+"\r\n'symbol'('x8','x','no_loc_info_available','Ident (Prolog Variable)')."
					+"\r\n'symbol'('x9','x','no_loc_info_available','Ident (Prolog Variable)')."	 
			 );
	}
	@Test
	public void LinkedListComprehension() throws Exception //
	{
	   check(
				"B = 1 [2<->3,4<->5|x<-6] 7"
			,
				"'bindval'('B','lParallel'('linkListComp'(['comprehensionGenerator'(_x,'int'(6))],['link'('int'(2),'int'(3)),'link'('int'(4),'int'(5))]),'int'(1),'int'(7),'no_loc_info_available'),'no_loc_info_available')."
				+"\r\n'symbol'('B','B','no_loc_info_available','Ident (Groundrep.)')."
				+"\r\n'symbol'('x','x','no_loc_info_available','Ident (Prolog Variable)')."
			);
	}
	
	@Test
	public void EasyNumeration() throws Exception //
	{
		check(
					"a = 1"
					+"\r\nx(a) = 1"
					+"\r\nb(a) = 1"
					+"\r\nc = 1?d -> d"
					+"\r\nchannel e: c"
				,
					"'bindval'('a','int'(1),'no_loc_info_available')."
					+"\r\n'agent'('x'(_a2),'int'(1),'no_loc_info_available')."
					+"\r\n'agent'('b'(_a3),'int'(1),'no_loc_info_available')."
					+"\r\n'bindval'('c','prefix'('no_loc_info_available',['in'(_d)],'int'(1),_d,'no_loc_info_available'),'no_loc_info_available')."
					+"\r\n'channel'('e','type'('dotTupleType'(['val_of'('c','no_loc_info_available')])))."
					+"\r\n'symbol'('a','a','no_loc_info_available','Ident (Groundrep.)')."
					+"\r\n'symbol'('x','x','no_loc_info_available','Function or Process')."
					+"\r\n'symbol'('a2','a','no_loc_info_available','Ident (Prolog Variable)')."
					+"\r\n'symbol'('b','b','no_loc_info_available','Function or Process')."
					+"\r\n'symbol'('a3','a','no_loc_info_available','Ident (Prolog Variable)')."
					+"\r\n'symbol'('c','c','no_loc_info_available','Ident (Groundrep.)')."
					+"\r\n'symbol'('d','d','no_loc_info_available','Ident (Prolog Variable)')."
					+"\r\n'symbol'('e','e','no_loc_info_available','Channel')."
			);
	}
	
	@Test
	public void RepWithStatements() throws Exception //
	{
		check( "A  = [] x:2,x,x:3,x@x"
			 ,
				"'bindval'('A','repChoice'(['comprehensionGenerator'(_x,'int'(2)),'comprehensionGuard'(_x),'comprehensionGenerator'(_x2,'int'(3)),'comprehensionGuard'(_x2)],_x2,'no_loc_info_available'),'no_loc_info_available')."				 
				+"\r\n'symbol'('A','A','no_loc_info_available','Ident (Groundrep.)')."
				+"\r\n'symbol'('x','x','no_loc_info_available','Ident (Prolog Variable)')."
				+"\r\n'symbol'('x2','x','no_loc_info_available','Ident (Prolog Variable)')."
			);
	}
	
	@Test
	public void RenamingAndRenamingComprehensions() //
	{
		check(
					"d = 1"
					+"\r\na = 1"
					+"\r\nA(a) = a[[a <- d,a<-d]]"
					+"\r\nb = 1"
					+"\r\nB = b[[b<-d,b<-d|b<-1]]"
					+"\r\nc = 1"
					+"\r\nC(c) = c[[c<-d,c<-d|c<-1]]"
				,
					"'bindval'('d','int'(1),'no_loc_info_available')."
					+"\r\n'bindval'('a','int'(1),'no_loc_info_available')."
					+"\r\n'agent'('A'(_a2),'procRenaming'(['rename'(_a2,'val_of'('d','no_loc_info_available')),'rename'(_a2,'val_of'('d','no_loc_info_available'))],_a2,'no_loc_info_available'),'no_loc_info_available')."
					+"\r\n'bindval'('b','int'(1),'no_loc_info_available')."
					+"\r\n'bindval'('B','procRenamingComp'('val_of'('b','no_loc_info_available'),['comprehensionGenerator'(_b2,'int'(1))],['rename'(_b2,'val_of'('d','no_loc_info_available')),'rename'(_b2,'val_of'('d','no_loc_info_available'))]),'no_loc_info_available')."
					+"\r\n'bindval'('c','int'(1),'no_loc_info_available')."
					+"\r\n'agent'('C'(_c2),'procRenamingComp'(_c2,['comprehensionGenerator'(_c3,'int'(1))],['rename'(_c3,'val_of'('d','no_loc_info_available')),'rename'(_c3,'val_of'('d','no_loc_info_available'))]),'no_loc_info_available')."
					+"\r\n'symbol'('d','d','no_loc_info_available','Ident (Groundrep.)')."
					+"\r\n'symbol'('a','a','no_loc_info_available','Ident (Groundrep.)')."
					+"\r\n'symbol'('A','A','no_loc_info_available','Function or Process')."
					+"\r\n'symbol'('a2','a','no_loc_info_available','Ident (Prolog Variable)')."
					+"\r\n'symbol'('b','b','no_loc_info_available','Ident (Groundrep.)')."
					+"\r\n'symbol'('B','B','no_loc_info_available','Ident (Groundrep.)')."
					+"\r\n'symbol'('b2','b','no_loc_info_available','Ident (Prolog Variable)')."
					+"\r\n'symbol'('c','c','no_loc_info_available','Ident (Groundrep.)')."
					+"\r\n'symbol'('C','C','no_loc_info_available','Function or Process')."
					+"\r\n'symbol'('c2','c','no_loc_info_available','Ident (Prolog Variable)')."
					+"\r\n'symbol'('c3','c','no_loc_info_available','Ident (Prolog Variable)')."

			);
	}
	
	@Test
	public void DifficultComprehensions() throws Exception //
	{	
		check(
				"A = {1|x@@y^a.z<-1}"
				+"\r\nB = {x|x<-1,{1|x},x<-2}"
				+"\r\nC = {1|x<-1,x,x<-2,x}"
			,	
				"'bindval'('A','setExp'('rangeEnum'(['int'(1)]),['comprehensionGenerator'('alsoPattern'([_x,'dotpat'(['appendPattern'([_y,_a]),_z])]),'int'(1))]),'no_loc_info_available')."
				+"\r\n'bindval'('B','setExp'('rangeEnum'([_x3]),['comprehensionGenerator'(_x2,'int'(1)),'comprehensionGuard'('setExp'('rangeEnum'(['int'(1)]),['comprehensionGuard'(_x2)])),'comprehensionGenerator'(_x3,'int'(2))]),'no_loc_info_available')."
				+"\r\n'bindval'('C','setExp'('rangeEnum'(['int'(1)]),['comprehensionGenerator'(_x4,'int'(1)),'comprehensionGuard'(_x4),'comprehensionGenerator'(_x5,'int'(2)),'comprehensionGuard'(_x5)]),'no_loc_info_available')."
				+"\r\n'symbol'('A','A','no_loc_info_available','Ident (Groundrep.)')."
				+"\r\n'symbol'('x','x','no_loc_info_available','Ident (Prolog Variable)')."
				+"\r\n'symbol'('y','y','no_loc_info_available','Ident (Prolog Variable)')."
				+"\r\n'symbol'('a','a','no_loc_info_available','Ident (Prolog Variable)')."
				+"\r\n'symbol'('z','z','no_loc_info_available','Ident (Prolog Variable)')."
				+"\r\n'symbol'('B','B','no_loc_info_available','Ident (Groundrep.)')."
				+"\r\n'symbol'('x2','x','no_loc_info_available','Ident (Prolog Variable)')."
				+"\r\n'symbol'('x3','x','no_loc_info_available','Ident (Prolog Variable)')."
				+"\r\n'symbol'('C','C','no_loc_info_available','Ident (Groundrep.)')."
				+"\r\n'symbol'('x4','x','no_loc_info_available','Ident (Prolog Variable)')."
				+"\r\n'symbol'('x5','x','no_loc_info_available','Ident (Prolog Variable)')."
			);		
	}
	
	@Test
	public void ListComprehensions() throws Exception //
	{
		check(
					"y = 1"
					+"\r\nA = <x|x<-1,x,y>"
					+"\r\nB = <x..y|x<-1,x,y>"
					+"\r\nC = <x..|x<-1,x,y>"
				,	
					"'bindval'('y','int'(1),'no_loc_info_available')."
					+"\r\n'bindval'('A','listExp'('rangeEnum'([_x]),['comprehensionGenerator'(_x,'int'(1)),'comprehensionGuard'(_x),'comprehensionGuard'('val_of'('y','no_loc_info_available'))]),'no_loc_info_available')."
					+"\r\n'bindval'('B','listExp'('rangeClosed'(_x2,'val_of'('y','no_loc_info_available')),['comprehensionGenerator'(_x2,'int'(1)),'comprehensionGuard'(_x2),'comprehensionGuard'('val_of'('y','no_loc_info_available'))]),'no_loc_info_available')."
					+"\r\n'bindval'('C','listExp'('rangeOpen'(_x3),['comprehensionGenerator'(_x3,'int'(1)),'comprehensionGuard'(_x3),'comprehensionGuard'('val_of'('y','no_loc_info_available'))]),'no_loc_info_available')."
					+"\r\n'symbol'('y','y','no_loc_info_available','Ident (Groundrep.)')."
					+"\r\n'symbol'('A','A','no_loc_info_available','Ident (Groundrep.)')."
					+"\r\n'symbol'('x','x','no_loc_info_available','Ident (Prolog Variable)')."
					+"\r\n'symbol'('B','B','no_loc_info_available','Ident (Groundrep.)')."
					+"\r\n'symbol'('x2','x','no_loc_info_available','Ident (Prolog Variable)')."
					+"\r\n'symbol'('C','C','no_loc_info_available','Ident (Groundrep.)')."
					+"\r\n'symbol'('x3','x','no_loc_info_available','Ident (Prolog Variable)')."
			);		
	}
	
	@Test
	public void SetComprehensions() throws Exception
	{
		check(
					"y = 1"
					+"\r\nA = {x|x<-1,x,y}"
					+"\r\nB = {x..y|x<-1,x,y}"
					+"\r\nC = {x..|x<-1,x,y}"
					+"\r\nD = {|x|x<-1,x,y|}"
				,	
					"'bindval'('y','int'(1),'no_loc_info_available')."
					+"\r\n'bindval'('A','setExp'('rangeEnum'([_x]),['comprehensionGenerator'(_x,'int'(1)),'comprehensionGuard'(_x),'comprehensionGuard'('val_of'('y','no_loc_info_available'))]),'no_loc_info_available')."
					+"\r\n'bindval'('B','setExp'('rangeClosed'(_x2,'val_of'('y','no_loc_info_available')),['comprehensionGenerator'(_x2,'int'(1)),'comprehensionGuard'(_x2),'comprehensionGuard'('val_of'('y','no_loc_info_available'))]),'no_loc_info_available')."
					+"\r\n'bindval'('C','setExp'('rangeOpen'(_x3),['comprehensionGenerator'(_x3,'int'(1)),'comprehensionGuard'(_x3),'comprehensionGuard'('val_of'('y','no_loc_info_available'))]),'no_loc_info_available')."
					+"\r\n'bindval'('D','closureComp'(['comprehensionGenerator'(_x4,'int'(1)),'comprehensionGuard'(_x4),'comprehensionGuard'('val_of'('y','no_loc_info_available'))],[_x4]),'no_loc_info_available')."
					+"\r\n'symbol'('y','y','no_loc_info_available','Ident (Groundrep.)')."
					+"\r\n'symbol'('A','A','no_loc_info_available','Ident (Groundrep.)')."
					+"\r\n'symbol'('x','x','no_loc_info_available','Ident (Prolog Variable)')."
					+"\r\n'symbol'('B','B','no_loc_info_available','Ident (Groundrep.)')."
					+"\r\n'symbol'('x2','x','no_loc_info_available','Ident (Prolog Variable)')."
					+"\r\n'symbol'('C','C','no_loc_info_available','Ident (Groundrep.)')."
					+"\r\n'symbol'('x3','x','no_loc_info_available','Ident (Prolog Variable)')."
					+"\r\n'symbol'('D','D','no_loc_info_available','Ident (Groundrep.)')."
					+"\r\n'symbol'('x4','x','no_loc_info_available','Ident (Prolog Variable)')."
			);		
	}
	
	
	@Test
	public void IfThenElse() throws Exception //
	{
		check("m = if true then 1 else 1"
		,
			"'bindval'('m','ifte'('true','int'(1),'int'(1),'no_loc_info_available','no_loc_info_available','no_loc_info_available'),'no_loc_info_available')."
			+"\r\n'symbol'('m','m','no_loc_info_available','Ident (Groundrep.)')."
		);
	}
	@Test
	public void ParamNumeration() throws Exception //
	{
		check(
				"B(x,y) = SKIP"
				+"\r\nB(x,y) = 5"
				+"\r\nF(x,y) = (1,2)"
				+"\r\nG(a) = F(a)"
				+"\r\ny(_) = 1"				
				,
				"'agent'('B'(_x,_y),'SKIP','no_loc_info_available')."
				+"\r\n'agent'('B'(_x2,_y2),'int'(5),'no_loc_info_available')."
				+"\r\n'agent'('F'(_x3,_y3),'tupleExp'(['int'(1),'int'(2)]),'no_loc_info_available')."
				+"\r\n'agent'('G'(_a),'agent_call'('no_loc_info_available','F',[_a]),'no_loc_info_available')."
				+"\r\n'agent'('y4'(_),'int'(1),'no_loc_info_available')."
				+"\r\n'symbol'('B','B','no_loc_info_available','Function or Process')."
				+"\r\n'symbol'('x','x','no_loc_info_available','Ident (Prolog Variable)')."
				+"\r\n'symbol'('y','y','no_loc_info_available','Ident (Prolog Variable)')."
				+"\r\n'symbol'('x2','x','no_loc_info_available','Ident (Prolog Variable)')."
				+"\r\n'symbol'('y2','y','no_loc_info_available','Ident (Prolog Variable)')."
				+"\r\n'symbol'('F','F','no_loc_info_available','Function or Process')."
				+"\r\n'symbol'('x3','x','no_loc_info_available','Ident (Prolog Variable)')."
				+"\r\n'symbol'('y3','y','no_loc_info_available','Ident (Prolog Variable)')."
				+"\r\n'symbol'('G','G','no_loc_info_available','Function or Process')."
				+"\r\n'symbol'('a','a','no_loc_info_available','Ident (Prolog Variable)')."
				+"\r\n'symbol'('y4','y','no_loc_info_available','Function or Process')."
				+"\r\n'symbol'('SKIP','SKIP','no_loc_info_available','BuiltIn primitive')."
			);
	}
	
	@Test
	public void Chars() throws Exception //not supported by cspmf
	{
		check(
					"E = 'a'"
					,
					"'bindval'('E','char'('a'),'no_loc_info_available')."
					+"\r\n'symbol'('E','E','no_loc_info_available','Ident (Groundrep.)')."
					);
	}
	
	@Test
	public void Maps() throws Exception //not supported by cspmf
	{
		check(
					"C = (| |)\r\nD = (|1=>2|)"
					,
					"'bindval'('C','emptyMap','no_loc_info_available')."
					+"\r\n'bindval'('D','mapExp'(['int'(1),'int'(2)]),'no_loc_info_available')."
					+"\r\n'symbol'('C','C','no_loc_info_available','Ident (Groundrep.)')."
					+"\r\n'symbol'('D','D','no_loc_info_available','Ident (Groundrep.)')."
					);
	}
	
	@Test
	public void TypeDefs() throws Exception //
	{
		check(	
				"datatype dt = cn|cn2.Bool"
				+"\r\nsubtype st = cn"
				+"\r\nnametype nt = Bool.Int"
				+"\r\nchannel c1,c2,c3: Bool.Int"
				,
				"'dataTypeDef'('dt',['constructor'('cn'),'constructorC'('cn2','dotTupleType'(['Bool']))])."
				+"\r\n'subTypeDef'('st',['constructor'('cn')])."
				+"\r\n'nameType'('nt','type'('dotTupleType'(['Bool','Int'])))."
				+"\r\n'channel'('c1','type'('dotTupleType'(['Bool','Int'])))."
				+"\r\n'channel'('c2','type'('dotTupleType'(['Bool','Int'])))."
				+"\r\n'channel'('c3','type'('dotTupleType'(['Bool','Int'])))."
				+"\r\n'symbol'('dt','dt','no_loc_info_available','Datatype')."
				+"\r\n'symbol'('cn','cn','no_loc_info_available','Constructor of Datatype')."
				+"\r\n'symbol'('cn2','cn2','no_loc_info_available','Constructor of Datatype')."
				+"\r\n'symbol'('st','st','no_loc_info_available','Datatype')."
				+"\r\n'symbol'('nt','nt','no_loc_info_available','Nametype')."
				+"\r\n'symbol'('c1','c1','no_loc_info_available','Channel')."
				+"\r\n'symbol'('c2','c2','no_loc_info_available','Channel')."
				+"\r\n'symbol'('c3','c3','no_loc_info_available','Channel')."
				+"\r\n'symbol'('Bool','Bool','no_loc_info_available','BuiltIn primitive')."
				+"\r\n'symbol'('Int','Int','no_loc_info_available','BuiltIn primitive')."
			);
	}
	
	@Test
	public void MultipleAgentDefs() throws Exception //
	{
		check(
				"a = 1"
				+"\r\nA = 1"
				+"\r\nI(a) = A"
				+"\r\nI(a) = a"
				+"\r\nI(A) = a"
				+"\r\nI(A) = A"
				,
				"'bindval'('a','int'(1),'no_loc_info_available')."
				+"\r\n'bindval'('A','int'(1),'no_loc_info_available')."
				+"\r\n'agent'('I'(_a2),'val_of'('A','no_loc_info_available'),'no_loc_info_available')."
				+"\r\n'agent'('I'(_a3),_a3,'no_loc_info_available')."
				+"\r\n'agent'('I'(_A2),'val_of'('a','no_loc_info_available'),'no_loc_info_available')."
				+"\r\n'agent'('I'(_A3),_A3,'no_loc_info_available')."
				+"\r\n'symbol'('a','a','no_loc_info_available','Ident (Groundrep.)')."
				+"\r\n'symbol'('A','A','no_loc_info_available','Ident (Groundrep.)')."
				+"\r\n'symbol'('I','I','no_loc_info_available','Function or Process')."
				+"\r\n'symbol'('a2','a','no_loc_info_available','Ident (Prolog Variable)')."
				+"\r\n'symbol'('a3','a','no_loc_info_available','Ident (Prolog Variable)')."
				+"\r\n'symbol'('A2','A','no_loc_info_available','Ident (Prolog Variable)')."
				+"\r\n'symbol'('A3','A','no_loc_info_available','Ident (Prolog Variable)')."							
			);
	}
	@Test
	public void RedefineBuiltins() throws Exception //cspmf comparison not possible
	{
		check("STOP = 1\r\nSKIP = 1"
		,
			"'bindval'('STOP','int'(1),'no_loc_info_available').\r\n'bindval'('SKIP','int'(1),'no_loc_info_available')."
			+"\r\n'symbol'('STOP','STOP','no_loc_info_available','Ident (Groundrep.)')."
			+"\r\n'symbol'('SKIP','SKIP','no_loc_info_available','Ident (Groundrep.)')."
		);
	}
	@Test
	public void CallSingleBuiltin() throws Exception //cspmf comparison not possible
	{
		check("P=STOP",
		
				 "'bindval'('P','STOP','no_loc_info_available')."
				+"\r\n'symbol'('P','P','no_loc_info_available','Ident (Groundrep.)')."
				+"\r\n'symbol'('STOP','STOP','no_loc_info_available','BuiltIn primitive').");
	}
	
	@Test
	public void CallBuiltinsMultipleTimes() throws Exception //cspmf comparison not possible
	{
		check( 		"P = STOP"
					+"\r\nQ = STOP"
					+"\r\nV = SKIP"
					+"\r\nW = SKIP"
				,
					"'bindval'('P','STOP','no_loc_info_available')."
					+"\r\n'bindval'('Q','STOP','no_loc_info_available')."
					+"\r\n'bindval'('V','SKIP','no_loc_info_available')."
					+"\r\n'bindval'('W','SKIP','no_loc_info_available')."
					+"\r\n'symbol'('P','P','no_loc_info_available','Ident (Groundrep.)')."
					+"\r\n'symbol'('Q','Q','no_loc_info_available','Ident (Groundrep.)')."
					+"\r\n'symbol'('V','V','no_loc_info_available','Ident (Groundrep.)')."
					+"\r\n'symbol'('W','W','no_loc_info_available','Ident (Groundrep.)')."
					+"\r\n'symbol'('STOP','STOP','no_loc_info_available','BuiltIn primitive')."
					+"\r\n'symbol'('SKIP','SKIP','no_loc_info_available','BuiltIn primitive')."
				);
	}
	
	@Test
	public void ExpressionsNewlinesBetween() throws Exception //
	{
	  check(
				"b= 2 \r\n a = true \r\n       and     \r\n b==1"
			, 
				"'bindval'('b','int'(2),'no_loc_info_available')."
				+"\r\n'bindval'('a','bool_and'('true','=='('val_of'('b','no_loc_info_available'),'int'(1))),'no_loc_info_available')."
				+"\r\n'symbol'('b','b','no_loc_info_available','Ident (Groundrep.)')."
				+"\r\n'symbol'('a','a','no_loc_info_available','Ident (Groundrep.)')."
			);	
	}
	
	@Test
	public void LetWithinTest() throws Exception //
	{
		check(			
				"channel a"
				+"\r\n R(b) = let" 
							+"\r\n b = STOP" 
					  +"\r\n within a-> b "
				+"\r\n Q = a-> a -> SKIP"
			,				
				"'channel'('a','type'('dotUnitType'))."
				+"\r\n'agent'('R'(_b),'let'(['bindval'('b2','STOP','no_loc_info_available')],'prefix'('no_loc_info_available',[],'a','val_of'('b2','no_loc_info_available'),'no_loc_info_available')),'no_loc_info_available')."
				+"\r\n'bindval'('Q','prefix'('no_loc_info_available',[],'a','prefix'('no_loc_info_available',[],'a','SKIP','no_loc_info_available'),'no_loc_info_available'),'no_loc_info_available')."
				+"\r\n'symbol'('a','a','no_loc_info_available','Channel')."
				+"\r\n'symbol'('R','R','no_loc_info_available','Function or Process')."
				+"\r\n'symbol'('b','b','no_loc_info_available','Ident (Prolog Variable)')."
				+"\r\n'symbol'('b2','b','no_loc_info_available','Ident (Groundrep.)')."
				+"\r\n'symbol'('Q','Q','no_loc_info_available','Ident (Groundrep.)')."
				+"\r\n'symbol'('STOP','STOP','no_loc_info_available','BuiltIn primitive')."
				+"\r\n'symbol'('SKIP','SKIP','no_loc_info_available','BuiltIn primitive')."
			);
	}
	
	@Test
	public void FunctionCalls() throws Exception //
	{
		check("A = 1.A(1)",
		"'bindval'('A','dotTuple'(['int'(1),'agent_call'('no_loc_info_available','val_of'('A','no_loc_info_available'),['int'(1)])]),'no_loc_info_available')."
		+"\r\n'symbol'('A','A','no_loc_info_available','Ident (Groundrep.)')."
		);
	}
	
	@Test
	public void Functional() throws Exception //
	{
	  check(	
				"A(x) = (1?x->1)(x)+x"
				+"\r\nA(x) = (1?x->1,x)"
				+"\r\n1 = ((1)(1))(1)"
			,		
				"'agent'('A'(_x),'+'('agent_call'('no_loc_info_available','prefix'('no_loc_info_available',['in'(_x2)],'int'(1),'int'(1),'no_loc_info_available'),[_x]),_x),'no_loc_info_available')."
				+"\r\n'agent'('A'(_x3),'tupleExp'(['prefix'('no_loc_info_available',['in'(_x4)],'int'(1),'int'(1),'no_loc_info_available'),_x3]),'no_loc_info_available')."
				+"\r\n'bindval'('int'(1),'agent_call'('no_loc_info_available','agent_call'('no_loc_info_available','int'(1),['int'(1)]),['int'(1)]),'no_loc_info_available')."
				+"\r\n'symbol'('A','A','no_loc_info_available','Function or Process')."
				+"\r\n'symbol'('x','x','no_loc_info_available','Ident (Prolog Variable)')."
				+"\r\n'symbol'('x2','x','no_loc_info_available','Ident (Prolog Variable)')."
				+"\r\n'symbol'('x3','x','no_loc_info_available','Ident (Prolog Variable)')."
				+"\r\n'symbol'('x4','x','no_loc_info_available','Ident (Prolog Variable)')."		
			);
	}
	
	@Test
	public void ProcOperators() throws Exception //
	{
	  check(	"J = 1 \\ 2"
				+"\r\nK = 1 ||| 2"
				+"\r\nL = 2 [|3|> 4"
				+"\r\nM = 2 [|3|] 4"
				+"\r\nN = 2 [3||4] 5"
				+"\r\nO = 2 [3<->4] 5"
				+"\r\nP = 2 [3<->4,6<->7] 5"
				+"\r\nQ = 2 |~| 3"
				+"\r\nR = 2 [] 3"
				+"\r\nS = 2 [+ 3 +] 4"
				+"\r\nT = 2 /\\ 3"
				+"\r\nU = 2 /+ 1 +\\ 3"
				+"\r\nV = 2 [> 3"
				+"\r\nW = 2 ; 3"
				+"\r\nX = 2 & 3"
				+"\r\nY = 1 -> 5"
				+"\r\nn = [] true @ 1"
				+"\r\no = |~| 1:2 @ 1"
				+"\r\np = ||| 1:2,true @ 2"
				+"\r\nq = || 1 @ [true] 1"
				+"\r\nr = ; 1 @ 2"
				+"\r\ns = [|true|] 1 @ 2"
				+"\r\nt = [1<->2] true @ true"
				+"\r\nu = [+1+] true @ true"
				,
				"'bindval'('J','\\'('int'(1),'int'(2),'src_span_operator'('no_loc_info_available','no_loc_info_available')),'no_loc_info_available')."
				+"\r\n'bindval'('K','|||'('int'(1),'int'(2),'src_span_operator'('no_loc_info_available','no_loc_info_available')),'no_loc_info_available')."
				+"\r\n'bindval'('L','exception'('int'(3),'int'(2),'int'(4),'no_loc_info_available'),'no_loc_info_available')."
				+"\r\n'bindval'('M','sharing'('int'(3),'int'(2),'int'(4),'no_loc_info_available'),'no_loc_info_available')."
				+"\r\n'bindval'('N','aParallel'('int'(3),'int'(2),'int'(4),'int'(5),'no_loc_info_available'),'no_loc_info_available')."
				+"\r\n'bindval'('O','lParallel'('linkList'(['link'('int'(3),'int'(4))]),'int'(2),'int'(5),'no_loc_info_available'),'no_loc_info_available')."
				+"\r\n'bindval'('P','lParallel'('linkList'(['link'('int'(3),'int'(4)),'link'('int'(6),'int'(7))]),'int'(2),'int'(5),'no_loc_info_available'),'no_loc_info_available')."
				+"\r\n'bindval'('Q','|~|'('int'(2),'int'(3),'src_span_operator'('no_loc_info_available','no_loc_info_available')),'no_loc_info_available')."
				+"\r\n'bindval'('R','[]'('int'(2),'int'(3),'src_span_operator'('no_loc_info_available','no_loc_info_available')),'no_loc_info_available')."
				+"\r\n'bindval'('S','syncExtChoice'('int'(2),'int'(3),'int'(4),'no_loc_info_available'),'no_loc_info_available')."
				+"\r\n'bindval'('T','/\\'('int'(2),'int'(3),'src_span_operator'('no_loc_info_available','no_loc_info_available')),'no_loc_info_available')."
				+"\r\n'bindval'('U','syncInterrupt'('int'(2),'int'(1),'int'(3),'no_loc_info_available'),'no_loc_info_available')."
				+"\r\n'bindval'('V','[>'('int'(2),'int'(3),'src_span_operator'('no_loc_info_available','no_loc_info_available')),'no_loc_info_available')."
				+"\r\n'bindval'('W',';'('int'(2),'int'(3),'src_span_operator'('no_loc_info_available','no_loc_info_available')),'no_loc_info_available')."
				+"\r\n'bindval'('X','&'('int'(2),'int'(3)),'no_loc_info_available')."
				+"\r\n'bindval'('Y','prefix'('no_loc_info_available',[],'int'(1),'int'(5),'no_loc_info_available'),'no_loc_info_available')."
				+"\r\n'bindval'('n','repChoice'(['comprehensionGuard'('true')],'int'(1),'no_loc_info_available'),'no_loc_info_available')."
				+"\r\n'bindval'('o','repInternalChoice'(['comprehensionGenerator'('int'(1),'int'(2))],'int'(1),'no_loc_info_available'),'no_loc_info_available')."
				+"\r\n'bindval'('p','repInterleave'(['comprehensionGenerator'('int'(1),'int'(2)),'comprehensionGuard'('true')],'int'(2),'no_loc_info_available'),'no_loc_info_available')."
				+"\r\n'bindval'('q','procRepAParallel'(['comprehensionGuard'('int'(1))],'pair'('true','int'(1)),'no_loc_info_available'),'no_loc_info_available')."
				+"\r\n'bindval'('r','repSequence'(['comprehensionGuard'('int'(1))],'int'(2),'no_loc_info_available'),'no_loc_info_available')."
				+"\r\n'bindval'('s','procRepSharing'('true',['comprehensionGuard'('int'(1))],'int'(2),'no_loc_info_available'),'no_loc_info_available')."
				+"\r\n'bindval'('t','procRepLinkParallel'('linkList'(['link'('int'(1),'int'(2))]),['comprehensionGuard'('true')],'true','no_loc_info_available'),'no_loc_info_available')."
				+"\r\n'bindval'('u','procRepSyncParallel'('int'(1),['comprehensionGuard'('true')],'true','no_loc_info_available'),'no_loc_info_available')."
				+"\r\n'symbol'('J','J','no_loc_info_available','Ident (Groundrep.)')."
				+"\r\n'symbol'('K','K','no_loc_info_available','Ident (Groundrep.)')."
				+"\r\n'symbol'('L','L','no_loc_info_available','Ident (Groundrep.)')."
				+"\r\n'symbol'('M','M','no_loc_info_available','Ident (Groundrep.)')."
				+"\r\n'symbol'('N','N','no_loc_info_available','Ident (Groundrep.)')."
				+"\r\n'symbol'('O','O','no_loc_info_available','Ident (Groundrep.)')."
				+"\r\n'symbol'('P','P','no_loc_info_available','Ident (Groundrep.)')."
				+"\r\n'symbol'('Q','Q','no_loc_info_available','Ident (Groundrep.)')."
				+"\r\n'symbol'('R','R','no_loc_info_available','Ident (Groundrep.)')."
				+"\r\n'symbol'('S','S','no_loc_info_available','Ident (Groundrep.)')."
				+"\r\n'symbol'('T','T','no_loc_info_available','Ident (Groundrep.)')."
				+"\r\n'symbol'('U','U','no_loc_info_available','Ident (Groundrep.)')."
				+"\r\n'symbol'('V','V','no_loc_info_available','Ident (Groundrep.)')."
				+"\r\n'symbol'('W','W','no_loc_info_available','Ident (Groundrep.)')."
				+"\r\n'symbol'('X','X','no_loc_info_available','Ident (Groundrep.)')."
				+"\r\n'symbol'('Y','Y','no_loc_info_available','Ident (Groundrep.)')."
				+"\r\n'symbol'('n','n','no_loc_info_available','Ident (Groundrep.)')."
				+"\r\n'symbol'('o','o','no_loc_info_available','Ident (Groundrep.)')."
				+"\r\n'symbol'('p','p','no_loc_info_available','Ident (Groundrep.)')."
				+"\r\n'symbol'('q','q','no_loc_info_available','Ident (Groundrep.)')."
				+"\r\n'symbol'('r','r','no_loc_info_available','Ident (Groundrep.)')."
				+"\r\n'symbol'('s','s','no_loc_info_available','Ident (Groundrep.)')."
				+"\r\n'symbol'('t','t','no_loc_info_available','Ident (Groundrep.)')."
				+"\r\n'symbol'('u','u','no_loc_info_available','Ident (Groundrep.)')."
			);
	}
	
	@Test
	public void LetWithinDifficult() throws Exception //
	{
	  check(
				"k = let\r\nI(a) = 1\r\nI(a) = let\r\nI(a) = a\r\nwithin 8\r\nI(a) = 3\r\nwithin 9"
				+"\r\nl(x) =  let\r\nu = 1\r\nb(u) = let\r\nc = u\r\nwithin 1\r\nwithin x"
			,
				"'bindval'('k','let'(['agent'('I'(_a),'int'(1),'no_loc_info_available'),'agent'('I'(_a2),'let'(['agent'('I2'(_a3),_a3,'no_loc_info_available')],'int'(8)),'no_loc_info_available'),'agent'('I'(_a4),'int'(3),'no_loc_info_available')],'int'(9)),'no_loc_info_available')."
				+"\r\n'agent'('l'(_x),'let'(['bindval'('u','int'(1),'no_loc_info_available'),'agent'('b'(_u2),'let'(['bindval'('c',_u2,'no_loc_info_available')],'int'(1)),'no_loc_info_available')],_x),'no_loc_info_available')."
				+"\r\n'symbol'('k','k','no_loc_info_available','Ident (Groundrep.)')."
				+"\r\n'symbol'('I','I','no_loc_info_available','Function or Process')."
				+"\r\n'symbol'('a','a','no_loc_info_available','Ident (Prolog Variable)')."
				+"\r\n'symbol'('a2','a','no_loc_info_available','Ident (Prolog Variable)')."
				+"\r\n'symbol'('I2','I','no_loc_info_available','Function or Process')."
				+"\r\n'symbol'('a3','a','no_loc_info_available','Ident (Prolog Variable)')."
				+"\r\n'symbol'('a4','a','no_loc_info_available','Ident (Prolog Variable)')."
				+"\r\n'symbol'('l','l','no_loc_info_available','Function or Process')."
				+"\r\n'symbol'('x','x','no_loc_info_available','Ident (Prolog Variable)')."
				+"\r\n'symbol'('u','u','no_loc_info_available','Ident (Groundrep.)')."
				+"\r\n'symbol'('b','b','no_loc_info_available','Function or Process')."
				+"\r\n'symbol'('u2','u','no_loc_info_available','Ident (Prolog Variable)')."
				+"\r\n'symbol'('c','c','no_loc_info_available','Ident (Groundrep.)')."
			);
	}
	
	@Test
	public void Lambda() throws Exception //
	{
	  check(
				"h = \\a,b@3\r\ni(a) = \\b@a+b\r\na = 1"
			,
				"'bindval'('h','lambda'([_a,_b],'int'(3)),'no_loc_info_available')."
				+"\r\n'agent'('i'(_a2),'lambda'([_b2],'+'(_a2,_b2)),'no_loc_info_available')."
				+"\r\n'bindval'('a3','int'(1),'no_loc_info_available')."
				+"\r\n'symbol'('h','h','no_loc_info_available','Ident (Groundrep.)')."
				+"\r\n'symbol'('a','a','no_loc_info_available','Ident (Prolog Variable)')."
				+"\r\n'symbol'('b','b','no_loc_info_available','Ident (Prolog Variable)')."
				+"\r\n'symbol'('i','i','no_loc_info_available','Function or Process')."
				+"\r\n'symbol'('a2','a','no_loc_info_available','Ident (Prolog Variable)')."
				+"\r\n'symbol'('b2','b','no_loc_info_available','Ident (Prolog Variable)')."
				+"\r\n'symbol'('a3','a','no_loc_info_available','Ident (Groundrep.)')."			
			);
	}
	
	@Test
	public void InputOutput() throws Exception //
	{
		check(  "Z = 1?1.2 -> 5"
				+"\r\na(d) = 1?1.2:1.2 -> 5"
				+"\r\na(d) = 1"
				+"\r\nb = 1$1.2 -> 5"
				+"\r\nc = 1$1.2:1.2 -> 5"
				+"\r\nd = 1!1.2 -> 5"
				+"\r\ne = 1?1@@2 -> 5"
				+"\r\nf = 1?1^2 -> 5"
				+"\r\ng = 1^2"
				+"\r\nj(y) = 1?y -> y"
				,
				"'bindval'('Z','prefix'('no_loc_info_available',['in'('dotpat'(['int'(1),'int'(2)]))],'int'(1),'int'(5),'no_loc_info_available'),'no_loc_info_available')."
				+"\r\n'agent'('a'(_d),'prefix'('no_loc_info_available',['inGuard'('dotpat'(['int'(1),'int'(2)]),'dotTuple'(['int'(1),'int'(2)]))],'int'(1),'int'(5),'no_loc_info_available'),'no_loc_info_available')."
				+"\r\n'agent'('a'(_d2),'int'(1),'no_loc_info_available')."
				+"\r\n'bindval'('b','prefix'('no_loc_info_available',['nondetIn'('dotpat'(['int'(1),'int'(2)]))],'int'(1),'int'(5),'no_loc_info_available'),'no_loc_info_available')."
				+"\r\n'bindval'('c','prefix'('no_loc_info_available',['nondetInGuard'('dotpat'(['int'(1),'int'(2)]),'dotTuple'(['int'(1),'int'(2)]))],'int'(1),'int'(5),'no_loc_info_available'),'no_loc_info_available')."
				+"\r\n'bindval'('d3','prefix'('no_loc_info_available',['out'('dotTuple'(['int'(1),'int'(2)]))],'int'(1),'int'(5),'no_loc_info_available'),'no_loc_info_available')."
				+"\r\n'bindval'('e','prefix'('no_loc_info_available',['in'('alsoPattern'(['int'(1),'int'(2)]))],'int'(1),'int'(5),'no_loc_info_available'),'no_loc_info_available')."
				+"\r\n'bindval'('f','prefix'('no_loc_info_available',['in'('appendPattern'(['int'(1),'int'(2)]))],'int'(1),'int'(5),'no_loc_info_available'),'no_loc_info_available')."
				+"\r\n'bindval'('g','^'('int'(1),'int'(2)),'no_loc_info_available')."
				+"\r\n'agent'('j'(_y),'prefix'('no_loc_info_available',['in'(_y2)],'int'(1),_y2,'no_loc_info_available'),'no_loc_info_available')."
				+"\r\n'symbol'('Z','Z','no_loc_info_available','Ident (Groundrep.)')."
				+"\r\n'symbol'('a','a','no_loc_info_available','Function or Process')."
				+"\r\n'symbol'('d','d','no_loc_info_available','Ident (Prolog Variable)')."
				+"\r\n'symbol'('d2','d','no_loc_info_available','Ident (Prolog Variable)')."
				+"\r\n'symbol'('b','b','no_loc_info_available','Ident (Groundrep.)')."
				+"\r\n'symbol'('c','c','no_loc_info_available','Ident (Groundrep.)')."
				+"\r\n'symbol'('d3','d','no_loc_info_available','Ident (Groundrep.)')."
				+"\r\n'symbol'('e','e','no_loc_info_available','Ident (Groundrep.)')."
				+"\r\n'symbol'('f','f','no_loc_info_available','Ident (Groundrep.)')."
				+"\r\n'symbol'('g','g','no_loc_info_available','Ident (Groundrep.)')."
				+"\r\n'symbol'('j','j','no_loc_info_available','Function or Process')."
				+"\r\n'symbol'('y','y','no_loc_info_available','Ident (Prolog Variable)')."
				+"\r\n'symbol'('y2','y','no_loc_info_available','Ident (Prolog Variable)')."
			);
	}
	
	@Test
	public void ExpressionOperators() throws Exception //
	{
		check(
				"v = -(1^2)^2"
				+"\r\nw = 0-1+2*3/4%-5"
				+"\r\nx = #1"
				+"\r\nz = true or false and not true"
		,
			"'bindval'('v','negate'('^'('^'('int'(1),'int'(2)),'int'(2))),'no_loc_info_available')."
			+"\r\n'bindval'('w','+'('-'('int'(0),'int'(1)),'%'('/'('*'('int'(2),'int'(3)),'int'(4)),'int'(-5))),"
			+"'no_loc_info_available')."
			+"\r\n'bindval'('x','#'('int'(1)),'no_loc_info_available')."
			+"\r\n'bindval'('z','bool_or'('true','bool_and'('false','bool_not'('true'))),'no_loc_info_available')."
			+"\r\n'symbol'('v','v','no_loc_info_available','Ident (Groundrep.)')."
			+"\r\n'symbol'('w','w','no_loc_info_available','Ident (Groundrep.)')."
			+"\r\n'symbol'('x','x','no_loc_info_available','Ident (Groundrep.)')."
			+"\r\n'symbol'('z','z','no_loc_info_available','Ident (Groundrep.)')."
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
		System.out.println(expected+"\r\n"+actual);
		assertEquals(expected, actual);
	}
	
	public void checkCSPMF(final String input, final String expected)
	{
		String actual;
		try 
		{
			actual = parseStringWithoutRenaming(input);
		} 
		catch (CSPMparserException e) 
		{
			actual = "";
		}
		System.out.println(expected+"\r\n"+actual);
		assertEquals(expected, actual);		
	}

	public String cspmfCompileToProlog(String input) throws Exception
	{
		
		String output = "";
		String[] command = {"cmd"};
		Process p = null;			
		OsCheck.OSType ostype = OsCheck.getOperatingSystemType();
		

		if(ostype == OsCheck.OSType.Windows)
		{							
			PrintWriter writer = new PrintWriter("build\\classes\\main\\cspmfIN.temp", "UTF-8");
			writer.println(input);
			writer.close();	
			p = Runtime.getRuntime().exec("build\\classes\\main\\cspmf.exe translate build\\classes\\main\\cspmfIN.temp --prologOutNormalised=build\\classes\\main\\cspmfOUT.temp");
			p.waitFor();			
			output = getStringFromFile("build\\classes\\main\\cspmfOUT.temp");				
			Files.delete(Paths.get("build\\classes\\main\\cspmfOUT.temp"));
			Files.delete(Paths.get("build\\classes\\main\\cspmfIN.temp"));			
		}
		else if(ostype == OsCheck.OSType.MacOS || ostype == OsCheck.OSType.Linux32 || ostype == OsCheck.OSType.Linux64)
		{
			PrintWriter writer = new PrintWriter("build/classes/main/cspmfIN.temp", "UTF-8");
			writer.println(input);
			writer.close();
			p = Runtime.getRuntime().exec("build/classes/main/cspmf translate build/classes/main/cspmfIN.temp --prologOutNormalised=build/classes/main/cspmfOUT.temp");
			p.waitFor();
			output = getStringFromFile("build/classes/main/cspmfOUT.temp");		
			if(ostype == OsCheck.OSType.MacOS)
			{
				output = output.replaceAll("\r","\r\n");
			}
			else
			{
				output = output.replaceAll("\n","\r\n");
			}
			Files.delete(Paths.get("build/classes/main/cspmfOUT.temp"));
			Files.delete(Paths.get("build/classes/main/cspmfIN.temp"));
		}
		else
		{
			throw new RuntimeException("This Operating System is not supported!");
		}
		return output;
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
		return parser.parseString(input,true);
	}
	
	private String parseStringWithoutRenaming(final String input) throws CSPMparserException 
	{
		CSPMparser parser = new CSPMparser();
		return parser.parseString(input,false);
	}

	public String getStringFromFile(String fp)
	{
		try 
		{	
			byte[] encoded = Files.readAllBytes(Paths.get(fp));
			String file_content = new String(encoded, StandardCharsets.UTF_8);
			return file_content;
		}
		catch(Exception e) 		
		{
			throw new RuntimeException("Your File has not been converted to a String.");
		}
	}
}
