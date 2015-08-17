package tree.symbols;

import tree.DefaultTreeNodeSymbol;

public class TSEllipsis extends DefaultTreeNodeSymbol {

	public static int id = ELLIPSIS;
	public static String text = "...";

	public TSEllipsis() {
		super(text, id);
	}

}
