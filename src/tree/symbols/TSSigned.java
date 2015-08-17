package tree.symbols;

import tree.DefaultTreeNodeSymbol;

public class TSSigned extends DefaultTreeNodeSymbol {

	public static int id = SIGNED;
	public static String text = "signed";

	public TSSigned() {
		super(text, id);
	}

}
