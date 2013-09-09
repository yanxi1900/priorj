package app;

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

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JPanel;

import project.JUnitVersionEnum;
import report.CodeTree;
import report.CoverageReport;

import technique.TechniquesEnum;
import util.ManagerFiles;
import util.Settings;

import main.PriorJ;
import main.PriorJFacade;
import main.PriorJImpl;

import coverage.TestCase;

import exception.CannotReadLogFileException;
import exception.CoverageUnrealizedException;
import exception.DirectoryNotExistException;
import exception.DuplicateProjectNameException;
import exception.EmptyPathException;
import exception.EmptyPriorJProjectNameException;
import exception.EmptySetOfTestCaseException;
import exception.InstrumentationUnrealizedException;

/**
 * Console is the main class which run in mode command
 * line, provide access for all PriorJ functionalities. 
 * 
 * @author Samuel T. C. Santos
 * @version 1.0
 */
public class Console {
	
	private String paths [];
	Scanner input = new Scanner(System.in);
	
	public void run(){
		PriorJFacade facade = new PriorJFacade();
		
		readPaths(facade);
		
		readJUnitVersion(facade);
		
		readMultipleTechniques(facade);
		
		executeRun(facade);
	}

	/**
	 * This method read and validate the input paths.
	 * 
	 * @param facade
	 */
	private void readPaths(PriorJFacade facade) {
		
		String pathApp = readPathApp();
		
		facade.setPathApp(pathApp);
		
		String pathCode = readPathCode();
		facade.setPathCode(pathCode);
		
		String pathLib = readPathLib();
		facade.setPathLib(pathLib);
		
		String pathTest = readPathTest();
		facade.setPathTest(pathTest);
	}

	/**
	 * This method allow the user indicate multiple techniques.
	 * 
	 */
	private void readMultipleTechniques(PriorJFacade facade){
		String option = "yes";
		while(option.toLowerCase().equals("yes")){
			readTechnique(facade);
			option = readContinueOption("\nAdd more technique");
		}
	}
	
	/**
	 * This method execute the process.
	 * 
	 * @param facade
	 */
	private void executeRun(PriorJFacade facade) {
		try {
			executeProcess(facade);
			executeReportProcess(facade);
		} catch (InstrumentationUnrealizedException e) {
			printerr("\n" + e.getMessage());
		}
		catch(CoverageUnrealizedException e){
			printerr("\n" + e.getMessage());
		}
		catch( CannotReadLogFileException e){
			printerr("\n" + e.getMessage());
		}
		catch(Exception e){
			printerr("\n" + e.getMessage());
		}
	}
	/**
	 * This method allow the user to access reports.
	 * 
	 * @param facade
	 */
	private void executeReportProcess(PriorJFacade facade) {
		String opt = "yes";

		while (opt.equals("yes")){
			menuActions();
			opt = readMenuOption();

			execute(opt, facade);	
			
			opt = readContinueOption();
		}
	}

	/**
	 * This method execute prioritization process.
	 * 
	 * @param facade
	 * @throws InstrumentationUnrealizedException
	 * @throws CoverageUnrealizedException
	 * @throws CannotReadLogFileException
	 * @throws Exception
	 */
	private void executeProcess(PriorJFacade facade)
			throws InstrumentationUnrealizedException,
			CoverageUnrealizedException, CannotReadLogFileException, Exception {
		println("0% \t Run Instrumentation");
		facade.runInstrumentation();
		
		println("25% \t Run Coverage");
		facade.runCoverage();
		
		println("50% \t Run Coverage Analysis");
		facade.runReadLog();
		
		println("75% \t Run Prioritization");
		facade.runPrioritization();
		
		println("100% \t Complete");
	}
	
	
	/**
	 * This method execute the selected option.
	 * 
	 * @param option
	 * 		An option
	 * @param facade
	 * 		The facade
	 */
	private void execute(String option, PriorJFacade facade){
		
		if (option.equals("1")){
			//coverage report
			showCoverageReport(facade);
		}
		else if (option.equals("2")){
			//code tree
			showCodeTree(facade);
		}
		else if (option.equals("3")){
			//jUnit report
			showSimpleJUnitReport(facade);
		}
		else if (option.equals("4")){
			//prioritized order
			showExecutionOrder(facade);		
		}
		else if (option.equals("5")){
			//java suite
			showJavaPrioritizedSuite(facade);
		}
		else if (option.equals("6")){
			List<String> tests = facade.getFailedTests();
			showFailedTestCases(tests);
		}
		else if (option.equals("7")){
			runAPFD(facade);
		}
		else if (option.equals("8")){
			runAPFDChart(facade);
		}
	
		
	}

