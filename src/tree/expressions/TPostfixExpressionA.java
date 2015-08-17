package tree.expressions;

import tree.symbols.TSBracketLeft;
import tree.symbols.TSBracketRight;

public class TPostfixExpressionA extends TPostfixExpression {

	public TPostfixExpressionA(TPostfixExpressionA node) {
		super(node);
	}
	
	public TPostfixExpression getPExpression() {
		return (TPostfixExpression)getChild(0);
	}
	public TExpression getIndex() {
		return (TExpression)getChild(1);
	}

	public TPostfixExpressionA(TPostfixExpression pexpr, TSBracketLeft bra_left, TExpression expr, TSBracketRight bra_right) {
		addChild(pexpr);
		addChild(bra_left);
		addChild(expr);
		addChild(bra_right);
	}
	
}
