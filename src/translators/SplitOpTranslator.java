package translators;

import java.util.LinkedList;

import java_cup.runtime.ComplexSymbolFactory.Location;

import tree.DefaultTreeNode;
import tree.DefaultTreeNodeSymbol;
import tree.declarations.TAbstractDeclarator;
import tree.declarations.TAlignmentSpecifier;
import tree.declarations.TEnumerator;
import tree.declarations.TParameterDeclaration;
import tree.declarations.TPointerComprehension;
import tree.declarations.TStructDeclarator;
import tree.expressions.TAssignmentExpression;
import tree.expressions.TCastExpression;
import tree.expressions.TExpression;
import tree.expressions.TMacroSpecifier;
import tree.expressions.TMacroSpecifierList;
import tree.expressions.TPostfixExpressionP;
import tree.expressions.TPrimaryExpression;
import tree.expressions.TUnaryExpression;
import tree.expressions.TUnaryOperator;
import tree.other.CommonDeclaration;
import tree.statements.TBlockItem;
import tree.statements.TBlockItemEndSplit;
import tree.statements.TBlockItemList;
import tree.statements.TBlockItemS;
import tree.statements.TBlockItemSplit;
import tree.statements.TCompoundStatement;
import tree.statements.TExpressionStatement;
import tree.statements.TIterationStatement;
import tree.statements.TLabeledStatement;
import tree.statements.TSelectionStatement;
import tree.statements.TStatement;
import tree.symbols.TSBorOp;
import tree.symbols.TSBraceLeft;
import tree.symbols.TSBraceRight;
import tree.symbols.TSSemicolon;
import tree.symbols.TSSwitch;
import tree.translation_unit.TFunctionDefinition;
import tree.translation_unit.TGeneratorDefinition;
import errors.CXInternalError;
import errors.CXSyntaxError;
import generation.Medium;

public class SplitOpTranslator extends DefaultTranslator {

	public SplitOpTranslator(Medium medium) {
		super(medium, new String[] {DeclarationTranslator.class.getSimpleName(), GeneratorTranslator.class.getSimpleName()});
	}
	
	class SplitExpression {
		private TCastExpression cexpr;
		private int counter;

		public SplitExpression(TCastExpression cexpr, int counter) {
			this.cexpr = new TCastExpression(cexpr);
			this.counter = counter;
		}

		public TCastExpression getCastExpression() {
			return cexpr;
		}

		public int getCounter() {
			return counter;
		}
	}
	
	private LinkedList<Boolean> canBreak = new LinkedList<Boolean>();
	private LinkedList<Boolean> canContinue = new LinkedList<Boolean>();
	
