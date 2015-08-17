package tree.declarations;

import java.util.LinkedList;

import errors.CXInternalError;

import tree.DefaultTreeNode;
import tree.TreeNode;
import tree.other.CommonDeclaration;
import tree.other.CommonSpecifiers;
import tree.other.CommonDeclarator;

public class TParameterDeclaration extends DefaultTreeNode implements CommonDeclaration {

	public TParameterDeclaration(TParameterDeclaration node) {
		super(node);
	}
	
	public TParameterDeclaration(TDeclarationSpecifiers specs, TDeclarator decl) {
		addChild(specs);
		addChild(decl);
	}
	
	public TParameterDeclaration(TDeclarationSpecifiers specs, TAbstractDeclarator decl) {
		addChild(specs);
		addChild(decl);
	}

	public TParameterDeclaration(TDeclarationSpecifiers specs) {
		addChild(specs);
	}

	@Override
	public boolean hasCommonSpecifiers() {
		return true;
	}
	@Override
	public boolean hasDeclarationSpecifiers() {
		return true;
	}
	@Override
	public boolean hasSpecifierQualifierList() {
		return false;
	}

	@Override
	public CommonSpecifiers getCommonSpecifiers() {
		return getDeclarationSpecifiers();
	}
	@Override
	public TDeclarationSpecifiers getDeclarationSpecifiers() {
		return (TDeclarationSpecifiers) getChild(0);
	}
	@Override
	public TSpecifierQualifierList getSpecifierQualifierList() {
		return null;
	}

	@Override
	public void setSpecifiers(CommonSpecifiers node) {
		if (!(node instanceof TDeclarationSpecifiers)) {
			throw new CXInternalError("tried to assign wrong type of specifiers");
		}
		setChild(0, (TDeclarationSpecifiers) node);
	}
	@Override
	public void setSpecifiers(LinkedList<TreeNode> specs) {
		setChild(0, TDeclarationSpecifiers.from(specs));
	}

	@Override
	public boolean hasCommonDeclarator() {
		return getNChildren() == 2;
	}
	@Override
	public boolean hasDeclarator() {
		return hasCommonDeclarator() && getChild(1) instanceof TDeclarator;
	}
	@Override
	public boolean hasAbstractDeclarator() {
		return hasCommonDeclarator() && getChild(1) instanceof TAbstractDeclarator;
	}

	@Override
	public CommonDeclarator getCommonDeclarator() {
		if (!hasCommonDeclarator())
			return null;
		return (CommonDeclarator) getChild(1);
	}
	@Override
	public TDeclarator getDeclarator() {
		if (!hasDeclarator())
			return null;
		return (TDeclarator) getChild(1);
	}
	@Override
	public TAbstractDeclarator getAbstractDeclarator() {
		if (!hasAbstractDeclarator())
			return null;
		return (TAbstractDeclarator) getChild(1);
	}

	@Override
	public void setCommonDeclarator(CommonDeclarator node) {
		if (node == null) {
			if (hasCommonDeclarator())
				removeChild(1);
		} else {
			if (!hasCommonDeclarator())
				addChild(node);
			else
				setChild(1, node);
		}
	}

	public boolean isVoid() {
		if (hasCommonDeclarator())
			return false;
		return getCommonSpecifiers().isVoid();
	}

}
