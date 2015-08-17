package tree.symbols;

import tree.DefaultTreeNodeSymbol;

public class TSXorOp extends DefaultTreeNodeSymbol {

	public static int id = XOR_OP;
	public static String text = "^";

	public TSXorOp() {
		super(text, id);
	}

}
