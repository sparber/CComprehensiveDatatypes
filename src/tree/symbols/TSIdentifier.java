package tree.symbols;

import tree.DefaultTreeNodeSymbol;

public class TSIdentifier extends DefaultTreeNodeSymbol {

	public static int id = IDENTIFIER;
	public static String text = "identifier";

	public TSIdentifier() {
		super(text, id, null);
	}

	public TSIdentifier(String value) {
		super(text, id, value);
	}

}
