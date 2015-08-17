package tree.symbols;

import tree.DefaultTreeNodeSymbol;

public class TSModOp extends DefaultTreeNodeSymbol {

	public static int id = MOD_OP;
	public static String text = "%";

	public TSModOp() {
		super(text, id);
	}

}
