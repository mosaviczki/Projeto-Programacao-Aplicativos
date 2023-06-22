package entities;

public class Rendimento extends Lancamento {
    private double valorRendimento;

    public Rendimento() {
        super();
    }

    public double getValorRendimento() {
        return valorRendimento;
    }

    public void calcularValorRendimento() {
        this.valorRendimento = 0;

        if (super.getValorMensal() > 0) {
            this.valorRendimento += super.getValorMensal() * 12;
        }
        if (super.getValorOcasional() > 0) {
            this.valorRendimento += super.getValorOcasional();
        }
    }
}
