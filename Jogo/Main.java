import java.util.Scanner;

 // Kauã Sousa Silva
    // Eduardo Mendonça dos Santos
    // João Pedro Gama

public class MenuJogo {

    Scanner scanner = new Scanner(System.in);

    public void exibirMenu() {
        int opcao;

        do {
            System.out.println("\n===== MENU DO JOGO =====");
            System.out.println("1 - Instruções");
            System.out.println("2 - Jogar");
            System.out.println("3 - Créditos");
            System.out.println("4 - Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    instrucoes();
                    break;
                case 2:
                    Jogo jogo = new Jogo(); // Cria o objeto da classe Jogo
                    jogo.IniciarJogo();     // Chama o metofo da classe jogo
                    break;
                case 3:
                    creditos();
                    break;
                case 4:
                    sair();
                    break;
                default:
                    System.out.println("Opção inválida! Tente novamente.");
            }

        } while (opcao != 4);
    }

    public void instrucoes() {
        System.out.println("\n--- INSTRUÇÕES ---");
        System.out.println("\n--- ♟️ INSTRUÇÕES DO JOGO DE DAMAS (Versão Programada) ---");
        System.out.println("O objetivo é capturar todas as peças do adversário ou bloqueá-lo.");
        System.out.println("----------------------------------------------------------");

        System.out.println("### CÓDIGO DAS PEÇAS NO TABULEIRO:");
        System.out.println("0: Vazio");
        System.out.println("1: Peça Branca (Normal) / 3: Dama Branca");
        System.out.println("2: Peça Preta (Normal) / 4: Dama Preta");
        System.out.println();

        System.out.println("### 1. MOVIMENTO BÁSICO");
        System.out.println("  - Peças Normais: Movem-se uma casa na diagonal, APENAS para frente.");
        System.out.println("    - Branca (1): Apenas para CIMA (Linhas menores).");
        System.out.println("    - Preta (2): Apenas para BAIXO (Linhas maiores).");
        System.out.println("  - Dama (3 e 4): Move-se para frente e para trás, qualquer número de casas na diagonal vazia.");
        System.out.println();

        System.out.println("### 2. CAPTURA ⚔️");
        System.out.println("  - Captura Obrigatória: (Não imposta no código) Você deve capturar se puder.");
        System.out.println("  - Peças Normais: Capturam pulando uma peça inimiga para uma casa vazia.");
        System.out.println("    - Podem capturar tanto para frente quanto para trás.");
        System.out.println("  - Dama: Captura uma única peça inimiga a qualquer distância na diagonal.");
        System.out.println("    - Deve PARAR obrigatoriamente na primeira casa vazia após o inimigo capturado.");
        System.out.println("  - Restrição: É proibido saltar sobre duas peças seguidas.");
        System.out.println();

        System.out.println("### 3. PROMOÇÃO (COROAÇÃO) 👑");
        System.out.println("  - Peça Branca (1) vira Dama (3) ao alcançar a Linha 0 (topo).");
        System.out.println("  - Peça Preta (2) vira Dama (4) ao alcançar a Linha 7 (base).");
        System.out.println();

        System.out.println("### 4. VENCEDOR");
        System.out.println("  - Vence quem capturar todas as peças do adversário.");
    }

    public void creditos() {
        System.out.println("\n--- CRÉDITOS ---");
        System.out.println("Jogo desenvolvido por: ");
        System.out.println(" Eduardo dos Santos");
        System.out.println(" Kauã Souza");
        System.out.println(" João Pedro");
    }

    public void sair() {
        System.out.println("\nSaindo do jogo... Até mais!");
    }

    public static void main(String[] args) {
        MenuJogo menu = new MenuJogo();
        menu.exibirMenu();
    }
}



class Jogo {

   

