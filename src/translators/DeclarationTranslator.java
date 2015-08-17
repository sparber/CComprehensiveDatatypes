package translators;

import errors.CXSyntaxError;
import generation.Medium;

import java.util.LinkedList;

import tree.declarations.TDeclaration;
import tree.declarations.TDeclarationDSIDL;
import tree.declarations.TDeclarationSpecifiers;
import tree.declarations.TInitDeclarator;
import tree.declarations.TInitDeclaratorList;
import tree.declarations.TParameterDeclaration;
import tree.declarations.TPointerComprehension;
import tree.declarations.TSpecifierQualifierList;
import tree.declarations.TStructDeclaration;
import tree.declarations.TStructDeclarationList;
import tree.declarations.TStructDeclarationSQLSDL;
import tree.declarations.TStructDeclarator;
import tree.declarations.TStructDeclaratorList;
import tree.declarations.TTypeName;
import tree.expressions.TUnaryOperator;
import tree.statements.TBlockItem;
import tree.statements.TBlockItemD;
import tree.statements.TBlockItemList;
import tree.statements.TBlockItemS;
import tree.statements.TIterationStatement;
import tree.statements.TIterationStatementF;
import tree.symbols.TSBorOp;
import tree.symbols.TSCompOp;
import tree.symbols.TSGenerator;
import tree.symbols.TSSemicolon;
import tree.translation_unit.TDeclarationList;
import tree.translation_unit.TExternalDeclaration;
import tree.translation_unit.TExternalDeclarationD;
import tree.translation_unit.TExternalDeclarationFD;
import tree.translation_unit.TFunctionDefinition;
import tree.translation_unit.TTranslationUnit;

public class DeclarationTranslator extends DefaultTranslator {
	
	public DeclarationTranslator(Medium medium) {
		super(medium, new String[]{});
	}

	private LinkedList<Integer> contains = new LinkedList<Integer>();
	
	@Override
	public TSCompOp visitTSCompOp(TSCompOp symbol) {
		if (symbol.getParent() instanceof TPointerComprehension)
			foundMatch();
		return null;
	}
	@Override
	public TSBorOp visitTSBorOp(TSBorOp symbol) {
		if (symbol.getParent() instanceof TUnaryOperator) {
			foundMatch();
		}
		return null;
	}
	@Override
	public TSGenerator visitTSGenerator(TSGenerator symbol) {
		foundMatch();
		return null;
	}
	
	protected void foundMatch() {
		contains.push(contains.pop()+1);
	}
	
	
	// declaration

	private LinkedList<LinkedList<TDeclaration>> declss = new LinkedList<LinkedList<TDeclaration>>();
	
	private LinkedList<TExternalDeclaration> extdecls = new LinkedList<TExternalDeclaration>();
	@Override
	public TTranslationUnit visitAfterTTranslationUnit(TTranslationUnit node) {
		return TTranslationUnit.from(extdecls);
	}
	@Override
	public boolean visitBeforeTExternalDeclarationD(TExternalDeclarationD node) {
		declss.push(new LinkedList<TDeclaration>());
		return true;
	}
	@Override
	public TExternalDeclaration visitAfterTExternalDeclarationD(TExternalDeclarationD node) {
		for (TDeclaration decl : declss.pop()) {
			extdecls.add(new TExternalDeclarationD(decl));
		}
		return null;
	}
	@Override
	public TExternalDeclaration visitAfterTExternalDeclarationFD(TExternalDeclarationFD node) {
		extdecls.add(node);
		return null;
	}
	
	@Override
	public boolean visitBeforeTDeclarationList(TDeclarationList node) {
		declss.push(new LinkedList<TDeclaration>());
		return true;
	}
	@Override
	public TDeclarationList visitAfterTDeclarationList(TDeclarationList node) {
		return TDeclarationList.from(declss.pop());
	}
	
	private LinkedList<LinkedList<TBlockItem>> itemss = new LinkedList<LinkedList<TBlockItem>>();
	@Override
	public boolean visitBeforeTBlockItemList(TBlockItemList node) {
		itemss.push(new LinkedList<TBlockItem>());
		return true;
	}
	@Override
	public TBlockItemList visitAfterTBlockItemList(TBlockItemList node) {
		return TBlockItemList.from(itemss.pop());
	}
	
	@Override
	public boolean visitBeforeTBlockItemD(TBlockItemD node) {
		declss.push(new LinkedList<TDeclaration>());
		return true;
	}
	@Override
	public TBlockItem visitAfterTBlockItemD(TBlockItemD node) {
		for (TDeclaration decl : declss.pop()) {
			itemss.peek().add(new TBlockItemD(decl));
		}
		return null;
	}
	@Override
	public TBlockItem visitAfterTBlockItemS(TBlockItemS node) {
		itemss.peek().add(node);
		return null;
	}
	
	@Override
	public boolean visitBeforeTIterationStatementF(TIterationStatementF node) {
		declss.push(new LinkedList<TDeclaration>());
		return true;
	}
	@Override
	public TIterationStatement visitAfterTIterationStatementF(TIterationStatementF node) {
		LinkedList<TDeclaration> decls = declss.pop();
		if (decls.size() == 1) {
			node.setExprDecl1(decls.getFirst());
		} else if (decls.size() > 1) {
			throw new CXSyntaxError("complex declaration cannot be used here", node);
		}
		return null;
	}
	
