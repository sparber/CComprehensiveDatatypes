package tree.expressions;

import tree.symbols.TSSetOp;

public class TSetExpressionSO extends TSetExpression {

	public TSetExpressionSO(TSetExpressionSO node) {
		super(node);
	}
	
	public TUnaryExpression getUExpression1() {
		return (TUnaryExpression) getChild(0);
	}
	public boolean hasUExpression2() {
		return getNChildren() == 3;
	}
	public TUnaryExpression getUExpression2() {
		if (hasUExpression2()) {
			return (TUnaryExpression) getChild(2);
		} else {
			return null;
		}
	}
	
	public TSetExpressionSO(TUnaryExpression uexpr1, TSSetOp set_op, TUnaryExpression uexpr2) {
		addChild(uexpr1);
		addChild(set_op);
		addChild(uexpr2);
	}

	public TSetExpressionSO(TUnaryExpression uexpr, TSSetOp set_op) {
		addChild(uexpr);
		addChild(set_op);
	}

}
