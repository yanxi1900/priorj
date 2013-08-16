package avl;

public interface ArvoreAVL {

    /**
	 * Retorna a raiz da arvore.
	 * @return a raiz da arvore.
	 */
    public No getRaiz();

    /**
	 * Informa a altura de um No da arvore.
	 * @param no - o No cuja altura deseja-se calcular.
	 * @return a altura de um No da arvore.
	 */
    public int getAltura(No no);

    /**
	 * Retorna os valores da arvore organizados em pre-ordem. 
	 * @return String representando os valores da arvore organizados em pre-ordem.
	 */
    public String percorrePreOrdem();

    /**
	 * Retorna os valores da arvore organizados em pos-ordem.
	 * @return String representando os valores da arvore organizados em pos-ordem.
	 */
    public String percorrePosOrdem();

    /**
	 * Insere um valor inteiro positivo na arvore AVL.
	 * (valores repetidos ou negativos caracterizam insercao
	 * mal sucedida)
	 * @param valor - O inteiro a ser inserido.
	 * @return 0, se a insercao for bem sucedida e -1, caso contrario.
	 */
    public int inserir(int valor);

    /**
	 * Pesquisa um valor inteiro na arvore AVL.
	 * @param valor - O inteiro a ser pesquisado.
	 * @return true, se o valor for encontrado e false, caso contrario.
	 */
    public boolean pesquisar(int valor);
}
