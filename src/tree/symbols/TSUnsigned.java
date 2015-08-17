package tree.symbols;

import tree.DefaultTreeNodeSymbol;

public class TSUnsigned extends DefaultTreeNodeSymbol {

	public static int id = UNSIGNED;
	public static String text = "unsigned";

	public TSUnsigned() {
		super(text, id);
	}

}
