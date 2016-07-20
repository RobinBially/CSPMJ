import java.util.*;
import java.io.*;

public class ScopeTree
{
	private int currentScopeNumber;
	private int scopeCounter;
	public TreeMap<Integer,Integer> scopeStructure;
	public HashMap<Integer,TreeMap<String,String>> tree;
	
	public ScopeTree()
	{
		currentScopeNumber = 0;
		scopeCounter = 0;
		scopeStructure = new TreeMap<Integer,Integer>();
		tree = new HashMap<Integer,TreeMap<String,String>>();
	}
	
	public void newLeaf()
	{
		scopeCounter++;
		//System.out.println("New Number is"+ scopeCounter);
		scopeStructure.put(scopeCounter,currentScopeNumber);
		currentScopeNumber = scopeCounter;
	}
	
	public boolean returnToParent()
	{
		if(scopeStructure.get(currentScopeNumber) != null)
		{
			currentScopeNumber = scopeStructure.get(currentScopeNumber);
			//System.out.println("Previous number was"+ currentScopeNumber);
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public void addSymbol(String symbol, String name)
	{
		if(tree.get(currentScopeNumber) == null)
		{
			TreeMap<String,String> tm = new TreeMap<String,String>();
			tm.put(name,symbol);
			tree.put(currentScopeNumber,tm);
		}	
		else
		{
			TreeMap<String,String> tm = tree.get(currentScopeNumber);
			tm.put(name,symbol);
		}
	}
	
	public boolean isDefined(String str)
	{
		if(tree.get(currentScopeNumber) == null)
		{
			return false;
		}
		else
		{
			if(tree.get(currentScopeNumber).containsValue(str))
			{
				return true;
			}
		}
		return false;
	}
	
	public String getSymbol(String str)
	{
		if(tree.get(currentScopeNumber) == null)
		{
			return "";
		}
		else
		{
			Map<String, String> reverseMap = new TreeMap(Collections.reverseOrder());
			reverseMap.putAll(tree.get(currentScopeNumber));
			for(String key : reverseMap.keySet())
			{
				if(reverseMap.get(key).equals(str))
				return key;
			}
			
		}
		return "";
	}
	
	public int getCurrentScopeNumber()
	{
		return currentScopeNumber;
	}
	public void setCurrentScopeNumber(int num)
	{
		currentScopeNumber = num;
	}
}