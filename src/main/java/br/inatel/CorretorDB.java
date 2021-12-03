package br.inatel;

import java.util.ArrayList;
import java.sql.SQLException;

public class CorretorDB extends Database {

  public boolean adicionarCorretorAoBanco(Corretor novoCorretor) {
    connect();

    String sql = "INSERT INTO corretor(nome, uf, cpf) VALUES (?, ?, ?)";
    try {
      pst = connection.prepareStatement(sql);
      pst.setString(1, novoCorretor.nome);
      pst.setString(2, novoCorretor.UF);
      pst.setString(3, novoCorretor.CPF);
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

  public Integer buscaIDDeCorretor(String cpfCorretor) {
    connect();

    String sql = "SELECT id_corretor FROM corretor WHERE cpf=?";
    Integer idDoCorretor = 0;

    try {

      // Preparando o statement
      pst = connection.prepareStatement(sql);
      pst.setString(1, cpfCorretor);

      // Executando o statement
      result = pst.executeQuery();

      while (result.next()) {

        idDoCorretor = result.getInt("id_corretor");
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
    return idDoCorretor;
  }
}
