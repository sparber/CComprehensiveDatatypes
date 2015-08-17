package tree.symbols;

import tree.DefaultTreeNodeSymbol;

public class TSSizeof extends DefaultTreeNodeSymbol {

	public static int id = SIZEOF;
	public static String text = "sizeof";

	public TSSizeof() {
		super(text, id);
	}

}
