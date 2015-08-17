package errors;

import tree.TreeNode;

public class CXFatalError extends CXError {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2578047221831409908L;

	public CXFatalError(String string, TreeNode node) {
		super(string, node);
	}

}
