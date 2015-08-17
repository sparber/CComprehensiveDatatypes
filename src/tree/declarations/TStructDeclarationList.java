package tree.declarations;

import java.util.LinkedList;

import tree.DefaultTreeNode;
import tree.TreeNode;
import tree.TreeNodeList;

public class TStructDeclarationList extends DefaultTreeNode implements TreeNodeList<TStructDeclaration> {

	public TStructDeclarationList(TStructDeclarationList node) {
		super(node);
	}
	
	public LinkedList<TStructDeclaration> getList() {
		LinkedList<TStructDeclaration> list = new LinkedList<TStructDeclaration>();
		for (TreeNode child : getChildren()) {
			list.add((TStructDeclaration) child);
		}
		return list;
	}

	public TStructDeclarationList(TStructDeclaration node) {
		addChild(node);
	}

	public TStructDeclarationList(TStructDeclarationList nodes, TStructDeclaration node) {
		addChildrenOf(nodes);
		addChild(node);
	}

	public static TStructDeclarationList from(LinkedList<TStructDeclaration> list) {
		TStructDeclarationList declist = null;
		for (TStructDeclaration decl : list) {
			if (declist == null) {
				declist = new TStructDeclarationList(decl);
			} else {
				declist = new TStructDeclarationList(declist, decl);
			}
		}
		return declist;
	}

}
