package tree.declarations;

import tree.DefaultTreeNode;
import tree.symbols.TSAtomic;
import tree.symbols.TSParLeft;
import tree.symbols.TSParRight;

public class TAtomicTypeSpecifier extends DefaultTreeNode {
	
	public TAtomicTypeSpecifier(TAtomicTypeSpecifier node) {
		super(node);
	}
	
	public TAtomicTypeSpecifier(TSAtomic atomic, TSParLeft par_left, TTypeName type_name, TSParRight par_right) {
		addChild(atomic);
		addChild(par_left);
		addChild(type_name);
		addChild(par_right);
	}

}
