package core;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import org.junit.*;

import util.PathTo;

public class InstrumentAppTest2 {
	
	private InstrumentApp dir1, dir2, dir3, dir4, dir5, dir6, dir7, dir8, dir9, dir10;
	private List<String> list;
	private int contaWPAs;
	
	//package classesMain
	private InstrumentClass jogoDaVelha;
	private InstrumentClass vinteUm;
	private InstrumentClass explorandoMS;
	private InstrumentClass meuSDT;
	
	//package classesJava
	private InstrumentClass fibonacci;
	private InstrumentClass pa;
	private InstrumentClass robo;
	private InstrumentClass sala;
	
	//package classesTests	
	private InstrumentClass roboT;
	private InstrumentClass salaT;
	private InstrumentClass cdT;
	private InstrumentClass cdtecaT;
	
	//package classesInterface
	private InstrumentClass dis;
	private InstrumentClass fg;
	private InstrumentClass priorj;
	private InstrumentClass priorjSys;
	
	//package classesPriorJ
	private InstrumentClass priorjFac;
	private InstrumentClass priorjImpl;
	private InstrumentClass priorjSysImpl;
	
	//package classesAbstracts
	private InstrumentClass cont;
	private InstrumentClass rod;
	private InstrumentClass trab;
	
	//package classesException
	private InstrumentClass dneEx;
	private InstrumentClass epEx;
	private InstrumentClass iuEx;
	
	//package variosPacotes
	private InstrumentClass cont2; //package1
	private InstrumentClass fib2;  //package1
	private InstrumentClass pa2;   //package2
	private InstrumentClass rod2;  //package2
	private InstrumentClass trab2; //package3
	
	//package classesMixed
	private InstrumentClass cd;
	private InstrumentClass tCD;
	private InstrumentClass tAbs;
	private InstrumentClass inter;	
	
