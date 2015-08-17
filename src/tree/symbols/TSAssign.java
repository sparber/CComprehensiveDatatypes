package tree.symbols;

import tree.DefaultTreeNodeSymbol;

public class TSAssign extends DefaultTreeNodeSymbol {

	public static int id = ASSIGN;
	public static String text = "=";

	public TSAssign() {
		super(text, id);
	}

}
