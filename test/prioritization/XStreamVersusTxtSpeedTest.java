package prioritization;

import static org.junit.Assert.*;

import input.InputParse;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import coverage.TestSuite;

import report.CoverageReport;

import util.PathTo;
import util.Reader;
import util.Timer;
/**
 * This class is an test to demonstrate who is more quick XStream or Txt config file.
 * 
 * @author Samuel T. C. Santos
 * 
 * @version 1.0
 *
 */
public class XStreamVersusTxtSpeedTest {

	String path;
	
	@Before
	public void setUp() throws Exception {
		path = PathTo.RESOURCES_PRIORITIZATION_FILES + PathTo.SEPARATOR;
	}

	@After
	public void tearDown() throws Exception {
		
	}

	@Test
	public void testXStreamVersusTxtReadingSpeed() throws Exception {
		
		//object to measure time
        Timer timer = new Timer();  
        
		//reading from XStrem
		String filename = path + "coveragePriorJ";
		Reader reader = new Reader(filename);
		
		List<TestSuite> suitesFromXStreamFile = (List<TestSuite>) reader.read();
	
		//get the time to XStream reading.
		Long timeXStream = timer.getTime();
		
		System.out.println("xstream time: " + timer);
		
		//reset the time counter
		timer.reset();
		
		//testing the file configuration
		filename = "jmock.txt";
		path += filename;
		
		InputParse coverage = new InputParse(path, "testsuite");
		coverage.runParse();
		
		List<TestSuite> suitesFromTxtFile = coverage.getResultAsSuite();
		
		Long timeTxt = timer.getTime();
		
		System.out.println("txt time: " + timer);
	
		assertTrue(suitesFromXStreamFile.size() == suitesFromTxtFile.size());
		
		//assertTrue(suitesFromTxtFile.size() == suites.size());
		assertTrue(timeTxt < timeXStream);
	}

}
