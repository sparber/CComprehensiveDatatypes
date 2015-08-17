package tree.expressions;

import java.util.LinkedList;

import tree.DefaultTreeNode;
import tree.TreeNode;
import tree.TreeNodeList;
import tree.symbols.TSComma;

public class TExpression extends DefaultTreeNode implements TreeNodeList<TAssignmentExpression> {

	public TExpression(TExpression node) {
		super(node);
	}
	
	public LinkedList<TAssignmentExpression> getList() {
		LinkedList<TAssignmentExpression> list = new LinkedList<TAssignmentExpression>();
		for (TreeNode child : getChildren()) {
			if (child instanceof TAssignmentExpression)
				list.add((TAssignmentExpression) child);
		}
		return list;
	}
	
	public TExpression(TExpression nodes, TSComma comma, TAssignmentExpression node) {
		addChildrenOf(nodes);
		addChild(comma);
		addChild(node);
	}

	public TExpression(TAssignmentExpression node) {
		addChild(node);
	}

	public static TExpression from(TUnaryExpression uexpr) {
		return new TExpression(TAssignmentExpression.from(uexpr));
	}

}
