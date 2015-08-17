package tree.statements;

import tree.DefaultTreeNodeSymbol;
import tree.expressions.TExpression;
import tree.symbols.TSElse;
import tree.symbols.TSIf;
import tree.symbols.TSParLeft;
import tree.symbols.TSParRight;
import tree.symbols.TSSwitch;

public class TSelectionStatement extends TStatement {

	public TSelectionStatement(TSelectionStatement node) {
		super(node);
	}
	
	public TSelectionStatement(TSIf i, TSParLeft par_left, TExpression expr, TSParRight par_right, TStatement statement1, TSElse els, TStatement statement2) {
		addChild(i);
		addChild(par_left);
		addChild(expr);
		addChild(par_right);
		addChild(statement1);
		addChild(els);
		addChild(statement2);
	}
	
	public TSelectionStatement(TSIf i, TSParLeft par_left, TExpression expr, TSParRight par_right, TStatement statement) {
		addChild(i);
		addChild(par_left);
		addChild(expr);
		addChild(par_right);
		addChild(statement);
	}
	
	public TSelectionStatement(TSSwitch switc, TSParLeft par_left, TExpression expr, TSParRight par_right, TStatement statement) {
		addChild(switc);
		addChild(par_left);
		addChild(expr);
		addChild(par_right);
		addChild(statement);
	}

	public DefaultTreeNodeSymbol getKeyword() {
		return (DefaultTreeNodeSymbol) getChild(0);
	}
}
