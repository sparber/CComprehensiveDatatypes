package tree.statements;

import tree.declarations.TDeclaration;

public class TBlockItemD extends TBlockItem {

	public TBlockItemD(TBlockItemD node) {
		super(node);
	}
	
	public TBlockItemD(TDeclaration decl) {
		addChild(decl);
	}
	
}
