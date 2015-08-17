package tree.expressions;

import tree.DefaultTreeNode;
import tree.symbols.TSEnumerationConstant;
import tree.symbols.TSFConstant;
import tree.symbols.TSIConstant;

public class TConstant extends DefaultTreeNode {

	public TConstant(TConstant node) {
		super(node);
	}
	
	public TConstant(TSIConstant cons) {
		addChild(cons);
	}

	public TConstant(TSFConstant cons) {
		addChild(cons);
	}

	public TConstant(TSEnumerationConstant cons) {
		addChild(cons);
	}
}
