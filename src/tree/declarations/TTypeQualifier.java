package tree.declarations;

import tree.DefaultTreeNode;
import tree.symbols.TSAtomic;
import tree.symbols.TSConst;
import tree.symbols.TSRestrict;
import tree.symbols.TSVolatile;

public class TTypeQualifier extends DefaultTreeNode {

	public TTypeQualifier(TTypeQualifier node) {
		super(node);
	}
	
	public TTypeQualifier(TSConst qualifier) {
		addChild(qualifier);
	}
	
	public TTypeQualifier(TSRestrict qualifier) {
		addChild(qualifier);
	}

	public TTypeQualifier(TSVolatile qualifier) {
		addChild(qualifier);
	}

	public TTypeQualifier(TSAtomic qualifier) {
		addChild(qualifier);
	}
}
