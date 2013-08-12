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
import java.io.IOException;

import java.util.List;
import java.util.Map;

import report.GenerateExecutionOrderReport;

import dao.XStreamWrite;



/**
 * This methdo sabe data of exucution to project folder.
 * 
 * @author Samuel T. C. Santos
 * @version 1.0
 */
public class SaveFile {
    
      /**
       * Save to XML format.
       * 
       * @param objects
       *        An Object.
       * @param local
       *        The folder destination.
       * @param filename 
       *        The file name.
       */
      public static void save(List<String> objects, String local, String filename ){
            
            File f = new File(local);
            
            if (!f.exists()){
            	f.mkdir();
            }
            
            XStreamWrite writer = new XStreamWrite(local+Settings.SEPARATOR+filename);

            writer.write(objects);
           
     }
          
     /**
      * This method save the Coverage Report.
      * 
      * @param code
      *         A String with the coverage report.
      */
     public static void saveCoverageReport(String code) {
        String nameFile = "report"+Settings.SEPARATOR+"coverageReport";
        WriteFile write = new WriteFile(nameFile, "txt");
        write.write(code);
        write.close();
     
    }
          
    /**
     * Save a java prioritized suite.
     * 
     * @param filename
     *         The suite java name.
     * @param code 
     *          The content file.
     */
    public static void saveCode(String filename , String code) {
       
        String local = Settings.USER_DIR;
        String directory = Settings.SEPARATOR+"report"+Settings.SEPARATOR+"suites"+Settings.SEPARATOR;
        
        String versionFile = local+directory+filename;
        
        WriteFile write = new WriteFile(versionFile, "java");
        
        write.write(code);
        write.close();
        
     }
    
    /**
     * This method save the prioritized excution order.
     * 
     * @param testCases
     *      A list of Strings with test cases names.
     * @param technique 
     *      Id of the prioritized Technique.
     */
    public static void  saveFileOrderExecutionTestCase(String filename, String content) {
        
        String local = Settings.ORDER + Settings.SEPARATOR;

        WriteFile write = new WriteFile(local+filename, "txt");
        
        write.write(content);
        write.close();
    }
    
    public static void save(List<String> map){
            
            String directory =  Settings.SEPARATOR + "priorjtmp" + Settings.SEPARATOR;
            String file = "impactedtests";
            
            File f = new File(Settings.USER_DIR+directory);
            
            if (!f.exists()){
               f.mkdir();
            }
            
            XStreamWrite writer = new XStreamWrite(Settings.USER_DIR+directory+file);

            writer.write(map);
    }
     
     
}
