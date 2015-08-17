package tree.symbols;

import tree.DefaultTreeNodeSymbol;

public class TSStruct extends DefaultTreeNodeSymbol {

	public static int id = STRUCT;
	public static String text = "struct";

	public TSStruct() {
		super(text, id);
	}

}
