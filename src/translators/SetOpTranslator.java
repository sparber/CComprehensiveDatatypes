package translators;

import generation.Medium;
import tree.expressions.TAssignmentExpression;
import tree.expressions.TPostfixExpression;
import tree.expressions.TSetExpression;
import tree.expressions.TSetExpressionSO;
import tree.expressions.TSetExpressionU;
import tree.expressions.TUnaryExpression;

public class SetOpTranslator extends DefaultTranslator {

	public SetOpTranslator(Medium medium) {
		super(medium, new String[] {});
	}

	@Override
	public TSetExpression visitAfterTSetExpressionSO(TSetExpressionSO node) {

		TSetExpression newNode;
		
		if (node.hasUExpression2()) {
		
			TAssignmentExpression[] aelist = new TAssignmentExpression[]{
					TAssignmentExpression.from(node.getUExpression1()),
					TAssignmentExpression.from(node.getUExpression2())
			};
	
			newNode = new TSetExpressionU(
					new TUnaryExpression(
							TPostfixExpression.from("cx_range", aelist)
					)
			);
			
		} else {
			
			TAssignmentExpression[] aelist = new TAssignmentExpression[]{
					TAssignmentExpression.from(node.getUExpression1()),
			};
	
			newNode = new TSetExpressionU(
					new TUnaryExpression(
							TPostfixExpression.from("cx_inf_range", aelist)
					)
			);
			
		}
		
		newNode.setLeft(node.getLeft());
		newNode.setRight(node.getRight());
		return newNode;
	}
	
}
