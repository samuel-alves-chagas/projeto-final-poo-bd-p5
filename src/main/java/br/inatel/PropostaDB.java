package br.inatel;

import java.sql.SQLException;

public class PropostaDB extends Database {
  public boolean adicionarPropostaAoBanco(Proposta novaProposta) {
    connect();

    String sql = "INSERT INTO proposta(idade, valor, cpf_titular, fk_idcorretor) VALUES (?, ?, ?, ?)";
    try {
      pst = connection.prepareStatement(sql);
      pst.setInt(1, novaProposta.getIdade());
      pst.setDouble(2, novaProposta.getValor());
      pst.setString(3, novaProposta.getTitular().CPF);
      pst.setInt(4, novaProposta.getCorretor().id);
      pst.execute();

      check = true;
    } catch (SQLException e) {
      System.out.println("Erro de operacao: " + e.getMessage());
      check = false;
    } finally {
      try {
        connection.close();
        pst.close();
      } catch (SQLException e) {
        System.out.println("Erro ao finalizar conexão: " + e.getMessage());
      }
    }
    return check;
  }

  public Integer buscaNrProposta(String cpfTitular) {
    connect();

    String sql = "SELECT nrProposta FROM proposta WHERE cpf_titular=? ORDER BY nrProposta DESC LIMIT 1";
    Integer nrProposta = 0;

    try {

      // Preparando o statement
      pst = connection.prepareStatement(sql);
      pst.setString(1, cpfTitular);

      // Executando o statement
      result = pst.executeQuery();

      while (result.next()) {

        nrProposta = result.getInt("nrProposta");
        check = true;
      }
    } catch (SQLException e) {
      System.out.println("Erro de operação: " + e.getMessage());
    } finally {
      try {
        connection.close();
        pst.close();
        result.close();
      } catch (SQLException e) {
        System.out.println("Erro ao fechar a conexão: " + e.getMessage());
      }
    }
    return nrProposta;
  }

  public boolean atualizarPropostaNoBanco(Proposta propostaAtualizada) {
    connect();

    String sql = "UPDATE proposta SET idade = ?, valor = ? WHERE nrProposta = ?";
    try {
      pst = connection.prepareStatement(sql);
      pst.setInt(1, propostaAtualizada.getIdade());
      pst.setDouble(2, propostaAtualizada.getValor());
      pst.setInt(3, propostaAtualizada.getNrProposta());
      pst.execute();

      check = true;
    } catch (SQLException e) {
      System.out.println("Erro de operacao: " + e.getMessage());
      check = false;
    } finally {
      try {
        connection.close();
        pst.close();
      } catch (SQLException e) {
        System.out.println("Erro ao finalizar conexão: " + e.getMessage());
      }
    }
    return check;
  }

  public boolean deletarProposta(Integer nrPropostaAlvo) {
    connect();

    String sql = "DELETE FROM proposta WHERE nrProposta = ?";
    try {
      pst = connection.prepareStatement(sql);
      pst.setInt(1, nrPropostaAlvo);
      pst.execute();

      check = true;
    } catch (SQLException e) {
      System.out.println("Erro de operacao: " + e.getMessage());
      check = false;
    } finally {
      try {
        connection.close();
        pst.close();
      } catch (SQLException e) {
        System.out.println("Erro ao finalizar conexão: " + e.getMessage());
      }
    }
    return check;
  }

  public Proposta buscaDePropostaPeloNrProposta(Integer nrProposta) {
    connect();

    String sql = "SELECT * FROM proposta WHERE nrProposta=? ORDER BY nrProposta DESC LIMIT 1";
    Proposta propostaEncontrada = new Proposta();

    try {

      // Preparando o statement
      pst = connection.prepareStatement(sql);
      pst.setInt(1, nrProposta);

      // Executando o statement
      result = pst.executeQuery();

      while (result.next()) {

        propostaEncontrada.setNrProposta(result.getInt("nrProposta"));
        propostaEncontrada.setIdade(result.getInt("idade"));
        propostaEncontrada.setValor(result.getDouble("valor"));
        check = true;
      }
    } catch (SQLException e) {
      System.out.println("Erro de operação: " + e.getMessage());
    } finally {
      try {
        connection.close();
        pst.close();
        result.close();
      } catch (SQLException e) {
        System.out.println("Erro ao fechar a conexão: " + e.getMessage());
      }
    }
    return propostaEncontrada;
  }

}
