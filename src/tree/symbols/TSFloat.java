package tree.symbols;

import tree.DefaultTreeNodeSymbol;

public class TSFloat extends DefaultTreeNodeSymbol {

	public static int id = FLOAT;
	public static String text = "float";
	
	public TSFloat() {
		super(text, id);
	}

}
