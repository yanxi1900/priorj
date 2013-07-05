package report;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import technique.TechniquesEnum;
import util.ReadXML;
import util.TestResult;

import coverage.TestCase;
import coverage.TestSuite;



/**
 *
 * @author Samuel Santos
 */
public class ExploreJUnitReport {

    private List<TestResult> testResults;
    private List<String> testCases;
    private List<TestSuite> suites;
    private ExecutionOrder order;

    /**
     * 
     */
    public ExploreJUnitReport() {
        init();
    }
    
    /**
     * 
     * @param technique
     * @param order
     */
    public ExploreJUnitReport(TechniquesEnum technique, ExecutionOrder order) {
        this.order = order;
        testCases = order.getTestsOrder() ;
        init();
    }

    private void init() {

        try {
            testResults = getJUnitResultExecution();
            suites = getAllTestSuites();
           
        } catch (Exception ex) {
            Logger.getLogger(ExploreJUnitReport.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

   

    /**
     * Retorna a posicao de um caso de testa que falhou na ordem de execucao.
     *
     * @return - um int representando uma posicao.
     */
    public int getPositionTest(String testName) {
        return order.getPositionTestCase(testName);
    }

    /**
     * Obter a lista com apenas os nomes dos métodos que passaram.
     *
     * @return - uma lista de nomes de metodos.
     */
    public List<String> getPassTestsNames() {
        List<String> names = new ArrayList<String>();

        for (TestResult tr : testResults) {
            if (tr.isPassed()){
                names.add(tr.getNameSuite()+"."+tr.getTestName());
            }
        }
        return names;
    }
    
    /**
     * Get all tests executed by junit execution.
     * 
     * @return 
     */
    public List<String> getAllTests() {
        List<String> names = new ArrayList<String>();

        for (TestResult tr : testResults) {
                names.add(tr.getNameSuite()+"."+tr.getTestName());
        }
        return names;
    }

    /**
     * Enviar a lista de Casos de testes.
     *
     * @param testCases
     */
    public void setTestSuite(List<TestSuite> suites) {
        //pegar a suite
        this.suites = suites;

    }

    public int getTotalTestNumber() throws Exception {
        return getTestsName().size();
    }

    /**
     * Envia para esse objeto a ordem de execucao dos casos de testes.
     *
     * @param order - objeto com a ordem de execucao.
     */
    public void setOrderExecution(ExecutionOrder order) {
        this.order = order;
    }

    public int getPositionTestCaseLess(String[] methods) {
        return order.getPositionTestCaseLess(methods);
    }

    public int getPositionTestCaseLess(Object[] methods) {
        return order.getPositionTestCaseLess(methods);
    }

    public int getFailedTestWithPositionLess() throws Exception{
        
        int less = getPositionTest( getFailedTests().get(0) );
        
        for (String test : getFailedTests() ){    
            int position = getPositionTest(test); 
            if (position < less){
                less = position;
            }
        }
        
        return less;
    }
    
    
    /**
     * 
     * @return
     * @throws Exception
     */
    public List<String> getFailedTests() throws Exception {
        //lista de metodos que falharam
        List<String> testsfailed = new ArrayList<String>();

        for (TestResult tr : testResults) {
            if (!tr.isPassed()) {
                //obtenha o nome completo para o teste q failhou...
                for (String testName : getTestsName()) {
                    testName = testName.replace(".java", "");
                    String name = tr.getNameSuite() + "." + tr.getTestName();

                    if (name.equals(testName)) {
                        testsfailed.add(testName);
                    }
                }
            }
        }

        //metodos que falharam...
        return testsfailed;
    }

    /**
     * Obter o nome de todos os casos de testes.
     *
     * @return - uma lista com o nome de todos os testes.
     */
    public List<String> getTestsName() throws Exception {
        return testCases;
    }

    private void addAllTestCases() throws Exception {

        if (this.testCases.size() != 0) {
            return;
        }
        
        List<TestCase> testcases = getCaptureAllTestCasePassedByJUnitExecution();
        for (TestCase test : testcases) {
            testCases.add(test.getName());
        }

    }

    //modifications today...
    private List<TestCase> getCaptureAllTestCasePassedByJUnitExecution() throws Exception {
        List<TestSuite> suites = getAllTestSuites();
        List<TestCase> testsExecuted = new ArrayList<TestCase>();

        for (TestSuite suite : suites) {
            String suiteName = suite.getPackageName() + "." + suite.getName().replace(".java", "");
            for (TestCase test : suite.getTestCases()) {
                if (containsTestCase(test.getName())) {
                    test.setName(suiteName + "." + test.getName());
                    testsExecuted.add(test);
                }
            }
        }

        return testsExecuted;
    }

    private boolean containsTestCase(String tcName) {
        for (TestResult tr : testResults) {
            if (tr.getTestName().equals(tcName)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Containt test case.
     *
     * @param tests - a list with names of test cases.
     * @param test - a test case.
     * @return - true or false.
     */
    private boolean containsTestCase(List<String> tests, TestCase test) {
        for (String tst : tests) {
            if (tst.equals(test.getName())) {
                return true;
            }
        }

        return false;
    }

    /**
     * Get all test suites.
     *
     * @return - a list of test suite.
     * @throws Exception - err read file.
     */
    private List<TestSuite> getAllTestSuites() {

        List<TestSuite> suites;
        try {
            suites = ReadXML.getAllTestSuites();
            return suites;
        } catch (Exception ex) {
            Logger.getLogger(ExploreJUnitReport.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }

    }

    /*
     * Get data from junit execution.
     */
    public List<TestResult> getJUnitResultExecution() {
        return ReadXML.getTestResults("TESTS-TestSuites");
    }
}
