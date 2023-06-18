package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import db.BancoDados;
import entities.Fundo;

public class FundoDao {
    private Connection conn;

    public FundoDao(Connection conn) {
        this.conn = conn;
    }

    public void create(Fundo fundo) throws SQLException {
        PreparedStatement statement = null;

        try {
            statement = conn.prepareStatement(
                    "insert into fundo (descricao, valor_mensal, valor_ocasional) values (?, ?, ?)");

            statement.setString(1, fundo.getDescricao());
            statement.setDouble(2, fundo.getValorMensal());
            statement.setDouble(3, fundo.getValorOcasional());

            statement.executeUpdate();
        } finally {
            BancoDados.finalizarStatement(statement);
            BancoDados.desconectar();
        }
    }

    public ArrayList<Fundo> findAll() throws SQLException {
        PreparedStatement statement = null;
        ResultSet result = null;
        ArrayList<Fundo> list = new ArrayList<Fundo>();

        try {
            statement = conn.prepareStatement("select * from fundo order by id");
            result = statement.executeQuery();

            while (result.next()) {
                Fundo fundo = new Fundo();

                fundo.setId(result.getInt("id"));
                fundo.setDescricao(result.getString("descricao"));
                fundo.setValorMensal(result.getDouble("valor_mensal"));
                fundo.setValorOcasional(result.getDouble("valor_ocasional"));

                list.add(fundo);
            }
            return list;
        } finally {
            BancoDados.finalizarStatement(statement);
            BancoDados.finalizarResultSet(result);
            BancoDados.desconectar();
        }
    }

    public void update(Fundo fundo) throws SQLException {
        PreparedStatement statement = null;

        try {
            statement = conn.prepareStatement(
                    "update fundo set descricao = ?, valor_mensal = ?, valor_ocasional = ? where id = ?");

            statement.setString(1, fundo.getDescricao());
            statement.setDouble(2, fundo.getValorMensal());
            statement.setDouble(3, fundo.getValorOcasional());
            statement.setInt(4, fundo.getId());

            statement.executeUpdate();
        } finally {
            BancoDados.finalizarStatement(statement);
            BancoDados.desconectar();
        }
    }

    public void delete(int idFundo) throws SQLException {
        PreparedStatement statement = null;

        try {
            statement = conn.prepareStatement("delete from fundo where id = ?");
            statement.setInt(1, idFundo);

            statement.executeUpdate();
        } finally {
            BancoDados.finalizarStatement(statement);
            BancoDados.desconectar();
        }
    }
}
