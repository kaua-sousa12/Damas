import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int[][] jogo = new int[8][8];

        String verificarVencedor;
        preencherMatriz(jogo);

        do {
            imprimirMatriz(jogo);
            jogadaBranca(jogo);
            verificarVencedor = verificarVencedor(jogo);
            imprimirMatriz(jogo);
            jogadaPreta(jogo);
        }while (verificarVencedor.equalsIgnoreCase("Continua"));
    }

    public static void jogadaPreta(int[][] jogo){
        Scanner read = new Scanner(System.in);

        boolean jogadaValida = false;
        System.out.println("\nJogador da peça preta: ");



        do {



            System.out.println("\nEscolha a linha que você quer mexer: ");
            int linha = read.nextInt();

            System.out.println("\nEscolha a coluna que você quer mexer: ");
            int coluna = read.nextInt();

            if(linha < 0 || linha > 7 || coluna < 0 || coluna > 7 || jogo[linha][coluna] == 0){
                System.out.println("\nDigite uma linha e coluna válida!");
            }else{

            }
                if(jogo[linha][coluna] == 4) {
                    AndarDama(jogo, linha, coluna);
                    return;
                }

                System.out.println("\nVocê quer ir pra esquerda ou direita?");
                String opcao = read.next();

                switch (opcao.toLowerCase()){
                    case "esquerda":
                        if(coluna == 0 || jogo[linha + 1][coluna - 1] == 2){
                            System.out.println("\nMovimento inválido, tente novamente.");
                        }else{
                            jogo[linha + 1][coluna - 1] = 2;
                            jogo[linha][coluna] = 0;
                            jogadaValida = true;
                        }
                        break;
                    case "direita":
                        if(coluna == 7 || jogo[linha + 1][coluna + 1] == 2){
                            System.out.println("\nMovimento inválido, tente novamente.");
                        }else{
                            jogo[linha + 1][coluna + 1] = 2;
                            jogo[linha][coluna] = 0;
                            jogadaValida = true;
                        }
                        break;
                }

        }while (!jogadaValida);
    }

    public static void jogadaBranca(int[][] jogo){
        Scanner read = new Scanner(System.in);

        boolean jogadaValida = false;
        System.out.println("\nJogador da peça branca: ");

        do {
            System.out.println("\nEscolha a linha que você quer mexer: ");
            int linha = read.nextInt();

            System.out.println("\nEscolha a coluna que você quer mexer: ");
            int coluna = read.nextInt();

            if(linha < 0 || linha > 7 || coluna < 0 || coluna > 7 || jogo[linha][coluna] == 0){
                System.out.println("\nDigite uma linha e coluna válida!");
            }else{

                if(jogo[linha][coluna] == 3) {
                    AndarDama(jogo, linha, coluna);
                    return;
                }

                System.out.println("\nVocê quer ir pra esquerda ou direita?");
                String opcao = read.next();

                switch (opcao.toLowerCase()){
                    case "esquerda":
                        if(coluna == 0 || jogo[linha - 1][coluna - 1] == 1){
                            System.out.println("\nMovimento inválido, tente novamente.");
                        }else{
                            if(jogo[linha - 1][coluna - 1] == 2  && jogo[linha - 2][coluna - 2] == 0){
                                jogo[linha - 2][coluna - 2] = 1;
                                jogo[linha][coluna] = 0;
                                jogo[linha - 1][coluna - 1] = 0;
                                jogadaValida = true;
                            } else if (jogo[linha - 1][coluna - 1] == 2 && jogo[linha - 2][coluna - 2] == 2){
                                System.out.println("Jogada inválida, tente novamente!");
                                continue;
                            }else{
                                jogo[linha - 1][coluna - 1] = 1;
                                jogo[linha][coluna] = 0;
                                jogadaValida = true;
                            }
                        }
                        break;
                    case "direita":
                        if(coluna == 7 || jogo[linha - 1][coluna + 1] == 1){
                            System.out.println("\nMovimento inválido, tente novamente.");
                        }else{
                            if(jogo[linha - 1][coluna + 1] == 2 && jogo[linha - 2][coluna + 2] == 0){
                                jogo[linha - 2][coluna + 2] = 1;
                                jogo[linha][coluna] = 0;
                                jogo[linha - 1][coluna + 1] = 0;
                                jogadaValida = true;
                            }else if (jogo[linha - 1][coluna + 1] == 2 && jogo[linha - 2][coluna + 2] == 2){
                                System.out.println("Jogada inválida, tente novamente!");
                                continue;
                            }else {
                                jogo[linha - 1][coluna + 1] = 1;
                                jogo[linha][coluna] = 0;
                                jogadaValida = true;
                            }
                        }
                        break;
                }
            }
        }while (!jogadaValida);
    }

    public static void preencherMatriz(int[][] jogo){
        // Preenche com as Pretas
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 8; j++) {
                if((i + j) % 2 != 0){
                    jogo[i][j] = 2;
                }
            }
        }
        // Preenche com as brancas
        for (int i = 5; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if((i + j) % 2 != 0){
                    jogo[i][j] = 1;
                }
            }
        }
    }


    public static void VerificarDama(int [][] jogo){

        for (int i = 0; i < jogo.length; i++) {
          for (int j = 0; j < jogo[i].length; j++) {


              if(jogo[i][j] == 1 && i == 0){
                  jogo[i][j] = 3;

                  System.out.println("Parabens sua peça virou dama");
              }
              if(jogo[i][j] == 2 && i == 7){
                  jogo[i][j] = 4;

                  System.out.println("Parabens sua peça virou dama");
              }
          }

        }

    }


    public static void AndarDama(int[][] jogo, int linha, int coluna){

        Scanner scanner = new Scanner(System.in);

        boolean jogadaValida = false;

        do {
            System.out.println("\nVocê quer ir para:");
            System.out.println("frente esquerda");
            System.out.println("frente direita");
            System.out.println("tras esquerda");
            System.out.println("tras direita");

                String opcao = scanner.nextLine();

                switch (opcao.toLowerCase()){
                    case "frente esquerda":
                        if(coluna == 0 || jogo[linha + 1][coluna - 1] == 2){
                            System.out.println("\nMovimento inválido, tente novamente.");
                        }else{
                            jogo[linha + 1][coluna - 1] = 2;
                            jogo[linha][coluna] = 0;
                            jogadaValida = true;
                        }
                        break;
                    case "frente direita":
                        if(coluna == 7 || jogo[linha + 1][coluna + 1] == 2){
                            System.out.println("\nMovimento inválido, tente novamente.");
                        }else{
                            jogo[linha + 1][coluna + 1] = 2;
                            jogo[linha][coluna] = 0;
                            jogadaValida = true;
                        }
                        break;

                    case "tras-esquerda":
                        if(linha ==7 || coluna ==0 || jogo[linha + 1][coluna - 1] != 0){
                            System.out.println("Movimento invalido, tente novamente.");
                        }else{
                            jogo[linha + 1][coluna - 1] = jogo[linha][coluna];
                            jogo[linha][coluna] = 0;
                            jogadaValida = true;
                        }
                        break;

                    case "tras-direita":
                        if (linha ==0 || coluna ==7 || jogo[linha + 1][coluna + 1] != 0){
                            System.out.println("Movimento invalido, tente novamente.");
                        }else{
                            jogo[linha + 1][coluna + 1] = jogo[linha][coluna];
                            jogo[linha][coluna] = 0;
                            jogadaValida = true;
                        }

                }

        }while (!jogadaValida);





    }

    public static void imprimirMatriz(int[][] jogo){
        for (int i = 0; i < jogo.length; i++) {
            for (int j = 0; j < jogo[i].length; j++) {
                System.out.print(jogo[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static String verificarVencedor(int[][] jogo){
        boolean temPreta = false;
        boolean temBranca = false;
        String verificar;

        for (int i = 0; i < jogo.length; i++) {
            for (int j = 0; j < jogo[i].length; j++) {
                if(jogo[i][j] == 1){
                    temBranca = true;
                }

                if(jogo[i][j] == 2){
                    temPreta = true;
                }
            }
        }

        if(temBranca && temPreta){
            verificar = "Continua";
        }else if (temBranca){
            verificar = "Branca";
        } else if (temPreta) {
            verificar = "Preta";
        }else{
            verificar = "Continua";
        }

        return verificar;
    }
}
