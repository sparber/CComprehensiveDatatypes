package tree.declarations;

import errors.CXInternalError;
import tree.DefaultTreeNode;
import tree.symbols.TSAssign;

public class TInitDeclarator extends DefaultTreeNode {

	public TInitDeclarator(TInitDeclarator node) {
		super(node);
	}
	
	public TDeclarator getDeclarator() {
		return (TDeclarator) getChild(0);
	}
	
	public void setDeclarator(TDeclarator node) {
		if (node == null)
			throw new CXInternalError("tried to remove declarator");
		setChild(0, node);
	}

	public TInitDeclarator(TDeclarator decl, TSAssign assign, TInitializer init) {
		addChild(decl);
		addChild(assign);
		addChild(init);
	}
	
	public TInitDeclarator(TDeclarator decl) {
		addChild(decl);
	}
	
}
