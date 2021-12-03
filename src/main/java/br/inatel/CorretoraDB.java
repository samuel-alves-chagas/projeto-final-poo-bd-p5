package br.inatel;

import java.util.ArrayList;
import java.sql.SQLException;

public class CorretoraDB extends Database {

  public boolean adicionarCorretoraAoBanco(Corretora novaCorretora) {
    connect();

    String sql = "INSERT INTO corretora(nome, uf) VALUES (?, ?)";
    try {
      pst = connection.prepareStatement(sql);
      pst.setString(1, novaCorretora.getNome());
      pst.setString(2, novaCorretora.getUF());
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

  public Integer buscaIDDeCorretora(String nomeCorretora) {
    connect();

    String sql = "SELECT id_corretora FROM corretora WHERE nome=? ORDER BY id_corretora DESC LIMIT 1";
    Integer idDaCorretora = 0;

    try {

      // Preparando o statement
      pst = connection.prepareStatement(sql);
      pst.setString(1, nomeCorretora);

      // Executando o statement
      result = pst.executeQuery();

      while (result.next()) {

        idDaCorretora = result.getInt("id_corretora");
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
    return idDaCorretora;
  }
}
