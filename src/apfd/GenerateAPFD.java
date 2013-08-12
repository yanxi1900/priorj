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
import java.util.LinkedList;
import java.util.List;


/**
 * This class generate the APFD value.
 * 
 * @author Samuel T. C. Santos
 * @version 1.0
 */
public class GenerateAPFD {

	private List<String> tests;
	private List<List<String>> combination;
	
	/**
	 * Constructor the class GenerateAPFD.
	 * 
	 * @param tests a list of prioritized test cases names.
	 * @param combination a list of list with tests names. 
	 */
    public GenerateAPFD(List<String> tests, List<List<String>> combination){
    	this.tests = tests;
    	this.combination = combination;
    }
    /**
     * Constructor default.
     */
    public GenerateAPFD(){
    	this.tests = new LinkedList<String>();
    	this.combination = new LinkedList<List<String>>();
    }
    
    /**
     * Return the test position into the prioritized suite.
     * 
     * @param testName the test case name.
     * 
     * @return the position.
     */
    public int getSmallestPositionTest(List<String> testList){
    	
    	int smallest = tests.indexOf(testList.get(0));
    	
    	for (String test: testList){
    		int position = tests.indexOf(test);
    		
    		if (position < smallest) 
    			smallest = position;
    	}
    	
    	return smallest + 1;
    }
    
    /**
     * The method return the prioritized position for
     * a given test case name.
     * 
     * @param testName the test case name.
     * 
     * @return the test position.
     */
    public int getPositionTest(String testName){
    	return this.tests.indexOf(testName) + 1;
    }
    
    /**
     * This method calculate the smallest position test inside
     * of each list passed in the combination tests.
     * 
     * @param combinationTests an list of list with test cases names.
     * @return an array with positions.
     */
    public int [] values(List<List<String>> combinationTests){
    	int [] values = new int[combinationTests.size()];
    	
    	for (int i=0; i< combinationTests.size(); i++){
    		values[i] = getSmallestPositionTest(combinationTests.get(i));
    	}
    	
    	return values;
    }
    
    
    /**
     * SET the prioritized test case list.
     * 
     * @param tests an list of test cases.
     */
    public void setTests(List<String> tests){
    	this.tests = tests;
    }
    
    /**
     * Set the test case failed list.
     * 
     * @param testsFailed a
     *          list of test cases failed.
     */
    public void setTestsCombination(List<List<String>> combination){
    	this.combination = combination;
    }
    
    /**
     * This method calculate the APFD.
     * 
     * @return the APDF value.
     */
    public float calculateAPFD(){
    	
    	float m = getNumberFaults(); //faults
        float n = getNumberTests();
        
        int [] positions = values(combination);
        
        float apfd = 1 - ( sumArray(positions) / (m * n)) + 1 / (2 * n);
             
        return apfd * 100;
    }
    
    /**
     * This method calculate the sum of all elements into the array.
     * 
     * @param array 
     *          a array of integer.
     * 
     * @return the sum of the elements.
     */
    public int sumArray(int [] array){
    	int sum = 0;
    	for (int x : array)
    		sum+= x;
    	return sum;
    }
    
    /**
     * This method compute the total of failed test cases
     * informed into the combination test lists.
     * 
     * @param combination an list of list with test case names.
     * @return an integer.
     */
    public int getNumberFaults(){
    	return combination.size();
    }
    
    /**
     * This method return the total test of the 
     * prioritized suite.
     * 
     * @return the number of tests.
     */
    public int getNumberTests(){
    	return tests.size();
    }
    
    /**
     * This method does a selection of every failed test cases.
     * 
     * @return a array of test case names.
     */
    public Object[] getFailedTests(){
    	List list = new LinkedList<String>();
    	
    	for (List<String> testList : combination)
    		for (String test : testList)
    			if (!list.contains(test))
    				list.add(test);
    	
    	return list.toArray();
    }
    
    /**
     * This method calculate the percentage of suite execution
     * until found the fault.
     * 
     * tests.size() ------ 100
     * test_position ---- x
     * 
     * x = test_position * 100 / tests.size()
     * 
     * where x represent the executed suite fraction!
     * 
     * @param position the position test.
     * 
     * @return a percentage of the suite fraction.
     */
    public double suiteFraction(int position){
    	int size = tests.size();
    	
    	return position * 100 /size;
    }
    
    /**
     * This method generate a serie of value for APDF Chart.
     * 
     * @return a serie of values.
     */
//    public double [][]  generateSerie(){
//    	
//    	Object [] failedTest = getFailedTests();
//    	
//    	List<Double> list = new LinkedList<Double>();
//    	
//    	for (Object test : failedTest){
//    		int position = getPositionTest(String.valueOf(test));
//    		list.add(suiteFraction(position));
//    	}
//    		
//    	double [][] serie = {};
//    	
//    	return serie;
//    }
}
