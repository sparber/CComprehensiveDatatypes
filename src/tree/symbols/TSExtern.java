package tree.symbols;

import tree.DefaultTreeNodeSymbol;

public class TSExtern extends DefaultTreeNodeSymbol {

	public static int id = EXTERN;
	public static String text = "extern";

	public TSExtern() {
		super(text, id);
	}

}
