package tree.declarations;

import tree.DefaultTreeNode;
import tree.symbols.TSStruct;
import tree.symbols.TSUnion;

public class TStructOrUnion extends DefaultTreeNode {

	public TStructOrUnion(TStructOrUnion node) {
		super(node);
	}
	
	public TStructOrUnion(TSStruct struct) {
		addChild(struct);
	}
	
	public TStructOrUnion(TSUnion union) {
		addChild(union);
	}
	
}
