/*
 * Aluno: Igor de Araújo Meira
 * Data: 27 de Junho de 2013
 */

package lp2.lab05;

import java.util.Scanner;

public class ExplorandoOMundoDasSeries {
	
	private static int comando;
	private static int razaoPA;
	private static int termo1PA;
	private static int nPA;
	private static int nFib;
	private static ProgressaoAritmetica pa;	
	private static Fibonacci fib;
	private static boolean existePA = false;
	private static boolean existeFib = false;
	
	public static void main (String args[]) {
		
		Scanner sc = new Scanner(System.in);
		
		do{			
			PerguntasParaComando(sc);
			
			Comando(sc);
			
		}while(comando != 6);
		
	}
	
	//Pergunta e recebe o comando do que deve ser feito.
	private static void PerguntasParaComando(Scanner sc) {
		System.out.println("Para criar uma PA: 1\n" +
							"Para criar uma série de Fibonacci: 2\n" +
							"Para ver um termo da sua PA: 3\n" +
							"Para ver um termo da serie de Fibonacci: 4\n" +
							"Para mostrar os n primeiros termos da sua PA e da série de Fibonacci: 5\n" +
							"Para sair: 6");
		comando = sc.nextInt();
		
	}
	//Executa o comando.
	private static void Comando(Scanner sc) {
		
		if(comando == 1){
			System.out.println("Primeiro termo da PA:");
			termo1PA = sc.nextInt();
			System.out.println("Razão da PA:");
			razaoPA = sc.nextInt();
			pa = new ProgressaoAritmetica(termo1PA, razaoPA);
			existePA = true;
			System.out.println("Sua Progressao Aritmetica foi criada.\n");
		
		}else if(comando == 2){
			fib = new Fibonacci();
			existeFib = true;
			System.out.println("Sua serie de Fibonacci foi criada.\n");
		
		}else if(comando == 3){
			if(existePA){
				System.out.println("Que termo você quer ver?");
				nPA = sc.nextInt();
				System.out.println("Termo " + nPA + ": " + pa.termo(nPA)+"\n");
			}else{
				System.out.println("Você não pode ver o termo de uma PA que não existe. " +
									"Primeiro crie uma PA.\n");
			}
		
		}else if(comando == 4){
			if(existeFib){
				System.out.println("Que termo você quer ver?");
				nFib = sc.nextInt();
				System.out.println("Termo " + nFib + ": " + fib.termo(nFib)+"\n");
			}else{
				System.out.println("Você não pode ver o termo de uma série de Fibonacci que não existe. " +
									"Primeiro crie uma série de Fibonacci.\n");
			}
			
		}else if(comando == 5){
			if(Existencia()){
				System.out.println("Até que termo você deseja ver a PA e a serie de Fibonacci?");
				nPA = sc.nextInt();
				nFib = nPA;
				System.out.println("Progressão Aritmética: " + pa.geraTermos(nPA)+"\n");
				System.out.println("Série de Fibonacci: " + fib.geraTermos(nFib)+"\n");
			}
			
		}
		
	}
	
	//Verifica a existencia de uma PA e de uma serie de Fibonacci, para o comando 5.
	private static boolean Existencia() {
		if(existeFib && existePA){
			return true;
		}else{
			if(existeFib){
				System.out.println("Você não pode ver os termos de uma PA que não existe. " +
									"Primeiro crie uma PA.\n");
			}else if(existePA){
				System.out.println("Você não pode ver os termos de uma série de Fibonacci que não existe. " +
									"Primeiro crie uma série de Fibonacci.\n");
			}else{
				System.out.println("Você não pode ver os termos de uma PA e de uma série de Fibonacci que não existem. " + 
									"Primeiro crie uma PA e uma série de Fibonacci.\n");
			}return false;	
		}	
	}
}