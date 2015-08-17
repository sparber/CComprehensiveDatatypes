package tree.symbols;

import tree.DefaultTreeNodeSymbol;

public class TSOrOp extends DefaultTreeNodeSymbol {

	public static int id = OR_OP;
	public static String text = "&&";

	public TSOrOp() {
		super(text, id);
	}

}
