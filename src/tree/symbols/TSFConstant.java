package tree.symbols;

import tree.DefaultTreeNodeSymbol;

public class TSFConstant extends DefaultTreeNodeSymbol {

	public static int id = F_CONSTANT;
	public static String text = "float constant";

	public TSFConstant() {
		super(text, id, null);
	}

	public TSFConstant(String value) {
		super(text, id, value);
	}

}