	private LinkedList<LinkedList<TInitDeclarator>> initdeclss = null;
	@Override
	public boolean visitBeforeTDeclaration(TDeclaration node) {
		initdeclss = new LinkedList<LinkedList<TInitDeclarator>>();
		initdeclss.add(new LinkedList<TInitDeclarator>());
		return true;
	}

	@Override
	public TDeclaration visitAfterTDeclaration(TDeclaration node) {
		if (initdeclss.getLast().isEmpty())
			initdeclss.removeLast();
		
		if (node instanceof TDeclarationDSIDL) {
			TDeclarationDSIDL dsidl = (TDeclarationDSIDL) node;
			int counter = 0;
			for (LinkedList<TInitDeclarator> initdecls : initdeclss) {
				TDeclarationSpecifiers specs;
				if (counter == 0)
					specs = dsidl.getDeclarationSpecifiers();
				else {
					specs = new TDeclarationSpecifiers(dsidl.getDeclarationSpecifiers());
					specs.setLeft(dsidl.getLeft());
				}
				
				TSSemicolon scolon = null;
				if (initdecls == initdeclss.getLast())
					scolon = dsidl.getSemicolon();
				else {
					scolon = new TSSemicolon();
					scolon.setRight(initdecls.getLast().getRight());
				}
					
				TDeclarationDSIDL decl = new TDeclarationDSIDL(
						specs,
						TInitDeclaratorList.from(initdecls),
						scolon
				);
				
				declss.peek().add(decl);

				counter++;
			}
		} else {
			declss.peek().add(node);
		}
		return null;
	}
	
	@Override
	public boolean visitBeforeTInitDeclarator(TInitDeclarator node) {
		contains.push(0);
		return true;
	}
	@Override
	public TInitDeclarator visitAfterTInitDeclarator(TInitDeclarator node) {
		if (contains.pop() > 0) {
			if (!initdeclss.getLast().isEmpty()) {
				initdeclss.add(new LinkedList<TInitDeclarator>());
			}
			
			initdeclss.getLast().add(node);
			
			initdeclss.add(new LinkedList<TInitDeclarator>());
		} else {
			initdeclss.getLast().add(node);
		}
		return null;
	}
	

	// struct declarator
	
	LinkedList<LinkedList<TStructDeclaration>> sdeclss = new LinkedList<LinkedList<TStructDeclaration>>();
	@Override
	public boolean visitBeforeTStructDeclarationList(TStructDeclarationList node) {
		sdeclss.push(new LinkedList<TStructDeclaration>());
		return true;
	}
	@Override
	public TStructDeclarationList visitAfterTStructDeclarationList(TStructDeclarationList node) {
		return TStructDeclarationList.from(sdeclss.pop());
	}
	
	private LinkedList<LinkedList<TStructDeclarator>> initsdeclss = null;
	@Override
	public boolean visitBeforeTStructDeclaration(TStructDeclaration node) {
		initsdeclss = new LinkedList<LinkedList<TStructDeclarator>>();
		initsdeclss.add(new LinkedList<TStructDeclarator>());
		return true;
	}
	@Override
	public TStructDeclaration visitAfterTStructDeclaration(TStructDeclaration node) {
		if (initsdeclss.getLast().isEmpty())
			initsdeclss.removeLast();
		
		if (node instanceof TStructDeclarationSQLSDL) {
			TStructDeclarationSQLSDL sqlsdl = (TStructDeclarationSQLSDL) node;
			int counter = 0;
			for (LinkedList<TStructDeclarator> initdecls : initsdeclss) {
				TSpecifierQualifierList specs;
				if (counter == 0)
					specs = sqlsdl.getSpecifierQualifierList();
				else {
					specs = new TSpecifierQualifierList(sqlsdl.getSpecifierQualifierList());
					specs.setLeft(sqlsdl.getLeft());
				}

				TSSemicolon scolon = null;
				if (initdecls == initsdeclss.getLast())
					scolon = sqlsdl.getSemicolon();
				else {
					scolon = new TSSemicolon();
					scolon.setRight(initdecls.getLast().getRight());
				}
					
				TStructDeclarationSQLSDL sdecl = new TStructDeclarationSQLSDL(
						specs,
						TStructDeclaratorList.from(initdecls),
						scolon
				);
				
				sdeclss.peek().add(sdecl);

				counter++;
			}
		} else {
			sdeclss.peek().add(node);
		}
		return null;
	}

	@Override
	public boolean visitBeforeTStructDeclarator(TStructDeclarator node) {
		contains.push(0);
		return true;
	}
	@Override
	public TStructDeclarator visitAfterTStructDeclarator(TStructDeclarator node) {
		if (contains.pop() > 0) {
			if (!initsdeclss.getLast().isEmpty()) {
				initsdeclss.add(new LinkedList<TStructDeclarator>());
			}
			
			initsdeclss.getLast().add(node);
			
			initsdeclss.add(new LinkedList<TStructDeclarator>());
		} else {
			initsdeclss.getLast().add(node);
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
		contains.pop();
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
		contains.pop();
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
		contains.pop();
		return null;
	}
	
}
