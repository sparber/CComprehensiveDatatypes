package tree.declarations;

import tree.DefaultTreeNode;
import tree.expressions.TConstantExpression;
import tree.expressions.TEnumerationConstant;
import tree.symbols.TSAssign;

public class TEnumerator extends DefaultTreeNode {

	public TEnumerator(TEnumerator node) {
		super(node);
	}
	
	public TEnumerator(TEnumerationConstant enumcons, TSAssign assign, TConstantExpression cexpr) {
		addChild(enumcons);
		addChild(assign);
		addChild(cexpr);
	}
	
	public TEnumerator(TEnumerationConstant enumcons) {
		addChild(enumcons);
	}
}
