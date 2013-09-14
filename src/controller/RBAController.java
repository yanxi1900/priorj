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

/**
 * This class is a controller to RBA application.
 * 
 * @author Samuel T. C.Santos
 * @version 1.0
 */

import japa.parser.ast.CompilationUnit;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import main.FacadeRBA;

import technique.RefactoringEnum;
import util.PathTo;

import dao.XStreamWrite;
import exceptions.DBException;

/**
 * <p>
 * 		This class is a controller to RBA API.
 * </p>
 * @author Samuel T. C. Santos
 * @version 1.0
 *
 */
public class RBAController {

	private ArrayList<CompilationUnit> compilation;
	private List<String> methodNames;
	private String pathApp = "";
	private String className = "";
	private String methodName = "";
	private String classOneName = "";
	private String classTwoName = "";
	private String originMethodName = "";
	private String newMethodName = "";
	private FacadeRBA facade;
	private int beginLine;
	private int endLine;
	private String fieldName = "";
	private List<String> signatures;

	/**
	 * <p>
	 * Constructor.
	 * </p>
	 */
	public RBAController() {
		facade = new FacadeRBA();
		signatures = new ArrayList<String>();
		methodNames = new ArrayList<String>();
	}

	/**
	 * <p>
	 * 	This method run the process.
	 * </p>
	 * @param refactoring
	 * 		Refactor type.
	 */
	public void run(RefactoringEnum refactoring) {

		switch (refactoring.getId()) {

		case 1:
			runRenameMethod();
			break;
		case 2:
			runExtractMethod();
			break;
		case 3:
			runMoveMethod();
			break;
		case 4:
			runPullUpMethod();
			break;
		case 5:
			runPullUpField();
			break;
		case 6:
			runAddParameter();
			break;
		default:
			System.out.println("refactoring id invalid!");
			break;
		}
	}

	/**
	 * <p>
	 * 		This method run the refactoring to rename method.
	 * </p>
	 */
	public void runRenameMethod() {
		try {
			compilation = facade.parse(pathApp);
			methodNames = handlerMethod(facade.getImpactedElementsRenameMethod(	compilation, className, methodName, newMethodName));
		} catch (DBException e) {
			e.printStackTrace();
		}
	}

	/**
	 * <p>
	 * 		Run the extract method.
	 * </p>
	 */
	public void runExtractMethod() {
		try {
			compilation = facade.parse(pathApp);
			methodNames = handlerMethod(facade.getImpactedElementsExtractMethod(compilation, className,originMethodName, newMethodName, beginLine, endLine));
		} catch (DBException e) {
			e.printStackTrace();
		}
	}

	/**
	 * <p>
	 * 		This method run the move method.
	 * </p>
	 */
	public void runMoveMethod() {
		try {
			compilation = facade.parse(pathApp);
			methodNames = handlerMethod(facade.getImpactedElementsMoveMethod(compilation, classOneName, classTwoName, methodName));

		} catch (DBException e) {
			e.printStackTrace();
		}
	}

	/**
	 * <p>
	 * 		this method run the pull up method.
	 * </p>
	 */
	public void runPullUpMethod() {
		try {
			compilation = facade.parse(pathApp);
			methodNames = handlerMethod(facade.getImpactedElementsPullUpMethod(	compilation, classOneName, classTwoName, methodName));

		} catch (DBException e) {
			e.printStackTrace();
		}
	}

	/**
	 * <p>
	 * 		This method run the pull up field.
	 * </p>
	 */
	public void runPullUpField() {
		try {
			compilation = facade.parse(pathApp);
			methodNames = handlerMethod(facade.getImpactedElementsPullUpField(compilation, classOneName, classTwoName, fieldName));

		} catch (DBException e) {
			e.printStackTrace();
		}
	}

	/**
	 * <p>
	 * 		This method run the add parameter.
	 * </p>
	 */
	public void runAddParameter() {
		try {
			compilation = facade.parse(pathApp);
			methodNames = handlerMethod(facade.getImpactedElementsAddParameter(	compilation, className, methodName));

		} catch (DBException e) {
			e.printStackTrace();
		}
	}

	public void setPathApp(String path) {
		this.pathApp = path;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

	public void setClassOneName(String classOneName) {
		this.classOneName = classOneName;
	}

	public void setClassTwoName(String classTwoName) {
		this.classTwoName = classTwoName;
	}

	public void setOriginMethodName(String originMethodName) {
		this.originMethodName = originMethodName;
	}

	public void setNewMethodName(String newMethodName) {
		this.newMethodName = newMethodName;
	}

	public void setBeginLine(int beginLine) {
		this.beginLine = beginLine;
	}

	public void setEndLine(int endLine) {
		this.endLine = endLine;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public List<String> getMethods() {
		return methodNames;
	}

	public List<String> getSignatures() {
		return signatures;
	}

	public void setSignatures(List<String> sig) {
		this.signatures = sig;
	}

	public ArrayList<CompilationUnit> getCompilation() {
		return compilation;
	}

	public List<String> getMethodNames() {
		return methodNames;
	}

	public String getPathApp() {
		return pathApp;
	}

	public String getClassName() {
		return className;
	}

	public String getMethodName() {
		return methodName;
	}

	public String getClassOneName() {
		return classOneName;
	}

	public String getClassTwoName() {
		return classTwoName;
	}

	public String getOriginMethodName() {
		return originMethodName;
	}

	public String getNewMethodName() {
		return newMethodName;
	}

	public FacadeRBA getFacade() {
		return facade;
	}

	public int getBeginLine() {
		return beginLine;
	}

	public int getEndLine() {
		return endLine;
	}

	public String getFieldName() {
		return fieldName;
	}

	/**
	 * <p>
	 * 		This method handler a string and return a list.
	 * </p>
	 * @param methods
	 * @return
	 */
	public List<String> handlerMethod(List<String> methods) {

		setSignatures(methods);

		List<String> names = new ArrayList<String>();

		for (String s : methods) {
			s = s.replace("]", "");
			s = s.replace("[", "");

			int index = s.indexOf("(");
			String name = s.substring(0, index);
			name = name.replace("*", ".");
			String param = s.substring(index, s.length());

			param = param.replace("(", "");
			param = param.replace(")", "");
			param = param.replace(",", "");

			String[] split = param.split(" ");

			String parameters = "(";
			for (int i = 0; i < split.length; i++) {
				if (i % 2 == 0) {
					parameters += split[i];
					if (i < split.length - 2)
						parameters += " ";
				}
			}
			parameters += ")";

			names.add(name + parameters);
		}

		return names;
	}

}

