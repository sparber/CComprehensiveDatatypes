package tree.symbols;

import tree.DefaultTreeNodeSymbol;

public class TSComma extends DefaultTreeNodeSymbol {

	public static int id = COMMA;
	public static String text = ",";

	public TSComma() {
		super(text, id);
	}

}
