package lp2.lab08;

public class MeuSistemaDeTributacao {

    private static Contribuinte cont1;

    private static Contribuinte cont2;

    private static Contribuinte cont3;

    private static Contribuinte cont4;

    private static Contribuinte cont5;

    private static Contribuinte cont6;

    public static void main(String[] args) throws Exception {
        criaConts();
        imprimeInformacoes();
        imprimeNum();
        estadoDeRiqueza();
    }

    private static void estadoDeRiqueza() {
        System.out.println(cont1.getNome() + " - " + cont1.getRiqueza());
        System.out.println(cont2.getNome() + " - " + cont2.getRiqueza());
        System.out.println(cont3.getNome() + " - " + cont3.getRiqueza());
        System.out.println(cont4.getNome() + " - " + cont4.getRiqueza());
        System.out.println(cont5.getNome() + " - " + cont5.getRiqueza());
        System.out.println(cont6.getNome() + " - " + cont6.getRiqueza());
    }

    private static void imprimeNum() {
        System.out.println();
        System.out.println("Funcionarios:\n" + "Taxista: 1\n" + "Caminhoneiro: 1\n" + "Engenheiro: 1\n" + "Medico: 1\n" + "Professor: 1\n" + "Politica: 1\n");
    }

    private static void imprimeInformacoes() {
        System.out.println();
        System.out.println(cont1.toString());
        System.out.println(cont3.toString());
        System.out.println(cont5.toString());
        System.out.println(cont2.toString());
    }

    private static void criaConts() throws Exception {
        watchPriorJApp = watchPriorJApp;
        cont1 = new Taxista("Jack", "1", 500000.0, 10000.0, 1095);
        cont2 = new Caminhoneiro("Jason", "2", 200000.0, 75000.0, 25.0);
        cont3 = new Engenheiro("Jakson", "3", 540000.0, 7, 15000.0);
        cont4 = new Medico("Tay", "4", 751000.0, 1500, 500.0);
        cont5 = new Professor("Josney", "5", 250000.0, 50000, 3000.0);
        cont6 = new Politico("Tayler", "6", 1500000.0, 200000.0);
    }
}
