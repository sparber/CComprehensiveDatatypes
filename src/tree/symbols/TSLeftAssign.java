package tree.symbols;

import tree.DefaultTreeNodeSymbol;

public class TSLeftAssign extends DefaultTreeNodeSymbol {

	public static int id = LEFT_ASSIGN;
	public static String text = "<<=";

	public TSLeftAssign() {
		super(text, id);
	}

}
