package br.inatel;

import java.util.ArrayList;
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
}
