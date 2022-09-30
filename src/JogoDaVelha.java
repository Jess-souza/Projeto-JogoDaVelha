import java.util.Scanner;

public class JogoDaVelha {
    private int jogador = 1;

    private int[][] tabuleiro = new int[3][3];

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
                        out += "_ ";
                        break;
                    case 1:
                        out += "O ";
                        break;
                    case 2:
                        out += "X ";
                        break;
                }
            }
            out += "\n";
        }
        return out;
    }

    public void executar() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Digite o nome do primeiro jogador: ");
        String jogador1 = sc.next();
        jogador1 = jogador1;
        System.out.println("Digite o nome do segundo jogador: ");
        String jogador2 = sc.next();
        jogador2 = jogador2;
        System.out.println("Vamos começar o jogo, boa sorte!");
        System.out.printf("%s você é o O \n", jogador1);
        System.out.printf("%s você é o X \n", jogador2);

        Scanner entrada = new Scanner(System.in);
        while (vencedor() == 0) {
            System.out.println(this);
            if (jogador == 1) {
                System.out.println("Jogador: " + jogador1);
            } else {
                System.out.println("Jogador: " + jogador2);
            }
            System.out.print("Coluna: ");
            int coluna = entrada.nextInt();
            System.out.print("Linha: ");
            int linha = entrada.nextInt();
            if (!jogar(coluna, linha)) {
                System.out.println("Jogada invalida, tente novamente...");
            }
        }

        System.out.println(this);
        //   System.out.println(vencedor());
        if (vencedor() > 0 && vencedor() == 2) {
            System.out.printf("%s foi o ganhador da rodada", jogador2);
        } else if (vencedor() > 0 && vencedor() == 1) {
            System.out.printf("%s foi o ganhador da rodada", jogador1);
        } else {
            System.out.println("A rodada encerrou com um empate!");
        }
    }

    public static void main(String[] args) {
        System.out.println("Bem vind@ ao jogo da velha!");
        JogoDaVelha jogo = new JogoDaVelha();
        jogo.executar();
    }
}

