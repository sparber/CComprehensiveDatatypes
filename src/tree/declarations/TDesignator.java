package tree.declarations;

import tree.DefaultTreeNode;
import tree.expressions.TConstantExpression;
import tree.symbols.TSBracketLeft;
import tree.symbols.TSBracketRight;
import tree.symbols.TSDot;
import tree.symbols.TSIdentifier;

public class TDesignator extends DefaultTreeNode {
	
	public TDesignator(TDesignator node) {
		super(node);
	}
	
	public TDesignator(TSBracketLeft bracket_left, TConstantExpression cexpr, TSBracketRight bracket_right) {
		addChild(bracket_left);
		addChild(cexpr);
		addChild(bracket_right);
	}
	
	public TDesignator(TSDot dot, TSIdentifier id) {
		addChild(dot);
		addChild(id);
	}

}