	@Before
	public void createObjects() {
		//classesMain
		dir1 = new InstrumentApp(PathTo.RESOURCES_FILES + "/InstrumentTests/classes/classesMain", PathTo.RESOURCES_FILES + "/InstrumentTests/classes/tests");
		jogoDaVelha = new InstrumentClass(PathTo.RESOURCES_FILES + "/InstrumentTests/classes/classesMain", "/JogoDaVelhaModularizado.java");
		vinteUm = new InstrumentClass(PathTo.RESOURCES_FILES + "/InstrumentTests/classes/classesMain", "/VinteUmJogo.java");
		explorandoMS = new InstrumentClass(PathTo.RESOURCES_FILES + "/InstrumentTests/classes/classesMain", "/ExplorandoOMundoDasSeries.java");
		meuSDT = new InstrumentClass(PathTo.RESOURCES_FILES + "/InstrumentTests/classes/classesMain", "/MeuSistemaDeTributacao.java");
	
		//classesJava
		dir2 = new InstrumentApp(PathTo.RESOURCES_FILES + "/InstrumentTests/classes/classesJava", PathTo.RESOURCES_FILES + "/InstrumentTests/classes/tests");
		fibonacci = new InstrumentClass(PathTo.RESOURCES_FILES + "/InstrumentTests/classes/classesJava", "/Fibonacci.java");
		pa = new InstrumentClass(PathTo.RESOURCES_FILES + "/InstrumentTests/classes/classesJava", "/ProgressaoAritmetica.java");
		robo = new InstrumentClass(PathTo.RESOURCES_FILES + "/InstrumentTests/classes/classesJava", "/Robo.java");
		sala = new InstrumentClass(PathTo.RESOURCES_FILES + "/InstrumentTests/classes/classesJava", "/Sala.java");
		
		//classesTests
		dir3 = new InstrumentApp(PathTo.RESOURCES_FILES + "/InstrumentTests/classes/classesTest", PathTo.RESOURCES_FILES + "/InstrumentTests/classes/tests");
		roboT = new InstrumentClass(PathTo.RESOURCES_FILES + "/InstrumentTests/classes/classesTests", "/TestaRobo.java");
		salaT = new InstrumentClass(PathTo.RESOURCES_FILES + "/InstrumentTests/classes/classesTests", "/TestaSala.java");
		cdtecaT = new InstrumentClass(PathTo.RESOURCES_FILES + "/InstrumentTests/classes/classesTests", "/CDtecaTest.java");
		cdT = new InstrumentClass(PathTo.RESOURCES_FILES + "/InstrumentTests/classes/classesTests", "/TestCD.java");
		
		//classesException
		dir4 = new InstrumentApp(PathTo.RESOURCES_FILES + "/InstrumentTests/classes/classesException", PathTo.RESOURCES_FILES + "/InstrumentTests/classes/tests");
		dneEx = new InstrumentClass(PathTo.RESOURCES_FILES + "/InstrumentTests/classes/classesException", "/DirectoryNotExistException.java");
		epEx = new InstrumentClass(PathTo.RESOURCES_FILES + "/InstrumentTests/classes/classesException", "/EmptyPathExcecao.java");
		iuEx = new InstrumentClass(PathTo.RESOURCES_FILES + "/InstrumentTests/classes/classesException", "/InstrumentationUnrealized.java");
		
		//classesMixed
		dir5 = new InstrumentApp(PathTo.RESOURCES_FILES + "/InstrumentTests/classes/classesMixed", PathTo.RESOURCES_FILES + "/InstrumentTests/classes/tests");
		cd = new InstrumentClass(PathTo.RESOURCES_FILES + "/InstrumentTests/classes/classesMixed", "/CD.java");
		tCD = new InstrumentClass(PathTo.RESOURCES_FILES + "/InstrumentTests/classes/classesMixed", "/TestaCD.java");
		tAbs = new InstrumentClass(PathTo.RESOURCES_FILES + "/InstrumentTests/classes/classesMixed", "/TaxiAbstract.java");
		inter = new InstrumentClass(PathTo.RESOURCES_FILES + "/InstrumentTests/classes/classesMixed", "/Interface.java");
		
		//classesWithoutClass
		dir6 = new InstrumentApp(PathTo.RESOURCES_FILES + "/InstrumentTests/classes/classesWithoutClass", PathTo.RESOURCES_FILES + "/InstrumentTests/classes/tests");
		
		//classesPriorJ
		dir7 = new InstrumentApp(PathTo.RESOURCES_FILES + "/InstrumentTests/classes/classesPriorJ", PathTo.RESOURCES_FILES + "/InstrumentTests/classes/tests");
		priorjFac = new InstrumentClass(PathTo.RESOURCES_FILES + "/InstrumentTests/classes/classesPriorJ", "/PriorJFacade.java");
		priorjImpl = new InstrumentClass(PathTo.RESOURCES_FILES + "/InstrumentTests/classes/classesPriorJ", "/PriorJImpl.java");
		priorjSysImpl = new InstrumentClass(PathTo.RESOURCES_FILES + "/InstrumentTests/classes/classesPriorJ", "/PriorJSystemImpl.java");
		
		//classesInterface
		dir8 = new InstrumentApp(PathTo.RESOURCES_FILES + "/InstrumentTests/classes/classesInterface", PathTo.RESOURCES_FILES + "/InstrumentTests/classes/tests");
		dis = new InstrumentClass(PathTo.RESOURCES_FILES + "/InstrumentTests/classes/classesInterface", "/Disciplinas.java");
		fg = new InstrumentClass(PathTo.RESOURCES_FILES + "/InstrumentTests/classes/classesInterface", "/FigurasGeometricas.java");
		priorj = new InstrumentClass(PathTo.RESOURCES_FILES + "/InstrumentTests/classes/classesInterface", "/PriorJ.java");
		priorjSys = new InstrumentClass(PathTo.RESOURCES_FILES + "/InstrumentTests/classes/classesInterface", "/PriorJSystem.java");
		
		//classesAbstracts
		dir9 = new InstrumentApp(PathTo.RESOURCES_FILES + "/InstrumentTests/classes/classesAbstracts", PathTo.RESOURCES_FILES + "/InstrumentTests/classes/tests");
		cont = new InstrumentClass(PathTo.RESOURCES_FILES + "/InstrumentTests/classes/classesAbstracts", "/Contribuinte.java");
		rod = new InstrumentClass(PathTo.RESOURCES_FILES + "/InstrumentTests/classes/classesAbstracts", "/Rodoviario.java");
		trab = new InstrumentClass(PathTo.RESOURCES_FILES + "/InstrumentTests/classes/classesAbstracts", "/Trabalhadores.java");
		
		/*
		 * variosPacotes
		 * package1, package2 e package3
		 */
		dir10 = new InstrumentApp(PathTo.RESOURCES_FILES + "/InstrumentTests/variosPacotes", PathTo.RESOURCES_FILES + "/InstrumentTests/classes/tests");
		cont2 = new InstrumentClass(PathTo.RESOURCES_FILES + "/InstrumentTests/variosPacotes/package1", "/Contribuinte2.java");
		rod2 = new InstrumentClass(PathTo.RESOURCES_FILES + "/InstrumentTests/variosPacotes/package2", "/Rodoviario2.java");
		trab2 = new InstrumentClass(PathTo.RESOURCES_FILES + "/InstrumentTests/variosPacotes/package3", "/Trabalhadores2.java");
		fib2 = new InstrumentClass(PathTo.RESOURCES_FILES + "/InstrumentTests/variosPacotes/package1", "/Fibonacci2.java");
		pa2 = new InstrumentClass(PathTo.RESOURCES_FILES + "/InstrumentTests/variosPacotes/package2", "/ProgressaoAritmetica2.java");
		
		
	}
	
