package errors;

import tree.TreeNode;

public abstract class CXError extends RuntimeException {

	private TreeNode node;

	public CXError(String string) {
		super(string);
	}
	
	public CXError(String string, TreeNode node) {
		super(string);
		this.node = node;
	}
	
	public TreeNode getNode() {
		return node;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 3250068196214490714L;


}
