package tree.symbols;

import tree.DefaultTreeNodeSymbol;

public class TSAddAssign extends DefaultTreeNodeSymbol {

	public static int id = ADD_ASSIGN;
	public static String text = "+=";

	public TSAddAssign() {
		super(text, id);
	}
}
