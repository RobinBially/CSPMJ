import CSPMparser.analysis.*;
import CSPMparser.node.*;
import java.util.*;
import java.io.*;


public class SymInfo
{
	private Node node;
	private String symbolInfo;
	private int letWithinCount; //Reference to letWithin block
	
	
	public SymInfo(Node n, String st, int lwc)
	{
		node = n;
		symbolInfo = st;
		letWithinCount = lwc;
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
	
	public int getLetWithinCount()
	{
		return letWithinCount;
	}
	
}