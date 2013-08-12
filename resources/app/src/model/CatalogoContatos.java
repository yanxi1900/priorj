package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

/**
 * <p>
 * 		Essa classe representa um catalogo de contatos, cada usuario
 * 		tem seu proprio catalogo de contatos, em cada entrada desse
 * 		catalogo temos um &lt;Contato, lista de Telefones &gt; cada contato
 * 		eh unico, por esse motivo,o  contato eh a chave do mapa e como
 * 		cada contato possui um lista de numeros de telefones essa
 * 		lista representa dos valores associados com esse contato no mapa.
 * </p>
 * 
 * <p>
 * 		Cada contato possui uma lista de email, esse catalogo associa tambem
 * 		cada contato com sua respectiva lista de emails.
 * </p>
 * 
 * @author Samuel T. C. Santos.
 * @version 1.0
 *
 */
public class CatalogoContatos implements Serializable {

	/**
	 * Associa cada contato a uma lista de telefones.
	 */
	private Map<Contato, List<Telefone>> mapaTelefones;
	/**
	 * Associa cada contato a uma lista de emails.
	 */
	private Map<Contato, List<String>> mapaEmails;
	
	
	/**
	 * <p>
	 *   Constroi um novo catalogo para um usuario passando para ele uma lista 
	 *   de telefones e uma outra lista com emails.
	 * </p>
	 * @param contato
	 * @param telefones
	 * @param emails
	 */
	public CatalogoContatos(Contato contato, List<Telefone> telefones, List<String> emails){
		mapaTelefones = new HashMap<Contato, List<Telefone>>();
		mapaEmails = new HashMap<Contato, List<String>>();
		
		adicionarTelefones(contato,telefones);
		adicionarEmails(contato, emails);
	}
	
	/**
	 * <p>
	 * 		Esse metodo adiciona uma entra para o catologo, representando 
	 * 		um contato e sua lista de telefones.
	 * </p>
	 * @param contato
	 * 		O objeto do tipo <code>Contato</code>.
	 * @param telefones
	 * 		Uma lista de telefones.
	 */
	public void adicionarTelefones(Contato contato, List<Telefone> telefones){
		mapaTelefones.put(contato, telefones);
	}
	
	public void adicionarEmails(Contato contato, List<String> emails){
		mapaEmails.put(contato,emails);
	}
	
	public CatalogoContatos() {
		mapaTelefones = new HashMap<Contato, List<Telefone>>();
		mapaEmails = new HashMap<Contato, List<String>>();
	}

	/**
	 * <p>
	 * 		Esse metodo cadastra um novo contato no catalogo de contato e associa
	 * 		esse novo contato do catalogo a uma lista de emails e a uma lista de contatos.
	 * </p> 
	 * @param contato
	 * @param telefones
	 * @param emails
	 */
	public void addContato(Contato contato, List<Telefone> telefones, List<String> emails){
		mapaTelefones.put(contato, telefones);
		mapaEmails.put(contato, emails);
		
	}
	
	/**
	 * <p>
	 * 		Esse metodo informa o total de contatos cadastrados nessa catalogo de contatos.
	 * </p>
	 * @return
	 * 		o total de contatos cadastrados.
	 */
	public int getTotalDeContatos(){
		int entradasTelefone = mapaTelefones.keySet().size();
		int entradasEmails = mapaEmails.keySet().size();
		
		return Math.max(entradasTelefone, entradasEmails);
	}
	
	/**
	 * <p>
	 * 		Esse metodo retorna a quantidade de telefones cadastrado nesse catalogo.
	 * 		
	 * </p>
	 * @return
	 * 		A quantidade de contatos nesse catalogo.
	 */
	public int getTotalDeTelefones(){
		int total = 0;
		
		for (Object key : mapaTelefones.keySet()){
			List<Telefone> tLista = (List<Telefone>) mapaTelefones.get(key);
			
			total += tLista.size();
		}
		
		return total;
	}
	
