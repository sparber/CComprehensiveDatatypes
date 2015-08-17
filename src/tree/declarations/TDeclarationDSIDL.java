package tree.declarations;

import java.util.LinkedList;

import errors.CXInternalError;

import tree.TreeNode;
import tree.other.CommonDeclaration;
import tree.other.CommonSpecifiers;
import tree.other.CommonDeclarator;
import tree.symbols.TSSemicolon;

public class TDeclarationDSIDL extends TDeclaration implements CommonDeclaration {

	public TDeclarationDSIDL(TDeclarationDSIDL node) {
		super(node);
	}
	
	public TInitDeclaratorList getInitDeclaratorList() {
		return (TInitDeclaratorList) getChild(1);
	}

	public TSSemicolon getSemicolon() {
		return (TSSemicolon) getChild(2);
	}
	
	public TDeclarationDSIDL(TDeclarationSpecifiers specs, TInitDeclaratorList declist, TSSemicolon semicolon) {
		addChild(specs);
		addChild(declist);
		addChild(semicolon);
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
		if (!(node instanceof TDeclarationSpecifiers))
			throw new CXInternalError("tried to assign wrong type of specifiers");
		setChild(0, (TDeclarationSpecifiers) node);
	}

	@Override
	public void setSpecifiers(LinkedList<TreeNode> specs) {
		setChild(0, TDeclarationSpecifiers.from(specs));
	}

	@Override
	public boolean hasCommonDeclarator() {
		return hasDeclarator();
	}
	@Override
	public boolean hasDeclarator() {
		return getInitDeclaratorList().hasDeclarator();
	}
	@Override
	public boolean hasAbstractDeclarator() {
		return false;
	}

	@Override
	public CommonDeclarator getCommonDeclarator() {
		return getDeclarator();
	}
	@Override
	public TDeclarator getDeclarator() {
		return getInitDeclaratorList().getDeclarator();
	}
	@Override
	public TAbstractDeclarator getAbstractDeclarator() {
		return null;
	}

	@Override
	public void setCommonDeclarator(CommonDeclarator node) {
		if (node == null) {
			getInitDeclaratorList().setDeclarator(null);
		} else {
			if (!(node instanceof TDeclarator))
				throw new CXInternalError("tried to assign wrong type of declarator");
			getInitDeclaratorList().setDeclarator((TDeclarator) node);
		}
	}

}
