package lp2.lab07;

import java.util.ArrayList;

public class MinhaCDteca {

    private ArrayList<CD> listaCDs;

    public MinhaCDteca() {
        listaCDs = new ArrayList<CD>();
    }

    public void adicionaCD(CD cd) {
        listaCDs.add(cd);
    }

    //Troca de i por j, no for e no if
    public String removeCD(CD cd) {
        for (int j = 0; j < listaCDs.size(); j++) {
            if ((listaCDs.get(j).getTitulo()).equals(cd.getTitulo())) {
                listaCDs.remove(j);
                return cd.toString();
            }
        }
        return null;
    }

    public String pesquisaCD(CD cd) {
        for (int i = 0; i < listaCDs.size(); i++) {
            if ((listaCDs.get(i).getTitulo()).equals(cd.getTitulo())) {
                return cd.toString();
            }
        }
        return null;
    }

    public int numeroDeCDs() {
        int numero = listaCDs.size();
        return numero;
    }

    @Override
    public String toString() {
        String lista = "";
        for (int i = 0; i < listaCDs.size(); i++) {
            lista += "CD" + (i + 1) + " - " + listaCDs.get(i).toString() + "\n";
        }
        return lista;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass()) return false;
        MinhaCDteca other = (MinhaCDteca) obj;
        if (listaCDs == null) {
            if (other.listaCDs != null) return false;
        } else if (listaCDs.size() != other.listaCDs.size()) {
            return false;
        }
        other.listaCDs.retainAll(listaCDs);
        if (listaCDs.size() == other.listaCDs.size()) {
            return true;
        }
        return false;
    }
}
