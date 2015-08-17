package tree.declarations;

import tree.DefaultTreeNode;
import tree.other.CommonDirectDeclarator;
import tree.symbols.TSIdentifier;

public abstract class TDirectDeclarator extends DefaultTreeNode implements CommonDirectDeclarator {
	
	public TDirectDeclarator() {
	}
	
	public TDirectDeclarator(TDirectDeclarator node) {
		super(node);
	}

	public abstract TSIdentifier getNameIdentifier();
	public abstract void setNameIdentifier(TSIdentifier name);

	@Override
	public abstract TDirectDeclarator translateGenerator(TParameterDeclaration param);

	public abstract TDirectAbstractDeclarator toAbstractDeclarator();


}
