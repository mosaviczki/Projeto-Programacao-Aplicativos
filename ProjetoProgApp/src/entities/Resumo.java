package entities;

public abstract class Resumo {
    private double valorRendimentos;
    private double valorInvestimentos;
    private double valorFundos;
    private double valorDespesas;

    public Resumo() {
    }

    public abstract double getTotalDisponivel();
    public abstract double getFundoDespesasOcasionais();
    public abstract double getValorTotal();

    public double getValorRendimento() {
        return valorRendimentos;
    }

    public void setValorRendimento(double rendimento) {
        this.valorRendimentos = rendimento;
    }

    public double getValorInvestimento() {
        return valorInvestimentos;
    }

    public void setValorInvestimento(double valorInvestimento) {
        this.valorInvestimentos = valorInvestimento;
    }

    public double getValorFundos() {
        return valorFundos;
    }

    public void setValorFundos(double valorFundos) {
        this.valorFundos = valorFundos;
    }

    public double getValorDespesas() {
        return valorDespesas;
    }

    public void setValorDespesas(double valorDespesas) {
        this.valorDespesas = valorDespesas;
    }
}
