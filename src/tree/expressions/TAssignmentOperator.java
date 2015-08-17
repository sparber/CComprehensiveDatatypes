package tree.expressions;

import tree.DefaultTreeNode;
import tree.symbols.TSAddAssign;
import tree.symbols.TSAndAssign;
import tree.symbols.TSAssign;
import tree.symbols.TSDivAssign;
import tree.symbols.TSLeftAssign;
import tree.symbols.TSModAssign;
import tree.symbols.TSMulAssign;
import tree.symbols.TSOrAssign;
import tree.symbols.TSRightAssign;
import tree.symbols.TSSubAssign;
import tree.symbols.TSXorAssign;

public class TAssignmentOperator extends DefaultTreeNode {

	public TAssignmentOperator(TAssignmentOperator node) {
		super(node);
	}
	
	public TAssignmentOperator(TSAssign assign) {
		addChild(assign);
	}
	
	public TAssignmentOperator(TSMulAssign assign) {
		addChild(assign);
	}

	public TAssignmentOperator(TSDivAssign assign) {
		addChild(assign);
	}
	
	public TAssignmentOperator(TSModAssign assign) {
		addChild(assign);
	}
	
	public TAssignmentOperator(TSAddAssign assign) {
		addChild(assign);
	}
	
	public TAssignmentOperator(TSSubAssign assign) {
		addChild(assign);
	}
	
	public TAssignmentOperator(TSLeftAssign assign) {
		addChild(assign);
	}
	
	public TAssignmentOperator(TSRightAssign assign) {
		addChild(assign);
	}
	
	public TAssignmentOperator(TSAndAssign assign) {
		addChild(assign);
	}
	
	public TAssignmentOperator(TSXorAssign assign) {
		addChild(assign);
	}
	
	public TAssignmentOperator(TSOrAssign assign) {
		addChild(assign);
	}
}
