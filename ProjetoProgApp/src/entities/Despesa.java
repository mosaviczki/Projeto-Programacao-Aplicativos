package entities;

public class Despesa {
    private int id, frequencia, id_subcategoria;
    private Categoria categoria;
    private String descricao;
    private double valor;

    public Despesa() {
    }

    public Despesa(int id, int frequencia, int id_subcategoria, Categoria categoria, String descricao, double valor) {
        this.id = id;
        this.frequencia = frequencia;
        this.id_subcategoria = id_subcategoria;
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

    public int getId_subcategoria() {
        return id_subcategoria;
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

    public void setId_subcategoria(int id_subcategoria) {
        this.id_subcategoria = id_subcategoria;
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
