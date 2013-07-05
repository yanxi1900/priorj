package technique;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertNotNull;

import java.util.LinkedList;
import java.util.List;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import coverage.ClassCode;
import coverage.Method;
import coverage.Statement;
import coverage.TestCase;
import exception.EmptySetOfTestCaseException;



public class TechniqueTotalStatementTest {

	private TechniqueTotalStatement tsc;
	
	private List<TestCase> testsList;	
	
	TestCase [] tests = {
			 new TestCase("testA"),
			 new TestCase("testB"), 
			 new TestCase("testC"),
			 new TestCase("testD"),
			 new TestCase("testE")
			};
	
	ClassCode [] classes = {
			new ClassCode("pkg","A"),
            new ClassCode("pkg","B")
			};
	
	Method[] methods = { 
			new Method ("m1") ,
		    new Method("m2"),
		    new Method("m3"),
		    new Method("m4"),
		    new Method("m5"),
		    new Method("m6"),
		    new Method("m7"),
			};
	
	Statement [] statementsA = {
			new Statement("11"),
			new Statement("2"),
			new Statement("13"),
			new Statement("4"),
			new Statement("11"),
			new Statement("51"),
			new Statement("131"),
			new Statement("19"),
			new Statement("8"),
			new Statement("110"),
			new Statement("121"),
			new Statement("17"),
			new Statement("41"),
			};
	
	Statement [] statementsB = {
			new Statement("9"),
			new Statement("20"),
			new Statement("1"),
			new Statement("40"),
			new Statement("101"),
			new Statement("510"),
			new Statement("21"),
			new Statement("107"),
			new Statement("410"),
			};
	
	Statement [] statementsC = {
			new Statement("9")
			};
	
	Statement [] statementsD = {
			new Statement("99"),
			new Statement("220"),
			new Statement("111"),
			new Statement("410")
			};
	
	Statement [] statementsE = {
			new Statement("89"),
			new Statement("230")
			};
	
	
	
	@Before
	public void setUp(){
		testsList = new LinkedList<TestCase>();
		tsc = new TechniqueTotalStatement(testsList);
		
	}
	
	@Test
	public void testInitialization() {
		assertNotNull(tsc);
	}
	
	@Test (expected = EmptySetOfTestCaseException.class)
	public void prioritizationEmptySetTestCase() throws EmptySetOfTestCaseException{
		List<String> prioritizedList = tsc.assingWeight();
		
		assertArrayEquals( new  String[]{}, prioritizedList.toArray());
	}
	
	@Test
	public void prioritizationOneTestCase() throws EmptySetOfTestCaseException{
		
		for (Statement stmt: statementsA){
			methods[0].addStatement(stmt);
		}
		
		for (Statement stmt: statementsB){
			methods[1].addStatement(stmt);
		}
		
		//class A and method m1 
		classes[0].addMethod(methods[0]);
		//classsB and method m2
		classes[1].addMethod(methods[1]);
		
		//test case A
		tests[0].addClassCoverage(classes[0]);
		tests[0].addClassCoverage(classes[1]);
		
		tests[0].setSignature("pkg.A.testA");
		
		//calculate and set
		int numberStmtCoverage = tests[0].getNumberStatementsCoverageDistinct();
		tests[0].setNumberStatementsCoverageDistinct(numberStmtCoverage);
		
		testsList.add(tests[0]);
		
		
		tsc = new TechniqueTotalStatement(testsList);
		
		List<String> prioritized = tsc.assingWeight();
	
		Assert.assertEquals("[pkg.A.testA]", prioritized.toString());
	}
	
	@Test
	public void prioritizationEveryTestCase() throws EmptySetOfTestCaseException{
		
		//method m1 has 13 statement 
		for (Statement stmt: statementsA){
			methods[0].addStatement(stmt);
		}
		//method m2 has 9 statement
		for (Statement stmt: statementsB){
			methods[1].addStatement(stmt);
		}
		//method m3 has 1 statement
		for (Statement stmt: statementsC){
			methods[2].addStatement(stmt);
		}
		//method m4 has 4 statement 
		for (Statement stmt: statementsD){
			methods[3].addStatement(stmt);
		}
		//method m5 has  2 statement
		for (Statement stmt: statementsE){
			methods[4].addStatement(stmt);
		}
		
		//classA cover the method m1,m2,m3,m4
		// classA has 13+9+1 = 23 statements
		classes[0].addMethod(methods[0]);
		classes[0].addMethod(methods[1]);
		classes[0].addMethod(methods[2]);
		
		//class B cover m4, m5
		//class B has 4 + 2 = 6 staments 
		classes[1].addMethod(methods[3]);
		classes[1].addMethod(methods[4]);
		
		//testA cover class A
		tests[0].addClassCoverage(classes[0]);
		//set the signature
		tests[0].setSignature("pkg.A.testA");
		
		//calculate and set
		int numberStmtCoverage = tests[0].getNumberStatementsCoverageDistinct();
		tests[0].setNumberStatementsCoverageDistinct(numberStmtCoverage);
		
		tests[1].addClassCoverage(classes[1]);
		tests[1].setSignature("pkg.B.TestB");
		
		//calculate and set
		numberStmtCoverage = tests[1].getNumberStatementsCoverageDistinct();
		tests[1].setNumberStatementsCoverageDistinct(numberStmtCoverage);
		
		testsList.add(tests[0]);
		testsList.add(tests[1]);
		
		tsc = new TechniqueTotalStatement(testsList);
		
		List<String> prioritized = tsc.assingWeight();
	
		System.out.println(prioritized);
		Assert.assertEquals("[pkg.A.testA, pkg.B.TestB]", prioritized.toString());
		
	}
	

}
