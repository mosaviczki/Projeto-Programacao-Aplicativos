package entities;

public class Investimento {
    private int id, mes, ano;
    private String descricao;
    private double valorMensal, valorOcasional;

    public Investimento() {
    }

    public Investimento(int id, String descricao, double valorMensal, double valorOcasional, int mes, int ano) {
        this.id = id;
        this.descricao = descricao;
        this.valorMensal = valorMensal;
        this.valorOcasional = valorOcasional;
        this.mes = mes;
        this.ano = ano;
    }

    public int getMes() {
		return mes;
	}

	public void setMes(int mes) {
		this.mes = mes;
	}

	public int getAno() {
		return ano;
	}

	public void setAno(int ano) {
		this.ano = ano;
	}

	public int getId() {
        return id;
    }

    public String getDescricao() {
        return descricao;
    }

    public double getValorMensal() {
        return valorMensal;
    }

    public double getValorOcasional() {
        return valorOcasional;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setValorMensal(double valorMensal) {
        this.valorMensal = valorMensal;
    }

    public void setValorOcasional(double valorOcasional) {
        this.valorOcasional = valorOcasional;
    }
}
