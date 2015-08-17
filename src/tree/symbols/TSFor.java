package tree.symbols;

import tree.DefaultTreeNodeSymbol;

public class TSFor extends DefaultTreeNodeSymbol {

	public static int id = FOR;
	public static String text = "for";

	public TSFor() {
		super(text, id);
	}

}
