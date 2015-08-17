package tree.symbols;

import tree.DefaultTreeNodeSymbol;

public class TSComplex extends DefaultTreeNodeSymbol {

	public static int id = COMPLEX;
	public static String text = "_Complex";

	public TSComplex() {
		super(text, id);
	}
}
