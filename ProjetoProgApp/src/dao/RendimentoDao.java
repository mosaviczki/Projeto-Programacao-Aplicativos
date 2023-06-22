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
                    "insert into rendimento (id_categoria, descricao, valor_mensal, valor_ocasional, mes, ano) values (?, ?, ?, ?, ?, ?)");

            statement.setInt(1, rendimento.getCategoria().getId());
            statement.setString(2, rendimento.getDescricao());
            statement.setDouble(3, rendimento.getValorMensal());
            statement.setDouble(4, rendimento.getValorOcasional());
            statement.setInt(5, rendimento.getMes());
            statement.setInt(6, rendimento.getAno());

            statement.executeUpdate();
        } finally {
            BancoDados.finalizarStatement(statement);
            BancoDados.desconectar();
        }
    }

    public ArrayList<Rendimento> findAll() throws SQLException {
        PreparedStatement statement = null;
        ResultSet result = null;
        ArrayList<Rendimento> list = new ArrayList<Rendimento>();

        try {
            statement = conn.prepareStatement("select * from rendimento order by id");
            result = statement.executeQuery();

            while (result.next()) {
                // Lancamento rendimento = new Rendimento();
                Rendimento rendimento = new Rendimento();

                rendimento.setId(result.getInt("id"));
                rendimento.getCategoria().setId(result.getInt("id_categoria"));
                rendimento.setDescricao(result.getString("descricao"));
                rendimento.setValorMensal(result.getDouble("valor_mensal"));
                rendimento.setValorOcasional(result.getDouble("valor_ocasional"));
                rendimento.setMes(result.getInt("mes"));
                rendimento.setAno(result.getInt("ano"));
                rendimento.calcularValorRendimento();

                list.add(rendimento);
            }
        } finally {
            BancoDados.finalizarResultSet(result);
            BancoDados.finalizarStatement(statement);
            BancoDados.desconectar();
        }

        return list;
    }

    public Rendimento findByName(String nomeRendimento) throws SQLException {
        PreparedStatement statement = null;
        ResultSet result = null;

        try {
            statement = conn.prepareStatement("select * from rendimento where descricao = ?");
            statement.setString(1, nomeRendimento);
            result = statement.executeQuery();

            if (result.next()) {
                Rendimento rendimento = new Rendimento();

                rendimento.setId(result.getInt("id"));
                rendimento.getCategoria().setId(result.getInt("id_categoria"));
                rendimento.setDescricao(result.getString("descricao"));
                rendimento.setValorMensal(result.getDouble("valor_mensal"));
                rendimento.setValorOcasional(result.getDouble("valor_ocasional"));
                rendimento.setMes(result.getInt("mes"));
                rendimento.setAno(result.getInt("ano"));
                rendimento.calcularValorRendimento();

                return rendimento;
            }
            return null;
        } finally {
            BancoDados.finalizarResultSet(result);
            BancoDados.finalizarStatement(statement);
            BancoDados.desconectar();
        }
    }

    public void update(Rendimento rendimento) throws SQLException {
        PreparedStatement statement = null;

        try {
            statement = conn.prepareStatement(
                    "update rendimento set id_categoria = ?, descricao = ?, valor_mensal = ?, valor_ocasional = ?, mes = ?, ano = ? where id = ?");

            statement.setInt(1, rendimento.getCategoria().getId());
            statement.setString(2, rendimento.getDescricao());
            statement.setDouble(3, rendimento.getValorMensal());
            statement.setDouble(4, rendimento.getValorOcasional());
            statement.setInt(5, rendimento.getMes());
            statement.setInt(6, rendimento.getAno());
            statement.setInt(7, rendimento.getId());

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

    public double getValueYearOcasional(int year) throws SQLException {
        PreparedStatement statement = null;
        ResultSet result = null;
        double value = 0;

        try {
            statement = conn.prepareStatement("select sum(valor_ocasional) as valor from rendimento where ano = ?");
            statement.setInt(1, year);
            result = statement.executeQuery();

            if (result.next()) {
                value = result.getDouble("valor");
            }
        } finally {
            BancoDados.finalizarResultSet(result);
            BancoDados.finalizarStatement(statement);
            BancoDados.desconectar();
        }

        return value;
    }

    public double getValueYearMensal(int year) throws SQLException {
        PreparedStatement statement = null;
        ResultSet result = null;
        double value = 0;

        try{
            statement = conn.prepareStatement("SELECT sum(valor_mensal) as valor FROM rendimento WHERE ano = ?");
            statement.setInt(1, year);
            result = statement.executeQuery();

            if(result.next()){
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

        try{
            statement = conn.prepareStatement("SELECT sum(valor_mensal) as valor FROM rendimento WHERE mes = ? AND ano = ?");
            statement.setInt(1, month);
            statement.setInt(2, year);
            result = statement.executeQuery();

            if(result.next()){
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

    public double getValueMonthMensal(int year) throws SQLException{
        PreparedStatement statement = null;
        ResultSet result = null;
        double value = 0;

        try{
            statement = conn.prepareStatement("SELECT sum(valor_mensal) as valor FROM rendimento WHERE ano = ?");
            statement.setInt(1, year);
            result = statement.executeQuery();

            if(result.next()){
                value = result.getDouble("valor");
            }
            return value;
        } finally {
            BancoDados.finalizarStatement(statement);
            BancoDados.finalizarResultSet(result);
            BancoDados.desconectar();
        }
    }

    public ArrayList<Rendimento> getRendimentoByMonth(int month) throws SQLException{
        PreparedStatement statement = null;
        ResultSet result = null;
        ArrayList<Rendimento> list = new ArrayList<Rendimento>();

        try{
            statement = conn.prepareStatement("SELECT * FROM rendimento WHERE mes <= ? order by id");
            statement.setInt(1, month);
            result = statement.executeQuery();

            while(result.next()){
                Rendimento rendimento = new Rendimento();

                rendimento.setId(result.getInt("id"));
                rendimento.getCategoria().setId(result.getInt("id_categoria"));
                rendimento.setDescricao(result.getString("descricao"));
                rendimento.setValorMensal(result.getDouble("valor_mensal"));
                rendimento.setValorOcasional(result.getDouble("valor_ocasional"));
                rendimento.setMes(result.getInt("mes"));
                rendimento.setAno(result.getInt("ano"));
                rendimento.calcularValorRendimento();

                list.add(rendimento);
            }
        } finally {
            BancoDados.finalizarStatement(statement);
            BancoDados.finalizarResultSet(result);
            BancoDados.desconectar();
        }

        return list;
    }

    public ArrayList<Rendimento> getRendimentoByYear(int year) throws SQLException{
        PreparedStatement statement = null;
        ResultSet result = null;
        ArrayList<Rendimento> list = new ArrayList<Rendimento>();

        try{
            statement = conn.prepareStatement("SELECT * FROM rendimento WHERE ano = ? order by id");
            statement.setInt(1, year);
            result = statement.executeQuery();

            while(result.next()){
                Rendimento rendimento = new Rendimento();

                rendimento.setId(result.getInt("id"));
                rendimento.getCategoria().setId(result.getInt("id_categoria"));
                rendimento.setDescricao(result.getString("descricao"));
                rendimento.setValorMensal(result.getDouble("valor_mensal"));
                rendimento.setValorOcasional(result.getDouble("valor_ocasional"));
                rendimento.setMes(result.getInt("mes"));
                rendimento.setAno(result.getInt("ano"));
                rendimento.calcularValorRendimento();

                list.add(rendimento);
            }
        } finally {
            BancoDados.finalizarStatement(statement);
            BancoDados.finalizarResultSet(result);
            BancoDados.desconectar();
        }

        return list;
    }

    public ArrayList<Rendimento> getRendimentoByCategory(int category) throws SQLException{
        PreparedStatement statement = null;
        ResultSet result = null;
        ArrayList<Rendimento> list = new ArrayList<Rendimento>();

        try{
            statement = conn.prepareStatement("SELECT * FROM rendimento WHERE id_categoria = ? order by id");
            statement.setInt(1, category);
            result = statement.executeQuery();

            while(result.next()){
                Rendimento rendimento = new Rendimento();

                rendimento.setId(result.getInt("id"));
                rendimento.getCategoria().setId(result.getInt("id_categoria"));
                rendimento.setDescricao(result.getString("descricao"));
                rendimento.setValorMensal(result.getDouble("valor_mensal"));
                rendimento.setValorOcasional(result.getDouble("valor_ocasional"));
                rendimento.setMes(result.getInt("mes"));
                rendimento.setAno(result.getInt("ano"));
                rendimento.calcularValorRendimento();

                list.add(rendimento);
            }
            return list;
        } finally {
            BancoDados.finalizarStatement(statement);
            BancoDados.finalizarResultSet(result);
            BancoDados.desconectar();
        }
    }
}
