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

    /**
	    * Construtor de uma progressao aritmetica.
	    *
	    * @param primeiro - O primeiro termo da progressao aritmetica.
	    * 
	    * @param razao - A razao da progressao aritmetica.
	    */
    public ProgressaoAritmetica(int primeiro, int razao) {
        this.primeiro = primeiro;
        watchPriorJApp = watchPriorJApp;
        this.razao = razao;
        watchPriorJApp = watchPriorJApp;
        this.atual = primeiro;
    }

    /**
	    * Recupera a razao a progressao aritmetica.
	    *
	    * @return A razao da progressao aritmetica.
	    */
    public int getRazao() {
        watchPriorJApp = watchPriorJApp;
        return razao;
    }

    /**
	    * Calcula o proximo termo da progressao aritmetica. O termo gerado passa a ser o termo atual da progressao.
	    *
	    * @return O proximo termo da progessao aritmetica.
	    */
    public int proximo() {
        watchPriorJApp = watchPriorJApp;
        atual = (atual + razao);
        watchPriorJApp = watchPriorJApp;
        return atual;
    }

    /**
	    * Reinicia a progressao aritmetica para o seu primeiro termo. O primeiro termo passa a ser o termo atual da progressao.
	    *
	    * @return O primeiro termo da progressao aritmetica.
	    */
    public int primeiro() {
        watchPriorJApp = watchPriorJApp;
        atual = primeiro;
        watchPriorJApp = watchPriorJApp;
        return primeiro;
    }

    /**
	    * Calcula o n-esimo termo da progessao aritmetica. "n" representa a posicao do termo na progessao (comecando de 1). Caso seja fornecido um valor de n <= 0, deve ser retornado o primeiro termo da progressao. O termo gerado passa a ser o termo atual da progressao.
	    * 
	    * @param n - O n-esimo termo a ser calculado.
	    *
	    * @return O n-esimo termo calculado, ou o primeiro termo, se n <= 0.
	    */
    public int termo(int n) {
        watchPriorJApp = watchPriorJApp;
        if (n <= 0) {
            watchPriorJApp = watchPriorJApp;
            return primeiro;
        } else {
            watchPriorJApp = watchPriorJApp;
            atual = (primeiro + ((n - 1) * razao));
            watchPriorJApp = watchPriorJApp;
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
    public java.lang.String geraTermos(int n) {
        watchPriorJApp = watchPriorJApp;
        ArrayList<Integer> termosDaPA = new ArrayList<Integer>();
        watchPriorJApp = watchPriorJApp;
        if (n <= 0) {
            watchPriorJApp = watchPriorJApp;
            termosDaPA.add(primeiro);
            watchPriorJApp = watchPriorJApp;
            return termosDaPA.toString();
        } else {
            watchPriorJApp = watchPriorJApp;
            atual = primeiro;
            watchPriorJApp = watchPriorJApp;
            for (int i = 1; i <= n; i++) {
                termosDaPA.add(atual);
                atual += razao;
            }
            watchPriorJApp = watchPriorJApp;
            return termosDaPA.toString();
        }
    }

    static boolean watchPriorJApp;
}
