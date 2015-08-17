package tree.expressions;

import java.util.LinkedList;

import tree.DefaultTreeNode;
import tree.TreeNode;
import tree.TreeNodeList;
import tree.symbols.TSComma;

public class TCompExpressionList extends DefaultTreeNode implements TreeNodeList<TAssignmentExpression> {

	@Override
	public LinkedList<TAssignmentExpression> getList() {
		LinkedList<TAssignmentExpression> list = new LinkedList<TAssignmentExpression>();
		for (TreeNode child : getChildren()) {
			if (child instanceof TAssignmentExpression) {
				list.add((TAssignmentExpression) child);
			}
		}
		return list;
	}
	
	public TCompExpressionList(TCompExpressionList node) {
		super(node);
	}
	
	public TCompExpressionList(TAssignmentExpression aexpr) {
		addChild(aexpr);
	}

	public TCompExpressionList(TCompExpressionList celist, TSComma comma, TAssignmentExpression aexpr) {
		addChildrenOf(celist);
		addChild(comma);
		addChild(aexpr);
	}
}
