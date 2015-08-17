package tree.expressions;

import tree.DefaultTreeNode;

public abstract class TSetExpression extends DefaultTreeNode {

	public TSetExpression() {
	}
	
	public TSetExpression(TSetExpression node) {
		super(node);
	}
	
	public static TSetExpression from(TUnaryExpression uexpr) {
		return new TSetExpressionU(uexpr);
	}

}
