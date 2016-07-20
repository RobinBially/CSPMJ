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
	public boolean comprehensionArg;
	
	
	public SymInfo(Node n, String syminf, int scn, String sn, String sr)
	{
		node = n;
		symbolInfo = syminf;
		scopeNumber = scn;
		comprehensionArg = false;
		symbolName = sn;
		symbolReference = sr;
	}
	
}