package tree.symbols;

import tree.DefaultTreeNodeSymbol;

public class TSMulAssign extends DefaultTreeNodeSymbol {

	public static int id = MUL_ASSIGN;
	public static String text = "*=";

	public TSMulAssign() {
		super(text, id);
	}

}
