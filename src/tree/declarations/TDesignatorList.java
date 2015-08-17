package tree.declarations;

import java.util.LinkedList;

import tree.DefaultTreeNode;
import tree.TreeNode;
import tree.TreeNodeList;

public class TDesignatorList extends DefaultTreeNode implements TreeNodeList<TDesignator> {

	public TDesignatorList(TDesignatorList node) {
		super(node);
	}
	
	public LinkedList<TDesignator> getList() {
		LinkedList<TDesignator> list = new LinkedList<TDesignator>();
		for (TreeNode child : getChildren()) {
			list.add((TDesignator) child);
		}
		return list;
	}
	
	public TDesignatorList(TDesignator node) {
		addChild(node);
	}

	public TDesignatorList(TDesignatorList nodes, TDesignator node) {
		addChildrenOf(nodes);
		addChild(node);
	}

}
