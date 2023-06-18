package service;

import entities.Rendimento;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import db.BancoDados;
import dao.RendimentoDao;
import java.util.List;

public class RendimentoService {
    public RendimentoService() {
    }

    public void createRedimento(Rendimento rendimento) throws SQLException, IOException {
        Connection conn = BancoDados.conectar();
        new RendimentoDao(conn).create(rendimento);
    }

    public void updateRedimento(Rendimento rendimento) throws SQLException, IOException {
        Connection conn = BancoDados.conectar();
        new RendimentoDao(conn).update(rendimento);
    }

    public void deleteRedimento(int id) throws SQLException, IOException {
        Connection conn = BancoDados.conectar();
        new RendimentoDao(conn).delete(id);
    }

    public List<Rendimento> findAllRedimentos() throws SQLException, IOException {
        Connection conn = BancoDados.conectar();
        return new RendimentoDao(conn).findAll();
    }
}
