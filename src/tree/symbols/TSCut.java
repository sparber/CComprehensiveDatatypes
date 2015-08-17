package tree.symbols;

import tree.DefaultTreeNodeSymbol;

public class TSCut extends DefaultTreeNodeSymbol {

	public static int id = CUT;
	public static String text = "cut";
	
	public TSCut() {
		super(text, id);
	}

}
