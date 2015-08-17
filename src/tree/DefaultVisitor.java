package tree;

import errors.CXError;
import errors.CXInternalError;
import generation.Medium;
import tree.declarations.*;
import tree.expressions.*;
import tree.other.CommonDeclarator;
import tree.other.CommonDirectDeclarator;
import tree.other.TreeRoot;
import tree.statements.*;
import tree.symbols.*;
import tree.translation_unit.*;

public class DefaultVisitor {
	
	private Medium medium = null;
	public DefaultVisitor() {
	}
	
	public DefaultVisitor(Medium medium) {
		this.medium  = medium;
	}
	
	public Medium getMedium() {
		return medium;
	}
	
	public boolean acceptsMessages() {
		return medium != null;
	}
	
	public void addError(CXError error) {
		if (medium == null) {
			throw new CXInternalError("visitor can not accept messages", error);
		}
		medium.error(error);
	}
	
	// abstract nodes
	public boolean visitBeforeDefaultTreeNode(DefaultTreeNode node) {
		return true;
	}
	public DefaultTreeNode visitAfterDefaultTreeNode(DefaultTreeNode node) {
		return null;
	}
	public DefaultTreeNodeSymbol visitDefaultTreeNodeSymbol(DefaultTreeNodeSymbol node) {
		return null;
	}

	// translation_unit
	public boolean visitBeforeTreeRoot(TreeRoot node) {
		return true;
	}
	public boolean visitBeforeTDeclarationList(TDeclarationList node) {
		return true;
	}
	public boolean visitBeforeTExternalDeclaration(TExternalDeclaration node) {
		return true;
	}
	public boolean visitBeforeTExternalDeclarationD(TExternalDeclarationD node) {
		return true;
	}
	public boolean visitBeforeTExternalDeclarationFD(TExternalDeclarationFD node) {
		return true;
	}
	public boolean visitBeforeTFunctionDefinition(TFunctionDefinition node) {
		return true;
	}
	public boolean visitBeforeTGeneratorDefinition(TGeneratorDefinition node) {
		return true;
	}
	public boolean visitBeforeTTranslationUnit(TTranslationUnit node) {
		return true;
	}

	public DefaultTreeNode visitAfterTreeRoot(TreeRoot node) {
		return null;
	}
	public TDeclarationList visitAfterTDeclarationList(TDeclarationList node) {
		return null;
	}
	public TExternalDeclaration visitAfterTExternalDeclaration(TExternalDeclaration node) {
		return null;
	}
	public TExternalDeclaration visitAfterTExternalDeclarationD(TExternalDeclarationD node) {
		return null;
	}
	public TExternalDeclaration visitAfterTExternalDeclarationFD(TExternalDeclarationFD node) {
		return null;
	}
	public TFunctionDefinition visitAfterTFunctionDefinition(TFunctionDefinition node) {
		return null;
	}
	public TFunctionDefinition visitAfterTGeneratorDefinition(TGeneratorDefinition node) {
		return null;
	}
	public TTranslationUnit visitAfterTTranslationUnit(TTranslationUnit node) {
		return null;
	}
	
	// statements
	public boolean visitBeforeTBlockItem(TBlockItem node) {
		return true;
	}
	public boolean visitBeforeTBlockItemD(TBlockItemD node) {
		return true;
	}
	public boolean visitBeforeTBlockItemS(TBlockItemS node) {
		return true;
	}
	public boolean visitBeforeTBlockItemSplit(TBlockItemSplit node) {
		return true;
	}
	public boolean visitBeforeTBlockItemEndSplit(TBlockItemEndSplit node) {
		return true;
	}
	public boolean visitBeforeTBlockItemList(TBlockItemList node) {
		return true;
	}
	public boolean visitBeforeTCompoundStatement(TCompoundStatement node) {
		return true;
	}
	public boolean visitBeforeTExpressionStatement(TExpressionStatement node) {
		return true;
	}
	public boolean visitBeforeTFinallyStatement(TFinallyStatement node) {
		return true;
	}
	public boolean visitBeforeTIterationStatement(TIterationStatement node) {
		return true;
	}
	public boolean visitBeforeTIterationStatementD(TIterationStatementD node) {
		return true;
	}
	public boolean visitBeforeTIterationStatementF(TIterationStatementF node) {
		return true;
	}
	public boolean visitBeforeTIterationStatementW(TIterationStatementW node) {
		return true;
	}
	public boolean visitBeforeTJumpStatement(TJumpStatement node) {
		return true;
	}
	public boolean visitBeforeTLabeledStatement(TLabeledStatement node) {
		return true;
	}
	public boolean visitBeforeTSelectionStatement(TSelectionStatement node) {
		return true;
	}
	public boolean visitBeforeTStatement(TStatement node) {
		return true;
	}

