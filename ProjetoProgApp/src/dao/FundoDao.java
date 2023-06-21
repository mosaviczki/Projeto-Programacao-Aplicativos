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
                    "insert into fundo (descricao, valor_mensal, valor_ocasional, mes, ano) values (?, ?, ?, ?, ?)");

            statement.setString(1, fundo.getDescricao());
            statement.setDouble(2, fundo.getValorMensal());
            statement.setDouble(3, fundo.getValorOcasional());
            statement.setDouble(4, fundo.getMes());
            statement.setDouble(5, fundo.getAno());

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
                fundo.setMes(result.getInt("mes"));
                fundo.setAno(result.getInt("ano"));

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
            statement.setDouble(4, fundo.getMes());
            statement.setDouble(5, fundo.getAno());
            statement.setInt(6, fundo.getId());

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

    public double getValueYearOcasional(int year) throws SQLException {
        PreparedStatement statement = null;
        ResultSet result = null;
        double value = 0;

        try {
            statement = conn.prepareStatement("select sum(valor_ocasional) as valor from fundo where ano = ?");
            statement.setInt(1, year);
            result = statement.executeQuery();

            if (result.next()) {
                value = result.getDouble("valor");
            }
            return value;
        } finally {
            BancoDados.finalizarStatement(statement);
            BancoDados.finalizarResultSet(result);
            BancoDados.desconectar();
        }
    }

    public double getValueYearMensal(int year) throws SQLException {
        PreparedStatement statement = null;
        ResultSet result = null;
        double value = 0;

        try {
            statement = conn.prepareStatement("select sum(valor_mensal) as valor from fundo where ano = ?");
            statement.setInt(1, year);
            result = statement.executeQuery();

            if (result.next()) {
                value = result.getDouble("valor");
            }
            return value*12;
        } finally {
            BancoDados.finalizarStatement(statement);
            BancoDados.finalizarResultSet(result);
            BancoDados.desconectar();
        }
    }

    public double getValueMonthTotal(int month, int year) throws SQLException {
        PreparedStatement statement = null;
        ResultSet result = null;
        double valueAnual = 0;

        try {
            statement = conn.prepareStatement("select sum(valor_ocasional) as valor from fundo where mes = ? and ano = ?");
            statement.setInt(1, month);
            statement.setInt(2, year);
            result = statement.executeQuery();

            if (result.next()) {
                valueAnual = result.getDouble("valor");
            }
            
            double valueMensal = getValueMonthMensal(year);

            return valueAnual+valueMensal;
        } finally {
            BancoDados.finalizarStatement(statement);
            BancoDados.finalizarResultSet(result);
            BancoDados.desconectar();
        }
    }

    public double getValueMonthMensal(int year) throws SQLException {
        PreparedStatement statement = null;
        ResultSet result = null;
        double value = 0;

        try {
            statement = conn.prepareStatement("select sum(valor_mensal) as valor from fundo where ano = ?");
            statement.setInt(1, year);
            result = statement.executeQuery();

            if (result.next()) {
                value = result.getDouble("valor");
            }
            return value;
        } finally {
            BancoDados.finalizarStatement(statement);
            BancoDados.finalizarResultSet(result);
            BancoDados.desconectar();
        }
    }

    public ArrayList<Fundo> getFundoByMonth(int month) throws SQLException{
        PreparedStatement statement = null;
        ResultSet result = null;
        ArrayList<Fundo> list = new ArrayList<Fundo>();

        try {
            statement = conn.prepareStatement("select * from fundo where mes <= ? order by id");
            statement.setInt(1, month);
            result = statement.executeQuery();

            while (result.next()) {
                Fundo fundo = new Fundo();

                fundo.setId(result.getInt("id"));
                fundo.setDescricao(result.getString("descricao"));
                fundo.setValorMensal(result.getDouble("valor_mensal"));
                fundo.setValorOcasional(result.getDouble("valor_ocasional"));
                fundo.setMes(result.getInt("mes"));
                fundo.setAno(result.getInt("ano"));

                list.add(fundo);
            }
            return list;
        } finally {
            BancoDados.finalizarStatement(statement);
            BancoDados.finalizarResultSet(result);
            BancoDados.desconectar();
        }
    }

    public ArrayList<Fundo> getFundoByYear(int year) throws SQLException{
        PreparedStatement statement = null;
        ResultSet result = null;
        ArrayList<Fundo> list = new ArrayList<Fundo>();

        try {
            statement = conn.prepareStatement("select * from fundo where ano = ? order by id");
            statement.setInt(1, year);
            result = statement.executeQuery();

            while (result.next()) {
                Fundo fundo = new Fundo();

                fundo.setId(result.getInt("id"));
                fundo.setDescricao(result.getString("descricao"));
                fundo.setValorMensal(result.getDouble("valor_mensal"));
                fundo.setValorOcasional(result.getDouble("valor_ocasional"));
                fundo.setMes(result.getInt("mes"));
                fundo.setAno(result.getInt("ano"));

                list.add(fundo);
            }
            return list;
        } finally {
            BancoDados.finalizarStatement(statement);
            BancoDados.finalizarResultSet(result);
            BancoDados.desconectar();
        }
    }
}
