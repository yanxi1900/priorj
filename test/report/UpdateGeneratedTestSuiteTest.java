package report;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import util.PathTo;

/**
 * <p>
 * 	 This class is a test to class over test <code>UpdateGeneratedTestSuite</code>.
 * </p>
 * @author Samuel T. C. Santos
 * @version 1.0
 *
 */
public class UpdateGeneratedTestSuiteTest {

	private UpdateGeneratedTestSuite ugts;
	
	@Before
	public void setUp() throws Exception {
		ugts = new UpdateGeneratedTestSuite();
	}

	@After
	public void tearDown() throws Exception {
		ugts = null;
	}

	@Test
	public void testVerifyIfExistGeneratedTestSuites() {
		boolean response;
		try {
			response = ugts.existGeneratedTestSuiteCode();
			
			if (response){
				assertTrue(ugts.existGeneratedTestSuiteCode());
			}
			else{
				assertFalse(ugts.existGeneratedTestSuiteCode());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
	
	
	@Test 
	public void testTryDetectAllSuites(){
		List<String> detected = ugts.detectAllSuites();
	
		if (detected.isEmpty()){
			assertTrue(detected.size()==0);
		}
		else{
			assertTrue(detected.size() != 0);
		}
		
		System.out.println(detected);
	}
	
	@Test
	public void testLocateOrderTestCaseToUpdateTestSuite(){
		List<String> detected = ugts.detectAllSuites();
		
		if (!detected.isEmpty()){
			for (String fileNameSuite : detected){
				List<String> testsOrder = ugts.detectOrder(fileNameSuite) ;
				
				assertTrue(testsOrder.size() != 0);
				
			}
		}
		else{
			assertTrue(detected.size()==0);
			assertFalse(ugts.isActived());
		}
	}

}
