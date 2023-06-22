package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import db.BancoDados;
import entities.Investimento;

public class InvestimentoDao{
    private Connection conn;

    public InvestimentoDao(Connection conn) {
        this.conn = conn;
    }

    public void create(Investimento investimento) throws SQLException{
        PreparedStatement statement = null;
        
        try{
            statement = conn.prepareStatement(
                "INSERT INTO investimento (descricao, valor_mensal, valor_ocasional, mes, ano) VALUES (?, ?, ?, ?, ?)");
            statement.setString(1, investimento.getDescricao());
            statement.setDouble(2, investimento.getValorMensal());
            statement.setDouble(3, investimento.getValorOcasional());
            statement.setDouble(4, investimento.getMes());
            statement.setDouble(5, investimento.getAno());

            statement.executeUpdate();
        } finally {
            BancoDados.finalizarStatement(statement);
            BancoDados.desconectar();
        }
    }

    public ArrayList<Investimento> findAll() throws SQLException{
        PreparedStatement statement = null;
        ResultSet result = null;
        ArrayList<Investimento> list = new ArrayList<Investimento>();

        try{
            statement = conn.prepareStatement("SELECT * FROM investimento order by id");
            result = statement.executeQuery();

            while(result.next()){
                Investimento investimento = new Investimento();

                investimento.setId(result.getInt("id"));
                investimento.setDescricao(result.getString("descricao"));
                investimento.setValorMensal(result.getDouble("valor_mensal"));
                investimento.setValorOcasional(result.getDouble("valor_ocasional"));
                investimento.setMes(result.getInt("mes"));
                investimento.setAno(result.getInt("ano"));

                list.add(investimento);
            }
            return list;
        } finally {
            BancoDados.finalizarStatement(statement);
            BancoDados.finalizarResultSet(result);
            BancoDados.desconectar();
        }
    }

    public void update(Investimento investimento) throws SQLException{
        PreparedStatement statement = null;

        try{
        	System.out.println(investimento.getId());
            statement = conn.prepareStatement(
                "UPDATE investimento SET descricao = ?, valor_mensal = ?, valor_ocasional = ?, mes = ?, ano = ? WHERE id = ?");
            statement.setString(1, investimento.getDescricao());
            statement.setDouble(2, investimento.getValorMensal());
            statement.setDouble(3, investimento.getValorOcasional());
            statement.setDouble(4, investimento.getMes());
            statement.setDouble(5, investimento.getAno());
            statement.setInt(6, investimento.getId());

            statement.executeUpdate();
        } finally {
            BancoDados.finalizarStatement(statement);
            BancoDados.desconectar();
        }
    }

    public void delete(int idInvestimento) throws SQLException{
        PreparedStatement statement = null;

        try{
            statement = conn.prepareStatement("DELETE FROM investimento WHERE id = ?");
            statement.setInt(1, idInvestimento);

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

        try{
            statement = conn.prepareStatement("SELECT sum(valor_ocasional) as valor FROM investimento WHERE ano = ?");
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

    public double getValueYearMensal(int year) throws SQLException{
        PreparedStatement statement = null;
        ResultSet result = null;
        double value = 0;

        try{
            statement = conn.prepareStatement("SELECT sum(valor_mensal) as valor FROM investimento WHERE ano = ?");
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
            statement = conn.prepareStatement("SELECT sum(valor_ocasional) as valor FROM investimento WHERE mes = ? AND ano = ?");
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
            statement = conn.prepareStatement("SELECT sum(valor_mensal) as valor FROM investimento WHERE ano = ?");
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

    public ArrayList<Investimento> getInvestimentoByMonth(int month) throws SQLException{
        PreparedStatement statement = null;
        ResultSet result = null;
        ArrayList<Investimento> list = new ArrayList<Investimento>();

        try{
            statement = conn.prepareStatement("SELECT * FROM investimento WHERE mes <= ? order by id");
            statement.setInt(1, month);
            result = statement.executeQuery();

            while(result.next()){
                Investimento investimento = new Investimento();

                investimento.setId(result.getInt("id"));
                investimento.setDescricao(result.getString("descricao"));
                investimento.setValorMensal(result.getDouble("valor_mensal"));
                investimento.setValorOcasional(result.getDouble("valor_ocasional"));
                investimento.setMes(result.getInt("mes"));
                investimento.setAno(result.getInt("ano"));

                list.add(investimento);
            }
            return list;
        } finally {
            BancoDados.finalizarStatement(statement);
            BancoDados.finalizarResultSet(result);
            BancoDados.desconectar();
        }
    }

    public ArrayList<Investimento> getInvestimentoByYear(int year) throws SQLException{
        PreparedStatement statement = null;
        ResultSet result = null;
        ArrayList<Investimento> list = new ArrayList<Investimento>();

        try{
            statement = conn.prepareStatement("SELECT * FROM investimento WHERE ano = ? order by id");
            statement.setInt(1, year);
            result = statement.executeQuery();

            while(result.next()){
                Investimento investimento = new Investimento();

                investimento.setId(result.getInt("id"));
                investimento.setDescricao(result.getString("descricao"));
                investimento.setValorMensal(result.getDouble("valor_mensal"));
                investimento.setValorOcasional(result.getDouble("valor_ocasional"));
                investimento.setMes(result.getInt("mes"));
                investimento.setAno(result.getInt("ano"));

                list.add(investimento);
            }
            return list;
        } finally {
            BancoDados.finalizarStatement(statement);
            BancoDados.finalizarResultSet(result);
            BancoDados.desconectar();
        }
    }

}
