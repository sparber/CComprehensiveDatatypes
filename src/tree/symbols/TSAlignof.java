package tree.symbols;

import tree.DefaultTreeNodeSymbol;

public class TSAlignof extends DefaultTreeNodeSymbol {
	
	public static int id = ALIGNOF;
	public static String text = "_Alignof";
	
	public TSAlignof() {
		super(text, id);
	}

}
