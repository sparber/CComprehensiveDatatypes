package tree.expressions;

import tree.symbols.TSDot;
import tree.symbols.TSIdentifier;
import tree.symbols.TSPtrOp;

public class TPostfixExpressionM extends TPostfixExpression {
	
	public TPostfixExpressionM(TPostfixExpressionM node) {
		super(node);
	}
	
	public TPostfixExpression getPExpression() {
		return (TPostfixExpression)getChild(0);
	}
	public boolean isDereferencing() {
		return getChild(1) instanceof TSPtrOp;
	}
	public TSIdentifier getIdentifier() {
		return (TSIdentifier)getChild(2);
	}

	public TPostfixExpressionM(TPostfixExpression pexpr, TSDot dot, TSIdentifier id) {
		addChild(pexpr);
		addChild(dot);
		addChild(id);
	}

	public TPostfixExpressionM(TPostfixExpression pexpr, TSPtrOp ptr_op, TSIdentifier id) {
		addChild(pexpr);
		addChild(ptr_op);
		addChild(id);
	}
}
