package tree.symbols;

import tree.DefaultTreeNodeSymbol;

public class TSSwitch extends DefaultTreeNodeSymbol {
	
	public static int id = SWITCH;
	public static String text = "switch";

	public TSSwitch() {
		super(text, id);
	}

}
