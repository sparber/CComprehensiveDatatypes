package tree.expressions;

import tree.DefaultTreeNode;
import tree.symbols.TSOrOp;

public class TLogicalOrExpression extends DefaultTreeNode {

	public TLogicalOrExpression(TLogicalOrExpression node) {
		super(node);
	}
	
	public TLogicalOrExpression(TLogicalAndExpression lexpr) {
		addChild(lexpr);
	}
	
	public TLogicalOrExpression(TLogicalOrExpression loexpr, TSOrOp or_op, TLogicalAndExpression laexpr) {
		addChild(loexpr);
		addChild(or_op);
		addChild(laexpr);
	}

	public static TLogicalOrExpression from(TUnaryExpression uexpr) {
		return new TLogicalOrExpression(TLogicalAndExpression.from(uexpr));
	}

	public static TLogicalOrExpression from(TCastExpression cexpr) {
		return new TLogicalOrExpression(TLogicalAndExpression.from(cexpr));
	}
}
