package mega.cosmos.app;

import static org.junit.Assert.assertEquals;
import static mega.cosmos.app.SplitUtils.*;

import java.util.List;

import org.junit.Test;

public class SplitUtilsTest {
    @Test
    public void classNameSplit() {
        List<String> classNameParts = splitClassName("FlightSessionRoot");
    	assertEquals(classNameParts.size(), 3);
    	assertEquals(classNameParts.get(0), "Flight");
    	assertEquals(classNameParts.get(1), "Session");
    	assertEquals(classNameParts.get(2), "Root");
    }
    @Test
    public void classNamePackage() {
        List<String> classNameParts = splitClassName("org.mega.kosmos.FlightSessionRoot");
    	assertEquals(classNameParts.size(), 3);
    	assertEquals(classNameParts.get(0), "Flight");
    	assertEquals(classNameParts.get(1), "Session");
    	assertEquals(classNameParts.get(2), "Root");
    }
}
