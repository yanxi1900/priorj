package model;

import java.io.Serializable;

/**
 * <p>
 * 	Essa classe representa um usuario com login e senha.
 * </p>
 * @author Samuel T. C. Santos
 * @version 1.0
 */
public class Usuario implements Serializable {

	/**
	 * default Serial version UID 
	 */
	private static final long serialVersionUID = 1L;
	
	private String nomeUsuario;
	private String senha;
	private boolean estaLogado;

	/**
	 * Construtor Default.
	 */
	public Usuario() {
		// TODO Auto-generated constructor stub
		nomeUsuario="";
		senha ="";
		estaLogado = false;
	}

	/**
	 * Cria um usuario com nome e Senha.
	 * @param nomeUsuario
	 * 		nome de usuario
	 * @param senha
	 * 		senha do usuario.
	 */
	public Usuario(String nomeUsuario, String senha) {
		this();
		this.nomeUsuario = nomeUsuario;
		this.senha = senha;
	}

	/**
	 * <p>Esse metodo torna o status do usuario para logado.</p>
	 * 
	 */
	public void logar(){
		estaLogado = true;
	}
	
	/**
	 * <p>Esse metodo desloga o usuario da agenda.</p>
	 */
	public void deslogar(){
		estaLogado = false;
	}
	
	/**
	 * <p>Esse metodo diz se o usuario esta ou nao logado.</p>
	 * @return
	 * 		true or false.
	 */
	public boolean isLogado(){
		return estaLogado;
	}
	
	/**
	 * Pegar o nome do usuario. (login)
	 * @return
	 */
	public String getNomeUsuario() {
		return nomeUsuario;
	}
	/**
	 * Enviar um login para o usuario.
	 * 
	 * @param nomeUsuario
	 */
	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}
	/**
	 * Obter a senha do usuario. 
	 * @return
	 */
	public String getSenha() {
		return senha;
	}
	/**
	 * Enviar a senha para o usuario.
	 * @param senha
	 */
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	/**
	 * <p>
	 * 		Esse metodo diz se o usario eh vazio, um usuario esta vazio
	 * 		quando nomeUsuario="" e a senha="". 
	 * 
	 * </p>
	 * @return
	 * 		true or false.
	 */
	public boolean isEmpty(){
		return nomeUsuario.isEmpty() && senha.isEmpty();
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		if (nomeUsuario == null) {
			if (other.nomeUsuario != null)
				return false;
		} else if (!nomeUsuario.equals(other.nomeUsuario))
			return false;
		if (senha == null) {
			if (other.senha != null)
				return false;
		} else if (!senha.equals(other.senha))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "[nomeUsuario=" + nomeUsuario + ", senha=" + senha + "]";
	}
	
    
	
}
