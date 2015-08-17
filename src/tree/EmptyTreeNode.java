package tree;

import java_cup.runtime.ComplexSymbolFactory.Location;

public class EmptyTreeNode implements TreeNode {

	private Location left;
	private Location right;
	private DefaultTreeNode parent = null;

	public EmptyTreeNode(Location left, Location right) {
		this.left = left;
		this.right = right;
	}
	
	@Override
	public Location getRealLeft() {
		return left;
	}
	@Override
	public Location getLeft() {
		return getRealLeft();
	}
	@Override
	public void setLeft(Location left) {
		this.left = left;
	}

	@Override
	public Location getRealRight() {
		return right;
	}
	@Override
	public Location getRight() {
		return getRealRight();
	}
	@Override
	public void setRight(Location right) {
		this.right = right;
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
	public void accept(DefaultVisitor visitor) {
	}

}
