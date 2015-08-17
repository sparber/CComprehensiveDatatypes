package tree.symbols;

import tree.DefaultTreeNodeSymbol;

public class TSInline extends DefaultTreeNodeSymbol {

	public static int id = INLINE;
	public static String text = "inline";
	
	public TSInline() {
		super(text, id);
	}

}
