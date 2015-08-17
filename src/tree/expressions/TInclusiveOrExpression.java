package tree.expressions;

import tree.DefaultTreeNode;
import tree.symbols.TSBorOp;

public class TInclusiveOrExpression extends DefaultTreeNode {

	public TInclusiveOrExpression(TInclusiveOrExpression node) {
		super(node);
	}
	
	public TInclusiveOrExpression(TExclusiveOrExpression eexpr) {
		addChild(eexpr);
	}
	
	public TInclusiveOrExpression(TInclusiveOrExpression iexpr, TSBorOp bor_op, TExclusiveOrExpression eexpr) {
		addChild(iexpr);
		addChild(bor_op);
		addChild(eexpr);
	}

	public static TInclusiveOrExpression from(TUnaryExpression uexpr) {
		return new TInclusiveOrExpression(TExclusiveOrExpression.from(uexpr));
	}

	public static TInclusiveOrExpression from(TCastExpression cexpr) {
		return new TInclusiveOrExpression(TExclusiveOrExpression.from(cexpr));
	}
}
