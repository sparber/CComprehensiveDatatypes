package tree.symbols;

import tree.DefaultTreeNodeSymbol;

public class TSIConstant extends DefaultTreeNodeSymbol {

	public static int id = I_CONSTANT;
	public static String text = "integer constant";

	public TSIConstant() {
		super(text, id, null);
	}

	public TSIConstant(String value) {
		super(text, id, value);
	}

}
