package tree.other;

import java.util.LinkedList;

import tree.DefaultTreeNode;
import tree.TreeNode;


public class TreeRoot extends DefaultTreeNode {
	
	private LinkedList<String> translators = new LinkedList<String>();

	public TreeRoot(TreeRoot node) {
		super(node);
		this.translators  = new LinkedList<String>(node.translators);
	}
	
	public TreeRoot(TreeNode node) {
		addChild(node);
	}
	
	public void addTranslator(String translator) {
		translators.add(translator);
	}

	public boolean translatedBy(String[] preTranslators) {
		for (String trans : preTranslators) {
			if (!translators.contains(trans))
				return false;
		}
		return true;
	}
	
}
