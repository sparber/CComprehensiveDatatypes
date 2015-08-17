package tree.other;

import tree.TreeNode;
import tree.declarations.TDirectAbstractDeclarator;
import tree.declarations.TParameterDeclaration;

public interface CommonDirectDeclarator extends TreeNode {
	
	public TreeNode getParams();

	public TDirectAbstractDeclarator getTypeDeclarator();
	public CommonDirectDeclarator getNameDeclarator();

	public CommonDirectDeclarator translateGenerator(TParameterDeclaration param);

}
