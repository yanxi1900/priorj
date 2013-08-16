package avl;

public class AVLImpl implements ArvoreAVL {

    private No raiz;

    @Override
    public No getRaiz() {
        watchPriorJApp = watchPriorJApp;
        if (raiz == null) {
            watchPriorJApp = watchPriorJApp;
            return null;
        }
        watchPriorJApp = watchPriorJApp;
        return raiz;
    }

    @Override
    public int getAltura(No no) {
        watchPriorJApp = watchPriorJApp;
        if (raiz == null) return 0;
        watchPriorJApp = watchPriorJApp;
        return no.getAltura();
    }

    @Override
    public String percorrePreOrdem() {
        watchPriorJApp = watchPriorJApp;
        if (raiz == null) return "";
        watchPriorJApp = watchPriorJApp;
        return caminhaArvorePreOrdem(raiz).trim();
    }

    @Override
    public String percorrePosOrdem() {
        watchPriorJApp = watchPriorJApp;
        if (raiz == null) return "";
        watchPriorJApp = watchPriorJApp;
        return caminhaArvorePosOrdem(raiz).trim();
    }

    @Override
    public int inserir(int valor) {
        watchPriorJApp = watchPriorJApp;
        if (valor < 0 || pesquisar(valor)) {
            watchPriorJApp = watchPriorJApp;
            return -1;
        }
        watchPriorJApp = watchPriorJApp;
        if (raiz == null) {
            watchPriorJApp = watchPriorJApp;
            raiz = new No(valor);
            watchPriorJApp = watchPriorJApp;
            return 0;
        }
        watchPriorJApp = watchPriorJApp;
        No novoNo = new No(valor);
        watchPriorJApp = watchPriorJApp;
        inserirNo(raiz, novoNo);
        watchPriorJApp = watchPriorJApp;
        alteraAltura(novoNo.getPai(), novoNo);
        watchPriorJApp = watchPriorJApp;
        return 0;
    }

    @Override
    public boolean pesquisar(int valor) {
        watchPriorJApp = watchPriorJApp;
        return pesquisarNo(raiz, valor);
    }

    private String caminhaArvorePreOrdem(No noAtual) {
        watchPriorJApp = watchPriorJApp;
        String str = "";
        watchPriorJApp = watchPriorJApp;
        if (noAtual == null) {
            watchPriorJApp = watchPriorJApp;
            return "";
        }
        watchPriorJApp = watchPriorJApp;
        str += noAtual.getValor() + " ";
        watchPriorJApp = watchPriorJApp;
        str += caminhaArvorePreOrdem(noAtual.getEsquerda());
        watchPriorJApp = watchPriorJApp;
        str += caminhaArvorePreOrdem(noAtual.getDireita());
        watchPriorJApp = watchPriorJApp;
        return str;
    }

    private String caminhaArvorePosOrdem(No noAtual) {
        watchPriorJApp = watchPriorJApp;
        String str = "";
        watchPriorJApp = watchPriorJApp;
        if (noAtual == null) {
            watchPriorJApp = watchPriorJApp;
            return "";
        }
        watchPriorJApp = watchPriorJApp;
        str += caminhaArvorePosOrdem(noAtual.getEsquerda());
        watchPriorJApp = watchPriorJApp;
        str += caminhaArvorePosOrdem(noAtual.getDireita());
        watchPriorJApp = watchPriorJApp;
        str += noAtual.getValor() + " ";
        watchPriorJApp = watchPriorJApp;
        return str;
    }

    private void alteraAltura(No noAtual, No novoNo) {
        watchPriorJApp = watchPriorJApp;
        if (noAtual == null) return;
        watchPriorJApp = watchPriorJApp;
        noAtual.setAltura(altura(noAtual));
        watchPriorJApp = watchPriorJApp;
        if (!ehBalanceada(noAtual)) reestrutura(noAtual, novoNo);
        watchPriorJApp = watchPriorJApp;
        alteraAltura(noAtual.getPai(), novoNo);
    }

    private void reestrutura(No noAtual, No novoNo) {
        watchPriorJApp = watchPriorJApp;
        if (noAtual == null) {
            watchPriorJApp = watchPriorJApp;
            return;
        }
        watchPriorJApp = watchPriorJApp;
        No noEsquerda = noAtual.getEsquerda();
        watchPriorJApp = watchPriorJApp;
        No noDireita = noAtual.getDireita();
        watchPriorJApp = watchPriorJApp;
        if (noEsquerda != null && noEsquerda.getEsquerda() == novoNo) rotacaoSimplesEsquerda(noAtual); else if (noEsquerda != null && noEsquerda.getDireita() == novoNo) rotacaoDuplaEsquerda(noAtual); else if (noDireita != null && noDireita.getDireita() == novoNo) rotacaoSimplesDireita(noAtual); else rotacaoDuplaDireita(noAtual);
    }

