package tree.declarations;

import tree.DefaultTreeNode;
import tree.symbols.TSInline;
import tree.symbols.TSNoreturn;

public class TFunctionSpecifier extends DefaultTreeNode {

	public TFunctionSpecifier(TFunctionSpecifier node) {
		super(node);
	}
	
	public TFunctionSpecifier(TSInline inline) {
		addChild(inline);
	}
	
	public TFunctionSpecifier(TSNoreturn noreturn) {
		addChild(noreturn);
	}
}
