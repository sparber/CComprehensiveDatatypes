package translators;

import generation.Medium;

import java.util.LinkedList;

import tree.declarations.TParameterDeclaration;
import tree.declarations.TTypeName;
import tree.expressions.TAssignmentExpression;
import tree.expressions.TCompExpression;
import tree.expressions.TMacroSpecifier;
import tree.expressions.TMacroSpecifierList;
import tree.expressions.TPostfixExpressionP;
import tree.expressions.TPrimaryExpression;
import tree.expressions.TUnaryExpression;
import tree.symbols.TSCompOp;

public class CompOpExpressionTranslator extends DefaultTranslator {

	public CompOpExpressionTranslator(Medium medium) {
		super(medium, new String[] {});
	}
	
	@Override
	public TUnaryExpression visitAfterTUnaryExpression(TUnaryExpression node) {
		if (node.getOperator() instanceof TSCompOp) {
			
			TCompExpression cexpr = node.getCompExpression();
			TTypeName tname = node.getTypeName();
			TParameterDeclaration pdecl;
			if (tname.hasAbstractDeclarator()) {
				pdecl = new TParameterDeclaration(tname.getCommonSpecifiers().toDeclarationSpecifiers(), tname.getAbstractDeclarator());
			} else {
				pdecl = new TParameterDeclaration(tname.getCommonSpecifiers().toDeclarationSpecifiers());
			}
			
			LinkedList<TAssignmentExpression> aexprs = cexpr.getAssignmentExpressions();
			aexprs.add(0, TAssignmentExpression.from(TUnaryExpression.from(getMedium().addCompType(pdecl).getSafeTypeName())));

			TMacroSpecifier mspec = TMacroSpecifier.from("cx_expr_to_comp", aexprs.toArray(new TAssignmentExpression[0]));
	
			TUnaryExpression newNode = new TUnaryExpression(new TPostfixExpressionP(new TPrimaryExpression(new TMacroSpecifierList(mspec))));
			newNode.setLeft(node.getLeft());
			newNode.setRight(node.getRight());
			return newNode;
			
		}
		return null;
	}
	

}
