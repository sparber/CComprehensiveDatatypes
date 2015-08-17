package tree.symbols;

import tree.DefaultTreeNodeSymbol;

public class TSDivOp extends DefaultTreeNodeSymbol {

	public static int id = DIV_OP;
	public static String text = "/";

	public TSDivOp() {
		super(text, id);
	}

}
