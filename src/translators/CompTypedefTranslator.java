package translators;

import errors.CXInternalError;
import generation.Medium;
import generation.OutputGenerator;

import java.util.LinkedList;
import java.util.ListIterator;

import java_cup.runtime.ComplexSymbolFactory.Location;
import lexer.LocationMethods;
import tree.declarations.TParameterDeclaration;
import tree.other.CompTypeName;
import tree.translation_unit.TExternalDeclaration;
import tree.translation_unit.TTranslationUnit;

public class CompTypedefTranslator extends DefaultTranslator {

	public CompTypedefTranslator(Medium medium) {
		super(medium, new String[] {CompOpDeclaratorTranslator.class.getSimpleName(), GeneratorTranslator.class.getSimpleName()});
	}
	
	protected Location getStartLocation(TExternalDeclaration decl) {
		Location start = decl.getLeft();
		if (start == null) {
			throw new CXInternalError("missing location information", decl);
		}
		if (start.getColumn() != 0) {
			start = new Location(start.getUnit(), start.getLine()+1, 0, start.getOffset()+1);
		}
		return start;
	}
	
	protected LinkedList<CompTypeName> getRequiredCompTypeNames(TExternalDeclaration decl, ListIterator<CompTypeName> tnames) {
		if (decl.getLeft() == null || decl.getRight() == null)
			throw new CXInternalError("missing location information", decl);
		
		LinkedList<CompTypeName> required = new LinkedList<CompTypeName>();
		while (tnames.hasNext()) {
			CompTypeName tname = tnames.next();
			if (LocationMethods.compareLocations(decl.getRight(), tname.getTypeName().getLeft()) >= 0) {
				tnames.remove();
				required.add(tname);
			}
		}
		
		return required;
	}


	@Override
	public TTranslationUnit visitAfterTTranslationUnit(TTranslationUnit node) {
		
		/* 
#ifndef CX_COMP_T_int_
#define CX_COMP_T_int_
_macro_type cx_comp_typedef(int, int_);
#endif
		 */
		
		LinkedList<TExternalDeclaration> declslist = node.getList();
		ListIterator<TExternalDeclaration> decls = declslist.listIterator();
		
		LinkedList<CompTypeName> tnames = new LinkedList<CompTypeName>(getMedium().getCompTypeNames());
		
		
		while (decls.hasNext()) {
			
			TExternalDeclaration decl = decls.next();
			
			LinkedList<CompTypeName> required = getRequiredCompTypeNames(decl, tnames.listIterator());
			
			Location left = getStartLocation(decl);
			decl.setLeft(left);
			
			StringBuilder sbuilder = new StringBuilder();
			
			for (CompTypeName tname : required) {
				String tnamestring = OutputGenerator.treeNodeToString(new TParameterDeclaration(tname.getTypeName()));
				sbuilder.append(
						"#ifndef CX_COMP_T_"+tname.getSafeTypeName()+"\n" +
						"#define CX_COMP_T_"+tname.getSafeTypeName()+"\n" +
						"_macro_type cx_comp_typedef("+tnamestring+", "+tname.getSafeTypeName()+");\n" +
						"#endif\n"
				);
			}

			Location realloc = decl.getRealLeft();
			if (sbuilder.length() > 0 && realloc != null) {
				sbuilder.append("#line "+realloc.getLine()+"\n");
			}
			
			getMedium().addWhiteSpaceFiller(left, sbuilder.toString());
		}
		
		return TTranslationUnit.from(declslist);
	}

}
