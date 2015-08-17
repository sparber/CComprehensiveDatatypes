package tree.symbols;

import tree.DefaultTreeNodeSymbol;

public class TSPtrOp extends DefaultTreeNodeSymbol {

	public static int id = PTR_OP;
	public static String text = "->";

	public TSPtrOp() {
		super(text, id);
	}

}
