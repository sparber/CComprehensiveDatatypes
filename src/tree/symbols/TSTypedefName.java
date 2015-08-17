package tree.symbols;

import tree.DefaultTreeNodeSymbol;

public class TSTypedefName extends DefaultTreeNodeSymbol {

	public static int id = TYPEDEF_NAME;
	public static String text = "typedef name";
	
	public TSTypedefName() {
		super(text, id);
	}

	public TSTypedefName(String value) {
		super(text, id, value);
	}

}
