package tree.symbols;

import tree.DefaultTreeNodeSymbol;

public class TSEnum extends DefaultTreeNodeSymbol {

	public static int id = ENUM;
	public static String text = "enum";
	
	public TSEnum() {
		super(text, id);
	}

}