	/**
	 * <p>
	 * 		Esse metodo retorna a quantidade de emails cadastrados para esse contado.
	 * </p>
	 * @return
	 */
	public int getTotalDeEmails(){
		int total = 0;
		
		for (Object key : mapaEmails.keySet()){
			List<String> eLista = (List<String>) mapaEmails.get(key);
			
			total += eLista.size();
			
		}
		
		return total;
	}
	
	/**
	 * <p>
	 * 		O catalogo de contatos esta vazio se nao ha emails nem telefones.
	 * </p>
	 * @return
	 * 		true or false.
	 */
	public boolean isEmpty(){
		return mapaEmails.isEmpty() && mapaTelefones.isEmpty();
	}
	
	/**
	 * <p>
	 * 		Esse metodo constroi a lista com todos os contatos do catalogo.
	 * </p>
	 * @return
	 * 		uma lista com todos os contatos.
	 */
	public List<Contato> getContatos(){
		List<Contato> listaDeContatos = new ArrayList<Contato>();
		
		for (Object key : mapaTelefones.keySet()){
			Contato c =(Contato) key;
			listaDeContatos.add(c);
		}
		
		return listaDeContatos;
	}
	
	/**
	 * <p>
	 * 		Esse metodo retorna uma lista com todos os emails de todos
	 * 		os contatos cadastrados nesse catalogo.
	 * </p>
	 * @return
	 * 		Uma lista com todos os emails do catalogo.
	 */
	public List<String> getEmails(){
		List<String> listaDeEmails = new ArrayList<String>();
		
		Collection c = mapaEmails.values();
		
		Iterator it = c.iterator();
		
		while(it.hasNext()){
			List<String> emails = (List<String>) it.next();
			listaDeEmails.addAll(emails);
		}
		
		return listaDeEmails;
	}
	
	/**
	 * <p>
	 * 		Esse metodo retorna uma lista com todos os telefones cadastrados
	 * 		nesse catalogo.
	 * </p>
	 * @return
	 * 		uma lista de telefones.
	 */
	public List<Telefone> getTelefones(){
		List<Telefone> listaDeFones = new ArrayList<Telefone>();
		
		Collection c = mapaTelefones.values();
		
		Iterator it = c.iterator();
		
		while(it.hasNext()){
			List<Telefone> fones = (List<Telefone>) it.next();
			listaDeFones.addAll(fones);
		}
		
		return listaDeFones;
	}
	
	/**
	 * <p>
	 * 		Esse metodo retorna um contato com sua lista de telefones e sua
	 * 		lista de emails para que seja manipulados fora do catalogo e 
	 * 		apos a manipulacao (consulta, remove,adicao, atulizacao), sera
	 * 		novamente retornado e atualizara o catalogo.
	 * </p> 
	 * @param contato
	 * 		um contato.
	 * @return
	 * 
	 * 		Uma entrada para do catalogo.
	 */
	public MapEntry getEntry(Contato contato){
		List<Telefone> telefones = mapaTelefones.get(contato);
		List<String> emails = mapaEmails.get(contato);
		
		MapEntry entradaDoCatalogo = new MapEntry(contato,telefones,emails);
		
		return entradaDoCatalogo;
	}
	
	/**
	 * <p>
	 * 		Esse metodo busca um contato com o nome infomardo.
	 * </p>
	 * @param nome
	 * 		o nome do contato.
	 * @return
	 * 		O contato caso exista ou um contato vazio caso nao exista.
	 */
	public Contato getContatoPorNome(String nome){
		for (Contato c : getContatos()){
			if (c.getNome().equals(nome)) return c;
		}
		
		return new Contato();
	}
	
	/**
	 * <p>
	 * 		Esse metodo recebe uma entrada do catalogo para atualizar a estada
	 * 		atual existente no catalogo.
	 * </p>
	 * @param entrada
	 * 		um objeto do com informacoes de dados do catalogo.
	 */
	public void atualizarCatalogo(MapEntry entrada){
		addContato(entrada.getContato(),entrada.getTelefones(), entrada.getEmails());
	}
	
}
