package translators;

import errors.CXSyntaxError;
import generation.Medium;

import java.util.LinkedList;

import java_cup.runtime.ComplexSymbolFactory.Location;
import tree.DefaultTreeNode;
import tree.TreeNode;
import tree.declarations.TAbstractDeclarator;
import tree.declarations.TDeclaration;
import tree.declarations.TDeclarationSpecifiers;
import tree.declarations.TDeclarator;
import tree.declarations.TIdentifierList;
import tree.declarations.TMacroTypeSpecifier;
import tree.declarations.TParameterDeclaration;
import tree.declarations.TParameterTypeList;
import tree.declarations.TTypeSpecifier;
import tree.expressions.TExpression;
import tree.expressions.TUnaryExpression;
import tree.other.CommonDeclaration;
import tree.other.CommonDeclarator;
import tree.other.WhiteSpaceFiller;
import tree.statements.TBlockItem;
import tree.statements.TBlockItemList;
import tree.statements.TBlockItemS;
import tree.statements.TCompoundStatement;
import tree.statements.TExpressionStatement;
import tree.symbols.TSBraceLeft;
import tree.symbols.TSBraceRight;
import tree.symbols.TSGenerator;
import tree.symbols.TSIdentifier;
import tree.symbols.TSInt;
import tree.symbols.TSSemicolon;
import tree.symbols.TSTypedefName;
import tree.translation_unit.TExternalDeclaration;
import tree.translation_unit.TFunctionDefinition;
import tree.translation_unit.TGeneratorDefinition;

public class GeneratorTranslator extends DeclaratorVisitor {

	public GeneratorTranslator(Medium medium) {
		super(medium, new String[] {DeclarationTranslator.class.getSimpleName()});
	}

	@Override
	public TSGenerator visitTSGenerator(TSGenerator symbol) {
		foundMatch();
		return null;
	}
	
	protected CommonDeclarator translateDeclarator(CommonDeclarator cdecl, TDeclarationSpecifiers specs) {
		
		TParameterDeclaration pdecl = null;
		if (cdecl.hasPointerComprehension()) {
			pdecl = new TParameterDeclaration(specs, new TAbstractDeclarator(cdecl.getPointerComprehension()));
		} else {
			pdecl = new TParameterDeclaration(specs);
		}
		if (pdecl.isVoid()) {
			
			TDeclarationSpecifiers intspec = new TDeclarationSpecifiers(new TTypeSpecifier(new TSInt()));
			intspec.setLeft(pdecl.getLeft());
			intspec.setRight(pdecl.getRight());
			
			pdecl = new TParameterDeclaration(intspec);
		}
		
		TParameterDeclaration param = new TParameterDeclaration(new TDeclarationSpecifiers(new TTypeSpecifier(TMacroTypeSpecifier.from("cx_fn_spec", new DefaultTreeNode[] {pdecl}))));
		
		return cdecl.newInstance(null, cdecl.getDirectDeclarator().translateGenerator(param));
	}
	
	protected TGeneratorDefinition translateFunctionDefinition(TFunctionDefinition fdef) {
		LinkedList<TBlockItem> items = new LinkedList<TBlockItem>();
		
		TExpression startExpr = TExpression.from(TUnaryExpression.from("cx_generator_start"));
		items.add(new TBlockItemS(new TExpressionStatement(startExpr, new TSSemicolon())));

		items.add(new TBlockItemS(fdef.getCompoundStatement()));
		
		TExpression endExpr = TExpression.from(TUnaryExpression.from("cx_generator_end"));
		items.add(new TBlockItemS(new TExpressionStatement(endExpr, new TSSemicolon())));
		
		Location right = fdef.getRight();
		fdef.setCompoundStatement(new TCompoundStatement(new TSBraceLeft(), TBlockItemList.from(items), new TSBraceRight()));
		fdef.setRight(right);
		
		return new TGeneratorDefinition(fdef);
	}

