package tree.symbols;

import tree.DefaultTreeNodeSymbol;

public class TSBracketLeft extends DefaultTreeNodeSymbol {

	public static int id = BRACKET_LEFT;
	public static String text = "[";

	public TSBracketLeft() {
		super(text, id);
	}

}
