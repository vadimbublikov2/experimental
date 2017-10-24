package mega.cosmos.app;

import static java.lang.System.out;
import static java.nio.file.Files.readAllLines;
import static java.nio.file.Paths.get;

import java.io.IOException;

public class ClassFinder {
	public static void main(String[] args) throws IOException {
	    ClassMatcher classMatcher = new ClassMatcher(args[1]);

	    readAllLines(get(args[0])).stream().filter(classMatcher::match).sorted(
	    		(l1, l2) -> {
	    			String className1 = l1.substring( Math.max(0, l1.lastIndexOf('.')) );
	    			String className2 = l2.substring( Math.max(0, l1.lastIndexOf('.')) );
	    			return className1.compareTo(className2);
	    		}
	    ).forEach(ClassFinder::print);		
	}
	
	private static void print(String str) {
		out.println( str.trim() );
	}
}
