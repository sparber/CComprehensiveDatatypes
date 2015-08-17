package tree.symbols;

import tree.DefaultTreeNodeSymbol;

public class TSReturn extends DefaultTreeNodeSymbol {
	
	public static int id = RETURN;
	public static String text = "return";

	public TSReturn() {
		super(text, id);
	}

}
