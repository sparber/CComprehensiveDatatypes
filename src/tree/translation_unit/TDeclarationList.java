package tree.translation_unit;

import java.util.LinkedList;

import tree.DefaultTreeNode;
import tree.TreeNode;
import tree.TreeNodeList;
import tree.declarations.TDeclaration;

public class TDeclarationList extends DefaultTreeNode implements TreeNodeList<TDeclaration> {

	public TDeclarationList(TDeclarationList node) {
		super(node);
	}
	
	public LinkedList<TDeclaration> getList() {
		LinkedList<TDeclaration> list = new LinkedList<TDeclaration>();
		for (TreeNode child : getChildren()) {
			list.add((TDeclaration) child);
		}
		return list;
	}

	public TDeclarationList(TDeclaration node) {
		addChild(node);
	}

	public TDeclarationList(TDeclarationList nodes, TDeclaration node) {
		addChildrenOf(nodes);
		addChild(node);
	}

	public static TDeclarationList from(LinkedList<TDeclaration> decls) {
		TDeclarationList declist = null;
		for (TDeclaration decl : decls) {
			if (declist == null) {
				declist = new TDeclarationList(decl);
			} else {
				declist = new TDeclarationList(declist, decl);
			}
		}
		return declist;
	}

}
