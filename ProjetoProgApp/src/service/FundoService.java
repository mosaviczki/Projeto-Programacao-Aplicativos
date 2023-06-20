package service;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import dao.FundoDao;
import db.BancoDados;
import entities.Fundo;

public class FundoService {
    public void createFundo(Fundo fundo) throws SQLException, IOException {
        Connection conn = BancoDados.conectar();
        new FundoDao(conn).create(fundo);  
    }

    public void updateFundo(Fundo fundo) throws SQLException, IOException {
        Connection conn = BancoDados.conectar();
        new FundoDao(conn).update(fundo);
    }

    public void deleteFundo(int id) throws SQLException, IOException {
        Connection conn = BancoDados.conectar();
        new FundoDao(conn).delete(id);
    }

    public List<Fundo> findAllFundo() throws SQLException, IOException {
        Connection conn = BancoDados.conectar();
        return new FundoDao(conn).findAll();
    }
}
