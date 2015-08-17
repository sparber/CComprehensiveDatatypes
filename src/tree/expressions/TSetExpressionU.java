package tree.expressions;

public class TSetExpressionU extends TSetExpression {

	public TSetExpressionU(TSetExpressionU node) {
		super(node);
	}
	
	public TUnaryExpression getUExpression() {
		return (TUnaryExpression) getChild(0);
	}
	
	public TSetExpressionU(TUnaryExpression uexpr) {
		addChild(uexpr);
	}

}
