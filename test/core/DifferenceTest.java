package core;

import static org.junit.Assert.*;

import org.junit.*;

import util.Settings;

public class DifferenceTest {
	
	private Difference difference;
	private Difference difference2;
	private Difference difference3;
	private Difference difference4;
	private Difference difference5;
	private Difference difference6;
	private Difference difference7;
	private Difference difference8;
	private Difference difference9;
	private Difference difference10;
	
	@Before
	public void criaObjs() {
//		difference = new Difference(Settings.DIFFERENCE_TESTS_NEW + "/VinteUm.java", Settings.DIFFERENCE_TESTS_OLD + "/VinteUm.java");
//		difference2 = new Difference(Settings.DIFFERENCE_TESTS_NEW + "/CConstrutor.java", Settings.DIFFERENCE_TESTS_OLD + "/CConstrutor.java");
//		difference3 = new Difference(Settings.DIFFERENCE_TESTS_NEW + "/CDteca1.java", Settings.DIFFERENCE_TESTS_OLD + "/CDteca1.java");
//		difference4 = new Difference(Settings.DIFFERENCE_TESTS_NEW + "/CInterface.java", Settings.DIFFERENCE_TESTS_OLD + "/CInterface.java");
//		difference5 = new Difference(Settings.DIFFERENCE_TESTS_NEW + "/CInterface2.java", Settings.DIFFERENCE_TESTS_OLD + "/CInterface2.java");
//		difference6 = new Difference(Settings.DIFFERENCE_TESTS_NEW + "/CDoWhile.java", Settings.DIFFERENCE_TESTS_OLD + "/CDoWhile.java");
	}
	
	//Test difference between VinteUm(New) and VinteUm(Old)
	//Obs.: Sem diferenca
	//OK!
	@Test
	public void testDifferenceVinteUm() {
		difference.diff();
		//System.out.println(difference.getStatementsDiff());
		assertTrue(difference.getStatementsDiff().toString().equals("[]"));
	}
	
	//Test difference between CConstrutor(New) and CConstrutor(Old)
	//Obs.: diferença no construtor
	@Test
	public void testDifferenceCConstrutor() {
		difference2.diff();
		System.out.println(difference2.getStatementsDiff());
		assertTrue(difference2.getStatementsDiff().toString().equals("[lp2.lab07.Construtor.Construtor().14, " +
				"lp2.lab07.Construtor.Construtor().15, lp2.lab07.Construtor.Construtor().16, lp2.lab07.Construtor.Construtor().17, " +
				"lp2.lab07.Construtor.Construtor().18, lp2.lab07.Construtor.Construtor().19, lp2.lab07.Construtor.Construtor().20]"));
	}
	
	//Test difference between CDteca1(New) and CDteca1(Old)
	//Obs.: troca de i por j no for e no if, teve "auteração no return"
	@Test
	public void testDifferenceCDteca1() {
		difference3.diff();
		//System.out.println(difference3.getStatementsDiff());
		assertTrue(difference3.getStatementsDiff().toString().equals("[lp2.lab07.MinhaCDteca.MinhaCDteca().10, " +
				"lp2.lab07.MinhaCDteca.MinhaCDteca().11, lp2.lab07.MinhaCDteca.removeCD().20, lp2.lab07.MinhaCDteca.removeCD().21, " +
				"lp2.lab07.MinhaCDteca.removeCD().22, lp2.lab07.MinhaCDteca.removeCD().23, lp2.lab07.MinhaCDteca.removeCD().26]"));
	}
	
	//Test difference between CDoWhile(New) and CDoWhile(Old)
	//Obs.:
	//
	@Test
	public void testDifferenceCDoWhile() {
		difference6.diff();
		System.out.println(difference6.getStatementsDiff());
		//assertTrue(difference6.getStatementsDiff().toString().equals("[]"));
	}
	
	//Test difference between CInterface(New) and CInterface(Old)
		//Obs.: troca do package, troca do atributo de um metodo (não precisa pegar)
		//FAIL!
//		@Test
//		public void testDifferenceCInterface() {
//			difference4.diff();
//			//System.out.println(difference4.getStatementsDiff());
//			assertFalse(difference4.getStatementsDiff().toString().equals("[]"));
//		}
		
		//Test difference between CInterface2(New) and CInterface2(Old)
		//Obs.: remoção de um metodo (deve falhar por NullPointerExeption)
		//FAIL!
//		@Test
//		public void testDifferenceCInterface2() {
//			difference5.diff();
//			//System.out.println(difference5.getStatementsDiff());
//			assertTrue(difference5.getStatementsDiff().toString().equals("[]"));
//		}
}
