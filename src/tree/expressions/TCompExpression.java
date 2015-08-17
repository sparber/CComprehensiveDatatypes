package tree.expressions;

import java.util.LinkedList;

import tree.DefaultTreeNode;
import tree.symbols.TSBraceLeft;
import tree.symbols.TSBraceRight;
import tree.symbols.TSComma;

public class TCompExpression extends DefaultTreeNode {

	public TCompExpression(TCompExpression node) {
		super(node);
	}
	
	public TCompExpression(TCastExpression cexpr) {
		addChild(cexpr);
	}
	
	public TCompExpression(TSBraceLeft bleft, TSBraceRight bright) {
		addChild(bleft);
		addChild(bright);
	}
	
	public TCompExpression(TSBraceLeft bleft, TCompExpressionList celist, TSBraceRight bright) {
		addChild(bleft);
		addChild(celist);
		addChild(bright);
	}

	public TCompExpression(TSBraceLeft bleft, TCompExpressionList celist, TSComma comma, TSBraceRight bright) {
		addChild(bleft);
		addChild(celist);
		addChild(comma);
		addChild(bright);
	}

	public LinkedList<TAssignmentExpression> getAssignmentExpressions() {
		if (getNChildren() == 1) {
			LinkedList<TAssignmentExpression> list = new LinkedList<TAssignmentExpression>();
			list.add(TAssignmentExpression.from((TCastExpression) getChild(0)));
			return list;
		} else if (getNChildren() == 2) {
			return new LinkedList<TAssignmentExpression>();
		} else {
			return ((TCompExpressionList) getChild(1)).getList();
		}
	}
}
