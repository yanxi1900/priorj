package util;

/*
* PriorJ: JUnit Test Case Prioritization.
* 
* Copyright (C) 2012-2013  Samuel T. C. Santos
*
* This program is free software: you can redistribute it and/or modify
* it under the terms of the GNU General Public License as published by
* the Free Software Foundation, either version 3 of the License, or
* (at your option) any later version.
*
* This program is distributed in the hope that it will be useful,
* but WITHOUT ANY WARRANTY; without even the implied warranty of
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
* GNU General Public License for more details.
* 
* You should have received a copy of the GNU General Public License
* along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertTrue;

import java.util.LinkedList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import coverage.ClassCode;
import coverage.Method;
import coverage.Statement;
import coverage.TestCase;
import exception.EmptySetOfTestCaseException;

/**
 * This class is a Test Class.
 * 
 * @author Samuel T. C. Santos
 * @version 1.0
 */
public class UtilTest {

	List<String> updateList;
	List<String> removeList;
	private List<TestCase> testsList;
	private List<Method> methodsList;
	private List<ClassCode> classcodeList;
	private List<Statement> statementsList;
	
	
	@Before
	public void setUp(){
		updateList = new LinkedList<String>();
		removeList = new LinkedList<String>();
		testsList = new LinkedList<TestCase>();
		methodsList = new LinkedList<Method>();
		classcodeList = new LinkedList<ClassCode>();
		statementsList = new LinkedList<Statement>();
	}
	
	@Test
	public void testUpdateEmptyLists(){
		updateList = Util.update(updateList,removeList); 
		assertArrayEquals(new String[]{},  updateList.toArray());
	}
		
	@Test
	public void testUpdateList() {
		
		List<String> updateList = new LinkedList<String>();
		List<String> removeList = new LinkedList<String>();
		
		String [] list = {"a","b","c","d","f","g","h","i"};
		
		for (String s : list)
			updateList.add(s);
		
		assertTrue(updateList.size() == 8);
		
		removeList.add("a");
		removeList.add("b");
		removeList.add("c");
		
		assertArrayEquals(list,  updateList.toArray());
				
		updateList = Util.update(updateList,removeList);
		
		assertTrue(updateList.size() == 5);
				
		assertArrayEquals(new String[] {"d","f","g","h","i"}, updateList.toArray());
		
		removeList.clear();
		
		removeList.add("i");
		removeList.add("f");
		removeList.add("g");
		
		updateList = Util.update(updateList,removeList);
		
		assertArrayEquals(new String[] {"d","h"}, updateList.toArray());
	}
	
