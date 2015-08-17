package tree.symbols;

import tree.DefaultTreeNodeSymbol;

public class TSDivAssign extends DefaultTreeNodeSymbol {

	public static int id = DIV_ASSIGN;
	public static String text = "/=";

	public TSDivAssign() {
		super(text, id);
	}

}
