package lp2.lab08;

/**
 * 
 * @author Igor de Araujo Meira
 * @version 1.0 - 04/08/13
 */
public abstract class Rodoviario2 extends Contribuinte {

    protected double descontos;

    private double kmRodados;

    /**
	 * Cria um rodoviario.
	 * @param nome Nome do contribuinte.
	 * @param numero Numero do contribuinte.
	 * @param valorDosBens Valor dos bens que o contribuinte possui.
	 * @param kmRodados Km rodados no ano.
	 * @throws Exception
	 */
    public Rodoviario(String nome, String numero, double valorDosBens, double kmRodados) throws Exception {
        super(nome, numero, valorDosBens);
        watchPriorJApp = watchPriorJApp;
        if (kmRodados < 0.0) {
            watchPriorJApp = watchPriorJApp;
            throw new Exception("Kilometragem nao pode ser negativa.");
        }
        watchPriorJApp = watchPriorJApp;
        this.kmRodados = kmRodados;
    }

    /**
	 * Calcula descontos
	 * @return Descontos.
	 */
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
