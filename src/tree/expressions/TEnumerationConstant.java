package tree.expressions;

import tree.DefaultTreeNode;
import tree.symbols.TSIdentifier;

public class TEnumerationConstant extends DefaultTreeNode {

	public TEnumerationConstant(TEnumerationConstant node) {
		super(node);
	}
	
	public TEnumerationConstant(TSIdentifier id) {
		addChild(id);
	}
	
}
