package entities;

public class Fundo {
    private int id;
    private String descricao;
    private double valorMensal, valorOcasional;

    public Fundo() {
    }

    public Fundo(int id, String descricao, double valorMensal, double valorOcasional) {
        this.id = id;
        this.descricao = descricao;
        this.valorMensal = valorMensal;
        this.valorOcasional = valorOcasional;
    }

    public int getId() {
        return id;
    }

    public String getDescricao() {
        return descricao;
    }

    public double getValorMensal() {
        return valorMensal;
    }

    public double getValorOcasional() {
        return valorOcasional;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setValorMensal(double valorMensal) {
        this.valorMensal = valorMensal;
    }

    public void setValorOcasional(double valorOcasional) {
        this.valorOcasional = valorOcasional;
    }

}
