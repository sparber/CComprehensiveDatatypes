package tree.symbols;

import tree.DefaultTreeNodeSymbol;

public class TSSubAssign extends DefaultTreeNodeSymbol {

	public static int id = SUB_ASSIGN;
	public static String text = "-=";

	public TSSubAssign() {
		super(text, id);
	}

}