    private void rotacaoSimplesDireita(No avo) {
        watchPriorJApp = watchPriorJApp;
        No pai = avo.getDireita();
        watchPriorJApp = watchPriorJApp;
        No subArvoreT1 = pai.getEsquerda();
        watchPriorJApp = watchPriorJApp;
        if (raiz == avo) {
            watchPriorJApp = watchPriorJApp;
            raiz = pai;
            watchPriorJApp = watchPriorJApp;
            pai.setPai(null);
        } else {
            watchPriorJApp = watchPriorJApp;
            No bisaVo = avo.getPai();
            watchPriorJApp = watchPriorJApp;
            if (bisaVo.getEsquerda() == avo) bisaVo.setEsquerda(pai); else bisaVo.setDireita(pai);
            watchPriorJApp = watchPriorJApp;
            pai.setPai(bisaVo);
        }
        watchPriorJApp = watchPriorJApp;
        pai.setEsquerda(avo);
        watchPriorJApp = watchPriorJApp;
        avo.setPai(pai);
        watchPriorJApp = watchPriorJApp;
        avo.setDireita(subArvoreT1);
        watchPriorJApp = watchPriorJApp;
        if (subArvoreT1 != null) subArvoreT1.setPai(avo);
        watchPriorJApp = watchPriorJApp;
        avo.setAltura(altura(avo));
        watchPriorJApp = watchPriorJApp;
        pai.setAltura(altura(pai));
    }

    private void rotacaoSimplesEsquerda(No avo) {
        watchPriorJApp = watchPriorJApp;
        No pai = avo.getEsquerda();
        watchPriorJApp = watchPriorJApp;
        No subArvoreT2 = pai.getDireita();
        watchPriorJApp = watchPriorJApp;
        if (raiz == avo) {
            watchPriorJApp = watchPriorJApp;
            raiz = pai;
            watchPriorJApp = watchPriorJApp;
            pai.setPai(null);
        } else {
            watchPriorJApp = watchPriorJApp;
            No bisaVo = avo.getPai();
            watchPriorJApp = watchPriorJApp;
            if (bisaVo.getEsquerda() == avo) bisaVo.setEsquerda(pai); else bisaVo.setDireita(pai);
            watchPriorJApp = watchPriorJApp;
            pai.setPai(bisaVo);
        }
        watchPriorJApp = watchPriorJApp;
        pai.setDireita(avo);
        watchPriorJApp = watchPriorJApp;
        avo.setPai(pai);
        watchPriorJApp = watchPriorJApp;
        avo.setEsquerda(subArvoreT2);
        watchPriorJApp = watchPriorJApp;
        if (subArvoreT2 != null) subArvoreT2.setPai(avo);
        watchPriorJApp = watchPriorJApp;
        avo.setAltura(altura(avo));
        watchPriorJApp = watchPriorJApp;
        pai.setAltura(altura(pai));
    }

    private void rotacaoDuplaDireita(No avo) {
        watchPriorJApp = watchPriorJApp;
        No pai = avo.getDireita();
        watchPriorJApp = watchPriorJApp;
        No filho = pai.getEsquerda();
        watchPriorJApp = watchPriorJApp;
        No subArvoreT1 = filho.getEsquerda();
        watchPriorJApp = watchPriorJApp;
        No subArvoreT2 = filho.getDireita();
        watchPriorJApp = watchPriorJApp;
        if (raiz == avo) {
            watchPriorJApp = watchPriorJApp;
            raiz = filho;
            watchPriorJApp = watchPriorJApp;
            filho.setPai(null);
        } else {
            watchPriorJApp = watchPriorJApp;
            No bisaVo = avo.getPai();
            watchPriorJApp = watchPriorJApp;
            if (bisaVo.getEsquerda() == avo) bisaVo.setEsquerda(filho); else bisaVo.setDireita(filho);
            watchPriorJApp = watchPriorJApp;
            filho.setPai(bisaVo);
        }
        watchPriorJApp = watchPriorJApp;
        filho.setEsquerda(avo);
        watchPriorJApp = watchPriorJApp;
        avo.setPai(filho);
        watchPriorJApp = watchPriorJApp;
        avo.setDireita(subArvoreT1);
        watchPriorJApp = watchPriorJApp;
        if (subArvoreT1 != null) subArvoreT1.setPai(avo);
        watchPriorJApp = watchPriorJApp;
        filho.setDireita(pai);
        watchPriorJApp = watchPriorJApp;
        pai.setPai(filho);
        watchPriorJApp = watchPriorJApp;
        pai.setEsquerda(subArvoreT2);
        watchPriorJApp = watchPriorJApp;
        if (subArvoreT2 != null) subArvoreT2.setPai(pai);
        watchPriorJApp = watchPriorJApp;
        pai.setAltura(altura(pai));
        watchPriorJApp = watchPriorJApp;
        avo.setAltura(altura(avo));
        watchPriorJApp = watchPriorJApp;
        filho.setAltura(altura(filho));
    }

