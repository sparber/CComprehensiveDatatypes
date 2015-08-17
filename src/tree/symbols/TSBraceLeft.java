package tree.symbols;

import tree.DefaultTreeNodeSymbol;

public class TSBraceLeft extends DefaultTreeNodeSymbol {

	public static int id = BRACE_LEFT;
	public static String text = "{";

	public TSBraceLeft() {
		super(text, id);
	}

}
