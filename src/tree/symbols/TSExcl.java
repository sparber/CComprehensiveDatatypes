package tree.symbols;

import tree.DefaultTreeNodeSymbol;

public class TSExcl extends DefaultTreeNodeSymbol {

	public static int id = EXCL;
	public static String text = "!";

	public TSExcl() {
		super(text, id);
	}

}
