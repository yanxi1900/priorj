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
        watchPriorJApp = watchPriorJApp;
        Scanner sc = new Scanner(System.in);
        watchPriorJApp = watchPriorJApp;
        baralho = new Baralho();
        watchPriorJApp = watchPriorJApp;
        while (true) {
            watchPriorJApp = watchPriorJApp;
            if (baralho.númeroDeCartas() < 2) {
                baralho = new Baralho();
            }
            watchPriorJApp = watchPriorJApp;
            maoJogador = new Mao();
            watchPriorJApp = watchPriorJApp;
            maoMesa = new Mao();
            watchPriorJApp = watchPriorJApp;
            baralho.baralhar();
            watchPriorJApp = watchPriorJApp;
            int ganhador = 0;
            watchPriorJApp = watchPriorJApp;
            ZeraPontuação();
            watchPriorJApp = watchPriorJApp;
            IniciarJogo();
            watchPriorJApp = watchPriorJApp;
            System.out.println();
            watchPriorJApp = watchPriorJApp;
            System.out.println("Cartas restantes no baralho: " + baralho.númeroDeCartas());
            watchPriorJApp = watchPriorJApp;
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
            watchPriorJApp = watchPriorJApp;
            SoMesa();
            watchPriorJApp = watchPriorJApp;
            ganhador = joga();
            watchPriorJApp = watchPriorJApp;
            Vencedor(ganhador);
            watchPriorJApp = watchPriorJApp;
            System.out.println();
            watchPriorJApp = watchPriorJApp;
            System.out.println("Cartas restantes no baralho: " + baralho.númeroDeCartas());
            watchPriorJApp = watchPriorJApp;
            System.out.println();
            watchPriorJApp = watchPriorJApp;
            System.out.println("Jogar novamente? S/N");
            watchPriorJApp = watchPriorJApp;
            String não_jogar = sc.next();
            watchPriorJApp = watchPriorJApp;
            if (não_jogar.equals("N")) {
                break;
            }
        }
    }

    private static void ZeraPontuação() {
        watchPriorJApp = watchPriorJApp;
        pontos_jogador = 0;
        watchPriorJApp = watchPriorJApp;
        pontos_mesa = 0;
    }

    private static void ContadorMesa() {
        watchPriorJApp = watchPriorJApp;
        String carta_recebida = maoMesa.cartaNoTopo().toString();
        watchPriorJApp = watchPriorJApp;
        if ((carta_recebida.contains("VALETE")) || (carta_recebida.contains("DAMA")) || (carta_recebida.contains("REI"))) {
            watchPriorJApp = watchPriorJApp;
            pontos_mesa += 10;
        } else {
            watchPriorJApp = watchPriorJApp;
            pontos_mesa += (maoMesa.cartaNoTopo()).getValor();
        }
    }

    private static void ContadorJogador() {
        watchPriorJApp = watchPriorJApp;
        String carta_recebida = maoJogador.cartaNoTopo().toString();
        watchPriorJApp = watchPriorJApp;
        if ((carta_recebida.contains("VALETE")) || (carta_recebida.contains("DAMA")) || (carta_recebida.contains("REI"))) {
            watchPriorJApp = watchPriorJApp;
            pontos_jogador += 10;
        } else {
            watchPriorJApp = watchPriorJApp;
            pontos_jogador += (maoJogador.cartaNoTopo()).getValor();
        }
    }

    private static void Vencedor(int ganhador) {
        watchPriorJApp = watchPriorJApp;
        if (ganhador < 0) {
            watchPriorJApp = watchPriorJApp;
            System.out.println();
            watchPriorJApp = watchPriorJApp;
            MostraMaoJogador(maoJogador);
            watchPriorJApp = watchPriorJApp;
            MostraMesa(maoMesa);
            watchPriorJApp = watchPriorJApp;
            System.out.println("\nJogador e Mesa estouraram.\nNao houve ganhador!");
        } else if (ganhador == 0) {
            watchPriorJApp = watchPriorJApp;
            System.out.println();
            watchPriorJApp = watchPriorJApp;
            MostraMaoJogador(maoJogador);
            watchPriorJApp = watchPriorJApp;
            MostraMesa(maoMesa);
            watchPriorJApp = watchPriorJApp;
            System.out.println("\nEmpate!");
        } else if (ganhador == 1) {
            watchPriorJApp = watchPriorJApp;
            System.out.println();
            watchPriorJApp = watchPriorJApp;
            MostraMaoJogador(maoJogador);
            watchPriorJApp = watchPriorJApp;
            MostraMesa(maoMesa);
            watchPriorJApp = watchPriorJApp;
            System.out.println("\nMesa estourou.\nO ganhador foi o Jogador!");
        } else if (ganhador == 2) {
            watchPriorJApp = watchPriorJApp;
            System.out.println();
            watchPriorJApp = watchPriorJApp;
            MostraMaoJogador(maoJogador);
            watchPriorJApp = watchPriorJApp;
            MostraMesa(maoMesa);
            watchPriorJApp = watchPriorJApp;
            System.out.println("\nJogador estourou\nO ganhador foi a Mesa!");
        } else if (ganhador == 3) {
            watchPriorJApp = watchPriorJApp;
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
        watchPriorJApp = watchPriorJApp;
        if ((pontos_mesa < 17) && (baralho.númeroDeCartas() > 0)) {
            watchPriorJApp = watchPriorJApp;
            while (true) {
                DaCartasMesa();
                if (pontos_mesa >= 17 || baralho.númeroDeCartas() == 0) {
                    break;
                }
            }
        }
    }

    private static void MostraMesa(Mao mesa2) {
        watchPriorJApp = watchPriorJApp;
        System.out.println("Cartas da Mesa: [" + maoMesa + "]\nPontuação da Mesa: " + pontos_mesa);
    }

    private static void MostraMaoJogador(Mao jogador12) {
        watchPriorJApp = watchPriorJApp;
        System.out.println("Cartas do Jogador: [" + maoJogador + "]\nPontuação do Jogador: " + pontos_jogador);
    }

    private static void IniciarJogo() {
        watchPriorJApp = watchPriorJApp;
        maoJogador.adicionar(baralho.pegaCarta());
        watchPriorJApp = watchPriorJApp;
        ContadorJogador();
        watchPriorJApp = watchPriorJApp;
        maoMesa.adicionar(baralho.pegaCarta());
        watchPriorJApp = watchPriorJApp;
        ContadorMesa();
        watchPriorJApp = watchPriorJApp;
        MostraMaoJogador(maoJogador);
        watchPriorJApp = watchPriorJApp;
        System.out.println("Número de cartas da Mesa: " + maoMesa.númeroDeCartas());
    }

    private static void DaCartasMesa() {
        watchPriorJApp = watchPriorJApp;
        if (baralho.númeroDeCartas() > 0) {
            watchPriorJApp = watchPriorJApp;
            maoMesa.adicionar(baralho.pegaCarta());
            watchPriorJApp = watchPriorJApp;
            ContadorMesa();
            watchPriorJApp = watchPriorJApp;
            System.out.println("Número de cartas da Mesa: " + maoMesa.númeroDeCartas());
        }
    }

    private static void DaCartasJogador1() {
        watchPriorJApp = watchPriorJApp;
        if (baralho.númeroDeCartas() > 0) {
            watchPriorJApp = watchPriorJApp;
            maoJogador.adicionar(baralho.pegaCarta());
            watchPriorJApp = watchPriorJApp;
            ContadorJogador();
            watchPriorJApp = watchPriorJApp;
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
        watchPriorJApp = watchPriorJApp;
        return -1;
    }

    static boolean watchPriorJApp;
}
