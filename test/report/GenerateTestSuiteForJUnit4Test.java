package report;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import util.PathTo;

/**
 * This class is a test to class over test <code>GenerateTestSuiteForJUnit4</code>.
 *
 * @author Samuel T. C. Santos
 * @version 1.0
 *
 */
public class GenerateTestSuiteForJUnit4Test {

	@Test
	public void testTryGenerateHeader() {		
		String headerStr = "package pkg;\n";
		headerStr += "import org.junit.runner.JUnitCore;\n";
		headerStr += "import org.junit.runner.Request;\n";
		headerStr += "import org.junit.runner.Result;\n";
		
		String header = GenerateTestSuiteForJUnit4.getHeader("pkg");
		
		assertEquals(header.length(), headerStr.length());
	}
	
	@Test
	public void testTryGenerateBodyHigher(){
		String bodyStr = "public class SuitePrioritizedPriorJ {\n";
		bodyStr += "\tprivate int failureCount = 0;\n";
		bodyStr += "\tprivate int runCount = 0;\n";
		bodyStr += "\tprivate int errorCount = 0;\n";
		
		String body = GenerateTestSuiteForJUnit4.getBodyHigher("");
		
		assertEquals(bodyStr, body);
	}
	
	@Test
	public void testTryGenerateSuiteCode(){
		List<String> list = Arrays.asList("com.br.TestXpto.a", "com.br.TestXpto.b", "com.br.TestXpto.c");
		
		System.out.println(GenerateTestSuiteForJUnit4.newGenerate("pkg.xpto", "ClassA", list));
	}

}
