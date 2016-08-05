import java.io.*;
import java.lang.*;
import java.util.*;

import CSPMparser.lexer.*;
import CSPMparser.node.TNl;
import CSPMparser.node.TPlus;
import CSPMparser.node.Token;

public class LexHelper extends Lexer 
{
	// soak multiple new lines
	private State helper_state = State.NORMAL;
	private Token old_token = null;
    
	public LexHelper(final PushbackReader in) 
	{
		super(in);
	}
	
	@Override
	protected void filter() throws LexerException, IOException 
	{
		System.out.println("token: " + token.getText());
		if (token instanceof TNl) 
		{
			if (helper_state.equals(State.NORMAL)) {
				// token would be saved and maybe added later
				helper_state = State.NEWLINE;
				old_token = token;
				token = null;
				System.out.println("newline in NORMAL state");
			} else if (helper_state.equals(State.NEWLINE)) {
			    System.out.println("newline in Newline state, it will be ignored");
			    token = null;  
			}
		} else {
			 if (helper_state.equals(State.NEWLINE)) {
				 // change to normal again
				 System.out.println("Change to normal state");
				 helper_state = State.NORMAL;
				 if (token instanceof TPlus) {
					 token = null;
				 }
			 } else {
				 
				 old_token = null;
			 }
		}

	}

}
