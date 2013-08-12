package controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import dao.Persistencia;

import model.CatalogoContatos;
import model.CatalogoUsuarios;
import model.Contato;
import model.EmptyFieldException;
import model.InvalidNumberException;
import model.MapEntry;
import model.Telefone;
import model.Usuario;

/**
 * 
 * <p>
 *   A classe <code>Agenda</code> eh responsavel por manipular as operacoes da agenda.
 *   Permite o controle e acesso aos dados da agenda e realizacao de buscas.
 * </p>
 * 
 * @author Renan
 * @author Samuel T. C. Santos
 * @version 1.0
 *
 */
public class Agenda implements Serializable {
	/**
	 * Esse catalogo eh carregado com as informacoes dos usuarios da agenda.
	 */
	private CatalogoUsuarios catalogo;
	/**
	 * Esse objeto permite o acessar os dados armazenados em disco.
	 */
	private Persistencia dao;
	
	/**
	 * Esse objeto representa o usario logado na agenda.
	 */
	private Usuario logado;
	
	/**
	 * Representa o contato que esta sendo acessado pelo usuario.
	 */
	private Contato ativo;
	
	/**
	 * Constroi uma agenda e ler a base de dados para carregar os dados no sistema.
	 */
	public Agenda(){
		catalogo = new CatalogoUsuarios();
		logado = new Usuario();
		ativo  = new Contato();
		
		lerBaseDeDados();
	}
	
	/**
	 * <p>Esse metodo ler a base de dados e carrega a informacoes no catalogo de dados
	 * do sistema. </p>
	 */
	public void lerBaseDeDados(){
		dao = new Persistencia();
		catalogo = dao.read();
	}
	
	/**
	 * <p>Esse metodo apaga completamente todos os dados da base de dados.</p>
	 */
	public void limparBaseDeDados(){
		catalogo.limpar();
		gravar();
	}
	
	/**
	 * <p>Esse metodo consulta a quantidade de usuarios que estao usando a agenda.</p>
	 * @return
	 * 		a quantidade de usuario cadastrados na basse de dados.
	 */
	public int consultarTamanhoDaBaseDeDados(){
		return catalogo.totalDeUsuarios();
	}
	
	/**
	 * <p>Esse metodo lista todos os usuarios cadastrados.</p>
	 */
	public List<Usuario> listarUsuarios(){
		return catalogo.listarUsuarios();
	}
	
	/**
	 * <p>
	 * 		Esse metodo retorna a lista de contatos do usuario logado.
	 * </p>
	 * @return
	 * 		uma lista de contatos.
	 */
	public List<Contato> listarContatos(){
		CatalogoContatos catalogoContatos = catalogo.getCatalogoContatos(logado);
		
		return catalogoContatos.getContatos();
	}
	
	/**
	 * <p>
	 * 		Pegar a lista de telefones para o contato ativo.
	 * </p>
	 * @return
	 */
	public List<Telefone> getTelefonesDoContatoAtivo(){
		CatalogoContatos catalogoContatos = catalogo.getCatalogoContatos(logado);
	
		return catalogoContatos.getTelefones();
	}
	
	
	/**
	 * <p>
	 *   Esse metodo permite logar um dos usuarios da agenda.
	 * </p>
	 * @param usuario
	 * 		nome do usuario (login)
	 * @param senha
	 * 		senha de acesso ao sistema (senha)
	 * @return
	 * 		true or false.
	 */
	public boolean fazLogin(String usuario, String senha){
		if ( esseUsuarioExiste(usuario, senha)){
			//agenda dever deslogar o ultimo login antes de fazer um novo.
			if (hasUsuarioLogado())
				fazLogoff();
			
			logarUsuario(usuario, senha);
			return true;
		}
		return false;
	}

	/***
	 * Esse metodo desloga o usuario logado no sistema.
	 * 
	 * @return
	 * 		true or false.
	 */
	public boolean fazLogoff(){
		if(hasUsuarioLogado()){
			logado.deslogar();
		}
		return !hasUsuarioLogado();
	}
	
	/**
	 * <p>
	 * 		Esse metodo loga o usuario que informou login e senha.
	 * </p>
	 * @param usuario
	 * 		o nome do usuario que esta fazendo o login.
	 * @param senha
	 * 		a senha do usuario.
	 */
	private void logarUsuario(String usuario, String senha) {
		logado = catalogo.getUsuario(usuario, senha);
		logado.logar();
	}
	
	/**
	 * <p>
	 * 		Esse metodo diz se ha um usuario ja logado na agenda.
	 * </p>
	 * @return
	 */
	public boolean hasUsuarioLogado(){
		return logado.isLogado();
	}
	
