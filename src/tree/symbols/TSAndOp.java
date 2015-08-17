package tree.symbols;

import tree.DefaultTreeNodeSymbol;

public class TSAndOp extends DefaultTreeNodeSymbol {

	public static int id = AND_OP;
	public static String text = "&&";

	public TSAndOp() {
		super(text, id);
	}

}
