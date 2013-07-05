package technique;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import util.Util;

import coverage.TestCase;

import exception.EmptySetOfTestCaseException;

/**
 * Total Method Coverage (TMC): Similar to the TSC, differing by using the 
 * number of covered methods instead of covered statements as ordering criteria. 
 * 
 * @author Samuel T. C. Santos
 * @version 1.0
 * 
 */
public class TechniqueTotalMethod implements Technique {
    
    private List<TestCase> tests;
    /**
     * Constructor. 
     * 
     * @param tests
     * 			A list of <code>coverage.TestCase</code>
     */
    public TechniqueTotalMethod(List<TestCase> tests){
        this.tests = tests;
    }

    public double getWeight(List objectList) {
        List aloneMethod = new ArrayList();
    	
    	for (Object o: objectList) 
    		if (!aloneMethod.contains(o)) 
    			aloneMethod.add(o);
    	
        return aloneMethod.size();
    }

    public List<String> assingWeight() throws EmptySetOfTestCaseException {
    	
    	if (this.tests.size() == 1)
    		return Arrays.asList(tests.get(0).getSignature());
    	else if (this.tests.isEmpty())
    		throw  new EmptySetOfTestCaseException();
        
        List<String> prioritizedList = new ArrayList<String>();
        
        List<TestCase> copyList = new ArrayList<TestCase>(tests);
        
        TestCase test = null;
        
        while(!copyList.isEmpty()){
            int testIndex = Util.biggerTestMethod(copyList);
            prioritizedList.add(copyList.get(testIndex).getSignature());
            copyList.remove(testIndex);
        }
       
        return prioritizedList;
    }
    
}

