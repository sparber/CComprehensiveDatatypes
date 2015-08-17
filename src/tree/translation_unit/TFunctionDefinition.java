package tree.translation_unit;

import java.util.LinkedList;

import errors.CXInternalError;

import tree.DefaultTreeNode;
import tree.TreeNode;
import tree.declarations.TAbstractDeclarator;
import tree.declarations.TDeclarationSpecifiers;
import tree.declarations.TDeclarator;
import tree.declarations.TDirectDeclarator;
import tree.declarations.TDirectDeclaratorI;
import tree.declarations.TSpecifierQualifierList;
import tree.other.CommonDeclaration;
import tree.other.CommonSpecifiers;
import tree.other.CommonDeclarator;
import tree.statements.TCompoundStatement;
import tree.symbols.TSIdentifier;

public class TFunctionDefinition extends DefaultTreeNode implements CommonDeclaration {
	
	protected TFunctionDefinition() {
	}

	public TFunctionDefinition(TFunctionDefinition node) {
		super(node);
	}
	
	public TFunctionDefinition(TDeclarationSpecifiers specs, TDeclarator decl, TDeclarationList decls, TCompoundStatement cstat) {
		addChild(specs);
		addChild(decl);
		addChild(decls);
		addChild(cstat);
	}
	
	public TFunctionDefinition(TDeclarationSpecifiers specs, TDeclarator decl, TCompoundStatement cstat) {
		addChild(specs);
		addChild(decl);
		addChild(cstat);
	}
	
	public TCompoundStatement getCompoundStatement() {
		if (getNChildren() == 3)
			return (TCompoundStatement) getChild(2);
		else
			return (TCompoundStatement) getChild(3);
	}
	
	public void setCompoundStatement(TCompoundStatement cstat) {
		if (getNChildren() == 3)
			setChild(2, cstat);
		else
			setChild(3, cstat);
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
		return hasAbstractDeclarator();
	}
	@Override
	public boolean hasDeclarator() {
		return false;
	}
	@Override
	public boolean hasAbstractDeclarator() {
		return getAbstractDeclarator() != null;
	}

	@Override
	public CommonDeclarator getCommonDeclarator() {
		return getAbstractDeclarator();
	}
	@Override
	public TDeclarator getDeclarator() {
		return (TDeclarator) getChild(1);
	}
	public void setDeclarator(TDeclarator decl) {
		setChild(1, decl);
	}
	@Override
	public TAbstractDeclarator getAbstractDeclarator() {
		return ((TDeclarator) getChild(1)).toAbstractDeclarator();
	}

	@Override
	public void setCommonDeclarator(CommonDeclarator node) {
		TDeclarator decl = (TDeclarator) getChild(1);
		if (node == null) {
			setChild(1, new TDeclarator(new TDirectDeclaratorI(decl.getDirectDeclarator().getNameIdentifier())));
		} else {
			if (!(node instanceof TAbstractDeclarator)) {
				throw new CXInternalError("tried to assign wrong type of declarator");
			}
			TAbstractDeclarator adecl = (TAbstractDeclarator) node;
			
			TDirectDeclarator ddecl = adecl.getDirectDeclarator().toDirectDeclarator(decl.getDirectDeclarator().getNameIdentifier());
			if (adecl.hasPointerComprehension())
				setChild(1, new TDeclarator(adecl.getPointerComprehension(), ddecl));
			else
				setChild(1, new TDeclarator(ddecl));
		}
	}

	public TreeNode getParams() {
		return getCommonDeclarator().getParams();
	}

	public String getFuncName() {
		TDeclarator decl = (TDeclarator) getChild(1);
		return decl.getNameIdentifier().value;
	}
	
	public void setFuncName(String value) {
		TDeclarator decl = (TDeclarator) getChild(1);
		decl.setNameIdentifier(new TSIdentifier(value));
	}

}
