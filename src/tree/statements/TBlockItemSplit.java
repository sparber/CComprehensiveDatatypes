package tree.statements;


public class TBlockItemSplit extends TBlockItemS {

	private int counter;

	public TBlockItemSplit(TBlockItemSplit node) {
		super(node);
		this.counter = node.counter;
	}
	
	public TBlockItemSplit(int counter, TStatement statement) {
		super(statement);
		this.counter = counter;
	}
	
	public int getCounter() {
		return counter;
	}
	
}
