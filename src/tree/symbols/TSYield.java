package tree.symbols;

import tree.DefaultTreeNodeSymbol;

public class TSYield extends DefaultTreeNodeSymbol {
	
	public static int id = YIELD;
	public static String text = "yield";

	public TSYield() {
		super(text, id);
	}

}