	/*
	 * Test classes Java with main.
	 * dir1 --> Settings.RESOURCES_FILES + /InstrumentTests/classes/classesMain
	 */
	@Test
	public void testCMain() throws Exception {
		dir1.run();
		
		//JogoDaVelaha
		FoundWPA(jogoDaVelha.getPathFile() + jogoDaVelha.getNameFile(), jogoDaVelha.getNameField());
		ContaWPAs();
		assertTrue(contaWPAs == 91);
		
		//VinteUmJogo
		FoundWPA(vinteUm.getPathFile() + vinteUm.getNameFile(), vinteUm.getNameField());
		ContaWPAs();
		assertTrue(contaWPAs == 72);
		
		//ExplorandoOMundoDasSeries
		FoundWPA(explorandoMS.getPathFile() + explorandoMS.getNameFile(), explorandoMS.getNameField());
		ContaWPAs();
		assertTrue(contaWPAs == 37);
		
		//MeuSistemaDeTributacao
		FoundWPA(meuSDT.getPathFile() + meuSDT.getNameFile(), meuSDT.getNameField());
		ContaWPAs();
		assertTrue(contaWPAs == 24);
	}
	
	/*
	 * Test classes Java without Main.
	 * dir2 --> Settings.RESOURCES_FILES + /InstrumentTests/classes/classesJava
	 */	
	@Test
	public void testCJava() throws Exception {
		dir2.run();
		
		//Fibonacci
		FoundWPA(fibonacci.getPathFile() + fibonacci.getNameFile(), fibonacci.getNameField());
		ContaWPAs();
		assertTrue(contaWPAs == 45);
		
		//ProgressaoAritmetica
		FoundWPA(pa.getPathFile() + pa.getNameFile(), pa.getNameField());
		ContaWPAs();
		assertTrue(contaWPAs == 22);
		
		//Robo
		FoundWPA(robo.getPathFile() + robo.getNameFile(), robo.getNameField());
		ContaWPAs();
		assertTrue(contaWPAs == 69);
		
		//Sala
		FoundWPA(sala.getPathFile() + sala.getNameFile(), sala.getNameField());
		ContaWPAs();
		assertTrue(contaWPAs == 26);
	}
	
