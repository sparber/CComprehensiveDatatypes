package tree.declarations;

import tree.DefaultTreeNode;
import tree.expressions.TMacroArgumentList;
import tree.symbols.TSIdentifier;
import tree.symbols.TSMacroType;
import tree.symbols.TSParLeft;
import tree.symbols.TSParRight;

public class TMacroTypeSpecifier extends DefaultTreeNode {

	public TMacroTypeSpecifier(TMacroTypeSpecifier node) {
		super(node);
	}
	
	public TMacroTypeSpecifier(TSMacroType mtype, TSIdentifier id, TSParLeft pleft, TSParRight pright) {
		addChild(mtype);
		addChild(id);
		addChild(pleft);
		addChild(pright);
	}

	public TMacroTypeSpecifier(TSMacroType mtype, TSIdentifier id, TSParLeft pleft, TMacroArgumentList alist, TSParRight pright) {
		addChild(mtype);
		addChild(id);
		addChild(pleft);
		addChild(alist);
		addChild(pright);
	}

	public static TMacroTypeSpecifier from(String name, DefaultTreeNode[] treeNodes) {
		TSMacroType mtype = new TSMacroType();
		TSIdentifier id = new TSIdentifier(name);
		if (treeNodes == null || treeNodes.length == 0)
			return new TMacroTypeSpecifier(mtype, id, new TSParLeft(), new TSParRight());
		else
			return new TMacroTypeSpecifier(mtype, id, new TSParLeft(), TMacroArgumentList.from(treeNodes), new TSParRight());
	}
}
