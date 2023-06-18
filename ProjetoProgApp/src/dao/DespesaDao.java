package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import db.BancoDados;
import entities.Despesa;

public class DespesaDao {
    private Connection conn;

    public DespesaDao(Connection conn) {
        this.conn = conn;
    }

    public void create(Despesa despesa) throws SQLException {
        PreparedStatement statement = null;

        try {
            statement = conn.prepareStatement(
                    "insert into despesa (id_categoria, id_subcategoria, descricao, frequencia, valor) values (?, ?, ?, ?, ?)");

            statement.setInt(1, despesa.getCategoria().getId());
            statement.setInt(2, despesa.getSubCategoria().getId());
            statement.setString(3, despesa.getDescricao());
            statement.setInt(4, despesa.getFrequencia());
            statement.setDouble(5, despesa.getValor());

            statement.executeUpdate();
        } finally {
            BancoDados.finalizarStatement(statement);
            BancoDados.desconectar();
        }
    }

    public ArrayList<Despesa> findAll() throws SQLException {
        PreparedStatement statement = null;
        ResultSet result = null;
        ArrayList<Despesa> list = new ArrayList<Despesa>();

        try {
            statement = conn.prepareStatement("select * from despesa order by id");
            result = statement.executeQuery();

            while (result.next()) {
                Despesa despesa = new Despesa();

                despesa.setId(result.getInt("id"));
                despesa.setCategoria(new CategoriaDao(conn).buscarPorId(result.getInt("id_categoria")));
                despesa.setDescricao(result.getString("descricao"));
                despesa.setFrequencia(result.getInt("frequencia"));
                despesa.setValor(result.getDouble("valor_despesa"));


                list.add(despesa);
            }
            return list;
        } finally {
            BancoDados.finalizarStatement(statement);
            BancoDados.finalizarResultSet(result);
            BancoDados.desconectar();
        }
    }

    public void update(Despesa despesa) throws SQLException {
        PreparedStatement statement = null;

        try {
            statement = conn.prepareStatement(
                    "update despesa set id_categoria = ?, descricao = ?, frequencia = ?, valor_despesa = ? where id = ?");

            statement.setInt(1, despesa.getCategoria().getId());
            statement.setString(2, despesa.getDescricao());
            statement.setInt(3, despesa.getFrequencia());
            statement.setDouble(4, despesa.getValor());
            statement.setInt(5, despesa.getId());

            statement.executeUpdate();
        } finally {
            BancoDados.finalizarStatement(statement);
            BancoDados.desconectar();
        }
    }

    public void delete(int idDespesa) throws SQLException {
        PreparedStatement statement = null;

        try {
            statement = conn.prepareStatement("delete from despesa where id = ?");
            statement.setInt(1, idDespesa);

            statement.executeUpdate();
        } finally {
            BancoDados.finalizarStatement(statement);
            BancoDados.desconectar();
        }
    }
}
