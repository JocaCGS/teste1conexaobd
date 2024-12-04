package com.colini.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.colini.interfaces.PessoasDAO;
import com.colini.models.Pessoas;

public class PessoasDAOImpl implements PessoasDAO{

    public void createTableIfNotExists(Connection connection) throws SQLException {
        String sql = "CREATE TABLE IF NOT EXISTS pessoas (" +
                     "nome VARCHAR(100), " +
                     "data_nascimento DATE, " +
                     "cpf VARCHAR(11), " +
                     "email VARCHAR(100), " +
                     "PRIMARY KEY (cpf)" +
                     ");";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.executeUpdate();
        statement.close();
    }
    
    
    public List<Pessoas> coletaDados(Connection connection) throws SQLException {
        List<Pessoas> lista = new ArrayList<Pessoas>();

        String sql = "SELECT * FROM pessoas";

        PreparedStatement statement = connection.prepareStatement(sql);

        ResultSet resultSet = statement.executeQuery();



        while (resultSet.next()) {
            String nome = resultSet.getString("nome");
            String nascimento = resultSet.getString("data_nascimento");
            String cpf = resultSet.getString("cpf");
            String email = resultSet.getString("email");
            System.out.println(nome + " - " + nascimento + " - " + cpf + " - " + email);
            Pessoas pessoa = new Pessoas(nome, nascimento, cpf, email);
            lista.add(pessoa);
        }
        statement.close();
        return lista;
    }

    public void insereDados(Pessoas p, Connection connection) throws SQLException {
        String sql = "INSERT INTO pessoas (nome, data_nascimento, cpf, email) VALUES (?,?,?,?)";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, p.getNome());
        statement.setString(2, p.getNascimento());
        statement.setString(3, p.getCpf());
        statement.setString(4, p.getEmail());
        statement.execute();

        statement.close();
    }

    public void deletaDados(String cpf, Connection connection) throws SQLException {
        String sql = "DELETE FROM pessoas WHERE cpf = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, cpf);
        statement.executeUpdate();
        statement.close();
    }

    public void alteraNome(String nome, String cpf, Connection connection) throws SQLException {
        String sql = "UPDATE pessoas SET nome =? WHERE cpf =?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, nome);
        statement.setString(2, cpf);
        statement.executeUpdate();
        statement.close();
    }

    public void alteraDataNascimento(String dataNascimento, String cpf, Connection connection) throws SQLException {
        String sql = "UPDATE pessoas SET data_nascimento =? WHERE cpf =?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, dataNascimento);
        statement.setString(2, cpf);
        statement.executeUpdate();
        statement.close();
    }

    public void alteraEmail(String email, String cpf, Connection connection) throws SQLException {
        String sql = "UPDATE pessoas SET email =? WHERE cpf =?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, email);
        statement.setString(2, cpf);
        statement.executeUpdate();
        statement.close();
    }


}