package tree.symbols;

import tree.DefaultTreeNodeSymbol;

public class TSRegister extends DefaultTreeNodeSymbol {

	public static int id = REGISTER;
	public static String text = "register";

	public TSRegister() {
		super(text, id);
	}

}
