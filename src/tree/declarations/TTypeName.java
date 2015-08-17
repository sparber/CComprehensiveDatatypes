package tree.declarations;

import java.util.LinkedList;

import errors.CXInternalError;

import tree.DefaultTreeNode;
import tree.TreeNode;
import tree.other.CommonDeclaration;
import tree.other.CommonSpecifiers;
import tree.other.CommonDeclarator;

public class TTypeName extends DefaultTreeNode implements CommonDeclaration {

	public TTypeName(TTypeName node) {
		super(node);
	}
	
	public TTypeName(TSpecifierQualifierList specqualis, TAbstractDeclarator decl) {
		addChild(specqualis);
		addChild(decl);
	}

	public TTypeName(TSpecifierQualifierList specqualis) {
		addChild(specqualis);
	}

	@Override
	public boolean hasCommonSpecifiers() {
		return true;
	}
	@Override
	public boolean hasDeclarationSpecifiers() {
		return false;
	}
	@Override
	public boolean hasSpecifierQualifierList() {
		return true;
	}

	@Override
	public CommonSpecifiers getCommonSpecifiers() {
		return getSpecifierQualifierList();
	}
	@Override
	public TDeclarationSpecifiers getDeclarationSpecifiers() {
		return null;
	}

	@Override
	public TSpecifierQualifierList getSpecifierQualifierList() {
		return (TSpecifierQualifierList) getChild(0);
	}

	@Override
	public void setSpecifiers(CommonSpecifiers node) {
		if (!(node instanceof TSpecifierQualifierList))
			throw new CXInternalError("tried to assign wrong type of specifiers");
		setChild(0, (TSpecifierQualifierList) node);
	}
	@Override
	public void setSpecifiers(LinkedList<TreeNode> specs) {
		setChild(0, TSpecifierQualifierList.from(specs));
	}

	@Override
	public boolean hasCommonDeclarator() {
		return hasAbstractDeclarator();
	}
	@Override
	public boolean hasDeclarator() {
		return false;
	}
	@Override
	public boolean hasAbstractDeclarator() {
		return getNChildren() == 2;
	}

	@Override
	public CommonDeclarator getCommonDeclarator() {
		return getAbstractDeclarator();
	}
	@Override
	public TDeclarator getDeclarator() {
		return null;
	}
	@Override
	public TAbstractDeclarator getAbstractDeclarator() {
		if (!hasCommonDeclarator())
			return null;
		return (TAbstractDeclarator) getChild(1);
	}

	@Override
	public void setCommonDeclarator(CommonDeclarator node) {
		if (node == null) {
			if (hasAbstractDeclarator())
				removeChild(1);
		} else {
			if (!(node instanceof TAbstractDeclarator))
				throw new CXInternalError("tried to assign wrong type of declarator");
			if (hasAbstractDeclarator())
				setChild(1, (TAbstractDeclarator) node);
			else
				addChild((TAbstractDeclarator) node);
		}
	}

}
