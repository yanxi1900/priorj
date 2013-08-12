package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Essa classe representa a entidade contato.
 * 
 * @autor Renan
 * @author Samuel T. C. Santos
 * @version 1.0
 */
public class Contato implements Serializable {

	/**
	 * default Serial version UID 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Nome do contato
	 */
	private String nome;
	/**
	 * Descricao para esse contato
	 */
	private String descricao;
	/**
	 * Idade do contato
	 */
	private int idade;
	

	/**
	 * Construtor default.
	 */
	public Contato() {
		this.nome = "";
		this.idade = 0;
		this.descricao = "";
	}

	/**
	 * Construtor para a classe Contato, cria um contato com nome 
	 * idade e descricao.
	 * 
	 * @param nome
	 * 		O nome do usuario.
	 * @param numero
	 * 		O numero do telefone.
	 * @param descricao
	 * 		uma descricao sobre esse contato.
	 * 		
	 */
	public Contato(String nome, int idade, String descricao) {
		this.nome = nome;
		this.idade = idade;
		this.descricao = descricao;

	}

	/**
	 * Get para o nome do contato.
	 * @return
	 * 		o nome do contato.
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * Set para o nome do contato.
	 * @param nome
	 * 		o nome do contato.
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * Get a descricao do contato.
	 * @return
	 * 		A descricao do contato.
	 */
	public String getDescricao() {
		return descricao;
	}
	/**
	 * Set a descricao do contato.
	 * @param descricao
	 * 		uma descricao para esse contato.
	 */
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	/**
	 * Todo contato possui um idade, esse metodo pegar a idade.
	 * 
	 * @return
	 * 		uma idade para esse contato.
	 */
	public int getIdade() {
		return idade;
	}
	/**
	 * Set a idade para esse contato.
	 * 
	 * @param idade
	 * 		a idade do contato.
	 */
	public void setIdade(int idade) {
		this.idade = idade;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Contato other = (Contato) obj;
		if (descricao == null) {
			if (other.descricao != null)
				return false;
		} else if (!descricao.equals(other.descricao))
			return false;
		if (idade != other.idade)
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}

	/**
	 * <p>
	 * 		Esse metodo diz se esse contato esta vazio.
	 * </p>
	 * @return
	 * 		true or false.
	 */
	public boolean isEmpty(){
		return nome.isEmpty() && idade == 0 && descricao.isEmpty();
	}
	@Override
	public String toString() {
		return "nome=" + nome + ", descricao=" + descricao + ", idade=" + idade + "]";
	}

}
