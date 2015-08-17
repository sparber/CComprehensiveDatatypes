package tree.symbols;

import tree.DefaultTreeNodeSymbol;

public class TSLeftOp extends DefaultTreeNodeSymbol {

	public static int id = LEFT_OP;
	public static String text = "<<";

	public TSLeftOp() {
		super(text, id);
	}

}
