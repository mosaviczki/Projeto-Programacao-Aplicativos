package entities;

public class ResumoAnual extends Resumo {
    public ResumoAnual() {
        super();
    }

    @Override
    public double getTotalDisponivel() {
        return super.getValorRendimento() - super.getValorInvestimento();
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
