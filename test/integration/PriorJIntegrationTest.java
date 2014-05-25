package integration;

import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import technique.TechniqueCreator;

import com.java.io.JavaIO;

import controller.DataManager;
import controller.PriorJ;
import controller.PriorJTest;
import dao.Repository;

/**
 * First Integration Test using DataManager and PriorJ.
 * 
 * @author Samuel T. C. Santos
 *
 */
public class PriorJIntegrationTest {
	
	Repository repository;
	PriorJ priorj;
	
	@SuppressWarnings("rawtypes")
	List<List> allSuites;
	String filePath;
	
	@Before
	public void setUp(){
		repository = new Repository();
		priorj = PriorJ.getInstance();
		filePath = JavaIO.USER_DIR+"/report/coveragePriorJ.xml";
	}
	
	@After
	public void tearDown(){
		repository = null;
		priorj = null;
	}
	
	@Test
	public void repositoryShouldWriteDataToCoverageFile(){
		deleteCoverageFile();
		writeCoverageData();
		List<List> allSuites = DataManager.openCoverageData(filePath);
		assertTrue(allSuites.size()==3);
	}

	@Test
	public void shouldSaveCoverageDataAnyWhere(){
		DataManager.saveCoverageData("c:/tests/tdd/","coverage.xml", allSuites);
		assertTrue(JavaIO.exist("c:/tests/tdd/coverage.xml"));
	}
	
	@SuppressWarnings("rawtypes")
	@Test
	public void shouldOpenCoverageFile(){
		deleteCoverageFile();
		writeCoverageData();
		List<List> coverage = DataManager.openCoverageData(filePath);
		assertTrue(coverage.size() == 3);
	}

	@Test
	public void shouldSavePrioritizationOrder() throws Exception{
		DataManager.createLocalbase("c:/tests");
		DataManager.createFolderVersion("Open-half-One", "priorVersion1");
		List<String> results = Arrays.asList("testY", "testD", "testB", "testX");
		String report = priorj.createOrderReport(TechniqueCreator.RANDOM, results);
		DataManager.save("RND.txt", report);	
		assertTrue(JavaIO.exist("c:/tests/Open-half-One/priorVersion1/RND.txt"));
	}
	
	private void writeCoverageData() {
		JavaIO.createXMLFile(JavaIO.USER_DIR+"/report", "coveragePriorJ.xml", "<list>", true);
		repository.addTestCase("org","SuiteAD", "test1");
		repository.addMethod("com.lux", "LuxPull", "pullProxy");
		repository.addStatement("26");
		repository.addStatement("28");
		repository.addStatement("29");
		repository.commit();
		
		repository.addTestCase("org","SuiteADX", "test2");
		repository.addMethod("com.lux", "LuxPullOver", "pullProxyExceptionHandler");
		repository.addStatement("19");
		
		repository.addMethod("com.lux", "LuxInLoop", "pullProxyError");
		repository.addStatement("25");
		repository.commit();
		
		repository.addTestCase("org","SuiteADI", "test3");
		repository.addMethod("com.lux", "LuxPullRandom", "pullProxyInvalidRequestAccess");
		repository.addStatement("298");
		repository.addStatement("245");
		repository.addStatement("126");
		repository.commit();
		repository.finishCommit();
	}

	private void deleteCoverageFile() {
		if(JavaIO.exist(filePath)){
			JavaIO.deleteFile(filePath);
		}
	}	
}
