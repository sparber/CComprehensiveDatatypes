package tree.expressions;

import tree.DefaultTreeNode;
import tree.symbols.TSGeOp;
import tree.symbols.TSGtOp;
import tree.symbols.TSLeOp;
import tree.symbols.TSLtOp;

public class TRelationalExpression extends DefaultTreeNode {

	public TRelationalExpression(TRelationalExpression node) {
		super(node);
	}
	
	public TRelationalExpression(TShiftExpression sexpr) {
		addChild(sexpr);
	}
	
	public TRelationalExpression(TRelationalExpression rexpr, TSLtOp lt_op, TShiftExpression sexpr) {
		addChild(rexpr);
		addChild(lt_op);
		addChild(sexpr);
	}
	
	public TRelationalExpression(TRelationalExpression rexpr, TSGtOp gt_op, TShiftExpression sexpr) {
		addChild(rexpr);
		addChild(gt_op);
		addChild(sexpr);
	}
	
	public TRelationalExpression(TRelationalExpression rexpr, TSLeOp le_op, TShiftExpression sexpr) {
		addChild(rexpr);
		addChild(le_op);
		addChild(sexpr);
	}
	
	public TRelationalExpression(TRelationalExpression rexpr, TSGeOp ge_op, TShiftExpression sexpr) {
		addChild(rexpr);
		addChild(ge_op);
		addChild(sexpr);
	}

	public static TRelationalExpression from(TUnaryExpression uexpr) {
		return new TRelationalExpression(TShiftExpression.from(uexpr));
	}

	public static TRelationalExpression from(TCastExpression cexpr) {
		return new TRelationalExpression(TShiftExpression.from(cexpr));
	}
}
