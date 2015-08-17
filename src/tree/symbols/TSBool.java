package tree.symbols;

import tree.DefaultTreeNodeSymbol;

public class TSBool extends DefaultTreeNodeSymbol {

	public static int id = BOOL;
	public static String text = "_Bool";

	public TSBool() {
		super(text, id);
	}

}
