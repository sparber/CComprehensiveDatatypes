package tree.declarations;

import java.util.Collection;
import java.util.LinkedList;

import errors.CXInternalError;

import tree.DefaultVisitor;
import tree.DefaultTreeNode;
import tree.TreeNodeList;
import tree.DefaultTreeNodeSymbol;
import tree.TreeNode;
import tree.other.CommonSpecifiers;
import tree.symbols.TSTypedef;

public class TDeclarationSpecifiers extends DefaultTreeNode implements TreeNodeList<TreeNode>, CommonSpecifiers {
	
	public TDeclarationSpecifiers(TDeclarationSpecifiers node) {
		super(node);
	}

	public LinkedList<TreeNode> getList() {
		return getChildren();
	}
	
	public TDeclarationSpecifiers(TStorageClassSpecifier node) {
		addChild(node);
	}

	public TDeclarationSpecifiers(TTypeSpecifier node) {
		addChild(node);
	}

	public TDeclarationSpecifiers(TTypeQualifier node) {
		addChild(node);
	}

	public TDeclarationSpecifiers(TFunctionSpecifier node) {
		addChild(node);
	}

	public TDeclarationSpecifiers(TAlignmentSpecifier node) {
		addChild(node);
	}

	public TDeclarationSpecifiers(TStorageClassSpecifier node, TDeclarationSpecifiers nodes) {
		addChild(node);
		addChildrenOf(nodes);
	}

	public TDeclarationSpecifiers(TTypeSpecifier node, TDeclarationSpecifiers nodes) {
		addChild(node);
		addChildrenOf(nodes);
	}

	public TDeclarationSpecifiers(TTypeQualifier node, TDeclarationSpecifiers nodes) {
		addChild(node);
		addChildrenOf(nodes);
	}

	public TDeclarationSpecifiers(TFunctionSpecifier node, TDeclarationSpecifiers nodes) {
		addChild(node);
		addChildrenOf(nodes);
	}

	public TDeclarationSpecifiers(TAlignmentSpecifier node, TDeclarationSpecifiers nodes) {
		addChild(node);
		addChildrenOf(nodes);
	}


	public class TypedefVisitor extends DefaultVisitor {
		public boolean contains = false;
		@Override
		public DefaultTreeNodeSymbol visitTSTypedef(TSTypedef symbol) {
			contains = true;
			return null;
		}
	}
	
	public boolean containsTypedef() {
		TypedefVisitor visitor = new TypedefVisitor();
		try {
			accept(visitor);
		} catch (Throwable e) {
			throw new CXInternalError("something went wrong during type name collection", e);
		}
		return visitor.contains;
	}
	
	private TDeclarationSpecifiers() {
	}

	public static TDeclarationSpecifiers from(Collection<TreeNode> list) {
		TDeclarationSpecifiers decl = new TDeclarationSpecifiers();
		decl.addChildren(list);
		return decl;
	}

	@Override
	public TDeclarationSpecifiers toDeclarationSpecifiers() {
		return this;
	}

	@Override
	public TSpecifierQualifierList toSpecifierQualifierList() {
		return TSpecifierQualifierList.from(getList());
	}

	@Override
	public boolean isVoid() {
		LinkedList<TreeNode> list = getList();
		if (list.size() != 1)
			return false;
		if (!(list.getFirst() instanceof TTypeSpecifier))
			return false;
		TTypeSpecifier tspec = (TTypeSpecifier) list.getFirst();
		return tspec.isVoid();
	}

}
