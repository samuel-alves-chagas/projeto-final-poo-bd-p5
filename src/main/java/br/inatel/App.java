package br.inatel;

import java.util.ArrayList;

public class App {
    public static void main(String[] args) {
        Corretora alianca = new Corretora("Alianca BH", "MG");
        System.out.println("Mostrando info da corretora: ");
        alianca.mostraInfo();
        System.out.println("");

        Corretor samuel = new Corretor("Samuel", "14548698604", "MG");
        System.out.println("Mostrando info do corretor: ");
        try {
            samuel.solicitaVinculo(alianca);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        samuel.mostraInfo();
        System.out.println("");

        System.out.println("Mostrando corretores associados à: " + alianca.getNome());
        System.out.println(alianca.getNomeCorretoresVinculados());
        System.out.println("");

        Cliente benedito = new Cliente("Benedito José", "77271238620", "MG");
        Cliente delma = new Cliente("Delma Alves", "56489972444", "MG");
        Cliente maira = new Cliente("Maira Alves", "78965432119", "MG");

        ArrayList<Cliente> dependentes = new ArrayList<Cliente>();
        dependentes.add(delma);
        dependentes.add(maira);
        samuel.criaProposta(benedito, dependentes);

        System.out.println("Mostrando info do corretor: ");
        samuel.mostraInfo();
    }
}
