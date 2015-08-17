package tree.declarations;

import tree.other.CommonDeclarator;
import tree.other.CommonDirectDeclarator;
import tree.symbols.TSIdentifier;
import errors.CXInternalError;

public class TDeclarator extends CommonDeclarator {

	public TDeclarator(TDeclarator node) {
		super(node);
	}
	
	@Override
	public boolean hasPointerComprehension() {
		return getNChildren() == 2;
	}
	@Override
	public TPointerComprehension getPointerComprehension() {
		if (hasPointerComprehension()) {
			return (TPointerComprehension) getChild(0);
		} else {
			return null;
		}
	}
	/*@Override
	public void setPointerComprehension(TPointerComprehension pc) {
		if (pc == null) {
			if (hasPointerComprehension())
				removeChild(0);
		} else {
			if (!hasPointerComprehension())
				addChild(0, pc);
			else
				setChild(0, pc);
		}
	}*/

	
	@Override
	public boolean hasDirectDeclarator() {
		return true;
	}
	@Override
	public TDirectDeclarator getDirectDeclarator() {
		if (hasPointerComprehension()) {
			return (TDirectDeclarator) getChild(1);
		} else {
			return (TDirectDeclarator) getChild(0);
		}
	}
	/*@Override
	public void setDirectDeclarator(TCommonDirectDeclarator ddecl) {
		if (hasPointerComprehension()) {
			setChild(1, ddecl);
		} else {
			setChild(0, ddecl);
		}
	}*/

	public TDeclarator(TPointerComprehension pointer, TDirectDeclarator dirdecl) {
		addChild(pointer);
		addChild(dirdecl);
	}
	
	public TDeclarator(TDirectDeclarator dirdecl) {
		addChild(dirdecl);
	}

	public TSIdentifier getNameIdentifier() {
		return getDirectDeclarator().getNameIdentifier();
	}

	@Override
	public CommonDeclarator newInstance(TPointerComprehension pc, CommonDirectDeclarator decl) {
		if (decl == null && pc == null)
			return null;
		else if (decl == null)
			throw new CXInternalError("declarator cannot be null", pc);
		else if (pc == null)
			return new TDeclarator((TDirectDeclarator) decl);
		else
			return new TDeclarator(pc, (TDirectDeclarator) decl);
	}

	public TDeclarator translateGenerator(TParameterDeclaration pdecl) {
		if (hasPointerComprehension())
			return new TDeclarator(getPointerComprehension(), getDirectDeclarator().translateGenerator(pdecl));
		else
			return new TDeclarator(getDirectDeclarator().translateGenerator(pdecl));
	}

	public TAbstractDeclarator toAbstractDeclarator() {
		TDirectAbstractDeclarator adecl = getDirectDeclarator().toAbstractDeclarator();
		if (adecl != null) {
			if (hasPointerComprehension())
				return new TAbstractDeclarator(getPointerComprehension(), adecl);
			else
				return new TAbstractDeclarator(adecl);
		} else {
			if (hasPointerComprehension())
				return new TAbstractDeclarator(getPointerComprehension());
			else
				return null;
		}
	}

	public void setNameIdentifier(TSIdentifier name) {
		getDirectDeclarator().setNameIdentifier(name);
	}

}
