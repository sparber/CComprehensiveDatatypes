package generation;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import errors.CXFatalError;

import java_cup.runtime.ComplexSymbolFactory;
import java_cup.runtime.ComplexSymbolFactory.Location;
import lexer.Lexer;
import parser.Parser;
import translators.CompOpDeclaratorTranslator;
import translators.CompOpExpressionTranslator;
import translators.CompTypedefTranslator;
import translators.DeclarationTranslator;
import translators.FinallyTranslator;
import translators.GeneratorTranslator;
import translators.KeywordsTranslator;
import translators.SetOpTranslator;
import translators.SplitOpTranslator;
import tree.other.TreeRoot;
import tree.translation_unit.TTranslationUnit;

public class CXCompiler {

	public static boolean translateFile(File infile, boolean directoryMode) {
		String path = infile.getPath();
		File outfile = new File(path.substring(0, path.length()-1));
		
		if (directoryMode && infile.lastModified() <= outfile.lastModified()) {
			// outfile is already newest version
			return true;
		}
		
		System.out.println("compiling "+infile);
		
		if (!infile.canRead()) {
			System.err.println("fatal error: can not read input file");
			return false;
		}
		
		Medium medium = new Medium(infile.getPath());
		
		FileInputStream instream;
		try {
			instream = new FileInputStream(infile);
		} catch (FileNotFoundException e) {
			System.err.println("fatal error: "+e.getMessage());
			return false;
		}
		
		Lexer scanner = new Lexer(new InputStreamReader(instream), new ComplexSymbolFactory(), medium);
		scanner.setUnit(infile.getPath());
		
		Parser p = new Parser(scanner, new ComplexSymbolFactory(), medium);

		TreeRoot root;
		try {
			root = new TreeRoot((TTranslationUnit)p.parse().value);
		} catch (Exception error) {
			medium.outputMessages();
			System.err.println("fatal error ("+error.getClass().getSimpleName()+"): "+error.getMessage());
			error.printStackTrace();
			return false;
		}
		
		try {
			instream.close();
		} catch (IOException e) {
		}
		
		medium.addWhiteSpaceFiller(new Location(medium.getUnit(), 0, 0, 0), new Location(medium.getUnit(), 1, 0, 0), "#line 1 \""+infile.getName()+"\"\n");
		try {
			root.accept(new DeclarationTranslator(medium));
			root.accept(new SetOpTranslator(medium));
			root.accept(new CompOpExpressionTranslator(medium));
			root.accept(new GeneratorTranslator(medium));
			root.accept(new CompOpDeclaratorTranslator(medium));
			root.accept(new SplitOpTranslator(medium));
			root.accept(new KeywordsTranslator(medium));
			root.accept(new CompTypedefTranslator(medium));
			root.accept(new FinallyTranslator(medium));
		} catch (CXFatalError error) {
			medium.outputMessages();
			System.err.println("fatal error: "+error.getMessage());
			return false;
		}
		
		medium.outputMessages();
		
		if (!medium.containsErrors()) {
			// write new syntax tree to the file
			
			FileOutputStream outstream;
			try {
				outstream = new FileOutputStream(outfile);
			} catch (FileNotFoundException e) {
				System.err.println("fatal error: "+e.getMessage());
				return false;
			}
			
			root.accept(new OutputGenerator(medium.getWhiteSpaceFillers(), outstream));
			
			try {
				outstream.close();
			} catch (IOException e) {
			}
			
			System.out.flush();
			System.err.flush();
			System.out.println("code written to "+outfile);
			
			return true;
		} else {
			
			System.out.flush();
			System.err.flush();
			System.out.println("no output produced.");
			
			return false;
			
		}
		
	}

	public static boolean translateDirectory(File f, String exclude[]) {
		
		boolean no_errors = true;
		
		for(File file : f.listFiles()) {
			int i;
			for (i=0; i<exclude.length; i++) {
				if (file.getPath().equals(exclude[i]))
					break;
			}
			if (i != exclude.length)
				// file was excluded
				continue;
			
			if (file.isDirectory()) {
				translateDirectory(file, exclude);
			} else {
				String fname = file.getName().toLowerCase();
				if (fname.endsWith(".cx") || fname.endsWith(".hx")){
					if (!translateFile(file, true))
						no_errors = false;
				}
			}
		}
		return no_errors;
	}
	
	private static String helptext = 
			"CXCompiler v0.1 by Alexander Sparber 2014\n" +
			"\n" +
			"usage: cxcompile <path to file/directory to compile>\n";

	public static void main(String[] args) {
		
		if (args.length == 0) {
			
			System.err.println(helptext );
			System.exit(0);
			
		}
		
		else if (args.length != 1) {
			
			System.err.println("wrong number of arguments. compilation terminated");
			System.err.println();
			System.err.println(helptext);
			System.exit(1);
			
		}
		
		File file = new File(args[0]);
		if (!file.exists()) {
			System.err.println("file not found. compilation terminated");
			System.err.println();
		}
		
		boolean no_errors = true;
		if (file.isDirectory()) {
			no_errors = translateDirectory(file, new String[] {});
		} else {
			no_errors = translateFile(file, false);
		}
		if (!no_errors) {
			System.exit(1);
		}
		
	}
	
}
