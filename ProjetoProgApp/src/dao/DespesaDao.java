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
                    "insert into despesa (id_categoria, descricao, valor_mensal, valor_ocasional, mes, ano) values (?, ?, ?, ?, ?, ?)");

            statement.setInt(1, despesa.getCategoria().getId());
            statement.setString(2, despesa.getDescricao());
            statement.setDouble(3, despesa.getValorMensal());
            statement.setDouble(4, despesa.getValorOcasional());
            statement.setInt(5, despesa.getMes());
            statement.setInt(6, despesa.getAno());

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
            statement = conn.prepareStatement("select * from despesa order by mes");
            result = statement.executeQuery();

            while (result.next()) {
                Despesa despesa = new Despesa();

                despesa.setId(result.getInt("id"));
                despesa.getCategoria().setId(result.getInt("id_categoria"));
                despesa.setDescricao(result.getString("descricao"));
                despesa.setValorMensal(result.getDouble("valor_mensal"));
                despesa.setValorOcasional(result.getInt("valor_ocasional"));
                despesa.setMes(result.getInt("mes"));
                despesa.setAno(result.getInt("ano"));
                despesa.calcularvalorDespesa();

                list.add(despesa);
            }
            return list;
        } finally {
            BancoDados.finalizarStatement(statement);
            BancoDados.finalizarResultSet(result);
            BancoDados.desconectar();
        }
    }

    public Despesa findByName(String nomeDespesa) throws SQLException{
        PreparedStatement statement = null;
        ResultSet result = null;

        try {
            statement = conn.prepareStatement("select * from despesa where descricao = ?");
            statement.setString(1, nomeDespesa);
            result = statement.executeQuery();

            if(result.next()) {
                Despesa despesa = new Despesa();

                despesa.setId(result.getInt("id"));
                despesa.getCategoria().setId(result.getInt("id_categoria"));
                despesa.setDescricao(result.getString("descricao"));
                despesa.setValorMensal(result.getDouble("valor_mensal"));
                despesa.setValorOcasional(result.getInt("valor_ocasional"));
                despesa.setMes(result.getInt("mes"));
                despesa.setAno(result.getInt("ano"));
                despesa.calcularvalorDespesa();

                return despesa;
            }
            return null;
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
                    "update despesa set id_categoria = ?, descricao = ?, valor_mensal = ?, valor_ocasional = ?, mes = ?, ano = ? where id = ?");

            statement.setInt(1, despesa.getCategoria().getId());
            statement.setString(2, despesa.getDescricao());
            statement.setDouble(3, despesa.getValorMensal());
            statement.setDouble(4, despesa.getValorOcasional());
            statement.setInt(5, despesa.getMes());
            statement.setInt(6, despesa.getAno());
            statement.setInt(7, despesa.getId());

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

    public double getValueYearOcasional(int year) throws SQLException{
        PreparedStatement statement = null;
        ResultSet result = null;
        double value = 0;

        try {
            statement = conn.prepareStatement("select sum(valor_ocasional) as valor from despesa where ano = ?");
            statement.setInt(1, year);
            result = statement.executeQuery();

            if(result.next()) {
                value = result.getDouble("valor");
            }
            return value;
        } finally {
            BancoDados.finalizarStatement(statement);
            BancoDados.finalizarResultSet(result);
            BancoDados.desconectar();
        }
    }

    public double getValueYearMensal(int year) throws SQLException{
        PreparedStatement statement = null;
        ResultSet result = null;
        double value = 0;

        try {
            statement = conn.prepareStatement("select sum(valor_mensal) as valor from despesa where ano = ?");
            statement.setInt(1, year);
            result = statement.executeQuery();

            if(result.next()) {
                value = result.getDouble("valor");
            }
            return value*12;
        } finally {
            BancoDados.finalizarStatement(statement);
            BancoDados.finalizarResultSet(result);
            BancoDados.desconectar();
        }
    }

    public double getValueMonthTotal(int month, int year) throws SQLException{
        PreparedStatement statement = null;
        ResultSet result = null;
        double valueAnual = 0;

        try {
            statement = conn.prepareStatement("select sum(valor_ocasional) as valor from despesa where mes = ? and ano = ?");
            statement.setInt(1, month);
            statement.setInt(2, year);
            result = statement.executeQuery();

            if(result.next()) {
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

    public double getValueMonthMensal(int year)throws SQLException{
        PreparedStatement statement = null;
        ResultSet result = null;
        double value = 0;

        try {
            statement = conn.prepareStatement("select sum(valor_mensal) as valor from despesa where ano = ?");
            statement.setInt(1, year);
            result = statement.executeQuery();

            if(result.next()) {
                value = result.getDouble("valor");
            }
            return value;
        } finally {
            BancoDados.finalizarStatement(statement);
            BancoDados.finalizarResultSet(result);
            BancoDados.desconectar();
        }
    }

    public ArrayList<Despesa> getDespesaByMonth(int month) throws SQLException{
        PreparedStatement statement = null;
        ResultSet result = null;
        ArrayList<Despesa> list = new ArrayList<Despesa>();

        try {
            statement = conn.prepareStatement("select * from despesa where mes <= ? order by id");
            statement.setInt(1, month);
            result = statement.executeQuery();

            while(result.next()) {
                Despesa despesa = new Despesa();

                despesa.setId(result.getInt("id"));
                despesa.getCategoria().setId(result.getInt("id_categoria"));
                despesa.setDescricao(result.getString("descricao"));
                despesa.setValorMensal(result.getDouble("valor_mensal"));
                despesa.setValorOcasional(result.getInt("valor_ocasional"));
                despesa.setMes(result.getInt("mes"));
                despesa.setAno(result.getInt("ano"));
                despesa.calcularvalorDespesa();

                list.add(despesa);
            }
            return list;
        } finally {
            BancoDados.finalizarStatement(statement);
            BancoDados.finalizarResultSet(result);
            BancoDados.desconectar();
        }
    }

    public ArrayList<Despesa> getDespesaByYear(int year) throws SQLException{
        PreparedStatement statement = null;
        ResultSet result = null;
        ArrayList<Despesa> list = new ArrayList<Despesa>();

        try {
            statement = conn.prepareStatement("select * from despesa where ano = ? order by id");
            statement.setInt(1, year);
            result = statement.executeQuery();

            while(result.next()) {
                Despesa despesa = new Despesa();

                despesa.setId(result.getInt("id"));
                despesa.getCategoria().setId(result.getInt("id_categoria"));
                despesa.setDescricao(result.getString("descricao"));
                despesa.setValorMensal(result.getDouble("valor_mensal"));
                despesa.setValorOcasional(result.getInt("valor_ocasional"));
                despesa.setMes(result.getInt("mes"));
                despesa.setAno(result.getInt("ano"));
                despesa.calcularvalorDespesa();

                list.add(despesa);
            }
            return list;
        } finally {
            BancoDados.finalizarStatement(statement);
            BancoDados.finalizarResultSet(result);
            BancoDados.desconectar();
        }
    }

    public ArrayList<Despesa> getDespesaByCategory(int category) throws SQLException{
        PreparedStatement statement = null;
        ResultSet result = null;
        ArrayList<Despesa> list = new ArrayList<Despesa>();

        try {
            statement = conn.prepareStatement("select * from despesa where id_categoria = ? order by id");
            statement.setInt(1, category);
            result = statement.executeQuery();

            while(result.next()) {
                Despesa despesa = new Despesa();

                despesa.setId(result.getInt("id"));
                despesa.getCategoria().setId(result.getInt("id_categoria"));
                despesa.setDescricao(result.getString("descricao"));
                despesa.setValorMensal(result.getDouble("valor_mensal"));
                despesa.setValorOcasional(result.getInt("valor_ocasional"));
                despesa.setMes(result.getInt("mes"));
                despesa.setAno(result.getInt("ano"));
                despesa.calcularvalorDespesa();

                list.add(despesa);
            }
            return list;
        } finally {
            BancoDados.finalizarStatement(statement);
            BancoDados.finalizarResultSet(result);
            BancoDados.desconectar();
        }
    }
}
