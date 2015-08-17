package tree.symbols;

import tree.DefaultTreeNodeSymbol;

public class TSRestrict extends DefaultTreeNodeSymbol {

	public static int id = RESTRICT;
	public static String text = "restrict";

	public TSRestrict() {
		super(text, id);
	}

}
