package tree.declarations;

import java.util.LinkedList;

import errors.CXInternalError;

import tree.TreeNode;
import tree.other.CommonDeclaration;
import tree.other.CommonSpecifiers;
import tree.other.CommonDeclarator;
import tree.symbols.TSSemicolon;

public class TStructDeclarationSQLSDL extends TStructDeclaration implements CommonDeclaration {
	
	public TStructDeclarationSQLSDL(TStructDeclarationSQLSDL node) {
		super(node);
	}
	
	public TStructDeclarationSQLSDL(TSpecifierQualifierList specqualis, TStructDeclaratorList decllist, TSSemicolon semicolon) {
		addChild(specqualis);
		addChild(decllist);
		addChild(semicolon);
	}

	public TStructDeclaratorList getStructDeclaratorList() {
		return (TStructDeclaratorList) getChild(1);
	}
	
	public TSSemicolon getSemicolon() {
		return (TSSemicolon) getChild(2);
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
		return false;
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
		return hasDeclarator();
	}
	@Override
	public boolean hasDeclarator() {
		return getStructDeclaratorList().hasDeclarator();
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
		return getStructDeclaratorList().getDeclarator();
	}
	@Override
	public TAbstractDeclarator getAbstractDeclarator() {
		return null;
	}

	@Override
	public void setCommonDeclarator(CommonDeclarator node) {
		if (node == null) {
			getStructDeclaratorList().setDeclarator(null);
		} else {
			if (!(node instanceof TDeclarator))
				throw new CXInternalError("tried to assign wrong type of declarator");
			getStructDeclaratorList().setDeclarator((TDeclarator) node);
		}
	}

}
