package tree.other;

import java.util.LinkedList;

import tree.TreeNode;
import tree.declarations.TAbstractDeclarator;
import tree.declarations.TDeclarationSpecifiers;
import tree.declarations.TDeclarator;
import tree.declarations.TSpecifierQualifierList;

public interface CommonDeclaration extends TreeNode {

	public boolean hasCommonSpecifiers();
	public boolean hasDeclarationSpecifiers();
	public boolean hasSpecifierQualifierList();
	
	public CommonSpecifiers getCommonSpecifiers();
	public TDeclarationSpecifiers getDeclarationSpecifiers();
	public TSpecifierQualifierList getSpecifierQualifierList();
	
	public void setSpecifiers(CommonSpecifiers node);
	public void setSpecifiers(LinkedList<TreeNode> specs);
	
	
	public boolean hasCommonDeclarator();
	public boolean hasDeclarator();
	public boolean hasAbstractDeclarator();
	
	public CommonDeclarator getCommonDeclarator();
	public TDeclarator getDeclarator();
	public TAbstractDeclarator getAbstractDeclarator();
	
	public void setCommonDeclarator(CommonDeclarator node);
	
}
