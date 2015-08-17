package tree.symbols;

import tree.DefaultTreeNodeSymbol;

public class TSDefault extends DefaultTreeNodeSymbol {

	public static int id = DEFAULT;
	public static String text = "default";

	public TSDefault() {
		super(text, id);
	}

}
