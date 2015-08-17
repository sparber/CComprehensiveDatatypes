package tree.symbols;

import tree.DefaultTreeNodeSymbol;

public class TSAndAssign extends DefaultTreeNodeSymbol {

	public static int id = AND_ASSIGN;
	public static String text = "&=";
	
	public TSAndAssign() {
		super(text, id);
	}

}
