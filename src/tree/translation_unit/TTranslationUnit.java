package tree.translation_unit;

import java.util.LinkedList;

import tree.DefaultTreeNode;
import tree.TreeNode;
import tree.TreeNodeList;

public class TTranslationUnit extends DefaultTreeNode implements TreeNodeList<TExternalDeclaration> {

	public TTranslationUnit(TTranslationUnit node) {
		super(node);
	}
	
	public LinkedList<TExternalDeclaration> getList() {
		LinkedList<TExternalDeclaration> list = new LinkedList<TExternalDeclaration>();
		for (TreeNode child : getChildren()) {
			list.add((TExternalDeclaration) child);
		}
		return list;
	}

	public TTranslationUnit(TExternalDeclaration node) {
		addChild(node);
	}
	
	public TTranslationUnit(TTranslationUnit nodes, TExternalDeclaration node) {
		addChildrenOf(nodes);
		addChild(node);
	}

	public static TTranslationUnit from(LinkedList<TExternalDeclaration> extdecls) {
		TTranslationUnit declist = null;
		for (TExternalDeclaration decl : extdecls) {
			if (declist == null) {
				declist = new TTranslationUnit(decl);
			} else {
				declist = new TTranslationUnit(declist, decl);
			}
		}
		return declist;
	}

}
