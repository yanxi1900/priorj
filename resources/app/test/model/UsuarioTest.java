package model;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * <p>
 *  Essa classe eh um teste para classe usuário.
 * </p>
 * @author Samuel T. C. Santos
 * @version 1.0
 *
 */
public class UsuarioTest {

	Usuario usuario;
	
	@Before
	public void setUp(){
		usuario = new Usuario();
	}
	
	@After
	public void tearDown(){
		usuario = null;
	}
	
	
	@Test
	public void testCriaUsuarioComNomeSenha(){
		usuario = new Usuario("samuel","123");
		
		assertEquals("samuel", usuario.getNomeUsuario());
		assertEquals("123", usuario.getSenha());
	}
	
	@Test
	public void testUsuarioNaoLogadoPorPadrao(){
		usuario = new Usuario("samuel","123");
		
		assertFalse(usuario.isLogado());
	}
	
	@Test
	public void testLogarUsuario(){
		usuario = new Usuario("samuel","123");
		
		usuario.logar();
		
		assertTrue(usuario.isLogado());
	}
	
	@Test
	public void testDeslogarUsuario(){
		usuario = new Usuario("samuel","123");
		
		usuario.logar();
		
		assertTrue(usuario.isLogado());
		
		usuario.deslogar();
		
		assertFalse(usuario.isLogado());
	}
	
	@Test
	public void testUsuarioVazio(){
		assertTrue (usuario.isEmpty());
	}
	
	@Test
	public void testUsuarioNaoVazio(){
		usuario.setNomeUsuario("a");
		usuario.setSenha("x*x.x1");
		assertFalse(usuario.isEmpty());
	}
	
	
}
