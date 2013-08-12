package model;

import java.util.List;

/**
 * <p>
 * 		Essa classe eh util para auxiliar na manipulacao das operacoes de
 * 		buscas e atualizacao pelas entradas do catalogo de contatos, cada contato
 *      no catalogo possui um conjunto de emails e um conjunto de telefones armazenados
 * 		em mapas distintos, um contato pode ou nao ter emails, pode ou nao ter
 * 		telefone essa classe busca essa informacoes no catologo as reune em um 
 * 		objeto onde iram ser manipuladores e posteriormente seram atualizadas 
 * 		novamente no catalogo.
 * </p>
 * @author Samuel T. C. Santos
 * 
 *
 */
public class MapEntry {

	private Contato contato;
	private List<String> emails;
	private List<Telefone> telefones;
	
	/**
	 * Cria uma entrada para o mapa do catalogo de contatos, com um contato
	 * que possui lista de telefones e lista de emails.
	 * 
	 * @param c
	 * @param telefones
	 * @param emails
	 */
	public MapEntry(Contato contato, List<Telefone> telefones, List<String> emails){
		this.contato = contato;
		this.telefones = telefones;
		this.emails = emails;
	}

	/**
	 * Pegar o contato
	 */
	public Contato getContato() {
		return contato;
	}
	/**
	 * Enviar o contato para essa entrada.
	 * @param contato
	 */
	public void setContato(Contato contato) {
		this.contato = contato;
	}
	/**
	 * Pegar a lista de todos os emails desse contato.
	 * @return
	 */
	public List<String> getEmails() {
		return emails;
	}
	/**
	 * Envia uma lista de emails para esse contato.
	 * @param emails
	 */
	public void setEmails(List<String> emails) {
		this.emails = emails;
	}
	/**
	 * Pegar a lista de telefones para esse contato.
	 * @return
	 */
	public List<Telefone> getTelefones() {
		return telefones;
	}

	/**
	 * Enviar uma nova lista de telefones.
	 * @param telefones
	 */
	public void setTelefones(List<Telefone> telefones) {
		this.telefones = telefones;
	}
	
	/**
	 * <p>
	 * 		Esse metodo adiciona um novo email para uma entrada do catalogo.
	 * </p>
	 * @param email
	 * 		o email a ser adicionado.
	 */
	public void adicionarEmail(String email){
		if (!existeEmail(email) && validarEmail(email))
			emails.add(email);
	}

	/**
	 * Esse metodo verifica se o email informado ja esta adicionado na lista
	 * de emails.
	 * 
	 * @param email
	 * 		um email qualquer.
	 * @return
	 * 		true or false.
	 */
	public boolean existeEmail(String email) {
		return emails.contains(email);
	}
	
	/**
	 * <p>
	 * 		Esse metodo atualiza um email desse contato, para isso eh preciso
	 * 		informa o email que sera atualizado e o novo valor para esse email.
	 * </p>
	 * @param emailAntigo
	 * 		o antigo email.
	 * @param emailNovo
	 * 		o novo email.
	 */
	public void atualizarEmail(String emailAntigo, String emailNovo){
		if(existeEmail(emailAntigo))
			removerEmail(emailAntigo);
		
		adicionarEmail(emailNovo);
	}
	
	/**
	 * <p>
	 * 		Esse metodo pesquisa se um determinado email existe na lista de emails.
	 * </p>
	 * @param email
	 * 		um email qualquer.
	 * @return
	 * 		true or false.
	 */
	public boolean contemEmail(String email){
		return existeEmail(email);
	}
	
	/**
	 * <p>
	 * 		Esse metodo remove um email da lista de emails dessa entrada do catalogo.
	 * </p>
	 * @param email
	 * 		um email qualquer.
	 */
	public void removerEmail(String email){
		if (existeEmail(email))
			emails.remove(email);
	}
	
	/**
	 * <p>
	 * 		Esse metodo valida a entrada de um email informado, um email eh
	 * 		valido que ele contem o caractere "@", caso contrario eh considerado
	 * 		invalido.
	 * </p>
	 * @param email
	 * 		um email qualquer.
	 */
	public boolean validarEmail(String email){
		return email.contains("@");
	}
	
	
	/**
	 * <p>
	 * 		Esse metodo adiciona um novo telefone nessa entrada.
	 * </p>
	 * @param telefone
	 */
	public void adicionarTelefone(Telefone telefone){
		if (!contemTelefone(telefone) && telefone.isValido()){
			telefones.add(telefone);
		}
	}
	
	/**
	 * <p>
	 * 		Esse metodo diz se o telefone passado ja esta adicionado na  lista
	 * 		de telefones dessa entrada.
	 * </p>
	 * @param telefone
	 * 		um telefone.
	 * @return
	 * 		true or false.
	 */
	public boolean contemTelefone(Telefone telefone){
		return telefones.contains(telefone);
	}
	/**
	 *<p>
	 *		Esse metodo remove um telefone da lista de telefones.
	 *</p> 
	 * @param telefone
	 * 		o telefone a ser removido.
	 */
	public void removerTelefone(Telefone telefone){
		if (contemTelefone(telefone)){
			telefones.remove(telefone);
		}
	}
	
	/**
	 * <p>
	 * 		Esse metodo pega um telefone por numero.
	 * 
	 * </p>
	 * @param numero
	 * 		O numero do telefone
	 * @return
	 * 		o telefone com o numero indicado.
	 */
	public Telefone getTelefonePorNumero(String numero){
		for (Telefone t : telefones){
			if ( t.getNumero().equals(numero)) return t;
		}
		return new Telefone();
	}
	
	/**
	 * <p>
	 * 		Esse metodo diz se e entrada esta vazia, uma entrada para o catalogo
	 * 		esta vazia se a lista de telefone, o contato e a lista de emails estiverem
	 * 		vazios.
	 * </p>
	 * @return
	 * 		true or false.
	 */
	public boolean isEmpty(){
		return emails.isEmpty() && telefones.isEmpty() && contato.isEmpty();
	}
	
	/**
	 * Retorna a quantidade total de emails adicionado para essas entrada.
	 * A quantidade de emails no MapEntry indica a quantidade de email 
	 * que esse contato tem adicionado.
	 *  
	 * @return
	 * 		a quantidade total de emails.
	 */
	public int getTotalDeEmails(){
		return emails.size();
	}
	/**
	 * <p>
	 * 		Esse metodo retorna a quantidade total de telefones adicionados
	 * 		nessa entrada.
	 * </p>
	 * @return
	 * 		a quantidade de telefones que foram registrados para esse contato.
	 */
	public int getTotalDeTelefones(){
		return telefones.size();
	}
	
}
