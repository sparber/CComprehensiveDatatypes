package tree.symbols;

import tree.DefaultTreeNodeSymbol;

public class TSEnumerationConstant extends DefaultTreeNodeSymbol {

	public static int id = ENUMERATION_CONSTANT;
	public static String text = "enumeration constant";

	public TSEnumerationConstant() {
		super(text, id, null);
	}

	public TSEnumerationConstant(String value) {
		super(text, id, value);
	}

}
