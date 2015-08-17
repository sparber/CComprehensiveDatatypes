package tree.expressions;

public class TPostfixExpressionP extends TPostfixExpression {

	public TPostfixExpressionP(TPostfixExpressionP node) {
		super(node);
	}
	
	public TPrimaryExpression getPExpression() {
		return (TPrimaryExpression)getChild(0);
	}
	
	public TPostfixExpressionP(TPrimaryExpression pexpr) {
		addChild(pexpr);
	}

}
