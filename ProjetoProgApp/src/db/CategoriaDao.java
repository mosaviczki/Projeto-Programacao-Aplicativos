package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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
}
