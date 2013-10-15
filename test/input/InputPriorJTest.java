package input;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import coverage.TestCase;
import coverage.TestSuite;

import util.PathTo;

public class InputPriorJTest {

	InputPriorJ input;
	
	String path = PathTo.RESOURCES_FILES + PathTo.SEPARATOR + "inputconfig.txt";
	
	@Before
	public void setUp() throws Exception {
		input = new InputPriorJ(path);
	}

	@After
	public void tearDown() throws Exception {
		input = null;
	}

	@Test
	public void testPropertyApp() {
		assertEquals("/home/samuel/AVL", input.pathApp());
	}
	
	@Test
	public void testPropertyCode(){
		assertEquals("/home/samuel/AVL/src", input.pathCode());
	}
	
	@Test
	public void testPropertyLib(){
		assertEquals("", input.pathLib());
	}
	
	@Test
	public void testPropertyTests(){
		assertEquals("/home/samuel/AVL/src/tests", input.pathTest());
	}
	
	@Test
	public void testPropertyJUnit3(){
		assertEquals("yes", input.JUnit3());
	}
	
	@Test
	public void testPropertyJUnit4(){
		assertEquals("no", input.JUnit4());
	}
	
	@Test
	public void testPropertyTMC(){
		assertEquals("yes", input.TMC());
	}
	
	@Test
	public void testPropertyTSC(){
		assertEquals("yes", input.TSC());
	}
	
	
	@Test
	public void testPropertyAMC(){
		assertEquals("yes", input.AMC());
	}
	
	@Test
	public void testPropertyASC(){
		assertEquals("yes", input.ASC());
	}
	
	@Test
	public void testPropertyRND(){
		assertEquals("yes", input.RND());
	}
	
	@Test
	public void testPropertyCB(){
		assertEquals("no", input.CB());
	}
	
	@Test
	public void testPropertyRBA(){
		assertEquals("no", input.RBA());
	}
	
	@Test
	public void testPropertyCoverageReport(){
		assertEquals("yes", input.coverageReport());
	}
	
	@Test
	public void testPropertyCodeTree(){
		assertEquals("yes", input.codeTree());
	}
	
	@Test
	public void testPropertyExecutionOrder(){
		assertEquals("yes", input.executionOrder());
	}

	@Test
	public void testPropertySuiteTMC(){
		assertEquals("yes", input.suiteTMC());
	}
	
	@Test
	public void testPropertySuiteTSC(){
		assertEquals("yes", input.suiteTSC());
	}
	
	
	@Test
	public void testPropertySuiteAMC(){
		assertEquals("yes", input.suiteAMC());
	}
	
	@Test
	public void testPropertySuiteASC(){
		assertEquals("yes", input.suiteASC());
	}
	
	@Test
	public void testPropertySuiteRND(){
		assertEquals("yes", input.suiteRND());
	}
	
	@Test
	public void testPropertySuiteCB(){
		assertEquals("no", input.suiteCB());
	}
	
	@Test
	public void testPropertySuiteRBA(){
		assertEquals("no", input.suiteRBA());
	}
	
	@Test
	public void testRBAPropertyRenameMethod(){
		assertEquals("", input.rmPathApp());
		assertEquals("", input.rmClassName());
		assertEquals("", input.rmMethodName());
		assertEquals("", input.rmNewMethodName());
	}
	
	
}
