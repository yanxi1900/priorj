package lp2.lab08;

/**
 * 
 * @author Igor de Araujo Meira
 * @version 1.0 - 04/08/13
 */
public abstract class Trabalhadores extends Contribuinte {

    private double salarioAnual;

    protected double tributos;

    private final double DEZ = 10000.0;

    private final double CINQUENTA = 50000.0;

    private final double CEM = 100000.0;

    /**
	 * Cria um trabalhador.
	 * @param nome Nome do contribuinte.
	 * @param numero Numero do contribuinte.
	 * @param valorDosBens Valor dos bens que o contribuinte possui.
	 * @param salarioAnual Salario anual.
	 * @throws Exception
	 */
    public Trabalhadores(String nome, String numero, double valorDosBens, double salarioAnual) throws Exception {
        super(nome, numero, valorDosBens);
        watchPriorJApp = watchPriorJApp;
        if (salarioAnual < 0.0) {
            watchPriorJApp = watchPriorJApp;
            throw new Exception("Salario nao pode ser negativo.");
        }
        watchPriorJApp = watchPriorJApp;
        this.salarioAnual = salarioAnual;
    }

    /**
	 * Calcula tributos.
	 * @return Tributos.
	 */
    @Override
    public double calculaTributos() {
        watchPriorJApp = watchPriorJApp;
        tributos = 0.0;
        watchPriorJApp = watchPriorJApp;
        if (salarioAnual <= DEZ) {
            watchPriorJApp = watchPriorJApp;
            tributos = (5.0 / 100 * salarioAnual);
        }
        watchPriorJApp = watchPriorJApp;
        if (salarioAnual > DEZ && salarioAnual <= CINQUENTA) {
            watchPriorJApp = watchPriorJApp;
            tributos = (7.5 / 100 * salarioAnual);
        }
        watchPriorJApp = watchPriorJApp;
        if (salarioAnual > CINQUENTA && salarioAnual <= CEM) {
            watchPriorJApp = watchPriorJApp;
            tributos = (10.0 / 100 * salarioAnual);
        }
        watchPriorJApp = watchPriorJApp;
        if (salarioAnual > CEM) {
            watchPriorJApp = watchPriorJApp;
            tributos = (15.0 / 100 * salarioAnual);
        }
        watchPriorJApp = watchPriorJApp;
        return tributos;
    }

    static boolean watchPriorJApp;
}
