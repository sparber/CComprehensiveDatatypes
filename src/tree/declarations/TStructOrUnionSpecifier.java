package tree.declarations;

import tree.DefaultTreeNode;
import tree.symbols.TSBraceLeft;
import tree.symbols.TSBraceRight;
import tree.symbols.TSIdentifier;
import tree.symbols.TSTypedefName;

public class TStructOrUnionSpecifier extends DefaultTreeNode {

	public TStructOrUnionSpecifier(TStructOrUnionSpecifier node) {
		super(node);
	}
	
	public TStructOrUnionSpecifier(TStructOrUnion strunion, TSBraceLeft brace_left, TStructDeclarationList decllist, TSBraceRight brace_right) {
		addChild(strunion);
		addChild(brace_left);
		addChild(decllist);
		addChild(brace_right);
	}
	
	public TStructOrUnionSpecifier(TStructOrUnion strunion, TSIdentifier id, TSBraceLeft brace_left, TStructDeclarationList decllist, TSBraceRight brace_right) {
		addChild(strunion);
		addChild(id);
		addChild(brace_left);
		addChild(decllist);
		addChild(brace_right);
	}
	
	public TStructOrUnionSpecifier(TStructOrUnion strunion, TSIdentifier id) {
		addChild(strunion);
		addChild(id);
	}

	public TStructOrUnionSpecifier(TStructOrUnion strunion, TSTypedefName tname) {
		addChild(strunion);
		addChild(tname);
	}
}
