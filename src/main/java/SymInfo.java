import CSPMparser.analysis.*;
import CSPMparser.node.*;
import java.util.*;
import java.io.*;


public class SymInfo
{
	private Node node;
	private String symbolInfo;
	
	
	public SymInfo(Node n, String st)
	{
		node = n;
		symbolInfo = st;
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
	
	
}