	/**
	 * <p>Esse metodo adiciona um novo usuario na agenda e sua lista de contatos.
	 *    Caso o usuario nao possui nenhum contanto a lista de contatos estara vazia.
	 * </p>
	 * @param usuario
	 * 		um usuario para ser cadastrado na agenda.
	 * @param catalogoContatos
	 * 		uma catalodo de contatos.
	 */
	public void adicionarUsuario(Usuario usuario, CatalogoContatos catalogoContatos){
		catalogo.adiciona(usuario, catalogoContatos);
		gravar();
	}
	
	/**
	 * Adiciona um novo usuario na agenda com login e senha.
	 * 
	 * @param usuario
	 * 		o login do usuario.
	 * @param senha
	 * 		a senha do usario.
	 */
	public void adicionarUsuario(String usuario, String senha){
		catalogo.adiciona(new Usuario(usuario,senha), new CatalogoContatos());
		gravar();
	}
	
	
	public void adicionarContato(String nome, int idade, String descricao){
		Contato contato = new Contato(nome, idade, descricao);
		
		CatalogoContatos catalogoContatos = catalogo.getCatalogoContatos(logado);
		
		catalogoContatos.addContato(contato, new ArrayList<Telefone>(), new ArrayList<String>());
		
		ativo = contato;
	}
	
	/**
	 * <p>
	 * 		Esse metodo tem a finalidade de atualizar um catalogo de contatos
	 * 		adicionando nele um novo telefone, para isso o sistema agenda
	 * 		ja tem o usuario logado e o contato ativo, essas duas variaveis 
	 * 		sao usandas como chaves principais para buscar na base de dados
	 * 		as informacoes necessarias para atualizar a base.
	 * </p>
	 * @param codPais
	 * @param codRegional
	 * @param codOperadora
	 * @param numero
	 * @param operadora
	 * @param operadoraLigar
	 */
	public void adicionarTelefone(String codPais, String codRegional, String codOperadora, String numero, String operadora, String operadoraLigar){
		Telefone telefone = new Telefone(codPais, codRegional,codOperadora, numero,operadora, operadoraLigar);
		
		CatalogoContatos catalogoContatos = catalogo.getCatalogoContatos(logado);
		
		MapEntry entrada = catalogoContatos.getEntry(ativo);
		
		entrada.adicionarTelefone(telefone);
		
		catalogoContatos.atualizarCatalogo(entrada);
		
		//registrar as informacoes na base de dados.
		gravar();
	}
	
	/**
	 * <p>
	 * 		Esse metodo eh semelhante ao metodo adicionar telefone, porem 
	 * 		faz a adicao de emails.
	 * </p>
	 * @param email
	 */
	public void adicionarEmail(String email){
		
		CatalogoContatos catalogoContatos = catalogo.getCatalogoContatos(logado);
		
		MapEntry entrada = catalogoContatos.getEntry(ativo);
		
		entrada.adicionarEmail(email);
		
		catalogoContatos.atualizarCatalogo(entrada);
		
		//adicionar informacoes na base de dados.
		gravar();
	}
	
	
	/**
	 * <p>
	 * 		Esse metodo remove um usario da agenda informando o nome e senha do
	 *      usuario.
	 * </p>
	 * @param nomeUsuario
	 * 		O nome do usuario.
	 * @param senha
	 * 		A senha do usario.
	 */
	public void removerUsuario(String nomeUsuario, String senha){
		if (esseUsuarioExiste(nomeUsuario, senha)){
			catalogo.remove(nomeUsuario,senha);
		}
		gravar();
	}

	/**
	 * <p>
	 * 		Esse metodo verifica se o usuario existe, ou seja, se esta cadastrado 
	 * 		na agenda.
	 * </p>
	 * @param nomeUsuario
	 * 		o nome de usuario.
	 * @param senha
	 * 		a senha do usuario.
	 * @return
	 */
	private boolean esseUsuarioExiste(String nomeUsuario, String senha) {
		return catalogo.existeUsuario(nomeUsuario, senha);
	}
	
	/**
	 * <p>Esse metodo diz se a agenda esta vazia.</p>
	 * @return
	 * 		true or false.
	 */
	public boolean isEmpty(){
		return consultarTamanhoDaBaseDeDados() == 0;
	}
	
	/**
	 * <p>
	 *    Esse metodo realiza a escrita de dados na base de dados eh utilizado
	 *    em todos as operacoes que resultam em modificacoes na base de dados, 
	 *    adicionar, remover e atualizar, essas operacoes afetam o estado dos 
	 *    dados armazenados, para isso a cada operacao desse tipo eh realizada
	 *    a reescrita dos dados persistidos para que nao haja perda de informacoes
	 *    ou para que o sistema nao sofra com a desincronizacao entre os dados da 
	 *    memoria principal e os dados persistidos no arquivo.
	 * </p>
	 */
	public void gravar(){
		dao.write(catalogo);
	}
	
