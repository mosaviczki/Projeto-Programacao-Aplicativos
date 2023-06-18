package service;

import entities.Despesa;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import db.BancoDados;
import dao.DespesaDao;

import java.util.ArrayList;

public class DespesaService {
    public void createDespesa(Despesa despesa) throws SQLException, IOException {
        Connection conn = BancoDados.conectar();
        new DespesaDao(conn).create(despesa);  
    }

    public void updateDespesa(Despesa despesa) throws SQLException, IOException {
        Connection conn = BancoDados.conectar();
        new DespesaDao(conn).update(despesa);
    }

    public void deleteDespesa(int id) throws SQLException, IOException {
        Connection conn = BancoDados.conectar();
        new DespesaDao(conn).delete(id);
    }

    public ArrayList<Despesa> findAllDespesa(int id) throws SQLException, IOException {
        Connection conn = BancoDados.conectar();
        return new DespesaDao(conn).findAll();
    }
}
