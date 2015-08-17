package tree.symbols;

import tree.DefaultTreeNodeSymbol;

public class TSBandOp extends DefaultTreeNodeSymbol {

	public static int id = BAND_OP;
	public static String text = "&";

	public TSBandOp() {
		super(text, id);
	}

}
