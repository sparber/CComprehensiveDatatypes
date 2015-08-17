package tree.expressions;

import tree.DefaultTreeNode;

public class TAssignmentExpression extends DefaultTreeNode {

	public TAssignmentExpression(TAssignmentExpression node) {
		super(node);
	}
	
	public TAssignmentExpression(TConditionalExpression cexpr) {
		addChild(cexpr);
	}
	
	public TAssignmentExpression(TUnaryExpression uexpr, TAssignmentOperator ass_op, TAssignmentExpression aexpr) {
		addChild(uexpr);
		addChild(ass_op);
		addChild(aexpr);
	}

	public static TAssignmentExpression from(TUnaryExpression uexpr) {
		return new TAssignmentExpression(TConditionalExpression.from(uexpr));
	}

	public static TAssignmentExpression from(TCastExpression cexpr) {
		return new TAssignmentExpression(TConditionalExpression.from(cexpr));
	}

}
