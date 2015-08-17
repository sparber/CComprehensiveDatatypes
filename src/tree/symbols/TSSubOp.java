package tree.symbols;

import tree.DefaultTreeNodeSymbol;

public class TSSubOp extends DefaultTreeNodeSymbol {

	public static int id = SUB_OP;
	public static String text = "-";
	
	public TSSubOp() {
		super(text, id);
	}

}
