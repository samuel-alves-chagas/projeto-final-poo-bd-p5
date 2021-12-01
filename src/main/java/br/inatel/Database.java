package br.inatel;

import io.github.cdimascio.dotenv.Dotenv;
import io.github.cdimascio.dotenv.DotenvEntry;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Database {

  Connection connection; // Objeto responsável por fazer a conexão com o banco
  Statement statement; // Objeto responsável por preparar as consultas
  ResultSet result; // Objeto responsável por executar as consultas
  PreparedStatement pst; // Objeto responsável por preparar, manipular e executar comandos (INSERT,
  // UPDATE, DELETE)

  Dotenv dotenv = Dotenv.load();

  final String user = dotenv.get("DATABASE_USER");;
  final String password = dotenv.get("DATABASE_PASSWORD");
  final String database = dotenv.get("DATABASE_NAME");

  final String url = "jdbc:mysql://localhost:3306/" + database + "?useTimezone=true&serverTimezone=UTC&useSSL=false";

  private boolean check = false; // Atributo interno para retorno de métodos

  public void connect() {
    try {
      connection = DriverManager.getConnection(url, user, password);
      System.out.println("Conexão feita com sucesso: " + connection);
    } catch (SQLException e) {
      System.out.println("Erro de conexão: " + e.getMessage());
    }
  }

  public boolean insertUser(User user) {
    connect();

    String sql = "INSERT INTO usuario(nome, cpf) VALUES (?, ?)";
    try {
      pst = connection.prepareStatement(sql);
      pst.setString(1, user.getNome());
      pst.setString(2, user.getCpf());
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

  public ArrayList<User> ListUser() {
    connect();
    ArrayList<User> users = new ArrayList<>();
    String sql = "SELECT * FROM usuario";

    try {
      statement = connection.createStatement();
      result = statement.executeQuery(sql);

      while (result.next()) {
        User userTemp = new User(result.getString("nome"), result.getString("cpf"));
        userTemp.id = result.getInt("id");
        System.out.println("ID = " + userTemp.id);
        System.out.println("NOME = " + userTemp.getNome());
        System.out.println("CPF = " + userTemp.getCpf());
        System.out.println("");
        users.add(userTemp);
      }
    } catch (SQLException e) {
      System.out.println("Erro ao finalizar conexão " + e.getMessage());
      check = false;
    } finally {
      try {
        connection.close();
        statement.close();
        result.close();
      } catch (SQLException e) {
        System.out.println("Erro ao finalizar conexão " + e.getMessage());
      }
    }

    return users;
  }

  public boolean updateUser(int id, String nome) {
    connect();
    String sql = "UPDATE usuario SET nome=? WHERE id=?";

    try {
      pst = connection.prepareStatement(sql);
      pst.setString(1, nome);
      pst.setInt(2, id);
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

  public boolean deleteUser(int id) {
    connect();
    String sql = "DELETE FROM usuario WHERE id=?";

    try {
      pst = connection.prepareStatement(sql);
      pst.setInt(1, id);
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
}
