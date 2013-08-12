package model;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * <p>
 * 		Essa classe representa a suite de teste para a classe <code>CatalogoUsuarios</code>.
 * </p>
 * 
 * @author Samuel T. C. Santos
 * @version 1.0
 *
 */
public class CatalogoUsuariosTest {

	CatalogoUsuarios catalogo;
	
	@Before
	public void setUp() throws Exception {
		catalogo = new CatalogoUsuarios();
	}

	@After
	public void tearDown() throws Exception {
		catalogo = null;
	}
	

	@Test
	public void testInicializacao() {
		assertNotNull(catalogo);
	}
	
	@Test
	public void testAdicionaUsuario(){
		catalogo.adiciona(new Usuario("usario","123456"), new CatalogoContatos());
	
		assertEquals(1, catalogo.totalDeUsuarios());
	}
	
	@Test
	public void testBuscaNoCatalogoUsuarioPadraoTrue(){
		catalogo.adiciona(new Usuario("usuario","123456"), new CatalogoContatos());
		
		assertTrue(catalogo.existeUsuario("usuario", "123456"));
	}
	
	@Test
	public void testBuscaNoCatalogoUsuarioPadraoFalse(){
		catalogo.adiciona(new Usuario("usuarioqualquer","123456"), new CatalogoContatos());
		
		assertFalse(catalogo.existeUsuario("usuario", "123456"));
	}
	
	@Test
	public void testListarUsuarios(){
		catalogo.adiciona(new Usuario("a","123456"), new CatalogoContatos());
		catalogo.adiciona(new Usuario("b","123456"), new CatalogoContatos());
		catalogo.adiciona(new Usuario("c","123456"), new CatalogoContatos());
		catalogo.adiciona(new Usuario("d","123456"), new CatalogoContatos());
		catalogo.adiciona(new Usuario("e","123456"), new CatalogoContatos());
		
		List<Usuario> lista = catalogo.listarUsuarios();
		
		assertTrue(lista.size() == 5);
	}
	
	
	@Test
	public void testCatalogoNaoAdicionaUsuarioDuplicado(){
		Usuario u = new Usuario("usuario","123");
		
		catalogo.adiciona(u, new CatalogoContatos());
		catalogo.adiciona(u, new CatalogoContatos());
		catalogo.adiciona(u, new CatalogoContatos());
		
		assertEquals(1, catalogo.totalDeUsuarios());
	}
	
	@Test
	public void testCatalogoGetUsuario(){
		Usuario a = new Usuario("a","*121");
		catalogo.adiciona(a, new CatalogoContatos());
		
		Usuario b = new Usuario("a","*121*");
		catalogo.adiciona(b, new CatalogoContatos());
		
		assertEquals(a, catalogo.getUsuario("a", "*121"));
	}
	
	@Test
	public void testCatalogoNaoEncontraUsuarioRetornaVazio(){
		Usuario a = new Usuario("a","*121");
		catalogo.adiciona(a, new CatalogoContatos());
		
		Usuario pesquisado =catalogo.getUsuario("ab", "*121*"); 
		
		assertEquals(true, pesquisado.isEmpty());
	}
	
	@Test
	public void testCatalogoRemoveUsuario(){
		Usuario ana = new Usuario("ana","*121");
		catalogo.adiciona(ana, new CatalogoContatos());
	
		Usuario joaozinho = new Usuario("joaozinho","*111");
		catalogo.adiciona(joaozinho, new CatalogoContatos());
	
		catalogo.remove("ana", "*121");
		
		assertTrue(catalogo.existeUsuario("joaozinho", "*111"));
		assertFalse(catalogo.existeUsuario("ana", "*121"));
	}
	
	
	

}
