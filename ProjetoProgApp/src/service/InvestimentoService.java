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

    public void createRedimento(Investimento investimento) throws SQLException, IOException {
        Connection conn = BancoDados.conectar();
        new InvestimentoDao(conn).create(investimento);
    }

    public void updateRedimento(Investimento investimento) throws SQLException, IOException {
        Connection conn = BancoDados.conectar();
        new InvestimentoDao(conn).update(investimento);
    }

    public void deleteRedimento(int id) throws SQLException, IOException {
        Connection conn = BancoDados.conectar();
        new InvestimentoDao(conn).delete(id);
    }

    public List<Investimento> findAllRedimentos() throws SQLException, IOException {
        Connection conn = BancoDados.conectar();
        return new InvestimentoDao(conn).findAll();
    }
}
