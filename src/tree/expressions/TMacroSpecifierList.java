package tree.expressions;

import java.util.LinkedList;

import tree.DefaultTreeNode;
import tree.TreeNode;
import tree.TreeNodeList;

public class TMacroSpecifierList extends DefaultTreeNode implements TreeNodeList<TMacroSpecifier> {

	public TMacroSpecifierList(TMacroSpecifierList node) {
		super(node);
	}
	
	public TMacroSpecifierList(TMacroSpecifier spec) {
		addChild(spec);
	}
	
	public TMacroSpecifierList(TMacroSpecifierList specs, TMacroSpecifier spec) {
		addChildrenOf(specs);
		addChild(spec);
	}

	@Override
	public LinkedList<TMacroSpecifier> getList() {
		LinkedList<TMacroSpecifier> list = new LinkedList<TMacroSpecifier>();
		for (TreeNode child : getChildren()) {
			list.add((TMacroSpecifier) child);
		}
		return list;
	}
	
	public static TMacroSpecifierList from(LinkedList<TMacroSpecifier> list) {
		TMacroSpecifierList specs = null;
		for (TMacroSpecifier spec : list) {
			if (specs == null) {
				specs = new TMacroSpecifierList(spec);
			} else {
				specs = new TMacroSpecifierList(specs, spec);
			}
		}
		return specs;
	}
	
}
