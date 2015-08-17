package tree.declarations;


public class TDeclarationSAD extends TDeclaration {
	
	public TDeclarationSAD(TDeclarationSAD node) {
		super(node);
	}

	public TStaticAssertDeclaration getStaticAssertDeclaration() {
		return (TStaticAssertDeclaration) getChild(0);
	}
	
	public TDeclarationSAD(TStaticAssertDeclaration stassdec) {
		addChild(stassdec);
	}
}
