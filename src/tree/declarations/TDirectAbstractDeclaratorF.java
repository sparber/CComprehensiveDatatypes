package tree.declarations;

import tree.other.CommonDirectDeclarator;
import tree.symbols.TSIdentifier;
import tree.symbols.TSParLeft;
import tree.symbols.TSParRight;

public class TDirectAbstractDeclaratorF extends TDirectAbstractDeclarator {
	
	public TDirectAbstractDeclaratorF(TDirectAbstractDeclaratorF node) {
		super(node);
	}
	
	public boolean hasDirAbsDeclarator() {
		return getChild(0) instanceof TDirectAbstractDeclarator;
	}
	
	public TDirectAbstractDeclarator getDirAbsDeclarator() {
		if (hasDirAbsDeclarator()) {
			return (TDirectAbstractDeclarator) getChild(0);
		} else {
			return null;
		}
	}
	public boolean hasParams() {
		return getParams() != null;
	}
	@Override
	public TParameterTypeList getParams() {
		if (hasDirAbsDeclarator()) {
			if (getChild(2) instanceof TParameterTypeList)
				return (TParameterTypeList) getChild(2);
			else
				return null;
		} else {
			if (getChild(1) instanceof TParameterTypeList)
				return (TParameterTypeList) getChild(1);
			else
				return null;
		}
	}
	public void setParams(TParameterTypeList ptl) {
		if (hasDirAbsDeclarator()) {
			if (hasParams())
				setChild(2, ptl);
			else
				addChild(2, ptl);
		} else {
			if (hasParams())
				setChild(1, ptl);
			else
				addChild(1, ptl);
		}
	}

	public TSParLeft getParLeft() {
		if (hasDirAbsDeclarator()) {
			return (TSParLeft) getChild(1);
		} else {
			return (TSParLeft) getChild(0);
		}
	}
	public TSParRight getParRight() {
		if (hasDirAbsDeclarator()) {
			if (hasParams())
				return (TSParRight) getChild(3);
			else
				return (TSParRight) getChild(2);
		} else {
			if (hasParams())
				return (TSParRight) getChild(2);
			else
				return (TSParRight) getChild(1);
		}
	}

	public TDirectAbstractDeclaratorF(TSParLeft par_left, TSParRight par_right) {
		addChild(par_left);
		addChild(par_right);
	}
	
	public TDirectAbstractDeclaratorF(TSParLeft par_left, TParameterTypeList params, TSParRight par_right) {
		addChild(par_left);
		addChild(params);
		addChild(par_right);
	}

	public TDirectAbstractDeclaratorF(TDirectAbstractDeclarator dirdecl, TSParLeft par_left, TSParRight par_right) {
		addChild(dirdecl);
		addChild(par_left);
		addChild(par_right);
	}
	
	public TDirectAbstractDeclaratorF(TDirectAbstractDeclarator dirdecl, TSParLeft par_left, TParameterTypeList params, TSParRight par_right) {
		addChild(dirdecl);
		addChild(par_left);
		addChild(params);
		addChild(par_right);
	}

	@Override
	public TDirectAbstractDeclarator getTypeDeclarator() {
		TDirectAbstractDeclarator child = null;
		if (hasDirAbsDeclarator())
			child = getDirAbsDeclarator().getTypeDeclarator();
		if (hasParams()) {
			if (child == null)
				return new TDirectAbstractDeclaratorF((TSParLeft) getChild(1), getParams(), (TSParRight) getChild(3));
			else
				return new TDirectAbstractDeclaratorF(child, (TSParLeft) getChild(1), getParams(), (TSParRight) getChild(3));
		} else {
			if (child == null)
				return new TDirectAbstractDeclaratorF((TSParLeft) getChild(1), (TSParRight) getChild(2));
			else
				return new TDirectAbstractDeclaratorF(child, (TSParLeft) getChild(1), (TSParRight) getChild(2));
		}
	}

	@Override
	public CommonDirectDeclarator getNameDeclarator() {
		if (hasDirAbsDeclarator())
			return getDirAbsDeclarator().getNameDeclarator();
		else
			return null;
	}

	@Override
	public TDirectAbstractDeclarator translateGenerator(TParameterDeclaration pdecl) {
		if (hasDirAbsDeclarator()) {
			TDirectAbstractDeclarator dirdecl = getDirAbsDeclarator().translateGenerator(pdecl);
			if (hasParams()) {
				return new TDirectAbstractDeclaratorF(dirdecl, getParLeft(), getParams(), getParRight());
			} else {
				return new TDirectAbstractDeclaratorF(dirdecl, getParLeft(), getParRight());
			}
		} else {
			if (hasParams()) {
				return new TDirectAbstractDeclaratorF(getParLeft(), getParams(), getParRight());
			} else {
				return new TDirectAbstractDeclaratorF(getParLeft(), getParRight());
			}
		}
	}

	@Override
	public TDirectDeclarator toDirectDeclarator(TSIdentifier name) {
		TDirectDeclarator ddecl = null;
		if (hasDirAbsDeclarator())
			ddecl = getDirAbsDeclarator().toDirectDeclarator(name);
		else
			ddecl = new TDirectDeclaratorI(name);
			
		if (hasParams())
			return new TDirectDeclaratorF(ddecl, getParLeft(), getParams(), getParRight());
		else
			return new TDirectDeclaratorF(ddecl, getParLeft(), getParRight());
	}

}
