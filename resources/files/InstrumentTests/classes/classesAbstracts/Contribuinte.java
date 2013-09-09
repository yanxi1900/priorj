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
        if (nome == null || nome.equals("")) {
            throw new Exception("Nome invalido.");
        }
        if (numero == null || numero.equals("")) {
            throw new Exception("Numero invalido.");
        }
        if (valorDosBens < 0.0) {
            throw new Exception("Valor dos bens nao pode ser negativo.");
        }
        this.nome = nome;
        this.numero = numero;
        this.valorDosBens = valorDosBens;
    }

    /**
	 *Retorna o nome do contribuinte.
	 * @return Nome.
	 */
    public String getNome() {
        return nome;
    }

    /**
	 * Retorna o numero do contribuinte.
	 * @return Numero.
	 */
    public String getNumero() {
        return numero;
    }

    /**
	 * Retorna o valor dos bens.
	 * @return Valor dos bens.
	 */
    public double getValorDosBens() {
        return valorDosBens;
    }

    /**
	 * Muda o nome do contribuinte.
	 * @param nome Novo nome do contribuinte.
	 */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
	 * Muda o numero do contribuinte.
	 * @param numero Novo numero do contribuinte.
	 */
    public void setNumero(String numero) {
        this.numero = numero;
    }

    /**
	 * Retorna o imposto.
	 * @return Imposto.
	 */
    public double getImposto() {
        return imposto;
    }

    private void setImposto(double imposto) {
        this.imposto = imposto;
    }

    /**
	 * Calcula o imposto que deve ser pago.
	 * @return Imposto para pagar.
	 */
    public double impostoPago() {
        setImposto(0.0);
        if (calculaDescontos() < calculaTributos()) {
            setImposto(calculaTributos() - calculaDescontos());
        }
        return getImposto();
    }

    @Override
    public String toString() {
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
}
