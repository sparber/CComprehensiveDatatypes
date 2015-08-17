package tree.symbols;

import tree.DefaultTreeNodeSymbol;

public class TSElse extends DefaultTreeNodeSymbol {
	
	public static int id = ELSE;
	public static String text = "else";

	public TSElse() {
		super(text, id);
	}

}
