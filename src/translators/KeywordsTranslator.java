package translators;

import java.util.LinkedList;

import tree.DefaultTreeNodeSymbol;
import tree.expressions.TAssignmentExpression;
import tree.expressions.TExpression;
import tree.expressions.TMacroSpecifier;
import tree.expressions.TMacroSpecifierList;
import tree.expressions.TPostfixExpressionP;
import tree.expressions.TPrimaryExpression;
import tree.expressions.TUnaryExpression;
import tree.statements.TBlockItemEndSplit;
import tree.statements.TBlockItemSplit;
import tree.statements.TExpressionStatement;
import tree.statements.TIterationStatement;
import tree.statements.TJumpStatement;
import tree.statements.TSelectionStatement;
import tree.statements.TStatement;
import tree.symbols.TSBreak;
import tree.symbols.TSContinue;
import tree.symbols.TSCut;
import tree.symbols.TSGoto;
import tree.symbols.TSReturn;
import tree.symbols.TSSemicolon;
import tree.symbols.TSSwitch;
import tree.symbols.TSWith;
import tree.symbols.TSYield;
import tree.translation_unit.TFunctionDefinition;
import tree.translation_unit.TGeneratorDefinition;
import errors.CXSyntaxError;
import generation.Medium;

public class KeywordsTranslator extends DefaultTranslator {

	public KeywordsTranslator(Medium medium) {
		super(medium, new String[] {
				GeneratorTranslator.class.getSimpleName(),
				SplitOpTranslator.class.getSimpleName()
		});
	}
	
	private LinkedList<Boolean> insideGenerator = new LinkedList<Boolean>();
	private LinkedList<Integer> insideSplit = new LinkedList<Integer>();
	private LinkedList<Boolean> canBreak = new LinkedList<Boolean>();
	private LinkedList<Boolean> canContinue = new LinkedList<Boolean>();
	@Override
	public boolean visitBeforeTFunctionDefinition(TFunctionDefinition node) {
		if (node instanceof TGeneratorDefinition)
			// prevent this method from double execution
			return true;
		insideGenerator.push(node.getParent() instanceof TGeneratorDefinition);
		insideSplit.push(-1);
		
		canBreak.push(false);
		canContinue.push(false);
		return true;
	}
	@Override
	public TFunctionDefinition visitAfterTFunctionDefinition(TFunctionDefinition node) {
		if (node instanceof TGeneratorDefinition)
			// prevent this method from double execution
			return null;
		insideGenerator.pop();
		insideSplit.pop();
		
		canBreak.pop();
		canContinue.pop();
		return null;
	}
	
	@Override
	public boolean visitBeforeTBlockItemSplit(TBlockItemSplit node) {
		insideSplit.push(node.getCounter());
		
		canBreak.push(false);
		canContinue.push(false);
		return true;
	}
	@Override
	public boolean visitBeforeTBlockItemEndSplit(TBlockItemEndSplit node) {
		insideSplit.pop();
		
		canBreak.pop();
		canContinue.pop();
		return true;
	}
	
	@Override
	public boolean visitBeforeTIterationStatement(TIterationStatement node) {
		canBreak.push(true);
		canContinue.push(true);
		return true;
	}
	@Override
	public TStatement visitAfterTIterationStatement(TIterationStatement node) {
		canBreak.pop();
		canContinue.pop();
		return null;
	}
	@Override
	public boolean visitBeforeTSelectionStatement(TSelectionStatement node) {
		if (node.getKeyword() instanceof TSSwitch) {
			canBreak.push(true);
		}
		return true;
	}
	@Override
	public TStatement visitAfterTSelectionStatement(TSelectionStatement node) {
		if (node.getKeyword() instanceof TSSwitch) {
			canBreak.pop();
		}
		return null;
	}
	
