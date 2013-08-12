package controller;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;

import model.CatalogoContatos;
import model.MapEntry;
import model.Telefone;
import model.Usuario;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import controller.Agenda;

/**
 * <p>
 *   Essa classe testa as funcionalidades da classe <code>Agenda</code>.
 * 
 * </p>
 * @author Samuel T. C. Santos
 * @version 1.0
 *
 */
public class AgendaTest {
	
	Agenda agenda;
	
	@Before
	public void setUp() {
		agenda = new Agenda();
	}
	
	@After
	public void tearDown(){
		agenda.limparBaseDeDados();
		agenda = null;
	}
	
	@Test
	public void testCondicaoInicialAgendaComBaseDeDadosVazia(){
		assertTrue(agenda.isEmpty());
	}
	
	@Test
	public void testAgendaConsultaTamanhoInicialDaBaseDeDados(){
		assertTrue(agenda.consultarTamanhoDaBaseDeDados() == 0);
	}
	
	@Test
	public void testAgendaAdicionaDadoNaBaseDeDados(){
		agenda.adicionarUsuario(new Usuario("a","123"), new CatalogoContatos());
		agenda.adicionarUsuario(new Usuario("b","123"), new CatalogoContatos());
		agenda.adicionarUsuario(new Usuario("c","123"), new CatalogoContatos());
		
		assertTrue(agenda.consultarTamanhoDaBaseDeDados() == 3);
	}

	@Test
	public void testAgendaLimparBaseDeDados(){
		agenda.adicionarUsuario(new Usuario("a","123"), new CatalogoContatos());
		agenda.adicionarUsuario(new Usuario("b","123"), new CatalogoContatos());
		agenda.adicionarUsuario(new Usuario("c","123"), new CatalogoContatos());
		
		assertTrue(agenda.consultarTamanhoDaBaseDeDados() == 3);
		
		agenda.limparBaseDeDados();
		
		assertTrue(agenda.isEmpty());
	}

	@Test
	public void testAgendaListarUsuarioNaBaseDeDados(){
		agenda.adicionarUsuario(new Usuario("a","123"), new CatalogoContatos());
		agenda.adicionarUsuario(new Usuario("b","123"), new CatalogoContatos());
		agenda.adicionarUsuario(new Usuario("c","123"), new CatalogoContatos());
		
		List<Usuario> usuarios = agenda.listarUsuarios();
		
		assertTrue(usuarios.size() == 3);
	}
	
	@Test
	public void testAgendaRemoveUsuarioCadastrado(){
		agenda.adicionarUsuario(new Usuario("a","123"), new CatalogoContatos());
		agenda.adicionarUsuario(new Usuario("b","123"), new CatalogoContatos());
		agenda.adicionarUsuario(new Usuario("c","123"), new CatalogoContatos());
		
		assertTrue (agenda.getTotalDeUsuarios() == 3);
		
		agenda.removerUsuario("a", "123");
		agenda.removerUsuario("b", "123");
		
		assertTrue (agenda.getTotalDeUsuarios() == 1);
	}
	
	
	@Test
	public void testAgendaFazLoginTrue() {
		agenda.adicionarUsuario(new Usuario("samuel","123"), new CatalogoContatos());
		
		assertTrue(agenda.fazLogin("samuel", "123"));
	}

	@Test
	public void testAgendaFazLoginFalse() {
		agenda.adicionarUsuario(new Usuario("samuel","123"), new CatalogoContatos());
		
		assertFalse(agenda.fazLogin("samuel", "1*23"));
	}

	@Test
	public void testAgendaGetUsuarioLogado(){
		agenda.adicionarUsuario(new Usuario("samuel","123"), new CatalogoContatos());
		agenda.adicionarUsuario(new Usuario("amanda","123"), new CatalogoContatos());
		
		agenda.fazLogin("amanda", "123");
		
		assertEquals("amanda",agenda.getUsuarioLogado().getNomeUsuario());
	}
	
	@Test
	public void testAgendaAdicionaContato(){
		agenda.adicionarUsuario(new Usuario("samuel","123"), new CatalogoContatos());
		agenda.adicionarUsuario(new Usuario("amanda","123"), new CatalogoContatos());
		
		agenda.fazLogin("amanda", "123");
		
		agenda.adicionarContato("samuel", 25, "programador java");
		
		MapEntry entrada = agenda.getMapEntry();
		
		assertEquals("samuel", entrada.getContato().getNome());
	}
	
	
	@Test
	public void testAgendaAdicionaTelefone(){
		agenda.adicionarUsuario(new Usuario("samuel","123"), new CatalogoContatos());
		agenda.adicionarUsuario(new Usuario("amanda","123"), new CatalogoContatos());
		
		agenda.fazLogin("amanda", "123");
		
		agenda.adicionarContato("samuel", 25, "programador java");
		
		MapEntry entrada = agenda.getMapEntry();
		
		entrada.adicionarTelefone(new Telefone("+55", "83", "31", "9990-0990", "CLARO", "31"));
		
		assertTrue(entrada.getTotalDeTelefones() == 1);
		
		assertTrue(agenda.getTotalDeTelefones() == 1);
	}

	
	@Test
	public void testAgendaAdicionaEmails(){
		agenda.adicionarUsuario(new Usuario("samuel","123"), new CatalogoContatos());
		agenda.adicionarUsuario(new Usuario("amanda","123"), new CatalogoContatos());
		
		agenda.fazLogin("amanda", "123");
		
		agenda.adicionarContato("samuel", 25, "programador java");
		
		MapEntry entrada = agenda.getMapEntry();
		
		entrada.adicionarEmail("samuel@g1.com");
		entrada.adicionarEmail("samuel@uol.com");
		entrada.adicionarEmail("samuel@terra.com.br");
		
		assertTrue(entrada.getTotalDeEmails() == 3);
		
		assertTrue(agenda.getTotalDeEmails() == 3);
	}
	
	@Test
	public void testAgendaFazLogoff(){
		
		agenda.adicionarUsuario(new Usuario("samuel","123"), new CatalogoContatos());
		agenda.adicionarUsuario(new Usuario("amanda","123"), new CatalogoContatos());
		
		assertFalse(agenda.hasUsuarioLogado());
		
		agenda.fazLogin("samuel", "123");
		
		assertTrue(agenda.hasUsuarioLogado());
		
		agenda.fazLogin("amanda", "123");
		
		assertTrue(agenda.hasUsuarioLogado());
		assertEquals("amanda", agenda.getUsuarioLogado().getNomeUsuario());
		
		agenda.fazLogoff();
		
		assertFalse(agenda.hasUsuarioLogado());
	}
	
}