	/*
	 * Test classes Tests.
	 * dir3 --> Settings.RESOURCES_FILES + /InstrumentTests/classes/classesTests
	 */
	@Test
	public void testCTests() throws Exception {
		dir3.run();
		
		//TestaRobo
		FoundWPA(roboT.getPathFile() + roboT.getNameFile(), roboT.getNameField());
		ContaWPAs();
		assertTrue(contaWPAs == 0);
		
		//TestaSala
		FoundWPA(salaT.getPathFile() + salaT.getNameFile(), salaT.getNameField());
		ContaWPAs();
		assertTrue(contaWPAs == 0);
		
		//CDtecaTest
		FoundWPA(cdtecaT.getPathFile() + cdtecaT.getNameFile(), cdtecaT.getNameField());
		ContaWPAs();
		assertTrue(contaWPAs == 0);
			
		//TestCD
		FoundWPA(cdT.getPathFile() + cdT.getNameFile(), cdT.getNameField());
		ContaWPAs();
		assertTrue(contaWPAs == 0);
	}
	
	/*
	 * Test classes Exceptions.
	 * dir4 --> Settings.RESOURCES_FILES + /InstrumentTests/classes/classesException
	 */
	@Test
	public void testCException() throws Exception {
		dir4.run();
		
		//DirectoryNotExistException
		FoundWPA(dneEx.getPathFile() + dneEx.getNameFile(), dneEx.getNameField());
		ContaWPAs();
		assertTrue(contaWPAs == 0);
		
		//EmptyPathExcecao
		FoundWPA(epEx.getPathFile() + epEx.getNameFile(), epEx.getNameField());
		ContaWPAs();
		assertTrue(contaWPAs == 0);
				
		//InstrumentationUnrealized
		FoundWPA(iuEx.getPathFile() + iuEx.getNameFile(), iuEx.getNameField());
		ContaWPAs();
		assertTrue(contaWPAs == 0);
	}
	
	/*
	 * Test classes Mixed.
	 * dir5 --> Settings.RESOURCES_FILES + /InstrumentTests/classes/classesMixed
	 */
	@Test
	public void testCMixed() throws Exception {
		dir5.run();
		
		//CD
		FoundWPA(iuEx.getPathFile() + iuEx.getNameFile(), iuEx.getNameField());
		ContaWPAs();
		assertTrue(iuEx.getWatchNumber() == 0);
		
		//TestaCD
		FoundWPA(tCD.getPathFile() + tCD.getNameFile(), tCD.getNameField());
		ContaWPAs();
		assertTrue(tCD.getWatchNumber() == 0);
		
		//TaxiAbstract
		FoundWPA(tAbs.getPathFile() + tAbs.getNameFile(), tAbs.getNameField());
		ContaWPAs();
		assertTrue(tAbs.getWatchNumber() == 9);
		
		//Interface
		FoundWPA(inter.getPathFile() + inter.getNameFile(), inter.getNameField());
		ContaWPAs();
		assertTrue(inter.getWatchNumber() == 0);
	}
	
	/*
	 * Test classes without class.
	 * dir1 --> Settings.RESOURCES_FILES + /InstrumentTests/classes/classesWithoutClass
	 */
	@Test
	public void testCWithoutClass() throws Exception {
		dir6.run();
	}
	
	/*
	 * Test classes PriorJ.
	 * dir7 --> Settings.RESOURCES_FILES + /InstrumentTests/classes/classesPriorJ
	 */
	@Test
	public void testCPriorJ() throws Exception {
		dir7.run();
		
		//PriorJFacade
		FoundWPA(priorjFac.getPathFile() + priorjFac.getNameFile(), priorjFac.getNameField());
		ContaWPAs();
		assertTrue(contaWPAs == 0);
		
		//PriorJImpl
		FoundWPA(priorjImpl.getPathFile() + priorjImpl.getNameFile(), priorjImpl.getNameField());
		ContaWPAs();
		assertTrue(contaWPAs == 0);
		
		//PriorJSystemImpl
		FoundWPA(priorjSysImpl.getPathFile() + priorjSysImpl.getNameFile(), priorjSysImpl.getNameField());
		ContaWPAs();
		assertTrue(contaWPAs == 0);
	}
	
