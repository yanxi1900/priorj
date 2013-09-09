package lp2.lab08;

/**
 * 
 * @author Igor de Araujo Meira
 * @version 1.0 - 04/08/13
 */
public abstract class Rodoviario extends Contribuinte {

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
        if (kmRodados < 0.0) {
            throw new Exception("Kilometragem nao pode ser negativa.");
        }
        this.kmRodados = kmRodados;
    }

    /**
	 * Calcula descontos
	 * @return Descontos.
	 */
    @Override
    public double calculaDescontos() {
        descontos = 0.0;
        descontos += (kmRodados * 0.01);
        return descontos;
    }
}