	public TBlockItem visitAfterTBlockItem(TBlockItem node) {
		return null;
	}
	public TBlockItem visitAfterTBlockItemD(TBlockItemD node) {
		return null;
	}
	public TBlockItem visitAfterTBlockItemS(TBlockItemS node) {
		return null;
	}
	public TBlockItem visitAfterTBlockItemSplit(TBlockItemSplit node) {
		return null;
	}
	public TBlockItem visitAfterTBlockItemEndSplit(TBlockItemEndSplit node) {
		return null;
	}
	public TBlockItemList visitAfterTBlockItemList(TBlockItemList node) {
		return null;
	}
	public TStatement visitAfterTCompoundStatement(TCompoundStatement node) {
		return null;
	}
	public TStatement visitAfterTExpressionStatement(TExpressionStatement node) {
		return null;
	}
	public TFinallyStatement visitAfterTFinallyStatement(TFinallyStatement node) {
		return null;
	}
	public TStatement visitAfterTIterationStatement(TIterationStatement node) {
		return null;
	}
	public TStatement visitAfterTIterationStatementD(TIterationStatementD node) {
		return null;
	}
	public TStatement visitAfterTIterationStatementF(TIterationStatementF node) {
		return null;
	}
	public TStatement visitAfterTIterationStatementW(TIterationStatementW node) {
		return null;
	}
	public TStatement visitAfterTJumpStatement(TJumpStatement node) {
		return null;
	}
	public TStatement visitAfterTLabeledStatement(TLabeledStatement node) {
		return null;
	}
	public TStatement visitAfterTSelectionStatement(TSelectionStatement node) {
		return null;
	}
	public TStatement visitAfterTStatement(TStatement node) {
		return null;
	}

	// declarations
	public boolean visitBeforeTAbstractDeclarator(TAbstractDeclarator node) {
		return true;
	}
	public boolean visitBeforeTAlignmentSpecifier(TAlignmentSpecifier node) {
		return true;
	}
	public boolean visitBeforeTAtomicTypeSpecifier(TAtomicTypeSpecifier node) {
		return true;
	}
	public boolean visitBeforeCommonDeclarator(CommonDeclarator node) {
		return true;
	}
	public boolean visitBeforeCommonDirectDeclarator(CommonDirectDeclarator node) {
		return true;
	}
	public boolean visitBeforeTDeclaration(TDeclaration node) {
		return true;
	}
	public boolean visitBeforeTDeclarationDS(TDeclarationDS node) {
		return true;
	}
	public boolean visitBeforeTDeclarationDSIDL(TDeclarationDSIDL node) {
		return true;
	}
	public boolean visitBeforeTDeclarationSAD(TDeclarationSAD node) {
		return true;
	}
	public boolean visitBeforeTDeclarationSpecifiers(TDeclarationSpecifiers node) {
		return true;
	}
	public boolean visitBeforeTDeclarator(TDeclarator node) {
		return true;
	}
	public boolean visitBeforeTDesignation(TDesignation node) {
		return true;
	}
	public boolean visitBeforeTDesignationInitializer(TDesignationInitializer node) {
		return true;
	}
	public boolean visitBeforeTDesignator(TDesignator node) {
		return true;
	}
	public boolean visitBeforeTDesignatorList(TDesignatorList node) {
		return true;
	}
	public boolean visitBeforeTDirectAbstractDeclarator(TDirectAbstractDeclarator node) {
		return true;
	}
	public boolean visitBeforeTDirectAbstractDeclaratorAD(TDirectAbstractDeclaratorAD node) {
		return true;
	}
	public boolean visitBeforeTDirectAbstractDeclaratorA(TDirectAbstractDeclaratorA node) {
		return true;
	}
	public boolean visitBeforeTDirectAbstractDeclaratorF(TDirectAbstractDeclaratorF node) {
		return true;
	}
	public boolean visitBeforeTDirectAbstractDeclaratorG(TDirectAbstractDeclaratorG node) {
		return true;
	}
	public boolean visitBeforeTDirectDeclarator(TDirectDeclarator node) {
		return true;
	}
	public boolean visitBeforeTDirectDeclaratorA(TDirectDeclaratorA node) {
		return true;
	}
	public boolean visitBeforeTDirectDeclaratorD(TDirectDeclaratorD node) {
		return true;
	}
	public boolean visitBeforeTDirectDeclaratorF(TDirectDeclaratorF node) {
		return true;
	}
	public boolean visitBeforeTDirectDeclaratorG(TDirectDeclaratorG node) {
		return true;
	}
	public boolean visitBeforeTDirectDeclaratorI(TDirectDeclaratorI node) {
		return true;
	}
	public boolean visitBeforeTEnumerator(TEnumerator node) {
		return true;
	}
	public boolean visitBeforeTEnumeratorList(TEnumeratorList node) {
		return true;
	}
	public boolean visitBeforeTEnumSpecifier(TEnumSpecifier node) {
		return true;
	}
	public boolean visitBeforeTFunctionSpecifier(TFunctionSpecifier node) {
		return true;
	}
	public boolean visitBeforeTIdentifierList(TIdentifierList node) {
		return true;
	}
	public boolean visitBeforeTInitDeclarator(TInitDeclarator node) {
		return true;
	}
	public boolean visitBeforeTInitDeclaratorList(TInitDeclaratorList node) {
		return true;
	}
	public boolean visitBeforeTInitializer(TInitializer node) {
		return true;
	}
	public boolean visitBeforeTInitializerList(TInitializerList node) {
		return true;
	}
	public boolean visitBeforeTMacroTypeSpecifier(TMacroTypeSpecifier node) {
		return true;
	}
	public boolean visitBeforeTParameterDeclaration(TParameterDeclaration node) {
		return true;
	}
	public boolean visitBeforeTParameterList(TParameterList node) {
		return true;
	}
	public boolean visitBeforeTParameterTypeList(TParameterTypeList node) {
		return true;
	}
	public boolean visitBeforeTPointerComprehension(TPointerComprehension node) {
		return true;
	}
	public boolean visitBeforeTSpecifierQualifierList(TSpecifierQualifierList node) {
		return true;
	}
	public boolean visitBeforeTStaticAssertDeclaration(TStaticAssertDeclaration node) {
		return true;
	}
	public boolean visitBeforeTStorageClassSpecifier(TStorageClassSpecifier node) {
		return true;
	}
	public boolean visitBeforeTStructDeclaration(TStructDeclaration node) {
		return true;
	}
	public boolean visitBeforeTStructDeclarationSAD(TStructDeclarationSAD node) {
		return true;
	}
	public boolean visitBeforeTStructDeclarationSQL(TStructDeclarationSQL node) {
		return true;
	}
	public boolean visitBeforeTStructDeclarationSQLSDL(TStructDeclarationSQLSDL node) {
		return true;
	}
	public boolean visitBeforeTStructDeclarationList(TStructDeclarationList node) {
		return true;
	}
	public boolean visitBeforeTStructDeclarator(TStructDeclarator node) {
		return true;
	}
	public boolean visitBeforeTStructDeclaratorList(TStructDeclaratorList node) {
		return true;
	}
	public boolean visitBeforeTStructOrUnion(TStructOrUnion node) {
		return true;
	}
	public boolean visitBeforeTStructOrUnionSpecifier(TStructOrUnionSpecifier node) {
		return true;
	}
	public boolean visitBeforeTTypeName(TTypeName node) {
		return true;
	}
	public boolean visitBeforeTTypeQualifier(TTypeQualifier node) {
		return true;
	}
	public boolean visitBeforeTTypeQualifierList(TTypeQualifierList node) {
		return true;
	}
	public boolean visitBeforeTTypeSpecifier(TTypeSpecifier node) {
		return true;
	}