	/**
	 * This method show the prioritized suites.
	 * 
	 * @param facade
	 */
	private void showJavaPrioritizedSuite(PriorJFacade facade) {
		List<String> codes  = facade.getJavaPrioritizedSuites();
		for (String s: codes){
			System.out.println(s);
		}
	}

	/**
	 * This method show the execution order.
	 * 
	 * @param facade
	 */
	private void showExecutionOrder(PriorJFacade facade) {
		List<String> order = facade.getOrder();
		for (String s: order){
			System.out.println(s);
		}
	}

	/**
	 * This method show the simple JUnit report.
	 * 
	 * @param facade
	 */
	private void showSimpleJUnitReport(PriorJFacade facade) {
		String report = facade.getSimpleJUnitReport();
		print(report);
	}

	/**
	 * This method show the simple code tree.
	 * 
	 * @param facade
	 */
	private void showCodeTree(PriorJFacade facade) {
		String codeTree = facade.getCodeTree();
		print(codeTree);
	}

	/**
	 * This method show the coverage report.
	 * 
	 * @param facade
	 */
	private void showCoverageReport(PriorJFacade facade) {
		String report = facade.getCoverageReport();
		print(report);
	}
	
	/**
	 * Show the actions menu.
	 */
	private void menuActions(){
		StringBuilder menu = new StringBuilder();
		menu.append("\n # Report Options");
		menu.append("\n(1). Coverage Report ");
		menu.append("\n(2). Code Tree ");
		menu.append("\n(3). JUnit Report ");
		menu.append("\n(4). Prioritization Order ");
		menu.append("\n(5). Prioritized Java Suite");
		menu.append("\n(6). Failed Test Cases");
		menu.append("\n(7). Average Percent of Faults Detected (Value)");
		menu.append("\n(8). Average Percent of Faults Detected (Chart)");
		menu.append("\n(9). F1-measure (value)");
		menu.append("\n(10). Suite Selection");
		
		print(menu.toString());
	}
	
	/**
	 * This method show failed tests cases.
	 * 
	 * @param tests
	 */
	private void showFailedTestCases(List<String> tests){
		if (tests.isEmpty())
			println("\nFailed test not found!");
		
		int counter = 1;
		for (String test : tests){
			println(" (" + counter + ") " + test);
			counter++;
		}
	}
	
	private String readMenuOption(){
		List<String>  opt = Arrays.asList("1", "2", "3", "4", "5","6", "7","8");
		print("\nOption: ");
		String in = input.nextLine();
		
		if (opt.contains(in)){
			return in;
		}
		else{
			return readMenuOption();
		}
	}
	
	/**
	 * This method ask the user if should be continued the program.
	 * @return
	 */
	private String readContinueOption(){
		print("Continue <yes/no>? ");
		String opt = input.nextLine();
		
		if (opt.toLowerCase().equals("yes") || opt.toLowerCase().equals("no")){
			return opt;
		}
		else{
			return readContinueOption();
		}
	}
	
	/**
	 * This method ask the user, showing a message.
	 * 
	 * @param message
	 * @return
	 */
	private String readContinueOption(String message){
		print( message + " <yes/no>? ");
		String opt = input.nextLine();
		
		if (opt.toLowerCase().equals("yes") || opt.toLowerCase().equals("no")){
			return opt;
		}
		else{
			return readContinueOption();
		}
	}
	
	
	
	/**
	 * Read from user the selected technique and validate read process.
	 * 
	 * @param facade
	 */
	private void readTechnique(PriorJFacade facade){
		try {
			String technique = readTechnique();
			facade.addTechnique(technique);
		} catch (Exception e) {
			printerr(e.getMessage());
			readTechnique(facade);
		}
	}
	
	/**
	 * Do reading the input option.
	 * 
	 * @return
	 */
	private String readTechnique(){
		try {
			menuTechniques();
			String in = input.nextLine();
			
			List<String> options = Arrays.asList("tmc","tsc","amc","asc","rnd","cd","rba");
			
			if (options.contains(in)){
				return in;
			}
			else
				return readTechnique();
		} catch (Exception e) {
			printerr(e.getMessage());
			return readTechnique();
		}
	}
	
