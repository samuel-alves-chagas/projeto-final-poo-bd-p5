package br.inatel;

public class Proposta {
  private int nrProposta;
  private int idade;
  private double valor;
  private Corretor corretor;
  private Cliente titular;

  public Proposta() {
  }

  public Proposta(Corretor corretor, Cliente titular, Integer idade, double valor) {
    this.corretor = corretor;
    this.titular = titular;
    this.idade = idade;

  }

  public void calculaValor() {
    if (idade > 50)
      this.valor = 239.74 + (valor * idade) / 250;
    else
      this.valor = 239.74 - (valor * idade) / 100;
  }

  public void mostraInfo() {
    System.out.println("Número: " + this.getNrProposta());
    System.out.println("Valor da proposta: " + this.getValor());
    System.out.println("Idade do titular: " + this.getIdade());
  }

  public int getNrProposta() {
    return nrProposta;
  }

  public void setNrProposta(int nrProposta) {
    this.nrProposta = nrProposta;
  }

  public Corretor getCorretor() {
    return corretor;
  }

  public void setCorretor(Corretor corretor) {
    this.corretor = corretor;
  }

  public Cliente getTitular() {
    return titular;
  }

  public void setTitular(Cliente titular) {
    this.titular = titular;
  }

  public int getIdade() {
    return idade;
  }

  public void setIdade(int idade) {
    this.idade = idade;
  }

  public double getValor() {
    return valor;
  }

  public void setValor(double valor) {
    this.valor = valor;
  }
}
