package tree.expressions;

import tree.DefaultTreeNode;
import tree.expressions.TMacroArgumentList;
import tree.symbols.TSIdentifier;
import tree.symbols.TSMacro;
import tree.symbols.TSParLeft;
import tree.symbols.TSParRight;

public class TMacroSpecifier extends DefaultTreeNode {

	public TMacroSpecifier(TMacroSpecifier node) {
		super(node);
	}
	
	public TMacroSpecifier(TSMacro mtype, TSIdentifier id, TSParLeft pleft, TSParRight pright) {
		addChild(mtype);
		addChild(id);
		addChild(pleft);
		addChild(pright);
	}

	public TMacroSpecifier(TSMacro mtype, TSIdentifier id, TSParLeft pleft, TMacroArgumentList alist, TSParRight pright) {
		addChild(mtype);
		addChild(id);
		addChild(pleft);
		addChild(alist);
		addChild(pright);
	}

	public static TMacroSpecifier from(String name, DefaultTreeNode[] treeNodes) {
		TSMacro macro = new TSMacro();
		TSIdentifier id = new TSIdentifier(name);
		if (treeNodes == null || treeNodes.length == 0)
			return new TMacroSpecifier(macro, id, new TSParLeft(), new TSParRight());
		else
			return new TMacroSpecifier(macro, id, new TSParLeft(), TMacroArgumentList.from(treeNodes), new TSParRight());
	}
}
