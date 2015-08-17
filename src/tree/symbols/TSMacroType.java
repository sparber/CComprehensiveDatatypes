package tree.symbols;

import tree.DefaultTreeNodeSymbol;

public class TSMacroType extends DefaultTreeNodeSymbol {

	public static int id = MACRO_TYPE;
	public static String text = "_macro_type";
	
	public TSMacroType() {
		super(text, id);
	}
}
