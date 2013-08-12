package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

/**
 * <p>
 * 	Essa classe representa um catalogo de usuarios do sistema agenda.
 *  Uma catalog relaciona um conjunto de chaves usuarios com um conjunto
 *  de valores lista de contados.
 *  
 *  Uma entra para o catalogo eh formada por usuaro e lista de contatos.
 *  
 * </p>
 * @author Samuel T. C. Santos
 * @version 1.0
 *
 */
public class CatalogoUsuarios implements Serializable {

	/**
	 * default Serial version UID 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Essa map associa um usuário com a sua lista de contatos.
	 */
	private Map<Usuario, CatalogoContatos> catalogo;
	
	
	public CatalogoUsuarios (){
		catalogo = new HashMap<Usuario, CatalogoContatos>();
	}
	
	/**
	 * <p>Esse metodo adiciona no catalogo um usuário e uma lista de contatos.</p>
	 * 
	 * @param usuario
	 * 		Um usuario
	 * @param catalogoContatos
	 * 		Um catalogo de Contatos
	 */
	public void adiciona(Usuario usuario, CatalogoContatos catalogoContatos){
		catalogo.put(usuario, catalogoContatos);
	}

	/**
	 * <p>
	 * Verifica se os dados do login informados  correspondem a um usuario 
	 * cadastrados no sistema agenda.
	 * </p>
	 * @param usuario
	 * 		nome do usuario
	 * @param senha
	 * 		senha do usuario
	 * @return
	 * 		true or false.
	 */	
	public boolean existeUsuario(String usuario, String senha) {
		Set usuarios = catalogo.keySet();
		
		for (Object o : usuarios){
			Usuario u = (Usuario) o;
			if (u.getNomeUsuario().equals(usuario) && u.getSenha().equals(senha))
				return true;
		}
		
		return false;
	}

	/**
	 * <p>Esse metodo informa o total de usuario adicionados no catalogo.</p>
	 * @return
	 * 		o total de usuarios.
	 */
	public int totalDeUsuarios() {
		return catalogo.keySet().size();
	}

	/**
	 * <p>Esse metodo retorna a lista com todos os usuarios.</p>
	 * @return
	 */
	public List<Usuario> listarUsuarios() {
		List<Usuario> lista = new ArrayList<Usuario>();
		
		for (Object o : catalogo.keySet()){
			lista.add((Usuario)o);
		}
		return lista;
	}

	/**
	 * <p>Esse metodo limpa todas as informacoes do catalogo de usuarios.</p>
	 */
	public void limpar() {
		catalogo.clear();
	}

	/**
	 * <p>
	 * 		Esse metodo remove do catalogo o usuario informado.
	 * </p>
	 * @param nomeUsuario
	 * 		nome de usuario.
	 * @param senha
	 * 		senha do usario.
	 */
	public void remove(String nomeUsuario, String senha) {
		Usuario u = getUsuario(nomeUsuario, senha);
		if (!u.isEmpty()){
			catalogo.remove(u);
		}
	}
	
	/**
	 * <p>
	 * 		Esse metodo pega o usuario com nome e senha informado.
	 * 		Caso o usuario informado nao existe eh retorna um objeto usuario
	 * 		vazio (ou seja, nomeUsuario="", e senha="").
	 * </p>
	 * @param nomeUsuario
	 * 		nome de usuario.
	 * @param senha
	 * 		senha do usuario.
	 * @return
	 * 		Um objeto do tipo <code>Usuario</code>
	 */
	public Usuario getUsuario(String nomeUsuario, String senha ){
		for (Object o : catalogo.keySet()){
			Usuario u = (Usuario) o;
			if(u.getNomeUsuario().equals(nomeUsuario) && u.getSenha().equals(senha))
				return u;
		}
		return new Usuario();
	}
	
	/**
	 * <p>
	 * 		Esse metodo pega todo o catalogo de contatos do usuario informado.
	 * </p>
	 * @param usuario
	 * 		um usuario.
	 * @return
	 * 		um catalogo de contatos, contendo todos os telefones, emails e 
	 * 		contatos do usuario.
	 */
	public CatalogoContatos getCatalogoContatos(Usuario usuario){
		return catalogo.get(usuario);
	}
	
}