	/**
	 * Show the available techniques list.
	 * 
	 */
	private void menuTechniques(){
		StringBuilder menu = new StringBuilder();
		menu.append("\n# Techniques Selection ");
		menu.append("\n(tmc) - Total Method Coverage");
		menu.append("\n(tsc) - Total Statement Coverage");
		menu.append("\n(amc) - Additional Method Coverage ");
		menu.append("\n(asc) - Additional Statement Coverage");
		menu.append("\n(rnd) - Random ");
		menu.append("\n(cb)  - Changed Blocks ");
		menu.append("\n(rba) - Refactoring Based Approach");
		menu.append("\nTechnique: ");
		print(menu.toString());
	}
	
	/**
	 * Handler the selected technique option read from input.
	 *  
	 * @param in
	 * @return
	 */
	private TechniquesEnum selectTechnique(String in){
		TechniquesEnum technique;
	
		if (in.equals("tmc"))
			technique = TechniquesEnum.TOTAL_METHOD_COVERAGE;
		else if (in.equals("tsc"))
			technique = TechniquesEnum.TOTAL_STATEMENT_COVERAGE;
		else if (in.equals("amc"))
			technique = TechniquesEnum.ADDITIONAL_METHOD_COVERAGE;
		else if (in.equals("asc"))
			technique = TechniquesEnum.ADDITIONAL_STATEMENT_COVERAGE;
		else if (in.equals("rnd"))
			technique = TechniquesEnum.Random;
		else if (in.equals("rba"))
			technique = TechniquesEnum.REFACTORING_BASED_APPROACH;
		else
			technique = null;
		
		return technique;
	}
	
	/**
	 * Read from input the JUnit version.
	 * 
	 * @param facade
	 */
	private void readJUnitVersion(PriorJFacade facade){
		try{
			String version = readJUnitVersion();
			facade.setJUnitVersion(version);
			createConsoleProject(version, facade);
		}
		catch(Exception ex){
			printerr(ex.getMessage()+"\n");
			readJUnitVersion();
		}
	}
	
	/**
	 * Create a new project to console.
	 * 
	 * @param version
	 * 	JUnit version.
	 * @param facade
	 * 	The Facade Object.
	 * @throws Exception 
	 */
	private void createConsoleProject(String version,PriorJFacade facade) throws Exception{
		if ( facade.searchProject("console"))
			facade.removeProject("console");
		
		facade.createProject("console", version);
		
	}
	/**
	 * Read a path to new code.
	 * 
	 * @return
	 */
	private String readPathCodeNew(){
	
		print("Path code new: ");
		String path = input.nextLine();
		
		if(validate(path))
			return path;
		else
			return readPathCodeNew();
	
	}
	
	/**
	 * This method read the path application.
	 * 
	 * @return
	 * 		path application.
	 */
	public String readPathApp(){		
		print("\nPath application: ");
		String path = input.nextLine();
		if (validate(path))
			return path;
		else
			return readPathApp();

	}
	

	/**
	 * This method read the path libraries.
	 * 
	 * @return
	 * 		the path libraries.
	 */
	public String readPathLib(){
	
		print("Path Libraries: ");
		String path = input.nextLine();
		
		if (path.isEmpty())
			return path;
		else if (validate(path))
			return path;
		else
			return readPathLib();
	}
	
	/**
	 * This method read the path to tests.
	 * 
	 * @return
	 * 		the path to tests.
	 */
	public String readPathTest(){
		print("Path Test: ");
		String path = input.nextLine();
		if (validate(path))	
			return path;
		else
			return readPathTest();
	}
	
	/**
	 * This method read a path to new code.
	 * 
	 * @return
	 * 		the path to new code version.
	 */
	public String readPathNewCode(){
		print("Path new code: ");
		String path = input.nextLine();
		if (validate(path))
			return path;
		else
			return readPathNewCode();

	}
	
	/**
	 * This method verify if the file exist.
	 * 
	 * @param path
	 * @return
	 */
	public boolean validate(String path){
		path = pathParse(path);
		return ManagerFiles.existFileOrDirectory(path);
	}
	
	
	/**
	 * This method read the path code.
	 * 
	 * @return
	 * 		the path code.
	 */
	public String readPathCode(){
		
		print("Path code: ");
		String pathCode = input.nextLine();
		
		if (validate(pathCode))
			return pathCode;
		else
			return readPathCode();
	
	}
	/**
	 * Read the JUnit version.
	 * 
	 * @return
	 */
	public String readJUnitVersion(){
		try {
			print("JUnit version <junit3/junit4>: ");
			String version = input.nextLine();
			return version;
			
		} catch (Exception e) {
			printerr("\n" + e.getMessage());
			return readJUnitVersion();
		}
	}
	
