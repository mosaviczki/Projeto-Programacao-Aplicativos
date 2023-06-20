package service;

import entities.Despesa;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import db.BancoDados;
import dao.DespesaDao;

import java.util.ArrayList;

public class DespesaService {
    private Connection conn;

    public void createDespesa(Despesa despesa, String nomeCategoria) throws SQLException, IOException {
        despesa.setCategoria(new CategoriaService().findCategoriaByName(nomeCategoria));
        conn = BancoDados.conectar();
        new DespesaDao(conn).create(despesa);
    }

    public void updateDespesa(Despesa newDespesa, String nomeCategoria) throws SQLException, IOException {
        newDespesa.setCategoria(new CategoriaService().findCategoriaByName(nomeCategoria));
        conn = BancoDados.conectar();
        new DespesaDao(conn).update(newDespesa);
    }

    public void deleteDespesa(int id) throws SQLException, IOException {
        conn = BancoDados.conectar();
        new DespesaDao(conn).delete(id);
    }

    public ArrayList<Despesa> findAllDespesa() throws SQLException, IOException {
        conn = BancoDados.conectar();
        ArrayList<Despesa> despesas = new DespesaDao(conn).findAll();

        for (Despesa despesa : despesas) {
            despesa.setCategoria(new CategoriaService().findCategoriaById(despesa.getCategoria().getId()));
        }

        return despesas;
    }

    public Despesa findDespesaByName(String name) throws SQLException, IOException {
        conn = BancoDados.conectar();
        return new DespesaDao(conn).findByName(name);
    }
}
