package tree.symbols;

import tree.DefaultTreeNodeSymbol;

public class TSStringLiteral extends DefaultTreeNodeSymbol {

	public static int id = STRING_LITERAL;
	public static String text = "string literal";

	public TSStringLiteral() {
		super(text, id, null);
	}

	public TSStringLiteral(String value) {
		super(text, id, value);
	}

}
