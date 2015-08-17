package tree.symbols;

import tree.DefaultTreeNodeSymbol;

public class TSChar extends DefaultTreeNodeSymbol {

	public static int id = CHAR;
	public static String text = "char";
	
	public TSChar() {
		super(text, id);
	}

}
