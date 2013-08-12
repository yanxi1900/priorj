package app;

/*
* PriorJ: JUnit Test Case Prioritization.
* 
* Copyright (C) 2012-2013  Samuel T. C. Santos, Julio H. Rocha
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

import input.InputPriorJ;

import java.util.List;
import java.util.Scanner;

import exception.CannotReadLogFileException;
import exception.CoverageUnrealizedException;
import exception.InstrumentationUnrealizedException;

import project.JUnitVersionEnum;
import util.ManagerFiles;
import util.Settings;

import main.PriorJFacade;

/**
 * This class is an auto run to PriorJ using a configuration file.
 * 
 * @author Samuel T. C. Santos
 * @version 1.0
 *
 */
public class AutoRunConsole {

	public static void main(String[] args) {
		
		String path = readPathsConfigFile();
		
		PriorJFacade facade = new PriorJFacade();
		InputPriorJ input = new InputPriorJ(path);
		
		setPaths(facade, input);
		
		setJUnitVersion(facade, input);
		
		addTechniques(facade, input);
		
		run(facade);
		
		showReports(facade, input);
	}

	private static void showReports(PriorJFacade facade, InputPriorJ input) {
		if (input.coverageReport().toLowerCase().equals("yes")){
			String report = facade.getCoverageReport();
			print(report);
		}
		
		if(input.codeTree().toLowerCase().equals("yes"))
			print(facade.getCodeTree());
		
		if(input.jUnitReport().toLowerCase().equals("yes"))
			print(facade.getSimpleJUnitReport());
		
		if(input.executionOrder().toLowerCase().equals("yes")){
			List<String> listOrder = facade.getOrder();
			
			for (String order : listOrder){
				print(order);
			}
		}
	}

	private static void run(PriorJFacade facade) {
		try {
			facade.runInstrumentation();
			facade.runCoverage();
			facade.runReadLog();
			facade.runPrioritization();
		} catch (InstrumentationUnrealizedException e) {
			System.err.println(e.getMessage());
		} catch (CoverageUnrealizedException e) {
			System.err.println(e.getMessage());
		} catch (CannotReadLogFileException e) {
			System.err.println(e.getMessage());
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}

	private static void addTechniques(PriorJFacade facade, InputPriorJ input) {
		if (input.TMC().toLowerCase().equals("yes"))
			facade.addTechnique("tmc");
		
		if (input.TSC().toLowerCase().equals("yes"))
			facade.addTechnique("tsc");
		
		if(input.AMC().toLowerCase().equals("yes"))
			facade.addTechnique("amc");
		
		if (input.ASC().toLowerCase().equals("yes"))
			facade.addTechnique("asc");
		
		if (input.RND().toLowerCase().equals("yes"))
			facade.addTechnique("rnd");
		
		if (input.CB().toLowerCase().equals("yes"))
			facade.addTechnique("cb");
		
		if(input.RBA().toLowerCase().equals("yes"))
			facade.addTechnique("rba");
	}

	private static void setJUnitVersion(PriorJFacade facade, InputPriorJ input) {
		if (input.JUnit3().toLowerCase().equals("yes"))
			facade.setJUnitVersion("junit3");
		else if (input.JUnit4().toLowerCase().equals("yes"))
			facade.setJUnitVersion("junit4");
		else
			System.out.println("Undefined JUnit Version!");
	}

	private static void setPaths(PriorJFacade facade, InputPriorJ input) {
		facade.setPathApp(input.pathApp());
		facade.setPathCode(input.pathCode());
		facade.setPathLib(input.pathLib());
		facade.setPathTest(input.pathTest());
		facade.setPathCodeNew(input.pathNewCode());
	}

	private static String readPathsConfigFile() {
		Scanner in = new Scanner(System.in);
		
		System.out.print("Configuration file: ");
		
		String path = in.nextLine();
	
		path = pathParse(path);
		
		if(ManagerFiles.existFileOrDirectory(path))
			return path;
		else
			return readPathsConfigFile();
	}

	private static String pathParse(String path) {
		if (path.contains("\\"))
			path = path.replace("\\", Settings.SEPARATOR);
		else if (path.contains("/"))
			path = path.replace("/",Settings.SEPARATOR);
		return path;
	}
	
	private static void print(String arg){
		System.out.println(arg);
	}
}
