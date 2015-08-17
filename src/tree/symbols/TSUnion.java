package tree.symbols;

import tree.DefaultTreeNodeSymbol;

public class TSUnion extends DefaultTreeNodeSymbol {

	public static int id = UNION;
	public static String text = "union";

	public TSUnion() {
		super(text, id);
	}

}
