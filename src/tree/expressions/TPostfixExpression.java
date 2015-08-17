package tree.expressions;

import tree.DefaultTreeNode;
import tree.symbols.TSParLeft;
import tree.symbols.TSParRight;

public abstract class TPostfixExpression extends DefaultTreeNode {

	public TPostfixExpression() {
	}
	
	public TPostfixExpression(TPostfixExpression node) {
		super(node);
	}
	
	public static TPostfixExpression from(String name, TAssignmentExpression[] aexprs) {
		if (aexprs == null || aexprs.length == 0) {
			return new TPostfixExpressionIv(
					new TPostfixExpressionP(TPrimaryExpression.from(name)),
					new TSParLeft(),
					new TSParRight()
				);
		} else {
			return new TPostfixExpressionIv(
					new TPostfixExpressionP(TPrimaryExpression.from(name)),
					new TSParLeft(),
					TArgumentExpressionList.from(aexprs),
					new TSParRight()
				);
		}
	}

	public static TPostfixExpression from(String name) {
		return new TPostfixExpressionP(TPrimaryExpression.from(name));
	}

	public static TPostfixExpression from(int number) {
		return new TPostfixExpressionP(TPrimaryExpression.from(number));
	}

	public static TPostfixExpression from(TExpression expr) {
		return new TPostfixExpressionP(TPrimaryExpression.from(expr));
	}

}
