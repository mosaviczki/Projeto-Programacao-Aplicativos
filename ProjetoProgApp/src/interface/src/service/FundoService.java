package service;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import dao.FundoDao;
import db.BancoDados;
import entities.Fundo;

public class FundoService {
    private Connection conn;

    public void createFundo(Fundo fundo) throws SQLException, IOException {
        conn = BancoDados.conectar();
        new FundoDao(conn).create(fundo);  
    }

    public void updateFundo(Fundo fundo) throws SQLException, IOException {
        conn = BancoDados.conectar();
        new FundoDao(conn).update(fundo);
    }

    public void deleteFundo(int id) throws SQLException, IOException {
        conn = BancoDados.conectar();
        new FundoDao(conn).delete(id);
    }

    public List<Fundo> findAllFundo() throws SQLException, IOException {
        conn = BancoDados.conectar();
        return new FundoDao(conn).findAll();
    }

    public double getFundoTotal(int mes, int ano) throws SQLException, IOException {
        conn = BancoDados.conectar();
        return new FundoDao(conn).getValueMonthTotal(mes, ano);
    }

    public double getFundoMensal(int ano) throws SQLException, IOException {
        conn = BancoDados.conectar();
        return new FundoDao(conn).getValueYearMensal(ano);
    }

    public double getFundoOcasional(int ano) throws SQLException, IOException {
        conn = BancoDados.conectar();
        return new FundoDao(conn).getValueYearOcasional(ano);
    }
}
