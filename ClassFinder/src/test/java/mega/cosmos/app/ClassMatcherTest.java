package mega.cosmos.app;
import org.junit.Test;

import static org.junit.Assert.*;

import java.util.List;

public class ClassMatcherTest {

   @Test
    public void patternSimple() {
        ClassMatcher classMatcher = new ClassMatcher("FlightSessionRoot");
        assertEquals(classMatcher.patternParts.size(), 3);
        assertEquals(classMatcher.patternParts.get(0), "Flight");
        assertEquals(classMatcher.patternParts.get(1), "Session");
        assertEquals(classMatcher.patternParts.get(2), "Root");
    }
   @Test
   public void matchSimple() {
       ClassMatcher classMatcher = new ClassMatcher("FlightSessionRoot");
       assertEquals(classMatcher.match("FlightSessionRoot"), true);
       assertEquals(classMatcher.match("FlightSessionRootServiceImpl"), true);
       assertEquals(classMatcher.match("ProjectFlightSessionRootServiceImpl"), true);
   }

   @Test
    public void patternTwoHumpAndPart() {
        ClassMatcher classMatcher = new ClassMatcher("FSRoot");
        assertEquals(classMatcher.patternParts.size(), 3);
        assertEquals(classMatcher.patternParts.get(0), "F");
        assertEquals(classMatcher.patternParts.get(1), "S");
        assertEquals(classMatcher.patternParts.get(2), "Root");
    }
   @Test
   public void matchTwoHumpAndPart() {
       ClassMatcher classMatcher = new ClassMatcher("FSRoot");
	   assertEquals(classMatcher.match("FlightSessionRoot"), true);
	   assertEquals(classMatcher.match("ProjectFlightSessionRootService"), true);
   }

   @Test
    public void patternHumpOnly() {
        ClassMatcher classMatcher = new ClassMatcher("FSRSI");
        assertEquals(classMatcher.patternParts.size(), 5);
        assertEquals(classMatcher.patternParts.get(0), "F");
        assertEquals(classMatcher.patternParts.get(1), "S");
        assertEquals(classMatcher.patternParts.get(2), "R");
        assertEquals(classMatcher.patternParts.get(3), "S");
        assertEquals(classMatcher.patternParts.get(4), "I");
   }
   @Test
   public void matchHumpOnly() {
	   ClassMatcher classMatcher =  new ClassMatcher("FSRSI");
	   assertEquals(classMatcher.match("FlightSessionRootServiceImple"), true);
	   assertEquals(classMatcher.match("ProjectFlightSessionRootServiceImple"), true);
   }
    
   @Test
    public void patternOnePart() {
        ClassMatcher classMatcher = new ClassMatcher("Session");
        assertEquals(classMatcher.patternParts.size(), 1);
        assertEquals(classMatcher.patternParts.get(0), "Session");    	
    }
   @Test
   public void matchOnePart() {
       ClassMatcher classMatcher = new ClassMatcher("Session");
       assertEquals( classMatcher.match("FlightSessionRoot"), true );
       assertEquals( classMatcher.match("Session"), true );
   }

   @Test
    public void patternOneHump() {
        ClassMatcher classMatcher = new ClassMatcher("S");
        assertEquals(classMatcher.patternParts.size(), 1);
        assertEquals(classMatcher.patternParts.get(0), "S");    	
    }
   @Test
   public void matchOneHump() {
       ClassMatcher classMatcher = new ClassMatcher("S");
       assertEquals(classMatcher.match("FlightSessionRoot"), true);
   }

    @Test
    public void patternLowerCase() {
        ClassMatcher classMatcher = new ClassMatcher("fsr");
        assertEquals(classMatcher.patternParts.size(), 3);
        assertEquals(classMatcher.patternParts.get(0), "F");
        assertEquals(classMatcher.patternParts.get(1), "S");
        assertEquals(classMatcher.patternParts.get(2), "R");
    }
    @Test
    public void matchLowerCaseAll() {
        ClassMatcher classMatcher = new ClassMatcher("fsr");
        assertEquals(classMatcher.match("FlightSessionRoot"), true);
    }
    public void matchLowerCaseNotAll() {
        ClassMatcher classMatcher = new ClassMatcher("fSr");
        assertEquals(classMatcher.match("FlightSessionRoot"), false);    	
    }

    @Test
    public void patternLowerCaseWithSpace() {
        ClassMatcher classMatcher = new ClassMatcher("fsr ");
        assertEquals(classMatcher.patternParts.size(), 3);
        assertEquals(classMatcher.patternParts.get(0), "F");
        assertEquals(classMatcher.patternParts.get(1), "S");
        assertEquals(classMatcher.patternParts.get(2), "R ");
    }
    public void matchLowerCaseWithSpace() {
        ClassMatcher classMatcher = new ClassMatcher("fsr ");
        assertEquals(classMatcher.match("FlightSessionRoot"), true);
        assertEquals(classMatcher.match("FlightSessionRootService"), false);    	
    }
    
    @Test
    public void patternWildcard() {
        ClassMatcher classMatcher = new ClassMatcher("Fl*Session");
        assertEquals(classMatcher.patternParts.size(), 2);
        assertEquals(classMatcher.patternParts.get(0), "Fl*");
        assertEquals(classMatcher.patternParts.get(1), "Session");
    }
    public void matchWildCard() {
        ClassMatcher classMatcher = new ClassMatcher("Fl*Session");
        assertEquals(classMatcher.match("ProjectFlightSessionRoot"), true);
    }

    @Test
    public void patternWildcardEnd() {
        ClassMatcher classMatcher = new ClassMatcher("FlSession*");
        assertEquals(classMatcher.patternParts.size(), 2);
        assertEquals(classMatcher.patternParts.get(0), "Fl");
        assertEquals(classMatcher.patternParts.get(1), "Session");
    }
    public void matchWildcardEnd() {
        ClassMatcher classMatcher = new ClassMatcher("FlSession*");
        assertEquals(classMatcher.match("ProjectFlightSessionRoot"), true);
    }

    @Test
    public void patternFull() {
        ClassMatcher classMatcher = new ClassMatcher("FliSR*ServiceImpl ");
        assertEquals(classMatcher.patternParts.size(), 5);
        assertEquals(classMatcher.patternParts.get(0), "Fli");
        assertEquals(classMatcher.patternParts.get(1), "S");
        assertEquals(classMatcher.patternParts.get(2), "R*");
        assertEquals(classMatcher.patternParts.get(3), "Service");
        assertEquals(classMatcher.patternParts.get(4), "Impl ");    
    }
    @Test
    public void matchFull() {
        ClassMatcher classMatcher = new ClassMatcher("FliSR*ServiceImpl ");
        assertEquals(classMatcher.match("FlightSessionRootServiceImpl"), true);
    }
    
}
