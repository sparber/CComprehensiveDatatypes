package tree.symbols;

import tree.DefaultTreeNodeSymbol;

public class TSStaticAssert extends DefaultTreeNodeSymbol {

	public static int id = STATIC_ASSERT;
	public static String text = "_Static_assert";
	
	public TSStaticAssert() {
		super(text, id);
	}

}
