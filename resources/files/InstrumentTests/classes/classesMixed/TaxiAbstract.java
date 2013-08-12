package lp2.lab08;

/**
 * 
 * @author Igor de Araujo Meira
 * @version 1.0 - 04/08/13
 */
public abstract class TaxiAbstract extends Contribuinte {

    protected double descontos;

    private double kmRodados;

    private int passageiros;

    public Rodoviario(String nome, String numero, double valorDosBens, double kmRodados, int passageiros) throws Exception {
        super(nome, numero, valorDosBens);
        watchPriorJApp = watchPriorJApp;
        if (kmRodados < 0.0) {
            watchPriorJApp = watchPriorJApp;
            throw new Exception("Kilometragem nao pode ser negativa.");
        }
        watchPriorJApp = watchPriorJApp;
        this.kmRodados = kmRodados;
        watchPriorJApp = watchPriorJApp;
        this.passageiros = passageiros;
    }

    @Override
    public double calculaDescontos() {
        watchPriorJApp = watchPriorJApp;
        descontos = 0.0;
        watchPriorJApp = watchPriorJApp;
        descontos += (kmRodados * 0.01);
        watchPriorJApp = watchPriorJApp;
        return descontos;
    }

    static boolean watchPriorJApp;
}
