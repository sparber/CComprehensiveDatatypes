package tree.statements;


public class TBlockItemS extends TBlockItem {

	public TBlockItemS(TBlockItemS node) {
		super(node);
	}
	
	public TBlockItemS(TStatement statement) {
		addChild(statement);
	}

	public TStatement getStatement() {
		return (TStatement) getChild(0);
	}
	
}
