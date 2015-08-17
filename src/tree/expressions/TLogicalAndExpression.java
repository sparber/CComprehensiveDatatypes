package tree.expressions;

import tree.DefaultTreeNode;
import tree.symbols.TSAndOp;

public class TLogicalAndExpression extends DefaultTreeNode {

	public TLogicalAndExpression(TLogicalAndExpression node) {
		super(node);
	}
	
	public TLogicalAndExpression(TInclusiveOrExpression iexpr) {
		addChild(iexpr);
	}
	
	public TLogicalAndExpression(TLogicalAndExpression lexpr, TSAndOp and_op, TInclusiveOrExpression iexpr) {
		addChild(lexpr);
		addChild(and_op);
		addChild(iexpr);
	}

	public static TLogicalAndExpression from(TUnaryExpression uexpr) {
		return new TLogicalAndExpression(TInclusiveOrExpression.from(uexpr));
	}

	public static TLogicalAndExpression from(TCastExpression cexpr) {
		return new TLogicalAndExpression(TInclusiveOrExpression.from(cexpr));
	}
}
