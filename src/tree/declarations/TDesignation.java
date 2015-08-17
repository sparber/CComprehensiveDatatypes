package tree.declarations;

import tree.DefaultTreeNode;
import tree.symbols.TSAssign;

public class TDesignation extends DefaultTreeNode {
	
	public TDesignation(TDesignation node) {
		super(node);
	}

	public TDesignation(TDesignatorList list, TSAssign assign) {
		addChild(list);
		addChild(assign);
	}
	
}
