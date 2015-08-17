package tree.statements;

import java.util.LinkedList;

import tree.DefaultTreeNode;
import tree.TreeNode;
import tree.TreeNodeList;

public class TBlockItemList extends DefaultTreeNode implements TreeNodeList<TBlockItem> {

	public TBlockItemList(TBlockItemList node) {
		super(node);
	}
	
	public LinkedList<TBlockItem> getList() {
		LinkedList<TBlockItem> list = new LinkedList<TBlockItem>();
		for (TreeNode child : getChildren()) {
			list.add((TBlockItem) child);
		}
		return list;
	}

	public TBlockItemList(TBlockItem node) {
		addChild(node);
	}

	public TBlockItemList(TBlockItemList nodes, TBlockItem node) {
		addChildrenOf(nodes);
		addChild(node);
	}

	public static TBlockItemList from(LinkedList<TBlockItem> items) {
		TBlockItemList itemlist = null;
		for (TBlockItem item : items) {
			if (itemlist == null) {
				itemlist = new TBlockItemList(item);
			} else {
				itemlist = new TBlockItemList(itemlist, item);
			}
		}
		return itemlist;
	}

}
