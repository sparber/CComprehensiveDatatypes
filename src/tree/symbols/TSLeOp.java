package tree.symbols;

import tree.DefaultTreeNodeSymbol;

public class TSLeOp extends DefaultTreeNodeSymbol {

	public static int id = LE_OP;
	public static String text = "<=";

	public TSLeOp() {
		super(text, id);
	}

}
