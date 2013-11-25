package apfd;

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
import static org.junit.Assert.*;

import input.InputParse;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import coverage.TestCase;
import coverage.TestSuite;
import exception.EmptySetOfTestCaseException;

import technique.TechniqueAdditionalMethod;
import util.PathTo;

import apfd.GenerateAPFD;

public class GenerateAPFDTest {

	private GenerateAPFD apfdGenerate;
	
	private String path;	
	private String filename;
	
	private InputParse parse;
	
	private TechniqueAdditionalMethod asc;
	
	@Before
	public void setUp(){	
		apfdGenerate = new GenerateAPFD();
		filename = "test-suite-five.txt";
		path = PathTo.RESOURCES_PRIORITIZATION_FILES + PathTo.SEPARATOR + filename;
		
	}
	
	@Test
	public void testSmallestPosition() throws EmptySetOfTestCaseException {
		
		String [] testNames = {"testA", "testB", "testC", "testD", "testD", "testF", "testG"};
		
		List<String> tests = Arrays.asList(testNames);
		
		List<String> combinationOne = Arrays.asList("testA", "testG", "testD");
		List<String> combinationTwo = Arrays.asList("testB", "testD", "testF");
		List<String> combinationThree = Arrays.asList("testF", "testG", "testD");
		
		apfdGenerate.setTests(tests);
		
		assertEquals(1 , apfdGenerate.getSmallestPositionTest(combinationOne));
		assertEquals(2 , apfdGenerate.getSmallestPositionTest(combinationTwo));
		assertEquals(4 , apfdGenerate.getSmallestPositionTest(combinationThree));
		
		//With Additional Statement
		
		parse = new InputParse(path, "testsuite");
		parse.runParse();
		List<TestCase> tests_2 = parse.getResultAsTestCase();
		
		asc = new TechniqueAdditionalMethod(tests_2);
		
		List<String> prioritization = asc.assingWeight();
		List<String> combOne = Arrays.asList(prioritization.get(0).toString(), prioritization.get(3).toString(), prioritization.get(5).toString());
		List<String> combTwo = Arrays.asList(prioritization.get(1).toString(), prioritization.get(7).toString(), prioritization.get(2).toString());
		List<String> combThree = Arrays.asList(prioritization.get(10).toString(), prioritization.get(3).toString(), prioritization.get(5).toString());
		List<String> combFour = Arrays.asList(prioritization.get(6).toString(), prioritization.get(9).toString(), prioritization.get(1).toString());
		
		apfdGenerate.setTests(prioritization);
		
		assertEquals(1, apfdGenerate.getSmallestPositionTest(combOne));
		assertEquals(2, apfdGenerate.getSmallestPositionTest(combTwo));
		assertEquals(4, apfdGenerate.getSmallestPositionTest(combThree));
		assertEquals(2, apfdGenerate.getSmallestPositionTest(combFour));
		
		/*System.out.println(apfdGenerate.getSmallestPositionTest(combOne));
		System.out.println(apfdGenerate.getSmallestPositionTest(combTwo));
		System.out.println(apfdGenerate.getSmallestPositionTest(combThree));
		System.out.println(apfdGenerate.getSmallestPositionTest(combFour));*/
	}

	@Test 
	public void testValuesPositionsArray() throws EmptySetOfTestCaseException{
		
		String [] testNames = {"testA", "testB", "testC", "testD", "testD", "testF", "testG"};
		List<String> tests = Arrays.asList(testNames);
		apfdGenerate.setTests(tests);
		
		List<String> combinationOne = Arrays.asList("testF", "testG", "testD");
		List<String> combinationTwo = Arrays.asList("testB", "testD", "testF");
		List<String> combinationThree = Arrays.asList("testF", "testG", "testC");
		
		List<List<String>> combination = new LinkedList<List<String>>();

		combination.add(combinationOne);
		combination.add(combinationTwo);
		combination.add(combinationThree);
		
		int [] values = {4, 2, 3};
		
		assertArrayEquals(values , apfdGenerate.values(combination));
		
		//With Additional Statement
		
		parse = new InputParse(path, "testsuite");
		parse.runParse();
		List<TestCase> tests_2 = parse.getResultAsTestCase();
		
		asc = new TechniqueAdditionalMethod(tests_2);
		
		List<String> prioritization = asc.assingWeight();
		List<String> combOne = Arrays.asList(prioritization.get(0).toString(), prioritization.get(3).toString(), prioritization.get(5).toString());
		List<String> combTwo = Arrays.asList(prioritization.get(1).toString(), prioritization.get(7).toString(), prioritization.get(2).toString());
		List<String> combThree = Arrays.asList(prioritization.get(10).toString(), prioritization.get(3).toString(), prioritization.get(5).toString());
		List<String> combFour = Arrays.asList(prioritization.get(6).toString(), prioritization.get(9).toString(), prioritization.get(1).toString());
				
		apfdGenerate.setTests(prioritization);
		
		List<List<String>> combinations = new LinkedList<List<String>>();
		
		combinations.add(combOne);
		combinations.add(combThree);
		combinations.add(combTwo);
		combinations.add(combFour);
		
		int[] valuesList = {1, 4, 2, 2};
		
		assertArrayEquals(valuesList , apfdGenerate.values(combinations));
	}
	
