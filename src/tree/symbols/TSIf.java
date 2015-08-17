package tree.symbols;

import tree.DefaultTreeNodeSymbol;

public class TSIf extends DefaultTreeNodeSymbol {

	public static int id = IF;
	public static String text = "if";

	public TSIf() {
		super(text, id);
	}

}
