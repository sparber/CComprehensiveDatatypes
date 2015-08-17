package tree.declarations;

import errors.CXSyntaxError;
import tree.TreeNode;
import tree.other.CommonDirectDeclarator;
import tree.symbols.TSIdentifier;
import tree.symbols.TSParLeft;
import tree.symbols.TSParRight;

public class TDirectDeclaratorF extends TDirectDeclarator {
	
	public TDirectDeclaratorF(TDirectDeclaratorF node) {
		super(node);
	}
	
	public TDirectDeclarator getDirDeclarator() {
		return (TDirectDeclarator) getChild(0);
	}
	
	public boolean hasParams() {
		return getNChildren() == 4;
	}
	@Override
	public TreeNode getParams() {
		if (!hasParams())
			return null;
		else
			return getChild(2);
	}
	public void setParams(TParameterTypeList ptl) {
		if (hasParams())
			setChild(2, ptl);
		else
			addChild(2, ptl);
	}
	
	public TSParLeft getParLeft() {
		return (TSParLeft) getChild(1);
	}

	public TSParRight getParRight() {
		if (hasParams())
			return (TSParRight) getChild(3);
		else
			return (TSParRight) getChild(2);
	}

	public TDirectDeclaratorF(TDirectDeclarator dirdecl, TSParLeft par_left, TParameterTypeList params, TSParRight par_right) {
		addChild(dirdecl);
		addChild(par_left);
		addChild(params);
		addChild(par_right);
	}
	
	public TDirectDeclaratorF(TDirectDeclarator dirdecl, TSParLeft par_left, TSParRight par_right) {
		addChild(dirdecl);
		addChild(par_left);
		addChild(par_right);
	}
	
	public TDirectDeclaratorF(TDirectDeclarator dirdecl, TSParLeft par_left, TIdentifierList ids, TSParRight par_right) {
		addChild(dirdecl);
		addChild(par_left);
		addChild(ids);
		addChild(par_right);
	}

	@Override
	public TSIdentifier getNameIdentifier() {
		return getDirDeclarator().getNameIdentifier();
	}
	@Override
	public void setNameIdentifier(TSIdentifier name) {
		getDirDeclarator().setNameIdentifier(name);
	}

	@Override
	public TDirectAbstractDeclarator getTypeDeclarator() {
		TDirectAbstractDeclarator child = getDirDeclarator().getTypeDeclarator();
		if (hasParams()) {
			if (getChild(2) instanceof TParameterTypeList) {
				if (child == null)
					return new TDirectAbstractDeclaratorF((TSParLeft) getChild(1), (TParameterTypeList) getChild(2), (TSParRight) getChild(3));
				else
					return new TDirectAbstractDeclaratorF(child, (TSParLeft) getChild(1), (TParameterTypeList) getChild(2), (TSParRight) getChild(3));
			} else {
				throw new CXSyntaxError("identifier list as parameters is not allowed here", this);
			}
		} else {
			if (child == null)
				return new TDirectAbstractDeclaratorF((TSParLeft) getChild(1), (TSParRight) getChild(2));
			else
				return new TDirectAbstractDeclaratorF(child, (TSParLeft) getChild(1), (TSParRight) getChild(2));
		}
	}

	@Override
	public CommonDirectDeclarator getNameDeclarator() {
		return getDirDeclarator().getNameDeclarator();
	}

	@Override
	public TDirectDeclarator translateGenerator(TParameterDeclaration pdecl) {
		TDirectDeclarator dirdecl = getDirDeclarator().translateGenerator(pdecl);
		if (hasParams()) {
			if (getChild(2) instanceof TParameterTypeList)
				return new TDirectDeclaratorF(dirdecl, getParLeft(), (TParameterTypeList) getChild(2), getParRight());
			else
				return new TDirectDeclaratorF(dirdecl, getParLeft(), (TIdentifierList) getChild(2), getParRight());
		} else {
			return new TDirectDeclaratorF(dirdecl, getParLeft(), getParRight());
		}
	}

	@Override
	public TDirectAbstractDeclarator toAbstractDeclarator() {
		if (hasParams() && (getParams() instanceof TIdentifierList))
			throw new CXSyntaxError("identifier list is not allowed here", this);
		
		TDirectAbstractDeclarator adecl = getDirDeclarator().toAbstractDeclarator();
		if (adecl != null) {
			if (hasParams())
				return new TDirectAbstractDeclaratorF(adecl, getParLeft(), (TParameterTypeList) getParams(), getParRight());
			else
				return new TDirectAbstractDeclaratorF(adecl, getParLeft(), getParRight());
		} else {
			if (hasParams())
				return new TDirectAbstractDeclaratorF(getParLeft(), (TParameterTypeList) getParams(), getParRight());
			else
				return new TDirectAbstractDeclaratorF(getParLeft(), getParRight());
		}
	}

}
