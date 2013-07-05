package apfd;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import apfd.GenerateAPFD;

public class GenerateAPFDTest {

	private GenerateAPFD apfdGenerate;
	
	@Before
	public void setUp(){	
		apfdGenerate = new GenerateAPFD();
	}
	
	@Test
	public void testSmallestPosition() {
		
		String [] testNames = {"testA", "testB", "testC", "testD", "testD", "testF", "testG"};
		
		List<String> tests = Arrays.asList(testNames);
		
		List<String> combinationOne = Arrays.asList("testA", "testG", "testD");
		List<String> combinationTwo = Arrays.asList("testB", "testD", "testF");
		List<String> combinationThree = Arrays.asList("testF", "testG", "testD");
		
		apfdGenerate.setTests(tests);
		
		assertEquals(1 , apfdGenerate.getSmallestPositionTest(combinationOne));
		assertEquals(2 , apfdGenerate.getSmallestPositionTest(combinationTwo));
		assertEquals(4 , apfdGenerate.getSmallestPositionTest(combinationThree));
		
	}

	@Test 
	public void testValuesPositionsArray(){
		
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
		
	}
	
	@Test
	public void testNumberFailure(){
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
