package lp2.lab07;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

/*
 * Aluno: Igor de Araujo Meira
 * Matricula: 112210079
 */

public class TestaCD {
	
	private CD cd1;
	private CD cd2;
	private CD cd3;
	private CD cd4;
	private CD cd5;
	
	@Before
	public void criaObjetos() throws Exception{
		cd1 = new CD("Meteora", "LP", 5);
		cd2 = new CD("Best Hits", "VH", 5, "Jump");
		cd3 = new CD("Yellow Submarine", "The Beatles", 13, "Yellow Submarine");
		cd4 = new CD("Meteora", "LP", 5);
		cd5 = new CD("Best Hits", "VH", 5, "Jump");
	}
	
	@Test
	public void testaCriaCD1(){
		try{
			new CD("", "LP", 5);
			Assert.fail("Esperava excecao de titulo.");
		}catch(Exception ex){
			Assert.assertEquals("Mensagem errada", "O CD deve possuir um titulo.", ex.getMessage());
		}
		try{
			new CD("Meteora", "", 5);
			Assert.fail("Esperava excecao de autor.");
		}catch(Exception ex){
			Assert.assertEquals("Mensagem errada", "O CD deve ter um autor.", ex.getMessage());
		}
		try{
			new CD("Meteora", "LP", 0);
			Assert.fail("Esperava excecao no M");
		}catch(Exception ex){
			Assert.assertEquals("Mensagem errada", "O CD deve conter no minimo uma musica.", ex.getMessage());
		}
		try{
			new CD("Meteora", "LP", -1);
			Assert.fail("Esperava excecao no M");
		}catch(Exception ex){
			Assert.assertEquals("Mensagem errada", "O CD deve conter no minimo uma musica.", ex.getMessage());
		}		
	}

	@Test
	public void testaCriaCD2(){
		try{
			new CD("", "VH", 5, "Jump");
			Assert.fail("Esperava excecao de titulo.");
		}catch(Exception ex){
			Assert.assertEquals("Mensagem errada", "O CD deve possuir um titulo.", ex.getMessage());
		}
		try{
			new CD("Best Hits", "", 5, "Jump");
			Assert.fail("Esperava excecao de autor.");
		}catch(Exception ex){
			Assert.assertEquals("Mensagem errada", "O CD deve ter um autor.", ex.getMessage());
		}
		try{
			new CD("Best Hits", "VH", 0, "Jump");
			Assert.fail("Esperava excecao no M");
		}catch(Exception ex){
			Assert.assertEquals("Mensagem errada", "O CD deve conter no minimo uma musica.", ex.getMessage());
		}
		try{
			new CD("Best Hits", "VH", -1, "Jump");
			Assert.fail("Esperava excecao no M");
		}catch(Exception ex){
			Assert.assertEquals("Mensagem errada", "O CD deve conter no minimo uma musica.", ex.getMessage());
		}
	}

	@Test
	public void testaSetCarroChefe() throws Exception{
		Assert.assertTrue(cd2.getCarroChefe().equals("Jump"));
		Assert.assertTrue(cd1.getCarroChefe().equals(""));
		cd2.setCarroChefe("Can't stop loving you");
		cd1.setCarroChefe("Numb");
		Assert.assertTrue(cd2.getCarroChefe().equals("Can't stop loving you"));
		Assert.assertTrue(cd1.getCarroChefe().equals("Numb"));
		Assert.assertFalse(cd2.getCarroChefe().equals("Jump"));
		Assert.assertFalse(cd1.getCarroChefe().equals(""));
	}

	@Test
	public void testaGetTitulo(){
		Assert.assertTrue(cd1.getTitulo().equals("Meteora"));
		Assert.assertTrue(cd2.getTitulo().equals("Best Hits"));
		Assert.assertFalse(cd1.getTitulo().equals(""));
		Assert.assertFalse(cd2.getTitulo().equals(""));
		Assert.assertFalse(cd1.getTitulo().equals(null));
		Assert.assertFalse(cd2.getTitulo().equals(null));
	}
	
	@Test
	public void testaGetAutor(){
		Assert.assertTrue(cd1.getAutor().equals("LP"));
		Assert.assertTrue(cd2.getAutor().equals("VH"));
		Assert.assertFalse(cd1.getAutor().equals(""));
		Assert.assertFalse(cd2.getAutor().equals(""));
		Assert.assertFalse(cd1.getAutor().equals(null));
		Assert.assertFalse(cd2.getAutor().equals(null));
	}
	
