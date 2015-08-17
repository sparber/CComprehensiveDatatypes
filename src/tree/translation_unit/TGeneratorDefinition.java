package tree.translation_unit;

public class TGeneratorDefinition extends TFunctionDefinition {

	public TGeneratorDefinition(TGeneratorDefinition node) {
		super(node);
	}
	
	public TGeneratorDefinition(TFunctionDefinition fdef) {
		addChild(fdef);
	}
	
	public TFunctionDefinition getFunctionDefinition() {
		return (TFunctionDefinition) getChild(0);
	}
	
}