    public void IniciarJogo(){
        int[][] jogo = new int[8][8];

        String verificarVencedor;
        preencherMatriz(jogo);

        do {
            imprimirMatriz(jogo);

            jogadaBranca(jogo); // jogador branco joga
            verificarDama(jogo); // verifica promoção para dama
            verificarVencedor = verificarVencedor(jogo);
            if (!verificarVencedor.equalsIgnoreCase("Continua")) break;

            imprimirMatriz(jogo);

            jogadaPreta(jogo); // jogador preto joga
            verificarDama(jogo); // verifica promoção para dama
            verificarVencedor = verificarVencedor(jogo);

        } while (verificarVencedor.equalsIgnoreCase("Continua"));

        if(verificarVencedor.equalsIgnoreCase("Preta"))
            System.out.println("Parabéns! O jogador das pretas venceu.");
        else if (verificarVencedor.equalsIgnoreCase("Branca")) {
            System.out.println("Parabéns! O jogador das brancas venceu.");
        }
    }

    //   JOGADA PRETA
    public static void jogadaPreta(int[][] jogo) {
        Scanner read = new Scanner(System.in);

        boolean jogadaValida = false;
        System.out.println("\nJogador da peça preta: ");

        
        do {
            System.out.println("\nEscolha a linha que você quer mexer: ");
            int linha = read.nextInt();

            System.out.println("\nEscolha a coluna que você quer mexer: ");
            int coluna = read.nextInt();

            // valida posição
            if (linha < 0 || linha > 7 || coluna < 0 || coluna > 7 || jogo[linha][coluna] == 0) {
                System.out.println("\nDigite uma linha e coluna válida!");
            } else {

                // caso seja dama preta
                if (jogo[linha][coluna] == 4) {
                    jogadaDamaPreta(jogo, linha, coluna);
                    jogadaValida = true;
                    continue;
                }

                System.out.println("\nVocê quer ir pra esquerda ou direita?");
                String opcao = read.next();

                switch (opcao.toLowerCase()) {

                    case "esquerda":
                        // verifica movimento básico inválido
                        if (coluna == 0 || jogo[linha + 1][coluna - 1] == 2) {
                            System.out.println("\nMovimento inválido, tente novamente.");

                        } else {
                         
                            if ((jogo[linha + 1][coluna - 1] == 1 || jogo[linha + 1][coluna - 1] == 3)
                                    && jogo[linha + 2][coluna - 2] == 0) {

                                jogo[linha + 2][coluna - 2] = 2; // move
                                jogo[linha][coluna] = 0;
                                jogo[linha + 1][coluna - 1] = 0; // remove inimigo
                                jogadaValida = true;

                            } else if ((jogo[linha + 1][coluna - 1] == 1 || jogo[linha + 1][coluna - 1] == 3)
                                    && (jogo[linha + 2][coluna - 2] == 1 || jogo[linha + 2][coluna - 2] == 3)) {

                                // duas peças inimigas juntas → proibir
                                System.out.println("Jogada inválida, tente novamente!");
                                continue;

                            } else {
                              
                                jogo[linha + 1][coluna - 1] = 2;
                                jogo[linha][coluna] = 0;
                                jogadaValida = true;
                            }
                        }
                        break;

                    case "direita":
                        if (coluna == 7 || jogo[linha + 1][coluna + 1] == 2) {
                            System.out.println("\nMovimento inválido, tente novamente.");
                        } else {

                          
                            if ((jogo[linha + 1][coluna + 1] == 1 || jogo[linha + 1][coluna + 1] == 3)
                                    && jogo[linha + 2][coluna + 2] == 0) {

                                jogo[linha + 2][coluna + 2] = 2;
                                jogo[linha][coluna] = 0;
                                jogo[linha + 1][coluna + 1] = 0;
                                jogadaValida = true;

                            } else if ((jogo[linha + 1][coluna + 1] == 1 || jogo[linha + 1][coluna + 1] == 3)
                                    && (jogo[linha + 2][coluna + 2] == 1 || jogo[linha + 2][coluna + 2] == 3)) {

                                System.out.println("Jogada inválida, tente novamente!");
                                continue;

                            } else {
                                jogo[linha + 1][coluna + 1] = 2;
                                jogo[linha][coluna] = 0;
                                jogadaValida = true;
                            }
                        }
                        break;
                }
            }
        } while (!jogadaValida);
    }

