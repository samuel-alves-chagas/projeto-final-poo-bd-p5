package br.inatel;

import java.util.ArrayList;

public class Proposta {
  public static int contador;
  private int nrProposta;
  private Corretor corretor;
  private Cliente titular;
  private ArrayList<Cliente> dependentes;

  public Proposta(Corretor corretor, Cliente titular, ArrayList<Cliente> dependentes) {
    contador++;
    this.nrProposta = contador;
    this.corretor = corretor;
    this.titular = titular;
    this.dependentes = new ArrayList<Cliente>();
    for (Cliente dependenteAtual : dependentes) {
      this.dependentes.add(dependenteAtual);
    }
  }

  public ArrayList<Cliente> getDependentes() {
    return dependentes;
  }

  public ArrayList<String> getNomeDependentes() {
    ArrayList<String> nomes = new ArrayList<String>();
    for (Cliente dependenteAtual : this.getDependentes()) {
      nomes.add(dependenteAtual.nome);
    }

    return nomes;
  }

  public void mostraInfo() {
    System.out.println("Proposta número " + this.getNrProposta());
    System.out.println("Titular: " + this.getTitular());
    System.out.println("Dependentes: " + this.getNomeDependentes());
  }

  public int getNrProposta() {
    return nrProposta;
  }

  public Corretor getCorretor() {
    return corretor;
  }

  public Cliente getTitular() {
    return titular;
  }

}
