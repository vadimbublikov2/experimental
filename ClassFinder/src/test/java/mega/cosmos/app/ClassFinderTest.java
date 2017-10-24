package mega.cosmos.app;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import org.junit.Before;
import org.junit.Test;

public class ClassFinderTest {
	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	
	@Before
	public void setUpStreams() {
	    outContent.reset();
	    System.setOut(new PrintStream(outContent));
	}	
	
	@Test
	public void outputFullAndSortTest() throws IOException {
		String[] args = new String[] {"src/test/resources/classes.txt", "Bar"};
		ClassFinder.main(args);
	    
		String[] resultOutput = new String(outContent.toByteArray()).split(System.lineSeparator());		
		assertEquals(4, resultOutput.length);
		assertEquals("DeviceBarometer", resultOutput[0]);
		assertEquals("e.f.FooBar", resultOutput[1]);
		assertEquals("c.d.FooBar", resultOutput[2]);
		assertEquals("a.b.FooBarBaz", resultOutput[3]);
	}

	@Test
	public void outputPatternSpaceTerminated() throws IOException {
		String[] args = new String[] {"src/test/resources/classes.txt", "Bar "};
		ClassFinder.main(args);
	    
		String[] resultOutput = new String(outContent.toByteArray()).split(System.lineSeparator());		
		assertEquals(2, resultOutput.length);
		assertEquals("e.f.FooBar", resultOutput[0]);
		assertEquals("c.d.FooBar", resultOutput[1]);
	}

	@Test
	public void outputPatternSpaceTerminatedWithWildcard() throws IOException {
		String[] args = new String[] {"src/test/resources/classes.txt", "B*r "};
		ClassFinder.main(args);
	    
		String[] resultOutput = new String(outContent.toByteArray()).split(System.lineSeparator());		
		assertEquals(3, resultOutput.length);
		assertEquals("DeviceBarometer", resultOutput[0]);
		assertEquals("e.f.FooBar", resultOutput[1]);
		assertEquals("c.d.FooBar", resultOutput[2]);
	}
	
}
