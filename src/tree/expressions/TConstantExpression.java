package tree.expressions;

import tree.DefaultTreeNode;

public class TConstantExpression extends DefaultTreeNode {

	public TConstantExpression(TConstantExpression node) {
		super(node);
	}
	
	public TConstantExpression(TConditionalExpression cexpr) {
		addChild(cexpr);
	}
	
}
