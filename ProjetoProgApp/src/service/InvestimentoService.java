package service;

import entities.Investimento;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import db.BancoDados;
import dao.InvestimentoDao;

import java.util.ArrayList;
import java.util.List;

public class InvestimentoService {
    private Connection conn;

    public void createInvestimento(Investimento investimento) throws SQLException, IOException {
        conn = BancoDados.conectar();
        new InvestimentoDao(conn).create(investimento);
    }

    public void updateInvestimento(Investimento investimento) throws SQLException, IOException {
        conn = BancoDados.conectar();
        new InvestimentoDao(conn).update(investimento);
    }

    public void deleteInvestimento(int id) throws SQLException, IOException {
        conn = BancoDados.conectar();
        new InvestimentoDao(conn).delete(id);
    }

    public List<Investimento> findAllInvestimento() throws SQLException, IOException {
        conn = BancoDados.conectar();
        return new InvestimentoDao(conn).findAll();
    }

    public ArrayList<Investimento> findAllInvestimentosByMonth(int month) throws SQLException, IOException {
        conn = BancoDados.conectar();
        ArrayList<Investimento> investimentos = new InvestimentoDao(conn).getInvestimentoByMonth(month);

        return investimentos;
    }

    public ArrayList<Investimento> findAllInvestimentosByYear(int year) throws SQLException, IOException {
        conn = BancoDados.conectar();
        ArrayList<Investimento> investimentos = new InvestimentoDao(conn).getInvestimentoByYear(year);

        return investimentos;
    }

    public double getInvestimentoTotal(int mes, int ano) throws SQLException, IOException {
        conn = BancoDados.conectar();
        return new InvestimentoDao(conn).getValueMonthTotal(mes, ano);
    }

    public double getInvestimentoMensal(int ano) throws SQLException, IOException {
        conn = BancoDados.conectar();
        return new InvestimentoDao(conn).getValueYearMensal(ano);
    }

    public double getInvestimentoOcasional(int ano) throws SQLException, IOException {
        conn = BancoDados.conectar();
        return new InvestimentoDao(conn).getValueYearOcasional(ano);
    }
}
