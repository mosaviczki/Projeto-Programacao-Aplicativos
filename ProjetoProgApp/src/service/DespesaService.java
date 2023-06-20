package service;

import entities.Despesa;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import db.BancoDados;
import dao.DespesaDao;

import java.util.ArrayList;

public class DespesaService {
    public void createDespesa(Despesa despesa, String nomeCategoria) throws SQLException, IOException {
        despesa.setCategoria(new CategoriaService().findCategoriaByName(nomeCategoria));
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

    public ArrayList<Despesa> findAllDespesa() throws SQLException, IOException {
        Connection conn = BancoDados.conectar();
        ArrayList<Despesa> despesas = new DespesaDao(conn).findAll();

        for (Despesa despesa : despesas) {
            despesa.setCategoria(new CategoriaService().findCategoriaById(despesa.getCategoria().getId()));
        }

        return despesas;
    }
}
