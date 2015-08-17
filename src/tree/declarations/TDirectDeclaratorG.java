package tree.declarations;

import java.util.LinkedList;

import errors.CXSyntaxError;

import tree.TreeNode;
import tree.other.CommonDirectDeclarator;
import tree.symbols.TSGenerator;
import tree.symbols.TSIdentifier;

public class TDirectDeclaratorG extends TDirectDeclarator {

	public TDirectDeclarator getDirDeclarator() {
		return (TDirectDeclarator) getChild(0);
	}
	
	public TSGenerator getGenerator() {
		return (TSGenerator) getChild(1);
	}

	public TDirectDeclaratorG(TDirectDeclaratorG node) {
		super(node);
	}
	
	public TDirectDeclaratorG(TDirectDeclarator dirdecl, TSGenerator gen) {
		addChild(dirdecl);
		addChild(gen);
	}
	
	@Override
	public TreeNode getParams() {
		return getDirDeclarator().getParams();
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
		if (child == null)
			return new TDirectAbstractDeclaratorG(getGenerator());
		else
			return new TDirectAbstractDeclaratorG(child, getGenerator());
	}

	@Override
	public CommonDirectDeclarator getNameDeclarator() {
		return getDirDeclarator().getNameDeclarator();
	}

	@Override
	public TDirectDeclarator translateGenerator(TParameterDeclaration pdecl) {
		TDirectDeclarator dirdecl = getDirDeclarator().translateGenerator(pdecl);
		
		TDirectDeclaratorF fdecl;
		if (!(dirdecl instanceof TDirectDeclaratorF)) {
			throw new CXSyntaxError("illegal generator declarator", this);
		}
			
		fdecl = (TDirectDeclaratorF) dirdecl;
		TParameterTypeList ptl;
		if (fdecl.hasParams()) {
			if (!(fdecl.getParams() instanceof TParameterTypeList)) {
				throw new CXSyntaxError("identifier list as parameters for generators are not supported", this);
			}
			ptl = (TParameterTypeList) fdecl.getParams();
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
	public TDirectAbstractDeclarator toAbstractDeclarator() {
		TDirectAbstractDeclarator adecl = getDirDeclarator().toAbstractDeclarator();
		if (adecl == null)
			return new TDirectAbstractDeclaratorG(getGenerator());
		else
			return new TDirectAbstractDeclaratorG(adecl, getGenerator());
	}

}
