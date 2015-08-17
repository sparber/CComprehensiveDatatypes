package tree.statements;

import tree.DefaultTreeNodeSymbol;
import tree.expressions.TAssignmentExpression;
import tree.symbols.TSBreak;
import tree.symbols.TSContinue;
import tree.symbols.TSCut;
import tree.symbols.TSGoto;
import tree.symbols.TSIdentifier;
import tree.symbols.TSReturn;
import tree.symbols.TSSemicolon;
import tree.symbols.TSWith;
import tree.symbols.TSYield;

public class TJumpStatement extends TStatement {

	public TJumpStatement(TJumpStatement node) {
		super(node);
	}
	
	public TJumpStatement(TSGoto got, TSIdentifier id, TSSemicolon semicolon) {
		addChild(got);
		addChild(id);
		addChild(semicolon);
	}
	
	public TJumpStatement(TSContinue continu, TSSemicolon semicolon) {
		addChild(continu);
		addChild(semicolon);
	}
	
	public TJumpStatement(TSBreak brea, TSSemicolon semicolon) {
		addChild(brea);
		addChild(semicolon);
	}
	
	public TJumpStatement(TSReturn retur, TSSemicolon semicolon) {
		addChild(retur);
		addChild(semicolon);
	}
	
	public TJumpStatement(TSReturn retur, TAssignmentExpression expr, TSSemicolon semicolon) {
		addChild(retur);
		addChild(expr);
		addChild(semicolon);
	}

	public TJumpStatement(TSWith wit, TAssignmentExpression expr, TSSemicolon semicolon) {
		addChild(wit);
		addChild(expr);
		addChild(semicolon);
	}

	public TJumpStatement(TSCut cu, TSSemicolon semicolon) {
		addChild(cu);
		addChild(semicolon);
	}

	public TJumpStatement(TSYield yiel, TAssignmentExpression expr, TSSemicolon semicolon) {
		addChild(yiel);
		addChild(expr);
		addChild(semicolon);
	}
	
	public TJumpStatement(TSYield yiel, TSSemicolon semicolon) {
		addChild(yiel);
		addChild(semicolon);
	}
	
	public DefaultTreeNodeSymbol getKeyword() {
		return (DefaultTreeNodeSymbol) getChild(0);
	}
	
	public boolean hasAssignmentExpression() {
		return getChild(1) instanceof TAssignmentExpression;
	}

	public TAssignmentExpression getAssignmentExpression() {
		if (hasAssignmentExpression())
			return (TAssignmentExpression) getChild(1);
		else
			return null;
	}
}
