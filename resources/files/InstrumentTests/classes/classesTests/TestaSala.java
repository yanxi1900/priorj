package lp2.lab06;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TestaSala {
    private Sala sala;

    @Before
    public void criaObjetos() throws Exception{
        	sala = new Sala(10, 10);
    }
    
    @Test
    public void testaCriaSala(){
    	try{
    		new Sala(-1, 10);
    		Assert.fail("Esperava excecao energia errada");
    	}catch(Exception e){
    		Assert.assertEquals("Mensagem errada", "Parâmetros incorretos para a criação da sala.", e.getMessage());
    	}
    	
    	try{
    		new Sala(10, -1);
    		Assert.fail("Esperava excecao energia errada");
    	}catch(Exception e){
    		Assert.assertEquals("Mensagem errada", "Parâmetros incorretos para a criação da sala.", e.getMessage());
    	}
    	
    	try{
    		new Sala(-1, -1);
    		Assert.fail("Esperava excecao energia errada");
    	}catch(Exception e){
    		Assert.assertEquals("Mensagem errada", "Parâmetros incorretos para a criação da sala.", e.getMessage());
    	}
    	
    	try{
    		new Sala(0, 0);
    		Assert.fail("Esperava excecao energia errada");
    	}catch(Exception e){
    		Assert.assertEquals("Mensagem errada", "Parâmetros incorretos para a criação da sala.", e.getMessage());
    	}
    	
    	try{
    		new Sala(10, 0);
    		Assert.fail("Esperava excecao energia errada");
    	}catch(Exception e){
    		Assert.assertEquals("Mensagem errada", "Parâmetros incorretos para a criação da sala.", e.getMessage());
    	}
    	
    	try{
    		new Sala(0, 10);
    		Assert.fail("Esperava excecao energia errada");
    	}catch(Exception e){
    		Assert.assertEquals("Mensagem errada", "Parâmetros incorretos para a criação da sala.", e.getMessage());
    	}
    	
    	try{
    		new Sala(0, 0);
    		Assert.fail("Esperava excecao energia errada");
    	}catch(Exception e){
    		Assert.assertEquals("Mensagem errada", "Parâmetros incorretos para a criação da sala.", e.getMessage());
    	}
    }

    @Test
    public void testaVerificaPosicaoValida(){
    	Assert.assertTrue(sala.posicaoValida(0, 0));
    	Assert.assertTrue(sala.posicaoValida(9, 9));
    	
    	Assert.assertFalse(sala.posicaoValida(-1, -1));
    	Assert.assertFalse(sala.posicaoValida(-1, 0));
    	Assert.assertFalse(sala.posicaoValida(0, -1));
    	
    	Assert.assertFalse(sala.posicaoValida(10, 10));
    	Assert.assertFalse(sala.posicaoValida(10, 0));
    	Assert.assertFalse(sala.posicaoValida(0, 10));
    
    	Assert.assertFalse(sala.posicaoValida(10, -1));
    	Assert.assertFalse(sala.posicaoValida(-1, 10));
    }
    
    @Test
    public void testaInserirObstaculos(){
    	Assert.assertTrue(sala.inserirObstaculo(0, 0));
    	Assert.assertFalse(sala.verificarPosicaoLivre(0, 0));
    		
    	Assert.assertTrue(sala.inserirObstaculo(9, 9));
    	Assert.assertFalse(sala.verificarPosicaoLivre(9, 9));
    	
    	Assert.assertFalse(sala.inserirObstaculo(10, 10));
    	Assert.assertFalse(sala.inserirObstaculo(-1, -1));
    	Assert.assertFalse(sala.inserirObstaculo(-1, 0));
    	Assert.assertFalse(sala.inserirObstaculo(0, -1));
    }
    
    @Test
    public void testaSetPosicao(){
    	Assert.assertTrue(sala.setPosicao(0, 0, Sala.OCUPADO));
    	Assert.assertFalse(sala.verificarPosicaoLivre(0, 0));
    	
    	Assert.assertTrue(sala.setPosicao(0, 0, Sala.LIVRE));
    	Assert.assertTrue(sala.verificarPosicaoLivre(0, 0));
    		
    	Assert.assertTrue(sala.setPosicao(9, 9, Sala.OCUPADO));
    	Assert.assertFalse(sala.verificarPosicaoLivre(9, 9));
    	
    	Assert.assertTrue(sala.setPosicao(9, 9, Sala.LIVRE));
    	Assert.assertTrue(sala.verificarPosicaoLivre(9, 9));
    	
    	Assert.assertFalse(sala.setPosicao(10, 10, Sala.OCUPADO));
    	Assert.assertFalse(sala.setPosicao(-1, -1, Sala.OCUPADO));
    	Assert.assertFalse(sala.setPosicao(-1, 0, Sala.OCUPADO));
    	Assert.assertFalse(sala.setPosicao(0, -1, Sala.OCUPADO));
    }    
}