	/*
	 * Test classes just interface.
	 * dir8 --> Settings.RESOURCES_FILES + /InstrumentTests/classes/classesInterface
	 */
	@Test
	public void testCInterface() throws Exception {
		dir8.run();
		
		//Disciplinas
		FoundWPA(dis.getPathFile() + dis.getNameFile(), dis.getNameField());
		ContaWPAs();
		assertTrue(contaWPAs == 0);
				
		//FigurasGeometricas
		FoundWPA(fg.getPathFile() + fg.getNameFile(), fg.getNameField());
		ContaWPAs();
		assertTrue(contaWPAs == 0);
			
		//PriorJ
		FoundWPA(priorj.getPathFile() + priorj.getNameFile(), priorj.getNameField());
		ContaWPAs();
		assertTrue(contaWPAs == 0);
				
		//PriorJSystem
		FoundWPA(priorjSys.getPathFile() + priorjSys.getNameFile(), priorjSys.getNameField());
		ContaWPAs();
		assertTrue(contaWPAs == 0);
	}
	
	/*
	 * Test classes just abstracts.
	 * dir9 --> Settings.RESOURCES_FILES + /InstrumentTests/classes/classesAbstracts
	 */
	@Test
	public void testCAbstract() throws Exception {
		dir9.run();
		
		//Contribuinte
		FoundWPA(cont.getPathFile() + cont.getNameFile(), cont.getNameField());
		ContaWPAs();
		assertTrue(contaWPAs == 22);
	
		//Rodoviario
		FoundWPA(rod.getPathFile() + rod.getNameFile(), rod.getNameField());
		ContaWPAs();
		assertTrue(contaWPAs == 8);
		
		//Trabalhadores
		FoundWPA(trab.getPathFile() + trab.getNameFile(), trab.getNameField());
		ContaWPAs();
		assertTrue(contaWPAs == 15);
	}
	
	/*
	 * Test varios pacotes no mesmo diretorio
	 * dir10 --> Settings.RESOURCES_FILES + /InstrumentTests/variosPacotes
	 */
	@Test
	public void testVariosPacotes() throws Exception {
		dir10.run();
		
		/*
		 * package1
		 * Contribuinte2
		 * Fibonacci2
		 */
		FoundWPA(cont2.getPathFile() + cont2.getNameFile(), cont2.getNameField());
		ContaWPAs();
		assertTrue(contaWPAs == 22);		
		FoundWPA(fib2.getPathFile() + fib2.getNameFile(), fib2.getNameField());
		ContaWPAs();
		assertTrue(contaWPAs == 39);
		
		/*
		 * package2
		 * Rodoviario2
		 * ProgressaoAritmetica2
		 */
		FoundWPA(rod2.getPathFile() + rod2.getNameFile(), rod2.getNameField());
		ContaWPAs();
		assertTrue(contaWPAs == 7);		
		FoundWPA(pa2.getPathFile() + pa2.getNameFile(), pa2.getNameField());
		ContaWPAs();
		assertTrue(contaWPAs == 19);
		
		/*
		 * package3
		 * Trabalhadores2
		 */
		FoundWPA(trab2.getPathFile() + trab2.getNameFile(), trab2.getNameField());
		ContaWPAs();
		assertTrue(contaWPAs == 14);
	}
	
	private void ContaWPAs() {
		contaWPAs = list.size();
	}

	private void FoundWPA(String fileName, String nameField) {
		String nameFile = fileName;
		list = new LinkedList<String>();	        
	    try {
	    	BufferedReader in = new BufferedReader(new FileReader(nameFile));
	        String str;
	        while (in.ready()) {
	        	str = in.readLine();
	            if (str.contains(nameField)){
	            	list.add(nameField);
	                }
	            }
	        in.close();
	    } catch (IOException e) {
	    System.out.println("Read file and return list error: " + e.getMessage());
	    }	
	}
}