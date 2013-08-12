package lp2.lab06;

/**
 * Classe que serve para a criacao de uma sala.
 * 
 * @author Igor de Araujo Meira
 * @version 1.0 - 12/07/2013
 *
 */

public class Sala {
	
	private int linha;
	private int coluna;
	private int[][] sala;
	public static final int OCUPADO = 0;
	public static final int LIVRE = 1;
	
	//construtor
	/**
	 * Construtor de uma sala.
	 * 
	 * @param linha - Numeros de linhas que a sala possui.
	 * @param coluna - Numeros de colunas que a sala possui.
	 * @throws Exception - Mensagem caso ocorra excecao.
	 */
	public Sala(int linha, int coluna) throws Exception{
		if(linha <= 0 || coluna <= 0){
			throw new Exception("Parâmetros incorretos para a criação da sala.");
		}
		this.linha = linha;
		this.coluna = coluna;
		iniciarSala();
	}
	
	private void iniciarSala(){
		sala = new int[linha][coluna];
		for (int i = 0; i < linha; i++) {
			for (int j = 0; j < coluna; j++ ){
				sala[i][j] = LIVRE;
			}
		}
	}

	//métodos
	/**
	 * Checa se a posicao informada eh valida.
	 * 
	 * @param i - Linha da sala.
	 * @param j - Coluna da sala.
	 * @return true se a posicao for valida, false se nao for.
	 */
	public boolean posicaoValida(int i, int j) {
		if(i < 0 || j < 0){
			return false;
		}if(i >= linha || j >= coluna){
			return false;
		}
		return true;
		
	}

	/**
	 * Insere obstaculos dentro da sala.
	 * 
	 * @param i - Linha da sala.
	 * @param j - Coluna da sala.
	 * @return true se puder colocar o obstaculo na posicao, false caso contrario.
	 */
	public boolean inserirObstaculo(int i, int j) {
		if(verificarPosicaoLivre(i,j)){
			sala[i][j] = OCUPADO;
			return true;
		}
		return false;
	}

	/**
	 * Verifica se uma posicao da sala esta livre.
	 * 
	 * @param i - Linha da sala.
	 * @param j - Coluna da sala.
	 * @return true se a posicao estiver livre e for valida, false se nao estiver livre ou nao for valida.
	 */
	public boolean verificarPosicaoLivre(int i, int j) {
		if(posicaoValida(i,j) && sala[i][j] == LIVRE){
			return true;
		}
		return false;
	}

	/**
	 * Torna um posicao livre ou ocupada.
	 * 
	 * @param i - Linha da sala.
	 * @param j - Coluna da sala.
	 * @param status - Qual o estado em que posicao deve ficar, livre ou ocupado.
	 * @return true se a modificacao puder ser feita, false caso nao.
	 */
	public boolean setPosicao(int i, int j, int status) {
		if(posicaoValida(i,j)){
			sala[i][j] = status;
			return true;
		}
		
		return false;
	}
}
