package tree.declarations;

import errors.CXInternalError;
import tree.DefaultTreeNode;
import tree.expressions.TConstantExpression;
import tree.symbols.TSColon;

public class TStructDeclarator extends DefaultTreeNode {

	public TStructDeclarator(TStructDeclarator node) {
		super(node);
	}
	
	public TStructDeclarator(TSColon colon, TConstantExpression cexpr) {
		addChild(colon);
		addChild(cexpr);
	}
	
	public TStructDeclarator(TDeclarator decl, TSColon colon, TConstantExpression cexpr) {
		addChild(decl);
		addChild(colon);
		addChild(cexpr);
	}
	
	public TStructDeclarator(TDeclarator decl) {
		addChild(decl);
	}
	
	public boolean hasDeclarator() {
		return getChild(0) instanceof TDeclarator;
	}

	public TDeclarator getDeclarator() {
		if (!hasDeclarator())
			return null;
		return (TDeclarator) getChild(0);
	}
	
	public void setDeclarator(TDeclarator node) {
		if (node == null) {
			if (getNChildren() == 1)
				throw new CXInternalError("struct declarator can not take a declarator");
			else if (hasDeclarator())
				removeChild(0);
		} else {
			if (hasDeclarator())
				setChild(0, node);
			else
				addChild(0, node);
		}
	}

}
