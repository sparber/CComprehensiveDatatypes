package tree.symbols;

import tree.DefaultTreeNodeSymbol;

public class TSBraceRight extends DefaultTreeNodeSymbol {

	public static int id = BRACE_RIGHT;
	public static String text = "}";

	public TSBraceRight() {
		super(text, id);
	}

}
