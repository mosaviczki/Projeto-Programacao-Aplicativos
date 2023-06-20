package service;

import java.io.IOException;
import java.sql.SQLException;

import entities.ResumoAnual;
import entities.ResumoMensal;

public class ResumoService {
    public ResumoMensal getResumoMensal(int mes, int ano) throws SQLException, IOException {
        ResumoMensal resumo = new ResumoMensal();
        resumo.setValorRendimento(new RendimentoService().getRendimentoTotal(mes, ano));
        resumo.setValorDespesas(new DespesaService().getDespesaTotal(mes, ano));
        resumo.setValorFundos(new FundoService().getFundoTotal(mes, ano));
        resumo.setValorInvestimento(new InvestimentoService().getInvestimentoTotal(mes, ano));

        return resumo;
    }

    public ResumoAnual getResumoAnualMensal(int ano) throws SQLException, IOException {
        ResumoAnual resumo = new ResumoAnual();
        resumo.setValorRendimento(new RendimentoService().getRendimentoMensal(ano));
        resumo.setValorDespesas(new DespesaService().getDespesaMensal(ano));
        resumo.setValorFundos(new FundoService().getFundoMensal(ano));
        resumo.setValorInvestimento(new InvestimentoService().getInvestimentoMensal(ano));

        return resumo;
    }

    public ResumoAnual getResumoAnualOcasional(int ano) throws SQLException, IOException {
        ResumoAnual resumo = new ResumoAnual();
        resumo.setValorRendimento(new RendimentoService().getRendimentoOcasional(ano));
        resumo.setValorDespesas(new DespesaService().getDespesaOcasional(ano));
        resumo.setValorFundos(new FundoService().getFundoOcasional(ano));
        resumo.setValorInvestimento(new InvestimentoService().getInvestimentoOcasional(ano));

        return resumo;
    }
}
