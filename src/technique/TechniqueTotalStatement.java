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
