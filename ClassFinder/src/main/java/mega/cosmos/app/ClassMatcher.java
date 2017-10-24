package mega.cosmos.app;

import static mega.cosmos.app.SplitUtils.*;

import java.util.List;

public class ClassMatcher {
    
    public static final String SPACE = " ";
	
	final List<String> patternParts;

    public ClassMatcher(String pattern) {
        this.patternParts = splitPattern(pattern);
    }

    public boolean match(String classNameOriginal) {
    	String className = classNameOriginal.trim().substring( Math.max(0, classNameOriginal.lastIndexOf('.')) );    	
    	List<String> classNameParts = splitClassName(className);
    	
    	while (!classNameParts.isEmpty() && classNameParts.size() >= patternParts.size()) {
    		boolean res = checkRecursive(patternParts, classNameParts, 0);
    		if (res) 
    			return true;
    		classNameParts.remove(0);
    	}
    	    	
    	return false;
    }
    
    private boolean checkRecursive(List<String> patternParts, List<String> classNameParts, int index) {
    	if (patternParts.size() == index) {
    		if (patternParts.size() == classNameParts.size()) {
    			return true;
    		} else if (patternParts.get(index-1).endsWith(SPACE)) {
    			return false;
    		} else {
    			return true;
    		}
    	}
    	if (!check(patternParts.get(index), classNameParts.get(index)))
    		return false;
    	return checkRecursive(patternParts, classNameParts, index+1);
    }
    
    private boolean check(String patternOriginal, String className) {
    	String pattern = patternOriginal.replace(SPACE, "");
    	if (!pattern.contains(WILDCARD)) {
    		return className.startsWith(pattern);
    	} else {
            for (int i = 0; i < className.length(); i++) {
                boolean matched = true;
                boolean wildcarded = false;
                for (int j = 0; j < pattern.length(); j++) {
                    if (className.length() <= i + j) return false;
                    char r = pattern.charAt(j);
                    char c = className.charAt(i + j);
                    if (r == '*') {
                        wildcarded = true;
                    } else if (r==c) {
                        wildcarded = false;
                        continue;
                    } else {
                        if (wildcarded) {
                            continue;
                        } else {
                            matched = false;
                            break;
                        }
                    }
                }
                if (matched) {
                    return true;
                } else {
                    continue;
                }
            }
            return false;
    	}
    }

}
