package mega.cosmos.app;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class SplitUtils {
	private SplitUtils() {		
	}
		
	public static final String WILDCARD = "*";
	
    public static List<String> splitPattern(String patternOriginal) {
	    String pattern =
                patternOriginal.endsWith(WILDCARD) ? patternOriginal.substring(0,patternOriginal.length()-1) : patternOriginal;

        List<String> splitPatterns = new ArrayList<>();
        StringBuilder stringBuilder = new StringBuilder();
        for ( char c : pattern.toCharArray() ) {
            if ( Character.isUpperCase(c) && stringBuilder.length()>0 ) {
                splitPatterns.add( stringBuilder.toString() );
                stringBuilder.setLength(0);
            }
            stringBuilder.append(c);
        }

        if (splitPatterns.isEmpty() && pattern.toLowerCase().equals(pattern)) {
            String patternEnd = null;
            String patternClean;
            if (pattern.endsWith(" ")) {
                patternEnd = pattern.substring(pattern.length()-2).toUpperCase();
                patternClean = pattern.substring(0, pattern.length()-2);
            } else {
                patternClean = pattern;
            }

            if (patternClean.length()>0) splitPatterns.addAll( Arrays.asList(patternClean.toUpperCase().split("")) );

            if (patternEnd != null) {
                splitPatterns.add(patternEnd);
            }

            return splitPatterns;
        }

        if (stringBuilder.length()>0)
            splitPatterns.add( stringBuilder.toString() );

        return splitPatterns;
    }
    
    public static List<String> splitClassName(String className) {
    	List<String> classNameParts = new ArrayList<>();
    	splitClassNameRecursive(classNameParts, className);
    	Collections.reverse(classNameParts);
    	return classNameParts;
    }
    
    private static int splitClassNameRecursive(List<String> classNameParts, String className) {
    	int beginIndex = 0;
    	for (; beginIndex < className.length(); beginIndex++) {
    		if ( Character.isUpperCase(className.charAt(beginIndex)) ) {
    			int offset = splitClassNameRecursive(classNameParts, className.substring(beginIndex+1));
    			classNameParts.add( className.substring(beginIndex, beginIndex+offset+1) );
    			return beginIndex;
    		}
    	}
    	return beginIndex;
    }
    
	
}
