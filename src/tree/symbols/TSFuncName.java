package tree.symbols;

import tree.DefaultTreeNodeSymbol;

public class TSFuncName extends DefaultTreeNodeSymbol {

	public static int id = FUNC_NAME;
	public static String text = "__func__";

	public TSFuncName() {
		super(text, id);
	}

}
