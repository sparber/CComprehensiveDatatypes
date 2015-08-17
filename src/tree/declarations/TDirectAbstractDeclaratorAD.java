package tree.declarations;

import tree.other.CommonDirectDeclarator;
import tree.symbols.TSIdentifier;
import tree.symbols.TSParLeft;
import tree.symbols.TSParRight;

public class TDirectAbstractDeclaratorAD extends TDirectAbstractDeclarator {

	public TDirectAbstractDeclaratorAD(TDirectAbstractDeclaratorAD node) {
		super(node);
	}
	
	public TAbstractDeclarator getAbsDeclarator() {
		return (TAbstractDeclarator) getChild(1);
	}

	public TDirectAbstractDeclaratorAD(TSParLeft par_left, TAbstractDeclarator decl, TSParRight par_right) {
		addChild(par_left);
		addChild(decl);
		addChild(par_right);
	}

	@Override
	public TDirectAbstractDeclarator getTypeDeclarator() {
		TAbstractDeclarator child = getAbsDeclarator().getTypeDeclarator();
		if (child == null)
			return null;
		else
			return new TDirectAbstractDeclaratorAD((TSParLeft) getChild(0), child, (TSParRight) getChild(2));
	}

	@Override
	public CommonDirectDeclarator getNameDeclarator() {
		TAbstractDeclarator decl = (TAbstractDeclarator) getAbsDeclarator().getNameDeclarator();
		if (decl == null)
			return null;
		return new TDirectAbstractDeclaratorAD((TSParLeft) getChild(0), (TAbstractDeclarator) getAbsDeclarator().getNameDeclarator(), (TSParRight) getChild(2));
	}

	@Override
	public TDirectAbstractDeclarator translateGenerator(TParameterDeclaration pdecl) {
		return new TDirectAbstractDeclaratorAD((TSParLeft) getChild(0), getAbsDeclarator().translateGenerator(pdecl), (TSParRight) getChild(2));
	}

	@Override
	public TDirectDeclarator toDirectDeclarator(TSIdentifier name) {
		return new TDirectDeclaratorD((TSParLeft) getChild(0), getAbsDeclarator().toDeclarator(name), (TSParRight) getChild(2));
	}

	@Override
	public TParameterTypeList getParams() {
		return null;
	}
		
}
