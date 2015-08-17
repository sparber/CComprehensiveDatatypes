package tree.declarations;

import java.util.LinkedList;

import tree.DefaultTreeNode;
import tree.TreeNode;
import tree.TreeNodeList;
import tree.other.CommonSpecifiers;

public class TSpecifierQualifierList extends DefaultTreeNode implements TreeNodeList<TreeNode>, CommonSpecifiers {

	public TSpecifierQualifierList(TSpecifierQualifierList node) {
		super(node);
	}
	
	public LinkedList<TreeNode> getList() {
		return getChildren();
	}

	public TSpecifierQualifierList(TTypeSpecifier node) {
		addChild(node);
	}

	public TSpecifierQualifierList(TTypeQualifier node) {
		addChild(node);
	}

	public TSpecifierQualifierList(TTypeSpecifier node, TSpecifierQualifierList nodes) {
		addChild(node);
		addChildrenOf(nodes);
	}

	public TSpecifierQualifierList(TTypeQualifier node, TSpecifierQualifierList nodes) {
		addChild(node);
		addChildrenOf(nodes);
	}

	private TSpecifierQualifierList(LinkedList<TreeNode> list) {
		addChildren(list);
	}

	public static TSpecifierQualifierList from(LinkedList<TreeNode> specs) {
		return new TSpecifierQualifierList(specs);
	}

	@Override
	public TDeclarationSpecifiers toDeclarationSpecifiers() {
		return TDeclarationSpecifiers.from(getList());
	}

	@Override
	public TSpecifierQualifierList toSpecifierQualifierList() {
		return this;
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
