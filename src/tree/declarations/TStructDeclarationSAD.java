package tree.declarations;


public class TStructDeclarationSAD extends TStructDeclaration {
	
	public TStructDeclarationSAD(TStructDeclarationSAD node) {
		super(node);
	}
	
	public TStructDeclarationSAD(TStaticAssertDeclaration stassdec) {
		addChild(stassdec);
	}
}
