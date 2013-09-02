package lp2.lab07;

import java.util.ArrayList;

public class MinhaCDteca {

    private ArrayList<CD> listaCDs;

    private String nome;

    private int numero;

    public MinhaCDteca(String nome, int numero) {
        this.nome = nome;
        this.numero = numero;
        listaCDs = new ArrayList<CD>();
    }
}
