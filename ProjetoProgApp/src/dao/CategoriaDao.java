package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import db.BancoDados;
import entities.Categoria;

public class CategoriaDao {
    private Connection conn;

    public CategoriaDao(Connection conn) {
        this.conn = conn;
    }

    public void create(Categoria categoria) throws SQLException {
        PreparedStatement statement = null;

        try {
            statement = conn.prepareStatement("insert into categoria (nome) values (?)");
            statement.setString(1, categoria.getNome());
            statement.executeUpdate();
        } finally {
            BancoDados.finalizarStatement(statement);
            BancoDados.desconectar();
        }
    }

    public Categoria buscarPorId(int idCategoria) throws SQLException {
        PreparedStatement statement = null;
        ResultSet result = null;

        try {
            statement = conn.prepareStatement("select * from categoria where id = ?");
            statement.setInt(1, idCategoria);
            result = statement.executeQuery();

            if (result.next()) {
                Categoria categoria = new Categoria();

                categoria.setId(result.getInt("id"));
                categoria.setNome(result.getString("nome"));

                return categoria;
            }
        } finally {
            BancoDados.finalizarStatement(statement);
            BancoDados.finalizarResultSet(result);
            BancoDados.desconectar();
        }
        return null;
    }

    public ArrayList<Categoria> findAll() throws SQLException {
        PreparedStatement statement = null;
        ResultSet result = null;
        ArrayList<Categoria> list = new ArrayList<Categoria>();

        try {
            statement = conn.prepareStatement("select * from categoria order by periodo");
            result = statement.executeQuery();

            while (result.next()) {
                Categoria categoria = new Categoria();

                categoria.setId(result.getInt("id"));
                categoria.setNome(result.getString("nome"));

                list.add(categoria);
            }
            return list;
        } finally {
            BancoDados.finalizarStatement(statement);
            BancoDados.finalizarResultSet(result);
            BancoDados.desconectar();
        }
    }

    public void update(Categoria categoria) throws SQLException {
        PreparedStatement statement = null;

        try {
            statement = conn.prepareStatement(
                    "update rendimento set nome = ? where id = ?");

            statement.setString(1, categoria.getNome());

            statement.executeUpdate();
        } finally {
            BancoDados.finalizarStatement(statement);
            BancoDados.desconectar();
        }
    }

    public void delete(int idCategoria) throws SQLException {
        PreparedStatement statement = null;

        try {
            statement = conn.prepareStatement("delete from categoria where id = ?");
            statement.setInt(1, idCategoria);

            statement.executeUpdate();
        } finally {
            BancoDados.finalizarStatement(statement);
            BancoDados.desconectar();
        }
    }
}
