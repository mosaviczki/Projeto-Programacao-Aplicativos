package entities;

import java.util.ArrayList;

public abstract class Resumo {
    private String descricao;
    private double total;
    private Rendimento rendimento;
    private Investimento investimento;
    private Fundo fundo;
    private ArrayList<Despesa> listaDespesas;

    public Resumo() {
        this.calcularTotal();
    }

    public abstract void calcularTotal();

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public Rendimento getRendimento() {
        return rendimento;
    }

    public void setRendimento(Rendimento rendimento) {
        this.rendimento = rendimento;
    }

    public Investimento getInvestimento() {
        return investimento;
    }

    public void setInvestimento(Investimento investimento) {
        this.investimento = investimento;
    }

    public Fundo getFundo() {
        return fundo;
    }

    public void setFundo(Fundo fundo) {
        this.fundo = fundo;
    }

    public ArrayList<Despesa> getListaDespesas() {
        return listaDespesas;
    }

    public void setListaDespesas(ArrayList<Despesa> listaDespesas) {
        this.listaDespesas = listaDespesas;
    }
}
