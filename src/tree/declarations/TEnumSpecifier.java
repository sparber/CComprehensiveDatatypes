package tree.declarations;

import tree.DefaultTreeNode;
import tree.symbols.TSBraceLeft;
import tree.symbols.TSBraceRight;
import tree.symbols.TSComma;
import tree.symbols.TSEnum;
import tree.symbols.TSIdentifier;

public class TEnumSpecifier extends DefaultTreeNode {

	public TEnumSpecifier(TEnumSpecifier node) {
		super(node);
	}
	
	public TEnumSpecifier(TSEnum en, TSBraceLeft brace_left, TEnumeratorList ens, TSBraceRight brace_right) {
		addChild(en);
		addChild(brace_left);
		addChild(ens);
		addChild(brace_right);
	}
	
	public TEnumSpecifier(TSEnum en, TSBraceLeft brace_left, TEnumeratorList ens, TSComma comma, TSBraceRight brace_right) {
		addChild(en);
		addChild(brace_left);
		addChild(ens);
		addChild(comma);
		addChild(brace_right);
	}

	public TEnumSpecifier(TSEnum en, TSIdentifier id, TSBraceLeft brace_left, TEnumeratorList ens, TSBraceRight brace_right) {
		addChild(en);
		addChild(id);
		addChild(brace_left);
		addChild(ens);
		addChild(brace_right);
	}
	
	public TEnumSpecifier(TSEnum en, TSIdentifier id, TSBraceLeft brace_left, TEnumeratorList ens, TSComma comma, TSBraceRight brace_right) {
		addChild(en);
		addChild(id);
		addChild(brace_left);
		addChild(ens);
		addChild(comma);
		addChild(brace_right);
	}

	public TEnumSpecifier(TSEnum en, TSIdentifier id) {
		addChild(en);
		addChild(id);
	}
}