	@Test
	public void testNumberFailure() throws EmptySetOfTestCaseException{
		List<String> combinationOne = Arrays.asList("testF", "testG", "testD");
		List<String> combinationTwo = Arrays.asList("testB", "testD", "testF");
		List<String> combinationThree = Arrays.asList("testF", "testG", "testC");
		
		List<List<String>> combination = new LinkedList<List<String>>();

		combination.add(combinationOne);
		apfdGenerate.setTestsCombination(combination);
		
		assertEquals(1, apfdGenerate.getNumberFaults());
		
		combination.add(combinationTwo);
		apfdGenerate.setTestsCombination(combination);
		
		assertEquals(2, apfdGenerate.getNumberFaults());
		
		combination.add(combinationThree);
		apfdGenerate.setTestsCombination(combination);
		
		assertEquals(3, apfdGenerate.getNumberFaults());
		
		//With Additional Statement
		
		parse = new InputParse(path, "testsuite");
		parse.runParse();
		List<TestCase> tests_2 = parse.getResultAsTestCase();
				
		asc = new TechniqueAdditionalMethod(tests_2);
			
		List<String> prioritization = asc.assingWeight();
		List<String> combOne = Arrays.asList(prioritization.get(0).toString(), prioritization.get(3).toString(), prioritization.get(5).toString());
		List<String> combTwo = Arrays.asList(prioritization.get(1).toString(), prioritization.get(7).toString(), prioritization.get(2).toString());
		List<String> combThree = Arrays.asList(prioritization.get(10).toString(), prioritization.get(3).toString(), prioritization.get(5).toString());
		List<String> combFour = Arrays.asList(prioritization.get(6).toString(), prioritization.get(9).toString(), prioritization.get(1).toString());
						
		apfdGenerate.setTests(prioritization);
			
		List<List<String>> combinations = new LinkedList<List<String>>();
			
		combinations.add(combOne);
		apfdGenerate.setTestsCombination(combinations);
		assertEquals(1, apfdGenerate.getNumberFaults());
		
		combinations.add(combThree);
		apfdGenerate.setTestsCombination(combinations);
		assertEquals(2, apfdGenerate.getNumberFaults());
		
		combinations.add(combTwo);
		apfdGenerate.setTestsCombination(combinations);		
		assertEquals(3, apfdGenerate.getNumberFaults());
		
		combinations.add(combFour);
		apfdGenerate.setTestsCombination(combinations);	
		assertEquals(4, apfdGenerate.getNumberFaults());
	}
	
	
	@Test
	public void testCalculateAPFD(){
		String [] tests = {"testA", "testB", "testC", "testD", "testD", "testF", "testG"};
		
		List<String> combinationOne = Arrays.asList("testF", "testA", "testD");
		List<String> combinationTwo = Arrays.asList("testB", "testD");

		List<List<String>> combination = new LinkedList<List<String>>();
		combination.add(combinationOne);
		combination.add(combinationTwo);
		
		apfdGenerate.setTests(Arrays.asList(tests));
		apfdGenerate.setTestsCombination(combination);
		
		assertTrue( apfdGenerate.getNumberFaults() == 2 );
		assertTrue( apfdGenerate.getNumberTests() == 7);
		
		int [] positions = apfdGenerate.values(combination);
		
		assertTrue( apfdGenerate.sumArray(positions) == 3);
		
		float m  = 2; //number faults
		float n = 7; //number tests
		
		float value = 1 - ((3/(m*n))) + 1/(2*n);
		value *= 100;
		
		assertTrue(value == apfdGenerate.calculateAPFD());
	}
	
	@Test 
	public void testSumElementsArray(){
		int [] arrayA = {1,2,3,4,5,6,7,8};
		int [] arrayB = {10,2,3,2,2,2,7,2};
		int [] arrayC = {1,2,3,1,1,2,2,10};

		assertEquals(36, apfdGenerate.sumArray(arrayA));
		assertEquals(30, apfdGenerate.sumArray(arrayB));
		assertEquals(22, apfdGenerate.sumArray(arrayC));	
	}
   
   @Test
	public void testPositionTest(){
		String [] tests = {"testA", "testB", "testC", "testD", "testD", "testF", "testG"};
		
		apfdGenerate.setTests(Arrays.asList(tests));
		
		assertTrue(1 == apfdGenerate.getPositionTest("testA"));
		
		assertTrue(4 == apfdGenerate.getPositionTest("testD"));
		
		assertTrue(7 == apfdGenerate.getPositionTest("testG"));
		
	}
   
   
	@Test
	public void testSuiteFraction(){
		String [] tests = {"testA", "testB", "testC", "testD", "testD", "testF", "testG"};
		
		int size = tests.length;
		int position = 2;
		
		double fraction = position * 100 / size;
		
		apfdGenerate.setTests(Arrays.asList(tests));
		
		assertTrue(fraction == apfdGenerate.suiteFraction(2));
		
		position = 5;
		fraction = position * 100 / size;
		
		assertTrue(fraction == apfdGenerate.suiteFraction(5));	
	}

	
}
