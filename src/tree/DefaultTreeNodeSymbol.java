package tree;

import errors.CXInternalError;

import java_cup.runtime.ComplexSymbolFactory.ComplexSymbol;
import java_cup.runtime.ComplexSymbolFactory.Location;

public abstract class DefaultTreeNodeSymbol extends DefaultVisitInvoker implements TreeNode, TreeNodePrintable, parser.sym {
	
	public ComplexSymbol symbol = null;

	public String name;
	public int sym;
	public String value = null;

	private Location left = null;
	private Location right = null;

	private DefaultTreeNode parent;
	
	public DefaultTreeNodeSymbol(String name, int sym) {
		this.name = name;
		this.sym = sym;
	}
	
	public DefaultTreeNodeSymbol(String name, int sym, String value) {
		this(name, sym);
		this.value = value;
	}
	
	public void copyFrom(DefaultTreeNodeSymbol child) {
		this.symbol = null;//child.symbol;
		this.name = child.name;
		this.sym = child.sym;
		this.value = child.value;
	}


	public void setSymbol(ComplexSymbol symbol) {
		this.symbol = symbol;
		if (sym != symbol.sym)
			throw new CXInternalError("tried to assign wrong type of symbol", this);
	}
	
	@Override
	public String getPrintableText() {
		return value != null ? value : name;
	}
	
	@Override
	public Location getRealLeft() {
		if (symbol == null)
			return null;
		return symbol.xleft;
	}
	@Override
	public Location getLeft() {
		if (left != null)
			return left;
		return getRealLeft();
	}
	@Override
	public void setLeft(Location left) {
		this.left = left;
	}
	@Override
	public Location getRealRight() {
		if (symbol == null)
			return null;
		return symbol.xright;
	}
	@Override
	public Location getRight() {
		if (right != null)
			return right;
		return getRealRight();
	}
	@Override
	public void setRight(Location right) {
		this.right = right;
	}
	
	@Override
	public void accept(DefaultVisitor visitor) {
		invokeVisit(visitor, "", true);
	}
	
	@Override
	public String toString() {
		return "Symbol: "+name+" "+value;
	}

	@Override
	public void setParent(DefaultTreeNode parent) {
		this.parent = parent;
	}

	@Override
	public DefaultTreeNode getParent() {
		return parent;
	}

	@Override
	public boolean isDuringIteration() {
		return false;
	}

}
