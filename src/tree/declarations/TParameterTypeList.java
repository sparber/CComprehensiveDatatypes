package tree.declarations;

import tree.DefaultTreeNode;
import tree.symbols.TSComma;
import tree.symbols.TSEllipsis;

public class TParameterTypeList extends DefaultTreeNode {

	public TParameterTypeList(TParameterTypeList node) {
		super(node);
	}
	
	public boolean hasVariadicArguments() {
		return getNChildren() == 3;
	}
	
	public TParameterList getParameterList() {
		return (TParameterList) getChild(0);
	}
	
	public void setParameterList(TParameterList plist) {
		setChild(0, plist);
	}

	public TParameterTypeList(TParameterList params, TSComma comma, TSEllipsis ellipsis) {
		addChild(params);
		addChild(comma);
		addChild(ellipsis);
	}

	public TParameterTypeList(TParameterList params) {
		addChild(params);
	}

}
