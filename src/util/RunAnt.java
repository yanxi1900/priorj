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

import org.apache.tools.ant.DefaultLogger;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.ProjectHelper;

/**
 * <code>RunAnt</code> class, initialization of Ant to Build application.
 * 
 * @author Samuel T. C. Santos
 * @author Julio Henrique
 * @version 1.0
 *
 */
public class RunAnt {
	private String fileName = "";
	private String pathApp = "";
	private String pathCode = "";
	private String pathTest ="";
	private String pathLib = "";
	
	/**
	 * Default Constructor.
	 * 
	 */
	public RunAnt(){
		//default
	}
	/**
	 * Constructor RunAnt.
	 * 
	 * @param pathAppe
	 * 		The application path 
	 * @param pathCode
	 * 		The code path
	 * @param pathTest
	 * 		The path Test
	 * @param name
	 * 		The build file name
	 * @param pathLib
	 * 		The libraries name
	 */
	public RunAnt(String pathApp, String pathCode, String pathTest, String name, String pathLib){
		this.pathApp = pathApp;
		this.pathCode = pathCode;
		this.pathTest = pathTest;
		this.fileName = name;
		this.pathLib = pathLib;
	}
	/**
	 * This path run the ant.
	 * 
	 * @param pathApp
	 * 		the application path.
	 * @param pathCode
	 * 		the code path
	 * @param pathTest
	 * 		the path test
	 * @param pathLib
	 * 		the path libraries
	 */
	public RunAnt(String pathApp, String pathCode, String pathTest, String pathLib){
		this(pathApp, pathCode, pathTest,"buildPriorJ", pathLib);
	}
	
	
	/**
	 * Run the process build.
	 */
	public void run(){
		
		File buildFile = new File(this.fileName + ".xml");
		
		Project p = new Project();
		//LOg
		DefaultLogger consoleLogger = new DefaultLogger();
		consoleLogger.setErrorPrintStream(System.err);
		consoleLogger.setOutputPrintStream(System.out);
		consoleLogger.setMessageOutputLevel(Project.MSG_INFO);
		p.addBuildListener(consoleLogger);
		p.setProperty("pathApp", getPathApp());
		p.setProperty("pathCode", getPathCode());
		p.setProperty("pathTest", getPathTest());
		p.setProperty("pathLib", getPathLib());
		p.setUserProperty("ant.file", buildFile.getAbsolutePath());
		p.init();
		ProjectHelper helper = ProjectHelper.getProjectHelper();
		p.addReference("ant.projectHelper", helper);
		helper.parse(p, buildFile);
		p.executeTarget(p.getDefaultTarget());
	}
	
	public String getPathLib() {
		return pathLib;
	}

	public void setPathLib(String pathLib) {
		this.pathLib = pathLib;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getPathApp() {
		return pathApp;
	}

	public void setPathApp(String pathApp) {
		this.pathApp = pathApp;
	}

	public String getPathCode() {
		return pathCode;
	}

	public void setPathCode(String pathCode) {
		this.pathCode = pathCode;
	}

	public String getPathTest() {
		return pathTest;
	}

	public void setPathTest(String pathTest) {
		this.pathTest = pathTest;
	}

}
