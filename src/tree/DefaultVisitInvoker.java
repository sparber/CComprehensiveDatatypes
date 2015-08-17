package tree;

import java.lang.reflect.InvocationTargetException;
import java.util.Iterator;
import java.util.LinkedList;

import errors.CXError;
import errors.CXFatalError;
import errors.CXInternalError;

public abstract class DefaultVisitInvoker {
	
	public abstract void accept(DefaultVisitor visitor) throws Throwable;
	public abstract DefaultTreeNode getParent();
	public abstract boolean isDuringIteration();
	
	protected void replaceWith(TreeNode node, DefaultTreeNode parent) {
		if (isDuringIteration() || !parent.isDuringIteration()) {
			throw new CXInternalError("invalid call of replaceWith");
		}
		parent.getIterator().remove();
		node.setParent(parent);
		parent.getIterator().add(node);
	}

	@SuppressWarnings("unchecked")
	protected boolean invokeVisit(DefaultVisitor visitor, String beforeafter, boolean reversed) {
		DefaultTreeNode parent = getParent();
		
		LinkedList<Class<? extends DefaultVisitInvoker>> classes = new LinkedList<Class<? extends DefaultVisitInvoker>>();
		Class<? extends Object> clazz = getClass();
		while (!clazz.equals(DefaultVisitInvoker.class)) {
			classes.push((Class<? extends DefaultVisitInvoker>)clazz);
			clazz = clazz.getSuperclass();
		}
		
		Iterator<Class<? extends DefaultVisitInvoker>> it = reversed ? classes.descendingIterator() : classes.iterator();

		while (it.hasNext()) {
			Class<? extends DefaultVisitInvoker> cl = it.next();

			try {
				Object r = visitor.getClass().getMethod("visit"+beforeafter+cl.getSimpleName(), new Class[] {cl}).invoke(visitor, this);
				if (r instanceof Boolean && !((Boolean)r).booleanValue()) {
					// visit method requests not to go any deeper
					return false;
				} else if (r instanceof TreeNode) {
					TreeNode newNode = (TreeNode)r;
					if (newNode != null) {
						replaceWith(newNode, parent);
						return true;
					}
				}
			} catch (IllegalAccessException | IllegalArgumentException
					| SecurityException e) {
				throw new CXInternalError("something went wrong in invoking method", e);
			} catch (InvocationTargetException e) {
				
				Throwable error = e.getTargetException();
				if (!(error instanceof CXError)) {
					throw new CXInternalError("something went wrong in invoking method", error);
				}
				if (error instanceof CXFatalError) {
					throw (CXFatalError) error;
				}
				visitor.addError((CXError) error);
				
			} catch (NoSuchMethodException e) {
				System.err.println("error: method not defined: visit"+cl.getSimpleName());
			}
		}
		return true;
	}

}