	@Override
	protected CommonDeclaration translate(CommonDeclaration decl, int n_matches) {
		if (!(decl instanceof TFunctionDefinition) && !(decl instanceof TDeclaration) && !(((DefaultTreeNode)decl).getParent() instanceof TExternalDeclaration))
			throw new CXSyntaxError("generator declarator is not allowed here", (DefaultTreeNode) decl);
		if (n_matches != 1)
			throw new CXSyntaxError("can not use more than one generator in one declarator", (DefaultTreeNode) decl);
		
		String macro = getInterfunctionMacro(decl);
		Location left = decl.getLeft();
		if (left.getColumn() > 1) {
			left = new Location(left.getUnit(), left.getLine()+1, 0, left.getOffset()+1);
		}
		WhiteSpaceFiller filler = getMedium().addWhiteSpaceFiller(left, macro);

		decl.setCommonDeclarator(translateDeclarator(decl.getCommonDeclarator(), decl.getDeclarationSpecifiers()));
		decl.setSpecifiers(new TDeclarationSpecifiers(new TTypeSpecifier(new TSTypedefName("cx_message"))));
		
		TDeclarator d = decl.getDeclarator();
		if (d != null) {
			d.setNameIdentifier(new TSIdentifier(d.getNameIdentifier().value+"_cx_real"));
		}

		decl.setLeft(filler.getRight());
		
		if (decl instanceof TFunctionDefinition) {
			return translateFunctionDefinition((TFunctionDefinition) decl);
		}
		
		return null;
	}
	
	protected int getNParams(CommonDeclaration decl) {
		TreeNode params = decl.getCommonDeclarator().getParams();
		if (params instanceof TIdentifierList) {
			return ((TIdentifierList) params).getList().size();
		} else if (params instanceof TParameterTypeList) {
			TParameterTypeList p = (TParameterTypeList)params;
			if (p.hasVariadicArguments())
				throw new CXSyntaxError("variadic arguments are not allowed in generator functions", p);
			return p.getParameterList().getList().size();
		}
		return 0;
	}
	
	protected String getFunctionName(CommonDeclaration decl) {
		if (decl.getDeclarator() == null)
			throw new CXSyntaxError("abstract generator functions are illegal", decl);
		return decl.getDeclarator().getNameIdentifier().value;
	}
	
	protected String getSafeTypeName(CommonDeclaration decl) {
		TParameterDeclaration pdecl = null;
		if (decl.getCommonDeclarator().hasPointerComprehension())
			pdecl = new TParameterDeclaration(decl.getDeclarationSpecifiers(), new TAbstractDeclarator(decl.getCommonDeclarator().getPointerComprehension()));
		else
			pdecl = new TParameterDeclaration(decl.getDeclarationSpecifiers());
		if (pdecl.isVoid()) {
			
			TDeclarationSpecifiers specs = new TDeclarationSpecifiers(new TTypeSpecifier(new TSInt()));
			specs.setLeft(pdecl.getLeft());
			specs.setRight(pdecl.getRight());
			
			pdecl = new TParameterDeclaration(specs);
		}
		return getMedium().addCompType(pdecl).getSafeTypeName();
	}
	
	protected String getInterfunctionMacro(CommonDeclaration decl) {
		int n_params = getNParams(decl);
		String funcName = getFunctionName(decl);
		String type_name = getSafeTypeName(decl);
		
		// "#define range(a, b) ({ __typeof(a) a_ = a; __typeof(b) b_ = b; interfunction(int, range_real, a_, b_) })\n";
		StringBuilder sb = new StringBuilder();
		
		sb.append("#define "+funcName+"(");
		for (int i=0; i<n_params; i++) {
			if (i > 0)
				sb.append(", ");
			sb.append("cx_v"+i);
		}
		sb.append(") ({ ");
		for (int i=0; i<n_params; i++) {
			sb.append("__typeof__(cx_v"+i+") cx_v"+i+"_ = cx_v"+i+"; ");
		}
		if (n_params > 0) {
			sb.append("cx_interfunction("+type_name+", "+funcName+"_cx_real, ");
			for (int i=0; i<n_params; i++) {
				if (i > 0)
					sb.append(", ");
				sb.append("cx_v"+i+"_");
			}
			sb.append(") })\n");
		} else {
			sb.append("cx_interfunction_("+type_name+", "+funcName+"_cx_real) })\n");
		}
		Location realloc = decl.getRealLeft();
		if (realloc != null) {
			sb.append("#line "+realloc.getLine()+"\n");
		}
		
		return sb.toString();
	}

}
