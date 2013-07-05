package controller;

/**
 *
 * @author Samuel T. C.Santos
 */

import japa.parser.ast.CompilationUnit;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import technique.RefactoringEnum;
import util.Settings;

import dao.XStreamWrite;


public class RBAController {
/*
	private List<RefactoringEnum> refatoring;
	private ArrayList<CompilationUnit> compilation;
	private List<String> methodNames;
	private String pathApp;
	private String className;
	private String methodName;
	private String classOneName;
	private String classTwoName;
	private String originMethodName;
	private String newMethodName;
	private FacadeRBA facade;
	private int beginLine;
	private int endLine;
	private String fieldName;

	private List<String> signatures;
	private Map<String, Double> mapWeight;

	public RBAController() {
		facade = new FacadeRBA();

		signatures = new ArrayList<String>();
		mapWeight = new HashMap<String, Double>();
	}

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

	public void runRenameMethod() {
		try {
			compilation = facade.parse(pathApp);
			methodNames = handlerMethod(facade.getImpactedElementsRenameMethod(
					compilation, className, methodName, newMethodName));
		} catch (DBException e) {
			e.printStackTrace();
		}
	}

	public void runExtractMethod() {
		try {

			compilation = facade.parse(pathApp);
			methodNames = handlerMethod(facade
					.getImpactedElementsExtractMethod(compilation, className,
							originMethodName, newMethodName, beginLine, endLine));

		} catch (DBException e) {
			e.printStackTrace();
		}
	}

	public void runMoveMethod() {
		try {
			compilation = facade.parse(pathApp);
			methodNames = handlerMethod(facade.getImpactedElementsMoveMethod(
					compilation, classOneName, classTwoName, methodName));

		} catch (DBException e) {
			e.printStackTrace();
		}
	}

	public void runPullUpMethod() {
		try {
			compilation = facade.parse(pathApp);
			methodNames = handlerMethod(facade.getImpactedElementsPullUpMethod(
					compilation, classOneName, classTwoName, methodName));

		} catch (DBException e) {
			e.printStackTrace();
		}
	}

	public void runPullUpField() {
		try {
			compilation = facade.parse(pathApp);
			methodNames = handlerMethod(facade.getImpactedElementsPullUpField(
					compilation, classOneName, classTwoName, fieldName));

		} catch (DBException e) {
			e.printStackTrace();
		}
	}

	public void runAddParameter() {
		try {
			compilation = facade.parse(pathApp);
			methodNames = handlerMethod(facade.getImpactedElementsAddParameter(
					compilation, className, methodName));

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

	private List<String> handlerMethod(List<String> methods) {

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

	public void save(List<String> methods) {

		String local = System.getProperty("user.dir");
		String directory = Settings.SEPARATOR + "priorjtmp"+ Settings.SEPARATOR;
		String file = "methods";

		File f = new File(local + directory);

		if (!f.exists()) {
			File createFile = new File(local + directory);

			createFile.mkdir();

		}
		XStreamWrite writer = new XStreamWrite(local + directory + file);
		writer.write(methods);

	}
*/
}

