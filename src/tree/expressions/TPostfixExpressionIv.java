package tree.expressions;

import tree.symbols.TSParLeft;
import tree.symbols.TSParRight;

public class TPostfixExpressionIv extends TPostfixExpression {
	
	public TPostfixExpressionIv(TPostfixExpressionIv node) {
		super(node);
	}
	
	public TPostfixExpression getPExpression() {
		return (TPostfixExpression)getChild(0);
	}
	public TArgumentExpressionList getArguments() {
		if (getNChildren() == 3) {
			return null;
		} else {
			return (TArgumentExpressionList)getChild(2);
		}
	}

	public TPostfixExpressionIv(TPostfixExpression pexpr, TSParLeft par_left, TSParRight par_right) {
		addChild(pexpr);
		addChild(par_left);
		addChild(par_right);
	}

	public TPostfixExpressionIv(TPostfixExpression pexpr, TSParLeft par_left, TArgumentExpressionList aelist, TSParRight par_right) {
		addChild(pexpr);
		addChild(par_left);
		addChild(aelist);
		addChild(par_right);
	}
	
}
