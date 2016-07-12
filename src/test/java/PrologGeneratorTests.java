import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.Ignore;


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
	
	@Ignore 
	@Test
	public void expressionsNewlinesBetween() throws Exception
	{
		check("b= 2 \n a = true \n       and     \n b==1", 
		"'bindval'('b','int'(2),'no_loc_info_available')."
		+"\n'bindval'('a','true','val_of'('b','no_loc_info_available'),'int'(1),'no_loc_info_available')."
		+"\n'symbol'('a','a','no_loc_info_available','Ident (Groundrep.)')."
		+"\n'symbol'('b','b','no_loc_info_available','Ident (Groundrep.)').");
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
