package technique;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import util.Util;

import coverage.TestCase;

import exception.EmptySetOfTestCaseException;

/**
 * This class implements the Total Statement Technique.
 * 
 * @author Samuel T. C. Santos
 *
 */
public class TechniqueTotalStatement implements Technique {
    
    List<TestCase> tests;
    
    /**
     * The constructor  
     * @param tests a test cases list.
     */
    public TechniqueTotalStatement(List<TestCase> tests){
        this.tests = tests;
    }
    
    public double getWeight(List objectList) {
        
        List aloneStmt = new ArrayList();
    	
    	for (Object o: objectList)
    		if (!aloneStmt.contains(o)) aloneStmt.add(o);
    	
        return aloneStmt.size();
    }
    
    public List<String> assingWeight() throws EmptySetOfTestCaseException  {
    	
    	if (this.tests.size() == 1)
    		return Arrays.asList(tests.get(0).getSignature());
    	else if (this.tests.isEmpty())
    		throw  new EmptySetOfTestCaseException();
    	
        List<String> prioritizedList = new ArrayList<String>();
        
        List<TestCase> copyList = new ArrayList<TestCase>(tests);
      
        int testIndex = 0;
        
        while(!copyList.isEmpty()){
            testIndex = Util.biggerTestStatement(copyList);
         
            prioritizedList.add(copyList.get(testIndex).getSignature());
          
            copyList.remove(testIndex);
        }   
            
        return prioritizedList;
    }

}
