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
        if (salarioAnual < 0.0) {
            throw new Exception("Salario nao pode ser negativo.");
        }
        this.salarioAnual = salarioAnual;
    }

    /**
	 * Calcula tributos.
	 * @return Tributos.
	 */
    @Override
    public double calculaTributos() {
        tributos = 0.0;
        if (salarioAnual <= DEZ) {
            tributos = (5.0 / 100 * salarioAnual);
        }
        if (salarioAnual > DEZ && salarioAnual <= CINQUENTA) {
            tributos = (7.5 / 100 * salarioAnual);
        }
        if (salarioAnual > CINQUENTA && salarioAnual <= CEM) {
            tributos = (10.0 / 100 * salarioAnual);
        }
        if (salarioAnual > CEM) {
            tributos = (15.0 / 100 * salarioAnual);
        }
        return tributos;
    }
}
