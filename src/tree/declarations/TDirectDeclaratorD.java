package tree.declarations;

import tree.DefaultTreeNode;
import tree.other.CommonDirectDeclarator;
import tree.symbols.TSIdentifier;
import tree.symbols.TSParLeft;
import tree.symbols.TSParRight;

public class TDirectDeclaratorD extends TDirectDeclarator {

	public TDirectDeclaratorD(TDirectDeclaratorD node) {
		super(node);
	}
	
	public TDeclarator getDeclarator() {
		return (TDeclarator) getChild(1);
	}

	public TDirectDeclaratorD(TSParLeft par_left, TDeclarator declarator, TSParRight par_right) {
		addChild(par_left);
		addChild(declarator);
		addChild(par_right);
	}

	@Override
	public TSIdentifier getNameIdentifier() {
		return getDeclarator().getNameIdentifier();
	}
	@Override
	public void setNameIdentifier(TSIdentifier name) {
		getDeclarator().setNameIdentifier(name);
	}

	@Override
	public TDirectAbstractDeclarator getTypeDeclarator() {
		TAbstractDeclarator child = getDeclarator().getTypeDeclarator();
		if (child == null)
			return null;
		else
			return new TDirectAbstractDeclaratorAD((TSParLeft) getChild(0), child, (TSParRight) getChild(2));
	}

	@Override
	public CommonDirectDeclarator getNameDeclarator() {
		return new TDirectDeclaratorD((TSParLeft) getChild(0), (TDeclarator) getDeclarator().getNameDeclarator(), (TSParRight) getChild(2));
	}

	@Override
	public TDirectDeclarator translateGenerator(TParameterDeclaration pdecl) {
		return new TDirectDeclaratorD((TSParLeft) getChild(0), getDeclarator().translateGenerator(pdecl), (TSParRight) getChild(2));
	}

	@Override
	public TDirectAbstractDeclarator toAbstractDeclarator() {
		TAbstractDeclarator adecl = getDeclarator().toAbstractDeclarator();
		if (adecl == null)
			return null;
		return new TDirectAbstractDeclaratorAD((TSParLeft) getChild(0), adecl, (TSParRight) getChild(2));
	}

	@Override
	public DefaultTreeNode getParams() {
		return null;
	}

}
