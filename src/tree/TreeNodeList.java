package tree;

import java.util.LinkedList;


public interface TreeNodeList<E extends TreeNode> extends TreeNode {
	
	public LinkedList<E> getList();
	
}
