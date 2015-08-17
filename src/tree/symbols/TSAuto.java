package tree.symbols;

import tree.DefaultTreeNodeSymbol;

public class TSAuto extends DefaultTreeNodeSymbol {

	public static int id = AUTO;
	public static String text = "auto";
	
	public TSAuto() {
		super(text, id);
	}

}
