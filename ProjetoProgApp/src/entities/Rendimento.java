package entities;

public class Rendimento {
    private int id, frequencia;
    private Categoria categoria;
    private String descricao;
    private double valor_redimento;

    public Rendimento() {
    }

    public Rendimento(int id, int frequencia, Categoria categoria, String descricao, double valor_redimento) {
        this.id = id;
        this.frequencia = frequencia;
        this.categoria = categoria;
        this.descricao = descricao;
        this.valor_redimento = valor_redimento;
    }

    public int getId() {
        return id;
    }

    public int getFrequencia() {
        return frequencia;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public String getDescricao() {
        return descricao;
    }

    public double getValor_redimento() {
        return valor_redimento;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setFrequencia(int frequencia) {
        this.frequencia = frequencia;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setValor_redimento(double valor_redimento) {
        this.valor_redimento = valor_redimento;
    }
}
