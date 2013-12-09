package report;

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

import java.util.LinkedList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import util.ReadXML;
import coverage.TestCase;
import coverage.TestSuite;

/**
 * This class is a test to  CoverageReport class.
 * 
 * 
 * @author Samuel T. C. Santos
 * @version 1.0
 *
 */
public class CoverageReportTest {

	private CoverageReport coverageReport;
	private List<TestSuite> suites;
	
	@Before 
	public void setUp(){
		suites = new LinkedList<TestSuite>();
		coverageReport = new CoverageReport();
	}
	
	@Test
	public void testWhenSuiteIsEmpty(){
		List<TestCase> tests = coverageReport.getTests();
		
		assertTrue(tests.isEmpty());
	}
	
	@Test
	public void testRemoveRepetedTestCase() throws Exception {
		List<TestSuite> suites = ReadXML.getAllTestSuites();
	
		coverageReport.buildReport(suites);
		
		List<TestCase> tests = coverageReport.getTests();
		
		if(!tests.isEmpty()){
			System.out.println(tests.size());
		}
		
	}
	
	@Test
	public void testCreatingEmptyCodeTreeCoverage(){
		CoverageReport report = new CoverageReport();
		
		String coverageReportStr = report.toString();
		
		assertEquals("", coverageReportStr);
	}
	
	
	@Test
	public void testCreateCoverageFileConfig() throws Exception{
		List<TestSuite> suites = ReadXML.getAllTestSuites();
		
		coverageReport.buildFileConfig(suites,"xml-security");
				
	}

}
