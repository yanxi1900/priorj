package prioritization;

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

import static org.junit.Assert.*;

import java.util.List;

import main.PriorJFacade;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import util.Settings;

/**
 * This class is a test to RBA methods.
 *  
 * @author Samuel T. C. Santos
 * @version 1.0
 *
 */
public class PrioritizationRBATest {
	
	private PriorJFacade facade;
	private String pathApp="";
	private String className="";
	private String methodName="";
	private String newMethodName="";
	private int beginLine;
	private int endLine;
	private String originMethodName="";
	private String classOneName="";
	private String classTwoName="";
	private String fieldName = "";
	
	
	
	@Before
	public void setUp(){
		facade = new PriorJFacade();
		facade.setPathApp(Settings.APP);
		facade.setPathCode(Settings.APP_CODE);
		facade.setPathLib(Settings.APP_LIB);
		facade.setPathTest(Settings.APP_TEST);
		
		facade.setPathCodeNew(Settings.APP_CODE_NEW);
		
		facade.setJUnitVersion("junit4");
		
		facade.addTechnique("rba");
	}
	
	@After
	public void setDown(){
		facade = null;
	}
	
	@Test
	public void testRunRenameMethod() {
		try {
			facade.runInstrumentation();
			facade.runCoverage();
			facade.runReadLog();
						
			pathApp = Settings.APP_CODE_NEW.replace(Settings.SEPARATOR + "src", "");
			
			className="calc.Calculator";
			methodName="div";
			newMethodName="division";
			
						
			List<String> result = facade.runRBARenameMethod(pathApp, className, methodName, newMethodName);
		
			assertTrue(!result.isEmpty());
			
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}
	
	@Test
	public void testRunExtractMethod() {
		try {
			facade.runInstrumentation();
			facade.runCoverage();
			facade.runReadLog();
						
			pathApp = Settings.APP_CODE_NEW.replace(Settings.SEPARATOR + "src", "");
					
			className="controller.Agenda";
			
			originMethodName = "getTelefonesDoContatoAtivo()";
			
			newMethodName="getCatalogoContatos";
			
			beginLine = 115;
			endLine = 115;
			
			List<String> result = facade.runRBAExtractMethod(pathApp, originMethodName, className, newMethodName, beginLine, endLine);
			
			assertTrue(!result.isEmpty());
			System.out.println(result);
			
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}
	
	@Test
	public void testRunMoveMethod() {
		try {
			facade.runInstrumentation();
			facade.runCoverage();
			facade.runReadLog();
						
			pathApp = Settings.APP_CODE_NEW.replace(Settings.SEPARATOR + "src", "");
		
			classOneName="model.Person";
			classTwoName="model.Employee";
			methodName = "toString()";
			
			List<String> result = facade.runRBAMoveMethod(pathApp, classOneName, classTwoName, methodName);
			
			assertTrue(!result.isEmpty());
			System.out.println(result);
			
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}
	
	@Test
	public void testRunPullUpMethod() {
		try {
			facade.runInstrumentation();
			facade.runCoverage();
			facade.runReadLog();
						
			pathApp = Settings.APP_CODE_NEW.replace(Settings.SEPARATOR + "src", "");
		
			classOneName = "model.Person";
			classTwoName = "model.Contact";
			methodName = "calculateAge";
					
			List<String> result = facade.runRBAPullUpMethod(pathApp, classOneName, classTwoName, methodName);
			
			assertTrue(!result.isEmpty());
			
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}
	
	@Test
	public void testRunPullUpField() {
		try {
			facade.runInstrumentation();
			facade.runCoverage();
			facade.runReadLog();
						
			pathApp = Settings.APP_CODE_NEW.replace(Settings.SEPARATOR + "src", "");
		
			classOneName = "model.Person";
			classTwoName = "model.Contact";
			fieldName = "birthyear";
					
			List<String> result = facade.runRBAPullUpField(pathApp, classOneName, classTwoName, fieldName);
			
			assertTrue(!result.isEmpty());
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}
	
	@Test
	public void testRunAddParameter() {
		try {
			facade.runInstrumentation();
			facade.runCoverage();
			facade.runReadLog();
						
			pathApp = Settings.APP_CODE_NEW.replace(Settings.SEPARATOR + "src", "");
		
			className = "model.Person";
			methodName = "setName(String name, String sobrenome)";
					
			List<String> result = facade.runRBAAddParameter(pathApp, className, methodName);
			
			assertTrue(!result.isEmpty());
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}
}
