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
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import coverage.TestSuite;



/**
 * Tests read data from xml file. //TESTS-TestSuites
 * @author Samuel Santos
 */
public class ReadXML {
    
    /**
        * Read xml file and return all tests suites.
        * @return - a list of test suite.
        * @throws Exception - err open file.
        */
    public static List<TestSuite> getAllTestSuites() throws Exception{
        //read log file...
        String local = System.getProperty("user.dir");
        String pathReport = Settings.SEPARATOR + "report"+  Settings.SEPARATOR +"coveragePriorJ";
        Reader readFileReport = new Reader(local + pathReport);
        //get list of test suites
        List<TestSuite> suites = (List<TestSuite>) readFileReport.read();

        return suites;
    }

    /**
     * Get the list of test results from junit execution.
     */
    public static List<TestResult> getTestResults(String xmlFile){
        List<TestResult> testResults = new ArrayList<TestResult>();
        TestResult testResult; // a object with test result.

        String local = System.getProperty("user.dir").replace("\\", Settings.SEPARATOR);
        
        String directory = Settings.SEPARATOR +"report"+Settings.SEPARATOR;

        xmlFile = local + directory + xmlFile + ".xml";

        try {
            File file = new File(xmlFile);

            if (file.exists()) {
                // Create a factory
                DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

                // Use the factory to create a builder
                DocumentBuilder builder = factory.newDocumentBuilder();
                Document doc = builder.parse(xmlFile);
                // Get a list of all elements in the document
                NodeList list = doc.getElementsByTagName("*");

                for (int i = 0; i < list.getLength(); i++) {
                    // Get element
                    Element element = (Element) list.item(i);

                    if(element.getNodeName().equals("testcase")){

                        boolean pass = true;

                        if (element.hasChildNodes()){
                            pass = false;
                        }
                        String suite = (String) element.getAttribute("classname");
                        String testcase = (String) element.getAttribute("name");
                        double time = Double.parseDouble((String)element.getAttribute("time"));

                        //create a test result
                        testResult = createTestResult(suite, testcase, time, pass);

                        //add na list
                        testResults.add(testResult);
                    }
                }

                return testResults;

            } else {
                System.out.print("File not found!");
                return null;
            }
        } catch (Exception e) {
            System.exit(1);
            return null;
        }

    }
    
    public static List<TestResult> getTestResults(){
       return getTestResults("TESTS-TestSuites");
    }
    
    /**
     * Verificar se o arquivo existe e tem conteudo.
     * @param filename - nome do arquivo.
     * @return  - true ou false
     */
    public static boolean exists(String filename){
        File f = new File(filename+".xml");
        return f.length() > 0;
    }
    
    
    /**
     * Create a test result.
     * @param nameSuite - name suite
     * @param testName - name test case
     * @param time - execution time
     * @param passed - true=passed ou false= no passed
     */
    private static TestResult createTestResult(String nameSuite, String testName, double time, boolean passed){

        return new TestResult(nameSuite, testName, time, passed);
    }

}
