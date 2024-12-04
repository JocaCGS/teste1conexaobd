package com.colini.interfaces;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.colini.models.Pessoas;

public interface PessoasDAO {
    public void createTableIfNotExists(Connection connection) throws SQLException;

    public List<Pessoas> coletaDados(Connection connection) throws SQLException;

    public void insereDados(Pessoas p, Connection connection) throws SQLException;

    public void deletaDados(String cpf, Connection connection) throws SQLException;

    public void alteraNome(String nome, String cpf, Connection connection) throws SQLException;

    public void alteraDataNascimento(String dataNascimento, String cpf, Connection connection) throws SQLException;

    public void alteraEmail(String email, String cpf, Connection connection) throws SQLException;
}
