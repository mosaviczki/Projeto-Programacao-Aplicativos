package service;

import entities.Rendimento;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import db.BancoDados;
import dao.RendimentoDao;

import java.util.ArrayList;

public class RendimentoService {
    private Connection conn;

    public void createRendimento(Rendimento rendimento, String nomeCategoria) throws SQLException, IOException {
        rendimento.setCategoria(new CategoriaService().findCategoriaByName(nomeCategoria));
        conn = BancoDados.conectar();
        new RendimentoDao(conn).create(rendimento);
    }

    public void updateRendimento(Rendimento newRendimento, String nomeCategoria) throws SQLException, IOException {
        newRendimento.setCategoria(new CategoriaService().findCategoriaByName(nomeCategoria));
        conn = BancoDados.conectar();
        new RendimentoDao(conn).update(newRendimento);
    }

    public void deleteRendimento(int id) throws SQLException, IOException {
        conn = BancoDados.conectar();
        new RendimentoDao(conn).delete(id);
    }

    public ArrayList<Rendimento> findAllRendimentos() throws SQLException, IOException {
        conn = BancoDados.conectar();
        ArrayList<Rendimento> rendimentos = new RendimentoDao(conn).findAll();

        for (Rendimento rendimento : rendimentos) {
            rendimento.setCategoria(new CategoriaService().findCategoriaById(rendimento.getCategoria().getId()));
        }

        return rendimentos;
    }

    public ArrayList<Rendimento> findAllRendimentosByMonth(int month) throws SQLException, IOException {
        conn = BancoDados.conectar();
        ArrayList<Rendimento> rendimentos = new RendimentoDao(conn).getRendimentoByMonth(month);

        return rendimentos;
    }

    public ArrayList<Rendimento> findAllRendimentosByYear(int year) throws SQLException, IOException {
        conn = BancoDados.conectar();
        ArrayList<Rendimento> rendimentos = new RendimentoDao(conn).getRendimentoByYear(year);

        return rendimentos;
    }

    public ArrayList<Rendimento> findAllRendimentosbyCategoria(int categoriaId) throws SQLException, IOException {
        conn = BancoDados.conectar();
        ArrayList<Rendimento> rendimentos = new RendimentoDao(conn).getRendimentoByCategory(categoriaId);

        return rendimentos;
    }

    public Rendimento findRendimentoByName(String name) throws SQLException, IOException {
        conn = BancoDados.conectar();
        return new RendimentoDao(conn).findByName(name);
    }

    public double getRendimentoTotal(int mes, int ano) throws SQLException, IOException {
        conn = BancoDados.conectar();
        return new RendimentoDao(conn).getValueMonthTotal(mes, ano);
    }

    public double getRendimentoMensal(int ano) throws SQLException, IOException {
        conn = BancoDados.conectar();
        return new RendimentoDao(conn).getValueYearMensal(ano);
    }

    public double getRendimentoOcasional(int ano) throws SQLException, IOException {
        conn = BancoDados.conectar();
        return new RendimentoDao(conn).getValueYearOcasional(ano);
    }
}
