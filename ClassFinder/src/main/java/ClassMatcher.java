import java.util.ArrayList;
import java.util.List;


public class ClassMatcher {
    List<String> patternParts;

    public ClassMatcher(String pattern) {
        this.patternParts = splitPattern(pattern);
    }

    private List<String> splitPattern(String pattern) {
        List<String> splitPatterns = new ArrayList<>();
        StringBuilder stringBuilder = new StringBuilder();
        for ( char c : pattern.toCharArray() ) {
            if ( Character.isUpperCase(c) && stringBuilder.length()>0 ) {
                splitPatterns.add( stringBuilder.toString() );
                stringBuilder.setLength(0);
            }
            stringBuilder.append(c);
        }
        if (stringBuilder.length()>0)
            splitPatterns.add( stringBuilder.toString() );

        return splitPatterns;
    }

}
