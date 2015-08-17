package tree.declarations;

import tree.DefaultTreeNode;
import tree.expressions.TConstantExpression;
import tree.symbols.TSComma;
import tree.symbols.TSParLeft;
import tree.symbols.TSParRight;
import tree.symbols.TSSemicolon;
import tree.symbols.TSStaticAssert;
import tree.symbols.TSStringLiteral;

public class TStaticAssertDeclaration extends DefaultTreeNode {

	public TStaticAssertDeclaration(TStaticAssertDeclaration node) {
		super(node);
	}
	
	public TStaticAssertDeclaration(TSStaticAssert statassert, TSParLeft par_left, TConstantExpression cexpr, TSComma comma, TSStringLiteral str, TSParRight par_right, TSSemicolon semicolon) {
		addChild(statassert);
		addChild(par_left);
		addChild(cexpr);
		addChild(comma);
		addChild(str);
		addChild(par_right);
		addChild(semicolon);
	}
	
}
