package tree.expressions;

import tree.DefaultTreeNode;
import tree.declarations.TTypeName;
import tree.symbols.TSParLeft;
import tree.symbols.TSParRight;

public class TCastExpression extends DefaultTreeNode {

	public TCastExpression(TCastExpression node) {
		super(node);
	}
	
	public TCastExpression(TSetExpression sexpr) {
		addChild(sexpr);
	}
	
	public TCastExpression(TSParLeft par_left, TTypeName type_name, TSParRight par_right, TCastExpression cexpr) {
		addChild(par_left);
		addChild(type_name);
		addChild(par_right);
		addChild(cexpr);
	}

	public static TCastExpression from(TUnaryExpression uexpr) {
		return new TCastExpression(TSetExpression.from(uexpr));
	}

}
