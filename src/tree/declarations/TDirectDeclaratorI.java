package tree.declarations;

import errors.CXInternalError;
import tree.DefaultTreeNode;
import tree.other.CommonDirectDeclarator;
import tree.symbols.TSIdentifier;

public class TDirectDeclaratorI extends TDirectDeclarator {

	public TDirectDeclaratorI(TDirectDeclaratorI node) {
		super(node);
	}
	
	public TDirectDeclaratorI(TSIdentifier id) {
		addChild(id);
	}

	@Override
	public TSIdentifier getNameIdentifier() {
		return (TSIdentifier) getChild(0);
	}
	@Override
	public void setNameIdentifier(TSIdentifier name) {
		setChild(0, name);
	}
	
	@Override
	public TDirectAbstractDeclarator getTypeDeclarator() {
		throw new CXInternalError("requested type declarator from identifier direct declarator in comp op translation", this);
	}

	@Override
	public CommonDirectDeclarator getNameDeclarator() {
		return this;
	}

	@Override
	public TDirectDeclarator translateGenerator(TParameterDeclaration pdecl) {
		return this;
	}

	@Override
	public TDirectAbstractDeclarator toAbstractDeclarator() {
		return null;
	}

	@Override
	public DefaultTreeNode getParams() {
		return null;
	}

}
