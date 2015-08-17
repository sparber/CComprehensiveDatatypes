package tree.symbols;

import tree.DefaultTreeNodeSymbol;

public class TSModAssign extends DefaultTreeNodeSymbol {

	public static int id = MOD_ASSIGN;
	public static String text = "%=";

	public TSModAssign() {
		super(text, id);
	}

}
