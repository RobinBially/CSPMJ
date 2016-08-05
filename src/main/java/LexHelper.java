import java.io.*;
import java.lang.*;
import java.util.*;

import CSPMparser.lexer.*;
import CSPMparser.node.*;


public class LexHelper extends Lexer 
{
	// soak multiple new lines
//	private State helper_state = State.NORMAL;
//	private Token old_token = null;
    int i = 0;
	public LexHelper(final PushbackReader in) 
	{
		super(in);
	}
	
	@Override
	protected void filter() throws LexerException, IOException 
	{
		i++;
		System.out.println("token: " + i);
	}

}
