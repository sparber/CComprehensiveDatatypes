package tree.symbols;

import tree.DefaultTreeNodeSymbol;

public class TSRightOp extends DefaultTreeNodeSymbol {

	public static int id = RIGHT_OP;
	public static String text = ">>";

	public TSRightOp() {
		super(text, id);
	}

}
