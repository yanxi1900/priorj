package model;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * <p>
 * 		Essa classe eh uma classe de teste para a classe <code>Telefone</code>.
 * </p>
 * @author Samuel T. C. Santos
 * @version 1.0
 *
 */
public class TelefoneTest {

	private Telefone telefone;
	
	@Before
	public void setUp() throws Exception {
		telefone = new Telefone();
	}

	@After
	public void tearDown() throws Exception {
		telefone = null;
	}

	@Test
	public void testTelefoneVazio() {
		assertTrue(telefone.isEmpty());
	}
	
	@Test
	public void testTelefoneInvalido(){
		assertFalse(telefone.isValido());
	}

	@Test
	public void testCriaTelefoneValido(){
		telefone = new Telefone("+55", "83", "31", "9999-9999","CLARO", "31");
		
		assertTrue(telefone.isValido());
	}
	
}
