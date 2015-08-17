package tree.symbols;

import tree.DefaultTreeNodeSymbol;

public class TSContinue extends DefaultTreeNodeSymbol {
	
	public static int id = CONTINUE;
	public static String text = "continue";

	public TSContinue() {
		super(text, id);
	}

}
