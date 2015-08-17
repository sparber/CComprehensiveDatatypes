package tree.expressions;

import java.util.LinkedList;

import errors.CXInternalError;

import tree.DefaultVisitor;
import tree.DefaultTreeNode;
import tree.TreeNode;
import tree.TreeNodeList;
import tree.DefaultTreeNodeSymbol;
import tree.declarations.TParameterDeclaration;
import tree.other.TreeRoot;
import tree.symbols.TSComma;

public class TMacroArgumentList extends DefaultTreeNode implements TreeNodeList<TreeNode> {
	
	public TMacroArgumentList(TMacroArgumentList node) {
		super(node);
	}

	public LinkedList<TreeNode> getList() {
		LinkedList<TreeNode> list = new LinkedList<TreeNode>();
		for (TreeNode child : getChildren()) {
			if (child instanceof TAssignmentExpression || child instanceof TParameterDeclaration)
				list.add(child);
		}
		return list;
	}

	public TMacroArgumentList(TAssignmentExpression node) {
		addChild(node);
	}

	public TMacroArgumentList(TParameterDeclaration node) {
		addChild(node);
	}

	public TMacroArgumentList(TMacroArgumentList nodes, TSComma comma, TAssignmentExpression node) {
		addChildrenOf(nodes);
		addChild(comma);
		addChild(node);
	}

	public TMacroArgumentList(TMacroArgumentList nodes, TSComma comma, TParameterDeclaration node) {
		addChildrenOf(nodes);
		addChild(comma);
		addChild(node);
	}
	
	public static class CommaVisitor extends DefaultVisitor {
		public boolean containsComma = false;
		@Override
		public DefaultTreeNodeSymbol visitTSComma(TSComma comma) {
			containsComma = true;
			return null;
		}
	}
	
	private static boolean containsComma(DefaultTreeNode node) {
		CommaVisitor cvisitor = new CommaVisitor();
		TreeRoot root = new TreeRoot(node);
		try {
			root.accept(cvisitor);
		} catch (Throwable e) {
			throw new CXInternalError("something went wrong in comma detection", e);
		}
		return cvisitor.containsComma;
	}

	public static TMacroArgumentList from(DefaultTreeNode[] args) {
		TMacroArgumentList malist = null;
		for (int i=0; i<args.length; i++) {
			DefaultTreeNode arg = args[i];
			if (arg instanceof TAssignmentExpression) {
				TAssignmentExpression aexpr;
				if (containsComma(arg))
					aexpr = TAssignmentExpression.from(TUnaryExpression.from(new TExpression((TAssignmentExpression) arg)));
				else
					aexpr = (TAssignmentExpression) arg;
				if (malist == null)
					malist = new TMacroArgumentList(aexpr);
				else
					malist = new TMacroArgumentList(malist, new TSComma(), aexpr);
			} else if (arg instanceof TParameterDeclaration) {
				if (malist == null)
					malist = new TMacroArgumentList((TParameterDeclaration) arg);
				else
					malist = new TMacroArgumentList(malist, new TSComma(), (TParameterDeclaration) arg);
			}
		}
		return malist;
	}

}
