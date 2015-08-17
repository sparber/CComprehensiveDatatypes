package tree.expressions;

import tree.DefaultTreeNode;
import tree.symbols.TSFuncName;
import tree.symbols.TSStringLiteral;

public class TString extends DefaultTreeNode {

	public TString(TString node) {
		super(node);
	}
	
	public TString(TSStringLiteral str) {
		addChild(str);
	}
	
	public TString(TSFuncName str) {
		addChild(str);
	}

}
