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
        if (kmRodados < 0.0) {
            throw new Exception("Kilometragem nao pode ser negativa.");
        }
        this.kmRodados = kmRodados;
        this.passageiros = passageiros;
    }

    @Override
    public double calculaDescontos() {
        descontos = 0.0;
        descontos += (kmRodados * 0.01);
        return descontos;
    }
}
