package tree.symbols;

import tree.DefaultTreeNodeSymbol;

public class TSAddOp extends DefaultTreeNodeSymbol {
	
	public static int id = ADD_OP;
	public static String text = "+";
	
	public TSAddOp() {
		super(text, id);
	}

}
