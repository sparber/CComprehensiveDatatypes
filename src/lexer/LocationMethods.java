package lexer;

import java_cup.runtime.ComplexSymbolFactory.Location;

public class LocationMethods {

	public static int compareLocations(Location loc1, Location loc2) {
		if (loc1.getLine() < loc2.getLine()) {
			return -1;
		} else if (loc1.getLine() > loc2.getLine()) {
			return 1;
		} else if (loc1.getColumn() < loc2.getColumn()) {
			return -1;
		} else if (loc1.getColumn() > loc2.getColumn()) {
			return 1;
		} else {
			return 0;
		}
	}
	
    public static Location calcRight(Location left, String value) {
		String text[] = value.split("\n|\r\n|\r", -1);
		if (text.length == 1) {
	        return new Location(
	        		left.getUnit(),
	        		left.getLine()+text.length-1,
	        		left.getColumn()+text[text.length-1].length()-1,
	        		left.getOffset()+value.length()
    		);
        } else {
	        return new Location(
	        		left.getUnit(),
	        		left.getLine()+text.length-1,
	        		text[text.length-1].length(),
	        		left.getOffset()+value.length()
    		);
        }
		
	}
	
	
}
