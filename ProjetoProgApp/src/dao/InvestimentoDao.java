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
                "INSERT INTO investimento (descricao, valor_mensal, valor_ocasional) VALUES (?, ?, ?)");
            statement.setString(1, investimento.getDescricao());
            statement.setDouble(2, investimento.getValorMensal());
            statement.setDouble(3, investimento.getValorOcasional());

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
            statement = conn.prepareStatement(
                "UPDATE investimento SET descricao = ?, valor_mensal = ?, valor_ocasional = ? WHERE id = ?");
            statement.setString(1, investimento.getDescricao());
            statement.setDouble(2, investimento.getValorMensal());
            statement.setDouble(3, investimento.getValorOcasional());
            statement.setInt(4, investimento.getId());

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

}
