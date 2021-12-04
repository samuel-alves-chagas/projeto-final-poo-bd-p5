package br.inatel;

public class Cliente extends Pessoa {

  public Cliente() {
    super("", "", "");
  }

  public Cliente(String nomeCliente, String cpfCliente, String UFCliente) {
    super(nomeCliente, cpfCliente, UFCliente);
  }

  @Override
  public void mostraInfo() {
    System.out.println("Nome do titular: " + this.nome);
    System.out.println("CPF do titular: " + this.CPF);
    System.out.println("UF do titular: " + this.UF);
  }
}
