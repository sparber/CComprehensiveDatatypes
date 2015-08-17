package tree.declarations;

import java.util.LinkedList;

import tree.DefaultTreeNode;
import tree.TreeNode;
import tree.TreeNodeList;
import tree.symbols.TSComma;

public class TEnumeratorList extends DefaultTreeNode implements TreeNodeList<TEnumerator> {

	public TEnumeratorList(TEnumeratorList node) {
		super(node);
	}
	
	public LinkedList<TEnumerator> getList() {
		LinkedList<TEnumerator> list = new LinkedList<TEnumerator>();
		for (TreeNode child : getChildren()) {
			if (child instanceof TEnumerator)
				list.add((TEnumerator) child);
		}
		return list;
	}

	public TEnumeratorList(TEnumerator node) {
		addChild(node);
	}

	public TEnumeratorList(TEnumeratorList nodes, TSComma comma, TEnumerator node) {
		addChildrenOf(nodes);
		addChild(comma);
		addChild(node);
	}

}
