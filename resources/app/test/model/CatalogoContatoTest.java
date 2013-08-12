package model;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
/**
 * <p>
 * 		Essa classe eh um teste para a classe <code>CatalogoContato</code>.
 * </p>
 * @author Samuel T. C. Santos
 * @version 1.0
 *
 */
public class CatalogoContatoTest {

	private CatalogoContatos catalogo;
	Contato c1;
	Contato c2;
	List<String> emails1;
	List<String> emails2;
	Telefone t1 , t2, t3, t4;
	
	@Before
	public void setUp() throws Exception {
		catalogo= new CatalogoContatos();
		
		c1 = new Contato("anderson",23,"...");
		c2 = new Contato("amanda", 22 , "...");
		
		emails1 = Arrays.asList("g1@gmail.com", "email@gmail.com");
		emails2 = Arrays.asList("e1@gmail.com","e2@gmail.com","e3@gmail.com");
		
		
		t1 = new Telefone("+55", "83", "31", "8993-9090", "CLARO", "31");
		t2 = new Telefone("+55", "45", "21", "2121-9090", "Embratel", "21");
		t3 = new Telefone("+55", "22", "11", "4343-9090", "NET", "11");
		t4 = new Telefone("+55", "22", "11", "3322-9090", "NET", "11");
		
	}

	@After
	public void tearDown() throws Exception {
		catalogo = null;
	}

	@Test
	public void testCatalogoVazio() {
		assertTrue(catalogo.isEmpty());
	}
	
	@Test
	public void testTotalDeEmails(){
		catalogo.adicionarEmails(c1, emails1);
		catalogo.adicionarEmails(c2, emails2);
		
		assertTrue( catalogo.getTotalDeEmails() == 5);
	}
	
	@Test
	public void testTotalDeContatos(){
		catalogo.adicionarEmails(c1, emails1);
		catalogo.adicionarEmails(c2, emails2);
		
		assertTrue( catalogo.getTotalDeContatos() == 2);
	}
	
	@Test
	public void testTotalDeTelefones(){
		catalogo.adicionarTelefones(c1, Arrays.asList(t1,t2));
		
		assertTrue( catalogo.getTotalDeTelefones() == 2);
	}
	
	@Test
	public void testCatalogoAdicionaContato(){
		
		catalogo.addContato(c1, Arrays.asList(t1,t2,t3), emails1);
		
		assertTrue(catalogo.getTotalDeContatos() == 1);
		assertTrue(catalogo.getTotalDeEmails() == 2);
		assertTrue(catalogo.getTotalDeTelefones() == 3);
	}
	
	@Test
	public void testGetListaDeContatos(){
		catalogo.addContato(c1, Arrays.asList(t1,t2,t3), emails1);
		catalogo.addContato(c2, Arrays.asList(t1), emails2);
		
		List<Contato> lista = catalogo.getContatos();
		
		assertTrue(lista.size() == 2);
	}
	
	
	@Test
	public void testGetListaDeEmails(){
		catalogo.addContato(c1, Arrays.asList(t1,t2,t3), emails1);
		catalogo.addContato(c2, Arrays.asList(t1), emails2);
		
		List<String> lista = catalogo.getEmails();
		
		assertTrue(lista.size() == 5);
	}
	
	@Test
	public void testGetListaDeTelefones(){
		catalogo.addContato(c1, Arrays.asList(t1,t2,t3), emails1);
		catalogo.addContato(c2, Arrays.asList(t1), emails2);
		
		List<Telefone> lista = catalogo.getTelefones();
		
		assertTrue(lista.size() == 4);
	}
	
	
	@Test
	public void testCatalogoGetContatoPorNome(){
		catalogo.addContato(c1, Arrays.asList(t1,t2,t3), emails1);
		catalogo.addContato(c2, Arrays.asList(t1), emails2);
		
		Contato c = catalogo.getContatoPorNome("amanda");
		
		assertTrue(c.getNome().equals("amanda"));
	}
	
	@Test
	public void testCatalogoGetMapEntryParaUmContato(){
		catalogo.addContato(c1, Arrays.asList(t1,t2,t3), emails1);
		catalogo.addContato(c2, Arrays.asList(t1), emails2);
		
		MapEntry entrada = catalogo.getEntry(c1);
		
		assertTrue(entrada.getTotalDeEmails() == 2);
		assertTrue(entrada.getTotalDeTelefones() == 3);
	}
	
}
