package tree.symbols;

import tree.DefaultTreeNodeSymbol;

public class TSNoreturn extends DefaultTreeNodeSymbol {

	public static int id = NORETURN;
	public static String text = "_Noreturn";
	
	public TSNoreturn() {
		super(text, id);
	}

}
