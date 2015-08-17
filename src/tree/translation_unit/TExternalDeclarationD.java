package tree.translation_unit;

import tree.declarations.TDeclaration;

public class TExternalDeclarationD extends TExternalDeclaration {
	
	public TExternalDeclarationD(TExternalDeclarationD node) {
		super(node);
	}
	
	public TExternalDeclarationD(TDeclaration decl) {
		addChild(decl);
	}

	public TDeclaration getDeclaration() {
		return (TDeclaration) getChild(0);
	}

}
