package app;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import project.JUnitVersionEnum;
import report.CodeTree;
import report.CoverageReport;

import technique.TechniquesEnum;
import util.Settings;

import main.PriorJ;
import main.PriorJFacade;
import main.PriorJImpl;

import coverage.TestCase;

import exception.CannotReadLogFileException;
import exception.CoverageUnrealizedException;
import exception.DirectoryNotExistException;
import exception.EmptyPathException;
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
		
		readPathApp(facade);
		
		readPathCode(facade);
		
		readPathLib(facade);
		
		readPathTest(facade);
		
		readJUnitVersion(facade);
		
		readTechnique(facade);
		
		try {
			println("0% \t Run Instrumentation");
			facade.runInstrumentation();
			
			println("25% \t Run Coverage");
			facade.runCoverage();
			
			println("50% \t Run Coverage Analysis");
			facade.runReadLog();
			
			println("75% \t Run Prioritization");
			facade.runPrioritization();
			
			println("100% \t complete");
			
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
	
	private void readTechnique(PriorJFacade facade){
		try {
			String technique = readTechnique();
			facade.addTechnique(technique);
		} catch (Exception e) {
			// TODO: handle exception
			printerr(e.getMessage());
			readTechnique(facade);
		}
	}
	
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
	
	private void menuTechniques(){
		StringBuilder menu = new StringBuilder();
		menu.append("\n# Techniques  ");
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
	
	private void readPathApp(PriorJFacade facade){
		try {
			String path = readPathApp();
			facade.setPathApp(path);
		} catch (Exception e) {
			printerr("\n" + e.getMessage());
			readPathApp();
		}
	}
	
	private void readPathCode(PriorJFacade facade){
		try {
			String path = readPathCode();
			facade.setPathCode(path);
		} catch (Exception e) {
			printerr("\n" + e.getMessage());
			readPathCode();
		}
	}
	
	private void readPathLib(PriorJFacade facade){
		try {
			String path = readPathLib();
			facade.setPathLib(path);
		} catch (Exception e) {
			printerr("\n" + e.getMessage());
			readPathApp();
		}
	}
	
	private void readPathTest(PriorJFacade facade){
		try {
			String path = readPathTest();
			facade.setPathTest(path);
		} catch (Exception e) {
			printerr("\n" + e.getMessage());
			readPathApp();
		}
	}
	
	private void readPathCodeNew(PriorJFacade facade){
		try {
			String path = readPathNewCode();
			facade.setPathCodeNew(path);
		} catch (Exception e) {
			printerr("\n" + e.getMessage());
			readPathCodeNew();
		}
	}
	
	private void readJUnitVersion(PriorJFacade facade){
		try{
			String version = readJUnitVersion();
			facade.setJUnitVersion(version);
		}
		catch(Exception ex){
			printerr(ex.getMessage()+"\n");
			readJUnitVersion();
		}
	}
	
	private String readPathCodeNew(){
		try{
			print("Path code new: ");
			String path = input.nextLine();
			return path;
		}
		catch(Exception e){
			printerr("\n" +e.getMessage());
			return readPathCodeNew();
		}
	}
	
	/**
	 * This method read the path application.
	 * 
	 * @return
	 * 		path application.
	 */
	public String readPathApp(){
		try{
			print("\nPath application: ");
			String path = input.nextLine();
			return path;
		}
		catch(IllegalArgumentException iae){
			printerr("\n" + iae.getMessage());
			return readPathApp();
		}

	}
	/**
	 * This method read the path libraries.
	 * 
	 * @return
	 * 		the path libraries.
	 */
	public String readPathLib(){
		try{
			print("Path Libraries: ");
			String path = input.nextLine();
			return path;
		}
		catch(IllegalArgumentException iae){
			printerr("\n" + iae.getMessage());
			return readPathLib();
		}

	}
	/**
	 * This method read the path to tests.
	 * 
	 * @return
	 * 		the path to tests.
	 */
	public String readPathTest(){
		try{
			print("Path Test: ");
			String path = input.nextLine();
			return path;
		}
		catch(IllegalArgumentException iae){
			printerr("\n" + iae.getMessage());
			return readPathTest();
		}

	}
	
	/**
	 * This method read a path to new code.
	 * 
	 * @return
	 * 		the path to new code version.
	 */
	public String readPathNewCode(){
		try{
			print("Path new code: ");
			String path = input.nextLine();
			return path;
		}
		catch(IllegalArgumentException iae){
			printerr("\n" + iae.getMessage());
			return readPathNewCode();
		}

	}
	
	
	/**
	 * This method read the path code.
	 * 
	 * @return
	 * 		the path code.
	 */
	public String readPathCode(){
		try {
			print("Path code: ");
			String pathCode = input.nextLine();
			return pathCode;
		} catch (IllegalArgumentException iae) {
			printerr("\n" + iae.getMessage());
			return readPathCode();
		}
	}
	
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

	
	public static void main(String[] args) throws Exception {
		Console console = new Console();
		console.run();
	}	
}

