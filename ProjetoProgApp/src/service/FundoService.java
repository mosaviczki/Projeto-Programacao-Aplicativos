package service;

import entities.Fundo;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import db.BancoDados;
import dao.FundoDao;

import java.util.ArrayList;

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

    public ArrayList<Fundo> findAllFundo(int id) throws SQLException, IOException {
        Connection conn = BancoDados.conectar();
        return new FundoDao(conn).findAll();
    }
}
