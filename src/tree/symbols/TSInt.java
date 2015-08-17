package tree.symbols;

import tree.DefaultTreeNodeSymbol;

public class TSInt extends DefaultTreeNodeSymbol {

	public static int id = INT;
	public static String text = "int";
	
	public TSInt() {
		super(text, id);
	}

}
