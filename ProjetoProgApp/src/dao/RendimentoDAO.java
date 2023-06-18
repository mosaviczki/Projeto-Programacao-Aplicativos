package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import db.BancoDados;
import entities.Rendimento;

public class RendimentoDao {
    private Connection conn;

    public RendimentoDao(Connection conn) {
        this.conn = conn;
    }

    public void create(Rendimento rendimento) throws SQLException {
        PreparedStatement statement = null;

        try {
            statement = conn.prepareStatement(
                    "insert into rendimento (id_categoria, descricao, frequencia, valor_rendimento) values (?, ?, ?, ?, ?)");

            statement.setInt(1, rendimento.getCategoria().getId());
            statement.setString(2, rendimento.getDescricao());
            statement.setInt(3, rendimento.getFrequencia());
            statement.setDouble(4, rendimento.getValor_redimento());

            statement.executeUpdate();
        } finally {
            BancoDados.finalizarStatement(statement);
            BancoDados.desconectar();
        }
    }

    public Rendimento buscarPorId(int idRendimento) throws SQLException {
        PreparedStatement statement = null;
        ResultSet result = null;

        try {
            statement = conn.prepareStatement("select * from rendimento where id = ?");
            statement.setInt(1, idRendimento);
            result = statement.executeQuery();

            if (result.next()) {
                Rendimento rendimento = new Rendimento();

                rendimento.setId(result.getInt("id"));
                rendimento.setCategoria(new CategoriaDao(conn).buscarPorId(result.getInt("id_categoria")));
                rendimento.setDescricao(result.getString("descricao"));
                rendimento.setFrequencia(result.getInt("frequencia"));
                rendimento.setValor_redimento(result.getDouble("valor_rendimento"));

                return rendimento;
            }
        } finally {
            BancoDados.finalizarStatement(statement);
            BancoDados.finalizarResultSet(result);
            BancoDados.desconectar();
        }
        return null;
    }

    public ArrayList<Rendimento> findAll() throws SQLException {
        PreparedStatement statement = null;
        ResultSet result = null;
        ArrayList<Rendimento> list = new ArrayList<Rendimento>();

        try {
            statement = conn.prepareStatement("select * from rendimento order by id");
            result = statement.executeQuery();

            while (result.next()) {
                Rendimento rendimento = new Rendimento();

                rendimento.setId(result.getInt("id"));
                rendimento.setCategoria(new CategoriaDao(conn).buscarPorId(result.getInt("id_categoria")));
                rendimento.setDescricao(result.getString("descricao"));
                rendimento.setFrequencia(result.getInt("frequencia"));
                rendimento.setValor_redimento(result.getDouble("valor_rendimento"));


                list.add(rendimento);
            }
            return list;
        } finally {
            BancoDados.finalizarStatement(statement);
            BancoDados.finalizarResultSet(result);
            BancoDados.desconectar();
        }
    }

    public void update(Rendimento rendimento) throws SQLException {
        PreparedStatement statement = null;

        try {
            statement = conn.prepareStatement(
                    "update rendimento set id_categoria = ?, descricao = ?, frequencia = ?, valor_rendimento = ? where id = ?");

            statement.setInt(1, rendimento.getCategoria().getId());
            statement.setString(2, rendimento.getDescricao());
            statement.setInt(3, rendimento.getFrequencia());
            statement.setDouble(4, rendimento.getValor_redimento());
            statement.setInt(5, rendimento.getId());

            statement.executeUpdate();
        } finally {
            BancoDados.finalizarStatement(statement);
            BancoDados.desconectar();
        }
    }

    public void delete(int idRendimento) throws SQLException {
        PreparedStatement statement = null;

        try {
            statement = conn.prepareStatement("delete from rendimento where id = ?");
            statement.setInt(1, idRendimento);

            statement.executeUpdate();
        } finally {
            BancoDados.finalizarStatement(statement);
            BancoDados.desconectar();
        }
    }
}
