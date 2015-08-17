package tree.declarations;

import tree.DefaultTreeNode;
import tree.other.CommonDirectDeclarator;
import tree.symbols.TSIdentifier;


public abstract class TDirectAbstractDeclarator extends DefaultTreeNode implements CommonDirectDeclarator {

	public TDirectAbstractDeclarator() {
	}
	
	public TDirectAbstractDeclarator(TDirectAbstractDeclarator node) {
		super(node);
	}
	
	@Override
	public abstract TDirectAbstractDeclarator translateGenerator(TParameterDeclaration pdecl);

	public abstract TDirectDeclarator toDirectDeclarator(TSIdentifier name);
	
	@Override
	public abstract TParameterTypeList getParams();
	
}
