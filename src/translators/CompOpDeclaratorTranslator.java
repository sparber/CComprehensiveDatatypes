package translators;

import generation.Medium;

import java.util.LinkedList;

import java_cup.runtime.ComplexSymbolFactory.Location;
import tree.DefaultTreeNode;
import tree.TreeNode;
import tree.declarations.TAbstractDeclarator;
import tree.declarations.TDeclarationSpecifiers;
import tree.declarations.TMacroTypeSpecifier;
import tree.declarations.TParameterDeclaration;
import tree.declarations.TPointerComprehension;
import tree.declarations.TTypeSpecifier;
import tree.expressions.TAssignmentExpression;
import tree.expressions.TUnaryExpression;
import tree.other.CommonDeclaration;
import tree.other.CommonDeclarator;
import tree.symbols.TSCompOp;
import tree.symbols.TSInt;

public class CompOpDeclaratorTranslator extends DeclaratorVisitor {
	
	public CompOpDeclaratorTranslator(Medium medium) {
		super(medium, new String[] {DeclarationTranslator.class.getSimpleName()});
	}

	@Override
	public TSCompOp visitTSCompOp(TSCompOp symbol) {
		if (symbol.getParent() instanceof TPointerComprehension)
			foundMatch();
		return null;
	}
	
	@Override
	protected CommonDeclaration translate(CommonDeclaration decl, int n_matches) {
		Location left = decl.getLeft();
		Location right = decl.getRight();
		
		LinkedList<TreeNode> specs = new LinkedList<TreeNode>();
		
		TAbstractDeclarator tdecl = decl.getCommonDeclarator().getTypeDeclarator();
		
		TParameterDeclaration pdecl = null;
		if (tdecl == null)
			pdecl = new TParameterDeclaration(decl.getCommonSpecifiers().toDeclarationSpecifiers());
		else
			pdecl = new TParameterDeclaration(decl.getCommonSpecifiers().toDeclarationSpecifiers(), tdecl);
		if (pdecl.getLeft() == null)
			pdecl.setLeft(left);
		if (pdecl.getRight() == null)
			pdecl.setRight(right);
		
		if (pdecl.isVoid()) {
			TDeclarationSpecifiers intspec = new TDeclarationSpecifiers(new TTypeSpecifier(new TSInt()));
			intspec.setLeft(pdecl.getLeft());
			intspec.setRight(pdecl.getRight());
			
			pdecl = new TParameterDeclaration(intspec);
		}
		TAssignmentExpression aexpr = TAssignmentExpression.from(TUnaryExpression.from(getMedium().addCompType(pdecl).getSafeTypeName()));

		specs.add(new TTypeSpecifier(TMacroTypeSpecifier.from("cx_comp_spec", new DefaultTreeNode[]{aexpr})));
		decl.setSpecifiers(specs);
		
		CommonDeclarator ndecl = decl.getCommonDeclarator().getNameDeclarator();
		decl.setCommonDeclarator(ndecl);

		if (n_matches > 1)
			return translate(decl, n_matches-1);
		
		decl.setLeft(left);
		decl.setRight(right);
		return null;
	}
	
}
