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
	private int tokenCount = -1;
	private State helper_state = State.NORMAL;
	private Token lastNoNlToken = null;
	private Token lastNl = null;
    
	public LexHelper(final PushbackReader in) 
	{
		super(in);
	}
	
	@Override
	protected void filter() throws LexerException, IOException 
	{
		//tokenCount++;//This token is referenced by a counter
		System.out.println("Bugfix");
		
					for(int i = 0;i<getTokenList().size();i++)
					{
						System.out.println("Token "+i+" : "+getTokenList().get(i).getText());
					}	
					System.out.println("current Token count "+tokenCount+"\n\n\n");

					
		//System.out.println("token "+tokenCount+" : " + token.getText());
		if (token instanceof TNl) 
		{
			if (helper_state.equals(State.NORMAL)) 
			{
				// token would be saved and maybe added later
				helper_state = State.NEWLINE;
				lastNl = token;
				System.out.println("newline in NORMAL state");
			} 
			else if (helper_state.equals(State.NEWLINE)) 
			{
			    System.out.println("newline in Newline state, it will be ignored");  
			}
			token = null;
			//tokenCount-=1;
		}
		else 
		{
		tokenCount++;
			 if (helper_state.equals(State.NEWLINE)) 
			//Previous was an ignored newline. Filter tokens, that must 
			//absorb whitespace on the left or whitespace right from previous tokens that
			//absorb whitespace on the right side and add lastNl to tokenlist(count-1)
			
			 { 
				 // change to normal again
				 System.out.println("Change to normal state");
				 helper_state = State.NORMAL;
				 				 			
			//	 if (instanceOfLeftWhite(token) || instanceOfRightWhite(lastNoNlToken)) 
			//	 {
					 //do nothing
			//	 }
			//	 else 
			//	 {
					
					//System.out.println("Modify! Replace Token: "+ modifiedList.get(tokenCount-1).toString()+" and replace by: "+lastNl.toString());
				//	System.out.println("Test before Modify");
		//			modifiedList = new ArrayList<IToken>(getTokenList());
				//	System.out.println("modifiedList length "+modifiedList.size()+" tokencount "+tokenCount);
				//	System.out.println("content of LIST:");
					
					// for(int i = 0;i<modifiedList.size();i++)
					// {
						// System.out.println("Token "+i+" : "+modifiedList.get(i).getText());
					// }
					
					//modifiedList.add(lastNl);
					
		//			setTokenList(modifiedList);
					
		//			tokenCount++;
			//	 }
			 } 			 
			 lastNoNlToken = token; //save this non-newline token	
			 
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
				|| t instanceof TDbracketL || t instanceof TDbracketR || t instanceof TDpBracketL
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
