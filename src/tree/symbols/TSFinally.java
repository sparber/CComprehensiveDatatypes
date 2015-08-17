package tree.symbols;

import tree.DefaultTreeNodeSymbol;

public class TSFinally extends DefaultTreeNodeSymbol {

	public static int id = FINALLY;
	public static String text = "finally";

	public TSFinally() {
		super(text, id);
	}

}
