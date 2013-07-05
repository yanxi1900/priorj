package technique;

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
