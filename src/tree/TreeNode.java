package tree;

import java_cup.runtime.ComplexSymbolFactory.Location;

public interface TreeNode {

	public Location getRealLeft();
	public Location getLeft();
	public void setLeft(Location left);
	public Location getRealRight();
	public Location getRight();
	public void setRight(Location right);
	
	public void setParent(DefaultTreeNode parent);
	public DefaultTreeNode getParent();
	
	public void accept(DefaultVisitor visitor);
	
}