	public TAbstractDeclarator visitAfterTAbstractDeclarator(TAbstractDeclarator node) {
		return null;
	}
	public TAlignmentSpecifier visitAfterTAlignmentSpecifier(TAlignmentSpecifier node) {
		return null;
	}
	public TAtomicTypeSpecifier visitAfterTAtomicTypeSpecifier(TAtomicTypeSpecifier node) {
		return null;
	}
	public CommonDeclarator visitAfterCommonDeclarator(CommonDeclarator node) {
		return null;
	}
	public CommonDirectDeclarator visitAfterCommonDirectDeclarator(CommonDirectDeclarator node) {
		return null;
	}
	public TDeclaration visitAfterTDeclaration(TDeclaration node) {
		return null;
	}
	public TDeclaration visitAfterTDeclarationDS(TDeclarationDS node) {
		return null;
	}
	public TDeclaration visitAfterTDeclarationDSIDL(TDeclarationDSIDL node) {
		return null;
	}
	public TDeclaration visitAfterTDeclarationSAD(TDeclarationSAD node) {
		return null;
	}
	public TDeclarationSpecifiers visitAfterTDeclarationSpecifiers(TDeclarationSpecifiers node) {
		return null;
	}
	public TDeclarator visitAfterTDeclarator(TDeclarator node) {
		return null;
	}
	public TDesignation visitAfterTDesignation(TDesignation node) {
		return null;
	}
	public TDesignationInitializer visitAfterTDesignationInitializer(TDesignationInitializer node) {
		return null;
	}
	public TDesignator visitAfterTDesignator(TDesignator node) {
		return null;
	}
	public TDesignatorList visitAfterTDesignatorList(TDesignatorList node) {
		return null;
	}
	public TDirectAbstractDeclarator visitAfterTDirectAbstractDeclarator(TDirectAbstractDeclarator node) {
		return null;
	}
	public TDirectAbstractDeclarator visitAfterTDirectAbstractDeclaratorAD(TDirectAbstractDeclaratorAD node) {
		return null;
	}
	public TDirectAbstractDeclarator visitAfterTDirectAbstractDeclaratorA(TDirectAbstractDeclaratorA node) {
		return null;
	}
	public TDirectAbstractDeclarator visitAfterTDirectAbstractDeclaratorF(TDirectAbstractDeclaratorF node) {
		return null;
	}
	public TDirectAbstractDeclarator visitAfterTDirectAbstractDeclaratorG(TDirectAbstractDeclaratorG node) {
		return null;
	}
	public TDirectDeclarator visitAfterTDirectDeclarator(TDirectDeclarator node) {
		return null;
	}
	public TDirectDeclarator visitAfterTDirectDeclaratorA(TDirectDeclaratorA node) {
		return null;
	}
	public TDirectDeclarator visitAfterTDirectDeclaratorD(TDirectDeclaratorD node) {
		return null;
	}
	public TDirectDeclarator visitAfterTDirectDeclaratorF(TDirectDeclaratorF node) {
		return null;
	}
	public TDirectDeclarator visitAfterTDirectDeclaratorG(TDirectDeclaratorG node) {
		return null;
	}
	public TDirectDeclarator visitAfterTDirectDeclaratorI(TDirectDeclaratorI node) {
		return null;
	}
	public TEnumerator visitAfterTEnumerator(TEnumerator node) {
		return null;
	}
	public TEnumeratorList visitAfterTEnumeratorList(TEnumeratorList node) {
		return null;
	}
	public TEnumSpecifier visitAfterTEnumSpecifier(TEnumSpecifier node) {
		return null;
	}
	public TFunctionSpecifier visitAfterTFunctionSpecifier(TFunctionSpecifier node) {
		return null;
	}
	public TIdentifierList visitAfterTIdentifierList(TIdentifierList node) {
		return null;
	}
	public TInitDeclarator visitAfterTInitDeclarator(TInitDeclarator node) {
		return null;
	}
	public TInitDeclaratorList visitAfterTInitDeclaratorList(TInitDeclaratorList node) {
		return null;
	}
	public TInitializer visitAfterTInitializer(TInitializer node) {
		return null;
	}
	public TInitializerList visitAfterTInitializerList(TInitializerList node) {
		return null;
	}
	public TMacroTypeSpecifier visitAfterTMacroTypeSpecifier(TMacroTypeSpecifier node) {
		return null;
	}
	public TParameterDeclaration visitAfterTParameterDeclaration(TParameterDeclaration node) {
		return null;
	}
	public TParameterList visitAfterTParameterList(TParameterList node) {
		return null;
	}
	public TParameterTypeList visitAfterTParameterTypeList(TParameterTypeList node) {
		return null;
	}
	public TPointerComprehension visitAfterTPointerComprehension(TPointerComprehension node) {
		return null;
	}
	public TSpecifierQualifierList visitAfterTSpecifierQualifierList(TSpecifierQualifierList node) {
		return null;
	}
	public TStaticAssertDeclaration visitAfterTStaticAssertDeclaration(TStaticAssertDeclaration node) {
		return null;
	}
	public TStorageClassSpecifier visitAfterTStorageClassSpecifier(TStorageClassSpecifier node) {
		return null;
	}
	public TStructDeclaration visitAfterTStructDeclaration(TStructDeclaration node) {
		return null;
	}
	public TStructDeclaration visitAfterTStructDeclarationSAD(TStructDeclarationSAD node) {
		return null;
	}
	public TStructDeclaration visitAfterTStructDeclarationSQL(TStructDeclarationSQL node) {
		return null;
	}
	public TStructDeclaration visitAfterTStructDeclarationSQLSDL(TStructDeclarationSQLSDL node) {
		return null;
	}
	public TStructDeclarationList visitAfterTStructDeclarationList(TStructDeclarationList node) {
		return null;
	}
	public TStructDeclarator visitAfterTStructDeclarator(TStructDeclarator node) {
		return null;
	}
	public TStructDeclaratorList visitAfterTStructDeclaratorList(TStructDeclaratorList node) {
		return null;
	}
	public TStructOrUnion visitAfterTStructOrUnion(TStructOrUnion node) {
		return null;
	}
	public TStructOrUnionSpecifier visitAfterTStructOrUnionSpecifier(TStructOrUnionSpecifier node) {
		return null;
	}
	public TTypeName visitAfterTTypeName(TTypeName node) {
		return null;
	}
	public TTypeQualifier visitAfterTTypeQualifier(TTypeQualifier node) {
		return null;
	}
	public TTypeQualifierList visitAfterTTypeQualifierList(TTypeQualifierList node) {
		return null;
	}
	public TTypeSpecifier visitAfterTTypeSpecifier(TTypeSpecifier node) {
		return null;
	}
	
