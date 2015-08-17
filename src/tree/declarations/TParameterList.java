package tree.declarations;

import java.util.LinkedList;

import tree.DefaultTreeNode;
import tree.TreeNode;
import tree.TreeNodeList;
import tree.symbols.TSComma;

public class TParameterList extends DefaultTreeNode implements TreeNodeList<TParameterDeclaration> {

	public TParameterList(TParameterList node) {
		super(node);
	}
	
	public LinkedList<TParameterDeclaration> getList() {
		LinkedList<TParameterDeclaration> list = new LinkedList<TParameterDeclaration>();
		for (TreeNode child : getChildren()) {
			if (child instanceof TParameterDeclaration)
				list.add((TParameterDeclaration) child);
		}
		return list;
	}

	public TParameterList(TParameterDeclaration node) {
		addChild(node);
	}

	public TParameterList(TParameterList nodes, TSComma comma, TParameterDeclaration node) {
		addChildrenOf(nodes);
		addChild(comma);
		addChild(node);
	}

	public static TParameterList from(LinkedList<TParameterDeclaration> list) {
		TParameterList plist = null;
		for (TParameterDeclaration pdecl : list) {
			if (plist == null) {
				plist = new TParameterList(pdecl);
			} else {
				plist = new TParameterList(plist, new TSComma(), pdecl);
			}
		}
		return plist;
	}

}
