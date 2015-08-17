package tree.declarations;

import java.util.LinkedList;

import tree.DefaultTreeNode;
import tree.TreeNode;
import tree.expressions.TAssignmentExpression;
import tree.other.CommonDirectDeclarator;
import tree.symbols.TSBracketLeft;
import tree.symbols.TSBracketRight;
import tree.symbols.TSIdentifier;
import tree.symbols.TSMulOp;
import tree.symbols.TSStatic;

public class TDirectDeclaratorA extends TDirectDeclarator {
	
	public TDirectDeclaratorA(TDirectDeclaratorA node) {
		super(node);
	}
	
	public TDirectDeclarator getDirDeclarator() {
		return (TDirectDeclarator) getChild(0);
	}
	
	public boolean hasMulOp() {
		for (TreeNode child : getChildren()) {
			if (child instanceof TSMulOp) {
				return true;
			}
		}
		return false;
	}
	public boolean hasStatic() {
		for (TreeNode child : getChildren()) {
			if (child instanceof TSStatic) {
				return true;
			}
		}
		return false;
	}
	public TTypeQualifierList getQualis() {
		for (TreeNode child : getChildren()) {
			if (child instanceof TTypeQualifierList) {
				return (TTypeQualifierList) child;
			}
		}
		return null;
	}
	public TAssignmentExpression getAExpr() {
		for (TreeNode child : getChildren()) {
			if (child instanceof TAssignmentExpression) {
				return (TAssignmentExpression) child;
			}
		}
		return null;
	}
	
	public TDirectDeclaratorA(TDirectDeclarator dirdecl, TSBracketLeft bracket_left, TSBracketRight bracket_right) {
		addChild(dirdecl);
		addChild(bracket_left);
		addChild(bracket_right);
	}
	
	public TDirectDeclaratorA(TDirectDeclarator dirdecl, TSBracketLeft bracket_left, TSMulOp mul_op, TSBracketRight bracket_right) {
		addChild(dirdecl);
		addChild(bracket_left);
		addChild(mul_op);
		addChild(bracket_right);
	}

	public TDirectDeclaratorA(TDirectDeclarator dirdecl, TSBracketLeft bracket_left, TSStatic stat, TTypeQualifierList qualis, TAssignmentExpression aexpr, TSBracketRight bracket_right) {
		addChild(dirdecl);
		addChild(bracket_left);
		addChild(stat);
		addChild(qualis);
		addChild(aexpr);
		addChild(bracket_right);
	}

	public TDirectDeclaratorA(TDirectDeclarator dirdecl, TSBracketLeft bracket_left, TSStatic stat, TAssignmentExpression aexpr, TSBracketRight bracket_right) {
		addChild(dirdecl);
		addChild(bracket_left);
		addChild(stat);
		addChild(aexpr);
		addChild(bracket_right);
	}

	public TDirectDeclaratorA(TDirectDeclarator dirdecl, TSBracketLeft bracket_left, TTypeQualifierList qualis, TSMulOp mul_op, TSBracketRight bracket_right) {
		addChild(dirdecl);
		addChild(bracket_left);
		addChild(qualis);
		addChild(mul_op);
		addChild(bracket_right);
	}

	public TDirectDeclaratorA(TDirectDeclarator dirdecl, TSBracketLeft bracket_left, TTypeQualifierList qualis, TSStatic stat, TAssignmentExpression aexpr, TSBracketRight bracket_right) {
		addChild(dirdecl);
		addChild(bracket_left);
		addChild(qualis);
		addChild(stat);
		addChild(aexpr);
		addChild(bracket_right);
	}

	public TDirectDeclaratorA(TDirectDeclarator dirdecl, TSBracketLeft bracket_left, TTypeQualifierList qualis, TAssignmentExpression aexpr, TSBracketRight bracket_right) {
		addChild(dirdecl);
		addChild(bracket_left);
		addChild(qualis);
		addChild(aexpr);
		addChild(bracket_right);
	}

	public TDirectDeclaratorA(TDirectDeclarator dirdecl, TSBracketLeft bracket_left, TTypeQualifierList qualis, TSBracketRight bracket_right) {
		addChild(dirdecl);
		addChild(bracket_left);
		addChild(qualis);
		addChild(bracket_right);
	}

	public TDirectDeclaratorA(TDirectDeclarator dirdecl, TSBracketLeft bracket_left, TAssignmentExpression aexpr, TSBracketRight bracket_right) {
		addChild(dirdecl);
		addChild(bracket_left);
		addChild(aexpr);
		addChild(bracket_right);
	}

	@Override
	public TSIdentifier getNameIdentifier() {
		return getDirDeclarator().getNameIdentifier();
	}
	@Override
	public void setNameIdentifier(TSIdentifier name) {
		getDirDeclarator().setNameIdentifier(name);
	}
	
	@Override
	public TDirectAbstractDeclarator getTypeDeclarator() {
		LinkedList<TreeNode> children = new LinkedList<TreeNode>();
		TDirectAbstractDeclarator child = getDirDeclarator().getTypeDeclarator();
		if (child != null)
			children.add(child);
		children.addAll(getChildren().subList(1, getNChildren()));
		return TDirectAbstractDeclaratorA.from(children);
	}

	@Override
	public CommonDirectDeclarator getNameDeclarator() {
		return getDirDeclarator().getNameDeclarator();
	}

	private TDirectDeclaratorA(LinkedList<TreeNode> children) {
		addChildren(children);
	}
	public static TDirectDeclaratorA from(LinkedList<TreeNode> children) {
		return new TDirectDeclaratorA(children);
	}

	@Override
	public TDirectDeclarator translateGenerator(TParameterDeclaration pdecl) {
		LinkedList<TreeNode> children = new LinkedList<TreeNode>();
		children.add(getDirDeclarator().translateGenerator(pdecl));
		children.addAll(getChildren().subList(1, getNChildren()));
		return TDirectDeclaratorA.from(children);
	}

	@Override
	public TDirectAbstractDeclarator toAbstractDeclarator() {
		LinkedList<TreeNode> children = new LinkedList<TreeNode>();
		TDirectAbstractDeclarator adecl = getDirDeclarator().toAbstractDeclarator();
		if (adecl != null)
			children.add(adecl);
		children.addAll(getChildren().subList(1, getNChildren()));
		return TDirectAbstractDeclaratorA.from(children);
	}

	@Override
	public DefaultTreeNode getParams() {
		return null;
	}

}
