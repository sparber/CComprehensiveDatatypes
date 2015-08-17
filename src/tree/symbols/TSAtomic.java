package tree.symbols;

import tree.DefaultTreeNodeSymbol;

public class TSAtomic extends DefaultTreeNodeSymbol {

	public static int id = ATOMIC;
	public static String text = "_Atomic";

	public TSAtomic() {
		super(text, id);
	}

}
