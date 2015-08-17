package lexer;
import java_cup.runtime.ComplexSymbolFactory;
import java_cup.runtime.ComplexSymbolFactory.ComplexSymbol;
import java_cup.runtime.ComplexSymbolFactory.Location;
import generation.Medium;
import parser.Parser;
import tree.DefaultTreeNodeSymbol;
import tree.symbols.*;

import java.io.*;

import java.util.LinkedList;

%%

%public
%class Lexer
%cup
%char
%line
%column
%unicode

%{
    /* Local variables */
    
    private ComplexSymbolFactory symbolFactory;
    private Medium medium;
    
    private String unit;
    
    // list for storing all generated tokens
    private LinkedList<ComplexSymbol> tokens = new LinkedList<ComplexSymbol>();
    

    /*public Lexer(java.io.InputStream in, ComplexSymbolFactory sf, Medium m) {
        this(in);
        symbolFactory = sf;
        medium = m;
    }*/

    public Lexer(java.io.Reader in, ComplexSymbolFactory sf, Medium m) {
	    this(in);
	    symbolFactory = sf;
	    medium = m;
    }
    
    public void setUnit(String unit) {
    	this.unit = unit;
    }

    private ComplexSymbolFactory.Location getLeft() {
        return new Location(unit,yyline+1,yycolumn+1,yychar);
    }
    private ComplexSymbolFactory.Location getRight() {
        return LocationMethods.calcRight(getLeft(), yytext());
    }

    private ComplexSymbol symbol(DefaultTreeNodeSymbol symbol) {
    	ComplexSymbol s = (ComplexSymbol)symbolFactory.newSymbol(symbol.name, symbol.sym, getLeft(), getRight(), symbol);
    	symbol.setSymbol(s);
		tokens.add(s);
        return s;
    }
    
    /*
     * a list of tokens is stored
     * tokens can be reused without the need to recalculate them
     */
    public LinkedList<ComplexSymbol> getTokens() {
    	return tokens;
    }
    
    private void error(String message) {
    	medium.error(getLeft(), getRight(), message);
    }
    private void warning(String message) {
    	medium.warning(getLeft(), getRight(), message);
    }
    
    public Medium getMedium() {
    	return medium;
    }
    
    private void addWhiteSpaceFiller(String filler) {
    	medium.addWhiteSpaceFiller(getLeft(), getRight(), filler);
    }
    
    
%}

%eofval{
     return symbolFactory.newSymbol("EOF", parser.sym.EOF, getLeft(), getLeft());
%eofval}



