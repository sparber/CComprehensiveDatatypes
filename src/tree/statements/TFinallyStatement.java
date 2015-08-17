package tree.statements;

import tree.DefaultTreeNode;
import tree.symbols.TSFinally;

public class TFinallyStatement extends DefaultTreeNode {

	public TFinallyStatement(TFinallyStatement node) {
		super(node);
	}
	
	public TFinallyStatement(TSFinally finall, TStatement stat) {
		addChild(finall);
		addChild(stat);
	}

	public TStatement getStatement() {
		return (TStatement) getChild(1);
	}

}
