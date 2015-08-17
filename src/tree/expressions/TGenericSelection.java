package tree.expressions;

import tree.DefaultTreeNode;
import tree.symbols.TSComma;
import tree.symbols.TSGeneric;
import tree.symbols.TSParLeft;
import tree.symbols.TSParRight;

public class TGenericSelection extends DefaultTreeNode {

	public TGenericSelection(TGenericSelection node) {
		super(node);
	}
	
	public TGenericSelection(TSGeneric generic, TSParLeft par_left, TAssignmentExpression expr, TSComma comma, TGenericAssocList genassoclist, TSParRight par_right) {
		addChild(generic);
		addChild(par_left);
		addChild(expr);
		addChild(comma);
		addChild(genassoclist);
		addChild(par_right);
	}

}
