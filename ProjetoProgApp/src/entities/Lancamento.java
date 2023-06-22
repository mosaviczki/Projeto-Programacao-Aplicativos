package entities;

public class Lancamento {
    private int id, mes, ano;
    private String descricao;
    private double valorMensal;
    private double valorOcasional;
    private Categoria categoria;

    public Lancamento() {
        this.valorOcasional = 0;
        this.valorMensal = 0;
        this.categoria = new Categoria();
    }

    public int getId() {
        return id;
    }

    public int getMes() {
        return mes;
    }

    public int getAno() {
        return ano;
    }

    public double getValorOcasional() {
        return valorOcasional;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public String getDescricao() {
        return descricao;
    }

    public double getValorMensal() {
        return valorMensal;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public void setValorOcasional(double valorOcasional) {
        this.valorOcasional = valorOcasional;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setValorMensal(double valor) {
        this.valorMensal = valor;
    }
}