	// expressions
	public boolean visitBeforeTAdditiveExpression(TAdditiveExpression node) {
		return true;
	}
	public boolean visitBeforeTAndExpression(TAndExpression node) {
		return true;
	}
	public boolean visitBeforeTArgumentExpressionList(TArgumentExpressionList node) {
		return true;
	}
	public boolean visitBeforeTAssignmentExpression(TAssignmentExpression node) {
		return true;
	}
	public boolean visitBeforeTAssignmentOperator(TAssignmentOperator node) {
		return true;
	}
	public boolean visitBeforeTCastExpression(TCastExpression node) {
		return true;
	}
	public boolean visitBeforeTCompExpression(TCompExpression node) {
		return true;
	}
	public boolean visitBeforeTCompExpressionList(TCompExpressionList node) {
		return true;
	}
	public boolean visitBeforeTConditionalExpression(TConditionalExpression node) {
		return true;
	}
	public boolean visitBeforeTConstant(TConstant node) {
		return true;
	}
	public boolean visitBeforeTConstantExpression(TConstantExpression node) {
		return true;
	}
	public boolean visitBeforeTEnumerationConstant(TEnumerationConstant node) {
		return true;
	}
	public boolean visitBeforeTEqualityExpression(TEqualityExpression node) {
		return true;
	}
	public boolean visitBeforeTExclusiveOrExpression(TExclusiveOrExpression node) {
		return true;
	}
	public boolean visitBeforeTExpression(TExpression node) {
		return true;
	}
	public boolean visitBeforeTGenericAssociation(TGenericAssociation node) {
		return true;
	}
	public boolean visitBeforeTGenericAssocList(TGenericAssocList node) {
		return true;
	}
	public boolean visitBeforeTGenericSelection(TGenericSelection node) {
		return true;
	}
	public boolean visitBeforeTInclusiveOrExpression(TInclusiveOrExpression node) {
		return true;
	}
	public boolean visitBeforeTLogicalAndExpression(TLogicalAndExpression node) {
		return true;
	}
	public boolean visitBeforeTLogicalOrExpression(TLogicalOrExpression node) {
		return true;
	}
	public boolean visitBeforeTMacroArgumentList(TMacroArgumentList node) {
		return true;
	}
	public boolean visitBeforeTMacroSpecifier(TMacroSpecifier node) {
		return true;
	}
	public boolean visitBeforeTMacroSpecifierList(TMacroSpecifierList node) {
		return true;
	}
	public boolean visitBeforeTMultiplicativeExpression(TMultiplicativeExpression node) {
		return true;
	}
	public boolean visitBeforeTPostfixExpression(TPostfixExpression node) {
		return true;
	}
	public boolean visitBeforeTPostfixExpressionA(TPostfixExpressionA node) {
		return true;
	}
	public boolean visitBeforeTPostfixExpressionID(TPostfixExpressionID node) {
		return true;
	}
	public boolean visitBeforeTPostfixExpressionIz(TPostfixExpressionIz node) {
		return true;
	}
	public boolean visitBeforeTPostfixExpressionIv(TPostfixExpressionIv node) {
		return true;
	}
	public boolean visitBeforeTPostfixExpressionM(TPostfixExpressionM node) {
		return true;
	}
	public boolean visitBeforeTPostfixExpressionP(TPostfixExpressionP node) {
		return true;
	}
	public boolean visitBeforeTPrimaryExpression(TPrimaryExpression node) {
		return true;
	}
	public boolean visitBeforeTRelationalExpression(TRelationalExpression node) {
		return true;
	}
	public boolean visitBeforeTSetExpression(TSetExpression node) {
		return true;
	}
	public boolean visitBeforeTSetExpressionSO(TSetExpressionSO node) {
		return true;
	}
	public boolean visitBeforeTSetExpressionU(TSetExpressionU node) {
		return true;
	}
	public boolean visitBeforeTShiftExpression(TShiftExpression node) {
		return true;
	}
	public boolean visitBeforeTString(TString node) {
		return true;
	}
	public boolean visitBeforeTUnaryExpression(TUnaryExpression node) {
		return true;
	}
	public boolean visitBeforeTUnaryOperator(TUnaryOperator node) {
		return true;
	}
	
