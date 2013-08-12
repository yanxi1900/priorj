package model;

import java.io.Serializable;

/**
 * Essa classe representa um telefone, com codigo, operadora, operadora ligar e numero.
 * 
 * @author Renan
 * @author Samuel T. C. Santos
 *
 */
public class Telefone implements Serializable {
	
	/**
	 * default Serial version UID 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Codigo de area utilizado pela operadora para identificar o estado.
	 */
	private String codigoRegional;
	/**
	 * Codigo da operadora de telefonica.
	 */
	private String codigoOperadora;
	/**
	 * Operadora configurada para ligar efetuar ligacoes.
	 */
	private String operadoraLigar;
	
	/**
	 * Usando para representar o nome das operadoras, TIM, Vivo, Oi, etc.
	 */
	private String nomeDaOperadora;
	/**
	 * Usando para telefones internacionais representa o codigo do pais.
	 */
	private String codigoDoPais;
	
	/**
	 * Numero do telefone
	 */
	private String numero;
	
	/**
	 * Default constructor.
	 */
	public Telefone(){
		numero="";
		operadoraLigar = "";
		codigoOperadora = "";
		codigoRegional = "";
		codigoDoPais = "";
		nomeDaOperadora="";
	}
	
	/**
	 * <p>
	 * 		Cria um telefone informando o codigo do pais, codigo regional, codigo da 
	 * 		operadora, numero do telefone, nome da operadora e operadora codigo da
	 * 		operadora programada para efetuar ligacoes.
	 * </p>
	 * @param codigoDoPais
	 * 		codigo do pais
	 * @param codigoRegional
	 * 		codigo da area da regiao
	 * @param codigoOperadora
	 * 		codigo da operadora
	 * @param numero
	 * 		numero do telefone
	 * @param nomeDaOperadora
	 * 		nome da operadora de telefonia
	 * @param operadoraLigar
	 * 		codigo da operadora programada para efetuar chamadas.
	 */
	public Telefone(String codigoDoPais, String codigoRegional, String codigoOperadora,
			String numero, String nomeDaOperadora,  String operadoraLigar) {
		this.codigoRegional = codigoRegional;
		this.codigoOperadora = codigoOperadora;
		this.operadoraLigar = operadoraLigar;
		this.nomeDaOperadora = nomeDaOperadora;
		this.codigoDoPais = codigoDoPais;
		this.numero = numero;
	}

	/**
	 * <p>
	 * 		pegar o codigo regional.
	 * </p>
	 * @return
	 * 		o codigo reginal.
	 */
	public String getCodigoRegional() {
		return codigoRegional;
	}

	/**
	 * <p>
	 * 		enviar o codigo regional.
	 * </p>
	 * @param codigoRegional
	 * 		o codigo regional.
	 */
	public void setCodigoRegional(String codigoRegional) {
		this.codigoRegional = codigoRegional;
	}

	/**
	 * Pegar o codigo da operadora.
	 * @return
	 * 		o codigo da operadora.
	 */
	public String getCodigoOperadora() {
		return codigoOperadora;
	}

	/**
	 * Enviar o codigo da operadora.
	 * @param codigoOperadora
	 * 		o codigo da operadora
	 */
	public void setCodigoOperadora(String codigoOperadora) {
		this.codigoOperadora = codigoOperadora;
	}
	/**
	 * Pegar a operadora definida para ligar.
	 * @return
	 * 		a operadora programada para ligar.
	 */
	public String getOperadoraLigar() {
		return operadoraLigar;
	}

	/**
	 * Enviar a operadora para ligar.
	 * @param operadoraLigar
	 * 		numero da operadora para ligar.
	 */
	public void setOperadoraLigar(String operadoraLigar) {
		this.operadoraLigar = operadoraLigar;
	}

	/**
	 * <p>
	 * Get o numero do telefone, cada telefone eh representado
	 * por uma string no formato ####-####.
	 * 
	 * </p>
	 * @return
	 * 		o numero do telefone.
	 */
	public String getNumero() {
		return numero;
	}
	
	/**
	 * Enviar o numero do telefone.
	 * @param numero
	 */
	public void setNumero(String numero) {
		this.numero = numero;
	}

	/**
	 * Verficar se o telefone eh valido.
	 * @return
	 * 		true or false.
	 */
	public boolean isValido() {
		return !isEmpty();
	}

	/**
	 * Pegar o nome da operadora.
	 * @return
	 * 		O nome da operadora.
	 */
	public String getNomeDaOperadora() {
		return nomeDaOperadora;
	}
	/**
	 * Enviar o nome da operadora.
	 * @param nomeDaOperadora
	 * 		O nome da operadora.
	 */
	public void setNomeDaOperadora(String nomeDaOperadora) {
		this.nomeDaOperadora = nomeDaOperadora;
	}
	/**
	 * Pegar o codigo do pais.
	 * @return
	 * 		O codigo do pais.
	 */
	public String getCodigoDoPais() {
		return codigoDoPais;
	}
	/**
	 * Enviar o codigo do pais.
	 * @param codigoDoPais
	 */
	public void setCodigoDoPais(String codigoDoPais) {
		this.codigoDoPais = codigoDoPais;
	}

	/**
	 * <p>
	 * 		Esse metodo diz se todos os campos de um telefone estao vazios.
	 * </p>
	 * @return
	 * 		true or false.
	 */
	public boolean isEmpty(){
		return codigoDoPais.isEmpty() &&
			   codigoRegional.isEmpty() &&
			   codigoOperadora.isEmpty() &&
			   nomeDaOperadora.isEmpty() &&
			   operadoraLigar.isEmpty() &&
			   numero.isEmpty();
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Telefone other = (Telefone) obj;
		if (codigoDoPais == null) {
			if (other.codigoDoPais != null)
				return false;
		} else if (!codigoDoPais.equals(other.codigoDoPais))
			return false;
		if (codigoOperadora == null) {
			if (other.codigoOperadora != null)
				return false;
		} else if (!codigoOperadora.equals(other.codigoOperadora))
			return false;
		if (codigoRegional == null) {
			if (other.codigoRegional != null)
				return false;
		} else if (!codigoRegional.equals(other.codigoRegional))
			return false;
		if (nomeDaOperadora == null) {
			if (other.nomeDaOperadora != null)
				return false;
		} else if (!nomeDaOperadora.equals(other.nomeDaOperadora))
			return false;
		if (numero == null) {
			if (other.numero != null)
				return false;
		} else if (!numero.equals(other.numero))
			return false;
		if (operadoraLigar == null) {
			if (other.operadoraLigar != null)
				return false;
		} else if (!operadoraLigar.equals(other.operadoraLigar))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		
		builder.append(codigoDoPais);
		builder.append(" ");
		builder.append(codigoRegional);
		builder.append(" ");
		builder.append(codigoOperadora);
		builder.append(" ");
		builder.append(numero);
		builder.append(" ");
		builder.append(nomeDaOperadora);
		builder.append(" ");
		builder.append("Ligar:");
		builder.append(" ");
		builder.append(operadoraLigar);
		
		return builder.toString();
	}
	
	
	
	
}
