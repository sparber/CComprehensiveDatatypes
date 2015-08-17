package tree.expressions;

import tree.declarations.TInitializerList;
import tree.declarations.TTypeName;
import tree.symbols.TSBraceLeft;
import tree.symbols.TSBraceRight;
import tree.symbols.TSComma;
import tree.symbols.TSParLeft;
import tree.symbols.TSParRight;

public class TPostfixExpressionIz extends TPostfixExpression {
	
	public TPostfixExpressionIz(TPostfixExpressionIz node) {
		super(node);
	}
	
	public TTypeName getTypeName() {
		return (TTypeName)getChild(1);
	}
	public TInitializerList getInitializerList() {
		return (TInitializerList)getChild(4);
	}

	public TPostfixExpressionIz(TSParLeft par_left, TTypeName type_name, TSParRight par_right, TSBraceLeft brace_left, TInitializerList init_list, TSBraceRight brace_right) {
		addChild(par_left);
		addChild(type_name);
		addChild(par_right);
		addChild(brace_left);
		addChild(init_list);
		addChild(brace_right);
	}

	public TPostfixExpressionIz(TSParLeft par_left, TTypeName type_name, TSParRight par_right, TSBraceLeft brace_left, TInitializerList init_list, TSComma comma, TSBraceRight brace_right) {
		addChild(par_left);
		addChild(type_name);
		addChild(par_right);
		addChild(brace_left);
		addChild(init_list);
		addChild(comma);
		addChild(brace_right);
	}
}
