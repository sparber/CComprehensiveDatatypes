package tree.expressions;

import tree.DefaultTreeNode;
import tree.symbols.TSDivOp;
import tree.symbols.TSModOp;
import tree.symbols.TSMulOp;

public class TMultiplicativeExpression extends DefaultTreeNode {

	public TMultiplicativeExpression(TMultiplicativeExpression node) {
		super(node);
	}
	
	public TMultiplicativeExpression(TCastExpression sexpr) {
		addChild(sexpr);
	}
	
	public TMultiplicativeExpression(TMultiplicativeExpression mexpr, TSMulOp mul_op, TCastExpression sexpr) {
		addChild(mexpr);
		addChild(mul_op);
		addChild(sexpr);
	}
	
	public TMultiplicativeExpression(TMultiplicativeExpression mexpr, TSDivOp div_op, TCastExpression sexpr) {
		addChild(mexpr);
		addChild(div_op);
		addChild(sexpr);
	}
	
	public TMultiplicativeExpression(TMultiplicativeExpression mexpr, TSModOp mod_op, TCastExpression sexpr) {
		addChild(mexpr);
		addChild(mod_op);
		addChild(sexpr);
	}

	public static TMultiplicativeExpression from(TUnaryExpression uexpr) {
		return new TMultiplicativeExpression(TCastExpression.from(uexpr));
	}

	public static TMultiplicativeExpression from(TCastExpression cexpr) {
		return new TMultiplicativeExpression(cexpr);
	}
	
}
