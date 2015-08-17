package tree.other;

import tree.DefaultTreeNode;
import tree.DefaultVisitor;
import tree.TreeNodePrintable;
import java_cup.runtime.ComplexSymbolFactory.Location;

public class WhiteSpaceSkipper implements TreeNodePrintable {
	
	private Location loc;

	public WhiteSpaceSkipper(Location loc) {
		this.loc = loc;
	}

	@Override
	public String getPrintableText() {
		return null;
	}
	
	@Override
	public Location getRealLeft() {
		return null;
	}
	@Override
	public Location getLeft() {
		return loc;
	}
	@Override
	public void setLeft(Location left) {
	}
	
	@Override
	public Location getRealRight() {
		return null;
	}
	@Override
	public Location getRight() {
		return loc;
	}
	@Override
	public void setRight(Location right) {
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
