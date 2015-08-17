package tree.declarations;

import java.util.LinkedList;

import errors.CXSyntaxError;

import tree.other.CommonDirectDeclarator;
import tree.symbols.TSGenerator;
import tree.symbols.TSIdentifier;

public class TDirectAbstractDeclaratorG extends TDirectAbstractDeclarator {

	public boolean hasDirAbsDeclarator() {
		return getChild(0) instanceof TDirectAbstractDeclarator;
	}
	public TDirectAbstractDeclarator getDirAbsDeclarator() {
		if (hasDirAbsDeclarator())
			return (TDirectAbstractDeclarator) getChild(0);
		else
			return null;
	}
	
	public TSGenerator getGenerator() {
		if (hasDirAbsDeclarator())
			return (TSGenerator) getChild(1);
		else
			return (TSGenerator) getChild(0);
	}

	public TDirectAbstractDeclaratorG(TDirectAbstractDeclaratorG node) {
		super(node);
	}
	
	public TDirectAbstractDeclaratorG(TSGenerator gen) {
		addChild(gen);
	}
	
	public TDirectAbstractDeclaratorG(TDirectAbstractDeclarator dirdecl, TSGenerator gen) {
		addChild(dirdecl);
		addChild(gen);
	}
	
	@Override
	public TParameterTypeList getParams() {
		if (hasDirAbsDeclarator())
			return getDirAbsDeclarator().getParams();
		else
			return null;
	}
	
	@Override
	public TDirectAbstractDeclarator getTypeDeclarator() {
		TDirectAbstractDeclarator child = null;
		if (hasDirAbsDeclarator())
			child = getDirAbsDeclarator().getTypeDeclarator();
		if (child == null)
			return new TDirectAbstractDeclaratorG(getGenerator());
		else
			return new TDirectAbstractDeclaratorG(child, getGenerator());
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
		TDirectAbstractDeclarator dirdecl = null;
		if (hasDirAbsDeclarator())
			dirdecl = getDirAbsDeclarator().translateGenerator(pdecl);
		
		if (!(dirdecl instanceof TDirectAbstractDeclaratorF)) {
			throw new CXSyntaxError("illegal generator declarator", this);
		}
		
		TParameterTypeList ptl;
		TDirectAbstractDeclaratorF fdecl = (TDirectAbstractDeclaratorF) dirdecl;
		if (fdecl.hasParams()) {
			ptl = fdecl.getParams();
			LinkedList<TParameterDeclaration> list = ptl.getParameterList().getList();
			list.addFirst(pdecl);
			ptl.setParameterList(TParameterList.from(list));
		} else {
			ptl = new TParameterTypeList(new TParameterList(pdecl));
		}
		fdecl.setParams(ptl);
		
		return fdecl;
	}

	@Override
	public TDirectDeclarator toDirectDeclarator(TSIdentifier name) {
		TDirectDeclarator ddecl = null;
		if (hasDirAbsDeclarator())
			ddecl = getDirAbsDeclarator().toDirectDeclarator(name);
		else
			ddecl = new TDirectDeclaratorI(name);
			
		return new TDirectDeclaratorG(ddecl, getGenerator());
	}
	
}
