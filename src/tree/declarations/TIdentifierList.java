package tree.declarations;

import java.util.LinkedList;

import tree.DefaultTreeNode;
import tree.TreeNode;
import tree.TreeNodeList;
import tree.symbols.TSComma;
import tree.symbols.TSIdentifier;

public class TIdentifierList extends DefaultTreeNode implements TreeNodeList<TSIdentifier> {

	public TIdentifierList(TIdentifierList node) {
		super(node);
	}
	
	public LinkedList<TSIdentifier> getList() {
		LinkedList<TSIdentifier> list = new LinkedList<TSIdentifier>();
		for (TreeNode child : getChildren()) {
			list.add((TSIdentifier) child);
		}
		return list;
	}

	public TIdentifierList(TSIdentifier node) {
		addChild(node);
	}

	public TIdentifierList(TIdentifierList nodes, TSComma comma, TSIdentifier node) {
		addChildrenOf(nodes);
		addChild(comma);
		addChild(node);
	}

}
