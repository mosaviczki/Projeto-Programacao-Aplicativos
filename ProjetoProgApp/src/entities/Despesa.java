package entities;

public class Despesa extends Lancamento {
    private double valorDespesa;
    
    public Despesa() {
        super();
    }

    public double getValorDespesa() {
        return valorDespesa;
    }

    public void calcularvalorDespesa() {
        this.valorDespesa = 0;

        if (super.getValorMensal() > 0) {
            this.valorDespesa += super.getValorMensal() * 12;
        }
        if (super.getValorOcasional() > 0) {
            this.valorDespesa += super.getValorOcasional();
        }
    }
}
