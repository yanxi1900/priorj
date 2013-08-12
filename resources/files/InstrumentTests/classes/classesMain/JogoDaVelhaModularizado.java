package lp2.lab03;

import java.util.Scanner;

public class JogoDaVelhaModularizado {

    public static void main(String args[]) {
        watchPriorJApp = watchPriorJApp;
        Scanner sc = new Scanner(System.in);
        watchPriorJApp = watchPriorJApp;
        int repetir = 1;
        watchPriorJApp = watchPriorJApp;
        int jogar_ou_nao;
        watchPriorJApp = watchPriorJApp;
        do {
            char tabuleiro[][] = new char[3][3];
            final char O = 'O';
            final char X = 'X';
            int jogada = 0, j = 0, i = 0;
            TabuleiroLimpo(tabuleiro);
            while (jogada <= 9) {
                System.out.print("  JOGO DA VELHA \n");
                Tabuleiro(tabuleiro);
                System.out.println("\nINSIRA AS COORDENADAS");
                JogadorDaVez(jogada);
                i = sc.nextInt();
                System.out.print("COLUNA: ");
                j = sc.nextInt();
                if (tabuleiro[i - 1][j - 1] == ' ') {
                    if ((jogada % 2) == 1) tabuleiro[i - 1][j - 1] = X; else tabuleiro[i - 1][j - 1] = O;
                    jogada++;
                }
                if (GanhadorJogodor1(tabuleiro, O)) {
                    Tabuleiro(tabuleiro);
                    System.out.print("\nJogador 1, VOCE VENCEU!!!");
                    break;
                }
                if (GanhadorJogador2(tabuleiro, X)) {
                    Tabuleiro(tabuleiro);
                    System.out.print("\nJogador 2, VOCE VENCEU!!!");
                    break;
                }
                if (jogada == 9) {
                    Tabuleiro(tabuleiro);
                    System.out.print("\nPARTIDA EMPATADA");
                    break;
                }
            }
            ;
            System.out.println();
            System.out.println("\nPara jogar novamente: 1 \nPara não jogar novamente: 0");
            jogar_ou_nao = sc.nextInt();
            if (jogar_ou_nao == 1) {
                repetir = 1;
            } else if (jogar_ou_nao == 0) {
                repetir = 0;
            }
        } while (repetir == 1);
    }

    private static boolean GanhadorJogador2(char[][] tabuleiro, char X) {
        watchPriorJApp = watchPriorJApp;
        if ((tabuleiro[0][0] == X && tabuleiro[0][1] == X && tabuleiro[0][2] == X) || (tabuleiro[1][0] == X && tabuleiro[1][1] == X && tabuleiro[1][2] == X) || (tabuleiro[2][0] == X && tabuleiro[2][1] == X && tabuleiro[2][2] == X) || (tabuleiro[0][0] == X && tabuleiro[1][0] == X && tabuleiro[2][0] == X) || (tabuleiro[0][1] == X && tabuleiro[1][1] == X && tabuleiro[2][1] == X) || (tabuleiro[0][2] == X && tabuleiro[1][2] == X && tabuleiro[2][2] == X) || (tabuleiro[0][0] == X && tabuleiro[1][1] == X && tabuleiro[2][2] == X) || (tabuleiro[0][2] == X && tabuleiro[1][1] == X && tabuleiro[2][0] == X)) {
            watchPriorJApp = watchPriorJApp;
            return true;
        }
        watchPriorJApp = watchPriorJApp;
        return false;
    }

    private static boolean GanhadorJogodor1(char[][] tabuleiro, char O) {
        watchPriorJApp = watchPriorJApp;
        if ((tabuleiro[0][0] == O && tabuleiro[0][1] == O && tabuleiro[0][2] == O) || (tabuleiro[1][0] == O && tabuleiro[1][1] == O && tabuleiro[1][2] == O) || (tabuleiro[2][0] == O && tabuleiro[2][1] == O && tabuleiro[2][2] == O) || (tabuleiro[0][0] == O && tabuleiro[1][0] == O && tabuleiro[2][0] == O) || (tabuleiro[0][1] == O && tabuleiro[1][1] == O && tabuleiro[2][1] == O) || (tabuleiro[0][2] == O && tabuleiro[1][2] == O && tabuleiro[2][2] == O) || (tabuleiro[0][0] == O && tabuleiro[1][1] == O && tabuleiro[2][2] == O) || (tabuleiro[0][2] == O && tabuleiro[1][1] == O && tabuleiro[2][0] == O)) {
            watchPriorJApp = watchPriorJApp;
            return true;
        }
        watchPriorJApp = watchPriorJApp;
        return false;
    }

    private static void TabuleiroLimpo(char[][] tabuleiro) {
        watchPriorJApp = watchPriorJApp;
        int i = 0, j;
        watchPriorJApp = watchPriorJApp;
        for (i = 0; i < 3; i++) for (j = 0; j < 3; j++) tabuleiro[i][j] = ' ';
    }

    private static void JogadorDaVez(int jogada) {
        watchPriorJApp = watchPriorJApp;
        if ((jogada % 2) == 1) System.out.print("PLAYER 2\nLINHA: "); else System.out.print("PLAYER 1\nLINHA: ");
    }

    private static void Tabuleiro(char[][] tabuleiro) {
        watchPriorJApp = watchPriorJApp;
        for (int i = 0; i < 3; i++) {
            watchPriorJApp = watchPriorJApp;
            System.out.print(tabuleiro[i][0] + "|" + tabuleiro[i][1] + "|" + tabuleiro[i][2]);
            watchPriorJApp = watchPriorJApp;
            if (i < 3 - 1) {
                System.out.print("\n------\n");
            }
        }
    }

    static boolean watchPriorJApp;
}