O	= [0-7]
D	= [0-9]
NZ	= [1-9]
L	= [a-zA-Z_$]
A	= [a-zA-Z_$0-9]
H	= [a-fA-F0-9]
HP	= (0[xX])
E	= ([Ee][+-]?{D}+)
P	= ([Pp][+-]?{D}+)
FS	= (f|F|l|L)
IS	= (((u|U)(l|L|ll|LL)?)|((l|L|ll|LL)(u|U)?))
CP	= (u|U|L)
SP	= (u8|u|U|L)
ES	= (\\(['\"\?\\abfnrtv]|[0-7]{1,3}|x[a-fA-F0-9]+))
WS	= [ \t\v\r\n\f]



InputCharacter = [^\r\n]
LineTerminator = \r|\n|\r\n

/* comments */
Comment = {TraditionalComment} | {EndOfLineComment} | {DocumentationComment}

TraditionalComment   = "/*" [^*] ~"*/" | "/*" "*"+ "/"
// Comment can be the last line of the file, without line terminator.
EndOfLineComment     = "//" {InputCharacter}* {LineTerminator}?
DocumentationComment = "/**" {CommentContent} "*"+ "/"
CommentContent       = ( [^*] | \*+ [^/*] )*

Preprocessor = "#" (\\{LineTerminator}|{InputCharacter})* {LineTerminator}?


%%


/* whitespace */
{WS}				{ /* ignore whitespace */ }

/* comments */
{Comment}
{
	// we want to preserve comments, but don't pass on a token
	addWhiteSpaceFiller(yytext());
}

/* preprocessor directives */
{Preprocessor}
{
	String directive = yytext();
	
	if (directive.contains("include")) {
		// we have to collect typedef names in included files
	
		// TODO: find arguments
		ProcessBuilder pb = new ProcessBuilder("cpp", "-P", "-D", "__attribute__(ARGS)=", "-D", "__extension__=", "-D", "__asm__(ARGS)=");
		String parent = new File(unit).getParent();
		if (parent != null)
			pb.directory(new File(parent));
		Process p = pb.start();
		
		BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(p.getOutputStream()));
		writer.write(directive);
		writer.flush();
		writer.close();
		
		medium.setCalmedMode(true);
	
		Lexer scanner = new Lexer(new InputStreamReader(p.getInputStream()), new ComplexSymbolFactory(), medium);
		scanner.setUnit(directive.trim());
		
		Parser parser = new Parser(scanner, new ComplexSymbolFactory(), medium);
		try {
			parser.parse();
		} catch (Exception e1) {
			error(e1.getMessage());
		}
		medium.setCalmedMode(false);
		
		// TODO: need to improve error handling; might also block like this
		BufferedReader br = new BufferedReader(new InputStreamReader(p.getErrorStream()));
		StringBuilder lines = new StringBuilder();
		String line;
		while ((line = br.readLine()) != null) {
	        lines.append(line+"\n");
	    }
	    if (lines.length() != 0)
		    warning(lines.toString());

		try {
			p.waitFor();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
	
	// we don't want to resolve preprocessor directives, but to preserve them
	addWhiteSpaceFiller(directive);
}

/* integer constants */
{HP}{H}+{IS}?					{ return symbol(new TSIConstant(yytext())); }
{NZ}{D}*{IS}?					{ return symbol(new TSIConstant(yytext())); }
"0"{O}*{IS}?					{ return symbol(new TSIConstant(yytext())); }
{CP}?"'"([^'\\\n]|{ES})+"'"		{ return symbol(new TSIConstant(yytext())); }

/* floating point constants */
{D}+{E}{FS}?					{ return symbol(new TSFConstant(yytext())); }
{D}*"."{D}+{E}?{FS}?			{ return symbol(new TSFConstant(yytext())); }
{D}+"."{E}{FS}?					{ return symbol(new TSFConstant(yytext())); }
{HP}{H}+{P}{FS}?				{ return symbol(new TSFConstant(yytext())); }
{HP}{H}*"."{H}+{P}{FS}?			{ return symbol(new TSFConstant(yytext())); }
{HP}{H}+"."{P}{FS}?				{ return symbol(new TSFConstant(yytext())); }


/* string literals */
({SP}?\"([^\"\\\n]|{ES})*\"{WS}*)+	{ return symbol(new TSStringLiteral(yytext())); }


/* keywords */
"auto"			{ return symbol(new TSAddAssign()); }
"break"			{ return symbol(new TSBreak()); }
"case"			{ return symbol(new TSCase()); }
"char"			{ return symbol(new TSChar()); }
"const"			{ return symbol(new TSConst()); }
"continue"		{ return symbol(new TSContinue()); }
"default"		{ return symbol(new TSDefault()); }
"do"			{ return symbol(new TSDo()); }
"double"		{ return symbol(new TSDouble()); }
"else"			{ return symbol(new TSElse()); }
"enum"			{ return symbol(new TSEnum()); }
"extern"		{ return symbol(new TSExtern()); }
"float"			{ return symbol(new TSFloat()); }
"for"			{ return symbol(new TSFor()); }
"goto"			{ return symbol(new TSGoto()); }
"if"			{ return symbol(new TSIf()); }
"inline"		{ return symbol(new TSInline()); }
"__inline"		{ return symbol(new TSInline()); }
"int"			{ return symbol(new TSInt()); }
"long"			{ return symbol(new TSLong()); }
"register"		{ return symbol(new TSRegister()); }
"restrict"		{ return symbol(new TSRestrict()); }
"__restrict"	{ return symbol(new TSRestrict()); }
"return"		{ return symbol(new TSReturn()); }
"short"			{ return symbol(new TSShort()); }
"signed"		{ return symbol(new TSSigned()); }
"sizeof"		{ return symbol(new TSSizeof()); }
"static"		{ return symbol(new TSStatic()); }
"struct"		{ return symbol(new TSStruct()); }
"switch"		{ return symbol(new TSSwitch()); }
"typedef"		{ return symbol(new TSTypedef()); }
"union"			{ return symbol(new TSUnion()); }
"unsigned"		{ return symbol(new TSUnsigned()); }
"void"			{ return symbol(new TSVoid()); }
"volatile"		{ return symbol(new TSVolatile()); }
"while"			{ return symbol(new TSWhile()); }

"_Alignas"			{ return symbol(new TSAlignas()); }
"_Alignof"			{ return symbol(new TSAlignof()); }
"_Atomic"			{ return symbol(new TSAtomic()); }
"_Bool"				{ return symbol(new TSBool()); }
"_Complex"			{ return symbol(new TSComplex()); }
"_Generic"			{ return symbol(new TSGeneric()); }
"_Imaginary"		{ return symbol(new TSImaginary()); }
"_Noreturn"			{ return symbol(new TSNoreturn()); }
"_Static_assert"	{ return symbol(new TSStaticAssert()); }
"_Thread_local"		{ return symbol(new TSThreadLocal()); }
"__func__"			{ return symbol(new TSFuncName()); }


/* extension keywords */
"_macro"		{ return symbol(new TSMacro()); }
"_macro_type"	{ return symbol(new TSMacroType()); }

"cut"			{ return symbol(new TSCut()); }
"generator"     { return symbol(new TSGenerator()); }
"with"          { return symbol(new TSWith()); }
"yield"         { return symbol(new TSYield()); }
"finally"       { return symbol(new TSFinally()); }


/* identifiers */
{L}{A}*
{
	if (medium.lookupType(yytext()) || yytext().equals("__builtin_va_list")) {
		return symbol(new TSTypedefName(yytext()));
	} else {
		return symbol(new TSIdentifier(yytext()));
	}
}


/* operators */
"-"			    { return symbol(new TSSubOp()); }
"+"		    	{ return symbol(new TSAddOp()); }
"*"		    	{ return symbol(new TSMulOp()); }
"/"		    	{ return symbol(new TSDivOp()); }
"%"		    	{ return symbol(new TSModOp()); }
"<"		    	{ return symbol(new TSLtOp()); }
">"		    	{ return symbol(new TSGtOp()); }
"&"		    	{ return symbol(new TSBandOp()); }
"^"		    	{ return symbol(new TSXorOp()); }
"|"		    	{ return symbol(new TSBorOp()); }

">>"			{ return symbol(new TSRightOp()); }
"<<"			{ return symbol(new TSLeftOp()); }
"++"			{ return symbol(new TSIncOp()); }
"--"			{ return symbol(new TSDecOp()); }
"->"			{ return symbol(new TSPtrOp()); }
"&&"			{ return symbol(new TSAndOp()); }
"||"			{ return symbol(new TSOrOp()); }
"<="			{ return symbol(new TSLeOp()); }
">="			{ return symbol(new TSGeOp()); }
"=="			{ return symbol(new TSEqOp()); }
"!="			{ return symbol(new TSNeOp()); }


/* assignment operators */
"="			    { return symbol(new TSAssign()); }

"+="			{ return symbol(new TSAddAssign()); }
"-="			{ return symbol(new TSSubAssign()); }
"*="			{ return symbol(new TSMulAssign()); }
"/="			{ return symbol(new TSDivAssign()); }
"%="			{ return symbol(new TSModAssign()); }
"&="			{ return symbol(new TSAndAssign()); }
"^="			{ return symbol(new TSXorAssign()); }
"|="			{ return symbol(new TSOrAssign()); }

">>="			{ return symbol(new TSRightAssign()); }
"<<="			{ return symbol(new TSLeftAssign()); }


/* brackets */
("{"|"<%")		{ return symbol(new TSBraceLeft()); }
("}"|"%>")		{ return symbol(new TSBraceRight()); }
"("			    { return symbol(new TSParLeft()); }
")"			    { return symbol(new TSParRight()); }
("["|"<:")		{ return symbol(new TSBracketLeft()); }
("]"|":>")		{ return symbol(new TSBracketRight()); }



/* other */
";"			    { return symbol(new TSSemicolon()); }
","			    { return symbol(new TSComma()); }
":"		    	{ return symbol(new TSColon()); }
"."			    { return symbol(new TSDot()); }
"!"		    	{ return symbol(new TSExcl()); }
"~"			    { return symbol(new TSTilde()); }
"?"		    	{ return symbol(new TSQues()); }

"..."			{ return symbol(new TSEllipsis()); }


/* extension operators */
"§"				{ return symbol(new TSCompOp()); }
".."			{ return symbol(new TSSetOp()); }


/* fallback */
.	    		{ error("unknown character"); }


