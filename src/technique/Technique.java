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
import java.util.List;

import exception.EmptySetOfTestCaseException;

/**
 * This interface represent a technique used for  prioritization.
 * 
 * @author Samuel Santos
 * @author Julio Henrique
 *
 */
public interface Technique {

	/**
	 * Assing weight for a list of object, i.e, methods or statements.
	 * 
	 * @param objectList a list of object type <code>Method</code>
	 *        or <code> Statement</code>
	 * @return a double
	 */
    public double getWeight(List objectList);
    
    /**
     * This method assing weith and does the prioritization process 
     * in a set of test case.
     * 
     * @return a list of prioritized tests.
     * 
     * @throws EmptySetOfTestCaseException when the set of test is empty.
     * 			
     */
    public List<String> assingWeight () throws EmptySetOfTestCaseException;
    
}