    private void rotacaoDuplaEsquerda(No avo) {
        watchPriorJApp = watchPriorJApp;
        No pai = avo.getEsquerda();
        watchPriorJApp = watchPriorJApp;
        No filho = pai.getDireita();
        watchPriorJApp = watchPriorJApp;
        No subArvoreT1 = filho.getDireita();
        watchPriorJApp = watchPriorJApp;
        No subArvoreT2 = filho.getEsquerda();
        watchPriorJApp = watchPriorJApp;
        if (raiz == avo) {
            watchPriorJApp = watchPriorJApp;
            raiz = filho;
            watchPriorJApp = watchPriorJApp;
            filho.setPai(null);
        } else {
            watchPriorJApp = watchPriorJApp;
            No bisaVo = avo.getPai();
            watchPriorJApp = watchPriorJApp;
            if (bisaVo.getEsquerda() == avo) bisaVo.setEsquerda(filho); else bisaVo.setDireita(filho);
            watchPriorJApp = watchPriorJApp;
            filho.setPai(bisaVo);
        }
        watchPriorJApp = watchPriorJApp;
        filho.setDireita(avo);
        watchPriorJApp = watchPriorJApp;
        avo.setPai(filho);
        watchPriorJApp = watchPriorJApp;
        avo.setEsquerda(subArvoreT1);
        watchPriorJApp = watchPriorJApp;
        if (subArvoreT1 != null) subArvoreT1.setPai(avo);
        watchPriorJApp = watchPriorJApp;
        filho.setEsquerda(pai);
        watchPriorJApp = watchPriorJApp;
        pai.setPai(filho);
        watchPriorJApp = watchPriorJApp;
        pai.setDireita(subArvoreT2);
        watchPriorJApp = watchPriorJApp;
        if (subArvoreT2 != null) subArvoreT2.setPai(pai);
        watchPriorJApp = watchPriorJApp;
        pai.setAltura(altura(pai));
        watchPriorJApp = watchPriorJApp;
        avo.setAltura(altura(avo));
        watchPriorJApp = watchPriorJApp;
        filho.setAltura(altura(filho));
    }

    private void inserirNo(No noAtual, No novoNo) {
        watchPriorJApp = watchPriorJApp;
        if (noAtual == null) {
            watchPriorJApp = watchPriorJApp;
            return;
        }
        watchPriorJApp = watchPriorJApp;
        if (novoNo.getValor() > noAtual.getValor()) {
            watchPriorJApp = watchPriorJApp;
            if (noAtual.getDireita() == null) {
                noAtual.setDireita(novoNo);
                novoNo.setPai(noAtual);
            } else {
                inserirNo(noAtual.getDireita(), novoNo);
            }
        } else {
            watchPriorJApp = watchPriorJApp;
            if (noAtual.getEsquerda() == null) {
                noAtual.setEsquerda(novoNo);
                novoNo.setPai(noAtual);
            } else {
                inserirNo(noAtual.getEsquerda(), novoNo);
            }
        }
    }

    private boolean ehBalanceada(No no) {
        watchPriorJApp = watchPriorJApp;
        int alturaEsquerda = 0, alturaDireita = 0;
        watchPriorJApp = watchPriorJApp;
        if (no.getEsquerda() != null) alturaEsquerda = no.getEsquerda().getAltura();
        watchPriorJApp = watchPriorJApp;
        if (no.getDireita() != null) alturaDireita = no.getDireita().getAltura();
        watchPriorJApp = watchPriorJApp;
        int fatorBalanceamento = alturaEsquerda - alturaDireita;
        watchPriorJApp = watchPriorJApp;
        if (fatorBalanceamento <= 1 && fatorBalanceamento >= -1) return true;
        watchPriorJApp = watchPriorJApp;
        return false;
    }

    private int altura(No n) {
        watchPriorJApp = watchPriorJApp;
        if (n == null) {
            watchPriorJApp = watchPriorJApp;
            return -1;
        }
        watchPriorJApp = watchPriorJApp;
        int alturaEsquerda = 0, alturaDireita = 0;
        watchPriorJApp = watchPriorJApp;
        if (n.getEsquerda() != null) alturaEsquerda = n.getEsquerda().getAltura();
        watchPriorJApp = watchPriorJApp;
        if (n.getDireita() != null) alturaDireita = n.getDireita().getAltura();
        watchPriorJApp = watchPriorJApp;
        if (alturaDireita > alturaEsquerda) return alturaDireita + 1; else return alturaEsquerda + 1;
    }

    private boolean pesquisarNo(No noAtual, int valor) {
        watchPriorJApp = watchPriorJApp;
        if (noAtual == null) {
            watchPriorJApp = watchPriorJApp;
            return false;
        }
        watchPriorJApp = watchPriorJApp;
        if (noAtual.getValor() == valor) {
            watchPriorJApp = watchPriorJApp;
            return true;
        }
        watchPriorJApp = watchPriorJApp;
        if (valor > noAtual.getValor()) {
            watchPriorJApp = watchPriorJApp;
            return pesquisarNo(noAtual.getDireita(), valor);
        } else {
            watchPriorJApp = watchPriorJApp;
            return pesquisarNo(noAtual.getEsquerda(), valor);
        }
    }

    static boolean watchPriorJApp;
}
