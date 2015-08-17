package tree.expressions;

import tree.DefaultTreeNode;
import tree.symbols.TSAddOp;
import tree.symbols.TSSubOp;

public class TAdditiveExpression extends DefaultTreeNode {

	public TAdditiveExpression(TAdditiveExpression node) {
		super(node);
	}
	
	public TAdditiveExpression(TMultiplicativeExpression mexpr) {
		addChild(mexpr);
	}
	
	public TAdditiveExpression(TAdditiveExpression aexpr, TSAddOp add_op, TMultiplicativeExpression mexpr) {
		addChild(aexpr);
		addChild(add_op);
		addChild(mexpr);
	}
	
	public TAdditiveExpression(TAdditiveExpression aexpr, TSSubOp sub_op, TMultiplicativeExpression mexpr) {
		addChild(aexpr);
		addChild(sub_op);
		addChild(mexpr);
	}

	public static TAdditiveExpression from(TUnaryExpression uexpr) {
		return new TAdditiveExpression(TMultiplicativeExpression.from(uexpr));
	}

	public static TAdditiveExpression from(TCastExpression cexpr) {
		return new TAdditiveExpression(TMultiplicativeExpression.from(cexpr));
	}
	
}
