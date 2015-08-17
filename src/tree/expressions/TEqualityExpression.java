package tree.expressions;

import tree.DefaultTreeNode;
import tree.symbols.TSEqOp;
import tree.symbols.TSNeOp;

public class TEqualityExpression extends DefaultTreeNode {

	public TEqualityExpression(TEqualityExpression node) {
		super(node);
	}
	
	public TEqualityExpression(TRelationalExpression rexpr) {
		addChild(rexpr);
	}
	
	public TEqualityExpression(TEqualityExpression eexpr, TSEqOp eq_op, TRelationalExpression rexpr) {
		addChild(eexpr);
		addChild(eq_op);
		addChild(rexpr);
	}

	public TEqualityExpression(TEqualityExpression eexpr, TSNeOp ne_op, TRelationalExpression rexpr) {
		addChild(eexpr);
		addChild(ne_op);
		addChild(rexpr);
	}

	public static TEqualityExpression from(TUnaryExpression uexpr) {
		return new TEqualityExpression(TRelationalExpression.from(uexpr));
	}

	public static TEqualityExpression from(TCastExpression cexpr) {
		return new TEqualityExpression(TRelationalExpression.from(cexpr));
	}
}
