package tree.symbols;

import tree.DefaultTreeNodeSymbol;

public class TSConst extends DefaultTreeNodeSymbol {

	public static int id = CONST;
	public static String text = "const";
	
	public TSConst() {
		super(text, id);
	}

}
