package tree.expressions;

import java.util.LinkedList;

import tree.DefaultTreeNode;
import tree.TreeNode;
import tree.TreeNodeList;
import tree.symbols.TSComma;

public class TGenericAssocList extends DefaultTreeNode implements TreeNodeList<TGenericAssociation> {

	public TGenericAssocList(TGenericAssocList node) {
		super(node);
	}
	
	public LinkedList<TGenericAssociation> getList() {
		LinkedList<TGenericAssociation> list = new LinkedList<TGenericAssociation>();
		for (TreeNode child : getChildren()) {
			if (child instanceof TGenericAssociation)
				list.add((TGenericAssociation) child);
		}
		return list;
	}

	public TGenericAssocList(TGenericAssociation node) {
		addChild(node);
	}

	public TGenericAssocList(TGenericAssocList nodes, TSComma comma, TGenericAssociation node) {
		addChildrenOf(nodes);
		addChild(comma);
		addChild(node);
	}

}
