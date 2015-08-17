package tree.symbols;

import tree.DefaultTreeNodeSymbol;

public class TSOrAssign extends DefaultTreeNodeSymbol {

	public static int id = OR_ASSIGN;
	public static String text = "|=";

	public TSOrAssign() {
		super(text, id);
	}

}
