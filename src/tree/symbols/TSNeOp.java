package tree.symbols;

import tree.DefaultTreeNodeSymbol;

public class TSNeOp extends DefaultTreeNodeSymbol {

	public static int id = NE_OP;
	public static String text = "!=";

	public TSNeOp() {
		super(text, id);
	}

}
