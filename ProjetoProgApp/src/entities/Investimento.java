package entities;

public class Investimento {
    private int id;
    private String descricao;
    private double valor_mensal, valor_ocasional;

    public Investimento() {
    }

    public Investimento(int id, String descricao, double valor_mensal, double valor_ocasional) {
        this.id = id;
        this.descricao = descricao;
        this.valor_mensal = valor_mensal;
        this.valor_ocasional = valor_ocasional;
    }

    public int getId() {
        return id;
    }

    public String getDescricao() {
        return descricao;
    }

    public double getValor_mensal() {
        return valor_mensal;
    }

    public double getValor_ocasional() {
        return valor_ocasional;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setValor_mensal(double valor_mensal) {
        this.valor_mensal = valor_mensal;
    }

    public void setValor_ocasional(double valor_ocasional) {
        this.valor_ocasional = valor_ocasional;
    }
}
