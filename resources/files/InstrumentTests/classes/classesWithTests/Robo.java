package lp2.lab06;

import java.util.ArrayList;

/**
 * Classe que serve para a criacao de um robo.
 * 
 * @author Igor de Araujo Meira
 * @version 1.0 - 12/07/2013
 *
 */

public class Robo {

	public Sala salaDoRobo;
	private int passosDoRobo;
	private int energiaDoRobo;
	private int[] posicaoDoRobo;
	
	//construtor
	/**
	 * Construtor de um Robo.
	 * 
	 * @param salaDoRobo - Sala onde o robo esta.
	 * @param energiaDoRobo - Energia que o robo possui.
	 * @throws Exception - Mensagem caso ocorra uma excecao.
	 */
	public Robo(Sala salaDoRobo, int energiaDoRobo) throws Exception {
		if(energiaDoRobo <= 0){
			throw new Exception("A energia do robo deve ser maior que zero.");
		}
		this.salaDoRobo = salaDoRobo;
		this.energiaDoRobo = energiaDoRobo;
		passosDoRobo = 0;
		iniciaRobo();	
	}
	
	private void iniciaRobo() {
		posicaoDoRobo = new int[2];
		posicaoDoRobo[0] = 0;
		posicaoDoRobo[1] = 0;
		ocupaPosicao();
	}

	//métodos
	/**
	 * Retorna a posicao do robo.
	 * 
	 * @return Uma string com a posicao do robo.
	 */
	public String getPosicao() {
		ArrayList<Integer> posicao = new ArrayList<Integer>();
		for (int i = 0; i < posicaoDoRobo.length; i++) {
			posicao.add(posicaoDoRobo[i]);
		}
		return posicao.toString();
	}
	
	/**
	 * Faz com que o robo movimente-se para cima.
	 * 
	 * @throws Exception - Mensagem caso ocorra excecao.
	 */
	public void movimentaCima() throws Exception {
		if(energiaDoRobo == 0){
			throw new Exception("O robo nao possui mais energia.");
		}
		if(!salaDoRobo.verificarPosicaoLivre(posicaoDoRobo[0] -1, posicaoDoRobo[1])) {
			if(!salaDoRobo.posicaoValida(posicaoDoRobo[0] -1, posicaoDoRobo[1])) {
				throw new Exception("Posicao invalida.");
			}
			throw new Exception("Posicao nao esta livre.");
		}
		liberaPosicao();
		posicaoDoRobo[0] -= 1;
		ocupaPosicao();
		passosDoRobo += 1;
		energiaDoRobo -= 1;
	}
	
	/**
	 * Faz com que o robo movimente-se para baixo.
	 * 
	 * @throws Exception - Mensagem caso ocorra excecao.
	 */
	public void movimentaBaixo() throws Exception {
		if(energiaDoRobo == 0){
			throw new Exception("O robo nao possui mais energia.");
		}
		if(!salaDoRobo.verificarPosicaoLivre(posicaoDoRobo[0] +1, posicaoDoRobo[1])) {
			if(!salaDoRobo.posicaoValida(posicaoDoRobo[0] +1, posicaoDoRobo[1])) {
				throw new Exception("Posicao invalida.");
			}
			throw new Exception("Posicao nao esta livre.");
		}
		liberaPosicao();
		posicaoDoRobo[0] += 1;
		ocupaPosicao();
		passosDoRobo += 1;
		energiaDoRobo -= 1;
	}
	
	/**
	 * Faz com que o robo movimente-se para a direita.
	 * 
	 * @throws Exception - Mensagem caso ocorra excecao.
	 */
	public void movimentaDireita() throws Exception {
		if(energiaDoRobo == 0){
			throw new Exception("O robo nao possui mais energia.");
		}
		if(!salaDoRobo.verificarPosicaoLivre(posicaoDoRobo[0], posicaoDoRobo[1] +1)) {
			if(!salaDoRobo.posicaoValida(posicaoDoRobo[0], posicaoDoRobo[1] +1)) {
				throw new Exception("Posicao invalida.");
			}
			throw new Exception("Posicao nao esta livre.");
		}
		liberaPosicao();
		posicaoDoRobo[1] += 1;
		ocupaPosicao();
		passosDoRobo += 1;
		energiaDoRobo -= 1;
	}
	
	/**
	 * Faz com que o robo movimente-se para a esquerda.
	 * 
	 * @throws Exception - Mensagem caso ocorra excecao.
	 */
	public void movimentaEsquerda() throws Exception {
		if(energiaDoRobo == 0){
			throw new Exception("O robo nao possui mais energia.");
		}
		if(!salaDoRobo.verificarPosicaoLivre(posicaoDoRobo[0], posicaoDoRobo[1] -1)) {
			if(!salaDoRobo.posicaoValida(posicaoDoRobo[0], posicaoDoRobo[1] -1)) {
				throw new Exception("Posicao invalida.");
			}
			throw new Exception("Posicao nao esta livre.");
		}
		liberaPosicao();
		posicaoDoRobo[1] -= 1;
		ocupaPosicao();
		passosDoRobo += 1;
		energiaDoRobo -= 1;
	}

	private void ocupaPosicao() {
		salaDoRobo.setPosicao(posicaoDoRobo[0], posicaoDoRobo[1], salaDoRobo.OCUPADO);
	}

	private void liberaPosicao() {
		salaDoRobo.setPosicao(posicaoDoRobo[0], posicaoDoRobo[1], salaDoRobo.LIVRE);		
	}

	/**
	 * Retorna a linha da sala em que o robo esta.
	 * 
	 * @return Um inteiro que representa a linha em que o robo esta.
	 */
	public int getLinha() {
		int linha;
		linha = posicaoDoRobo[0];
		return linha;
	}
	/**
	 * Retorna a coluna da sala em que o robo esta.
	 * 
	 * @return Um inteiro que representa a coluna em que esta o robo.
	 */
	public int getColuna() {
		int coluna;
		coluna = posicaoDoRobo[1];
		return coluna;
	}
	/**
	 * Retorna a quantidade de passos dados pelo robo.
	 * 
	 * @return Um inteiro que representa a quantidade de passos do robo.
	 */
	public int getPassos() {
		return passosDoRobo;
	}
	
	/**
	 * Retorna a quantidade de energia do robo.
	 * 
	 * @return Um inteiro que representa a energia que o robo possui.
	 */
	public int getEnergia() {
		return energiaDoRobo;
	}	
}