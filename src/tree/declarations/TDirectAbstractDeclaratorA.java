package tree.declarations;

import java.util.LinkedList;

import tree.TreeNode;
import tree.expressions.TAssignmentExpression;
import tree.other.CommonDirectDeclarator;
import tree.symbols.TSBracketLeft;
import tree.symbols.TSBracketRight;
import tree.symbols.TSIdentifier;
import tree.symbols.TSMulOp;
import tree.symbols.TSStatic;

public class TDirectAbstractDeclaratorA extends TDirectAbstractDeclarator {
	
	public TDirectAbstractDeclaratorA(TDirectAbstractDeclaratorA node) {
		super(node);
	}
	
	public boolean hasDirAbsDeclarator() {
		return getChild(0) instanceof TDirectAbstractDeclarator;
	}
	
	public TDirectAbstractDeclarator getDirAbsDeclarator() {
		if (hasDirAbsDeclarator()) {
			return (TDirectAbstractDeclarator) getChild(0);
		} else {
			return null;
		}
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

	public TDirectAbstractDeclaratorA(TSBracketLeft bracket_left, TSBracketRight bracket_right) {
		addChild(bracket_left);
		addChild(bracket_right);
	}
	
	public TDirectAbstractDeclaratorA(TSBracketLeft bracket_left, TSMulOp mul_op, TSBracketRight bracket_right) {
		addChild(bracket_left);
		addChild(mul_op);
		addChild(bracket_right);
	}

	public TDirectAbstractDeclaratorA(TSBracketLeft bracket_left, TSStatic stat, TTypeQualifierList qualis, TAssignmentExpression aexpr, TSBracketRight bracket_right) {
		addChild(bracket_left);
		addChild(stat);
		addChild(qualis);
		addChild(aexpr);
		addChild(bracket_right);
	}

	public TDirectAbstractDeclaratorA(TSBracketLeft bracket_left, TSStatic stat, TAssignmentExpression aexpr, TSBracketRight bracket_right) {
		addChild(bracket_left);
		addChild(stat);
		addChild(aexpr);
		addChild(bracket_right);
	}

	public TDirectAbstractDeclaratorA(TSBracketLeft bracket_left, TTypeQualifierList qualis, TAssignmentExpression aexpr, TSBracketRight bracket_right) {
		addChild(bracket_left);
		addChild(qualis);
		addChild(aexpr);
		addChild(bracket_right);
	}

	public TDirectAbstractDeclaratorA(TSBracketLeft bracket_left, TTypeQualifierList qualis, TSStatic stat, TAssignmentExpression aexpr, TSBracketRight bracket_right) {
		addChild(bracket_left);
		addChild(qualis);
		addChild(stat);
		addChild(aexpr);
		addChild(bracket_right);
	}

	public TDirectAbstractDeclaratorA(TSBracketLeft bracket_left, TTypeQualifierList qualis, TSBracketRight bracket_right) {
		addChild(bracket_left);
		addChild(qualis);
		addChild(bracket_right);
	}

	public TDirectAbstractDeclaratorA(TSBracketLeft bracket_left, TAssignmentExpression aexpr, TSBracketRight bracket_right) {
		addChild(bracket_left);
		addChild(aexpr);
		addChild(bracket_right);
	}

	public TDirectAbstractDeclaratorA(TDirectAbstractDeclarator dirdecl, TSBracketLeft bracket_left, TSBracketRight bracket_right) {
		addChild(dirdecl);
		addChild(bracket_left);
		addChild(bracket_right);
	}
	
	public TDirectAbstractDeclaratorA(TDirectAbstractDeclarator dirdecl, TSBracketLeft bracket_left, TSMulOp mul_op, TSBracketRight bracket_right) {
		addChild(dirdecl);
		addChild(bracket_left);
		addChild(mul_op);
		addChild(bracket_right);
	}

	public TDirectAbstractDeclaratorA(TDirectAbstractDeclarator dirdecl, TSBracketLeft bracket_left, TSStatic stat, TTypeQualifierList qualis, TAssignmentExpression aexpr, TSBracketRight bracket_right) {
		addChild(dirdecl);
		addChild(bracket_left);
		addChild(stat);
		addChild(qualis);
		addChild(aexpr);
		addChild(bracket_right);
	}

	public TDirectAbstractDeclaratorA(TDirectAbstractDeclarator dirdecl, TSBracketLeft bracket_left, TSStatic stat, TAssignmentExpression aexpr, TSBracketRight bracket_right) {
		addChild(dirdecl);
		addChild(bracket_left);
		addChild(stat);
		addChild(aexpr);
		addChild(bracket_right);
	}

	public TDirectAbstractDeclaratorA(TDirectAbstractDeclarator dirdecl, TSBracketLeft bracket_left, TTypeQualifierList qualis, TAssignmentExpression aexpr, TSBracketRight bracket_right) {
		addChild(dirdecl);
		addChild(bracket_left);
		addChild(qualis);
		addChild(aexpr);
		addChild(bracket_right);
	}

	public TDirectAbstractDeclaratorA(TDirectAbstractDeclarator dirdecl, TSBracketLeft bracket_left, TTypeQualifierList qualis, TSStatic stat, TAssignmentExpression aexpr, TSBracketRight bracket_right) {
		addChild(dirdecl);
		addChild(bracket_left);
		addChild(qualis);
		addChild(stat);
		addChild(aexpr);
		addChild(bracket_right);
	}

	public TDirectAbstractDeclaratorA(TDirectAbstractDeclarator dirdecl, TSBracketLeft bracket_left, TTypeQualifierList qualis, TSBracketRight bracket_right) {
		addChild(dirdecl);
		addChild(bracket_left);
		addChild(qualis);
		addChild(bracket_right);
	}

	public TDirectAbstractDeclaratorA(TDirectAbstractDeclarator dirdecl, TSBracketLeft bracket_left, TAssignmentExpression aexpr, TSBracketRight bracket_right) {
		addChild(dirdecl);
		addChild(bracket_left);
		addChild(aexpr);
		addChild(bracket_right);
	}
	
	private TDirectAbstractDeclaratorA() {
	}

	public static TDirectAbstractDeclarator from(LinkedList<TreeNode> children) {
		TDirectAbstractDeclaratorA decl = new TDirectAbstractDeclaratorA();
		decl.addChildren(children);
		return decl;
	}

	@Override
	public TDirectAbstractDeclarator getTypeDeclarator() {
		LinkedList<TreeNode> children = new LinkedList<TreeNode>();
		TDirectAbstractDeclarator child = null;
		if (hasDirAbsDeclarator())
			child = getDirAbsDeclarator().getTypeDeclarator();
		if (child != null)
			children.add(child);
		children.addAll(getChildren().subList(1, getNChildren()));
		return TDirectAbstractDeclaratorA.from(children);
	}

	@Override
	public CommonDirectDeclarator getNameDeclarator() {
		if (hasDirAbsDeclarator())
			return getDirAbsDeclarator().getNameDeclarator();
		else
			return null;
	}

	@Override
	public TDirectAbstractDeclarator translateGenerator(TParameterDeclaration pdecl) {
		LinkedList<TreeNode> children = new LinkedList<TreeNode>();
		if (hasDirAbsDeclarator())
			children.add(getDirAbsDeclarator().translateGenerator(pdecl));
		children.addAll(getChildren().subList(1, getNChildren()));
		return TDirectAbstractDeclaratorA.from(children);
	}

	@Override
	public TDirectDeclarator toDirectDeclarator(TSIdentifier name) {
		LinkedList<TreeNode> children = new LinkedList<TreeNode>();
		TDirectDeclarator ddecl = null;
		if (hasDirAbsDeclarator())
			ddecl = getDirAbsDeclarator().toDirectDeclarator(name);
		else
			ddecl = new TDirectDeclaratorI(name);
		children.add(ddecl);
		children.addAll(getChildren().subList(1, getNChildren()));
		return TDirectDeclaratorA.from(children);
	}

	@Override
	public TParameterTypeList getParams() {
		return null;
	}
	
}
