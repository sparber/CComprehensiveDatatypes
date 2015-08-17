package tree.declarations;

import java.util.LinkedList;
import java.util.List;

import tree.DefaultTreeNode;
import tree.TreeNode;
import tree.TreeNodeList;
import tree.symbols.TSCompOp;
import tree.symbols.TSMulOp;

public class TPointerComprehension extends DefaultTreeNode implements TreeNodeList<TreeNode> {

	public TPointerComprehension(TPointerComprehension node) {
		super(node);
	}
	
	public LinkedList<TreeNode> getList() {
		return getChildren();
	}

	public TPointerComprehension(TSMulOp mul_op, TTypeQualifierList qualis, TPointerComprehension pointer) {
		addChild(mul_op);
		addChildrenOf(qualis);
		addChildrenOf(pointer);
	}
	
	public TPointerComprehension(TSMulOp mul_op, TTypeQualifierList qualis) {
		addChild(mul_op);
		addChildrenOf(qualis);
	}

	public TPointerComprehension(TSMulOp mul_op, TPointerComprehension pointer) {
		addChild(mul_op);
		addChildrenOf(pointer);
	}

	public TPointerComprehension(TSMulOp mul_op) {
		addChild(mul_op);
	}

	public TPointerComprehension(TSCompOp comp_op, TTypeQualifierList qualis, TPointerComprehension pointer) {
		addChild(comp_op);
		addChildrenOf(qualis);
		addChildrenOf(pointer);
	}
	
	public TPointerComprehension(TSCompOp comp_op, TTypeQualifierList qualis) {
		addChild(comp_op);
		addChildrenOf(qualis);
	}

	public TPointerComprehension(TSCompOp comp_op, TPointerComprehension pointer) {
		addChild(comp_op);
		addChildrenOf(pointer);
	}

	public TPointerComprehension(TSCompOp comp_op) {
		addChild(comp_op);
	}
	
	private TPointerComprehension(List<TreeNode> items) {
		addChildren(new LinkedList<TreeNode>(items));
	}

	public static TPointerComprehension from(List<TreeNode> items) {
		if (items.isEmpty())
			return null;
		return new TPointerComprehension(items);
	}

}
