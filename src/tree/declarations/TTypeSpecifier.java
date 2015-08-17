package tree.declarations;

import tree.DefaultTreeNode;
import tree.symbols.TSBool;
import tree.symbols.TSChar;
import tree.symbols.TSComplex;
import tree.symbols.TSDouble;
import tree.symbols.TSFloat;
import tree.symbols.TSImaginary;
import tree.symbols.TSInt;
import tree.symbols.TSLong;
import tree.symbols.TSShort;
import tree.symbols.TSSigned;
import tree.symbols.TSTypedefName;
import tree.symbols.TSUnsigned;
import tree.symbols.TSVoid;

public class TTypeSpecifier extends DefaultTreeNode {

	public TTypeSpecifier(TTypeSpecifier node) {
		super(node);
	}
	
	public TTypeSpecifier(TSVoid spec) {
		addChild(spec);
	}
	
	public boolean isVoid() {
		return getChild(0) instanceof TSVoid;
	}
	
	public TTypeSpecifier(TSChar spec) {
		addChild(spec);
	}
	
	public TTypeSpecifier(TSShort spec) {
		addChild(spec);
	}
	
	public TTypeSpecifier(TSInt spec) {
		addChild(spec);
	}
	
	public TTypeSpecifier(TSLong spec) {
		addChild(spec);
	}
	
	public TTypeSpecifier(TSFloat spec) {
		addChild(spec);
	}
	
	public TTypeSpecifier(TSDouble spec) {
		addChild(spec);
	}
	
	public TTypeSpecifier(TSSigned spec) {
		addChild(spec);
	}
	
	public TTypeSpecifier(TSUnsigned spec) {
		addChild(spec);
	}
	
	public TTypeSpecifier(TSBool spec) {
		addChild(spec);
	}
	
	public TTypeSpecifier(TSComplex spec) {
		addChild(spec);
	}
	
	public TTypeSpecifier(TSImaginary spec) {
		addChild(spec);
	}
	
	public TTypeSpecifier(TAtomicTypeSpecifier spec) {
		addChild(spec);
	}
	
	public TTypeSpecifier(TStructOrUnionSpecifier spec) {
		addChild(spec);
	}
	
	public TTypeSpecifier(TEnumSpecifier spec) {
		addChild(spec);
	}
	
	public TTypeSpecifier(TSTypedefName spec) {
		addChild(spec);
	}
	
	public TTypeSpecifier(TMacroTypeSpecifier spec) {
		addChild(spec);
	}
}
