import java.io.*;
import java.lang.*;
import java.util.*;

import CSPMparser.lexer.*;
import CSPMparser.node.*;

import de.hhu.stups.sablecc.patch.*;
import java.util.concurrent.LinkedBlockingQueue;

public class LexHelper extends Lexer 
{
	private List<IToken> modifiedList = new ArrayList<IToken>();
	private State helper_state = State.NORMAL;
	private Token lastNoNewlineToken = null;
	private Token lastNewline = null;
	private Token saveToken = null;
    private boolean useFilter = true;
	
	public LexHelper(final PushbackReader in) 
	{
		super(in);
	}
	
	@Override
	protected void filter() throws LexerException, IOException 
	{
		if(useFilter)
		{	
			if(token instanceof TBlank)
			{
				token = null;
			}
			else if (token instanceof TNl) 
			{
				if (helper_state.equals(State.NORMAL)) //Newline in NORMAL-state
				{
					// token would be saved and maybe added later
					helper_state = State.NEWLINE;
					lastNewline = token;
				}
				token = null;	//newline in Newline state, it will be ignored");  	
			}
			else 
			{
				if (helper_state.equals(State.NEWLINE)) //Previous was an ignored newline. Filter tokens, that must 
				{									//absorb whitespace on the left or whitespace right from previous tokens that
													//absorb whitespace on the right side and add lastNewline to tokenlist(count-1)			 
					 helper_state = State.NORMAL;
												
					 if (instanceOfLeftWhite(token) || instanceOfRightWhite(lastNoNewlineToken)) 
					 {
						 //do nothing
					 }
					 else 
					 {
						saveToken = token;
						token = lastNewline;
						useFilter = false;
						filterWrap();
						useFilter = true;
						token = saveToken;
						saveToken = null;
					 }
				 } 			 
				 lastNoNewlineToken = token; //save this non-newline-token		 
			}
		}
	}
	
	
	public boolean instanceOfLeftWhite(Token t) //Tokens that absorb whitespace left from them
	{	

		if(t == null)
		{
			return false;
		}
		else if(   t instanceof TThen || t instanceof TElse || t instanceof TAnd || t instanceof TOr 
				|| t instanceof TNot || t instanceof TDotdot || t instanceof TDdot || t instanceof TBSlash 
				|| t instanceof TDpipe || t instanceof TPipeEquals || t instanceof TPipe || t instanceof TDoubleat 
				|| t instanceof TILeaving || t instanceof TIChoice || t instanceof TEChoice 
				|| t instanceof TInterrupt || t instanceof TTimeout || t instanceof TParR || t instanceof TLge 
				|| t instanceof TSeqClose || t instanceof TGreater || t instanceof TTriaR || t instanceof TSyncParL 
				|| t instanceof TSyncParR || t instanceof TSyncIntL || t instanceof TSyncIntR 
				|| t instanceof TDbracketL || t instanceof TDbracketR || t instanceof TDpBracketL 
				|| t instanceof TDpBracketR || t instanceof TBracketPipeL || t instanceof TBracketPipeR 
				|| t instanceof TBracketL || t instanceof TBracketR || t instanceof TBraceR || t instanceof TGuard 
				|| t instanceof TDot || t instanceof TSemicolon || t instanceof TPrefix || t instanceof TCat 
				|| t instanceof TEqual || t instanceof TTraMod || t instanceof TFailMod || t instanceof TFailDiv 
				|| t instanceof TRefusal || t instanceof TModel || t instanceof TMulDivMod || t instanceof TPlus)
		{
			return true;
		}
		return false;
		
	}

	public boolean instanceOfRightWhite(Token t) //Tokens that absorb whitespace right from them
	{
		if(t == null)
		{
			return false;
		}
		else if(   t instanceof TThen || t instanceof TElse || t instanceof TLet || t instanceof TWithin 
				|| t instanceof TAnd || t instanceof TOr || t instanceof TNot || t instanceof TComma 
				|| t instanceof TDotdot || t instanceof TDdot || t instanceof TEq || t instanceof TAt 
				|| t instanceof THash || t instanceof TBSlash || t instanceof TDpipe 
				|| t instanceof TPipeEquals || t instanceof TPipe || t instanceof TDoubleat 
				|| t instanceof TILeaving || t instanceof TIChoice || t instanceof TEChoice 
				|| t instanceof TInterrupt || t instanceof TTimeout || t instanceof TParL || t instanceof TLge 
				|| t instanceof TSeqOpen || t instanceof TLess || t instanceof TTriaL || t instanceof TSyncParL 
				|| t instanceof TSyncParR || t instanceof TSyncIntL || t instanceof TSyncIntR 
				|| t instanceof TDbracketL || t instanceof TDpBracketL
				|| t instanceof TDpBracketR || t instanceof TBracketPipeL || t instanceof TBracketPipeR 
				|| t instanceof TBracketL || t instanceof TBraceL || t instanceof TGuard || t instanceof TDot 
				|| t instanceof TSemicolon || t instanceof TPrefix || t instanceof TCat || t instanceof TEqual 
				|| t instanceof TTraMod || t instanceof TFailMod || t instanceof TFailDiv 
				|| t instanceof TRefusal || t instanceof TModel || t instanceof TMulDivMod || t instanceof TPlus)
		{
			return true;
		}
		return false;
		
	}
}
