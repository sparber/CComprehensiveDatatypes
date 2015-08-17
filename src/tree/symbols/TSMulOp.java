package tree.symbols;

import tree.DefaultTreeNodeSymbol;

public class TSMulOp extends DefaultTreeNodeSymbol {

	public static int id = MUL_OP;
	public static String text = "*";

	public TSMulOp() {
		super(text, id);
	}

}