	@Test
	public void testBiggerStatement() throws EmptySetOfTestCaseException{
		
		String [] methodsName = {"m1", "m2", "m3", "m4", 
 				"m5", "m6", "m7", "m8",
 				"m9", "m10", "m11","m12",
 				"m13", "m14", "m15","m16",
 				"m17", "m18", "m19","m20",
 				"m21", "m22", "m23","m24",
 				"m25", "m26", "27","m28"};

		String [] testsName = {"test1", "test2", "test3", "test4"};
		
		String [] classesName = {"A", "B","C","D","E","F"};
		
		String [] statementsLine = {"11", "13", "9", "9", "15", "21",
									"22", "23", "26", "29", "25", "28",
									"41", "43", "59", "69", "55", "51",
									"31", "113", "90", "190", "150", "201",
									"311", "113", "910", "101", "110", "100",
									"431", "132", "204", "290", "350", "401",
									"341", "313", "391", "139", "145", "271",
									"371", "114", "940", "180", "415", "291"
									};
		
		//create List with methods
		for (String methodname : methodsName){
			methodsList.add(new Method(methodname));
		}
		assertTrue(methodsList.size()==28);
		
		//create a List with Test
		for (String testname : testsName){
			testsList.add(new TestCase(testname));
		}
		
		assertTrue(testsList.size()==4);
		
		//create a list of classes
		String packageName = "pkg"; 
		for (String className : classesName){
			classcodeList.add(new ClassCode(packageName, className));
		}
		
		assertTrue(classcodeList.size() == 6);
		
		//create a list of Statements
		for (String line : statementsLine){
			statementsList.add(new Statement(line));
		}
		
		assertTrue(statementsList.size() == 48);
		
		//put 6 statement to method m1
		for (int i=0; i< 6; i++)
			methodsList.get(0).addStatement(statementsList.get(i));
		
		assertTrue(methodsList.get(0).getNumberStatements() == 6);
		assertTrue(methodsList.get(0).getUniqueNumberStatements() == 5);
		
		//put 6 statement to method m2
		for (int i=6; i < 12 ; i++)
			methodsList.get(1).addStatement(statementsList.get(i));
		
		assertTrue(methodsList.get(1).getNumberStatements() == 6);
		assertTrue(methodsList.get(1).getUniqueNumberStatements() == 6);
					
		//put 10 statement to method m3
		for (int i=12; i < 22 ; i++)
			methodsList.get(2).addStatement(statementsList.get(i));
		
		assertTrue(methodsList.get(2).getNumberStatements() == 10);
		assertTrue(methodsList.get(2).getUniqueNumberStatements() == 10);
		
		
		//put 20 statement to method m4
		for (int i=22; i < 42 ; i++)
			methodsList.get(3).addStatement(statementsList.get(i));
		
		assertTrue(methodsList.get(3).getNumberStatements() == 20);
		assertTrue(methodsList.get(3).getUniqueNumberStatements() == 20);
		
		//put 15 statement to method m5
		for (int i=0; i < 15 ; i++)
			methodsList.get(4).addStatement(statementsList.get(i));
		
		assertTrue(methodsList.get(4).getNumberStatements() == 15);
		assertTrue(methodsList.get(4).getUniqueNumberStatements() == 14);
		
		//put 30 statements to method m6
		for (int i=0; i < 30 ; i++)
			methodsList.get(5).addStatement(statementsList.get(i));
		
		assertTrue(methodsList.get(5).getNumberStatements() == 30);
		assertTrue(methodsList.get(5).getUniqueNumberStatements() == 28);
		
		
		//put 1 statement to each other methods
		for (int i=6; i < methodsList.size(); i++){
			methodsList.get(i).addStatement(statementsList.get(i));
		}
		
		//put 5 methods to class A
		for (int i=0; i< 5; i++)
			classcodeList.get(0).addMethod(methodsList.get(i));
		
		assertTrue(classcodeList.get(0).getMethodCoverage().size() == 5);
		
		//put 5 methods to class B
		for (int i=5; i< 10; i++)
			classcodeList.get(1).addMethod(methodsList.get(i));
		
		
		//put 5 methods to class C
		for (int i=10; i < 15; i++)
			classcodeList.get(2).addMethod(methodsList.get(i));
		
		//put 6 method to class D
		for (int i=15; i< 21; i++)
			classcodeList.get(3).addMethod(methodsList.get(i));
		
		//put 6 method to class E
		for (int i=0; i< 6; i++)
			classcodeList.get(4).addMethod(methodsList.get(i));
		
		//put 10 method to class F
		for (int i=0; i< 10; i++)
			classcodeList.get(5).addMethod(methodsList.get(i));
				
		//set class code to tests
		//put 3 classes to test1
		for (int i=0; i < 3; i++) 
			testsList.get(0).addClassCoverage(classcodeList.get(i));
		
		assertTrue(testsList.get(0).getStatementsCoverage().size()== 96);
		assertTrue(testsList.get(0).getStatementsCoverageDistinct().size()== 92);
		
		//put 2 classes to test2
		for (int i=0; i < 2; i++) 
			testsList.get(1).addClassCoverage(classcodeList.get(i));
		
		assertTrue(testsList.get(1).getStatementsCoverageDistinct().size()== 87);
		
		//put 3 classes to test3
		for (int i=3; i < 6; i++) 
			testsList.get(2).addClassCoverage(classcodeList.get(i));
		
		assertTrue(testsList.get(2).getStatementsCoverageDistinct().size()== 176);
		
		
		//put 1 class to test4
		testsList.get(3).addClassCoverage(classcodeList.get(0));
		
		assertTrue(testsList.get(3).getStatementsCoverageDistinct().size()== 55);
		
		//set information about number statement
		for (TestCase test : testsList){
			test.setNumberStatementsCoverageDistinct(test.getStatementsCoverageDistinct().size());
		}
		
		int positionBiggerTestByStatementNumber = Util.biggerTestStatement(testsList);
		
		System.out.println(positionBiggerTestByStatementNumber);
		
		assertTrue(positionBiggerTestByStatementNumber == 2);
	}
	
