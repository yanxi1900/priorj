package util;


import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import coverage.TestCase;

import exception.EmptySetOfTestCaseException;

/**
 * This is an auxiliary class to Prioritization Techniques.
 * 
 * @author Samuel T. C. Santos
 * @version 1.0
 */
public class Util {
    
	/**
	 * This method remove the content inside the list.
	 * 
	 * @param listMethods a list of strings.
	 * @param list a list with string which will be removed from firts list.
	 * 
	 * @return the first list.
	 */
    public static List<String> update(List<String> listMethods, List<String> list){
    	
    	for (String s: list){
    		if (listMethods.contains(s)){
    			int index = listMethods.indexOf(s);
    			listMethods.remove(index);
    		}
    	}
    	return listMethods;
    }

    /***
     * Find the position of the bigger test case considering the
     * number of statements coverage.
     * 
     * @param testList a TestCase list.
     * 
     * @return a index.
     * @throws EmptySetOfTestCaseException 
     */
    public static Integer biggerTestStatement(List<TestCase> testList) throws EmptySetOfTestCaseException{
    	
    	if (testList.isEmpty())
    		throw new EmptySetOfTestCaseException();
    	
        int index=0;
        int bigger=0;
        int indexBigger=0;

        for (TestCase test : testList){
            if (test.getNumberStatementsCoverageDistinct() > bigger) {
                bigger = test.getNumberStatementsCoverageDistinct();
                indexBigger = index;
            }
            index++;
        }

        Map<Integer, TestCase> sameWeight = new HashMap<Integer, TestCase>();

        for (int i = 0 ; i < testList.size(); i++){
            TestCase testCurrent  =  testList.get(i);

            if (testCurrent.getNumberStatementsCoverageDistinct()==bigger)
                sameWeight.put(i, testCurrent);
        }

        int randomIndex = 0;
        Set listKeys = sameWeight.keySet();
          
        if (sameWeight.size()>1){
            Double random = Math.random() * sameWeight.size(); 
        
            randomIndex = random.intValue();
                    
            int counter=0;
            
            for (Object valueKey : listKeys){
                if (counter == randomIndex){
                    randomIndex = (Integer )valueKey;
                    break;
                }
                counter++;
            }

            return randomIndex;
        }
        else{
             Iterator it = listKeys.iterator();
             Integer elementKey = (Integer) it.next();
             
             return  elementKey;
        }

    }

    /**
     * Find the bigger test case position considering the
     * number of methods distinct.
     * 
     * @param testList a list of Test Case
     * 
     * @return a index.
     */
    public static Integer biggerTestMethod(List<TestCase> testList) throws EmptySetOfTestCaseException{
    	
    	if (testList.isEmpty())
    		throw new EmptySetOfTestCaseException();
    	
        int index=0;
        int bigger=0;
        int indexBigger=0;

        for (TestCase test : testList){
            if (test.getNumberMethodsCoveredDistinct() > bigger) {
                bigger = test.getNumberMethodsCoveredDistinct();
                indexBigger = index;
            }
            index++;
        }

        Map<Integer, TestCase> sameWeight = new HashMap<Integer, TestCase>();

        for (int i = 0 ; i < testList.size(); i++){
            TestCase testCurrent  =  testList.get(i);

            if (testCurrent.getNumberMethodsCoveredDistinct()==bigger)
                sameWeight.put(i, testCurrent);
        }

        int indexBigInt =0;
        
        Set listKeys = sameWeight.keySet();
      
        if (sameWeight.size()>1){
            Double indexBig = Math.random() * sameWeight.size(); 

            indexBigInt = indexBig.intValue();
            
            int counter=0;
            for (Object valueKey : listKeys){
                if (counter == indexBigInt){
                    indexBigInt = (Integer )valueKey;
                    break;
                }
                counter++;
            }
           return indexBigInt;
        }
        else{
             Iterator it = listKeys.iterator();
             Integer element = (Integer) it.next();
             
             return  element;
        }
    }
           
}//class end
