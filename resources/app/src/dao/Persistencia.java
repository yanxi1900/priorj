package dao;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;

import model.CatalogoUsuarios;

/**
 * <p>Essa classe eh responsavel por gerenciar a persistencia de dados.</p>
 * 
 * @author Samuel T. C. Santos
 * @version 1.0
 */
public class Persistencia implements Serializable {

	/**
*
*/
	private static final long serialVersionUID = 1L;

	/**
	 * Cria um objeto persistencia.
	 */
	public Persistencia() {
		// default
	}

	/**
	 * <p>Serializa os dados do catalogo (escrever).</p>
	 * @param catalogo
	 */
	public void write(CatalogoUsuarios catalogo) {
		try {
			Serializa.serializes(catalogo);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * <p>Deserializa os dados do catalogo (ler).</p>
	 * @return
	 */
	public CatalogoUsuarios read() {
		CatalogoUsuarios catalogo;
		try {
			File f = new File("data.ser");

			if (f.exists() && f.length() > 0) {
				catalogo = (CatalogoUsuarios) Deserializa.deserialize();
				return catalogo;
			} else {
				return new CatalogoUsuarios();
			}
		} catch (ClassNotFoundException e) {
			System.err.println(e.getMessage());
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		return new  CatalogoUsuarios();
	}

}