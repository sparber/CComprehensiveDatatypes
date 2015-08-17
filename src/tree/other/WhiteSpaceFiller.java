package tree.other;

import java_cup.runtime.ComplexSymbolFactory.Location;
import tree.DefaultTreeNode;
import tree.DefaultVisitor;
import tree.TreeNodePrintable;


public class WhiteSpaceFiller implements TreeNodePrintable {
	
	private String text;
	private Location left;
	private Location right;

	public WhiteSpaceFiller(Location left, Location right, String text) {
		this.text = text;
		setLeft(left);
		setRight(right);
	}
	
	@Override
	public String getPrintableText() {
		return text;
	}

	@Override
	public Location getRealLeft() {
		return null;
	}
	@Override
	public Location getLeft() {
		return left;
	}
	@Override
	public void setLeft(Location left) {
		this.left = left;
	}

	@Override
	public Location getRealRight() {
		return null;
	}
	@Override
	public Location getRight() {
		return right;
	}
	@Override
	public void setRight(Location right) {
		this.right = right;
	}

	@Override
	public void setParent(DefaultTreeNode parent) {
	}

	@Override
	public DefaultTreeNode getParent() {
		return null;
	}

	@Override
	public void accept(DefaultVisitor visitor) {
	}

}
