package tree.other;

import tree.DefaultTreeNode;
import tree.TreeNode;
import tree.declarations.TAbstractDeclarator;
import tree.declarations.TDirectAbstractDeclarator;
import tree.declarations.TPointerComprehension;
import tree.symbols.TSCompOp;

public abstract class CommonDeclarator extends DefaultTreeNode {
	
	public CommonDeclarator() {
	}
	public CommonDeclarator(CommonDeclarator node) {
		super(node);
	}

	public abstract CommonDeclarator newInstance(TPointerComprehension pc, CommonDirectDeclarator decl);


	public abstract boolean hasDirectDeclarator();
	public abstract CommonDirectDeclarator getDirectDeclarator();
	//public abstract void setDirectDeclarator(TCommonDirectDeclarator ddecl);
	
	public abstract boolean hasPointerComprehension();
	public abstract TPointerComprehension getPointerComprehension();
	//public abstract void setPointerComprehension(TPointerComprehension pc);
	
	public TreeNode getParams() {
		if (!hasDirectDeclarator())
			return null;
		return getDirectDeclarator().getParams();
	}
	
	public int getIndexOfCompOp() {
		if (!hasPointerComprehension())
			return -1;
		int i = 0;
		for (TreeNode pc : getPointerComprehension().getList()) {
			if (pc instanceof TSCompOp)
				return i;
			i++;
		}
		return -1;
	}

	public TAbstractDeclarator getTypeDeclarator() {
		if (!hasPointerComprehension()) {
			return TAbstractDeclarator.newAbstractInstance(null, getDirectDeclarator().getTypeDeclarator());
		} else {
			
			int index = getIndexOfCompOp();
			
			if (index == -1) {
				TDirectAbstractDeclarator tdecl = null;
				if (hasDirectDeclarator())
					tdecl = getDirectDeclarator().getTypeDeclarator();
				return TAbstractDeclarator.newAbstractInstance(getPointerComprehension(), tdecl);
			} else {
				return TAbstractDeclarator.newAbstractInstance(TPointerComprehension.from(getPointerComprehension().getList().subList(0, index)), null);
			}
			
		}
	}
	
	public CommonDeclarator getNameDeclarator() {
		if (!hasPointerComprehension()) {
			return newInstance(null, getDirectDeclarator().getNameDeclarator());
		} else {
			
			int index = getIndexOfCompOp();
			int size = getPointerComprehension().getList().size();
			
			if (index == -1) {
				if (!hasDirectDeclarator())
					return null;
				return newInstance(null, getDirectDeclarator().getNameDeclarator());
			} else {
				return newInstance(TPointerComprehension.from(getPointerComprehension().getList().subList(index+1, size)), getDirectDeclarator());
			}
			
		}
	}

}
