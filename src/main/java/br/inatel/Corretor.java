package br.inatel;

public class Corretor extends Pessoa {

  public Corretor() {
    super("", "", "");
  }

  public Corretor(String nomeCorretor, String cpfCorretor, String UFCorretor) {
    super(nomeCorretor, cpfCorretor, UFCorretor);
  }

  public Boolean solicitaVinculo(Corretora corretoraDesejada) {
    if (this.UF == corretoraDesejada.getUF()) {
      return true;
    }
    return false;
  }

  @Override
  public void mostraInfo() {
    System.out.println("Nome do corretor que digitou a proposta: " + this.nome);
    System.out.println("CPF do corretor: " + this.CPF);
    System.out.println("UF do corretor: " + this.UF);
  }

}
