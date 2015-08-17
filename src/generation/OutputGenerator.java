package generation;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.nio.charset.Charset;
import java.util.LinkedList;

import java_cup.runtime.ComplexSymbolFactory.Location;
import lexer.LocationMethods;
import tree.DefaultTreeNodeSymbol;
import tree.DefaultVisitor;
import tree.TreeNode;
import tree.TreeNodePrintable;
import tree.other.TreeRoot;
import tree.other.WhiteSpaceSkipper;
import tree.translation_unit.TTranslationUnit;

public class OutputGenerator extends DefaultVisitor {
	
	LinkedList<TreeNodePrintable> whitespace_orig;
	LinkedList<TreeNodePrintable> whitespace;
	Location last;
	private PrintStream ostream;
	
	public OutputGenerator(LinkedList<TreeNodePrintable> whitespace) {
		this(whitespace, System.out);
	}
	
	public OutputGenerator(LinkedList<TreeNodePrintable> whitespace, OutputStream ostream) {
		this.whitespace_orig = whitespace;
		this.ostream = new PrintStream(ostream);
	}

	public void fillWhiteSpace(Location left, Location right) {
		if (right == null || left == null)
			return;
		while (right.getLine() > left.getLine()) {
			left = new Location(left.getLine()+1, 0, left.getOffset()+1);
			ostream.println();
		}
		
		/*if (right.getLine() == left.getLine() && right.getColumn() < left.getColumn() && right.getColumn() < 2) {
			ostream.println();
			right = new Location(right.getLine(), 0, right.getOffset()+1);
		}*/
		
		while (right.getColumn() > left.getColumn()+1) {
			left = new Location(left.getLine(), left.getColumn()+1, left.getOffset()+1);
			ostream.print(" ");
		}
		
	}
	
	@Override
	public boolean visitBeforeTreeRoot(TreeRoot node) {
		whitespace = new LinkedList<TreeNodePrintable>(whitespace_orig);
		last = new Location(1, 0, 0);
		return true;
	}
	
	@Override
	public TTranslationUnit visitAfterTreeRoot(TreeRoot node) {
		while (!whitespace.isEmpty()) {
			output(whitespace.poll());
		}
		return null;
	}

	private void output(TreeNodePrintable symbol) {
		fillWhiteSpace(last, symbol.getLeft());
		if (symbol.getLeft() != null)
			last = symbol.getLeft();
		String text = symbol.getPrintableText();
		if (symbol.getLeft() == null)
			text = " "+text;
		if (symbol.getRight() == null)
			text = text+" ";
		ostream.print(text);
		last = symbol.getRight() != null ? symbol.getRight() : LocationMethods.calcRight(last, text);
	}

	@Override
	public DefaultTreeNodeSymbol visitDefaultTreeNodeSymbol(DefaultTreeNodeSymbol symbol) {
		while (symbol.getLeft() != null && !whitespace.isEmpty() && !(whitespace.peek() instanceof WhiteSpaceSkipper) && LocationMethods.compareLocations(whitespace.peek().getLeft(), symbol.getLeft()) <= 0) {
			output(whitespace.poll());
		}
		if (!whitespace.isEmpty() && (whitespace.peek() instanceof WhiteSpaceSkipper))
			whitespace.poll();
		output(symbol);
		return null;
	}
	
	public static String treeNodeToString(TreeNode tree) {
		ByteArrayOutputStream ostream = new ByteArrayOutputStream();
		OutputGenerator gen = new OutputGenerator(new LinkedList<TreeNodePrintable>(), ostream);
		new TreeRoot(tree).accept(gen);
		return new String(ostream.toByteArray(), Charset.defaultCharset());
	}
	
}
