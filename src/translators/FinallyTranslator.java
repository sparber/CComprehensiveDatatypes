package translators;

import java.util.LinkedList;

import generation.Medium;
import tree.statements.TBlockItem;
import tree.statements.TBlockItemList;
import tree.statements.TBlockItemS;
import tree.statements.TCompoundStatement;

public class FinallyTranslator extends DefaultTranslator {

	public FinallyTranslator(Medium medium) {
		super(medium, new String[] {SplitOpTranslator.class.getSimpleName()});
	}
	
	@Override
	public TCompoundStatement visitAfterTCompoundStatement(TCompoundStatement node) {
		if (!node.hasFinallyStatement())
			return null;
		
		LinkedList<TBlockItem> items = node.getItems().getList();
		items.add(new TBlockItemS(node.getFinallyStatement().getStatement()));
		return new TCompoundStatement(node.getBraceLeft(), TBlockItemList.from(items),	node.getBraceRight());
	}

}
