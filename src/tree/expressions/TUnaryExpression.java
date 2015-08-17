package tree.expressions;

import tree.DefaultTreeNode;
import tree.DefaultTreeNodeSymbol;
import tree.TreeNode;
import tree.declarations.TTypeName;
import tree.symbols.TSAlignof;
import tree.symbols.TSCompOp;
import tree.symbols.TSDecOp;
import tree.symbols.TSIncOp;
import tree.symbols.TSParLeft;
import tree.symbols.TSParRight;
import tree.symbols.TSSizeof;

public class TUnaryExpression extends DefaultTreeNode {

	public TUnaryExpression(TUnaryExpression node) {
		super(node);
	}
	
	public TUnaryExpression(TPostfixExpression pexpr) {
		addChild(pexpr);
	}
	
	public TUnaryExpression(TSIncOp inc_op, TUnaryExpression uexpr) {
		addChild(inc_op);
		addChild(uexpr);
	}
	
	public TUnaryExpression(TSDecOp dec_op, TUnaryExpression uexpr) {
		addChild(dec_op);
		addChild(uexpr);
	}
	
	public TUnaryExpression(TUnaryOperator uop, TCastExpression cexpr) {
		addChild(uop);
		addChild(cexpr);
	}
	
	public TUnaryExpression(TSSizeof sizeof, TUnaryExpression uexpr) {
		addChild(sizeof);
		addChild(uexpr);
	}
	
	public TUnaryExpression(TSSizeof sizeof, TSParLeft par_left, TTypeName type_name, TSParRight par_right) {
		addChild(sizeof);
		addChild(par_left);
		addChild(type_name);
		addChild(par_right);
	}
	
	public TUnaryExpression(TSAlignof alignof, TSParLeft par_left, TTypeName type_name, TSParRight par_right) {
		addChild(alignof);
		addChild(par_left);
		addChild(type_name);
		addChild(par_right);
	}

	public TUnaryExpression(TSCompOp comp_op, TSParLeft par_left, TTypeName tname, TSParRight par_right, TCompExpression cexpr) {
		addChild(comp_op);
		addChild(par_left);
		addChild(tname);
		addChild(par_right);
		addChild(cexpr);
	}
	
	public DefaultTreeNodeSymbol getOperator() {
		TreeNode child = getChild(0);
		if (child instanceof TUnaryOperator)
			return ((TUnaryOperator) child).getOperator();
		else if (child instanceof DefaultTreeNodeSymbol)
			return (DefaultTreeNodeSymbol) child;
		return null;
	}
	
	public TTypeName getTypeName() {
		if (getNChildren() < 3)
			return null;
		TreeNode node = getChild(2);
		if (node instanceof TTypeName)
			return (TTypeName) node;
		return null;
	}
	
	public TCastExpression getCastExpression() {
		TreeNode node = null;
		if (getNChildren() == 2 && (node = getChild(1)) instanceof TCastExpression)
			return (TCastExpression) node;
		return null;
	}
	
	public TCompExpression getCompExpression() {
		TreeNode node = null;
		if (getNChildren() == 5 && (node = getChild(4)) instanceof TCompExpression)
			return (TCompExpression) node;
		return null;
	}

	public static TUnaryExpression from(String name) {
		return new TUnaryExpression(TPostfixExpression.from(name));
	}

	public static TUnaryExpression from(int number) {
		return new TUnaryExpression(TPostfixExpression.from(number));
	}

	public static TUnaryExpression from(TExpression expr) {
		return new TUnaryExpression(TPostfixExpression.from(expr));
	}

}
