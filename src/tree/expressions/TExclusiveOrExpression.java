package tree.expressions;

import tree.DefaultTreeNode;
import tree.symbols.TSXorOp;

public class TExclusiveOrExpression extends DefaultTreeNode {

	public TExclusiveOrExpression(TExclusiveOrExpression node) {
		super(node);
	}
	
	public TExclusiveOrExpression(TAndExpression aexpr) {
		addChild(aexpr);
	}
	
	public TExclusiveOrExpression(TExclusiveOrExpression eexpr, TSXorOp xor_op, TAndExpression aexpr) {
		addChild(eexpr);
		addChild(xor_op);
		addChild(aexpr);
	}

	public static TExclusiveOrExpression from(TUnaryExpression uexpr) {
		return new TExclusiveOrExpression(TAndExpression.from(uexpr));
	}

	public static TExclusiveOrExpression from(TCastExpression cexpr) {
		return new TExclusiveOrExpression(TAndExpression.from(cexpr));
	}
}
