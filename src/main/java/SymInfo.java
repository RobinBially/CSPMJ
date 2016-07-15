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
	public int blockNumber;
	public boolean comprehensionArg;
	
	
	public SymInfo(Node n, String syminf, int bn, String sn, String sr)
	{
		node = n;
		symbolInfo = syminf;
		blockNumber = bn;
		comprehensionArg = false;
		symbolName = sn;
		symbolReference = sr;
	}
	
}