package technique;

import java.util.ArrayList;
import java.util.List;

import util.Util;

import coverage.TestCase;

import exception.EmptySetOfTestCaseException;


public class TechniqueAdditionalStatement implements Technique {

    private List<TestCase> tests;
    
    public TechniqueAdditionalStatement(List<TestCase> tests){
        this.tests = tests;
     }

    public double getWeight(List objectList) {
        return 0;
    }

  
    public List<String> assingWeight() throws EmptySetOfTestCaseException {
         List<TestCase> copyList = new ArrayList<TestCase>(tests);
        
        List<String> allObjects = new ArrayList<String>();
        
        List<String> suiteList = new ArrayList<String>();
        
        for (TestCase test: tests){
            for (String method : test.getStatementsCoverageDistinct()){
                allObjects.add(method);
            }
        }
        
        int testIndex=0;
        
        while(!copyList.isEmpty()){
            testIndex = Util.biggerTestStatement(copyList);
            allObjects = Util.update(allObjects, copyList.get(testIndex).getStatementsCoverageDistinct());
          
            suiteList.add(copyList.get(testIndex).getSignature());
            
            copyList.remove(testIndex);
             
        }
        
        return suiteList;
    }
    
}
