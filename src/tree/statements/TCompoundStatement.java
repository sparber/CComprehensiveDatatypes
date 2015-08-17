package tree.statements;

import tree.symbols.TSBraceLeft;
import tree.symbols.TSBraceRight;

public class TCompoundStatement extends TStatement {

	public TCompoundStatement(TCompoundStatement node) {
		super(node);
	}
	
	public boolean hasItems() {
		return getNChildren() > 2;
	}
	public TBlockItemList getItems() {
		if (!hasItems())
			return null;
		return (TBlockItemList) getChild(1);
	}
	
	public boolean hasFinallyStatement() {
		return getNChildren() == 4;
	}
	public TFinallyStatement getFinallyStatement() {
		if (!hasFinallyStatement())
			return null;
		return (TFinallyStatement) getChild(2);
	}
	
	public TSBraceLeft getBraceLeft() {
		return (TSBraceLeft) getChild(0);
	}
	public TSBraceRight getBraceRight() {
		return (TSBraceRight) getChild(getNChildren()-1);
	}
	
	public TCompoundStatement(TSBraceLeft brace_left, TSBraceRight brace_right) {
		addChild(brace_left);
		addChild(brace_right);
	}
	
	public TCompoundStatement(TSBraceLeft brace_left, TBlockItemList items, TSBraceRight brace_right) {
		addChild(brace_left);
		addChild(items);
		addChild(brace_right);
	}
	
	public TCompoundStatement(TSBraceLeft brace_left, TBlockItemList items, TFinallyStatement fstat, TSBraceRight brace_right) {
		addChild(brace_left);
		addChild(items);
		addChild(fstat);
		addChild(brace_right);
	}

}
