package tree.declarations;

import java.util.LinkedList;

import tree.DefaultTreeNode;
import tree.TreeNode;
import tree.TreeNodeList;

public class TTypeQualifierList extends DefaultTreeNode implements TreeNodeList<TTypeQualifier> {

	public TTypeQualifierList(TTypeQualifierList node) {
		super(node);
	}
	
	public LinkedList<TTypeQualifier> getList() {
		LinkedList<TTypeQualifier> list = new LinkedList<TTypeQualifier>();
		for (TreeNode child : getChildren()) {
			list.add((TTypeQualifier) child);
		}
		return list;
	}

	public TTypeQualifierList(TTypeQualifier node) {
		addChild(node);
	}

	public TTypeQualifierList(TTypeQualifierList nodes, TTypeQualifier node) {
		addChildrenOf(nodes);
		addChild(node);
	}

}
