package tree.translation_unit;


public class TExternalDeclarationFD extends TExternalDeclaration {
	
	public TFunctionDefinition getFunctionDefinition() {
		return (TFunctionDefinition) getChild(0);
	}
	
	public TExternalDeclarationFD(TExternalDeclarationFD node) {
		super(node);
	}
	
	public TExternalDeclarationFD(TFunctionDefinition def) {
		addChild(def);
	}
	
}
