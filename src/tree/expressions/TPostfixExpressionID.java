package tree.expressions;

import tree.symbols.TSDecOp;
import tree.symbols.TSIncOp;

public class TPostfixExpressionID extends TPostfixExpression {
	
	public TPostfixExpressionID(TPostfixExpressionID node) {
		super(node);
	}
	
	public TPostfixExpression getPExpression() {
		return (TPostfixExpression)getChild(0);
	}
	public boolean isIncrementation() {
		return getChild(1) instanceof TSIncOp;
	}
	public boolean isDecrementation() {
		return getChild(1) instanceof TSDecOp;
	}

	public TPostfixExpressionID(TPostfixExpression pexpr, TSIncOp inc_op) {
		addChild(pexpr);
		addChild(inc_op);
	}
	
	public TPostfixExpressionID(TPostfixExpression pexpr, TSDecOp dec_op) {
		addChild(pexpr);
		addChild(dec_op);
	}
}
