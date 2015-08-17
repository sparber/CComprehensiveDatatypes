package tree.expressions;

import tree.DefaultTreeNode;
import tree.DefaultTreeNodeSymbol;
import tree.symbols.TSAddOp;
import tree.symbols.TSBandOp;
import tree.symbols.TSBorOp;
import tree.symbols.TSExcl;
import tree.symbols.TSMulOp;
import tree.symbols.TSSubOp;
import tree.symbols.TSTilde;

public class TUnaryOperator extends DefaultTreeNode {

	public TUnaryOperator(TUnaryOperator node) {
		super(node);
	}
	
	public TUnaryOperator(TSBandOp band_op) {
		addChild(band_op);
	}

	public TUnaryOperator(TSMulOp mul_op) {
		addChild(mul_op);
	}

	public TUnaryOperator(TSAddOp add_op) {
		addChild(add_op);
	}

	public TUnaryOperator(TSSubOp sub_op) {
		addChild(sub_op);
	}

	public TUnaryOperator(TSTilde tilde_op) {
		addChild(tilde_op);
	}

	public TUnaryOperator(TSExcl excl_op) {
		addChild(excl_op);
	}

	public TUnaryOperator(TSBorOp bor_op) {
		addChild(bor_op);
	}

	public DefaultTreeNodeSymbol getOperator() {
		return (DefaultTreeNodeSymbol) getChild(0);
	}
	
}
