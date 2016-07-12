import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.Ignore;

//Notepad Regex for replacing src-locations:    'src_span'\(\d+,\d+,\d+,\d+,\d+,\d+\)

public class PrologGeneratorTests 
{

	@Test
	public void test1() throws Exception 
	{
		check("P=STOP","'bindval'('P','stop'('no_loc_info_available'),'no_loc_info_available')."
				+ "\n" + "'symbol'('P','P','no_loc_info_available','Ident (Groundrep.)').\n");
	}

	@Test
	public void fail_test1() throws Exception 
	{
		checkParserError("P=");
	}
	

	@Test
	public void expressionsNewlinesBetween() throws Exception
	{
		check("b= 2 \n a = true \n       and     \n b==1", 
		"'bindval'('b','int'(2),'no_loc_info_available')."
		+"\n'bindval'('a','true','val_of'('b','no_loc_info_available'),'int'(1),'no_loc_info_available')."
		+"\n'symbol'('a','a','no_loc_info_available','Ident (Groundrep.)')."
		+"\n'symbol'('b','b','no_loc_info_available','Ident (Groundrep.)').\n");
		
	}
	
	@Test
	public void LetWithinTest() throws Exception
	{
		check("channel a \n R(b) = let \n b = STOP \n within a-> b \n Q = a-> a -> SKIP",				
		"'channel'('a','type'('dotUnitType')).\n"
		+"'agent'('R'(_b),'let'(['bindval'('b2','stop'('no_loc_info_available'),'no_loc_info_available')],'prefix'('no_loc_info_available',[],'a','val_of'('b2','no_loc_info_available'),'no_loc_info_available')),'no_loc_info_available').\n"
		+"'bindval'('Q','prefix'('no_loc_info_available',[],'a','prefix'('no_loc_info_available',[],'a','skip'('no_loc_info_available'),'no_loc_info_available'),'no_loc_info_available'),'no_loc_info_available').\n"
		+"'symbol'('a','a','no_loc_info_available','Channel').\n"
		+"'symbol'('Q','Q','no_loc_info_available','Ident (Groundrep.)').\n"
		+"'symbol'('R','R','no_loc_info_available','Function or Process').\n"
		+"'symbol'('b','b','no_loc_info_available','Ident (Prolog Variable)').\n"
		+"'symbol'('b2','b','no_loc_info_available','Ident (Groundrep.)').\n");
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
