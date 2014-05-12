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
import java.util.List;

import coverage.TestCase;

public class TechniqueAdditionalStatement implements Technique {

    public TechniqueAdditionalStatement(){
    
     }

	@Override
	public List<String> prioritize(List<TestCase> tests) throws EmptySetOfTestCaseException {
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
           testIndex = TechniqueAssignWeight.biggerTestStatement(copyList);
           allObjects = TechniqueAssignWeight.update(allObjects, copyList.get(testIndex).getStatementsCoverageDistinct());
         
           suiteList.add(copyList.get(testIndex).getSignature());
           
           copyList.remove(testIndex);
            
       }
       
       return suiteList;
	}
    
}
