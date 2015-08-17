package tree.statements;

import tree.expressions.TExpression;
import tree.symbols.TSParLeft;
import tree.symbols.TSParRight;
import tree.symbols.TSWhile;

public class TIterationStatementW extends TIterationStatement {

	public TIterationStatementW(TIterationStatementW node) {
		super(node);
	}
	
	public TIterationStatementW(TSWhile whil, TSParLeft par_left, TExpression expr, TSParRight par_right, TStatement statement) {
		addChild(whil);
		addChild(par_left);
		addChild(expr);
		addChild(par_right);
		addChild(statement);
	}
	
}
