import CSPMparser.analysis.*;
import CSPMparser.node.*;
import java.util.*;
import java.io.*;


public class SymInfo
{
	public Node node;
	public String symbolInfo;
	public String symbolName;
	public String symbolReference;
	public int scopeNumber;
		
	public SymInfo(Node n, String syminf, int scn, String sn, String sr)
	{
		node = n;
		symbolInfo = syminf;
		scopeNumber = scn;
		symbolName = sn;
		symbolReference = sr;
	}
	
}