package tree.declarations;

import tree.other.CommonDeclarator;
import tree.other.CommonDirectDeclarator;
import tree.symbols.TSIdentifier;


public class TAbstractDeclarator extends CommonDeclarator {
	
	public TAbstractDeclarator(TAbstractDeclarator node) {
		super(node);
	}

	public TAbstractDeclarator(TPointerComprehension pointer, TDirectAbstractDeclarator dirdecl) {
		addChild(pointer);
		addChild(dirdecl);
	}
	
	public TAbstractDeclarator(TPointerComprehension pointer) {
		addChild(pointer);
	}

	public TAbstractDeclarator(TDirectAbstractDeclarator dirdecl) {
		addChild(dirdecl);
	}

	@Override
	public boolean hasDirectDeclarator() {
		if (getNChildren() == 1)
			return getChild(0) instanceof TDirectAbstractDeclarator;
		else
			return true;
	}
	@Override
	public TDirectAbstractDeclarator getDirectDeclarator() {
		if (getNChildren() == 2)
			return (TDirectAbstractDeclarator) getChild(1);
		else if (hasDirectDeclarator())
			return (TDirectAbstractDeclarator) getChild(0);
		else
			return null;
	}
	/*@Override
	public void setDirectDeclarator(TCommonDirectDeclarator ddecl) {
		if (ddecl == null) {
			if (hasDirectDeclarator()) {
				if (hasPointerComprehension())
					removeChild(1);
				else
					throw new RuntimeException("tried to create empty TAbstractDeclarator");
			}
		} else {
			if (!(ddecl instanceof TDirectAbstractDeclarator))
				throw new RuntimeException();
			if (hasPointerComprehension()) {
				if (hasDirectDeclarator())
					setChild(1, ddecl);
				else
					addChild(ddecl);
			} else {
				setChild(0, ddecl);
			}
		}
	}*/

	@Override
	public boolean hasPointerComprehension() {
		return getChild(0) instanceof TPointerComprehension;
	}
	@Override
	public TPointerComprehension getPointerComprehension() {
		if (hasPointerComprehension())
			return (TPointerComprehension) getChild(0);
		else
			return null;
	}
	/*@Override
	public void setPointerComprehension(TPointerComprehension pc) {
		if (pc == null) {
			if (hasPointerComprehension()) {
				if (!hasDirectDeclarator())
					throw new RuntimeException("tried to create empty TAbstractDeclarator");
				removeChild(0);
			}
		} else {
			if (!hasPointerComprehension())
				addChild(0, pc);
			else
				setChild(0, pc);
		}
	}*/

	@Override
	public TAbstractDeclarator newInstance(TPointerComprehension pc, CommonDirectDeclarator decl) {
		if (pc == null && decl == null)
			return null;
		else if (decl == null)
			return new TAbstractDeclarator(pc);
		else if (pc == null)
			return new TAbstractDeclarator((TDirectAbstractDeclarator) decl);
		else
			return new TAbstractDeclarator(pc, (TDirectAbstractDeclarator) decl);
	}
	
	public static TAbstractDeclarator newAbstractInstance(TPointerComprehension pc, CommonDirectDeclarator decl) {
		if (pc == null && decl == null)
			return null;
		else if (decl == null)
			return new TAbstractDeclarator(pc);
		else if (pc == null)
			return new TAbstractDeclarator((TDirectAbstractDeclarator) decl);
		else
			return new TAbstractDeclarator(pc, (TDirectAbstractDeclarator) decl);
	}

	public TAbstractDeclarator translateGenerator(TParameterDeclaration pdecl) {
		if (hasPointerComprehension())
			return new TAbstractDeclarator(getPointerComprehension(), getDirectDeclarator().translateGenerator(pdecl));
		else
			return new TAbstractDeclarator(getDirectDeclarator().translateGenerator(pdecl));
	}

	public TDeclarator toDeclarator(TSIdentifier name) {
		TDirectDeclarator ddecl = null;
		if (hasDirectDeclarator())
			ddecl = getDirectDeclarator().toDirectDeclarator(name);
		else
			ddecl = new TDirectDeclaratorI(name);
		
		if (hasPointerComprehension())
			return new TDeclarator(getPointerComprehension(), ddecl);
		else
			return new TDeclarator(ddecl);
	}

}
