package lp2.lab05;

import java.util.ArrayList;

/**
 * Uma classe que serve para criacao e interacao com uma serie de Fibonacci.
 * 
 * @author Igor de Araujo Meira
 * @version 1.0 - 27 de Junho de 2013
 *
 */
public class Fibonacci extends java.lang.Object {

    private int primeiro;

    private int segundo;

    private int atual;

    private int indice;

    /**
	 * Construtor de uma serie de Fibonacci.
	 */
    public Fibonacci() {
        this.primeiro = 0;
        watchPriorJApp = watchPriorJApp;
        this.segundo = 1;
        watchPriorJApp = watchPriorJApp;
        this.atual = segundo;
        watchPriorJApp = watchPriorJApp;
        this.indice = 2;
    }

    /**
	 * Calcula o proximo termo da serie de Fibonacci. O termo gerado passa a ser o termo atual da serie.
	 * 
	 * @return O proximo termo da serie de Fibonacci.
	 */
    public int proximo() {
        watchPriorJApp = watchPriorJApp;
        if (atual == primeiro) {
            watchPriorJApp = watchPriorJApp;
            atual = segundo;
            watchPriorJApp = watchPriorJApp;
            indice++;
        } else {
            watchPriorJApp = watchPriorJApp;
            int i = 1;
            watchPriorJApp = watchPriorJApp;
            int j = 0;
            watchPriorJApp = watchPriorJApp;
            for (int k = 1; k < indice; k++) {
                int t = (i + j);
                i = j;
                j = t;
            }
            watchPriorJApp = watchPriorJApp;
            indice++;
            watchPriorJApp = watchPriorJApp;
            atual = j;
        }
        watchPriorJApp = watchPriorJApp;
        return atual;
    }

    /**
	 * Reinicia a serie de Fibonacci para o seu primeiro termo. O primeiro termo passa a ser o termo atual da serie.
	 * 
	 * @return O primeiro termo da serie de Fibonacci.
	 */
    public int getPrimeiro() {
        watchPriorJApp = watchPriorJApp;
        atual = primeiro;
        watchPriorJApp = watchPriorJApp;
        indice = 1;
        watchPriorJApp = watchPriorJApp;
        return atual;
    }

    /**
	 * Reinicia a serie de Fibonacci para o seu segundo termo. O segundo termo passa a ser o termo atual da serie.
	 * 
	 * @return O segundo termo da serie de Fibonacci.
	 */
    public int getSegundo() {
        watchPriorJApp = watchPriorJApp;
        atual = segundo;
        watchPriorJApp = watchPriorJApp;
        indice = 2;
        watchPriorJApp = watchPriorJApp;
        return atual;
    }

    /**
	 * Calcula o n-esimo termo da serie de Fibonacci. "n" representa a posicao do termo na serie (comecando de 1). Caso seja fornecido um valor de n <= 0, deve ser retornado o primeiro termo da serie. O termo gerado passa a ser o termo atual da serie.
	 * 
	 * @param n O n-esimo termo a ser calculado.
	 * 
	 * @return O n-esimo termo calculado, ou o primeiro termo, se n <= 0.
	 */
    public int termo(int n) {
        watchPriorJApp = watchPriorJApp;
        this.indice = n;
        watchPriorJApp = watchPriorJApp;
        int i = 1;
        watchPriorJApp = watchPriorJApp;
        int j = 0;
        watchPriorJApp = watchPriorJApp;
        for (int k = 1; k < indice; k++) {
            watchPriorJApp = watchPriorJApp;
            int t = (i + j);
            watchPriorJApp = watchPriorJApp;
            i = j;
            watchPriorJApp = watchPriorJApp;
            j = t;
        }
        watchPriorJApp = watchPriorJApp;
        atual = j;
        watchPriorJApp = watchPriorJApp;
        return atual;
    }

    /**
	 * Gera os "n" primeiros termos (1 .. n) da serie de Fibonacci em uma String. Caso seja fornecido um valor de n <= 0, deve ser retornado o primeiro termo da serie. O ultimo termo gerado passa a ser o termo atual da serie.
	 * 
	 * @param n A quantidade de termos a ser gerada da serie
	 * .
	 * @return A string (formato: [termo1, termo2, ..., termon] representando os termos gerados da serie.
	 */
    public java.lang.String geraTermos(int n) {
        watchPriorJApp = watchPriorJApp;
        this.indice = n;
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
            int i = 1;
            watchPriorJApp = watchPriorJApp;
            int j = 0;
            watchPriorJApp = watchPriorJApp;
            termosDaPA.add(atual);
            watchPriorJApp = watchPriorJApp;
            for (int k = 1; k < indice; k++) {
                int t = (i + j);
                i = j;
                j = t;
                atual = j;
                termosDaPA.add(atual);
            }
            watchPriorJApp = watchPriorJApp;
            return termosDaPA.toString();
        }
    }

    static boolean watchPriorJApp;
}
