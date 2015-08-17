package translators;

import errors.CXImplicationError;
import generation.Medium;
import tree.DefaultVisitor;
import tree.other.TreeRoot;

public abstract class DefaultTranslator extends DefaultVisitor {
	
	private String className;
	private String[] preTranslators;

	public DefaultTranslator(Medium medium, String[] preTranslators) {
		super(medium);
		this.className = getClass().getSimpleName();
		this.preTranslators = preTranslators;
	}
	public DefaultTranslator(Medium  medium, String className, String[] preTranslators) {
		super(medium);
		this.className = className;
		this.preTranslators = preTranslators;
	}
	
	@Override
	public boolean visitBeforeTreeRoot(TreeRoot node) {
		if (!node.translatedBy(preTranslators)) {
			throw new CXImplicationError("Some of the required translations have not been made yet", node);
		}
		return true;
	}
	
	@Override
	public TreeRoot visitAfterTreeRoot(TreeRoot node) {
		node.addTranslator(className);
		return null;
	}

}