	/**
	 * Show a message.
	 * @param arg
	 */
	public static void print(String arg){
		System.out.print(arg);
	}
	
	
	
	/**
	 * This method calculate the APFD value.
	 * 
	 * @param facade
	 */
	public void runAPFD(PriorJFacade facade){
		List<List<String>> combinations = new ArrayList<List<String>>();
		
		String option = "yes";
		
		List<String> failedTests = facade.getFailedTests();
		
		while (option.toLowerCase().equals("yes")){
			println(" # Add new Fault");
			addFaultAPFD(combinations, failedTests);
			
			option = readContinueOption("Add more Faults");
		}
		
		showAPFDValue(facade, combinations);
		
	}
	
	/**
	 * This method generate the APFD value and next show the chart.
	 * 
	 * @param facade
	 */
	public void runAPFDChart(PriorJFacade facade){
		runAPFD(facade);
		showAPFDChart(facade);
	}
	
	/**
	 * This method show the APFD chart.
	 * 
	 */
	public void showAPFDChart(PriorJFacade facade){
		JFrame frame = new JFrame();
		
		JPanel panelChart = facade.generateAPFDChart();
		
		frame.add(panelChart);
		
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(new Dimension(500,400));
		frame.setVisible(true);
	}

	/**
	 * This method calculate and show the generated APFD value.
	 * 
	 * @param facade
	 * @param combinations
	 */
	private void showAPFDValue(PriorJFacade facade, List<List<String>> combinations) {
		double apfdValue = facade.generateAPFD(combinations);
		
		println("APFD = " + String.format("%.2f",apfdValue));
	}
	
	/**
	 * This method read from user a combination to APFD input.
	 * 
	 * @param combinations
	 * @param tests
	 */
	private void addFaultAPFD(List<List<String>> combinations, List<String> tests){
		List<String> combination = new ArrayList<String>();
		
		String option = "yes";
		String selectedTest = "";
		
		//range for reading options
		int readingRange = tests.size();
		int testIndex = 0;
		
		while( option.toLowerCase().equals("yes")){
			println(" # Select failed test case");
			showFailedTestCases(tests);
	
			selectedTest = readFailedTestOption(readingRange);
			
			testIndex = Integer.parseInt(selectedTest);
			testIndex--;
			doCombination(combination, tests.get(testIndex));
			
			option = readContinueOption("Add more tests");
		}
	
		combinations.add(combination);
	}
	
	/**
	 * This method add a test case to combination.
	 * 
	 * @param combination
	 * @param testCaseName
	 */
	private void doCombination(List<String> combination, String testCaseName){
		combination.add(testCaseName);
	}
	
	/**
	 * This method validate the reading of failed test selection.
	 * 
	 * @param size
	 * 		the number of failed tests.
	 * 
	 * @return
	 */
	private String readFailedTestOption(int size){
		List<String>  opt = new ArrayList<String>();
		
		for (int i = 1; i <= size ; i++){
			opt.add(String.valueOf(i));
		}
		
		print("\nOption: ");
		String in = input.nextLine();
		
		if (opt.contains(in)){
			return in;
		}
		else{
			return readMenuOption();
		}
	}
	
	/**
	 * Show a message.
	 * @param arg
	 */
	public static void println(String arg){
		System.out.println( arg);
	}
	
	/**
	 * Show a error message.
	 * 
	 * @param arg
	 */
	public static void printerr(String arg){
		System.err.println(arg);
	}
	
	/**
	 * write a new line.
	 */
	public static void newLine(){
		System.out.println("");
	}
	
	/**
	 * This method do a parse in the path.
	 * 
	 * @param path
	 * @return
	 */
	private static String pathParse(String path) {
		if (path.contains("\\"))
			path = path.replace("\\", Settings.SEPARATOR);
		else if (path.contains("/"))
			path = path.replace("/",Settings.SEPARATOR);
		return path;
	}

	/**
	 * Run the application on command line mode.
	 * 
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		Console console = new Console();
		console.run();
	}	
}

