package br.inatel;

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

  public boolean associaCorretorACorretora(Integer id_corretor, Integer id_corretora) {
    connect();

    String sql = "INSERT INTO associa(fk_idcorretor, fk_idcorretora) VALUES (?, ?)";
    try {
      pst = connection.prepareStatement(sql);
      pst.setInt(1, id_corretor);
      pst.setInt(2, id_corretora);
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

  public Integer buscaDeIdCorretorPeloNrProposta(Integer nrProposta) {
    connect();

    String sql = "SELECT fk_idcorretor FROM proposta WHERE nrProposta=? ORDER BY nrProposta DESC LIMIT 1";
    Integer id_corretor = 0;

    try {

      // Preparando o statement
      pst = connection.prepareStatement(sql);
      pst.setInt(1, nrProposta);

      // Executando o statement
      result = pst.executeQuery();

      while (result.next()) {

        id_corretor = result.getInt("fk_idCorretor");
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
    return id_corretor;
  }

  public Corretor buscaCorretorPorID(Integer id_corretor) {
    connect();

    String sql = "SELECT * FROM corretor WHERE id_corretor=? ORDER BY id_corretor DESC LIMIT 1";
    Corretor corretorEncontrado = new Corretor();

    try {

      // Preparando o statement
      pst = connection.prepareStatement(sql);
      pst.setInt(1, id_corretor);

      // Executando o statement
      result = pst.executeQuery();

      while (result.next()) {

        corretorEncontrado.id = id_corretor;
        corretorEncontrado.nome = result.getString("nome");
        corretorEncontrado.UF = result.getString("uf");
        corretorEncontrado.CPF = result.getString("cpf");

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
    return corretorEncontrado;
  }
}