	@Override
	public TStatement visitAfterTJumpStatement(TJumpStatement node) {
		DefaultTreeNodeSymbol symbol = node.getKeyword();
		
		LinkedList<TMacroSpecifier> mspecs = new LinkedList<TMacroSpecifier>();
		
		if (insideSplit.peek() != -1) {
			// inside splitting area
			
			// extension keywords
			if (symbol instanceof TSWith) {
				
				mspecs.add(TMacroSpecifier.from("cx_with", new TAssignmentExpression[] {
						node.getAssignmentExpression(),
				}));
				
			}
			
			else if (symbol instanceof TSCut) {
				
				mspecs.add(TMacroSpecifier.from("cx_cut", new TAssignmentExpression[] {
				}));

			}
			
			else if (symbol instanceof TSYield) {
				if (!insideGenerator.peek())
					throw new CXSyntaxError("illegal yield instruction outside generator definition", node);
				
				if (node.hasAssignmentExpression()) {
					mspecs.add(TMacroSpecifier.from("cx_yield_", new TAssignmentExpression[] {
							node.getAssignmentExpression(),
					}));
				} else {
					mspecs.add(TMacroSpecifier.from("cx_yield_", new TAssignmentExpression[] {
							TAssignmentExpression.from(TUnaryExpression.from(0)),
					}));
				}
			}
			
			// standard keywords
			else if (symbol instanceof TSGoto) {
				throw new CXSyntaxError("illegal goto instruction inside splitting area", node);
			}

			else if (symbol instanceof TSReturn) {
				if (insideGenerator.peek()) {
					if (node.hasAssignmentExpression())
						throw new CXSyntaxError("illegal return instruction inside generator definition", node);
				
					mspecs.add(TMacroSpecifier.from("cx_generator_return_", null));
				} else {

					if (node.hasAssignmentExpression()) {
						mspecs.add(TMacroSpecifier.from("cx_return", new TAssignmentExpression[] {
								node.getAssignmentExpression(),
						}));
					} else {
						mspecs.add(TMacroSpecifier.from("cx_return_void", new TAssignmentExpression[] {
						}));
					}
					
				}
			}

		} else {
			// outside splitting area
			
			// extension keywords
			if (symbol instanceof TSWith) {
				throw new CXSyntaxError("illegal with instruction outside splitting area", node);
			}
			
			else if (symbol instanceof TSCut) {
				throw new CXSyntaxError("illegal cut instruction outside splitting area", node);
			}
			
			else if (symbol instanceof TSYield) {
				if (!insideGenerator.peek())
					throw new CXSyntaxError("illegal yield instruction outside generator definition", node);
				
				if (node.hasAssignmentExpression()) {
					mspecs.add(TMacroSpecifier.from("cx_yield", new TAssignmentExpression[] {
							node.getAssignmentExpression(),
					}));
				} else {
					mspecs.add(TMacroSpecifier.from("cx_yield", new TAssignmentExpression[] {
							TAssignmentExpression.from(TUnaryExpression.from(0)),
					}));
				}
			}
			
			// standard keywords
			else if (symbol instanceof TSGoto) {
				// no translation required
				return null;
			}
			
			else if (symbol instanceof TSReturn) {
				if (insideGenerator.peek()) {
					if (node.hasAssignmentExpression())
						throw new CXSyntaxError("illegal return instruction inside generator definition", node);
					
					mspecs.add(TMacroSpecifier.from("cx_generator_return", null));
				} else {
					return null;
				}
			}

		}

		if (symbol instanceof TSContinue) {
			if (canContinue.peek() || insideSplit.peek() == -1)
				// no translation required
				return null;
			mspecs.add(TMacroSpecifier.from("cx_continue", new TAssignmentExpression[] {
			}));
		}

		else if (symbol instanceof TSBreak) {
			if (canBreak.peek() || insideSplit.peek() == -1)
				// no translation required
				return null;
			mspecs.add(TMacroSpecifier.from("cx_break", new TAssignmentExpression[] {
			}));
		}
		
		if (mspecs.isEmpty())
			return null;

		TExpression expr = TExpression.from(new TUnaryExpression(new TPostfixExpressionP(new TPrimaryExpression(TMacroSpecifierList.from(mspecs)))));
		TExpressionStatement estat = new TExpressionStatement(expr, new TSSemicolon());
		estat.setLeft(node.getLeft());
		estat.setRight(node.getRight());
		return estat;
	}

}
