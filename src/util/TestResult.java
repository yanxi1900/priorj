package util;

/**
 * Essa classe representa as informações
 * sibre o resultado de execução de um Test.
 *
 * @author Samuel Santos
 */
public class TestResult {

    private String nameSuite;
    private String testName;
    private double timeExecution;
    private boolean passed;

    public TestResult(String nameSuite, String testName, double time, boolean passed){
            this.nameSuite = nameSuite;
            this.testName = testName;
            this.timeExecution = time;
            this.passed = passed;
    }

    public String getNameSuite() {
        return nameSuite;
    }

    public void setNameSuite(String nameSuite) {
        this.nameSuite = nameSuite;
    }


    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    public double getTimeExecution() {
        return timeExecution;
    }

    public void setTimeExecution(double timeExecution) {
        this.timeExecution = timeExecution;
    }

    public boolean isPassed(){
        return passed;
    }
    /**
     * Dados de resultado do teste.
     * @return
     */
    public String toString(){
        return  nameSuite + " " + testName + " " + timeExecution;
    }
}
