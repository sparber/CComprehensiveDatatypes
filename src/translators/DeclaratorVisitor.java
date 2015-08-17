package translators;

import errors.CXInternalError;
import generation.Medium;

import java.util.LinkedList;

import tree.declarations.TDeclaration;
import tree.declarations.TDeclarationDSIDL;
import tree.declarations.TParameterDeclaration;
import tree.declarations.TStructDeclaration;
import tree.declarations.TStructDeclarationSQLSDL;
import tree.declarations.TTypeName;
import tree.other.CommonDeclaration;
import tree.translation_unit.TFunctionDefinition;

public abstract class DeclaratorVisitor extends DefaultTranslator {
	
	public DeclaratorVisitor(Medium medium, String className, String[] preTranslators) {
		super(medium, className, preTranslators);
	}
	public DeclaratorVisitor(Medium medium, String[] preTranslators) {
		super(medium, preTranslators);
	}

	private LinkedList<Integer> contains = new LinkedList<Integer>();
	
	protected void foundMatch() {
		contains.push(contains.pop()+1);
	}
	
	protected abstract CommonDeclaration translate(CommonDeclaration decl, int n_matches);
	
	
	// declaration

	@Override
	public boolean visitBeforeTDeclaration(TDeclaration node) {
		contains.push(0);
		return true;
	}
	@Override
	public TDeclaration visitAfterTDeclarationDSIDL(TDeclarationDSIDL node) {
		int n_matches;
		if ((n_matches = contains.pop()) > 0) {
				
			if (node.getInitDeclaratorList().getList().size() != 1)
				throw new CXInternalError("constructed wrong initdecls list in DeclarationVisitor");
			
			return (TDeclarationDSIDL) translate(node, n_matches);
		}

		return null;
	}
	

	// type_name
	
	@Override
	public boolean visitBeforeTTypeName(TTypeName node) {
		contains.push(0);
		return true;
	}
	@Override
	public TTypeName visitAfterTTypeName(TTypeName node) {
		int n_matches;
		if ((n_matches = contains.pop()) > 0) {
			
			return (TTypeName) translate(node, n_matches);
			
		}
		return null;
	}
	
	
	// struct declarator
	
	@Override
	public boolean visitBeforeTStructDeclaration(TStructDeclaration node) {
		contains.push(0);
		return true;
	}
	@Override
	public TStructDeclaration visitAfterTStructDeclarationSQLSDL(TStructDeclarationSQLSDL node) {
		int n_matches = 0;
		if ((n_matches = contains.pop()) > 0) {
			
			if (node.getStructDeclaratorList().getList().size() != 1)
				throw new CXInternalError("constructed wrong initdecls list in DeclarationVisitor");

			return (TStructDeclarationSQLSDL) translate(node, n_matches);
			
		}
		return null;
	}


	// parameter declaration
	
	@Override
	public boolean visitBeforeTParameterDeclaration(TParameterDeclaration node) {
		contains.push(0);
		return true;
	}
	@Override
	public TParameterDeclaration visitAfterTParameterDeclaration(TParameterDeclaration node) {
		int n_matches = 0;
		if ((n_matches = contains.pop()) > 0) {
			
			return (TParameterDeclaration) translate(node, n_matches);
			
		}
		return null;
	}


	// function definition
	
	@Override
	public boolean visitBeforeTFunctionDefinition(TFunctionDefinition node) {
		contains.push(0);
		return true;
	}
	@Override
	public TFunctionDefinition visitAfterTFunctionDefinition(TFunctionDefinition node) {
		int n_matches = 0;
		if ((n_matches = contains.pop()) > 0) {
			
			return (TFunctionDefinition) translate(node, n_matches);
			
		}
		return null;
	}
	
	
}
