package br.inatel;

public abstract class Pessoa {
  public static int contador;
  protected int id;
  protected String nome;
  protected String CPF;
  protected String UF;

  public Pessoa(String nome, String CPF, String UF) {
    contador++;
    this.id = contador;
    this.nome = nome;
    this.CPF = CPF;
    this.UF = UF;
  }

  public void mostraInfo() {
    System.out.println("O ID do(a) " + this.nome + " é: " + this.id);
    System.out.println("O CPF do(a) " + this.nome + " é: " + this.CPF);
    System.out.println("A UF do(a) " + this.nome + " é: " + this.UF);
  }
}
