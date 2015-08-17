package tree.declarations;

import tree.symbols.TSSemicolon;

public class TDeclarationDS extends TDeclaration {
	
	public TDeclarationDS(TDeclarationDS node) {
		super(node);
	}
	
	public TDeclarationSpecifiers getDeclarationSpecifiers() {
		return (TDeclarationSpecifiers) getChild(0);
	}

	public TDeclarationDS(TDeclarationSpecifiers specs, TSSemicolon semicolon) {
		addChild(specs);
		addChild(semicolon);
	}
	
}
