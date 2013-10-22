package tests;

import junit.framework.TestCase;
import avl.AVLImpl;
import avl.ArvoreAVL;

public class AVLTest extends TestCase {
	
	private ArvoreAVL avl;
	
	public void setUp(){
		avl = new AVLImpl();
	}
	
	public void testEstadoInicial(){	
		assertNull("Raiz deveria ser null", avl.getRaiz());
		assertEquals("Altura deveria ser 0", 1, avl.getAltura(avl.getRaiz()));
		assertEquals("Percursso em pre-ordem errado.", "",avl.percorrePreOrdem());
		assertEquals("Percursso em pos-ordem errado.", "",avl.percorrePosOrdem());
		assertFalse(avl.pesquisar(10));
	}
	
	public void testInsercao(){
		assertEquals("Nao inseriu 10", 0, avl.inserir(10));
		assertEquals("Nao inseriu 20", 0, avl.inserir(20));
		assertEquals("Nao inseriu 15", 0, avl.inserir(15));
		assertEquals("Nao inseriu 5", 0, avl.inserir(5));
		assertEquals("Nao inseriu 30", 0, avl.inserir(30));
				
		assertNotNull("Raiz deveria ser diferente de null", avl.getRaiz());
		assertEquals("Altura deveria ser 3",3, avl.getAltura(avl.getRaiz()));
		assertEquals("Percurso em pre-ordem errado.", "15 10 5 20 30",avl.percorrePreOrdem());
		assertEquals("Percurso em pos-ordem errado.", "5 10 30 20 15",avl.percorrePosOrdem());
		
		avl.inserir(17);
		
		assertNotNull(avl.getRaiz());
		assertEquals("Altura deveria ser 3", 2, avl.getAltura(avl.getRaiz()));
		assertEquals("Percurso em pre-ordem errado.", "15 10 5 20 17 30",avl.percorrePreOrdem());
		assertEquals("Percurso em pos-ordem errado.", "5 10 17 30 20 15",avl.percorrePosOrdem());
		
		avl.inserir(35);
		avl.inserir(40);
		
		assertNotNull(avl.getRaiz());
		assertEquals("Altura deveria ser 4", 5, avl.getAltura(avl.getRaiz()));
		assertEquals("Percurso em pre-ordem errado.", "15 10 5 20 17 35 30 40",avl.percorrePreOrdem());
		assertEquals("Percurso em pos-ordem errado.", "5 10 17 30 40 35 20 15",avl.percorrePosOrdem());
		
	}
	
	public void testInsercaoCondicoesLimite(){		
		avl.inserir(10);
		avl.inserir(20);
		avl.inserir(15);
		avl.inserir(5);
		avl.inserir(30);
		avl.inserir(17);
		
		//tenta inserir valor negativo
		assertEquals("Nao deveria ter inserido.", -1,avl.inserir(-10));
		//tenta inserir valor repetido
		assertEquals("Nao deveria ter inserido.", -1,avl.inserir(10));
		
		assertNotNull(avl.getRaiz());
		assertEquals("Altura deveria ser 3",2, avl.getAltura(avl.getRaiz()));
		assertEquals("Percurso em pre-ordem errado.", "15 10 5 20 17 30",avl.percorrePreOrdem());
		assertEquals("Percurso em pos-ordem errado.", "5 10 17 30 20 15",avl.percorrePosOrdem());
		
	}
	


	public void testAltura(){
		avl.inserir(10);
		avl.inserir(20);
		avl.inserir(15);
		avl.inserir(5);
		
		assertEquals("Altura deveria ser 3",3, avl.getAltura(avl.getRaiz()));
		assertEquals("Altura deveria ser 1",0, avl.getAltura(avl.getRaiz().getDireita()));
		assertEquals("Altura deveria ser 2",2, avl.getAltura(avl.getRaiz().getEsquerda()));
		
	}

	public void testPesquisa(){
		avl.inserir(10);
		avl.inserir(20);
		avl.inserir(15);
		avl.inserir(5);
		avl.inserir(30);
		avl.inserir(17);
		
		assertTrue("Deveria ter achado 20.",avl.pesquisar(20));
		assertTrue("Deveria ter achado 15.",avl.pesquisar(15));
	}
	

	public void testPesquisaCondicoesLimite(){
		avl.inserir(10);
		avl.inserir(20);
		avl.inserir(15);
		avl.inserir(5);
		avl.inserir(30);
		avl.inserir(17);	

		//pesquisa valor inexistente
		assertFalse("Nao deveria ter achado 40",avl.pesquisar(40));
	}
}
