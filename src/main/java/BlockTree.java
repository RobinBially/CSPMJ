import java.util.*;
import java.io.*;

public class BlockTree
{
	private int currentBlockNumber;
	private int blockCounter;
	private TreeMap<Integer,Integer> blockStructure;
	private HashMap<Integer,TreeMap<String,String>> tree;
	
	public BlockTree()
	{
		currentBlockNumber = 0;
		blockCounter = 0;
		blockStructure = new TreeMap<Integer,Integer>();
		tree = new HashMap<Integer,TreeMap<String,String>>();
	}
	
	public void newLeaf()
	{
		blockCounter++;
		blockStructure.put(blockCounter,currentBlockNumber);
		currentBlockNumber = blockCounter;
	}
	
	public void returnToParent()
	{
		currentBlockNumber = blockStructure.get(currentBlockNumber);
	}
	
	public void addSymbol(String symbol, String name)
	{
		if(tree.get(currentBlockNumber) == null)
		{
			TreeMap<String,String> tm = new TreeMap<String,String>();
			tm.put(name,symbol);
			tree.put(currentBlockNumber,tm);
		}	
		else
		{
			TreeMap<String,String> tm = tree.get(currentBlockNumber);
			tm.put(name,symbol);
		}
	}
	
	public boolean isDefined(String str)
	{
		if(tree.get(currentBlockNumber) == null)
		{
			return false;
		}
		else
		{
			if(tree.get(currentBlockNumber).containsValue(str))
			{
				return true;
			}
		}
		return false;
	}
	
	public String getSymbol(String str)
	{
		if(tree.get(currentBlockNumber) == null)
		{
			return null;
		}
		else
		{
			Map<String, String> reverseMap = new TreeMap(Collections.reverseOrder());
			reverseMap.putAll(tree.get(currentBlockNumber));
			for(String key : reverseMap.keySet())
			{
				return key;
			}
			
		}
		return null;
	}
	
	public int getCurrentBlockNumber()
	{
		return currentBlockNumber;
	}
	public void setCurrentBlockNumber(int num)
	{
		currentBlockNumber = num;
	}
}