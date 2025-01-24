package org.example;

import java.io.*;
import java.util.*;

public class Main {
    public static Scanner scanner = new Scanner(System.in);
    static File file = new File("src\\main\\java\\files\\BancoDeDados");
    static Player playerLogged = null;



    static int opcaoUser = 0;

    static Random random = new Random();


    public static void main(String[] args) {
        lerBancoDeDados();
        adicionarNomeDoPlayer();
        menuPrincipal();
        scanner.close();

    }

    public static void adicionarNomeDoPlayer() {
        System.out.println("Deseja criar um novo Player?\n");
        simOuNao();
        if (opcaoUser == 1){
            System.out.print("Digite seu nome: ");
            String nome = scanner.nextLine();

            Player player = new Player(
                    nome,
                    0,
                    0
            );

            player.boasVindas();
            Player.getListaPlayers().add(player);
            salvarPlayer();
            playerLogged = player;

        }else{
            for (Player player : Player.listaPlayers){
                System.out.println(player);
            }
            System.out.print("\nDigite o nome do player que deseja continuar a jogar: ");
            String nome = scanner.nextLine();
            Player player = Player.FindByName(nome);
            playerLogged = player;
            System.out.println(playerLogged);

        }
    }

    public static void lerBancoDeDados() {
        if (!(file.exists())) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException("erro ao criar o arquivo", e);
            }
            return;
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(file))){
            String linha;

            while ((linha = reader.readLine()) != null) {
                String[] parte = linha.split(",");

            if (parte.length != 3) {
                System.out.println("Linha inválida no arquivo: " + linha);
                continue; // Ignora a linha inválida
            }

            try {
                String nome = parte[0];
                long dinheiroNovo = Long.parseLong(parte[1]);
                int idadeNova = Integer.parseInt(parte[2]);

                Player playerNovo = new Player(
                        nome,
                        dinheiroNovo,
                        idadeNova
                );
                Player.listaPlayers.add(playerNovo);
            }catch (NumberFormatException e) {
                System.out.println("Erro ao converter dados na linha: " + linha);
            }

            }


        } catch (FileNotFoundException e) {
            throw new RuntimeException("nao achou o banco de dados",e);
    } catch (IOException e) {
            throw new RuntimeException("Erro ao ler o banco de dados",e);
        }
    }

    public static void salvarPlayer(){
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, false))){
            for (Player player : Player.listaPlayers){
                String linha = player.getNome() + "," + player.getDinheiro() + "," + player.getIdade();
                writer.write(linha + "\n");
            }
        } catch (IOException e) {
            throw new RuntimeException("Erro ao salvar",e);
        }

    }

    public static void seletorDeTrabalhos() {
        String menuTrabalhos =
                """
                        _____________________________________________________
                        |        BEM_VINDO AO SELETOR DE TRABALHOS          |
                        |                                                   |
                        | Digite 1 Para Trabalhar como pegador de latinhas. |
                        |                                                   |
                        | Digite 2 Para Trabalhar como  pescador.           |
                        |                                                   |
                        | Digite 3 Para Trabalhar como  manobrista de carro.|
                        |                                                   |
                        | Digite 4 Para Sair do seletor de trabalhos.       |
                        |___________________________________________________|
                        """;

        do {
            System.out.println(menuTrabalhos);
            opcaoUser = scanner.nextInt();
            scanner.nextLine();

            switch (opcaoUser) {
                case 1:
                    System.out.println("O pegador de latinhas ganha R$ 40,0 trabalhando por 3 anos de vida trabalhados." +
                            "\nVocê deseja aceitar esse trabalho?");
                    simOuNao();
                    if (opcaoUser == 1) {
                        playerLogged.setIdade(playerLogged.getIdade() + 3);
                        playerLogged.setDinheiro(playerLogged.getDinheiro() + 40);
                        System.out.println("Voce Trabalhou por 3 anos... " +
                                "\nSua idade atual é de: " + (playerLogged.getIdade()) + " anos" +
                                "\nSeu saldo total é de: R$ " + (playerLogged.getDinheiro()));

                        menuPrincipal();
                    } else {
                        seletorDeTrabalhos();
                    }
                case 2:
                    System.out.println("O pescador ganha uma renda variavel, isso vai depender da situação do mar" +
                            " pescando por 5 anos ele pode ganhar de 20 ou 100 reais." +
                            "\nVocê deseja aceitar esse trabalho?");
                    simOuNao();
                    if (opcaoUser == 1) {
                        playerLogged.setIdade(playerLogged.getIdade() + 5);
                        System.out.println("Voce Pescou por 5 anos... " +
                                "\nSua idade atual é de: " + (playerLogged.getIdade()) + " anos");
                        if (random.nextInt(1 + 1) == 1) {
                            playerLogged.setDinheiro(playerLogged.getDinheiro() + 20);
                            System.out.println("Infelismente o mar não estava pra peixe\nSeu salário foi de apenas R$20" +
                                    "\nSeu saldo total é de: R$" + playerLogged.getDinheiro());
                            menuPrincipal();
                        } else {
                            playerLogged.setDinheiro(playerLogged.getDinheiro() + 100);
                            System.out.println("O mar estava lotado de peixes!\nSeu salário foi de R$100" +
                                    "\nSeu saldo total é de: R$" + playerLogged.getDinheiro());
                            menuPrincipal();
                        }
                    } else {
                        seletorDeTrabalhos();
                    }
                    break;
                case 3:
                    System.out.println("O manobrista de carro ganha R$ 80,0 trabalhando por 7 anos de vida." +
                            "\nVocê deseja aceitar esse trabalho?");
                    simOuNao();
                    if (opcaoUser == 1) {
                        String motorista = "";
                        System.out.println("Qual é seu nome?");
                        motorista = scanner.nextLine();
                        playerLogged.setIdade(playerLogged.getIdade() + 7);

                        if (motorista.equalsIgnoreCase("bia")) {
                            playerLogged.setDinheiro(10);

                            System.out.println("Voce Trabalhou por 7 anos... " +
                                    "\nSua idade atual é de: " + (playerLogged.getIdade() ) + " anos" +
                                    "\nVoçê acabou batendo todos os carro e ficou pobre..." +
                                    "\nSeu saldo total é de: R$" + (playerLogged.getDinheiro() ));
                            menuPrincipal();
                        } else {
                            playerLogged.setDinheiro(playerLogged.getDinheiro() + 80);
                            System.out.println("Voce Trabalhou por 7 anos...\nNessa jornada voçê não bateu nenhum carro!!! " +
                                    "\nSua idade atual é de: " + (playerLogged.getIdade()) + " anos" +
                                    "\nSeu saldo total é de: R$ " + (playerLogged.getDinheiro() ));

                            menuPrincipal();
                        }

                    } else {
                        seletorDeTrabalhos();
                    }
                    break;


                case 4:
                    System.out.println("Saiu da loja\nIndo para o menu principal");
                    menuPrincipal();
                    break;

                default:
                    System.out.println("Opção invalida");
                    break;
            }

        } while (opcaoUser < 0 || opcaoUser > 5);
    }


    public static void loja() {

        String menuLoja =
                """
                        _______________________________________________
                        | BEM_VINDO A LOJA DE ROUPAS Gabriel - SHORTS |
                        |                                             |
                        | Digite 1 Para comprar uma blusa por R$ 50,0.|
                        |                                             |
                        | Digite 2 Para comprar uma calça por R$ 35,0.|
                        |                                             |
                        | Digite 3 Para comprar um shorts por R$ 20,0.|
                        |                                             |
                        | Digite 4 Para Sair da loja.                 |
                        |_____________________________________________|
                        """;

        do {
            System.out.println(menuLoja);
            opcaoUser = scanner.nextInt();
            scanner.nextLine();
            switch (opcaoUser) {
                case 1:
                    if (playerLogged.getDinheiro() >= 50) {
                        playerLogged.setDinheiro(playerLogged.getDinheiro() - 50);
                        System.out.println("Voce gastou R$ 50 para comprar uma blusa\nAgora seu saldo total é: " + playerLogged.getDinheiro() + "\nAproveite sua blusa e volte sempre! (:");
                        loja();
                    } else {
                        System.out.println("SEU POBRE IMUNDO, você nao tem dinheiro sufuciente, vai trabalhar!");
                        loja();
                    }
                    break;
                case 2:
                    if (playerLogged.getDinheiro() >= 35) {
                        playerLogged.setDinheiro(playerLogged.getDinheiro() - 35);
                        System.out.println("Voce gastou R$ 35 para comprar uma blusa\nAgora seu saldo total é: " + playerLogged.getDinheiro() + "\nAproveite sua blusa e volte sempre! (:");
                        loja();
                    } else {
                        System.out.println("SEU POBRE IMUNDO, você nao tem dinheiro sufuciente, vai trabalhar!");
                        loja();
                    }
                    break;
                case 3:
                    if (playerLogged.getDinheiro() >= 20) {
                        playerLogged.setDinheiro(playerLogged.getDinheiro() - 20);
                        System.out.println("Voce gastou R$ 20 para comprar uma blusa\nAgora seu saldo total é: " + playerLogged.getDinheiro() + "\nAproveite sua blusa e volte sempre! (:");
                        loja();
                    } else {
                        System.out.println("SEU POBRE IMUNDO, você nao tem dinheiro sufuciente, vai trabalhar!");
                        loja();
                    }
                    break;
                case 4:
                    System.out.println("Saiu da loja\nIndo para o menu principal");
                    menuPrincipal();
                    break;
                default:
                    System.out.println("Opção invalida, tente novamente");

            }

        } while (opcaoUser < 0 || opcaoUser > 5);
    }

    public static void lojaDeRaspadinhas() {

        String menuRaspadinha =
                """
                        _____________________________________________________
                        |        BEM_VINDO A LOJA DE RASPADINHAS            |
                        |           Rapadinha Babão da sorte                |
                        |                                                   |
                        | Digite 1 Para saber como funciona a loja.         |
                        |                                                   |
                        | Digite 2 Para Comprar 1 numero da sorte.          |
                        |                                                   |
                        | Digite 3 Para Comprar 3 numero da sorte.          |
                        |                                                   |
                        | Digite 4 Para Sair da loja de rapadinhas.         |
                        |___________________________________________________|
                        """;
        int numUser = 0;
        long valorInvestimentoUser = 0, total = 0;
        System.out.println(menuRaspadinha);
        opcaoUser = scanner.nextInt();
        scanner.nextLine();

        switch (opcaoUser) {
            case 1:
                System.out.println("Está loja funciona como um casino, nela voçê pode escolher 1 ou 3 numeros dentre 10 de 1 a 30." +
                        "\nSe conseguir acertar 1 numero dentre os 10 numeros de 1 a 30, seu dinheiro investido vai ser 6 vezes o valor investido." +
                        "\nSe conseguir acertar 3 numeros dentre os 10 numeros de 1 a 30, seu dinheiro investido vai ser 2 vezes o valor investido.");
                lojaDeRaspadinhas();
                break;
            case 2:

                System.out.println("Digite o seu numero de 1 a 30");
                numUser = scanner.nextInt();
                scanner.nextLine();
                while (numUser < 0 || numUser > 31) {
                    System.out.println("Digite o numero de 1 a 30, corretamente");
                    numUser = scanner.nextInt();
                    scanner.nextLine();
                }

                System.out.println("Digite o valor que voçê quer depositar no número: " + numUser +
                        "\nVoçê tem: R$" + playerLogged.getDinheiro());

                valorInvestimentoUser = scanner.nextLong();
                if (valorInvestimentoUser < 0) {
                    System.out.println("Voçê não colocou o valor correto.\nVoltando para o menu...");
                    lojaDeRaspadinhas();

                } else {
                    if (playerLogged.getDinheiro() - valorInvestimentoUser < 0) {
                        System.out.println("Voçê não tem dinheiro sufuciente\nvoltando para o menu...");
                    } else {
                        playerLogged.setDinheiro(playerLogged.getDinheiro() -  valorInvestimentoUser);

                        System.out.println("O numero escolhido pelo jogador foi: " + numUser + "\n\nVamos iniciar o sorteio...");
                        Set<Integer> hashSet = new HashSet<>();
                        for (int vezes = 0; vezes < 25; vezes++) {
                            hashSet.add(random.nextInt(30 + 1));
                        }
                        for (int i = 0; i < 20; i++) {
                            hashSet.remove(random.nextInt(hashSet.size()));
                            if (hashSet.size() == 9) {
                                break;
                            }
                        }

                        ArrayList valorListaTotal = new ArrayList<>(hashSet);
                        for (int i = 0; i < 10; i++) {
                            try {
                                Thread.sleep(500);
                            } catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            }
                            System.out.println(valorListaTotal.get(i));
                        }

                        if (valorListaTotal.contains(numUser)) {
                            playerLogged.setDinheiro(playerLogged.getDinheiro() + total);
                            System.out.println("Voçê conseguiu acertar o numero, Parabenss!" +
                                    "\nO premio que voçê vai ganhar é de: R$" + (total = valorInvestimentoUser * 2) +
                                    "\nAgora seu dinheiro é de: R$" + (playerLogged.getDinheiro()));
                            System.out.println("\nQuer jogar novamente?" +
                                    "\nSeu dinheiro é de: R$" + playerLogged.getDinheiro());

                            if (opcaoUser == 1) {
                                lojaDeRaspadinhas();
                            } else {
                                menuPrincipal();
                            }
                        } else {
                            System.out.println("É uma pena, mas parece que voçê perdeu...");

                            System.out.println("Quer jogar novamente?");

                            if (opcaoUser == 1) {
                                lojaDeRaspadinhas();
                            } else {
                                menuPrincipal();
                            }
                        }
                    }
                }

            case 3:
                System.out.println("EM DESENVOLVIMENTO...");
                menuPrincipal();
                break;
            case 4:
                System.out.println("Saiu da loja\nIndo para o menu principal");
                break;
            default:
                System.out.println("Opção invalida!");
        }

    }

    public static void simOuNao() {

        System.out.println(
                "Digite 1 para SIM" +
                        "\nDigite 2 para NÃO");

        opcaoUser = scanner.nextInt();
        scanner.nextLine();

        while (opcaoUser < 1 || opcaoUser > 2) {
            System.out.println("Opção invalida " +
                    "Digite 1 para SIM " +
                    "ou 2 para NÃO.");
            opcaoUser = scanner.nextInt();
            scanner.nextLine();
        }

    }

    public static void menuPrincipal() {
        String menusPrincipais =
                """
                        _____________________________________________________
                        |            BEM_VINDO AO JOGO DO BIEL              |
                        |                                                   |
                        | Digite 1 Para ir Trabalhar.                       |
                        |                                                   |
                        | Digite 2 Para ir comprar uma Roupa.               |
                        |                                                   |
                        | Digite 3 Para ir comprar Rapadinhas.              |
                        |                                                   |
                        | Digite 4 Para Salvar o seu player                 |
                        |                                                   |
                        |___________________________________________________|
                        """;

        do {
            System.out.println(menusPrincipais);
            opcaoUser = scanner.nextInt();
            scanner.nextLine();
            switch (opcaoUser) {
                case 1:
                    seletorDeTrabalhos();
                    break;
                case 2:
                    loja();
                    break;
                case 3:
                    lojaDeRaspadinhas();
                    break;
                case 4:
                    salvarPlayer();
                    System.out.println("Player salvo com sucesso\n");

                    for (Player player : Player.listaPlayers){
                        System.out.println(player);
                    }

                    break;

                default:
                    System.out.println("opção invalida");
                    menuPrincipal();
            }
        } while (opcaoUser < 0 || opcaoUser > 5);

    }
}