	/**
	 * <p>Esse metodo lista todos os usuario cadastrados na agenda.</p>
	 */
	public void listarBaseDeDados(){
		List<Usuario> lista = catalogo.listarUsuarios();
		for (Usuario u : lista){
			System.out.println(u.toString());
		}
	}
	
	/**
	 * <p>
	 * 		Sempre qeu o usuario acessar informacoes sobre um contato de sua
	 * 		lista de contatos, esse automaticamente sera considerado o contato 
	 * 		ativo que recebe acesso direto e podera ter seus dados modificado
	 * 		conforme as acoes do usuario.
	 * </p>
	 * @return
	 * 		O usuario ativo.
	 */
	public Contato getContatoAtivo(){
		return ativo;
	}
	
	/**
	 * <p>
	 * 		Para que o usuario consiga utilizar sua agenda ele devera logar
	 * 		com um usuario, o usuario que esta logado podera ser acessado
	 * 		atraves desse metodo, com o usuario logado eh possivel acessar
	 * 		qual o catalogo de contatos qeu esse usuario possui, ou seja,
	 * 		a partir desse objetos podemos localizar na base de dados in-
	 * 		formacoes pertimentes a sua lista de contatos.
	 * </p>
	 * @return
	 */
	public Usuario getUsuarioLogado(){
		return logado;
	}
	
	/**
	 * <p>
	 * 		Esse metodo retorna a quantidade total de usuario cadastrados 	
	 * 		na agenda.
	 * </p>
	 * @return
	 * 		a quantidade de usuarios cadastrados na agenda.
	 */
	public int getTotalDeUsuarios(){
		return listarUsuarios().size();
	}
	
	/**
	 * <p>
	 * 		Esse metodo retorna o catalogo de contatos do usario logado.
	 * 		A partir do usuario logado a agenda permite localizar seu
	 * 		catalogo de contatos, no catalogo de contatos estao todos
	 * 		os contatos desse usuario e a respectivas listas de emails
	 * 		e telefones para cada um dos contatos.
	 * </p>
	 * @return
	 * 		O catalogo de contatos.
	 */
	public CatalogoContatos getCatalogoDoUsuarioLogado(){
		return catalogo.getCatalogoContatos(logado);
	}
	
	/**
	 * <p>
	 * 		O objeto <code>MapEntry</code> foi projetado como uma solucao
	 * 		rapida e eficiente de reunir em um unico objeto os dados de
	 * 		um contato, ou seja, o contato, a lista de telefones e a lista
	 * 		de emails, esses tres dados podem ser editados a partir de 
	 * 		um objeto <code>MapEntry</code> e em seguinda podem ser
	 * 		devolvido ao catalogo para serem atualizado, abstraindo do programador
	 * 		a forma como essa atualizacao acontece, o programador apenas	
	 * 		precisara manipular o objeto MapEntry e ao final passa-lo novamente
	 * 		para o catalogo usando o metodo atualizarEntrada.
	 * </p>
	 * @return
	 * 		Um objeto do tipo <code>MapEntry</code>.
	 */
	public MapEntry getMapEntry(){
		return getCatalogoDoUsuarioLogado().getEntry(ativo);
	}
	
	/**
	 * <p>
	 * 		Esse metodo retorna a quantidade total de contato do usuario logado.
	 * </p>
	 * 
	 * @return	
	 * 		o total de contato do usuario logado.
	 */
	public int getTotalDeContatos(){
		return getCatalogoDoUsuarioLogado().getTotalDeContatos();
	}
	
	/**
	 * <p>
	 * 		Esse metodo retorna o total de telefones do contato ativo.
	 * </p>
	 * @return
	 * 		o total de telefones do contato ativo.
	 */
	public int getTotalDeTelefones(){
		return getMapEntry().getTotalDeTelefones();
	}
	
	/**
	 * <p>
	 * 		Esse metodo retorna o total de emails do contato ativo.
	 * </p>
	 * @return
	 * 		o total de telefones.
	 */
	public int getTotalDeEmails(){
		return getMapEntry().getTotalDeEmails();
	}

	/**
	 * <p>
	 * 		Esse metodo ativa o contato de nome informado.
	 * </p>
	 * @param nomeContatoAtivo
	 * @return
	 */
	public void ativeContato(String nomeContatoAtivo) {
		for (Contato c : getCatalogoDoUsuarioLogado().getContatos()){
			if (c.getNome().equals(nomeContatoAtivo)){
				ativo = c;
			}
		}
	}
}
