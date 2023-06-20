package entities;

public class ResumoMensal extends Resumo {
    public ResumoMensal() {
        super();
    }

    @Override
    public double getTotalDisponivel() {
        return super.getValorRendimento() - super.getValorInvestimento() - this.getFundoDespesasOcasionais();
    }

    @Override
    public double getFundoDespesasOcasionais() {
        return super.getValorFundos();
    }

    @Override
    public double getValorTotal() {
        return this.getTotalDisponivel() - super.getValorDespesas();
    }
}
