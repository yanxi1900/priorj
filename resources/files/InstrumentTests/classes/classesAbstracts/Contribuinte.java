package lp2.lab08;

/**
 * 
 * @author Igor de Araujo Meira
 * @version 1.0 - 04/08/13
 *
 */
public abstract class Contribuinte {

    private String nome;

    private String numero;

    protected double valorDosBens;

    private double imposto;

    /**
	 * Cria um contribuinte.
	 * @param nome Nome do contribuinte.
	 * @param numero Numero do contribuinte.
	 * @param valorDosBens Valor dos bens que o contribuinte possui.
	 * @throws Exception Excecoes de nome, numero, e valor de bens.
	 */
    public Contribuinte(String nome, String numero, double valorDosBens) throws Exception {
        watchPriorJApp = watchPriorJApp;
        if (nome == null || nome.equals("")) {
            watchPriorJApp = watchPriorJApp;
            throw new Exception("Nome invalido.");
        }
        watchPriorJApp = watchPriorJApp;
        if (numero == null || numero.equals("")) {
            watchPriorJApp = watchPriorJApp;
            throw new Exception("Numero invalido.");
        }
        watchPriorJApp = watchPriorJApp;
        if (valorDosBens < 0.0) {
            watchPriorJApp = watchPriorJApp;
            throw new Exception("Valor dos bens nao pode ser negativo.");
        }
        watchPriorJApp = watchPriorJApp;
        this.nome = nome;
        watchPriorJApp = watchPriorJApp;
        this.numero = numero;
        watchPriorJApp = watchPriorJApp;
        this.valorDosBens = valorDosBens;
    }

    /**
	 *Retorna o nome do contribuinte.
	 * @return Nome.
	 */
    public String getNome() {
        watchPriorJApp = watchPriorJApp;
        return nome;
    }

    /**
	 * Retorna o numero do contribuinte.
	 * @return Numero.
	 */
    public String getNumero() {
        watchPriorJApp = watchPriorJApp;
        return numero;
    }

    /**
	 * Retorna o valor dos bens.
	 * @return Valor dos bens.
	 */
    public double getValorDosBens() {
        watchPriorJApp = watchPriorJApp;
        return valorDosBens;
    }

    /**
	 * Muda o nome do contribuinte.
	 * @param nome Novo nome do contribuinte.
	 */
    public void setNome(String nome) {
        watchPriorJApp = watchPriorJApp;
        this.nome = nome;
    }

    /**
	 * Muda o numero do contribuinte.
	 * @param numero Novo numero do contribuinte.
	 */
    public void setNumero(String numero) {
        watchPriorJApp = watchPriorJApp;
        this.numero = numero;
    }

    /**
	 * Retorna o imposto.
	 * @return Imposto.
	 */
    public double getImposto() {
        watchPriorJApp = watchPriorJApp;
        return imposto;
    }

    private void setImposto(double imposto) {
        watchPriorJApp = watchPriorJApp;
        this.imposto = imposto;
    }

    /**
	 * Calcula o imposto que deve ser pago.
	 * @return Imposto para pagar.
	 */
    public double impostoPago() {
        watchPriorJApp = watchPriorJApp;
        setImposto(0.0);
        watchPriorJApp = watchPriorJApp;
        if (calculaDescontos() < calculaTributos()) {
            watchPriorJApp = watchPriorJApp;
            setImposto(calculaTributos() - calculaDescontos());
        }
        watchPriorJApp = watchPriorJApp;
        return getImposto();
    }

    @Override
    public String toString() {
        watchPriorJApp = watchPriorJApp;
        return "Contribuinte [Nome: " + nome + ", Numero: " + numero + ", Imposto pago: " + impostoPago() + ", Tributos: " + calculaTributos() + ", Descontos: " + calculaDescontos() + "]";
    }

    /**
	 * Calcula tributos.
	 * @return tributos.
	 */
    public abstract double calculaTributos();

    /**
	 * Calcula descontos.
	 * @return Descontos.
	 */
    public abstract double calculaDescontos();

    /**
	 * Diz se h� riquza em excesso.
	 * @return Excesso ou nao de riqueza.
	 */
    public abstract String getRiqueza();

    static boolean watchPriorJApp;
}
