package tree.symbols;

import tree.DefaultTreeNodeSymbol;

public class TSTypedef extends DefaultTreeNodeSymbol {

	public static int id = TYPEDEF;
	public static String text = "typedef";

	public TSTypedef() {
		super(text, id);
	}

}
