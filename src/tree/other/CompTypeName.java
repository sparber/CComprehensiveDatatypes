package tree.other;

import tree.DefaultTreeNodeSymbol;
import tree.DefaultVisitor;
import tree.declarations.TParameterDeclaration;
import errors.CXInternalError;

public class CompTypeName {

	public class SafeTypeGenerator extends DefaultVisitor {
		
		private StringBuilder sb = new StringBuilder();
		
		@Override
		public DefaultTreeNodeSymbol visitDefaultTreeNodeSymbol(DefaultTreeNodeSymbol symbol) {
			String text = symbol.value != null ? symbol.value : symbol.name;
			if (text.matches("^[a-zA-Z_0-9]*$"))
				sb.append(text);
			else
				sb.append('$');
			sb.append('_');
			return null;
		}
		
		public String toString() {
			return sb.toString();
		}
		
	}

	private TParameterDeclaration pdecl;
	private String tname;
	
	public CompTypeName(TParameterDeclaration pdecl) {
		if (pdecl.hasDeclarator()) {
			throw new CXInternalError("requesting safe type name of declarator instead of abstract declarator");
		}
		this.pdecl = pdecl;
		
		TreeRoot root = new TreeRoot(pdecl);
		SafeTypeGenerator stgen = new SafeTypeGenerator();
		try {
			root.accept(stgen);
		} catch (Throwable e) {
			throw new CXInternalError("something went wrong in safe type name generation", e);
		}
		
		this.tname = stgen.toString();
	}
	
	public TParameterDeclaration getTypeName() {
		return pdecl;
	}
	
	public String getSafeTypeName() {
		return tname;
	}
	
	public boolean equals(CompTypeName other) {
		return tname.equals(other.tname);
	}
	
}
