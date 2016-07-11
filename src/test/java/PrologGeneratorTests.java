import static org.junit.Assert.*;

import org.junit.Test;


public class PrologGeneratorTests {

	@Test
	public void test1() throws Exception {
		check("P=STOP","'bindval'('P','stop'('no_loc_info_available'),'no_loc_info_available')."
				+ "\n" + "'symbol'('P','P','no_loc_info_available','Ident (Groundrep.)').\n");
	}

	@Test
	public void fail_test1() throws Exception {
		checkParserError("P=");
	}
	
	
	private void check(final String input, final String expected) {
		String actual;
		try {
			actual = parseString(input);
		} catch (CSPMparserException e) {
			actual = "";
		}
		assertEquals(expected, actual);
	}
	
	private void checkParserError(final String input) {
		boolean exception_occurred = false;
		try { 
			parseString(input);
		} catch (CSPMparserException e) {
			exception_occurred = true;
		}
		assertTrue(exception_occurred);		
	}

	private String parseString(final String input) throws CSPMparserException {
		CSPMparser parser = new CSPMparser();
		return parser.parseString(input);
	}


}
