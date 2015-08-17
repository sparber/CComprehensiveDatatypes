package tree.symbols;

import tree.DefaultTreeNodeSymbol;

public class TSBreak extends DefaultTreeNodeSymbol {
	
	public static int id = BREAK;
	public static String text = "break";

	public TSBreak() {
		super(text, id);
	}

}
