package br.inatel;

import java.util.ArrayList;

public class Corretor extends Pessoa {
  private ArrayList<Corretora> corretorasVinculadas;
  private ArrayList<Proposta> propostasCriadas;

  public Corretor(String nomeCorretor, String cpfCorretor, String UFCorretor) {
    super(nomeCorretor, cpfCorretor, UFCorretor);
    this.corretorasVinculadas = new ArrayList<Corretora>();
    this.propostasCriadas = new ArrayList<Proposta>();
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

  public ArrayList<Proposta> getPropostasCriadas() {
    return propostasCriadas;
  }

  public ArrayList<Integer> getNumeroPropostas() {
    ArrayList<Integer> numeros = new ArrayList<Integer>();
    for (Proposta propostaAtual : this.getPropostasCriadas()) {
      numeros.add(propostaAtual.getNrProposta());
    }

    return numeros;
  }

  // TO DO: Fazer validação via banco
  public Exception verificaTitular(Pessoa titularDaProposta) {
    return null;
  }

  public Exception criaProposta(Cliente titular, ArrayList<Cliente> dependentes) {
    try {
      Exception err = this.verificaTitular(titular);
      Proposta novaProposta = new Proposta(this, titular, dependentes);
      this.propostasCriadas.add(novaProposta);
      return err;
    } catch (Exception e) {
      return e;
    }
  }

  // TODO: mostrar informações das propostas
  public void mostraInfoProposta(Integer nrProposta) {
  }

  public void mostraInfo() {
    super.mostraInfo();
    System.out.println("Corretoras vinculadas do(a) " + this.nome + " : " + getNomeCorretorasVinculadas());
    System.out.println("Propostas criadas pelo(a) " + this.nome + " : " + getNumeroPropostas());
  }

}
