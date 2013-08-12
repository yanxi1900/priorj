package lp2.lab05;

import java.util.ArrayList;

/**
 * Uma classe que serve para criacao e interacao com uma Progressao Aritmetica.
 * 
 * @author Igor de Araujo Meira
 * @version 1.0 - 27 de Junho de 2013
 *
 */

public class ProgressaoAritmetica extends java.lang.Object {

	private int primeiro;
	
	private int atual;
	
	private int razao;
	
	//construtor
	/**
	    * Construtor de uma progressao aritmetica.
	    *
	    * @param primeiro - O primeiro termo da progressao aritmetica.
	    * 
	    * @param razao - A razao da progressao aritmetica.
	    */
	public ProgressaoAritmetica(int primeiro, int razao){
		this.primeiro = primeiro;
		this.razao = razao;
		this.atual = primeiro;
	}
	
	//métodos
	
	   /**
	    * Recupera a razao a progressao aritmetica.
	    *
	    * @return A razao da progressao aritmetica.
	    */
	public int getRazao(){
		return razao;		
	}
	
	   /**
	    * Calcula o proximo termo da progressao aritmetica. O termo gerado passa a ser o termo atual da progressao.
	    *
	    * @return O proximo termo da progessao aritmetica.
	    */
	public int proximo(){
		atual = (atual + razao);
		return atual;
	}
	
	   /**
	    * Reinicia a progressao aritmetica para o seu primeiro termo. O primeiro termo passa a ser o termo atual da progressao.
	    *
	    * @return O primeiro termo da progressao aritmetica.
	    */
	public int primeiro(){
		atual = primeiro;
		return primeiro;
	}
	
	   /**
	    * Calcula o n-esimo termo da progessao aritmetica. "n" representa a posicao do termo na progessao (comecando de 1). Caso seja fornecido um valor de n <= 0, deve ser retornado o primeiro termo da progressao. O termo gerado passa a ser o termo atual da progressao.
	    * 
	    * @param n - O n-esimo termo a ser calculado.
	    *
	    * @return O n-esimo termo calculado, ou o primeiro termo, se n <= 0.
	    */
	public int termo(int n){
		if(n <= 0){
			return primeiro;
		}else{
			atual = (primeiro + ((n-1) * razao));
			return atual;
				
			}
		}
	
	   /**
	    * Gera os "n" primeiros termos (1 .. n) da progressao aritmetica em uma String. Caso seja fornecido um valor de n <= 0, deve ser retornado o primeiro termo da progressao. O ultimo termo gerado passa a ser o termo atual da progessao.
	    * 
	    * @param n - A quantidade de termos a ser gerada da progressao.
	    *
	    * @return A string (formato: [termo1, termo2, ..., termon] representando os termos gerados da progressao.
	    */
	public java.lang.String geraTermos(int n){		
		ArrayList<Integer> termosDaPA = new ArrayList<Integer>();
		if(n <= 0){
			termosDaPA.add(primeiro);
			return termosDaPA.toString();
		}else{
			atual = primeiro;
			for (int i = 1; i <= n; i++) {
				termosDaPA.add(atual);
				atual += razao;
			}
			return termosDaPA.toString();
		}
	}	
}