    //   JOGADA BRANCA
    public static void jogadaBranca(int[][] jogo) {
        Scanner read = new Scanner(System.in);

        boolean jogadaValida = false;
        System.out.println("\nJogador da peça branca: ");

      
        do {
            System.out.println("\nEscolha a linha que você quer mexer: ");
            int linha = read.nextInt();

            System.out.println("\nEscolha a coluna que você quer mexer: ");
            int coluna = read.nextInt();

            if (linha < 0 || linha > 7 || coluna < 0 || coluna > 7 || jogo[linha][coluna] == 0) {
                System.out.println("\nDigite uma linha e coluna válida!");
            } else {

              
                if (jogo[linha][coluna] == 3) {
                    jogadaDamaBranca(jogo, linha, coluna);
                    jogadaValida = true;
                    continue;
                }

                System.out.println("\nVocê quer ir pra esquerda ou direita?");
                String opcao = read.next();

                switch (opcao.toLowerCase()) {

                    case "esquerda":
                        if (coluna == 0 || jogo[linha - 1][coluna - 1] == 1) {
                            System.out.println("\nMovimento inválido, tente novamente.");
                        } else {

                        
                            if ((jogo[linha - 1][coluna - 1] == 2 || jogo[linha - 1][coluna - 1] == 4)
                                    && jogo[linha - 2][coluna - 2] == 0) {

                                jogo[linha - 2][coluna - 2] = 1;
                                jogo[linha][coluna] = 0;
                                jogo[linha - 1][coluna - 1] = 0;
                                jogadaValida = true;

                            } else if ((jogo[linha - 1][coluna - 1] == 2 || jogo[linha - 1][coluna - 1] == 4)
                                    && (jogo[linha - 2][coluna - 2] == 2 || jogo[linha - 2][coluna - 2] == 4)) {

                                System.out.println("Jogada inválida, tente novamente!");
                                continue;

                            } else {
                                jogo[linha - 1][coluna - 1] = 1;
                                jogo[linha][coluna] = 0;
                                jogadaValida = true;
                            }
                        }
                        break;

                    case "direita":
                        if (coluna == 7 || jogo[linha - 1][coluna + 1] == 1) {
                            System.out.println("\nMovimento inválido, tente novamente.");
                        } else {

                           
                            if ((jogo[linha - 1][coluna + 1] == 2 || jogo[linha - 1][coluna + 1] == 4)
                                    && jogo[linha - 2][coluna + 2] == 0) {

                                jogo[linha - 2][coluna + 2] = 1;
                                jogo[linha][coluna] = 0;
                                jogo[linha - 1][coluna + 1] = 0;
                                jogadaValida = true;

                            } else if ((jogo[linha - 1][coluna + 1] == 2 || jogo[linha - 1][coluna + 1] == 4)
                                    && (jogo[linha - 2][coluna + 2] == 2 || jogo[linha - 2][coluna + 2] == 4)) {

                                System.out.println("Jogada inválida, tente novamente!");
                                continue;

                            } else {
                                jogo[linha - 1][coluna + 1] = 1;
                                jogo[linha][coluna] = 0;
                                jogadaValida = true;
                            }
                        }
                        break;
                }
            }
        } while (!jogadaValida);
    }

  
    public static void preencherMatriz(int[][] jogo) {
        // peças pretas
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 8; j++) {
                if ((i + j) % 2 != 0) {
                    jogo[i][j] = 2;
                }
            }
        }
        // peças brancas
        for (int i = 5; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if ((i + j) % 2 != 0) {
                    jogo[i][j] = 1;
                }
            }
        }
    }

    public static void imprimirMatriz(int[][] jogo) {
        for (int i = 0; i < jogo.length; i++) {
            for (int j = 0; j < jogo[i].length; j++) {
                System.out.print(jogo[i][j] + " ");
            }
            System.out.println();
        }
    }

  
    public static String verificarVencedor(int[][] jogo) {
        boolean temPreta = false;
        boolean temBranca = false;
        String verificar;

        for (int i = 0; i < jogo.length; i++) {
            for (int j = 0; j < jogo[i].length; j++) {
                if (jogo[i][j] == 1 || jogo[i][j] == 3) {
                    temBranca = true;
                }

                if (jogo[i][j] == 2 || jogo[i][j] == 4) {
                    temPreta = true;
                }
            }
        }

        if (temBranca && temPreta) {
            verificar = "Continua";
        } else if (temBranca) {
            verificar = "Branca";
        } else if (temPreta) {
            verificar = "Preta";
        } else {
            verificar = "Continua";
        }

        return verificar;
    }

    // faz uma peça virar dama
    public static void verificarDama(int[][] jogo) {
        for (int i = 0; i < jogo.length; i++) {
            for (int j = 0; j < jogo[i].length; j++) {

                // branca vira dama ao chegar no topo
                if (jogo[i][j] == 1 && i == 0) {
                    jogo[i][j] = 3;
                    System.out.println("Parabens sua peça virou dama");
                }

                // preta vira dama ao chegar no fundo
                if (jogo[i][j] == 2 && i == 7) {
                    jogo[i][j] = 4;
                    System.out.println("Parabens sua peça virou dama");
                }
            }
        }
    }

    //   Dama Branca
    public static void jogadaDamaBranca(int[][] jogo, int linha, int coluna) {
        Scanner read = new Scanner(System.in);
        boolean jogadaValida = false;

        while (!jogadaValida) {

            System.out.println("""
                Escolha a direção da Dama:
                1 - Cima Esquerda
                2 - Cima Direita
                3 - Baixo Esquerda
                4 - Baixo Direita
                """);

            int opcao = read.nextInt();

            System.out.println("Quantas casas deseja mover?");
            int posicoes = read.nextInt();

            int passoLinha = 0, passoColuna = 0;

          
            switch (opcao) {
                case 1 -> { passoLinha = -1; passoColuna = -1; }
                case 2 -> { passoLinha = -1; passoColuna = 1; }
                case 3 -> { passoLinha = 1;  passoColuna = -1; }
                case 4 -> { passoLinha = 1;  passoColuna = 1; }
                default -> {
                    System.out.println("Opção inválida!");
                    continue;
                }
            }

            int inimigosEncontrados = 0;
            int ultimaLinhaInimigo = -1;
            int ultimaColunaInimigo = -1;

            boolean caminhoValido = true;

           
            for (int i = 1; i <= posicoes; i++) {

                int novaLinha = linha + i * passoLinha;
                int novaColuna = coluna + i * passoColuna;

                if (novaLinha < 0 || novaLinha > 7 || novaColuna < 0 || novaColuna > 7) {
                    caminhoValido = false;
                    break;
                }

                
                if (jogo[novaLinha][novaColuna] == 0) {
                    continue;
                }

               
                if (jogo[novaLinha][novaColuna] == 2 || jogo[novaLinha][novaColuna] == 4) {

                    inimigosEncontrados++;

                   
                    if (i < posicoes) {
                        int proxLinha = linha + (i + 1) * passoLinha;
                        int proxColuna = coluna + (i + 1) * passoColuna;

                        if (jogo[proxLinha][proxColuna] != 0) {
                            caminhoValido = false;
                            break;
                        }
                    }

                    ultimaLinhaInimigo = novaLinha;
                    ultimaColunaInimigo = novaColuna;

                    
                    if (inimigosEncontrados > 1) {
                        caminhoValido = false;
                        break;
                    }

                } else {
                   
                    caminhoValido = false;
                    break;
                }
            }

            if (!caminhoValido) {
                System.out.println("\nMovimento inválido! Caminho bloqueado.");
                continue;
            }

            // calcula destino final
            int linhaDestino = linha + posicoes * passoLinha;
            int colunaDestino = coluna + posicoes * passoColuna;

            
            if (jogo[linhaDestino][colunaDestino] != 0) {
                System.out.println("\nCasa final ocupada!");
                continue;
            }

           
            if (inimigosEncontrados == 1) {
                int distInimigo = Math.abs(ultimaLinhaInimigo - linha);

                // jogador tentou andar mais do que o permitido após capturar
                if (posicoes != distInimigo + 1) {
                    System.out.println("\nApós capturar, a dama deve parar imediatamente após o inimigo!");
                    continue;
                }

                jogo[ultimaLinhaInimigo][ultimaColunaInimigo] = 0; // remove inimigo
            }

            // realiza movimento
            jogo[linhaDestino][colunaDestino] = 3;
            jogo[linha][coluna] = 0;

            jogadaValida = true;
        }
    }

    //   Dama PRETA (mesma lógica da branca, só mudam os inimigos)
    public static void jogadaDamaPreta(int[][] jogo, int linha, int coluna) {
        Scanner read = new Scanner(System.in);
        boolean jogadaValida = false;

        while (!jogadaValida) {

            System.out.println("""
                Escolha a direção da Dama:
                1 - Cima Esquerda
                2 - Cima Direita
                3 - Baixo Esquerda
                4 - Baixo Direita
                """);

            int opcao = read.nextInt();

            System.out.println("Quantas casas deseja mover?");
            int posicoes = read.nextInt();

            int passoLinha = 0, passoColuna = 0;

            // define direção do movimento
            switch (opcao) {
                case 1 -> { passoLinha = -1; passoColuna = -1; }
                case 2 -> { passoLinha = -1; passoColuna = 1; }
                case 3 -> { passoLinha = 1;  passoColuna = -1; }
                case 4 -> { passoLinha = 1;  passoColuna = 1; }
                default -> {
                    System.out.println("Opção inválida!");
                    continue;
                }
            }

            int inimigosEncontrados = 0;
            int ultimaLinhaInimigo = -1;
            int ultimaColunaInimigo = -1;

            boolean caminhoValido = true;

            for (int i = 1; i <= posicoes; i++) {

                int novaLinha = linha + i * passoLinha;
                int novaColuna = coluna + i * passoColuna;

                // fora da área do tabuleiro
                if (novaLinha < 0 || novaLinha > 7 || novaColuna < 0 || novaColuna > 7) {
                    caminhoValido = false;
                    break;
                }

                // casa vazia = ok
                if (jogo[novaLinha][novaColuna] == 0) {
                    continue;
                }

                // inimigo branco (1 ou 3)
                if (jogo[novaLinha][novaColuna] == 1 || jogo[novaLinha][novaColuna] == 3) {

                    inimigosEncontrados++;

                    // verifica se há peça imediatamente após → proibido
                    if (i < posicoes) {
                        int proxLinha = linha + (i + 1) * passoLinha;
                        int proxColuna = coluna + (i + 1) * passoColuna;

                        if (jogo[proxLinha][proxColuna] != 0) {
                            caminhoValido = false;
                            break;
                        }
                    }

                    ultimaLinhaInimigo = novaLinha;
                    ultimaColunaInimigo = novaColuna;

                    if (inimigosEncontrados > 1) {
                        caminhoValido = false;
                        break;
                    }

                } else {
                    // peça amiga no caminho → inválido
                    caminhoValido = false;
                    break;
                }
            }

            if (!caminhoValido) {
                System.out.println("\nMovimento inválido! Caminho bloqueado.");
                continue;
            }

            int linhaDestino = linha + posicoes * passoLinha;
            int colunaDestino = coluna + posicoes * passoColuna;

            // destino precisa ser vazio
            if (jogo[linhaDestino][colunaDestino] != 0) {
                System.out.println("\nCasa final ocupada!");
                continue;
            }

            // captura
            if (inimigosEncontrados == 1) {
                int distInimigo = Math.abs(ultimaLinhaInimigo - linha);

                // cheque: dama deve parar logo após o salto
                if (posicoes != distInimigo + 1) {
                    System.out.println("\nApós capturar, a dama deve parar imediatamente após o inimigo!");
                    continue;
                }

                jogo[ultimaLinhaInimigo][ultimaColunaInimigo] = 0;
            }

            // move
            jogo[linhaDestino][colunaDestino] = 4;
            jogo[linha][coluna] = 0;

            jogadaValida = true;
        }
    }
}
