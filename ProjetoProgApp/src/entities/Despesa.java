package entities;

public class Despesa {
    private int id, frequencia;
    private Categoria categoria;
    private Categoria subCategoria;
    private String descricao;
    private double valor;

    public Despesa() {
    }

    public Despesa(int id, int frequencia, Categoria categoria, Categoria subcategoria, String descricao,
            double valor) {
        this.id = id;
        this.frequencia = frequencia;
        this.subCategoria = subcategoria;
        this.categoria = categoria;
        this.descricao = descricao;
        this.valor = valor;
    }

    public int getId() {
        return id;
    }

    public int getFrequencia() {
        return frequencia;
    }

    public Categoria getSubCategoria() {
        return subCategoria;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public String getDescricao() {
        return descricao;
    }

    public double getValor() {
        return valor;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setFrequencia(int frequencia) {
        this.frequencia = frequencia;
    }

    public void setId_subcategoria(Categoria subCategoria) {
        this.subCategoria = subCategoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }
}
