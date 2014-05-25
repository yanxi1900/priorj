package tests;
import junit.framework.TestCase;
import junit.framework.TestResult;
public class TMC {
	private int failureCount = 0;
	private int runCount = 0;
	private int errorCount = 0;
	public void run(){
		TestCase tcComSuiteBTestB = null;
		tcComSuiteBTestB = new com.suiteB(){
			public void runTest(){
				try{
					testB();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		};
		setResult("testB",tcComSuiteBTestB.run());
		TestCase tcComSuiteCTestC = null;
		tcComSuiteCTestC = new com.suiteC(){
			public void runTest(){
				try{
					testC();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		};
		setResult("testC",tcComSuiteCTestC.run());
		TestCase tcComSuiteATestA = null;
		tcComSuiteATestA = new com.suiteA(){
			public void runTest(){
				try{
					testA();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		};
		setResult("testA",tcComSuiteATestA.run());
	}
	private void setResult(String testCaseName,TestResult result){
		System.out.println("TestCase: " + testCaseName +" " + getStatus(result));
		runCount += result.runCount();
		failureCount += result.failureCount();
		errorCount += result.errorCount();
	}
	private String getStatus(TestResult result){
		int erroCount = result.errorCount();
		int failureCount = result.failureCount();
		if(erroCount > 0) return "[ERROR]";
		else if(failureCount > 0) return "[FAILURE]";
		else return "[ACCEPTED]";
	}
	public void printResult(){
		System.out.println("=================== Test Suite Prioritized - PriorJ =================");
		System.out.println("Run: " + this.runCount);
		System.out.println("Faults: " + this.failureCount);
		System.out.println("Errors: " + this.errorCount);
	}
	public static void main(String[] args) {
		TMC st = new TMC();
		st.tcComSuiteBTestB();
		st.tcComSuiteCTestC();
		st.tcComSuiteATestA();
		st.printResult();
	}
}
