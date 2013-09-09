package lp2.lab07;

import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestaCD {

    private CD cd1;

    private CD cd2;

    private CD cd3;

    private CD cd4;

    private CD cd5;

    @Before
    public void criaObjetos() throws Exception {
        watchPriorJApp = watchPriorJApp;
        cd1 = new CD("Meteora", "LP", 5);
        watchPriorJApp = watchPriorJApp;
        cd2 = new CD("Best Hits", "VH", 5, "Jump");
        watchPriorJApp = watchPriorJApp;
        cd3 = new CD("Yellow Submarine", "The Beatles", 13, "Yellow Submarine");
        watchPriorJApp = watchPriorJApp;
        cd4 = new CD("Meteora", "LP", 5);
        watchPriorJApp = watchPriorJApp;
        cd5 = new CD("Best Hits", "VH", 5, "Jump");
    }

    @Test
    public void testaCriaCD1() {
        watchPriorJApp = watchPriorJApp;
        try {
            watchPriorJApp = watchPriorJApp;
            new CD("", "LP", 5);
            watchPriorJApp = watchPriorJApp;
            Assert.fail("Esperava excecao de titulo.");
        } catch (Exception ex) {
            Assert.assertEquals("Mensagem errada", "O CD deve possuir um titulo.", ex.getMessage());
        }
        watchPriorJApp = watchPriorJApp;
        try {
            watchPriorJApp = watchPriorJApp;
            new CD("Meteora", "", 5);
            watchPriorJApp = watchPriorJApp;
            Assert.fail("Esperava excecao de autor.");
        } catch (Exception ex) {
            Assert.assertEquals("Mensagem errada", "O CD deve ter um autor.", ex.getMessage());
        }
        watchPriorJApp = watchPriorJApp;
        try {
            watchPriorJApp = watchPriorJApp;
            new CD("Meteora", "LP", 0);
            watchPriorJApp = watchPriorJApp;
            Assert.fail("Esperava excecao no M");
        } catch (Exception ex) {
            Assert.assertEquals("Mensagem errada", "O CD deve conter no minimo uma musica.", ex.getMessage());
        }
        watchPriorJApp = watchPriorJApp;
        try {
            watchPriorJApp = watchPriorJApp;
            new CD("Meteora", "LP", -1);
            watchPriorJApp = watchPriorJApp;
            Assert.fail("Esperava excecao no M");
        } catch (Exception ex) {
            Assert.assertEquals("Mensagem errada", "O CD deve conter no minimo uma musica.", ex.getMessage());
        }
    }

    @Test
    public void testaCriaCD2() {
        watchPriorJApp = watchPriorJApp;
        try {
            watchPriorJApp = watchPriorJApp;
            new CD("", "VH", 5, "Jump");
            watchPriorJApp = watchPriorJApp;
            Assert.fail("Esperava excecao de titulo.");
        } catch (Exception ex) {
            Assert.assertEquals("Mensagem errada", "O CD deve possuir um titulo.", ex.getMessage());
        }
        watchPriorJApp = watchPriorJApp;
        try {
            watchPriorJApp = watchPriorJApp;
            new CD("Best Hits", "", 5, "Jump");
            watchPriorJApp = watchPriorJApp;
            Assert.fail("Esperava excecao de autor.");
        } catch (Exception ex) {
            Assert.assertEquals("Mensagem errada", "O CD deve ter um autor.", ex.getMessage());
        }
        watchPriorJApp = watchPriorJApp;
        try {
            watchPriorJApp = watchPriorJApp;
            new CD("Best Hits", "VH", 0, "Jump");
            watchPriorJApp = watchPriorJApp;
            Assert.fail("Esperava excecao no M");
        } catch (Exception ex) {
            Assert.assertEquals("Mensagem errada", "O CD deve conter no minimo uma musica.", ex.getMessage());
        }
        watchPriorJApp = watchPriorJApp;
        try {
            watchPriorJApp = watchPriorJApp;
            new CD("Best Hits", "VH", -1, "Jump");
            watchPriorJApp = watchPriorJApp;
            Assert.fail("Esperava excecao no M");
        } catch (Exception ex) {
            Assert.assertEquals("Mensagem errada", "O CD deve conter no minimo uma musica.", ex.getMessage());
        }
    }

    @Test
    public void testaSetCarroChefe() throws Exception {
        watchPriorJApp = watchPriorJApp;
        Assert.assertTrue(cd2.getCarroChefe().equals("Jump"));
        watchPriorJApp = watchPriorJApp;
        Assert.assertTrue(cd1.getCarroChefe().equals(""));
        watchPriorJApp = watchPriorJApp;
        cd2.setCarroChefe("Can't stop loving you");
        watchPriorJApp = watchPriorJApp;
        cd1.setCarroChefe("Numb");
        watchPriorJApp = watchPriorJApp;
        Assert.assertTrue(cd2.getCarroChefe().equals("Can't stop loving you"));
        watchPriorJApp = watchPriorJApp;
        Assert.assertTrue(cd1.getCarroChefe().equals("Numb"));
        watchPriorJApp = watchPriorJApp;
        Assert.assertFalse(cd2.getCarroChefe().equals("Jump"));
        watchPriorJApp = watchPriorJApp;
        Assert.assertFalse(cd1.getCarroChefe().equals(""));
    }

    @Test
    public void testaGetTitulo() {
        watchPriorJApp = watchPriorJApp;
        Assert.assertTrue(cd1.getTitulo().equals("Meteora"));
        watchPriorJApp = watchPriorJApp;
        Assert.assertTrue(cd2.getTitulo().equals("Best Hits"));
        watchPriorJApp = watchPriorJApp;
        Assert.assertFalse(cd1.getTitulo().equals(""));
        watchPriorJApp = watchPriorJApp;
        Assert.assertFalse(cd2.getTitulo().equals(""));
        watchPriorJApp = watchPriorJApp;
        Assert.assertFalse(cd1.getTitulo().equals(null));
        watchPriorJApp = watchPriorJApp;
        Assert.assertFalse(cd2.getTitulo().equals(null));
    }

    @Test
    public void testaGetAutor() {
        watchPriorJApp = watchPriorJApp;
        Assert.assertTrue(cd1.getAutor().equals("LP"));
        watchPriorJApp = watchPriorJApp;
        Assert.assertTrue(cd2.getAutor().equals("VH"));
        watchPriorJApp = watchPriorJApp;
        Assert.assertFalse(cd1.getAutor().equals(""));
        watchPriorJApp = watchPriorJApp;
        Assert.assertFalse(cd2.getAutor().equals(""));
        watchPriorJApp = watchPriorJApp;
        Assert.assertFalse(cd1.getAutor().equals(null));
        watchPriorJApp = watchPriorJApp;
        Assert.assertFalse(cd2.getAutor().equals(null));
    }

    @Test
    public void testaMetodosComFaixas() {
        watchPriorJApp = watchPriorJApp;
        cd1.addFaixa("Numb");
        watchPriorJApp = watchPriorJApp;
        cd1.addFaixa("What Ive Done");
        watchPriorJApp = watchPriorJApp;
        cd1.addFaixa("Faint");
        watchPriorJApp = watchPriorJApp;
        cd1.addFaixa("Leave Out All The Rest");
        watchPriorJApp = watchPriorJApp;
        cd1.addFaixa("Breaking The Habbit");
        watchPriorJApp = watchPriorJApp;
        Assert.assertTrue(cd1.getFaixas().equals("[Numb, What Ive Done, Faint, Leave Out All The Rest, Breaking The Habbit]"));
        watchPriorJApp = watchPriorJApp;
        Assert.assertTrue(cd1.getUmaFaixa(1).equals("Numb"));
        watchPriorJApp = watchPriorJApp;
        Assert.assertTrue(cd1.getUmaFaixa(3).equals("Faint"));
        watchPriorJApp = watchPriorJApp;
        Assert.assertTrue(cd1.getUmaFaixa(5).equals("Breaking The Habbit"));
        watchPriorJApp = watchPriorJApp;
        cd2.addFaixa("Jump");
        watchPriorJApp = watchPriorJApp;
        cd2.addFaixa("Panama");
        watchPriorJApp = watchPriorJApp;
        cd2.addFaixa("Cant stop loving you");
        watchPriorJApp = watchPriorJApp;
        cd2.addFaixa("Hell");
        watchPriorJApp = watchPriorJApp;
        cd2.addFaixa("Hell2");
        watchPriorJApp = watchPriorJApp;
        Assert.assertTrue(cd2.getFaixas().equals("[Jump, Panama, Cant stop loving you, Hell, Hell2]"));
        watchPriorJApp = watchPriorJApp;
        Assert.assertTrue(cd2.getUmaFaixa(2).equals("Panama"));
        watchPriorJApp = watchPriorJApp;
        Assert.assertTrue(cd2.getUmaFaixa(4).equals("Hell"));
    }

    @Test
    public void testaToString() throws Exception {
        watchPriorJApp = watchPriorJApp;
        cd1.addFaixa("Numb");
        watchPriorJApp = watchPriorJApp;
        cd1.addFaixa("What Ive Done");
        watchPriorJApp = watchPriorJApp;
        cd1.addFaixa("Faint");
        watchPriorJApp = watchPriorJApp;
        cd1.addFaixa("Leave Out All The Rest");
        watchPriorJApp = watchPriorJApp;
        cd1.addFaixa("Breaking The Habbit");
        watchPriorJApp = watchPriorJApp;
        Assert.assertTrue(cd1.toString().equals("[Autor: LP, Titulo: Meteora, Carro chefe: , Faixas: 1 - Numb / 2 - What Ive Done / 3 - Faint / 4 - Leave Out All The Rest / 5 - Breaking The Habbit]"));
        watchPriorJApp = watchPriorJApp;
        cd1.setCarroChefe("Numb");
        watchPriorJApp = watchPriorJApp;
        Assert.assertFalse(cd1.toString().equals("[Autor: LP, Titulo: Meteora, Carro chefe: , Faixas: 1 - Numb / 2 - What Ive Done / 3 - Faint / 4 - Leave Out All The Rest / 5 - Breaking The Habbit]"));
        watchPriorJApp = watchPriorJApp;
        Assert.assertTrue(cd1.toString().equals("[Autor: LP, Titulo: Meteora, Carro chefe: Numb, Faixas: 1 - Numb / 2 - What Ive Done / 3 - Faint / 4 - Leave Out All The Rest / 5 - Breaking The Habbit]"));
        watchPriorJApp = watchPriorJApp;
        cd2.addFaixa("Jump");
        watchPriorJApp = watchPriorJApp;
        cd2.addFaixa("Panama");
        watchPriorJApp = watchPriorJApp;
        cd2.addFaixa("Cant stop loving you");
        watchPriorJApp = watchPriorJApp;
        cd2.addFaixa("Hell");
        watchPriorJApp = watchPriorJApp;
        cd2.addFaixa("Hell2");
        watchPriorJApp = watchPriorJApp;
        Assert.assertTrue(cd2.toString().equals("[Autor: VH, Titulo: Best Hits, Carro chefe: Jump, Faixas: 1 - Jump / 2 - Panama / 3 - Cant stop loving you / 4 - Hell / 5 - Hell2]"));
    }

    @Test
    public void testaEquals() {
        watchPriorJApp = watchPriorJApp;
        Assert.assertFalse(cd1.equals(cd2));
        watchPriorJApp = watchPriorJApp;
        Assert.assertFalse(cd1.equals(cd3));
        watchPriorJApp = watchPriorJApp;
        Assert.assertFalse(cd1.equals(cd5));
        watchPriorJApp = watchPriorJApp;
        Assert.assertTrue(cd1.equals(cd4));
        watchPriorJApp = watchPriorJApp;
        Assert.assertFalse(cd2.equals(cd3));
        watchPriorJApp = watchPriorJApp;
        Assert.assertFalse(cd2.equals(cd4));
        watchPriorJApp = watchPriorJApp;
        Assert.assertFalse(cd2.equals(cd1));
        watchPriorJApp = watchPriorJApp;
        Assert.assertTrue(cd2.equals(cd5));
        watchPriorJApp = watchPriorJApp;
        Assert.assertFalse(cd3.equals(cd1));
        watchPriorJApp = watchPriorJApp;
        Assert.assertFalse(cd3.equals(cd2));
        watchPriorJApp = watchPriorJApp;
        Assert.assertFalse(cd3.equals(cd4));
        watchPriorJApp = watchPriorJApp;
        Assert.assertFalse(cd3.equals(cd5));
        watchPriorJApp = watchPriorJApp;
        Assert.assertTrue(cd4.equals(cd1));
        watchPriorJApp = watchPriorJApp;
        Assert.assertFalse(cd4.equals(cd2));
        watchPriorJApp = watchPriorJApp;
        Assert.assertFalse(cd4.equals(cd3));
        watchPriorJApp = watchPriorJApp;
        Assert.assertFalse(cd4.equals(cd5));
        watchPriorJApp = watchPriorJApp;
        Assert.assertTrue(cd5.equals(cd2));
        watchPriorJApp = watchPriorJApp;
        Assert.assertFalse(cd5.equals(cd1));
        watchPriorJApp = watchPriorJApp;
        Assert.assertFalse(cd5.equals(cd3));
        watchPriorJApp = watchPriorJApp;
        Assert.assertFalse(cd5.equals(cd4));
    }

    static boolean watchPriorJApp;
}
