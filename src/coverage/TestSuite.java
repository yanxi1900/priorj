package coverage;

import java.util.ArrayList;
import java.util.List;

/**
 * <code> TestSuite</code> represents a test suite, i.e., a set of
�*test cases, the package name where the suite is located and the name of the suite.
 * 
 * @author Samuel Santos
 * @author Julio Henrique
 *
 */
public class TestSuite {

    private String packageName;
    private String name;
    private List<TestCase> testCases;
    
    /**
     * <code> TestSuite</code>  constructor.
     * @param packageName the package name.
     * @param name the suite name.
     */
    public TestSuite(String packageName, String name) {
        this.packageName = packageName;
        this.name = name;
        this.testCases = new ArrayList<TestCase>();
    }

    /**
     * Add a test case in the suite.
     * 
     * @param tc a object of type <code>TestCase</code>.
     */
    public void addTestCase(TestCase tc) { 
        testCases.add(tc);
    }

    /**
     * Get the suite name.
     * 
     * @return the suite name.
     */
    public String getName() {
        return name;
    }

    /**
     * Send a name to suite.
     * 
     * @param name a <code>String</code> representing the suite name.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get the package name.
     * 
     * @return the package name.
     */
    public String getPackageName() {
        return packageName;
    }

    /**
     * Set the package name.
     * 
     * @param packageName the package name.
     */
    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    /**
     * Get the list of <code>TestCase</code> in this <code>TestSuite</code>.
     * 
     * @return the list of <code>TesteCase</code>.
     */
    public List<TestCase> getTestCases() {
        return testCases;
    }

    /**
     * Set the list of test cases to test suite.
     * 
     * @param testCases a list of objects type <code>TestCase</code>.
     */
    public void setTestCases(List<TestCase> testCases) {
        this.testCases = testCases;
    }


    /**
     * Get the numbers of Test Suites.
     * 
     * @return the number of test suites.
     */
    public int getNumberOfSuites() {
        return testCases.size();
    }
    
    
    /**
     * Compare two objects of type <code>TestSuite</code>.
     * 
     * @param obj a object of type<code> Statement</code>
     * 
     * @return returns zero if equal, negative if less, positive if greater
     */
    public int compareTo(TestSuite suite){
    	return getNumberOfSuites() - suite.getNumberOfSuites();
    }
  
    /* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((packageName == null) ? 0 : packageName.hashCode());
		result = prime * result + ((testCases == null) ? 0 : testCases.hashCode());
		return result;
	}

	 /**
     * Compare two objects of type <code>TestSuite</code> and says if are equal.
     * 
     * @param obj  a object of type <code>Statement</code>.
     * 
     * @return  true or false 
     */
   
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TestSuite other = (TestSuite) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (packageName == null) {
			if (other.packageName != null)
				return false;
		} else if (!packageName.equals(other.packageName))
			return false;
		if (testCases == null) {
			if (other.testCases != null)
				return false;
		} else if (!testCases.equals(other.testCases))
			return false;
		return true;
	}

    
    /**
     * View the data object of type <code>TestSuite</code>.
     * @return the <code>String</code> representing the object.
     */
    public String toString() {
        return packageName + "." + name;
    }

}
