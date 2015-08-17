package tree.symbols;

import tree.DefaultTreeNodeSymbol;

public class TSGenerator extends DefaultTreeNodeSymbol {
	
	public static int id = GENERATOR;
	public static String text = "generator";
	
	public TSGenerator() {
		super(text, id);
	}

}
