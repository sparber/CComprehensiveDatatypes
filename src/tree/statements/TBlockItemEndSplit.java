package tree.statements;



public class TBlockItemEndSplit extends TBlockItemS {

	private int counter;

	public TBlockItemEndSplit(TBlockItemEndSplit node) {
		super(node);
		this.counter = node.counter;
	}
	
	public TBlockItemEndSplit(int counter, TStatement statement) {
		super(statement);
		this.counter = counter;
	}
	
	public int getCounter() {
		return counter;
	}

}
