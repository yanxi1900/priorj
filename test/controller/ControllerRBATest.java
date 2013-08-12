package controller;

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

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import util.Settings;
/**
 * <p>
 * 	This class is a test to RBA controller.
 * </p>
 * @author Samuel T. C. Santos
 * @version 1.0
 *
 */
public class ControllerRBATest {

	private RBAController controller;
	
	@Before
	public void setUp(){
		controller = new RBAController();
	}
	
	@After
	public void tearDown(){
		controller = null;
	}
		
	@Test //ok
	public void testControllerRunRenameMethod() {
		String pathApp = Settings.APP_CODE;
		String className = "calc.Calculator";
		String methodName = "div";
		String newMethodName= "division";
		
		controller.setPathApp(pathApp);
		controller.setClassName(className);
		controller.setMethodName(methodName);
		controller.setNewMethodName(newMethodName);
		
		assertEquals(pathApp, controller.getPathApp());
		assertEquals(className, controller.getClassName());
		assertEquals(methodName, controller.getMethodName());
		assertEquals(newMethodName, controller.getNewMethodName());
		
		controller.runRenameMethod();

		List<String> methods = controller.getMethods();
		
		for (String s  : methods){
			System.out.println(s);
		}
		
		assertFalse(methods.isEmpty());
	}

	@Test //no
	public void testControllerRunExtractMethod() {
		String pathApp = Settings.APP_CODE_NEW;
		
		String className="controller.Agenda";
		String originMethodName = "getTelefonesDoContatoAtivo()";
		String newMethodName="getCatalogoContatos";
		
		int beginLine = 115;
		int endLine = 115;
		
		controller.setPathApp(pathApp);
		controller.setClassName(className);
		controller.setMethodName(originMethodName);
		controller.setNewMethodName(newMethodName);
		
		controller.setBeginLine(beginLine);
		controller.setEndLine(endLine);
		
		controller.runExtractMethod();
		
		assertEquals(pathApp , controller.getPathApp());
		assertEquals(className, controller.getClassName());
		assertEquals(originMethodName, controller.getOriginMethodName());
		assertEquals(newMethodName, controller.getNewMethodName());
		
		assertTrue(controller.getBeginLine() == beginLine);
		assertTrue( controller.getEndLine() == endLine);
		
		
		List<String> methods = controller.getMethods();
		
		for (String s  : methods){
			System.out.println(s);
		}
			
		assertFalse(methods.isEmpty());
	}
	
	@Test //ok
	public void testControllerPullUpMethod(){
		String pathApp = Settings.APP_CODE_NEW;
		
		String classOneName = "model.Person";
		String classTwoName = "model.Contact";
		String methodName = "calculateAge";
				
		controller.setPathApp(pathApp);
		controller.setClassOneName(classOneName);
		controller.setClassTwoName(classTwoName);
		controller.setMethodName(methodName);
		
		controller.runPullUpMethod();
		
		List<String> methods = controller.getMethods();
		
		for (String s  : methods){
			System.out.println(s);
		}
			
		assertFalse(methods.isEmpty());
	}

	@Test //ok
	public void testControllerPullUpField(){
		String pathApp = Settings.APP_CODE_NEW;

		String classOneName = "model.Person";
		String classTwoName = "model.Contact";
		String fieldName = "birthyear";
				
		controller.setPathApp(pathApp);
		controller.setClassOneName(classOneName);
		controller.setClassTwoName(classTwoName);
		controller.setFieldName(fieldName);
		
		controller.runPullUpField();
		
		List<String> methods = controller.getMethods();
		
		for (String s  : methods){
			System.out.println(s);
		}
			
		assertFalse(methods.isEmpty());
	}

	
	@Test //ok
	public void testControllerRunMoveMethod(){
		String pathApp = Settings.APP_CODE;
		
		String classOneName="model.Person";
		String classTwoName="model.Employee";
		String methodName = "toString()";
		
		controller.setPathApp(pathApp);
		controller.setClassOneName(classOneName);
		controller.setClassTwoName(classTwoName);
		controller.setMethodName(methodName);
		
		controller.runMoveMethod();
		
		List<String> methods = controller.getMethods();
		
		for (String s  : methods){
			System.out.println(s);
		}
			
		assertFalse(methods.isEmpty());
	}
	
	
	@Test
	public void testControllerRunAddParameter(){
		String pathApp = Settings.APP_CODE_NEW;
		
		String className = "model.Person";
		String methodName = "setName(String name, String sobrenome)";
			
		controller.setPathApp(pathApp);
		controller.setClassName(className);
		controller.setMethodName(methodName);
		
		assertEquals(className, controller.getClassName());
		
		assertEquals(methodName , controller.getMethodName());
		
		controller.runAddParameter();
		
		List<String> methods = controller.getMethods();
		
		for (String s  : methods){
			System.out.println(s);
		}
			
		assertFalse(methods.isEmpty());
	}
	
	@Test
	public void testControllerRBAHandler(){
		List<String> tests = new ArrayList<String>();
	
		tests.add("model.Person*toString([])");
		
		tests = controller.handlerMethod(tests);
		
		assertEquals("[model.Person.toString()]",tests.toString());
	}
}
