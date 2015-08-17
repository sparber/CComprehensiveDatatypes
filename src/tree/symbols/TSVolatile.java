package tree.symbols;

import tree.DefaultTreeNodeSymbol;

public class TSVolatile extends DefaultTreeNodeSymbol {

	public static int id = VOLATILE;
	public static String text = "volatile";
	
	public TSVolatile() {
		super(text, id);
	}

}
