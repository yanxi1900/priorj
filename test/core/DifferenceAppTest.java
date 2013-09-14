package core;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.*;

import util.PathTo;

public class DifferenceAppTest {
	
	private DifferenceApp dir1;
	private DifferenceApp dir2;
	
	private Difference cConstructor;
	private Difference cDoWhile;
	private Difference CDteca1;
	private Difference vinteUm;
	
	@Before
	public void criaObjs() {
		
//		dir1 = new DifferenceApp(Settings.DIFFERENCEAPP_TESTS_NEW, Settings.DIFFERENCEAPP_TESTS_OLD);
//		dir2 = new DifferenceApp(Settings.DIFFERENCEAPP_TESTS_NEW_WP, Settings.DIFFERENCEAPP_TESTS_OLD_WP);
//		
//		cConstructor = new Difference(Settings.DIFFERENCE_TESTS_NEW + "/CConstrutor.java", Settings.DIFFERENCE_TESTS_OLD + "/CConstrutor.java");
//		cDoWhile = new Difference(Settings.DIFFERENCE_TESTS_NEW + "/CDoWhile.java", Settings.DIFFERENCE_TESTS_OLD + "/CDoWhile.java");
//		CDteca1 = new Difference(Settings.DIFFERENCE_TESTS_NEW + "/CDteca1.java", Settings.DIFFERENCE_TESTS_OLD + "/CDteca1.java");
//		vinteUm = new Difference(Settings.DIFFERENCE_TESTS_NEW + "/VinteUm.java", Settings.DIFFERENCE_TESTS_OLD + "/VinteUm.java");
	}
	
	@Test
	public void testDifferenceApp() throws Exception {
		
		List<String> differencesList = differences();
		dir1.run();
//		System.out.println(dir1.getListDiff());
//		System.out.println();
//		System.out.println(differencesList);
		assertTrue(dir1.getListDiff().equals(differencesList));
	}
	
	@Test
	public void testDifferenceAppWithPackages() throws Exception {
		
		List<String> differencesList = differences();
		dir2.run();
//		System.out.println(dir2.getListDiff());
//		System.out.println();
//		System.out.println(differencesList);
		assertTrue(dir2.getListDiff().equals(differencesList));
	}
	
	@Test
	public void testGetListDiff() throws Exception {
		
		dir1.run();
		assertTrue(dir1.getListDiff().equals(differences()));		
	}

	private List<String> differences() {
		
		List<String> lista = new ArrayList<String>();
		
		CDteca1.diff();
		cDoWhile.diff();
		cConstructor.diff();
		vinteUm.diff();
		
		lista.addAll(vinteUm.getStatementsDiff());
		lista.addAll(CDteca1.getStatementsDiff());
		lista.addAll(cConstructor.getStatementsDiff());
		lista.addAll(cDoWhile.getStatementsDiff());
		return lista;
	}
}
