package tree.symbols;

import tree.DefaultTreeNodeSymbol;

public class TSSemicolon extends DefaultTreeNodeSymbol {

	public static int id = SEMICOLON;
	public static String text = ";";

	public TSSemicolon() {
		super(text, id);
	}

}
