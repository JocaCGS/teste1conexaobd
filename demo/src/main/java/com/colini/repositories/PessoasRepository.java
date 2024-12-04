package com.colini.repositories;

import java.sql.Connection;
import java.sql.SQLException;

import com.colini.dao.PessoasDAOImpl;
import com.colini.models.Pessoas;

public class PessoasRepository {
    PessoasDAOImpl pessoasDAOImpl = new PessoasDAOImpl();

    public PessoasRepository() throws SQLException {
        this.pessoasDAOImpl = new PessoasDAOImpl();
    }

    public void createTableIfNotExists(Connection connection) throws SQLException {
        pessoasDAOImpl.createTableIfNotExists(connection);
    }

    public void coletaDados(Connection connection) throws SQLException {
        pessoasDAOImpl.coletaDados(connection);

    }

    public void insereDados(Pessoas p, Connection connection) throws SQLException {
        pessoasDAOImpl.insereDados(p, connection);
    }

    public void deletaDados(String cpf, Connection connection) throws SQLException {
        pessoasDAOImpl.deletaDados(cpf, connection);
    }

    public void alteraNome(String nome, String cpf, Connection connection) throws SQLException {
        pessoasDAOImpl.alteraNome(nome, cpf, connection);
    }

    public void alteraDataNascimento(String dataNascimento, String cpf, Connection connection) throws SQLException {
        pessoasDAOImpl.alteraDataNascimento(dataNascimento, cpf, connection);
    }

    public void alteraEmail(String email, String cpf, Connection connection) throws SQLException {
        pessoasDAOImpl.alteraEmail(email, cpf, connection);
    }
}
