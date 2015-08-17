package tree.expressions;

import tree.DefaultTreeNode;
import tree.symbols.TSColon;
import tree.symbols.TSQues;

public class TConditionalExpression extends DefaultTreeNode {

	public TConditionalExpression(TConditionalExpression node) {
		super(node);
	}
	
	public TConditionalExpression(TLogicalOrExpression lexpr) {
		addChild(lexpr);
	}
	
	public TConditionalExpression(TLogicalOrExpression lexpr, TSQues ques, TExpression expr, TSColon colon, TConditionalExpression cexpr) {
		addChild(lexpr);
		addChild(ques);
		addChild(expr);
		addChild(colon);
		addChild(cexpr);
	}

	public static TConditionalExpression from(TUnaryExpression uexpr) {
		return new TConditionalExpression(TLogicalOrExpression.from(uexpr));
	}

	public static TConditionalExpression from(TCastExpression cexpr) {
		return new TConditionalExpression(TLogicalOrExpression.from(cexpr));
	}

	
}
