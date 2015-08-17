package tree.expressions;

import tree.DefaultTreeNode;
import tree.symbols.TSBandOp;

public class TAndExpression extends DefaultTreeNode {

	public TAndExpression(TAndExpression node) {
		super(node);
	}
	
	public TAndExpression(TEqualityExpression eexpr) {
		addChild(eexpr);
	}
	
	public TAndExpression(TAndExpression aexpr, TSBandOp band_op, TEqualityExpression eexpr) {
		addChild(aexpr);
		addChild(band_op);
		addChild(eexpr);
	}

	public static TAndExpression from(TUnaryExpression uexpr) {
		return new TAndExpression(TEqualityExpression.from(uexpr));
	}

	public static TAndExpression from(TCastExpression cexpr) {
		return new TAndExpression(TEqualityExpression.from(cexpr));
	}
}
