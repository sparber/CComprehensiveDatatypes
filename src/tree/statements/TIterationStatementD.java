package tree.statements;

import tree.expressions.TExpression;
import tree.symbols.TSDo;
import tree.symbols.TSParLeft;
import tree.symbols.TSParRight;
import tree.symbols.TSSemicolon;
import tree.symbols.TSWhile;

public class TIterationStatementD extends TIterationStatement {

	public TIterationStatementD(TIterationStatementD node) {
		super(node);
	}
	
	public TIterationStatementD(TSDo d, TStatement statement, TSWhile whil, TSParLeft par_left, TExpression expr, TSParRight par_right, TSSemicolon semicolon) {
		addChild(d);
		addChild(statement);
		addChild(whil);
		addChild(par_left);
		addChild(expr);
		addChild(par_right);
		addChild(semicolon);
	}
	
}
