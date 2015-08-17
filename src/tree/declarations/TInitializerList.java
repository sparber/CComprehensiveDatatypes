package tree.declarations;

import java.util.LinkedList;

import tree.DefaultTreeNode;
import tree.TreeNode;
import tree.TreeNodeList;
import tree.symbols.TSComma;

public class TInitializerList extends DefaultTreeNode implements TreeNodeList<TDesignationInitializer> {

	public TInitializerList(TInitializerList node) {
		super(node);
	}
	
	public LinkedList<TDesignationInitializer> getList() {
		LinkedList<TDesignationInitializer> list = new LinkedList<TDesignationInitializer>();
		for (TreeNode child : getChildren()) {
			if (child instanceof TDesignationInitializer)
				list.add((TDesignationInitializer) child);
		}
		return list;
	}

	public TInitializerList(TDesignationInitializer node) {
		addChild(node);
	}

	public TInitializerList(TInitializerList nodes, TSComma comma, TDesignationInitializer node) {
		addChildrenOf(nodes);
		addChild(comma);
		addChild(node);
	}

}
