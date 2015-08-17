package tree.declarations;

import tree.symbols.TSSemicolon;

public class TStructDeclarationSQL extends TStructDeclaration {
	
	public TStructDeclarationSQL(TStructDeclarationSQL node) {
		super(node);
	}
	
	public TStructDeclarationSQL(TSpecifierQualifierList specqualis, TSSemicolon semicolon) {
		addChild(specqualis);
		addChild(semicolon);
	}

}
