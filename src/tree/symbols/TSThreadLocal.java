package tree.symbols;

import tree.DefaultTreeNodeSymbol;

public class TSThreadLocal extends DefaultTreeNodeSymbol {

	public static int id = THREAD_LOCAL;
	public static String text = "_Thread_local";

	public TSThreadLocal() {
		super(text, id);
	}

}