	public TAdditiveExpression visitAfterTAdditiveExpression(TAdditiveExpression node) {
		return null;
	}
	public TAndExpression visitAfterTAndExpression(TAndExpression node) {
		return null;
	}
	public TArgumentExpressionList visitAfterTArgumentExpressionList(TArgumentExpressionList node) {
		return null;
	}
	public TAssignmentExpression visitAfterTAssignmentExpression(TAssignmentExpression node) {
		return null;
	}
	public TAssignmentOperator visitAfterTAssignmentOperator(TAssignmentOperator node) {
		return null;
	}
	public TCastExpression visitAfterTCastExpression(TCastExpression node) {
		return null;
	}
	public TConditionalExpression visitAfterTConditionalExpression(TConditionalExpression node) {
		return null;
	}
	public TCompExpression visitAfterTCompExpression(TCompExpression node) {
		return null;
	}
	public TCompExpressionList visitAfterTCompExpressionList(TCompExpressionList node) {
		return null;
	}
	public TConstant visitAfterTConstant(TConstant node) {
		return null;
	}
	public TConstantExpression visitAfterTConstantExpression(TConstantExpression node) {
		return null;
	}
	public TEnumerationConstant visitAfterTEnumerationConstant(TEnumerationConstant node) {
		return null;
	}
	public TEqualityExpression visitAfterTEqualityExpression(TEqualityExpression node) {
		return null;
	}
	public TExclusiveOrExpression visitAfterTExclusiveOrExpression(TExclusiveOrExpression node) {
		return null;
	}
	public TExpression visitAfterTExpression(TExpression node) {
		return null;
	}
	public TGenericAssociation visitAfterTGenericAssociation(TGenericAssociation node) {
		return null;
	}
	public TGenericAssocList visitAfterTGenericAssocList(TGenericAssocList node) {
		return null;
	}
	public TGenericSelection visitAfterTGenericSelection(TGenericSelection node) {
		return null;
	}
	public TInclusiveOrExpression visitAfterTInclusiveOrExpression(TInclusiveOrExpression node) {
		return null;
	}
	public TLogicalAndExpression visitAfterTLogicalAndExpression(TLogicalAndExpression node) {
		return null;
	}
	public TLogicalOrExpression visitAfterTLogicalOrExpression(TLogicalOrExpression node) {
		return null;
	}
	public TMacroArgumentList visitAfterTMacroArgumentList(TMacroArgumentList node) {
		return null;
	}
	public TMacroSpecifier visitAfterTMacroSpecifier(TMacroSpecifier node) {
		return null;
	}
	public TMacroSpecifierList visitAfterTMacroSpecifierList(TMacroSpecifierList node) {
		return null;
	}
	public TMultiplicativeExpression visitAfterTMultiplicativeExpression(TMultiplicativeExpression node) {
		return null;
	}
	public TPostfixExpression visitAfterTPostfixExpression(TPostfixExpression node) {
		return null;
	}
	public TPostfixExpression visitAfterTPostfixExpressionA(TPostfixExpressionA node) {
		return null;
	}
	public TPostfixExpression visitAfterTPostfixExpressionID(TPostfixExpressionID node) {
		return null;
	}
	public TPostfixExpression visitAfterTPostfixExpressionIz(TPostfixExpressionIz node) {
		return null;
	}
	public TPostfixExpression visitAfterTPostfixExpressionIv(TPostfixExpressionIv node) {
		return null;
	}
	public TPostfixExpression visitAfterTPostfixExpressionM(TPostfixExpressionM node) {
		return null;
	}
	public TPostfixExpression visitAfterTPostfixExpressionP(TPostfixExpressionP node) {
		return null;
	}
	public TPrimaryExpression visitAfterTPrimaryExpression(TPrimaryExpression node) {
		return null;
	}
	public TRelationalExpression visitAfterTRelationalExpression(TRelationalExpression node) {
		return null;
	}
	public TSetExpression visitAfterTSetExpression(TSetExpression node) {
		return null;
	}
	public TSetExpression visitAfterTSetExpressionSO(TSetExpressionSO node) {
		return null;
	}
	public TSetExpression visitAfterTSetExpressionU(TSetExpressionU node) {
		return null;
	}
	public TShiftExpression visitAfterTShiftExpression(TShiftExpression node) {
		return null;
	}
	public TString visitAfterTString(TString node) {
		return null;
	}
	public TUnaryExpression visitAfterTUnaryExpression(TUnaryExpression node) {
		return null;
	}
	public TUnaryOperator visitAfterTUnaryOperator(TUnaryOperator node) {
		return null;
	}

