package tree.symbols;

import tree.DefaultTreeNodeSymbol;

public class TSRightAssign extends DefaultTreeNodeSymbol {

	public static int id = RIGHT_ASSIGN;
	public static String text = ">>=";

	public TSRightAssign() {
		super(text, id);
	}

}
