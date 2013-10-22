package report;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import util.PathTo;

/**
 * Test to <code>GenerateTestSuite</code>.
 * 
 * @author Samuel T. C. Santos
 * @version 1.0
 *
 */
public class GenerateTestSuiteTest {

	
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {	
	}

	@Test
	public void testGetNonEmptyHeader() {
		String header = GenerateTestSuite.getHeader("pkg");
		
		String strHeader = "package pkg;\n" +
				"import junit.framework.TestCase;\n" +
				"import junit.framework.TestResult;\n";
		
		assertFalse(header.isEmpty());
		assertEquals(strHeader, header);
	}

	@Test
	public void testGetEmptyHeader() {
		String header = GenerateTestSuite.getHeader("");
		
		String strHeader = "import junit.framework.TestCase;\n" +
				"import junit.framework.TestResult;\n";
		
		assertFalse(header.isEmpty());
		assertEquals(strHeader, header);
	}
	
	@Test
	public void testGetNonEmptySuiteBodyTop(){
		String strBodyTop = "public class SuiteA {\n" +
							 "\tprivate int failureCount = 0;\n" +
							 "\tprivate int runCount = 0;\n"+
							 "\tprivate int errorCount = 0;\n";
		
		String bodyTop = GenerateTestSuite.getSuiteBodyTop("SuiteA");
		
		assertEquals(strBodyTop, bodyTop);
	}
	
	
	@Test
	public void testGetEmptySuiteBodyTop(){
		String strBodyTop = "public class SuitePrioritizedPriorJ {\n" +
							 "\tprivate int failureCount = 0;\n" +
							 "\tprivate int runCount = 0;\n"+
							 "\tprivate int errorCount = 0;\n";
		
		String bodyTop = GenerateTestSuite.getSuiteBodyTop("");
		
		assertEquals(strBodyTop, bodyTop);
	}
	
	@Test
	public void testExtractSubListFromList(){
		List<String> list = Arrays.asList("a","b","c","d","e","f","g","h","i","j");
		
		List<String> subList = GenerateTestSuite.extractSubList(list, 50);
		
		assertEquals("[a, b, c, d, e]", subList.toString());
		
		subList = GenerateTestSuite.extractSubList(list, 10);
		assertEquals("[a]", subList.toString());
		
		subList = GenerateTestSuite.extractSubList(list, 20);
		assertEquals("[a, b]", subList.toString());
		
	}
	
	@Test
	public void testExtractSubListFromEmptyList(){
		List<String> list = new ArrayList<String>();
		
		List<String> subList = GenerateTestSuite.extractSubList(list, 20);
		
		assertEquals("[]", subList.toString());
	}
	
	@Test
	public void testGetPackageName() throws Exception{
		String path = PathTo.APP_AVL_TEST;
		
		String packageName = GenerateTestSuite.getPackageName(path);
		
		assertEquals("tests", packageName);
	}
	
	@Test
	public void testGetPackageNameWithManySpaces() throws Exception{
		String path = PathTo.RESOURCES_GENERATED_SUITE_FILES;
		
		String packageName = GenerateTestSuite.getPackageName(path);
		
		assertEquals("tests", packageName);
	}
	
	@Test (expected = Exception.class)
	public void testGetPackageNameReturnNull() throws Exception{
		String path = PathTo.RESOURCES_PRIORITIZATION_FILES;
		
		//throws an exception here...
		String packageName = GenerateTestSuite.getPackageName(path);
		
		assertNull(packageName);
	}
	
	@Test
	public void testGeneratedSuiteNameNonEmpty(){
		String [] paths = {"java", "code", "com"};
		
		String suiteName = GenerateTestSuite.generateNameSuite(paths);
		
		assertEquals("tcJavaCodeCom", suiteName);
	}
	
	@Test
	public void testGeneratedSuiteNameEmpty(){
		String [] paths = {};
		
		String suiteName = GenerateTestSuite.generateNameSuite(paths);
		
		assertEquals("tc", suiteName);
	}
	
	@Test
	public void testGetInstanceNormalPath(){
		String [] path = {"com","br", "java","application","testA"};
		
		String instancePath = GenerateTestSuite.getPathInstance(path);
		
		assertEquals("com.br.java.application", instancePath);
	}
	
