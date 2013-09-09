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

    /**
	 * Construtor de um Robo.
	 * 
	 * @param salaDoRobo - Sala onde o robo esta.
	 * @param energiaDoRobo - Energia que o robo possui.
	 * @throws Exception - Mensagem caso ocorra uma excecao.
	 */
    public Robo(Sala salaDoRobo, int energiaDoRobo) throws Exception {
        if (energiaDoRobo <= 0) {
            watchPriorJApp = watchPriorJApp;
            throw new Exception("A energia do robo deve ser maior que zero.");
        }
        watchPriorJApp = watchPriorJApp;
        this.salaDoRobo = salaDoRobo;
        watchPriorJApp = watchPriorJApp;
        this.energiaDoRobo = energiaDoRobo;
        watchPriorJApp = watchPriorJApp;
        passosDoRobo = 0;
        watchPriorJApp = watchPriorJApp;
        iniciaRobo();
    }

    private void iniciaRobo() {
        watchPriorJApp = watchPriorJApp;
        posicaoDoRobo = new int[2];
        watchPriorJApp = watchPriorJApp;
        posicaoDoRobo[0] = 0;
        watchPriorJApp = watchPriorJApp;
        posicaoDoRobo[1] = 0;
        watchPriorJApp = watchPriorJApp;
        ocupaPosicao();
    }

    /**
	 * Retorna a posicao do robo.
	 * 
	 * @return Uma string com a posicao do robo.
	 */
    public String getPosicao() {
        watchPriorJApp = watchPriorJApp;
        ArrayList<Integer> posicao = new ArrayList<Integer>();
        watchPriorJApp = watchPriorJApp;
        for (int i = 0; i < posicaoDoRobo.length; i++) {
            watchPriorJApp = watchPriorJApp;
            posicao.add(posicaoDoRobo[i]);
        }
        watchPriorJApp = watchPriorJApp;
        return posicao.toString();
    }

    /**
	 * Faz com que o robo movimente-se para cima.
	 * 
	 * @throws Exception - Mensagem caso ocorra excecao.
	 */
    public void movimentaCima() throws Exception {
        watchPriorJApp = watchPriorJApp;
        if (energiaDoRobo == 0) {
            watchPriorJApp = watchPriorJApp;
            throw new Exception("O robo nao possui mais energia.");
        }
        watchPriorJApp = watchPriorJApp;
        if (!salaDoRobo.verificarPosicaoLivre(posicaoDoRobo[0] - 1, posicaoDoRobo[1])) {
            watchPriorJApp = watchPriorJApp;
            if (!salaDoRobo.posicaoValida(posicaoDoRobo[0] - 1, posicaoDoRobo[1])) {
                watchPriorJApp = watchPriorJApp;
                throw new Exception("Posicao invalida.");
            }
            watchPriorJApp = watchPriorJApp;
            throw new Exception("Posicao nao esta livre.");
        }
        watchPriorJApp = watchPriorJApp;
        liberaPosicao();
        watchPriorJApp = watchPriorJApp;
        posicaoDoRobo[0] -= 1;
        watchPriorJApp = watchPriorJApp;
        ocupaPosicao();
        watchPriorJApp = watchPriorJApp;
        passosDoRobo += 1;
        watchPriorJApp = watchPriorJApp;
        energiaDoRobo -= 1;
    }

    /**
	 * Faz com que o robo movimente-se para baixo.
	 * 
	 * @throws Exception - Mensagem caso ocorra excecao.
	 */
    public void movimentaBaixo() throws Exception {
        watchPriorJApp = watchPriorJApp;
        if (energiaDoRobo == 0) {
            watchPriorJApp = watchPriorJApp;
            throw new Exception("O robo nao possui mais energia.");
        }
        watchPriorJApp = watchPriorJApp;
        if (!salaDoRobo.verificarPosicaoLivre(posicaoDoRobo[0] + 1, posicaoDoRobo[1])) {
            watchPriorJApp = watchPriorJApp;
            if (!salaDoRobo.posicaoValida(posicaoDoRobo[0] + 1, posicaoDoRobo[1])) {
                watchPriorJApp = watchPriorJApp;
                throw new Exception("Posicao invalida.");
            }
            watchPriorJApp = watchPriorJApp;
            throw new Exception("Posicao nao esta livre.");
        }
        watchPriorJApp = watchPriorJApp;
        liberaPosicao();
        watchPriorJApp = watchPriorJApp;
        posicaoDoRobo[0] += 1;
        watchPriorJApp = watchPriorJApp;
        ocupaPosicao();
        watchPriorJApp = watchPriorJApp;
        passosDoRobo += 1;
        watchPriorJApp = watchPriorJApp;
        energiaDoRobo -= 1;
    }

    /**
	 * Faz com que o robo movimente-se para a direita.
	 * 
	 * @throws Exception - Mensagem caso ocorra excecao.
	 */
    public void movimentaDireita() throws Exception {
        watchPriorJApp = watchPriorJApp;
        if (energiaDoRobo == 0) {
            watchPriorJApp = watchPriorJApp;
            throw new Exception("O robo nao possui mais energia.");
        }
        watchPriorJApp = watchPriorJApp;
        if (!salaDoRobo.verificarPosicaoLivre(posicaoDoRobo[0], posicaoDoRobo[1] + 1)) {
            watchPriorJApp = watchPriorJApp;
            if (!salaDoRobo.posicaoValida(posicaoDoRobo[0], posicaoDoRobo[1] + 1)) {
                watchPriorJApp = watchPriorJApp;
                throw new Exception("Posicao invalida.");
            }
            watchPriorJApp = watchPriorJApp;
            throw new Exception("Posicao nao esta livre.");
        }
        watchPriorJApp = watchPriorJApp;
        liberaPosicao();
        watchPriorJApp = watchPriorJApp;
        posicaoDoRobo[1] += 1;
        watchPriorJApp = watchPriorJApp;
        ocupaPosicao();
        watchPriorJApp = watchPriorJApp;
        passosDoRobo += 1;
        watchPriorJApp = watchPriorJApp;
        energiaDoRobo -= 1;
    }

    /**
	 * Faz com que o robo movimente-se para a esquerda.
	 * 
	 * @throws Exception - Mensagem caso ocorra excecao.
	 */
    public void movimentaEsquerda() throws Exception {
        watchPriorJApp = watchPriorJApp;
        if (energiaDoRobo == 0) {
            watchPriorJApp = watchPriorJApp;
            throw new Exception("O robo nao possui mais energia.");
        }
        watchPriorJApp = watchPriorJApp;
        if (!salaDoRobo.verificarPosicaoLivre(posicaoDoRobo[0], posicaoDoRobo[1] - 1)) {
            watchPriorJApp = watchPriorJApp;
            if (!salaDoRobo.posicaoValida(posicaoDoRobo[0], posicaoDoRobo[1] - 1)) {
                watchPriorJApp = watchPriorJApp;
                throw new Exception("Posicao invalida.");
            }
            watchPriorJApp = watchPriorJApp;
            throw new Exception("Posicao nao esta livre.");
        }
        watchPriorJApp = watchPriorJApp;
        liberaPosicao();
        watchPriorJApp = watchPriorJApp;
        posicaoDoRobo[1] -= 1;
        watchPriorJApp = watchPriorJApp;
        ocupaPosicao();
        watchPriorJApp = watchPriorJApp;
        passosDoRobo += 1;
        watchPriorJApp = watchPriorJApp;
        energiaDoRobo -= 1;
    }

    private void ocupaPosicao() {
        watchPriorJApp = watchPriorJApp;
        salaDoRobo.setPosicao(posicaoDoRobo[0], posicaoDoRobo[1], salaDoRobo.OCUPADO);
    }

    private void liberaPosicao() {
        watchPriorJApp = watchPriorJApp;
        salaDoRobo.setPosicao(posicaoDoRobo[0], posicaoDoRobo[1], salaDoRobo.LIVRE);
    }

    /**
	 * Retorna a linha da sala em que o robo esta.
	 * 
	 * @return Um inteiro que representa a linha em que o robo esta.
	 */
    public int getLinha() {
        watchPriorJApp = watchPriorJApp;
        int linha;
        watchPriorJApp = watchPriorJApp;
        linha = posicaoDoRobo[0];
        watchPriorJApp = watchPriorJApp;
        return linha;
    }

    /**
	 * Retorna a coluna da sala em que o robo esta.
	 * 
	 * @return Um inteiro que representa a coluna em que esta o robo.
	 */
    public int getColuna() {
        watchPriorJApp = watchPriorJApp;
        int coluna;
        watchPriorJApp = watchPriorJApp;
        coluna = posicaoDoRobo[1];
        watchPriorJApp = watchPriorJApp;
        return coluna;
    }

    /**
	 * Retorna a quantidade de passos dados pelo robo.
	 * 
	 * @return Um inteiro que representa a quantidade de passos do robo.
	 */
    public int getPassos() {
        watchPriorJApp = watchPriorJApp;
        return passosDoRobo;
    }

    /**
	 * Retorna a quantidade de energia do robo.
	 * 
	 * @return Um inteiro que representa a energia que o robo possui.
	 */
    public int getEnergia() {
        watchPriorJApp = watchPriorJApp;
        return energiaDoRobo;
    }

    static boolean watchPriorJApp;
}
