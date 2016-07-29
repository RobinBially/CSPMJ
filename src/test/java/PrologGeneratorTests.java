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
	private OsCheck.OSType ostype;
	private String newline;
	
	public PrologGeneratorTests()
	{
		ostype = OsCheck.getOperatingSystemType();
		if(ostype == OsCheck.OSType.Windows)
			newline = "\r\n";
		else
			newline = "\n";
	}
	
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
						+newline+"l(x) =  let\r\nu = 1\r\nb(u) = let\r\nc = u\r\nwithin 1\r\nwithin x";
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
						+newline+"A(x) = (1?x->1,x)"
						+newline+"1 = ((1)(1))(1)";
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
						+newline+" R(b) = let" 
									+newline+" b = a" 
							  +newline+" within a-> b "
						+newline+" Q = a-> a -> a";
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
						+newline+"A = 1"
						+newline+"I(a) = A"
						+newline+"I(a) = a"
						+newline+"I(A) = a"
						+newline+"I(A) = A";
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
						+newline+"subtype st = cn"
						+newline+"nametype nt = {1}.{1}"
						+newline+"channel c1,c2,c3: {1}.{1}";
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
						+newline+"B(x,y) = 5"
						+newline+"F(x,y) = (1,2)"
						+newline+"G(a) = F(a)"
						+newline+"y(_) = 1"	;
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
						+newline+"A = {x|x<-1,x,y}"
						+newline+"B = {x..y|x<-1,x,y}"
						+newline+"C = {x..|x<-1,x,y}"
						+newline+"D = {|x|x<-1,x,y|}";
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
							+newline+"A = <x|x<-1,x,y>"
							+newline+"B = <x..y|x<-1,x,y>"
							+newline+"C = <x..|x<-1,x,y>";
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
							+newline+"B = {x|x<-1,{1|x},x<-2}"
							+newline+"C = {1|x<-1,x,x<-2,x}";
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
						+newline+"a(d) = 1?1.2:1.2 -> 5"
						+newline+"a(d) = 1"
					//	+newline+"b = 1$1.2 -> 5" 		Restricted Input is not supported by cspmf
					//	+newline+"c = 1$1.2:1.2 -> 5" 	Restricted Input is not supported by cspmf
						+newline+"d = 1!1.2 -> 5"
						+newline+"e = 1?1@@2 -> 5"
						+newline+"f = 1?1^2 -> 5"
						+newline+"g = 1^2"
						+newline+"j(y) = 1?y -> y";
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
							+newline+"a = 1"
							+newline+"A(a) = a[[a <- d,a<-d]]"
							+newline+"b = 1"
							+newline+"B = b[[b<-d,b<-d|b<-1]]"
							+newline+"c = 1"
							+newline+"C(c) = c[[c<-d,c<-d|c<-1]]";
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
								+newline+"x(a) = 1"
								+newline+"b(a) = 1"
								+newline+"c = 1?d -> d"
								+newline+"channel e: c";
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
						+newline+"C = let D = member"
						+newline+"member = 1"
						+newline+"within 1";
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
						+newline+"A = f";
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
						+newline+"L = 2 [|3|> 4"
						+newline+"M = 2 [|3|] 4"
						+newline+"N = 2 [3||4] 5"
						+newline+"O = 2 [3<->4] 5"
						+newline+"P = 2 [3<->4,6<->7] 5"
						+newline+"Q = 2 |~| 3"
						+newline+"R = 2 [] 3"
					//	+newline+"S = 2 [+ 3 +] 4" not supported by cspmf
					//	+newline+"T = 2 /\\ 3"	 cspmf does not escape
					//	+newline+"U = 2 /+ 1 +\\ 3" not supported by cspmf
						+newline+"V = 2 [> 3"
						+newline+"W = 2 ; 3"
						+newline+"X = 2 & 3"
						+newline+"Y = 1 -> 5"
						+newline+"n = [] true @ 1"
						+newline+"o = |~| 1:2 @ 1"
						+newline+"p = ||| 1:2,true @ 2"
						+newline+"q = || 1 @ [true] 1"
						+newline+"r = ; 1 @ 2"
						+newline+"s = [|true|] 1 @ 2"
						+newline+"t = [1<->2] true @ true";
					//	+newline+"u = [+1+] true @ true"; not supported by cspmf
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
						+newline+"w = 0-1+2*3/4%-5"
						+newline+"x = #1"
						+newline+"z = true or false and not true"
						+newline+"u = -1+2";
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
					+newline+"assert 1 [F= 1"
					+newline+"assert 1 [FD= 1"
					+newline+"assert not 1->2 [R= 2"
					+newline+"assert 1 |= LTL: \"true\""
					+newline+"assert 1 |= CTL: \"true\""
					+newline+"assert 1 :[deadlock free]"
					+newline+"assert 1 :[deadlock free [F]]"
					+newline+"assert 1 :[deadlock free [FD]]"
					+newline+"assert 1 :[deadlock free [T]]"
					+newline+"assert 1 :[divergence free]"
					+newline+"assert 1 :[divergence free [F]]"
					+newline+"assert 1 :[divergence free [FD]]"
					+newline+"assert 1 :[divergence free [T]]"
					+newline+"assert 1 :[livelock free]"
					+newline+"assert 1 :[livelock free [F]]"
					+newline+"assert 1 :[livelock free [FD]]"
					+newline+"assert 1 :[livelock free [T]]"
					+newline+"assert 1 :[deterministic]"
					+newline+"assert 1 :[deterministic [T]]"
					+newline+"assert 1 :[deterministic [F]]"
					+newline+"assert 1 :[deterministic [FD]]";
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
					+newline+"E(x) = {let x = 1"
					+newline+"1 = {x|1}"
					+newline+"within 2 |x<-1,let 1 = x within 2}"
					+newline+"F = {1|x<-1,x,x<-2,x}";
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
				+newline+"{1} = 1"
				+newline+"_ = 3"
				+newline+"<> = 3"
				+newline+"<1,2> = 5"
				+newline+"1 = {1|_<-2}"
				+newline+"1 = {1|{}<-2}"
				+newline+"1 = {1|{1}<-2}"
				+newline+"1 = {1|<><-2}"
				+newline+"1 = {1|<1,2,3><-2}"
				+newline+"1 = {1|(x,y)<-2,x}"
				+newline+"1 = {1|(x)<-2,x}"
				+newline+"1 = {1|(a.b^c@@(d),e)<-2,a,b,c,d,e}";
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
						+newline+"B = [] 1 @  1 -> 2"
						+newline+"C = \\1@1 -> 1"
						+newline+"D = 1 -> \\1@1"
						+newline+"E = let 1=1 within 1->1"
						+newline+"F = 1 -> let 1=1 within 1"
						+newline+"G = let 1=1 within \\1@1"
						+newline+"H = \\1@let 1=1 within 1"
						+newline+"I = 1?1.2.3:{1}.1 -> 1";
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
						+newline+"nocurry(1,2) = nocurry(2,1)"
						+newline+"curry(1)(2) = 1"
						+newline+"1 = curry(1)(2)"
						+newline+"1 = (1)(2)(3)"
						+newline+"1 = (1,2)(3,4)(5,6)"
						+newline+"channel c: {1}.curry(1)(2)";
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
				+newline+"assert 1 [FD= 2 :[tau priority 1]"
				+newline+"assert 1 [F= 2 :[tau priority 1]"
				+newline+"assert 1 [R= 2 :[tau priority 1]"
			,
				"'assertTauPrio'('False','int'(1),'TauTrace','int'(2),'int'(1),'no_loc_info_available')."
				+newline+"'assertTauPrio'('False','int'(1),'TauFailureDivergence','int'(2),'int'(1),'no_loc_info_available')."
				+newline+"'assertTauPrio'('False','int'(1),'TauFailure','int'(2),'int'(1),'no_loc_info_available')."
				+newline+"'assertTauPrio'('False','int'(1),'TauRefusalTesting','int'(2),'int'(1),'no_loc_info_available')."
			);
	}
	
	@Test
	public void Curry() throws Exception //
	{
		check(
					"nocurry(1) = nocurry(2)"
					+newline+"nocurry(1,2) = nocurry(2,1)"
					+newline+"curry(1)(2) = 1"
					+newline+"1 = curry(1)(2)"
					+newline+"1 = (1)(2)(3)"
					+newline+"1 = (1,2)(3,4)(5,6)"
					+newline+"channel c: {1}.curry(1)(2)"

				,
					"'agent'('nocurry'('int'(1)),'agent_call'('no_loc_info_available','nocurry',['int'(2)]),'no_loc_info_available')."
					+newline+"'agent'('nocurry'('int'(1),'int'(2)),'agent_call'('no_loc_info_available','nocurry',['int'(2),'int'(1)]),'no_loc_info_available')."
					+newline+"'agent_curry'('curry'(['int'(1)],['int'(2)]),'int'(1),'no_loc_info_available')."
					+newline+"'bindval'('int'(1),'agent_call_curry'('curry',[['int'(1)],['int'(2)]]),'no_loc_info_available')."
					+newline+"'bindval'('int'(1),'agent_call_curry'('int'(1),[['int'(2)],['int'(3)]]),'no_loc_info_available')."
					+newline+"'bindval'('int'(1),'agent_call_curry'('tupleExp'(['int'(1),'int'(2)]),[['int'(3),'int'(4)],['int'(5),'int'(6)]]),'no_loc_info_available')."
					+newline+"'channel'('c','type'('dotTupleType'(['setExp'('rangeEnum'(['int'(1)])),'agent_call_curry'('curry',[['int'(1)],['int'(2)]])])))."
					+newline+"'symbol'('nocurry','nocurry','no_loc_info_available','Function or Process')."
					+newline+"'symbol'('curry','curry','no_loc_info_available','Function or Process')."
					+newline+"'symbol'('c','c','no_loc_info_available','Channel')."
			 );
	}
	
	@Test
	public void CommentsAndPragmas() throws Exception //
	{
			check( 
						"{-" 
						+newline+"This is a"
						+newline+"multiline comment"
						+newline+"-}"
						+newline+"{-# assert_ltl \"formula\" #-}"
						+newline+"{-# assert_ltl \"formula\" \"comment\r\nwith newline\" #-}"
						+newline+"{-# assert_ctl \"formula\" #-}"
						+newline+"{-# assert_ctl \"formula\" \"comment\" #-}"
						+newline+"-- This is a line comment"
					,
						"'pragma'('assert_ltl \"formula\"')."
						+newline+"'pragma'('assert_ltl \"formula\" \"comment\\r\\nwith newline\"')."
						+newline+"'pragma'('assert_ctl \"formula\"')."
						+newline+"'pragma'('assert_ctl \"formula\" \"comment\"')."
						+newline+"'comment'('blockComment'('{-\\r\\nThis is a\\r\\nmultiline comment\\r\\n-}'),'no_loc_info_available')."
						+newline+"'comment'('pragmaComment'('{-# assert_ltl \"formula\" #-}'),'no_loc_info_available')."
						+newline+"'comment'('pragmaComment'('{-# assert_ltl \"formula\" \"comment\\r\\nwith newline\" #-}'),'no_loc_info_available')."
						+newline+"'comment'('pragmaComment'('{-# assert_ctl \"formula\" #-}'),'no_loc_info_available')."
						+newline+"'comment'('pragmaComment'('{-# assert_ctl \"formula\" \"comment\" #-}'),'no_loc_info_available')."
						+newline+"'comment'('lineComment'('-- This is a line comment','no_loc_info_available'))."
					);
	}
	
	@Test
	public void PatternsInComprehensions() throws Exception  //
	{
		check(
				"{} = 2"
				+newline+"{1} = 1"
				+newline+"_ = 3"
				+newline+"<> = 3"
				+newline+"<1,2> = 5"
				+newline+"1 = {1|_<-2}"
				+newline+"1 = {1|{}<-2}"
				+newline+"1 = {1|{1}<-2}"
				+newline+"1 = {1|<><-2}"
				+newline+"1 = {1|<1,2,3><-2}"
				+newline+"1 = {1|(x,y)<-2,x}"
				+newline+"1 = {1|(x)<-2,x}"
				+newline+"1 = {1|(a.b^c@@(d),e)<-2,a,b,c,d,e}"
			 ,
				"'bindval'('emptySet','int'(2),'no_loc_info_available')."
				+newline+"'bindval'('singleSetPat'(['int'(1)]),'int'(1),'no_loc_info_available')."
				+newline+"'bindval'(_,'int'(3),'no_loc_info_available')."
				+newline+"'bindval'('listPat'([]),'int'(3),'no_loc_info_available')."
				+newline+"'bindval'('listPat'(['int'(1),'int'(2)]),'int'(5),'no_loc_info_available')."
				+newline+"'bindval'('int'(1),'setExp'('rangeEnum'(['int'(1)]),['comprehensionGenerator'(_,'int'(2))]),'no_loc_info_available')."
				+newline+"'bindval'('int'(1),'setExp'('rangeEnum'(['int'(1)]),['comprehensionGenerator'('emptySet','int'(2))]),'no_loc_info_available')."
				+newline+"'bindval'('int'(1),'setExp'('rangeEnum'(['int'(1)]),['comprehensionGenerator'('singleSetPat'(['int'(1)]),'int'(2))]),'no_loc_info_available')."
				+newline+"'bindval'('int'(1),'setExp'('rangeEnum'(['int'(1)]),['comprehensionGenerator'('listPat'([]),'int'(2))]),'no_loc_info_available')."
				+newline+"'bindval'('int'(1),'setExp'('rangeEnum'(['int'(1)]),['comprehensionGenerator'('listPat'(['int'(1),'int'(2),'int'(3)]),'int'(2))]),'no_loc_info_available')."
				+newline+"'bindval'('int'(1),'setExp'('rangeEnum'(['int'(1)]),['comprehensionGenerator'('tuplePat'([_x,_y]),'int'(2)),'comprehensionGuard'(_x)]),'no_loc_info_available')."
				+newline+"'bindval'('int'(1),'setExp'('rangeEnum'(['int'(1)]),['comprehensionGenerator'(_x2,'int'(2)),'comprehensionGuard'(_x2)]),'no_loc_info_available')."
				+newline+"'bindval'('int'(1),'setExp'('rangeEnum'(['int'(1)]),['comprehensionGenerator'('tuplePat'(['alsoPattern'(['dotpat'([_a,'appendPattern'([_b,_c])]),_d]),_e]),'int'(2)),'comprehensionGuard'(_a),'comprehensionGuard'(_b),'comprehensionGuard'(_c),'comprehensionGuard'(_d),'comprehensionGuard'(_e)]),'no_loc_info_available')."
				+newline+"'symbol'('x','x','no_loc_info_available','Ident (Prolog Variable)')."
				+newline+"'symbol'('y','y','no_loc_info_available','Ident (Prolog Variable)')."
				+newline+"'symbol'('x2','x','no_loc_info_available','Ident (Prolog Variable)')."
				+newline+"'symbol'('a','a','no_loc_info_available','Ident (Prolog Variable)')."
				+newline+"'symbol'('b','b','no_loc_info_available','Ident (Prolog Variable)')."
				+newline+"'symbol'('c','c','no_loc_info_available','Ident (Prolog Variable)')."
				+newline+"'symbol'('d','d','no_loc_info_available','Ident (Prolog Variable)')."
				+newline+"'symbol'('e','e','no_loc_info_available','Ident (Prolog Variable)')."
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
					+newline+"A = f"
			 ,
					"'cspTransparent'(['f','member'])."
					+newline+"'bindval'('A','f','no_loc_info_available')."
					+newline+"'symbol'('f','f','no_loc_info_available','Transparent function')."
					+newline+"'symbol'('member','member','no_loc_info_available','Transparent function')."
					+newline+"'symbol'('A','A','no_loc_info_available','Ident (Groundrep.)')."
			 );		
	}
	
	@Test
	public void Assertions() throws Exception //
	{
		check(		"assert 1 [T= 1"
					+newline+"assert 1 [F= 1"
					+newline+"assert 1 [FD= 1"
					+newline+"assert not 1->2 [R= 2"
					+newline+"assert 1 |= LTL: \"true\""
					+newline+"assert 1 |= CTL: \"true\""
					+newline+"assert 1 :[deadlock free]"
					+newline+"assert 1 :[deadlock free [F]]"
					+newline+"assert 1 :[deadlock free [FD]]"
					+newline+"assert 1 :[deadlock free [T]]"
					+newline+"assert 1 :[divergence free]"
					+newline+"assert 1 :[divergence free [F]]"
					+newline+"assert 1 :[divergence free [FD]]"
					+newline+"assert 1 :[divergence free [T]]"
					+newline+"assert 1 :[livelock free]"
					+newline+"assert 1 :[livelock free [F]]"
					+newline+"assert 1 :[livelock free [FD]]"
					+newline+"assert 1 :[livelock free [T]]"
					+newline+"assert 1 :[deterministic]"
					+newline+"assert 1 :[deterministic [T]]"
					+newline+"assert 1 :[deterministic [F]]"
					+newline+"assert 1 :[deterministic [FD]]"
					+newline+"assert 1 :[has trace]: 2"
					+newline+"assert 1 :[has trace [F]]: 2"
					+newline+"assert 1 :[has trace [FD]]: 2"
					+newline+"assert 1 :[has trace [T]]: 2"
				,
					"'assertRef'('False','int'(1),'Trace','int'(1),'no_loc_info_available')."
					+newline+"'assertRef'('False','int'(1),'Failure','int'(1),'no_loc_info_available')."
					+newline+"'assertRef'('False','int'(1),'FailureDivergence','int'(1),'no_loc_info_available')."
					+newline+"'assertRef'('True','prefix'('no_loc_info_available',[],'int'(1),'int'(2),'no_loc_info_available'),'RefusalTesting','int'(2),'no_loc_info_available')."
					+newline+"'assertLtl'('False','int'(1),'true','no_loc_info_available')."
					+newline+"'assertCtl'('False','int'(1),'true','no_loc_info_available')."
					+newline+"'assertModelCheck'('False','int'(1),'DeadlockFree')."
					+newline+"'assertModelCheckExt'('False','int'(1),'DeadlockFree','F')."
					+newline+"'assertModelCheckExt'('False','int'(1),'DeadlockFree','FD')."
					+newline+"'assertModelCheckExt'('False','int'(1),'DeadlockFree','T')."
					+newline+"'assertModelCheck'('False','int'(1),'LivelockFree')."
					+newline+"'assertModelCheckExt'('False','int'(1),'LivelockFree','F')."
					+newline+"'assertModelCheckExt'('False','int'(1),'LivelockFree','FD')."
					+newline+"'assertModelCheckExt'('False','int'(1),'LivelockFree','T')."
					+newline+"'assertModelCheck'('False','int'(1),'LivelockFree')."
					+newline+"'assertModelCheckExt'('False','int'(1),'LivelockFree','F')."
					+newline+"'assertModelCheckExt'('False','int'(1),'LivelockFree','FD')."
					+newline+"'assertModelCheckExt'('False','int'(1),'LivelockFree','T')."
					+newline+"'assertModelCheck'('False','int'(1),'Deterministic')."
					+newline+"'assertModelCheckExt'('False','int'(1),'Deterministic','T')."
					+newline+"'assertModelCheckExt'('False','int'(1),'Deterministic','F')."
					+newline+"'assertModelCheckExt'('False','int'(1),'Deterministic','FD')."
					+newline+"'assertHasTrace'('False','int'(1),'int'(2))."
					+newline+"'assertHasTraceExt'('False','int'(1),'int'(2),'F')."
					+newline+"'assertHasTraceExt'('False','int'(1),'int'(2),'FD')."
					+newline+"'assertHasTraceExt'('False','int'(1),'int'(2),'T')."
				);
	}
	
	@Test
	public void ComplexBuiltinCallInLetWithin() //
	{
	   check(
				"B  = {member|x<-1}"
				+newline+"C = let D = member"
				+newline+"member = 1"
				+newline+"within 1"
			 ,
				"'bindval'('B','setExp'('rangeEnum'(['member']),['comprehensionGenerator'(_x,'int'(1))]),'no_loc_info_available')."
				+newline+"'bindval'('C','let'(['bindval'('D','val_of'('member','no_loc_info_available'),'no_loc_info_available'),'bindval'('member','int'(1),'no_loc_info_available')],'int'(1)),'no_loc_info_available')."
				+newline+"'symbol'('B','B','no_loc_info_available','Ident (Groundrep.)')."
				+newline+"'symbol'('x','x','no_loc_info_available','Ident (Prolog Variable)')."
				+newline+"'symbol'('C','C','no_loc_info_available','Ident (Groundrep.)')."
				+newline+"'symbol'('D','D','no_loc_info_available','Ident (Groundrep.)')."
				+newline+"'symbol'('member','member','no_loc_info_available','Ident (Groundrep.)')."
				+newline+"'symbol'('member','member','no_loc_info_available','BuiltIn primitive')."	 
			 );		
	}
	
	@Test
	public void ComplexComprehensionAndLetWithin() //
	{
		check(
					"A(x) = {x+{x|x<-1}|x<-1,x<-2,x} + x"
					+newline+"E(x) = {let x = 1"
					+newline+"1 = {x|1}"
					+newline+"within 2 |x<-1,let 1 = x within 2}"
					+newline+"F = {1|x<-1,x,x<-2,x}"
			 ,
					"'agent'('A'(_x),'+'('setExp'('rangeEnum'(['+'(_x4,'setExp'('rangeEnum'([_x2]),['comprehensionGenerator'(_x2,'int'(1))]))]),['comprehensionGenerator'(_x3,'int'(1)),'comprehensionGenerator'(_x4,'int'(2)),'comprehensionGuard'(_x4)]),_x),'no_loc_info_available')."
					+newline+"'agent'('E'(_x5),'setExp'('rangeEnum'(['let'(['bindval'('x6','int'(1),'no_loc_info_available'),'bindval'('int'(1),'setExp'('rangeEnum'(['val_of'('x6','no_loc_info_available')]),['comprehensionGuard'('int'(1))]),'no_loc_info_available')],'int'(2))]),['comprehensionGenerator'(_x7,'int'(1)),'comprehensionGuard'('let'(['bindval'('int'(1),_x7,'no_loc_info_available')],'int'(2)))]),'no_loc_info_available')."
					+newline+"'bindval'('F','setExp'('rangeEnum'(['int'(1)]),['comprehensionGenerator'(_x8,'int'(1)),'comprehensionGuard'(_x8),'comprehensionGenerator'(_x9,'int'(2)),'comprehensionGuard'(_x9)]),'no_loc_info_available')."
					+newline+"'symbol'('A','A','no_loc_info_available','Function or Process')."
					+newline+"'symbol'('x','x','no_loc_info_available','Ident (Prolog Variable)')."
					+newline+"'symbol'('x2','x','no_loc_info_available','Ident (Prolog Variable)')."
					+newline+"'symbol'('x3','x','no_loc_info_available','Ident (Prolog Variable)')."
					+newline+"'symbol'('x4','x','no_loc_info_available','Ident (Prolog Variable)')."
					+newline+"'symbol'('E','E','no_loc_info_available','Function or Process')."
					+newline+"'symbol'('x5','x','no_loc_info_available','Ident (Prolog Variable)')."
					+newline+"'symbol'('x6','x','no_loc_info_available','Ident (Groundrep.)')."
					+newline+"'symbol'('x7','x','no_loc_info_available','Ident (Prolog Variable)')."
					+newline+"'symbol'('F','F','no_loc_info_available','Ident (Groundrep.)')."
					+newline+"'symbol'('x8','x','no_loc_info_available','Ident (Prolog Variable)')."
					+newline+"'symbol'('x9','x','no_loc_info_available','Ident (Prolog Variable)')."	 
			 );
	}
	@Test
	public void LinkedListComprehension() throws Exception //
	{
	   check(
				"B = 1 [2<->3,4<->5|x<-6] 7"
			,
				"'bindval'('B','lParallel'('linkListComp'(['comprehensionGenerator'(_x,'int'(6))],['link'('int'(2),'int'(3)),'link'('int'(4),'int'(5))]),'int'(1),'int'(7),'no_loc_info_available'),'no_loc_info_available')."
				+newline+"'symbol'('B','B','no_loc_info_available','Ident (Groundrep.)')."
				+newline+"'symbol'('x','x','no_loc_info_available','Ident (Prolog Variable)')."
			);
	}
	
	@Test
	public void EasyNumeration() throws Exception //
	{
		check(
					"a = 1"
					+newline+"x(a) = 1"
					+newline+"b(a) = 1"
					+newline+"c = 1?d -> d"
					+newline+"channel e: c"
				,
					"'bindval'('a','int'(1),'no_loc_info_available')."
					+newline+"'agent'('x'(_a2),'int'(1),'no_loc_info_available')."
					+newline+"'agent'('b'(_a3),'int'(1),'no_loc_info_available')."
					+newline+"'bindval'('c','prefix'('no_loc_info_available',['in'(_d)],'int'(1),_d,'no_loc_info_available'),'no_loc_info_available')."
					+newline+"'channel'('e','type'('dotTupleType'(['val_of'('c','no_loc_info_available')])))."
					+newline+"'symbol'('a','a','no_loc_info_available','Ident (Groundrep.)')."
					+newline+"'symbol'('x','x','no_loc_info_available','Function or Process')."
					+newline+"'symbol'('a2','a','no_loc_info_available','Ident (Prolog Variable)')."
					+newline+"'symbol'('b','b','no_loc_info_available','Function or Process')."
					+newline+"'symbol'('a3','a','no_loc_info_available','Ident (Prolog Variable)')."
					+newline+"'symbol'('c','c','no_loc_info_available','Ident (Groundrep.)')."
					+newline+"'symbol'('d','d','no_loc_info_available','Ident (Prolog Variable)')."
					+newline+"'symbol'('e','e','no_loc_info_available','Channel')."
			);
	}
	
	@Test
	public void RepWithStatements() throws Exception //
	{
		check( "A  = [] x:2,x,x:3,x@x"
			 ,
				"'bindval'('A','repChoice'(['comprehensionGenerator'(_x,'int'(2)),'comprehensionGuard'(_x),'comprehensionGenerator'(_x2,'int'(3)),'comprehensionGuard'(_x2)],_x2,'no_loc_info_available'),'no_loc_info_available')."				 
				+newline+"'symbol'('A','A','no_loc_info_available','Ident (Groundrep.)')."
				+newline+"'symbol'('x','x','no_loc_info_available','Ident (Prolog Variable)')."
				+newline+"'symbol'('x2','x','no_loc_info_available','Ident (Prolog Variable)')."
			);
	}
	
	@Test
	public void RenamingAndRenamingComprehensions() //
	{
		check(
					"d = 1"
					+newline+"a = 1"
					+newline+"A(a) = a[[a <- d,a<-d]]"
					+newline+"b = 1"
					+newline+"B = b[[b<-d,b<-d|b<-1]]"
					+newline+"c = 1"
					+newline+"C(c) = c[[c<-d,c<-d|c<-1]]"
				,
					"'bindval'('d','int'(1),'no_loc_info_available')."
					+newline+"'bindval'('a','int'(1),'no_loc_info_available')."
					+newline+"'agent'('A'(_a2),'procRenaming'(['rename'(_a2,'val_of'('d','no_loc_info_available')),'rename'(_a2,'val_of'('d','no_loc_info_available'))],_a2,'no_loc_info_available'),'no_loc_info_available')."
					+newline+"'bindval'('b','int'(1),'no_loc_info_available')."
					+newline+"'bindval'('B','procRenamingComp'('val_of'('b','no_loc_info_available'),['comprehensionGenerator'(_b2,'int'(1))],['rename'(_b2,'val_of'('d','no_loc_info_available')),'rename'(_b2,'val_of'('d','no_loc_info_available'))]),'no_loc_info_available')."
					+newline+"'bindval'('c','int'(1),'no_loc_info_available')."
					+newline+"'agent'('C'(_c2),'procRenamingComp'(_c2,['comprehensionGenerator'(_c3,'int'(1))],['rename'(_c3,'val_of'('d','no_loc_info_available')),'rename'(_c3,'val_of'('d','no_loc_info_available'))]),'no_loc_info_available')."
					+newline+"'symbol'('d','d','no_loc_info_available','Ident (Groundrep.)')."
					+newline+"'symbol'('a','a','no_loc_info_available','Ident (Groundrep.)')."
					+newline+"'symbol'('A','A','no_loc_info_available','Function or Process')."
					+newline+"'symbol'('a2','a','no_loc_info_available','Ident (Prolog Variable)')."
					+newline+"'symbol'('b','b','no_loc_info_available','Ident (Groundrep.)')."
					+newline+"'symbol'('B','B','no_loc_info_available','Ident (Groundrep.)')."
					+newline+"'symbol'('b2','b','no_loc_info_available','Ident (Prolog Variable)')."
					+newline+"'symbol'('c','c','no_loc_info_available','Ident (Groundrep.)')."
					+newline+"'symbol'('C','C','no_loc_info_available','Function or Process')."
					+newline+"'symbol'('c2','c','no_loc_info_available','Ident (Prolog Variable)')."
					+newline+"'symbol'('c3','c','no_loc_info_available','Ident (Prolog Variable)')."

			);
	}
	
	@Test
	public void DifficultComprehensions() throws Exception //
	{	
		check(
				"A = {1|x@@y^a.z<-1}"
				+newline+"B = {x|x<-1,{1|x},x<-2}"
				+newline+"C = {1|x<-1,x,x<-2,x}"
			,	
				"'bindval'('A','setExp'('rangeEnum'(['int'(1)]),['comprehensionGenerator'('alsoPattern'([_x,'dotpat'(['appendPattern'([_y,_a]),_z])]),'int'(1))]),'no_loc_info_available')."
				+newline+"'bindval'('B','setExp'('rangeEnum'([_x3]),['comprehensionGenerator'(_x2,'int'(1)),'comprehensionGuard'('setExp'('rangeEnum'(['int'(1)]),['comprehensionGuard'(_x2)])),'comprehensionGenerator'(_x3,'int'(2))]),'no_loc_info_available')."
				+newline+"'bindval'('C','setExp'('rangeEnum'(['int'(1)]),['comprehensionGenerator'(_x4,'int'(1)),'comprehensionGuard'(_x4),'comprehensionGenerator'(_x5,'int'(2)),'comprehensionGuard'(_x5)]),'no_loc_info_available')."
				+newline+"'symbol'('A','A','no_loc_info_available','Ident (Groundrep.)')."
				+newline+"'symbol'('x','x','no_loc_info_available','Ident (Prolog Variable)')."
				+newline+"'symbol'('y','y','no_loc_info_available','Ident (Prolog Variable)')."
				+newline+"'symbol'('a','a','no_loc_info_available','Ident (Prolog Variable)')."
				+newline+"'symbol'('z','z','no_loc_info_available','Ident (Prolog Variable)')."
				+newline+"'symbol'('B','B','no_loc_info_available','Ident (Groundrep.)')."
				+newline+"'symbol'('x2','x','no_loc_info_available','Ident (Prolog Variable)')."
				+newline+"'symbol'('x3','x','no_loc_info_available','Ident (Prolog Variable)')."
				+newline+"'symbol'('C','C','no_loc_info_available','Ident (Groundrep.)')."
				+newline+"'symbol'('x4','x','no_loc_info_available','Ident (Prolog Variable)')."
				+newline+"'symbol'('x5','x','no_loc_info_available','Ident (Prolog Variable)')."
			);		
	}
	
	@Test
	public void ListComprehensions() throws Exception //
	{
		check(
					"y = 1"
					+newline+"A = <x|x<-1,x,y>"
					+newline+"B = <x..y|x<-1,x,y>"
					+newline+"C = <x..|x<-1,x,y>"
				,	
					"'bindval'('y','int'(1),'no_loc_info_available')."
					+newline+"'bindval'('A','listExp'('rangeEnum'([_x]),['comprehensionGenerator'(_x,'int'(1)),'comprehensionGuard'(_x),'comprehensionGuard'('val_of'('y','no_loc_info_available'))]),'no_loc_info_available')."
					+newline+"'bindval'('B','listExp'('rangeClosed'(_x2,'val_of'('y','no_loc_info_available')),['comprehensionGenerator'(_x2,'int'(1)),'comprehensionGuard'(_x2),'comprehensionGuard'('val_of'('y','no_loc_info_available'))]),'no_loc_info_available')."
					+newline+"'bindval'('C','listExp'('rangeOpen'(_x3),['comprehensionGenerator'(_x3,'int'(1)),'comprehensionGuard'(_x3),'comprehensionGuard'('val_of'('y','no_loc_info_available'))]),'no_loc_info_available')."
					+newline+"'symbol'('y','y','no_loc_info_available','Ident (Groundrep.)')."
					+newline+"'symbol'('A','A','no_loc_info_available','Ident (Groundrep.)')."
					+newline+"'symbol'('x','x','no_loc_info_available','Ident (Prolog Variable)')."
					+newline+"'symbol'('B','B','no_loc_info_available','Ident (Groundrep.)')."
					+newline+"'symbol'('x2','x','no_loc_info_available','Ident (Prolog Variable)')."
					+newline+"'symbol'('C','C','no_loc_info_available','Ident (Groundrep.)')."
					+newline+"'symbol'('x3','x','no_loc_info_available','Ident (Prolog Variable)')."
			);		
	}
	
	@Test
	public void SetComprehensions() throws Exception
	{
		check(
					"y = 1"
					+newline+"A = {x|x<-1,x,y}"
					+newline+"B = {x..y|x<-1,x,y}"
					+newline+"C = {x..|x<-1,x,y}"
					+newline+"D = {|x|x<-1,x,y|}"
				,	
					"'bindval'('y','int'(1),'no_loc_info_available')."
					+newline+"'bindval'('A','setExp'('rangeEnum'([_x]),['comprehensionGenerator'(_x,'int'(1)),'comprehensionGuard'(_x),'comprehensionGuard'('val_of'('y','no_loc_info_available'))]),'no_loc_info_available')."
					+newline+"'bindval'('B','setExp'('rangeClosed'(_x2,'val_of'('y','no_loc_info_available')),['comprehensionGenerator'(_x2,'int'(1)),'comprehensionGuard'(_x2),'comprehensionGuard'('val_of'('y','no_loc_info_available'))]),'no_loc_info_available')."
					+newline+"'bindval'('C','setExp'('rangeOpen'(_x3),['comprehensionGenerator'(_x3,'int'(1)),'comprehensionGuard'(_x3),'comprehensionGuard'('val_of'('y','no_loc_info_available'))]),'no_loc_info_available')."
					+newline+"'bindval'('D','closureComp'(['comprehensionGenerator'(_x4,'int'(1)),'comprehensionGuard'(_x4),'comprehensionGuard'('val_of'('y','no_loc_info_available'))],[_x4]),'no_loc_info_available')."
					+newline+"'symbol'('y','y','no_loc_info_available','Ident (Groundrep.)')."
					+newline+"'symbol'('A','A','no_loc_info_available','Ident (Groundrep.)')."
					+newline+"'symbol'('x','x','no_loc_info_available','Ident (Prolog Variable)')."
					+newline+"'symbol'('B','B','no_loc_info_available','Ident (Groundrep.)')."
					+newline+"'symbol'('x2','x','no_loc_info_available','Ident (Prolog Variable)')."
					+newline+"'symbol'('C','C','no_loc_info_available','Ident (Groundrep.)')."
					+newline+"'symbol'('x3','x','no_loc_info_available','Ident (Prolog Variable)')."
					+newline+"'symbol'('D','D','no_loc_info_available','Ident (Groundrep.)')."
					+newline+"'symbol'('x4','x','no_loc_info_available','Ident (Prolog Variable)')."
			);		
	}
	
	
	@Test
	public void IfThenElse() throws Exception //
	{
		check("m = if true then 1 else 1"
		,
			"'bindval'('m','ifte'('true','int'(1),'int'(1),'no_loc_info_available','no_loc_info_available','no_loc_info_available'),'no_loc_info_available')."
			+newline+"'symbol'('m','m','no_loc_info_available','Ident (Groundrep.)')."
		);
	}
	@Test
	public void ParamNumeration() throws Exception //
	{
		check(
				"B(x,y) = SKIP"
				+newline+"B(x,y) = 5"
				+newline+"F(x,y) = (1,2)"
				+newline+"G(a) = F(a)"
				+newline+"y(_) = 1"				
				,
				"'agent'('B'(_x,_y),'SKIP','no_loc_info_available')."
				+newline+"'agent'('B'(_x2,_y2),'int'(5),'no_loc_info_available')."
				+newline+"'agent'('F'(_x3,_y3),'tupleExp'(['int'(1),'int'(2)]),'no_loc_info_available')."
				+newline+"'agent'('G'(_a),'agent_call'('no_loc_info_available','F',[_a]),'no_loc_info_available')."
				+newline+"'agent'('y4'(_),'int'(1),'no_loc_info_available')."
				+newline+"'symbol'('B','B','no_loc_info_available','Function or Process')."
				+newline+"'symbol'('x','x','no_loc_info_available','Ident (Prolog Variable)')."
				+newline+"'symbol'('y','y','no_loc_info_available','Ident (Prolog Variable)')."
				+newline+"'symbol'('x2','x','no_loc_info_available','Ident (Prolog Variable)')."
				+newline+"'symbol'('y2','y','no_loc_info_available','Ident (Prolog Variable)')."
				+newline+"'symbol'('F','F','no_loc_info_available','Function or Process')."
				+newline+"'symbol'('x3','x','no_loc_info_available','Ident (Prolog Variable)')."
				+newline+"'symbol'('y3','y','no_loc_info_available','Ident (Prolog Variable)')."
				+newline+"'symbol'('G','G','no_loc_info_available','Function or Process')."
				+newline+"'symbol'('a','a','no_loc_info_available','Ident (Prolog Variable)')."
				+newline+"'symbol'('y4','y','no_loc_info_available','Function or Process')."
				+newline+"'symbol'('SKIP','SKIP','no_loc_info_available','BuiltIn primitive')."
			);
	}
	
	@Test
	public void Chars() throws Exception //not supported by cspmf
	{
		check(
					"E = 'a'"
					,
					"'bindval'('E','char'('a'),'no_loc_info_available')."
					+newline+"'symbol'('E','E','no_loc_info_available','Ident (Groundrep.)')."
					);
	}
	
	@Test
	public void Maps() throws Exception //not supported by cspmf
	{
		check(
					"C = (| |)\r\nD = (|1=>2|)"
					,
					"'bindval'('C','emptyMap','no_loc_info_available')."
					+newline+"'bindval'('D','mapExp'(['int'(1),'int'(2)]),'no_loc_info_available')."
					+newline+"'symbol'('C','C','no_loc_info_available','Ident (Groundrep.)')."
					+newline+"'symbol'('D','D','no_loc_info_available','Ident (Groundrep.)')."
					);
	}
	
	@Test
	public void TypeDefs() throws Exception //
	{
		check(	
				"datatype dt = cn|cn2.Bool"
				+newline+"subtype st = cn"
				+newline+"nametype nt = Bool.Int"
				+newline+"channel c1,c2,c3: Bool.Int"
				,
				"'dataTypeDef'('dt',['constructor'('cn'),'constructorC'('cn2','dotTupleType'(['Bool']))])."
				+newline+"'subTypeDef'('st',['constructor'('cn')])."
				+newline+"'nameType'('nt','type'('dotTupleType'(['Bool','Int'])))."
				+newline+"'channel'('c1','type'('dotTupleType'(['Bool','Int'])))."
				+newline+"'channel'('c2','type'('dotTupleType'(['Bool','Int'])))."
				+newline+"'channel'('c3','type'('dotTupleType'(['Bool','Int'])))."
				+newline+"'symbol'('dt','dt','no_loc_info_available','Datatype')."
				+newline+"'symbol'('cn','cn','no_loc_info_available','Constructor of Datatype')."
				+newline+"'symbol'('cn2','cn2','no_loc_info_available','Constructor of Datatype')."
				+newline+"'symbol'('st','st','no_loc_info_available','Datatype')."
				+newline+"'symbol'('nt','nt','no_loc_info_available','Nametype')."
				+newline+"'symbol'('c1','c1','no_loc_info_available','Channel')."
				+newline+"'symbol'('c2','c2','no_loc_info_available','Channel')."
				+newline+"'symbol'('c3','c3','no_loc_info_available','Channel')."
				+newline+"'symbol'('Bool','Bool','no_loc_info_available','BuiltIn primitive')."
				+newline+"'symbol'('Int','Int','no_loc_info_available','BuiltIn primitive')."
			);
	}
	
	@Test
	public void MultipleAgentDefs() throws Exception //
	{
		check(
				"a = 1"
				+newline+"A = 1"
				+newline+"I(a) = A"
				+newline+"I(a) = a"
				+newline+"I(A) = a"
				+newline+"I(A) = A"
				,
				"'bindval'('a','int'(1),'no_loc_info_available')."
				+newline+"'bindval'('A','int'(1),'no_loc_info_available')."
				+newline+"'agent'('I'(_a2),'val_of'('A','no_loc_info_available'),'no_loc_info_available')."
				+newline+"'agent'('I'(_a3),_a3,'no_loc_info_available')."
				+newline+"'agent'('I'(_A2),'val_of'('a','no_loc_info_available'),'no_loc_info_available')."
				+newline+"'agent'('I'(_A3),_A3,'no_loc_info_available')."
				+newline+"'symbol'('a','a','no_loc_info_available','Ident (Groundrep.)')."
				+newline+"'symbol'('A','A','no_loc_info_available','Ident (Groundrep.)')."
				+newline+"'symbol'('I','I','no_loc_info_available','Function or Process')."
				+newline+"'symbol'('a2','a','no_loc_info_available','Ident (Prolog Variable)')."
				+newline+"'symbol'('a3','a','no_loc_info_available','Ident (Prolog Variable)')."
				+newline+"'symbol'('A2','A','no_loc_info_available','Ident (Prolog Variable)')."
				+newline+"'symbol'('A3','A','no_loc_info_available','Ident (Prolog Variable)')."							
			);
	}
	@Test
	public void RedefineBuiltins() throws Exception //cspmf comparison not possible
	{
		check("STOP = 1\r\nSKIP = 1"
		,
			"'bindval'('STOP','int'(1),'no_loc_info_available').\r\n'bindval'('SKIP','int'(1),'no_loc_info_available')."
			+newline+"'symbol'('STOP','STOP','no_loc_info_available','Ident (Groundrep.)')."
			+newline+"'symbol'('SKIP','SKIP','no_loc_info_available','Ident (Groundrep.)')."
		);
	}
	@Test
	public void CallSingleBuiltin() throws Exception //cspmf comparison not possible
	{
		check("P=STOP",
		
				 "'bindval'('P','STOP','no_loc_info_available')."
				+newline+"'symbol'('P','P','no_loc_info_available','Ident (Groundrep.)')."
				+newline+"'symbol'('STOP','STOP','no_loc_info_available','BuiltIn primitive').");
	}
	
	@Test
	public void CallBuiltinsMultipleTimes() throws Exception //cspmf comparison not possible
	{
		check( 		"P = STOP"
					+newline+"Q = STOP"
					+newline+"V = SKIP"
					+newline+"W = SKIP"
				,
					"'bindval'('P','STOP','no_loc_info_available')."
					+newline+"'bindval'('Q','STOP','no_loc_info_available')."
					+newline+"'bindval'('V','SKIP','no_loc_info_available')."
					+newline+"'bindval'('W','SKIP','no_loc_info_available')."
					+newline+"'symbol'('P','P','no_loc_info_available','Ident (Groundrep.)')."
					+newline+"'symbol'('Q','Q','no_loc_info_available','Ident (Groundrep.)')."
					+newline+"'symbol'('V','V','no_loc_info_available','Ident (Groundrep.)')."
					+newline+"'symbol'('W','W','no_loc_info_available','Ident (Groundrep.)')."
					+newline+"'symbol'('STOP','STOP','no_loc_info_available','BuiltIn primitive')."
					+newline+"'symbol'('SKIP','SKIP','no_loc_info_available','BuiltIn primitive')."
				);
	}
	
	@Test
	public void ExpressionsNewlinesBetween() throws Exception //
	{
	  check(
				"b= 2 \r\n a = true \r\n       and     \r\n b==1"
			, 
				"'bindval'('b','int'(2),'no_loc_info_available')."
				+newline+"'bindval'('a','bool_and'('true','=='('val_of'('b','no_loc_info_available'),'int'(1))),'no_loc_info_available')."
				+newline+"'symbol'('b','b','no_loc_info_available','Ident (Groundrep.)')."
				+newline+"'symbol'('a','a','no_loc_info_available','Ident (Groundrep.)')."
			);	
	}
	
	@Test
	public void LetWithinTest() throws Exception //
	{
		check(			
				"channel a"
				+newline+" R(b) = let" 
							+newline+" b = STOP" 
					  +newline+" within a-> b "
				+newline+" Q = a-> a -> SKIP"
			,				
				"'channel'('a','type'('dotUnitType'))."
				+newline+"'agent'('R'(_b),'let'(['bindval'('b2','STOP','no_loc_info_available')],'prefix'('no_loc_info_available',[],'a','val_of'('b2','no_loc_info_available'),'no_loc_info_available')),'no_loc_info_available')."
				+newline+"'bindval'('Q','prefix'('no_loc_info_available',[],'a','prefix'('no_loc_info_available',[],'a','SKIP','no_loc_info_available'),'no_loc_info_available'),'no_loc_info_available')."
				+newline+"'symbol'('a','a','no_loc_info_available','Channel')."
				+newline+"'symbol'('R','R','no_loc_info_available','Function or Process')."
				+newline+"'symbol'('b','b','no_loc_info_available','Ident (Prolog Variable)')."
				+newline+"'symbol'('b2','b','no_loc_info_available','Ident (Groundrep.)')."
				+newline+"'symbol'('Q','Q','no_loc_info_available','Ident (Groundrep.)')."
				+newline+"'symbol'('STOP','STOP','no_loc_info_available','BuiltIn primitive')."
				+newline+"'symbol'('SKIP','SKIP','no_loc_info_available','BuiltIn primitive')."
			);
	}
	
	@Test
	public void FunctionCalls() throws Exception //
	{
		check("A = 1.A(1)",
		"'bindval'('A','dotTuple'(['int'(1),'agent_call'('no_loc_info_available','val_of'('A','no_loc_info_available'),['int'(1)])]),'no_loc_info_available')."
		+newline+"'symbol'('A','A','no_loc_info_available','Ident (Groundrep.)')."
		);
	}
	
	@Test
	public void Functional() throws Exception //
	{
	  check(	
				"A(x) = (1?x->1)(x)+x"
				+newline+"A(x) = (1?x->1,x)"
				+newline+"1 = ((1)(1))(1)"
			,		
				"'agent'('A'(_x),'+'('agent_call'('no_loc_info_available','prefix'('no_loc_info_available',['in'(_x2)],'int'(1),'int'(1),'no_loc_info_available'),[_x]),_x),'no_loc_info_available')."
				+newline+"'agent'('A'(_x3),'tupleExp'(['prefix'('no_loc_info_available',['in'(_x4)],'int'(1),'int'(1),'no_loc_info_available'),_x3]),'no_loc_info_available')."
				+newline+"'bindval'('int'(1),'agent_call'('no_loc_info_available','agent_call'('no_loc_info_available','int'(1),['int'(1)]),['int'(1)]),'no_loc_info_available')."
				+newline+"'symbol'('A','A','no_loc_info_available','Function or Process')."
				+newline+"'symbol'('x','x','no_loc_info_available','Ident (Prolog Variable)')."
				+newline+"'symbol'('x2','x','no_loc_info_available','Ident (Prolog Variable)')."
				+newline+"'symbol'('x3','x','no_loc_info_available','Ident (Prolog Variable)')."
				+newline+"'symbol'('x4','x','no_loc_info_available','Ident (Prolog Variable)')."		
			);
	}
	
	@Test
	public void ProcOperators() throws Exception //
	{
	  check(	"J = 1 \\ 2"
				+newline+"K = 1 ||| 2"
				+newline+"L = 2 [|3|> 4"
				+newline+"M = 2 [|3|] 4"
				+newline+"N = 2 [3||4] 5"
				+newline+"O = 2 [3<->4] 5"
				+newline+"P = 2 [3<->4,6<->7] 5"
				+newline+"Q = 2 |~| 3"
				+newline+"R = 2 [] 3"
				+newline+"S = 2 [+ 3 +] 4"
				+newline+"T = 2 /\\ 3"
				+newline+"U = 2 /+ 1 +\\ 3"
				+newline+"V = 2 [> 3"
				+newline+"W = 2 ; 3"
				+newline+"X = 2 & 3"
				+newline+"Y = 1 -> 5"
				+newline+"n = [] true @ 1"
				+newline+"o = |~| 1:2 @ 1"
				+newline+"p = ||| 1:2,true @ 2"
				+newline+"q = || 1 @ [true] 1"
				+newline+"r = ; 1 @ 2"
				+newline+"s = [|true|] 1 @ 2"
				+newline+"t = [1<->2] true @ true"
				+newline+"u = [+1+] true @ true"
				,
				"'bindval'('J','\\'('int'(1),'int'(2),'src_span_operator'('no_loc_info_available','no_loc_info_available')),'no_loc_info_available')."
				+newline+"'bindval'('K','|||'('int'(1),'int'(2),'src_span_operator'('no_loc_info_available','no_loc_info_available')),'no_loc_info_available')."
				+newline+"'bindval'('L','exception'('int'(3),'int'(2),'int'(4),'no_loc_info_available'),'no_loc_info_available')."
				+newline+"'bindval'('M','sharing'('int'(3),'int'(2),'int'(4),'no_loc_info_available'),'no_loc_info_available')."
				+newline+"'bindval'('N','aParallel'('int'(3),'int'(2),'int'(4),'int'(5),'no_loc_info_available'),'no_loc_info_available')."
				+newline+"'bindval'('O','lParallel'('linkList'(['link'('int'(3),'int'(4))]),'int'(2),'int'(5),'no_loc_info_available'),'no_loc_info_available')."
				+newline+"'bindval'('P','lParallel'('linkList'(['link'('int'(3),'int'(4)),'link'('int'(6),'int'(7))]),'int'(2),'int'(5),'no_loc_info_available'),'no_loc_info_available')."
				+newline+"'bindval'('Q','|~|'('int'(2),'int'(3),'src_span_operator'('no_loc_info_available','no_loc_info_available')),'no_loc_info_available')."
				+newline+"'bindval'('R','[]'('int'(2),'int'(3),'src_span_operator'('no_loc_info_available','no_loc_info_available')),'no_loc_info_available')."
				+newline+"'bindval'('S','syncExtChoice'('int'(2),'int'(3),'int'(4),'no_loc_info_available'),'no_loc_info_available')."
				+newline+"'bindval'('T','/\\'('int'(2),'int'(3),'src_span_operator'('no_loc_info_available','no_loc_info_available')),'no_loc_info_available')."
				+newline+"'bindval'('U','syncInterrupt'('int'(2),'int'(1),'int'(3),'no_loc_info_available'),'no_loc_info_available')."
				+newline+"'bindval'('V','[>'('int'(2),'int'(3),'src_span_operator'('no_loc_info_available','no_loc_info_available')),'no_loc_info_available')."
				+newline+"'bindval'('W',';'('int'(2),'int'(3),'src_span_operator'('no_loc_info_available','no_loc_info_available')),'no_loc_info_available')."
				+newline+"'bindval'('X','&'('int'(2),'int'(3)),'no_loc_info_available')."
				+newline+"'bindval'('Y','prefix'('no_loc_info_available',[],'int'(1),'int'(5),'no_loc_info_available'),'no_loc_info_available')."
				+newline+"'bindval'('n','repChoice'(['comprehensionGuard'('true')],'int'(1),'no_loc_info_available'),'no_loc_info_available')."
				+newline+"'bindval'('o','repInternalChoice'(['comprehensionGenerator'('int'(1),'int'(2))],'int'(1),'no_loc_info_available'),'no_loc_info_available')."
				+newline+"'bindval'('p','repInterleave'(['comprehensionGenerator'('int'(1),'int'(2)),'comprehensionGuard'('true')],'int'(2),'no_loc_info_available'),'no_loc_info_available')."
				+newline+"'bindval'('q','procRepAParallel'(['comprehensionGuard'('int'(1))],'pair'('true','int'(1)),'no_loc_info_available'),'no_loc_info_available')."
				+newline+"'bindval'('r','repSequence'(['comprehensionGuard'('int'(1))],'int'(2),'no_loc_info_available'),'no_loc_info_available')."
				+newline+"'bindval'('s','procRepSharing'('true',['comprehensionGuard'('int'(1))],'int'(2),'no_loc_info_available'),'no_loc_info_available')."
				+newline+"'bindval'('t','procRepLinkParallel'('linkList'(['link'('int'(1),'int'(2))]),['comprehensionGuard'('true')],'true','no_loc_info_available'),'no_loc_info_available')."
				+newline+"'bindval'('u','procRepSyncParallel'('int'(1),['comprehensionGuard'('true')],'true','no_loc_info_available'),'no_loc_info_available')."
				+newline+"'symbol'('J','J','no_loc_info_available','Ident (Groundrep.)')."
				+newline+"'symbol'('K','K','no_loc_info_available','Ident (Groundrep.)')."
				+newline+"'symbol'('L','L','no_loc_info_available','Ident (Groundrep.)')."
				+newline+"'symbol'('M','M','no_loc_info_available','Ident (Groundrep.)')."
				+newline+"'symbol'('N','N','no_loc_info_available','Ident (Groundrep.)')."
				+newline+"'symbol'('O','O','no_loc_info_available','Ident (Groundrep.)')."
				+newline+"'symbol'('P','P','no_loc_info_available','Ident (Groundrep.)')."
				+newline+"'symbol'('Q','Q','no_loc_info_available','Ident (Groundrep.)')."
				+newline+"'symbol'('R','R','no_loc_info_available','Ident (Groundrep.)')."
				+newline+"'symbol'('S','S','no_loc_info_available','Ident (Groundrep.)')."
				+newline+"'symbol'('T','T','no_loc_info_available','Ident (Groundrep.)')."
				+newline+"'symbol'('U','U','no_loc_info_available','Ident (Groundrep.)')."
				+newline+"'symbol'('V','V','no_loc_info_available','Ident (Groundrep.)')."
				+newline+"'symbol'('W','W','no_loc_info_available','Ident (Groundrep.)')."
				+newline+"'symbol'('X','X','no_loc_info_available','Ident (Groundrep.)')."
				+newline+"'symbol'('Y','Y','no_loc_info_available','Ident (Groundrep.)')."
				+newline+"'symbol'('n','n','no_loc_info_available','Ident (Groundrep.)')."
				+newline+"'symbol'('o','o','no_loc_info_available','Ident (Groundrep.)')."
				+newline+"'symbol'('p','p','no_loc_info_available','Ident (Groundrep.)')."
				+newline+"'symbol'('q','q','no_loc_info_available','Ident (Groundrep.)')."
				+newline+"'symbol'('r','r','no_loc_info_available','Ident (Groundrep.)')."
				+newline+"'symbol'('s','s','no_loc_info_available','Ident (Groundrep.)')."
				+newline+"'symbol'('t','t','no_loc_info_available','Ident (Groundrep.)')."
				+newline+"'symbol'('u','u','no_loc_info_available','Ident (Groundrep.)')."
			);
	}
	
	@Test
	public void LetWithinDifficult() throws Exception //
	{
	  check(
				"k = let\r\nI(a) = 1\r\nI(a) = let\r\nI(a) = a\r\nwithin 8\r\nI(a) = 3\r\nwithin 9"
				+newline+"l(x) =  let\r\nu = 1\r\nb(u) = let\r\nc = u\r\nwithin 1\r\nwithin x"
			,
				"'bindval'('k','let'(['agent'('I'(_a),'int'(1),'no_loc_info_available'),'agent'('I'(_a2),'let'(['agent'('I2'(_a3),_a3,'no_loc_info_available')],'int'(8)),'no_loc_info_available'),'agent'('I'(_a4),'int'(3),'no_loc_info_available')],'int'(9)),'no_loc_info_available')."
				+newline+"'agent'('l'(_x),'let'(['bindval'('u','int'(1),'no_loc_info_available'),'agent'('b'(_u2),'let'(['bindval'('c',_u2,'no_loc_info_available')],'int'(1)),'no_loc_info_available')],_x),'no_loc_info_available')."
				+newline+"'symbol'('k','k','no_loc_info_available','Ident (Groundrep.)')."
				+newline+"'symbol'('I','I','no_loc_info_available','Function or Process')."
				+newline+"'symbol'('a','a','no_loc_info_available','Ident (Prolog Variable)')."
				+newline+"'symbol'('a2','a','no_loc_info_available','Ident (Prolog Variable)')."
				+newline+"'symbol'('I2','I','no_loc_info_available','Function or Process')."
				+newline+"'symbol'('a3','a','no_loc_info_available','Ident (Prolog Variable)')."
				+newline+"'symbol'('a4','a','no_loc_info_available','Ident (Prolog Variable)')."
				+newline+"'symbol'('l','l','no_loc_info_available','Function or Process')."
				+newline+"'symbol'('x','x','no_loc_info_available','Ident (Prolog Variable)')."
				+newline+"'symbol'('u','u','no_loc_info_available','Ident (Groundrep.)')."
				+newline+"'symbol'('b','b','no_loc_info_available','Function or Process')."
				+newline+"'symbol'('u2','u','no_loc_info_available','Ident (Prolog Variable)')."
				+newline+"'symbol'('c','c','no_loc_info_available','Ident (Groundrep.)')."
			);
	}
	
	@Test
	public void Lambda() throws Exception //
	{
	  check(
				"h = \\a,b@3\r\ni(a) = \\b@a+b\r\na = 1"
			,
				"'bindval'('h','lambda'([_a,_b],'int'(3)),'no_loc_info_available')."
				+newline+"'agent'('i'(_a2),'lambda'([_b2],'+'(_a2,_b2)),'no_loc_info_available')."
				+newline+"'bindval'('a3','int'(1),'no_loc_info_available')."
				+newline+"'symbol'('h','h','no_loc_info_available','Ident (Groundrep.)')."
				+newline+"'symbol'('a','a','no_loc_info_available','Ident (Prolog Variable)')."
				+newline+"'symbol'('b','b','no_loc_info_available','Ident (Prolog Variable)')."
				+newline+"'symbol'('i','i','no_loc_info_available','Function or Process')."
				+newline+"'symbol'('a2','a','no_loc_info_available','Ident (Prolog Variable)')."
				+newline+"'symbol'('b2','b','no_loc_info_available','Ident (Prolog Variable)')."
				+newline+"'symbol'('a3','a','no_loc_info_available','Ident (Groundrep.)')."			
			);
	}
	
	@Test
	public void InputOutput() throws Exception //
	{
		check(  "Z = 1?1.2 -> 5"
				+newline+"a(d) = 1?1.2:1.2 -> 5"
				+newline+"a(d) = 1"
				+newline+"b = 1$1.2 -> 5"
				+newline+"c = 1$1.2:1.2 -> 5"
				+newline+"d = 1!1.2 -> 5"
				+newline+"e = 1?1@@2 -> 5"
				+newline+"f = 1?1^2 -> 5"
				+newline+"g = 1^2"
				+newline+"j(y) = 1?y -> y"
				,
				"'bindval'('Z','prefix'('no_loc_info_available',['in'('dotpat'(['int'(1),'int'(2)]))],'int'(1),'int'(5),'no_loc_info_available'),'no_loc_info_available')."
				+newline+"'agent'('a'(_d),'prefix'('no_loc_info_available',['inGuard'('dotpat'(['int'(1),'int'(2)]),'dotTuple'(['int'(1),'int'(2)]))],'int'(1),'int'(5),'no_loc_info_available'),'no_loc_info_available')."
				+newline+"'agent'('a'(_d2),'int'(1),'no_loc_info_available')."
				+newline+"'bindval'('b','prefix'('no_loc_info_available',['nondetIn'('dotpat'(['int'(1),'int'(2)]))],'int'(1),'int'(5),'no_loc_info_available'),'no_loc_info_available')."
				+newline+"'bindval'('c','prefix'('no_loc_info_available',['nondetInGuard'('dotpat'(['int'(1),'int'(2)]),'dotTuple'(['int'(1),'int'(2)]))],'int'(1),'int'(5),'no_loc_info_available'),'no_loc_info_available')."
				+newline+"'bindval'('d3','prefix'('no_loc_info_available',['out'('dotTuple'(['int'(1),'int'(2)]))],'int'(1),'int'(5),'no_loc_info_available'),'no_loc_info_available')."
				+newline+"'bindval'('e','prefix'('no_loc_info_available',['in'('alsoPattern'(['int'(1),'int'(2)]))],'int'(1),'int'(5),'no_loc_info_available'),'no_loc_info_available')."
				+newline+"'bindval'('f','prefix'('no_loc_info_available',['in'('appendPattern'(['int'(1),'int'(2)]))],'int'(1),'int'(5),'no_loc_info_available'),'no_loc_info_available')."
				+newline+"'bindval'('g','^'('int'(1),'int'(2)),'no_loc_info_available')."
				+newline+"'agent'('j'(_y),'prefix'('no_loc_info_available',['in'(_y2)],'int'(1),_y2,'no_loc_info_available'),'no_loc_info_available')."
				+newline+"'symbol'('Z','Z','no_loc_info_available','Ident (Groundrep.)')."
				+newline+"'symbol'('a','a','no_loc_info_available','Function or Process')."
				+newline+"'symbol'('d','d','no_loc_info_available','Ident (Prolog Variable)')."
				+newline+"'symbol'('d2','d','no_loc_info_available','Ident (Prolog Variable)')."
				+newline+"'symbol'('b','b','no_loc_info_available','Ident (Groundrep.)')."
				+newline+"'symbol'('c','c','no_loc_info_available','Ident (Groundrep.)')."
				+newline+"'symbol'('d3','d','no_loc_info_available','Ident (Groundrep.)')."
				+newline+"'symbol'('e','e','no_loc_info_available','Ident (Groundrep.)')."
				+newline+"'symbol'('f','f','no_loc_info_available','Ident (Groundrep.)')."
				+newline+"'symbol'('g','g','no_loc_info_available','Ident (Groundrep.)')."
				+newline+"'symbol'('j','j','no_loc_info_available','Function or Process')."
				+newline+"'symbol'('y','y','no_loc_info_available','Ident (Prolog Variable)')."
				+newline+"'symbol'('y2','y','no_loc_info_available','Ident (Prolog Variable)')."
			);
	}
	
	@Test
	public void ExpressionOperators() throws Exception //
	{
		check(
				"v = -(1^2)^2"
				+newline+"w = 0-1+2*3/4%-5"
				+newline+"x = #1"
				+newline+"z = true or false and not true"
		,
			"'bindval'('v','negate'('^'('^'('int'(1),'int'(2)),'int'(2))),'no_loc_info_available')."
			+newline+"'bindval'('w','+'('-'('int'(0),'int'(1)),'%'('/'('*'('int'(2),'int'(3)),'int'(4)),'int'(-5))),"
			+"'no_loc_info_available')."
			+newline+"'bindval'('x','#'('int'(1)),'no_loc_info_available')."
			+newline+"'bindval'('z','bool_or'('true','bool_and'('false','bool_not'('true'))),'no_loc_info_available')."
			+newline+"'symbol'('v','v','no_loc_info_available','Ident (Groundrep.)')."
			+newline+"'symbol'('w','w','no_loc_info_available','Ident (Groundrep.)')."
			+newline+"'symbol'('x','x','no_loc_info_available','Ident (Groundrep.)')."
			+newline+"'symbol'('z','z','no_loc_info_available','Ident (Groundrep.)')."
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
		System.out.println(expected+newline+""+actual);
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
		System.out.println(expected+newline+""+actual);
		assertEquals(expected, actual);		
	}

	public String cspmfCompileToProlog(String input) throws Exception
	{
		
		String output = "";
		String[] command = {"cmd"};
		Process p = null;					

		if(ostype  == OsCheck.OSType.Windows)
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
