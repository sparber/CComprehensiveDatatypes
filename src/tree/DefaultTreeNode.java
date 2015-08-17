package tree;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;

import errors.CXInternalError;

import java_cup.runtime.ComplexSymbolFactory.Location;

public abstract class DefaultTreeNode extends DefaultVisitInvoker implements TreeNode {
	
	protected DefaultTreeNode parent = null;
	private LinkedList<TreeNode> children = new LinkedList<TreeNode>();
	protected ListIterator<TreeNode> iterator = null;

	public DefaultTreeNode() {
	}
	
	public DefaultTreeNode(DefaultTreeNode node) {
		for (TreeNode child : node.children) {
			if (child instanceof DefaultTreeNodeSymbol) {
				try {
					DefaultTreeNodeSymbol newChild = (DefaultTreeNodeSymbol) child.getClass().newInstance();
					newChild.copyFrom((DefaultTreeNodeSymbol) child);
					addChild(newChild);
				} catch (ReflectiveOperationException e) {
					throw new CXInternalError("something went wrong in trying to make a copy of a TreeNodeSymbol", e);
				}
			} else {
				try {
					TreeNode newChild = child.getClass().getConstructor(new Class[] {child.getClass()}).newInstance(child);
					addChild(newChild);
				} catch (Exception e) {
					throw new CXInternalError("something went wrong in trying to make a copy of a TreeNode", e);
				}
			}
		}
	}
	
	public int getNChildren() {
		return children.size();
	}
	public TreeNode getChild(int index) {
		return children.get(index);
	}
	public void setChild(int index, TreeNode child) {
		children.set(index, child);
	}
	public void removeChild(int index) {
		children.remove(index);
	}
	public LinkedList<TreeNode> getChildren() {
		return children;
	}
	
	protected void addChild(int index, TreeNode child) {
		children.add(index, child);
		child.setParent(this);
	}

	protected void addChild(TreeNode child) {
		children.add(child);
		child.setParent(this);
	}
	
	protected void addChildren(Collection<TreeNode> children) {
		for (TreeNode child : children) {
			addChild(child);
		}
	}
	
	protected void addChildrenOf(DefaultTreeNode node) {
		addChildren(node.getChildren());
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
	public Location getRealLeft() {
		for (TreeNode child : children) {
			Location left = child.getRealLeft();
			if (left != null) {
				return left;
			}
		}
		return null;
	}
	@Override
	public Location getLeft() {
		if (children.size() == 0)
			return null;
		return children.getFirst().getLeft();
	}
	@Override
	public void setLeft(Location left) {
		if (children.size() == 0)
			return;
		children.getFirst().setLeft(left);
	}
	@Override
	public Location getRealRight() {
		Iterator<TreeNode> it = children.descendingIterator();
		while (it.hasNext()) {
			TreeNode child = it.next();
			Location right = child.getRealRight();
			if (right != null) {
				return right;
			}
		}
		return null;
	}
	@Override
	public Location getRight() {
		if (children.size() == 0)
			return null;
		return children.getLast().getRight();
	}
	@Override
	public void setRight(Location right) {
		if (children.size() == 0)
			return;
		children.getLast().setRight(right);
	}
	
	public ListIterator<TreeNode> getIterator() {
		return iterator;
	}
	@Override
	public boolean isDuringIteration() {
		return iterator != null;
	}
	
	@Override
	public void accept(DefaultVisitor visitor) {
		if (!invokeVisit(visitor, "Before", true))
			return;
		iterator = children.listIterator();
		while (iterator.hasNext()) {
			TreeNode child = iterator.next();
			child.accept(visitor);
		}
		iterator = null;
		invokeVisit(visitor, "After", false);
	}

}
