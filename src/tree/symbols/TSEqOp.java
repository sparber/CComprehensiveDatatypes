package tree.symbols;

import tree.DefaultTreeNodeSymbol;

public class TSEqOp extends DefaultTreeNodeSymbol {

	public static int id = EQ_OP;
	public static String text = "==";

	public TSEqOp() {
		super(text, id);
	}

}
