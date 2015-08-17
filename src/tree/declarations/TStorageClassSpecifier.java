package tree.declarations;

import tree.DefaultTreeNode;
import tree.symbols.TSAuto;
import tree.symbols.TSExtern;
import tree.symbols.TSRegister;
import tree.symbols.TSStatic;
import tree.symbols.TSThreadLocal;
import tree.symbols.TSTypedef;

public class TStorageClassSpecifier extends DefaultTreeNode {

	public TStorageClassSpecifier(TStorageClassSpecifier node) {
		super(node);
	}
	
	public TStorageClassSpecifier(TSTypedef typedef) {
		addChild(typedef);
	}
	
	public TStorageClassSpecifier(TSExtern extern) {
		addChild(extern);
	}
	
	public TStorageClassSpecifier(TSStatic stat) {
		addChild(stat);
	}
	
	public TStorageClassSpecifier(TSThreadLocal thread_local) {
		addChild(thread_local);
	}
	
	public TStorageClassSpecifier(TSAuto auto) {
		addChild(auto);
	}
	
	public TStorageClassSpecifier(TSRegister register) {
		addChild(register);
	}
	
}
