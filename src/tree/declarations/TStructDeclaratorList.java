package tree.declarations;

import java.util.LinkedList;

import tree.DefaultTreeNode;
import tree.TreeNode;
import tree.TreeNodeList;
import tree.symbols.TSComma;

public class TStructDeclaratorList extends DefaultTreeNode implements TreeNodeList<TStructDeclarator> {

	public TStructDeclaratorList(TStructDeclaratorList node) {
		super(node);
	}
	
	public LinkedList<TStructDeclarator> getList() {
		LinkedList<TStructDeclarator> list = new LinkedList<TStructDeclarator>();
		for (TreeNode child : getChildren()) {
			if (child instanceof TStructDeclarator)
				list.add((TStructDeclarator) child);
		}
		return list;
	}

	public TStructDeclaratorList(TStructDeclarator node) {
		addChild(node);
	}

	public TStructDeclaratorList(TStructDeclaratorList nodes, TSComma comma, TStructDeclarator node) {
		addChildrenOf(nodes);
		addChild(comma);
		addChild(node);
	}

	public static TStructDeclaratorList from(LinkedList<TStructDeclarator> initdecls) {
		TStructDeclaratorList declist = null;
		for (TStructDeclarator decl : initdecls) {
			if (declist == null) {
				declist = new TStructDeclaratorList(decl);
			} else {
				declist = new TStructDeclaratorList(declist, new TSComma(), decl);
			}
		}
		return declist;
	}

	public boolean hasDeclarator() {
		LinkedList<TStructDeclarator> sdecls = getList();
		if (sdecls.size() == 1 && sdecls.getFirst().hasDeclarator())
			return true;
		else
			return false;
	}

	public TDeclarator getDeclarator() {
		LinkedList<TStructDeclarator> sdecls = getList();
		if (sdecls.size() == 1)
			return sdecls.getFirst().getDeclarator();
		else
			return null;
	}

	public void setDeclarator(TDeclarator node) {
		LinkedList<TStructDeclarator> sdecls = getList();
		if (sdecls.size() == 1)
			sdecls.getFirst().setDeclarator(node);
	}

}