	@Test
	public void testBiggerMethod() throws EmptySetOfTestCaseException{
		String [] methodsName = {"m1", "m2", "m3", "m4", 
				 				"m5", "m6", "m7", "m8",
				 				"m9", "m10", "m11","m12",
				 				"m13", "m14", "m15","m16",
				 				"m17", "m18", "m19","m20",
				 				"m21", "m22", "m23","m24",
				 				"m25", "m26", "27","m28"};
		
		String [] testsName = {"test1", "test2", "test3", "test4", 
 								"test5", "test6", "test7", "test8",
 								"test9", "test10", "test11","test12"};
		
		String [] classesName = {"A", "B","C","D","E","F"};
		
		//create List with methods
		for (String methodname : methodsName){
			methodsList.add(new Method(methodname));
		}
		assertTrue(methodsList.size()==28);
		
		//create a List with Test
		for (String testname : testsName){
			testsList.add(new TestCase(testname));
		}
		
		assertTrue(testsList.size()==12);
		
		//create list of classes
		String packageName = "pkg"; 
		for (String className : classesName){
			classcodeList.add(new ClassCode(packageName, className));
		}
		
		assertTrue(classcodeList.size() == 6);
		
		//added 5 methods to class A
		for (int i=0; i < 5; i++)
			classcodeList.get(0).addMethod(methodsList.get(i));
		
		assertTrue(classcodeList.get(0).getNumberMethodCoverage()==5);
		
		//added 9 methods to class B
		for (int i=5; i<14; i++ )
			classcodeList.get(1).addMethod(methodsList.get(i));
		
		assertTrue(classcodeList.get(1).getNumberMethodCoverage()==9);
		
		//added 14 methods to class C
		for (int i=14; i<28; i++ )
			classcodeList.get(2).addMethod(methodsList.get(i));
		
		//added 11 methods to class D
		for (int i=0; i<11; i++ )
			classcodeList.get(3).addMethod(methodsList.get(i));
		
		//add 3 methods to class E
		for (int i=0; i<3; i++ )
			classcodeList.get(4).addMethod(methodsList.get(i));
		
		//add 2 methods to class F
		for (int i=0; i<2; i++ )
			classcodeList.get(5).addMethod(methodsList.get(i));
				
		//Put 2 classes to test1
		for (int i=0; i<2; i++)
			testsList.get(0).addClassCoverage(classcodeList.get(i));
		
		//this the has 2 to classes
		assertTrue(testsList.get(0).getNumberMethodCoverage()==14);
		assertTrue(testsList.get(0).getMethodCoverageDistinct().size() ==14);
		
		//Put 6 classes to test2
		for (int i=0; i<6; i++)
			testsList.get(1).addClassCoverage(classcodeList.get(i));
		
		//the sum 5 + 9 + 14 + 11 + 3 + 2  number of method covered by each class. 
		assertTrue(testsList.get(1).getMethodCoverageDistinct().size() == 44);
		
		//Put 3 classes to test3
		for (int i=0; i<3; i++)
			testsList.get(2).addClassCoverage(classcodeList.get(i));
		
		assertTrue(testsList.get(2).getMethodCoverageDistinct().size() == 28);
		
		for (int i=3 ; i < testsList.size(); i++)
			assertTrue(testsList.get(i).getMethodCoverageDistinct().size()==0);
	
		//set the needed information
		for (TestCase test: testsList)
			test.setNumberMethodsCoveredDistinct(test.getMethodCoverageDistinct().size());
		
		int positionBiggerTest = Util.biggerTestMethod(testsList);
			
		assertTrue(positionBiggerTest==1);
		
		testsList.remove(positionBiggerTest);
		
		//the new bigger test is still in the same position
		
		positionBiggerTest = Util.biggerTestMethod(testsList);
		
		assertTrue(positionBiggerTest==1);
		
		testsList.remove(positionBiggerTest);
		
		positionBiggerTest = Util.biggerTestMethod(testsList);
		
		assertTrue(positionBiggerTest==0);
	}
	
	@Test(expected = EmptySetOfTestCaseException.class)
	public void testBiggerMethodWhenIsEmptyList() throws EmptySetOfTestCaseException{
		int positionBiggerTest = Util.biggerTestMethod(testsList);
		assertTrue(positionBiggerTest == 0);
	}
	
	

}
