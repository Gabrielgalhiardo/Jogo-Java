package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        double dinheiro = 500;
        String menu=
                """
                _______________________________________________
                | BEM_VINDO A LOJA DE ROUPAS Gabriel - SHORTS |
                | Digite 1 para comprar uma blusa por R$ 50,0 |
                |                                             |
                | Digite 2 para comprar uma calça por R$ 35,0 |
                |                                             |
                | Digite 3 para comprar um shorts por R$ 20,0 |
                |_____________________________________________|
                """;
        System.out.println(menu);
        int opcaoUser = scanner.nextInt();
        scanner.nextLine();

        switch (opcaoUser){
            case 1:
                if (dinheiro >= 50){
                System.out.println("Voce gastou R$ 50 para comprar uma blusa\nAgora seu saldo total é: " + dinheiro + "\nAproveite sua blusa e volte sempre! (:");
            }else {
                    System.out.println("SEU POBRE IMUNDO, você nao tem dinheiro sufuciente, vai trabalhar!");
                }
                break;
            case 2:
                if (dinheiro >= 50){
                    System.out.println("Voce gastou R$ 35 para comprar uma blusa\nAgora seu saldo total é: " + dinheiro + "\nAproveite sua blusa e volte sempre! (:");
                }else {
                    System.out.println("SEU POBRE IMUNDO, você nao tem dinheiro sufuciente, vai trabalhar!");
                }
                break;
            case 3:
                if (dinheiro >= 50){
                    System.out.println("Voce gastou R$ 20 para comprar uma blusa\nAgora seu saldo total é: " + dinheiro + "\nAproveite sua blusa e volte sempre! (:");
                }else {
                    System.out.println("SEU POBRE IMUNDO, você nao tem dinheiro sufuciente, vai trabalhar!");
                }
                break;
            default:
                System.out.println("Opção invalida, tente novamente");

        }


    }
}