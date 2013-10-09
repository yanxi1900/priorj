package input;

import java.util.LinkedList;
import java.util.List;

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
 * This class is a input to PriorJ.
 * 
 * @author Samuel T. C. Santos
 * @version 1.0.0
 *
 */
public class InputPriorJ {

	InputParse parse;
	
	public InputPriorJ(){
		parse = new InputParse();
	}
	
	public InputPriorJ(String pathConfigFile){
		parse = new InputParse(pathConfigFile);
		parse.runParse();
	}
		
	public String pathApp(){
		return parse.getValue(Property.APPLICATION);
	}

	public String pathCode() {
		return parse.getValue(Property.CODE);
	}

	public String pathLib() {
		return parse.getValue(Property.LIB);
	}

	public String pathTest() {
		return parse.getValue(Property.TESTS);
	}
	
	public String pathNewCode(){
		return parse.getValue(Property.NEW_CODE);
	}

	public String JUnit3() {
		return parse.getValue(Property.JUNIT3);
	}

	public String JUnit4() {
		return parse.getValue(Property.JUNIT4);
	}

	public String TMC() {
		return parse.getValue(Property.TMC);
	}
	
	public String TSC() {
		return parse.getValue(Property.TSC);
	}
	
	public String AMC() {
		return parse.getValue(Property.AMC);
	}
	
	public String ASC() {
		return parse.getValue(Property.ASC);
	}
	
	public String RND() {
		return parse.getValue(Property.RND);
	}
	
	public String CB() {
		return parse.getValue(Property.CB);
	}
	
	public String RBA() {
		return parse.getValue(Property.RBA);
	}

	public String coverageReport() {
		return parse.getValue(Property.COVERAGE_REPORT);
	}
	
	public String codeTree(){
		return  parse.getValue(Property.CODE_TREE);
	}
	
	public String jUnitReport(){
		return parse.getValue(Property.JUNIT_REPORT);
	}
	
	public String executionOrder(){
		return  parse.getValue(Property.EXECUTION_ORDER);
	}
	
	public String suiteTMC() {
		return parse.getValue(Property.SUITE_TMC);
	}
	
	public String suiteTSC() {
		return parse.getValue(Property.SUITE_TSC);
	}
	
	public String suiteAMC() {
		return parse.getValue(Property.SUITE_AMC);
	}
	
	public String suiteASC() {
		return parse.getValue(Property.SUITE_ASC);
	}
	
	public String suiteRND() {
		return parse.getValue(Property.SUITE_RND);
	}
	
	public String suiteCB() {
		return parse.getValue(Property.SUITE_CB);
	}
	
	public String suiteRBA() {
		return parse.getValue(Property.SUITE_RBA);
	}
	
	public boolean hasSuite(){
		return suiteAMC().toLowerCase().equals("yes") ||
		suiteASC().toLowerCase().equals("yes") ||
		suiteCB().toLowerCase().equals("yes") ||
		suiteRBA().toLowerCase().equals("yes") ||
		suiteRND().toLowerCase().equals("yes") ||
		suiteTMC().toLowerCase().equals("yess") ||
		suiteTSC().toLowerCase().equals("yes");
	}
	
	public String rmPathApp() {
		return parse.getValue(Property.RM_PATH_APP);
	}

	public String rmClassName() {
		return parse.getValue(Property.RM_CLASS_NAME);
	}

	public String rmMethodName() {
		return parse.getValue(Property.RM_METHOD_NAME);
	}

	public String rmNewMethodName() {
		return parse.getValue(Property.RM_NEW_METHOD_NAME);
	}
	
	
}
