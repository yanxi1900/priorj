package util;

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
/**
 * 
 * This class recovery informations about the JUnit execution.
 *  
 * @author Samuel T. C.  Santos
 * @version 1.0
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
