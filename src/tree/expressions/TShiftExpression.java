package tree.expressions;

import tree.DefaultTreeNode;
import tree.symbols.TSLeftOp;
import tree.symbols.TSRightOp;

public class TShiftExpression extends DefaultTreeNode {

	public TShiftExpression(TShiftExpression node) {
		super(node);
	}
	
	public TShiftExpression(TAdditiveExpression aexpr) {
		addChild(aexpr);
	}
	
	public TShiftExpression(TShiftExpression sexpr, TSLeftOp left_op, TAdditiveExpression aexpr) {
		addChild(sexpr);
		addChild(left_op);
		addChild(aexpr);
	}
	
	public TShiftExpression(TShiftExpression sexpr, TSRightOp right_op, TAdditiveExpression aexpr) {
		addChild(sexpr);
		addChild(right_op);
		addChild(aexpr);
	}

	public static TShiftExpression from(TUnaryExpression uexpr) {
		return new TShiftExpression(TAdditiveExpression.from(uexpr));
	}

	public static TShiftExpression from(TCastExpression cexpr) {
		return new TShiftExpression(TAdditiveExpression.from(cexpr));
	}
	
}
