package dao;

import java.util.ArrayList;
import java.util.List;

import util.Settings;

import coverage.ClassCode;
import coverage.Method;
import coverage.Statement;
import coverage.TestCase;
import coverage.TestSuite;




/**
 * <code>Repository</code> The class repository enables data saved in XML format following 
 * the trail of the tests. is responsible for the construction of the log file.
 * 
 * @author Samuel Santos
 * @author Julio Henrique
 *
 */
public class Repository {

    private List<TestSuite> suites;
    private TestSuite atualSuite;
    private TestCase atualTestCase;
    private ClassCode atualClass;
    private Method atualMethod;
    private Writer escritor;
    
    /**
     * <code>Repository</code> Constructor.
     */
    public Repository() {
        this.suites = new ArrayList<TestSuite>();
        this.escritor = new Writer("report" + Settings.SEPARATOR +"coveragePriorJ");
        
    }

    /**
     * Get the method that is currently running.
     * 
     * @return a object of type <code>Method</code> 
     */
    public Method getAtualMethod() {
        return atualMethod;
    }

    /**
     * Set the method that is currently running.
     * 
     * @param atualMethod a object of type <code>Method</code>.
     */
    public void setAtualMethod(Method atualMethod) {
        this.atualMethod = atualMethod;
    }

    /**
     * Get the list of suite test.
     *  
     * @return a list of <code>TestSuite</code>.
     */
    public List<TestSuite> getSuites() {
        return suites;
    }

    /**
     * Set the list of suite test.
     * 
     * @param suites a list of  <code>TestSuite</code>.
     */
    public void setSuites(List<TestSuite> suites) {
        this.suites = suites;
    }

    /**
     * Get the test suite currently running.
     * 
     * @return a object of type  <code>TestSuite</code>.
     */
    public TestSuite getAtualSuite() {
        return atualSuite;
    }

    /**
     * Set the test suite currently running.
     * 
     * @param atualSuite a object of type  <code>TestSuite</code>.
     */
    public void setAtualSuite(TestSuite atualSuite) {
        this.atualSuite = atualSuite;
    }

    /**
     * Take the object that writes data in the xml file.
     * 
     * @return a object of type <code>Writer</code>.
     */
    public Writer getEscritor() {
        return escritor;
    }

    /**
     * Set the object that writes data in the xml file.
     * @param escritor a object of type <code>Writer</code>.
     */
    public void setEscritor(Writer escritor) {
        this.escritor = escritor;
    }

    /**
     * Get the test case currently running.
     * 
     * @return a object of type <code>TestCase</code>.
     */
    public TestCase getAtualTestCase() {
        return atualTestCase;
    }

    /**
     * Set the test case currently running.
     * 
     * @param atualTestCase a object of type <code>TestCasse</code>.
     */
    public void setAtualTestCase(TestCase atualTestCase) {
        this.atualTestCase = atualTestCase;
    }

    /**
     * Set the class code currently running.
     * 
     * @return a object of type <code>ClassCode</code>.
     */
    public ClassCode getAtualClass() {
        return atualClass;
    }

    /**
     * Set the class code currently running.
     * 
     * @param atualClass a object of type <code>ClassCode</code>.
     */
    public void setAtualClass(ClassCode atualClass) {
        this.atualClass = atualClass;
    }

    /**
     * Method which adds a <code>TestSuite</code> in the log.
     * 
     * @param packageName the package name.
     * @param name the test suite name.
     */
    private void addTestSuite(String packageName, String name) { 
        TestSuite suite = new TestSuite(packageName, name);

        if (getAtualSuite() == null) {
            setAtualSuite(suite);
        } else if (!getAtualSuite().toString().equals(suite.toString())) {
            suites.add(atualSuite);
            setAtualSuite(suite);
        }
    }

    /**
     * Method which adds a <code>TestCase</code> in the log.
     * 
     * @param packageName the package name.
     * @param suiteName the test suite name.
     * @param testCase the test case name.
     */
    public void addTestCase(String packageName, String suiteName, String testCase) {

        addTestSuite(packageName, suiteName);

        TestCase tc = new TestCase(testCase);

        if (getAtualTestCase() == null) {
            setAtualTestCase(tc);
        } else if (!getAtualTestCase().toString().equals(tc.toString())) {

            atualSuite.addTestCase(atualTestCase);

            setAtualClass(null);

            setAtualMethod(null);

            setAtualTestCase(tc);
        }

    }
    /**
     * 
     * @param packageName
     * @param suiteName
     * @param testCase
     * @param signature
     */
    public void addTestCase(String packageName, String suiteName, String testCase, String signature) {

        addTestSuite(packageName, suiteName);

        TestCase tc = new TestCase(testCase);
        tc.setSignature(signature);
        
        if (getAtualTestCase() == null) {
            setAtualTestCase(tc);
        } else if (!getAtualTestCase().toString().equals(tc.toString())) {

            atualSuite.addTestCase(atualTestCase);

            setAtualClass(null);

            setAtualMethod(null);

            setAtualTestCase(tc);
        }

    }
    

    /**
     * Method which adds a <code>ClassCode</code> in the log.
     * 
     * @param packageName the package name.
     * @param name the class code name.
     */
    private void addClassCode(String packageName, String name) {

        ClassCode classCode = new ClassCode(packageName, name);

        if (getAtualTestCase() == null || classCode.toString().equals(getAtualClass())) {
            return;
        }

        setAtualClass(classCode);

        getAtualTestCase().addClassCoverage(getAtualClass());
    }

    /**
     * Method which adds a <code>Method</code> in the log.
     * 
     * @param packageName the package name.
     * @param className	 the class name.
     * @param name	the method name.
     */
    public void addMethod(String packageName, String className, String name) {

        Method method = new Method(name);

        addClassCode(packageName, className);

        if (getAtualClass() == null || method.toString().equals(getAtualMethod())) {
            return;
        }

        setAtualMethod(method);

        getAtualClass().addMethod(getAtualMethod());
    }

    /**
     * Method which adds a Statement in the log.
     * 
     * @param number a string representing the number line.
     */
    public void addStatement(String number) {

        Statement sttm = new Statement(number);

        if (getAtualMethod() != null) {
            getAtualMethod().addStatement(sttm);
        }
    }

    /**
     * Method that performs recording data in the XML file.
     */
    public void commit() {

        getAtualSuite().addTestCase(getAtualTestCase());

        suites.add(getAtualSuite());

        escritor.write(suites);
    }
    
    /**
     * Ends the process of writing the XML file, thus completing the construction of the log file.
     */
    public void finishCommit() {
        escritor.write("</list>");
    }
}
