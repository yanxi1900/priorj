package input;

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

import static org.junit.Assert.*;

import org.junit.Test;

public class PropertyTest {

	@Test
	public void testProperties() {
		
		assertEquals("application", Property.APPLICATION.getName());
		assertEquals("code", Property.CODE.getName());
		assertEquals("libraries", Property.LIB.getName());
		assertEquals("tests", Property.TESTS.getName());
		assertEquals("new_code", Property.NEW_CODE.getName());
		assertEquals("junit_version_3", Property.JUNIT3.getName());
		assertEquals("junit_version_4", Property.JUNIT4.getName());
		assertEquals("technique_tmc", Property.TMC.getName());
		assertEquals("technique_amc", Property.AMC.getName());
		assertEquals("technique_tsc", Property.TSC.getName());
		assertEquals("technique_asc", Property.ASC.getName());
		assertEquals("technique_rnd", Property.RND.getName());
		assertEquals("technique_cb", Property.CB.getName());
		assertEquals("technique_rba", Property.RBA.getName());
		assertEquals("coverage_report", Property.COVERAGE_REPORT.getName());
		
		assertEquals("junit_report", Property.JUNIT_REPORT.getName());
		assertEquals("code_tree", Property.CODE_TREE.getName());
		assertEquals("execution_order", Property.EXECUTION_ORDER.getName());
		
		assertEquals("suite_tmc", Property.SUITE_TMC.getName());
		assertEquals("suite_amc", Property.SUITE_AMC.getName());
		assertEquals("suite_tsc", Property.SUITE_TSC.getName());
		assertEquals("suite_asc", Property.SUITE_ASC.getName());
		assertEquals("suite_rnd", Property.SUITE_RND.getName());
		assertEquals("suite_cb", Property.SUITE_CB.getName());
		assertEquals("suite_rba", Property.SUITE_RBA.getName());
		
		
	}
}
