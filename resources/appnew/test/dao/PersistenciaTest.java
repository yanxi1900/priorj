package dao;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;


import model.CatalogoContatos;
import model.CatalogoUsuarios;
import model.Contato;
import model.Usuario;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class PersistenciaTest {

	Persistencia dao;

	CatalogoUsuarios catalogo;

	@Before
	public void setUp() throws Exception {
		dao = new Persistencia();
		catalogo = new CatalogoUsuarios();
	}

	@After
	public void tearDown() throws Exception {
		dao = null;

		File f = new File("data.ser");

		if (f.exists())
			f.delete();
	}

	@Test
	public void testWrite() {
		catalogo.adiciona(new Usuario("a","123"),new CatalogoContatos());

		dao.write(catalogo);

		File f = new File("data.ser");

		assertTrue(f.exists());

	}

	@Test
	public void testRead() {
		catalogo.adiciona(new Usuario("a","123"), new CatalogoContatos());
		catalogo.adiciona(new Usuario("b","123"), new CatalogoContatos());
		catalogo.adiciona(new Usuario("c","123"), new CatalogoContatos());
		catalogo.adiciona(new Usuario("d","123"), new CatalogoContatos());
		
		dao.write(catalogo);


		CatalogoUsuarios catalogoSalvo = dao.read();

		assertTrue(catalogoSalvo.totalDeUsuarios() == 4);
	}

	@Test
	public void testStartingReading() {
		
		CatalogoUsuarios catalogoSalvo = dao.read();

		assertTrue(catalogoSalvo.totalDeUsuarios() == 0);
	}

}
