package report;

import dao.XStreamRead;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.net.ssl.ManagerFactoryParameters;
import javax.swing.JOptionPane;

import util.ManagerFiles;
import util.WriteFile;


/**
 * This class is a factory to TestSuite.
 * 
 * @author Samuel T. C. Santos
 * @version 1.0
 * 
 */
public class SuiteFactory {
    
    private String suiteName="";
    private String packageName ="";
    private String path = "";
    private int size;
   
    /**
     * Generate a SuiteFactory
     * @param suiteName
     * 		the suite name
     * @param packageName
     * 		the package name.
     * @param size
     * 		the selection size.
     */
    public SuiteFactory(String suiteName, String packageName ,int size){
        this.suiteName = suiteName;
        this.packageName = packageName;
        this.size = size;
    }
    
    /**
     * Constructor
     * 
     * @param suiteName
     * 		The suite name.
     * @param packageName
     * 		The package name.
     * @param path
     * 		The location to save.
     * @param size
     * 		the selection size.
     */
    public SuiteFactory(String suiteName, String packageName, String path ,int size){
        this(suiteName, packageName, size);
        this.path = path;
    }
   /**
    * Default constructor.
    */
    public SuiteFactory(){
    	
    }
    
    private void saveSelectionRBA() throws Exception{
        String filename = suiteName+"RBASelection";
        List<String> tests = getTestsSelectionRBA();
        
        String content = generateSuite(tests);
        
        saveCode( filename, content, path);
    }
    
    private List<String> getTestsSelectionRBA(){
        
        String local = System.getProperty("user.dir");
        String dir = "/priorjtmp/";
        String file = "mapweight";

        try {
            XStreamRead reader = new XStreamRead(local+ dir + file);
            List<String> methods = (List<String>)reader.read();
    
            return methods;
        } catch (Exception ex) {
           ex.printStackTrace();
           return null;
        }
    
    }
  
    
    /**
     * This method generate a list of suite code.
     * 
     * @param tests
     *      An list of prioritized tests order 
     * @param names
     *      A list of suites names.
     * @return
     *      A list of prioritized suite code.
     * @throws Exception 
     */
    public List<String> generateSuite(List<List<String>> tests, List<String> names) throws Exception{
       
        List<String> suites = new LinkedList<String>();
        

        for (int i=0; i < names.size(); i++){
            String clazz = suiteName+names.get(i);
            String suite = GenerateTestSuite.generate(packageName, clazz, tests.get(i), size);
          
            suites.add(suite);
        }
        
        return suites;
    }
    
    public String generateSuite(List<String> tests) throws Exception{

        String clazz = suiteName+"RBASelection";
        String suite = GenerateTestSuite.generate(packageName, clazz, tests, 100);

        return suite;
    }
   
    
    public void saveCode(String filename, String content, String path) {
       
        if (content.equals("")) {
            return;
        }
        
        WriteFile write = new WriteFile(path+filename, "java");
        
        write.write(content);
        write.close();
    }

    /**
     * This method save a list of test suite code.
     * 
     * @param path
     *      The place to save.
     * @param suites
     *      The suite code to save.
     * @param filenames 
     *      The file names.
     */
    public void save(String path, List<String> suites, List<String> filenames){
        
        try {
        	if (!ManagerFiles.existsDirectory(path))
        		ManagerFiles.createDirectory(path);
        	
            for(int i=0; i < filenames.size(); i++){
                saveCode(suiteName+filenames.get(i).replace(".java",""), suites.get(i) ,path);
            }
            
            JOptionPane.showMessageDialog(null, "Save Successfully!"," Save", JOptionPane.INFORMATION_MESSAGE);

        } catch (Exception ex) {
            System.err.append(ex.getMessage());
        }
       
    }
    
    /**
     * Set the suite name.
     * 
     * @param name
     * 	The suite name.
     */
    public void setSuiteName(String name){
    	this.suiteName = name;
    }
}
