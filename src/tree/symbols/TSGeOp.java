package tree.symbols;

import tree.DefaultTreeNodeSymbol;

public class TSGeOp extends DefaultTreeNodeSymbol {

	public static int id = GE_OP;
	public static String text = ">=";

	public TSGeOp() {
		super(text, id);
	}

}
