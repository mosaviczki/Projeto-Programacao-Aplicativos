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

    public ArrayList<Despesa> findAllDespesasByMonth(int month) throws SQLException, IOException {
        conn = BancoDados.conectar();
        ArrayList<Despesa> despesas = new DespesaDao(conn).getDespesaByMonth(month);

        return despesas;
    }

    public ArrayList<Despesa> findAllDespesasByYear(int year) throws SQLException, IOException {
        conn = BancoDados.conectar();
        ArrayList<Despesa> despesas = new DespesaDao(conn).getDespesaByYear(year);

        return despesas;
    }

    public ArrayList<Despesa> findAllDespesasbyCategoria(int categoriaId) throws SQLException, IOException {
        conn = BancoDados.conectar();
        ArrayList<Despesa> despesas = new DespesaDao(conn).getDespesaByCategory(categoriaId);

        return despesas;
    }

    public Despesa findDespesaByName(String name) throws SQLException, IOException {
        conn = BancoDados.conectar();
        return new DespesaDao(conn).findByName(name);
    }

    public double getDespesaTotal(int mes, int ano) throws SQLException, IOException {
        conn = BancoDados.conectar();
        return new DespesaDao(conn).getValueMonthTotal(mes, ano);
    }

    public double getDespesaMensal(int ano) throws SQLException, IOException {
        conn = BancoDados.conectar();
        return new DespesaDao(conn).getValueYearMensal(ano);
    }

    public double getDespesaOcasional(int ano) throws SQLException, IOException {
        conn = BancoDados.conectar();
        return new DespesaDao(conn).getValueYearOcasional(ano);
    }
}
