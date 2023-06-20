package service;

import entities.Investimento;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import db.BancoDados;
import dao.InvestimentoDao;
import java.util.List;

public class InvestimentoService {

    public InvestimentoService() {
    }

    public void createInvestimento(Investimento investimento) throws SQLException, IOException {
        Connection conn = BancoDados.conectar();
        new InvestimentoDao(conn).create(investimento);
    }

    public void updateInvestimento(Investimento investimento) throws SQLException, IOException {
        Connection conn = BancoDados.conectar();
        new InvestimentoDao(conn).update(investimento);
    }

    public void deleteInvestimento(int id) throws SQLException, IOException {
        Connection conn = BancoDados.conectar();
        new InvestimentoDao(conn).delete(id);
    }

    public List<Investimento> findAllInvestimento() throws SQLException, IOException {
        Connection conn = BancoDados.conectar();
        return new InvestimentoDao(conn).findAll();
    }
    
    
}
