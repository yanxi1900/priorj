package technique;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import util.SaveFile;
import util.TestCaseComparable;

import coverage.TestCase;



/**
 * ...
 * 
 * @author Samuel T. C. Santos
 *
 */
public class TechniqueRefactoringBasedApproach implements Technique {

    List<String> impactedMethods;
    List<TestCase> tests;
    List<TestCaseComparable> impactedTest;

    /**
     * 
     * @param impacted
     * @param tests
     */
    public TechniqueRefactoringBasedApproach(List<String> impacted, List<TestCase> tests){
            this.impactedMethods = impacted;
            this.tests = tests;
            this.impactedTest = new ArrayList<TestCaseComparable>();
    }

    /**
     * This method say if a method is in list of methods.
     * 
     * @param value a method or statement.
     * 
     * @return true or false
     */
    public boolean containsObject(Object value){
            Iterator<String> itCoveraged = impactedMethods.iterator();
            while (itCoveraged.hasNext()) {
                    String methodName = itCoveraged.next();
                    
                    if(methodName.equals(value)){
                            return true;
                    }
            }
            return false;
    }

    public double getWeight(List objectList) {
            int count = 0;
            Iterator<String> itObjects = objectList.iterator();
            while (itObjects.hasNext()) {
                    String methodName = itObjects.next();
                    if(containsObject(methodName)){
                           count++;
                    }
            }
            return count;
    }


    public List<String> assingWeight() {
        
        List<TestCase> copyList = new ArrayList<TestCase>(tests);
       
        //prioritized list 
        List<String> suiteList = new ArrayList<String>();
        
        List<TestCase> notImpacted = new ArrayList<TestCase>();
        
        //find all tests with weight
        for (TestCase test : copyList){
            
            double weight = getWeight(test.getMethodCoverage());
            
			if (weight != 0.0){
               impactedTest.add(new TestCaseComparable(weight,test));
            }
            else notImpacted.add(test);
            
        }
        
        
        //Shuffle list test case without weight
        Collections.shuffle(notImpacted);
        
        //Sorting impacted tests
        Collections.sort(impactedTest);
        
        for (int i = impactedTest.size()-1; i >= 0; i--) {
        	TestCase testcase = impactedTest.get(i).getTestCase();
        	suiteList.add(testcase.getSignature());
		}
        for (TestCase test : notImpacted)
        	suiteList.add(test.getSignature());
  
        return suiteList;
    }
    
     public TestCase biggerWeight(List<TestCase> tests){
        int indexBigger = 0;
        double bigger=0;
        
        //store the weights
        List<Double> weightList = new ArrayList<Double>();
        
        int index=0;
        for (TestCase test: tests){
            
            double weight = getWeight(test.getMethodCoverage());
            weightList.add(weight);
            
            if (weight > bigger){
                indexBigger = index;
                bigger = weight;
            }
            index++;
        }
        
        List<TestCase> sameWeight = new ArrayList<TestCase>();
        
        //find tests with same weight.
        for (int i=0 ; i < weightList.size(); i++){
            if (weightList.get(i) == bigger){
                sameWeight.add(tests.get(i));
            }
        }
        
        if (sameWeight.size()>1)
            Collections.shuffle(sameWeight);
        
        return sameWeight.get(0);
        
    }
     
     /**
      * Save to a file the list of method with weight.
      * 
      */
     public void saveImpactedTests(){
         List<String> testnames = new ArrayList<String>();
         
         for (TestCaseComparable obj: impactedTest){
             TestCase test = obj.getTestCase();
        	 testnames.add(test.getSignature());
         }    
         SaveFile.save(testnames);
     }

}
