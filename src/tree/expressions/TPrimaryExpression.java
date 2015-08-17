package tree.expressions;

import tree.DefaultTreeNode;
import tree.symbols.TSIConstant;
import tree.symbols.TSIdentifier;
import tree.symbols.TSParLeft;
import tree.symbols.TSParRight;

public class TPrimaryExpression extends DefaultTreeNode {

	public TPrimaryExpression(TPrimaryExpression node) {
		super(node);
	}
	
	public TPrimaryExpression(TSIdentifier id) {
		addChild(id);
	}
	
	public TPrimaryExpression(TConstant cons) {
		addChild(cons);
	}

	public TPrimaryExpression(TString str) {
		addChild(str);
	}
	
	public TPrimaryExpression(TMacroSpecifierList mspecs) {
		addChild(mspecs);
	}
	
	public TPrimaryExpression(TSParLeft par_left, TExpression expr, TSParRight par_right) {
		addChild(par_left);
		addChild(expr);
		addChild(par_right);
	}
	
	public TPrimaryExpression(TGenericSelection gs) {
		addChild(gs);
	}

	public static TPrimaryExpression from(String value) {
		return new TPrimaryExpression(new TSIdentifier(value));
	}

	public static TPrimaryExpression from(int number) {
		return new TPrimaryExpression(new TConstant(new TSIConstant(""+number)));
	}

	public static TPrimaryExpression from(TExpression expr) {
		return new TPrimaryExpression(new TSParLeft(), expr, new TSParRight());
	}
	
}
