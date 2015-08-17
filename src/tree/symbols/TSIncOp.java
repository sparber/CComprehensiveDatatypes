package tree.symbols;

import tree.DefaultTreeNodeSymbol;

public class TSIncOp extends DefaultTreeNodeSymbol {

	public static int id = INC_OP;
	public static String text = "++";

	public TSIncOp() {
		super(text, id);
	}

}
