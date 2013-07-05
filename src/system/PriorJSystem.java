package system;

import java.io.IOException;
import java.util.List;

import project.JUnitVersionEnum;
import report.CoverageReport;

import coverage.TestCase;

import exception.CannotReadLogFileException;
import exception.CoverageUnrealizedException;
import exception.InstrumentationUnrealizedException;

/**
 * This class represent the core of application, provide all steps 
 * before of prioritization process. This class instrumentation,
 * coverage of code, copy backup file, create the log file and read it. 
 * 
 * @author Samuel T. C. Santos
 * @version 1.0
 */
public interface PriorJSystem {

	/**
    * Method responsible for instrumenting the application code.
    * 
    * starts a class type InstrumentadorAPP and run instrumentation
    * code that contains the path passed by parameter.
    *   
    * @param path  
    * 			Path of the code to be instrumented.
    * 
    * @throws Exception 
    * 			Internal problem instrumentation.
    */
    public void instrumentCode(String path) throws Exception;
    
    /**
     * Method to start the process of checking the difference between
     * two codes present on the way past.
     * 
     * @param pathCodeNew
     * 			The path of the new code.
     * @param pathCodeOld  
     * 			The path of the old code.
     * @return List  
     * 			List containing all the lines that have been modified.
     * 
     * @throws Exception
     * 			Input/Output problems.
     */
    public List checkDifference(String pathCodeNew, String pathCodeOld) throws Exception;
    
    /**
     * Method auxiliary responsible for the copy of the application, to the root
     * directory of the tool this copy becomes necessary, by high modification 
     * made in the code and the desire to preserve a unique code stored.
     *
     * @param pathOrigemApp 
     * 				Path where the application is to be copied
     * @throws IOException
     * 				Access file problems in copy.
     */
     public void copyBackupFiles(String pathOrigemApp) throws IOException, Exception;
     
     /**
      * Method of Auxiliary responsible for the copies necessary files,
      * Into the application folder, so that the execution of test cases
      * With the code may occur satisfactorily.
      *
      * 
      * @throws IOException 
      * 		Access Files problems.
      */
      public void copyFiles() throws IOException;
      
      /**
       * Auxiliary method that will set within the file aspect, the required paths
       * application, for the faithful execution and capture the total route.
       * 
       * @param versionprivate void lerArquivoDeLog() throws Exception 
       * 			The JUnitVersionEnum used, JUnitVersionEnum.JUNIT3 or
       * 			JUnitVersionEnum.JUNIT4.
       */
      public void setPathAspectFile(JUnitVersionEnum version); 
      
      /**
       * Delete the aspect files and libraries used.
       */
      public void deleteAspectosFile();
      
      /**
       * Execute Ant run the JUnit test case suite.
       * 
       * @param pathApp 
       * 		The application path.
       * @param pathCode
       * 		The code path.
       * @param pathTest
       * 		The test path.
       * @param pathLib
       * 		The libraries path.
       */
      public void executaAnt(String pathApp, String pathCode, String pathTest, String pathLib);
      /**
       * This method read the log file.
       * 
       * @throws Exception
       * 		When try read file.
       */
      public void readLogFile() throws CannotReadLogFileException;
      /**
       * This method do instrumentation.
       * 
       * @throws Exception
       * 		Access files problems.
       */
      public void runInstrumentation() throws InstrumentationUnrealizedException;
      /**
       * This method do coverage.
       * 
       * @param version
       * 			The JUnitVersionEnum used.
       * 
       * @throws Exception
       * 			Access files Problems
       */
      public void runCoverage(JUnitVersionEnum version) throws CoverageUnrealizedException;
      /**
       * This method execute a read log file.
       * 
       * @throws Exception
       * 
       */
      public void runReadLog() throws CannotReadLogFileException;
      /**
       * Get the <code>CoverageReport</code> an object with
       * information about coverage.
       * 
       * @return
       * 		The Object <code>CoverageReport</code> .
       */
      public CoverageReport getCoverageReport();
      /**
       * Get the list of test cases.
       * 
       * @return
       * 		List of test cases.
       */
      public List<TestCase> getTests();
      /**
       * Get path code.
       * 
       * @return
       * 		The path code.
       */
      public String getPathCode();
      /**
       * Set the path code.
       * 
       * @param pathCode
       * 		The path code.
       */
      public void setPathCode(String pathCode);
      /**
       * Get the path test.
       * 
       * @return
       * 		The path test.
       */
      public String getPathTests();
      /**
       * Set the path test.
       * 
       * @param pathTests
       * 		The path test.
       */
      public void setPathTests(String pathTests);
      /**
       * Get the new path code.
       *  
       * @return
       * 		The new path.
       */
      public String getPathCodeNew();
      /**
       * Set the path code New
       * 
       * @param pathCodeOld
       * 		The path of an application updated.
       */
      public void setPathCodeNew(String pathCodeNew);
     
      /**
       * Get the application path.
       * 
       * @return
       * 		The path of the application.
       */
      public String getPathApp();
      /**
       * Set the application path.
       * 
       * @param pathApp
       * 		This path of the application.
       */
      public void setPathApp(String pathApp);
      /**
       * Get the total path code.
       * 
       * @return
       * 		The path application + / + the path code.
       */
      public String getTotalPathCode();

      /**
       * Get the total path of the tests.
       * 
       * @return
       * 		The path application path + / + the path tests.
       */
      public String getTotalPathTests();

      
      /**
       * Get path libraries.
       * 
       * @return
       * 		The path libraries.
       */
      public String getPathLib();
      
       /**
       * Set the path libraries.
       *  
       * @param pathLib 
       * 	A <code>String</code> containing the path of the libraries.
       */
      public void setPathLib(String pathLib) ;
      
      /**
       * This method say if the application code was instrumented.
       * 
       * @return
       * 		True or False.
       */
      public boolean isInstrumented();
      
      /**
       * This method say if the coverage was completed.
       * 
       * @return
       * 		True or False.
       */
      public boolean isCovered();
       
      /**
       * This method say if the log file was read.
       * 
       * @return
       * 		True or False.
       */
      public boolean isLog();
      
}
