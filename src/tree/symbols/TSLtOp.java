package tree.symbols;

import tree.DefaultTreeNodeSymbol;

public class TSLtOp extends DefaultTreeNodeSymbol {

	public static int id = LT_OP;
	public static String text = "<";

	public TSLtOp() {
		super(text, id);
	}

}
