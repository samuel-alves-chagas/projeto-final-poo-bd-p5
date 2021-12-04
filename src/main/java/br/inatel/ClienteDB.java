package br.inatel;

import java.sql.SQLException;

public class ClienteDB extends Database {

  public boolean adicionarCliente(Cliente novoCliente) {
    connect();

    String sql = "INSERT INTO cliente(nome, uf, cpf) VALUES (?, ?, ?)";
    try {
      pst = connection.prepareStatement(sql);
      pst.setString(1, novoCliente.nome);
      pst.setString(2, novoCliente.UF);
      pst.setString(3, novoCliente.CPF);
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

  public Integer buscaIDDeCliente(String cpfCliente) {
    connect();

    String sql = "SELECT id_cliente FROM cliente WHERE cpf=?";
    Integer idDoCliente = 0;

    try {

      // Preparando o statement
      pst = connection.prepareStatement(sql);
      pst.setString(1, cpfCliente);

      // Executando o statement
      result = pst.executeQuery();

      while (result.next()) {

        idDoCliente = result.getInt("id_cliente");
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
    return idDoCliente;
  }

  public String buscaDeCPFDoClientePeloNrProposta(Integer nrProposta) {
    connect();

    String sql = "SELECT cpf_titular FROM proposta WHERE nrProposta=? ORDER BY nrProposta DESC LIMIT 1";
    String cpfDoCliente = "";

    try {

      // Preparando o statement
      pst = connection.prepareStatement(sql);
      pst.setInt(1, nrProposta);

      // Executando o statement
      result = pst.executeQuery();

      while (result.next()) {

        cpfDoCliente = result.getString("cpf_titular");
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
    return cpfDoCliente;
  }

  public Cliente buscaDeClientePorCPF(String cpfCliente) {
    connect();

    String sql = "SELECT * FROM cliente WHERE cpf=?";
    Cliente clienteEncontrato = new Cliente();

    try {

      // Preparando o statement
      pst = connection.prepareStatement(sql);
      pst.setString(1, cpfCliente);

      // Executando o statement
      result = pst.executeQuery();

      while (result.next()) {

        clienteEncontrato.id = result.getInt("id_cliente");
        clienteEncontrato.nome = result.getString("nome");
        clienteEncontrato.UF = result.getString("uf");
        clienteEncontrato.CPF = result.getString("cpf");

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
    return clienteEncontrato;
  }

}
