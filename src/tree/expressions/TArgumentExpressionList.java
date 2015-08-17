package tree.expressions;

import java.util.LinkedList;

import tree.DefaultTreeNode;
import tree.TreeNode;
import tree.TreeNodeList;
import tree.symbols.TSComma;

public class TArgumentExpressionList extends DefaultTreeNode implements TreeNodeList<TAssignmentExpression> {

	public TArgumentExpressionList(TArgumentExpressionList node) {
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

	public TArgumentExpressionList(TAssignmentExpression node) {
		addChild(node);
	}

	public TArgumentExpressionList(TArgumentExpressionList nodes, TSComma comma, TAssignmentExpression node) {
		addChildrenOf(nodes);
		addChild(comma);
		addChild(node);
	}

	public static TArgumentExpressionList from(TAssignmentExpression[] aexprs) {
		TArgumentExpressionList aelist = null;
		for (int i=0; i<aexprs.length; i++) {
			TAssignmentExpression aexpr = aexprs[i];
			if (aelist == null) {
				aelist = new TArgumentExpressionList(aexpr);
			} else {
				aelist = new TArgumentExpressionList(aelist, new TSComma(), aexpr);
			}
		}
		return aelist;
	}

}
