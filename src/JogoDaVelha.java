import java.util.InputMismatchException;
import java.util.Scanner;

public class JogoDaVelha {
    private int jogador = 1;
    private int[][] tabuleiro = new int[3][3];
    public String[] jogadores = new String[2];
    int placarJogador1, placarJogador2 = 0;

    public boolean jogar(int x, int y) {
        if ((x < 0) || (x > 2) || (y < 0) || (y > 2)) {
            return false;
        }
        if (tabuleiro[x][y] != 0) {
            return false;
        }
        tabuleiro[x][y] = jogador;
        jogador = (jogador == 1) ? 2 : 1;
        return true;
    }

    public int vencedor() {
        for (int j = 1; j < 3; j++) {
            for (int linha = 0; linha < 3; linha++) {
                boolean fim = true;
                for (int coluna = 0; coluna < 3; coluna++) {
                    if (tabuleiro[coluna][linha] != j) {
                        fim = false;
                    }
                }
                if (fim) {
                    return j;
                }
            }
            for (int coluna = 0; coluna < 3; coluna++) {
                boolean fim = true;
                for (int linha = 0; linha < 3; linha++) {
                    if (tabuleiro[coluna][linha] != j) {
                        fim = false;
                    }
                }
                if (fim) {
                    return j;
                }
            }
            boolean fim = true;
            for (int posicao = 0; posicao < 3; posicao++) {
                if (tabuleiro[posicao][posicao] != j) {
                    fim = false;
                }
            }
            if (fim) {
                return j;
            }
            fim = true;
            for (int posicao = 2; posicao >= 0; posicao--) {
                if (tabuleiro[posicao][2 - posicao] != j) {
                    fim = false;
                }
            }
            if (fim) {
                return j;
            }
        }
        boolean empate = true;
        for (int linha = 0; linha < 3; linha++) {
            for (int coluna = 0; coluna < 3; coluna++) {
                if (tabuleiro[coluna][linha] == 0) {
                    empate = false;
                }
            }
        }
        if (empate) {
            return 3;
        }
        return 0;
    }

    public String toString() {
        String out = "";
        for (int linha = 0; linha < 3; linha++) {
            for (int coluna = 0; coluna < 3; coluna++) {
                switch (tabuleiro[coluna][linha]) {
                    case 0:
                        out += "| _ |";
                        break;
                    case 1:
                        out += "| O |";
                        break;
                    case 2:
                        out += "| X |";
                        break;
                }
            }
            out += "\n";
        }
        return out;
    }

    protected void jogadores() {
        if (vencedor() <= 0) {
            Scanner sc = new Scanner(System.in);
            System.out.println("Digite o nome do primeiro jogador: ");
            String jogador1 = sc.next();
            jogadores[0] = jogador1;
            System.out.println("Digite o nome do segundo jogador: ");
            String jogador2 = sc.next();
            jogadores[1] = jogador2;
            System.out.println("Vamos começar o jogo, boa sorte!");
            System.out.printf("%s você é o O \n", jogador1);
            System.out.printf("%s você é o X \n", jogador2);
        }
    }

    public void executar() {
        jogadores();
        Scanner entrada = new Scanner(System.in);
        boolean validador = true;
        while (vencedor() == 0) {
            System.out.println(this);
            if (jogador == 1) {
                System.out.println("Jogador: " + jogadores[0]);
            } else {
                System.out.println("Jogador: " + jogadores[1]);
            }
            while (validador = true) {
                try {
                    System.out.print("Coluna: ");
                    int coluna = entrada.nextInt();
                    System.out.print("Linha: ");
                    int linha = entrada.nextInt();
                    if (!jogar(coluna, linha)) {
                        System.out.println("Jogada invalida, tente novamente...");
                    }
//                    validador = false;
                    break;
                } catch (InputMismatchException e) {
                    System.out.println("Por favor, digite um número válido!");
                    entrada.nextLine();
                }
                    System.out.print("Coluna: ");
                    int coluna = entrada.nextInt();
                    System.out.print("Linha: ");
                    int linha = entrada.nextInt();
                    if (!jogar(coluna, linha)) {
                        System.out.println("Jogada invalida, tente novamente...");
                }
                validador = false;
            }

        }

        System.out.println(this);
        if (vencedor() > 0 && vencedor() == 2) {
            System.out.printf("%s foi o(a) ganhador(a) da rodada\n", jogadores[1]);
            placarJogador2 += 1;
        } else if (vencedor() > 0 && vencedor() == 1) {
            System.out.printf("%s foi o(a) ganhador(a) da rodada\n", jogadores[0]);
            placarJogador1 += 1;
        } else {
            System.out.println("A rodada encerrou com um empate!");
        }
        System.out.printf("%s possui %d pontos!\n", jogadores[0], placarJogador1);
        System.out.printf("%s possui %d pontos!\n", jogadores[1], placarJogador2);
    }


    public static void main(String[] args) {
        System.out.println("-----------Bem vind@ ao jogo da velha!-----------");
        System.out.println("Instruções: nossas colunas e linhas iniciam do número 0, logo, se você deseja jogar na primeira" +
                " coluna e primeira linha deve digitar 0 para coluna e 0 para linha.");
        JogoDaVelha jogo = new JogoDaVelha();
        jogo.executar();
        Scanner jogar = new Scanner(System.in);
        boolean jogarNovamente = true;
        do {
            System.out.println("Deseja jogar novamente? [1] Sim [2] Não");
            try {
                int resposta = jogar.nextInt();

                switch (resposta) {
                    case 1:
                        jogo.executar();
                        break;
                    case 2:
                        jogarNovamente = false;
                        break;
                    default:
                        System.out.println("Resposta inválida. Digite uma resposta válida, por favor");
                        System.out.println("Deseja jogar novamente? [1] Sim [2] Não");
                        resposta = jogar.nextInt();
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Resposta inválida, o jogo será encerrado!");
                return;
            }
        } while (jogarNovamente == true);
        System.out.println("Obrigada por jogar nosso jogo!");
    }
}

