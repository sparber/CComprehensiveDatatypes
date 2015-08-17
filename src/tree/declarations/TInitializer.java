package tree.declarations;

import tree.DefaultTreeNode;
import tree.expressions.TAssignmentExpression;
import tree.symbols.TSBraceLeft;
import tree.symbols.TSBraceRight;
import tree.symbols.TSComma;

public class TInitializer extends DefaultTreeNode {

	public TInitializer(TInitializer node) {
		super(node);
	}
	
	public TInitializer(TSBraceLeft brace_left, TInitializerList inits, TSBraceRight brace_right) {
		addChild(brace_left);
		addChild(inits);
		addChild(brace_right);
	}
	
	public TInitializer(TSBraceLeft brace_left, TInitializerList inits, TSComma comma, TSBraceRight brace_right) {
		addChild(brace_left);
		addChild(inits);
		addChild(comma);
		addChild(brace_right);
	}
	
	public TInitializer(TAssignmentExpression aexpr) {
		addChild(aexpr);
	}
}
