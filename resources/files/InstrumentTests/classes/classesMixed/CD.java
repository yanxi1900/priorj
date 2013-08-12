package lp2.lab07;

import java.util.ArrayList;

/** 
 * 
 * @author Igor de Araujo Meira
 * @version 1.0 - 22/07/2013
 *  
 */

public class CD {
	
	private String titulo;
	private String autor;
	private ArrayList<String> faixas;
	private String carroChefe;
	
	//contrutor1
	/**
	 * Cria um CD recebendo titulo, autor e o numero de musicas.
	 * 
	 * @param titulo Nome do CD.
	 * @param autor Nome da banda ou cantor do CD.
	 * @param M Numero de faixas que o CD deve ter.
	 * @throws Exception Mensagem de excecao, caso nao tenha titulo ou nome, ou numero de faixas menor que um.
	 */
	public CD(String titulo, String autor, int M) throws Exception {
		if(titulo == "" || titulo == null){
			throw new Exception("O CD deve possuir um titulo.");
		}
		else if(autor == "" || autor == null){
			throw new Exception("O CD deve ter um autor.");
		}
		else if(M <= 0){
			throw new Exception("O CD deve conter no minimo uma musica.");
		}
		
		this.titulo  = titulo;
		this.autor = autor;
		carroChefe = "";
		iniciaFaixasDoCD(M);
	}

	//construtor2
	/**
	 * Cria um CD recebendo titulo, autor, numero de musicas e o carro chefe do CD.
	 * 
	 * @param titulo Nome do CD.
	 * @param autor Nome da banda ou cantor do CD.
	 * @param M Numero de faixas que o CD deve ter.
	 * @param carroChefe Nome da principal musica do CD.
	 * @throws Exception Mensagem de excecao, caso nao tenha titulo ou nome, ou numero de faixas menor que um.
	 */
	public CD(String titulo, String autor, int M, String carroChefe) throws Exception{
		if(titulo == "" || titulo == null){
			throw new Exception("O CD deve possuir um titulo.");
		}
		if(autor == "" || autor == null){
			throw new Exception("O CD deve ter um autor.");
		}
		if(M <= 0){
			throw new Exception("O CD deve conter no minimo uma musica.");
		}
		
		this.titulo = titulo;
		this.autor = autor;
		this.carroChefe = carroChefe;
		iniciaFaixasDoCD(M);
	}
	
	//métodos
	private void iniciaFaixasDoCD(int M) {
		faixas = new ArrayList<String>();
		for (int i = 0; i < M; i++) {
			faixas.add(i, "");
		}
		if(carroChefe != "" || carroChefe != null){
			faixas.add(0, carroChefe);
		}
	}
	/**
	 * Modifica o carro chefe do CD.
	 * 
	 * @param novoCC Nome do novo carro chefe do CD.
	 */
	public void setCarroChefe(String novoCC) throws Exception{
		if(!faixas.contains(novoCC)){
			throw new Exception("Essa faixa nao esta no CD.");
		}
		carroChefe = novoCC;
	}
	/**
	 * Retorna o titulo do CD.
	 * 
	 * @return Titulo do CD.
	 */
	public String getTitulo(){
		return titulo;
	}
	/**
	 * Retorna o autor do CD.
	 * 
	 * @return Autor do CD.
	 */
	public String getAutor(){
		return autor;
	}
	/**
	 * Retorna as musicas que estao no CD.
	 * 
	 * @return As musicas do CD.
	 */
	public String getFaixas(){
		return faixas.toString();
	}
	/**
	 * Retorna o nome do carro chefe.
	 * 
	 * @return O carro chefe do CD.
	 */
	public String getCarroChefe(){
		return carroChefe;
	}
	/**
	 * Retorna uma musica do CD.
	 * 
	 * @param i - Numero da musica CD.
	 * @return Uma musica do CD.
	 */
	public String getUmaFaixa(int i){
		if(i > faixas.size() || i <= 0){
			return null;
		}
		return faixas.get(i-1);
	}
	/**
	 * Adiciona nova musica ao CD.
	 * 
	 * @param nomeDaFaixa - Nome da musica que quer adicionar.
	 * @return true: a musica foi adicionada. 
	 * 		   false: a musica nao foi adicionada.
	 */
	public boolean addFaixa(String nomeDaFaixa){
		for (int i = 0; i < faixas.size(); i++) {
			if(faixas.get(i) == ""){
				faixas.set(i, nomeDaFaixa);
				return true;
			}
		}
		return false;		
	}
	/**
	 * Retorna as informacoes do CD, no formato String.
	 * 
	 * @return Informacoes do CD (autor, titulo, carro chefe e musicas).
	 */
	@Override
	public String toString() {
		String listaMusicas = "";
		for (int i = 0; i < faixas.size() - 1; i++) {
			listaMusicas += i+1 + " - " + faixas.get(i) + " / ";
		}
		listaMusicas += faixas.size() + " - " + faixas.get(faixas.size() - 1);
		return "[Autor: " + getAutor() + ", Titulo: " + getTitulo() + ", Carro chefe: " + getCarroChefe()
				+ ", Faixas: " + listaMusicas + "]";
	}
	/**
	 * Compara dois CDs.
	 * 
	 * @return true, caso sejam iguais. false, caso nao.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CD other = (CD) obj;
		if (autor == null) {
			if (other.autor != null)
				return false;
		} else if (!autor.equals(other.autor))
			return false;
		if (titulo == null) {
			if (other.titulo != null)
				return false;
		} else if (!titulo.equals(other.titulo))
			return false;
		return true;
	}
}