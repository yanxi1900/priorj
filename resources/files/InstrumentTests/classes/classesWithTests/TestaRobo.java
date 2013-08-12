package lp2.lab06;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/*
 * Aluno: Igor de Araújo Meira
 * Data: 12/07/2013
 */

public class TestaRobo {
	
	private Robo robo;
	private Sala sala;
	
	@Before
	public void criaObjetos() throws Exception {
		sala = new Sala(10, 10);
		robo = new Robo(sala, 100);
	}
	
	@Test
	public void testaCriacaoDeRobo() {
		try{
			new Robo(sala, 0);
			Assert.fail("Esperava excecao na energia.");
		}catch(Exception ex){
			Assert.assertEquals("Mensagem errada", "A energia do robo deve ser maior que zero.", ex.getMessage());
		}
		try{
			new Robo(sala, -1);
			Assert.fail("Esperava excecao na energia.");
		}catch(Exception ex){
			Assert.assertEquals("Mensagem errada", "A energia do robo deve ser maior que zero.", ex.getMessage());
		}
		
		Assert.assertFalse(sala.verificarPosicaoLivre(0, 0));
		Assert.assertTrue(!sala.verificarPosicaoLivre(0, 0));
		
	}

	@Test
	public void testaGetPosicao() throws Exception {
		Assert.assertTrue(robo.getPosicao().equals("[0, 0]"));
		Assert.assertFalse(robo.getPosicao().equals("[1, 0]"));
		Assert.assertFalse(robo.getPosicao().equals("[0, 1]"));
		Assert.assertFalse(robo.getPosicao().equals("[1, 1]"));
		Assert.assertFalse(robo.getPosicao().equals("[0, -1]"));
		Assert.assertFalse(robo.getPosicao().equals("[-1, 0]"));
		Assert.assertFalse(robo.getPosicao().equals("[-1, -1]"));
	
	}
	
	@Test
	public void testaGetLinha() {
		Assert.assertTrue(robo.getLinha() == 0);
		Assert.assertFalse(robo.getLinha() < 0);
		Assert.assertFalse(robo.getLinha() > 0);
	}
	
	@Test
	public void testaGetColuna() {
		Assert.assertTrue(robo.getColuna() == 0);
		Assert.assertFalse(robo.getColuna() < 0);
		Assert.assertFalse(robo.getColuna() > 0);
	}
	
	@Test
	public void testaGetPassos() {
		Assert.assertTrue(robo.getPassos() == 0);
		Assert.assertFalse(robo.getPassos() < 0);
		Assert.assertFalse(robo.getPassos() > 0);
	}
	
	@Test
	public void testaGetEnergia() {
		Assert.assertTrue(robo.getEnergia() == 100);
		Assert.assertFalse(robo.getEnergia() < 100);
		Assert.assertFalse(robo.getEnergia() > 100);
	}

	@Test
	public void testaOcupadoLivre() {
		Assert.assertFalse(robo.salaDoRobo.verificarPosicaoLivre(0, 0));
		Assert.assertTrue(!(robo.salaDoRobo.verificarPosicaoLivre(0, 0)));
	}

	@Test
	public void testaMovimentacao() throws Exception {
		//testa excecao de posicao invalida (cima e esquerda)
		try{
			robo.movimentaCima();
			Assert.fail();
		}catch(Exception ex){
			Assert.assertEquals("Mensagem errada", "Posicao invalida.", ex.getMessage());
		}
		try{
			robo.movimentaEsquerda();
			Assert.fail();
		}catch(Exception ex){
			Assert.assertEquals("Mensagem errada", "Posicao invalida.", ex.getMessage());
		}
		Assert.assertTrue(robo.getPosicao().equals("[0, 0]"));
		Assert.assertTrue(robo.getEnergia() == 100);
		Assert.assertTrue(robo.getPassos() == 0);
		
		//testa movimentos sem excecao
		robo.movimentaBaixo();
		Assert.assertTrue(robo.getPosicao().equals("[1, 0]"));
		Assert.assertTrue(robo.getEnergia() == 99);
		Assert.assertTrue(robo.getPassos() == 1);
		Assert.assertFalse(robo.getPosicao().equals("[0, 0]"));
		Assert.assertFalse(robo.getEnergia() == 100);
		Assert.assertFalse(robo.getPassos() == 0);
		
		robo.movimentaDireita();
		Assert.assertTrue(robo.getPosicao().equals("[1, 1]"));
		Assert.assertTrue(robo.getEnergia() == 98);
		Assert.assertTrue(robo.getPassos() == 2);
		Assert.assertFalse(robo.getPosicao().equals("[1, 0]"));
		Assert.assertFalse(robo.getEnergia() >= 99);
		Assert.assertFalse(robo.getPassos() <= 1);
		
		robo.movimentaCima();
		Assert.assertTrue(robo.getPosicao().equals("[0, 1]"));
		Assert.assertTrue(robo.getEnergia() == 97);
		Assert.assertTrue(robo.getPassos() == 3);
		Assert.assertFalse(robo.getPosicao().equals("[1, 1]"));
		Assert.assertFalse(robo.getEnergia() >= 98);
		Assert.assertFalse(robo.getPassos() <= 2);
		
		robo.movimentaEsquerda();
		Assert.assertTrue(robo.getPosicao().equals("[0, 0]"));
		Assert.assertTrue(robo.getEnergia() == 96);
		Assert.assertTrue(robo.getPassos() == 4);
		Assert.assertFalse(robo.getPosicao().equals("[0, 1]"));
		Assert.assertFalse(robo.getEnergia() >= 97);
		Assert.assertFalse(robo.getPassos() <= 3);
		
		//testa excecao de posicao invalida (baixo e direita)
		for (int i = 0; i < 9; i++) {
			robo.movimentaDireita();
		}
		for (int i = 0; i < 9; i++) {
			robo.movimentaBaixo();
		}
		Assert.assertTrue(robo.getPosicao().equals("[9, 9]"));
		Assert.assertTrue(robo.getEnergia() == 78);
		Assert.assertTrue(robo.getPassos() == 22);
		Assert.assertFalse(robo.getPosicao().equals("[0, 0]"));
		Assert.assertFalse(robo.getEnergia() >= 79);
		Assert.assertFalse(robo.getPassos() <= 21);
		
		try{
			robo.movimentaBaixo();
			Assert.fail();
		}catch(Exception ex){
			Assert.assertEquals("Mensagem errada", "Posicao invalida.", ex.getMessage());
		}
		try{
			robo.movimentaDireita();
			Assert.fail();
		}catch(Exception ex){
			Assert.assertEquals("Mensagem errada", "Posicao invalida.", ex.getMessage());
		}
		Assert.assertTrue(robo.getPosicao().equals("[9, 9]"));
		Assert.assertTrue(robo.getEnergia() == 78);
		Assert.assertTrue(robo.getPassos() == 22);
		
		//testa excecao de energia (enrgia = 0)
		for (int i = 0; i < 39; i++) {
			robo.movimentaEsquerda();
			robo.movimentaDireita();			
		}
		Assert.assertTrue(robo.getEnergia() == 0);
		try{
			robo.movimentaCima();
			Assert.fail();
		}catch(Exception ex){
			Assert.assertEquals("Mensagem errada", "O robo nao possui mais energia.", ex.getMessage());
		}
	}
	
	@Test
	public void testaPosicaoOcupada() {
		sala.inserirObstaculo(0, 1);		
		try{
			robo.movimentaDireita();
			Assert.fail();
		}catch(Exception ex){
			Assert.assertEquals("Mensagem errada", "Posicao nao esta livre.", ex.getMessage());
		}
	}
}