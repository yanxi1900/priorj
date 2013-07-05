package technique;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import coverage.TestCase;



public class TechniqueRandom implements Technique {

    private List<TestCase> tests;
    
    public TechniqueRandom(List<TestCase> tests){
        this.tests  = tests;
    }
    
    public double getWeight(List objectList) {
        int count = (int)( 100*Math.random());
        return count;
    }


    public List<String> assingWeight() {
        
        Collections.shuffle(tests);
        
        List<String> signatures = new ArrayList<String>();
        
        for (TestCase test : tests)
        	signatures.add(test.getSignature());
        
        return signatures;
    }

}
