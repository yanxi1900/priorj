package report;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import util.PathTo;

public class UpdateGeneratedTestSuiteTest {

	private String filename;
	
	@Before
	public void setUp() throws Exception {
		filename = PathTo.RESOURCES_FILES + PathTo.SEPARATOR + "Main.java";
	}

	@After
	public void tearDown() throws Exception {
		filename = "";
	}

	@Test
	public void test() {
		fail("Not yet implemented");
	}

}
