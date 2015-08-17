package tree.statements;

import tree.expressions.TConstantExpression;
import tree.symbols.TSCase;
import tree.symbols.TSColon;
import tree.symbols.TSDefault;
import tree.symbols.TSIdentifier;

public class TLabeledStatement extends TStatement {

	public TLabeledStatement(TLabeledStatement node) {
		super(node);
	}
	
	public TLabeledStatement(TSIdentifier id, TSColon colon, TStatement statement) {
		addChild(id);
		addChild(colon);
		addChild(statement);
	}
	
	public TLabeledStatement(TSCase cas, TConstantExpression cexpr, TSColon colon, TStatement statement) {
		addChild(cas);
		addChild(cexpr);
		addChild(colon);
		addChild(statement);
	}
	
	public TLabeledStatement(TSDefault def, TSColon colon, TStatement statement) {
		addChild(def);
		addChild(colon);
		addChild(statement);
	}
}
