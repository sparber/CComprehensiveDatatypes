package tree.symbols;

import tree.DefaultTreeNodeSymbol;

public class TSWhile extends DefaultTreeNodeSymbol {

	public static int id = WHILE;
	public static String text = "while";

	public TSWhile() {
		super(text, id);
	}

}
