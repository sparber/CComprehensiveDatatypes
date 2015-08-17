package tree.symbols;

import tree.DefaultTreeNodeSymbol;

public class TSVoid extends DefaultTreeNodeSymbol {

	public static int id = VOID;
	public static String text = "void";

	public TSVoid() {
		super(text, id);
	}

}
