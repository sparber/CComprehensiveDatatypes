package errors;

import tree.TreeNode;

public class CXInternalError extends CXError {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5416556484525040887L;

	public CXInternalError(String string) {
		super(string);
	}

	public CXInternalError(String string, Throwable cause) {
		super(string);
		initCause(cause);
	}

	public CXInternalError(String string, TreeNode node) {
		super(string, node);
	}

}
