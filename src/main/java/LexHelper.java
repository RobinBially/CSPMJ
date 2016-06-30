import java.io.IOException;
import java.io.PushbackReader;

import CSPMparser.lexer.*;
import CSPMparser.node.TNl;

public class LexHelper extends Lexer {
	// soak multiple new lines
	private boolean prior_newline = false;
	// remove new lines before and after binary operators
	private boolean prior_binary = false;
	
	public LexHelper(final PushbackReader in) {
		super(in);
	}
	
	@Override
	protected void filter() throws LexerException, IOException {
		if (token instanceof TNl) {
			if (prior_newline || prior_binary) {
				token = null;
			}
			prior_newline = true;
		}
		else {
			prior_newline = false;
		}
	}
}
