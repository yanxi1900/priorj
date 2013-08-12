package model;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


/**
 * <p>
 * 		Esse teste eh um tete para classe <code>model.Contato</code> .</>
 * </p>
 * @author Samuel T. C. Santos
 * @version 1.0
 */
public class ContatoTest {

	Contato contato;

	@Before
	public void setUp(){
		contato = new Contato();
	}
	
	@After
	public void tearDown(){
		contato = null;
	}
	
	@Test
	public void testCriaDefaultContato(){
		assertTrue(contato.isEmpty());
	}
	
	@Test
	public void testCriaContatoUsandoConstrutor(){
		contato = new Contato("samuel", 25, "primeiro contato");
		
		assertFalse(contato.isEmpty());
		assertEquals("samuel", contato.getNome());
		assertTrue( contato.getIdade() == 25);
		assertEquals("primeiro contato", contato.getDescricao());
	}
	
	@Test
	public void testContatoVaziosSaoIguais(){
		Contato c1 = new Contato();
		Contato c2 = new Contato();
		
		assertTrue(c1.equals(c2));
	}
	
	@Test
	public void testContatosNaoVaziosComDadosDiferenteSaoDiferentes(){
		Contato c1 = new Contato("k2", 12, "...");
		Contato c2 = new Contato("joao", 23, "...");
		
		assertFalse(c1.equals(c2));
	}
	
	@Test
	public void testContatosNaoVaziosComDadosIguaisSaoEquais(){
		Contato c1 = new Contato("k2", 12, "...");
		Contato c2 = new Contato("k2", 12, "...");
		
		assertTrue(c1.equals(c2));
	}
	
	
}