	// symbols
	public DefaultTreeNodeSymbol visitTSAddAssign(TSAddAssign symbol) {
		return null;
	}
	public DefaultTreeNodeSymbol visitTSAddOp(TSAddOp symbol) {
		return null;
	}
	public DefaultTreeNodeSymbol visitTSAlignas(TSAlignas symbol) {
		return null;
	}
	public DefaultTreeNodeSymbol visitTSAlignof(TSAlignof symbol) {
		return null;
	}
	public DefaultTreeNodeSymbol visitTSAndAssign(TSAddAssign symbol) {
		return null;
	}
	public DefaultTreeNodeSymbol visitTSAndOp(TSAndOp symbol) {
		return null;
	}
	public DefaultTreeNodeSymbol visitTSAssign(TSAssign symbol) {
		return null;
	}
	public DefaultTreeNodeSymbol visitTSAtomic(TSAtomic symbol) {
		return null;
	}
	public DefaultTreeNodeSymbol visitTSAuto(TSAuto symbol) {
		return null;
	}
	public DefaultTreeNodeSymbol visitTSBandOp(TSBandOp symbol) {
		return null;
	}
	public DefaultTreeNodeSymbol visitTSBool(TSBool symbol) {
		return null;
	}
	public DefaultTreeNodeSymbol visitTSBorOp(TSBorOp symbol) {
		return null;
	}
	public DefaultTreeNodeSymbol visitTSBraceLeft(TSBraceLeft symbol) {
		return null;
	}
	public DefaultTreeNodeSymbol visitTSBraceRight(TSBraceRight symbol) {
		return null;
	}
	public DefaultTreeNodeSymbol visitTSBracketLeft(TSBracketLeft symbol) {
		return null;
	}
	public DefaultTreeNodeSymbol visitTSBracketRight(TSBracketRight symbol) {
		return null;
	}
	public DefaultTreeNodeSymbol visitTSBreak(TSBreak symbol) {
		return null;
	}
	public DefaultTreeNodeSymbol visitTSCase(TSCase symbol) {
		return null;
	}
	public DefaultTreeNodeSymbol visitTSChar(TSChar symbol) {
		return null;
	}
	public DefaultTreeNodeSymbol visitTSColon(TSColon symbol) {
		return null;
	}
	public DefaultTreeNodeSymbol visitTSComma(TSComma symbol) {
		return null;
	}
	public DefaultTreeNodeSymbol visitTSComplex(TSComplex symbol) {
		return null;
	}
	public DefaultTreeNodeSymbol visitTSCompOp(TSCompOp symbol) {
		return null;
	}
	public DefaultTreeNodeSymbol visitTSConst(TSConst symbol) {
		return null;
	}
	public DefaultTreeNodeSymbol visitTSContinue(TSContinue symbol) {
		return null;
	}
	public DefaultTreeNodeSymbol visitTSCut(TSCut symbol) {
		return null;
	}
	public DefaultTreeNodeSymbol visitTSDecOp(TSDecOp symbol) {
		return null;
	}
	public DefaultTreeNodeSymbol visitTSDefault(TSDefault symbol) {
		return null;
	}
	public DefaultTreeNodeSymbol visitTSDivAssign(TSDivAssign symbol) {
		return null;
	}
	public DefaultTreeNodeSymbol visitTSDivOp(TSDivOp symbol) {
		return null;
	}
	public DefaultTreeNodeSymbol visitTSDo(TSDo symbol) {
		return null;
	}
	public DefaultTreeNodeSymbol visitTSDot(TSDot symbol) {
		return null;
	}
	public DefaultTreeNodeSymbol visitTSDouble(TSDouble symbol) {
		return null;
	}
	public DefaultTreeNodeSymbol visitTSEllipsis(TSEllipsis symbol) {
		return null;
	}
	public DefaultTreeNodeSymbol visitTSElse(TSElse symbol) {
		return null;
	}
	public DefaultTreeNodeSymbol visitTSEnum(TSEnum symbol) {
		return null;
	}
	public DefaultTreeNodeSymbol visitTSEnumerationConstant(TSEnumerationConstant symbol) {
		return null;
	}
	public DefaultTreeNodeSymbol visitTSEqOp(TSEqOp symbol) {
		return null;
	}
	public DefaultTreeNodeSymbol visitTSExcl(TSExcl symbol) {
		return null;
	}
	public DefaultTreeNodeSymbol visitTSExtern(TSExtern symbol) {
		return null;
	}
	public DefaultTreeNodeSymbol visitTSFConstant(TSFConstant symbol) {
		return null;
	}
	public DefaultTreeNodeSymbol visitTSFinally(TSFinally symbol) {
		return null;
	}
	public DefaultTreeNodeSymbol visitTSFloat(TSFloat symbol) {
		return null;
	}
	public DefaultTreeNodeSymbol visitTSFor(TSFor symbol) {
		return null;
	}
	public DefaultTreeNodeSymbol visitTSFuncName(TSFuncName symbol) {
		return null;
	}
	public DefaultTreeNodeSymbol visitTSGenerator(TSGenerator symbol) {
		return null;
	}
	public DefaultTreeNodeSymbol visitTSGeneric(TSGeneric symbol) {
		return null;
	}
	public DefaultTreeNodeSymbol visitTSGeOp(TSGeOp symbol) {
		return null;
	}
	public DefaultTreeNodeSymbol visitTSGoto(TSGoto symbol) {
		return null;
	}
	public DefaultTreeNodeSymbol visitTSGtOp(TSGtOp symbol) {
		return null;
	}
	public DefaultTreeNodeSymbol visitTSIConstant(TSIConstant symbol) {
		return null;
	}
	public DefaultTreeNodeSymbol visitTSIdentifier(TSIdentifier symbol) {
		return null;
	}
	public DefaultTreeNodeSymbol visitTSIf(TSIf symbol) {
		return null;
	}
	public DefaultTreeNodeSymbol visitTSImaginary(TSImaginary symbol) {
		return null;
	}
	public DefaultTreeNodeSymbol visitTSIncOp(TSIncOp symbol) {
		return null;
	}
	public DefaultTreeNodeSymbol visitTSInline(TSInline symbol) {
		return null;
	}
	public DefaultTreeNodeSymbol visitTSInt(TSInt symbol) {
		return null;
	}
	public DefaultTreeNodeSymbol visitTSLeftAssign(TSLeftAssign symbol) {
		return null;
	}
	public DefaultTreeNodeSymbol visitTSLeftOp(TSLeftOp symbol) {
		return null;
	}
	public DefaultTreeNodeSymbol visitTSLeOp(TSLeOp symbol) {
		return null;
	}
	public DefaultTreeNodeSymbol visitTSLong(TSLong symbol) {
		return null;
	}
	public DefaultTreeNodeSymbol visitTSLtOp(TSLtOp symbol) {
		return null;
	}
	public DefaultTreeNodeSymbol visitTSMacro(TSMacro symbol) {
		return null;
	}
	public DefaultTreeNodeSymbol visitTSMacroType(TSMacroType symbol) {
		return null;
	}
	public DefaultTreeNodeSymbol visitTSModAssign(TSModAssign symbol) {
		return null;
	}
	public DefaultTreeNodeSymbol visitTSModOp(TSModOp symbol) {
		return null;
	}
	public DefaultTreeNodeSymbol visitTSMulAssign(TSMulAssign symbol) {
		return null;
	}
	public DefaultTreeNodeSymbol visitTSMulOp(TSMulOp symbol) {
		return null;
	}
	public DefaultTreeNodeSymbol visitTSNeOp(TSNeOp symbol) {
		return null;
	}
	public DefaultTreeNodeSymbol visitTSNoreturn(TSNoreturn symbol) {
		return null;
	}
	public DefaultTreeNodeSymbol visitTSOrAssign(TSOrAssign symbol) {
		return null;
	}
	public DefaultTreeNodeSymbol visitTSOrOp(TSOrOp symbol) {
		return null;
	}
	public DefaultTreeNodeSymbol visitTSParLeft(TSParLeft symbol) {
		return null;
	}
	public DefaultTreeNodeSymbol visitTSParRight(TSParRight symbol) {
		return null;
	}
	public DefaultTreeNodeSymbol visitTSPtrOp(TSPtrOp symbol) {
		return null;
	}
	public DefaultTreeNodeSymbol visitTSQues(TSQues symbol) {
		return null;
	}
	public DefaultTreeNodeSymbol visitTSRegister(TSRegister symbol) {
		return null;
	}
	public DefaultTreeNodeSymbol visitTSRestrict(TSRestrict symbol) {
		return null;
	}
	public DefaultTreeNodeSymbol visitTSReturn(TSReturn symbol) {
		return null;
	}
	public DefaultTreeNodeSymbol visitTSRightAssign(TSRightAssign symbol) {
		return null;
	}
	public DefaultTreeNodeSymbol visitTSRightOp(TSRightOp symbol) {
		return null;
	}
	public DefaultTreeNodeSymbol visitTSSemicolon(TSSemicolon symbol) {
		return null;
	}
	public DefaultTreeNodeSymbol visitTSSetOp(TSSetOp symbol) {
		return null;
	}
	public DefaultTreeNodeSymbol visitTSShort(TSShort symbol) {
		return null;
	}
	public DefaultTreeNodeSymbol visitTSSigned(TSSigned symbol) {
		return null;
	}
	public DefaultTreeNodeSymbol visitTSSizeof(TSSizeof symbol) {
		return null;
	}
	public DefaultTreeNodeSymbol visitTSStatic(TSStatic symbol) {
		return null;
	}
	public DefaultTreeNodeSymbol visitTSStaticAssert(TSStaticAssert symbol) {
		return null;
	}
	public DefaultTreeNodeSymbol visitTSStringLiteral(TSStringLiteral symbol) {
		return null;
	}
	public DefaultTreeNodeSymbol visitTSStruct(TSStruct symbol) {
		return null;
	}
	public DefaultTreeNodeSymbol visitTSSubAssign(TSSubAssign symbol) {
		return null;
	}
	public DefaultTreeNodeSymbol visitTSSubOp(TSSubOp symbol) {
		return null;
	}
	public DefaultTreeNodeSymbol visitTSSwitch(TSSwitch symbol) {
		return null;
	}
	public DefaultTreeNodeSymbol visitTSThreadLocal(TSThreadLocal symbol) {
		return null;
	}
	public DefaultTreeNodeSymbol visitTSTilde(TSTilde symbol) {
		return null;
	}
	public DefaultTreeNodeSymbol visitTSTypedef(TSTypedef symbol) {
		return null;
	}
	public DefaultTreeNodeSymbol visitTSTypedefName(TSTypedefName symbol) {
		return null;
	}
	public DefaultTreeNodeSymbol visitTSUnion(TSUnion symbol) {
		return null;
	}
	public DefaultTreeNodeSymbol visitTSUnsigned(TSUnsigned symbol) {
		return null;
	}
	public DefaultTreeNodeSymbol visitTSVoid(TSVoid symbol) {
		return null;
	}
	public DefaultTreeNodeSymbol visitTSVolatile(TSVolatile symbol) {
		return null;
	}
	public DefaultTreeNodeSymbol visitTSWhile(TSWhile symbol) {
		return null;
	}
	public DefaultTreeNodeSymbol visitTSWith(TSWith symbol) {
		return null;
	}
	public DefaultTreeNodeSymbol visitTSXorAssign(TSXorAssign symbol) {
		return null;
	}
	public DefaultTreeNodeSymbol visitTSXorOp(TSXorOp symbol) {
		return null;
	}
	public DefaultTreeNodeSymbol visitTSYield(TSYield symbol) {
		return null;
	}
}
