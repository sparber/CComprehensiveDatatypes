package tree.symbols;

import tree.DefaultTreeNodeSymbol;

public class TSGeneric extends DefaultTreeNodeSymbol {

	public static int id = GENERIC;
	public static String text = "_Generic";

	public TSGeneric() {
		super(text, id);
	}

}
