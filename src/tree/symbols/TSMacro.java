package tree.symbols;

import tree.DefaultTreeNodeSymbol;

public class TSMacro extends DefaultTreeNodeSymbol {

	public static int id = MACRO;
	public static String text = "_macro";
	
	public TSMacro() {
		super(text, id);
	}
}
