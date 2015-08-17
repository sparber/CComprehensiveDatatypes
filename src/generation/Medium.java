package generation;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.ListIterator;

import java_cup.runtime.ComplexSymbolFactory.Location;
import lexer.LocationMethods;
import tree.EmptyTreeNode;
import tree.TreeNodePrintable;
import tree.declarations.TParameterDeclaration;
import tree.other.CompTypeName;
import tree.other.WhiteSpaceFiller;
import tree.other.WhiteSpaceSkipper;
import errors.CXError;
import errors.CXInternalError;

public class Medium {

	public String unit;
	
	public Medium(String unit) {
		this.unit = unit;
	}

	private LinkedList<Message> messages = new LinkedList<Message>();
	private boolean containsErrors = false;
	private boolean calmed = false;
	
	public void setCalmedMode(boolean calmed) {
		this.calmed = calmed;
	}

	public void addMessage(Message message) {
		if (calmed)
			return;
		if (message.type == Message.ERROR)
			containsErrors  = true;
		messages.offerLast(message);
	}
	
	public void error(CXError error) {
		if (calmed)
			return;
		containsErrors  = true;
		addMessage(new Message(Message.ERROR, error.getNode(), error.getMessage()));
	}
	public void error(Location left, Location right, String message) {
		if (calmed)
			return;
		containsErrors  = true;
		addMessage(new Message(Message.ERROR, new EmptyTreeNode(left, right), message));
	}
	public void warning(Location left, Location right, String message) {
		if (calmed)
			return;
		addMessage(new Message(Message.WARNING, new EmptyTreeNode(left, right), message));
	}
	
	public boolean containsErrors() {
		return containsErrors;
	}

	public Message getNextMessage() {
		return messages.pollFirst();
	}
	
	public void outputMessages() {
		Message m = getNextMessage();
		while (m != null) {
			System.err.println(m);
			m = getNextMessage();
		}
	}


	private LinkedList<HashSet<String>> typenames = new LinkedList<HashSet<String>>();

	public void newScope() {
		typenames.push(new HashSet<String>());
	}
	public void deleteScope() {
		typenames.pop();
	}
	public void addType(String name){
		typenames.peek().add(name);
	}
	public boolean lookupType(String name) {
		for (HashSet<String> scope: typenames)
			if (scope.contains(name))
				return true;
		return false;
	}


	private LinkedList<CompTypeName> comptypenames = new LinkedList<CompTypeName>();

	public CompTypeName addCompType(TParameterDeclaration pdecl) {
		if (pdecl.getLeft() == null || pdecl.getRight() == null)
			throw new CXInternalError("missing location information", pdecl);
		return addCompType(new CompTypeName(pdecl));
	}
	public CompTypeName addCompType(CompTypeName tname){
		ListIterator<CompTypeName> iterator = comptypenames.listIterator();
		while (iterator.hasNext()) {
			CompTypeName tn = iterator.next();
			if (tn.equals(tname)) {
				if (LocationMethods.compareLocations(tname.getTypeName().getLeft(), tn.getTypeName().getLeft()) < 0) {
					iterator.set(tname);
					return tname;
				} else {
					return tn;
				}
			}
		}
		comptypenames.add(tname);
		return tname;
	}
	public LinkedList<CompTypeName> getCompTypeNames() {
		return comptypenames;
	}

	
	// list of all white space filling strings
	private HashMap<String,LinkedList<TreeNodePrintable>> fillers = new HashMap<String,LinkedList<TreeNodePrintable>>();

	public WhiteSpaceFiller addWhiteSpaceFiller(Location left, Location right, String filler) {
		if (left.getUnit() != right.getUnit())
			throw new CXInternalError("location units don't match");
		if (!fillers.containsKey(left.getUnit())) {
			fillers.put(left.getUnit(), new LinkedList<TreeNodePrintable>());
		}
		LinkedList<TreeNodePrintable> fs = fillers.get(left.getUnit());
		int i=0;
		for (TreeNodePrintable f : fs) {
			if (LocationMethods.compareLocations(f.getLeft(), left) > 0)
				break;
			i++;
		}
		WhiteSpaceFiller f = new WhiteSpaceFiller(left, right, filler);
		fs.add(i, f);
		return f;
	}
	public WhiteSpaceFiller addWhiteSpaceFillerAfter(TreeNodePrintable before, Location left, Location right, String filler) {
		if (left.getUnit() != right.getUnit())
			throw new CXInternalError("location units don't match");
		if (!fillers.containsKey(left.getUnit())) {
			fillers.put(left.getUnit(), new LinkedList<TreeNodePrintable>());
		}
		LinkedList<TreeNodePrintable> fs = fillers.get(left.getUnit());
		int i=0;
		for (TreeNodePrintable f : fs) {
			i++;
			if (f.equals(before))
				break;
		}
		WhiteSpaceFiller f = new WhiteSpaceFiller(left, right, filler);
		fs.add(i, f);
		return f;
	}
	public WhiteSpaceFiller addWhiteSpaceFillerAfter(TreeNodePrintable before, Location left, String filler) {
		return addWhiteSpaceFillerAfter(before, left, LocationMethods.calcRight(left, filler), filler);
	}
	public WhiteSpaceSkipper addWhiteSpaceSkipperAfter(TreeNodePrintable before) {
		if (!fillers.containsKey(unit)) {
			fillers.put(unit, new LinkedList<TreeNodePrintable>());
		}
		LinkedList<TreeNodePrintable> fs = fillers.get(unit);
		int i=0;
		for (TreeNodePrintable f : fs) {
			i++;
			if (f.equals(before))
				break;
		}
		WhiteSpaceSkipper f = new WhiteSpaceSkipper(before.getRight());
		fs.add(i, f);
		return f;
	}
	public LinkedList<TreeNodePrintable> getWhiteSpaceFillers(String unit) {
		if (fillers.containsKey(unit))
			return fillers.get(unit);
		else
			return new LinkedList<TreeNodePrintable>();
	}
	public LinkedList<TreeNodePrintable> getWhiteSpaceFillers() {
		return getWhiteSpaceFillers(unit);
	}
	public WhiteSpaceFiller addWhiteSpaceFiller(Location left, String filler) {
		return addWhiteSpaceFiller(left, LocationMethods.calcRight(left, filler), filler);
	}

	public String getUnit() {
		return unit;
	}
	
}
