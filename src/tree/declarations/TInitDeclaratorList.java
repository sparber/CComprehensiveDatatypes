package tree.declarations;

import java.util.LinkedList;

import errors.CXInternalError;

import tree.DefaultVisitor;
import tree.DefaultTreeNode;
import tree.TreeNode;
import tree.TreeNodeList;
import tree.symbols.TSComma;

public class TInitDeclaratorList extends DefaultTreeNode implements TreeNodeList<TInitDeclarator> {

	public TInitDeclaratorList(TInitDeclaratorList node) {
		super(node);
	}
	
	public LinkedList<TInitDeclarator> getList() {
		LinkedList<TInitDeclarator> list = new LinkedList<TInitDeclarator>();
		for (TreeNode child : getChildren()) {
			if (child instanceof TInitDeclarator) {
				list.add((TInitDeclarator) child);
			}
		}
		return list;
	}

	public TInitDeclaratorList(TInitDeclarator node) {
		addChild(node);
	}

	public TInitDeclaratorList(TInitDeclaratorList nodes, TSComma comma, TInitDeclarator node) {
		addChildrenOf(nodes);
		addChild(comma);
		addChild(node);
	}
	
	public class TypeNameVisitor extends DefaultVisitor {
		public LinkedList<String> names = new LinkedList<String>();
		
		@Override
		public boolean visitBeforeTParameterTypeList(TParameterTypeList node) {
			return false;
		}
		@Override
		public boolean visitBeforeTIdentifierList(TIdentifierList node) {
			return false;
		}

		@Override
		public TDirectDeclarator visitAfterTDirectDeclaratorI(TDirectDeclaratorI node) {
			names.add(node.getNameIdentifier().value);
			return null;
		}
	}
	
	public LinkedList<String> getNewTypeNames() {
		TypeNameVisitor visitor = new TypeNameVisitor();
		try {
			accept(visitor);
		} catch (Throwable e) {
			throw new CXInternalError("error in type name collection", e);
		}
		return visitor.names;
	}

	public static TInitDeclaratorList from(LinkedList<TInitDeclarator> initdecls) {
		TInitDeclaratorList declist = null;
		for (TInitDeclarator decl : initdecls) {
			if (declist == null) {
				declist = new TInitDeclaratorList(decl);
			} else {
				declist = new TInitDeclaratorList(declist, new TSComma(), decl);
			}
		}
		return declist;
	}

	public boolean hasDeclarator() {
		LinkedList<TInitDeclarator> initdecls = getList();
		if (initdecls.size() == 1)
			return true;
		else
			return false;
	}

	public TDeclarator getDeclarator() {
		LinkedList<TInitDeclarator> initdecls = getList();
		if (initdecls.size() == 1)
			return initdecls.getFirst().getDeclarator();
		else
			return null;
	}

	public void setDeclarator(TDeclarator node) {
		LinkedList<TInitDeclarator> initdecls = getList();
		if (initdecls.size() == 1)
			initdecls.getFirst().setDeclarator(node);
	}

}
