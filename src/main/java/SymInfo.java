import CSPMparser.analysis.*;
import CSPMparser.node.*;
import java.util.*;
import java.io.*;


public class SymInfo
{
	private Node node;
	private String symbolInfo;
	private int structCount; //Reference to struct
	private boolean comprehensionArg;
	
	
	public SymInfo(Node n, String st, int stc)
	{
		node = n;
		symbolInfo = st;
		structCount = stc;
		comprehensionArg = false;
	}
	
	public String getSymbolInfo()
	{
		return symbolInfo;
	}
	
	public void setSymbolInfo(String s)
	{
		symbolInfo = s;
	}
	
	public Node getNode()
	{
		return node;
	}
	
	public void setNode(Node n)
	{
		node = n;
	}
	
	public int getStructCount()
	{
		return structCount;
	}
	
	public void setComprehensionArg(boolean b)
	{
		comprehensionArg = b;
	}
	
	public boolean isComprehensionArg()
	{
		return comprehensionArg;
	}
	
	
}