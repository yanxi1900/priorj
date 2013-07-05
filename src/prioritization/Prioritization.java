package prioritization;


import java.util.ArrayList;
import java.util.List;

import technique.Technique;
import technique.TechniqueAdditionalMethod;
import technique.TechniqueAdditionalStatement;
import technique.TechniqueEchelonAdditional;
import technique.TechniqueEchelonTotal;
import technique.TechniqueRandom;
import technique.TechniqueRefactoringBasedApproach;
import technique.TechniqueTotalMethod;
import technique.TechniqueTotalStatement;
import util.Settings;

import core.DifferenceApp;
import core.InstrumentApp;
import coverage.TestCase;
import dao.XStreamRead;

import exception.EmptySetOfTestCaseException;

/**
 * Class which performing the prioritization process, choosing the 
 * selected technique and generating the prioritized list of test cases.
 * 
 * @author Samuel T. C. Santos
 * @version 1.0
 */
public class Prioritization {

    private List<TestCase> tests;
    private List<String> suiteList;
    private List blockAffected;
    private String pathCodeOld=""; 
    private List<String> impacted;
    private String pathCodeNew=""; //path of the new version
    private int techniqueId;
    Technique technique = null;
    
    public Prioritization(List<TestCase> tests, int technique , String pathCodeNew, String totalPathCode){
        suiteList = new ArrayList<String>();
        this.tests = tests;
        
        this.pathCodeOld = totalPathCode;
        this.pathCodeNew = pathCodeNew;
        
        this.techniqueId = technique;
    }
    
    /**
     * This method do a prioritization in the set of test cases.
     * 
     * @throws EmptySetOfTestCaseException
     * 			When the set of test cases is empty.
     */
    public void prioritize() throws EmptySetOfTestCaseException{
        
        switch(techniqueId){
            case 1: //TSC
                technique = new TechniqueTotalStatement(tests);
                suiteList = technique.assingWeight();
                
                break;
            case 2: //TMC
                technique = new TechniqueTotalMethod(tests);
                suiteList = technique.assingWeight();
                
                break;
                
            case 3: //ASC
                technique = new TechniqueAdditionalStatement(tests);
                suiteList = technique.assingWeight();
                
                break;
                
            case 4: //AMC
                technique = new TechniqueAdditionalMethod(tests);
                suiteList = technique.assingWeight();
                
                break;
                
            case 5: //ET or Changed Blocks
                instrumentaCodigo(pathCodeNew);
                if (blockAffected == null) {
                    try {
                        
                        System.out.println("path code old" + pathCodeOld);
                        System.out.println("path code new" + pathCodeNew);
                        
                        blockAffected = checkDifference(pathCodeNew,pathCodeOld);
                        

                        technique = new TechniqueEchelonTotal(blockAffected, tests);
                        suiteList = technique.assingWeight();

                    } catch (Exception ex) {
                        System.out.println("Changed Block: " + ex.getMessage());
                    }
                }
                
                break;
                
            case 6: //Random
                technique = new TechniqueRandom(tests);
                suiteList = technique.assingWeight();
                
                break;
                
            case 7: //EA
                instrumentaCodigo(pathCodeNew);
                if (blockAffected == null) {
                    try {
                        blockAffected = checkDifference(pathCodeOld, pathCodeNew);
                        
                        technique = new TechniqueEchelonAdditional(blockAffected,tests);
                        suiteList = technique.assingWeight();

                    } catch (Exception ex) {
                        System.out.println(ex.getMessage());
                    }
                }
                break;
                
            case 8: //RBA

                technique = new TechniqueRefactoringBasedApproach(impacted, tests);               
                suiteList = technique.assingWeight();
                break;
                
            default:
                break;
                
        }
        
    }
    
    /**
     * 
     * @param impacted
     */
    public void setImpacted (List<String> impacted){
        this.impacted = impacted;
    }
    
    public List<String> getBlockAffected(){
        return impacted;
    }
    
    /**
     * This method does prioritization of the suite test.
     * 
     * @param testList
     * 		A list of test case.
     * @param technique
     * 		A prioritization technique 
     * @return 
     * 		A list of prioritized test cases.
     * 
     * @throws EmptySetOfTestCaseException
     * 		When the set of test case is empty. 
     */
    public List<String> assignWeight() throws EmptySetOfTestCaseException{
    	
        return technique.assingWeight();        
    }
        
    /**
     * Method to start the process of checking the difference between
     * two codes present on the way past.
     * 
     * @param pathCodeNew  the path of the new code.
     * @param pathCodeOld  the path of the old code.
     * @return List  a list containing all the lines that have been modified.
     * 
     * @throws Exception - checking problems.
     */
    private List checkDifference(String pathCodeNew, String pathCodeOld) throws Exception  {
        DifferenceApp diff = new DifferenceApp(pathCodeNew, pathCodeOld);
        diff.run();
        
        List<String> diferencas = diff.getListDiff();
                
        diff = new DifferenceApp(pathCodeOld, pathCodeNew);
        diff.run();
        
        List<String> newDifferences = diff.getListDiff();
        
        for (String d : newDifferences){
            if (!diferencas.contains(d)) diferencas.add(d);
        }
       
        return diferencas;

    }

      /**
     * Method responsible for instrumenting the application code.
     * He starts a class type InstrumentadorAPP and wheel instrumentation
     *  code that contains the path parameter passed.
     *   
     * @param path  path of the code to be instrumented.
     * @throws Exception internal problem instrumentation.
     */
    private void instrumentaCodigo(String path)  {
        InstrumentApp inst = new InstrumentApp(path);
        try {
            inst.run();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    
    
//    public List<String> selectionTestRBA(){
//        List<String> testNames = new ArrayList<String>();
//        
//        for (TestCase test : suiteList)
//            testNames.add(test.getSignature());
//            
//        return testNames;
//
//    }
    
    /**
     * This method is used by Refactoring Based Approach to prioritization test suite.
     * 
     * @return
     * 	
     */
    public List<String> getRBAMethods(){
     
        String local = Settings.USER_DIR;
        String dir = Settings.SEPARATOR+"priorjtmp"+Settings.SEPARATOR;
        String file = "methods";

        try {  
            XStreamRead reader = new XStreamRead(local+ dir + file);
            List<String> methods =  (List<String>)reader.read();
            
            return methods;
        } catch (Exception ex) {
           ex.printStackTrace();
           return null;
        } 

    }

    public void setTotalPathCode(String totalPathCode) {
        this.pathCodeOld = totalPathCode;
    }
    
    public void setPathCodeNew(String pathCodeOld) {
        this.pathCodeNew = pathCodeOld;
    }
             
}
