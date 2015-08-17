package tree.declarations;

import tree.DefaultTreeNode;
import tree.expressions.TConstantExpression;
import tree.symbols.TSAlignas;
import tree.symbols.TSParLeft;
import tree.symbols.TSParRight;

public class TAlignmentSpecifier extends DefaultTreeNode {
	
	public TAlignmentSpecifier(TAlignmentSpecifier node) {
		super(node);
	}
	
	public TAlignmentSpecifier(TSAlignas alignas, TSParLeft par_left, TTypeName type_name, TSParRight par_right) {
		addChild(alignas);
		addChild(par_left);
		addChild(type_name);
		addChild(par_right);
	}

	public TAlignmentSpecifier(TSAlignas alignas, TSParLeft par_left, TConstantExpression cexpr, TSParRight par_right) {
		addChild(alignas);
		addChild(par_left);
		addChild(cexpr);
		addChild(par_right);
	}
}
