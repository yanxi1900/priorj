package coverage;

import org.junit.Before;
import org.junit.Test;

import coverage.TestCase;
import coverage.TestSuite;

public class TestSuiteTest {

	private TestSuite  suite;
	
	@Before
	public void setUp(){
		suite = new TestSuite("pkg", "UnderTest");
	}
	
	@Test
	public void testSuiteAddTestCase() {
		suite.addTestCase(new TestCase("testA"));
		
		assert(suite.getTestCases().size()==1);
	}

}
