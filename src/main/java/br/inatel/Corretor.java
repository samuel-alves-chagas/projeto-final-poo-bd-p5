package br.inatel;

import java.util.ArrayList;

public class Corretor extends Pessoa {
  private ArrayList<Corretora> corretorasVinculadas;

  public Corretor(String nomeCorretor, String cpfCorretor, String UFCorretor) {
    this.nome = nomeCorretor;
    this.CPF = cpfCorretor;
    this.UF = UFCorretor;
    this.corretorasVinculadas = new ArrayList<Corretora>();
  }

  public ArrayList<Corretora> getCorretorasVinculadas() {
    return corretorasVinculadas;
  }

  public ArrayList<String> getNomeCorretorasVinculadas() {
    ArrayList<String> nomes = new ArrayList<String>();
    for (Corretora corretoraVinculada : this.getCorretorasVinculadas()) {
      nomes.add(corretoraVinculada.getNome());
    }

    return nomes;
  }

  public Exception solicitaVinculo(Corretora corretoraDesejada) {
    if (this.UF == corretoraDesejada.getUF()) {
      corretoraDesejada.vincularCorretor(this);
      this.corretorasVinculadas.add(corretoraDesejada);
      return null;
    } else {
      return new Exception("O corretor " + this.nome + " não possui abrangência para a corretora solicitada");
    }
  }

  public void mostraInfo() {
    super.mostraInfo();
    System.out.println("Corretoras vinculadas do(a) " + this.nome + " : " + getNomeCorretorasVinculadas());
  }

}
