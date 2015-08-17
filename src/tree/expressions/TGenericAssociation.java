package tree.expressions;

import tree.DefaultTreeNode;
import tree.declarations.TTypeName;
import tree.symbols.TSColon;
import tree.symbols.TSDefault;

public class TGenericAssociation extends DefaultTreeNode {

	public TGenericAssociation(TGenericAssociation node) {
		super(node);
	}
	
	public TGenericAssociation(TTypeName type_name, TSColon colon, TAssignmentExpression expr) {
		addChild(type_name);
		addChild(colon);
		addChild(expr);
	}

	public TGenericAssociation(TSDefault def, TSColon colon, TAssignmentExpression expr) {
		addChild(def);
		addChild(colon);
		addChild(expr);
	}
}
