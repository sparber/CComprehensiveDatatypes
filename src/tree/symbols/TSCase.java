package tree.symbols;

import tree.DefaultTreeNodeSymbol;

public class TSCase extends DefaultTreeNodeSymbol {

	public static int id = CASE;
	public static String text = "case";

	public TSCase() {
		super(text, id);
	}

}
