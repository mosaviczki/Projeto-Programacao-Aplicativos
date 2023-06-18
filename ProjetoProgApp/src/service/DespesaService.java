package service;

import entities.Despesa;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import db.BancoDados;
import dao.DespesasDAO;
import java.util.List;

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

    public List<Despesa> findAllDespesa(int id) throws SQLException, IOException {
        Connection conn = BancoDados.conectar();
        return new DespesaDao(conn).findAll();
    }
}
