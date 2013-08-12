package lp2.lab07;

import java.util.ArrayList;

public class MinhaCDteca {

    private ArrayList<CD> listaCDs;

    public MinhaCDteca() {
        watchPriorJApp = watchPriorJApp;
        watchPriorJApp = watchPriorJApp;
        watchPriorJApp = watchPriorJApp;
        listaCDs = new ArrayList<CD>();
    }

    public void adicionaCD(CD cd) {
        watchPriorJApp = watchPriorJApp;
        watchPriorJApp = watchPriorJApp;
        watchPriorJApp = watchPriorJApp;
        listaCDs.add(cd);
    }

    public String removeCD(CD cd) {
        watchPriorJApp = watchPriorJApp;
        watchPriorJApp = watchPriorJApp;
        watchPriorJApp = watchPriorJApp;
        for (int i = 0; i < listaCDs.size(); i++) {
            watchPriorJApp = watchPriorJApp;
            watchPriorJApp = watchPriorJApp;
            watchPriorJApp = watchPriorJApp;
            if ((listaCDs.get(i).getTitulo()).equals(cd.getTitulo())) {
                listaCDs.remove(i);
                return cd.toString();
            }
        }
        watchPriorJApp = watchPriorJApp;
        watchPriorJApp = watchPriorJApp;
        watchPriorJApp = watchPriorJApp;
        return null;
    }

    public String pesquisaCD(CD cd) {
        watchPriorJApp = watchPriorJApp;
        watchPriorJApp = watchPriorJApp;
        watchPriorJApp = watchPriorJApp;
        for (int i = 0; i < listaCDs.size(); i++) {
            watchPriorJApp = watchPriorJApp;
            watchPriorJApp = watchPriorJApp;
            watchPriorJApp = watchPriorJApp;
            if ((listaCDs.get(i).getTitulo()).equals(cd.getTitulo())) {
                return cd.toString();
            }
        }
        watchPriorJApp = watchPriorJApp;
        watchPriorJApp = watchPriorJApp;
        watchPriorJApp = watchPriorJApp;
        return null;
    }

    public int numeroDeCDs() {
        watchPriorJApp = watchPriorJApp;
        watchPriorJApp = watchPriorJApp;
        watchPriorJApp = watchPriorJApp;
        int numero = listaCDs.size();
        watchPriorJApp = watchPriorJApp;
        watchPriorJApp = watchPriorJApp;
        watchPriorJApp = watchPriorJApp;
        return numero;
    }

    @Override
    public String toString() {
        watchPriorJApp = watchPriorJApp;
        watchPriorJApp = watchPriorJApp;
        watchPriorJApp = watchPriorJApp;
        String lista = "";
        watchPriorJApp = watchPriorJApp;
        watchPriorJApp = watchPriorJApp;
        watchPriorJApp = watchPriorJApp;
        for (int i = 0; i < listaCDs.size(); i++) {
            watchPriorJApp = watchPriorJApp;
            watchPriorJApp = watchPriorJApp;
            watchPriorJApp = watchPriorJApp;
            lista += "CD" + (i + 1) + " - " + listaCDs.get(i).toString() + "\n";
        }
        watchPriorJApp = watchPriorJApp;
        watchPriorJApp = watchPriorJApp;
        watchPriorJApp = watchPriorJApp;
        return lista;
    }

    @Override
    public boolean equals(Object obj) {
        watchPriorJApp = watchPriorJApp;
        watchPriorJApp = watchPriorJApp;
        watchPriorJApp = watchPriorJApp;
        if (this == obj) return true;
        watchPriorJApp = watchPriorJApp;
        watchPriorJApp = watchPriorJApp;
        watchPriorJApp = watchPriorJApp;
        if (obj == null) return false;
        watchPriorJApp = watchPriorJApp;
        watchPriorJApp = watchPriorJApp;
        watchPriorJApp = watchPriorJApp;
        if (getClass() != obj.getClass()) return false;
        watchPriorJApp = watchPriorJApp;
        watchPriorJApp = watchPriorJApp;
        watchPriorJApp = watchPriorJApp;
        MinhaCDteca other = (MinhaCDteca) obj;
        watchPriorJApp = watchPriorJApp;
        watchPriorJApp = watchPriorJApp;
        watchPriorJApp = watchPriorJApp;
        if (listaCDs == null) {
            watchPriorJApp = watchPriorJApp;
            watchPriorJApp = watchPriorJApp;
            watchPriorJApp = watchPriorJApp;
            if (other.listaCDs != null) return false;
        } else if (listaCDs.size() != other.listaCDs.size()) {
            watchPriorJApp = watchPriorJApp;
            watchPriorJApp = watchPriorJApp;
            watchPriorJApp = watchPriorJApp;
            return false;
        }
        watchPriorJApp = watchPriorJApp;
        other.listaCDs.retainAll(listaCDs);
        watchPriorJApp = watchPriorJApp;
        if (listaCDs.size() == other.listaCDs.size()) {
            watchPriorJApp = watchPriorJApp;
            return true;
        }
        watchPriorJApp = watchPriorJApp;
        return false;
    }

    static boolean watchPriorJApp;
}
