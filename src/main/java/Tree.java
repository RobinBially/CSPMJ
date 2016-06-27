/**********************************************************
* Tree data structure for CSPM - "let within" expressions *
*				   robin.bially@hhu.de					  *
***********************************************************/

import java.util.*;

public class Tree
{
	private Tree parent;
	private ArrayList<Tree> leafs = new ArrayList<Tree>();
	private HashMap<String,String> types = new HashMap<String,String>();
	private HashMap<String,String> validTypes = new HashMap<String,String>();
	private boolean currentNode;
	
	public Tree(Tree t)
	{
		parent = t;	
		currentNode = true;
	}
	
	public Tree getParent()
	{
		return parent;
	}
	
	public HashMap<String,String> getTypes()
	{
		return types;
	}
	
	public void addLeaf()
	{
		currentNode = false;
		leafs.add(new Tree(this));
	}
	
	public ArrayList<Tree> getLeafs()
	{
		return leafs;
	}
	
	public void setParentAsCurrent()
	{
		currentNode = false;
		parent.setCurrent(true);
	}
	
	public void setCurrent(boolean b)
	{
		currentNode = b;
	}
	
	public Tree getCurrent()
	{
		if(currentNode == true)
		{
			return this;
		}
		else
		{
			for(Tree l : leafs)
			{
				if(l.getCurrent() != null)
				{
					return l.getCurrent();
				}
			}
			return null;
		}
	}
	
	//Merge types from all leafs up to the root
	public HashMap<String,String> getValidTypes() 
	{
			Tree current = parent;
			validTypes.putAll(types);

			while(current != null)
			{			
				validTypes.putAll(current.getTypes());
				current = current.getParent();
			}			
			return validTypes;
	}
	
	public void setHeadAsCurrent() //This should NOT be called in a leaf!!!
	{
		deleteCurrentNode();
		currentNode = true;
	}
	
	public void deleteCurrentNode()
	{
		for(int i = 0; i<leafs.size();i++)
		{
			leafs.get(i).deleteCurrentNode();
		}
		currentNode = false;
	}
}