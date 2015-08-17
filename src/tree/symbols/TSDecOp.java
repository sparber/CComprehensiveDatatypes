package tree.symbols;

import tree.DefaultTreeNodeSymbol;

public class TSDecOp extends DefaultTreeNodeSymbol {

	public static int id = DEC_OP;
	public static String text = "--";

	public TSDecOp() {
		super(text, id);
	}

}
