package org.example;

import java.util.ArrayList;
import java.util.List;

public class Player {

    public static final List<Player> listaPlayers = new ArrayList<>();
    private String nome;
    private long dinheiro;
    private int idade;


    public void boasVindas(){
        System.out.println("\n\n----------------------------Boas Vindas ao meu jogo " + nome + " -------------------------------" +
                "\n\nNele seus Status iniciais são: R$" + dinheiro + "\nE sua idade é de: " + idade + " anos");
    }


    public Player(String nome, long dinheiro, int idade) {
        this.nome = nome;
        this.dinheiro = dinheiro;
        this.idade = idade;
    }

    public static Player FindByName(String nome){
        return listaPlayers.stream()
                .filter(player -> player.getNome().equalsIgnoreCase(nome) )
                .findFirst()
                .orElse(null);

    }

    public static List<Player> getListaPlayers(){
        return listaPlayers;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public long getDinheiro() {
        return dinheiro;
    }

    public void setDinheiro(long dinheiro) {
        this.dinheiro = dinheiro;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    @Override
    public String toString() {
        return  "Nome: " + nome + ",Dinheiro: R$ " + dinheiro + ",Idade:" + idade;
    }

}


