package tree.statements;

import tree.expressions.TExpression;
import tree.symbols.TSSemicolon;

public class TExpressionStatement extends TStatement {

	public TExpressionStatement(TExpressionStatement node) {
		super(node);
	}
	
	public TExpressionStatement(TSSemicolon semicolon) {
		addChild(semicolon);
	}
	
	public TExpressionStatement(TExpression expr, TSSemicolon semicolon) {
		addChild(expr);
		addChild(semicolon);
	}
	
}
