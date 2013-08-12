package technique;


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

