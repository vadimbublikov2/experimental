package mega.cosmos.app;

import static mega.cosmos.app.SplitUtils.splitPatternClassName;
import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

public class SplitUtilsTest {
    @Test
    public void classNameSplit() {
        List<String> classNameParts = splitPatternClassName("FlightSessionRoot");
    	assertEquals(classNameParts.size(), 3);
    	assertEquals(classNameParts.get(0), "Flight");
    	assertEquals(classNameParts.get(1), "Session");
    	assertEquals(classNameParts.get(2), "Root");
    }
    @Test
    public void classNamePackage() {
        List<String> classNameParts = splitPatternClassName("Se*Root");
    	assertEquals(classNameParts.size(), 2);
    	assertEquals(classNameParts.get(0), "Se*");
    	assertEquals(classNameParts.get(1), "Root");
    }
}
