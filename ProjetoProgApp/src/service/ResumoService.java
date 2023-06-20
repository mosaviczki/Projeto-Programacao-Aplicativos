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

    // public ResumoAnual getResumoAnual(int ano) throws SQLException, IOException {
    //     ResumoAnual resumo = new ResumoAnual();
    //     resumo.setValorRendimento(new RendimentoService().getRendimentoTotal(ano));
    //     resumo.setValorDespesas(new DespesaService().getDespesaTotal(ano));
    //     resumo.setValorFundos(new FundoService().getFundoTotal(ano));
    //     resumo.setValorInvestimento(new InvestimentoService().getInvestimentoTotal(ano));


    //     return resumo;
    // }
}
