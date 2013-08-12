/**
 * @author Igor de Araujo Meira
 */

package lp2.lab07;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

public class TestaCDteca {

	private MinhaCDteca cdteca1;
	private MinhaCDteca cdteca2;
	private CD cd1;
	private CD cd2;
	private CD cd3;
	private CD cd4;
	private CD cd5;
	
	@Before
	public void testaCriaObjetos() throws Exception{
		cdteca1 = new MinhaCDteca();
		cdteca2 = new MinhaCDteca();
		cd1 = new CD("Meteora", "LP", 5);
		cd2 = new CD("Best Hits", "VH", 5, "Jump");
		cd3 = new CD("Yellow Submarine", "The Beatles", 13, "Yellow Submarine");
		cd4 = new CD("Meteora", "LP", 5);
		cd5 = new CD("Best Hits", "VH", 5, "Jump");
		adicionaMusicas();
	}
	
	private void adicionaMusicas() {
		cd1.addFaixa("Numb");
		cd1.addFaixa("What Ive Done");
		cd1.addFaixa("Faint");
		cd1.addFaixa("Leave Out All The Rest");
		cd1.addFaixa("Breaking The Habbit");
		
		cd2.addFaixa("Jump");
		cd2.addFaixa("Panama");
		cd2.addFaixa("Cant stop loving you");
		cd2.addFaixa("Hell");
		cd2.addFaixa("Hell2");
		
	}

	private void adicionaCDs(){
		cdteca1.adicionaCD(cd1);
		cdteca1.adicionaCD(cd2);
		
		cdteca2.adicionaCD(cd2);
		cdteca2.adicionaCD(cd1);
	}
	
	@Test
	public void testaAdicionaCD(){
		Assert.assertTrue(cdteca1.numeroDeCDs() == 0);
		cdteca1.adicionaCD(cd1);
		Assert.assertTrue(cdteca1.numeroDeCDs() == 1);
		Assert.assertFalse(cdteca1.numeroDeCDs() == 0);
		cdteca1.adicionaCD(cd2);
		Assert.assertTrue(cdteca1.numeroDeCDs() == 2);
		Assert.assertFalse(cdteca1.numeroDeCDs() == 1);
	}
	
	@Test
	public void testaRemoveCD(){
		adicionaCDs();
		Assert.assertTrue(cdteca1.numeroDeCDs() == 2);
		cdteca1.removeCD(cd2);
		Assert.assertTrue(cdteca1.numeroDeCDs() == 1);
		Assert.assertFalse(cdteca1.numeroDeCDs() == 2);
		cdteca1.removeCD(cd1);
		Assert.assertTrue(cdteca1.numeroDeCDs() == 0);
		Assert.assertFalse(cdteca1.numeroDeCDs() == 1);
	}
	
	@Test
	public void testaPesquisaCD(){
		adicionaCDs();
		Assert.assertTrue(cdteca1.pesquisaCD(cd1).equals(cd1.toString()));
		Assert.assertTrue(cdteca1.pesquisaCD(cd2).equals(cd2.toString()));
		Assert.assertTrue(cdteca2.pesquisaCD(cd2).equals(cd2.toString()));
		Assert.assertTrue(cdteca2.pesquisaCD(cd1).equals(cd1.toString()));
		Assert.assertEquals(null, cdteca2.pesquisaCD(cd3));
	}
	
	@Test
	public void testaEquals(){
		adicionaCDs();
		Assert.assertTrue(cdteca1.equals(cdteca2));
		cdteca2.adicionaCD(cd3);
		Assert.assertFalse(cdteca1.equals(cdteca2));
		cdteca2.removeCD(cd3);
		cdteca2.adicionaCD(cd5);
		cdteca1.adicionaCD(cd4);
		Assert.assertTrue(cdteca1.equals(cdteca2));
	}
}