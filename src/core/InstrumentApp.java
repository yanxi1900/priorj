package core;

/*
* PriorJ: JUnit Test Case Prioritization.
* 
* Copyright (C) 2012-2013  Julio Henrique Rocha
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
import java.io.FilenameFilter;

/**
 * <p>
 * 		This class instrument an entire application.
 * </p>
 * @author Samuel T. C. Santos
 * 		   Julio H. Rocha
 * @version 1.0
 */
public class InstrumentApp {

	/**
	 * path to file
	 */
    private File dir;

    private String pathTest;
    
    /**
     * Constructor.
     * @param path
     */
    public InstrumentApp(String path, String pathTest) {
        this.dir = new File(path);
        this.pathTest = pathTest;
    }

    /**
     * <p>
     * 		This method run the process.
     * </p>
     * @throws Exception
     */
    public void run() throws Exception {
        
        instrumentClassOfPath(dir);
    }

    /**
     * <p>
     * 		This method instrument all files in the path passed.
     * </p>
     * @param path
     * @throws Exception
     */
    private void instrumentClassOfPath(File path) throws Exception {       
//        FilenameFilter filter = new FilenameFilter() {
//            public boolean accept(File dir, String name) {
//                return !name.contains("Exception")
//                && !name.contains("Test")
//                && !name.contains("PriorJ");
//            }
//        };
        
    	File[] children = path.listFiles();
        
        if(children != null){
        	for (File arq : children) {
        		if (arq.isDirectory() && !arq.getPath().equals(pathTest)) {
        			instrumentClassOfPath(arq);
        		} else if (arq.getName().endsWith(".java")) {
        			InstrumentClass instrumenta = new InstrumentClass(path.toString() + "/", arq.getName());
        			instrumenta.instrumentationRun();
        		}
        	}
        }
    }

}
