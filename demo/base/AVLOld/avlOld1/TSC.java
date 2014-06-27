package tests;
import junit.framework.TestCase;
import junit.framework.TestResult;
public class TSC {
	private int failureCount = 0;
	private int runCount = 0;
	private int errorCount = 0;
	public void run(){
		TestCase tcTestsAVLTestTestInsercaoCondicoesLimite = null;
		tcTestsAVLTestTestInsercaoCondicoesLimite = new tests.AVLTest(){
			public void runTest(){
				try{
					testInsercaoCondicoesLimite();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		};
		setResult("testInsercaoCondicoesLimite",tcTestsAVLTestTestInsercaoCondicoesLimite.run());
		TestCase tcTestsAVLTestTestInsercao = null;
		tcTestsAVLTestTestInsercao = new tests.AVLTest(){
			public void runTest(){
				try{
					testInsercao();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		};
		setResult("testInsercao",tcTestsAVLTestTestInsercao.run());
		TestCase tcTestsAVLTestTestAltura = null;
		tcTestsAVLTestTestAltura = new tests.AVLTest(){
			public void runTest(){
				try{
					testAltura();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		};
		setResult("testAltura",tcTestsAVLTestTestAltura.run());
		TestCase tcTestsAVLTestTestPesquisa = null;
		tcTestsAVLTestTestPesquisa = new tests.AVLTest(){
			public void runTest(){
				try{
					testPesquisa();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		};
		setResult("testPesquisa",tcTestsAVLTestTestPesquisa.run());
		TestCase tcTestsAVLTestTestPesquisaCondicoesLimite = null;
		tcTestsAVLTestTestPesquisaCondicoesLimite = new tests.AVLTest(){
			public void runTest(){
				try{
					testPesquisaCondicoesLimite();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		};
		setResult("testPesquisaCondicoesLimite",tcTestsAVLTestTestPesquisaCondicoesLimite.run());
		TestCase tcTestsAVLTestTestEstadoInicial = null;
		tcTestsAVLTestTestEstadoInicial = new tests.AVLTest(){
			public void runTest(){
				try{
					testEstadoInicial();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		};
		setResult("testEstadoInicial",tcTestsAVLTestTestEstadoInicial.run());
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
		TSC st = new TSC();
		st.tcTestsAVLTestTestInsercaoCondicoesLimite();
		st.tcTestsAVLTestTestInsercao();
		st.tcTestsAVLTestTestAltura();
		st.tcTestsAVLTestTestPesquisa();
		st.tcTestsAVLTestTestPesquisaCondicoesLimite();
		st.tcTestsAVLTestTestEstadoInicial();
		st.printResult();
	}
}
