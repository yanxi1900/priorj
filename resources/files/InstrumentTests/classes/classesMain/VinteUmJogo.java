package lp2.lab04;

import p1.aplic.cartas.*;
import java.util.*;

public class VinteUmJogo {

    private static final int VINTE_UM = 21;

    private static Mao maoJogador;

    private static Mao maoMesa;

    private static Baralho baralho;

    private static int pontos_jogador = 0;

    private static int pontos_mesa = 0;

    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        baralho = new Baralho();
        while (true) {
            if (baralho.númeroDeCartas() < 2) {
                baralho = new Baralho();
            }
            maoJogador = new Mao();
            maoMesa = new Mao();
            baralho.baralhar();
            int ganhador = 0;
            ZeraPontuação();
            IniciarJogo();
            System.out.println();
            System.out.println("Cartas restantes no baralho: " + baralho.númeroDeCartas());
            while (true) {
                if (baralho.númeroDeCartas() == 0) {
                    break;
                }
                if ((pontos_jogador > VINTE_UM) && (pontos_mesa > VINTE_UM)) {
                    break;
                } else if ((pontos_jogador == VINTE_UM) || (pontos_mesa == VINTE_UM)) {
                    break;
                } else if ((pontos_jogador > VINTE_UM) && (pontos_mesa < VINTE_UM)) {
                    break;
                } else if ((pontos_jogador < VINTE_UM) && (pontos_mesa > VINTE_UM)) {
                    break;
                }
                System.out.println();
                System.out.println("Para receber outra carta: qualquer tecla.\nPara parar: t.");
                String comando = (new Scanner(System.in)).nextLine();
                if ((comando.equals("t")) && (pontos_mesa > 17)) {
                    break;
                } else if ((comando.equals("t")) && (pontos_mesa < 17)) {
                    break;
                } else {
                    DaCartasJogador1();
                    if ((pontos_jogador >= VINTE_UM) || (pontos_mesa >= VINTE_UM)) {
                        break;
                    }
                    if (pontos_mesa < 17) {
                        DaCartasMesa();
                    }
                    System.out.println("Cartas restantes no baralho: " + baralho.númeroDeCartas());
                }
                if (baralho.númeroDeCartas() == 0) {
                    System.out.println("O baralho acabou!");
                    break;
                }
            }
            SoMesa();
            ganhador = joga();
            Vencedor(ganhador);
            System.out.println();
            System.out.println("Cartas restantes no baralho: " + baralho.númeroDeCartas());
            System.out.println();
            System.out.println("Jogar novamente? S/N");
            String não_jogar = sc.next();
            if (não_jogar.equals("N")) {
                break;
            }
        }
    }

    private static void ZeraPontuação() {
        pontos_jogador = 0;
        pontos_mesa = 0;
    }

    private static void ContadorMesa() {
        String carta_recebida = maoMesa.cartaNoTopo().toString();
        if ((carta_recebida.contains("VALETE")) || (carta_recebida.contains("DAMA")) || (carta_recebida.contains("REI"))) {
            pontos_mesa += 10;
        } else {
            pontos_mesa += (maoMesa.cartaNoTopo()).getValor();
        }
    }

    private static void ContadorJogador() {
       String carta_recebida = maoJogador.cartaNoTopo().toString();
       if ((carta_recebida.contains("VALETE")) || (carta_recebida.contains("DAMA")) || (carta_recebida.contains("REI"))) {
           pontos_jogador += 10;
        } else {
            pontos_jogador += (maoJogador.cartaNoTopo()).getValor();
        }
    }

    private static void Vencedor(int ganhador) {
        if (ganhador < 0) {
            System.out.println();
            MostraMaoJogador(maoJogador);
            MostraMesa(maoMesa);
            System.out.println("\nJogador e Mesa estouraram.\nNao houve ganhador!");
        } else if (ganhador == 0) {
            System.out.println();
            MostraMaoJogador(maoJogador);
            MostraMesa(maoMesa);
            System.out.println("\nEmpate!");
        } else if (ganhador == 1) {
            System.out.println();
            MostraMaoJogador(maoJogador);
            MostraMesa(maoMesa);
            System.out.println("\nMesa estourou.\nO ganhador foi o Jogador!");
        } else if (ganhador == 2) {
            System.out.println();
            MostraMaoJogador(maoJogador);
            MostraMesa(maoMesa);
            System.out.println("\nJogador estourou\nO ganhador foi a Mesa!");
        } else if (ganhador == 3) {
            if (pontos_jogador < pontos_mesa) {
                System.out.println();
                MostraMaoJogador(maoJogador);
                MostraMesa(maoMesa);
                System.out.println("\nO ganhador foi a Mesa!");
            } else if (pontos_jogador > pontos_mesa) {
                System.out.println();
                MostraMaoJogador(maoJogador);
                MostraMesa(maoMesa);
                System.out.println("\nO ganhador foi o Jogador!");
            } else if (pontos_jogador == pontos_mesa) {
                System.out.println();
                MostraMaoJogador(maoJogador);
                MostraMesa(maoMesa);
                System.out.println("\nO ganhador foi a Mesa!");
            }
        }
    }

    private static void SoMesa() {
        if ((pontos_mesa < 17) && (baralho.númeroDeCartas() > 0)) {
            while (true) {
                DaCartasMesa();
                if (pontos_mesa >= 17 || baralho.númeroDeCartas() == 0) {
                    break;
                }
            }
        }
    }

    private static void MostraMesa(Mao mesa2) {
        System.out.println("Cartas da Mesa: [" + maoMesa + "]\nPontuação da Mesa: " + pontos_mesa);
    }

    private static void MostraMaoJogador(Mao jogador12) {
        System.out.println("Cartas do Jogador: [" + maoJogador + "]\nPontuação do Jogador: " + pontos_jogador);
    }

    private static void IniciarJogo() {
        maoJogador.adicionar(baralho.pegaCarta());
        ContadorJogador();
        maoMesa.adicionar(baralho.pegaCarta());
        ContadorMesa();
        MostraMaoJogador(maoJogador);
        System.out.println("Número de cartas da Mesa: " + maoMesa.númeroDeCartas());
    }

    private static void DaCartasMesa() {
        if (baralho.númeroDeCartas() > 0) {
            maoMesa.adicionar(baralho.pegaCarta());
            ContadorMesa();
            System.out.println("Número de cartas da Mesa: " + maoMesa.númeroDeCartas());
        }
    }

    private static void DaCartasJogador1() {
        if (baralho.númeroDeCartas() > 0) {
            maoJogador.adicionar(baralho.pegaCarta());
            ContadorJogador();
            MostraMaoJogador(maoJogador);
        }
    }

    private static int joga() {
        watchPriorJApp = watchPriorJApp;
        while (true) {
            watchPriorJApp = watchPriorJApp;
            if (pontos_jogador == VINTE_UM && pontos_mesa == VINTE_UM) {
                return 0;
            } else if (pontos_jogador <= VINTE_UM && pontos_mesa > VINTE_UM) {
                return 1;
            } else if (pontos_mesa <= VINTE_UM && pontos_jogador > VINTE_UM) {
                return 2;
            } else if (pontos_jogador > VINTE_UM && pontos_mesa > VINTE_UM) {
                break;
            } else if (pontos_jogador <= VINTE_UM && pontos_mesa <= VINTE_UM) {
                return 3;
            }
        }
        return -1;
    }
}
