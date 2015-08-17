package tree.symbols;

import tree.DefaultTreeNodeSymbol;

public class TSXorAssign extends DefaultTreeNodeSymbol {

	public static int id = XOR_ASSIGN;
	public static String text = "^=";

	public TSXorAssign() {
		super(text, id);
	}

}
