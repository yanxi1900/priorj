package model;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;

public class MapEntryTest {

	MapEntry entrada;
	
	@Before
	public void setUp() throws Exception {
		entrada = new MapEntry(new Contato(), new ArrayList<Telefone>(), new ArrayList<String>());
	}

	@After
	public void tearDown() throws Exception {
		entrada = null;
	}

	@Test
	public void testEntradaVazia() {
		assertTrue(entrada.isEmpty());
	}

	@Test
	public void testMapEntryValidaEmailTrue(){
		assertTrue(entrada.validarEmail("samuel@gmail.com"));
	}
	
	@Test
	public void testMapEntryValidaEmailFalse(){
		assertFalse(entrada.validarEmail("samuel_gmail.com"));
	}
	
	@Test
	public void testMapEntryAdicionaEmail(){
		entrada.adicionarEmail("samuel@g1.com.br");
		entrada.adicionarEmail("karla@g1.com.br");
		
		assertTrue(entrada.getTotalDeEmails() == 2);
	}
	
	@Test
	public void testMapEntryAtualizarEmail(){
		entrada.adicionarEmail("samuel@g1.com.br");
		entrada.adicionarEmail("karla@g1.com.br");
		
		entrada.atualizarEmail("samuel@g1.com.br", "samuel@g1.com");
		
		assertTrue(entrada.contemEmail("samuel@g1.com"));
		assertFalse(entrada.contemEmail("samuel@g1.com.br"));
	}
	
	@Test
	public void testMapEntryRemoveEmail(){
		entrada.adicionarEmail("samuel@g1.com.br");
		entrada.adicionarEmail("karla@g1.com.br");
		
		assertTrue(entrada.getTotalDeEmails() == 2);
		
		entrada.removerEmail("samuel@g1.com.br");
		
		assertTrue(entrada.getTotalDeEmails() == 1);
	}
	
	@Test
	public void testMapEntryPesquisarEmail(){
		entrada.adicionarEmail("samuel@g1.com.br");
		entrada.adicionarEmail("karla@g1.com.br");
		
		assertTrue(entrada.contemEmail("samuel@g1.com.br"));
		assertTrue(entrada.contemEmail("karla@g1.com.br"));
		
	}
	
	@Test
	public void testMapEntryAdicionaTelefone(){
		Telefone telefone = new Telefone("+55", "82", "41", "9888-8765", "VIVO", "41");
		
		entrada.adicionarTelefone(telefone);
		
		assertTrue(entrada.getTotalDeTelefones() == 1);
	}
	
	@Test
	public void testMapEntryContemTelefone(){
		Telefone telefone = new Telefone("+55", "82", "41", "9888-8765", "VIVO", "41");
		Telefone telefone1 = new Telefone("+55", "82", "21", "9888-8765", "Embratel", "41");
		Telefone telefone2 = new Telefone("+55", "82", "21", "9888-8765", "Embratel", "41");
		
		entrada.adicionarTelefone(telefone1);
		entrada.adicionarTelefone(telefone2);
		
		assertTrue(entrada.contemTelefone(telefone1));
		assertTrue(entrada.contemTelefone(telefone2));
		assertFalse(entrada.contemTelefone(telefone));
		
	}
	
	@Test
	public void testMapEntryDeleteTelefone(){
		Telefone telefone = new Telefone("+55", "82", "41", "9888-8765", "VIVO", "41");
		Telefone telefone1 = new Telefone("+55", "82", "21", "9888-8765", "Embratel", "41");
		Telefone telefone2 = new Telefone("+55", "82", "21", "9888-8765", "Embratel", "41");
		
		entrada.adicionarTelefone(telefone1);
		entrada.adicionarTelefone(telefone2);
		
		assertTrue(entrada.contemTelefone(telefone1));
		assertTrue(entrada.contemTelefone(telefone2));
		
		entrada.removerTelefone(telefone1);
		entrada.removerTelefone(telefone2);
		
		assertTrue(entrada.getTotalDeTelefones() == 0);
	}
	
	
	@Test
	public void testMapEntryGetTelefonePorNumero(){
		Telefone telefone1 = new Telefone("+55", "41", "41", "9818-8765", "TIM", "41");
		Telefone telefone2 = new Telefone("+55", "31", "21", "9888-8765", "Embratel", "11");
		Telefone telefone3 = new Telefone("+55", "82", "31", "9888-8765", "Claro", "31");
		
		entrada.adicionarTelefone(telefone1);
		entrada.adicionarTelefone(telefone2);
		entrada.adicionarTelefone(telefone3);
		
		Telefone t = entrada.getTelefonePorNumero("9888-8765");
		
		
		assertTrue(t.getNumero().equals("9888-8765"));
	}
	
	@Test
	public void testMapEntryGetTelefonePorNumeroQuandoNaoExiste(){
		Telefone telefone1 = new Telefone("+55", "41", "41", "9818-8765", "TIM", "41");
		Telefone telefone2 = new Telefone("+55", "31", "21", "9888-8765", "Embratel", "11");
		Telefone telefone3 = new Telefone("+55", "82", "31", "9888-8765", "Claro", "31");
		
		entrada.adicionarTelefone(telefone1);
		entrada.adicionarTelefone(telefone2);
		entrada.adicionarTelefone(telefone3);
		
		Telefone t = entrada.getTelefonePorNumero("9888-000");
		
		assertTrue(t.isEmpty());
	}
	

}
