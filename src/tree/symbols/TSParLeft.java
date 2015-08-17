package tree.symbols;

import tree.DefaultTreeNodeSymbol;

public class TSParLeft extends DefaultTreeNodeSymbol {

	public static int id = PAR_LEFT;
	public static String text = "(";

	public TSParLeft() {
		super(text, id);
	}

}
