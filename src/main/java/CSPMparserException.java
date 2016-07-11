
import CSPMparser.node.Token;

@SuppressWarnings("serial")
public class CSPMparserException extends Exception {

	private final Token token;

	/**
	 * This constructor is not intended to be referenced by clients.
	 */
	public CSPMparserException(final Token token, final String msg) {
		super(msg);
		this.token = token;
	}

	public String getTokenString() {
		return token == null ? null : token.getText();
	}

	public int getTokenLine() {
		return token == null ? 0 : token.getLine();
	}

	public int getTokenColumn() {
		return token == null ? 0 : token.getPos();
	}

}
