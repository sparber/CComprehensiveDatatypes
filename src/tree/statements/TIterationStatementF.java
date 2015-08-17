package tree.statements;

import tree.DefaultTreeNode;
import tree.TreeNode;
import tree.declarations.TDeclaration;
import tree.expressions.TExpression;
import tree.symbols.TSFor;
import tree.symbols.TSParLeft;
import tree.symbols.TSParRight;

public class TIterationStatementF extends TIterationStatement {

	public TIterationStatementF(TIterationStatementF node) {
		super(node);
	}
	
	public boolean isDeclaration() {
		return getExprDecl1() instanceof TDeclaration;
	}
	public boolean isExpressionStatement() {
		return getExprDecl1() instanceof TExpressionStatement;
	}
	
	public TreeNode getExprDecl1() {
		return getChild(2);
	}
	public void setExprDecl1(DefaultTreeNode node) {
		setChild(2, node);
	}

	public TExpressionStatement getExpr2() {
		return (TExpressionStatement) getChild(3);
	}

	public TExpression getExpr3() {
		if (getNChildren() == 6)
			return null;
		return (TExpression) getChild(4);
	}
	
	public TIterationStatementF(TSFor fo, TSParLeft par_left, TExpressionStatement estat1, TExpressionStatement estat2, TSParRight par_right, TStatement statement) {
		addChild(fo);
		addChild(par_left);
		addChild(estat1);
		addChild(estat2);
		addChild(par_right);
		addChild(statement);
	}

	public TIterationStatementF(TSFor fo, TSParLeft par_left, TExpressionStatement estat1, TExpressionStatement estat2, TExpression expr, TSParRight par_right, TStatement statement) {
		addChild(fo);
		addChild(par_left);
		addChild(estat1);
		addChild(estat2);
		addChild(expr);
		addChild(par_right);
		addChild(statement);
	}

	public TIterationStatementF(TSFor fo, TSParLeft par_left, TDeclaration decl, TExpressionStatement estat, TSParRight par_right, TStatement statement) {
		addChild(fo);
		addChild(par_left);
		addChild(decl);
		addChild(estat);
		addChild(par_right);
		addChild(statement);
	}

	public TIterationStatementF(TSFor fo, TSParLeft par_left, TDeclaration decl, TExpressionStatement estat, TExpression expr, TSParRight par_right, TStatement statement) {
		addChild(fo);
		addChild(par_left);
		addChild(decl);
		addChild(estat);
		addChild(expr);
		addChild(par_right);
		addChild(statement);
	}
}
