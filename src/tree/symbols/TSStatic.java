package tree.symbols;

import tree.DefaultTreeNodeSymbol;

public class TSStatic extends DefaultTreeNodeSymbol {

	public static int id = STATIC;
	public static String text = "static";

	public TSStatic() {
		super(text, id);
	}

}