	@Test
	public void testaMetodosComFaixas(){
		//cd1
		cd1.addFaixa("Numb");
		cd1.addFaixa("What Ive Done");
		cd1.addFaixa("Faint");
		cd1.addFaixa("Leave Out All The Rest");
		cd1.addFaixa("Breaking The Habbit");
		Assert.assertTrue(cd1.getFaixas().equals("[Numb, What Ive Done, Faint, Leave Out All The Rest, Breaking The Habbit]"));
		Assert.assertTrue(cd1.getUmaFaixa(1).equals("Numb"));
		Assert.assertTrue(cd1.getUmaFaixa(3).equals("Faint"));
		Assert.assertTrue(cd1.getUmaFaixa(5).equals("Breaking The Habbit"));
		
		
		//cd2
		cd2.addFaixa("Jump");
		cd2.addFaixa("Panama");
		cd2.addFaixa("Cant stop loving you");
		cd2.addFaixa("Hell");
		cd2.addFaixa("Hell2");
		Assert.assertTrue(cd2.getFaixas().equals("[Jump, Panama, Cant stop loving you, Hell, Hell2]"));
		Assert.assertTrue(cd2.getUmaFaixa(2).equals("Panama"));
		Assert.assertTrue(cd2.getUmaFaixa(4).equals("Hell"));
	}
	
	@Test
	public void testaToString() throws Exception{
		//cd1
		cd1.addFaixa("Numb");
		cd1.addFaixa("What Ive Done");
		cd1.addFaixa("Faint");
		cd1.addFaixa("Leave Out All The Rest");
		cd1.addFaixa("Breaking The Habbit");
		Assert.assertTrue(cd1.toString().equals("[Autor: LP, Titulo: Meteora, Carro chefe: , Faixas: 1 - Numb / 2 - What Ive Done / 3 - Faint / 4 - Leave Out All The Rest / 5 - Breaking The Habbit]"));
		cd1.setCarroChefe("Numb");
		Assert.assertFalse(cd1.toString().equals("[Autor: LP, Titulo: Meteora, Carro chefe: , Faixas: 1 - Numb / 2 - What Ive Done / 3 - Faint / 4 - Leave Out All The Rest / 5 - Breaking The Habbit]"));
		Assert.assertTrue(cd1.toString().equals("[Autor: LP, Titulo: Meteora, Carro chefe: Numb, Faixas: 1 - Numb / 2 - What Ive Done / 3 - Faint / 4 - Leave Out All The Rest / 5 - Breaking The Habbit]"));
		
		//cd2
		cd2.addFaixa("Jump");
		cd2.addFaixa("Panama");
		cd2.addFaixa("Cant stop loving you");
		cd2.addFaixa("Hell");
		cd2.addFaixa("Hell2");
		Assert.assertTrue(cd2.toString().equals("[Autor: VH, Titulo: Best Hits, Carro chefe: Jump, Faixas: 1 - Jump / 2 - Panama / 3 - Cant stop loving you / 4 - Hell / 5 - Hell2]"));
	}
	
	@Test
	public void testaEquals(){
		//cd1
		Assert.assertFalse(cd1.equals(cd2));
		Assert.assertFalse(cd1.equals(cd3));
		Assert.assertFalse(cd1.equals(cd5));
		Assert.assertTrue(cd1.equals(cd4));
		//cd2
		Assert.assertFalse(cd2.equals(cd3));
		Assert.assertFalse(cd2.equals(cd4));
		Assert.assertFalse(cd2.equals(cd1));
		Assert.assertTrue(cd2.equals(cd5));
		//cd3
		Assert.assertFalse(cd3.equals(cd1));
		Assert.assertFalse(cd3.equals(cd2));
		Assert.assertFalse(cd3.equals(cd4));
		Assert.assertFalse(cd3.equals(cd5));
		//cd4
		Assert.assertTrue(cd4.equals(cd1));
		Assert.assertFalse(cd4.equals(cd2));
		Assert.assertFalse(cd4.equals(cd3));
		Assert.assertFalse(cd4.equals(cd5));
		//cd5
		Assert.assertTrue(cd5.equals(cd2));
		Assert.assertFalse(cd5.equals(cd1));
		Assert.assertFalse(cd5.equals(cd3));
		Assert.assertFalse(cd5.equals(cd4));
		
		
	}
}