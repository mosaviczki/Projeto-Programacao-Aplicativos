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

    public void create(String name) throws SQLException {
        PreparedStatement statement = null;

        try {
            statement = conn.prepareStatement("insert into categoria (nome) values (?)");
            statement.setString(1, name);
            statement.executeUpdate();
        } finally {
            BancoDados.finalizarStatement(statement);
            BancoDados.desconectar();
        }
    }

    public Categoria buscarPorId(int id) throws SQLException {
        PreparedStatement statement = null;
        ResultSet result = null;
        Categoria categoria = null;

        try {
            statement = conn.prepareStatement("select * from categoria where id = ?");
            statement.setInt(1, id);
            result = statement.executeQuery();

            if (result.next()) {
                categoria = new Categoria();

                categoria.setId(id);
                categoria.setNome(result.getString("nome"));
            }
        } finally {
            BancoDados.finalizarStatement(statement);
            BancoDados.finalizarResultSet(result);
            BancoDados.desconectar();
        }
        return categoria;
    }

    public Categoria findByName(String name) throws SQLException {
        PreparedStatement statement = null;
        ResultSet result = null;

        try {
            statement = conn.prepareStatement("SELECT * FROM categoria WHERE nome = ?");
            statement.setString(1, name);
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
            statement = conn.prepareStatement("select * from categoria order by id");
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

    public void update(String newName, int id) throws SQLException {
        PreparedStatement statement = null;

        try {
            statement = conn.prepareStatement(
                    "update categoria set nome = ? where id = ?");

            statement.setString(1, newName);
            statement.setInt(2, id);

            statement.executeUpdate();
        } finally {
            BancoDados.finalizarStatement(statement);
            BancoDados.desconectar();
        }
    }

    public void delete(int id) throws SQLException {
        PreparedStatement statement = null;

        try {
            statement = conn.prepareStatement("delete from categoria where id = ?");
            statement.setInt(1, id);

            statement.executeUpdate();
        } finally {
            BancoDados.finalizarStatement(statement);
            BancoDados.desconectar();
        }
    }
}