	private LinkedList<CommonDeclaration> functions = new LinkedList<CommonDeclaration>();
	private LinkedList<Boolean> containsSplittingArea = new LinkedList<Boolean>();
	private LinkedList<LinkedList<TBlockItemEndSplit>> outerss = new LinkedList<LinkedList<TBlockItemEndSplit>>();
	@Override
	public boolean visitBeforeTFunctionDefinition(TFunctionDefinition node) {
		if (node instanceof TGeneratorDefinition)
			// prevent from double execution
			return true;
		functions.push(node);
		outerss.push(new LinkedList<TBlockItemEndSplit>());
		containsSplittingArea.push(false);
		
		canBreak.push(false);
		canContinue.push(false);
		return true;
	}
	@Override
	public TFunctionDefinition visitAfterTFunctionDefinition(TFunctionDefinition node) {
		if (node instanceof TGeneratorDefinition)
			// prevent from double execution
			return null;
		if (containsSplittingArea.pop()) {
			LinkedList<TBlockItem> items = new LinkedList<TBlockItem>();
			
			TMacroSpecifier mspec;
			if (getReturnType().isVoid()) {
				mspec = TMacroSpecifier.from("cx_func_start_void", new DefaultTreeNode[] {
				});
			} else {
				mspec = TMacroSpecifier.from("cx_func_start", new DefaultTreeNode[] {
						new TParameterDeclaration(getReturnType())
				});
			}
			TPrimaryExpression pexpr = new TPrimaryExpression(new TMacroSpecifierList(mspec));
			TExpression expr = TExpression.from(new TUnaryExpression(new TPostfixExpressionP(pexpr)));
			items.add(new TBlockItemS(new TExpressionStatement(expr , new TSSemicolon())));
			
			items.add(new TBlockItemS(node.getCompoundStatement()));
			
			Location right = node.getRight();
			node.setCompoundStatement(new TCompoundStatement(new TSBraceLeft(), TBlockItemList.from(items), new TSBraceRight()));
			node.setRight(right);
			
		}
		
		functions.pop();
		outerss.pop();
		
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
	
	private int counter = 0;
	private LinkedList<Boolean> containsSplitOp = new LinkedList<Boolean>();
	
	private LinkedList<LinkedList<SplitExpression>> splitexprss = new LinkedList<LinkedList<SplitExpression>>();
	
	@Override
	public DefaultTreeNodeSymbol visitTSBorOp(TSBorOp symbol) {
		if (symbol.getParent() instanceof TUnaryOperator) {
			if (functions.isEmpty()) {
				throw new CXSyntaxError("split operators outside functions are not allowed", symbol);
			}
			containsSplitOp.pop();
			containsSplitOp.push(true);
			
			containsSplittingArea.pop();
			containsSplittingArea.push(true);
		}
		return null;
	}
	
	@Override
	public boolean visitBeforeTUnaryExpression(TUnaryExpression node) {
		containsSplitOp.push(false);
		return true;
	}
	@Override
	public TUnaryExpression visitAfterTUnaryExpression(TUnaryExpression node) {
		if (containsSplitOp.pop()) {
			TCastExpression cexpr = node.getCastExpression();
			if (cexpr == null) {
				throw new CXInternalError("something went wrong during translation", node);
			}
			splitexprss.peek().add(new SplitExpression(cexpr, counter));
			TUnaryExpression uexpr = TUnaryExpression.from("cx_value("+counter+")");
			counter += 4;
			uexpr.setLeft(node.getLeft());
			uexpr.setRight(node.getRight());
			return uexpr;
		}
		return null;
	}
	
	protected TParameterDeclaration getReturnType() {
		CommonDeclaration cdecl = functions.peek();
		TParameterDeclaration pdecl = null;
		TPointerComprehension pc = cdecl.getDeclarator().getPointerComprehension();
		if (pc == null)
			pdecl = new TParameterDeclaration(cdecl.getDeclarationSpecifiers());
		else
			pdecl = new TParameterDeclaration(cdecl.getDeclarationSpecifiers(), new TAbstractDeclarator(pc));
		return pdecl;
	}
	
	protected TBlockItemSplit getBlockItemSplit(SplitExpression sexpr) {
		//$split(12, int, getPrimes(range(1000, 2000)))
		
		TPrimaryExpression pexpr = new TPrimaryExpression(new TMacroSpecifierList(TMacroSpecifier.from("cx_split", new DefaultTreeNode[] {
				TAssignmentExpression.from(TUnaryExpression.from(sexpr.getCounter())),
				TAssignmentExpression.from(sexpr.getCastExpression()),
		})));
		TExpression expr = TExpression.from(new TUnaryExpression(new TPostfixExpressionP(pexpr)));
		
		return new TBlockItemSplit(sexpr.getCounter(), new TExpressionStatement(expr, new TSSemicolon()));
	}
	protected TBlockItemEndSplit getBlockItemEndSplit(SplitExpression sexpr, LinkedList<TBlockItemEndSplit> outers) {
		//$end_split(12)
		
		TBlockItemEndSplit outer = null;
		for (TBlockItemEndSplit o : outers) {
			if (o == null)
				continue;
			outer = o;
			break;
		}
		
		LinkedList<TMacroSpecifier> mspecs = new LinkedList<TMacroSpecifier>();
		
		mspecs.add(TMacroSpecifier.from("cx_end_split", new DefaultTreeNode[] {
				TAssignmentExpression.from(TUnaryExpression.from(sexpr.getCounter())),
		}));

		String ending = outer == null ? "" : "_";
		if (canBreak.peek()) {
			mspecs.add(TMacroSpecifier.from("cx_end_split_break"+ending, new DefaultTreeNode[] {
					TAssignmentExpression.from(TUnaryExpression.from(sexpr.getCounter())),
			}));
		}
		if (canContinue.peek()) {
			mspecs.add(TMacroSpecifier.from("cx_end_split_continue"+ending, new DefaultTreeNode[] {
					TAssignmentExpression.from(TUnaryExpression.from(sexpr.getCounter())),
			}));
		}
		
		if (outer == null) {
			String voi = getReturnType().isVoid() ? "_void" : "";
			mspecs.add(TMacroSpecifier.from("cx_end_split_other"+voi, new DefaultTreeNode[] {
					TAssignmentExpression.from(TUnaryExpression.from(sexpr.getCounter())),
			}));
		} else {
			mspecs.add(TMacroSpecifier.from("cx_end_split_other_", new DefaultTreeNode[] {
					TAssignmentExpression.from(TUnaryExpression.from(sexpr.getCounter())),
			}));
		}
		
		TExpression expr = TExpression.from(new TUnaryExpression(new TPostfixExpressionP(new TPrimaryExpression(TMacroSpecifierList.from(mspecs)))));
		return new TBlockItemEndSplit(sexpr.getCounter(), new TExpressionStatement(expr, new TSSemicolon()));
	}

	// block item
	private LinkedList<LinkedList<TBlockItem>> itemss = new LinkedList<LinkedList<TBlockItem>>();
	private LinkedList<LinkedList<TBlockItem>> enditemss = new LinkedList<LinkedList<TBlockItem>>();
	@Override
	public boolean visitBeforeTBlockItemList(TBlockItemList node) {
		itemss.push(new LinkedList<TBlockItem>());
		enditemss.push(new LinkedList<TBlockItem>());
		outerss.peek().push(null);
		return true;
	}
	@Override
	public TBlockItemList visitAfterTBlockItemList(TBlockItemList node) {
		for (int i = 0; i<enditemss.peek().size(); i++) {
			canBreak.pop();
			canContinue.pop();
		}
		LinkedList<TBlockItem> items = itemss.pop();
		items.addAll(enditemss.pop());
		outerss.peek().pop();
		TBlockItemList bilist = TBlockItemList.from(items);
		return bilist;
	}
	
	@Override
	public boolean visitBeforeTBlockItem(TBlockItem node) {
		splitexprss.add(new LinkedList<SplitExpression>());
		return true;
	}
	@Override
	public TBlockItem visitAfterTBlockItem(TBlockItem node) {
		LinkedList<TBlockItem> items = itemss.peek();
		LinkedList<TBlockItem> enditems = enditemss.peek();
		
		LinkedList<SplitExpression> splitexprs = splitexprss.pop();
		if (!splitexprs.isEmpty()) {
			
			for (SplitExpression sexpr : splitexprs) {
				
				items.add(getBlockItemSplit(sexpr));
				
				TBlockItemEndSplit endsplit = getBlockItemEndSplit(sexpr, outerss.peek());
				outerss.peek().pop();
				outerss.peek().push(endsplit);
				enditems.add(0, endsplit);
				
				canBreak.push(false);
				canContinue.push(false);
			}
			
		}
		items.add(node);
		return null;
	}
	
	// statement
	@Override
	public boolean visitBeforeTStatement(TStatement node) {
		if (node.getParent() instanceof TBlockItem)
			return true;
		
		splitexprss.add(new LinkedList<SplitExpression>());
		return true;
	}
	@Override
	public TStatement visitAfterTStatement(TStatement node) {
		if (node.getParent() instanceof TBlockItem)
			return null;
		
		LinkedList<SplitExpression> splitexprs = splitexprss.pop();
		if (!splitexprs.isEmpty()) {
			
			LinkedList<TBlockItem> items = new LinkedList<TBlockItem>();
			LinkedList<TBlockItem> enditems = new LinkedList<TBlockItem>();

			for (SplitExpression sexpr : splitexprs) {
				
				items.add(getBlockItemSplit(sexpr));
				
				TBlockItemEndSplit endsplit = getBlockItemEndSplit(sexpr, outerss.peek());
				outerss.peek().pop();
				outerss.peek().push(endsplit);
				enditems.add(0, endsplit);
				
				canBreak.push(false);
				canContinue.push(false);
			}
			
			for (int i = 0; i<splitexprs.size(); i++) {
				canBreak.pop();
				canContinue.pop();
			}
			
			items.add(new TBlockItemS(node));
			items.addAll(enditems);
			
			TCompoundStatement cstat = new TCompoundStatement(new TSBraceLeft(), TBlockItemList.from(items), new TSBraceRight());
			cstat.setLeft(node.getLeft());
			cstat.setRight(node.getRight());
			return cstat;
		}
		return null;
	}
	
	
	
	// forbidden locations
	protected void forbiddenBefore() {
		splitexprss.add(new LinkedList<SplitExpression>());
	}
	protected void forbiddenAfter(DefaultTreeNode node, String locationName) {
		LinkedList<SplitExpression> splitexprs = splitexprss.pop();
		if (!splitexprs.isEmpty()) {
			throw new CXSyntaxError("split expressions in "+locationName+" are not allowed", node);
		}
	}
	
	@Override
	public boolean visitBeforeTStructDeclarator(TStructDeclarator node) {
		forbiddenBefore();
		return true;
	}
	@Override
	public TStructDeclarator visitAfterTStructDeclarator(TStructDeclarator node) {
		forbiddenAfter(node, "structs");
		return null;
	}
	@Override
	public boolean visitBeforeTEnumerator(TEnumerator node) {
		forbiddenBefore();
		return true;
	}
	@Override
	public TEnumerator visitAfterTEnumerator(TEnumerator node) {
		forbiddenAfter(node, "enumerators");
		return null;
	}
	@Override
	public boolean visitBeforeTAlignmentSpecifier(TAlignmentSpecifier node) {
		forbiddenBefore();
		return true;
	}
	@Override
	public TAlignmentSpecifier visitAfterTAlignmentSpecifier(TAlignmentSpecifier node) {
		forbiddenAfter(node, "alignment specifiers");
		return null;
	}
	@Override
	public boolean visitBeforeTLabeledStatement(TLabeledStatement node) {
		forbiddenBefore();
		return true;
	}
	@Override
	public TLabeledStatement visitAfterTLabeledStatement(TLabeledStatement node) {
		forbiddenAfter(node, "case specifiers");
		return null;
	}
	@Override
	public boolean visitBeforeTIterationStatement(TIterationStatement node) {
		canBreak.push(true);
		canContinue.push(true);
		forbiddenBefore();
		return true;
	}
	@Override
	public TIterationStatement visitAfterTIterationStatement(TIterationStatement node) {
		canBreak.pop();
		canContinue.pop();
		forbiddenAfter(node, "iteration statement headers");
		return null;
	}
	
}
