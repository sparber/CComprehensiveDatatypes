package errors;

import tree.TreeNode;

public class CXSyntaxError extends CXError {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2495860870685924680L;

	public CXSyntaxError(String string, TreeNode node) {
		super(string, node);
	}

	public CXSyntaxError(String string) {
		super(string);
	}
	
}