	@Test
	public void testGetInstanceEmptyPath(){
		String [] path = {};
		
		String instancePath = GenerateTestSuite.getPathInstance(path);
		
		assertEquals("", instancePath);
	}
	
	
	@Test
	public void testGetFinalSuite(){
		String finalSuite = GenerateTestSuite.getFinalSuite("A",new ArrayList<String>());
		
		String finalStr = "\tprivate void setResult(String testCaseName,TestResult result){\n"+
						"\t\tSystem.out.println(\"TestCase: \" + testCaseName +\" \" + getStatus(result));\n"
						+ "\t\trunCount += result.runCount();\n"
						+ "\t\tfailureCount += result.failureCount();\n"
						+ "\t\terrorCount += result.errorCount();\n"
						+ "\t}\n" 
						+ "\tprivate String getStatus(TestResult result){\n"
						+ "\t\tint erroCount = result.errorCount();\n"
						+ "\t\tint failureCount = result.failureCount();\n"
						+ "\t\tif(erroCount > 0) return \"[ERROR]\";\n"
						+ "\t\telse if(failureCount > 0) return \"[FAILURE]\";\n"
						+ "\t\telse return \"[ACCEPTED]\";\n"
						+ "\t}\n"
						+ "\tpublic void printResult(){\n"
						+ "\t\tSystem.out.println(\"=================== Test Suite Prioritized - PriorJ =================\");\n"
						+ "\t\tSystem.out.println(\"Run: \" + this.runCount);\n"
						+ "\t\tSystem.out.println(\"Faults: \" + this.failureCount);\n"
						+ "\t\tSystem.out.println(\"Errors: \" + this.errorCount);\n"
						+ "\t}\n"
						+ "\tpublic static void main(String[] args) {\n"
						+ "\t\t" + "A" + " st = new " + "A" + "();\n"
						+ "\t\tst.run();\n"
						+ "\t\tst.printResult();\n"
						+ "\t}\n"
						+ "}\n";
						
		assertEquals(finalStr, finalSuite);
	}
	
	
	@Test
	public void testGetFinalSuiteWithEmptyName(){
		String finalSuite = GenerateTestSuite.getFinalSuite("", new ArrayList<String>());
		
		String finalStr = "\tprivate void setResult(String testCaseName,TestResult result){\n"+
						"\t\tSystem.out.println(\"TestCase: \" + testCaseName +\" \" + getStatus(result));\n"
						+ "\t\trunCount += result.runCount();\n"
						+ "\t\tfailureCount += result.failureCount();\n"
						+ "\t\terrorCount += result.errorCount();\n"
						+ "\t}\n" 
						+ "\tprivate String getStatus(TestResult result){\n"
						+ "\t\tint erroCount = result.errorCount();\n"
						+ "\t\tint failureCount = result.failureCount();\n"
						+ "\t\tif(erroCount > 0) return \"[ERROR]\";\n"
						+ "\t\telse if(failureCount > 0) return \"[FAILURE]\";\n"
						+ "\t\telse return \"[ACCEPTED]\";\n"
						+ "\t}\n"
						+ "\tpublic void printResult(){\n"
						+ "\t\tSystem.out.println(\"=================== Test Suite Prioritized - PriorJ =================\");\n"
						+ "\t\tSystem.out.println(\"Run: \" + this.runCount);\n"
						+ "\t\tSystem.out.println(\"Faults: \" + this.failureCount);\n"
						+ "\t\tSystem.out.println(\"Errors: \" + this.errorCount);\n"
						+ "\t}\n"
						+ "\tpublic static void main(String[] args) {\n"
						+ "\t\t" + "PrioritizedSuite" + " st = new " + "PrioritizedSuite" + "();\n"
						+ "\t\tst.run();\n"
						+ "\t\tst.printResult();\n"
						+ "\t}\n"
						+ "}\n";
						
		assertEquals(finalStr, finalSuite);
	}
	
		
	@Test
	public void testGenerateTestSuite(){
		
		List<String> list = Arrays.asList("testA","testB","testC");
		
		String suite = GenerateTestSuite.newGenerate("pkg", "X", list);
		
		System.out.println(suite);
	}
	
}
