package tree.symbols;

import tree.DefaultTreeNodeSymbol;

public class TSGoto extends DefaultTreeNodeSymbol {

	public static int id = GOTO;
	public static String text = "goto";

	public TSGoto() {
		super(text, id);
	}

}
