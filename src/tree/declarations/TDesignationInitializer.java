package tree.declarations;

import tree.DefaultTreeNode;

public class TDesignationInitializer extends DefaultTreeNode {

	public TDesignationInitializer(TDesignationInitializer node) {
		super(node);
	}
	
	public TDesignationInitializer(TDesignation designation, TInitializer init) {
		addChild(designation);
		addChild(init);
	}
	
	public TDesignationInitializer(TInitializer init) {
		addChild(init);
	}
}
