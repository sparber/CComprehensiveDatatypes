package tree.other;

import java.util.LinkedList;

import tree.TreeNode;
import tree.declarations.TDeclarationSpecifiers;
import tree.declarations.TSpecifierQualifierList;

public interface CommonSpecifiers extends TreeNode {
	
	public LinkedList<TreeNode> getList();
	
	public TDeclarationSpecifiers toDeclarationSpecifiers();
	public TSpecifierQualifierList toSpecifierQualifierList();

	public boolean isVoid();

}
